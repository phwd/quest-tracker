package com.facebook.acra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anrreport.ANRReport;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.common.process.ProcessName;
import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConfig;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class ACRA {
    public static final String LOG_TAG = "ACRA";
    private static IANRDetector mANRDetector = null;
    private static ANRReport mANRReport = null;
    private static AcraReportingConfig mConfig = null;
    private static String mReportHost = null;
    private static FlexibleReportSender mReportSender = null;
    private static final Object sANRDetectorLock = new Object();
    private static AppStateUpdater sAppStateUpdater = null;
    private static boolean sInitialized = false;
    private static boolean sNativeLibraryLoaded = false;
    private static final Object sNativeLibraryLoadingLock = new Object();

    public static ErrorReporter init(AcraReportingConfig acraReportingConfig) {
        return init(acraReportingConfig, 0, null, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0146 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.facebook.acra.ErrorReporter init(com.facebook.acra.config.AcraReportingConfig r8, long r9, com.facebook.acra.anr.AppStateUpdater r11, com.facebook.acra.ErrorReporter.ExcludedReportObserver r12) {
        /*
        // Method dump skipped, instructions count: 610
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ACRA.init(com.facebook.acra.config.AcraReportingConfig, long, com.facebook.acra.anr.AppStateUpdater, com.facebook.acra.ErrorReporter$ExcludedReportObserver):com.facebook.acra.ErrorReporter");
    }

    private static String getProperty(String str) {
        String str2 = SystemPropertiesInternal.get(str);
        return (str2 == null || str2.equals(BuildConfig.PROVIDER_SUFFIX)) ? System.getProperty(str) : str2;
    }

    private static void addFuryTracesToReportSourceRef(JSONObject jSONObject, String str) {
        String furyTraces = getFuryTraces(mConfig.getApplicationContext(), str);
        if (furyTraces != null && !furyTraces.equals(BuildConfig.PROVIDER_SUFFIX)) {
            try {
                JSONObject jSONObject2 = new JSONObject(furyTraces);
                BLog.i("add furyTraces from file: %s", furyTraces);
                jSONObject.put("fury_traces", jSONObject2);
            } catch (JSONException e) {
                BLog.w(LOG_TAG, "Invalid fury file contents: %s", furyTraces, e);
            }
        }
    }

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

    private static JSONObject getReportSourceRefOverride(Context context) {
        File dir = context.getDir("errorreporting", 0);
        if (!dir.exists()) {
            return null;
        }
        File file = new File(dir, "report_source");
        if (!file.exists()) {
            return null;
        }
        File file2 = new File(file, "report_source_ref.txt");
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

    private static JSONObject jsonifyReportSourceRef(String str) {
        BLog.d(LOG_TAG, "Validating report source ref override file contents: %s", str);
        if (str.startsWith("report_source_ref=")) {
            str = str.replace("report_source_ref=", BuildConfig.PROVIDER_SUFFIX);
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            BLog.w(LOG_TAG, "Invalid report source ref override file contents: %s", str, e);
            return null;
        }
    }

    private static String getProcessName() {
        String str = ProcessName.current().mFullProcessName;
        return str == null ? com.facebook.common.build.config.BuildConfig.VERSION_NAME : str;
    }

    private static void initializeAnrDetector(Context context, ErrorReporter errorReporter, String str) {
        int intValue = getIntValue("android_anr_detector_to_use");
        int intValue2 = getIntValue("error_monitor_check_interval");
        synchronized (sANRDetectorLock) {
            mANRReport = new ANRReport(context, errorReporter);
            mANRDetector = mConfig.createANRDetector(intValue, new ANRDetectorConfig(context, str, mANRReport, sAppStateUpdater, new Handler(Looper.getMainLooper()), intValue, mConfig.isInternalBuild(), getFlagValue("should_report_soft_errors"), getFlagValue("log_on_signal_handler"), getFlagValue("avoid_mutex_on_signal_handler"), getIntValue("anr_recovery_timeout"), getFlagValue("record_signal_time"), getFlagValueDefaultTrue("anr_gk_cached"), errorReporter.mAppVersionCode, errorReporter.mAppVersionName, errorReporter.getSigquitTracesPath(), ErrorReporter.getSigquitTracesExtension(), getIntValue("foreground_check_period")), intValue2);
        }
    }

    public static AcraReportingConfig getConfig() {
        return mConfig;
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
        return getFlagValue("log_position_anr_trace_file");
    }

    public static boolean getCachedShouldUploadSystemANRTraces() {
        return getFlagValue("should_upload_system_anr_traces_gk_cached");
    }

    public static boolean getCachedShouldDedupDiskPersistence() {
        return getFlagValue("should_dedup_disk_persistence_gk_cached");
    }

    private static void closeStreamNoException(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                BLog.e(LOG_TAG, e, "Error while closing stream: ");
            }
        }
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValue(String str) {
        return getFlagValue(mConfig.getApplicationContext().getSharedPreferences("acra_flags_store", 0), str, false);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static boolean getFlagValueDefaultTrue(String str) {
        return getFlagValue(mConfig.getApplicationContext().getSharedPreferences("acra_flags_store", 0), str, true);
    }

    @SuppressLint({"SharedPreferencesUse"})
    private static int getIntValue(String str) {
        return getIntValue(mConfig.getApplicationContext().getSharedPreferences("acra_flags_store", 0), str);
    }
}
