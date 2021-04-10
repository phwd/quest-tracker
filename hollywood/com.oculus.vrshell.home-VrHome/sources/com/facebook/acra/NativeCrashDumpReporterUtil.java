package com.facebook.acra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.facebook.acra.Spool;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.InputStreamField;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.debug.log.BLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class NativeCrashDumpReporterUtil {
    public static boolean processHeapDump(Context ctx, CrashReportData crashReport, Spool.FileBeingConsumed minidump, AcraReportingConfig config) throws IOException {
        if (!isConnectedToWifi(ctx) && !isEmulator()) {
            return false;
        }
        if (config.shouldReportField("CORE_DUMP") && processCoreDump(ctx, crashReport, minidump)) {
            BLog.d("ACRA_NATIVE", "Processed CoreDump");
            return true;
        } else if (!config.shouldReportField("FAT_MINIDUMP") || !processFatMinidump(ctx, crashReport, minidump)) {
            return false;
        } else {
            BLog.d("ACRA_NATIVE", "Processed FatMiniDump");
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
        long maxAge = now - 86400000;
        File lastCoredumpProcessed = new File(ctx.getApplicationInfo().dataDir, "core_dump_processed");
        if (lastCoredumpProcessed.exists() && lastCoredumpProcessed.lastModified() > maxAge) {
            return false;
        }
        crashReport.getInputStreamFields().put("CORE_DUMP", new InputStreamField(new FileInputStream(coreDump), true, true, coreDump.length()));
        crashReport.getInputStreamFields().put("APP_PROCESS_FILE", new InputStreamField(new FileInputStream(appProcess), true, true, appProcess.length()));
        updateProcessedTime(lastCoredumpProcessed, now);
        return true;
    }

    private static boolean processFatMinidump(Context ctx, CrashReportData crashReport, Spool.FileBeingConsumed minidump) throws IOException {
        File extraMaps = new File(minidump.fileName.getParentFile(), minidump.fileName.getName().split("_")[0] + "_" + "extra-maps.fatdmp");
        if (!extraMaps.exists()) {
            return false;
        }
        long now = System.currentTimeMillis();
        long maxAge = now - 3600000;
        File lastFatMinidumpProcessed = new File(ctx.getApplicationInfo().dataDir, "fat_minidump_processed");
        if (lastFatMinidumpProcessed.exists() && lastFatMinidumpProcessed.lastModified() > maxAge) {
            return false;
        }
        crashReport.getInputStreamFields().put("FAT_MINIDUMP", new InputStreamField(new FileInputStream(extraMaps), true, true, extraMaps.length()));
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
                return name.endsWith("extra-maps.fatdmp");
            }
        });
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    private static boolean isEmulator() {
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith(BuildConfig.VERSION_NAME) || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT))) {
            return true;
        }
        return false;
    }
}
