package com.facebook.inject;

import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public interface Binder {
    <T> void assertBindingInstalled(Key<T> key);

    <T> void assertBindingInstalled(Class<T> cls);

    <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2);

    <T> AnnotatedBindingBuilder<T> bind(Class<T> cls);

    <T> LinkedBindingBuilder<T> bind(Key<T> key);

    <T> void bindAssistedProvider(Class<? extends Object<T>> cls);

    <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls);

    <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls);

    <T> LinkedBindingBuilder<T> bindDefault(Key<T> key);

    <T> MultiBinding<T> bindMulti(Key<T> key);

    <T> MultiBinding<T> bindMulti(Class<T> cls);

    <T> MultiBinding<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2);

    void bindScope(Class<? extends Annotation> cls, Scope scope);

    void declareMultiBinding(Key<?> key);

    void declareMultiBinding(Class<?> cls);

    void declareMultiBinding(Class<?> cls, Class<? extends Annotation> cls2);

    void require(Class<?> cls);
}
