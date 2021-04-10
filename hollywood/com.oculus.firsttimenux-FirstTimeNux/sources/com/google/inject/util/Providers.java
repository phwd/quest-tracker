package com.google.inject.util;

import javax.inject.Provider;

public final class Providers {
    private Providers() {
    }

    public static <T> Provider<T> of(final T instance) {
        return new Provider<T>() {
            /* class com.google.inject.util.Providers.AnonymousClass1 */

            @Override // javax.inject.Provider
            public T get() {
                return (T) instance;
            }

            public String toString() {
                return "of(" + instance + ")";
            }
        };
    }
}
