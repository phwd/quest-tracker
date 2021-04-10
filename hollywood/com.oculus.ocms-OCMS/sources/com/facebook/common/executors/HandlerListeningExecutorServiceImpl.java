package com.facebook.common.executors;

import android.os.Handler;
import com.facebook.annotations.OkToExtend;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@OkToExtend
@Nullsafe(Nullsafe.Mode.LOCAL)
public class HandlerListeningExecutorServiceImpl extends AbstractExecutorService implements HandlerListeningExecutorService {
    protected final Handler mHandler;

    /* access modifiers changed from: protected */
    public Runnable convertCommand(Runnable runnable) {
        return runnable;
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public HandlerListeningExecutorServiceImpl(Handler handler) {
        this.mHandler = handler;
    }

    @Override // com.facebook.common.executors.HandlerListeningExecutorService
    public Handler getHandler() {
        return this.mHandler;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new ListenableScheduledFutureImpl(getHandler(), runnable, t);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new ListenableScheduledFutureImpl(getHandler(), callable);
    }

    /* access modifiers changed from: protected */
    public <T> ListenableScheduledFutureImpl<T> newFutureFor(Runnable runnable, @Nullable T t) {
        return new ListenableScheduledFutureImpl<>(getHandler(), runnable, t);
    }

    /* access modifiers changed from: protected */
    public <T> ListenableScheduledFutureImpl<T> newFutureFor(Callable<T> callable) {
        return new ListenableScheduledFutureImpl<>(getHandler(), callable);
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable runnable) {
        if ((runnable instanceof Future) && !(runnable instanceof HandlerDeadlockAwareForwardingFuture)) {
            BLog.w(getClass(), "%s submitted as runnable to %s. Potential deadlocks on get().", runnable.getClass(), getClass());
        }
        this.mHandler.post(convertCommand(runnable));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    public ListenableScheduledFuture<?> submit(Runnable runnable) {
        return submit(runnable, (Object) null);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    public <T> ListenableScheduledFuture<T> submit(Runnable runnable, @Nullable T t) {
        if (runnable != null) {
            ListenableScheduledFutureImpl<T> newFutureFor = newFutureFor(runnable, t);
            execute(newFutureFor);
            return newFutureFor;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    public <T> ListenableScheduledFuture<T> submit(Callable<T> callable) {
        if (callable != null) {
            ListenableScheduledFutureImpl<T> newFutureFor = newFutureFor(callable);
            execute(newFutureFor);
            return newFutureFor;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService, com.google.common.util.concurrent.ListeningScheduledExecutorService
    public ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ListenableScheduledFutureImpl newFutureFor = newFutureFor(runnable, null);
        this.mHandler.postDelayed(convertCommand(newFutureFor), timeUnit.toMillis(j));
        return newFutureFor;
    }

    @Override // java.util.concurrent.ScheduledExecutorService, com.google.common.util.concurrent.ListeningScheduledExecutorService
    public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        ListenableScheduledFutureImpl newFutureFor = newFutureFor(callable);
        this.mHandler.postDelayed(convertCommand(newFutureFor), timeUnit.toMillis(j));
        return newFutureFor;
    }

    @Override // java.util.concurrent.ScheduledExecutorService, com.google.common.util.concurrent.ListeningScheduledExecutorService
    public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService, com.google.common.util.concurrent.ListeningScheduledExecutorService
    public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.common.executors.HandlerListeningExecutorService
    public void quit() {
        this.mHandler.getLooper().quit();
    }

    @Override // com.facebook.common.executors.HandlerListeningExecutorService
    public boolean isHandlerThread() {
        return Thread.currentThread() == this.mHandler.getLooper().getThread();
    }
}
