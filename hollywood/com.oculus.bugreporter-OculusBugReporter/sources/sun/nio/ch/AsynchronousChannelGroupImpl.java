package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.Channel;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import sun.security.action.GetIntegerAction;

/* access modifiers changed from: package-private */
public abstract class AsynchronousChannelGroupImpl extends AsynchronousChannelGroup implements Executor {
    private static final int internalThreadCount = ((Integer) AccessController.doPrivileged(new GetIntegerAction("sun.nio.ch.internalThreadPoolSize", 1))).intValue();
    private final ThreadPool pool;
    private final AtomicBoolean shutdown = new AtomicBoolean();
    private final Object shutdownNowLock = new Object();
    private final Queue<Runnable> taskQueue;
    private volatile boolean terminateInitiated;
    private final AtomicInteger threadCount = new AtomicInteger();
    private ScheduledThreadPoolExecutor timeoutExecutor;

    /* access modifiers changed from: package-private */
    public abstract Object attachForeignChannel(Channel channel, FileDescriptor fileDescriptor) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void closeAllChannels() throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void detachForeignChannel(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void executeOnHandlerTask(Runnable runnable);

    /* access modifiers changed from: package-private */
    public abstract boolean isEmpty();

    /* access modifiers changed from: package-private */
    public abstract void shutdownHandlerTasks();

    AsynchronousChannelGroupImpl(AsynchronousChannelProvider provider, ThreadPool pool2) {
        super(provider);
        this.pool = pool2;
        if (pool2.isFixedThreadPool()) {
            this.taskQueue = new ConcurrentLinkedQueue();
        } else {
            this.taskQueue = null;
        }
        this.timeoutExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1, ThreadPool.defaultThreadFactory());
        this.timeoutExecutor.setRemoveOnCancelPolicy(true);
    }

    /* access modifiers changed from: package-private */
    public final ExecutorService executor() {
        return this.pool.executor();
    }

    /* access modifiers changed from: package-private */
    public final boolean isFixedThreadPool() {
        return this.pool.isFixedThreadPool();
    }

    /* access modifiers changed from: package-private */
    public final int fixedThreadCount() {
        if (isFixedThreadPool()) {
            return this.pool.poolSize();
        }
        return this.pool.poolSize() + internalThreadCount;
    }

    private Runnable bindToGroup(final Runnable task) {
        return new Runnable() {
            /* class sun.nio.ch.AsynchronousChannelGroupImpl.AnonymousClass1 */

            @Override // java.lang.Runnable
            public void run() {
                Invoker.bindToGroup(this);
                task.run();
            }
        };
    }

    private void startInternalThread(final Runnable task) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class sun.nio.ch.AsynchronousChannelGroupImpl.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                ThreadPool.defaultThreadFactory().newThread(task).start();
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void startThreads(Runnable task) {
        if (!isFixedThreadPool()) {
            for (int i = 0; i < internalThreadCount; i++) {
                startInternalThread(task);
                this.threadCount.incrementAndGet();
            }
        }
        if (this.pool.poolSize() > 0) {
            Runnable task2 = bindToGroup(task);
            for (int i2 = 0; i2 < this.pool.poolSize(); i2++) {
                try {
                    this.pool.executor().execute(task2);
                    this.threadCount.incrementAndGet();
                } catch (RejectedExecutionException e) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final int threadCount() {
        return this.threadCount.get();
    }

    /* access modifiers changed from: package-private */
    public final int threadExit(Runnable task, boolean replaceMe) {
        if (replaceMe) {
            try {
                if (Invoker.isBoundToAnyGroup()) {
                    this.pool.executor().execute(bindToGroup(task));
                } else {
                    startInternalThread(task);
                }
                return this.threadCount.get();
            } catch (RejectedExecutionException e) {
            }
        }
        return this.threadCount.decrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public final void executeOnPooledThread(Runnable task) {
        if (isFixedThreadPool()) {
            executeOnHandlerTask(task);
        } else {
            this.pool.executor().execute(bindToGroup(task));
        }
    }

    /* access modifiers changed from: package-private */
    public final void offerTask(Runnable task) {
        this.taskQueue.offer(task);
    }

    /* access modifiers changed from: package-private */
    public final Runnable pollTask() {
        Queue<Runnable> queue = this.taskQueue;
        if (queue == null) {
            return null;
        }
        return queue.poll();
    }

    /* access modifiers changed from: package-private */
    public final Future<?> schedule(Runnable task, long timeout, TimeUnit unit) {
        try {
            return this.timeoutExecutor.schedule(task, timeout, unit);
        } catch (RejectedExecutionException rej) {
            if (this.terminateInitiated) {
                return null;
            }
            throw new AssertionError(rej);
        }
    }

    @Override // java.nio.channels.AsynchronousChannelGroup
    public final boolean isShutdown() {
        return this.shutdown.get();
    }

    @Override // java.nio.channels.AsynchronousChannelGroup
    public final boolean isTerminated() {
        return this.pool.executor().isTerminated();
    }

    private void shutdownExecutors() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class sun.nio.ch.AsynchronousChannelGroupImpl.AnonymousClass3 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                AsynchronousChannelGroupImpl.this.pool.executor().shutdown();
                AsynchronousChannelGroupImpl.this.timeoutExecutor.shutdown();
                return null;
            }
        });
    }

    @Override // java.nio.channels.AsynchronousChannelGroup
    public final void shutdown() {
        if (!this.shutdown.getAndSet(true) && isEmpty()) {
            synchronized (this.shutdownNowLock) {
                if (!this.terminateInitiated) {
                    this.terminateInitiated = true;
                    shutdownHandlerTasks();
                    shutdownExecutors();
                }
            }
        }
    }

    @Override // java.nio.channels.AsynchronousChannelGroup
    public final void shutdownNow() throws IOException {
        this.shutdown.set(true);
        synchronized (this.shutdownNowLock) {
            if (!this.terminateInitiated) {
                this.terminateInitiated = true;
                closeAllChannels();
                shutdownHandlerTasks();
                shutdownExecutors();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void detachFromThreadPool() {
        if (this.shutdown.getAndSet(true)) {
            throw new AssertionError((Object) "Already shutdown");
        } else if (isEmpty()) {
            shutdownHandlerTasks();
        } else {
            throw new AssertionError((Object) "Group not empty");
        }
    }

    @Override // java.nio.channels.AsynchronousChannelGroup
    public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.pool.executor().awaitTermination(timeout, unit);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(final Runnable task) {
        if (System.getSecurityManager() != null) {
            final AccessControlContext acc = AccessController.getContext();
            task = new Runnable() {
                /* class sun.nio.ch.AsynchronousChannelGroupImpl.AnonymousClass4 */

                @Override // java.lang.Runnable
                public void run() {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        /* class sun.nio.ch.AsynchronousChannelGroupImpl.AnonymousClass4.AnonymousClass1 */

                        @Override // java.security.PrivilegedAction
                        public Void run() {
                            task.run();
                            return null;
                        }
                    }, acc);
                }
            };
        }
        executeOnPooledThread(task);
    }
}
