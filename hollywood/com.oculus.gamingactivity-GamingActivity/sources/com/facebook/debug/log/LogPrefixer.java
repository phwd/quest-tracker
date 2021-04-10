package com.facebook.debug.log;

import com.facebook.infer.annotation.NullsafeStrict;
import javax.annotation.Nullable;

@NullsafeStrict
public final class LogPrefixer {
    @Nullable
    private static String sGlobalPrefix = null;

    public static void setGlobalPrefix(String globalPrefix) {
        sGlobalPrefix = globalPrefix;
    }

    public static final String renderClass(Class<?> cls) {
        return sGlobalPrefix != null ? sGlobalPrefix + cls.getSimpleName() : cls.getSimpleName();
    }
}
