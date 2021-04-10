package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MobileConfigKeyUtils {
    public static final int CLIENT_DRIVEN_KEY = -1;
    public static final int FAKE_CONFIG_KEY_START = 32768;
    public static final int FAKE_PARAM_KEY_START = 16384;

    public static boolean isFakeParamKey(int key) {
        return key == -1 || key >= 16384;
    }

    public static boolean validConfigKey(int key) {
        return key > 0 && key < 1048576;
    }

    public static String getConfigSpec(int configKey, String configName) {
        if (validConfigKey(configKey)) {
            return String.valueOf(configKey);
        }
        return configName;
    }

    public static boolean validConfigName(String name) {
        return name.length() > 1 && !replacedConfigName(name);
    }

    public static boolean replacedConfigName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        if (name.startsWith("_")) {
            for (char c : name.substring(1).toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        for (char c2 : name.substring(0).toCharArray()) {
            if (!Character.isDigit(c2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validParamName(String name) {
        return name.length() >= 1 && !replacedParamName(name);
    }

    public static boolean replacedParamName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        if (name.startsWith("_")) {
            for (char c : name.substring(1).toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        for (char c2 : name.substring(0).toCharArray()) {
            if (!Character.isDigit(c2)) {
                return false;
            }
        }
        return true;
    }
}
