package java.util.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public class SynchronousQueue extends AbstractQueue implements BlockingQueue, Serializable {
    static final int MAX_TIMED_SPINS = (Runtime.getRuntime().availableProcessors() < 2 ? 0 : 32);
    static final int MAX_UNTIMED_SPINS = (MAX_TIMED_SPINS * 16);
    private static final long serialVersionUID = -3223113410248163686L;
    private ReentrantLock qlock;
    private volatile transient Transferer transferer;
    private WaitQueue waitingConsumers;
    private WaitQueue waitingProducers;

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return new Object[0];
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return "[]";
    }

    static abstract class Transferer {
        /* access modifiers changed from: package-private */
        public abstract Object transfer(Object obj, boolean z, long j);

        Transferer() {
        }
    }

    static final class TransferStack extends Transferer {
        private static final long HEAD;
        private static final Unsafe U = Unsafe.getUnsafe();
        volatile SNode head;

        static boolean isFulfilling(int i) {
            return (i & 2) != 0;
        }

        TransferStack() {
        }

        /* access modifiers changed from: package-private */
        public static final class SNode {
            private static final long MATCH;
            private static final long NEXT;
            private static final Unsafe U = Unsafe.getUnsafe();
            Object item;
            volatile SNode match;
            int mode;
            volatile SNode next;
            volatile Thread waiter;

            SNode(Object obj) {
                this.item = obj;
            }

            /* access modifiers changed from: package-private */
            public boolean casNext(SNode sNode, SNode sNode2) {
                return sNode == this.next && U.compareAndSwapObject(this, NEXT, sNode, sNode2);
            }

            /* access modifiers changed from: package-private */
            public boolean tryMatch(SNode sNode) {
                if (this.match == null && U.compareAndSwapObject(this, MATCH, null, sNode)) {
                    Thread thread = this.waiter;
                    if (thread != null) {
                        this.waiter = null;
                        LockSupport.unpark(thread);
                    }
                    return true;
                } else if (this.match == sNode) {
                    return true;
                } else {
                    return false;
                }
            }

            /* access modifiers changed from: package-private */
            public void tryCancel() {
                U.compareAndSwapObject(this, MATCH, null, this);
            }

            /* access modifiers changed from: package-private */
            public boolean isCancelled() {
                return this.match == this;
            }

            static {
                try {
                    MATCH = U.objectFieldOffset(SNode.class.getDeclaredField("match"));
                    NEXT = U.objectFieldOffset(SNode.class.getDeclaredField("next"));
                } catch (ReflectiveOperationException e) {
                    throw new Error(e);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean casHead(SNode sNode, SNode sNode2) {
            return sNode == this.head && U.compareAndSwapObject(this, HEAD, sNode, sNode2);
        }

        static SNode snode(SNode sNode, Object obj, SNode sNode2, int i) {
            if (sNode == null) {
                sNode = new SNode(obj);
            }
            sNode.mode = i;
            sNode.next = sNode2;
            return sNode;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
            r3 = r2.next;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
            if (r3 != null) goto L_0x0036;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
            r4 = r3.next;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
            if (r3.tryMatch(r2) == false) goto L_0x0049;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
            casHead(r2, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
            if (r0 != 0) goto L_0x0046;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0049, code lost:
            r2.casNext(r3, r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
            return r2.item;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
            return r3.item;
         */
        @Override // java.util.concurrent.SynchronousQueue.Transferer
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object transfer(java.lang.Object r8, boolean r9, long r10) {
            /*
            // Method dump skipped, instructions count: 165
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.SynchronousQueue.TransferStack.transfer(java.lang.Object, boolean, long):java.lang.Object");
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
            */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
        java.util.concurrent.SynchronousQueue.TransferStack.SNode awaitFulfill(java.util.concurrent.SynchronousQueue.TransferStack.SNode r10, boolean r11, long r12) {
            /*
                r9 = this;
                r0 = 0
                if (r11 == 0) goto L_0x000a
                long r2 = java.lang.System.nanoTime()
                long r2 = r2 + r12
                goto L_0x000b
            L_0x000a:
                r2 = r0
            L_0x000b:
                java.lang.Thread r4 = java.lang.Thread.currentThread()
                boolean r5 = r9.shouldSpin(r10)
                r6 = 0
                if (r5 == 0) goto L_0x001e
                if (r11 == 0) goto L_0x001b
                int r5 = java.util.concurrent.SynchronousQueue.MAX_TIMED_SPINS
                goto L_0x001f
            L_0x001b:
                int r5 = java.util.concurrent.SynchronousQueue.MAX_UNTIMED_SPINS
                goto L_0x001f
            L_0x001e:
                r5 = r6
            L_0x001f:
                boolean r7 = r4.isInterrupted()
                if (r7 == 0) goto L_0x0028
                r10.tryCancel()
            L_0x0028:
                java.util.concurrent.SynchronousQueue$TransferStack$SNode r7 = r10.match
                if (r7 == 0) goto L_0x002d
                return r7
            L_0x002d:
                if (r11 == 0) goto L_0x003d
                long r12 = java.lang.System.nanoTime()
                long r12 = r2 - r12
                int r7 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
                if (r7 > 0) goto L_0x003d
                r10.tryCancel()
                goto L_0x001f
            L_0x003d:
                if (r5 <= 0) goto L_0x0048
                boolean r7 = r9.shouldSpin(r10)
                if (r7 == 0) goto L_0x001e
                int r5 = r5 + -1
                goto L_0x001f
            L_0x0048:
                java.lang.Thread r7 = r10.waiter
                if (r7 != 0) goto L_0x004f
                r10.waiter = r4
                goto L_0x001f
            L_0x004f:
                if (r11 != 0) goto L_0x0055
                java.util.concurrent.locks.LockSupport.park(r9)
                goto L_0x001f
            L_0x0055:
                r7 = 1000(0x3e8, double:4.94E-321)
                int r7 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
                if (r7 <= 0) goto L_0x001f
                java.util.concurrent.locks.LockSupport.parkNanos(r9, r12)
                goto L_0x001f
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.SynchronousQueue.TransferStack.awaitFulfill(java.util.concurrent.SynchronousQueue$TransferStack$SNode, boolean, long):java.util.concurrent.SynchronousQueue$TransferStack$SNode");
        }

        /* access modifiers changed from: package-private */
        public boolean shouldSpin(SNode sNode) {
            SNode sNode2 = this.head;
            return sNode2 == sNode || sNode2 == null || isFulfilling(sNode2.mode);
        }

        /* access modifiers changed from: package-private */
        public void clean(SNode sNode) {
            SNode sNode2;
            sNode.item = null;
            sNode.waiter = null;
            SNode sNode3 = sNode.next;
            if (sNode3 != null && sNode3.isCancelled()) {
                sNode3 = sNode3.next;
            }
            while (true) {
                sNode2 = this.head;
                if (sNode2 != null && sNode2 != sNode3 && sNode2.isCancelled()) {
                    casHead(sNode2, sNode2.next);
                }
            }
            while (sNode2 != null && sNode2 != sNode3) {
                SNode sNode4 = sNode2.next;
                if (sNode4 == null || !sNode4.isCancelled()) {
                    sNode2 = sNode4;
                } else {
                    sNode2.casNext(sNode4, sNode4.next);
                }
            }
        }

        static {
            try {
                HEAD = U.objectFieldOffset(TransferStack.class.getDeclaredField("head"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    static final class TransferQueue extends Transferer {
        private static final long CLEANME;
        private static final long HEAD;
        private static final long TAIL;
        private static final Unsafe U = Unsafe.getUnsafe();
        volatile transient QNode cleanMe;
        volatile transient QNode head;
        volatile transient QNode tail;

        /* access modifiers changed from: package-private */
        public static final class QNode {
            private static final long ITEM;
            private static final long NEXT;
            private static final Unsafe U = Unsafe.getUnsafe();
            final boolean isData;
            volatile Object item;
            volatile QNode next;
            volatile Thread waiter;

            QNode(Object obj, boolean z) {
                this.item = obj;
                this.isData = z;
            }

            /* access modifiers changed from: package-private */
            public boolean casNext(QNode qNode, QNode qNode2) {
                return this.next == qNode && U.compareAndSwapObject(this, NEXT, qNode, qNode2);
            }

            /* access modifiers changed from: package-private */
            public boolean casItem(Object obj, Object obj2) {
                return this.item == obj && U.compareAndSwapObject(this, ITEM, obj, obj2);
            }

            /* access modifiers changed from: package-private */
            public void tryCancel(Object obj) {
                U.compareAndSwapObject(this, ITEM, obj, this);
            }

            /* access modifiers changed from: package-private */
            public boolean isCancelled() {
                return this.item == this;
            }

            /* access modifiers changed from: package-private */
            public boolean isOffList() {
                return this.next == this;
            }

            static {
                try {
                    ITEM = U.objectFieldOffset(QNode.class.getDeclaredField("item"));
                    NEXT = U.objectFieldOffset(QNode.class.getDeclaredField("next"));
                } catch (ReflectiveOperationException e) {
                    throw new Error(e);
                }
            }
        }

        TransferQueue() {
            QNode qNode = new QNode(null, false);
            this.head = qNode;
            this.tail = qNode;
        }

        /* access modifiers changed from: package-private */
        public void advanceHead(QNode qNode, QNode qNode2) {
            if (qNode == this.head && U.compareAndSwapObject(this, HEAD, qNode, qNode2)) {
                qNode.next = qNode;
            }
        }

        /* access modifiers changed from: package-private */
        public void advanceTail(QNode qNode, QNode qNode2) {
            if (this.tail == qNode) {
                U.compareAndSwapObject(this, TAIL, qNode, qNode2);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean casCleanMe(QNode qNode, QNode qNode2) {
            return this.cleanMe == qNode && U.compareAndSwapObject(this, CLEANME, qNode, qNode2);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.SynchronousQueue.Transferer
        public Object transfer(Object obj, boolean z, long j) {
            boolean z2 = obj != null;
            QNode qNode = null;
            while (true) {
                QNode qNode2 = this.tail;
                QNode qNode3 = this.head;
                if (!(qNode2 == null || qNode3 == null)) {
                    if (qNode3 == qNode2 || qNode2.isData == z2) {
                        QNode qNode4 = qNode2.next;
                        if (qNode2 != this.tail) {
                            continue;
                        } else if (qNode4 != null) {
                            advanceTail(qNode2, qNode4);
                        } else if (z && j <= 0) {
                            return null;
                        } else {
                            if (qNode == null) {
                                qNode = new QNode(obj, z2);
                            }
                            if (!qNode2.casNext(null, qNode)) {
                                qNode = qNode;
                            } else {
                                advanceTail(qNode2, qNode);
                                Object awaitFulfill = awaitFulfill(qNode, obj, z, j);
                                if (awaitFulfill == qNode) {
                                    clean(qNode2, qNode);
                                    return null;
                                }
                                if (!qNode.isOffList()) {
                                    advanceHead(qNode2, qNode);
                                    if (awaitFulfill != null) {
                                        qNode.item = qNode;
                                    }
                                    qNode.waiter = null;
                                }
                                return awaitFulfill != null ? awaitFulfill : obj;
                            }
                        }
                    } else {
                        QNode qNode5 = qNode3.next;
                        if (qNode2 == this.tail && qNode5 != null && qNode3 == this.head) {
                            Object obj2 = qNode5.item;
                            if (z2 == (obj2 != null) || obj2 == qNode5 || !qNode5.casItem(obj2, obj)) {
                                advanceHead(qNode3, qNode5);
                            } else {
                                advanceHead(qNode3, qNode5);
                                LockSupport.unpark(qNode5.waiter);
                                return obj2 != null ? obj2 : obj;
                            }
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Object awaitFulfill(QNode qNode, Object obj, boolean z, long j) {
            long nanoTime = z ? System.nanoTime() + j : 0;
            Thread currentThread = Thread.currentThread();
            int i = this.head.next == qNode ? z ? SynchronousQueue.MAX_TIMED_SPINS : SynchronousQueue.MAX_UNTIMED_SPINS : 0;
            while (true) {
                if (currentThread.isInterrupted()) {
                    qNode.tryCancel(obj);
                }
                Object obj2 = qNode.item;
                if (obj2 != obj) {
                    return obj2;
                }
                if (z) {
                    j = nanoTime - System.nanoTime();
                    if (j <= 0) {
                        qNode.tryCancel(obj);
                    }
                }
                if (i > 0) {
                    i--;
                } else if (qNode.waiter == null) {
                    qNode.waiter = currentThread;
                } else if (!z) {
                    LockSupport.park(this);
                } else if (j > 1000) {
                    LockSupport.parkNanos(this, j);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void clean(QNode qNode, QNode qNode2) {
            QNode qNode3;
            QNode qNode4;
            qNode2.waiter = null;
            while (qNode.next == qNode2) {
                QNode qNode5 = this.head;
                QNode qNode6 = qNode5.next;
                if (qNode6 == null || !qNode6.isCancelled()) {
                    QNode qNode7 = this.tail;
                    if (qNode7 != qNode5) {
                        QNode qNode8 = qNode7.next;
                        if (qNode7 != this.tail) {
                            continue;
                        } else if (qNode8 != null) {
                            advanceTail(qNode7, qNode8);
                        } else if (qNode2 == qNode7 || ((qNode4 = qNode2.next) != qNode2 && !qNode.casNext(qNode2, qNode4))) {
                            QNode qNode9 = this.cleanMe;
                            if (qNode9 != null) {
                                QNode qNode10 = qNode9.next;
                                if (qNode10 == null || qNode10 == qNode9 || !qNode10.isCancelled() || !(qNode10 == qNode7 || (qNode3 = qNode10.next) == null || qNode3 == qNode10 || !qNode9.casNext(qNode10, qNode3))) {
                                    casCleanMe(qNode9, null);
                                }
                                if (qNode9 == qNode) {
                                    return;
                                }
                            } else if (casCleanMe(null, qNode)) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    advanceHead(qNode5, qNode6);
                }
            }
        }

        static {
            try {
                HEAD = U.objectFieldOffset(TransferQueue.class.getDeclaredField("head"));
                TAIL = U.objectFieldOffset(TransferQueue.class.getDeclaredField("tail"));
                CLEANME = U.objectFieldOffset(TransferQueue.class.getDeclaredField("cleanMe"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    public SynchronousQueue() {
        this(false);
    }

    public SynchronousQueue(boolean z) {
        this.transferer = z ? new TransferQueue() : new TransferStack();
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(Object obj) {
        if (obj != null) {
            return this.transferer.transfer(obj, true, 0) != null;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public Object take() {
        Object transfer = this.transferer.transfer(null, false, 0);
        if (transfer != null) {
            return transfer;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public Object poll(long j, TimeUnit timeUnit) {
        Object transfer = this.transferer.transfer(null, true, timeUnit.toNanos(j));
        if (transfer != null || !Thread.interrupted()) {
            return transfer;
        }
        throw new InterruptedException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean containsAll(Collection collection) {
        return collection.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return Collections.emptyIterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        if (objArr.length > 0) {
            objArr[0] = null;
        }
        return objArr;
    }

    static class WaitQueue implements Serializable {
        WaitQueue() {
        }
    }

    static class LifoWaitQueue extends WaitQueue {
        private static final long serialVersionUID = -3633113410248163686L;

        LifoWaitQueue() {
        }
    }

    static class FifoWaitQueue extends WaitQueue {
        private static final long serialVersionUID = -3623113410248163686L;

        FifoWaitQueue() {
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        if (this.transferer instanceof TransferQueue) {
            this.qlock = new ReentrantLock(true);
            this.waitingProducers = new FifoWaitQueue();
            this.waitingConsumers = new FifoWaitQueue();
        } else {
            this.qlock = new ReentrantLock();
            this.waitingProducers = new LifoWaitQueue();
            this.waitingConsumers = new LifoWaitQueue();
        }
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
