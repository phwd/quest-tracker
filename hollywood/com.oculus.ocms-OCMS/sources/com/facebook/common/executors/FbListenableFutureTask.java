package com.facebook.common.executors;

import com.facebook.common.runnablename.ProvidesInnerRunnable;
import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

public class FbListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V>, ProvidesInnerRunnable {
    private final ExecutionList executionList;
    private final WeakReference<Callable<V>> mCallableWeakRef;
    private final WeakReference<Runnable> mRunnableWeakRef;

    public static <V> FbListenableFutureTask<V> create(Callable<V> callable) {
        return new FbListenableFutureTask<>(callable);
    }

    public static <V> FbListenableFutureTask<V> create(Runnable runnable, @Nullable V v) {
        return new FbListenableFutureTask<>(runnable, v);
    }

    FbListenableFutureTask(Callable<V> callable) {
        super(callable);
        this.executionList = new ExecutionList();
        this.mCallableWeakRef = new WeakReference<>(callable);
        this.mRunnableWeakRef = null;
    }

    FbListenableFutureTask(Runnable runnable, @Nullable V v) {
        super(runnable, v);
        this.executionList = new ExecutionList();
        this.mCallableWeakRef = null;
        this.mRunnableWeakRef = new WeakReference<>(runnable);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.executionList.add(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public void done() {
        this.executionList.execute();
    }

    @Override // com.facebook.common.runnablename.ProvidesInnerRunnable
    public Object getInnerRunnable() {
        WeakReference<Callable<V>> weakReference = this.mCallableWeakRef;
        Runnable runnable = null;
        Callable<V> callable = weakReference != null ? weakReference.get() : null;
        if (callable != null) {
            return callable;
        }
        WeakReference<Runnable> weakReference2 = this.mRunnableWeakRef;
        if (weakReference2 != null) {
            runnable = weakReference2.get();
        }
        return runnable != null ? runnable : this;
    }
}
