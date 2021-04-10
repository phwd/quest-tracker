package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public class SynchronousQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    static final int MAX_TIMED_SPINS = (Runtime.getRuntime().availableProcessors() < 2 ? 0 : 32);
    static final int MAX_UNTIMED_SPINS = (MAX_TIMED_SPINS * 16);
    static final long SPIN_FOR_TIMEOUT_THRESHOLD = 1000;
    private static final long serialVersionUID = -3223113410248163686L;
    private ReentrantLock qlock;
    private volatile transient Transferer<E> transferer;
    private WaitQueue waitingConsumers;
    private WaitQueue waitingProducers;

    /* access modifiers changed from: package-private */
    public static abstract class Transferer<E> {
        /* access modifiers changed from: package-private */
        public abstract E transfer(E e, boolean z, long j);

        Transferer() {
        }
    }

    static final class TransferStack<E> extends Transferer<E> {
        static final int DATA = 1;
        static final int FULFILLING = 2;
        private static final long HEAD;
        static final int REQUEST = 0;
        private static final Unsafe U = Unsafe.getUnsafe();
        volatile SNode head;

        TransferStack() {
        }

        static boolean isFulfilling(int m) {
            return (m & 2) != 0;
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

            SNode(Object item2) {
                this.item = item2;
            }

            /* access modifiers changed from: package-private */
            public boolean casNext(SNode cmp, SNode val) {
                return cmp == this.next && U.compareAndSwapObject(this, NEXT, cmp, val);
            }

            /* access modifiers changed from: package-private */
            public boolean tryMatch(SNode s) {
                if (this.match == null && U.compareAndSwapObject(this, MATCH, null, s)) {
                    Thread w = this.waiter;
                    if (w != null) {
                        this.waiter = null;
                        LockSupport.unpark(w);
                    }
                    return true;
                } else if (this.match == s) {
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
        public boolean casHead(SNode h, SNode nh) {
            return h == this.head && U.compareAndSwapObject(this, HEAD, h, nh);
        }

        static SNode snode(SNode s, Object e, SNode next, int mode) {
            if (s == null) {
                s = new SNode(e);
            }
            s.mode = mode;
            s.next = next;
            return s;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.SynchronousQueue.Transferer
        public E transfer(E e, boolean timed, long nanos) {
            SNode s = null;
            int mode = e == null ? 0 : 1;
            while (true) {
                SNode h = this.head;
                if (h == null || h.mode == mode) {
                    if (!timed || nanos > 0) {
                        SNode snode = snode(s, e, h, mode);
                        s = snode;
                        if (casHead(h, snode)) {
                            SNode m = awaitFulfill(s, timed, nanos);
                            if (m == s) {
                                clean(s);
                                return null;
                            }
                            SNode h2 = this.head;
                            if (h2 != null && h2.next == s) {
                                casHead(h2, s.next);
                            }
                            return mode == 0 ? (E) m.item : (E) s.item;
                        }
                    } else if (h == null || !h.isCancelled()) {
                        return null;
                    } else {
                        casHead(h, h.next);
                    }
                } else if (isFulfilling(h.mode)) {
                    SNode m2 = h.next;
                    if (m2 == null) {
                        casHead(h, null);
                    } else {
                        SNode mn = m2.next;
                        if (m2.tryMatch(h)) {
                            casHead(h, mn);
                        } else {
                            h.casNext(m2, mn);
                        }
                    }
                } else if (h.isCancelled()) {
                    casHead(h, h.next);
                } else {
                    SNode snode2 = snode(s, e, h, mode | 2);
                    s = snode2;
                    if (casHead(h, snode2)) {
                        while (true) {
                            SNode m3 = s.next;
                            if (m3 == null) {
                                casHead(s, null);
                                s = null;
                                break;
                            }
                            SNode mn2 = m3.next;
                            if (m3.tryMatch(s)) {
                                casHead(s, mn2);
                                return mode == 0 ? (E) m3.item : (E) s.item;
                            }
                            s.casNext(m3, mn2);
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public SNode awaitFulfill(SNode s, boolean timed, long nanos) {
            int spins;
            long deadline = timed ? System.nanoTime() + nanos : 0;
            Thread w = Thread.currentThread();
            if (shouldSpin(s)) {
                spins = timed ? SynchronousQueue.MAX_TIMED_SPINS : SynchronousQueue.MAX_UNTIMED_SPINS;
            } else {
                spins = 0;
            }
            while (true) {
                if (w.isInterrupted()) {
                    s.tryCancel();
                }
                SNode m = s.match;
                if (m != null) {
                    return m;
                }
                if (timed) {
                    nanos = deadline - System.nanoTime();
                    if (nanos <= 0) {
                        s.tryCancel();
                    }
                }
                if (spins > 0) {
                    spins = shouldSpin(s) ? spins - 1 : 0;
                } else if (s.waiter == null) {
                    s.waiter = w;
                } else if (!timed) {
                    LockSupport.park(this);
                } else if (nanos > 1000) {
                    LockSupport.parkNanos(this, nanos);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean shouldSpin(SNode s) {
            SNode h = this.head;
            return h == s || h == null || isFulfilling(h.mode);
        }

        /* access modifiers changed from: package-private */
        public void clean(SNode s) {
            SNode p;
            s.item = null;
            s.waiter = null;
            SNode past = s.next;
            if (past != null && past.isCancelled()) {
                past = past.next;
            }
            while (true) {
                SNode sNode = this.head;
                p = sNode;
                if (sNode != null && p != past && p.isCancelled()) {
                    casHead(p, p.next);
                }
            }
            while (p != null && p != past) {
                SNode n = p.next;
                if (n == null || !n.isCancelled()) {
                    p = n;
                } else {
                    p.casNext(n, n.next);
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

    static final class TransferQueue<E> extends Transferer<E> {
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

            QNode(Object item2, boolean isData2) {
                this.item = item2;
                this.isData = isData2;
            }

            /* access modifiers changed from: package-private */
            public boolean casNext(QNode cmp, QNode val) {
                return this.next == cmp && U.compareAndSwapObject(this, NEXT, cmp, val);
            }

            /* access modifiers changed from: package-private */
            public boolean casItem(Object cmp, Object val) {
                return this.item == cmp && U.compareAndSwapObject(this, ITEM, cmp, val);
            }

            /* access modifiers changed from: package-private */
            public void tryCancel(Object cmp) {
                U.compareAndSwapObject(this, ITEM, cmp, this);
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
            QNode h = new QNode(null, false);
            this.head = h;
            this.tail = h;
        }

        /* access modifiers changed from: package-private */
        public void advanceHead(QNode h, QNode nh) {
            if (h == this.head && U.compareAndSwapObject(this, HEAD, h, nh)) {
                h.next = h;
            }
        }

        /* access modifiers changed from: package-private */
        public void advanceTail(QNode t, QNode nt) {
            if (this.tail == t) {
                U.compareAndSwapObject(this, TAIL, t, nt);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean casCleanMe(QNode cmp, QNode val) {
            return this.cleanMe == cmp && U.compareAndSwapObject(this, CLEANME, cmp, val);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.SynchronousQueue.Transferer
        public E transfer(E e, boolean timed, long nanos) {
            QNode s;
            QNode s2 = null;
            boolean isData = e != null;
            while (true) {
                QNode t = this.tail;
                QNode h = this.head;
                if (!(t == null || h == null)) {
                    if (h == t || t.isData == isData) {
                        QNode tn = t.next;
                        if (t != this.tail) {
                            continue;
                        } else if (tn != null) {
                            advanceTail(t, tn);
                        } else if (timed && nanos <= 0) {
                            return null;
                        } else {
                            if (s2 == null) {
                                s = new QNode(e, isData);
                            } else {
                                s = s2;
                            }
                            if (!t.casNext(null, s)) {
                                s2 = s;
                            } else {
                                advanceTail(t, s);
                                E e2 = (E) awaitFulfill(s, e, timed, nanos);
                                if (e2 == s) {
                                    clean(t, s);
                                    return null;
                                }
                                if (!s.isOffList()) {
                                    advanceHead(t, s);
                                    if (e2 != null) {
                                        s.item = s;
                                    }
                                    s.waiter = null;
                                }
                                return e2 != null ? e2 : e;
                            }
                        }
                    } else {
                        QNode m = h.next;
                        if (t == this.tail && m != null && h == this.head) {
                            E e3 = (E) m.item;
                            if (isData == (e3 != null) || e3 == m || !m.casItem(e3, e)) {
                                advanceHead(h, m);
                            } else {
                                advanceHead(h, m);
                                LockSupport.unpark(m.waiter);
                                return e3 != null ? e3 : e;
                            }
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Object awaitFulfill(QNode s, E e, boolean timed, long nanos) {
            int spins;
            long deadline = timed ? System.nanoTime() + nanos : 0;
            Thread w = Thread.currentThread();
            if (this.head.next == s) {
                spins = timed ? SynchronousQueue.MAX_TIMED_SPINS : SynchronousQueue.MAX_UNTIMED_SPINS;
            } else {
                spins = 0;
            }
            while (true) {
                if (w.isInterrupted()) {
                    s.tryCancel(e);
                }
                Object x = s.item;
                if (x != e) {
                    return x;
                }
                if (timed) {
                    nanos = deadline - System.nanoTime();
                    if (nanos <= 0) {
                        s.tryCancel(e);
                    }
                }
                if (spins > 0) {
                    spins--;
                } else if (s.waiter == null) {
                    s.waiter = w;
                } else if (!timed) {
                    LockSupport.park(this);
                } else if (nanos > 1000) {
                    LockSupport.parkNanos(this, nanos);
                }
            }
        }

        /* JADX INFO: Multiple debug info for r5v1 java.util.concurrent.SynchronousQueue$TransferQueue$QNode: [D('sn' java.util.concurrent.SynchronousQueue$TransferQueue$QNode), D('dp' java.util.concurrent.SynchronousQueue$TransferQueue$QNode)] */
        /* access modifiers changed from: package-private */
        public void clean(QNode pred, QNode s) {
            QNode dn;
            QNode sn;
            s.waiter = null;
            while (pred.next == s) {
                QNode h = this.head;
                QNode hn = h.next;
                if (hn == null || !hn.isCancelled()) {
                    QNode t = this.tail;
                    if (t != h) {
                        QNode tn = t.next;
                        if (t != this.tail) {
                            continue;
                        } else if (tn != null) {
                            advanceTail(t, tn);
                        } else if (s == t || ((sn = s.next) != s && !pred.casNext(s, sn))) {
                            QNode dp = this.cleanMe;
                            if (dp != null) {
                                QNode d = dp.next;
                                if (d == null || d == dp || !d.isCancelled() || !(d == t || (dn = d.next) == null || dn == d || !dp.casNext(d, dn))) {
                                    casCleanMe(dp, null);
                                }
                                if (dp == pred) {
                                    return;
                                }
                            } else if (casCleanMe(null, pred)) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    advanceHead(h, hn);
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

    public SynchronousQueue(boolean fair) {
        this.transferer = fair ? new TransferQueue<>() : new TransferStack<>();
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        } else if (this.transferer.transfer(e, false, 0) == null) {
            Thread.interrupted();
            throw new InterruptedException();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        } else if (this.transferer.transfer(e, true, unit.toNanos(timeout)) != null) {
            return true;
        } else {
            if (!Thread.interrupted()) {
                return false;
            }
            throw new InterruptedException();
        }
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(E e) {
        if (e != null) {
            return this.transferer.transfer(e, true, 0) != null;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        E e = this.transferer.transfer(null, false, 0);
        if (e != null) {
            return e;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E e = this.transferer.transfer(null, true, unit.toNanos(timeout));
        if (e != null || !Thread.interrupted()) {
            return e;
        }
        throw new InterruptedException();
    }

    @Override // java.util.Queue
    public E poll() {
        return this.transferer.transfer(null, true, 0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return 0;
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public void clear() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object o) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        return c.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override // java.util.Queue
    public E peek() {
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return Collections.emptyIterator();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Spliterators.emptySpliterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return new Object[0];
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        if (a.length > 0) {
            a[0] = null;
        }
        return a;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return "[]";
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c != this) {
            int n = 0;
            while (true) {
                E e = poll();
                if (e == null) {
                    return n;
                }
                c.add(e);
                n++;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c != this) {
            int n = 0;
            while (n < maxElements) {
                E e = poll();
                if (e == null) {
                    break;
                }
                c.add(e);
                n++;
            }
            return n;
        } else {
            throw new IllegalArgumentException();
        }
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

    private void writeObject(ObjectOutputStream s) throws IOException {
        if (this.transferer instanceof TransferQueue) {
            this.qlock = new ReentrantLock(true);
            this.waitingProducers = new FifoWaitQueue();
            this.waitingConsumers = new FifoWaitQueue();
        } else {
            this.qlock = new ReentrantLock();
            this.waitingProducers = new LifoWaitQueue();
            this.waitingConsumers = new LifoWaitQueue();
        }
        s.defaultWriteObject();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        if (this.waitingProducers instanceof FifoWaitQueue) {
            this.transferer = new TransferQueue();
        } else {
            this.transferer = new TransferStack();
        }
    }
}
