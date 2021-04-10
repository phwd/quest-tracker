package com.facebook.acra.util;

import android.os.Build;
import android.os.Process;
import com.facebook.debug.log.BLog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class CrashTimeDataCollectorHelper {
    private static final String LOG_TAG = "ACRA";
    private static final String TIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.000ZZZZZ";

    public static String getJailStatus() {
        String buildTags = Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return "yes";
        }
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return "yes";
            }
        } catch (Exception ex) {
            BLog.e(LOG_TAG, ex, "Failed to find Superuser.pak");
        }
        Map<String, String> env = System.getenv();
        if (env != null) {
            String[] dirs = env.get("PATH").split(":");
            int length = dirs.length;
            for (int i = 0; i < length; i++) {
                try {
                    if (new File(dirs[i] + "/su").exists()) {
                        return "yes";
                    }
                } catch (Exception ex2) {
                    BLog.e(LOG_TAG, ex2, "Failed to find su binary in the PATH");
                }
            }
        }
        return "no";
    }

    public static String formatAppInstallTime(long time) {
        return formatTimestamp(time);
    }

    public static String formatAppUpgradeTime(long time) {
        return formatTimestamp(time);
    }

    private static String formatTimestamp(long time) {
        return new SimpleDateFormat(TIME_STAMP_FORMAT, Locale.US).format(new Date(time));
    }

    public static UUID generateReportUuid() {
        try {
            return UUID.randomUUID();
        } catch (Throwable th) {
            return UUID.nameUUIDFromBytes(String.format("%s-%s-%s", Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.nanoTime()), Integer.valueOf(Process.myTid())).getBytes());
        }
    }
}
