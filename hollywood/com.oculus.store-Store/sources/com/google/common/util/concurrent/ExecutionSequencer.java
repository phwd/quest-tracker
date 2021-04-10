package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

@Beta
public final class ExecutionSequencer {
    private final AtomicReference<ListenableFuture<Object>> ref = new AtomicReference<>(Futures.immediateFuture(null));

    /* access modifiers changed from: package-private */
    public enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED
    }

    private ExecutionSequencer() {
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    public <T> ListenableFuture<T> submit(final Callable<T> callable, Executor executor) {
        Preconditions.checkNotNull(callable);
        return submitAsync(new AsyncCallable<T>() {
            /* class com.google.common.util.concurrent.ExecutionSequencer.AnonymousClass1 */

            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<T> call() throws Exception {
                return Futures.immediateFuture(callable.call());
            }
        }, executor);
    }

    public <T> ListenableFuture<T> submitAsync(final AsyncCallable<T> callable, final Executor executor) {
        Preconditions.checkNotNull(callable);
        final AtomicReference<RunningState> runningState = new AtomicReference<>(RunningState.NOT_RUN);
        AsyncCallable<T> task = new AsyncCallable<T>() {
            /* class com.google.common.util.concurrent.ExecutionSequencer.AnonymousClass2 */

            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<T> call() throws Exception {
                if (!runningState.compareAndSet(RunningState.NOT_RUN, RunningState.STARTED)) {
                    return Futures.immediateCancelledFuture();
                }
                return callable.call();
            }
        };
        final SettableFuture<Object> newFuture = SettableFuture.create();
        final ListenableFuture<?> oldFuture = this.ref.getAndSet(newFuture);
        final ListenableFuture<T> taskFuture = Futures.submitAsync(task, new Executor() {
            /* class com.google.common.util.concurrent.ExecutionSequencer.AnonymousClass3 */

            public void execute(Runnable runnable) {
                oldFuture.addListener(runnable, executor);
            }
        });
        final ListenableFuture<T> outputFuture = Futures.nonCancellationPropagating(taskFuture);
        Runnable listener = new Runnable() {
            /* class com.google.common.util.concurrent.ExecutionSequencer.AnonymousClass4 */

            public void run() {
                if (taskFuture.isDone() || (outputFuture.isCancelled() && runningState.compareAndSet(RunningState.NOT_RUN, RunningState.CANCELLED))) {
                    newFuture.setFuture(oldFuture);
                }
            }
        };
        outputFuture.addListener(listener, MoreExecutors.directExecutor());
        taskFuture.addListener(listener, MoreExecutors.directExecutor());
        return outputFuture;
    }
}
