package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;

public abstract class BaseScopeAwareInjector extends DelegatingInjector implements InjectableComponentWithoutContext, ScopeAwareInjector {
    protected final FbInjector mDelegate;

    @Override // com.facebook.inject.DelegatingInjector, com.facebook.inject.InjectorLike
    @Deprecated
    public ScopeAwareInjector getScopeAwareInjector() {
        return this;
    }

    public BaseScopeAwareInjector(FbInjector fbInjector) {
        super(fbInjector);
        this.mDelegate = fbInjector;
    }

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Key<T> key, Context context) {
        Object enterPreamble = enterPreamble();
        try {
            return (T) this.mDelegate.getScopeUnawareInjector().getInstance(key, context);
        } finally {
            exitPostamble(enterPreamble);
        }
    }
}
