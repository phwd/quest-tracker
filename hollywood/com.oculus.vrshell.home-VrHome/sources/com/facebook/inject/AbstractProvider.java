package com.facebook.inject;

import android.content.Context;
import com.facebook.testenv.TestEnvironment;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public abstract class AbstractProvider<T> implements InjectorLike, ProviderWithInjector<T>, StaticBindingInterceptor {
    private InjectorLike mInjector;

    public void setInjector(InjectorLike injector) {
        this.mInjector = injector;
    }

    @Override // com.facebook.inject.Injector
    public <T extends Scope> T getScope(Class<? extends Annotation> type) {
        return (T) this.mInjector.getScope(type);
    }

    @Override // com.facebook.inject.Injector
    public <S> Provider<S> getProvider(Key<S> key, Context context) {
        return getScopeAwareInjectorInternal().getProvider(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> Lazy<S> getLazy(Key<S> key, Context context) {
        return this.mInjector.getLazy(key, context);
    }

    public <S> S getInstance(Class<S> type) {
        return (S) getInstance(type, this.mInjector.getInjectorThreadStack().getContext());
    }

    public <S> S getInstance(Class<S> type, Class<? extends Annotation> annotation) {
        return (S) getInstance(type, annotation, this.mInjector.getInjectorThreadStack().getContext());
    }

    public <S> S getInstance(Key<S> key) {
        return (S) getInstance(key, this.mInjector.getInjectorThreadStack().getContext());
    }

    public Object getInstance(int key) {
        return getInstance(key, this.mInjector.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Class<S> type, Context context) {
        return (S) this.mInjector.getInstance(type, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Class<S> type, Class<? extends Annotation> annotation, Context context) {
        return (S) this.mInjector.getInstance(type, annotation, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Key<S> key, Context context) {
        return (S) this.mInjector.getInstance(key, context);
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int key, Context context) {
        return this.mInjector.getInstance(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Set<T> getSet(Key<T> key, Context context) {
        return this.mInjector.getSet(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Provider<Set<T>> getSetProvider(Key<T> key, Context context) {
        return getScopeAwareInjectorInternal().getSetProvider(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Lazy<Set<T>> getLazySet(Key<T> key, Context context) {
        return this.mInjector.getLazySet(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Lazy<List<T>> getLazyList(Key<T> key, Context context) {
        return this.mInjector.getLazyList(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Provider<List<T>> getListProvider(Key<T> key, Context context) {
        return getScopeAwareInjectorInternal().getListProvider(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <T> List<T> getList(Key<T> key, Context context) {
        return this.mInjector.getList(key, context);
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

    /* access modifiers changed from: protected */
    public Injector getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // com.facebook.inject.StaticBindingInterceptor
    public Object getObjectForBindingId(int bindingId) {
        if (TestEnvironment.isTest() && (this.mInjector instanceof StaticBindingInterceptor)) {
            return ((StaticBindingInterceptor) this.mInjector).getObjectForBindingId(bindingId);
        }
        throw new RuntimeException("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\nName of injector: " + getClass().getCanonicalName());
    }
}
