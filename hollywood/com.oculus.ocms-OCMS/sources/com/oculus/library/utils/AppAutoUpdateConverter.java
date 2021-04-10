package com.oculus.library.utils;

public class AppAutoUpdateConverter {
    private static final String ENABLED = "ENABLED";

    public static boolean toBoolean(String str) {
        return ENABLED.equals(str);
    }
}
