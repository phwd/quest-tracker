package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public final class Futures extends GwtFuturesCatchingSpecialization {
    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new CallbackListener(listenableFuture, futureCallback), executor);
    }

    static final class CallbackListener<V> implements Runnable {
        final FutureCallback<? super V> callback;
        final Future<V> future;

        CallbackListener(Future<V> future2, FutureCallback<? super V> futureCallback) {
            this.future = future2;
            this.callback = futureCallback;
        }

        public final void run() {
            try {
                this.callback.onSuccess(Futures.getDone(this.future));
            } catch (ExecutionException e) {
                this.callback.onFailure(e.getCause());
            } catch (Error | RuntimeException e2) {
                this.callback.onFailure(e2);
            }
        }

        public final String toString() {
            MoreObjects.ToStringHelper toStringHelper = new MoreObjects.ToStringHelper(getClass().getSimpleName(), (byte) 0);
            FutureCallback<? super V> futureCallback = this.callback;
            MoreObjects.ToStringHelper.ValueHolder valueHolder = new MoreObjects.ToStringHelper.ValueHolder((byte) 0);
            toStringHelper.holderTail.next = valueHolder;
            toStringHelper.holderTail = valueHolder;
            valueHolder.value = futureCallback;
            return toStringHelper.toString();
        }
    }

    @CanIgnoreReturnValue
    public static <V> V getDone(Future<V> future) throws ExecutionException {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
        return (V) Uninterruptibles.getUninterruptibly(future);
    }
}
