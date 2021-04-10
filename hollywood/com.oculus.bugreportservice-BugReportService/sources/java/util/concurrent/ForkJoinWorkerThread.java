package java.util.concurrent;

import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.ProtectionDomain;
import java.util.concurrent.ForkJoinPool;
import sun.misc.Unsafe;

public class ForkJoinWorkerThread extends Thread {
    private static final long INHERITABLETHREADLOCALS;
    private static final long INHERITEDACCESSCONTROLCONTEXT;
    private static final long THREADLOCALS;
    private static final Unsafe U = Unsafe.getUnsafe();
    final ForkJoinPool pool;
    final ForkJoinPool.WorkQueue workQueue;

    /* access modifiers changed from: package-private */
    public void afterTopLevelExec() {
    }

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    /* access modifiers changed from: protected */
    public void onTermination(Throwable th) {
    }

    protected ForkJoinWorkerThread(ForkJoinPool forkJoinPool) {
        super("aForkJoinWorkerThread");
        this.pool = forkJoinPool;
        this.workQueue = forkJoinPool.registerWorker(this);
    }

    ForkJoinWorkerThread(ForkJoinPool forkJoinPool, ThreadGroup threadGroup, AccessControlContext accessControlContext) {
        super(threadGroup, null, "aForkJoinWorkerThread");
        U.putOrderedObject(this, INHERITEDACCESSCONTROLCONTEXT, accessControlContext);
        eraseThreadLocals();
        this.pool = forkJoinPool;
        this.workQueue = forkJoinPool.registerWorker(this);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.workQueue.array == null) {
            Throwable th = null;
            try {
                onStart();
                this.pool.runWorker(this.workQueue);
                try {
                    onTermination(null);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable unused) {
            }
            this.pool.deregisterWorker(this, th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void eraseThreadLocals() {
        U.putObject(this, THREADLOCALS, null);
        U.putObject(this, INHERITABLETHREADLOCALS, null);
    }

    static {
        try {
            THREADLOCALS = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocals"));
            INHERITABLETHREADLOCALS = U.objectFieldOffset(Thread.class.getDeclaredField("inheritableThreadLocals"));
            INHERITEDACCESSCONTROLCONTEXT = U.objectFieldOffset(Thread.class.getDeclaredField("inheritedAccessControlContext"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnocuousForkJoinWorkerThread extends ForkJoinWorkerThread {
        private static final AccessControlContext INNOCUOUS_ACC = new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, null)});
        private static final ThreadGroup innocuousThreadGroup = createThreadGroup();

        @Override // java.lang.Thread
        public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        }

        InnocuousForkJoinWorkerThread(ForkJoinPool forkJoinPool) {
            super(forkJoinPool, innocuousThreadGroup, INNOCUOUS_ACC);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ForkJoinWorkerThread
        public void afterTopLevelExec() {
            eraseThreadLocals();
        }

        @Override // java.lang.Thread
        public ClassLoader getContextClassLoader() {
            return ClassLoader.getSystemClassLoader();
        }

        private static ThreadGroup createThreadGroup() {
            try {
                Unsafe unsafe = Unsafe.getUnsafe();
                long objectFieldOffset = unsafe.objectFieldOffset(Thread.class.getDeclaredField("group"));
                long objectFieldOffset2 = unsafe.objectFieldOffset(ThreadGroup.class.getDeclaredField("parent"));
                ThreadGroup threadGroup = (ThreadGroup) unsafe.getObject(Thread.currentThread(), objectFieldOffset);
                while (threadGroup != null) {
                    ThreadGroup threadGroup2 = (ThreadGroup) unsafe.getObject(threadGroup, objectFieldOffset2);
                    if (threadGroup2 == null) {
                        return new ThreadGroup(threadGroup, "InnocuousForkJoinWorkerThreadGroup");
                    }
                    threadGroup = threadGroup2;
                }
                throw new Error("Cannot create ThreadGroup");
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }
}
