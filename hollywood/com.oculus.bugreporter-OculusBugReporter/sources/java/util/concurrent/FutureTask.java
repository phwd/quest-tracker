package java.util.concurrent;

import com.oculus.bugreporter.Constants;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Unsafe;

public class FutureTask<V> implements RunnableFuture<V> {
    private static final int CANCELLED = 4;
    private static final int COMPLETING = 1;
    private static final int EXCEPTIONAL = 3;
    private static final int INTERRUPTED = 6;
    private static final int INTERRUPTING = 5;
    private static final int NEW = 0;
    private static final int NORMAL = 2;
    private static final long RUNNER;
    private static final long STATE;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long WAITERS;
    private Callable<V> callable;
    private Object outcome;
    private volatile Thread runner;
    private volatile int state;
    private volatile WaitNode waiters;

    private V report(int s) throws ExecutionException {
        V v = (V) this.outcome;
        if (s == 2) {
            return v;
        }
        if (s >= 4) {
            throw new CancellationException();
        }
        throw new ExecutionException((Throwable) v);
    }

    public FutureTask(Callable<V> callable2) {
        if (callable2 != null) {
            this.callable = callable2;
            this.state = 0;
            return;
        }
        throw new NullPointerException();
    }

    public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = 0;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.state >= 4;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.state != 0;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (this.state != 0) {
            return false;
        }
        if (!U.compareAndSwapInt(this, STATE, 0, mayInterruptIfRunning ? 5 : 4)) {
            return false;
        }
        if (mayInterruptIfRunning) {
            try {
                Thread t = this.runner;
                if (t != null) {
                    t.interrupt();
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

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        int s = this.state;
        if (s <= 1) {
            s = awaitDone(false, 0);
        }
        return report(s);
    }

    @Override // java.util.concurrent.Future
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (unit != null) {
            int s = this.state;
            if (s <= 1) {
                int awaitDone = awaitDone(true, unit.toNanos(timeout));
                s = awaitDone;
                if (awaitDone <= 1) {
                    throw new TimeoutException();
                }
            }
            return report(s);
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    public void done() {
    }

    /* access modifiers changed from: protected */
    public void set(V v) {
        if (U.compareAndSwapInt(this, STATE, 0, 1)) {
            this.outcome = v;
            U.putOrderedInt(this, STATE, 2);
            finishCompletion();
        }
    }

    /* access modifiers changed from: protected */
    public void setException(Throwable t) {
        if (U.compareAndSwapInt(this, STATE, 0, 1)) {
            this.outcome = t;
            U.putOrderedInt(this, STATE, 3);
            finishCompletion();
        }
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        boolean ran;
        V result;
        if (this.state == 0 && U.compareAndSwapObject(this, RUNNER, null, Thread.currentThread())) {
            try {
                Callable<V> c = this.callable;
                if (c != null && this.state == 0) {
                    try {
                        result = c.call();
                        ran = true;
                    } catch (Throwable ex) {
                        setException(ex);
                        result = null;
                        ran = false;
                    }
                    if (ran) {
                        set(result);
                    }
                }
            } finally {
                this.runner = null;
                int s = this.state;
                if (s >= 5) {
                    handlePossibleCancellationInterrupt(s);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean runAndReset() {
        int s;
        if (this.state != 0 || !U.compareAndSwapObject(this, RUNNER, null, Thread.currentThread())) {
            return false;
        }
        boolean ran = false;
        int s2 = this.state;
        try {
            Callable<V> c = this.callable;
            if (c != null && s2 == 0) {
                try {
                    c.call();
                    ran = true;
                } catch (Throwable ex) {
                    setException(ex);
                }
            }
            if (!ran || s != 0) {
                return false;
            }
            return true;
        } finally {
            this.runner = null;
            s = this.state;
            if (s >= 5) {
                handlePossibleCancellationInterrupt(s);
            }
        }
    }

    private void handlePossibleCancellationInterrupt(int s) {
        if (s == 5) {
            while (this.state == 5) {
                Thread.yield();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WaitNode {
        volatile WaitNode next;
        volatile Thread thread = Thread.currentThread();

        WaitNode() {
        }
    }

    private void finishCompletion() {
        while (true) {
            WaitNode waitNode = this.waiters;
            WaitNode q = waitNode;
            if (waitNode == null) {
                break;
            } else if (U.compareAndSwapObject(this, WAITERS, q, null)) {
                while (true) {
                    Thread t = q.thread;
                    if (t != null) {
                        q.thread = null;
                        LockSupport.unpark(t);
                    }
                    WaitNode next = q.next;
                    if (next == null) {
                        break;
                    }
                    q.next = null;
                    q = next;
                }
            }
        }
        done();
        this.callable = null;
    }

    private int awaitDone(boolean timed, long nanos) throws InterruptedException {
        long elapsed;
        long startTime = 0;
        WaitNode q = null;
        boolean queued = false;
        while (true) {
            int s = this.state;
            if (s > 1) {
                if (q != null) {
                    q.thread = null;
                }
                return s;
            } else if (s == 1) {
                Thread.yield();
            } else if (Thread.interrupted()) {
                removeWaiter(q);
                throw new InterruptedException();
            } else if (q == null) {
                if (timed && nanos <= 0) {
                    return s;
                }
                q = new WaitNode();
            } else if (!queued) {
                Unsafe unsafe = U;
                long j = WAITERS;
                WaitNode waitNode = this.waiters;
                q.next = waitNode;
                queued = unsafe.compareAndSwapObject(this, j, waitNode, q);
            } else if (timed) {
                if (startTime == 0) {
                    startTime = System.nanoTime();
                    if (startTime == 0) {
                        startTime = 1;
                    }
                    elapsed = nanos;
                } else {
                    long elapsed2 = System.nanoTime() - startTime;
                    if (elapsed2 >= nanos) {
                        removeWaiter(q);
                        return this.state;
                    }
                    elapsed = nanos - elapsed2;
                }
                if (this.state < 1) {
                    LockSupport.parkNanos(this, elapsed);
                }
            } else {
                LockSupport.park(this);
            }
        }
    }

    private void removeWaiter(WaitNode node) {
        if (node != null) {
            node.thread = null;
            while (true) {
                WaitNode pred = null;
                WaitNode q = this.waiters;
                while (q != null) {
                    WaitNode s = q.next;
                    if (q.thread != null) {
                        pred = q;
                    } else if (pred != null) {
                        pred.next = s;
                        if (pred.thread == null) {
                        }
                    } else if (!U.compareAndSwapObject(this, WAITERS, q, s)) {
                    }
                    q = s;
                }
                return;
            }
        }
    }

    static {
        try {
            STATE = U.objectFieldOffset(FutureTask.class.getDeclaredField(Constants.EXTRA_STATE));
            RUNNER = U.objectFieldOffset(FutureTask.class.getDeclaredField("runner"));
            WAITERS = U.objectFieldOffset(FutureTask.class.getDeclaredField("waiters"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
