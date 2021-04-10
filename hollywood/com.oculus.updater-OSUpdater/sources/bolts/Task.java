package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    private static Task<?> TASK_CANCELLED = new Task<>(true);
    private static Task<Boolean> TASK_FALSE = new Task<>((Boolean) false);
    private static Task<?> TASK_NULL = new Task<>((Object) null);
    private static Task<Boolean> TASK_TRUE = new Task<>((Boolean) true);
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations = new ArrayList();
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock = new Object();
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;

    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return unobservedExceptionHandler;
    }

    Task() {
    }

    private Task(TResult tresult) {
        trySetResult(tresult);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.lock) {
            tresult = this.result;
        }
        return tresult;
    }

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            if (this.error != null) {
                this.errorHasBeenObserved = true;
                if (this.unobservedErrorNotifier != null) {
                    this.unobservedErrorNotifier.setObserved();
                    this.unobservedErrorNotifier = null;
                }
            }
            exc = this.error;
        }
        return exc;
    }

    public boolean waitForCompletion(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean isCompleted;
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait(timeUnit.toMillis(j));
            }
            isCompleted = isCompleted();
        }
        return isCompleted;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        if (tresult == null) {
            return (Task<TResult>) TASK_NULL;
        }
        if (tresult instanceof Boolean) {
            return tresult.booleanValue() ? (Task<TResult>) TASK_TRUE : (Task<TResult>) TASK_FALSE;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor) {
        return call(callable, executor, null);
    }

    public static <TResult> Task<TResult> call(final Callable<TResult> callable, Executor executor, final CancellationToken cancellationToken) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable() {
                /* class bolts.Task.AnonymousClass4 */

                /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: bolts.TaskCompletionSource */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    CancellationToken cancellationToken = cancellationToken;
                    if (cancellationToken == null || !cancellationToken.isCancellationRequested()) {
                        try {
                            taskCompletionSource.setResult(callable.call());
                        } catch (CancellationException unused) {
                            taskCompletionSource.setCancelled();
                        } catch (Exception e) {
                            taskCompletionSource.setError(e);
                        }
                    } else {
                        taskCompletionSource.setCancelled();
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor, final CancellationToken cancellationToken) {
        boolean isCompleted;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            isCompleted = isCompleted();
            if (!isCompleted) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    /* class bolts.Task.AnonymousClass10 */

                    @Override // bolts.Continuation
                    public Void then(Task<TResult> task) {
                        Task.completeImmediately(taskCompletionSource, continuation, task, executor, cancellationToken);
                        return null;
                    }
                });
            }
        }
        if (isCompleted) {
            completeImmediately(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        boolean isCompleted;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            isCompleted = isCompleted();
            if (!isCompleted) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    /* class bolts.Task.AnonymousClass11 */

                    @Override // bolts.Continuation
                    public Void then(Task<TResult> task) {
                        Task.completeAfterTask(taskCompletionSource, continuation, task, executor, cancellationToken);
                        return null;
                    }
                });
            }
        }
        if (isCompleted) {
            completeAfterTask(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, null);
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                /* class bolts.Task.AnonymousClass14 */

                /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: bolts.TaskCompletionSource */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    CancellationToken cancellationToken = cancellationToken;
                    if (cancellationToken == null || !cancellationToken.isCancellationRequested()) {
                        try {
                            taskCompletionSource.setResult(continuation.then(task));
                        } catch (CancellationException unused) {
                            taskCompletionSource.setCancelled();
                        } catch (Exception e) {
                            taskCompletionSource.setError(e);
                        }
                    } else {
                        taskCompletionSource.setCancelled();
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                /* class bolts.Task.AnonymousClass15 */

                public void run() {
                    CancellationToken cancellationToken = cancellationToken;
                    if (cancellationToken == null || !cancellationToken.isCancellationRequested()) {
                        try {
                            Task task = (Task) continuation.then(task);
                            if (task == null) {
                                taskCompletionSource.setResult(null);
                            } else {
                                task.continueWith(new Continuation<TContinuationResult, Void>() {
                                    /* class bolts.Task.AnonymousClass15.AnonymousClass1 */

                                    @Override // bolts.Continuation
                                    public Void then(Task<TContinuationResult> task) {
                                        if (cancellationToken == null || !cancellationToken.isCancellationRequested()) {
                                            if (task.isCancelled()) {
                                                taskCompletionSource.setCancelled();
                                            } else if (task.isFaulted()) {
                                                taskCompletionSource.setError(task.getError());
                                            } else {
                                                taskCompletionSource.setResult(task.getResult());
                                            }
                                            return null;
                                        }
                                        taskCompletionSource.setCancelled();
                                        return null;
                                    }
                                });
                            }
                        } catch (CancellationException unused) {
                            taskCompletionSource.setCancelled();
                        } catch (Exception e) {
                            taskCompletionSource.setError(e);
                        }
                    } else {
                        taskCompletionSource.setCancelled();
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation<TResult, Void> continuation : this.continuations) {
                try {
                    continuation.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean trySetResult(TResult tresult) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = tresult;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean trySetError(Exception exc) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = exc;
            this.errorHasBeenObserved = false;
            this.lock.notifyAll();
            runContinuations();
            if (!this.errorHasBeenObserved && getUnobservedExceptionHandler() != null) {
                this.unobservedErrorNotifier = new UnobservedErrorNotifier(this);
            }
            return true;
        }
    }
}
