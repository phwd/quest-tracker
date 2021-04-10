package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;

public final class SettableFuture<V> extends AbstractFuture.TrustedFuture<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public boolean set(V value) {
        return super.set(value);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }

    private SettableFuture() {
    }
}
