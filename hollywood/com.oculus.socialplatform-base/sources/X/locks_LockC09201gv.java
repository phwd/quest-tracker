package X;

import androidx.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* renamed from: X.1gv  reason: invalid class name and case insensitive filesystem */
public final class locks.LockC09201gv implements Lock {
    public final void lock() {
    }

    @Override // java.util.concurrent.locks.Lock
    public final void lockInterruptibly() throws InterruptedException {
    }

    public final boolean tryLock() {
        return true;
    }

    @Override // java.util.concurrent.locks.Lock
    public final boolean tryLock(long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return true;
    }

    public final void unlock() {
    }

    @NonNull
    public final Condition newCondition() {
        throw new UnsupportedOperationException("Should not be called");
    }
}
