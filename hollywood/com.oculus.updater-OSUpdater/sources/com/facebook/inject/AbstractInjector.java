package com.facebook.inject;

public abstract class AbstractInjector extends FbInjector {
    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.FbInjector
    public abstract <T> void injectComponent(Class<T> cls, T t);
}
