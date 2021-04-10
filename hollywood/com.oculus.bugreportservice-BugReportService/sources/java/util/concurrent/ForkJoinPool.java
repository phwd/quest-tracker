package java.util.concurrent;

import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public class ForkJoinPool extends AbstractExecutorService {
    private static final int ABASE;
    private static final int ASHIFT;
    private static final int COMMON_MAX_SPARES;
    static final int COMMON_PARALLELISM = Math.max(common.config & 65535, 1);
    private static final long CTL;
    private static final long RUNSTATE;
    private static final Unsafe U = Unsafe.getUnsafe();
    static final ForkJoinPool common = ((ForkJoinPool) AccessController.doPrivileged(new PrivilegedAction() {
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

    private static void checkPermission() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(modifyThreadPermission);
            throw null;
        }
    }

    private static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
        private DefaultForkJoinWorkerThreadFactory() {
        }

        @Override // java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
        public final ForkJoinWorkerThread newThread(ForkJoinPool forkJoinPool) {
            return new ForkJoinWorkerThread(forkJoinPool);
        }
    }

    /* access modifiers changed from: private */
    public static final class EmptyTask extends ForkJoinTask {
        private static final long serialVersionUID = -7721805057305804111L;

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            return true;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        EmptyTask() {
            this.status = -268435456;
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
        private static final long QLOCK;
        private static final Unsafe U = Unsafe.getUnsafe();
        ForkJoinTask[] array;
        volatile int base = 4096;
        int config;
        volatile ForkJoinTask currentJoin;
        volatile ForkJoinTask currentSteal;
        int hint;
        int nsteals;
        final ForkJoinWorkerThread owner;
        volatile Thread parker;
        final ForkJoinPool pool;
        volatile int qlock;
        volatile int scanState;
        int stackPred;
        int top = 4096;

        WorkQueue(ForkJoinPool forkJoinPool, ForkJoinWorkerThread forkJoinWorkerThread) {
            this.pool = forkJoinPool;
            this.owner = forkJoinWorkerThread;
        }

        /* access modifiers changed from: package-private */
        public final int queueSize() {
            int i = this.base - this.top;
            if (i >= 0) {
                return 0;
            }
            return -i;
        }

        /* access modifiers changed from: package-private */
        public final boolean isEmpty() {
            ForkJoinTask[] forkJoinTaskArr;
            int length;
            int i = this.base;
            int i2 = this.top;
            int i3 = i - i2;
            if (i3 < 0) {
                return i3 == -1 && ((forkJoinTaskArr = this.array) == null || (length = forkJoinTaskArr.length) == 0 || forkJoinTaskArr[(length - 1) & (i2 - 1)] == null);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final void push(ForkJoinTask forkJoinTask) {
            int length;
            U.storeFence();
            int i = this.top;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr != null && (length = forkJoinTaskArr.length) > 0) {
                forkJoinTaskArr[(length - 1) & i] = forkJoinTask;
                this.top = i + 1;
                ForkJoinPool forkJoinPool = this.pool;
                int i2 = this.base - i;
                if (i2 == 0 && forkJoinPool != null) {
                    U.fullFence();
                    forkJoinPool.signalWork();
                } else if (length + i2 == 1) {
                    growArray();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask[] growArray() {
            int length;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            int length2 = forkJoinTaskArr != null ? forkJoinTaskArr.length << 1 : 8192;
            if (length2 < 8192 || length2 > 67108864) {
                throw new RejectedExecutionException("Queue capacity exceeded");
            }
            ForkJoinTask[] forkJoinTaskArr2 = new ForkJoinTask[length2];
            this.array = forkJoinTaskArr2;
            if (forkJoinTaskArr != null && forkJoinTaskArr.length - 1 > 0) {
                int i = this.top;
                int i2 = this.base;
                if (i - i2 > 0) {
                    int i3 = length2 - 1;
                    do {
                        long j = ((long) ABASE) + (((long) (i2 & length)) << ASHIFT);
                        ForkJoinTask forkJoinTask = (ForkJoinTask) U.getObjectVolatile(forkJoinTaskArr, j);
                        if (forkJoinTask != null && U.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, null)) {
                            forkJoinTaskArr2[i2 & i3] = forkJoinTask;
                        }
                        i2++;
                    } while (i2 != i);
                    U.storeFence();
                }
            }
            return forkJoinTaskArr2;
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask pop() {
            int length;
            int i = this.base;
            int i2 = this.top;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || i == i2 || (length = forkJoinTaskArr.length) <= 0) {
                return null;
            }
            int i3 = i2 - 1;
            long j = (((long) ((length - 1) & i3)) << ASHIFT) + ((long) ABASE);
            ForkJoinTask forkJoinTask = (ForkJoinTask) U.getObject(forkJoinTaskArr, j);
            if (forkJoinTask == null || !U.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, null)) {
                return null;
            }
            this.top = i3;
            return forkJoinTask;
        }

        /* access modifiers changed from: package-private */
        public final ForkJoinTask poll() {
            int i;
            int length;
            while (true) {
                int i2 = this.base;
                int i3 = this.top;
                ForkJoinTask[] forkJoinTaskArr = this.array;
                if (forkJoinTaskArr == null || (i = i2 - i3) >= 0 || (length = forkJoinTaskArr.length) <= 0) {
                    return null;
                }
                long j = (((long) ((length - 1) & i2)) << ASHIFT) + ((long) ABASE);
                ForkJoinTask forkJoinTask = (ForkJoinTask) U.getObjectVolatile(forkJoinTaskArr, j);
                int i4 = i2 + 1;
                if (i2 == this.base) {
                    if (forkJoinTask != null) {
                        if (U.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, null)) {
                            this.base = i4;
                            return forkJoinTask;
                        }
                    } else if (i == -1) {
                        return null;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final int sharedPush(ForkJoinTask forkJoinTask) {
            int length;
            if (!U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                return 1;
            }
            int i = this.base;
            int i2 = this.top;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            int i3 = 0;
            if (forkJoinTaskArr != null && (length = forkJoinTaskArr.length) > 0) {
                int i4 = length - 1;
                int i5 = i - i2;
                if (i4 + i5 > 0) {
                    forkJoinTaskArr[i4 & i2] = forkJoinTask;
                    this.top = i2 + 1;
                    this.qlock = 0;
                    if (i5 < 0 && i == this.base) {
                        i3 = i5;
                    }
                    return i3;
                }
            }
            growAndSharedPush(forkJoinTask);
            return i3;
        }

        private void growAndSharedPush(ForkJoinTask forkJoinTask) {
            int length;
            try {
                growArray();
                int i = this.top;
                ForkJoinTask[] forkJoinTaskArr = this.array;
                if (forkJoinTaskArr != null && (length = forkJoinTaskArr.length) > 0) {
                    forkJoinTaskArr[(length - 1) & i] = forkJoinTask;
                    this.top = i + 1;
                }
            } finally {
                this.qlock = 0;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean trySharedUnpush(ForkJoinTask forkJoinTask) {
            int length;
            boolean z = true;
            int i = this.top - 1;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr != null && (length = forkJoinTaskArr.length) > 0) {
                long j = (((long) ((length - 1) & i)) << ASHIFT) + ((long) ABASE);
                if (((ForkJoinTask) U.getObject(forkJoinTaskArr, j)) == forkJoinTask && U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                    if (this.top == i + 1 && this.array == forkJoinTaskArr && U.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, null)) {
                        this.top = i;
                    } else {
                        z = false;
                    }
                    U.putOrderedInt(this, QLOCK, 0);
                    return z;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public final void cancelAll() {
            ForkJoinTask forkJoinTask = this.currentJoin;
            if (forkJoinTask != null) {
                this.currentJoin = null;
                ForkJoinTask.cancelIgnoringExceptions(forkJoinTask);
            }
            ForkJoinTask forkJoinTask2 = this.currentSteal;
            if (forkJoinTask2 != null) {
                this.currentSteal = null;
                ForkJoinTask.cancelIgnoringExceptions(forkJoinTask2);
            }
            while (true) {
                ForkJoinTask poll = poll();
                if (poll != null) {
                    ForkJoinTask.cancelIgnoringExceptions(poll);
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void localPopAndExec() {
            int length;
            int i = 0;
            do {
                int i2 = this.base;
                int i3 = this.top;
                ForkJoinTask[] forkJoinTaskArr = this.array;
                if (forkJoinTaskArr != null && i2 != i3 && (length = forkJoinTaskArr.length) > 0) {
                    int i4 = i3 - 1;
                    ForkJoinTask forkJoinTask = (ForkJoinTask) U.getAndSetObject(forkJoinTaskArr, (((long) ((length - 1) & i4)) << ASHIFT) + ((long) ABASE), null);
                    if (forkJoinTask != null) {
                        this.top = i4;
                        this.currentSteal = forkJoinTask;
                        forkJoinTask.doExec();
                        i++;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (i <= 1023);
        }

        /* access modifiers changed from: package-private */
        public final void localPollAndExec() {
            int length;
            int i = 0;
            while (true) {
                int i2 = this.base;
                int i3 = this.top;
                ForkJoinTask[] forkJoinTaskArr = this.array;
                if (forkJoinTaskArr != null && i2 != i3 && (length = forkJoinTaskArr.length) > 0) {
                    int i4 = i2 + 1;
                    ForkJoinTask forkJoinTask = (ForkJoinTask) U.getAndSetObject(forkJoinTaskArr, (((long) (i2 & (length - 1))) << ASHIFT) + ((long) ABASE), null);
                    if (forkJoinTask != null) {
                        this.base = i4;
                        forkJoinTask.doExec();
                        i++;
                        if (i > 1023) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void runTask(ForkJoinTask forkJoinTask) {
            if (forkJoinTask != null) {
                forkJoinTask.doExec();
                if (this.config < 0) {
                    localPollAndExec();
                } else {
                    localPopAndExec();
                }
                int i = this.nsteals + 1;
                this.nsteals = i;
                ForkJoinWorkerThread forkJoinWorkerThread = this.owner;
                this.currentSteal = null;
                if (i < 0) {
                    transferStealCount(this.pool);
                }
                if (forkJoinWorkerThread != null) {
                    forkJoinWorkerThread.afterTopLevelExec();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void transferStealCount(ForkJoinPool forkJoinPool) {
            AuxState auxState;
            if (forkJoinPool != null && (auxState = forkJoinPool.auxState) != null) {
                long j = (long) this.nsteals;
                this.nsteals = 0;
                if (j < 0) {
                    j = 2147483647L;
                }
                auxState.lock();
                try {
                    auxState.stealCount += j;
                } finally {
                    auxState.unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0056  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean tryRemoveAndExec(java.util.concurrent.ForkJoinTask r13) {
            /*
            // Method dump skipped, instructions count: 131
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.WorkQueue.tryRemoveAndExec(java.util.concurrent.ForkJoinTask):boolean");
        }

        /* access modifiers changed from: package-private */
        public final CountedCompleter popCC(CountedCompleter countedCompleter, int i) {
            int length;
            int i2 = this.base;
            int i3 = this.top;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || i2 == i3 || (length = forkJoinTaskArr.length) <= 0) {
                return null;
            }
            boolean z = true;
            int i4 = i3 - 1;
            long j = (((long) ((length - 1) & i4)) << ASHIFT) + ((long) ABASE);
            ForkJoinTask forkJoinTask = (ForkJoinTask) U.getObjectVolatile(forkJoinTaskArr, j);
            if (!(forkJoinTask instanceof CountedCompleter)) {
                return null;
            }
            CountedCompleter countedCompleter2 = (CountedCompleter) forkJoinTask;
            CountedCompleter countedCompleter3 = countedCompleter2;
            while (countedCompleter3 != countedCompleter) {
                countedCompleter3 = countedCompleter3.completer;
                if (countedCompleter3 == null) {
                    return null;
                }
            }
            if ((i & 1) == 0) {
                if (!U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                    return null;
                }
                if (this.top == i3 && this.array == forkJoinTaskArr && U.compareAndSwapObject(forkJoinTaskArr, j, countedCompleter2, null)) {
                    this.top = i4;
                } else {
                    z = false;
                }
                U.putOrderedInt(this, QLOCK, 0);
                if (z) {
                    return countedCompleter2;
                }
                return null;
            } else if (!U.compareAndSwapObject(forkJoinTaskArr, j, countedCompleter2, null)) {
                return null;
            } else {
                this.top = i4;
                return countedCompleter2;
            }
        }

        /* access modifiers changed from: package-private */
        public final int pollAndExecCC(CountedCompleter countedCompleter) {
            int length;
            int i = this.base;
            int i2 = this.top;
            ForkJoinTask[] forkJoinTaskArr = this.array;
            if (forkJoinTaskArr == null || i == i2 || (length = forkJoinTaskArr.length) <= 0) {
                return i | Integer.MIN_VALUE;
            }
            long j = ((long) ABASE) + (((long) ((length - 1) & i)) << ASHIFT);
            ForkJoinTask forkJoinTask = (ForkJoinTask) U.getObjectVolatile(forkJoinTaskArr, j);
            if (forkJoinTask != null) {
                if (!(forkJoinTask instanceof CountedCompleter)) {
                    return -1;
                }
                CountedCompleter countedCompleter2 = (CountedCompleter) forkJoinTask;
                CountedCompleter countedCompleter3 = countedCompleter2;
                while (countedCompleter3 != countedCompleter) {
                    countedCompleter3 = countedCompleter3.completer;
                    if (countedCompleter3 == null) {
                        return -1;
                    }
                }
                int i3 = i + 1;
                if (i == this.base && U.compareAndSwapObject(forkJoinTaskArr, j, countedCompleter2, null)) {
                    this.base = i3;
                    countedCompleter2.doExec();
                    return 1;
                }
            }
            return 2;
        }

        /* access modifiers changed from: package-private */
        public final boolean isApparentlyUnblocked() {
            ForkJoinWorkerThread forkJoinWorkerThread;
            Thread.State state;
            return (this.scanState < 0 || (forkJoinWorkerThread = this.owner) == null || (state = forkJoinWorkerThread.getState()) == Thread.State.BLOCKED || state == Thread.State.WAITING || state == Thread.State.TIMED_WAITING) ? false : true;
        }

        static {
            try {
                QLOCK = U.objectFieldOffset(WorkQueue.class.getDeclaredField("qlock"));
                ABASE = U.arrayBaseOffset(ForkJoinTask[].class);
                int arrayIndexScale = U.arrayIndexScale(ForkJoinTask[].class);
                if (((arrayIndexScale - 1) & arrayIndexScale) == 0) {
                    ASHIFT = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
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

    private void tryInitialize(boolean z) {
        if (this.runState == 0) {
            int i = this.config & 65535;
            int i2 = i > 1 ? i - 1 : 1;
            int i3 = i2 | (i2 >>> 1);
            int i4 = i3 | (i3 >>> 2);
            int i5 = i4 | (i4 >>> 4);
            int i6 = i5 | (i5 >>> 8);
            AuxState auxState2 = new AuxState();
            WorkQueue[] workQueueArr = new WorkQueue[((((i6 | (i6 >>> 16)) + 1) << 1) & 65535)];
            synchronized (modifyThreadPermission) {
                if (this.runState == 0) {
                    this.workQueues = workQueueArr;
                    this.auxState = auxState2;
                    this.runState = 1;
                }
            }
        }
        if (z && this.runState < 0) {
            tryTerminate(false, false);
            throw new RejectedExecutionException();
        }
    }

    private boolean createWorker(boolean z) {
        ForkJoinWorkerThread forkJoinWorkerThread;
        Throwable th;
        ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory = this.factory;
        Throwable th2 = null;
        if (forkJoinWorkerThreadFactory != null) {
            try {
                forkJoinWorkerThread = forkJoinWorkerThreadFactory.newThread(this);
                if (forkJoinWorkerThread != null) {
                    if (z) {
                        try {
                            WorkQueue workQueue = forkJoinWorkerThread.workQueue;
                            if (workQueue != null) {
                                workQueue.config |= 131072;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            th2 = th;
                            deregisterWorker(forkJoinWorkerThread, th2);
                            return false;
                        }
                    }
                    forkJoinWorkerThread.start();
                    return true;
                }
            } catch (Throwable th4) {
                th = th4;
                forkJoinWorkerThread = null;
                th2 = th;
                deregisterWorker(forkJoinWorkerThread, th2);
                return false;
            }
        } else {
            forkJoinWorkerThread = null;
        }
        deregisterWorker(forkJoinWorkerThread, th2);
        return false;
    }

    private void tryAddWorker(long j) {
        long j2 = j;
        do {
            long j3 = (-281474976710656L & (281474976710656L + j2)) | (281470681743360L & (4294967296L + j2));
            if (this.ctl != j2 || !U.compareAndSwapLong(this, CTL, j2, j3)) {
                j2 = this.ctl;
                if ((140737488355328L & j2) == 0) {
                    return;
                }
            } else {
                createWorker(false);
                return;
            }
        } while (((int) j2) == 0);
    }

    /* access modifiers changed from: package-private */
    public final WorkQueue registerWorker(ForkJoinWorkerThread forkJoinWorkerThread) {
        int length;
        forkJoinWorkerThread.setDaemon(true);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.ueh;
        if (uncaughtExceptionHandler != null) {
            forkJoinWorkerThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        WorkQueue workQueue = new WorkQueue(this, forkJoinWorkerThread);
        int i = this.config & -65536;
        AuxState auxState2 = this.auxState;
        int i2 = 0;
        if (auxState2 != null) {
            auxState2.lock();
            try {
                long j = auxState2.indexSeed - 1640531527;
                auxState2.indexSeed = j;
                int i3 = (int) j;
                WorkQueue[] workQueueArr = this.workQueues;
                if (workQueueArr != null && (length = workQueueArr.length) > 0) {
                    int i4 = length - 1;
                    int i5 = ((i3 << 1) | 1) & i4;
                    if (workQueueArr[i5] != null) {
                        int i6 = 2;
                        if (length > 4) {
                            i6 = 2 + ((length >>> 1) & 65534);
                        }
                        int i7 = length;
                        loop0:
                        while (true) {
                            int i8 = 0;
                            do {
                                i5 = (i5 + i6) & i4;
                                if (workQueueArr[i5] == null) {
                                    break loop0;
                                }
                                i8++;
                            } while (i8 < i7);
                            i7 <<= 1;
                            workQueueArr = (WorkQueue[]) Arrays.copyOf(workQueueArr, i7);
                            this.workQueues = workQueueArr;
                            i4 = i7 - 1;
                        }
                    }
                    workQueue.hint = i3;
                    workQueue.config = i | i5;
                    workQueue.scanState = (2147418112 & i3) | i5;
                    workQueueArr[i5] = workQueue;
                    i2 = i5;
                }
            } finally {
                auxState2.unlock();
            }
        }
        forkJoinWorkerThread.setName(this.workerNamePrefix.concat(Integer.toString(i2 >>> 1)));
        return workQueue;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a0 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void deregisterWorker(java.util.concurrent.ForkJoinWorkerThread r16, java.lang.Throwable r17) {
        /*
        // Method dump skipped, instructions count: 187
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.deregisterWorker(java.util.concurrent.ForkJoinWorkerThread, java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public final void signalWork() {
        int i;
        WorkQueue workQueue;
        while (true) {
            long j = this.ctl;
            if (j < 0) {
                int i2 = (int) j;
                if (i2 != 0) {
                    WorkQueue[] workQueueArr = this.workQueues;
                    if (workQueueArr != null && workQueueArr.length > (i = 65535 & i2) && (workQueue = workQueueArr[i]) != null) {
                        int i3 = i2 & Integer.MAX_VALUE;
                        int i4 = workQueue.scanState;
                        long j2 = (((long) workQueue.stackPred) & 4294967295L) | (-4294967296L & (281474976710656L + j));
                        if (i2 == i4 && U.compareAndSwapLong(this, CTL, j, j2)) {
                            workQueue.scanState = i3;
                            LockSupport.unpark(workQueue.parker);
                            return;
                        }
                    } else {
                        return;
                    }
                } else if ((140737488355328L & j) != 0) {
                    tryAddWorker(j);
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private boolean tryRelease(long j, WorkQueue workQueue, long j2) {
        int i = (int) j;
        int i2 = i & Integer.MAX_VALUE;
        if (workQueue == null) {
            return false;
        }
        int i3 = workQueue.scanState;
        long j3 = (-4294967296L & (j + j2)) | (((long) workQueue.stackPred) & 4294967295L);
        if (i != i3 || !U.compareAndSwapLong(this, CTL, j, j3)) {
            return false;
        }
        workQueue.scanState = i2;
        LockSupport.unpark(workQueue.parker);
        return true;
    }

    private void tryReactivate(WorkQueue workQueue, WorkQueue[] workQueueArr, int i) {
        int length;
        WorkQueue workQueue2;
        long j = this.ctl;
        int i2 = (int) j;
        if (i2 != 0 && workQueue != null && workQueueArr != null && (length = workQueueArr.length) > 0 && ((i ^ i2) & 65536) == 0 && (workQueue2 = workQueueArr[(length - 1) & i2]) != null) {
            long j2 = (-4294967296L & (281474976710656L + j)) | (((long) workQueue2.stackPred) & 4294967295L);
            int i3 = Integer.MAX_VALUE & i2;
            if (workQueue.scanState < 0 && workQueue2.scanState == i2 && U.compareAndSwapLong(this, CTL, j, j2)) {
                workQueue2.scanState = i3;
                LockSupport.unpark(workQueue2.parker);
            }
        }
    }

    private void inactivate(WorkQueue workQueue, int i) {
        long j;
        int i2 = (i + 65536) | Integer.MIN_VALUE;
        long j2 = ((long) i2) & 4294967295L;
        if (workQueue != null) {
            workQueue.scanState = i2;
            do {
                j = this.ctl;
                workQueue.stackPred = (int) j;
            } while (!U.compareAndSwapLong(this, CTL, j, j2 | (-4294967296L & (j - 281474976710656L))));
        }
    }

    private int awaitWork(WorkQueue workQueue) {
        if (workQueue != null && workQueue.scanState < 0) {
            long j = this.ctl;
            if (((int) (j >> 48)) + (this.config & 65535) <= 0) {
                return timedAwaitWork(workQueue, j);
            }
            if ((this.runState & 2) != 0) {
                workQueue.qlock = -1;
                return -1;
            } else if (workQueue.scanState < 0) {
                workQueue.parker = Thread.currentThread();
                if (workQueue.scanState < 0) {
                    LockSupport.park(this);
                }
                workQueue.parker = null;
                if ((this.runState & 2) != 0) {
                    workQueue.qlock = -1;
                    return -1;
                } else if (workQueue.scanState < 0) {
                    Thread.interrupted();
                }
            }
        }
        return 0;
    }

    private int timedAwaitWork(WorkQueue workQueue, long j) {
        int i;
        AuxState auxState2;
        WorkQueue[] workQueueArr;
        int i2 = 1 - ((short) ((int) (j >>> 32)));
        if (i2 <= 0) {
            i2 = 1;
        }
        long currentTimeMillis = (((long) i2) * 2000) + System.currentTimeMillis();
        int i3 = -1;
        if (this.runState < 0) {
            int tryTerminate = tryTerminate(false, false);
            if (tryTerminate <= 0) {
                return tryTerminate;
            }
            i = tryTerminate;
        } else {
            i = 0;
        }
        if (workQueue != null && workQueue.scanState < 0) {
            workQueue.parker = Thread.currentThread();
            if (workQueue.scanState < 0) {
                LockSupport.parkUntil(this, currentTimeMillis);
            }
            workQueue.parker = null;
            if ((this.runState & 2) != 0) {
                workQueue.qlock = -1;
                return -1;
            }
            int i4 = workQueue.scanState;
            if (i4 < 0 && !Thread.interrupted() && ((int) j) == i4 && (auxState2 = this.auxState) != null && this.ctl == j && currentTimeMillis - System.currentTimeMillis() <= 20) {
                auxState2.lock();
                try {
                    int i5 = workQueue.config;
                    int i6 = i5 & 65535;
                    long j2 = (-4294967296L & (j - 4294967296L)) | (4294967295L & ((long) workQueue.stackPred));
                    if ((this.runState & 2) != 0 || (workQueueArr = this.workQueues) == null || i6 >= workQueueArr.length || i6 < 0 || workQueueArr[i6] != workQueue || !U.compareAndSwapLong(this, CTL, j, j2)) {
                        i3 = i;
                    } else {
                        workQueueArr[i6] = null;
                        workQueue.config = 262144 | i5;
                        workQueue.qlock = -1;
                    }
                    return i3;
                } finally {
                    auxState2.unlock();
                }
            }
        }
        return i;
    }

    private boolean tryDropSpare(WorkQueue workQueue) {
        WorkQueue[] workQueueArr;
        int length;
        boolean z;
        boolean z2;
        long j;
        if (workQueue == null || !workQueue.isEmpty()) {
            return false;
        }
        do {
            long j2 = this.ctl;
            if (((short) ((int) (j2 >> 32))) <= 0) {
                return false;
            }
            int i = (int) j2;
            if ((i == 0 && ((int) (j2 >> 48)) <= 0) || (workQueueArr = this.workQueues) == null || (length = workQueueArr.length) <= 0) {
                return false;
            }
            if (i == 0) {
                z = U.compareAndSwapLong(this, CTL, j2, ((j2 - 4294967296L) & 281470681743360L) | ((j2 - 281474976710656L) & -281474976710656L) | (j2 & 4294967295L));
                continue;
            } else {
                WorkQueue workQueue2 = workQueueArr[(length - 1) & i];
                if (workQueue2 != null && workQueue2.scanState == i) {
                    long j3 = ((long) workQueue2.stackPred) & 4294967295L;
                    if (workQueue == workQueue2 || workQueue.scanState >= 0) {
                        j = j3 | ((j2 - 4294967296L) & 281470681743360L) | (j2 & -281474976710656L);
                        z2 = true;
                    } else {
                        j = j3 | ((281474976710656L + j2) & -281474976710656L) | (j2 & 281470681743360L);
                        z2 = false;
                    }
                    if (U.compareAndSwapLong(this, CTL, j2, j)) {
                        workQueue2.scanState = i & Integer.MAX_VALUE;
                        LockSupport.unpark(workQueue2.parker);
                        z = z2;
                        continue;
                    }
                }
                z = false;
                continue;
            }
        } while (!z);
        int i2 = workQueue.config;
        int i3 = 65535 & i2;
        if (i3 >= 0 && i3 < workQueueArr.length && workQueueArr[i3] == workQueue) {
            workQueueArr[i3] = null;
        }
        workQueue.config = i2 | 262144;
        workQueue.qlock = -1;
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void runWorker(WorkQueue workQueue) {
        workQueue.growArray();
        int i = (workQueue.config & 131072) != 0 ? 0 : 1023;
        long j = ((long) workQueue.hint) * -2685821657736338717L;
        if ((this.runState & 2) == 0) {
            if (j == 0) {
                j = 1;
            }
            while (true) {
                if (i != 0 || !tryDropSpare(workQueue)) {
                    long j2 = j ^ (j >>> 12);
                    long j3 = j2 ^ (j2 << 25);
                    j = j3 ^ (j3 >>> 27);
                    if (scan(workQueue, i, ((int) (j >>> 48)) | 1, (int) j) < 0 && awaitWork(workQueue) < 0) {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private int scan(WorkQueue workQueue, int i, int i2, int i3) {
        int length;
        int i4;
        ForkJoinTask[] forkJoinTaskArr;
        int length2;
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr != null && workQueue != null && (length = workQueueArr.length) > 0) {
            int i5 = length - 1;
            int i6 = i5 & i3;
            int i7 = workQueue.scanState;
            int i8 = i3;
            int i9 = i6;
            int i10 = 0;
            while (true) {
                WorkQueue workQueue2 = workQueueArr[i9];
                if (workQueue2 != null) {
                    int i11 = workQueue2.base;
                    if (i11 - workQueue2.top < 0 && (forkJoinTaskArr = workQueue2.array) != null && (length2 = forkJoinTaskArr.length) > 0) {
                        i4 = i5;
                        long j = ((long) ABASE) + (((long) ((length2 - 1) & i11)) << ASHIFT);
                        ForkJoinTask forkJoinTask = (ForkJoinTask) U.getObjectVolatile(forkJoinTaskArr, j);
                        if (forkJoinTask != null) {
                            int i12 = i11 + 1;
                            if (i11 != workQueue2.base) {
                                break;
                            } else if (i7 < 0) {
                                tryReactivate(workQueue, workQueueArr, i8);
                                break;
                            } else if (!U.compareAndSwapObject(forkJoinTaskArr, j, forkJoinTask, null)) {
                                break;
                            } else {
                                workQueue2.base = i12;
                                workQueue.currentSteal = forkJoinTask;
                                if (i12 != workQueue2.top) {
                                    signalWork();
                                }
                                workQueue.runTask(forkJoinTask);
                                i10++;
                                if (i10 > i) {
                                    break;
                                }
                                i5 = i4;
                            }
                        } else {
                            break;
                        }
                    }
                }
                i4 = i5;
                if (i10 != 0) {
                    break;
                }
                int i13 = (i9 + i2) & i4;
                if (i13 == i6) {
                    if (i7 < 0) {
                        return i7;
                    }
                    if (i8 >= 0) {
                        inactivate(workQueue, i7);
                        break;
                    }
                    i8 <<= 1;
                }
                i9 = i13;
                i5 = i4;
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int helpComplete(WorkQueue workQueue, CountedCompleter countedCompleter, int i) {
        int length;
        WorkQueue workQueue2;
        CountedCompleter popCC;
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr == null || (length = workQueueArr.length) <= 1 || countedCompleter == null || workQueue == null) {
            return 0;
        }
        int i2 = length - 1;
        int i3 = workQueue.config;
        int i4 = ~i3;
        int i5 = i4 & i2;
        int i6 = i;
        int i7 = i4;
        int i8 = i5;
        int i9 = 3;
        int i10 = 0;
        int i11 = 0;
        int i12 = 1;
        while (true) {
            int i13 = countedCompleter.status;
            if (i13 < 0) {
                return i13;
            }
            if (i12 != 1 || (popCC = workQueue.popCC(countedCompleter, i3)) == null) {
                int i14 = i5 | 1;
                if (i14 < 0 || i14 > i2 || (workQueue2 = workQueueArr[i14]) == null) {
                    i12 = 0;
                } else {
                    i12 = workQueue2.pollAndExecCC(countedCompleter);
                    if (i12 < 0) {
                        i10 += i12;
                    }
                }
                if (i12 <= 0) {
                    i5 = (i5 + i9) & i2;
                    if (i5 != i8) {
                        continue;
                    } else if (i11 == i10) {
                        return i13;
                    } else {
                        i11 = i10;
                        i10 = 0;
                    }
                } else if (i12 == 1 && i6 != 0 && i6 - 1 == 0) {
                    return i13;
                } else {
                    int i15 = (i7 << 13) ^ i7;
                    int i16 = i15 ^ (i15 >>> 17);
                    int i17 = i16 ^ (i16 << 5);
                    i9 = (i7 >>> 16) | 3;
                    i7 = i17;
                    i5 = i17 & i2;
                }
            } else {
                popCC.doExec();
                if (i6 != 0 && i6 - 1 == 0) {
                    return i13;
                }
            }
            i8 = i5;
            i10 = 0;
            i11 = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
        r9.hint = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        if (r8.status >= 0) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        r10 = r14.base;
        r12 = r12 + r10;
        r11 = r14.currentJoin;
        r13 = null;
        r15 = r14.array;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        if (r15 == null) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        r3 = r15.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
        if (r3 <= 0) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        r21 = r6;
        r22 = r4;
        r3 = ((long) java.util.concurrent.ForkJoinPool.ABASE) + (((long) ((r3 - 1) & r10)) << java.util.concurrent.ForkJoinPool.ASHIFT);
        r5 = (java.util.concurrent.ForkJoinTask) java.util.concurrent.ForkJoinPool.U.getObjectVolatile(r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0065, code lost:
        if (r5 == null) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        r6 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006b, code lost:
        if (r10 != r14.base) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006f, code lost:
        if (r9.currentJoin != r8) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
        if (r14.currentSteal != r8) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0077, code lost:
        if (r8.status >= 0) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008a, code lost:
        if (java.util.concurrent.ForkJoinPool.U.compareAndSwapObject(r15, r3, r5, null) == false) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008c, code lost:
        r14.base = r6;
        r24.currentSteal = r5;
        r3 = r24.top;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0093, code lost:
        r13.doExec();
        r24.currentSteal = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009a, code lost:
        if (r25.status >= 0) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a0, code lost:
        if (r24.top != r3) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a3, code lost:
        r13 = r24.pop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a7, code lost:
        if (r13 != null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00aa, code lost:
        r24.currentSteal = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b1, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b3, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b5, code lost:
        r22 = r4;
        r21 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b9, code lost:
        r6 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ba, code lost:
        if (r13 != null) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00be, code lost:
        if (r6 != r14.base) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c3, code lost:
        if ((r6 - r14.top) < 0) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c5, code lost:
        if (r11 != null) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c9, code lost:
        if (r11 != r14.currentJoin) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cd, code lost:
        if (r22 != r12) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d0, code lost:
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00de, code lost:
        r4 = r22;
        r6 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00fd, code lost:
        r3 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void helpStealer(java.util.concurrent.ForkJoinPool.WorkQueue r24, java.util.concurrent.ForkJoinTask r25) {
        /*
        // Method dump skipped, instructions count: 258
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.helpStealer(java.util.concurrent.ForkJoinPool$WorkQueue, java.util.concurrent.ForkJoinTask):void");
    }

    private boolean tryCompensate(WorkQueue workQueue) {
        int length;
        boolean z;
        WorkQueue workQueue2;
        long j = this.ctl;
        WorkQueue[] workQueueArr = this.workQueues;
        int i = this.config & 65535;
        int i2 = ((int) (j >> 48)) + i;
        int i3 = ((short) ((int) (j >> 32))) + i;
        if (workQueue == null || workQueue.qlock < 0 || i == 0 || workQueueArr == null || (length = workQueueArr.length) <= 0) {
            return false;
        }
        int i4 = length - 1;
        int i5 = 0;
        while (true) {
            if (i5 <= i4) {
                int i6 = (i5 << 1) | 1;
                if (i6 <= i4 && i6 >= 0 && (workQueue2 = workQueueArr[i6]) != null && workQueue2.scanState >= 0 && workQueue2.currentSteal == null) {
                    z = false;
                    break;
                }
                i5++;
            } else {
                z = true;
                break;
            }
        }
        if (!z || this.ctl != j) {
            return false;
        }
        int i7 = (int) j;
        if (i7 != 0) {
            return tryRelease(j, workQueueArr[i4 & i7], 0);
        }
        if (i3 >= i && i2 > 1 && workQueue.isEmpty()) {
            return U.compareAndSwapLong(this, CTL, j, ((j - 281474976710656L) & -281474976710656L) | (281474976710655L & j));
        }
        if (i3 >= 32767 || (this == common && i3 >= COMMON_MAX_SPARES + i)) {
            throw new RejectedExecutionException("Thread limit exceeded replacing blocked worker");
        }
        boolean z2 = i3 >= i;
        if (!U.compareAndSwapLong(this, CTL, j, (j & -281474976710656L) | (281470681743360L & (4294967296L + j))) || !createWorker(z2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int awaitJoin(WorkQueue workQueue, ForkJoinTask forkJoinTask, long j) {
        int i;
        if (workQueue == null) {
            return 0;
        }
        ForkJoinTask forkJoinTask2 = workQueue.currentJoin;
        if (forkJoinTask == null) {
            return 0;
        }
        int i2 = forkJoinTask.status;
        if (i2 < 0) {
            return i2;
        }
        workQueue.currentJoin = forkJoinTask;
        CountedCompleter countedCompleter = forkJoinTask instanceof CountedCompleter ? (CountedCompleter) forkJoinTask : null;
        do {
            if (countedCompleter != null) {
                helpComplete(workQueue, countedCompleter, 0);
            } else {
                helpStealer(workQueue, forkJoinTask);
            }
            i = forkJoinTask.status;
            if (i < 0) {
                break;
            }
            long j2 = 0;
            if (j != 0) {
                long nanoTime = j - System.nanoTime();
                if (nanoTime <= 0) {
                    break;
                }
                long millis = TimeUnit.NANOSECONDS.toMillis(nanoTime);
                j2 = millis <= 0 ? 1 : millis;
            }
            if (tryCompensate(workQueue)) {
                forkJoinTask.internalWait(j2);
                U.getAndAddLong(this, CTL, 281474976710656L);
            }
            i = forkJoinTask.status;
        } while (i >= 0);
        workQueue.currentJoin = forkJoinTask2;
        return i;
    }

    private int tryTerminate(boolean z, boolean z2) {
        Unsafe unsafe;
        long j;
        int i;
        while (true) {
            int i2 = this.runState;
            if (i2 < 0) {
                long j2 = 0;
                if ((i2 & 2) == 0) {
                    if (!z) {
                        long j3 = 0;
                        loop1:
                        while (true) {
                            long j4 = this.ctl;
                            if (((int) (j4 >> 48)) + (this.config & 65535) > 0) {
                                return 0;
                            }
                            WorkQueue[] workQueueArr = this.workQueues;
                            if (workQueueArr != null) {
                                long j5 = j4;
                                for (WorkQueue workQueue : workQueueArr) {
                                    if (workQueue != null) {
                                        int i3 = workQueue.base;
                                        j5 += (long) i3;
                                        if (!(workQueue.currentSteal == null && i3 == workQueue.top)) {
                                            return 0;
                                        }
                                    }
                                }
                                j4 = j5;
                            }
                            if (j3 == j4) {
                                break;
                            }
                            j3 = j4;
                        }
                    }
                    do {
                        unsafe = U;
                        j = RUNSTATE;
                        i = this.runState;
                    } while (!unsafe.compareAndSwapInt(this, j, i, i | 2));
                }
                while (true) {
                    long j6 = this.ctl;
                    WorkQueue[] workQueueArr2 = this.workQueues;
                    if (workQueueArr2 != null) {
                        long j7 = j6;
                        for (WorkQueue workQueue2 : workQueueArr2) {
                            if (workQueue2 != null) {
                                workQueue2.cancelAll();
                                j7 += (long) workQueue2.base;
                                if (workQueue2.qlock >= 0) {
                                    workQueue2.qlock = -1;
                                    ForkJoinWorkerThread forkJoinWorkerThread = workQueue2.owner;
                                    if (forkJoinWorkerThread != null) {
                                        try {
                                            forkJoinWorkerThread.interrupt();
                                        } catch (Throwable unused) {
                                        }
                                    }
                                }
                            }
                        }
                        j6 = j7;
                    }
                    if (j2 == j6) {
                        break;
                    }
                    j2 = j6;
                }
                if (((short) ((int) (this.ctl >>> 32))) + (this.config & 65535) <= 0) {
                    this.runState = -2147483641;
                    synchronized (this) {
                        notifyAll();
                    }
                }
                return -1;
            } else if (!z2 || this == common) {
                return 1;
            } else {
                if (i2 == 0) {
                    tryInitialize(false);
                } else {
                    U.compareAndSwapInt(this, RUNSTATE, i2, i2 | Integer.MIN_VALUE);
                }
            }
        }
    }

    private void tryCreateExternalQueue(int i) {
        AuxState auxState2 = this.auxState;
        if (auxState2 != null && i >= 0) {
            WorkQueue workQueue = new WorkQueue(this, null);
            workQueue.config = i;
            workQueue.scanState = Integer.MAX_VALUE;
            boolean z = true;
            workQueue.qlock = 1;
            auxState2.lock();
            try {
                WorkQueue[] workQueueArr = this.workQueues;
                if (workQueueArr == null || i >= workQueueArr.length || workQueueArr[i] != null) {
                    z = false;
                } else {
                    workQueueArr[i] = workQueue;
                }
                if (z) {
                    try {
                        workQueue.growArray();
                    } finally {
                        workQueue.qlock = 0;
                    }
                }
            } finally {
                auxState2.unlock();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void externalPush(ForkJoinTask forkJoinTask) {
        int length;
        int probe = ThreadLocalRandom.getProbe();
        if (probe == 0) {
            ThreadLocalRandom.localInit();
            probe = ThreadLocalRandom.getProbe();
        }
        while (true) {
            int i = this.runState;
            WorkQueue[] workQueueArr = this.workQueues;
            if (i <= 0 || workQueueArr == null || (length = workQueueArr.length) <= 0) {
                tryInitialize(true);
            } else {
                int i2 = (length - 1) & probe & 126;
                WorkQueue workQueue = workQueueArr[i2];
                if (workQueue == null) {
                    tryCreateExternalQueue(i2);
                } else {
                    int sharedPush = workQueue.sharedPush(forkJoinTask);
                    if (sharedPush >= 0) {
                        if (sharedPush == 0) {
                            signalWork();
                            return;
                        }
                        probe = ThreadLocalRandom.advanceProbe(probe);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private ForkJoinTask externalSubmit(ForkJoinTask forkJoinTask) {
        WorkQueue workQueue;
        if (forkJoinTask != null) {
            Thread currentThread = Thread.currentThread();
            if (currentThread instanceof ForkJoinWorkerThread) {
                ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) currentThread;
                if (forkJoinWorkerThread.pool == this && (workQueue = forkJoinWorkerThread.workQueue) != null) {
                    workQueue.push(forkJoinTask);
                    return forkJoinTask;
                }
            }
            externalPush(forkJoinTask);
            return forkJoinTask;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public final boolean tryExternalUnpush(ForkJoinTask forkJoinTask) {
        int length;
        WorkQueue workQueue;
        int probe = ThreadLocalRandom.getProbe();
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr == null || (length = workQueueArr.length) <= 0 || (workQueue = workQueueArr[probe & (length - 1) & 126]) == null || !workQueue.trySharedUnpush(forkJoinTask)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int externalHelpComplete(CountedCompleter countedCompleter, int i) {
        int length;
        int probe = ThreadLocalRandom.getProbe();
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr == null || (length = workQueueArr.length) <= 0) {
            return 0;
        }
        return helpComplete(workQueueArr[probe & (length - 1) & 126], countedCompleter, i);
    }

    public ForkJoinPool() {
        this(Math.min(32767, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, null, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ForkJoinPool(int r7, java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory r8, java.lang.Thread.UncaughtExceptionHandler r9, boolean r10) {
        /*
            r6 = this;
            checkParallelism(r7)
            checkFactory(r8)
            if (r10 == 0) goto L_0x000b
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x000c
        L_0x000b:
            r10 = 0
        L_0x000c:
            r4 = r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "ForkJoinPool-"
            r10.append(r0)
            int r0 = nextPoolId()
            r10.append(r0)
            java.lang.String r0 = "-worker-"
            r10.append(r0)
            java.lang.String r5 = r10.toString()
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            checkPermission()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.<init>(int, java.util.concurrent.ForkJoinPool$ForkJoinWorkerThreadFactory, java.lang.Thread$UncaughtExceptionHandler, boolean):void");
    }

    private static int checkParallelism(int i) {
        if (i > 0 && i <= 32767) {
            return i;
        }
        throw new IllegalArgumentException();
    }

    private static ForkJoinWorkerThreadFactory checkFactory(ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory) {
        if (forkJoinWorkerThreadFactory != null) {
            return forkJoinWorkerThreadFactory;
        }
        throw new NullPointerException();
    }

    private ForkJoinPool(int i, ForkJoinWorkerThreadFactory forkJoinWorkerThreadFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, int i2, String str) {
        this.workerNamePrefix = str;
        this.factory = forkJoinWorkerThreadFactory;
        this.ueh = uncaughtExceptionHandler;
        this.config = (65535 & i) | i2;
        long j = (long) (-i);
        this.ctl = ((j << 32) & 281470681743360L) | ((j << 48) & -281474976710656L);
    }

    public static ForkJoinPool commonPool() {
        return common;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        ForkJoinTask forkJoinTask;
        if (runnable != null) {
            if (runnable instanceof ForkJoinTask) {
                forkJoinTask = (ForkJoinTask) runnable;
            } else {
                forkJoinTask = new ForkJoinTask.RunnableExecuteAction(runnable);
            }
            externalSubmit(forkJoinTask);
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public ForkJoinTask submit(Runnable runnable) {
        ForkJoinTask forkJoinTask;
        if (runnable != null) {
            if (runnable instanceof ForkJoinTask) {
                forkJoinTask = (ForkJoinTask) runnable;
            } else {
                forkJoinTask = new ForkJoinTask.AdaptedRunnableAction(runnable);
            }
            externalSubmit(forkJoinTask);
            return forkJoinTask;
        }
        throw new NullPointerException();
    }

    public static int getCommonPoolParallelism() {
        return COMMON_PARALLELISM;
    }

    public String toString() {
        AuxState auxState2 = this.auxState;
        if (auxState2 != null) {
            long j = auxState2.stealCount;
        }
        long j2 = this.ctl;
        WorkQueue[] workQueueArr = this.workQueues;
        if (workQueueArr != null) {
            for (int i = 0; i < workQueueArr.length; i++) {
                WorkQueue workQueue = workQueueArr[i];
                if (workQueue != null) {
                    workQueue.queueSize();
                    if ((i & 1) != 0) {
                        int i2 = workQueue.nsteals;
                        workQueue.isApparentlyUnblocked();
                    }
                }
            }
        }
        int i3 = (this.config & 65535) + ((int) (j2 >> 48));
        int i4 = this.runState;
        if ((i4 & 4) == 0 && (i4 & 2) == 0) {
            int i5 = i4 & Integer.MIN_VALUE;
        }
        new StringBuilder();
        super.toString();
        throw null;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return new ForkJoinTask.AdaptedRunnable(runnable, obj);
    }

    static {
        try {
            CTL = U.objectFieldOffset(ForkJoinPool.class.getDeclaredField("ctl"));
            RUNSTATE = U.objectFieldOffset(ForkJoinPool.class.getDeclaredField("runState"));
            ABASE = U.arrayBaseOffset(ForkJoinTask[].class);
            int arrayIndexScale = U.arrayIndexScale(ForkJoinTask[].class);
            if (((arrayIndexScale - 1) & arrayIndexScale) == 0) {
                ASHIFT = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
                int i = 256;
                try {
                    String property = System.getProperty("java.util.concurrent.ForkJoinPool.common.maximumSpares");
                    if (property != null) {
                        i = Integer.parseInt(property);
                    }
                } catch (Exception unused) {
                }
                COMMON_MAX_SPARES = i;
                return;
            }
            throw new Error("array index scale not a power of two");
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.concurrent.ForkJoinPool makeCommonPool() {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ForkJoinPool.makeCommonPool():java.util.concurrent.ForkJoinPool");
    }

    /* access modifiers changed from: private */
    public static final class InnocuousForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {
        private static final AccessControlContext innocuousAcc;

        private InnocuousForkJoinWorkerThreadFactory() {
        }

        static {
            Permissions permissions = new Permissions();
            permissions.add(ForkJoinPool.modifyThreadPermission);
            permissions.add(new RuntimePermission("enableContextClassLoaderOverride"));
            permissions.add(new RuntimePermission("modifyThreadGroup"));
            innocuousAcc = new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, permissions)});
        }

        @Override // java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
        public final ForkJoinWorkerThread newThread(final ForkJoinPool forkJoinPool) {
            return (ForkJoinWorkerThread) AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.util.concurrent.ForkJoinPool.InnocuousForkJoinWorkerThreadFactory.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public ForkJoinWorkerThread run() {
                    return new ForkJoinWorkerThread.InnocuousForkJoinWorkerThread(forkJoinPool);
                }
            }, innocuousAcc);
        }
    }
}
