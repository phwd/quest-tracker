package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import javax.inject.Provider;

public abstract class BaseScopeAwareInjector extends DelegatingInjector implements ScopeAwareInjector {
    protected final FbInjector mDelegate;

    public BaseScopeAwareInjector(FbInjector delegate) {
        super(delegate);
        this.mDelegate = delegate;
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public <T> T getInstance(Key<T> key, Context context) {
        Object preamble = enterPreamble();
        try {
            return (T) this.mDelegate.getScopeUnawareInjector().getInstance(key, context);
        } finally {
            exitPostamble(preamble);
        }
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int key, Context context) {
        Object preamble = enterPreamble();
        try {
            return this.mDelegate.getScopeUnawareInjector().getInstance(key, context);
        } finally {
            exitPostamble(preamble);
        }
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public <T> Provider<T> getProvider(Key<T> key, Context context) {
        return wrapProvider(this.mDelegate.getScopeUnawareInjector().getProvider(key, context));
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public <T> Lazy<T> getLazy(Key<T> key, Context context) {
        return ProviderLazy.fromProvider(this.mDelegate.getScopeUnawareInjector().getProvider(key, context), this);
    }

    @Override // com.facebook.inject.DelegatingInjector, com.facebook.inject.InjectorLike
    @Deprecated
    public ScopeAwareInjector getScopeAwareInjector() {
        return this;
    }

    /* access modifiers changed from: protected */
    public final <T> Provider<T> wrapProvider(final Provider<T> provider) {
        return new Provider<T>() {
            /* class com.facebook.inject.BaseScopeAwareInjector.AnonymousClass1 */

            @Override // javax.inject.Provider
            public T get() {
                Object preamble = BaseScopeAwareInjector.this.enterPreamble();
                try {
                    return (T) provider.get();
                } finally {
                    BaseScopeAwareInjector.this.exitPostamble(preamble);
                }
            }
        };
    }
}
