package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public abstract class AbstractProvider<T> implements InjectorLike, ProviderWithInjector<T> {
    private InjectorLike mInjector;

    @Override // com.facebook.inject.ProviderWithInjector
    public void setInjector(InjectorLike injectorLike) {
        this.mInjector = injectorLike;
    }

    @Override // com.facebook.inject.Injector
    public <T extends Scope> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mInjector.getScope(cls);
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Key<S> key, Context context) {
        return (S) this.mInjector.getInstance(key, context);
    }

    @Override // com.facebook.inject.InjectorLike
    @Deprecated
    public ScopeUnawareInjector getScopeUnawareInjector() {
        return this.mInjector.getScopeUnawareInjector();
    }

    @Override // com.facebook.inject.InjectorLike
    @Deprecated
    public ScopeAwareInjector getScopeAwareInjector() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // com.facebook.inject.InjectorLike
    @Deprecated
    public InjectorThreadStack getInjectorThreadStack() {
        return this.mInjector.getInjectorThreadStack();
    }

    @Override // com.facebook.inject.InjectorLike
    public InjectorLike getApplicationInjector() {
        return this.mInjector.getApplicationInjector();
    }
}
