package com.facebook.inject;

import javax.annotation.Nullable;
import javax.inject.Provider;

public class ProviderLazy<T> implements Lazy<T> {
    private T mCachedInstance;
    @Nullable
    private Provider<T> mProvider;
    @Nullable
    private volatile BasicScopeAwareInjector mScopeAwareInjector;
    private final byte mScopesSeenAtConstruction = ScopeSet.get().getSeenScopes();

    private ProviderLazy(Provider<T> provider, BasicScopeAwareInjector scopeAwareInjector) {
        this.mProvider = provider;
        this.mScopeAwareInjector = scopeAwareInjector;
    }

    public static <T> Lazy<T> fromProvider(Provider<T> provider, BasicScopeAwareInjector contextScopeAwareInjector) {
        if (provider instanceof Lazy) {
            return castProviderToLazy(provider);
        }
        return new ProviderLazy(provider, contextScopeAwareInjector);
    }

    private static <T> Lazy<T> castProviderToLazy(Provider<T> provider) {
        return (Lazy) provider;
    }

    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public T get() {
        if (this.mScopeAwareInjector != null) {
            synchronized (this) {
                if (this.mScopeAwareInjector != null) {
                    BasicScopeAwareInjector scopeAwareInjector = this.mScopeAwareInjector;
                    ScopeSet scopeSet = ScopeSet.get();
                    byte enterResult = scopeSet.enterScopes(this.mScopesSeenAtConstruction);
                    Object token = scopeAwareInjector.enterPreamble();
                    try {
                        this.mCachedInstance = this.mProvider.get();
                        this.mProvider = null;
                        this.mScopeAwareInjector = null;
                    } finally {
                        scopeAwareInjector.exitPostamble(token);
                        scopeSet.resetScopes(enterResult);
                    }
                }
            }
        }
        return this.mCachedInstance;
    }
}
