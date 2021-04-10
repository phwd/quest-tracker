package com.facebook.acra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anrreport.ANRReport;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acraconfig.AcraConfig;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.common.exceptionhandler.CustomStackTracerInterface;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.common.exceptionhandler.ManagedExceptionHandler;
import com.facebook.common.process.ProcessName;
import com.facebook.debug.log.BLog;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

public class ACRA {
    public static final String LOG_TAG = ACRA.class.getSimpleName();
    @GuardedBy("sANRDetectorLock")
    @Nullable
    private static IANRDetector mANRDetector;
    @GuardedBy("sANRDetectorLock")
    @Nullable
    private static ANRReport mANRReport;
    private static AcraReportingConfig mConfig;
    private static String mReportHost;
    private static FlexibleReportSender mReportSender;
    private static final Object sANRDetectorLock = new Object();
    @Nullable
    private static AppStateUpdater sAppStateUpdater;
    private static boolean sInitialized;
    @GuardedBy("sNativeLibraryLoadingLock")
    private static boolean sNativeLibraryLoaded = false;
    private static final Object sNativeLibraryLoadingLock = new Object();

    public static ErrorReporter init(AcraReportingConfig config) {
        return init(config, 0, null, null);
    }

    public static ErrorReporter init(AcraReportingConfig config, long appStartTickTimeMs, @Nullable AppStateUpdater appStateUpdater, @Nullable ErrorReporter.ExcludedReportObserver excludedReportObserver) {
        sInitialized = true;
        BLog.d(LOG_TAG, "ACRA init; app state monitoring:%s ;reportURL: %s", appStateUpdater != null ? "yes" : "no", config.crashReportUrl());
        ErrorReporter errorReporter = ErrorReporter.getInstance();
        if (appStartTickTimeMs > 0) {
            errorReporter.setAppStartTickTimeMs(appStartTickTimeMs);
        }
        if (mConfig == null) {
            mConfig = config;
            Context applicationContext = mConfig.getApplicationContext();
            BLog.d(LOG_TAG, "ACRA is enabled for process %s, initializing...", getProcessName());
            deleteHostsFileIfEmpty(applicationContext);
            if (excludedReportObserver != null) {
                errorReporter.setExcludedReportObserver(excludedReportObserver);
            }
            errorReporter.init(mConfig);
            Throwable earlyInitFailure = null;
            try {
                errorReporter.initFallible();
            } catch (Throwable ex) {
                earlyInitFailure = ex;
            }
            mReportSender = mConfig.createReportSender();
            errorReporter.setReportSender(mReportSender);
            initSenderHost(applicationContext);
            initSenderSkipCertChecks(applicationContext);
            errorReporter.checkReportsOnApplicationStart();
            if (mConfig.shouldStopAnrDetectorOnErrorReporting()) {
                ExceptionHandlerManager.addExceptionHandler(new ManagedExceptionHandler() {
                    /* class com.facebook.acra.ACRA.AnonymousClass1 */

                    @Override // com.facebook.common.exceptionhandler.ManagedExceptionHandler
                    public void handleUncaughtException(Thread t, Throwable e, @Nullable CustomStackTracerInterface.CustomStackTrace customStackTrace) {
                        ACRA.stopANRDetector();
                    }
                }, 100);
            }
            ExceptionHandlerManager.addExceptionHandler(errorReporter, 0);
            if (earlyInitFailure != null) {
                errorReporter.reportErrorAndTerminate(Thread.currentThread(), earlyInitFailure);
            }
            if (mConfig.shouldInstallPeriodicReporter()) {
                installPeriodicReporter(mConfig.getApplicationContext(), errorReporter);
            }
        }
        sAppStateUpdater = appStateUpdater;
        maybeInitializeAndStartANRDetector(errorReporter);
        if (mConfig.shouldInitReportSource()) {
            initReportSource();
        }
        return errorReporter;
    }

    @Nullable
    private static String getProperty(String key) {
        String prop = SystemPropertiesInternal.get(key);
        if (prop == null || prop.equals("")) {
            return System.getProperty(key);
        }
        return prop;
    }

    private static void initReportSource() {
        String reportSourceOverride = getProperty("fb.report_source");
        if (reportSourceOverride != null && !reportSourceOverride.equals("")) {
            BLog.i("Overriding report_source from prop: %s", reportSourceOverride);
            ErrorReporter.putCustomData("report_source", reportSourceOverride);
            ErrorReporter.putCustomData("report_source_ref", "");
        }
        JSONObject reportSourceRefOverride = getReportSourceRefOverride(mConfig.getApplicationContext());
        if (reportSourceRefOverride != null) {
            String furyTracesFileName = getProperty("fb.fury_stacktraces_filename");
            if (furyTracesFileName != null && !furyTracesFileName.equals("")) {
                addFuryTracesToReportSourceRef(reportSourceRefOverride, furyTracesFileName);
            }
            BLog.i("Overriding report_source_ref from file: %s", reportSourceRefOverride.toString());
            ErrorReporter.putCustomData("report_source_ref", reportSourceRefOverride.toString());
        }
        String mobileBuildTargetOverride = getProperty("fb.testing.build_target");
        if (mobileBuildTargetOverride != null && !mobileBuildTargetOverride.equals("")) {
            BLog.i("Overriding mobile_build_target from prop: %s", mobileBuildTargetOverride);
            ErrorReporter.putCustomData("mobile_build_target", mobileBuildTargetOverride);
        }
    }

