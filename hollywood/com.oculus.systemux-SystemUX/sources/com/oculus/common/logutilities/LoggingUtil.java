package com.oculus.common.logutilities;

public final class LoggingUtil {
    private static String TAG_PREFIX = "[SUI] ";

    public static void initPrefix(String str) {
        TAG_PREFIX = str;
    }

    public static String tag(Class cls) {
        return TAG_PREFIX + cls.getSimpleName();
    }

    public static String tag(String str) {
        return TAG_PREFIX + str;
    }
}
