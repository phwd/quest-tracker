package java.util.concurrent;

import com.oculus.bugreporter.Constants;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Unsafe;

public class Phaser {
    private static final long COUNTS_MASK = 4294967295L;
    private static final int EMPTY = 1;
    private static final int MAX_PARTIES = 65535;
    private static final int MAX_PHASE = Integer.MAX_VALUE;
    private static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final int ONE_ARRIVAL = 1;
    private static final int ONE_DEREGISTER = 65537;
    private static final int ONE_PARTY = 65536;
    private static final long PARTIES_MASK = 4294901760L;
    private static final int PARTIES_SHIFT = 16;
    private static final int PHASE_SHIFT = 32;
    static final int SPINS_PER_ARRIVAL = (NCPU < 2 ? 1 : 256);
    private static final long STATE;
    private static final long TERMINATION_BIT = Long.MIN_VALUE;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final int UNARRIVED_MASK = 65535;
    private final AtomicReference<QNode> evenQ;
    private final AtomicReference<QNode> oddQ;
    private final Phaser parent;
    private final Phaser root;
    private volatile long state;

    private static int unarrivedOf(long s) {
        int counts = (int) s;
        if (counts == 1) {
            return 0;
        }
        return 65535 & counts;
    }

    private static int partiesOf(long s) {
        return ((int) s) >>> 16;
    }

    private static int phaseOf(long s) {
        return (int) (s >>> 32);
    }

    private static int arrivedOf(long s) {
        int counts = (int) s;
        if (counts == 1) {
            return 0;
        }
        return (counts >>> 16) - (65535 & counts);
    }

    private AtomicReference<QNode> queueFor(int phase) {
        return (phase & 1) == 0 ? this.evenQ : this.oddQ;
    }

    private String badArrive(long s) {
        return "Attempted arrival of unregistered party for " + stateToString(s);
    }

    private String badRegister(long s) {
        return "Attempt to register more than 65535 parties for " + stateToString(s);
    }

