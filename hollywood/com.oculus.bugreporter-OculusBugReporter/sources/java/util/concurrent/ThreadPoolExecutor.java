package java.util.concurrent;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutor extends AbstractExecutorService {
    private static final int CAPACITY = 536870911;
    private static final int COUNT_BITS = 29;
    private static final boolean ONLY_ONE = true;
    private static final int RUNNING = -536870912;
    private static final int SHUTDOWN = 0;
    private static final int STOP = 536870912;
    private static final int TERMINATED = 1610612736;
    private static final int TIDYING = 1073741824;
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
    private final BlockingQueue<Runnable> workQueue;
    private final HashSet<Worker> workers;

    private static int runStateOf(int c) {
        return RUNNING & c;
    }

    private static int workerCountOf(int c) {
        return CAPACITY & c;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private static boolean runStateLessThan(int c, int s) {
        return c < s;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }

    private static boolean isRunning(int c) {
        return c < 0;
    }

    private boolean compareAndIncrementWorkerCount(int expect) {
        return this.ctl.compareAndSet(expect, expect + 1);
    }

    private boolean compareAndDecrementWorkerCount(int expect) {
        return this.ctl.compareAndSet(expect, expect - 1);
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

        Worker(Runnable firstTask2) {
            setState(-1);
            this.firstTask = firstTask2;
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
        public boolean tryAcquire(int unused) {
            if (!compareAndSetState(0, 1)) {
                return false;
            }
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public boolean tryRelease(int unused) {
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

        /* access modifiers changed from: package-private */
        public void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = this.thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException e) {
                }
            }
        }
    }

    private void advanceRunState(int targetState) {
        int c;
        do {
            c = this.ctl.get();
            if (runStateAtLeast(c, targetState)) {
                return;
            }
        } while (!this.ctl.compareAndSet(c, ctlOf(targetState, workerCountOf(c))));
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final void tryTerminate() {
        while (true) {
            int c = this.ctl.get();
            if (!isRunning(c) && !runStateAtLeast(c, 1073741824)) {
                if (runStateOf(c) == 0 && !this.workQueue.isEmpty()) {
                    return;
                }
                if (workerCountOf(c) != 0) {
                    interruptIdleWorkers(true);
                    return;
                }
                ReentrantLock mainLock2 = this.mainLock;
                mainLock2.lock();
                try {
                    if (this.ctl.compareAndSet(c, ctlOf(1073741824, 0))) {
                        try {
                            terminated();
                            this.ctl.set(ctlOf(TERMINATED, 0));
                            this.termination.signalAll();
                            mainLock2.unlock();
                            return;
                        } catch (Throwable th) {
                            this.ctl.set(ctlOf(TERMINATED, 0));
                            this.termination.signalAll();
                            throw th;
                        }
                    }
                } finally {
                    mainLock2.unlock();
                }
            } else {
                return;
            }
        }
    }

    private void checkShutdownAccess() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(shutdownPerm);
            ReentrantLock mainLock2 = this.mainLock;
            mainLock2.lock();
            try {
                Iterator<Worker> it = this.workers.iterator();
                while (it.hasNext()) {
                    security.checkAccess(it.next().thread);
                }
            } finally {
                mainLock2.unlock();
            }
        }
    }

    private void interruptWorkers() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            Iterator<Worker> it = this.workers.iterator();
            while (it.hasNext()) {
                it.next().interruptIfStarted();
            }
        } finally {
            mainLock2.unlock();
        }
    }

    private void interruptIdleWorkers(boolean onlyOne) {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            Iterator<Worker> it = this.workers.iterator();
            while (it.hasNext()) {
                Worker w = it.next();
                Thread t = w.thread;
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        t.interrupt();
                        continue;
                    } catch (SecurityException e) {
                        continue;
                    } finally {
                        w.unlock();
                    }
                }
                if (onlyOne) {
                    break;
                }
            }
        } finally {
            mainLock2.unlock();
        }
    }

    private void interruptIdleWorkers() {
        interruptIdleWorkers(false);
    }

    /* access modifiers changed from: package-private */
    public final void reject(Runnable command) {
        this.handler.rejectedExecution(command, this);
    }

    /* access modifiers changed from: package-private */
    public void onShutdown() {
    }

    /* access modifiers changed from: package-private */
    public final boolean isRunningOrShutdown(boolean shutdownOK) {
        int rs = runStateOf(this.ctl.get());
        return rs == RUNNING || (rs == 0 && shutdownOK);
    }

    private List<Runnable> drainQueue() {
        BlockingQueue<Runnable> q = this.workQueue;
        ArrayList<Runnable> taskList = new ArrayList<>();
        q.drainTo(taskList);
        if (!q.isEmpty()) {
            Runnable[] runnableArr = (Runnable[]) q.toArray(new Runnable[0]);
            for (Runnable r : runnableArr) {
                if (q.remove(r)) {
                    taskList.add(r);
                }
            }
        }
        return taskList;
    }

    /* JADX INFO: Multiple debug info for r0v3 boolean: [D('c' int), D('workerStarted' boolean)] */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a2, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean addWorker(java.lang.Runnable r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 163
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ThreadPoolExecutor.addWorker(java.lang.Runnable, boolean):boolean");
    }

    private void addWorkerFailed(Worker w) {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        if (w != null) {
            try {
                this.workers.remove(w);
            } catch (Throwable th) {
                mainLock2.unlock();
                throw th;
            }
        }
        decrementWorkerCount();
        tryTerminate();
        mainLock2.unlock();
    }

    /* JADX INFO: finally extract failed */
    private void processWorkerExit(Worker w, boolean completedAbruptly) {
        if (completedAbruptly) {
            decrementWorkerCount();
        }
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            this.completedTaskCount += w.completedTasks;
            this.workers.remove(w);
            mainLock2.unlock();
            tryTerminate();
            int c = this.ctl.get();
            if (runStateLessThan(c, 536870912)) {
                if (!completedAbruptly) {
                    int min = this.allowCoreThreadTimeOut ? 0 : this.corePoolSize;
                    if (min == 0 && !this.workQueue.isEmpty()) {
                        min = 1;
                    }
                    if (workerCountOf(c) >= min) {
                        return;
                    }
                }
                addWorker(null, false);
            }
        } catch (Throwable th) {
            mainLock2.unlock();
            throw th;
        }
    }

    private Runnable getTask() {
        Runnable r;
        boolean timedOut = false;
        while (true) {
            int c = this.ctl.get();
            int rs = runStateOf(c);
            if (rs < 0 || (rs < 536870912 && !this.workQueue.isEmpty())) {
                int wc = workerCountOf(c);
                boolean timed = this.allowCoreThreadTimeOut || wc > this.corePoolSize;
                if ((wc <= this.maximumPoolSize && (!timed || !timedOut)) || (wc <= 1 && !this.workQueue.isEmpty())) {
                    if (timed) {
                        try {
                            r = this.workQueue.poll(this.keepAliveTime, TimeUnit.NANOSECONDS);
                        } catch (InterruptedException e) {
                            timedOut = false;
                        }
                    } else {
                        r = this.workQueue.take();
                    }
                    if (r != null) {
                        return r;
                    }
                    timedOut = true;
                } else if (compareAndDecrementWorkerCount(c)) {
                    return null;
                }
            }
        }
        decrementWorkerCount();
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock();
        boolean completedAbruptly = true;
        while (true) {
            if (task == null) {
                try {
                    Runnable task2 = getTask();
                    task = task2;
                    if (task2 == null) {
                        completedAbruptly = false;
                        return;
                    }
                } finally {
                    processWorkerExit(w, completedAbruptly);
                }
            }
            w.lock();
            if ((runStateAtLeast(this.ctl.get(), 536870912) || (Thread.interrupted() && runStateAtLeast(this.ctl.get(), 536870912))) && !wt.isInterrupted()) {
                wt.interrupt();
            }
            try {
                beforeExecute(wt, task);
                Throwable thrown = null;
                try {
                    task.run();
                    afterExecute(task, null);
                } catch (RuntimeException x) {
                    throw x;
                } catch (Error x2) {
                    throw x2;
                } catch (Throwable x3) {
                    afterExecute(task, thrown);
                    throw x3;
                }
            } finally {
                w.completedTasks++;
                w.unlock();
            }
        }
    }

    public ThreadPoolExecutor(int corePoolSize2, int maximumPoolSize2, long keepAliveTime2, TimeUnit unit, BlockingQueue<Runnable> workQueue2) {
        this(corePoolSize2, maximumPoolSize2, keepAliveTime2, unit, workQueue2, Executors.defaultThreadFactory(), defaultHandler);
    }

    public ThreadPoolExecutor(int corePoolSize2, int maximumPoolSize2, long keepAliveTime2, TimeUnit unit, BlockingQueue<Runnable> workQueue2, ThreadFactory threadFactory2) {
        this(corePoolSize2, maximumPoolSize2, keepAliveTime2, unit, workQueue2, threadFactory2, defaultHandler);
    }

    public ThreadPoolExecutor(int corePoolSize2, int maximumPoolSize2, long keepAliveTime2, TimeUnit unit, BlockingQueue<Runnable> workQueue2, RejectedExecutionHandler handler2) {
        this(corePoolSize2, maximumPoolSize2, keepAliveTime2, unit, workQueue2, Executors.defaultThreadFactory(), handler2);
    }

    public ThreadPoolExecutor(int corePoolSize2, int maximumPoolSize2, long keepAliveTime2, TimeUnit unit, BlockingQueue<Runnable> workQueue2, ThreadFactory threadFactory2, RejectedExecutionHandler handler2) {
        this.ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        this.mainLock = new ReentrantLock();
        this.workers = new HashSet<>();
        this.termination = this.mainLock.newCondition();
        if (corePoolSize2 < 0 || maximumPoolSize2 <= 0 || maximumPoolSize2 < corePoolSize2 || keepAliveTime2 < 0) {
            throw new IllegalArgumentException();
        } else if (workQueue2 == null || threadFactory2 == null || handler2 == null) {
            throw new NullPointerException();
        } else {
            this.corePoolSize = corePoolSize2;
            this.maximumPoolSize = maximumPoolSize2;
            this.workQueue = workQueue2;
            this.keepAliveTime = unit.toNanos(keepAliveTime2);
            this.threadFactory = threadFactory2;
            this.handler = handler2;
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        if (command != null) {
            int c = this.ctl.get();
            if (workerCountOf(c) < this.corePoolSize) {
                if (!addWorker(command, true)) {
                    c = this.ctl.get();
                } else {
                    return;
                }
            }
            if (isRunning(c) && this.workQueue.offer(command)) {
                int recheck = this.ctl.get();
                if (!isRunning(recheck) && remove(command)) {
                    reject(command);
                } else if (workerCountOf(recheck) == 0) {
                    addWorker(null, false);
                }
            } else if (!addWorker(command, false)) {
                reject(command);
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            checkShutdownAccess();
            advanceRunState(0);
            interruptIdleWorkers();
            onShutdown();
            mainLock2.unlock();
            tryTerminate();
        } catch (Throwable th) {
            mainLock2.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            checkShutdownAccess();
            advanceRunState(536870912);
            interruptWorkers();
            List<Runnable> tasks = drainQueue();
            mainLock2.unlock();
            tryTerminate();
            return tasks;
        } catch (Throwable th) {
            mainLock2.unlock();
            throw th;
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return !isRunning(this.ctl.get());
    }

    public boolean isTerminating() {
        int c = this.ctl.get();
        return !isRunning(c) && runStateLessThan(c, TERMINATED);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return runStateAtLeast(this.ctl.get(), TERMINATED);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        while (!runStateAtLeast(this.ctl.get(), TERMINATED)) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.termination.awaitNanos(nanos);
            } finally {
                mainLock2.unlock();
            }
        }
        mainLock2.unlock();
        return true;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        shutdown();
    }

    public void setThreadFactory(ThreadFactory threadFactory2) {
        if (threadFactory2 != null) {
            this.threadFactory = threadFactory2;
            return;
        }
        throw new NullPointerException();
    }

    public ThreadFactory getThreadFactory() {
        return this.threadFactory;
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler handler2) {
        if (handler2 != null) {
            this.handler = handler2;
            return;
        }
        throw new NullPointerException();
    }

    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return this.handler;
    }

    public void setCorePoolSize(int corePoolSize2) {
        if (corePoolSize2 >= 0) {
            int delta = corePoolSize2 - this.corePoolSize;
            this.corePoolSize = corePoolSize2;
            if (workerCountOf(this.ctl.get()) > corePoolSize2) {
                interruptIdleWorkers();
            } else if (delta > 0) {
                int k = Math.min(delta, this.workQueue.size());
                while (true) {
                    int k2 = k - 1;
                    if (k > 0 && addWorker(null, true) && !this.workQueue.isEmpty()) {
                        k = k2;
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public boolean prestartCoreThread() {
        if (workerCountOf(this.ctl.get()) >= this.corePoolSize || !addWorker(null, true)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void ensurePrestart() {
        int wc = workerCountOf(this.ctl.get());
        if (wc < this.corePoolSize) {
            addWorker(null, true);
        } else if (wc == 0) {
            addWorker(null, false);
        }
    }

    public int prestartAllCoreThreads() {
        int n = 0;
        while (addWorker(null, true)) {
            n++;
        }
        return n;
    }

    public boolean allowsCoreThreadTimeOut() {
        return this.allowCoreThreadTimeOut;
    }

    public void allowCoreThreadTimeOut(boolean value) {
        if (value && this.keepAliveTime <= 0) {
            throw new IllegalArgumentException("Core threads must have nonzero keep alive times");
        } else if (value != this.allowCoreThreadTimeOut) {
            this.allowCoreThreadTimeOut = value;
            if (value) {
                interruptIdleWorkers();
            }
        }
    }

    public void setMaximumPoolSize(int maximumPoolSize2) {
        if (maximumPoolSize2 <= 0 || maximumPoolSize2 < this.corePoolSize) {
            throw new IllegalArgumentException();
        }
        this.maximumPoolSize = maximumPoolSize2;
        if (workerCountOf(this.ctl.get()) > maximumPoolSize2) {
            interruptIdleWorkers();
        }
    }

    public int getMaximumPoolSize() {
        return this.maximumPoolSize;
    }

    public void setKeepAliveTime(long time, TimeUnit unit) {
        if (time < 0) {
            throw new IllegalArgumentException();
        } else if (time != 0 || !allowsCoreThreadTimeOut()) {
            long keepAliveTime2 = unit.toNanos(time);
            this.keepAliveTime = keepAliveTime2;
            if (keepAliveTime2 - this.keepAliveTime < 0) {
                interruptIdleWorkers();
            }
        } else {
            throw new IllegalArgumentException("Core threads must have nonzero keep alive times");
        }
    }

    public long getKeepAliveTime(TimeUnit unit) {
        return unit.convert(this.keepAliveTime, TimeUnit.NANOSECONDS);
    }

    public BlockingQueue<Runnable> getQueue() {
        return this.workQueue;
    }

    public boolean remove(Runnable task) {
        boolean removed = this.workQueue.remove(task);
        tryTerminate();
        return removed;
    }

    public void purge() {
        BlockingQueue<Runnable> q = this.workQueue;
        try {
            Iterator<Runnable> it = q.iterator();
            while (it.hasNext()) {
                Runnable r = it.next();
                if ((r instanceof Future) && ((Future) r).isCancelled()) {
                    it.remove();
                }
            }
        } catch (ConcurrentModificationException e) {
            Object[] array = q.toArray();
            for (Object r2 : array) {
                if ((r2 instanceof Future) && ((Future) r2).isCancelled()) {
                    q.remove(r2);
                }
            }
        }
        tryTerminate();
    }

    public int getPoolSize() {
        int i;
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            if (runStateAtLeast(this.ctl.get(), 1073741824)) {
                i = 0;
            } else {
                i = this.workers.size();
            }
            return i;
        } finally {
            mainLock2.unlock();
        }
    }

    public int getActiveCount() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        int n = 0;
        try {
            Iterator<Worker> it = this.workers.iterator();
            while (it.hasNext()) {
                if (it.next().isLocked()) {
                    n++;
                }
            }
            return n;
        } finally {
            mainLock2.unlock();
        }
    }

    public int getLargestPoolSize() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            return this.largestPoolSize;
        } finally {
            mainLock2.unlock();
        }
    }

    public long getTaskCount() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            long n = this.completedTaskCount;
            Iterator<Worker> it = this.workers.iterator();
            while (it.hasNext()) {
                Worker w = it.next();
                n += w.completedTasks;
                if (w.isLocked()) {
                    n++;
                }
            }
            return ((long) this.workQueue.size()) + n;
        } finally {
            mainLock2.unlock();
        }
    }

    public long getCompletedTaskCount() {
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            long n = this.completedTaskCount;
            Iterator<Worker> it = this.workers.iterator();
            while (it.hasNext()) {
                n += it.next().completedTasks;
            }
            return n;
        } finally {
            mainLock2.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public String toString() {
        String runState;
        ReentrantLock mainLock2 = this.mainLock;
        mainLock2.lock();
        try {
            long ncompleted = this.completedTaskCount;
            int nactive = 0;
            int nworkers = this.workers.size();
            Iterator<Worker> it = this.workers.iterator();
            while (it.hasNext()) {
                Worker w = it.next();
                ncompleted += w.completedTasks;
                if (w.isLocked()) {
                    nactive++;
                }
            }
            mainLock2.unlock();
            int c = this.ctl.get();
            if (runStateLessThan(c, 0)) {
                runState = "Running";
            } else if (runStateAtLeast(c, TERMINATED)) {
                runState = "Terminated";
            } else {
                runState = "Shutting down";
            }
            return super.toString() + "[" + runState + ", pool size = " + nworkers + ", active threads = " + nactive + ", queued tasks = " + this.workQueue.size() + ", completed tasks = " + ncompleted + "]";
        } catch (Throwable th) {
            mainLock2.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void beforeExecute(Thread t, Runnable r) {
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable r, Throwable t) {
    }

    /* access modifiers changed from: protected */
    public void terminated() {
    }

    public static class CallerRunsPolicy implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }

    public static class AbortPolicy implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + e.toString());
        }
    }

    public static class DiscardPolicy implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        }
    }

    public static class DiscardOldestPolicy implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                e.getQueue().poll();
                e.execute(r);
            }
        }
    }
}
