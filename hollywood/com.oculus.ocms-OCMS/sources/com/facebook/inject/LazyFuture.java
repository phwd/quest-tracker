package com.facebook.inject;

import com.google.common.util.concurrent.ListenableFuture;

public abstract class LazyFuture<V> implements Lazy<ListenableFuture<V>> {
    private ListenableFuture<V> mFuture = null;

    /* access modifiers changed from: protected */
    public abstract ListenableFuture<V> createFuture();

    @Override // com.facebook.inject.Lazy, javax.inject.Provider
    public synchronized ListenableFuture<V> get() {
        if (this.mFuture == null) {
            this.mFuture = createFuture();
        }
        return this.mFuture;
    }
}
