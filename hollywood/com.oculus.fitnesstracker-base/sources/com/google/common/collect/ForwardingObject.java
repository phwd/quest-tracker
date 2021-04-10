package com.google.common.collect;

public abstract class ForwardingObject {
    /* access modifiers changed from: protected */
    public abstract Object delegate();

    public String toString() {
        return delegate().toString();
    }
}
