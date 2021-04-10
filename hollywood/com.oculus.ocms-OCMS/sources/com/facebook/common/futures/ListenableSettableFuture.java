package com.facebook.common.futures;

import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ListenableSettableFuture<V> implements ListenableFuture<V> {
    private boolean mCancelled;
    private boolean mDone;
    private Throwable mException;
    private final ExecutionList mListenerList = new ExecutionList();
    private V mResult;

    /* access modifiers changed from: protected */
    public void interruptTask() {
    }

    public synchronized boolean cancel(boolean z) {
        if (this.mDone) {
            return false;
        }
        this.mCancelled = true;
        this.mDone = true;
        if (z) {
            interruptTask();
        }
        notifyAll();
        this.mListenerList.execute();
        return true;
    }

    public synchronized boolean isCancelled() {
        return this.mCancelled;
    }

    public synchronized boolean isDone() {
        return this.mDone;
    }

    public synchronized boolean set(V v) {
        if (this.mDone) {
            return false;
        }
        this.mResult = v;
        this.mDone = true;
        this.mListenerList.execute();
        notifyAll();
        return true;
    }

    @Override // java.util.concurrent.Future
    public synchronized V get() throws InterruptedException, ExecutionException {
        try {
        } catch (TimeoutException e) {
            throw new ExecutionException(e);
        }
        return get(0, TimeUnit.MILLISECONDS);
    }

    @Override // java.util.concurrent.Future
    public synchronized V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!Thread.interrupted()) {
            if (!this.mDone) {
                if (j > 0) {
                    timeUnit.timedWait(this, j);
                } else {
                    wait();
                }
            }
            if (!this.mDone) {
                throw new TimeoutException();
            } else if (this.mCancelled) {
                throw new CancellationException();
            } else if (this.mException == null) {
            } else {
                throw new ExecutionException(this.mException);
            }
        } else {
            throw new InterruptedException();
        }
        return this.mResult;
    }

    public synchronized boolean setException(Throwable th) {
        if (th == null) {
            throw new NullPointerException();
        } else if (this.mDone) {
            return false;
        } else {
            this.mException = th;
            this.mDone = true;
            this.mListenerList.execute();
            notifyAll();
            return true;
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.mListenerList.add(runnable, executor);
    }
}
