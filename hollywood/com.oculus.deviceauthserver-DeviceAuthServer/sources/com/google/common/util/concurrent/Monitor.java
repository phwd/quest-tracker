package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;

@Beta
public final class Monitor {
    @GuardedBy("lock")
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;
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

    public Monitor(boolean fair2) {
        this.activeGuards = null;
        this.fair = fair2;
        this.lock = new ReentrantLock(fair2);
    }

    public void enter() {
        this.lock.lock();
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enter(long time, TimeUnit unit) {
        boolean tryLock;
        long timeoutNanos = unit.toNanos(time);
        ReentrantLock lock2 = this.lock;
        if (!this.fair && lock2.tryLock()) {
            return true;
        }
        long deadline = System.nanoTime() + timeoutNanos;
        boolean interrupted = Thread.interrupted();
        while (true) {
            try {
                tryLock = lock2.tryLock(timeoutNanos, TimeUnit.NANOSECONDS);
                break;
            } catch (InterruptedException e) {
                interrupted = true;
                timeoutNanos = deadline - System.nanoTime();
            } catch (Throwable th) {
                if (1 != 0) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return tryLock;
    }

    public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
            lock2.lockInterruptibly();
            boolean satisfied = false;
            try {
                if (!guard.isSatisfied()) {
                    await(guard, signalBeforeWaiting);
                }
                satisfied = true;
            } finally {
                if (!satisfied) {
                    leave();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
            lock2.lock();
            boolean satisfied = false;
            try {
                if (!guard.isSatisfied()) {
                    awaitUninterruptibly(guard, signalBeforeWaiting);
                }
                satisfied = true;
            } finally {
                if (!satisfied) {
                    leave();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterWhen(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long timeoutNanos = unit.toNanos(time);
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            boolean reentrant = lock2.isHeldByCurrentThread();
            boolean satisfied = false;
            if (this.fair || !lock2.tryLock()) {
                long deadline = System.nanoTime() + timeoutNanos;
                if (!lock2.tryLock(time, unit)) {
                    return false;
                }
                timeoutNanos = deadline - System.nanoTime();
            }
            try {
                if (guard.isSatisfied() || awaitNanos(guard, timeoutNanos, reentrant)) {
                    satisfied = true;
                }
                if (!satisfied) {
                    if (0 != 0 && !reentrant) {
                        try {
                            signalNextWaiter();
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    lock2.unlock();
                }
                return satisfied;
            } catch (Throwable th2) {
                if (0 == 0) {
                    if (1 != 0 && !reentrant) {
                        signalNextWaiter();
                    }
                }
                throw th2;
            } finally {
                lock2.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0061 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r18, long r19, java.util.concurrent.TimeUnit r21) {
        /*
        // Method dump skipped, instructions count: 141
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            boolean satisfied = false;
            try {
                satisfied = guard.isSatisfied();
                return satisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            lock2.lockInterruptibly();
            boolean satisfied = false;
            try {
                satisfied = guard.isSatisfied();
                return satisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard, long time, TimeUnit unit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(time, unit)) {
            return false;
        } else {
            boolean satisfied = false;
            try {
                satisfied = guard.isSatisfied();
                return satisfied;
            } finally {
                if (!satisfied) {
                    this.lock.unlock();
                }
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            if (!lock2.tryLock(time, unit)) {
                return false;
            }
            boolean satisfied = false;
            try {
                satisfied = guard.isSatisfied();
                return satisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            if (!lock2.tryLock()) {
                return false;
            }
            boolean satisfied = false;
            try {
                satisfied = guard.isSatisfied();
                return satisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
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

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long timeoutNanos = unit.toNanos(time);
        if ((guard.monitor == this) && this.lock.isHeldByCurrentThread()) {
            return guard.isSatisfied() || awaitNanos(guard, timeoutNanos, true);
        }
        throw new IllegalMonitorStateException();
    }

    public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long timeoutNanos = unit.toNanos(time);
        boolean z = true;
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            boolean signalBeforeWaiting = true;
            long deadline = System.nanoTime() + timeoutNanos;
            boolean interrupted = Thread.interrupted();
            while (true) {
                try {
                    boolean awaitNanos = awaitNanos(guard, timeoutNanos, signalBeforeWaiting);
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return awaitNanos;
                } catch (InterruptedException e) {
                    interrupted = true;
                    if (guard.isSatisfied()) {
                        if (1 != 0) {
                            Thread.currentThread().interrupt();
                        }
                        return z;
                    }
                    signalBeforeWaiting = false;
                    timeoutNanos = deadline - System.nanoTime();
                } catch (Throwable th) {
                    if (1 != 0) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
        }
    }

    public void leave() {
        ReentrantLock lock2 = this.lock;
        try {
            if (lock2.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            lock2.unlock();
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
        } catch (Throwable throwable) {
            signalAllWaiters();
            throw Throwables.propagate(throwable);
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
        int waiters = guard.waiterCount;
        guard.waiterCount = waiters + 1;
        if (waiters == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy("lock")
    private void endWaitingFor(Guard guard) {
        int waiters = guard.waiterCount - 1;
        guard.waiterCount = waiters;
        if (waiters == 0) {
            Guard p = this.activeGuards;
            Guard pred = null;
            while (p != guard) {
                pred = p;
                p = p.next;
            }
            if (pred == null) {
                this.activeGuards = p.next;
            } else {
                pred.next = p.next;
            }
            p.next = null;
        }
    }

    @GuardedBy("lock")
    private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
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
    private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
        if (signalBeforeWaiting) {
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
    private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (nanos >= 0) {
            try {
                nanos = guard.condition.awaitNanos(nanos);
                if (guard.isSatisfied()) {
                    return true;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
        endWaitingFor(guard);
        return false;
    }
}
