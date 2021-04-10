package java.util.concurrent;

import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public class ForkJoinPool extends AbstractExecutorService {
    private static final int ABASE;
    private static final long AC_MASK = -281474976710656L;
    private static final int AC_SHIFT = 48;
    private static final long AC_UNIT = 281474976710656L;
    private static final long ADD_WORKER = 140737488355328L;
    private static final int ASHIFT;
    private static final int COMMON_MAX_SPARES;
    static final int COMMON_PARALLELISM = Math.max(common.config & 65535, 1);
    private static final long CTL;
    private static final int DEFAULT_COMMON_MAX_SPARES = 256;
    static final int EVENMASK = 65534;
    static final int FIFO_QUEUE = Integer.MIN_VALUE;
    private static final long IDLE_TIMEOUT_MS = 2000;
    static final int IS_OWNED = 1;
    static final int LIFO_QUEUE = 0;
    static final int MAX_CAP = 32767;
    static final int MODE_MASK = -65536;
    static final int POLL_LIMIT = 1023;
    private static final long RUNSTATE;
    private static final int SEED_INCREMENT = -1640531527;
    private static final int SHUTDOWN = Integer.MIN_VALUE;
    static final int SMASK = 65535;
    static final int SPARE_WORKER = 131072;
    private static final long SP_MASK = 4294967295L;
    static final int SQMASK = 126;
    static final int SS_SEQ = 65536;
    private static final int STARTED = 1;
    private static final int STOP = 2;
    private static final long TC_MASK = 281470681743360L;
    private static final int TC_SHIFT = 32;
    private static final long TC_UNIT = 4294967296L;
    private static final int TERMINATED = 4;
    private static final long TIMEOUT_SLOP_MS = 20;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long UC_MASK = -4294967296L;
    static final int UNREGISTERED = 262144;
    static final int UNSIGNALLED = Integer.MIN_VALUE;
    static final ForkJoinPool common = ((ForkJoinPool) AccessController.doPrivileged(new PrivilegedAction<ForkJoinPool>() {
        /* class java.util.concurrent.ForkJoinPool.AnonymousClass1 */

        @Override // java.security.PrivilegedAction
        public ForkJoinPool run() {
            return ForkJoinPool.makeCommonPool();
        }
    }));
    public static final ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory = new DefaultForkJoinWorkerThreadFactory();
    static final RuntimePermission modifyThreadPermission = new RuntimePermission("modifyThread");
    private static int poolNumberSequence;
    AuxState auxState;
    final int config;
    volatile long ctl;
    final ForkJoinWorkerThreadFactory factory;
    volatile int runState;
    final Thread.UncaughtExceptionHandler ueh;
    volatile WorkQueue[] workQueues;
    final String workerNamePrefix;

    public interface ForkJoinWorkerThreadFactory {
        ForkJoinWorkerThread newThread(ForkJoinPool forkJoinPool);
    }

    public interface ManagedBlocker {
        boolean block() throws InterruptedException;

        boolean isReleasable();
    }

    private static void checkPermission() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(modifyThreadPermission);
        }
    }

    private static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
        private DefaultForkJoinWorkerThreadFactory() {
        }

        @Override // java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
        public final ForkJoinWorkerThread newThread(ForkJoinPool pool) {
            return new ForkJoinWorkerThread(pool);
        }
    }

    /* access modifiers changed from: private */
    public static final class EmptyTask extends ForkJoinTask<Void> {
        private static final long serialVersionUID = -7721805057305804111L;

        EmptyTask() {
            this.status = -268435456;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void x) {
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static final class AuxState extends ReentrantLock {
        private static final long serialVersionUID = -6001602636862214147L;
        long indexSeed;
        volatile long stealCount;

        AuxState() {
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WorkQueue {
        private static final int ABASE;
        private static final int ASHIFT;
        static final int INITIAL_QUEUE_CAPACITY = 8192;
        static final int MAXIMUM_QUEUE_CAPACITY = 67108864;
        private static final long QLOCK;
        private static final Unsafe U = Unsafe.getUnsafe();
        ForkJoinTask<?>[] array;
        volatile int base = 4096;
        int config;
        volatile ForkJoinTask<?> currentJoin;
        volatile ForkJoinTask<?> currentSteal;
        int hint;
        int nsteals;
        final ForkJoinWorkerThread owner;
        volatile Thread parker;
        final ForkJoinPool pool;
        volatile int qlock;
        volatile int scanState;
        int stackPred;
        int top = 4096;

        WorkQueue(ForkJoinPool pool2, ForkJoinWorkerThread owner2) {
            this.pool = pool2;
            this.owner = owner2;
        }

        /* access modifiers changed from: package-private */
        public final int getPoolIndex() {
            return (this.config & 65535) >>> 1;
        }

        /* access modifiers changed from: package-private */
        public final int queueSize() {
            int n = this.base - this.top;
            if (n >= 0) {
                return 0;
            }
            return -n;
        }

        /* access modifiers changed from: package-private */
        public final boolean isEmpty() {
            ForkJoinTask<?>[] a;
            int al;
            int i = this.base;
            int s = this.top;
            int n = i - s;
            return n >= 0 || (n == -1 && ((a = this.array) == null || (al = a.length) == 0 || a[(al + -1) & (s + -1)] == null));
        }

        /* access modifiers changed from: package-private */
        public final void push(ForkJoinTask<?> task) {
            int al;
            U.storeFence();
            int s = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (a != null && (al = a.length) > 0) {
                a[(al - 1) & s] = task;
                this.top = s + 1;
                ForkJoinPool p = this.pool;
                int d = this.base - s;
                if (d == 0 && p != null) {
                    U.fullFence();
                    p.signalWork();
                } else if (al + d == 1) {
                    growArray();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask<?>[] growArray() {
            int oldMask;
            ForkJoinTask<?>[] oldA = this.array;
            int size = oldA != null ? oldA.length << 1 : 8192;
            if (size < 8192 || size > 67108864) {
                throw new RejectedExecutionException("Queue capacity exceeded");
            }
            ForkJoinTask<?>[] a = new ForkJoinTask[size];
            this.array = a;
            if (oldA != null && oldA.length - 1 > 0) {
                int t = this.top;
                int b = this.base;
                if (t - b > 0) {
                    int mask = size - 1;
                    int b2 = b;
                    do {
                        long offset = (((long) (b2 & oldMask)) << ASHIFT) + ((long) ABASE);
                        ForkJoinTask<?> x = (ForkJoinTask) U.getObjectVolatile(oldA, offset);
                        if (x != null) {
                            if (U.compareAndSwapObject(oldA, offset, x, null)) {
                                a[b2 & mask] = x;
                            }
                        }
                        b2++;
                    } while (b2 != t);
                    U.storeFence();
                }
            }
            return a;
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask<?> pop() {
            int al;
            int b = this.base;
            int s = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (a == null || b == s || (al = a.length) <= 0) {
                return null;
            }
            int s2 = s - 1;
            long offset = (((long) ((al - 1) & s2)) << ASHIFT) + ((long) ABASE);
            ForkJoinTask<?> t = (ForkJoinTask) U.getObject(a, offset);
            if (t == null || !U.compareAndSwapObject(a, offset, t, null)) {
                return null;
            }
            this.top = s2;
            return t;
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask<?> pollAt(int b) {
            int al;
            ForkJoinTask<?>[] a = this.array;
            if (a == null || (al = a.length) <= 0) {
                return null;
            }
            long offset = (((long) ((al - 1) & b)) << ASHIFT) + ((long) ABASE);
            ForkJoinTask<?> t = (ForkJoinTask) U.getObjectVolatile(a, offset);
            if (t == null) {
                return null;
            }
            int b2 = b + 1;
            if (b != this.base || !U.compareAndSwapObject(a, offset, t, null)) {
                return null;
            }
            this.base = b2;
            return t;
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask<?> poll() {
            while (true) {
                int b = this.base;
                int s = this.top;
                ForkJoinTask<?>[] a = this.array;
                if (a == null) {
                    return null;
                }
                int d = b - s;
                if (d >= 0) {
                    return null;
                }
                int al = a.length;
                if (al <= 0) {
                    return null;
                }
                long offset = (((long) ((al - 1) & b)) << ASHIFT) + ((long) ABASE);
                ForkJoinTask<?> t = (ForkJoinTask) U.getObjectVolatile(a, offset);
                int b2 = b + 1;
                if (b == this.base) {
                    if (t != null) {
                        if (U.compareAndSwapObject(a, offset, t, null)) {
                            this.base = b2;
                            return t;
                        }
                    } else if (d == -1) {
                        return null;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask<?> nextLocalTask() {
            return this.config < 0 ? poll() : pop();
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask<?> peek() {
            int al;
            ForkJoinTask<?>[] a = this.array;
            if (a == null || (al = a.length) <= 0) {
                return null;
            }
            return a[(al - 1) & (this.config < 0 ? this.base : this.top - 1)];
        }

        /* access modifiers changed from: package-private */
        public final boolean tryUnpush(ForkJoinTask<?> task) {
            int al;
            int b = this.base;
            int s = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (a == null || b == s || (al = a.length) <= 0) {
                return false;
            }
            int s2 = s - 1;
            if (!U.compareAndSwapObject(a, (((long) ((al - 1) & s2)) << ASHIFT) + ((long) ABASE), task, null)) {
                return false;
            }
            this.top = s2;
            return true;
        }

        /* access modifiers changed from: package-private */
        public final int sharedPush(ForkJoinTask<?> task) {
            int al;
            if (!U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                return 1;
            }
            int b = this.base;
            int s = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (a != null && (al = a.length) > 0) {
                int d = b - s;
                if ((al - 1) + d > 0) {
                    a[(al - 1) & s] = task;
                    this.top = s + 1;
                    this.qlock = 0;
                    if (d >= 0 || b != this.base) {
                        return 0;
                    }
                    return d;
                }
            }
            growAndSharedPush(task);
            return 0;
        }

        private void growAndSharedPush(ForkJoinTask<?> task) {
            int al;
            try {
                growArray();
                int s = this.top;
                ForkJoinTask<?>[] a = this.array;
                if (a != null && (al = a.length) > 0) {
                    a[(al - 1) & s] = task;
                    this.top = s + 1;
                }
            } finally {
                this.qlock = 0;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean trySharedUnpush(ForkJoinTask<?> task) {
            int al;
            boolean popped = false;
            int s = this.top - 1;
            ForkJoinTask<?>[] a = this.array;
            if (a != null && (al = a.length) > 0) {
                long offset = (((long) ((al - 1) & s)) << ASHIFT) + ((long) ABASE);
                if (((ForkJoinTask) U.getObject(a, offset)) == task && U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                    if (this.top == s + 1 && this.array == a && U.compareAndSwapObject(a, offset, task, null)) {
                        popped = true;
                        this.top = s;
                    }
                    U.putOrderedInt(this, QLOCK, 0);
                }
            }
            return popped;
        }

        /* access modifiers changed from: package-private */
        public final void cancelAll() {
            ForkJoinTask<?> t = this.currentJoin;
            if (t != null) {
                this.currentJoin = null;
                ForkJoinTask.cancelIgnoringExceptions(t);
            }
            ForkJoinTask<?> t2 = this.currentSteal;
            if (t2 != null) {
                this.currentSteal = null;
                ForkJoinTask.cancelIgnoringExceptions(t2);
            }
            while (true) {
                ForkJoinTask<?> t3 = poll();
                if (t3 != null) {
                    ForkJoinTask.cancelIgnoringExceptions(t3);
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void localPopAndExec() {
            int al;
            int nexec = 0;
            do {
                int b = this.base;
                int s = this.top;
                ForkJoinTask<?>[] a = this.array;
                if (a != null && b != s && (al = a.length) > 0) {
                    int s2 = s - 1;
                    ForkJoinTask<?> t = (ForkJoinTask) U.getAndSetObject(a, (((long) ((al - 1) & s2)) << ASHIFT) + ((long) ABASE), null);
                    if (t != null) {
                        this.top = s2;
                        this.currentSteal = t;
                        t.doExec();
                        nexec++;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (nexec <= 1023);
        }

        /* JADX INFO: Multiple debug info for r1v1 int: [D('index' int), D('b' int)] */
        /* access modifiers changed from: package-private */
        public final void localPollAndExec() {
            int al;
            int nexec = 0;
            while (true) {
                int b = this.base;
                int s = this.top;
                ForkJoinTask<?>[] a = this.array;
                if (a != null && b != s && (al = a.length) > 0) {
                    int b2 = b + 1;
                    ForkJoinTask<?> t = (ForkJoinTask) U.getAndSetObject(a, (((long) (b & (al - 1))) << ASHIFT) + ((long) ABASE), null);
                    if (t != null) {
                        this.base = b2;
                        t.doExec();
                        nexec++;
                        if (nexec > 1023) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void runTask(ForkJoinTask<?> task) {
            if (task != null) {
                task.doExec();
                if (this.config < 0) {
                    localPollAndExec();
                } else {
                    localPopAndExec();
                }
                int ns = this.nsteals + 1;
                this.nsteals = ns;
                ForkJoinWorkerThread thread = this.owner;
                this.currentSteal = null;
                if (ns < 0) {
                    transferStealCount(this.pool);
                }
                if (thread != null) {
                    thread.afterTopLevelExec();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void transferStealCount(ForkJoinPool p) {
            AuxState aux;
            if (p != null && (aux = p.auxState) != null) {
                long s = (long) this.nsteals;
                this.nsteals = 0;
                if (s < 0) {
                    s = 2147483647L;
                }
                aux.lock();
                try {
                    aux.stealCount += s;
                } finally {
                    aux.unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean tryRemoveAndExec(ForkJoinTask<?> task) {
            ForkJoinTask<?>[] a;
            int al;
            if (task == null || task.status < 0) {
                return true;
            }
            do {
                int b = this.base;
                int i = this.top;
                int s = i;
                int i2 = b - i;
                int d = i2;
                if (i2 < 0 && (a = this.array) != null && (al = a.length) > 0) {
                    while (true) {
                        s--;
                        long offset = (long) ((((al - 1) & s) << ASHIFT) + ABASE);
                        ForkJoinTask<?> t = (ForkJoinTask) U.getObjectVolatile(a, offset);
                        if (t == null) {
                            break;
                        } else if (t == task) {
                            boolean removed = false;
                            if (s + 1 == this.top) {
                                if (U.compareAndSwapObject(a, offset, t, null)) {
                                    this.top = s;
                                    removed = true;
                                }
                            } else if (this.base == b) {
                                removed = U.compareAndSwapObject(a, offset, t, new EmptyTask());
                            }
                            if (removed) {
                                ForkJoinTask<?> ps = this.currentSteal;
                                this.currentSteal = task;
                                task.doExec();
                                this.currentSteal = ps;
                            }
                        } else if (t.status >= 0 || s + 1 != this.top) {
                            d++;
                            if (d == 0) {
                                if (this.base == b) {
                                    return false;
                                }
                            }
                        } else if (U.compareAndSwapObject(a, offset, t, null)) {
                            this.top = s;
                        }
                    }
                } else {
                    return true;
                }
            } while (task.status >= 0);
            return false;
        }

        /* access modifiers changed from: package-private */
        public final CountedCompleter<?> popCC(CountedCompleter<?> task, int mode) {
            int al;
            int b = this.base;
            int s = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (a == null || b == s || (al = a.length) <= 0) {
                return null;
            }
            int index = (al - 1) & (s - 1);
            long offset = (((long) index) << ASHIFT) + ((long) ABASE);
            ForkJoinTask<?> o = (ForkJoinTask) U.getObjectVolatile(a, offset);
            if (!(o instanceof CountedCompleter)) {
                return null;
            }
            CountedCompleter<?> t = (CountedCompleter) o;
            CountedCompleter<?> r = t;
            while (r != task) {
                CountedCompleter<?> countedCompleter = r.completer;
                r = countedCompleter;
                if (countedCompleter == null) {
                    return null;
                }
                offset = offset;
                o = o;
                index = index;
            }
            if ((mode & 1) == 0) {
                boolean popped = false;
                if (!U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                    return null;
                }
                if (this.top == s && this.array == a) {
                    if (U.compareAndSwapObject(a, offset, t, null)) {
                        popped = true;
                        this.top = s - 1;
                    }
                }
                U.putOrderedInt(this, QLOCK, 0);
                if (popped) {
                    return t;
                }
                return null;
            } else if (!U.compareAndSwapObject(a, offset, t, null)) {
                return null;
            } else {
                this.top = s - 1;
                return t;
            }
        }

        /* access modifiers changed from: package-private */
        public final int pollAndExecCC(CountedCompleter<?> task) {
            int b = this.base;
            int s = this.top;
            ForkJoinTask<?>[] a = this.array;
            if (a != null && b != s) {
                int al = a.length;
                if (al > 0) {
                    long offset = (((long) ((al - 1) & b)) << ASHIFT) + ((long) ABASE);
                    ForkJoinTask<?> o = (ForkJoinTask) U.getObjectVolatile(a, offset);
                    if (o == null) {
                        return 2;
                    }
                    if (!(o instanceof CountedCompleter)) {
                        return -1;
                    }
                    CountedCompleter<?> t = (CountedCompleter) o;
                    CountedCompleter<?> r = t;
                    while (r != task) {
                        CountedCompleter<?> countedCompleter = r.completer;
                        r = countedCompleter;
                        if (countedCompleter == null) {
                            return -1;
                        }
                        s = s;
                    }
                    int b2 = b + 1;
                    if (b == this.base) {
                        if (U.compareAndSwapObject(a, offset, t, null)) {
                            this.base = b2;
                            t.doExec();
                            return 1;
                        }
                    }
                    return 2;
                }
            }
            return b | Integer.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public final boolean isApparentlyUnblocked() {
            Thread wt;
            Thread.State s;
            return (this.scanState < 0 || (wt = this.owner) == null || (s = wt.getState()) == Thread.State.BLOCKED || s == Thread.State.WAITING || s == Thread.State.TIMED_WAITING) ? false : true;
        }

        static {
            try {
                QLOCK = U.objectFieldOffset(WorkQueue.class.getDeclaredField("qlock"));
                ABASE = U.arrayBaseOffset(ForkJoinTask[].class);
                int scale = U.arrayIndexScale(ForkJoinTask[].class);
                if (((scale - 1) & scale) == 0) {
                    ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
                    return;
                }
                throw new Error("array index scale not a power of two");
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    private static final synchronized int nextPoolId() {
        int i;
        synchronized (ForkJoinPool.class) {
            i = poolNumberSequence + 1;
            poolNumberSequence = i;
        }
        return i;
    }

    private void tryInitialize(boolean checkTermination) {
        if (this.runState == 0) {
            int p = this.config & 65535;
            int n = p > 1 ? p - 1 : 1;
            int n2 = n | (n >>> 1);
            int n3 = n2 | (n2 >>> 2);
            int n4 = n3 | (n3 >>> 4);
            int n5 = n4 | (n4 >>> 8);
            AuxState aux = new AuxState();
            WorkQueue[] ws = new WorkQueue[(65535 & (((n5 | (n5 >>> 16)) + 1) << 1))];
            synchronized (modifyThreadPermission) {
                if (this.runState == 0) {
                    this.workQueues = ws;
                    this.auxState = aux;
                    this.runState = 1;
                }
            }
        }
        if (checkTermination && this.runState < 0) {
            tryTerminate(false, false);
            throw new RejectedExecutionException();
        }
    }

    private boolean createWorker(boolean isSpare) {
        WorkQueue q;
        ForkJoinWorkerThreadFactory fac = this.factory;
        Throwable ex = null;
        ForkJoinWorkerThread wt = null;
        if (fac != null) {
            try {
                ForkJoinWorkerThread newThread = fac.newThread(this);
                wt = newThread;
                if (newThread != null) {
                    if (isSpare && (q = wt.workQueue) != null) {
                        q.config |= 131072;
                    }
                    wt.start();
                    return true;
                }
            } catch (Throwable rex) {
                ex = rex;
            }
        }
        deregisterWorker(wt, ex);
        return false;
    }

    private void tryAddWorker(long c) {
        do {
            long nc = (AC_MASK & (AC_UNIT + c)) | (TC_MASK & (TC_UNIT + c));
            if (this.ctl != c || !U.compareAndSwapLong(this, CTL, c, nc)) {
                long j = this.ctl;
                c = j;
                if ((j & ADD_WORKER) == 0) {
                    return;
                }
            } else {
                createWorker(false);
                return;
            }
        } while (((int) c) == 0);
    }

    /* access modifiers changed from: package-private */
    public final WorkQueue registerWorker(ForkJoinWorkerThread wt) {
        wt.setDaemon(true);
        Thread.UncaughtExceptionHandler handler = this.ueh;
        if (handler != null) {
            wt.setUncaughtExceptionHandler(handler);
        }
        WorkQueue w = new WorkQueue(this, wt);
        int i = 0;
        int mode = this.config & MODE_MASK;
        AuxState aux = this.auxState;
        if (aux != null) {
            aux.lock();
            try {
                long j = aux.indexSeed - 1640531527;
                aux.indexSeed = j;
                int s = (int) j;
                WorkQueue[] ws = this.workQueues;
                if (ws != null) {
                    int length = ws.length;
                    int n = length;
                    if (length > 0) {
                        int i2 = n - 1;
                        int m = i2;
                        i = i2 & (1 | (s << 1));
                        if (ws[i] != null) {
                            int probes = 0;
                            int step = 2;
                            if (n > 4) {
                                step = 2 + ((n >>> 1) & EVENMASK);
                            }
                            while (true) {
                                int i3 = (i + step) & m;
                                i = i3;
                                if (ws[i3] == null) {
                                    break;
                                }
                                probes++;
                                if (probes >= n) {
                                    int i4 = n << 1;
                                    n = i4;
                                    WorkQueue[] workQueueArr = (WorkQueue[]) Arrays.copyOf(ws, i4);
                                    ws = workQueueArr;
                                    this.workQueues = workQueueArr;
                                    m = n - 1;
                                    probes = 0;
                                }
                            }
                        }
                        w.hint = s;
                        w.config = i | mode;
                        w.scanState = (2147418112 & s) | i;
                        ws[i] = w;
                    }
                }
            } finally {
                aux.unlock();
            }
        }
        wt.setName(this.workerNamePrefix.concat(Integer.toString(i >>> 1)));
        return w;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00aa A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void deregisterWorker(java.util.concurrent.ForkJoinWorkerThread r19, java.lang.Throwable r20) {
        /*
        // Method dump skipped, instructions count: 197
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.deregisterWorker(java.util.concurrent.ForkJoinWorkerThread, java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public final void signalWork() {
        int i;
        WorkQueue v;
        while (true) {
            long c = this.ctl;
            if (c < 0) {
                int sp = (int) c;
                if (sp != 0) {
                    WorkQueue[] ws = this.workQueues;
                    if (ws != null && ws.length > (i = 65535 & sp) && (v = ws[i]) != null) {
                        int ns = sp & Integer.MAX_VALUE;
                        int vs = v.scanState;
                        long nc = (((long) v.stackPred) & SP_MASK) | (UC_MASK & (AC_UNIT + c));
                        if (sp == vs) {
                            if (U.compareAndSwapLong(this, CTL, c, nc)) {
                                v.scanState = ns;
                                LockSupport.unpark(v.parker);
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } else if ((ADD_WORKER & c) != 0) {
                    tryAddWorker(c);
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private boolean tryRelease(long c, WorkQueue v, long inc) {
        int sp = (int) c;
        int ns = sp & Integer.MAX_VALUE;
        if (v == null) {
            return false;
        }
        int vs = v.scanState;
        long nc = (((long) v.stackPred) & SP_MASK) | (UC_MASK & (c + inc));
        if (sp != vs || !U.compareAndSwapLong(this, CTL, c, nc)) {
            return false;
        }
        v.scanState = ns;
        LockSupport.unpark(v.parker);
        return true;
    }

    private void tryReactivate(WorkQueue w, WorkQueue[] ws, int r) {
        int wl;
        WorkQueue v;
        long c = this.ctl;
        int sp = (int) c;
        if (sp != 0 && w != null && ws != null && (wl = ws.length) > 0 && ((sp ^ r) & 65536) == 0 && (v = ws[(wl - 1) & sp]) != null) {
            long nc = (((long) v.stackPred) & SP_MASK) | (UC_MASK & (AC_UNIT + c));
            int ns = sp & Integer.MAX_VALUE;
            if (w.scanState < 0 && v.scanState == sp) {
                if (U.compareAndSwapLong(this, CTL, c, nc)) {
                    v.scanState = ns;
                    LockSupport.unpark(v.parker);
                }
            }
        }
    }

    private void inactivate(WorkQueue w, int ss) {
        long c;
        int ns = (ss + 65536) | Integer.MIN_VALUE;
        long lc = ((long) ns) & SP_MASK;
        if (w != null) {
            w.scanState = ns;
            do {
                c = this.ctl;
                w.stackPred = (int) c;
            } while (!U.compareAndSwapLong(this, CTL, c, (UC_MASK & (c - AC_UNIT)) | lc));
        }
    }

    private int awaitWork(WorkQueue w) {
        if (w == null || w.scanState >= 0) {
            return 0;
        }
        long c = this.ctl;
        if (((int) (c >> 48)) + (this.config & 65535) <= 0) {
            return timedAwaitWork(w, c);
        }
        if ((this.runState & 2) != 0) {
            w.qlock = -1;
            return -1;
        } else if (w.scanState >= 0) {
            return 0;
        } else {
            w.parker = Thread.currentThread();
            if (w.scanState < 0) {
                LockSupport.park(this);
            }
            w.parker = null;
            if ((this.runState & 2) != 0) {
                w.qlock = -1;
                return -1;
            } else if (w.scanState >= 0) {
                return 0;
            } else {
                Thread.interrupted();
                return 0;
            }
        }
    }

    private int timedAwaitWork(WorkQueue w, long c) {
        Throwable th;
        int stat;
        int stat2 = 0;
        int i = 1;
        int scale = 1 - ((short) ((int) (c >>> 32)));
        if (scale > 0) {
            i = scale;
        }
        long deadline = (((long) i) * IDLE_TIMEOUT_MS) + System.currentTimeMillis();
        if (this.runState < 0) {
            int tryTerminate = tryTerminate(false, false);
            stat2 = tryTerminate;
            if (tryTerminate <= 0) {
                return stat2;
            }
        }
        if (w != null && w.scanState < 0) {
            w.parker = Thread.currentThread();
            if (w.scanState < 0) {
                LockSupport.parkUntil(this, deadline);
            }
            w.parker = null;
            if ((this.runState & 2) != 0) {
                w.qlock = -1;
                return -1;
            }
            int ss = w.scanState;
            if (ss < 0 && !Thread.interrupted() && ((int) c) == ss) {
                AuxState aux = this.auxState;
                if (aux != null && this.ctl == c) {
                    if (deadline - System.currentTimeMillis() <= TIMEOUT_SLOP_MS) {
                        aux.lock();
                        try {
                            int cfg = w.config;
                            int idx = cfg & 65535;
                            long nc = (UC_MASK & (c - TC_UNIT)) | (SP_MASK & ((long) w.stackPred));
                            try {
                                if ((this.runState & 2) == 0) {
                                    WorkQueue[] ws = this.workQueues;
                                    if (ws != null && idx < ws.length && idx >= 0 && ws[idx] == w) {
                                        try {
                                            if (U.compareAndSwapLong(this, CTL, c, nc)) {
                                                ws[idx] = null;
                                                w.config = cfg | 262144;
                                                stat = -1;
                                                w.qlock = -1;
                                                aux.unlock();
                                                return stat;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            aux.unlock();
                                            throw th;
                                        }
                                    }
                                }
                                stat = stat2;
                                aux.unlock();
                                return stat;
                            } catch (Throwable th3) {
                                th = th3;
                                aux.unlock();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            aux.unlock();
                            throw th;
                        }
                    }
                }
            }
        }
        return stat2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean tryDropSpare(java.util.concurrent.ForkJoinPool.WorkQueue r20) {
        /*
        // Method dump skipped, instructions count: 229
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.tryDropSpare(java.util.concurrent.ForkJoinPool$WorkQueue):boolean");
    }

    /* access modifiers changed from: package-private */
    public final void runWorker(WorkQueue w) {
        w.growArray();
        int bound = (w.config & 131072) != 0 ? 0 : 1023;
        long seed = ((long) w.hint) * -2685821657736338717L;
        if ((this.runState & 2) == 0) {
            long r = seed == 0 ? 1 : seed;
            while (true) {
                if (bound != 0 || !tryDropSpare(w)) {
                    long r2 = r ^ (r >>> 12);
                    long r3 = r2 ^ (r2 << 25);
                    r = r3 ^ (r3 >>> 27);
                    if (scan(w, bound, ((int) (r >>> 48)) | 1, (int) r) < 0 && awaitWork(w) < 0) {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private int scan(WorkQueue w, int bound, int step, int r) {
        int stat;
        int m;
        int stat2;
        int wl;
        int origin;
        int origin2;
        int stat3 = 0;
        WorkQueue[] ws = this.workQueues;
        if (ws == null || w == null) {
            stat = 0;
        } else {
            int length = ws.length;
            int origin3 = length;
            if (length > 0) {
                int m2 = origin3 - 1;
                int origin4 = m2 & r;
                int idx = origin4;
                int npolls = 0;
                int ss = w.scanState;
                int r2 = r;
                while (true) {
                    WorkQueue q = ws[idx];
                    if (q != null) {
                        int b = q.base;
                        if (b - q.top < 0) {
                            ForkJoinTask<?>[] a = q.array;
                            if (a != null) {
                                int al = a.length;
                                if (al > 0) {
                                    wl = origin3;
                                    stat2 = stat3;
                                    m = m2;
                                    long offset = ((long) ABASE) + (((long) ((al - 1) & b)) << ASHIFT);
                                    ForkJoinTask<?> t = (ForkJoinTask) U.getObjectVolatile(a, offset);
                                    if (t == null) {
                                        return stat2;
                                    }
                                    int b2 = b + 1;
                                    if (b != q.base) {
                                        return stat2;
                                    }
                                    if (ss < 0) {
                                        tryReactivate(w, ws, r2);
                                        return stat2;
                                    } else if (!U.compareAndSwapObject(a, offset, t, null)) {
                                        return stat2;
                                    } else {
                                        q.base = b2;
                                        w.currentSteal = t;
                                        if (b2 != q.top) {
                                            signalWork();
                                        }
                                        w.runTask(t);
                                        npolls++;
                                        if (npolls > bound) {
                                            return stat2;
                                        }
                                        origin = origin4;
                                        origin4 = origin;
                                        origin3 = wl;
                                        stat3 = stat2;
                                        m2 = m;
                                    }
                                } else {
                                    stat2 = stat3;
                                    m = m2;
                                    wl = origin3;
                                    origin2 = origin4;
                                }
                            } else {
                                stat2 = stat3;
                                m = m2;
                                wl = origin3;
                                origin2 = origin4;
                            }
                        } else {
                            stat2 = stat3;
                            m = m2;
                            wl = origin3;
                            origin2 = origin4;
                        }
                    } else {
                        stat2 = stat3;
                        m = m2;
                        wl = origin3;
                        origin2 = origin4;
                    }
                    if (npolls != 0) {
                        return stat2;
                    }
                    int idx2 = (idx + step) & m;
                    origin = origin2;
                    if (idx2 != origin) {
                        idx = idx2;
                    } else if (ss < 0) {
                        return ss;
                    } else {
                        if (r2 >= 0) {
                            inactivate(w, ss);
                            return stat2;
                        }
                        r2 <<= 1;
                        idx = idx2;
                    }
                    origin4 = origin;
                    origin3 = wl;
                    stat3 = stat2;
                    m2 = m;
                }
            } else {
                stat = 0;
            }
        }
        return stat;
    }

    /* JADX INFO: Multiple debug info for r7v4 int: [D('i' int), D('origin' int)] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int helpComplete(java.util.concurrent.ForkJoinPool.WorkQueue r21, java.util.concurrent.CountedCompleter<?> r22, int r23) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.helpComplete(java.util.concurrent.ForkJoinPool$WorkQueue, java.util.concurrent.CountedCompleter, int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        r9.hint = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        if (r10.status >= 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        r11 = r13.base;
        r8 = r8 + r11;
        r11 = r13.currentJoin;
        r13 = null;
        r14 = r13.array;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (r14 == null) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        r4 = r14.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005d, code lost:
        if (r4 <= 0) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        r23 = r6;
        r24 = r7;
        r26 = r5;
        r4 = ((long) java.util.concurrent.ForkJoinPool.ABASE) + (((long) ((r4 - 1) & r11)) << java.util.concurrent.ForkJoinPool.ASHIFT);
        r6 = (java.util.concurrent.ForkJoinTask) java.util.concurrent.ForkJoinPool.U.getObjectVolatile(r14, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007b, code lost:
        if (r6 == null) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007d, code lost:
        r7 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0081, code lost:
        if (r11 != r13.base) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0085, code lost:
        if (r9.currentJoin != r10) goto L_0x000b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0089, code lost:
        if (r13.currentSteal != r10) goto L_0x000b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008d, code lost:
        if (r10.status >= 0) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009f, code lost:
        if (java.util.concurrent.ForkJoinPool.U.compareAndSwapObject(r14, r4, r6, null) == false) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a1, code lost:
        r13.base = r7;
        r28.currentSteal = r6;
        r12 = r28.top;
        r13 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a8, code lost:
        r13.doExec();
        r28.currentSteal = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00af, code lost:
        if (r29.status >= 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b5, code lost:
        if (r28.top != r12) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b8, code lost:
        r6 = r28.pop();
        r13 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bd, code lost:
        if (r6 != null) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c1, code lost:
        r28.currentSteal = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c4, code lost:
        r13 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c6, code lost:
        r13 = r6;
        r7 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c9, code lost:
        r26 = r5;
        r23 = r6;
        r24 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d0, code lost:
        r26 = r5;
        r23 = r6;
        r24 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d8, code lost:
        r7 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d9, code lost:
        if (r13 != null) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00dd, code lost:
        if (r7 != r13.base) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e3, code lost:
        if ((r7 - r13.top) < 0) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e5, code lost:
        r10 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e6, code lost:
        if (r11 != null) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00ea, code lost:
        if (r11 != r13.currentJoin) goto L_0x000b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ed, code lost:
        if (r3 != r8) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f0, code lost:
        r3 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00fd, code lost:
        r6 = r23;
        r7 = r24;
        r5 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x000b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x000b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x000b, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x000b, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void helpStealer(java.util.concurrent.ForkJoinPool.WorkQueue r28, java.util.concurrent.ForkJoinTask<?> r29) {
        /*
        // Method dump skipped, instructions count: 310
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.helpStealer(java.util.concurrent.ForkJoinPool$WorkQueue, java.util.concurrent.ForkJoinTask):void");
    }

    private boolean tryCompensate(WorkQueue w) {
        int wl;
        boolean busy;
        WorkQueue v;
        long c = this.ctl;
        WorkQueue[] ws = this.workQueues;
        int pc = this.config & 65535;
        int ac = pc + ((int) (c >> 48));
        int tc = pc + ((short) ((int) (c >> 32)));
        if (w == null || w.qlock < 0 || pc == 0 || ws == null || (wl = ws.length) <= 0) {
            return false;
        }
        int m = wl - 1;
        int i = 0;
        while (true) {
            if (i > m) {
                busy = true;
                break;
            }
            int k = (i << 1) | 1;
            if (k <= m && k >= 0 && (v = ws[k]) != null && v.scanState >= 0 && v.currentSteal == null) {
                busy = false;
                break;
            }
            i++;
        }
        if (busy) {
            if (this.ctl == c) {
                int sp = (int) c;
                if (sp != 0) {
                    return tryRelease(c, ws[m & sp], 0);
                }
                if (tc >= pc && ac > 1 && w.isEmpty()) {
                    return U.compareAndSwapLong(this, CTL, c, (AC_MASK & (c - AC_UNIT)) | (281474976710655L & c));
                }
                if (tc >= MAX_CAP || (this == common && tc >= COMMON_MAX_SPARES + pc)) {
                    throw new RejectedExecutionException("Thread limit exceeded replacing blocked worker");
                }
                boolean canBlock = false;
                boolean isSpare = tc >= pc;
                if (U.compareAndSwapLong(this, CTL, c, (AC_MASK & c) | (TC_MASK & (TC_UNIT + c))) && createWorker(isSpare)) {
                    canBlock = true;
                }
                return canBlock;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int awaitJoin(WorkQueue w, ForkJoinTask<?> task, long deadline) {
        long ms;
        int i;
        int s = 0;
        if (w != null) {
            ForkJoinTask<?> prevJoin = w.currentJoin;
            if (task != null) {
                int i2 = task.status;
                s = i2;
                if (i2 >= 0) {
                    w.currentJoin = task;
                    CountedCompleter<?> cc = task instanceof CountedCompleter ? (CountedCompleter) task : null;
                    do {
                        if (cc != null) {
                            helpComplete(w, cc, 0);
                        } else {
                            helpStealer(w, task);
                        }
                        int i3 = task.status;
                        s = i3;
                        if (i3 < 0) {
                            break;
                        }
                        if (deadline == 0) {
                            ms = 0;
                        } else {
                            long ns = deadline - System.nanoTime();
                            if (ns <= 0) {
                                break;
                            }
                            long millis = TimeUnit.NANOSECONDS.toMillis(ns);
                            ms = millis;
                            if (millis <= 0) {
                                ms = 1;
                            }
                        }
                        if (tryCompensate(w)) {
                            task.internalWait(ms);
                            U.getAndAddLong(this, CTL, AC_UNIT);
                        }
                        i = task.status;
                        s = i;
                    } while (i >= 0);
                    w.currentJoin = prevJoin;
                }
            }
        }
        return s;
    }

    private WorkQueue findNonEmptyStealQueue() {
        int wl;
        int r = ThreadLocalRandom.nextSecondarySeed();
        WorkQueue[] ws = this.workQueues;
        if (ws == null || (wl = ws.length) <= 0) {
            return null;
        }
        int m = wl - 1;
        int origin = r & m;
        int k = origin;
        int oldSum = 0;
        int checkSum = 0;
        while (true) {
            WorkQueue q = ws[k];
            if (q != null) {
                int b = q.base;
                if (b - q.top < 0) {
                    return q;
                }
                checkSum += b;
            }
            int i = (k + 1) & m;
            k = i;
            if (i == origin) {
                if (oldSum == checkSum) {
                    return null;
                }
                checkSum = 0;
                oldSum = checkSum;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void helpQuiescePool(WorkQueue w) {
        ForkJoinTask<?> ps = w.currentSteal;
        int wc = w.config;
        boolean active = true;
        while (true) {
            if (wc >= 0) {
                ForkJoinTask<?> t = w.pop();
                if (t != null) {
                    w.currentSteal = t;
                    t.doExec();
                    w.currentSteal = ps;
                }
            }
            WorkQueue q = findNonEmptyStealQueue();
            if (q != null) {
                if (!active) {
                    active = true;
                    U.getAndAddLong(this, CTL, AC_UNIT);
                }
                ForkJoinTask<?> t2 = q.pollAt(q.base);
                if (t2 != null) {
                    w.currentSteal = t2;
                    t2.doExec();
                    w.currentSteal = ps;
                    int i = w.nsteals + 1;
                    w.nsteals = i;
                    if (i < 0) {
                        w.transferStealCount(this);
                    }
                }
            } else if (active) {
                long c = this.ctl;
                if (U.compareAndSwapLong(this, CTL, c, ((c - AC_UNIT) & AC_MASK) | (281474976710655L & c))) {
                    active = false;
                }
            } else {
                long c2 = this.ctl;
                if (((int) (c2 >> 48)) + (this.config & 65535) <= 0 && U.compareAndSwapLong(this, CTL, c2, c2 + AC_UNIT)) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final ForkJoinTask<?> nextTaskFor(WorkQueue w) {
        ForkJoinTask<?> t;
        do {
            ForkJoinTask<?> t2 = w.nextLocalTask();
            if (t2 != null) {
                return t2;
            }
            WorkQueue q = findNonEmptyStealQueue();
            if (q == null) {
                return null;
            }
            t = q.pollAt(q.base);
        } while (t == null);
        return t;
    }

    static int getSurplusQueuedTaskCount() {
        Thread t = Thread.currentThread();
        int i = 0;
        if (!(t instanceof ForkJoinWorkerThread)) {
            return 0;
        }
        ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
        ForkJoinPool pool = wt.pool;
        int p = pool.config & 65535;
        WorkQueue q = wt.workQueue;
        int n = q.top - q.base;
        int a = ((int) (pool.ctl >> 48)) + p;
        int p2 = p >>> 1;
        if (a <= p2) {
            int p3 = p2 >>> 1;
            if (a > p3) {
                i = 1;
            } else {
                int p4 = p3 >>> 1;
                if (a > p4) {
                    i = 2;
                } else if (a > (p4 >>> 1)) {
                    i = 4;
                } else {
                    i = 8;
                }
            }
        }
        return n - i;
    }

    private int tryTerminate(boolean now, boolean enable) {
        Unsafe unsafe;
        long j;
        int rs;
        while (true) {
            int rs2 = this.runState;
            if (rs2 < 0) {
                if ((rs2 & 2) != 0) {
                    long oldSum = 0;
                } else {
                    if (!now) {
                        long oldSum2 = 0;
                        loop1:
                        while (true) {
                            long checkSum = this.ctl;
                            if (((int) (checkSum >> 48)) + (this.config & 65535) > 0) {
                                return 0;
                            }
                            WorkQueue[] ws = this.workQueues;
                            if (ws != null) {
                                for (WorkQueue w : ws) {
                                    if (w != null) {
                                        int b = w.base;
                                        checkSum += (long) b;
                                        if (!(w.currentSteal == null && b == w.top)) {
                                            return 0;
                                        }
                                    }
                                }
                            }
                            if (oldSum2 == checkSum) {
                                break;
                            }
                            oldSum2 = checkSum;
                        }
                    }
                    do {
                        unsafe = U;
                        j = RUNSTATE;
                        rs = this.runState;
                    } while (!unsafe.compareAndSwapInt(this, j, rs, rs | 2));
                }
                long oldSum3 = 0;
                while (true) {
                    long checkSum2 = this.ctl;
                    WorkQueue[] ws2 = this.workQueues;
                    if (ws2 != null) {
                        long checkSum3 = checkSum2;
                        for (WorkQueue w2 : ws2) {
                            if (w2 != null) {
                                w2.cancelAll();
                                checkSum3 += (long) w2.base;
                                if (w2.qlock >= 0) {
                                    w2.qlock = -1;
                                    ForkJoinWorkerThread wt = w2.owner;
                                    if (wt != null) {
                                        try {
                                            wt.interrupt();
                                        } catch (Throwable th) {
                                        }
                                    }
                                }
                            }
                        }
                        checkSum2 = checkSum3;
                    }
                    if (oldSum3 == checkSum2) {
                        break;
                    }
                    oldSum3 = checkSum2;
                }
                if (((short) ((int) (this.ctl >>> 32))) + (this.config & 65535) <= 0) {
                    this.runState = -2147483641;
                    synchronized (this) {
                        notifyAll();
                    }
                }
                return -1;
            } else if (!enable || this == common) {
                return 1;
            } else {
                if (rs2 == 0) {
                    tryInitialize(false);
                } else {
                    U.compareAndSwapInt(this, RUNSTATE, rs2, rs2 | Integer.MIN_VALUE);
                }
            }
        }
    }

    private void tryCreateExternalQueue(int index) {
        AuxState aux = this.auxState;
        if (aux != null && index >= 0) {
            WorkQueue q = new WorkQueue(this, null);
            q.config = index;
            q.scanState = Integer.MAX_VALUE;
            q.qlock = 1;
            boolean installed = false;
            aux.lock();
            try {
                WorkQueue[] ws = this.workQueues;
                if (ws != null && index < ws.length && ws[index] == null) {
                    ws[index] = q;
                    installed = true;
                }
                if (installed) {
                    try {
                        q.growArray();
                    } finally {
                        q.qlock = 0;
                    }
                }
            } finally {
                aux.unlock();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void externalPush(ForkJoinTask<?> task) {
        int wl;
        int probe = ThreadLocalRandom.getProbe();
        int r = probe;
        if (probe == 0) {
            ThreadLocalRandom.localInit();
            r = ThreadLocalRandom.getProbe();
        }
        while (true) {
            int rs = this.runState;
            WorkQueue[] ws = this.workQueues;
            if (rs <= 0 || ws == null || (wl = ws.length) <= 0) {
                tryInitialize(true);
            } else {
                int k = (wl - 1) & r & 126;
                WorkQueue q = ws[k];
                if (q == null) {
                    tryCreateExternalQueue(k);
                } else {
                    int stat = q.sharedPush(task);
                    if (stat >= 0) {
                        if (stat == 0) {
                            signalWork();
                            return;
                        }
                        r = ThreadLocalRandom.advanceProbe(r);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private <T> ForkJoinTask<T> externalSubmit(ForkJoinTask<T> task) {
        WorkQueue q;
        if (task != null) {
            Thread t = Thread.currentThread();
            if (t instanceof ForkJoinWorkerThread) {
                ForkJoinWorkerThread w = (ForkJoinWorkerThread) t;
                if (w.pool == this && (q = w.workQueue) != null) {
                    q.push(task);
                    return task;
                }
            }
            externalPush(task);
            return task;
        }
        throw new NullPointerException();
    }

    static WorkQueue commonSubmitterQueue() {
        WorkQueue[] ws;
        int wl;
        ForkJoinPool p = common;
        int r = ThreadLocalRandom.getProbe();
        if (p == null || (ws = p.workQueues) == null || (wl = ws.length) <= 0) {
            return null;
        }
        return ws[(wl - 1) & r & 126];
    }

    /* access modifiers changed from: package-private */
    public final boolean tryExternalUnpush(ForkJoinTask<?> task) {
        int wl;
        WorkQueue w;
        int r = ThreadLocalRandom.getProbe();
        WorkQueue[] ws = this.workQueues;
        return ws != null && (wl = ws.length) > 0 && (w = ws[((wl + -1) & r) & 126]) != null && w.trySharedUnpush(task);
    }

    /* access modifiers changed from: package-private */
    public final int externalHelpComplete(CountedCompleter<?> task, int maxTasks) {
        int wl;
        int r = ThreadLocalRandom.getProbe();
        WorkQueue[] ws = this.workQueues;
        if (ws == null || (wl = ws.length) <= 0) {
            return 0;
        }
        return helpComplete(ws[(wl - 1) & r & 126], task, maxTasks);
    }

    public ForkJoinPool() {
        this(Math.min((int) MAX_CAP, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, null, false);
    }

    public ForkJoinPool(int parallelism) {
        this(parallelism, defaultForkJoinWorkerThreadFactory, null, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ForkJoinPool(int r7, java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory r8, java.lang.Thread.UncaughtExceptionHandler r9, boolean r10) {
        /*
            r6 = this;
            int r1 = checkParallelism(r7)
            java.util.concurrent.ForkJoinPool$ForkJoinWorkerThreadFactory r2 = checkFactory(r8)
            if (r10 == 0) goto L_0x000d
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            r4 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "ForkJoinPool-"
            r0.append(r3)
            int r3 = nextPoolId()
            r0.append(r3)
            java.lang.String r3 = "-worker-"
            r0.append(r3)
            java.lang.String r5 = r0.toString()
            r0 = r6
            r3 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            checkPermission()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.<init>(int, java.util.concurrent.ForkJoinPool$ForkJoinWorkerThreadFactory, java.lang.Thread$UncaughtExceptionHandler, boolean):void");
    }

    private static int checkParallelism(int parallelism) {
        if (parallelism > 0 && parallelism <= MAX_CAP) {
            return parallelism;
        }
        throw new IllegalArgumentException();
    }

    private static ForkJoinWorkerThreadFactory checkFactory(ForkJoinWorkerThreadFactory factory2) {
        if (factory2 != null) {
            return factory2;
        }
        throw new NullPointerException();
    }

    private ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory2, Thread.UncaughtExceptionHandler handler, int mode, String workerNamePrefix2) {
        this.workerNamePrefix = workerNamePrefix2;
        this.factory = factory2;
        this.ueh = handler;
        this.config = (65535 & parallelism) | mode;
        long np = (long) (-parallelism);
        this.ctl = ((np << 48) & AC_MASK) | ((np << 32) & TC_MASK);
    }

    public static ForkJoinPool commonPool() {
        return common;
    }

    public <T> T invoke(ForkJoinTask<T> task) {
        if (task != null) {
            externalSubmit(task);
            return task.join();
        }
        throw new NullPointerException();
    }

    public void execute(ForkJoinTask<?> task) {
        externalSubmit(task);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable task) {
        ForkJoinTask<?> job;
        if (task != null) {
            if (task instanceof ForkJoinTask) {
                job = (ForkJoinTask) task;
            } else {
                job = new ForkJoinTask.RunnableExecuteAction(task);
            }
            externalSubmit(job);
            return;
        }
        throw new NullPointerException();
    }

    public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task) {
        return externalSubmit(task);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ForkJoinTask<T> submit(Callable<T> task) {
        return externalSubmit(new ForkJoinTask.AdaptedCallable(task));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> ForkJoinTask<T> submit(Runnable task, T result) {
        return externalSubmit(new ForkJoinTask.AdaptedRunnable(task, result));
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public ForkJoinTask<?> submit(Runnable task) {
        ForkJoinTask<?> job;
        if (task != null) {
            if (task instanceof ForkJoinTask) {
                job = (ForkJoinTask) task;
            } else {
                job = new ForkJoinTask.AdaptedRunnableAction(task);
            }
            return externalSubmit(job);
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
        ArrayList<Future<T>> futures = new ArrayList<>(tasks.size());
        try {
            for (Callable<T> t : tasks) {
                ForkJoinTask.AdaptedCallable adaptedCallable = new ForkJoinTask.AdaptedCallable(t);
                futures.add(adaptedCallable);
                externalSubmit(adaptedCallable);
            }
            int size = futures.size();
            for (int i = 0; i < size; i++) {
                ((ForkJoinTask) futures.get(i)).quietlyJoin();
            }
            return futures;
        } catch (Throwable t2) {
            int size2 = futures.size();
            for (int i2 = 0; i2 < size2; i2++) {
                futures.get(i2).cancel(false);
            }
            throw t2;
        }
    }

    public ForkJoinWorkerThreadFactory getFactory() {
        return this.factory;
    }

    public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.ueh;
    }

    public int getParallelism() {
        int par = this.config & 65535;
        if (par > 0) {
            return par;
        }
        return 1;
    }

    public static int getCommonPoolParallelism() {
        return COMMON_PARALLELISM;
    }

    public int getPoolSize() {
        return (this.config & 65535) + ((short) ((int) (this.ctl >>> 32)));
    }

    public boolean getAsyncMode() {
        return (this.config & Integer.MIN_VALUE) != 0;
    }

    public int getRunningThreadCount() {
        int rc = 0;
        WorkQueue[] ws = this.workQueues;
        if (ws != null) {
            for (int i = 1; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w != null && w.isApparentlyUnblocked()) {
                    rc++;
                }
            }
        }
        return rc;
    }

    public int getActiveThreadCount() {
        int r = (this.config & 65535) + ((int) (this.ctl >> 48));
        if (r <= 0) {
            return 0;
        }
        return r;
    }

    public boolean isQuiescent() {
        return (this.config & 65535) + ((int) (this.ctl >> 48)) <= 0;
    }

    public long getStealCount() {
        AuxState sc = this.auxState;
        long count = sc == null ? 0 : sc.stealCount;
        WorkQueue[] ws = this.workQueues;
        if (ws != null) {
            for (int i = 1; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w != null) {
                    count += (long) w.nsteals;
                }
            }
        }
        return count;
    }

    public long getQueuedTaskCount() {
        long count = 0;
        WorkQueue[] ws = this.workQueues;
        if (ws != null) {
            for (int i = 1; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w != null) {
                    count += (long) w.queueSize();
                }
            }
        }
        return count;
    }

    public int getQueuedSubmissionCount() {
        int count = 0;
        WorkQueue[] ws = this.workQueues;
        if (ws != null) {
            for (int i = 0; i < ws.length; i += 2) {
                WorkQueue w = ws[i];
                if (w != null) {
                    count += w.queueSize();
                }
            }
        }
        return count;
    }

    public boolean hasQueuedSubmissions() {
        WorkQueue[] ws = this.workQueues;
        if (ws == null) {
            return false;
        }
        for (int i = 0; i < ws.length; i += 2) {
            WorkQueue w = ws[i];
            if (!(w == null || w.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ForkJoinTask<?> pollSubmission() {
        int wl;
        ForkJoinTask<?> t;
        ThreadLocalRandom.nextSecondarySeed();
        WorkQueue[] ws = this.workQueues;
        if (ws == null || (wl = ws.length) <= 0) {
            return null;
        }
        int m = wl - 1;
        for (int i = 0; i < wl; i++) {
            WorkQueue w = ws[(i << 1) & m];
            if (!(w == null || (t = w.poll()) == null)) {
                return t;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int drainTasksTo(Collection<? super ForkJoinTask<?>> c) {
        int count = 0;
        WorkQueue[] ws = this.workQueues;
        if (ws != null) {
            for (WorkQueue w : ws) {
                if (w != null) {
                    while (true) {
                        ForkJoinTask<?> t = w.poll();
                        if (t == null) {
                            break;
                        }
                        c.add(t);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public String toString() {
        long c;
        String level;
        long c2;
        long qt = 0;
        long qs = 0;
        int rc = 0;
        AuxState sc = this.auxState;
        long st = sc == null ? 0 : sc.stealCount;
        long c3 = this.ctl;
        WorkQueue[] ws = this.workQueues;
        if (ws != null) {
            int i = 0;
            while (i < ws.length) {
                WorkQueue w = ws[i];
                if (w != null) {
                    int size = w.queueSize();
                    if ((i & 1) == 0) {
                        c2 = c3;
                        qs += (long) size;
                    } else {
                        c2 = c3;
                        qt += (long) size;
                        st += (long) w.nsteals;
                        if (w.isApparentlyUnblocked()) {
                            rc++;
                        }
                    }
                } else {
                    c2 = c3;
                }
                i++;
                c3 = c2;
            }
            c = c3;
        } else {
            c = c3;
        }
        int pc = this.config & 65535;
        int tc = ((short) ((int) (c >>> 32))) + pc;
        int ac = ((int) (c >> 48)) + pc;
        if (ac < 0) {
            ac = 0;
        }
        int rs = this.runState;
        if ((rs & 4) != 0) {
            level = "Terminated";
        } else if ((rs & 2) != 0) {
            level = "Terminating";
        } else if ((Integer.MIN_VALUE & rs) != 0) {
            level = "Shutting down";
        } else {
            level = "Running";
        }
        return super.toString() + "[" + level + ", parallelism = " + pc + ", size = " + tc + ", active = " + ac + ", running = " + rc + ", steals = " + st + ", tasks = " + qt + ", submissions = " + qs + "]";
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        checkPermission();
        tryTerminate(false, true);
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        checkPermission();
        tryTerminate(true, true);
        return Collections.emptyList();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return (this.runState & 4) != 0;
    }

    public boolean isTerminating() {
        int rs = this.runState;
        return (rs & 2) != 0 && (rs & 4) == 0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return (this.runState & Integer.MIN_VALUE) != 0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        if (!Thread.interrupted()) {
            boolean z = false;
            if (this == common) {
                awaitQuiescence(timeout, unit);
                return false;
            }
            long nanos = unit.toNanos(timeout);
            if (isTerminated()) {
                return true;
            }
            if (nanos <= 0) {
                return false;
            }
            long deadline = System.nanoTime() + nanos;
            synchronized (this) {
                while (!isTerminated()) {
                    if (nanos <= 0) {
                        return z;
                    }
                    long millis = TimeUnit.NANOSECONDS.toMillis(nanos);
                    wait(millis > 0 ? millis : 1);
                    nanos = deadline - System.nanoTime();
                    z = false;
                }
                return true;
            }
        }
        throw new InterruptedException();
    }

    /* JADX INFO: Multiple debug info for r7v5 int: [D('k' int), D('r' int)] */
    public boolean awaitQuiescence(long timeout, TimeUnit unit) {
        WorkQueue[] ws;
        int wl;
        ForkJoinPool forkJoinPool = this;
        long nanos = unit.toNanos(timeout);
        Thread thread = Thread.currentThread();
        if (thread instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread wt = (ForkJoinWorkerThread) thread;
            if (wt.pool == forkJoinPool) {
                forkJoinPool.helpQuiescePool(wt.workQueue);
                return true;
            }
        }
        long startTime = System.nanoTime();
        int r = 0;
        boolean found = true;
        while (!isQuiescent() && (ws = forkJoinPool.workQueues) != null && (wl = ws.length) > 0) {
            if (!found) {
                if (System.nanoTime() - startTime > nanos) {
                    return false;
                }
                Thread.yield();
            }
            found = false;
            int m = wl - 1;
            int j = (m + 1) << 2;
            while (true) {
                if (j < 0) {
                    break;
                }
                int r2 = r + 1;
                int r3 = r & m;
                if (r3 <= m && r3 >= 0) {
                    WorkQueue q = ws[r3];
                    if (q != null) {
                        int b = q.base;
                        if (b - q.top < 0) {
                            ForkJoinTask<?> t = q.pollAt(b);
                            if (t != null) {
                                t.doExec();
                            }
                            found = true;
                            r = r2;
                        }
                    }
                }
                j--;
                r = r2;
            }
            forkJoinPool = this;
        }
        return true;
    }

    static void quiesceCommonPool() {
        common.awaitQuiescence(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void managedBlock(java.util.concurrent.ForkJoinPool.ManagedBlocker r11) throws java.lang.InterruptedException {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof java.util.concurrent.ForkJoinWorkerThread
            if (r1 == 0) goto L_0x0045
            r1 = r0
            java.util.concurrent.ForkJoinWorkerThread r1 = (java.util.concurrent.ForkJoinWorkerThread) r1
            r2 = r1
            java.util.concurrent.ForkJoinPool r1 = r1.pool
            r9 = r1
            if (r1 == 0) goto L_0x0045
            java.util.concurrent.ForkJoinPool$WorkQueue r1 = r2.workQueue
        L_0x0013:
            boolean r3 = r11.isReleasable()
            if (r3 != 0) goto L_0x0044
            boolean r3 = r9.tryCompensate(r1)
            if (r3 == 0) goto L_0x0013
        L_0x001f:
            boolean r3 = r11.isReleasable()     // Catch:{ all -> 0x0037 }
            if (r3 != 0) goto L_0x002b
            boolean r3 = r11.block()     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x001f
        L_0x002b:
            sun.misc.Unsafe r3 = java.util.concurrent.ForkJoinPool.U
            long r5 = java.util.concurrent.ForkJoinPool.CTL
            r7 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            r4 = r9
            r3.getAndAddLong(r4, r5, r7)
            goto L_0x0044
        L_0x0037:
            r3 = move-exception
            r10 = r3
            sun.misc.Unsafe r3 = java.util.concurrent.ForkJoinPool.U
            long r5 = java.util.concurrent.ForkJoinPool.CTL
            r7 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            r4 = r9
            r3.getAndAddLong(r4, r5, r7)
            throw r10
        L_0x0044:
            goto L_0x0051
        L_0x0045:
            boolean r1 = r11.isReleasable()
            if (r1 != 0) goto L_0x0051
            boolean r1 = r11.block()
            if (r1 == 0) goto L_0x0045
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.managedBlock(java.util.concurrent.ForkJoinPool$ManagedBlocker):void");
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new ForkJoinTask.AdaptedRunnable(runnable, value);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new ForkJoinTask.AdaptedCallable(callable);
    }

    static {
        try {
            CTL = U.objectFieldOffset(ForkJoinPool.class.getDeclaredField("ctl"));
            RUNSTATE = U.objectFieldOffset(ForkJoinPool.class.getDeclaredField("runState"));
            ABASE = U.arrayBaseOffset(ForkJoinTask[].class);
            int scale = U.arrayIndexScale(ForkJoinTask[].class);
            if (((scale - 1) & scale) == 0) {
                ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
                int commonMaxSpares = 256;
                try {
                    String p = System.getProperty("java.util.concurrent.ForkJoinPool.common.maximumSpares");
                    if (p != null) {
                        commonMaxSpares = Integer.parseInt(p);
                    }
                } catch (Exception e) {
                }
                COMMON_MAX_SPARES = commonMaxSpares;
                return;
            }
            throw new Error("array index scale not a power of two");
        } catch (ReflectiveOperationException e2) {
            throw new Error(e2);
        }
    }

    static ForkJoinPool makeCommonPool() {
        Thread.UncaughtExceptionHandler handler;
        int parallelism = -1;
        ForkJoinWorkerThreadFactory factory2 = null;
        Thread.UncaughtExceptionHandler handler2 = null;
        try {
            String pp = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
            String fp = System.getProperty("java.util.concurrent.ForkJoinPool.common.threadFactory");
            String hp = System.getProperty("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
            if (pp != null) {
                parallelism = Integer.parseInt(pp);
            }
            if (fp != null) {
                factory2 = (ForkJoinWorkerThreadFactory) ClassLoader.getSystemClassLoader().loadClass(fp).newInstance();
            }
            if (hp != null) {
                handler2 = (Thread.UncaughtExceptionHandler) ClassLoader.getSystemClassLoader().loadClass(hp).newInstance();
            }
            handler = handler2;
        } catch (Exception e) {
            handler = null;
        }
        if (factory2 == null) {
            if (System.getSecurityManager() == null) {
                factory2 = defaultForkJoinWorkerThreadFactory;
            } else {
                factory2 = new InnocuousForkJoinWorkerThreadFactory();
            }
        }
        if (parallelism < 0) {
            int availableProcessors = Runtime.getRuntime().availableProcessors() - 1;
            parallelism = availableProcessors;
            if (availableProcessors <= 0) {
                parallelism = 1;
            }
        }
        if (parallelism > MAX_CAP) {
            parallelism = MAX_CAP;
        }
        return new ForkJoinPool(parallelism, factory2, handler, 0, "ForkJoinPool.commonPool-worker-");
    }

    /* access modifiers changed from: private */
    public static final class InnocuousForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
        private static final AccessControlContext innocuousAcc;

        private InnocuousForkJoinWorkerThreadFactory() {
        }

        static {
            Permissions innocuousPerms = new Permissions();
            innocuousPerms.add(ForkJoinPool.modifyThreadPermission);
            innocuousPerms.add(new RuntimePermission("enableContextClassLoaderOverride"));
            innocuousPerms.add(new RuntimePermission("modifyThreadGroup"));
            innocuousAcc = new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, innocuousPerms)});
        }

        @Override // java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
        public final ForkJoinWorkerThread newThread(final ForkJoinPool pool) {
            return (ForkJoinWorkerThread) AccessController.doPrivileged(new PrivilegedAction<ForkJoinWorkerThread>() {
                /* class java.util.concurrent.ForkJoinPool.InnocuousForkJoinWorkerThreadFactory.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public ForkJoinWorkerThread run() {
                    return new ForkJoinWorkerThread.InnocuousForkJoinWorkerThread(pool);
                }
            }, innocuousAcc);
        }
    }
}
