package com.facebook.infer.annotation;

public class Assertions {
    public static <T> T assumeNotNull(T object, String explanation) {
        return object;
    }

    public static <T> T assumeNotNull(T object) {
        return object;
    }
}
