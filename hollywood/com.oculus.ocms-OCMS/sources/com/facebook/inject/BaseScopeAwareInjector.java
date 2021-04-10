package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import javax.inject.Provider;

public abstract class BaseScopeAwareInjector extends DelegatingInjector implements ScopeAwareInjector, InjectableComponentWithoutContext {
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

    @Override // com.facebook.inject.Injector, com.facebook.inject.DelegatingInjector
    public <T> T getInstance(Key<T> key) {
        return (T) getInstance(key, this.mDelegate.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int i) {
        return getInstance(i, this.mDelegate.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector, com.facebook.inject.DelegatingInjector
    public <T> T getInstance(Key<T> key, Context context) {
        Object enterPreamble = enterPreamble();
        try {
            return (T) this.mDelegate.getScopeUnawareInjector().getInstance(key, context);
        } finally {
            exitPostamble(enterPreamble);
        }
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int i, Context context) {
        Object enterPreamble = enterPreamble();
        try {
            return this.mDelegate.getScopeUnawareInjector().getInstance(i, context);
        } finally {
            exitPostamble(enterPreamble);
        }
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector, com.facebook.inject.DelegatingInjector
    public <T> Provider<T> getProvider(Key<T> key, Context context) {
        return wrapProvider(this.mDelegate.getScopeUnawareInjector().getProvider(key, context));
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector, com.facebook.inject.DelegatingInjector
    public <T> Lazy<T> getLazy(Key<T> key, Context context) {
        return ProviderLazy.fromProvider(this.mDelegate.getScopeUnawareInjector().getProvider(key, context), this);
    }

    /* access modifiers changed from: protected */
    public final <T> Provider<T> wrapProvider(final Provider<T> provider) {
        return new Provider<T>() {
            /* class com.facebook.inject.BaseScopeAwareInjector.AnonymousClass1 */

            @Override // javax.inject.Provider
            public T get() {
                Object enterPreamble = BaseScopeAwareInjector.this.enterPreamble();
                try {
                    return (T) provider.get();
                } finally {
                    BaseScopeAwareInjector.this.exitPostamble(enterPreamble);
                }
            }
        };
    }
}
