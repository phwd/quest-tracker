package java.util.concurrent.locks;

import java.io.Serializable;
import sun.misc.Unsafe;

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements Serializable {
    private static final long HEAD;
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
        static final Node EXCLUSIVE = null;
        private static final long NEXT;
        static final long PREV;
        static final Node SHARED = new Node();
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
        public final Node predecessor() {
            Node node = this.prev;
            if (node != null) {
                return node;
            }
            throw new NullPointerException();
        }

        Node() {
        }

        Node(Node node) {
            this.nextWaiter = node;
            U.putObject(this, THREAD, Thread.currentThread());
        }

        Node(int i) {
            U.putInt(this, WAITSTATUS, i);
            U.putObject(this, THREAD, Thread.currentThread());
        }

        /* access modifiers changed from: package-private */
        public final boolean compareAndSetWaitStatus(int i, int i2) {
            return U.compareAndSwapInt(this, WAITSTATUS, i, i2);
        }

        /* access modifiers changed from: package-private */
        public final boolean compareAndSetNext(Node node, Node node2) {
            return U.compareAndSwapObject(this, NEXT, node, node2);
        }
    }

    /* access modifiers changed from: protected */
    public final int getState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public final void setState(int i) {
        this.state = i;
    }

    /* access modifiers changed from: protected */
    public final boolean compareAndSetState(int i, int i2) {
        return U.compareAndSwapInt(this, STATE, i, i2);
    }

    private Node enq(Node node) {
        while (true) {
            Node node2 = this.tail;
            if (node2 != null) {
                U.putObject(node, Node.PREV, node2);
                if (compareAndSetTail(node2, node)) {
                    node2.next = node;
                    return node2;
                }
            } else {
                initializeSyncQueue();
            }
        }
    }

    private Node addWaiter(Node node) {
        Node node2 = new Node(node);
        while (true) {
            Node node3 = this.tail;
            if (node3 != null) {
                U.putObject(node2, Node.PREV, node3);
                if (compareAndSetTail(node3, node2)) {
                    node3.next = node2;
                    return node2;
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
        int i = node.waitStatus;
        if (i < 0) {
            node.compareAndSetWaitStatus(i, 0);
        }
        Node node2 = node.next;
        if (node2 == null || node2.waitStatus > 0) {
            node2 = null;
            Node node3 = this.tail;
            while (node3 != node && node3 != null) {
                if (node3.waitStatus <= 0) {
                    node2 = node3;
                }
                node3 = node3.prev;
            }
        }
        if (node2 != null) {
            LockSupport.unpark(node2.thread);
        }
    }

    private void doReleaseShared() {
        while (true) {
            Node node = this.head;
            if (!(node == null || node == this.tail)) {
                int i = node.waitStatus;
                if (i == -1) {
                    if (!node.compareAndSetWaitStatus(-1, 0)) {
                        continue;
                    } else {
                        unparkSuccessor(node);
                    }
                } else if (i == 0 && !node.compareAndSetWaitStatus(0, -3)) {
                }
            }
            if (node == this.head) {
                return;
            }
        }
    }

    private void setHeadAndPropagate(Node node, int i) {
        Node node2;
        Node node3 = this.head;
        setHead(node);
        if (i > 0 || node3 == null || node3.waitStatus < 0 || (node2 = this.head) == null || node2.waitStatus < 0) {
            Node node4 = node.next;
            if (node4 == null || node4.isShared()) {
                doReleaseShared();
            }
        }
    }

    private void cancelAcquire(Node node) {
        int i;
        if (node != null) {
            node.thread = null;
            Node node2 = node.prev;
            while (node2.waitStatus > 0) {
                node2 = node2.prev;
                node.prev = node2;
            }
            Node node3 = node2.next;
            node.waitStatus = 1;
            if (node != this.tail || !compareAndSetTail(node, node2)) {
                if (node2 == this.head || (((i = node2.waitStatus) != -1 && (i > 0 || !node2.compareAndSetWaitStatus(i, -1))) || node2.thread == null)) {
                    unparkSuccessor(node);
                } else {
                    Node node4 = node.next;
                    if (node4 != null && node4.waitStatus <= 0) {
                        node2.compareAndSetNext(node3, node4);
                    }
                }
                node.next = node;
                return;
            }
            node2.compareAndSetNext(node3, null);
        }
    }

    private static boolean shouldParkAfterFailedAcquire(Node node, Node node2) {
        int i = node.waitStatus;
        if (i == -1) {
            return true;
        }
        if (i > 0) {
            do {
                node = node.prev;
                node2.prev = node;
            } while (node.waitStatus > 0);
            node.next = node2;
            return false;
        }
        node.compareAndSetWaitStatus(i, -1);
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
    public final boolean acquireQueued(Node node, int i) {
        boolean z = false;
        while (true) {
            try {
                Node predecessor = node.predecessor();
                if (predecessor == this.head && tryAcquire(i)) {
                    setHead(node);
                    predecessor.next = null;
                    return z;
                } else if (shouldParkAfterFailedAcquire(predecessor, node) && parkAndCheckInterrupt()) {
                    z = true;
                }
            } catch (Throwable th) {
                cancelAcquire(node);
                throw th;
            }
        }
    }

    private void doAcquireInterruptibly(int i) {
        Node addWaiter = addWaiter(Node.EXCLUSIVE);
        while (true) {
            try {
                Node predecessor = addWaiter.predecessor();
                if (predecessor == this.head && tryAcquire(i)) {
                    setHead(addWaiter);
                    predecessor.next = null;
                    return;
                } else if (shouldParkAfterFailedAcquire(predecessor, addWaiter)) {
                    if (parkAndCheckInterrupt()) {
                        throw new InterruptedException();
                    }
                }
            } catch (Throwable th) {
                cancelAcquire(addWaiter);
                throw th;
            }
        }
    }

    private void doAcquireShared(int i) {
        Node predecessor;
        int tryAcquireShared;
        Node addWaiter = addWaiter(Node.SHARED);
        boolean z = false;
        while (true) {
            try {
                predecessor = addWaiter.predecessor();
                if (predecessor == this.head && (tryAcquireShared = tryAcquireShared(i)) >= 0) {
                    break;
                } else if (shouldParkAfterFailedAcquire(predecessor, addWaiter) && parkAndCheckInterrupt()) {
                    z = true;
                }
            } catch (Throwable th) {
                cancelAcquire(addWaiter);
                throw th;
            }
        }
        setHeadAndPropagate(addWaiter, tryAcquireShared);
        predecessor.next = null;
        if (z) {
            selfInterrupt();
        }
    }

    private boolean doAcquireSharedNanos(int i, long j) {
        int tryAcquireShared;
        if (j <= 0) {
            return false;
        }
        long nanoTime = System.nanoTime() + j;
        Node addWaiter = addWaiter(Node.SHARED);
        while (true) {
            try {
                Node predecessor = addWaiter.predecessor();
                if (predecessor != this.head || (tryAcquireShared = tryAcquireShared(i)) < 0) {
                    long nanoTime2 = nanoTime - System.nanoTime();
                    if (nanoTime2 <= 0) {
                        return false;
                    }
                    if (shouldParkAfterFailedAcquire(predecessor, addWaiter) && nanoTime2 > 1000) {
                        LockSupport.parkNanos(this, nanoTime2);
                    }
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                } else {
                    setHeadAndPropagate(addWaiter, tryAcquireShared);
                    predecessor.next = null;
                    return true;
                }
            } finally {
                cancelAcquire(addWaiter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean tryAcquire(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean tryRelease(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public int tryAcquireShared(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean tryReleaseShared(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }

    public final void acquire(int i) {
        if (!tryAcquire(i) && acquireQueued(addWaiter(Node.EXCLUSIVE), i)) {
            selfInterrupt();
        }
    }

    public final void acquireInterruptibly(int i) {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else if (!tryAcquire(i)) {
            doAcquireInterruptibly(i);
        }
    }

    public final boolean release(int i) {
        if (!tryRelease(i)) {
            return false;
        }
        Node node = this.head;
        if (node == null || node.waitStatus == 0) {
            return true;
        }
        unparkSuccessor(node);
        return true;
    }

    public final void acquireShared(int i) {
        if (tryAcquireShared(i) < 0) {
            doAcquireShared(i);
        }
    }

    public final boolean tryAcquireSharedNanos(int i, long j) {
        if (!Thread.interrupted()) {
            return tryAcquireShared(i) >= 0 || doAcquireSharedNanos(i, j);
        }
        throw new InterruptedException();
    }

    public final boolean releaseShared(int i) {
        if (!tryReleaseShared(i)) {
            return false;
        }
        doReleaseShared();
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean apparentlyFirstQueuedIsExclusive() {
        Node node;
        Node node2 = this.head;
        return (node2 == null || (node = node2.next) == null || node.isShared() || node.thread == null) ? false : true;
    }

    public final boolean hasQueuedPredecessors() {
        Node node;
        Node node2 = this.tail;
        Node node3 = this.head;
        return node3 != node2 && ((node = node3.next) == null || node.thread != Thread.currentThread());
    }

    public String toString() {
        new StringBuilder();
        super.toString();
        throw null;
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
        for (Node node2 = this.tail; node2 != node; node2 = node2.prev) {
            if (node2 == null) {
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
        Node enq = enq(node);
        int i = enq.waitStatus;
        if (i <= 0 && enq.compareAndSetWaitStatus(i, -1)) {
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
            int state2 = getState();
            if (release(state2)) {
                return state2;
            }
            throw new IllegalMonitorStateException();
        } catch (Throwable th) {
            node.waitStatus = 1;
            throw th;
        }
    }

    public class ConditionObject implements Condition, Serializable {
        private static final long serialVersionUID = 1173984872572414699L;
        private transient Node firstWaiter;
        private transient Node lastWaiter;

        public ConditionObject() {
        }

        private Node addConditionWaiter() {
            Node node = this.lastWaiter;
            if (!(node == null || node.waitStatus == -2)) {
                unlinkCancelledWaiters();
                node = this.lastWaiter;
            }
            Node node2 = new Node(-2);
            if (node == null) {
                this.firstWaiter = node2;
            } else {
                node.nextWaiter = node2;
            }
            this.lastWaiter = node2;
            return node2;
        }

        private void doSignal(Node node) {
            do {
                Node node2 = node.nextWaiter;
                this.firstWaiter = node2;
                if (node2 == null) {
                    this.lastWaiter = null;
                }
                node.nextWaiter = null;
                if (!AbstractQueuedSynchronizer.this.transferForSignal(node)) {
                    node = this.firstWaiter;
                } else {
                    return;
                }
            } while (node != null);
        }

        private void doSignalAll(Node node) {
            this.firstWaiter = null;
            this.lastWaiter = null;
            while (true) {
                Node node2 = node.nextWaiter;
                node.nextWaiter = null;
                AbstractQueuedSynchronizer.this.transferForSignal(node);
                if (node2 != null) {
                    node = node2;
                } else {
                    return;
                }
            }
        }

        private void unlinkCancelledWaiters() {
            Node node = this.firstWaiter;
            Node node2 = null;
            while (node != null) {
                Node node3 = node.nextWaiter;
                if (node.waitStatus != -2) {
                    node.nextWaiter = null;
                    if (node2 == null) {
                        this.firstWaiter = node3;
                    } else {
                        node2.nextWaiter = node3;
                    }
                    if (node3 == null) {
                        this.lastWaiter = node2;
                    }
                } else {
                    node2 = node;
                }
                node = node3;
            }
        }

        @Override // java.util.concurrent.locks.Condition
        public final void signal() {
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                Node node = this.firstWaiter;
                if (node != null) {
                    doSignal(node);
                    return;
                }
                return;
            }
            throw new IllegalMonitorStateException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final void signalAll() {
            if (AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                Node node = this.firstWaiter;
                if (node != null) {
                    doSignalAll(node);
                    return;
                }
                return;
            }
            throw new IllegalMonitorStateException();
        }

        private int checkInterruptWhileWaiting(Node node) {
            if (Thread.interrupted()) {
                return AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node) ? -1 : 1;
            }
            return 0;
        }

        private void reportInterruptAfterWait(int i) {
            if (i == -1) {
                throw new InterruptedException();
            } else if (i == 1) {
                AbstractQueuedSynchronizer.selfInterrupt();
            }
        }

        @Override // java.util.concurrent.locks.Condition
        public final void await() {
            if (!Thread.interrupted()) {
                Node addConditionWaiter = addConditionWaiter();
                int fullyRelease = AbstractQueuedSynchronizer.this.fullyRelease(addConditionWaiter);
                int i = 0;
                while (!AbstractQueuedSynchronizer.this.isOnSyncQueue(addConditionWaiter)) {
                    LockSupport.park(this);
                    i = checkInterruptWhileWaiting(addConditionWaiter);
                    if (i != 0) {
                        break;
                    }
                }
                if (AbstractQueuedSynchronizer.this.acquireQueued(addConditionWaiter, fullyRelease) && i != -1) {
                    i = 1;
                }
                if (addConditionWaiter.nextWaiter != null) {
                    unlinkCancelledWaiters();
                }
                if (i != 0) {
                    reportInterruptAfterWait(i);
                    return;
                }
                return;
            }
            throw new InterruptedException();
        }

        @Override // java.util.concurrent.locks.Condition
        public final long awaitNanos(long j) {
            if (!Thread.interrupted()) {
                long nanoTime = System.nanoTime() + j;
                Node addConditionWaiter = addConditionWaiter();
                int fullyRelease = AbstractQueuedSynchronizer.this.fullyRelease(addConditionWaiter);
                int i = 0;
                long j2 = j;
                while (true) {
                    if (AbstractQueuedSynchronizer.this.isOnSyncQueue(addConditionWaiter)) {
                        break;
                    } else if (j2 <= 0) {
                        AbstractQueuedSynchronizer.this.transferAfterCancelledWait(addConditionWaiter);
                        break;
                    } else {
                        if (j2 > 1000) {
                            LockSupport.parkNanos(this, j2);
                        }
                        i = checkInterruptWhileWaiting(addConditionWaiter);
                        if (i != 0) {
                            break;
                        }
                        j2 = nanoTime - System.nanoTime();
                    }
                }
                if (AbstractQueuedSynchronizer.this.acquireQueued(addConditionWaiter, fullyRelease) && i != -1) {
                    i = 1;
                }
                if (addConditionWaiter.nextWaiter != null) {
                    unlinkCancelledWaiters();
                }
                if (i != 0) {
                    reportInterruptAfterWait(i);
                }
                long nanoTime2 = nanoTime - System.nanoTime();
                if (nanoTime2 <= j) {
                    return nanoTime2;
                }
                return Long.MIN_VALUE;
            }
            throw new InterruptedException();
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
        Node node = new Node();
        if (unsafe.compareAndSwapObject(this, j, null, node)) {
            this.tail = node;
        }
    }

    private final boolean compareAndSetTail(Node node, Node node2) {
        return U.compareAndSwapObject(this, TAIL, node, node2);
    }
}
