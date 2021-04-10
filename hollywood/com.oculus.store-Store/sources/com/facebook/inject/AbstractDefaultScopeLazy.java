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

    protected AbstractDefaultScopeLazy(InjectorLike injector) {
        this.mInjector = injector;
        this.mScopeAwareInjector = injector.getScopeAwareInjector();
    }

    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public final T get() {
        if (this.mScopeAwareInjector != null) {
            synchronized (this) {
                if (this.mScopeAwareInjector != null) {
                    BasicScopeAwareInjector scopeAwareInjector = this.mScopeAwareInjector;
                    ScopeSet scopeSet = ScopeSet.get();
                    byte enterResult = scopeSet.enterScopes(this.mScopesSeenAtConstruction);
                    Object token = scopeAwareInjector.enterPreamble();
                    try {
                        this.mCachedInstance = onGetInstance(this.mInjector.getScopeUnawareInjector());
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
