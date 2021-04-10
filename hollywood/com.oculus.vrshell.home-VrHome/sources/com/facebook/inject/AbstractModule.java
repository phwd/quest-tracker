package com.facebook.inject;

import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public abstract class AbstractModule {
    protected Binder mBinder;

    AbstractModule() {
    }

    /* access modifiers changed from: protected */
    public Binder getBinder() {
        return this.mBinder;
    }

    /* access modifiers changed from: protected */
    public void configure() {
    }

    /* access modifiers changed from: protected */
    public <T> AnnotatedBindingBuilder<T> bind(Class<T> type) {
        return this.mBinder.bind(type);
    }

    /* access modifiers changed from: protected */
    public <T> LinkedBindingBuilder<T> bind(Key<T> key) {
        return this.mBinder.bind(key);
    }

    /* access modifiers changed from: protected */
    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> type) {
        return this.mBinder.bindDefault(type);
    }

    /* access modifiers changed from: protected */
    public <T> LinkedBindingBuilder<T> bindDefault(Key<T> key) {
        return this.mBinder.bindDefault(key);
    }

    /* access modifiers changed from: protected */
    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> type) {
        return this.mBinder.bindComponent(type);
    }

    /* access modifiers changed from: protected */
    public <T> void bindAssistedProvider(Class<? extends Object<T>> cls) {
        this.mBinder.bindAssistedProvider(cls);
    }

    /* access modifiers changed from: protected */
    public <T> void declareMultiBinding(Class<T> type) {
        this.mBinder.declareMultiBinding((Class<?>) type);
    }

    /* access modifiers changed from: protected */
    public <T> void declareMultiBinding(Class<T> type, Class<? extends Annotation> annotation) {
        this.mBinder.declareMultiBinding(type, annotation);
    }

    /* access modifiers changed from: protected */
    public <T> void declareMultiBinding(Key<T> key) {
        this.mBinder.declareMultiBinding((Key<?>) key);
    }

    /* access modifiers changed from: protected */
    public <T> MultiBinding<T> bindMulti(Class<T> type) {
        return this.mBinder.bindMulti(type);
    }

    /* access modifiers changed from: protected */
    public <T> MultiBinding<T> bindMulti(Class<T> type, Class<? extends Annotation> annotation) {
        return this.mBinder.bindMulti(type, annotation);
    }

    /* access modifiers changed from: protected */
    public <T> MultiBinding<T> bindMulti(Key<T> key) {
        return this.mBinder.bindMulti(key);
    }

    /* access modifiers changed from: protected */
    public <T> void assertBindingInstalled(Class<T> type) {
        this.mBinder.assertBindingInstalled(type);
    }

    /* access modifiers changed from: protected */
    public <T> void assertBindingInstalled(Class<T> type, Class<? extends Annotation> annotation) {
        this.mBinder.assertBindingInstalled(Key.get((Class) type, annotation));
    }

    /* access modifiers changed from: protected */
    public <T> void assertBindingInstalled(Key<T> key) {
        this.mBinder.assertBindingInstalled(key);
    }

    /* access modifiers changed from: protected */
    public void require(Class<?> moduleClass) {
        this.mBinder.require(moduleClass);
    }

    /* access modifiers changed from: protected */
    public void bindScope(Class<? extends Annotation> annotation, Scope scope) {
        this.mBinder.bindScope(annotation, scope);
    }
}
