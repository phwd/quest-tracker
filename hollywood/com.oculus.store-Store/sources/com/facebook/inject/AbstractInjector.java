package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public abstract class AbstractInjector extends FbInjector {
    @Override // com.facebook.inject.InjectorLike
    public abstract InjectorLike getApplicationInjector();

    @Override // com.facebook.inject.Injector
    public abstract <T> T getInstance(Key<T> key, Context context);

    @Override // com.facebook.inject.Injector
    public abstract <T> Lazy<T> getLazy(Key<T> key, Context context);

    @Override // com.facebook.inject.Injector
    public abstract <T> Provider<T> getProvider(Key<T> key, Context context);

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.FbInjector
    public abstract <T> void injectComponent(Class<T> cls, T t);

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Class<T> type) {
        return (T) getInstance(Key.get((Class) type), getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Class<T> type, Class<? extends Annotation> annotationType) {
        return (T) getInstance(Key.get((Class) type, annotationType), getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Class<T> type, Context context) {
        return (T) getInstance(Key.get((Class) type), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Class<T> type, Class<? extends Annotation> annotationType, Context context) {
        return (T) getInstance(Key.get((Class) type, annotationType), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Set<T> getSet(Key<T> key, Context context) {
        return (Set) getInstance(getMultiBindingKey(key), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Provider<Set<T>> getSetProvider(Key<T> key, Context context) {
        return getProvider(getMultiBindingKey(key), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Lazy<Set<T>> getLazySet(Key<T> key, Context context) {
        return getLazy(getMultiBindingKey(key), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Lazy<List<T>> getLazyList(Key<T> key, Context context) {
        return getLazy(getListBindingKey(key), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> Provider<List<T>> getListProvider(Key<T> key, Context context) {
        return getProvider(getListBindingKey(key), context);
    }

    @Override // com.facebook.inject.Injector
    public <T> List<T> getList(Key<T> key, Context context) {
        return (List) getInstance(getListBindingKey(key), context);
    }
}
