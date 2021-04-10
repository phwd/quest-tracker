package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* access modifiers changed from: package-private */
public abstract class WrappingExecutorService implements ExecutorService {
    private final ExecutorService delegate;

    /* access modifiers changed from: protected */
    public abstract <T> Callable<T> wrapTask(Callable<T> callable);

    protected WrappingExecutorService(ExecutorService delegate2) {
        this.delegate = (ExecutorService) Preconditions.checkNotNull(delegate2);
    }

    /* access modifiers changed from: protected */
    public Runnable wrapTask(Runnable command) {
        final Callable<Object> wrapped = wrapTask(Executors.callable(command, null));
        return new Runnable() {
            /* class com.google.common.util.concurrent.WrappingExecutorService.AnonymousClass1 */

            public void run() {
                try {
                    wrapped.call();
                } catch (Exception e) {
                    Throwables.propagate(e);
                }
            }
        };
    }

    private final <T> ImmutableList<Callable<T>> wrapTasks(Collection<? extends Callable<T>> tasks) {
        ImmutableList.Builder<Callable<T>> builder = ImmutableList.builder();
        for (Callable<T> task : tasks) {
            builder.add(wrapTask(task));
        }
        return builder.build();
    }

    public final void execute(Runnable command) {
        this.delegate.execute(wrapTask(command));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> Future<T> submit(Callable<T> task) {
        return this.delegate.submit(wrapTask((Callable) Preconditions.checkNotNull(task)));
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future<?> submit(Runnable task) {
        return this.delegate.submit(wrapTask(task));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> Future<T> submit(Runnable task, T result) {
        return this.delegate.submit(wrapTask(task), result);
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.delegate.invokeAll(wrapTasks(tasks));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate.invokeAll(wrapTasks(tasks), timeout, unit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return (T) this.delegate.invokeAny(wrapTasks(tasks));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) this.delegate.invokeAny(wrapTasks(tasks), timeout, unit);
    }

    public final void shutdown() {
        this.delegate.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public final boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    public final boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate.awaitTermination(timeout, unit);
    }
}
