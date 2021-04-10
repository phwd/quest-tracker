package java.util.concurrent.locks;

import android.support.v4.app.NotificationCompat;
import com.oculus.bugreporter.Constants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import sun.misc.Unsafe;

public class StampedLock implements Serializable {
    private static final long ABITS = 255;
    private static final int CANCELLED = 1;
    private static final int HEAD_SPINS = (NCPU > 1 ? 1024 : 0);
    private static final long INTERRUPTED = 1;
    private static final int LG_READERS = 7;
    private static final int MAX_HEAD_SPINS;
    private static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final long ORIGIN = 256;
    private static final int OVERFLOW_YIELD_RATE = 7;
    private static final long PARKBLOCKER;
    private static final long RBITS = 127;
    private static final long RFULL = 126;
    private static final int RMODE = 0;
    private static final long RUNIT = 1;
    private static final long SBITS = -128;
    private static final int SPINS = (NCPU > 1 ? 64 : 0);
    private static final long STATE;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final int WAITING = -1;
    private static final long WBIT = 128;
    private static final long WCOWAIT;
    private static final long WHEAD;
    private static final int WMODE = 1;
    private static final long WNEXT;
    private static final long WSTATUS;
    private static final long WTAIL;
    private static final long serialVersionUID = -6001602636862214147L;
    transient ReadLockView readLockView;
    transient ReadWriteLockView readWriteLockView;
    private transient int readerOverflow;
    private volatile transient long state = 256;
    private volatile transient WNode whead;
    transient WriteLockView writeLockView;
    private volatile transient WNode wtail;

