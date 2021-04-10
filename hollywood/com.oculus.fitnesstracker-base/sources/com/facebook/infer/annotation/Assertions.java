package com.facebook.infer.annotation;

public final class Assertions {
    public static <T> T assertNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new AssertionError();
    }
}
