package java.util.concurrent;

public class ExecutorCompletionService<V> implements CompletionService<V> {
    private final AbstractExecutorService aes;
    private final BlockingQueue<Future<V>> completionQueue;
    private final Executor executor;

    private static class QueueingFuture<V> extends FutureTask<Void> {
        private final BlockingQueue<Future<V>> completionQueue;
        private final Future<V> task;

        QueueingFuture(RunnableFuture<V> task2, BlockingQueue<Future<V>> completionQueue2) {
            super(task2, null);
            this.task = task2;
            this.completionQueue = completionQueue2;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.FutureTask
        public void done() {
            this.completionQueue.add(this.task);
        }
    }

    private RunnableFuture<V> newTaskFor(Callable<V> task) {
        AbstractExecutorService abstractExecutorService = this.aes;
        if (abstractExecutorService == null) {
            return new FutureTask(task);
        }
        return abstractExecutorService.newTaskFor(task);
    }

    private RunnableFuture<V> newTaskFor(Runnable task, V result) {
        AbstractExecutorService abstractExecutorService = this.aes;
        if (abstractExecutorService == null) {
            return new FutureTask(task, result);
        }
        return abstractExecutorService.newTaskFor(task, result);
    }

    public ExecutorCompletionService(Executor executor2) {
        if (executor2 != null) {
            this.executor = executor2;
            this.aes = executor2 instanceof AbstractExecutorService ? (AbstractExecutorService) executor2 : null;
            this.completionQueue = new LinkedBlockingQueue();
            return;
        }
        throw new NullPointerException();
    }

    public ExecutorCompletionService(Executor executor2, BlockingQueue<Future<V>> completionQueue2) {
        if (executor2 == null || completionQueue2 == null) {
            throw new NullPointerException();
        }
        this.executor = executor2;
        this.aes = executor2 instanceof AbstractExecutorService ? (AbstractExecutorService) executor2 : null;
        this.completionQueue = completionQueue2;
    }

    @Override // java.util.concurrent.CompletionService
    public Future<V> submit(Callable<V> task) {
        if (task != null) {
            RunnableFuture<V> f = newTaskFor(task);
            this.executor.execute(new QueueingFuture(f, this.completionQueue));
            return f;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.CompletionService
    public Future<V> submit(Runnable task, V result) {
        if (task != null) {
            RunnableFuture<V> f = newTaskFor(task, result);
            this.executor.execute(new QueueingFuture(f, this.completionQueue));
            return f;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.CompletionService
    public Future<V> take() throws InterruptedException {
        return this.completionQueue.take();
    }

    @Override // java.util.concurrent.CompletionService
    public Future<V> poll() {
        return this.completionQueue.poll();
    }

    @Override // java.util.concurrent.CompletionService
    public Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException {
        return this.completionQueue.poll(timeout, unit);
    }
}
