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

    public static boolean processHeapDump(Context ctx, CrashReportData crashReport, Spool.FileBeingConsumed minidump, AcraReportingConfig config) throws IOException {
        if (!isConnectedToWifi(ctx) && !isEmulator()) {
            return false;
        }
        if (config.shouldReportField(ReportField.CORE_DUMP) && processCoreDump(ctx, crashReport, minidump)) {
            BLog.d(TAG, "Processed CoreDump");
            return true;
        } else if (!config.shouldReportField(ReportField.FAT_MINIDUMP) || !processFatMinidump(ctx, crashReport, minidump)) {
            return false;
        } else {
            BLog.d(TAG, "Processed FatMiniDump");
            return true;
        }
    }

    public static void cleanupHeapDump(Context ctx) {
        removeCoreDump(ctx);
        removeFatMinidump(ctx);
    }

    private static boolean processCoreDump(Context ctx, CrashReportData crashReport, Spool.FileBeingConsumed minidump) throws IOException {
        File coreDump = new File(ctx.getApplicationInfo().dataDir, "core");
        if (!coreDump.exists() || coreDump.length() == 0) {
            return false;
        }
        File appProcess = new File(BuildConstants.is64BitBuild() ? "/system/bin/app_process64" : "/system/bin/app_process32");
        if (!appProcess.exists()) {
            appProcess = new File("/system/bin/app_process");
        }
        if (!appProcess.exists() || Math.abs(coreDump.lastModified() - minidump.fileName.lastModified()) > 60000) {
            return false;
        }
        long now = System.currentTimeMillis();
        long maxAge = now - TimeConstants.MS_PER_DAY;
        File lastCoredumpProcessed = new File(ctx.getApplicationInfo().dataDir, "core_dump_processed");
        if (lastCoredumpProcessed.exists() && lastCoredumpProcessed.lastModified() > maxAge) {
            return false;
        }
        crashReport.getInputStreamFields().put(ReportField.CORE_DUMP, new InputStreamField(new FileInputStream(coreDump), true, true, coreDump.length()));
        crashReport.getInputStreamFields().put(ReportField.APP_PROCESS_FILE, new InputStreamField(new FileInputStream(appProcess), true, true, appProcess.length()));
        updateProcessedTime(lastCoredumpProcessed, now);
        return true;
    }

    private static boolean processFatMinidump(Context ctx, CrashReportData crashReport, Spool.FileBeingConsumed minidump) throws IOException {
        File extraMaps = new File(minidump.fileName.getParentFile(), minidump.fileName.getName().split(UltralightNames.FQN_SEPARATOR)[0] + UltralightNames.FQN_SEPARATOR + EXTRA_MAP_SUFFIX);
        if (!extraMaps.exists()) {
            return false;
        }
        long now = System.currentTimeMillis();
        long maxAge = now - TimeConstants.MS_PER_HOUR;
        File lastFatMinidumpProcessed = new File(ctx.getApplicationInfo().dataDir, "fat_minidump_processed");
        if (lastFatMinidumpProcessed.exists() && lastFatMinidumpProcessed.lastModified() > maxAge) {
            return false;
        }
        crashReport.getInputStreamFields().put(ReportField.FAT_MINIDUMP, new InputStreamField(new FileInputStream(extraMaps), true, true, extraMaps.length()));
        updateProcessedTime(lastFatMinidumpProcessed, now);
        return true;
    }

    private static void updateProcessedTime(File lastProcessed, long now) throws IOException {
        if (!lastProcessed.exists()) {
            new FileOutputStream(lastProcessed).close();
        } else {
            lastProcessed.setLastModified(now);
        }
    }

    @SuppressLint({"MissingPermission"})
    private static boolean isConnectedToWifi(Context ctx) {
        try {
            ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService("connectivity");
            if (connManager != null) {
                NetworkInfo wifiInfo = connManager.getNetworkInfo(1);
                return wifiInfo != null && wifiInfo.isConnected();
            }
        } catch (Exception e) {
        }
        return false;
    }

    private static boolean removeCoreDump(Context ctx) {
        return new File(ctx.getApplicationInfo().dataDir, "core").delete();
    }

    private static void removeFatMinidump(Context ctx) {
        File[] files = ctx.getDir("minidumps", 0).listFiles(new FilenameFilter() {
            /* class com.facebook.acra.NativeCrashDumpReporterUtil.AnonymousClass1 */

            public boolean accept(File dir, String name) {
                return name.endsWith(NativeCrashDumpReporterUtil.EXTRA_MAP_SUFFIX);
            }
        });
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    private static boolean isEmulator() {
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT))) {
            return true;
        }
        return false;
    }
}
