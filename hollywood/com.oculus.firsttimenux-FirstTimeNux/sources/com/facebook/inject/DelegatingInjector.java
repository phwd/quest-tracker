package com.facebook.inject;

import android.content.Context;
import com.facebook.testenv.TestEnvironment;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.inject.Provider;

public abstract class DelegatingInjector extends AbstractInjector implements StaticBindingInterceptor {
    private final FbInjector mDelegate;

    @Override // com.facebook.inject.Injector
    public abstract <T> T getInstance(Key<T> key);

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public abstract <T> T getInstance(Key<T> key, Context context);

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public abstract <T> Lazy<T> getLazy(Key<T> key, Context context);

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public abstract <T> Provider<T> getProvider(Key<T> key, Context context);

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.FbInjector
    public abstract <T> void injectComponent(Class<T> cls, T t);

    protected DelegatingInjector(FbInjector delegate) {
        this.mDelegate = delegate;
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.InjectorLike
    public InjectorLike getApplicationInjector() {
        return this.mDelegate.getApplicationInjector();
    }

    @Override // com.facebook.inject.FbInjector
    public List<Class<?>> getRequiredModulesForTool() {
        return this.mDelegate.getRequiredModulesForTool();
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
