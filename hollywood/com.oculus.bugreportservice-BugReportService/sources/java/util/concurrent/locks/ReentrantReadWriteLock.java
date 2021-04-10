package java.util.concurrent.locks;

import java.io.ObjectInputStream;
import java.io.Serializable;
import sun.misc.Unsafe;

public class ReentrantReadWriteLock implements ReadWriteLock, Serializable {
    private static final long TID;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = -6992448646407690164L;
    private final ReadLock readerLock;
    final Sync sync;
    private final WriteLock writerLock;

    public ReentrantReadWriteLock() {
        this(false);
    }

    public ReentrantReadWriteLock(boolean z) {
        this.sync = z ? new FairSync() : new NonfairSync();
        this.readerLock = new ReadLock(this);
        this.writerLock = new WriteLock(this);
    }

    public WriteLock writeLock() {
        return this.writerLock;
    }

    @Override // java.util.concurrent.locks.ReadWriteLock
    public ReadLock readLock() {
        return this.readerLock;
    }

    static abstract class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 6317671515068378041L;
        private transient HoldCounter cachedHoldCounter;
        private transient Thread firstReader;
        private transient int firstReaderHoldCount;
        private transient ThreadLocalHoldCounter readHolds = new ThreadLocalHoldCounter();

        static int exclusiveCount(int i) {
            return i & 65535;
        }

        static int sharedCount(int i) {
            return i >>> 16;
        }

        /* access modifiers changed from: package-private */
        public abstract boolean readerShouldBlock();

        /* access modifiers changed from: package-private */
        public abstract boolean writerShouldBlock();

        /* access modifiers changed from: package-private */
        public static final class HoldCounter {
            int count;
            final long tid = ReentrantReadWriteLock.getThreadId(Thread.currentThread());

            HoldCounter() {
            }
        }

        /* access modifiers changed from: package-private */
        public static final class ThreadLocalHoldCounter extends ThreadLocal {
            ThreadLocalHoldCounter() {
            }

            @Override // java.lang.ThreadLocal
            public HoldCounter initialValue() {
                return new HoldCounter();
            }
        }

