package java.util.concurrent;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Executors {
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    }

    public static ExecutorService newWorkStealingPool(int parallelism) {
        return new ForkJoinPool(parallelism, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    }

    public static ExecutorService newWorkStealingPool() {
        return new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    }

    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory);
    }

    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()));
    }

    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory));
    }

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue());
    }

    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1));
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory) {
        return new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1, threadFactory));
    }

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }

    public static ExecutorService unconfigurableExecutorService(ExecutorService executor) {
        if (executor != null) {
            return new DelegatedExecutorService(executor);
        }
        throw new NullPointerException();
    }

    public static ScheduledExecutorService unconfigurableScheduledExecutorService(ScheduledExecutorService executor) {
        if (executor != null) {
            return new DelegatedScheduledExecutorService(executor);
        }
        throw new NullPointerException();
    }

    public static ThreadFactory defaultThreadFactory() {
        return new DefaultThreadFactory();
    }

    public static ThreadFactory privilegedThreadFactory() {
        return new PrivilegedThreadFactory();
    }

    public static <T> Callable<T> callable(Runnable task, T result) {
        if (task != null) {
            return new RunnableAdapter(task, result);
        }
        throw new NullPointerException();
    }

    public static Callable<Object> callable(Runnable task) {
        if (task != null) {
            return new RunnableAdapter(task, null);
        }
        throw new NullPointerException();
    }

    public static Callable<Object> callable(final PrivilegedAction<?> action) {
        if (action != null) {
            return new Callable<Object>() {
                /* class java.util.concurrent.Executors.AnonymousClass1 */

                @Override // java.util.concurrent.Callable
                public Object call() {
                    return PrivilegedAction.this.run();
                }
            };
        }
        throw new NullPointerException();
    }

    public static Callable<Object> callable(final PrivilegedExceptionAction<?> action) {
        if (action != null) {
            return new Callable<Object>() {
                /* class java.util.concurrent.Executors.AnonymousClass2 */

                @Override // java.util.concurrent.Callable
                public Object call() throws Exception {
                    return PrivilegedExceptionAction.this.run();
                }
            };
        }
        throw new NullPointerException();
    }

    public static <T> Callable<T> privilegedCallable(Callable<T> callable) {
        if (callable != null) {
            return new PrivilegedCallable(callable);
        }
        throw new NullPointerException();
    }

    public static <T> Callable<T> privilegedCallableUsingCurrentClassLoader(Callable<T> callable) {
        if (callable != null) {
            return new PrivilegedCallableUsingCurrentClassLoader(callable);
        }
        throw new NullPointerException();
    }

    private static final class RunnableAdapter<T> implements Callable<T> {
        private final T result;
        private final Runnable task;

        RunnableAdapter(Runnable task2, T result2) {
            this.task = task2;
            this.result = result2;
        }

        @Override // java.util.concurrent.Callable
        public T call() {
            this.task.run();
            return this.result;
        }
    }

    private static final class PrivilegedCallable<T> implements Callable<T> {
        final AccessControlContext acc = AccessController.getContext();
        final Callable<T> task;

        PrivilegedCallable(Callable<T> task2) {
            this.task = task2;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            try {
                return (T) AccessController.doPrivileged(new PrivilegedExceptionAction<T>() {
                    /* class java.util.concurrent.Executors.PrivilegedCallable.AnonymousClass1 */

                    @Override // java.security.PrivilegedExceptionAction
                    public T run() throws Exception {
                        return PrivilegedCallable.this.task.call();
                    }
                }, this.acc);
            } catch (PrivilegedActionException e) {
                throw e.getException();
            }
        }
    }

    private static final class PrivilegedCallableUsingCurrentClassLoader<T> implements Callable<T> {
        final AccessControlContext acc = AccessController.getContext();
        final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        final Callable<T> task;

        PrivilegedCallableUsingCurrentClassLoader(Callable<T> task2) {
            this.task = task2;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            try {
                return (T) AccessController.doPrivileged(new PrivilegedExceptionAction<T>() {
                    /* class java.util.concurrent.Executors.PrivilegedCallableUsingCurrentClassLoader.AnonymousClass1 */

                    @Override // java.security.PrivilegedExceptionAction
                    public T run() throws Exception {
                        Thread t = Thread.currentThread();
                        ClassLoader cl = t.getContextClassLoader();
                        if (PrivilegedCallableUsingCurrentClassLoader.this.ccl == cl) {
                            return PrivilegedCallableUsingCurrentClassLoader.this.task.call();
                        }
                        t.setContextClassLoader(PrivilegedCallableUsingCurrentClassLoader.this.ccl);
                        try {
                            return PrivilegedCallableUsingCurrentClassLoader.this.task.call();
                        } finally {
                            t.setContextClassLoader(cl);
                        }
                    }
                }, this.acc);
            } catch (PrivilegedActionException e) {
                throw e.getException();
            }
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        DefaultThreadFactory() {
            ThreadGroup threadGroup;
            SecurityManager s = System.getSecurityManager();
            if (s != null) {
                threadGroup = s.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.group = threadGroup;
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            ThreadGroup threadGroup = this.group;
            Thread t = new Thread(threadGroup, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != 5) {
                t.setPriority(5);
            }
            return t;
        }
    }

    /* access modifiers changed from: private */
    public static class PrivilegedThreadFactory extends DefaultThreadFactory {
        final AccessControlContext acc = AccessController.getContext();
        final ClassLoader ccl = Thread.currentThread().getContextClassLoader();

        PrivilegedThreadFactory() {
        }

        @Override // java.util.concurrent.Executors.DefaultThreadFactory, java.util.concurrent.ThreadFactory
        public Thread newThread(final Runnable r) {
            return super.newThread(new Runnable() {
                /* class java.util.concurrent.Executors.PrivilegedThreadFactory.AnonymousClass1 */

                @Override // java.lang.Runnable
                public void run() {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        /* class java.util.concurrent.Executors.PrivilegedThreadFactory.AnonymousClass1.AnonymousClass1 */

                        @Override // java.security.PrivilegedAction
                        public Void run() {
                            Thread.currentThread().setContextClassLoader(PrivilegedThreadFactory.this.ccl);
                            r.run();
                            return null;
                        }
                    }, PrivilegedThreadFactory.this.acc);
                }
            });
        }
    }

    private static class DelegatedExecutorService extends AbstractExecutorService {
        private final ExecutorService e;

        DelegatedExecutorService(ExecutorService executor) {
            this.e = executor;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            this.e.execute(command);
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
            this.e.shutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            return this.e.shutdownNow();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            return this.e.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            return this.e.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return this.e.awaitTermination(timeout, unit);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public Future<?> submit(Runnable task) {
            return this.e.submit(task);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Callable<T> task) {
            return this.e.submit(task);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Runnable task, T result) {
            return this.e.submit(task, result);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return this.e.invokeAll(tasks);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            return this.e.invokeAll(tasks, timeout, unit);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            return (T) this.e.invokeAny(tasks);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return (T) this.e.invokeAny(tasks, timeout, unit);
        }
    }

    private static class FinalizableDelegatedExecutorService extends DelegatedExecutorService {
        FinalizableDelegatedExecutorService(ExecutorService executor) {
            super(executor);
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            super.shutdown();
        }
    }

    private static class DelegatedScheduledExecutorService extends DelegatedExecutorService implements ScheduledExecutorService {
        private final ScheduledExecutorService e;

        DelegatedScheduledExecutorService(ScheduledExecutorService executor) {
            super(executor);
            this.e = executor;
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            return this.e.schedule(command, delay, unit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            return this.e.schedule(callable, delay, unit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            return this.e.scheduleAtFixedRate(command, initialDelay, period, unit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
            return this.e.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        }
    }

    private Executors() {
    }
}