    private static void addFuryTracesToReportSourceRef(JSONObject reportSourceRef, String furyTracesFileName) {
        String furyTraces = getFuryTraces(mConfig.getApplicationContext(), furyTracesFileName);
        if (furyTraces != null && !furyTraces.equals("")) {
            try {
                JSONObject jsonContent = new JSONObject(furyTraces);
                BLog.i("add furyTraces from file: %s", furyTraces);
                reportSourceRef.put("fury_traces", jsonContent);
            } catch (JSONException e) {
                BLog.w(LOG_TAG, "Invalid fury file contents: %s", furyTraces, e);
            }
        }
    }

    @Nullable
    private static String getFuryTraces(Context ctx, String furyTracesFileName) {
        if (ctx == null) {
            return null;
        }
        File furyFile = new File(ctx.getFilesDir(), furyTracesFileName);
        if (!furyFile.exists()) {
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(furyFile);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line);
                    } else {
                        String sb2 = sb.toString();
                        fis.close();
                        return sb2;
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (IOException e) {
            BLog.w(LOG_TAG, "Failed to read fury traces file", e);
            return null;
        }
        throw th;
    }

    @Nullable
    private static JSONObject getReportSourceRefOverride(Context ctx) {
        File appErrorReportingDir = ctx.getDir("errorreporting", 0);
        if (!appErrorReportingDir.exists()) {
            return null;
        }
        File reportSourceRefDir = new File(appErrorReportingDir, "report_source");
        if (!reportSourceRefDir.exists()) {
            return null;
        }
        File reportSourceRefFile = new File(reportSourceRefDir, "report_source_ref.txt");
        if (!reportSourceRefFile.exists()) {
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(reportSourceRefFile);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line);
                    } else {
                        JSONObject jsonifyReportSourceRef = jsonifyReportSourceRef(sb.toString());
                        fis.close();
                        return jsonifyReportSourceRef;
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (IOException e) {
            BLog.w(LOG_TAG, "Failed to read report source ref override file", e);
            return null;
        }
        throw th;
    }

    @Nullable
    private static JSONObject jsonifyReportSourceRef(String reportSourceRef) {
        String validationResult;
        BLog.d(LOG_TAG, "Validating report source ref override file contents: %s", reportSourceRef);
        if (reportSourceRef.startsWith("report_source_ref=")) {
            validationResult = reportSourceRef.replace("report_source_ref=", "");
        } else {
            validationResult = reportSourceRef;
        }
        try {
            return new JSONObject(validationResult);
        } catch (JSONException e) {
            BLog.w(LOG_TAG, "Invalid report source ref override file contents: %s", validationResult, e);
            return null;
        }
    }

