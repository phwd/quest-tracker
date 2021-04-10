package com.oculus.util;

import com.oculus.common.build.BuildConfig;

public class StringUtil {
    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        if (s.length() == 1) {
            return s.toUpperCase();
        }
        char first = s.charAt(0);
        return !Character.isUpperCase(first) ? Character.toUpperCase(first) + s.substring(1) : s;
    }
}
