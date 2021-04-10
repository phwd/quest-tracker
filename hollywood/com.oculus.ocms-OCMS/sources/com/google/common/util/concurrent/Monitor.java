package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
@GwtIncompatible
public final class Monitor {
    @GuardedBy("lock")
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    public static abstract class Guard {
        final Condition condition;
        @Weak
        final Monitor monitor;
        @NullableDecl
        @GuardedBy("monitor.lock")
        Guard next;
        @GuardedBy("monitor.lock")
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        protected Guard(Monitor monitor2) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor2, "monitor");
            this.condition = monitor2.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean z) {
        this.activeGuards = null;
        this.fair = z;
        this.lock = new ReentrantLock(z);
    }

    public void enter() {
        this.lock.lock();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:9:0x001b */
    /* JADX DEBUG: Multi-variable search result rejected for r8v4, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r8v9, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r8v10, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:9|10|14|15|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002b */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enter(long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r7 = this;
            long r8 = toSafeNanos(r8, r10)
            java.util.concurrent.locks.ReentrantLock r10 = r7.lock
            boolean r0 = r7.fair
            r1 = 1
            if (r0 != 0) goto L_0x0012
            boolean r0 = r10.tryLock()
            if (r0 == 0) goto L_0x0012
            return r1
        L_0x0012:
            boolean r0 = java.lang.Thread.interrupted()
            long r2 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0034 }
            r4 = r8
        L_0x001b:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x002b }
            boolean r8 = r10.tryLock(r4, r6)     // Catch:{ InterruptedException -> 0x002b }
            if (r0 == 0) goto L_0x002a
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L_0x002a:
            return r8
        L_0x002b:
            long r4 = remainingNanos(r2, r8)     // Catch:{ all -> 0x0031 }
            r0 = 1
            goto L_0x001b
        L_0x0031:
            r8 = move-exception
            r0 = 1
            goto L_0x0035
        L_0x0034:
            r8 = move-exception
        L_0x0035:
            if (r0 == 0) goto L_0x003e
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L_0x003e:
            goto L_0x0040
        L_0x003f:
            throw r8
        L_0x0040:
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enter(long, java.util.concurrent.TimeUnit):boolean");
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.lock.tryLock(j, timeUnit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lockInterruptibly();
            try {
                if (!guard.isSatisfied()) {
                    await(guard, isHeldByCurrentThread);
                }
            } catch (Throwable th) {
                leave();
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (awaitNanos(r11, r0, r3) != false) goto L_0x0049;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a A[Catch:{ all -> 0x0050, all -> 0x0057 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhen(com.google.common.util.concurrent.Monitor.Guard r11, long r12, java.util.concurrent.TimeUnit r14) throws java.lang.InterruptedException {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhen(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lock();
            try {
                if (!guard.isSatisfied()) {
                    awaitUninterruptibly(guard, isHeldByCurrentThread);
                }
            } catch (Throwable th) {
                leave();
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|13|37|38|39) */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0070, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0071, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0076, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x006a */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0036 A[Catch:{ InterruptedException -> 0x005d, all -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0037 A[Catch:{ InterruptedException -> 0x005d, all -> 0x0058 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r12, long r13, java.util.concurrent.TimeUnit r15) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard, long j, TimeUnit timeUnit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(j, timeUnit)) {
            return false;
        } else {
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock(j, timeUnit)) {
                return false;
            }
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock()) {
                return false;
            }
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long safeNanos = toSafeNanos(j, timeUnit);
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            if (!Thread.interrupted()) {
                return awaitNanos(guard, safeNanos, true);
            }
            throw new InterruptedException();
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0028 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.google.common.util.concurrent.Monitor$Guard] */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.google.common.util.concurrent.Monitor$Guard] */
    /* JADX WARN: Type inference failed for: r8v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        if (r6 == false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        return r8;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0039 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047 A[LOOP:0: B:10:0x0028->B:22:0x0047, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003f A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean waitForUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r8, long r9, java.util.concurrent.TimeUnit r11) {
        /*
            r7 = this;
            long r9 = toSafeNanos(r9, r11)
            com.google.common.util.concurrent.Monitor r11 = r8.monitor
            r0 = 0
            r1 = 1
            if (r11 != r7) goto L_0x000c
            r11 = 1
            goto L_0x000d
        L_0x000c:
            r11 = 0
        L_0x000d:
            java.util.concurrent.locks.ReentrantLock r2 = r7.lock
            boolean r2 = r2.isHeldByCurrentThread()
            r11 = r11 & r2
            if (r11 == 0) goto L_0x0059
            boolean r11 = r8.isSatisfied()
            if (r11 == 0) goto L_0x001d
            return r1
        L_0x001d:
            long r2 = initNanoTime(r9)
            boolean r11 = java.lang.Thread.interrupted()
            r4 = r9
            r6 = r11
            r11 = 1
        L_0x0028:
            boolean r8 = r7.awaitNanos(r8, r4, r11)     // Catch:{ InterruptedException -> 0x0039, all -> 0x0036 }
            if (r6 == 0) goto L_0x0035
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L_0x0035:
            return r8
        L_0x0036:
            r8 = move-exception
            r1 = r6
            goto L_0x004f
        L_0x0039:
            boolean r11 = r8.isSatisfied()     // Catch:{ all -> 0x004e }
            if (r11 == 0) goto L_0x0047
            java.lang.Thread r8 = java.lang.Thread.currentThread()
            r8.interrupt()
            return r1
        L_0x0047:
            long r4 = remainingNanos(r2, r9)
            r11 = 0
            r6 = 1
            goto L_0x0028
        L_0x004e:
            r8 = move-exception
        L_0x004f:
            if (r1 == 0) goto L_0x0058
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L_0x0058:
            throw r8
        L_0x0059:
            java.lang.IllegalMonitorStateException r8 = new java.lang.IllegalMonitorStateException
            r8.<init>()
            goto L_0x0060
        L_0x005f:
            throw r8
        L_0x0060:
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.waitForUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor == this) {
            this.lock.lock();
            try {
                return guard.waiterCount;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    private static long toSafeNanos(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        if (nanos <= 0) {
            return 0;
        }
        if (nanos > 6917529027641081853L) {
            return 6917529027641081853L;
        }
        return nanos;
    }

    private static long initNanoTime(long j) {
        if (j <= 0) {
            return 0;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1;
        }
        return nanoTime;
    }

    private static long remainingNanos(long j, long j2) {
        if (j2 <= 0) {
            return 0;
        }
        return j2 - (System.nanoTime() - j);
    }

    @GuardedBy("lock")
    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    @GuardedBy("lock")
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            throw Throwables.propagate(th);
        }
    }

    @GuardedBy("lock")
    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    @GuardedBy("lock")
    private void beginWaitingFor(Guard guard) {
        int i = guard.waiterCount;
        guard.waiterCount = i + 1;
        if (i == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy("lock")
    private void endWaitingFor(Guard guard) {
        int i = guard.waiterCount - 1;
        guard.waiterCount = i;
        if (i == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    @GuardedBy("lock")
    private void await(Guard guard, boolean z) throws InterruptedException {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private void awaitUninterruptibly(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private boolean awaitNanos(Guard guard, long j, boolean z) throws InterruptedException {
        boolean z2 = true;
        while (j > 0) {
            if (z2) {
                if (z) {
                    try {
                        signalNextWaiter();
                    } catch (Throwable th) {
                        if (!z2) {
                            endWaitingFor(guard);
                        }
                        throw th;
                    }
                }
                beginWaitingFor(guard);
                z2 = false;
            }
            j = guard.condition.awaitNanos(j);
            if (guard.isSatisfied()) {
                if (!z2) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        if (!z2) {
            endWaitingFor(guard);
        }
        return false;
    }
}
