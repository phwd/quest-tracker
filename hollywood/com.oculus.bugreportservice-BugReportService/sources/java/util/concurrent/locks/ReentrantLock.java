package java.util.concurrent.locks;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ReentrantLock implements Lock, Serializable {
    private static final long serialVersionUID = 7373984872572414699L;
    private final Sync sync;

    /* access modifiers changed from: package-private */
    public static abstract class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -5179523762034025860L;

        /* access modifiers changed from: package-private */
        public abstract void lock();

        Sync() {
        }

        /* access modifiers changed from: package-private */
        public final boolean nonfairTryAcquire(int i) {
            Thread currentThread = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (compareAndSetState(0, i)) {
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            } else if (currentThread == getExclusiveOwnerThread()) {
                int i2 = state + i;
                if (i2 >= 0) {
                    setState(i2);
                    return true;
                }
                throw new Error("Maximum lock count exceeded");
            }
            return false;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryRelease(int i) {
            int state = getState() - i;
            if (Thread.currentThread() == getExclusiveOwnerThread()) {
                boolean z = false;
                if (state == 0) {
                    z = true;
                    setExclusiveOwnerThread(null);
                }
                setState(state);
                return z;
            }
            throw new IllegalMonitorStateException();
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        /* access modifiers changed from: package-private */
        public final AbstractQueuedSynchronizer.ConditionObject newCondition() {
            return new AbstractQueuedSynchronizer.ConditionObject();
        }

        /* access modifiers changed from: package-private */
        public final Thread getOwner() {
            if (getState() == 0) {
                return null;
            }
            return getExclusiveOwnerThread();
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            throw null;
        }
    }

    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        NonfairSync() {
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.locks.ReentrantLock.Sync
        public final void lock() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                acquire(1);
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryAcquire(int i) {
            return nonfairTryAcquire(i);
        }
    }

    static final class FairSync extends Sync {
        private static final long serialVersionUID = -3000897897090466540L;

        FairSync() {
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.locks.ReentrantLock.Sync
        public final void lock() {
            acquire(1);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryAcquire(int i) {
            Thread currentThread = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (!hasQueuedPredecessors() && compareAndSetState(0, i)) {
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            } else if (currentThread == getExclusiveOwnerThread()) {
                int i2 = state + i;
                if (i2 >= 0) {
                    setState(i2);
                    return true;
                }
                throw new Error("Maximum lock count exceeded");
            }
            return false;
        }
    }

    public ReentrantLock() {
        this.sync = new NonfairSync();
    }

    public ReentrantLock(boolean z) {
        this.sync = z ? new FairSync() : new NonfairSync();
    }

    @Override // java.util.concurrent.locks.Lock
    public void lock() {
        this.sync.lock();
    }

    public void lockInterruptibly() {
        this.sync.acquireInterruptibly(1);
    }

    public boolean tryLock() {
        return this.sync.nonfairTryAcquire(1);
    }

    @Override // java.util.concurrent.locks.Lock
    public void unlock() {
        this.sync.release(1);
    }

    public Condition newCondition() {
        return this.sync.newCondition();
    }

    public String toString() {
        this.sync.getOwner();
        new StringBuilder();
        super.toString();
        throw null;
    }
}
