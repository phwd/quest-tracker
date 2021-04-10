package java.util.concurrent;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public abstract class ForkJoinTask<V> implements Future<V>, Serializable {
    static final int CANCELLED = -1073741824;
    static final int DONE_MASK = -268435456;
    static final int EXCEPTIONAL = Integer.MIN_VALUE;
    private static final int EXCEPTION_MAP_CAPACITY = 32;
    static final int NORMAL = -268435456;
    static final int SIGNAL = 65536;
    static final int SMASK = 65535;
    private static final long STATUS;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final ExceptionNode[] exceptionTable = new ExceptionNode[32];
    private static final ReentrantLock exceptionTableLock = new ReentrantLock();
    private static final ReferenceQueue<Object> exceptionTableRefQueue = new ReferenceQueue<>();
    private static final long serialVersionUID = -7721805057305804111L;
    volatile int status;

    /* access modifiers changed from: protected */
    public abstract boolean exec();

    public abstract V getRawResult();

    /* access modifiers changed from: protected */
    public abstract void setRawResult(V v);

    private int setCompletion(int completion) {
        int s;
        do {
            s = this.status;
            if (s < 0) {
                return s;
            }
        } while (!U.compareAndSwapInt(this, STATUS, s, s | completion));
        if ((s >>> 16) != 0) {
            synchronized (this) {
                notifyAll();
            }
        }
        return completion;
    }

    /* access modifiers changed from: package-private */
    public final int doExec() {
        int s = this.status;
        if (s < 0) {
            return s;
        }
        try {
            if (exec()) {
                return setCompletion(-268435456);
            }
            return s;
        } catch (Throwable rex) {
            return setExceptionalCompletion(rex);
        }
    }

    /* access modifiers changed from: package-private */
    public final void internalWait(long timeout) {
        int s = this.status;
        if (s >= 0 && U.compareAndSwapInt(this, STATUS, s, s | 65536)) {
            synchronized (this) {
                if (this.status >= 0) {
                    try {
                        wait(timeout);
                    } catch (InterruptedException e) {
                    }
                } else {
                    notifyAll();
                }
            }
        }
    }

    private int externalAwaitDone() {
        int i;
        int i2 = 0;
        if (this instanceof CountedCompleter) {
            i2 = ForkJoinPool.common.externalHelpComplete((CountedCompleter) this, 0);
        } else if (ForkJoinPool.common.tryExternalUnpush(this)) {
            i2 = doExec();
        }
        int s = i2;
        if (s >= 0) {
            int i3 = this.status;
            s = i3;
            if (i3 >= 0) {
                boolean interrupted = false;
                do {
                    if (U.compareAndSwapInt(this, STATUS, s, s | 65536)) {
                        synchronized (this) {
                            if (this.status >= 0) {
                                try {
                                    wait(0);
                                } catch (InterruptedException e) {
                                    interrupted = true;
                                }
                            } else {
                                notifyAll();
                            }
                        }
                    }
                    i = this.status;
                    s = i;
                } while (i >= 0);
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return s;
    }

    private int externalInterruptibleAwaitDone() throws InterruptedException {
        if (!Thread.interrupted()) {
            int i = this.status;
            int s = i;
            if (i >= 0) {
                int i2 = 0;
                if (this instanceof CountedCompleter) {
                    i2 = ForkJoinPool.common.externalHelpComplete((CountedCompleter) this, 0);
                } else if (ForkJoinPool.common.tryExternalUnpush(this)) {
                    i2 = doExec();
                }
                s = i2;
                if (i2 >= 0) {
                    while (true) {
                        int i3 = this.status;
                        s = i3;
                        if (i3 < 0) {
                            break;
                        } else if (U.compareAndSwapInt(this, STATUS, s, s | 65536)) {
                            synchronized (this) {
                                if (this.status >= 0) {
                                    wait(0);
                                } else {
                                    notifyAll();
                                }
                            }
                        }
                    }
                }
            }
            return s;
        }
        throw new InterruptedException();
    }

    private int doJoin() {
        int s;
        int s2 = this.status;
        if (s2 < 0) {
            return s2;
        }
        Thread t = Thread.currentThread();
        if (!(t instanceof ForkJoinWorkerThread)) {
            return externalAwaitDone();
        }
        ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
        ForkJoinPool.WorkQueue w = wt.workQueue;
        if (!w.tryUnpush(this) || (s = doExec()) >= 0) {
            return wt.pool.awaitJoin(w, this, 0);
        }
        return s;
    }

    private int doInvoke() {
        int s = doExec();
        if (s < 0) {
            return s;
        }
        Thread t = Thread.currentThread();
        if (!(t instanceof ForkJoinWorkerThread)) {
            return externalAwaitDone();
        }
        ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
        return wt.pool.awaitJoin(wt.workQueue, this, 0);
    }

    /* access modifiers changed from: package-private */
    public static final class ExceptionNode extends WeakReference<ForkJoinTask<?>> {
        final Throwable ex;
        final int hashCode;
        ExceptionNode next;
        final long thrower = Thread.currentThread().getId();

        ExceptionNode(ForkJoinTask<?> task, Throwable ex2, ExceptionNode next2, ReferenceQueue<Object> exceptionTableRefQueue) {
            super(task, exceptionTableRefQueue);
            this.ex = ex2;
            this.next = next2;
            this.hashCode = System.identityHashCode(task);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final int recordExceptionalCompletion(Throwable ex) {
        int s = this.status;
        if (s < 0) {
            return s;
        }
        int h = System.identityHashCode(this);
        ReentrantLock lock = exceptionTableLock;
        lock.lock();
        try {
            expungeStaleExceptions();
            ExceptionNode[] t = exceptionTable;
            int i = (t.length - 1) & h;
            ExceptionNode e = t[i];
            while (true) {
                if (e == null) {
                    t[i] = new ExceptionNode(this, ex, t[i], exceptionTableRefQueue);
                    break;
                } else if (e.get() == this) {
                    break;
                } else {
                    e = e.next;
                }
            }
            lock.unlock();
            return setCompletion(Integer.MIN_VALUE);
        } catch (Throwable th) {
            lock.unlock();
            throw th;
        }
    }

    private int setExceptionalCompletion(Throwable ex) {
        int s = recordExceptionalCompletion(ex);
        if ((-268435456 & s) == Integer.MIN_VALUE) {
            internalPropagateException(ex);
        }
        return s;
    }

    /* access modifiers changed from: package-private */
    public void internalPropagateException(Throwable ex) {
    }

    static final void cancelIgnoringExceptions(ForkJoinTask<?> t) {
        if (t != null && t.status >= 0) {
            try {
                t.cancel(false);
            } catch (Throwable th) {
            }
        }
    }

    private void clearExceptionalCompletion() {
        int h = System.identityHashCode(this);
        ReentrantLock lock = exceptionTableLock;
        lock.lock();
        try {
            ExceptionNode[] t = exceptionTable;
            int i = (t.length - 1) & h;
            ExceptionNode e = t[i];
            ExceptionNode pred = null;
            while (true) {
                if (e == null) {
                    break;
                }
                ExceptionNode next = e.next;
                if (e.get() != this) {
                    pred = e;
                    e = next;
                } else if (pred == null) {
                    t[i] = next;
                } else {
                    pred.next = next;
                }
            }
            expungeStaleExceptions();
            this.status = 0;
        } finally {
            lock.unlock();
        }
    }

    private Throwable getThrowableException() {
        Throwable ex;
        int h = System.identityHashCode(this);
        ReentrantLock lock = exceptionTableLock;
        lock.lock();
        try {
            expungeStaleExceptions();
            ExceptionNode[] t = exceptionTable;
            ExceptionNode e = t[(t.length - 1) & h];
            while (e != null && e.get() != this) {
                e = e.next;
            }
            if (e == null || (ex = e.ex) == null) {
                return null;
            }
            if (e.thrower != Thread.currentThread().getId()) {
                try {
                    Constructor<?>[] constructors = ex.getClass().getConstructors();
                    Constructor<?> noArgCtor = null;
                    for (Constructor<?> c : constructors) {
                        Class<?>[] ps = c.getParameterTypes();
                        if (ps.length == 0) {
                            noArgCtor = c;
                        } else if (ps.length == 1 && ps[0] == Throwable.class) {
                            return (Throwable) c.newInstance(ex);
                        }
                    }
                    if (noArgCtor != null) {
                        Throwable wx = (Throwable) noArgCtor.newInstance(new Object[0]);
                        wx.initCause(ex);
                        return wx;
                    }
                } catch (Exception e2) {
                }
            }
            return ex;
        } finally {
            lock.unlock();
        }
    }

    private static void expungeStaleExceptions() {
        while (true) {
            Object x = exceptionTableRefQueue.poll();
            if (x == null) {
                return;
            }
            if (x instanceof ExceptionNode) {
                int hashCode = ((ExceptionNode) x).hashCode;
                ExceptionNode[] t = exceptionTable;
                int i = (t.length - 1) & hashCode;
                ExceptionNode e = t[i];
                ExceptionNode pred = null;
                while (true) {
                    if (e == null) {
                        break;
                    }
                    ExceptionNode next = e.next;
                    if (e != x) {
                        pred = e;
                        e = next;
                    } else if (pred == null) {
                        t[i] = next;
                    } else {
                        pred.next = next;
                    }
                }
            }
        }
    }

    static final void helpExpungeStaleExceptions() {
        ReentrantLock lock = exceptionTableLock;
        if (lock.tryLock()) {
            try {
                expungeStaleExceptions();
            } finally {
                lock.unlock();
            }
        }
    }

    static void rethrow(Throwable ex) {
        uncheckedThrow(ex);
    }

    static <T extends Throwable> void uncheckedThrow(Throwable t) throws Throwable {
        if (t != null) {
            throw t;
        }
        throw new Error("Unknown Exception");
    }

    private void reportException(int s) {
        if (s == CANCELLED) {
            throw new CancellationException();
        } else if (s == Integer.MIN_VALUE) {
            rethrow(getThrowableException());
        }
    }

    public final ForkJoinTask<V> fork() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            ((ForkJoinWorkerThread) t).workQueue.push(this);
        } else {
            ForkJoinPool.common.externalPush(this);
        }
        return this;
    }

    public final V join() {
        int s = doJoin() & -268435456;
        if (s != -268435456) {
            reportException(s);
        }
        return getRawResult();
    }

    public final V invoke() {
        int s = doInvoke() & -268435456;
        if (s != -268435456) {
            reportException(s);
        }
        return getRawResult();
    }

    public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2) {
        t2.fork();
        int s1 = t1.doInvoke() & -268435456;
        if (s1 != -268435456) {
            t1.reportException(s1);
        }
        int s2 = t2.doJoin() & -268435456;
        if (s2 != -268435456) {
            t2.reportException(s2);
        }
    }

    public static void invokeAll(ForkJoinTask<?>... tasks) {
        Throwable ex = null;
        int last = tasks.length - 1;
        for (int i = last; i >= 0; i--) {
            ForkJoinTask<?> t = tasks[i];
            if (t == null) {
                if (ex == null) {
                    ex = new NullPointerException();
                }
            } else if (i != 0) {
                t.fork();
            } else if (t.doInvoke() < -268435456 && ex == null) {
                ex = t.getException();
            }
        }
        for (int i2 = 1; i2 <= last; i2++) {
            ForkJoinTask<?> t2 = tasks[i2];
            if (t2 != null) {
                if (ex != null) {
                    t2.cancel(false);
                } else if (t2.doJoin() < -268435456) {
                    ex = t2.getException();
                }
            }
        }
        if (ex != null) {
            rethrow(ex);
        }
    }

    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks) {
        if (!(tasks instanceof RandomAccess) || !(tasks instanceof List)) {
            invokeAll((ForkJoinTask[]) tasks.toArray(new ForkJoinTask[tasks.size()]));
            return tasks;
        }
        List<? extends ForkJoinTask<?>> ts = (List) tasks;
        Throwable ex = null;
        int last = ts.size() - 1;
        for (int i = last; i >= 0; i--) {
            ForkJoinTask<?> t = (ForkJoinTask) ts.get(i);
            if (t == null) {
                if (ex == null) {
                    ex = new NullPointerException();
                }
            } else if (i != 0) {
                t.fork();
            } else if (t.doInvoke() < -268435456 && ex == null) {
                ex = t.getException();
            }
        }
        for (int i2 = 1; i2 <= last; i2++) {
            ForkJoinTask<?> t2 = (ForkJoinTask) ts.get(i2);
            if (t2 != null) {
                if (ex != null) {
                    t2.cancel(false);
                } else if (t2.doJoin() < -268435456) {
                    ex = t2.getException();
                }
            }
        }
        if (ex != null) {
            rethrow(ex);
        }
        return tasks;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        return (setCompletion(CANCELLED) & -268435456) == CANCELLED;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.status < 0;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return (this.status & -268435456) == CANCELLED;
    }

    public final boolean isCompletedAbnormally() {
        return this.status < -268435456;
    }

    public final boolean isCompletedNormally() {
        return (this.status & -268435456) == -268435456;
    }

    public final Throwable getException() {
        int s = this.status & -268435456;
        if (s >= -268435456) {
            return null;
        }
        if (s == CANCELLED) {
            return new CancellationException();
        }
        return getThrowableException();
    }

    public void completeExceptionally(Throwable ex) {
        Throwable th;
        if ((ex instanceof RuntimeException) || (ex instanceof Error)) {
            th = ex;
        } else {
            th = new RuntimeException(ex);
        }
        setExceptionalCompletion(th);
    }

    public void complete(V value) {
        try {
            setRawResult(value);
            setCompletion(-268435456);
        } catch (Throwable rex) {
            setExceptionalCompletion(rex);
        }
    }

    public final void quietlyComplete() {
        setCompletion(-268435456);
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        int s = -268435456 & (Thread.currentThread() instanceof ForkJoinWorkerThread ? doJoin() : externalInterruptibleAwaitDone());
        if (s == CANCELLED) {
            throw new CancellationException();
        } else if (s != Integer.MIN_VALUE) {
            return getRawResult();
        } else {
            throw new ExecutionException(getThrowableException());
        }
    }

    @Override // java.util.concurrent.Future
    public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        int s;
        Thread t;
        long deadline;
        long nanos = unit.toNanos(timeout);
        if (!Thread.interrupted()) {
            int i = this.status;
            int s2 = i;
            if (i >= 0 && nanos > 0) {
                long d = System.nanoTime() + nanos;
                long deadline2 = d == 0 ? 1 : d;
                Thread t2 = Thread.currentThread();
                if (t2 instanceof ForkJoinWorkerThread) {
                    ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t2;
                    s2 = wt.pool.awaitJoin(wt.workQueue, this, deadline2);
                } else {
                    int i2 = 0;
                    if (this instanceof CountedCompleter) {
                        i2 = ForkJoinPool.common.externalHelpComplete((CountedCompleter) this, 0);
                    } else if (ForkJoinPool.common.tryExternalUnpush(this)) {
                        i2 = doExec();
                    }
                    s2 = i2;
                    if (i2 >= 0) {
                        while (true) {
                            s = this.status;
                            if (s < 0) {
                                break;
                            }
                            long ns = deadline2 - System.nanoTime();
                            if (ns <= 0) {
                                break;
                            }
                            long ms = TimeUnit.NANOSECONDS.toMillis(ns);
                            if (ms > 0) {
                                t = t2;
                                deadline = deadline2;
                                if (U.compareAndSwapInt(this, STATUS, s, s | 65536)) {
                                    synchronized (this) {
                                        try {
                                            if (this.status >= 0) {
                                                wait(ms);
                                            } else {
                                                notifyAll();
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            throw th;
                                        }
                                    }
                                }
                            } else {
                                t = t2;
                                deadline = deadline2;
                            }
                            deadline2 = deadline;
                            t2 = t;
                        }
                        s2 = s;
                    }
                }
            }
            if (s2 >= 0) {
                s2 = this.status;
            }
            int s3 = s2 & -268435456;
            if (s3 == -268435456) {
                return getRawResult();
            }
            if (s3 == CANCELLED) {
                throw new CancellationException();
            } else if (s3 != Integer.MIN_VALUE) {
                throw new TimeoutException();
            } else {
                throw new ExecutionException(getThrowableException());
            }
        } else {
            throw new InterruptedException();
        }
    }

    public final void quietlyJoin() {
        doJoin();
    }

    public final void quietlyInvoke() {
        doInvoke();
    }

    public static void helpQuiesce() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
            wt.pool.helpQuiescePool(wt.workQueue);
            return;
        }
        ForkJoinPool.quiesceCommonPool();
    }

    public void reinitialize() {
        if ((this.status & -268435456) == Integer.MIN_VALUE) {
            clearExceptionalCompletion();
        } else {
            this.status = 0;
        }
    }

    public static ForkJoinPool getPool() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) t).pool;
        }
        return null;
    }

    public static boolean inForkJoinPool() {
        return Thread.currentThread() instanceof ForkJoinWorkerThread;
    }

    public boolean tryUnfork() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) t).workQueue.tryUnpush(this);
        }
        return ForkJoinPool.common.tryExternalUnpush(this);
    }

    public static int getQueuedTaskCount() {
        ForkJoinPool.WorkQueue q;
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            q = ((ForkJoinWorkerThread) t).workQueue;
        } else {
            q = ForkJoinPool.commonSubmitterQueue();
        }
        if (q == null) {
            return 0;
        }
        return q.queueSize();
    }

    public static int getSurplusQueuedTaskCount() {
        return ForkJoinPool.getSurplusQueuedTaskCount();
    }

    protected static ForkJoinTask<?> peekNextLocalTask() {
        ForkJoinPool.WorkQueue q;
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            q = ((ForkJoinWorkerThread) t).workQueue;
        } else {
            q = ForkJoinPool.commonSubmitterQueue();
        }
        if (q == null) {
            return null;
        }
        return q.peek();
    }

    protected static ForkJoinTask<?> pollNextLocalTask() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) t).workQueue.nextLocalTask();
        }
        return null;
    }

    protected static ForkJoinTask<?> pollTask() {
        Thread t = Thread.currentThread();
        if (!(t instanceof ForkJoinWorkerThread)) {
            return null;
        }
        ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
        return wt.pool.nextTaskFor(wt.workQueue);
    }

    protected static ForkJoinTask<?> pollSubmission() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) t).pool.pollSubmission();
        }
        return null;
    }

    public final short getForkJoinTaskTag() {
        return (short) this.status;
    }

    public final short setForkJoinTaskTag(short newValue) {
        Unsafe unsafe;
        long j;
        int s;
        do {
            unsafe = U;
            j = STATUS;
            s = this.status;
        } while (!unsafe.compareAndSwapInt(this, j, s, (65535 & newValue) | (-65536 & s)));
        return (short) s;
    }

    public final boolean compareAndSetForkJoinTaskTag(short expect, short update) {
        int s;
        do {
            s = this.status;
            if (((short) s) != expect) {
                return false;
            }
        } while (!U.compareAndSwapInt(this, STATUS, s, (-65536 & s) | (65535 & update)));
        return true;
    }

    /* access modifiers changed from: package-private */
    public static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
        private static final long serialVersionUID = 5232453952276885070L;
        T result;
        final Runnable runnable;

        AdaptedRunnable(Runnable runnable2, T result2) {
            if (runnable2 != null) {
                this.runnable = runnable2;
                this.result = result2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final T getRawResult() {
            return this.result;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final void setRawResult(T v) {
            this.result = v;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            invoke();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {
        private static final long serialVersionUID = 5232453952276885070L;
        final Runnable runnable;

        AdaptedRunnableAction(Runnable runnable2) {
            if (runnable2 != null) {
                this.runnable = runnable2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            invoke();
        }
    }

    static final class RunnableExecuteAction extends ForkJoinTask<Void> {
        private static final long serialVersionUID = 5232453952276885070L;
        final Runnable runnable;

        RunnableExecuteAction(Runnable runnable2) {
            if (runnable2 != null) {
                this.runnable = runnable2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ForkJoinTask
        public void internalPropagateException(Throwable ex) {
            rethrow(ex);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
        private static final long serialVersionUID = 2838392045355241008L;
        final Callable<? extends T> callable;
        T result;

        AdaptedCallable(Callable<? extends T> callable2) {
            if (callable2 != null) {
                this.callable = callable2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final T getRawResult() {
            return this.result;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final void setRawResult(T v) {
            this.result = v;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            try {
                this.result = (T) this.callable.call();
                return true;
            } catch (RuntimeException rex) {
                throw rex;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            invoke();
        }
    }

    public static ForkJoinTask<?> adapt(Runnable runnable) {
        return new AdaptedRunnableAction(runnable);
    }

    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result) {
        return new AdaptedRunnable(runnable, result);
    }

    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable) {
        return new AdaptedCallable(callable);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(getException());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        Object ex = s.readObject();
        if (ex != null) {
            setExceptionalCompletion((Throwable) ex);
        }
    }

    static {
        try {
            STATUS = U.objectFieldOffset(ForkJoinTask.class.getDeclaredField(NotificationCompat.CATEGORY_STATUS));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
