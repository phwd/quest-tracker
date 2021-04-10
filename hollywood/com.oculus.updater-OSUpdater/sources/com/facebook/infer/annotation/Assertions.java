package com.facebook.infer.annotation;

import javax.annotation.Nullable;

public class Assertions {
    public static <T> T assumeNotNull(@Nullable T t) {
        return t;
    }

    public static <T> T assumeNotNull(@Nullable T t, String str) {
        return t;
    }

    public static <T> T assertNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new AssertionError();
    }

    public static AssertionError assertUnreachable() {
        throw new AssertionError();
    }
}
