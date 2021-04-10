package android.icu.impl;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ICURWLock {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Stats stats = null;

    public static final class Stats {
        public int _mrc;
        public int _rc;
        public int _wc;
        public int _wrc;
        public int _wwc;
    }

    public void acquireRead() {
        if (this.stats != null) {
            synchronized (this) {
                this.stats._rc++;
                if (this.rwl.getReadLockCount() > 0) {
                    this.stats._mrc++;
                }
                if (this.rwl.isWriteLocked()) {
                    this.stats._wrc++;
                }
            }
        }
        this.rwl.readLock().lock();
    }

    public void releaseRead() {
        this.rwl.readLock().unlock();
    }

    public void acquireWrite() {
        if (this.stats != null) {
            synchronized (this) {
                this.stats._wc++;
                if (this.rwl.getReadLockCount() > 0 || this.rwl.isWriteLocked()) {
                    this.stats._wwc++;
                }
            }
        }
        this.rwl.writeLock().lock();
    }

    public void releaseWrite() {
        this.rwl.writeLock().unlock();
    }
}
