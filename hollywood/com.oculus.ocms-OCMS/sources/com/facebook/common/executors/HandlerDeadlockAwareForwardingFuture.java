package com.facebook.common.executors;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.ForwardingFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class HandlerDeadlockAwareForwardingFuture<V> extends ForwardingFuture<V> {
    private final Handler mHandler;

    public HandlerDeadlockAwareForwardingFuture(Handler handler) {
        this.mHandler = handler;
    }

    private boolean isOnHandlerThread() {
        return Looper.myLooper() == this.mHandler.getLooper();
    }

    private void checkDeadlock() {
        if (!isDone()) {
            throw new IllegalStateException("Must not call get() function from this Handler thread. Will deadlock!");
        }
    }

    @Override // java.util.concurrent.Future, com.google.common.util.concurrent.ForwardingFuture
    public V get() throws InterruptedException, ExecutionException {
        if (isOnHandlerThread()) {
            checkDeadlock();
        }
        return (V) super.get();
    }

    @Override // java.util.concurrent.Future, com.google.common.util.concurrent.ForwardingFuture
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (isOnHandlerThread()) {
            checkDeadlock();
        }
        return (V) super.get(j, timeUnit);
    }
}
