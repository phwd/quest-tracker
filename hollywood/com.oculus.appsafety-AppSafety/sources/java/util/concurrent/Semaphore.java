package java.util.concurrent;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Semaphore implements Serializable {
    private static final long serialVersionUID = -3222578661600680210L;
    private final Sync sync;

    static abstract class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1192457210091910933L;

        Sync(int permits) {
            setState(permits);
        }

        /* access modifiers changed from: package-private */
        public final int getPermits() {
            return getState();
        }

        /* access modifiers changed from: package-private */
        public final int nonfairTryAcquireShared(int acquires) {
            int available;
            int remaining;
            do {
                available = getState();
                remaining = available - acquires;
                if (remaining < 0) {
                    break;
                }
            } while (!compareAndSetState(available, remaining));
            return remaining;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryReleaseShared(int releases) {
            int current;
            int next;
            do {
                current = getState();
                next = current + releases;
                if (next < current) {
                    throw new Error("Maximum permit count exceeded");
                }
            } while (!compareAndSetState(current, next));
            return true;
        }

        /* access modifiers changed from: package-private */
        public final void reducePermits(int reductions) {
            int current;
            int next;
            do {
                current = getState();
                next = current - reductions;
                if (next > current) {
                    throw new Error("Permit count underflow");
                }
            } while (!compareAndSetState(current, next));
        }

        /* access modifiers changed from: package-private */
        public final int drainPermits() {
            int current;
            do {
                current = getState();
                if (current == 0) {
                    break;
                }
            } while (!compareAndSetState(current, 0));
            return current;
        }
    }

    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = -2694183684443567898L;

        NonfairSync(int permits) {
            super(permits);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public int tryAcquireShared(int acquires) {
            return nonfairTryAcquireShared(acquires);
        }
    }

    static final class FairSync extends Sync {
        private static final long serialVersionUID = 2014338818796000944L;

        FairSync(int permits) {
            super(permits);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:3:0x0008  */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int tryAcquireShared(int r4) {
            /*
                r3 = this;
            L_0x0000:
                boolean r0 = r3.hasQueuedPredecessors()
                if (r0 == 0) goto L_0x0008
                r0 = -1
                return r0
            L_0x0008:
                int r0 = r3.getState()
                int r1 = r0 - r4
                if (r1 < 0) goto L_0x0018
                boolean r2 = r3.compareAndSetState(r0, r1)
                if (r2 == 0) goto L_0x0017
                goto L_0x0018
            L_0x0017:
                goto L_0x0000
            L_0x0018:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.Semaphore.FairSync.tryAcquireShared(int):int");
        }
    }

    public Semaphore(int permits) {
        this.sync = new NonfairSync(permits);
    }

    public Semaphore(int permits, boolean fair) {
        this.sync = fair ? new FairSync(permits) : new NonfairSync(permits);
    }

    public void acquire() throws InterruptedException {
        this.sync.acquireSharedInterruptibly(1);
    }

    public void acquireUninterruptibly() {
        this.sync.acquireShared(1);
    }

    public boolean tryAcquire() {
        return this.sync.nonfairTryAcquireShared(1) >= 0;
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException {
        return this.sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }

    public void release() {
        this.sync.releaseShared(1);
    }

    public void acquire(int permits) throws InterruptedException {
        if (permits >= 0) {
            this.sync.acquireSharedInterruptibly(permits);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void acquireUninterruptibly(int permits) {
        if (permits >= 0) {
            this.sync.acquireShared(permits);
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean tryAcquire(int permits) {
        if (permits >= 0) {
            return this.sync.nonfairTryAcquireShared(permits) >= 0;
        }
        throw new IllegalArgumentException();
    }

    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException {
        if (permits >= 0) {
            return this.sync.tryAcquireSharedNanos(permits, unit.toNanos(timeout));
        }
        throw new IllegalArgumentException();
    }

    public void release(int permits) {
        if (permits >= 0) {
            this.sync.releaseShared(permits);
            return;
        }
        throw new IllegalArgumentException();
    }

    public int availablePermits() {
        return this.sync.getPermits();
    }

    public int drainPermits() {
        return this.sync.drainPermits();
    }

    /* access modifiers changed from: protected */
    public void reducePermits(int reduction) {
        if (reduction >= 0) {
            this.sync.reducePermits(reduction);
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean isFair() {
        return this.sync instanceof FairSync;
    }

    public final boolean hasQueuedThreads() {
        return this.sync.hasQueuedThreads();
    }

    public final int getQueueLength() {
        return this.sync.getQueueLength();
    }

    /* access modifiers changed from: protected */
    public Collection<Thread> getQueuedThreads() {
        return this.sync.getQueuedThreads();
    }

    public String toString() {
        return super.toString() + "[Permits = " + this.sync.getPermits() + "]";
    }
}
