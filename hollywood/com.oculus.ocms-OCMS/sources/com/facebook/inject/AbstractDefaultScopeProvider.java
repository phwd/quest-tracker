package com.facebook.inject;

import javax.inject.Provider;

public abstract class AbstractDefaultScopeProvider<T> implements Provider<T> {
    private final InjectorLike mInjector;
    private final ScopeAwareInjector mScopeAwareInjector;

    /* access modifiers changed from: protected */
    public abstract T onGetInstance(InjectorLike injectorLike);

    protected AbstractDefaultScopeProvider(InjectorLike injectorLike) {
        this.mInjector = injectorLike;
        this.mScopeAwareInjector = injectorLike.getScopeAwareInjector();
    }

    @Override // javax.inject.Provider
    public final T get() {
        ScopeUnawareInjector scopeUnawareInjector = this.mInjector.getScopeUnawareInjector();
        Object enterPreamble = this.mScopeAwareInjector.enterPreamble();
        try {
            return onGetInstance(scopeUnawareInjector);
        } finally {
            this.mScopeAwareInjector.exitPostamble(enterPreamble);
        }
    }
}
