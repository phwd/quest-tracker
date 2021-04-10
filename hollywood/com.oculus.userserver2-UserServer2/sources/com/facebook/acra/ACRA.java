package com.facebook.acra;

import X.AnonymousClass06;
import X.C0065Ke;
import X.C0066Kf;
import X.Hw;
import X.JS;
import X.JV;
import X.JW;
import X.Mi;
import X.NG;
import X.NJ;
import X.NP;
import X.g6;
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
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anrreport.ANRReport;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.util.NativeProcFileReader;
import com.facebook.acra.util.NoSync;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

public class ACRA {
    public static final String ACRA_FLAGS_STORE = "acra_flags_store";
    public static final String ANDROID_ANR_DETECTOR_TO_USE = "android_anr_detector_to_use";
    public static final String ANR_FOREGROUND_CHECK_PERIOD = "foreground_check_period";
    public static final String ANR_RECOVERY_TIMEOUT = "anr_recovery_timeout";
    public static final String BREAKPAD_LIB_NAME = "breakpad_lib_name";
    public static final String ERROR_MONITOR_CHECK_INTERVAL = "error_monitor_check_interval";
    public static final String FORCE_NIGHTWATCH_PROPERTY_NAME = "com.facebook.force_nightwatch";
    public static final int HYBRID_ANR_DETECTOR = 4;
    public static final String IS_FIRST_RUN_AFTER_UPGRADE = "is_first_run_after_upgrade";
    public static final String LOGCAT_FILE_KEY = "logcatFileName";
    public static final String LOG_TAG = "ACRA";
    public static final int MULTI_SIGNAL_ANR_DETECTOR = 6;
    public static final int PROCESS_ERROR_MONITOR_ANR_DETECTOR = 5;
    public static final String REPORT_HOST_FILE_NAME = "report_host.txt";
    public static final String RUN_ANR_DETECTOR_ON_BROWSER_PROCESS = "run_anr_detector_on_browser_process";
    public static final String SESSION_ID_KEY = "session_id";
    public static final String SHOULD_AVOID_MUTEX_ON_SIGNAL_HANDLER = "avoid_mutex_on_signal_handler";
    public static final String SHOULD_DEDUP_DISK_PERSISTENCE_GK_CACHED = "should_dedup_disk_persistence_gk_cached";
    public static final String SHOULD_LOG_ON_SIGNAL_HANDLER = "log_on_signal_handler";
    public static final String SHOULD_LOG_PROCESS_POSITION_IN_ANR_TRACE_FILE = "log_position_anr_trace_file";
    public static final String SHOULD_RECORD_SIGNAL_TIME = "record_signal_time";
    public static final String SHOULD_REPORT_SOFT_ERRORS = "should_report_soft_errors";
    public static final String SHOULD_UPLOAD_ANR_REPORTS = "anr_gk_cached";
    public static final String SHOULD_UPLOAD_SYSTEM_ANR_TRACES_GK_CACHED = "should_upload_system_anr_traces_gk_cached";
    public static final int SIGQUIT_BASED_ANR_DETECTOR = 3;
    public static final String SKIP_SSL_CERT_CHECKS_FILE_NAME = "skip_cert_checks.txt";
    @GuardedBy("sANRDetectorLock")
    @Nullable
    public static IANRDetector mANRDetector;
    @GuardedBy("sANRDetectorLock")
    @Nullable
    public static ANRReport mANRReport;
    public static AcraReportingConfig mConfig;
    public static String mReportHost;
    public static FlexibleReportSender mReportSender;
    public static final Object sANRDetectorLock = new Object();
    @Nullable
    public static AppStateUpdater sAppStateUpdater;
    public static boolean sInitialized;
    @GuardedBy("sNativeLibraryLoadingLock")
    public static boolean sNativeLibraryLoaded;
    public static final Object sNativeLibraryLoadingLock = new Object();

