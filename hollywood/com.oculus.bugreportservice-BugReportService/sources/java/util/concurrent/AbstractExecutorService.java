package java.util.concurrent;

public abstract class AbstractExecutorService implements ExecutorService {
    /* access modifiers changed from: protected */
    public RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return new FutureTask(runnable, obj);
    }

    @Override // java.util.concurrent.ExecutorService
    public Future submit(Runnable runnable) {
        if (runnable != null) {
            RunnableFuture newTaskFor = newTaskFor(runnable, null);
            execute(newTaskFor);
            return newTaskFor;
        }
        throw new NullPointerException();
    }
}
