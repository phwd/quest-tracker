package java.util.concurrent;

import java.util.concurrent.locks.LockSupport;
import sun.misc.Unsafe;

public class FutureTask implements RunnableFuture {
    private static final long RUNNER;
    private static final long STATE;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long WAITERS;
    private Callable callable;
    private Object outcome;
    private volatile Thread runner;
    private volatile int state = 0;
    private volatile WaitNode waiters;

    /* access modifiers changed from: package-private */
    public static final class WaitNode {
        volatile WaitNode next;
        volatile Thread thread;
    }

    /* access modifiers changed from: protected */
    public void done() {
    }

    public FutureTask(Runnable runnable, Object obj) {
        this.callable = Executors.callable(runnable, obj);
    }

    public boolean isCancelled() {
        return this.state >= 4;
    }

    public boolean isDone() {
        return this.state != 0;
    }

    public boolean cancel(boolean z) {
        if (this.state != 0) {
            return false;
        }
        if (!U.compareAndSwapInt(this, STATE, 0, z ? 5 : 4)) {
            return false;
        }
        if (z) {
            try {
                Thread thread = this.runner;
                if (thread != null) {
                    thread.interrupt();
                }
                U.putOrderedInt(this, STATE, 6);
            } catch (Throwable th) {
                finishCompletion();
                throw th;
            }
        }
        finishCompletion();
        return true;
    }

    /* access modifiers changed from: protected */
    public void set(Object obj) {
        if (U.compareAndSwapInt(this, STATE, 0, 1)) {
            this.outcome = obj;
            U.putOrderedInt(this, STATE, 2);
            finishCompletion();
        }
    }

    /* access modifiers changed from: protected */
    public void setException(Throwable th) {
        if (U.compareAndSwapInt(this, STATE, 0, 1)) {
            this.outcome = th;
            U.putOrderedInt(this, STATE, 3);
            finishCompletion();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        Object obj;
        if (this.state == 0 && U.compareAndSwapObject(this, RUNNER, null, Thread.currentThread())) {
            try {
                Callable callable2 = this.callable;
                if (callable2 != null && this.state == 0) {
                    try {
                        obj = callable2.call();
                        z = true;
                    } catch (Throwable th) {
                        z = false;
                        setException(th);
                        obj = null;
                    }
                    if (z) {
                        set(obj);
                    }
                }
            } finally {
                this.runner = null;
                int i = this.state;
                if (i >= 5) {
                    handlePossibleCancellationInterrupt(i);
                }
            }
        }
    }

    private void handlePossibleCancellationInterrupt(int i) {
        if (i == 5) {
            while (this.state == 5) {
                Thread.yield();
            }
        }
    }

    private void finishCompletion() {
        while (true) {
            WaitNode waitNode = this.waiters;
            if (waitNode == null) {
                break;
            } else if (U.compareAndSwapObject(this, WAITERS, waitNode, null)) {
                while (true) {
                    Thread thread = waitNode.thread;
                    if (thread != null) {
                        waitNode.thread = null;
                        LockSupport.unpark(thread);
                    }
                    WaitNode waitNode2 = waitNode.next;
                    if (waitNode2 == null) {
                        break;
                    }
                    waitNode.next = null;
                    waitNode = waitNode2;
                }
            }
        }
        done();
        this.callable = null;
    }

    static {
        try {
            STATE = U.objectFieldOffset(FutureTask.class.getDeclaredField("state"));
            RUNNER = U.objectFieldOffset(FutureTask.class.getDeclaredField("runner"));
            WAITERS = U.objectFieldOffset(FutureTask.class.getDeclaredField("waiters"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
