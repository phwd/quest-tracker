package com.facebook.inject;

import javax.annotation.Nullable;

public abstract class AbstractDefaultScopeLazy<T> implements Lazy<T> {
    private T mCachedInstance;
    private final InjectorLike mInjector;
    @Nullable
    private volatile BasicScopeAwareInjector mScopeAwareInjector;
    private final byte mScopesSeenAtConstruction = ScopeSet.get().getSeenScopes();

    /* access modifiers changed from: protected */
    public abstract T onGetInstance(InjectorLike injectorLike);

    protected AbstractDefaultScopeLazy(InjectorLike injectorLike) {
        this.mInjector = injectorLike;
        this.mScopeAwareInjector = injectorLike.getScopeAwareInjector();
    }

    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public final T get() {
        if (this.mScopeAwareInjector != null) {
            synchronized (this) {
                if (this.mScopeAwareInjector != null) {
                    BasicScopeAwareInjector basicScopeAwareInjector = this.mScopeAwareInjector;
                    ScopeSet scopeSet = ScopeSet.get();
                    byte enterScopes = scopeSet.enterScopes(this.mScopesSeenAtConstruction);
                    Object enterPreamble = basicScopeAwareInjector.enterPreamble();
                    try {
                        this.mCachedInstance = onGetInstance(this.mInjector.getScopeUnawareInjector());
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
