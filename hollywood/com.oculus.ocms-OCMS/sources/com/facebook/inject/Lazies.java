package com.facebook.inject;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import javax.inject.Provider;

public final class Lazies {
    private Lazies() {
    }

    public static <T> Lazy<T> of(final T t) {
        return new Lazy<T>() {
            /* class com.facebook.inject.Lazies.AnonymousClass1 */

            @Override // com.facebook.inject.Lazy, javax.inject.Provider
            public T get() {
                return (T) t;
            }
        };
    }

    public static <T> Lazy<T> from(Provider<T> provider) {
        if (provider instanceof Lazy) {
            return (Lazy) provider;
        }
        return new ProviderMemoizingLazy(provider);
    }

    private static final class ProviderMemoizingLazy<T> implements Lazy<T> {
        @Nullable
        private Provider<T> mProvider;
        private T mValue;

        public ProviderMemoizingLazy(Provider<T> provider) {
            this.mProvider = (Provider) Preconditions.checkNotNull(provider);
        }

        @Override // com.facebook.inject.Lazy, javax.inject.Provider
        public T get() {
            if (this.mProvider != null) {
                synchronized (this) {
                    if (this.mProvider != null) {
                        this.mValue = this.mProvider.get();
                        this.mProvider = null;
                    }
                }
            }
            return this.mValue;
        }
    }
}