        Sync() {
            setState(getState());
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryRelease(int i) {
            if (isHeldExclusively()) {
                int state = getState() - i;
                boolean z = exclusiveCount(state) == 0;
                if (z) {
                    setExclusiveOwnerThread(null);
                }
                setState(state);
                return z;
            }
            throw new IllegalMonitorStateException();
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryAcquire(int i) {
            Thread currentThread = Thread.currentThread();
            int state = getState();
            int exclusiveCount = exclusiveCount(state);
            if (state != 0) {
                if (exclusiveCount == 0 || currentThread != getExclusiveOwnerThread()) {
                    return false;
                }
                if (exclusiveCount + exclusiveCount(i) <= 65535) {
                    setState(state + i);
                    return true;
                }
                throw new Error("Maximum lock count exceeded");
            } else if (writerShouldBlock() || !compareAndSetState(state, i + state)) {
                return false;
            } else {
                setExclusiveOwnerThread(currentThread);
                return true;
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean tryReleaseShared(int i) {
            int state;
            int i2;
            Thread currentThread = Thread.currentThread();
            if (this.firstReader == currentThread) {
                int i3 = this.firstReaderHoldCount;
                if (i3 == 1) {
                    this.firstReader = null;
                } else {
                    this.firstReaderHoldCount = i3 - 1;
                }
            } else {
                HoldCounter holdCounter = this.cachedHoldCounter;
                if (holdCounter == null || holdCounter.tid != ReentrantReadWriteLock.getThreadId(currentThread)) {
                    holdCounter = (HoldCounter) this.readHolds.get();
                }
                int i4 = holdCounter.count;
                if (i4 <= 1) {
                    this.readHolds.remove();
                    if (i4 <= 0) {
                        throw unmatchedUnlockException();
                    }
                }
                holdCounter.count--;
            }
            do {
                state = getState();
                i2 = state - 65536;
            } while (!compareAndSetState(state, i2));
            if (i2 == 0) {
                return true;
            }
            return false;
        }

        private IllegalMonitorStateException unmatchedUnlockException() {
            return new IllegalMonitorStateException("attempt to unlock read lock, not locked by current thread");
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final int tryAcquireShared(int i) {
            Thread currentThread = Thread.currentThread();
            int state = getState();
            if (exclusiveCount(state) != 0 && getExclusiveOwnerThread() != currentThread) {
                return -1;
            }
            int sharedCount = sharedCount(state);
            if (readerShouldBlock() || sharedCount >= 65535 || !compareAndSetState(state, 65536 + state)) {
                return fullTryAcquireShared(currentThread);
            }
            if (sharedCount == 0) {
                this.firstReader = currentThread;
                this.firstReaderHoldCount = 1;
            } else if (this.firstReader == currentThread) {
                this.firstReaderHoldCount++;
            } else {
                HoldCounter holdCounter = this.cachedHoldCounter;
                if (holdCounter == null || holdCounter.tid != ReentrantReadWriteLock.getThreadId(currentThread)) {
                    holdCounter = (HoldCounter) this.readHolds.get();
                    this.cachedHoldCounter = holdCounter;
                } else if (holdCounter.count == 0) {
                    this.readHolds.set(holdCounter);
                }
                holdCounter.count++;
            }
            return 1;
        }

        /* access modifiers changed from: package-private */
        public final int fullTryAcquireShared(Thread thread) {
            int state;
            HoldCounter holdCounter = null;
            do {
                state = getState();
                if (exclusiveCount(state) != 0) {
                    if (getExclusiveOwnerThread() != thread) {
                        return -1;
                    }
                } else if (readerShouldBlock() && this.firstReader != thread) {
                    if (holdCounter == null && ((holdCounter = this.cachedHoldCounter) == null || holdCounter.tid != ReentrantReadWriteLock.getThreadId(thread))) {
                        holdCounter = (HoldCounter) this.readHolds.get();
                        if (holdCounter.count == 0) {
                            this.readHolds.remove();
                        }
                    }
                    if (holdCounter.count == 0) {
                        return -1;
                    }
                }
                if (sharedCount(state) == 65535) {
                    throw new Error("Maximum lock count exceeded");
                }
            } while (!compareAndSetState(state, 65536 + state));
            if (sharedCount(state) == 0) {
                this.firstReader = thread;
                this.firstReaderHoldCount = 1;
            } else if (this.firstReader == thread) {
                this.firstReaderHoldCount++;
            } else {
                if (holdCounter == null) {
                    holdCounter = this.cachedHoldCounter;
                }
                if (holdCounter == null || holdCounter.tid != ReentrantReadWriteLock.getThreadId(thread)) {
                    holdCounter = (HoldCounter) this.readHolds.get();
                } else if (holdCounter.count == 0) {
                    this.readHolds.set(holdCounter);
                }
                holdCounter.count++;
                this.cachedHoldCounter = holdCounter;
            }
            return 1;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        /* access modifiers changed from: package-private */
        public final Thread getOwner() {
            if (exclusiveCount(getState()) == 0) {
                return null;
            }
            return getExclusiveOwnerThread();
        }

        /* access modifiers changed from: package-private */
        public final int getReadLockCount() {
            return sharedCount(getState());
        }

        /* access modifiers changed from: package-private */
        public final boolean isWriteLocked() {
            return exclusiveCount(getState()) != 0;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            throw null;
        }

        /* access modifiers changed from: package-private */
        public final int getCount() {
            return getState();
        }
    }

    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = -8159625535654395037L;

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.Sync
        public final boolean writerShouldBlock() {
            return false;
        }

        NonfairSync() {
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.Sync
        public final boolean readerShouldBlock() {
            return apparentlyFirstQueuedIsExclusive();
        }
    }

    static final class FairSync extends Sync {
        private static final long serialVersionUID = -2274990926593161451L;

        FairSync() {
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.Sync
        public final boolean writerShouldBlock() {
            return hasQueuedPredecessors();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.Sync
        public final boolean readerShouldBlock() {
            return hasQueuedPredecessors();
        }
    }

    public static class ReadLock implements Lock, Serializable {
        private static final long serialVersionUID = -5992448646407690164L;
        private final Sync sync;

        protected ReadLock(ReentrantReadWriteLock reentrantReadWriteLock) {
            this.sync = reentrantReadWriteLock.sync;
        }

        @Override // java.util.concurrent.locks.Lock
        public void lock() {
            this.sync.acquireShared(1);
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
            this.sync.releaseShared(1);
        }

        public String toString() {
            this.sync.getReadLockCount();
            new StringBuilder();
            super.toString();
            throw null;
        }
    }

    public static class WriteLock implements Lock, Serializable {
        private static final long serialVersionUID = -4992448646407690164L;
        private final Sync sync;

        protected WriteLock(ReentrantReadWriteLock reentrantReadWriteLock) {
            this.sync = reentrantReadWriteLock.sync;
        }

        @Override // java.util.concurrent.locks.Lock
        public void lock() {
            this.sync.acquire(1);
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
            this.sync.release(1);
        }

        public String toString() {
            this.sync.getOwner();
            new StringBuilder();
            super.toString();
            throw null;
        }
    }

    public int getReadLockCount() {
        return this.sync.getReadLockCount();
    }

    public boolean isWriteLocked() {
        return this.sync.isWriteLocked();
    }

    public String toString() {
        int count = this.sync.getCount();
        Sync.exclusiveCount(count);
        Sync.sharedCount(count);
        new StringBuilder();
        super.toString();
        throw null;
    }

    static final long getThreadId(Thread thread) {
        return U.getLongVolatile(thread, TID);
    }

    static {
        try {
            TID = U.objectFieldOffset(Thread.class.getDeclaredField("tid"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
