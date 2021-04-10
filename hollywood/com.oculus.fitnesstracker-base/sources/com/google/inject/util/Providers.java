package com.google.inject.util;

import javax.inject.Provider;

public final class Providers {
    public static <T> Provider<T> of(final T t) {
        return new Provider<T>() {
            /* class com.google.inject.util.Providers.AnonymousClass1 */

            @Override // javax.inject.Provider
            public final T get() {
                return (T) t;
            }

            public final String toString() {
                return "of(" + t + ")";
            }
        };
    }
}