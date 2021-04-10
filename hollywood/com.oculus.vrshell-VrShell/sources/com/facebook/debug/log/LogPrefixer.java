package com.facebook.debug.log;

import com.facebook.infer.annotation.NullsafeStrict;
import javax.annotation.Nullable;

@NullsafeStrict
public final class LogPrefixer {
    @Nullable
    private static String sGlobalPrefix;

    public static void setGlobalPrefix(String str) {
        sGlobalPrefix = str;
    }

    public static final String renderClass(Class<?> cls) {
        if (sGlobalPrefix == null) {
            return cls.getSimpleName();
        }
        return sGlobalPrefix + cls.getSimpleName();
    }
}
