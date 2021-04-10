package java.util.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public abstract class ForkJoinTask implements Future, Serializable {
    private static final long STATUS;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final ExceptionNode[] exceptionTable = new ExceptionNode[32];
    private static final ReentrantLock exceptionTableLock = new ReentrantLock();
    private static final ReferenceQueue exceptionTableRefQueue = new ReferenceQueue();
    private static final long serialVersionUID = -7721805057305804111L;
    volatile int status;

    /* access modifiers changed from: protected */
    public abstract boolean exec();

    public abstract Object getRawResult();

    /* access modifiers changed from: package-private */
    public void internalPropagateException(Throwable th) {
    }

    private int setCompletion(int i) {
        int i2;
        do {
            i2 = this.status;
            if (i2 < 0) {
                return i2;
            }
        } while (!U.compareAndSwapInt(this, STATUS, i2, i2 | i));
        if ((i2 >>> 16) != 0) {
            synchronized (this) {
                notifyAll();
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final int doExec() {
        int i = this.status;
        if (i < 0) {
            return i;
        }
        try {
            if (exec()) {
                return setCompletion(-268435456);
            }
            return i;
        } catch (Throwable th) {
            return setExceptionalCompletion(th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void internalWait(long j) {
        int i = this.status;
        if (i >= 0 && U.compareAndSwapInt(this, STATUS, i, i | 65536)) {
            synchronized (this) {
                if (this.status >= 0) {
                    try {
                        wait(j);
                    } catch (InterruptedException unused) {
                    }
                } else {
                    notifyAll();
                }
            }
        }
    }

    private int externalAwaitDone() {
        int i;
        boolean z = false;
        if (this instanceof CountedCompleter) {
            i = ForkJoinPool.common.externalHelpComplete((CountedCompleter) this, 0);
        } else {
            i = ForkJoinPool.common.tryExternalUnpush(this) ? doExec() : 0;
        }
        if (i < 0) {
            return i;
        }
        int i2 = this.status;
        if (i2 < 0) {
            return i2;
        }
        int i3 = i2;
        do {
            if (U.compareAndSwapInt(this, STATUS, i3, i3 | 65536)) {
                synchronized (this) {
                    if (this.status >= 0) {
                        try {
                            wait(STATUS);
                        } catch (InterruptedException unused) {
                            z = true;
                        }
                    } else {
                        notifyAll();
                    }
                }
            }
            i3 = this.status;
        } while (i3 >= 0);
        if (z) {
            Thread.currentThread().interrupt();
        }
        return i3;
    }

    private int doInvoke() {
        int doExec = doExec();
        if (doExec < 0) {
            return doExec;
        }
        Thread currentThread = Thread.currentThread();
        if (!(currentThread instanceof ForkJoinWorkerThread)) {
            return externalAwaitDone();
        }
        ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) currentThread;
        return forkJoinWorkerThread.pool.awaitJoin(forkJoinWorkerThread.workQueue, this, STATUS);
    }

    /* access modifiers changed from: package-private */
    public static final class ExceptionNode extends WeakReference {
        final Throwable ex;
        final int hashCode;
        ExceptionNode next;
        final long thrower = Thread.currentThread().getId();

        ExceptionNode(ForkJoinTask forkJoinTask, Throwable th, ExceptionNode exceptionNode, ReferenceQueue referenceQueue) {
            super(forkJoinTask, referenceQueue);
            this.ex = th;
            this.next = exceptionNode;
            this.hashCode = System.identityHashCode(forkJoinTask);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final int recordExceptionalCompletion(Throwable th) {
        int i = this.status;
        if (i < 0) {
            return i;
        }
        int identityHashCode = System.identityHashCode(this);
        ReentrantLock reentrantLock = exceptionTableLock;
        reentrantLock.lock();
        try {
            expungeStaleExceptions();
            ExceptionNode[] exceptionNodeArr = exceptionTable;
            int length = identityHashCode & (exceptionNodeArr.length - 1);
            ExceptionNode exceptionNode = exceptionNodeArr[length];
            while (true) {
                if (exceptionNode == null) {
                    exceptionNodeArr[length] = new ExceptionNode(this, th, exceptionNodeArr[length], exceptionTableRefQueue);
                    break;
                } else if (exceptionNode.get() == this) {
                    break;
                } else {
                    exceptionNode = exceptionNode.next;
                }
            }
            reentrantLock.unlock();
            return setCompletion(Integer.MIN_VALUE);
        } catch (Throwable th2) {
            reentrantLock.unlock();
            throw th2;
        }
    }

    private int setExceptionalCompletion(Throwable th) {
        int recordExceptionalCompletion = recordExceptionalCompletion(th);
        if ((-268435456 & recordExceptionalCompletion) == Integer.MIN_VALUE) {
            internalPropagateException(th);
        }
        return recordExceptionalCompletion;
    }

    static final void cancelIgnoringExceptions(ForkJoinTask forkJoinTask) {
        if (forkJoinTask != null && forkJoinTask.status >= 0) {
            try {
                forkJoinTask.cancel(false);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private Throwable getThrowableException() {
        Throwable th;
        int identityHashCode = System.identityHashCode(this);
        ReentrantLock reentrantLock = exceptionTableLock;
        reentrantLock.lock();
        try {
            expungeStaleExceptions();
            ExceptionNode[] exceptionNodeArr = exceptionTable;
            ExceptionNode exceptionNode = exceptionNodeArr[identityHashCode & (exceptionNodeArr.length - 1)];
            while (exceptionNode != null && exceptionNode.get() != this) {
                exceptionNode = exceptionNode.next;
            }
            reentrantLock.unlock();
            if (exceptionNode == null || (th = exceptionNode.ex) == null) {
                return null;
            }
            if (exceptionNode.thrower != Thread.currentThread().getId()) {
                try {
                    Constructor[] constructors = th.getClass().getConstructors();
                    Constructor constructor = null;
                    for (Constructor constructor2 : constructors) {
                        Class[] parameterTypes = constructor2.getParameterTypes();
                        if (parameterTypes.length == 0) {
                            constructor = constructor2;
                        } else if (parameterTypes.length == 1 && parameterTypes[0] == Throwable.class) {
                            return (Throwable) constructor2.newInstance(th);
                        }
                    }
                    if (constructor != null) {
                        Throwable th2 = (Throwable) constructor.newInstance(new Object[0]);
                        th2.initCause(th);
                        return th2;
                    }
                } catch (Exception unused) {
                }
            }
            return th;
        } catch (Throwable th3) {
            reentrantLock.unlock();
            throw th3;
        }
    }

    private static void expungeStaleExceptions() {
        while (true) {
            Reference poll = exceptionTableRefQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll instanceof ExceptionNode) {
                int i = ((ExceptionNode) poll).hashCode;
                ExceptionNode[] exceptionNodeArr = exceptionTable;
                int length = i & (exceptionNodeArr.length - 1);
                ExceptionNode exceptionNode = exceptionNodeArr[length];
                ExceptionNode exceptionNode2 = null;
                while (true) {
                    if (exceptionNode == null) {
                        break;
                    }
                    ExceptionNode exceptionNode3 = exceptionNode.next;
                    if (exceptionNode != poll) {
                        exceptionNode2 = exceptionNode;
                        exceptionNode = exceptionNode3;
                    } else if (exceptionNode2 == null) {
                        exceptionNodeArr[length] = exceptionNode3;
                    } else {
                        exceptionNode2.next = exceptionNode3;
                    }
                }
            }
        }
    }

    static final void helpExpungeStaleExceptions() {
        ReentrantLock reentrantLock = exceptionTableLock;
        if (reentrantLock.tryLock()) {
            try {
                expungeStaleExceptions();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    static void rethrow(Throwable th) {
        uncheckedThrow(th);
        throw null;
    }

    static void uncheckedThrow(Throwable th) {
        if (th != null) {
            throw th;
        }
        throw new Error("Unknown Exception");
    }

    private void reportException(int i) {
        if (i == -1073741824) {
            throw new CancellationException();
        } else if (i == Integer.MIN_VALUE) {
            rethrow(getThrowableException());
            throw null;
        }
    }

    public final Object invoke() {
        int doInvoke = doInvoke() & -268435456;
        if (doInvoke != -268435456) {
            reportException(doInvoke);
        }
        return getRawResult();
    }

    public boolean cancel(boolean z) {
        return (setCompletion(-1073741824) & -268435456) == -1073741824;
    }

    static final class AdaptedRunnable extends ForkJoinTask implements RunnableFuture {
        private static final long serialVersionUID = 5232453952276885070L;
        Object result;
        final Runnable runnable;

        AdaptedRunnable(Runnable runnable2, Object obj) {
            if (runnable2 != null) {
                this.runnable = runnable2;
                this.result = obj;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Object getRawResult() {
            return this.result;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java.lang.Runnable
        public final void run() {
            invoke();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class AdaptedRunnableAction extends ForkJoinTask implements RunnableFuture {
        private static final long serialVersionUID = 5232453952276885070L;
        final Runnable runnable;

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        AdaptedRunnableAction(Runnable runnable2) {
            if (runnable2 != null) {
                this.runnable = runnable2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        @Override // java.lang.Runnable
        public final void run() {
            invoke();
        }
    }

    static final class RunnableExecuteAction extends ForkJoinTask {
        private static final long serialVersionUID = 5232453952276885070L;
        final Runnable runnable;

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        RunnableExecuteAction(Runnable runnable2) {
            if (runnable2 != null) {
                this.runnable = runnable2;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            this.runnable.run();
            return true;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ForkJoinTask
        public void internalPropagateException(Throwable th) {
            ForkJoinTask.rethrow(th);
            throw null;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    static {
        try {
            STATUS = U.objectFieldOffset(ForkJoinTask.class.getDeclaredField("status"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
