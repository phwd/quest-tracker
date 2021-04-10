package com.facebook.common.executors;

import android.os.Handler;
import com.facebook.common.runnablename.ProvidesInnerRunnable;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ListenableScheduledFutureImpl<V> extends HandlerDeadlockAwareForwardingFuture<V> implements Runnable, ProvidesInnerRunnable, ListenableScheduledFuture<V>, RunnableFuture<V> {
    private final FbListenableFutureTask<V> mListenableFuture;

    @Override // com.facebook.common.executors.HandlerDeadlockAwareForwardingFuture, com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
    public /* bridge */ /* synthetic */ Object get() throws InterruptedException, ExecutionException {
        return super.get();
    }

    @Override // com.facebook.common.executors.HandlerDeadlockAwareForwardingFuture, com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
    public /* bridge */ /* synthetic */ Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return super.get(j, timeUnit);
    }

    public ListenableScheduledFutureImpl(Handler handler, Callable<V> callable) {
        super(handler);
        this.mListenableFuture = FbListenableFutureTask.create(callable);
    }

    public ListenableScheduledFutureImpl(Handler handler, Runnable runnable, @Nullable V v) {
        super(handler);
        this.mListenableFuture = FbListenableFutureTask.create(runnable, v);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.util.concurrent.ForwardingFuture, com.google.common.util.concurrent.ForwardingFuture
    public FbListenableFutureTask<V> delegate() {
        return this.mListenableFuture;
    }

    public long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public int compareTo(Delayed delayed) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        delegate().addListener(runnable, executor);
    }

    public void run() {
        delegate().run();
    }

    @Override // com.google.common.util.concurrent.ForwardingFuture
    public boolean cancel(boolean z) {
        return super.cancel(false);
    }

    @Override // com.facebook.common.runnablename.ProvidesInnerRunnable
    public Object getInnerRunnable() {
        return this.mListenableFuture;
    }
}
