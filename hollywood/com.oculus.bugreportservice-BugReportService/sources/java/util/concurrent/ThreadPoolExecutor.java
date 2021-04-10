package java.util.concurrent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutor extends AbstractExecutorService {
    private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();
    private static final RuntimePermission shutdownPerm = new RuntimePermission("modifyThread");
    private volatile boolean allowCoreThreadTimeOut;
    private long completedTaskCount;
    private volatile int corePoolSize;
    private final AtomicInteger ctl;
    private volatile RejectedExecutionHandler handler;
    private volatile long keepAliveTime;
    private int largestPoolSize;
    private final ReentrantLock mainLock;
    private volatile int maximumPoolSize;
    private final Condition termination;
    private volatile ThreadFactory threadFactory;
    private final BlockingQueue workQueue;
    private final HashSet workers;

    private static int ctlOf(int i, int i2) {
        return i | i2;
    }

    private static boolean isRunning(int i) {
        return i < 0;
    }

    private static boolean runStateAtLeast(int i, int i2) {
        return i >= i2;
    }

    private static boolean runStateLessThan(int i, int i2) {
        return i < i2;
    }

    private static int runStateOf(int i) {
        return i & -536870912;
    }

    private static int workerCountOf(int i) {
        return i & 536870911;
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
    }

    /* access modifiers changed from: protected */
    public void beforeExecute(Thread thread, Runnable runnable) {
    }

    /* access modifiers changed from: protected */
    public void terminated() {
    }

    private boolean compareAndIncrementWorkerCount(int i) {
        return this.ctl.compareAndSet(i, i + 1);
    }

    private boolean compareAndDecrementWorkerCount(int i) {
        return this.ctl.compareAndSet(i, i - 1);
    }

    private void decrementWorkerCount() {
        do {
        } while (!compareAndDecrementWorkerCount(this.ctl.get()));
    }

    /* access modifiers changed from: private */
    public final class Worker extends AbstractQueuedSynchronizer implements Runnable {
        private static final long serialVersionUID = 6138294804551838833L;
        volatile long completedTasks;
        Runnable firstTask;
        final Thread thread;

        Worker(Runnable runnable) {
            setState(-1);
            this.firstTask = runnable;
            this.thread = ThreadPoolExecutor.this.getThreadFactory().newThread(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadPoolExecutor.this.runWorker(this);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public boolean isHeldExclusively() {
            return getState() != 0;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public boolean tryAcquire(int i) {
            if (!compareAndSetState(0, 1)) {
                return false;
            }
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public boolean tryRelease(int i) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock() {
            acquire(1);
        }

        public boolean tryLock() {
            return tryAcquire(1);
        }

        public void unlock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final void tryTerminate() {
        while (true) {
            int i = this.ctl.get();
            if (!isRunning(i) && !runStateAtLeast(i, 1073741824)) {
                if (runStateOf(i) == 0 && !this.workQueue.isEmpty()) {
                    return;
                }
                if (workerCountOf(i) != 0) {
                    interruptIdleWorkers(true);
                    return;
                }
                ReentrantLock reentrantLock = this.mainLock;
                reentrantLock.lock();
                try {
                    if (this.ctl.compareAndSet(i, ctlOf(1073741824, 0))) {
                        try {
                            terminated();
                            this.ctl.set(ctlOf(1610612736, 0));
                            this.termination.signalAll();
                            reentrantLock.unlock();
                            return;
                        } catch (Throwable th) {
                            this.ctl.set(ctlOf(1610612736, 0));
                            this.termination.signalAll();
                            throw th;
                        }
                    }
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                return;
            }
        }
    }

    private void interruptIdleWorkers(boolean z) {
        ReentrantLock reentrantLock = this.mainLock;
        reentrantLock.lock();
        try {
            Iterator it = this.workers.iterator();
            while (it.hasNext()) {
                Worker worker = (Worker) it.next();
                Thread thread = worker.thread;
                if (!thread.isInterrupted() && worker.tryLock()) {
                    try {
                        thread.interrupt();
                    } catch (SecurityException unused) {
                    } catch (Throwable th) {
                        worker.unlock();
                        throw th;
                    }
                    worker.unlock();
                    continue;
                }
                if (z) {
                    break;
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void reject(Runnable runnable) {
        this.handler.rejectedExecution(runnable, this);
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a0, code lost:
        return false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean addWorker(java.lang.Runnable r6, boolean r7) {
        /*
        // Method dump skipped, instructions count: 161
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ThreadPoolExecutor.addWorker(java.lang.Runnable, boolean):boolean");
    }

    private void addWorkerFailed(Worker worker) {
        ReentrantLock reentrantLock = this.mainLock;
        reentrantLock.lock();
        if (worker != null) {
            try {
                this.workers.remove(worker);
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        decrementWorkerCount();
        tryTerminate();
        reentrantLock.unlock();
    }

    /* JADX INFO: finally extract failed */
    private void processWorkerExit(Worker worker, boolean z) {
        if (z) {
            decrementWorkerCount();
        }
        ReentrantLock reentrantLock = this.mainLock;
        reentrantLock.lock();
        try {
            this.completedTaskCount += worker.completedTasks;
            this.workers.remove(worker);
            reentrantLock.unlock();
            tryTerminate();
            int i = this.ctl.get();
            if (runStateLessThan(i, 536870912)) {
                if (!z) {
                    int i2 = this.allowCoreThreadTimeOut ? 0 : this.corePoolSize;
                    if (i2 == 0 && !this.workQueue.isEmpty()) {
                        i2 = 1;
                    }
                    if (workerCountOf(i) >= i2) {
                        return;
                    }
                }
                addWorker(null, false);
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    private Runnable getTask() {
        Runnable runnable;
        loop0:
        while (true) {
            boolean z = false;
            while (true) {
                int i = this.ctl.get();
                int runStateOf = runStateOf(i);
                if (runStateOf < 0 || (runStateOf < 536870912 && !this.workQueue.isEmpty())) {
                    int workerCountOf = workerCountOf(i);
                    boolean z2 = this.allowCoreThreadTimeOut || workerCountOf > this.corePoolSize;
                    if ((workerCountOf <= this.maximumPoolSize && (!z2 || !z)) || (workerCountOf <= 1 && !this.workQueue.isEmpty())) {
                        if (z2) {
                            try {
                                runnable = (Runnable) this.workQueue.poll(this.keepAliveTime, TimeUnit.NANOSECONDS);
                            } catch (InterruptedException unused) {
                            }
                        } else {
                            runnable = (Runnable) this.workQueue.take();
                        }
                        if (runnable != null) {
                            return runnable;
                        }
                        z = true;
                    } else if (compareAndDecrementWorkerCount(i)) {
                        return null;
                    }
                }
            }
        }
        decrementWorkerCount();
        return null;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:19:0x004b */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.concurrent.ThreadPoolExecutor] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void runWorker(java.util.concurrent.ThreadPoolExecutor.Worker r8) {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ThreadPoolExecutor.runWorker(java.util.concurrent.ThreadPoolExecutor$Worker):void");
    }

    public ThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue blockingQueue, ThreadFactory threadFactory2) {
        this(i, i2, j, timeUnit, blockingQueue, threadFactory2, defaultHandler);
    }

    public ThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue blockingQueue, ThreadFactory threadFactory2, RejectedExecutionHandler rejectedExecutionHandler) {
        this.ctl = new AtomicInteger(ctlOf(-536870912, 0));
        this.mainLock = new ReentrantLock();
        this.workers = new HashSet();
        this.termination = this.mainLock.newCondition();
        if (i < 0 || i2 <= 0 || i2 < i || j < 0) {
            throw new IllegalArgumentException();
        } else if (blockingQueue == null || threadFactory2 == null || rejectedExecutionHandler == null) {
            throw new NullPointerException();
        } else {
            this.corePoolSize = i;
            this.maximumPoolSize = i2;
            this.workQueue = blockingQueue;
            this.keepAliveTime = timeUnit.toNanos(j);
            this.threadFactory = threadFactory2;
            this.handler = rejectedExecutionHandler;
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (runnable != null) {
            int i = this.ctl.get();
            if (workerCountOf(i) < this.corePoolSize) {
                if (!addWorker(runnable, true)) {
                    i = this.ctl.get();
                } else {
                    return;
                }
            }
            if (isRunning(i) && this.workQueue.offer(runnable)) {
                int i2 = this.ctl.get();
                if (!isRunning(i2) && remove(runnable)) {
                    reject(runnable);
                    throw null;
                } else if (workerCountOf(i2) == 0) {
                    addWorker(null, false);
                }
            } else if (!addWorker(runnable, false)) {
                reject(runnable);
                throw null;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public ThreadFactory getThreadFactory() {
        return this.threadFactory;
    }

    public boolean remove(Runnable runnable) {
        boolean remove = this.workQueue.remove(runnable);
        tryTerminate();
        return remove;
    }

    /* JADX INFO: finally extract failed */
    public String toString() {
        ReentrantLock reentrantLock = this.mainLock;
        reentrantLock.lock();
        try {
            this.workers.size();
            Iterator it = this.workers.iterator();
            while (it.hasNext()) {
                Worker worker = (Worker) it.next();
                long j = worker.completedTasks;
                worker.isLocked();
            }
            reentrantLock.unlock();
            int i = this.ctl.get();
            if (!runStateLessThan(i, 0)) {
                runStateAtLeast(i, 1610612736);
            }
            new StringBuilder();
            super.toString();
            throw null;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public static class AbortPolicy implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            StringBuilder sb = new StringBuilder();
            sb.append("Task ");
            sb.append(runnable.toString());
            sb.append(" rejected from ");
            threadPoolExecutor.toString();
            throw null;
        }
    }
}
