package com.google.common.io;

import java.lang.reflect.Method;

final class Closer$SuppressingSuppressor implements Closer$Suppressor {
    static final Closer$SuppressingSuppressor INSTANCE = new Closer$SuppressingSuppressor();
    static final Method addSuppressed = getAddSuppressed();

    Closer$SuppressingSuppressor() {
    }

    private static Method getAddSuppressed() {
        try {
            return Throwable.class.getMethod("addSuppressed", Throwable.class);
        } catch (Throwable unused) {
            return null;
        }
    }
}
