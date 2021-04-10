package com.facebook.inject;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.testenv.TestEnvironment;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public abstract class AbstractProvider<T> implements ProviderWithInjector<T>, InjectorLike, StaticBindingInterceptor {
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
    public <S> Provider<S> getProvider(Key<S> key, Context context) {
        return getScopeAwareInjectorInternal().getProvider(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> Lazy<S> getLazy(Key<S> key, Context context) {
        return this.mInjector.getLazy(key, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Class<S> cls) {
        return (S) getInstance(cls, this.mInjector.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2) {
        return (S) getInstance(cls, cls2, this.mInjector.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Key<S> key) {
        return (S) getInstance(key, this.mInjector.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int i) {
        return getInstance(i, this.mInjector.getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Class<S> cls, Context context) {
        return (S) this.mInjector.getInstance(cls, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Class<S> cls, Class<? extends Annotation> cls2, Context context) {
        return (S) this.mInjector.getInstance(cls, cls2, context);
    }

    @Override // com.facebook.inject.Injector
    public <S> S getInstance(Key<S> key, Context context) {
        return (S) this.mInjector.getInstance(key, context);
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int i, Context context) {
        return this.mInjector.getInstance(i, context);
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
    @VisibleForTesting
    public Injector getScopeAwareInjectorInternal() {
        return this.mInjector.getScopeAwareInjector();
    }

    @Override // com.facebook.inject.StaticBindingInterceptor
    public Object getObjectForBindingId(int i) {
        if (TestEnvironment.isTest()) {
            InjectorLike injectorLike = this.mInjector;
            if (injectorLike instanceof StaticBindingInterceptor) {
                return ((StaticBindingInterceptor) injectorLike).getObjectForBindingId(i);
            }
        }
        throw new RuntimeException("Not Implemented. This should only be called from the test frameworks, and must be explicitly overridden\nName of injector: " + getClass().getCanonicalName());
    }
}
