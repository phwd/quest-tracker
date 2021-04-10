package com.facebook.inject;

import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public abstract class AbstractModule implements Module {
    protected Binder mBinder;

    /* access modifiers changed from: protected */
    public void configure() {
    }

    AbstractModule() {
    }

    /* access modifiers changed from: protected */
    public Binder getBinder() {
        return this.mBinder;
    }

    /* access modifiers changed from: protected */
    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return this.mBinder.bind(cls);
    }

    /* access modifiers changed from: protected */
    public <T> LinkedBindingBuilder<T> bind(Key<T> key) {
        return this.mBinder.bind(key);
    }

    /* access modifiers changed from: protected */
    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        return this.mBinder.bindDefault(cls);
    }

    /* access modifiers changed from: protected */
    public <T> LinkedBindingBuilder<T> bindDefault(Key<T> key) {
        return this.mBinder.bindDefault(key);
    }

    /* access modifiers changed from: protected */
    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return this.mBinder.bindComponent(cls);
    }

    /* access modifiers changed from: protected */
    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        this.mBinder.bindAssistedProvider(cls);
    }

    /* access modifiers changed from: protected */
    public <T> void declareMultiBinding(Class<T> cls) {
        this.mBinder.declareMultiBinding((Class<?>) cls);
    }

    /* access modifiers changed from: protected */
    public <T> void declareMultiBinding(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.declareMultiBinding(cls, cls2);
    }

    /* access modifiers changed from: protected */
    public <T> void declareMultiBinding(Key<T> key) {
        this.mBinder.declareMultiBinding((Key<?>) key);
    }

    /* access modifiers changed from: protected */
    public <T> MultiBinding<T> bindMulti(Class<T> cls) {
        return this.mBinder.bindMulti(cls);
    }

    /* access modifiers changed from: protected */
    public <T> MultiBinding<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return this.mBinder.bindMulti(cls, cls2);
    }

    /* access modifiers changed from: protected */
    public <T> MultiBinding<T> bindMulti(Key<T> key) {
        return this.mBinder.bindMulti(key);
    }

    /* access modifiers changed from: protected */
    public <T> void assertBindingInstalled(Class<T> cls) {
        this.mBinder.assertBindingInstalled(cls);
    }

    /* access modifiers changed from: protected */
    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        this.mBinder.assertBindingInstalled(Key.get((Class) cls, cls2));
    }

    /* access modifiers changed from: protected */
    public <T> void assertBindingInstalled(Key<T> key) {
        this.mBinder.assertBindingInstalled(key);
    }

    /* access modifiers changed from: protected */
    public void require(Class<?> cls) {
        this.mBinder.require(cls);
    }

    /* access modifiers changed from: protected */
    public void bindScope(Class<? extends Annotation> cls, Scope scope) {
        this.mBinder.bindScope(cls, scope);
    }
}
