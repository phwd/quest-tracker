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
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return "yes";
        }
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return "yes";
            }
        } catch (Exception e) {
            BLog.e("ACRA", e, "Failed to find Superuser.pak");
        }
        Map<String, String> map = System.getenv();
        if (map == null) {
            return "no";
        }
        String[] split = map.get("PATH").split(":");
        for (String str2 : split) {
            try {
                if (new File(str2 + "/su").exists()) {
                    return "yes";
                }
            } catch (Exception e2) {
                BLog.e("ACRA", e2, "Failed to find su binary in the PATH");
            }
        }
        return "no";
    }

    public static String formatAppInstallTime(long j) {
        return formatTimestamp(j);
    }

    public static String formatAppUpgradeTime(long j) {
        return formatTimestamp(j);
    }

    private static String formatTimestamp(long j) {
        return new SimpleDateFormat(TIME_STAMP_FORMAT, Locale.US).format(new Date(j));
    }

    public static UUID generateReportUuid() {
        try {
            return UUID.randomUUID();
        } catch (Throwable unused) {
            return UUID.nameUUIDFromBytes(String.format("%s-%s-%s", Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.nanoTime()), Integer.valueOf(Process.myTid())).getBytes());
        }
    }
}
