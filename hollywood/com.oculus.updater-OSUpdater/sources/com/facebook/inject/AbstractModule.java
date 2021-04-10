package com.facebook.inject;

import java.lang.annotation.Annotation;

public abstract class AbstractModule implements Module {
    protected Binder mBinder;

    AbstractModule() {
    }

    /* access modifiers changed from: protected */
    public void bindScope(Class<? extends Annotation> cls, Scope scope) {
        this.mBinder.bindScope(cls, scope);
    }
}