    static {
        int i = 0;
        if (NCPU > 1) {
            i = 65536;
        }
        MAX_HEAD_SPINS = i;
        try {
            STATE = U.objectFieldOffset(StampedLock.class.getDeclaredField(Constants.EXTRA_STATE));
            WHEAD = U.objectFieldOffset(StampedLock.class.getDeclaredField("whead"));
            WTAIL = U.objectFieldOffset(StampedLock.class.getDeclaredField("wtail"));
            WSTATUS = U.objectFieldOffset(WNode.class.getDeclaredField(NotificationCompat.CATEGORY_STATUS));
            WNEXT = U.objectFieldOffset(WNode.class.getDeclaredField("next"));
            WCOWAIT = U.objectFieldOffset(WNode.class.getDeclaredField("cowait"));
            PARKBLOCKER = U.objectFieldOffset(Thread.class.getDeclaredField("parkBlocker"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WNode {
        volatile WNode cowait;
        final int mode;
        volatile WNode next;
        volatile WNode prev;
        volatile int status;
        volatile Thread thread;

        WNode(int m, WNode p) {
            this.mode = m;
            this.prev = p;
        }
    }

    public long writeLock() {
        long s = this.state;
        if ((s & ABITS) == 0) {
            long j = s + 128;
            if (U.compareAndSwapLong(this, STATE, s, j)) {
                return j;
            }
        }
        return acquireWrite(false, 0);
    }

    public long tryWriteLock() {
        long s = this.state;
        if ((s & ABITS) == 0) {
            long j = s + 128;
            if (U.compareAndSwapLong(this, STATE, s, j)) {
                return j;
            }
        }
        return 0;
    }

    public long tryWriteLock(long time, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(time);
        if (!Thread.interrupted()) {
            long next = tryWriteLock();
            if (next != 0) {
                return next;
            }
            if (nanos <= 0) {
                return 0;
            }
            long nanoTime = System.nanoTime() + nanos;
            long deadline = nanoTime;
            if (nanoTime == 0) {
                deadline = 1;
            }
            long next2 = acquireWrite(true, deadline);
            if (next2 != 1) {
                return next2;
            }
        }
        throw new InterruptedException();
    }

    public long writeLockInterruptibly() throws InterruptedException {
        if (!Thread.interrupted()) {
            long next = acquireWrite(true, 0);
            if (next != 1) {
                return next;
            }
        }
        throw new InterruptedException();
    }

    public long readLock() {
        long s = this.state;
        if (this.whead == this.wtail && (ABITS & s) < RFULL) {
            long j = s + 1;
            if (U.compareAndSwapLong(this, STATE, s, j)) {
                return j;
            }
        }
        return acquireRead(false, 0);
    }

    public long tryReadLock() {
        while (true) {
            long s = this.state;
            long m = s & ABITS;
            if (m == 128) {
                return 0;
            }
            if (m < RFULL) {
                long next = s + 1;
                if (U.compareAndSwapLong(this, STATE, s, next)) {
                    return next;
                }
            } else {
                long next2 = tryIncReaderOverflow(s);
                if (next2 != 0) {
                    return next2;
                }
            }
        }
    }

    public long tryReadLock(long time, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(time);
        if (!Thread.interrupted()) {
            long s = this.state;
            long m = s & ABITS;
            if (m != 128) {
                if (m < RFULL) {
                    long next = s + 1;
                    if (U.compareAndSwapLong(this, STATE, s, next)) {
                        return next;
                    }
                } else {
                    long next2 = tryIncReaderOverflow(s);
                    if (next2 != 0) {
                        return next2;
                    }
                }
            }
            if (nanos <= 0) {
                return 0;
            }
            long nanoTime = System.nanoTime() + nanos;
            long deadline = nanoTime;
            if (nanoTime == 0) {
                deadline = 1;
            }
            long next3 = acquireRead(true, deadline);
            if (next3 != 1) {
                return next3;
            }
        }
        throw new InterruptedException();
    }

    public long readLockInterruptibly() throws InterruptedException {
        if (!Thread.interrupted()) {
            long next = acquireRead(true, 0);
            if (next != 1) {
                return next;
            }
        }
        throw new InterruptedException();
    }

    public long tryOptimisticRead() {
        long s = this.state;
        if ((s & 128) == 0) {
            return s & SBITS;
        }
        return 0;
    }

    public boolean validate(long stamp) {
        U.loadFence();
        return (stamp & SBITS) == (SBITS & this.state);
    }

    public void unlockWrite(long stamp) {
        if (this.state != stamp || (stamp & 128) == 0) {
            throw new IllegalMonitorStateException();
        }
        long stamp2 = 128 + stamp;
        U.putLongVolatile(this, STATE, stamp2 == 0 ? 256 : stamp2);
        WNode h = this.whead;
        if (h != null && h.status != 0) {
            release(h);
        }
    }

    public void unlockRead(long stamp) {
        WNode h;
        while (true) {
            long s = this.state;
            if ((s & SBITS) != (stamp & SBITS) || (stamp & ABITS) == 0) {
                break;
            }
            long m = ABITS & s;
            if (m == 0 || m == 128) {
                break;
            } else if (m < RFULL) {
                if (U.compareAndSwapLong(this, STATE, s, s - 1)) {
                    if (m == 1 && (h = this.whead) != null && h.status != 0) {
                        release(h);
                        return;
                    }
                    return;
                }
            } else if (tryDecReaderOverflow(s) != 0) {
                return;
            }
        }
        throw new IllegalMonitorStateException();
    }

    public void unlock(long stamp) {
        WNode h;
        long a = stamp & ABITS;
        while (true) {
            long s = this.state;
            if ((s & SBITS) != (stamp & SBITS)) {
                break;
            }
            long m = s & ABITS;
            if (m != 0) {
                if (m != 128) {
                    if (a == 0 || a >= 128) {
                        break;
                    } else if (m < RFULL) {
                        if (U.compareAndSwapLong(this, STATE, s, s - 1)) {
                            if (m == 1 && (h = this.whead) != null && h.status != 0) {
                                release(h);
                                return;
                            }
                            return;
                        }
                    } else if (tryDecReaderOverflow(s) != 0) {
                        return;
                    }
                } else if (a == m) {
                    long s2 = 128 + s;
                    U.putLongVolatile(this, STATE, s2 == 0 ? 256 : s2);
                    WNode h2 = this.whead;
                    if (h2 != null && h2.status != 0) {
                        release(h2);
                        return;
                    }
                    return;
                }
            } else {
                break;
            }
        }
        throw new IllegalMonitorStateException();
    }

    public long tryConvertToWriteLock(long stamp) {
        long a = stamp & ABITS;
        while (true) {
            long s = this.state;
            if ((s & SBITS) != (stamp & SBITS)) {
                break;
            }
            long m = s & ABITS;
            if (m != 0) {
                if (m != 128) {
                    if (m != 1 || a == 0) {
                        break;
                    }
                    long next = (s - 1) + 128;
                    if (U.compareAndSwapLong(this, STATE, s, next)) {
                        return next;
                    }
                } else if (a != m) {
                    return 0;
                } else {
                    return stamp;
                }
            } else if (a != 0) {
                break;
            } else {
                long next2 = s + 128;
                if (U.compareAndSwapLong(this, STATE, s, next2)) {
                    return next2;
                }
            }
        }
        return 0;
    }

    public long tryConvertToReadLock(long stamp) {
        long a = stamp & ABITS;
        while (true) {
            long s = this.state;
            if ((s & SBITS) != (stamp & SBITS)) {
                break;
            }
            long m = s & ABITS;
            if (m == 0) {
                if (a != 0) {
                    break;
                } else if (m < RFULL) {
                    long next = s + 1;
                    if (U.compareAndSwapLong(this, STATE, s, next)) {
                        return next;
                    }
                } else {
                    long next2 = tryIncReaderOverflow(s);
                    if (next2 != 0) {
                        return next2;
                    }
                }
            } else if (m == 128) {
                if (a == m) {
                    long next3 = 129 + s;
                    U.putLongVolatile(this, STATE, next3);
                    WNode h = this.whead;
                    if (!(h == null || h.status == 0)) {
                        release(h);
                    }
                    return next3;
                }
            } else if (a == 0 || a >= 128) {
                return 0;
            } else {
                return stamp;
            }
        }
        return 0;
    }

    public long tryConvertToOptimisticRead(long stamp) {
        WNode h;
        long a = stamp & ABITS;
        U.loadFence();
        while (true) {
            long s = this.state;
            if ((s & SBITS) == (stamp & SBITS)) {
                long m = s & ABITS;
                if (m != 0) {
                    if (m != 128) {
                        if (a == 0 || a >= 128) {
                            break;
                        } else if (m < RFULL) {
                            long next = s - 1;
                            if (U.compareAndSwapLong(this, STATE, s, next)) {
                                if (!(m != 1 || (h = this.whead) == null || h.status == 0)) {
                                    release(h);
                                }
                                return next & SBITS;
                            }
                        } else {
                            long next2 = tryDecReaderOverflow(s);
                            if (next2 != 0) {
                                return next2 & SBITS;
                            }
                        }
                    } else if (a == m) {
                        Unsafe unsafe = U;
                        long j = STATE;
                        long s2 = 128 + s;
                        long next3 = s2 == 0 ? 256 : s2;
                        unsafe.putLongVolatile(this, j, next3);
                        WNode h2 = this.whead;
                        if (!(h2 == null || h2.status == 0)) {
                            release(h2);
                        }
                        return next3;
                    }
                } else if (a != 0) {
                    return 0;
                } else {
                    return s;
                }
            } else {
                break;
            }
        }
        return 0;
    }

    public boolean tryUnlockWrite() {
        long s = this.state;
        if ((s & 128) == 0) {
            return false;
        }
        long s2 = 128 + s;
        U.putLongVolatile(this, STATE, s2 == 0 ? 256 : s2);
        WNode h = this.whead;
        if (h == null || h.status == 0) {
            return true;
        }
        release(h);
        return true;
    }

    public boolean tryUnlockRead() {
        WNode h;
        while (true) {
            long s = this.state;
            long m = s & ABITS;
            if (m == 0 || m >= 128) {
                return false;
            }
            if (m < RFULL) {
                if (U.compareAndSwapLong(this, STATE, s, s - 1)) {
                    if (!(m != 1 || (h = this.whead) == null || h.status == 0)) {
                        release(h);
                    }
                    return true;
                }
            } else if (tryDecReaderOverflow(s) != 0) {
                return true;
            }
        }
    }

    private int getReadLockCount(long s) {
        long j = RBITS & s;
        long readers = j;
        if (j >= RFULL) {
            readers = ((long) this.readerOverflow) + RFULL;
        }
        return (int) readers;
    }

    public boolean isWriteLocked() {
        return (this.state & 128) != 0;
    }

    public boolean isReadLocked() {
        return (this.state & RBITS) != 0;
    }

    public int getReadLockCount() {
        return getReadLockCount(this.state);
    }

    public String toString() {
        String str;
        long s = this.state;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if ((ABITS & s) == 0) {
            str = "[Unlocked]";
        } else if ((128 & s) != 0) {
            str = "[Write-locked]";
        } else {
            str = "[Read-locks:" + getReadLockCount(s) + "]";
        }
        sb.append(str);
        return sb.toString();
    }

    public Lock asReadLock() {
        ReadLockView v = this.readLockView;
        if (v != null) {
            return v;
        }
        ReadLockView readLockView2 = new ReadLockView();
        this.readLockView = readLockView2;
        return readLockView2;
    }

    public Lock asWriteLock() {
        WriteLockView v = this.writeLockView;
        if (v != null) {
            return v;
        }
        WriteLockView writeLockView2 = new WriteLockView();
        this.writeLockView = writeLockView2;
        return writeLockView2;
    }

    public ReadWriteLock asReadWriteLock() {
        ReadWriteLockView v = this.readWriteLockView;
        if (v != null) {
            return v;
        }
        ReadWriteLockView readWriteLockView2 = new ReadWriteLockView();
        this.readWriteLockView = readWriteLockView2;
        return readWriteLockView2;
    }

    /* access modifiers changed from: package-private */
    public final class ReadLockView implements Lock {
        ReadLockView() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lock() {
            StampedLock.this.readLock();
        }

        @Override // java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            StampedLock.this.readLockInterruptibly();
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock() {
            return StampedLock.this.tryReadLock() != 0;
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return StampedLock.this.tryReadLock(time, unit) != 0;
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
            StampedLock.this.unstampedUnlockRead();
        }

        @Override // java.util.concurrent.locks.Lock
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public final class WriteLockView implements Lock {
        WriteLockView() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lock() {
            StampedLock.this.writeLock();
        }

        @Override // java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            StampedLock.this.writeLockInterruptibly();
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock() {
            return StampedLock.this.tryWriteLock() != 0;
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return StampedLock.this.tryWriteLock(time, unit) != 0;
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
            StampedLock.this.unstampedUnlockWrite();
        }

        @Override // java.util.concurrent.locks.Lock
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }
    }

    final class ReadWriteLockView implements ReadWriteLock {
        ReadWriteLockView() {
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock readLock() {
            return StampedLock.this.asReadLock();
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock writeLock() {
            return StampedLock.this.asWriteLock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void unstampedUnlockWrite() {
        long s = this.state;
        if ((s & 128) != 0) {
            long s2 = 128 + s;
            U.putLongVolatile(this, STATE, s2 == 0 ? 256 : s2);
            WNode h = this.whead;
            if (h != null && h.status != 0) {
                release(h);
                return;
            }
            return;
        }
        throw new IllegalMonitorStateException();
    }

    /* access modifiers changed from: package-private */
    public final void unstampedUnlockRead() {
        WNode h;
        while (true) {
            long s = this.state;
            long m = s & ABITS;
            if (m != 0 && m < 128) {
                if (m < RFULL) {
                    if (U.compareAndSwapLong(this, STATE, s, s - 1)) {
                        if (m == 1 && (h = this.whead) != null && h.status != 0) {
                            release(h);
                            return;
                        }
                        return;
                    }
                } else if (tryDecReaderOverflow(s) != 0) {
                    return;
                }
            }
        }
        throw new IllegalMonitorStateException();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        U.putLongVolatile(this, STATE, 256);
    }

    private long tryIncReaderOverflow(long s) {
        if ((ABITS & s) == RFULL) {
            if (!U.compareAndSwapLong(this, STATE, s, s | RBITS)) {
                return 0;
            }
            this.readerOverflow++;
            U.putLongVolatile(this, STATE, s);
            return s;
        } else if ((LockSupport.nextSecondarySeed() & 7) != 0) {
            return 0;
        } else {
            Thread.yield();
            return 0;
        }
    }

    private long tryDecReaderOverflow(long s) {
        long next;
        if ((ABITS & s) == RFULL) {
            if (!U.compareAndSwapLong(this, STATE, s, s | RBITS)) {
                return 0;
            }
            int r = this.readerOverflow;
            if (r > 0) {
                this.readerOverflow = r - 1;
                next = s;
            } else {
                next = s - 1;
            }
            U.putLongVolatile(this, STATE, next);
            return next;
        } else if ((LockSupport.nextSecondarySeed() & 7) != 0) {
            return 0;
        } else {
            Thread.yield();
            return 0;
        }
    }

    private void release(WNode h) {
        Thread w;
        if (h != null) {
            U.compareAndSwapInt(h, WSTATUS, -1, 0);
            WNode wNode = h.next;
            WNode q = wNode;
            if (wNode == null || q.status == 1) {
                WNode t = this.wtail;
                while (t != null && t != h) {
                    if (t.status <= 0) {
                        q = t;
                    }
                    t = t.prev;
                }
            }
            if (q != null && (w = q.thread) != null) {
                U.unpark(w);
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:123:0x01b9 */
    /* JADX INFO: Multiple debug info for r0v1 long: [D('s' long), D('node' java.util.concurrent.locks.StampedLock$WNode)] */
    /* JADX INFO: Multiple debug info for r0v9 java.util.concurrent.locks.StampedLock$WNode: [D('wasInterrupted' boolean), D('h' java.util.concurrent.locks.StampedLock$WNode)] */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0182, code lost:
        if ((r2 & java.util.concurrent.locks.StampedLock.ABITS) != 0) goto L_0x0187;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long acquireWrite(boolean r29, long r30) {
        /*
        // Method dump skipped, instructions count: 448
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.locks.StampedLock.acquireWrite(boolean, long):long");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:247:0x023a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:262:0x02be */
    /* JADX DEBUG: Multi-variable search result rejected for r10v3, resolved type: java.util.concurrent.locks.StampedLock$WNode */
    /* JADX DEBUG: Multi-variable search result rejected for r10v5, resolved type: java.util.concurrent.locks.StampedLock$WNode */
    /* JADX DEBUG: Multi-variable search result rejected for r10v6, resolved type: java.util.concurrent.locks.StampedLock$WNode */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r0v1 java.util.concurrent.locks.StampedLock$WNode: [D('h' java.util.concurrent.locks.StampedLock$WNode), D('wasInterrupted' boolean)] */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0133, code lost:
        if (r9 == false) goto L_0x013c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0135, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x013c, code lost:
        return r34;
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01c5 A[LOOP:3: B:63:0x0100->B:113:0x01c5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x034f  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x001d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0141 A[EDGE_INSN: B:243:0x0141->B:76:0x0141 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long acquireRead(boolean r38, long r39) {
        /*
        // Method dump skipped, instructions count: 868
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.locks.StampedLock.acquireRead(boolean, long):long");
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.concurrent.locks.StampedLock$WNode: [D('pred' java.util.concurrent.locks.StampedLock$WNode), D('h' java.util.concurrent.locks.StampedLock$WNode)] */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        if (r8 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006a, code lost:
        if (r11 != r10.wtail) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006c, code lost:
        java.util.concurrent.locks.StampedLock.U.compareAndSwapObject(r10, java.util.concurrent.locks.StampedLock.WTAIL, r11, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long cancelWaiter(java.util.concurrent.locks.StampedLock.WNode r11, java.util.concurrent.locks.StampedLock.WNode r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 247
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.locks.StampedLock.cancelWaiter(java.util.concurrent.locks.StampedLock$WNode, java.util.concurrent.locks.StampedLock$WNode, boolean):long");
    }
}