    @Nullable
    public static String getFuryTraces(Context context, String str) {
        if (context != null) {
            File file = new File(context.getFilesDir(), str);
            if (file.exists()) {
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
                                String obj = sb.toString();
                                fileInputStream.close();
                                return obj;
                            }
                        }
                    } catch (Throwable unused) {
                    }
                } catch (IOException e) {
                    Mi.A03("ACRA", "Failed to read fury traces file", e);
                    return null;
                }
            }
        }
        return null;
        throw th;
    }

    public static void initSenderHost(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        String str = null;
        try {
            File fileStreamPath = context.getFileStreamPath(REPORT_HOST_FILE_NAME);
            if (fileStreamPath.canRead()) {
                bufferedReader = new BufferedReader(new FileReader(fileStreamPath));
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str = readLine.trim();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        mReportSender.setHost(str);
                        mReportHost = str;
                    }
                    closeStreamNoException(bufferedReader);
                } catch (IOException unused) {
                    bufferedReader2 = bufferedReader;
                    closeStreamNoException(bufferedReader2);
                } catch (Throwable th2) {
                    th = th2;
                    closeStreamNoException(bufferedReader);
                    throw th;
                }
            }
        } catch (IOException unused2) {
            closeStreamNoException(bufferedReader2);
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            closeStreamNoException(bufferedReader);
            throw th;
        }
    }

    public static void writeSenderHost(String str) {
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
                    Mi.A06("ACRA", e, "could not write to host file: ");
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
            Mi.A06("ACRA", e, "could not write to host file: ");
            closeStreamNoException(outputStreamWriter);
        }
    }

    public static void addFuryTracesToReportSourceRef(JSONObject jSONObject, String str) {
        String furyTraces = getFuryTraces(mConfig.getApplicationContext(), str);
        if (furyTraces != null && !furyTraces.equals("")) {
            try {
                jSONObject.put("fury_traces", new JSONObject(furyTraces));
            } catch (JSONException e) {
                Mi.A05("ACRA", "Invalid fury file contents: %s", furyTraces, e);
            }
        }
    }

    public static void closeStreamNoException(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Mi.A06("ACRA", e, "Error while closing stream: ");
            }
        }
    }

    public static void customDelayedMessagesSent() {
        ErrorReporter.putCustomData(ErrorReportingConstants.CUSTOM_DELAYED_MESSAGES_SENT, "true");
    }

    public static void deleteHostsFileIfEmpty(Context context) {
        try {
            File fileStreamPath = context.getFileStreamPath(REPORT_HOST_FILE_NAME);
            if (!fileStreamPath.exists()) {
                return;
            }
            if (!fileStreamPath.canRead() || !fileStreamPath.canWrite()) {
                Mi.A00("ACRA", "cannot read or write host file");
            } else if (fileStreamPath.length() == 0 && !context.deleteFile(REPORT_HOST_FILE_NAME)) {
                Mi.A00("ACRA", "could not delete empty host file");
            }
        } catch (SecurityException e) {
            Mi.A06("ACRA", e, "could not delete empty host file: ");
        }
    }

    public static int getAnrDetectorId() {
        return getIntValue(ANDROID_ANR_DETECTOR_TO_USE);
    }

    public static boolean getCachedShouldDedupDiskPersistence() {
        return getFlagValue(SHOULD_DEDUP_DISK_PERSISTENCE_GK_CACHED);
    }

    public static boolean getCachedShouldLogProcessPositionInAnrTraceFile() {
        return getFlagValue(SHOULD_LOG_PROCESS_POSITION_IN_ANR_TRACE_FILE);
    }

    public static boolean getCachedShouldUploadANRReports() {
        return getFlagValueDefaultTrue(SHOULD_UPLOAD_ANR_REPORTS);
    }

    public static boolean getCachedShouldUploadSystemANRTraces() {
        return getFlagValue(SHOULD_UPLOAD_SYSTEM_ANR_TRACES_GK_CACHED);
    }

    @SuppressLint({"SharedPreferencesUse"})
    public static boolean getFlagValueDefaultTrue(String str) {
        return mConfig.getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0).getBoolean(str, true);
    }

    @Nullable
    public static JSONObject getReportSourceRefOverride(Context context) {
        File dir = context.getDir(ErrorReportingConstants.APP_ERROR_REPORTING_DIR_NAME, 0);
        if (dir.exists()) {
            File file = new File(dir, "report_source");
            if (file.exists()) {
                File file2 = new File(file, ErrorReportingConstants.REPORT_SOURCE_REF_FILE_NAME);
                if (file2.exists()) {
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
                        Mi.A03("ACRA", "Failed to read report source ref override file", e);
                        return null;
                    }
                }
            }
        }
        return null;
        throw th;
    }

    public static boolean getSystemProperty(String str, boolean z) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Boolean) cls.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls, str, Boolean.valueOf(z))).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return z;
        }
    }

    public static void initReportSource() {
        String property = getProperty(ErrorReportingConstants.REPORT_SOURCE_OVERRIDE_PROP);
        if (property != null && !property.equals("")) {
            ErrorReporter.putCustomData("report_source", property);
            ErrorReporter.putCustomData(ErrorReportingConstants.REPORT_SOURCE_REF, "");
        }
        JSONObject reportSourceRefOverride = getReportSourceRefOverride(mConfig.getApplicationContext());
        if (reportSourceRefOverride != null) {
            String property2 = getProperty(ErrorReportingConstants.REPORT_FURY_TRACES_FILE_PROP);
            if (property2 != null && !property2.equals("")) {
                addFuryTracesToReportSourceRef(reportSourceRefOverride, property2);
            }
            reportSourceRefOverride.toString();
            ErrorReporter.putCustomData(ErrorReportingConstants.REPORT_SOURCE_REF, reportSourceRefOverride.toString());
        }
        String property3 = getProperty("fb.testing.build_target");
        if (property3 != null && !property3.equals("")) {
            ErrorReporter.putCustomData("mobile_build_target", property3);
        }
    }

    public static void initSenderSkipCertChecks(Context context) {
        mReportSender.setSkipSslCertsChecks(context.getFileStreamPath(SKIP_SSL_CERT_CHECKS_FILE_NAME).exists());
    }

    public static void initializeAnrDetector(Context context, ErrorReporter errorReporter, String str) {
        int intValue = getIntValue(ANDROID_ANR_DETECTOR_TO_USE);
        int intValue2 = getIntValue(ERROR_MONITOR_CHECK_INTERVAL);
        synchronized (sANRDetectorLock) {
            ANRReport aNRReport = new ANRReport(context, errorReporter);
            mANRReport = aNRReport;
            mANRDetector = mConfig.createANRDetector(intValue, new ANRDetectorConfig(context, str, aNRReport, sAppStateUpdater, new Handler(Looper.getMainLooper()), intValue, mConfig.isInternalBuild(), getFlagValue(SHOULD_REPORT_SOFT_ERRORS), getFlagValue(SHOULD_LOG_ON_SIGNAL_HANDLER), getFlagValue(SHOULD_AVOID_MUTEX_ON_SIGNAL_HANDLER), getIntValue(ANR_RECOVERY_TIMEOUT), getFlagValue(SHOULD_RECORD_SIGNAL_TIME), getFlagValueDefaultTrue(SHOULD_UPLOAD_ANR_REPORTS), errorReporter.getAppVersionCode(), errorReporter.getAppVersionName(), errorReporter.getSigquitTracesPath(), errorReporter.getSigquitTracesExtension(), getIntValue(ANR_FOREGROUND_CHECK_PERIOD)), intValue2);
        }
    }

    @SuppressLint({"BadMethodUse-java.util.concurrent.Executors.newScheduledThreadPool"})
    public static void installPeriodicReporter(Context context, final ErrorReporter errorReporter) {
        int A00 = NP.A00(context, "acraconfig_logcat_native_crash_periodic_interval_mins", 0);
        if (A00 > 0) {
            long j = (long) A00;
            Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
                /* class com.facebook.acra.ACRA.AnonymousClass3 */

                public void run() {
                    ErrorReporter.this.checkNativeReports();
                }
            }, j, j, TimeUnit.MINUTES);
        }
    }

    @Nullable
    public static JSONObject jsonifyReportSourceRef(String str) {
        if (str.startsWith(ErrorReportingConstants.REPORT_SOURCE_REF_LACRIMA_PREFIX)) {
            str = str.replace(ErrorReportingConstants.REPORT_SOURCE_REF_LACRIMA_PREFIX, "");
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            Mi.A05("ACRA", "Invalid report source ref override file contents: %s", str, e);
            return null;
        }
    }

    public static void loadAcraNativeLibrary(final Context context) {
        new Thread(new Runnable() {
            /* class com.facebook.acra.ACRA.AnonymousClass2 */

            public void run() {
                try {
                    synchronized (ACRA.sNativeLibraryLoadingLock) {
                        g6.A00();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } catch (UnsatisfiedLinkError unused) {
                }
            }
        }).start();
    }

    @SuppressLint({"BadMethodUse-java.lang.Thread.start"})
    public static void maybeInitializeAndStartANRDetector(ErrorReporter errorReporter) {
        Context applicationContext = mConfig.getApplicationContext();
        String processName = getProcessName();
        if (shouldRunANRDetector(processName) && mConfig.shouldStartANRDetector()) {
            initializeAnrDetector(applicationContext, errorReporter, processName);
            IANRDetector iANRDetector = mANRDetector;
            if (iANRDetector != null) {
                iANRDetector.start();
            }
        }
    }

    public static void nativeLibrarySuccessfullyLoaded(Context context) {
        String str;
        String A05;
        File dir;
        boolean A01 = NP.A01(context, "acraconfig_enable_nightwatch");
        boolean A012 = NP.A01(context, "nightwatch_monitor_resources");
        int A00 = NP.A00(context, "nightwatch_tick_info_count", 0);
        if (A01 || A012 || A00 > 0) {
            String processName = getProcessName();
            if (!processName.contains(":")) {
                NP.A01(context, "acraconfig_nightwatch_use_setsid");
                NP.A01(context, "acraconfig_nightwatch_use_lss_on_exec");
                NP.A01(context, "nightwatch_use_mmap");
                NP.A00(context, "nightwatch_monitor_resources_interval_ms", ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS);
                NP.A00(context, "nightwatch_split_mmap", 0);
                NP.A00(context, "nightwatch_mmap_update_min_interval_ms", 0);
                NP.A00(context, "acra_nightwatch_turn_off_fast_jni_methods", 0);
                new NG();
                if (NP.A01(context, "acraconfig_nightwatch_use_asl_session_id")) {
                    A05 = AnonymousClass06.A05(processName.replace(':', '_'), "_", mConfig.getSessionId(), ".txt");
                    dir = context.getDir("watcher", 0);
                } else {
                    Random random = new Random();
                    A05 = AnonymousClass06.A05(processName.replace(':', '_'), "_", new UUID(random.nextLong(), random.nextLong()).toString(), ".txt");
                    dir = context.getDir("watcher", 0);
                }
                NJ.A00(new File(dir, A05));
            }
        }
        synchronized (sANRDetectorLock) {
            IANRDetector iANRDetector = mANRDetector;
            if (iANRDetector != null) {
                iANRDetector.nativeLibraryLoaded(getFlagValue(SHOULD_UPLOAD_ANR_REPORTS));
            }
        }
        NativeProcFileReader.nativeLibraryLoaded();
        if (NP.A01(context, "acraconfig_disable_fs_sync_syscalls")) {
            NoSync.disableFSSync(NP.A01(context, "acraconfig_use_fast_fs_sync_hooks"));
        }
        C0065Ke ke = C0066Kf.A00().A00;
        if (ke != null) {
            str = ke.A00;
        } else {
            str = null;
        }
        if ("videoplayer".equalsIgnoreCase(str) && NP.A01(context, "acraconfig_disable_fs_sync_syscalls_vps")) {
            NoSync.disableFSSync(NP.A01(context, "acraconfig_use_fast_fs_sync_hooks"));
        }
        if ("remotecodec".equalsIgnoreCase(str)) {
            NoSync.disableFSSync(NP.A01(context, "acraconfig_use_fast_fs_sync_hooks"));
        }
    }

    public static void onSplashScreenDismissed() {
        ErrorReporter.putCustomData(ErrorReportingConstants.SPLASH_SCREEN_DISMISSED, "true");
    }

    public static void safeToLoadNativeLibraries(Context context) {
        synchronized (sNativeLibraryLoadingLock) {
            if (!sNativeLibraryLoaded) {
                loadAcraNativeLibrary(context);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0075, code lost:
        r4.setANRDataProvider(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0078, code lost:
        if (r2 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007a, code lost:
        r1 = com.facebook.acra.ACRA.sNativeLibraryLoadingLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007c, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0 = com.facebook.acra.ACRA.sNativeLibraryLoaded;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007f, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0080, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        com.facebook.acra.ACRA.mANRDetector.nativeLibraryLoaded(getFlagValue(com.facebook.acra.ACRA.SHOULD_UPLOAD_ANR_REPORTS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setANRDataProvider(com.facebook.acra.anr.ANRDataProvider r6) {
        /*
        // Method dump skipped, instructions count: 147
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ACRA.setANRDataProvider(com.facebook.acra.anr.ANRDataProvider):void");
    }

    public static void setANRDetectorCheckIntervalMs(long j) {
        IANRDetector iANRDetector = mANRDetector;
        if (iANRDetector != null) {
            iANRDetector.setCheckIntervalMs(j);
        }
    }

    public static void setANRDetectorListener(ANRDetectorListener aNRDetectorListener) {
        IANRDetector iANRDetector = mANRDetector;
        if (iANRDetector != null) {
            iANRDetector.setListener(aNRDetectorListener);
        }
    }

    public static void setPerformanceMarker(PerformanceMarker performanceMarker) {
        ANRReport aNRReport = mANRReport;
        if (aNRReport != null) {
            aNRReport.mPerformanceMarker = performanceMarker;
        }
    }

    public static void setReportHost(String str) {
        mReportSender.setHost(str);
        writeSenderHost(str);
    }

    public static void setSkipSslCertChecks(boolean z) {
        mReportSender.setSkipSslCertsChecks(z);
        writeSkipCertChecksFile(z);
    }

    public static boolean shouldRunANRDetector(String str) {
        if (getFlagValue(SHOULD_RECORD_SIGNAL_TIME) || (!str.contains(":")) || (str.contains(":browser") && getFlagValue(RUN_ANR_DETECTOR_ON_BROWSER_PROCESS))) {
            return true;
        }
        return false;
    }

    public static boolean shouldRunNightwatch(Context context) {
        return NP.A01(context, "acraconfig_enable_nightwatch");
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

    @SuppressLint({"SharedPreferencesUse"})
    public static void updateCachedFlagIfNeeded(String str, boolean z) {
        SharedPreferences sharedPreferences = mConfig.getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0);
        if (sharedPreferences.getBoolean(str, false) != z) {
            sharedPreferences.edit().putBoolean(str, z).apply();
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    public static void updateCachedIntIfNeeded(String str, int i) {
        SharedPreferences sharedPreferences = mConfig.getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0);
        if (sharedPreferences.getInt(str, 0) != i) {
            sharedPreferences.edit().putInt(str, i).apply();
        }
    }

    public static void writeSkipCertChecksFile(boolean z) {
        String str;
        String str2;
        Object[] objArr;
        try {
            File fileStreamPath = mConfig.getApplicationContext().getFileStreamPath(SKIP_SSL_CERT_CHECKS_FILE_NAME);
            if (z) {
                fileStreamPath.createNewFile();
                if (!fileStreamPath.exists()) {
                    str = "ACRA";
                    str2 = "Failed to create skip cert checks file: %s";
                    objArr = new Object[]{fileStreamPath.toString()};
                } else {
                    return;
                }
            } else {
                fileStreamPath.delete();
                if (fileStreamPath.exists()) {
                    str = "ACRA";
                    str2 = "Failed to delete skip cert checks file: %s";
                    objArr = new Object[]{fileStreamPath.toString()};
                } else {
                    return;
                }
            }
            Mi.A04(str, str2, objArr);
        } catch (IOException e) {
            Mi.A06("ACRA", e, "could not create ssl cert checks file.");
        }
    }

    @Nullable
    public static ANRReport getANRReport() {
        return mANRReport;
    }

    public static AcraReportingConfig getConfig() {
        return mConfig;
    }

    public static String getProcessName() {
        String str = C0066Kf.A00().A01;
        if (str == null) {
            return "unknown";
        }
        return str;
    }

    @Nullable
    public static String getProperty(String str) {
        String A01 = Hw.A01(str);
        if (A01 == null || A01.equals("")) {
            return System.getProperty(str);
        }
        return A01;
    }

    public static String getReportHost() {
        return mReportHost;
    }

    public static boolean isInitialized() {
        return sInitialized;
    }

    @SuppressLint({"SharedPreferencesUse"})
    public static boolean getFlagValue(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences.getBoolean(str, z);
    }

    @SuppressLint({"SharedPreferencesUse"})
    public static boolean getFlagValue(String str) {
        return mConfig.getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0).getBoolean(str, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    public static int getIntValue(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.getInt(str, 0);
    }

    @SuppressLint({"SharedPreferencesUse"})
    public static int getIntValue(String str) {
        return mConfig.getApplicationContext().getSharedPreferences(ACRA_FLAGS_STORE, 0).getInt(str, 0);
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig) {
        return init(acraReportingConfig, 0, null, null);
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig, long j) {
        return init(acraReportingConfig, j, null, null);
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig, long j, @Nullable AppStateUpdater appStateUpdater) {
        return init(acraReportingConfig, j, appStateUpdater, null);
    }

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig, long j, @Nullable AppStateUpdater appStateUpdater, @Nullable ErrorReporter.ExcludedReportObserver excludedReportObserver) {
        sInitialized = true;
        acraReportingConfig.crashReportUrl();
        ErrorReporter instance = ErrorReporter.getInstance();
        if (j > 0) {
            instance.setAppStartTickTimeMs(j);
        }
        if (mConfig == null) {
            mConfig = acraReportingConfig;
            Context applicationContext = acraReportingConfig.getApplicationContext();
            getProcessName();
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
            FlexibleReportSender createReportSender = mConfig.createReportSender();
            mReportSender = createReportSender;
            instance.setReportSender(createReportSender);
            initSenderHost(applicationContext);
            initSenderSkipCertChecks(applicationContext);
            instance.checkReportsOnApplicationStart();
            if (mConfig.shouldStopAnrDetectorOnErrorReporting()) {
                JV.A02(new JW() {
                    /* class com.facebook.acra.ACRA.AnonymousClass1 */

                    @Override // X.JW
                    public void handleUncaughtException(Thread thread, Throwable th, @Nullable JS js) {
                        ACRA.stopANRDetector();
                    }
                }, 100);
            }
            JV.A02(instance, 0);
            if (th != null) {
                instance.reportErrorAndTerminate(Thread.currentThread(), th);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (mConfig.shouldInstallPeriodicReporter()) {
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
}
