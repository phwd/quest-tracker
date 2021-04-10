package com.facebook.infer.annotation;

import javax.annotation.Nullable;

public class Assertions {
    public static <T> T assertNotNull(@Nullable T object) {
        if (object != null) {
            return object;
        }
        throw new AssertionError();
    }

    public static <T> T assumeNotNull(@Nullable T object, String explanation) {
        return object;
    }

    public static <T> T assumeNotNull(@Nullable T object) {
        return object;
    }
}
