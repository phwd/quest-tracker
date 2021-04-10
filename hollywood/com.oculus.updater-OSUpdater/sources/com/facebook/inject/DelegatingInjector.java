package com.facebook.inject;

import java.lang.annotation.Annotation;

public abstract class DelegatingInjector extends AbstractInjector {
    private final FbInjector mDelegate;

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.FbInjector
    public abstract <T> void injectComponent(Class<T> cls, T t);

    protected DelegatingInjector(FbInjector fbInjector) {
        this.mDelegate = fbInjector;
    }

    @Override // com.facebook.inject.InjectorLike
    public InjectorLike getApplicationInjector() {
        return this.mDelegate.getApplicationInjector();
    }

    @Override // com.facebook.inject.InjectorLike
    public ScopeUnawareInjector getScopeUnawareInjector() {
        return this.mDelegate.getScopeUnawareInjector();
    }

    @Override // com.facebook.inject.InjectorLike
    public ScopeAwareInjector getScopeAwareInjector() {
        return this.mDelegate.getScopeAwareInjector();
    }

    @Override // com.facebook.inject.InjectorLike
    public InjectorThreadStack getInjectorThreadStack() {
        return this.mDelegate.getInjectorThreadStack();
    }

    @Override // com.facebook.inject.Injector
    public <T extends Scope> T getScope(Class<? extends Annotation> cls) {
        return (T) this.mDelegate.getScope(cls);
    }
}