    @SuppressLint({"BadMethodUse-java.util.concurrent.Executors.newScheduledThreadPool"})
    private static void installPeriodicReporter(Context ctx, final ErrorReporter errorReporter) {
        int intervalMins = AcraConfig.nativeCrashPeriodicReportIntervalMins(ctx);
        if (intervalMins > 0) {
            Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
                /* class com.facebook.acra.ACRA.AnonymousClass3 */

                public void run() {
                    ErrorReporter.this.checkNativeReports();
                }
            }, (long) intervalMins, (long) intervalMins, TimeUnit.MINUTES);
        }
    }

    public static String getProcessName() {
        String name = ProcessName.current().getFullName();
        if (name == null) {
            return BuildConfig.VERSION_NAME;
        }
        return name;
    }

    private static boolean shouldRunANRDetector(String processName) {
        boolean isMainProcess;
        boolean z = false;
        if (getFlagValue("record_signal_time")) {
            return true;
        }
        if (!processName.contains(":")) {
            isMainProcess = true;
        } else {
            isMainProcess = false;
        }
        if (isMainProcess || (processName.contains(":browser") && getFlagValue("run_anr_detector_on_browser_process"))) {
            z = true;
        }
        return z;
    }

    @SuppressLint({"BadMethodUse-java.lang.Thread.start"})
    private static void maybeInitializeAndStartANRDetector(ErrorReporter errorReporter) {
        Context context = getConfig().getApplicationContext();
        String processName = getProcessName();
        if (!shouldRunANRDetector(processName)) {
            BLog.e(LOG_TAG, "Skipping ANR Detector for process: %s", processName);
            return;
        }
        BLog.e(LOG_TAG, "Initializing ANR detector for process: %s", processName);
        boolean shouldStartANRDetector = mConfig.shouldStartANRDetector();
        BLog.d(LOG_TAG, "ANRDetector will start: %b", Boolean.valueOf(shouldStartANRDetector));
        if (shouldStartANRDetector) {
            initializeAnrDetector(context, errorReporter, processName);
            if (mANRDetector != null) {
                mANRDetector.start();
                BLog.d(LOG_TAG, "ANRDetector started");
            }
        }
    }

    private static void initializeAnrDetector(Context context, ErrorReporter errorReporter, String processName) {
        int anrDetectorId = getIntValue("android_anr_detector_to_use");
        int errorMonitorCheckInterval = getIntValue("error_monitor_check_interval");
        synchronized (sANRDetectorLock) {
            mANRReport = new ANRReport(context, errorReporter);
            mANRDetector = mConfig.createANRDetector(anrDetectorId, new ANRDetectorConfig(context, processName, mANRReport, sAppStateUpdater, new Handler(Looper.getMainLooper()), anrDetectorId, mConfig.isInternalBuild(), getFlagValue("should_report_soft_errors"), getFlagValue("log_on_signal_handler"), getFlagValue("avoid_mutex_on_signal_handler"), getIntValue("anr_recovery_timeout"), getFlagValue("record_signal_time"), getCachedShouldUploadANRReports(), errorReporter.getAppVersionCode(), errorReporter.getAppVersionName(), errorReporter.getSigquitTracesPath(), errorReporter.getSigquitTracesExtension(), getIntValue("foreground_check_period")), errorMonitorCheckInterval);
        }
    }

    public static AcraReportingConfig getConfig() {
        return mConfig;
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValue(String flagName) {
        return getFlagValue(getConfig().getApplicationContext().getSharedPreferences("acra_flags_store", 0), flagName, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValueDefaultTrue(String flagName) {
        return getFlagValue(getConfig().getApplicationContext().getSharedPreferences("acra_flags_store", 0), flagName, true);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static int getIntValue(String preferenceName) {
        return getIntValue(getConfig().getApplicationContext().getSharedPreferences("acra_flags_store", 0), preferenceName);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValue(SharedPreferences sharedPrefs, String flagName, boolean defaultValue) {
        return sharedPrefs.getBoolean(flagName, defaultValue);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static int getIntValue(SharedPreferences sharedPrefs, String prefName) {
        return sharedPrefs.getInt(prefName, 0);
    }

    public static boolean getCachedShouldLogProcessPositionInAnrTraceFile() {
        return getFlagValue("log_position_anr_trace_file");
    }

    public static boolean getCachedShouldUploadSystemANRTraces() {
        return getFlagValue("should_upload_system_anr_traces_gk_cached");
    }

    public static boolean getCachedShouldDedupDiskPersistence() {
        return getFlagValue("should_dedup_disk_persistence_gk_cached");
    }

    public static boolean getCachedShouldUploadANRReports() {
        return getFlagValueDefaultTrue("anr_gk_cached");
    }

    public static void stopANRDetector() {
        if (mANRDetector != null) {
            mANRDetector.stop(null);
        }
    }

    private static void deleteHostsFileIfEmpty(Context context) {
        try {
            File file = context.getFileStreamPath("report_host.txt");
            if (file.exists()) {
                if (!file.canRead() || !file.canWrite()) {
                    BLog.e(LOG_TAG, "cannot read or write host file");
                } else if (file.length() == 0 && !context.deleteFile("report_host.txt")) {
                    BLog.e(LOG_TAG, "could not delete empty host file");
                }
            }
        } catch (SecurityException e) {
            BLog.e(LOG_TAG, e, "could not delete empty host file: ");
        }
    }

    private static void initSenderHost(Context context) {
        Throwable th;
        IOException e;
        BufferedReader reader = null;
        try {
            File file = context.getFileStreamPath("report_host.txt");
            if (!file.canRead()) {
                closeStreamNoException(null);
                return;
            }
            BufferedReader reader2 = new BufferedReader(new FileReader(file));
            try {
                String line = reader2.readLine();
                String host = line == null ? null : line.trim();
                BLog.d(LOG_TAG, "ACRA read host from host file %s", host);
                if (!TextUtils.isEmpty(host)) {
                    BLog.i(LOG_TAG, "setting crash reporting host to %s", host);
                    mReportSender.setHost(host);
                    mReportHost = host;
                }
                closeStreamNoException(reader2);
            } catch (IOException e2) {
                e = e2;
                reader = reader2;
                try {
                    BLog.i(LOG_TAG, e, "could not read host file: ");
                    closeStreamNoException(reader);
                } catch (Throwable th2) {
                    th = th2;
                    closeStreamNoException(reader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                closeStreamNoException(reader);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            BLog.i(LOG_TAG, e, "could not read host file: ");
            closeStreamNoException(reader);
        }
    }

    private static void initSenderSkipCertChecks(Context context) {
        mReportSender.setSkipSslCertsChecks(context.getFileStreamPath("skip_cert_checks.txt").exists());
    }

    private static void closeStreamNoException(@Nullable Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                BLog.e(LOG_TAG, e, "Error while closing stream: ");
            }
        }
    }
}
