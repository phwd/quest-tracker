package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;
import sun.misc.Unsafe;

public class LinkedTransferQueue<E> extends AbstractQueue<E> implements TransferQueue<E>, Serializable {
    private static final int ASYNC = 1;
    private static final int CHAINED_SPINS = 64;
    private static final int FRONT_SPINS = 128;
    private static final long HEAD;
    private static final boolean MP;
    private static final int NOW = 0;
    private static final long SWEEPVOTES;
    static final int SWEEP_THRESHOLD = 32;
    private static final int SYNC = 2;
    private static final long TAIL;
    private static final int TIMED = 3;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = -3223113410248163686L;
    volatile transient Node head;
    private volatile transient int sweepVotes;
    private volatile transient Node tail;

    static {
        boolean z = true;
        if (Runtime.getRuntime().availableProcessors() <= 1) {
            z = false;
        }
        MP = z;
        try {
            HEAD = U.objectFieldOffset(LinkedTransferQueue.class.getDeclaredField("head"));
            TAIL = U.objectFieldOffset(LinkedTransferQueue.class.getDeclaredField("tail"));
            SWEEPVOTES = U.objectFieldOffset(LinkedTransferQueue.class.getDeclaredField("sweepVotes"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Node {
        private static final long ITEM;
        private static final long NEXT;
        private static final Unsafe U = Unsafe.getUnsafe();
        private static final long WAITER;
        private static final long serialVersionUID = -3375979862319811754L;
        final boolean isData;
        volatile Object item;
        volatile Node next;
        volatile Thread waiter;

        /* access modifiers changed from: package-private */
        public final boolean casNext(Node cmp, Node val) {
            return U.compareAndSwapObject(this, NEXT, cmp, val);
        }

        /* access modifiers changed from: package-private */
        public final boolean casItem(Object cmp, Object val) {
            return U.compareAndSwapObject(this, ITEM, cmp, val);
        }

        Node(Object item2, boolean isData2) {
            U.putObject(this, ITEM, item2);
            this.isData = isData2;
        }

        /* access modifiers changed from: package-private */
        public final void forgetNext() {
            U.putObject(this, NEXT, this);
        }

        /* access modifiers changed from: package-private */
        public final void forgetContents() {
            U.putObject(this, ITEM, this);
            U.putObject(this, WAITER, null);
        }

        /* access modifiers changed from: package-private */
        public final boolean isMatched() {
            Object x = this.item;
            if (x != this) {
                if ((x == null) != this.isData) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean isUnmatchedRequest() {
            return !this.isData && this.item == null;
        }

        /* access modifiers changed from: package-private */
        public final boolean cannotPrecede(boolean haveData) {
            Object x;
            boolean d = this.isData;
            if (!(d == haveData || (x = this.item) == this)) {
                if ((x != null) == d) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public final boolean tryMatchData() {
            Object x = this.item;
            if (x == null || x == this || !casItem(x, null)) {
                return false;
            }
            LockSupport.unpark(this.waiter);
            return true;
        }

        static {
            try {
                ITEM = U.objectFieldOffset(Node.class.getDeclaredField("item"));
                NEXT = U.objectFieldOffset(Node.class.getDeclaredField("next"));
                WAITER = U.objectFieldOffset(Node.class.getDeclaredField("waiter"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    private boolean casTail(Node cmp, Node val) {
        return U.compareAndSwapObject(this, TAIL, cmp, val);
    }

    private boolean casHead(Node cmp, Node val) {
        return U.compareAndSwapObject(this, HEAD, cmp, val);
    }

    private boolean casSweepVotes(int cmp, int val) {
        return U.compareAndSwapInt(this, SWEEPVOTES, cmp, val);
    }

    /* JADX INFO: Multiple debug info for r3v2 java.util.concurrent.LinkedTransferQueue$Node: [D('itemE' E), D('n' java.util.concurrent.LinkedTransferQueue$Node)] */
    private E xfer(E e, boolean haveData, int how, long nanos) {
        boolean z;
        Node s;
        Node node;
        if (!haveData || e != null) {
            Node s2 = null;
            while (true) {
                Node h = this.head;
                Node p = h;
                while (true) {
                    z = true;
                    if (p == null) {
                        break;
                    }
                    boolean isData = p.isData;
                    E e2 = (E) p.item;
                    if (e2 != p) {
                        if ((e2 != null) == isData) {
                            if (isData == haveData) {
                                break;
                            } else if (p.casItem(e2, e)) {
                                Node q = p;
                                while (true) {
                                    if (q == h) {
                                        break;
                                    }
                                    Node n = q.next;
                                    if (this.head == h) {
                                        if (casHead(h, n == null ? q : n)) {
                                            h.forgetNext();
                                            break;
                                        }
                                    }
                                    Node node2 = this.head;
                                    h = node2;
                                    if (node2 == null) {
                                        break;
                                    }
                                    Node node3 = h.next;
                                    q = node3;
                                    if (node3 != null) {
                                        if (!q.isMatched()) {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                LockSupport.unpark(p.waiter);
                                return e2;
                            }
                        }
                    }
                    Node n2 = p.next;
                    if (p != n2) {
                        node = n2;
                    } else {
                        node = this.head;
                        h = node;
                    }
                    p = node;
                }
                if (how == 0) {
                    break;
                }
                if (s2 == null) {
                    s = new Node(e, haveData);
                } else {
                    s = s2;
                }
                Node pred = tryAppend(s, haveData);
                if (pred == null) {
                    s2 = s;
                } else if (how != 1) {
                    if (how != 3) {
                        z = false;
                    }
                    return awaitMatch(s, pred, e, z, nanos);
                }
            }
            return e;
        }
        throw new NullPointerException();
    }

    private Node tryAppend(Node s, boolean haveData) {
        Node s2;
        Node u;
        Node t = this.tail;
        Node p = t;
        while (true) {
            Node node = null;
            if (p == null) {
                Node node2 = this.head;
                p = node2;
                if (node2 == null) {
                    if (casHead(null, s)) {
                        return s;
                    }
                }
            }
            if (p.cannotPrecede(haveData)) {
                return null;
            }
            Node n = p.next;
            if (n != null) {
                if (p != t && t != (u = this.tail)) {
                    t = u;
                    node = u;
                } else if (p != n) {
                    node = n;
                }
                p = node;
            } else if (!p.casNext(null, s)) {
                p = p.next;
            } else {
                if (p != t) {
                    do {
                        if (this.tail == t && casTail(t, s)) {
                            break;
                        }
                        Node node3 = this.tail;
                        t = node3;
                        if (node3 != null && (s2 = t.next) != null) {
                            Node node4 = s2.next;
                            s = node4;
                            if (node4 == null) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } while (s != t);
                }
                return p;
            }
        }
    }

    private E awaitMatch(Node s, Node pred, E e, boolean timed, long nanos) {
        long deadline = timed ? System.nanoTime() + nanos : 0;
        Thread w = Thread.currentThread();
        int spins = -1;
        ThreadLocalRandom randomYields = null;
        long nanos2 = nanos;
        while (true) {
            E e2 = (E) s.item;
            if (e2 != e) {
                s.forgetContents();
                return e2;
            } else if (w.isInterrupted() || (timed && nanos2 <= 0)) {
                unsplice(pred, s);
                if (s.casItem(e, s)) {
                    return e;
                }
            } else if (spins < 0) {
                int spinsFor = spinsFor(pred, s.isData);
                spins = spinsFor;
                if (spinsFor > 0) {
                    randomYields = ThreadLocalRandom.current();
                }
            } else if (spins > 0) {
                spins--;
                if (randomYields.nextInt(64) == 0) {
                    Thread.yield();
                }
            } else if (s.waiter == null) {
                s.waiter = w;
            } else if (timed) {
                nanos2 = deadline - System.nanoTime();
                if (nanos2 > 0) {
                    LockSupport.parkNanos(this, nanos2);
                }
            } else {
                LockSupport.park(this);
            }
        }
    }

    private static int spinsFor(Node pred, boolean haveData) {
        if (!MP || pred == null) {
            return 0;
        }
        if (pred.isData != haveData) {
            return 192;
        }
        if (pred.isMatched()) {
            return 128;
        }
        if (pred.waiter == null) {
            return 64;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final Node succ(Node p) {
        Node next = p.next;
        return p == next ? this.head : next;
    }

    /* access modifiers changed from: package-private */
    public final Node firstDataNode() {
        while (true) {
            Node p = this.head;
            while (true) {
                if (p == null) {
                    return null;
                }
                Object item = p.item;
                if (p.isData) {
                    if (!(item == null || item == p)) {
                        return p;
                    }
                } else if (item == null) {
                    return null;
                }
                Node p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
    }

    private int countOfMode(boolean data) {
        int count;
        loop0:
        while (true) {
            count = 0;
            Node p = this.head;
            while (true) {
                if (p == null) {
                    break loop0;
                }
                if (!p.isMatched()) {
                    if (p.isData == data) {
                        count++;
                        if (count == Integer.MAX_VALUE) {
                            break loop0;
                        }
                    } else {
                        return 0;
                    }
                }
                Node p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
        return count;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        int charLength;
        int size;
        String[] a = null;
        loop0:
        while (true) {
            charLength = 0;
            size = 0;
            Node p = this.head;
            while (true) {
                if (p == null) {
                    break loop0;
                }
                Object item = p.item;
                if (!p.isData) {
                    if (item == null) {
                        break loop0;
                    }
                } else if (!(item == null || item == p)) {
                    if (a == null) {
                        a = new String[4];
                    } else if (size == a.length) {
                        a = (String[]) Arrays.copyOf(a, size * 2);
                    }
                    String s = item.toString();
                    a[size] = s;
                    charLength += s.length();
                    size++;
                }
                Node p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
        if (size == 0) {
            return "[]";
        }
        return Helpers.toString(a, size, charLength);
    }

    private Object[] toArrayInternal(Object[] a) {
        int size;
        Object[] x = a;
        loop0:
        while (true) {
            size = 0;
            Node p = this.head;
            while (true) {
                if (p == null) {
                    break loop0;
                }
                Object item = p.item;
                if (!p.isData) {
                    if (item == null) {
                        break loop0;
                    }
                } else if (!(item == null || item == p)) {
                    if (x == null) {
                        x = new Object[4];
                    } else if (size == x.length) {
                        x = Arrays.copyOf(x, (size + 4) * 2);
                    }
                    x[size] = item;
                    size++;
                }
                Node p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
        if (x == null) {
            return new Object[0];
        }
        if (a == null || size > a.length) {
            return size == x.length ? x : Arrays.copyOf(x, size);
        }
        if (a != x) {
            System.arraycopy(x, 0, a, 0, size);
        }
        if (size < a.length) {
            a[size] = null;
        }
        return a;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArrayInternal(null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        if (a != null) {
            return (T[]) toArrayInternal(a);
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public final class Itr implements Iterator<E> {
        private Node lastPred;
        private Node lastRet;
        private E nextItem;
        private Node nextNode;

        private void advance(Node prev) {
            Node n;
            Node r = this.lastRet;
            if (r == null || r.isMatched()) {
                Node b = this.lastPred;
                if (b != null && !b.isMatched()) {
                    while (true) {
                        Node s = b.next;
                        if (s == null || s == b || !s.isMatched() || (n = s.next) == null || n == s) {
                            break;
                        }
                        b.casNext(s, n);
                    }
                } else {
                    this.lastPred = null;
                }
            } else {
                this.lastPred = r;
            }
            this.lastRet = prev;
            Node p = prev;
            while (true) {
                Node s2 = p == null ? LinkedTransferQueue.this.head : p.next;
                if (s2 == null) {
                    break;
                } else if (s2 == p) {
                    p = null;
                } else {
                    E e = (E) s2.item;
                    if (!s2.isData) {
                        if (e == null) {
                            break;
                        }
                    } else if (!(e == null || e == s2)) {
                        this.nextItem = e;
                        this.nextNode = s2;
                        return;
                    }
                    if (p == null) {
                        p = s2;
                    } else {
                        Node n2 = s2.next;
                        if (n2 == null) {
                            break;
                        } else if (s2 == n2) {
                            p = null;
                        } else {
                            p.casNext(s2, n2);
                        }
                    }
                }
            }
            this.nextNode = null;
            this.nextItem = null;
        }

        Itr() {
            advance(null);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.nextNode != null;
        }

        @Override // java.util.Iterator
        public final E next() {
            Node p = this.nextNode;
            if (p != null) {
                E e = this.nextItem;
                advance(p);
                return e;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node lastRet2 = this.lastRet;
            if (lastRet2 != null) {
                this.lastRet = null;
                if (lastRet2.tryMatchData()) {
                    LinkedTransferQueue.this.unsplice(this.lastPred, lastRet2);
                    return;
                }
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class LTQSpliterator<E> implements Spliterator<E> {
        static final int MAX_BATCH = 33554432;
        int batch;
        Node current;
        boolean exhausted;

        LTQSpliterator() {
        }

        @Override // java.util.Spliterator
        public Spliterator<E> trySplit() {
            int b = this.batch;
            int n = MAX_BATCH;
            if (b <= 0) {
                n = 1;
            } else if (b < MAX_BATCH) {
                n = b + 1;
            }
            if (this.exhausted) {
                return null;
            }
            Node node = this.current;
            Node p = node;
            if (node == null) {
                Node firstDataNode = LinkedTransferQueue.this.firstDataNode();
                p = firstDataNode;
                if (firstDataNode == null) {
                    return null;
                }
            }
            if (p.next == null) {
                return null;
            }
            Object[] a = new Object[n];
            int i = 0;
            do {
                Object e = p.item;
                if (e != p) {
                    a[i] = e;
                    if (e != null) {
                        i++;
                    }
                }
                Node p2 = p.next;
                if (p == p2) {
                    p = LinkedTransferQueue.this.firstDataNode();
                } else {
                    p = p2;
                }
                if (p == null || i >= n) {
                    this.current = p;
                }
            } while (p.isData);
            this.current = p;
            if (p == null) {
                this.exhausted = true;
            }
            if (i <= 0) {
                return null;
            }
            this.batch = i;
            return Spliterators.spliterator(a, 0, i, 4368);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            if (action == null) {
                throw new NullPointerException();
            } else if (!this.exhausted) {
                Node node = this.current;
                Node p = node;
                if (node == null) {
                    Node firstDataNode = LinkedTransferQueue.this.firstDataNode();
                    p = firstDataNode;
                    if (firstDataNode == null) {
                        return;
                    }
                }
                this.exhausted = true;
                do {
                    Object e = (Object) p.item;
                    if (!(e == null || e == p)) {
                        action.accept(e);
                    }
                    Node p2 = p.next;
                    if (p == p2) {
                        p = LinkedTransferQueue.this.firstDataNode();
                    } else {
                        p = p2;
                    }
                    if (p == null) {
                        return;
                    }
                } while (p.isData);
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            Object e;
            if (action == null) {
                throw new NullPointerException();
            } else if (this.exhausted) {
                return false;
            } else {
                Node node = this.current;
                Node p = node;
                if (node == null) {
                    Node firstDataNode = LinkedTransferQueue.this.firstDataNode();
                    p = firstDataNode;
                    if (firstDataNode == null) {
                        return false;
                    }
                }
                do {
                    Object obj = p.item;
                    e = obj;
                    if (obj == p) {
                        e = null;
                    }
                    Node p2 = p.next;
                    if (p == p2) {
                        p = LinkedTransferQueue.this.firstDataNode();
                    } else {
                        p = p2;
                    }
                    if (e != null || p == null) {
                        this.current = p;
                    }
                } while (p.isData);
                this.current = p;
                if (p == null) {
                    this.exhausted = true;
                }
                if (e == null) {
                    return false;
                }
                action.accept(e);
                return true;
            }
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return Long.MAX_VALUE;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4368;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new LTQSpliterator();
    }

    /* access modifiers changed from: package-private */
    public final void unsplice(Node pred, Node s) {
        s.waiter = null;
        if (pred != null && pred != s && pred.next == s) {
            Node n = s.next;
            if (n == null || (n != s && pred.casNext(s, n) && pred.isMatched())) {
                while (true) {
                    Node h = this.head;
                    if (h != pred && h != s && h != null) {
                        if (h.isMatched()) {
                            Node hn = h.next;
                            if (hn != null) {
                                if (hn != h && casHead(h, hn)) {
                                    h.forgetNext();
                                }
                            } else {
                                return;
                            }
                        } else if (pred.next != pred && s.next != s) {
                            while (true) {
                                int v = this.sweepVotes;
                                if (v < 32) {
                                    if (casSweepVotes(v, v + 1)) {
                                        return;
                                    }
                                } else if (casSweepVotes(v, 0)) {
                                    sweep();
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void sweep() {
        Node p = this.head;
        while (p != null) {
            Node s = p.next;
            if (s == null) {
                return;
            }
            if (!s.isMatched()) {
                p = s;
            } else {
                Node n = s.next;
                if (n != null) {
                    if (s == n) {
                        p = this.head;
                    } else {
                        p.casNext(s, n);
                    }
                } else {
                    return;
                }
            }
        }
    }

    private boolean findAndRemove(Object e) {
        if (e == null) {
            return false;
        }
        Node pred = null;
        Node p = this.head;
        while (p != null) {
            Object item = p.item;
            if (p.isData) {
                if (item != null && item != p && e.equals(item) && p.tryMatchData()) {
                    unsplice(pred, p);
                    return true;
                }
            } else if (item == null) {
                return false;
            }
            pred = p;
            Node node = p.next;
            p = node;
            if (node == pred) {
                pred = null;
                p = this.head;
            }
        }
        return false;
    }

    public LinkedTransferQueue() {
    }

    public LinkedTransferQueue(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) {
        xfer(e, true, 1, 0);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) {
        xfer(e, true, 1, 0);
        return true;
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(E e) {
        xfer(e, true, 1, 0);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue, java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean add(E e) {
        xfer(e, true, 1, 0);
        return true;
    }

    @Override // java.util.concurrent.TransferQueue
    public boolean tryTransfer(E e) {
        return xfer(e, true, 0, 0) == null;
    }

    @Override // java.util.concurrent.TransferQueue
    public void transfer(E e) throws InterruptedException {
        if (xfer(e, true, 2, 0) != null) {
            Thread.interrupted();
            throw new InterruptedException();
        }
    }

    @Override // java.util.concurrent.TransferQueue
    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (xfer(e, true, 3, unit.toNanos(timeout)) == null) {
            return true;
        }
        if (!Thread.interrupted()) {
            return false;
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        E e = xfer(null, false, 2, 0);
        if (e != null) {
            return e;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E e = xfer(null, false, 3, unit.toNanos(timeout));
        if (e != null || !Thread.interrupted()) {
            return e;
        }
        throw new InterruptedException();
    }

    @Override // java.util.Queue
    public E poll() {
        return xfer(null, false, 0, 0);
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override // java.util.Queue
    public E peek() {
        while (true) {
            Node p = this.head;
            while (true) {
                if (p == null) {
                    return null;
                }
                E e = (E) p.item;
                if (p.isData) {
                    if (!(e == null || e == p)) {
                        return e;
                    }
                } else if (e == null) {
                    return null;
                }
                Node p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return firstDataNode() == null;
    }

    @Override // java.util.concurrent.TransferQueue
    public boolean hasWaitingConsumer() {
        while (true) {
            Node p = this.head;
            while (true) {
                if (p == null) {
                    return false;
                }
                Object item = p.item;
                if (p.isData) {
                    if (!(item == null || item == p)) {
                        return false;
                    }
                } else if (item == null) {
                    return true;
                }
                Node p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return countOfMode(true);
    }

    @Override // java.util.concurrent.TransferQueue
    public int getWaitingConsumerCount() {
        return countOfMode(false);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        return findAndRemove(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Node p = this.head;
        while (p != null) {
            Object item = p.item;
            if (p.isData) {
                if (!(item == null || item == p || !o.equals(item))) {
                    return true;
                }
            } else if (item == null) {
                return false;
            }
            p = succ(p);
        }
        return false;
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            s.writeObject(it.next());
        }
        s.writeObject(null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.concurrent.LinkedTransferQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        while (true) {
            Object readObject = s.readObject();
            if (readObject != null) {
                offer(readObject);
            } else {
                return;
            }
        }
    }
}
