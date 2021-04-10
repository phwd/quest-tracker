package com.google.common.util.concurrent;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
final class Platform {
    static boolean isInstanceOfThrowableClass(@NullableDecl Throwable t, Class<? extends Throwable> expectedClass) {
        return expectedClass.isInstance(t);
    }

    private Platform() {
    }
}
