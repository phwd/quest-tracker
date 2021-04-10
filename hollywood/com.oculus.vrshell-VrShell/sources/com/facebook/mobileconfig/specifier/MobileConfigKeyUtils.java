package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MobileConfigKeyUtils {
    public static final int CLIENT_DRIVEN_KEY = -1;
    public static final int FAKE_CONFIG_KEY_START = 32768;
    public static final int FAKE_PARAM_KEY_START = 16384;

    public static boolean isFakeParamKey(int i) {
        return i == -1 || i >= 16384;
    }

    public static boolean validConfigKey(int i) {
        return i > 0 && i < 1048576;
    }

    public static String getConfigSpec(int i, String str) {
        return validConfigKey(i) ? String.valueOf(i) : str;
    }

    public static boolean validConfigName(String str) {
        return str.length() > 1 && !replacedConfigName(str);
    }

    public static boolean replacedConfigName(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (str.startsWith("_")) {
            for (char c : str.substring(1).toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        for (char c2 : str.substring(0).toCharArray()) {
            if (!Character.isDigit(c2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validParamName(String str) {
        return str.length() >= 1 && !replacedParamName(str);
    }

    public static boolean replacedParamName(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (str.startsWith("_")) {
            for (char c : str.substring(1).toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        for (char c2 : str.substring(0).toCharArray()) {
            if (!Character.isDigit(c2)) {
                return false;
            }
        }
        return true;
    }
}
