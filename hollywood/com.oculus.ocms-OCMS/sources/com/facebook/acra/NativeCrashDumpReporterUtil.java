package com.facebook.acra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.facebook.acra.Spool;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.util.InputStreamField;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.time.TimeConstants;
import com.facebook.debug.log.BLog;
import com.facebook.ultralight.names.UltralightNames;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class NativeCrashDumpReporterUtil {
    private static final String DUMP_DIRECTORY = "minidumps";
    private static final String EXTRA_MAP_SUFFIX = "extra-maps.fatdmp";
    private static final int MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS = 60000;
    private static final int MIN_TIME_ELAPSED_SINCE_LAST_COREDUMP_MS = 86400000;
    private static final int MIN_TIME_ELAPSED_SINCE_LAST_FATMINIDUMP_MS = 3600000;
    public static final String TAG = "ACRA_NATIVE";

    public static boolean processHeapDump(Context context, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed, AcraReportingConfig acraReportingConfig) throws IOException {
        if (!isConnectedToWifi(context) && !isEmulator()) {
            return false;
        }
        if (acraReportingConfig.shouldReportField(ReportField.CORE_DUMP) && processCoreDump(context, crashReportData, fileBeingConsumed)) {
            BLog.d(TAG, "Processed CoreDump");
            return true;
        } else if (!acraReportingConfig.shouldReportField(ReportField.FAT_MINIDUMP) || !processFatMinidump(context, crashReportData, fileBeingConsumed)) {
            return false;
        } else {
            BLog.d(TAG, "Processed FatMiniDump");
            return true;
        }
    }

    public static void cleanupHeapDump(Context context) {
        removeCoreDump(context);
        removeFatMinidump(context);
    }

    private static boolean processCoreDump(Context context, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed) throws IOException {
        File file = new File(context.getApplicationInfo().dataDir, "core");
        if (!file.exists() || file.length() == 0) {
            return false;
        }
        File file2 = new File(BuildConstants.is64BitBuild() ? "/system/bin/app_process64" : "/system/bin/app_process32");
        if (!file2.exists()) {
            file2 = new File("/system/bin/app_process");
        }
        if (!file2.exists() || Math.abs(file.lastModified() - fileBeingConsumed.fileName.lastModified()) > 60000) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - TimeConstants.MS_PER_DAY;
        File file3 = new File(context.getApplicationInfo().dataDir, "core_dump_processed");
        if (file3.exists() && file3.lastModified() > j) {
            return false;
        }
        crashReportData.getInputStreamFields().put(ReportField.CORE_DUMP, new InputStreamField(new FileInputStream(file), true, true, file.length()));
        crashReportData.getInputStreamFields().put(ReportField.APP_PROCESS_FILE, new InputStreamField(new FileInputStream(file2), true, true, file2.length()));
        updateProcessedTime(file3, currentTimeMillis);
        return true;
    }

    private static boolean processFatMinidump(Context context, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed) throws IOException {
        File file = new File(fileBeingConsumed.fileName.getParentFile(), fileBeingConsumed.fileName.getName().split(UltralightNames.FQN_SEPARATOR)[0] + UltralightNames.FQN_SEPARATOR + EXTRA_MAP_SUFFIX);
        if (!file.exists()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - 3600000;
        File file2 = new File(context.getApplicationInfo().dataDir, "fat_minidump_processed");
        if (file2.exists() && file2.lastModified() > j) {
            return false;
        }
        crashReportData.getInputStreamFields().put(ReportField.FAT_MINIDUMP, new InputStreamField(new FileInputStream(file), true, true, file.length()));
        updateProcessedTime(file2, currentTimeMillis);
        return true;
    }

    private static void updateProcessedTime(File file, long j) throws IOException {
        if (!file.exists()) {
            new FileOutputStream(file).close();
        } else {
            file.setLastModified(j);
        }
    }

    @SuppressLint({"MissingPermission"})
    private static boolean isConnectedToWifi(Context context) {
        NetworkInfo networkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || !networkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean removeCoreDump(Context context) {
        return new File(context.getApplicationInfo().dataDir, "core").delete();
    }

    private static void removeFatMinidump(Context context) {
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

    private static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }
}
