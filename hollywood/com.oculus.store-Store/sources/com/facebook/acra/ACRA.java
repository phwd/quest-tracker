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
    public static final String LOG_TAG = ACRA.class.getSimpleName();
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

    public static ErrorReporter init(AcraReportingConfig config, long appStartTickTimeMs) {
        return init(config, appStartTickTimeMs, null, null);
    }

    public static ErrorReporter init(AcraReportingConfig config, long appStartTickTimeMs, @Nullable AppStateUpdater appStateUpdater) {
        return init(config, appStartTickTimeMs, appStateUpdater, null);
    }

    public static boolean isInitialized() {
        return sInitialized;
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
        String reportSourceOverride = getProperty(ErrorReportingConstants.REPORT_SOURCE_OVERRIDE_PROP);
        if (reportSourceOverride != null && !reportSourceOverride.equals("")) {
            BLog.i("Overriding report_source from prop: %s", reportSourceOverride);
            ErrorReporter.putCustomData("report_source", reportSourceOverride);
            ErrorReporter.putCustomData(ErrorReportingConstants.REPORT_SOURCE_REF, "");
        }
        JSONObject reportSourceRefOverride = getReportSourceRefOverride(mConfig.getApplicationContext());
        if (reportSourceRefOverride != null) {
            String furyTracesFileName = getProperty(ErrorReportingConstants.REPORT_FURY_TRACES_FILE_PROP);
            if (furyTracesFileName != null && !furyTracesFileName.equals("")) {
                addFuryTracesToReportSourceRef(reportSourceRefOverride, furyTracesFileName);
            }
            BLog.i("Overriding report_source_ref from file: %s", reportSourceRefOverride.toString());
            ErrorReporter.putCustomData(ErrorReportingConstants.REPORT_SOURCE_REF, reportSourceRefOverride.toString());
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
        File appErrorReportingDir = ctx.getDir(ErrorReportingConstants.APP_ERROR_REPORTING_DIR_NAME, 0);
        if (!appErrorReportingDir.exists()) {
            return null;
        }
        File reportSourceRefDir = new File(appErrorReportingDir, "report_source");
        if (!reportSourceRefDir.exists()) {
            return null;
        }
        File reportSourceRefFile = new File(reportSourceRefDir, ErrorReportingConstants.REPORT_SOURCE_REF_FILE_NAME);
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
        if (reportSourceRef.startsWith(ErrorReportingConstants.REPORT_SOURCE_REF_LACRIMA_PREFIX)) {
            validationResult = reportSourceRef.replace(ErrorReportingConstants.REPORT_SOURCE_REF_LACRIMA_PREFIX, "");
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

    public static void safeToLoadNativeLibraries(Context ctx) {
        synchronized (sNativeLibraryLoadingLock) {
            if (!sNativeLibraryLoaded) {
                loadAcraNativeLibrary(ctx);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void nativeLibrarySuccessfullyLoaded(Context ctx) {
        boolean runNightwatch = shouldRunNightwatch(ctx);
        boolean shouldMonitorResources = AcraConfig.shouldMonitorResourcesOnNightwatch(ctx);
        int tickInfoCount = AcraConfig.getNightwatchTickInfoCount(ctx);
        if (runNightwatch || shouldMonitorResources || tickInfoCount > 0) {
            String processName = getProcessName();
            if (!processName.contains(":")) {
                Nightwatch.NightwatchConfigs configs = new Nightwatch.NightwatchConfigs(runNightwatch, AcraConfig.shouldUseSetSidOnNightwatch(ctx), AcraConfig.shouldUseLssExecOnNightwatch(ctx), AcraConfig.shouldUseMmapOnNightwatch(ctx), shouldMonitorResources, AcraConfig.monitorResourcesIntervalMs(ctx), tickInfoCount, AcraConfig.getNightwatchProcessType(ctx), AcraConfig.getShouldNightWatchUseFastOrCriticalJniMethods(ctx));
                if (AcraConfig.shouldUseAslSessionIdOnNightwatch(ctx)) {
                    Nightwatch.startWatcher(ctx, processName, getConfig().getSessionId(), configs);
                } else {
                    Nightwatch.startWatcher(ctx, processName, configs);
                }
            }
        }
        synchronized (sANRDetectorLock) {
            if (mANRDetector != null) {
                mANRDetector.nativeLibraryLoaded(getFlagValue(SHOULD_UPLOAD_ANR_REPORTS));
            }
        }
        NativeProcFileReader.nativeLibraryLoaded();
        if (AcraConfig.disableFSSyncSyscalls(ctx)) {
            NoSync.disableFSSync(AcraConfig.useFSSyncFastHooks(ctx));
        }
        String processName2 = ProcessName.current().getShortPrivateName();
        if ("videoplayer".equalsIgnoreCase(processName2) && AcraConfig.disableFSSyncSyscallsVps(ctx)) {
            NoSync.disableFSSync(AcraConfig.useFSSyncFastHooks(ctx));
        }
        if ("remotecodec".equalsIgnoreCase(processName2)) {
            NoSync.disableFSSync(AcraConfig.useFSSyncFastHooks(ctx));
        }
    }

    private static boolean shouldRunNightwatch(Context ctx) {
        if (BuildConstants.isInternalBuild()) {
            return getSystemProperty(FORCE_NIGHTWATCH_PROPERTY_NAME, false);
        }
        return AcraConfig.shouldEnableNightwatch(ctx);
    }

    private static boolean getSystemProperty(String propertyName, boolean defValue) {
        try {
            Class c = Class.forName("android.os.SystemProperties");
            return ((Boolean) c.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(c, propertyName, Boolean.valueOf(defValue))).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return defValue;
        }
    }

    private static void loadAcraNativeLibrary(final Context ctx) {
        new Thread(new Runnable() {
            /* class com.facebook.acra.ACRA.AnonymousClass2 */

            public void run() {
                try {
                    synchronized (ACRA.sNativeLibraryLoadingLock) {
                        NativeLoader.loadLibrary("acra");
                        boolean unused = ACRA.sNativeLibraryLoaded = true;
                    }
                    ACRA.nativeLibrarySuccessfullyLoaded(ctx);
                } catch (UnsatisfiedLinkError e) {
                    BLog.d(ACRA.LOG_TAG, e, "Failed to load libacra");
                }
            }
        }).start();
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
            return "unknown";
        }
        return name;
    }

    private static boolean shouldRunANRDetector(String processName) {
        boolean isMainProcess;
        boolean z = false;
        if (getFlagValue(SHOULD_RECORD_SIGNAL_TIME)) {
            return true;
        }
        if (!processName.contains(":")) {
            isMainProcess = true;
        } else {
            isMainProcess = false;
        }
        if (isMainProcess || (processName.contains(":browser") && getFlagValue(RUN_ANR_DETECTOR_ON_BROWSER_PROCESS))) {
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
        int anrDetectorId = getIntValue(ANDROID_ANR_DETECTOR_TO_USE);
        int errorMonitorCheckInterval = getIntValue(ERROR_MONITOR_CHECK_INTERVAL);
        synchronized (sANRDetectorLock) {
            mANRReport = new ANRReport(context, errorReporter);
            mANRDetector = mConfig.createANRDetector(anrDetectorId, new ANRDetectorConfig(context, processName, mANRReport, sAppStateUpdater, new Handler(Looper.getMainLooper()), anrDetectorId, mConfig.isInternalBuild(), getFlagValue(SHOULD_REPORT_SOFT_ERRORS), getFlagValue(SHOULD_LOG_ON_SIGNAL_HANDLER), getFlagValue(SHOULD_AVOID_MUTEX_ON_SIGNAL_HANDLER), getIntValue(ANR_RECOVERY_TIMEOUT), getFlagValue(SHOULD_RECORD_SIGNAL_TIME), getCachedShouldUploadANRReports(), errorReporter.getAppVersionCode(), errorReporter.getAppVersionName(), errorReporter.getSigquitTracesPath(), errorReporter.getSigquitTracesExtension(), getIntValue(ANR_FOREGROUND_CHECK_PERIOD)), errorMonitorCheckInterval);
        }
    }

    public static int getAnrDetectorId() {
        return getIntValue(ANDROID_ANR_DETECTOR_TO_USE);
    }

    public static AcraReportingConfig getConfig() {
        return mConfig;
    }

    public static void setReportHost(String host) {
        mReportSender.setHost(host);
        writeSenderHost(host);
    }

    public static void setSkipSslCertChecks(boolean skipCheckCerts) {
        mReportSender.setSkipSslCertsChecks(skipCheckCerts);
        writeSkipCertChecksFile(skipCheckCerts);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b6, code lost:
        r2.setANRDataProvider(r7);
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00ba, code lost:
        if (r3 == false) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00bc, code lost:
        r6 = com.facebook.acra.ACRA.sNativeLibraryLoadingLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00be, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c1, code lost:
        if (com.facebook.acra.ACRA.sNativeLibraryLoaded == false) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c3, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c4, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c5, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c7, code lost:
        com.facebook.acra.ACRA.mANRDetector.nativeLibraryLoaded(getFlagValue(com.facebook.acra.ACRA.SHOULD_UPLOAD_ANR_REPORTS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setANRDataProvider(com.facebook.acra.anr.ANRDataProvider r7) {
        /*
        // Method dump skipped, instructions count: 215
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ACRA.setANRDataProvider(com.facebook.acra.anr.ANRDataProvider):void");
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static void updateCachedFlagIfNeeded(String flagName, boolean newValue) {
        SharedPreferences prefs = getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0);
        if (getFlagValue(prefs, flagName, false) != newValue) {
            prefs.edit().putBoolean(flagName, newValue).apply();
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static void updateCachedIntIfNeeded(String prefName, int newValue) {
        SharedPreferences prefs = getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0);
        if (getIntValue(prefs, prefName) != newValue) {
            prefs.edit().putInt(prefName, newValue).apply();
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValue(String flagName) {
        return getFlagValue(getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0), flagName, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValueDefaultTrue(String flagName) {
        return getFlagValue(getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0), flagName, true);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static int getIntValue(String preferenceName) {
        return getIntValue(getConfig().getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0), preferenceName);
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
        if (mANRReport != null) {
            mANRReport.setPerformanceMarker(performanceMarker);
        }
    }

    public static void setANRDetectorCheckIntervalMs(long checkIntervalMs) {
        if (mANRDetector != null) {
            mANRDetector.setCheckIntervalMs(checkIntervalMs);
        }
    }

    public static void startANRDetector() {
        if (mANRDetector != null) {
            mANRDetector.start();
        }
    }

    public static void stopANRDetector() {
        if (mANRDetector != null) {
            mANRDetector.stop(null);
        }
    }

    public static void setANRDetectorListener(ANRDetectorListener listener) {
        if (mANRDetector != null) {
            mANRDetector.setListener(listener);
        }
    }

    private static void writeSkipCertChecksFile(boolean skipCheckCerts) {
        try {
            File skipCertChecksFile = mConfig.getApplicationContext().getFileStreamPath(SKIP_SSL_CERT_CHECKS_FILE_NAME);
            if (skipCheckCerts) {
                skipCertChecksFile.createNewFile();
                if (!skipCertChecksFile.exists()) {
                    BLog.e(LOG_TAG, "Failed to create skip cert checks file: %s", skipCertChecksFile.toString());
                    return;
                }
                return;
            }
            skipCertChecksFile.delete();
            if (skipCertChecksFile.exists()) {
                BLog.e(LOG_TAG, "Failed to delete skip cert checks file: %s", skipCertChecksFile.toString());
            }
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "could not create ssl cert checks file.");
        }
    }

    private static void writeSenderHost(String host) {
        Throwable th;
        IOException e;
        OutputStreamWriter osw = null;
        try {
            OutputStreamWriter osw2 = new OutputStreamWriter(mConfig.getApplicationContext().openFileOutput(REPORT_HOST_FILE_NAME, 0));
            try {
                osw2.write(host);
                osw2.flush();
                closeStreamNoException(osw2);
            } catch (IOException e2) {
                e = e2;
                osw = osw2;
                try {
                    BLog.e(LOG_TAG, e, "could not write to host file: ");
                    closeStreamNoException(osw);
                } catch (Throwable th2) {
                    th = th2;
                    closeStreamNoException(osw);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                osw = osw2;
                closeStreamNoException(osw);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            BLog.e(LOG_TAG, e, "could not write to host file: ");
            closeStreamNoException(osw);
        }
    }

    private static void deleteHostsFileIfEmpty(Context context) {
        try {
            File file = context.getFileStreamPath(REPORT_HOST_FILE_NAME);
            if (file.exists()) {
                if (!file.canRead() || !file.canWrite()) {
                    BLog.e(LOG_TAG, "cannot read or write host file");
                } else if (file.length() == 0 && !context.deleteFile(REPORT_HOST_FILE_NAME)) {
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
            File file = context.getFileStreamPath(REPORT_HOST_FILE_NAME);
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
        mReportSender.setSkipSslCertsChecks(context.getFileStreamPath(SKIP_SSL_CERT_CHECKS_FILE_NAME).exists());
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

    public static String getReportHost() {
        return mReportHost;
    }
}
