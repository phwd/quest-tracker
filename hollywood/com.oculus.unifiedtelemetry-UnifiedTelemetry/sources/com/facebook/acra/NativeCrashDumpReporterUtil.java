package com.facebook.acra;

import X.AnonymousClass06;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.facebook.acra.Spool;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.util.InputStreamField;
import com.oculus.time.TimeModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class NativeCrashDumpReporterUtil {
    public static final String DUMP_DIRECTORY = "minidumps";
    public static final String EXTRA_MAP_SUFFIX = "extra-maps.fatdmp";
    public static final int MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS = 60000;
    public static final int MIN_TIME_ELAPSED_SINCE_LAST_COREDUMP_MS = 86400000;
    public static final int MIN_TIME_ELAPSED_SINCE_LAST_FATMINIDUMP_MS = 3600000;
    public static final String TAG = "ACRA_NATIVE";

    @SuppressLint({"MissingPermission"})
    public static boolean isConnectedToWifi(Context context) {
        NetworkInfo networkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || !networkInfo.isConnected()) ? false : true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void removeFatMinidump(Context context) {
        File[] listFiles = context.getDir("minidumps", 0).listFiles(new FilenameFilter() {
            /* class com.facebook.acra.NativeCrashDumpReporterUtil.AnonymousClass1 */

            public boolean accept(File file, String str) {
                return str.endsWith(NativeCrashDumpReporterUtil.EXTRA_MAP_SUFFIX);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
    }

    public static boolean isEmulator() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith("unknown") && !Build.MODEL.contains("google_sdk")) {
            String str2 = Build.MODEL;
            if (str2.contains("Emulator") || str2.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT))) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean processFatMinidump(Context context, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed) throws IOException {
        File file = new File(fileBeingConsumed.fileName.getParentFile(), AnonymousClass06.A05(fileBeingConsumed.fileName.getName().split("_")[0], "_", EXTRA_MAP_SUFFIX));
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - TimeModule.MILLISECONDS_PER_HOUR;
            File file2 = new File(context.getApplicationInfo().dataDir, "fat_minidump_processed");
            if (!file2.exists() || file2.lastModified() <= j) {
                crashReportData.mInputStreamFields.put(ReportField.FAT_MINIDUMP, new InputStreamField(new FileInputStream(file), true, true, file.length()));
                updateProcessedTime(file2, currentTimeMillis);
                return true;
            }
        }
        return false;
    }

    public static void cleanupHeapDump(Context context) {
        removeCoreDump(context);
        removeFatMinidump(context);
    }

    public static boolean processCoreDump(Context context, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed) throws IOException {
        String str;
        File file = new File(context.getApplicationInfo().dataDir, "core");
        if (file.exists() && file.length() != 0) {
            if ("arm64".contains("64")) {
                str = "/system/bin/app_process64";
            } else {
                str = "/system/bin/app_process32";
            }
            File file2 = new File(str);
            if (!file2.exists()) {
                file2 = new File("/system/bin/app_process");
            }
            if (file2.exists() && Math.abs(file.lastModified() - fileBeingConsumed.fileName.lastModified()) <= 60000) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = currentTimeMillis - 86400000;
                File file3 = new File(context.getApplicationInfo().dataDir, "core_dump_processed");
                if (!file3.exists() || file3.lastModified() <= j) {
                    crashReportData.mInputStreamFields.put(ReportField.CORE_DUMP, new InputStreamField(new FileInputStream(file), true, true, file.length()));
                    crashReportData.mInputStreamFields.put(ReportField.APP_PROCESS_FILE, new InputStreamField(new FileInputStream(file2), true, true, file2.length()));
                    updateProcessedTime(file3, currentTimeMillis);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean processHeapDump(Context context, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed, AcraReportingConfig acraReportingConfig) throws IOException {
        if (isConnectedToWifi(context) || isEmulator()) {
            if (acraReportingConfig.shouldReportField(ReportField.CORE_DUMP) && processCoreDump(context, crashReportData, fileBeingConsumed)) {
                return true;
            }
            if (!acraReportingConfig.shouldReportField(ReportField.FAT_MINIDUMP) || !processFatMinidump(context, crashReportData, fileBeingConsumed)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean removeCoreDump(Context context) {
        return new File(context.getApplicationInfo().dataDir, "core").delete();
    }

    public static void updateProcessedTime(File file, long j) throws IOException {
        if (!file.exists()) {
            new FileOutputStream(file).close();
        } else {
            file.setLastModified(j);
        }
    }
}
