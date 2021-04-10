package com.facebook.androidinternals;

import java.lang.reflect.InvocationTargetException;

public final class ReflectionHelper {
    public static void rethrowRuntimeExceptions(InvocationTargetException e) {
        Throwable targetException = e.getTargetException();
        if (targetException instanceof RuntimeException) {
            throw ((RuntimeException) targetException);
        } else if (targetException instanceof Error) {
            throw ((Error) targetException);
        }
    }
}
