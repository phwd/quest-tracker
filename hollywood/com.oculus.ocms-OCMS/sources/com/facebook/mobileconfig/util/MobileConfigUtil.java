package com.facebook.mobileconfig.util;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigUtil {
    @Nullable
    public static String getTaskNumberIfValid(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return null;
        }
        if ((trim.startsWith("#") || trim.startsWith("t") || trim.startsWith("T")) && trim.length() > 1) {
            trim = trim.substring(1);
        }
        if (!trim.matches("^[0-9]+$")) {
            return null;
        }
        return trim;
    }

    @Nullable
    public static String getUserIDIfValid(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return null;
        }
        return trim;
    }
}
