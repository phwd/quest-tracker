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

    private ProviderLazy(Provider<T> provider, BasicScopeAwareInjector basicScopeAwareInjector) {
        this.mProvider = provider;
        this.mScopeAwareInjector = basicScopeAwareInjector;
    }

    public static <T> Lazy<T> fromProvider(Provider<T> provider, BasicScopeAwareInjector basicScopeAwareInjector) {
        if (provider instanceof Lazy) {
            return castProviderToLazy(provider);
        }
        return new ProviderLazy(provider, basicScopeAwareInjector);
    }

    private static <T> Lazy<T> castProviderToLazy(Provider<T> provider) {
        return (Lazy) provider;
    }

    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public T get() {
        if (this.mScopeAwareInjector != null) {
            synchronized (this) {
                if (this.mScopeAwareInjector != null) {
                    BasicScopeAwareInjector basicScopeAwareInjector = this.mScopeAwareInjector;
                    ScopeSet scopeSet = ScopeSet.get();
                    byte enterScopes = scopeSet.enterScopes(this.mScopesSeenAtConstruction);
                    Object enterPreamble = basicScopeAwareInjector.enterPreamble();
                    try {
                        this.mCachedInstance = this.mProvider.get();
                        this.mProvider = null;
                        this.mScopeAwareInjector = null;
                    } finally {
                        basicScopeAwareInjector.exitPostamble(enterPreamble);
                        scopeSet.resetScopes(enterScopes);
                    }
                }
            }
        }
        return this.mCachedInstance;
    }
}
