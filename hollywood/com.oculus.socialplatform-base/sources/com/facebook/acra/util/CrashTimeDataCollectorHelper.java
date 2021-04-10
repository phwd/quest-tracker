package com.facebook.acra.util;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import android.os.Build;
import android.os.Process;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class CrashTimeDataCollectorHelper {
    public static final String LOG_TAG = "ACRA";
    public static final String TIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.000ZZZZZ";

    public static String formatTimestamp(long j) {
        return new SimpleDateFormat(TIME_STAMP_FORMAT, Locale.US).format(new Date(j));
    }

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
            AnonymousClass0MD.A0C("ACRA", e, "Failed to find Superuser.pak");
        }
        Map<String, String> map = System.getenv();
        if (map == null) {
            return "no";
        }
        for (String str2 : map.get("PATH").split(":")) {
            try {
                if (new File(AnonymousClass006.A07(str2, "/su")).exists()) {
                    return "yes";
                }
            } catch (Exception e2) {
                AnonymousClass0MD.A0C("ACRA", e2, "Failed to find su binary in the PATH");
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

    public static UUID generateReportUuid() {
        try {
            return UUID.randomUUID();
        } catch (Throwable unused) {
            return UUID.nameUUIDFromBytes(String.format("%s-%s-%s", Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.nanoTime()), Integer.valueOf(Process.myTid())).getBytes());
        }
    }
}