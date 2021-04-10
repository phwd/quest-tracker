package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

final class AsyncSettableFuture<V> extends ForwardingListenableFuture<V> {
    private final ListenableFuture<V> dereferenced = Futures.dereference(this.nested);
    private final NestedFuture<V> nested = new NestedFuture<>();

    public static <V> AsyncSettableFuture<V> create() {
        return new AsyncSettableFuture<>();
    }

    private AsyncSettableFuture() {
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.util.concurrent.ForwardingListenableFuture, com.google.common.util.concurrent.ForwardingListenableFuture, com.google.common.util.concurrent.ForwardingListenableFuture, com.google.common.util.concurrent.ForwardingFuture, com.google.common.util.concurrent.ForwardingFuture
    public ListenableFuture<V> delegate() {
        return this.dereferenced;
    }

    public boolean setFuture(ListenableFuture<? extends V> future) {
        return this.nested.setFuture((ListenableFuture) Preconditions.checkNotNull(future));
    }

    public boolean setValue(@Nullable V value) {
        return setFuture(Futures.immediateFuture(value));
    }

    public boolean setException(Throwable exception) {
        return setFuture(Futures.immediateFailedFuture(exception));
    }

    public boolean isSet() {
        return this.nested.isDone();
    }

    /* access modifiers changed from: private */
    public static final class NestedFuture<V> extends AbstractFuture<ListenableFuture<? extends V>> {
        private NestedFuture() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.util.concurrent.ListenableFuture<? extends V> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean setFuture(ListenableFuture<? extends V> value) {
            boolean result = set(value);
            if (isCancelled()) {
                value.cancel(wasInterrupted());
            }
            return result;
        }
    }
}
