package java.util.concurrent.locks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import sun.misc.Unsafe;

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements Serializable {
    private static final long HEAD;
    static final long SPIN_FOR_TIMEOUT_THRESHOLD = 1000;
    private static final long STATE;
    private static final long TAIL;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = 7373984972572414691L;
    private volatile transient Node head;
    private volatile int state;
    private volatile transient Node tail;

    protected AbstractQueuedSynchronizer() {
    }

    /* access modifiers changed from: package-private */
    public static final class Node {
        static final int CANCELLED = 1;
        static final int CONDITION = -2;
        static final Node EXCLUSIVE = null;
        private static final long NEXT;
        static final long PREV;
        static final int PROPAGATE = -3;
        static final Node SHARED = new Node();
        static final int SIGNAL = -1;
        private static final long THREAD;
        private static final Unsafe U = Unsafe.getUnsafe();
        private static final long WAITSTATUS;
        volatile Node next;
        Node nextWaiter;
        volatile Node prev;
        volatile Thread thread;
        volatile int waitStatus;

        static {
            try {
                NEXT = U.objectFieldOffset(Node.class.getDeclaredField("next"));
                PREV = U.objectFieldOffset(Node.class.getDeclaredField("prev"));
                THREAD = U.objectFieldOffset(Node.class.getDeclaredField("thread"));
                WAITSTATUS = U.objectFieldOffset(Node.class.getDeclaredField("waitStatus"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean isShared() {
            return this.nextWaiter == SHARED;
        }

        /* access modifiers changed from: package-private */
        public final Node predecessor() throws NullPointerException {
            Node p = this.prev;
            if (p != null) {
                return p;
            }
            throw new NullPointerException();
        }

        Node() {
        }

        Node(Node nextWaiter2) {
            this.nextWaiter = nextWaiter2;
            U.putObject(this, THREAD, Thread.currentThread());
        }

        Node(int waitStatus2) {
            U.putInt(this, WAITSTATUS, waitStatus2);
            U.putObject(this, THREAD, Thread.currentThread());
        }

        /* access modifiers changed from: package-private */
        public final boolean compareAndSetWaitStatus(int expect, int update) {
            return U.compareAndSwapInt(this, WAITSTATUS, expect, update);
        }

        /* access modifiers changed from: package-private */
        public final boolean compareAndSetNext(Node expect, Node update) {
            return U.compareAndSwapObject(this, NEXT, expect, update);
        }
    }

    /* access modifiers changed from: protected */
    public final int getState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public final void setState(int newState) {
        this.state = newState;
    }

    /* access modifiers changed from: protected */
    public final boolean compareAndSetState(int expect, int update) {
        return U.compareAndSwapInt(this, STATE, expect, update);
    }

    private Node enq(Node node) {
        while (true) {
            Node oldTail = this.tail;
            if (oldTail != null) {
                U.putObject(node, Node.PREV, oldTail);
                if (compareAndSetTail(oldTail, node)) {
                    oldTail.next = node;
                    return oldTail;
                }
            } else {
                initializeSyncQueue();
            }
        }
    }

    private Node addWaiter(Node mode) {
        Node node = new Node(mode);
        while (true) {
            Node oldTail = this.tail;
            if (oldTail != null) {
                U.putObject(node, Node.PREV, oldTail);
                if (compareAndSetTail(oldTail, node)) {
                    oldTail.next = node;
                    return node;
                }
            } else {
                initializeSyncQueue();
            }
        }
    }

    private void setHead(Node node) {
        this.head = node;
        node.thread = null;
        node.prev = null;
    }

    private void unparkSuccessor(Node node) {
        int ws = node.waitStatus;
        if (ws < 0) {
            node.compareAndSetWaitStatus(ws, 0);
        }
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            Node p = this.tail;
            while (p != node && p != null) {
                if (p.waitStatus <= 0) {
                    s = p;
                }
                p = p.prev;
            }
        }
        if (s != null) {
            LockSupport.unpark(s.thread);
        }
    }

    private void doReleaseShared() {
        while (true) {
            Node h = this.head;
            if (!(h == null || h == this.tail)) {
                int ws = h.waitStatus;
                if (ws == -1) {
                    if (!h.compareAndSetWaitStatus(-1, 0)) {
                        continue;
                    } else {
                        unparkSuccessor(h);
                    }
                } else if (ws == 0 && !h.compareAndSetWaitStatus(0, -3)) {
                }
            }
            if (h == this.head) {
                return;
            }
        }
    }

    private void setHeadAndPropagate(Node node, int propagate) {
        Node h;
        Node h2 = this.head;
        setHead(node);
        if (propagate > 0 || h2 == null || h2.waitStatus < 0 || (h = this.head) == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared()) {
                doReleaseShared();
            }
        }
    }

    private void cancelAcquire(Node node) {
        int ws;
        if (node != null) {
            node.thread = null;
            Node pred = node.prev;
            while (pred.waitStatus > 0) {
                Node node2 = pred.prev;
                pred = node2;
                node.prev = node2;
            }
            Node predNext = pred.next;
            node.waitStatus = 1;
            if (node != this.tail || !compareAndSetTail(node, pred)) {
                if (pred == this.head || (((ws = pred.waitStatus) != -1 && (ws > 0 || !pred.compareAndSetWaitStatus(ws, -1))) || pred.thread == null)) {
                    unparkSuccessor(node);
                } else {
                    Node next = node.next;
                    if (next != null && next.waitStatus <= 0) {
                        pred.compareAndSetNext(predNext, next);
                    }
                }
                node.next = node;
                return;
            }
            pred.compareAndSetNext(predNext, null);
        }
    }

    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        if (ws == -1) {
            return true;
        }
        if (ws > 0) {
            do {
                Node node2 = pred.prev;
                pred = node2;
                node.prev = node2;
            } while (pred.waitStatus > 0);
            pred.next = node;
            return false;
        }
        pred.compareAndSetWaitStatus(ws, -1);
        return false;
    }

    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }

    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }

    /* access modifiers changed from: package-private */
    public final boolean acquireQueued(Node node, int arg) {
        boolean interrupted = false;
        while (true) {
            try {
                Node p = node.predecessor();
                if (p == this.head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null;
                    return interrupted;
                } else if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) {
                    interrupted = true;
                }
            } catch (Throwable t) {
                cancelAcquire(node);
                throw t;
            }
        }
    }

    private void doAcquireInterruptibly(int arg) throws InterruptedException {
        Node node = addWaiter(Node.EXCLUSIVE);
        while (true) {
            try {
                Node p = node.predecessor();
                if (p == this.head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null;
                    return;
                } else if (shouldParkAfterFailedAcquire(p, node)) {
                    if (parkAndCheckInterrupt()) {
                        throw new InterruptedException();
                    }
                }
            } catch (Throwable t) {
                cancelAcquire(node);
                throw t;
            }
        }
    }

    private boolean doAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (nanosTimeout <= 0) {
            return false;
        }
        long deadline = System.nanoTime() + nanosTimeout;
        Node node = addWaiter(Node.EXCLUSIVE);
        while (true) {
            try {
                Node p = node.predecessor();
                if (p != this.head || !tryAcquire(arg)) {
                    long nanosTimeout2 = deadline - System.nanoTime();
                    if (nanosTimeout2 <= 0) {
                        return false;
                    }
                    if (shouldParkAfterFailedAcquire(p, node) && nanosTimeout2 > 1000) {
                        LockSupport.parkNanos(this, nanosTimeout2);
                    }
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                } else {
                    setHead(node);
                    p.next = null;
                    return true;
                }
            } finally {
                cancelAcquire(node);
            }
        }
    }

    private void doAcquireShared(int arg) {
        Node p;
        int r;
        Node node = addWaiter(Node.SHARED);
        boolean interrupted = false;
        while (true) {
            try {
                p = node.predecessor();
                if (p == this.head && (r = tryAcquireShared(arg)) >= 0) {
                    break;
                } else if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) {
                    interrupted = true;
                }
            } catch (Throwable t) {
                cancelAcquire(node);
                throw t;
            }
        }
        setHeadAndPropagate(node, r);
        p.next = null;
        if (interrupted) {
            selfInterrupt();
        }
    }

    private void doAcquireSharedInterruptibly(int arg) throws InterruptedException {
        int r;
        Node node = addWaiter(Node.SHARED);
        while (true) {
            try {
                Node p = node.predecessor();
                if (p == this.head && (r = tryAcquireShared(arg)) >= 0) {
                    setHeadAndPropagate(node, r);
                    p.next = null;
                    return;
                } else if (shouldParkAfterFailedAcquire(p, node)) {
                    if (parkAndCheckInterrupt()) {
                        throw new InterruptedException();
                    }
                }
            } catch (Throwable t) {
                cancelAcquire(node);
                throw t;
            }
        }
    }

    private boolean doAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException {
        int r;
        if (nanosTimeout <= 0) {
            return false;
        }
        long deadline = System.nanoTime() + nanosTimeout;
        Node node = addWaiter(Node.SHARED);
        while (true) {
            try {
                Node p = node.predecessor();
                if (p != this.head || (r = tryAcquireShared(arg)) < 0) {
                    long nanosTimeout2 = deadline - System.nanoTime();
                    if (nanosTimeout2 <= 0) {
                        return false;
                    }
                    if (shouldParkAfterFailedAcquire(p, node) && nanosTimeout2 > 1000) {
                        LockSupport.parkNanos(this, nanosTimeout2);
                    }
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                } else {
                    setHeadAndPropagate(node, r);
                    p.next = null;
                    return true;
                }
            } finally {
                cancelAcquire(node);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public int tryAcquireShared(int arg) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean tryReleaseShared(int arg) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }

    public final void acquire(int arg) {
        if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) {
            selfInterrupt();
        }
    }

    public final void acquireInterruptibly(int arg) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else if (!tryAcquire(arg)) {
            doAcquireInterruptibly(arg);
        }
    }

    public final boolean tryAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (!Thread.interrupted()) {
            return tryAcquire(arg) || doAcquireNanos(arg, nanosTimeout);
        }
        throw new InterruptedException();
    }

    public final boolean release(int arg) {
        if (!tryRelease(arg)) {
            return false;
        }
        Node h = this.head;
        if (h == null || h.waitStatus == 0) {
            return true;
        }
        unparkSuccessor(h);
        return true;
    }

    public final void acquireShared(int arg) {
        if (tryAcquireShared(arg) < 0) {
            doAcquireShared(arg);
        }
    }

    public final void acquireSharedInterruptibly(int arg) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else if (tryAcquireShared(arg) < 0) {
            doAcquireSharedInterruptibly(arg);
        }
    }

    public final boolean tryAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (!Thread.interrupted()) {
            return tryAcquireShared(arg) >= 0 || doAcquireSharedNanos(arg, nanosTimeout);
        }
        throw new InterruptedException();
    }

    public final boolean releaseShared(int arg) {
        if (!tryReleaseShared(arg)) {
            return false;
        }
        doReleaseShared();
        return true;
    }

    public final boolean hasQueuedThreads() {
        return this.head != this.tail;
    }

    public final boolean hasContended() {
        return this.head != null;
    }

    public final Thread getFirstQueuedThread() {
        if (this.head == this.tail) {
            return null;
        }
        return fullGetFirstQueuedThread();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        if (r0 != null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        if (r0 == null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Thread fullGetFirstQueuedThread() {
        /*
            r4 = this;
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r0 = r4.head
            r1 = r0
            if (r0 == 0) goto L_0x0015
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r0 = r1.next
            r2 = r0
            if (r0 == 0) goto L_0x0015
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r0 = r2.prev
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r3 = r4.head
            if (r0 != r3) goto L_0x0015
            java.lang.Thread r0 = r2.thread
            r3 = r0
            if (r0 != 0) goto L_0x002a
        L_0x0015:
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r0 = r4.head
            r1 = r0
            if (r0 == 0) goto L_0x002b
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r0 = r1.next
            r2 = r0
            if (r0 == 0) goto L_0x002b
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r0 = r2.prev
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r3 = r4.head
            if (r0 != r3) goto L_0x002b
            java.lang.Thread r0 = r2.thread
            r3 = r0
            if (r0 == 0) goto L_0x002b
        L_0x002a:
            return r3
        L_0x002b:
            r0 = 0
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r2 = r4.tail
        L_0x002e:
            if (r2 == 0) goto L_0x003c
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r3 = r4.head
            if (r2 == r3) goto L_0x003c
            java.lang.Thread r3 = r2.thread
            if (r3 == 0) goto L_0x0039
            r0 = r3
        L_0x0039:
            java.util.concurrent.locks.AbstractQueuedSynchronizer$Node r2 = r2.prev
            goto L_0x002e
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.locks.AbstractQueuedSynchronizer.fullGetFirstQueuedThread():java.lang.Thread");
    }

    public final boolean isQueued(Thread thread) {
        if (thread != null) {
            for (Node p = this.tail; p != null; p = p.prev) {
                if (p.thread == thread) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public final boolean apparentlyFirstQueuedIsExclusive() {
        Node s;
        Node h = this.head;
        return (h == null || (s = h.next) == null || s.isShared() || s.thread == null) ? false : true;
    }

    public final boolean hasQueuedPredecessors() {
        Node s;
        Node t = this.tail;
        Node h = this.head;
        return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
    }

    public final int getQueueLength() {
        int n = 0;
        for (Node p = this.tail; p != null; p = p.prev) {
            if (p.thread != null) {
                n++;
            }
        }
        return n;
    }

    public final Collection<Thread> getQueuedThreads() {
        ArrayList<Thread> list = new ArrayList<>();
        for (Node p = this.tail; p != null; p = p.prev) {
            Thread t = p.thread;
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }

    public final Collection<Thread> getExclusiveQueuedThreads() {
        Thread t;
        ArrayList<Thread> list = new ArrayList<>();
        for (Node p = this.tail; p != null; p = p.prev) {
            if (!p.isShared() && (t = p.thread) != null) {
                list.add(t);
            }
        }
        return list;
    }

    public final Collection<Thread> getSharedQueuedThreads() {
        Thread t;
        ArrayList<Thread> list = new ArrayList<>();
        for (Node p = this.tail; p != null; p = p.prev) {
            if (p.isShared() && (t = p.thread) != null) {
                list.add(t);
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[State = ");
        sb.append(getState());
        sb.append(", ");
        sb.append(hasQueuedThreads() ? "non" : "");
        sb.append("empty queue]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final boolean isOnSyncQueue(Node node) {
        if (node.waitStatus == -2 || node.prev == null) {
            return false;
        }
        if (node.next != null) {
            return true;
        }
        return findNodeFromTail(node);
    }

    private boolean findNodeFromTail(Node node) {
        for (Node p = this.tail; p != node; p = p.prev) {
            if (p == null) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean transferForSignal(Node node) {
        if (!node.compareAndSetWaitStatus(-2, 0)) {
            return false;
        }
        Node p = enq(node);
        int ws = p.waitStatus;
        if (ws <= 0 && p.compareAndSetWaitStatus(ws, -1)) {
            return true;
        }
        LockSupport.unpark(node.thread);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean transferAfterCancelledWait(Node node) {
        if (node.compareAndSetWaitStatus(-2, 0)) {
            enq(node);
            return true;
        }
        while (!isOnSyncQueue(node)) {
            Thread.yield();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int fullyRelease(Node node) {
        try {
            int savedState = getState();
            if (release(savedState)) {
                return savedState;
            }
            throw new IllegalMonitorStateException();
        } catch (Throwable t) {
            node.waitStatus = 1;
            throw t;
        }
    }

    public final boolean owns(ConditionObject condition) {
        return condition.isOwnedBy(this);
    }

    public final boolean hasWaiters(ConditionObject condition) {
        if (owns(condition)) {
            return condition.hasWaiters();
        }
        throw new IllegalArgumentException("Not owner");
    }

    public final int getWaitQueueLength(ConditionObject condition) {
        if (owns(condition)) {
            return condition.getWaitQueueLength();
        }
        throw new IllegalArgumentException("Not owner");
    }

    public final Collection<Thread> getWaitingThreads(ConditionObject condition) {
        if (owns(condition)) {
            return condition.getWaitingThreads();
        }
        throw new IllegalArgumentException("Not owner");
    }

    public class ConditionObject implements Condition, Serializable {
        private static final int REINTERRUPT = 1;
        private static final int THROW_IE = -1;
        private static final long serialVersionUID = 1173984872572414699L;
        private transient Node firstWaiter;
        private transient Node lastWaiter;

        public ConditionObject() {
        }

        private Node addConditionWaiter() {
            Node t = this.lastWaiter;
            if (!(t == null || t.waitStatus == -2)) {
                unlinkCancelledWaiters();
                t = this.lastWaiter;
            }
            Node node = new Node(-2);
            if (t == null) {
                this.firstWaiter = node;
            } else {
                t.nextWaiter = node;
            }
            this.lastWaiter = node;
            return node;
        }

        private void doSignal(Node first) {
            Node node;
            do {
                Node node2 = first.nextWaiter;
                this.firstWaiter = node2;
                if (node2 == null) {
                    this.lastWaiter = null;
                }
                first.nextWaiter = null;
                if (!AbstractQueuedSynchronizer.this.transferForSignal(first)) {
                    node = this.firstWaiter;
                    first = node;
                } else {
                    return;
                }
            } while (node != null);
        }

        private void doSignalAll(Node first) {
            this.firstWaiter = null;
            this.lastWaiter = null;
            do {
                Node next = first.nextWaiter;
                first.nextWaiter = null;
                AbstractQueuedSynchronizer.this.transferForSignal(first);
                first = next;
            } while (first != null);
        }

        private void unlinkCancelledWaiters() {
            Node t = this.firstWaiter;
            Node trail = null;
            while (t != null) {
                Node next = t.nextWaiter;
                if (t.waitStatus != -2) {
                    t.nextWaiter = null;
                    if (trail == null) {
                        this.firstWaiter = next;
                    } else {
                        trail.nextWaiter = next;
                    }
                    if (next == null) {
                        this.lastWaiter = trail;
                    }
                } else {
                    trail = t;
                }
                t = next;
            }
        }

        @Override // java.util.concurrent.locks.Condition
        public final void signal() {
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                Node first = this.firstWaiter;
                if (first != null) {
                    doSignal(first);
                    return;
                }
                return;
            }
            throw new IllegalMonitorStateException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final void signalAll() {
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                Node first = this.firstWaiter;
                if (first != null) {
                    doSignalAll(first);
                    return;
                }
                return;
            }
            throw new IllegalMonitorStateException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final void awaitUninterruptibly() {
            Node node = addConditionWaiter();
            int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
            boolean interrupted = false;
            while (!AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                LockSupport.park(this);
                if (Thread.interrupted()) {
                    interrupted = true;
                }
            }
            if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) || interrupted) {
                AbstractQueuedSynchronizer.selfInterrupt();
            }
        }

        private int checkInterruptWhileWaiting(Node node) {
            if (Thread.interrupted()) {
                return AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node) ? -1 : 1;
            }
            return 0;
        }

        private void reportInterruptAfterWait(int interruptMode) throws InterruptedException {
            if (interruptMode == -1) {
                throw new InterruptedException();
            } else if (interruptMode == 1) {
                AbstractQueuedSynchronizer.selfInterrupt();
            }
        }

        @Override // java.util.concurrent.locks.Condition
        public final void await() throws InterruptedException {
            if (!Thread.interrupted()) {
                Node node = addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                int interruptMode = 0;
                while (!AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                    LockSupport.park(this);
                    int checkInterruptWhileWaiting = checkInterruptWhileWaiting(node);
                    interruptMode = checkInterruptWhileWaiting;
                    if (checkInterruptWhileWaiting != 0) {
                        break;
                    }
                }
                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }
                if (node.nextWaiter != null) {
                    unlinkCancelledWaiters();
                }
                if (interruptMode != 0) {
                    reportInterruptAfterWait(interruptMode);
                    return;
                }
                return;
            }
            throw new InterruptedException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final long awaitNanos(long nanosTimeout) throws InterruptedException {
            if (!Thread.interrupted()) {
                long deadline = System.nanoTime() + nanosTimeout;
                Node node = addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                int interruptMode = 0;
                while (true) {
                    if (AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                        break;
                    } else if (nanosTimeout <= 0) {
                        AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node);
                        break;
                    } else {
                        if (nanosTimeout > 1000) {
                            LockSupport.parkNanos(this, nanosTimeout);
                        }
                        int checkInterruptWhileWaiting = checkInterruptWhileWaiting(node);
                        interruptMode = checkInterruptWhileWaiting;
                        if (checkInterruptWhileWaiting != 0) {
                            break;
                        }
                        nanosTimeout = deadline - System.nanoTime();
                    }
                }
                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }
                if (node.nextWaiter != null) {
                    unlinkCancelledWaiters();
                }
                if (interruptMode != 0) {
                    reportInterruptAfterWait(interruptMode);
                }
                long remaining = deadline - System.nanoTime();
                if (remaining <= nanosTimeout) {
                    return remaining;
                }
                return Long.MIN_VALUE;
            }
            throw new InterruptedException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final boolean awaitUntil(Date deadline) throws InterruptedException {
            long abstime = deadline.getTime();
            if (!Thread.interrupted()) {
                Node node = addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                boolean timedout = false;
                int interruptMode = 0;
                while (true) {
                    if (!AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                        if (System.currentTimeMillis() < abstime) {
                            LockSupport.parkUntil(this, abstime);
                            int checkInterruptWhileWaiting = checkInterruptWhileWaiting(node);
                            interruptMode = checkInterruptWhileWaiting;
                            if (checkInterruptWhileWaiting != 0) {
                                break;
                            }
                        } else {
                            timedout = AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }
                if (node.nextWaiter != null) {
                    unlinkCancelledWaiters();
                }
                if (interruptMode != 0) {
                    reportInterruptAfterWait(interruptMode);
                }
                return !timedout;
            }
            throw new InterruptedException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final boolean await(long time, TimeUnit unit) throws InterruptedException {
            long nanosTimeout = unit.toNanos(time);
            if (!Thread.interrupted()) {
                long deadline = System.nanoTime() + nanosTimeout;
                Node node = addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                boolean timedout = false;
                int interruptMode = 0;
                while (true) {
                    if (AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                        break;
                    } else if (nanosTimeout <= 0) {
                        timedout = AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node);
                        break;
                    } else {
                        if (nanosTimeout > 1000) {
                            LockSupport.parkNanos(this, nanosTimeout);
                        }
                        int checkInterruptWhileWaiting = checkInterruptWhileWaiting(node);
                        interruptMode = checkInterruptWhileWaiting;
                        if (checkInterruptWhileWaiting != 0) {
                            break;
                        }
                        nanosTimeout = deadline - System.nanoTime();
                    }
                }
                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }
                if (node.nextWaiter != null) {
                    unlinkCancelledWaiters();
                }
                if (interruptMode != 0) {
                    reportInterruptAfterWait(interruptMode);
                }
                return !timedout;
            }
            throw new InterruptedException();
        }

        /* access modifiers changed from: package-private */
        public final boolean isOwnedBy(AbstractQueuedSynchronizer sync) {
            return sync == AbstractQueuedSynchronizer.this;
        }

        /* access modifiers changed from: protected */
        public final boolean hasWaiters() {
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                for (Node w = this.firstWaiter; w != null; w = w.nextWaiter) {
                    if (w.waitStatus == -2) {
                        return true;
                    }
                }
                return false;
            }
            throw new IllegalMonitorStateException();
        }

        /* access modifiers changed from: protected */
        public final int getWaitQueueLength() {
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                int n = 0;
                for (Node w = this.firstWaiter; w != null; w = w.nextWaiter) {
                    if (w.waitStatus == -2) {
                        n++;
                    }
                }
                return n;
            }
            throw new IllegalMonitorStateException();
        }

        /* access modifiers changed from: protected */
        public final Collection<Thread> getWaitingThreads() {
            Thread t;
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                ArrayList<Thread> list = new ArrayList<>();
                for (Node w = this.firstWaiter; w != null; w = w.nextWaiter) {
                    if (w.waitStatus == -2 && (t = w.thread) != null) {
                        list.add(t);
                    }
                }
                return list;
            }
            throw new IllegalMonitorStateException();
        }
    }

    static {
        try {
            STATE = U.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            HEAD = U.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            TAIL = U.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private final void initializeSyncQueue() {
        Unsafe unsafe = U;
        long j = HEAD;
        Node h = new Node();
        if (unsafe.compareAndSwapObject(this, j, null, h)) {
            this.tail = h;
        }
    }

    private final boolean compareAndSetTail(Node expect, Node update) {
        return U.compareAndSwapObject(this, TAIL, expect, update);
    }
}
