package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.Exception;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtIncompatible
@Deprecated
public abstract class AbstractCheckedFuture<V, X extends Exception> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements CheckedFuture<V, X> {
    /* access modifiers changed from: protected */
    public abstract X mapException(Exception exc);

    protected AbstractCheckedFuture(ListenableFuture<V> delegate) {
        super(delegate);
    }

    @Override // com.google.common.util.concurrent.CheckedFuture
    @CanIgnoreReturnValue
    public V checkedGet() throws Exception {
        try {
            return get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw mapException(e);
        } catch (CancellationException | ExecutionException e2) {
            throw mapException(e2);
        }
    }

    @Override // com.google.common.util.concurrent.CheckedFuture
    @CanIgnoreReturnValue
    public V checkedGet(long timeout, TimeUnit unit) throws TimeoutException, Exception {
        try {
            return get(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw mapException(e);
        } catch (CancellationException | ExecutionException e2) {
            throw mapException(e2);
        }
    }
}
