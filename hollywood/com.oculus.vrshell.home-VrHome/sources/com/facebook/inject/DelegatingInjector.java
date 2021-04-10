package com.facebook.inject;

import com.facebook.testenv.TestEnvironment;
import java.lang.annotation.Annotation;

public abstract class DelegatingInjector extends AbstractInjector implements StaticBindingInterceptor {
    private final FbInjector mDelegate;

    protected DelegatingInjector(FbInjector delegate) {
        this.mDelegate = delegate;
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
    public <T extends Scope> T getScope(Class<? extends Annotation> type) {
        return (T) this.mDelegate.getScope(type);
    }

    @Override // com.facebook.inject.StaticBindingInterceptor
    public Object getObjectForBindingId(int bindingId) {
        if (TestEnvironment.isTest() && (this.mDelegate instanceof StaticBindingInterceptor)) {
            return ((StaticBindingInterceptor) this.mDelegate).getObjectForBindingId(bindingId);
        }
        if (TestEnvironment.isTest()) {
            return null;
        }
        throw new RuntimeException("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\n In Test Environment( " + TestEnvironment.isTest() + " ) Name of injector: " + getClass().getCanonicalName() + " || Delegate: " + this.mDelegate.getClass().getCanonicalName());
    }
}
