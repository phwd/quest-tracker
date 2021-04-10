package com.oculus.util;

import com.oculus.common.build.BuildConfig;

public final class StringUtil {
    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }
}
