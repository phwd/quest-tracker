package com.oculus.common.logutilities;

import X.AnonymousClass006;

public final class LoggingUtil {
    public static String TAG_PREFIX = "[SUI] ";

    public static void initPrefix(String str) {
        TAG_PREFIX = str;
    }

    public static String tag(Class cls) {
        return AnonymousClass006.A07(TAG_PREFIX, cls.getSimpleName());
    }

    public static String tag(String str) {
        return AnonymousClass006.A07(TAG_PREFIX, str);
    }
}
