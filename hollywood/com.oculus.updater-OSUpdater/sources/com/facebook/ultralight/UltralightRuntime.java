package com.facebook.ultralight;

import com.facebook.inject.Lazy;
import javax.annotation.Nullable;
import javax.inject.Provider;

public abstract class UltralightRuntime {
    public static final Lazy<Object> NULL_LAZY = new Lazy<Object>() {
        /* class com.facebook.ultralight.UltralightRuntime.AnonymousClass2 */

        @Override // com.facebook.inject.Lazy, javax.inject.Provider
        @Nullable
        public Object get() {
            return null;
        }
    };
    public static final Provider<Object> NULL_PROVIDER = new Provider<Object>() {
        /* class com.facebook.ultralight.UltralightRuntime.AnonymousClass1 */

        @Override // javax.inject.Provider
        public Object get() {
            return UltralightRuntime.throwLocalInjectionTooEarly();
        }
    };

    public static <T> T throwLocalInjectionTooEarly() {
        throw new LocalInjectionBeforeInstanceInjectionException("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