    private int doArrive(int adjust) {
        long s;
        int phase;
        int unarrived;
        long s2;
        long n;
        Phaser root2 = this.root;
        do {
            s = root2 == this ? this.state : reconcileState();
            phase = (int) (s >>> 32);
            if (phase < 0) {
                return phase;
            }
            int counts = (int) s;
            unarrived = counts == 1 ? 0 : 65535 & counts;
            if (unarrived > 0) {
                s2 = s - ((long) adjust);
            } else {
                throw new IllegalStateException(badArrive(s));
            }
        } while (!U.compareAndSwapLong(this, STATE, s, s2));
        if (unarrived != 1) {
            return phase;
        }
        long n2 = s2 & 4294901760L;
        int nextUnarrived = ((int) n2) >>> 16;
        if (root2 == this) {
            if (onAdvance(phase, nextUnarrived)) {
                n = Long.MIN_VALUE | n2;
            } else if (nextUnarrived == 0) {
                n = 1 | n2;
            } else {
                n = ((long) nextUnarrived) | n2;
            }
            U.compareAndSwapLong(this, STATE, s2, n | (((long) ((phase + 1) & Integer.MAX_VALUE)) << 32));
            releaseWaiters(phase);
            return phase;
        } else if (nextUnarrived != 0) {
            return this.parent.doArrive(1);
        } else {
            int phase2 = this.parent.doArrive(ONE_DEREGISTER);
            U.compareAndSwapLong(this, STATE, s2, s2 | 1);
            return phase2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
        r1 = r13.doRegister(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0085, code lost:
        if (r1 >= 0) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008a, code lost:
        r10 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009a, code lost:
        if (java.util.concurrent.Phaser.U.compareAndSwapLong(r21, java.util.concurrent.Phaser.STATE, r14, (((long) r10) << 32) | r11) != false) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009c, code lost:
        r14 = r21.state;
        r10 = (int) (r21.root.state >>> 32);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int doRegister(int r22) {
        /*
        // Method dump skipped, instructions count: 189
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.Phaser.doRegister(int):int");
    }

    private long reconcileState() {
        long j;
        Phaser root2 = this.root;
        long s = this.state;
        if (root2 == this) {
            return s;
        }
        long s2 = s;
        while (true) {
            int phase = (int) (root2.state >>> 32);
            if (phase == ((int) (s2 >>> 32))) {
                return s2;
            }
            Unsafe unsafe = U;
            long j2 = STATE;
            long j3 = ((long) phase) << 32;
            if (phase < 0) {
                j = COUNTS_MASK & s2;
            } else {
                int p = ((int) s2) >>> 16;
                if (p == 0) {
                    j = 1;
                } else {
                    j = (4294901760L & s2) | ((long) p);
                }
            }
            long s3 = j3 | j;
            if (unsafe.compareAndSwapLong(this, j2, s2, s3)) {
                return s3;
            }
            s2 = this.state;
        }
    }

    public Phaser() {
        this(null, 0);
    }

    public Phaser(int parties) {
        this(null, parties);
    }

    public Phaser(Phaser parent2) {
        this(parent2, 0);
    }

    public Phaser(Phaser parent2, int parties) {
        long j;
        if ((parties >>> 16) == 0) {
            int phase = 0;
            this.parent = parent2;
            if (parent2 != null) {
                Phaser root2 = parent2.root;
                this.root = root2;
                this.evenQ = root2.evenQ;
                this.oddQ = root2.oddQ;
                if (parties != 0) {
                    phase = parent2.doRegister(1);
                }
            } else {
                this.root = this;
                this.evenQ = new AtomicReference<>();
                this.oddQ = new AtomicReference<>();
            }
            if (parties == 0) {
                j = 1;
            } else {
                j = (((long) phase) << 32) | (((long) parties) << 16) | ((long) parties);
            }
            this.state = j;
            return;
        }
        throw new IllegalArgumentException("Illegal number of parties");
    }

    public int register() {
        return doRegister(1);
    }

    public int bulkRegister(int parties) {
        if (parties < 0) {
            throw new IllegalArgumentException();
        } else if (parties == 0) {
            return getPhase();
        } else {
            return doRegister(parties);
        }
    }

    public int arrive() {
        return doArrive(1);
    }

    public int arriveAndDeregister() {
        return doArrive(ONE_DEREGISTER);
    }

    public int arriveAndAwaitAdvance() {
        long s;
        int phase;
        int unarrived;
        long s2;
        long n;
        Phaser root2 = this.root;
        do {
            s = root2 == this ? this.state : reconcileState();
            phase = (int) (s >>> 32);
            if (phase < 0) {
                return phase;
            }
            int counts = (int) s;
            unarrived = counts == 1 ? 0 : 65535 & counts;
            if (unarrived > 0) {
                s2 = s - 1;
            } else {
                throw new IllegalStateException(badArrive(s));
            }
        } while (!U.compareAndSwapLong(this, STATE, s, s2));
        if (unarrived > 1) {
            return root2.internalAwaitAdvance(phase, null);
        }
        if (root2 != this) {
            return this.parent.arriveAndAwaitAdvance();
        }
        long n2 = s2 & 4294901760L;
        int nextUnarrived = ((int) n2) >>> 16;
        if (onAdvance(phase, nextUnarrived)) {
            n = n2 | Long.MIN_VALUE;
        } else if (nextUnarrived == 0) {
            n = n2 | 1;
        } else {
            n = n2 | ((long) nextUnarrived);
        }
        int nextPhase = (phase + 1) & Integer.MAX_VALUE;
        if (!U.compareAndSwapLong(this, STATE, s2, n | (((long) nextPhase) << 32))) {
            return (int) (this.state >>> 32);
        }
        releaseWaiters(phase);
        return nextPhase;
    }

    public int awaitAdvance(int phase) {
        Phaser root2 = this.root;
        int p = (int) ((root2 == this ? this.state : reconcileState()) >>> 32);
        if (phase < 0) {
            return phase;
        }
        if (p == phase) {
            return root2.internalAwaitAdvance(phase, null);
        }
        return p;
    }

    public int awaitAdvanceInterruptibly(int phase) throws InterruptedException {
        Phaser root2 = this.root;
        int p = (int) ((root2 == this ? this.state : reconcileState()) >>> 32);
        if (phase < 0) {
            return phase;
        }
        if (p == phase) {
            QNode node = new QNode(this, phase, true, false, 0);
            p = root2.internalAwaitAdvance(phase, node);
            if (node.wasInterrupted) {
                throw new InterruptedException();
            }
        }
        return p;
    }

    public int awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
        long nanos = unit.toNanos(timeout);
        Phaser root2 = this.root;
        int p = (int) ((root2 == this ? this.state : reconcileState()) >>> 32);
        if (phase < 0) {
            return phase;
        }
        if (p != phase) {
            return p;
        }
        QNode node = new QNode(this, phase, true, true, nanos);
        int p2 = root2.internalAwaitAdvance(phase, node);
        if (node.wasInterrupted) {
            throw new InterruptedException();
        } else if (p2 != phase) {
            return p2;
        } else {
            throw new TimeoutException();
        }
    }

    public void forceTermination() {
        long s;
        Phaser root2 = this.root;
        do {
            s = root2.state;
            if (s < 0) {
                return;
            }
        } while (!U.compareAndSwapLong(root2, STATE, s, s | Long.MIN_VALUE));
        releaseWaiters(0);
        releaseWaiters(1);
    }

    public final int getPhase() {
        return (int) (this.root.state >>> 32);
    }

    public int getRegisteredParties() {
        return partiesOf(this.state);
    }

    public int getArrivedParties() {
        return arrivedOf(reconcileState());
    }

    public int getUnarrivedParties() {
        return unarrivedOf(reconcileState());
    }

    public Phaser getParent() {
        return this.parent;
    }

    public Phaser getRoot() {
        return this.root;
    }

    public boolean isTerminated() {
        return this.root.state < 0;
    }

    /* access modifiers changed from: protected */
    public boolean onAdvance(int phase, int registeredParties) {
        return registeredParties == 0;
    }

    public String toString() {
        return stateToString(reconcileState());
    }

    private String stateToString(long s) {
        return super.toString() + "[phase = " + phaseOf(s) + " parties = " + partiesOf(s) + " arrived = " + arrivedOf(s) + "]";
    }

    private void releaseWaiters(int phase) {
        Thread t;
        AtomicReference<QNode> head = (phase & 1) == 0 ? this.evenQ : this.oddQ;
        while (true) {
            QNode q = head.get();
            if (q != null && q.phase != ((int) (this.root.state >>> 32))) {
                if (head.compareAndSet(q, q.next) && (t = q.thread) != null) {
                    q.thread = null;
                    LockSupport.unpark(t);
                }
            } else {
                return;
            }
        }
    }

    private int abortWait(int phase) {
        int p;
        Thread t;
        AtomicReference<QNode> head = (phase & 1) == 0 ? this.evenQ : this.oddQ;
        while (true) {
            QNode q = head.get();
            p = (int) (this.root.state >>> 32);
            if (q == null || ((t = q.thread) != null && q.phase == p)) {
                return p;
            }
            if (head.compareAndSet(q, q.next) && t != null) {
                q.thread = null;
                LockSupport.unpark(t);
            }
        }
        return p;
    }

    static {
        try {
            STATE = U.objectFieldOffset(Phaser.class.getDeclaredField(Constants.EXTRA_STATE));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private int internalAwaitAdvance(int phase, QNode node) {
        int p;
        int lastUnarrived;
        boolean interrupted;
        releaseWaiters(phase - 1);
        int lastUnarrived2 = 0;
        int spins = SPINS_PER_ARRIVAL;
        QNode node2 = node;
        boolean queued = false;
        while (true) {
            long s = this.state;
            int i = (int) (s >>> 32);
            p = i;
            if (i != phase) {
                break;
            } else if (node2 == null) {
                int unarrived = ((int) s) & 65535;
                if (unarrived != lastUnarrived2) {
                    lastUnarrived2 = unarrived;
                    if (unarrived < NCPU) {
                        spins += SPINS_PER_ARRIVAL;
                        lastUnarrived = lastUnarrived2;
                        interrupted = Thread.interrupted();
                        if (interrupted || spins - 1 < 0) {
                            QNode node3 = new QNode(this, phase, false, false, 0);
                            node3.wasInterrupted = interrupted;
                            node2 = node3;
                            spins = spins;
                        }
                        lastUnarrived2 = lastUnarrived;
                    }
                }
                lastUnarrived = lastUnarrived2;
                interrupted = Thread.interrupted();
                QNode node32 = new QNode(this, phase, false, false, 0);
                node32.wasInterrupted = interrupted;
                node2 = node32;
                spins = spins;
                lastUnarrived2 = lastUnarrived;
            } else if (node2.isReleasable()) {
                break;
            } else if (!queued) {
                AtomicReference<QNode> head = (phase & 1) == 0 ? this.evenQ : this.oddQ;
                QNode q = head.get();
                node2.next = q;
                if ((q == null || q.phase == phase) && ((int) (this.state >>> 32)) == phase) {
                    queued = head.compareAndSet(q, node2);
                }
            } else {
                try {
                    ForkJoinPool.managedBlock(node2);
                } catch (InterruptedException e) {
                    node2.wasInterrupted = true;
                }
            }
        }
        if (node2 != null) {
            if (node2.thread != null) {
                node2.thread = null;
            }
            if (node2.wasInterrupted && !node2.interruptible) {
                Thread.currentThread().interrupt();
            }
            if (p == phase) {
                int i2 = (int) (this.state >>> 32);
                p = i2;
                if (i2 == phase) {
                    return abortWait(phase);
                }
            }
        }
        releaseWaiters(phase);
        return p;
    }

    /* access modifiers changed from: package-private */
    public static final class QNode implements ForkJoinPool.ManagedBlocker {
        final long deadline;
        final boolean interruptible;
        long nanos;
        QNode next;
        final int phase;
        final Phaser phaser;
        volatile Thread thread;
        final boolean timed;
        boolean wasInterrupted;

        QNode(Phaser phaser2, int phase2, boolean interruptible2, boolean timed2, long nanos2) {
            this.phaser = phaser2;
            this.phase = phase2;
            this.interruptible = interruptible2;
            this.nanos = nanos2;
            this.timed = timed2;
            this.deadline = timed2 ? System.nanoTime() + nanos2 : 0;
            this.thread = Thread.currentThread();
        }

        @Override // java.util.concurrent.ForkJoinPool.ManagedBlocker
        public boolean isReleasable() {
            if (this.thread == null) {
                return true;
            }
            if (this.phaser.getPhase() != this.phase) {
                this.thread = null;
                return true;
            }
            if (Thread.interrupted()) {
                this.wasInterrupted = true;
            }
            if (this.wasInterrupted && this.interruptible) {
                this.thread = null;
                return true;
            } else if (!this.timed) {
                return false;
            } else {
                if (this.nanos > 0) {
                    long nanoTime = this.deadline - System.nanoTime();
                    this.nanos = nanoTime;
                    if (nanoTime > 0) {
                        return false;
                    }
                }
                this.thread = null;
                return true;
            }
        }

        @Override // java.util.concurrent.ForkJoinPool.ManagedBlocker
        public boolean block() {
            while (!isReleasable()) {
                if (this.timed) {
                    LockSupport.parkNanos(this, this.nanos);
                } else {
                    LockSupport.park(this);
                }
            }
            return true;
        }
    }
}
