package bolts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR;
    public static final Executor IMMEDIATE_EXECUTOR;
    public static Task<?> TASK_CANCELLED = new Task<>(true);
    public static Task<Boolean> TASK_FALSE = new Task<>((Boolean) false);
    public static Task<?> TASK_NULL = new Task<>((Object) null);
    public static Task<Boolean> TASK_TRUE = new Task<>((Boolean) true);
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.INSTANCE.uiThread;
    public static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    public boolean cancelled;
    public boolean complete;
    public List<Continuation<TResult, Void>> continuations = new ArrayList();
    public Exception error;
    public boolean errorHasBeenObserved;
    public final Object lock = new Object();
    public TResult result;
    public UnobservedErrorNotifier unobservedErrorNotifier;

    public class TaskCompletionSource extends TaskCompletionSource<TResult> {
        public TaskCompletionSource() {
        }
    }

    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: bolts.Task<TResult> */
    /* JADX WARN: Multi-variable type inference failed */
    public <TOut> Task<TOut> cast() {
        return this;
    }

    static {
        BoltsExecutors boltsExecutors = BoltsExecutors.INSTANCE;
        BACKGROUND_EXECUTOR = boltsExecutors.background;
        IMMEDIATE_EXECUTOR = boltsExecutors.immediate;
    }

    public static <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                /* class bolts.Task.AnonymousClass15 */

                public void run() {
                    CancellationToken cancellationToken = cancellationToken;
                    if (cancellationToken == null || !cancellationToken.tokenSource.isCancellationRequested()) {
                        try {
                            Task task = (Task) continuation.then(task);
                            if (task == null) {
                                taskCompletionSource.setResult(null);
                            } else {
                                task.continueWith(new Continuation<TContinuationResult, Void>() {
                                    /* class bolts.Task.AnonymousClass15.AnonymousClass1 */

                                    @Override // bolts.Continuation
                                    public Void then(Task<TContinuationResult> task) {
                                        CancellationToken cancellationToken = cancellationToken;
                                        if ((cancellationToken != null && cancellationToken.tokenSource.isCancellationRequested()) || task.isCancelled()) {
                                            taskCompletionSource.setCancelled();
                                            return null;
                                        } else if (task.isFaulted()) {
                                            taskCompletionSource.setError(task.getError());
                                            return null;
                                        } else {
                                            taskCompletionSource.setResult(task.getResult());
                                            return null;
                                        }
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

    public static <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                /* class bolts.Task.AnonymousClass14 */

                /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: bolts.TaskCompletionSource */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    CancellationToken cancellationToken = cancellationToken;
                    if (cancellationToken == null || !cancellationToken.tokenSource.isCancellationRequested()) {
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

    public static <TResult> Task<TResult>.TaskCompletionSource create() {
        return new TaskCompletionSource();
    }

    public static <TResult> Task<TResult> forError(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setError(exc);
        return taskCompletionSource.task;
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
        return taskCompletionSource.task;
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

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            exc = this.error;
            if (exc != null) {
                this.errorHasBeenObserved = true;
                UnobservedErrorNotifier unobservedErrorNotifier2 = this.unobservedErrorNotifier;
                if (unobservedErrorNotifier2 != null) {
                    unobservedErrorNotifier2.task = null;
                    this.unobservedErrorNotifier = null;
                }
            }
        }
        return exc;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.lock) {
            tresult = this.result;
        }
        return tresult;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = false;
            if (getError() != null) {
                z = true;
            }
        }
        return z;
    }

    public Task<Void> makeVoid() {
        return continueWithTask(new Continuation<TResult, Task<Void>>() {
            /* class bolts.Task.AnonymousClass3 */

            /* JADX DEBUG: Type inference failed for r0v6. Raw type applied. Possible types: bolts.Task<?>, bolts.Task<java.lang.Void> */
            @Override // bolts.Continuation
            public Task<Void> then(Task<TResult> task) throws Exception {
                if (task.isCancelled()) {
                    return Task.TASK_CANCELLED;
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                return Task.forResult(null);
            }
        });
    }

    public boolean trySetCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = false;
            if (!this.complete) {
                z = true;
                this.complete = true;
                this.cancelled = true;
                this.lock.notifyAll();
                runContinuations();
            }
        }
        return z;
    }

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
            if (!this.errorHasBeenObserved && unobservedExceptionHandler != null) {
                this.unobservedErrorNotifier = new UnobservedErrorNotifier(this);
            }
            return true;
        }
    }

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

    public static <TResult> Task<TResult> cancelled() {
        return (Task<TResult>) TASK_CANCELLED;
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return unobservedExceptionHandler;
    }

    /* JADX DEBUG: Type inference failed for r0v7. Raw type applied. Possible types: bolts.Task<?>, bolts.Task<java.lang.Void> */
    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return TASK_NULL;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final ArrayList arrayList = new ArrayList();
        final Object obj = new Object();
        final AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Iterator<? extends Task<?>> it = collection.iterator();
        while (it.hasNext()) {
            ((Task) it.next()).continueWith(new Continuation<Object, Void>() {
                /* class bolts.Task.AnonymousClass8 */

                @Override // bolts.Continuation
                public Void then(Task<Object> task) {
                    if (task.isFaulted()) {
                        synchronized (obj) {
                            arrayList.add(task.getError());
                        }
                    }
                    if (task.isCancelled()) {
                        atomicBoolean.set(true);
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        ArrayList arrayList = arrayList;
                        if (arrayList.size() != 0) {
                            if (arrayList.size() == 1) {
                                taskCompletionSource.setError((Exception) arrayList.get(0));
                            } else {
                                taskCompletionSource.setError(new AggregateException(String.format("There were %d exceptions.", Integer.valueOf(arrayList.size())), arrayList));
                                return null;
                            }
                        } else if (atomicBoolean.get()) {
                            taskCompletionSource.setCancelled();
                            return null;
                        } else {
                            taskCompletionSource.setResult(null);
                            return null;
                        }
                    }
                    return null;
                }
            });
        }
        return taskCompletionSource.task;
    }

    public static <TResult> Task<List<TResult>> whenAllResult(final Collection<? extends Task<TResult>> collection) {
        return whenAll(collection).onSuccess(new Continuation<Void, List<TResult>>() {
            /* class bolts.Task.AnonymousClass7 */

            @Override // bolts.Continuation
            public List<TResult> then(Task<Void> task) throws Exception {
                if (collection.size() == 0) {
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList();
                for (Task task2 : collection) {
                    arrayList.add(task2.getResult());
                }
                return arrayList;
            }
        });
    }

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: bolts.Task<?>, bolts.Task<bolts.Task<?>> */
    public static Task<Task<?>> whenAny(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return TASK_NULL;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Iterator<? extends Task<?>> it = collection.iterator();
        while (it.hasNext()) {
            ((Task) it.next()).continueWith(new Continuation<Object, Void>() {
                /* class bolts.Task.AnonymousClass6 */

                @Override // bolts.Continuation
                public Void then(Task<Object> task) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.setResult(task);
                        return null;
                    }
                    task.getError();
                    return null;
                }
            });
        }
        return taskCompletionSource.task;
    }

    public static <TResult> Task<Task<TResult>> whenAnyResult(Collection<? extends Task<TResult>> collection) {
        if (collection.size() == 0) {
            return (Task<Task<TResult>>) TASK_NULL;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Iterator<? extends Task<TResult>> it = collection.iterator();
        while (it.hasNext()) {
            ((Task) it.next()).continueWith(new Continuation<TResult, Void>() {
                /* class bolts.Task.AnonymousClass5 */

                @Override // bolts.Continuation
                public Void then(Task<TResult> task) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.setResult(task);
                        return null;
                    }
                    task.getError();
                    return null;
                }
            });
        }
        return taskCompletionSource.task;
    }

    public static void setUnobservedExceptionHandler(UnobservedExceptionHandler unobservedExceptionHandler2) {
        unobservedExceptionHandler = unobservedExceptionHandler2;
    }

    public Task() {
    }

    public Task(TResult tresult) {
        trySetResult(tresult);
    }

    public Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return call(callable, IMMEDIATE_EXECUTOR, null);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, CancellationToken cancellationToken) {
        return call(callable, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor) {
        return call(callable, executor, null);
    }

    public static <TResult> Task<TResult> call(final Callable<TResult> callable, Executor executor, final CancellationToken cancellationToken) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable() {
                /* class bolts.Task.AnonymousClass4 */

                /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: bolts.TaskCompletionSource */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    CancellationToken cancellationToken = cancellationToken;
                    if (cancellationToken == null || !cancellationToken.tokenSource.isCancellationRequested()) {
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
        return taskCompletionSource.task;
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable) {
        return call(callable, BACKGROUND_EXECUTOR, null);
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable, CancellationToken cancellationToken) {
        return call(callable, BACKGROUND_EXECUTOR, cancellationToken);
    }

    public static Task<Void> delay(long j) {
        return delay(j, BoltsExecutors.INSTANCE.scheduled, null);
    }

    public static Task<Void> delay(long j, CancellationToken cancellationToken) {
        return delay(j, BoltsExecutors.INSTANCE.scheduled, cancellationToken);
    }

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: bolts.Task<?>, bolts.Task<java.lang.Void> */
    /* JADX DEBUG: Type inference failed for r0v8. Raw type applied. Possible types: bolts.Task<?>, bolts.Task<java.lang.Void> */
    public static Task<Void> delay(long j, ScheduledExecutorService scheduledExecutorService, CancellationToken cancellationToken) {
        if (cancellationToken != null && cancellationToken.tokenSource.isCancellationRequested()) {
            return TASK_CANCELLED;
        }
        if (j <= 0) {
            return TASK_NULL;
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
            /* class bolts.Task.AnonymousClass1 */

            public void run() {
                taskCompletionSource.trySetResult(null);
            }
        }, j, TimeUnit.MILLISECONDS);
        if (cancellationToken != null) {
            cancellationToken.tokenSource.register(new Runnable() {
                /* class bolts.Task.AnonymousClass2 */

                public void run() {
                    schedule.cancel(true);
                    taskCompletionSource.task.trySetCancelled();
                }
            });
        }
        return taskCompletionSource.task;
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation) {
        return continueWhile(callable, continuation, IMMEDIATE_EXECUTOR, null);
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, CancellationToken cancellationToken) {
        return continueWhile(callable, continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, Executor executor) {
        return continueWhile(callable, continuation, executor, null);
    }

    public Task<Void> continueWhile(final Callable<Boolean> callable, final Continuation<Void, Task<Void>> continuation, final Executor executor, final CancellationToken cancellationToken) {
        final Capture capture = new Capture();
        capture.value = (T) new Continuation<Void, Task<Void>>() {
            /* class bolts.Task.AnonymousClass9 */

            /* JADX DEBUG: Type inference failed for r0v12. Raw type applied. Possible types: bolts.Task<?>, bolts.Task<java.lang.Void> */
            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                CancellationToken cancellationToken = cancellationToken;
                if (cancellationToken != null && cancellationToken.tokenSource.isCancellationRequested()) {
                    return Task.TASK_CANCELLED;
                }
                if (((Boolean) callable.call()).booleanValue()) {
                    return Task.forResult(null).onSuccessTask(continuation, executor).onSuccessTask(capture.value, executor);
                }
                return Task.forResult(null);
            }
        };
        return makeVoid().continueWithTask(capture.value, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, CancellationToken cancellationToken) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return continueWith(continuation, executor, null);
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: bolts.Task<TResult>, bolts.Task<TContinuationResult> */
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
        return (Task<TResult>) taskCompletionSource.task;
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken cancellationToken) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(continuation, executor, null);
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: bolts.Task<TResult>, bolts.Task<TContinuationResult> */
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
        return (Task<TResult>) taskCompletionSource.task;
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, CancellationToken cancellationToken) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return onSuccess(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor, final CancellationToken cancellationToken) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            /* class bolts.Task.AnonymousClass12 */

            @Override // bolts.Continuation
            public Task<TContinuationResult> then(Task<TResult> task) {
                CancellationToken cancellationToken = cancellationToken;
                if (cancellationToken == null || !cancellationToken.tokenSource.isCancellationRequested()) {
                    if (task.isFaulted()) {
                        return Task.forError(task.getError());
                    }
                    if (!task.isCancelled()) {
                        return task.continueWith(continuation);
                    }
                }
                return (Task<TContinuationResult>) Task.TASK_CANCELLED;
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken cancellationToken) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return onSuccessTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, final CancellationToken cancellationToken) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            /* class bolts.Task.AnonymousClass13 */

            @Override // bolts.Continuation
            public Task<TContinuationResult> then(Task<TResult> task) {
                CancellationToken cancellationToken = cancellationToken;
                if (cancellationToken == null || !cancellationToken.tokenSource.isCancellationRequested()) {
                    if (task.isFaulted()) {
                        return Task.forError(task.getError());
                    }
                    if (!task.isCancelled()) {
                        return task.continueWithTask(continuation);
                    }
                }
                return (Task<TContinuationResult>) Task.TASK_CANCELLED;
            }
        }, executor);
    }

    public void waitForCompletion() throws InterruptedException {
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait();
            }
        }
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
}
