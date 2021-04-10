package com.facebook.androidinternals;

import java.lang.reflect.InvocationTargetException;

public final class ReflectionHelper {
    private ReflectionHelper() {
    }

    public static void rethrowRuntimeExceptions(InvocationTargetException invocationTargetException) {
        Throwable targetException = invocationTargetException.getTargetException();
        if (targetException instanceof RuntimeException) {
            throw ((RuntimeException) targetException);
        } else if (targetException instanceof Error) {
            throw ((Error) targetException);
        }
    }
}
