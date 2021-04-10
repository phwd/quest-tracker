package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public interface Injector {
    @Deprecated
    Object getInstance(int i, Context context);

    @Deprecated
    <T> T getInstance(Key<T> key, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Context context);

    @Deprecated
    <T> T getInstance(Class<T> cls, Class<? extends Annotation> cls2, Context context);

    @Deprecated
    <T> Lazy<T> getLazy(Key<T> key, Context context);

    @Deprecated
    <T> Lazy<List<T>> getLazyList(Key<T> key, Context context);

    @Deprecated
    <T> Lazy<Set<T>> getLazySet(Key<T> key, Context context);

    @Deprecated
    <T> List<T> getList(Key<T> key, Context context);

    @Deprecated
    <T> Provider<List<T>> getListProvider(Key<T> key, Context context);

    @Deprecated
    <T> Provider<T> getProvider(Key<T> key, Context context);

    <T extends Scope> T getScope(Class<? extends Annotation> cls);

    @Deprecated
    <T> Set<T> getSet(Key<T> key, Context context);

    @Deprecated
    <T> Provider<Set<T>> getSetProvider(Key<T> key, Context context);
}
