package com.facebook.acra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anrreport.ANRReport;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.util.NativeProcFileReader;
import com.facebook.acra.util.NoSync;
import com.facebook.acraconfig.AcraConfig;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.exceptionhandler.CustomStackTracerInterface;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.common.exceptionhandler.ManagedExceptionHandler;
import com.facebook.common.process.ProcessName;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.nightwatch.Nightwatch;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

public class ACRA {
    private static final String ACRA_FLAGS_STORE = "acra_flags_store";
    private static final String ANDROID_ANR_DETECTOR_TO_USE = "android_anr_detector_to_use";
    private static final String ANR_FOREGROUND_CHECK_PERIOD = "foreground_check_period";
    private static final String ANR_RECOVERY_TIMEOUT = "anr_recovery_timeout";
    public static final String BREAKPAD_LIB_NAME = "breakpad_lib_name";
    private static final String ERROR_MONITOR_CHECK_INTERVAL = "error_monitor_check_interval";
    private static final String FORCE_NIGHTWATCH_PROPERTY_NAME = "com.facebook.force_nightwatch";
    public static final int HYBRID_ANR_DETECTOR = 4;
    public static final String IS_FIRST_RUN_AFTER_UPGRADE = "is_first_run_after_upgrade";
    public static final String LOGCAT_FILE_KEY = "logcatFileName";
    public static final String LOG_TAG = "ACRA";
    public static final int MULTI_SIGNAL_ANR_DETECTOR = 6;
    public static final int PROCESS_ERROR_MONITOR_ANR_DETECTOR = 5;
    private static final String REPORT_HOST_FILE_NAME = "report_host.txt";
    private static final String RUN_ANR_DETECTOR_ON_BROWSER_PROCESS = "run_anr_detector_on_browser_process";
    public static final String SESSION_ID_KEY = "session_id";
    private static final String SHOULD_AVOID_MUTEX_ON_SIGNAL_HANDLER = "avoid_mutex_on_signal_handler";
    private static final String SHOULD_DEDUP_DISK_PERSISTENCE_GK_CACHED = "should_dedup_disk_persistence_gk_cached";
    private static final String SHOULD_LOG_ON_SIGNAL_HANDLER = "log_on_signal_handler";
    private static final String SHOULD_LOG_PROCESS_POSITION_IN_ANR_TRACE_FILE = "log_position_anr_trace_file";
    private static final String SHOULD_RECORD_SIGNAL_TIME = "record_signal_time";
    private static final String SHOULD_REPORT_SOFT_ERRORS = "should_report_soft_errors";
    private static final String SHOULD_UPLOAD_ANR_REPORTS = "anr_gk_cached";
    private static final String SHOULD_UPLOAD_SYSTEM_ANR_TRACES_GK_CACHED = "should_upload_system_anr_traces_gk_cached";
    public static final int SIGQUIT_BASED_ANR_DETECTOR = 3;
    private static final String SKIP_SSL_CERT_CHECKS_FILE_NAME = "skip_cert_checks.txt";
    @GuardedBy("sANRDetectorLock")
    @Nullable
    private static IANRDetector mANRDetector = null;
    @GuardedBy("sANRDetectorLock")
    @Nullable
    private static ANRReport mANRReport = null;
    private static AcraReportingConfig mConfig = null;
    private static String mReportHost = null;
    private static FlexibleReportSender mReportSender = null;
    private static final Object sANRDetectorLock = new Object();
    @Nullable
    private static AppStateUpdater sAppStateUpdater = null;
    private static boolean sInitialized = false;
    @GuardedBy("sNativeLibraryLoadingLock")
    private static boolean sNativeLibraryLoaded = false;
    private static final Object sNativeLibraryLoadingLock = new Object();

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig) {
        return init(acraReportingConfig, 0, null, null);
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig, long j) {
        return init(acraReportingConfig, j, null, null);
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig, long j, @Nullable AppStateUpdater appStateUpdater) {
        return init(acraReportingConfig, j, appStateUpdater, null);
    }

    public static boolean isInitialized() {
        return sInitialized;
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig, long j, @Nullable AppStateUpdater appStateUpdater, @Nullable ErrorReporter.ExcludedReportObserver excludedReportObserver) {
        sInitialized = true;
        BLog.d(LOG_TAG, "ACRA init; app state monitoring:%s ;reportURL: %s", appStateUpdater != null ? "yes" : "no", acraReportingConfig.crashReportUrl());
        ErrorReporter instance = ErrorReporter.getInstance();
        if (j > 0) {
            instance.setAppStartTickTimeMs(j);
        }
        if (mConfig == null) {
            mConfig = acraReportingConfig;
            Context applicationContext = mConfig.getApplicationContext();
            BLog.d(LOG_TAG, "ACRA is enabled for process %s, initializing...", getProcessName());
            deleteHostsFileIfEmpty(applicationContext);
            if (excludedReportObserver != null) {
                instance.setExcludedReportObserver(excludedReportObserver);
            }
            instance.init(mConfig);
            Throwable th = null;
            try {
                instance.initFallible();
            } catch (Throwable th2) {
                th = th2;
            }
            mReportSender = mConfig.createReportSender();
            instance.setReportSender(mReportSender);
            initSenderHost(applicationContext);
            initSenderSkipCertChecks(applicationContext);
            instance.checkReportsOnApplicationStart();
            if (mConfig.shouldStopAnrDetectorOnErrorReporting()) {
                ExceptionHandlerManager.addExceptionHandler(new ManagedExceptionHandler() {
                    /* class com.facebook.acra.ACRA.AnonymousClass1 */

                    @Override // com.facebook.common.exceptionhandler.ManagedExceptionHandler
                    public void handleUncaughtException(Thread thread, Throwable th, @Nullable CustomStackTracerInterface.CustomStackTrace customStackTrace) {
                        ACRA.stopANRDetector();
                    }
                }, 100);
            }
            ExceptionHandlerManager.addExceptionHandler(instance, 0);
            if (th != null) {
                instance.reportErrorAndTerminate(Thread.currentThread(), th);
            }
            if (mConfig.shouldInstallPeriodicReporter()) {
                installPeriodicReporter(mConfig.getApplicationContext(), instance);
            }
        }
        sAppStateUpdater = appStateUpdater;
        maybeInitializeAndStartANRDetector(instance);
        if (mConfig.shouldInitReportSource()) {
            initReportSource();
        }
        return instance;
    }

    @Nullable
    private static String getProperty(String str) {
        String str2 = SystemPropertiesInternal.get(str);
        return (str2 == null || str2.equals("")) ? System.getProperty(str) : str2;
    }

    private static void initReportSource() {
        String property = getProperty(ErrorReportingConstants.REPORT_SOURCE_OVERRIDE_PROP);
        if (property != null && !property.equals("")) {
            BLog.i("Overriding report_source from prop: %s", property);
            ErrorReporter.putCustomData("report_source", property);
            ErrorReporter.putCustomData(ErrorReportingConstants.REPORT_SOURCE_REF, "");
        }
        JSONObject reportSourceRefOverride = getReportSourceRefOverride(mConfig.getApplicationContext());
        if (reportSourceRefOverride != null) {
            String property2 = getProperty(ErrorReportingConstants.REPORT_FURY_TRACES_FILE_PROP);
            if (property2 != null && !property2.equals("")) {
                addFuryTracesToReportSourceRef(reportSourceRefOverride, property2);
            }
            BLog.i("Overriding report_source_ref from file: %s", reportSourceRefOverride.toString());
            ErrorReporter.putCustomData(ErrorReportingConstants.REPORT_SOURCE_REF, reportSourceRefOverride.toString());
        }
        String property3 = getProperty("fb.testing.build_target");
        if (property3 != null && !property3.equals("")) {
            BLog.i("Overriding mobile_build_target from prop: %s", property3);
            ErrorReporter.putCustomData("mobile_build_target", property3);
        }
    }

    private static void addFuryTracesToReportSourceRef(JSONObject jSONObject, String str) {
        String furyTraces = getFuryTraces(mConfig.getApplicationContext(), str);
        if (furyTraces != null && !furyTraces.equals("")) {
            try {
                JSONObject jSONObject2 = new JSONObject(furyTraces);
                BLog.i("add furyTraces from file: %s", furyTraces);
                jSONObject.put("fury_traces", jSONObject2);
            } catch (JSONException e) {
                BLog.w(LOG_TAG, "Invalid fury file contents: %s", furyTraces, e);
            }
        }
    }

    @Nullable
    private static String getFuryTraces(Context context, String str) {
        if (context == null) {
            return null;
        }
        File file = new File(context.getFilesDir(), str);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        String sb2 = sb.toString();
                        fileInputStream.close();
                        return sb2;
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (IOException e) {
            BLog.w(LOG_TAG, "Failed to read fury traces file", e);
            return null;
        }
        throw th;
    }

    @Nullable
    private static JSONObject getReportSourceRefOverride(Context context) {
        File dir = context.getDir(ErrorReportingConstants.APP_ERROR_REPORTING_DIR_NAME, 0);
        if (!dir.exists()) {
            return null;
        }
        File file = new File(dir, "report_source");
        if (!file.exists()) {
            return null;
        }
        File file2 = new File(file, ErrorReportingConstants.REPORT_SOURCE_REF_FILE_NAME);
        if (!file2.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file2);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        JSONObject jsonifyReportSourceRef = jsonifyReportSourceRef(sb.toString());
                        fileInputStream.close();
                        return jsonifyReportSourceRef;
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (IOException e) {
            BLog.w(LOG_TAG, "Failed to read report source ref override file", e);
            return null;
        }
        throw th;
    }

    @Nullable
    private static JSONObject jsonifyReportSourceRef(String str) {
        BLog.d(LOG_TAG, "Validating report source ref override file contents: %s", str);
        if (str.startsWith(ErrorReportingConstants.REPORT_SOURCE_REF_LACRIMA_PREFIX)) {
            str = str.replace(ErrorReportingConstants.REPORT_SOURCE_REF_LACRIMA_PREFIX, "");
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            BLog.w(LOG_TAG, "Invalid report source ref override file contents: %s", str, e);
            return null;
        }
    }

    public static void onSplashScreenDismissed() {
        ErrorReporter.putCustomData(ErrorReportingConstants.SPLASH_SCREEN_DISMISSED, "true");
    }

    public static void customDelayedMessagesSent() {
        ErrorReporter.putCustomData(ErrorReportingConstants.CUSTOM_DELAYED_MESSAGES_SENT, "true");
    }

    @Nullable
    public static ANRReport getANRReport() {
        return mANRReport;
    }

    public static void safeToLoadNativeLibraries(Context context) {
        synchronized (sNativeLibraryLoadingLock) {
            if (!sNativeLibraryLoaded) {
                loadAcraNativeLibrary(context);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void nativeLibrarySuccessfullyLoaded(Context context) {
        boolean shouldRunNightwatch = shouldRunNightwatch(context);
        boolean shouldMonitorResourcesOnNightwatch = AcraConfig.shouldMonitorResourcesOnNightwatch(context);
        int nightwatchTickInfoCount = AcraConfig.getNightwatchTickInfoCount(context);
        if (shouldRunNightwatch || shouldMonitorResourcesOnNightwatch || nightwatchTickInfoCount > 0) {
            String processName = getProcessName();
            if (!processName.contains(":")) {
                Nightwatch.NightwatchConfigs nightwatchConfigs = new Nightwatch.NightwatchConfigs(shouldRunNightwatch, AcraConfig.shouldUseSetSidOnNightwatch(context), AcraConfig.shouldUseLssExecOnNightwatch(context), AcraConfig.shouldUseMmapOnNightwatch(context), shouldMonitorResourcesOnNightwatch, AcraConfig.monitorResourcesIntervalMs(context), nightwatchTickInfoCount, AcraConfig.shouldNightwatchSplitMmap(context), AcraConfig.getNightwatchMmapUpdateMinIntervalMs(context), AcraConfig.getShouldNightWatchUseFastOrCriticalJniMethods(context));
                if (AcraConfig.shouldUseAslSessionIdOnNightwatch(context)) {
                    Nightwatch.startWatcher(context, processName, getConfig().getSessionId(), nightwatchConfigs);
                } else {
                    Nightwatch.startWatcher(context, processName, nightwatchConfigs);
                }
            }
        }
        synchronized (sANRDetectorLock) {
            if (mANRDetector != null) {
                mANRDetector.nativeLibraryLoaded(getFlagValue(SHOULD_UPLOAD_ANR_REPORTS));
            }
        }
        NativeProcFileReader.nativeLibraryLoaded();
        if (AcraConfig.disableFSSyncSyscalls(context)) {
            NoSync.disableFSSync(AcraConfig.useFSSyncFastHooks(context));
        }
        String shortPrivateName = ProcessName.current().getShortPrivateName();
        if ("videoplayer".equalsIgnoreCase(shortPrivateName) && AcraConfig.disableFSSyncSyscallsVps(context)) {
            NoSync.disableFSSync(AcraConfig.useFSSyncFastHooks(context));
        }
        if ("remotecodec".equalsIgnoreCase(shortPrivateName)) {
            NoSync.disableFSSync(AcraConfig.useFSSyncFastHooks(context));
        }
    }

    private static boolean shouldRunNightwatch(Context context) {
        if (BuildConstants.isInternalBuild()) {
            return getSystemProperty(FORCE_NIGHTWATCH_PROPERTY_NAME, false);
        }
        return AcraConfig.shouldEnableNightwatch(context);
    }

    private static boolean getSystemProperty(String str, boolean z) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Boolean) cls.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls, str, Boolean.valueOf(z))).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return z;
        }
    }

    private static void loadAcraNativeLibrary(final Context context) {
        new Thread(new Runnable() {
            /* class com.facebook.acra.ACRA.AnonymousClass2 */

            public void run() {
                try {
                    synchronized (ACRA.sNativeLibraryLoadingLock) {
                        NativeLoader.loadLibrary("acra");
                        boolean unused = ACRA.sNativeLibraryLoaded = true;
                    }
                    ACRA.nativeLibrarySuccessfullyLoaded(context);
                } catch (UnsatisfiedLinkError e) {
                    BLog.d(ACRA.LOG_TAG, e, "Failed to load libacra");
                }
            }
        }).start();
    }

    @SuppressLint({"BadMethodUse-java.util.concurrent.Executors.newScheduledThreadPool"})
    private static void installPeriodicReporter(Context context, final ErrorReporter errorReporter) {
        int nativeCrashPeriodicReportIntervalMins = AcraConfig.nativeCrashPeriodicReportIntervalMins(context);
        if (nativeCrashPeriodicReportIntervalMins > 0) {
            long j = (long) nativeCrashPeriodicReportIntervalMins;
            Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
                /* class com.facebook.acra.ACRA.AnonymousClass3 */

                public void run() {
                    ErrorReporter.this.checkNativeReports();
                }
            }, j, j, TimeUnit.MINUTES);
        }
    }

    public static String getProcessName() {
        String fullName = ProcessName.current().getFullName();
        return fullName == null ? "unknown" : fullName;
    }

    private static boolean shouldRunANRDetector(String str) {
        if (getFlagValue(SHOULD_RECORD_SIGNAL_TIME) || (!str.contains(":"))) {
            return true;
        }
        if (!str.contains(":browser") || !getFlagValue(RUN_ANR_DETECTOR_ON_BROWSER_PROCESS)) {
            return false;
        }
        return true;
    }

    @SuppressLint({"BadMethodUse-java.lang.Thread.start"})
    private static void maybeInitializeAndStartANRDetector(ErrorReporter errorReporter) {
        Context applicationContext = getConfig().getApplicationContext();
        String processName = getProcessName();
        if (!shouldRunANRDetector(processName)) {
            BLog.i(LOG_TAG, "Skipping ANR Detector for process: %s", processName);
            return;
        }
        BLog.i(LOG_TAG, "OK: initializing ANR detector for process: %s", processName);
        boolean shouldStartANRDetector = mConfig.shouldStartANRDetector();
        BLog.d(LOG_TAG, "ANRDetector will start: %b", Boolean.valueOf(shouldStartANRDetector));
        if (shouldStartANRDetector) {
            initializeAnrDetector(applicationContext, errorReporter, processName);
            IANRDetector iANRDetector = mANRDetector;
            if (iANRDetector != null) {
                iANRDetector.start();
                BLog.d(LOG_TAG, "ANRDetector started");
            }
        }
    }

    private static void initializeAnrDetector(Context context, ErrorReporter errorReporter, String str) {
        int intValue = getIntValue(ANDROID_ANR_DETECTOR_TO_USE);
        int intValue2 = getIntValue(ERROR_MONITOR_CHECK_INTERVAL);
        synchronized (sANRDetectorLock) {
            mANRReport = new ANRReport(context, errorReporter);
            mANRDetector = mConfig.createANRDetector(intValue, new ANRDetectorConfig(context, str, mANRReport, sAppStateUpdater, new Handler(Looper.getMainLooper()), intValue, mConfig.isInternalBuild(), getFlagValue(SHOULD_REPORT_SOFT_ERRORS), getFlagValue(SHOULD_LOG_ON_SIGNAL_HANDLER), getFlagValue(SHOULD_AVOID_MUTEX_ON_SIGNAL_HANDLER), getIntValue(ANR_RECOVERY_TIMEOUT), getFlagValue(SHOULD_RECORD_SIGNAL_TIME), getCachedShouldUploadANRReports(), errorReporter.getAppVersionCode(), errorReporter.getAppVersionName(), errorReporter.getSigquitTracesPath(), errorReporter.getSigquitTracesExtension(), getIntValue(ANR_FOREGROUND_CHECK_PERIOD)), intValue2);
        }
    }

    public static int getAnrDetectorId() {
        return getIntValue(ANDROID_ANR_DETECTOR_TO_USE);
    }

    public static AcraReportingConfig getConfig() {
        return mConfig;
    }

    public static void setReportHost(String str) {
        mReportSender.setHost(str);
        writeSenderHost(str);
    }

    public static void setSkipSslCertChecks(boolean z) {
        mReportSender.setSkipSslCertsChecks(z);
        writeSkipCertChecksFile(z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00b8, code lost:
        r0.setANRDataProvider(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00bb, code lost:
        if (r2 == false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00bd, code lost:
        r6 = com.facebook.acra.ACRA.sNativeLibraryLoadingLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00bf, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r3 = com.facebook.acra.ACRA.sNativeLibraryLoaded;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00c2, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c7, code lost:
        if (r3 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c9, code lost:
        com.facebook.acra.ACRA.mANRDetector.nativeLibraryLoaded(getFlagValue(com.facebook.acra.ACRA.SHOULD_UPLOAD_ANR_REPORTS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setANRDataProvider(com.facebook.acra.anr.ANRDataProvider r6) {
        /*
        // Method dump skipped, instructions count: 216
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ACRA.setANRDataProvider(com.facebook.acra.anr.ANRDataProvider):void");
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static void updateCachedFlagIfNeeded(String str, boolean z) {
        SharedPreferences sharedPreferences = getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0);
        if (getFlagValue(sharedPreferences, str, false) != z) {
            sharedPreferences.edit().putBoolean(str, z).apply();
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static void updateCachedIntIfNeeded(String str, int i) {
        SharedPreferences sharedPreferences = getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0);
        if (getIntValue(sharedPreferences, str) != i) {
            sharedPreferences.edit().putInt(str, i).apply();
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValue(String str) {
        return getFlagValue(getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0), str, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValueDefaultTrue(String str) {
        return getFlagValue(getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0), str, true);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static int getIntValue(String str) {
        return getIntValue(getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0), str);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValue(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences.getBoolean(str, z);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static int getIntValue(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.getInt(str, 0);
    }

    public static boolean getCachedShouldLogProcessPositionInAnrTraceFile() {
        return getFlagValue(SHOULD_LOG_PROCESS_POSITION_IN_ANR_TRACE_FILE);
    }

    public static boolean getCachedShouldUploadSystemANRTraces() {
        return getFlagValue(SHOULD_UPLOAD_SYSTEM_ANR_TRACES_GK_CACHED);
    }

    public static boolean getCachedShouldDedupDiskPersistence() {
        return getFlagValue(SHOULD_DEDUP_DISK_PERSISTENCE_GK_CACHED);
    }

    public static boolean getCachedShouldUploadANRReports() {
        return getFlagValueDefaultTrue(SHOULD_UPLOAD_ANR_REPORTS);
    }

    public static void setPerformanceMarker(PerformanceMarker performanceMarker) {
        ANRReport aNRReport = mANRReport;
        if (aNRReport != null) {
            aNRReport.setPerformanceMarker(performanceMarker);
        }
    }

    public static void setANRDetectorCheckIntervalMs(long j) {
        IANRDetector iANRDetector = mANRDetector;
        if (iANRDetector != null) {
            iANRDetector.setCheckIntervalMs(j);
        }
    }

    public static void startANRDetector() {
        IANRDetector iANRDetector = mANRDetector;
        if (iANRDetector != null) {
            iANRDetector.start();
        }
    }

    public static void stopANRDetector() {
        IANRDetector iANRDetector = mANRDetector;
        if (iANRDetector != null) {
            iANRDetector.stop(null);
        }
    }

    public static void setANRDetectorListener(ANRDetectorListener aNRDetectorListener) {
        IANRDetector iANRDetector = mANRDetector;
        if (iANRDetector != null) {
            iANRDetector.setListener(aNRDetectorListener);
        }
    }

    private static void writeSkipCertChecksFile(boolean z) {
        try {
            File fileStreamPath = mConfig.getApplicationContext().getFileStreamPath(SKIP_SSL_CERT_CHECKS_FILE_NAME);
            if (z) {
                fileStreamPath.createNewFile();
                if (!fileStreamPath.exists()) {
                    BLog.e(LOG_TAG, "Failed to create skip cert checks file: %s", fileStreamPath.toString());
                    return;
                }
                return;
            }
            fileStreamPath.delete();
            if (fileStreamPath.exists()) {
                BLog.e(LOG_TAG, "Failed to delete skip cert checks file: %s", fileStreamPath.toString());
            }
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "could not create ssl cert checks file.");
        }
    }

    private static void writeSenderHost(String str) {
        Throwable th;
        IOException e;
        OutputStreamWriter outputStreamWriter = null;
        try {
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(mConfig.getApplicationContext().openFileOutput(REPORT_HOST_FILE_NAME, 0));
            try {
                outputStreamWriter2.write(str);
                outputStreamWriter2.flush();
                closeStreamNoException(outputStreamWriter2);
            } catch (IOException e2) {
                e = e2;
                outputStreamWriter = outputStreamWriter2;
                try {
                    BLog.e(LOG_TAG, e, "could not write to host file: ");
                    closeStreamNoException(outputStreamWriter);
                } catch (Throwable th2) {
                    th = th2;
                    closeStreamNoException(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStreamWriter = outputStreamWriter2;
                closeStreamNoException(outputStreamWriter);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            BLog.e(LOG_TAG, e, "could not write to host file: ");
            closeStreamNoException(outputStreamWriter);
        }
    }

    private static void deleteHostsFileIfEmpty(Context context) {
        try {
            File fileStreamPath = context.getFileStreamPath(REPORT_HOST_FILE_NAME);
            if (fileStreamPath.exists()) {
                if (fileStreamPath.canRead()) {
                    if (fileStreamPath.canWrite()) {
                        if (fileStreamPath.length() == 0 && !context.deleteFile(REPORT_HOST_FILE_NAME)) {
                            BLog.e(LOG_TAG, "could not delete empty host file");
                            return;
                        }
                        return;
                    }
                }
                BLog.e(LOG_TAG, "cannot read or write host file");
            }
        } catch (SecurityException e) {
            BLog.e(LOG_TAG, e, "could not delete empty host file: ");
        }
    }

    private static void initSenderHost(Context context) {
        Throwable th;
        IOException e;
        BufferedReader bufferedReader = null;
        String str = null;
        try {
            File fileStreamPath = context.getFileStreamPath(REPORT_HOST_FILE_NAME);
            if (!fileStreamPath.canRead()) {
                closeStreamNoException(null);
                return;
            }
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(fileStreamPath));
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    str = readLine.trim();
                }
                BLog.d(LOG_TAG, "ACRA read host from host file %s", str);
                if (!TextUtils.isEmpty(str)) {
                    BLog.i(LOG_TAG, "setting crash reporting host to %s", str);
                    mReportSender.setHost(str);
                    mReportHost = str;
                }
                closeStreamNoException(bufferedReader2);
            } catch (IOException e2) {
                e = e2;
                bufferedReader = bufferedReader2;
                try {
                    BLog.i(LOG_TAG, e, "could not read host file: ");
                    closeStreamNoException(bufferedReader);
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    closeStreamNoException(bufferedReader2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                closeStreamNoException(bufferedReader2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            BLog.i(LOG_TAG, e, "could not read host file: ");
            closeStreamNoException(bufferedReader);
        }
    }

    private static void initSenderSkipCertChecks(Context context) {
        mReportSender.setSkipSslCertsChecks(context.getFileStreamPath(SKIP_SSL_CERT_CHECKS_FILE_NAME).exists());
    }

    private static void closeStreamNoException(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                BLog.e(LOG_TAG, e, "Error while closing stream: ");
            }
        }
    }

    public static String getReportHost() {
        return mReportHost;
    }
}
