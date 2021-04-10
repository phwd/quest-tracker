package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class LinkedBlockingDeque<E> extends AbstractQueue<E> implements BlockingDeque<E>, Serializable {
    private static final long serialVersionUID = -387911632671998426L;
    private final int capacity;
    private transient int count;
    transient Node<E> first;
    transient Node<E> last;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    /* access modifiers changed from: package-private */
    public static final class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E x) {
            this.item = x;
        }
    }

    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int capacity2) {
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        if (capacity2 > 0) {
            this.capacity = capacity2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public LinkedBlockingDeque(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            for (Object obj : c) {
                if (obj == null) {
                    throw new NullPointerException();
                } else if (!linkLast(new Node<>(obj))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            lock2.unlock();
        }
    }

    private boolean linkFirst(Node<E> node) {
        if (this.count >= this.capacity) {
            return false;
        }
        Node<E> f = this.first;
        node.next = f;
        this.first = node;
        if (this.last == null) {
            this.last = node;
        } else {
            f.prev = node;
        }
        this.count++;
        this.notEmpty.signal();
        return true;
    }

    private boolean linkLast(Node<E> node) {
        if (this.count >= this.capacity) {
            return false;
        }
        Node<E> l = this.last;
        node.prev = l;
        this.last = node;
        if (this.first == null) {
            this.first = node;
        } else {
            l.next = node;
        }
        this.count++;
        this.notEmpty.signal();
        return true;
    }

    private E unlinkFirst() {
        Node<E> f = this.first;
        if (f == null) {
            return null;
        }
        Node<E> n = f.next;
        E item = f.item;
        f.item = null;
        f.next = f;
        this.first = n;
        if (n == null) {
            this.last = null;
        } else {
            n.prev = null;
        }
        this.count--;
        this.notFull.signal();
        return item;
    }

    private E unlinkLast() {
        Node<E> l = this.last;
        if (l == null) {
            return null;
        }
        Node<E> p = l.prev;
        E item = l.item;
        l.item = null;
        l.prev = l;
        this.last = p;
        if (p == null) {
            this.first = null;
        } else {
            p.next = null;
        }
        this.count--;
        this.notFull.signal();
        return item;
    }

    /* access modifiers changed from: package-private */
    public void unlink(Node<E> x) {
        Node<E> p = x.prev;
        Node<E> n = x.next;
        if (p == null) {
            unlinkFirst();
        } else if (n == null) {
            unlinkLast();
        } else {
            p.next = n;
            n.prev = p;
            x.item = null;
            this.count--;
            this.notFull.signal();
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public boolean offerFirst(E e) {
        if (e != null) {
            Node<E> node = new Node<>(e);
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                return linkFirst(node);
            } finally {
                lock2.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public boolean offerLast(E e) {
        if (e != null) {
            Node<E> node = new Node<>(e);
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                return linkLast(node);
            } finally {
                lock2.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.util.concurrent.BlockingDeque
    public void putFirst(E e) throws InterruptedException {
        if (e != null) {
            Node<E> node = new Node<>(e);
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            while (!linkFirst(node)) {
                try {
                    this.notFull.await();
                } finally {
                    lock2.unlock();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingDeque
    public void putLast(E e) throws InterruptedException {
        if (e != null) {
            Node<E> node = new Node<>(e);
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            while (!linkLast(node)) {
                try {
                    this.notFull.await();
                } finally {
                    lock2.unlock();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingDeque
    public boolean offerFirst(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e != null) {
            Node<E> node = new Node<>(e);
            long nanos = unit.toNanos(timeout);
            ReentrantLock lock2 = this.lock;
            lock2.lockInterruptibly();
            while (!linkFirst(node)) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = this.notFull.awaitNanos(nanos);
                } finally {
                    lock2.unlock();
                }
            }
            lock2.unlock();
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingDeque
    public boolean offerLast(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e != null) {
            Node<E> node = new Node<>(e);
            long nanos = unit.toNanos(timeout);
            ReentrantLock lock2 = this.lock;
            lock2.lockInterruptibly();
            while (!linkLast(node)) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = this.notFull.awaitNanos(nanos);
                } finally {
                    lock2.unlock();
                }
            }
            lock2.unlock();
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Deque
    public E removeFirst() {
        E x = pollFirst();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Deque
    public E removeLast() {
        E x = pollLast();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Deque
    public E pollFirst() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return unlinkFirst();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.Deque
    public E pollLast() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return unlinkLast();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingDeque
    public E takeFirst() throws InterruptedException {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        while (true) {
            try {
                E x = unlinkFirst();
                if (x != null) {
                    return x;
                }
                this.notEmpty.await();
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.concurrent.BlockingDeque
    public E takeLast() throws InterruptedException {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        while (true) {
            try {
                E x = unlinkLast();
                if (x != null) {
                    return x;
                }
                this.notEmpty.await();
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.concurrent.BlockingDeque
    public E pollFirst(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (true) {
            try {
                E x = unlinkFirst();
                if (x != null) {
                    lock2.unlock();
                    return x;
                } else if (nanos <= 0) {
                    return null;
                } else {
                    nanos = this.notEmpty.awaitNanos(nanos);
                }
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.concurrent.BlockingDeque
    public E pollLast(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (true) {
            try {
                E x = unlinkLast();
                if (x != null) {
                    lock2.unlock();
                    return x;
                } else if (nanos <= 0) {
                    return null;
                } else {
                    nanos = this.notEmpty.awaitNanos(nanos);
                }
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.Deque
    public E getFirst() {
        E x = peekFirst();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Deque
    public E getLast() {
        E x = peekLast();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Deque
    public E peekFirst() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.first == null ? null : this.first.item;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.Deque
    public E peekLast() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.last == null ? null : this.last.item;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            for (Node<E> p = this.first; p != null; p = p.next) {
                if (o.equals(p.item)) {
                    unlink(p);
                    return true;
                }
            }
            lock2.unlock();
            return false;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            for (Node<E> p = this.last; p != null; p = p.prev) {
                if (o.equals(p.item)) {
                    unlink(p);
                    return true;
                }
            }
            lock2.unlock();
            return false;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.concurrent.BlockingDeque, java.util.Collection, java.util.AbstractQueue, java.util.concurrent.BlockingQueue, java.util.Queue, java.util.Deque
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Queue, java.util.Deque
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        putLast(e);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return offerLast(e, timeout, unit);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.AbstractQueue, java.util.Queue, java.util.Deque
    public E remove() {
        return removeFirst();
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Queue, java.util.Deque
    public E poll() {
        return pollFirst();
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        return takeFirst();
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return pollFirst(timeout, unit);
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.AbstractQueue, java.util.Queue, java.util.Deque
    public E element() {
        return getFirst();
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Queue, java.util.Deque
    public E peek() {
        return peekFirst();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.capacity - this.count;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c) {
        return drainTo(c, Integer.MAX_VALUE);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> c, int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c == this) {
            throw new IllegalArgumentException();
        } else if (maxElements <= 0) {
            return 0;
        } else {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                int n = Math.min(maxElements, this.count);
                for (int i = 0; i < n; i++) {
                    c.add(this.first.item);
                    unlinkFirst();
                }
                return n;
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.concurrent.BlockingDeque, java.util.Deque
    public void push(E e) {
        addFirst(e);
    }

    @Override // java.util.Deque
    public E pop() {
        return removeFirst();
    }

    @Override // java.util.AbstractCollection, java.util.concurrent.BlockingDeque, java.util.Collection, java.util.concurrent.BlockingQueue, java.util.Deque
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override // java.util.AbstractCollection, java.util.concurrent.BlockingDeque, java.util.Collection, java.util.Deque
    public int size() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.count;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.concurrent.BlockingDeque, java.util.Collection, java.util.concurrent.BlockingQueue, java.util.Deque
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            for (Node<E> p = this.first; p != null; p = p.next) {
                if (o.equals(p.item)) {
                    return true;
                }
            }
            lock2.unlock();
            return false;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            Object[] a = new Object[this.count];
            int k = 0;
            Node<E> p = this.first;
            while (p != null) {
                int k2 = k + 1;
                a[k] = p.item;
                p = p.next;
                k = k2;
            }
            return a;
        } finally {
            lock2.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.AbstractCollection, java.util.Collection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T[] toArray(T[] r6) {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.lock()
            int r1 = r6.length     // Catch:{ all -> 0x0037 }
            int r2 = r5.count     // Catch:{ all -> 0x0037 }
            if (r1 >= r2) goto L_0x001c
            java.lang.Class r1 = r6.getClass()     // Catch:{ all -> 0x0037 }
            java.lang.Class r1 = r1.getComponentType()     // Catch:{ all -> 0x0037 }
            int r2 = r5.count     // Catch:{ all -> 0x0037 }
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)     // Catch:{ all -> 0x0037 }
            java.lang.Object[] r1 = (java.lang.Object[]) r1     // Catch:{ all -> 0x0037 }
            r6 = r1
        L_0x001c:
            r1 = 0
            java.util.concurrent.LinkedBlockingDeque$Node<E> r2 = r5.first     // Catch:{ all -> 0x0037 }
        L_0x001f:
            if (r2 == 0) goto L_0x002c
            int r3 = r1 + 1
            E r4 = r2.item     // Catch:{ all -> 0x0037 }
            r6[r1] = r4     // Catch:{ all -> 0x0037 }
            java.util.concurrent.LinkedBlockingDeque$Node<E> r1 = r2.next     // Catch:{ all -> 0x0037 }
            r2 = r1
            r1 = r3
            goto L_0x001f
        L_0x002c:
            int r2 = r6.length     // Catch:{ all -> 0x0037 }
            if (r2 <= r1) goto L_0x0032
            r2 = 0
            r6[r1] = r2     // Catch:{ all -> 0x0037 }
        L_0x0032:
            r0.unlock()
            return r6
        L_0x0037:
            r1 = move-exception
            r0.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.LinkedBlockingDeque.toArray(java.lang.Object[]):java.lang.Object[]");
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return Helpers.collectionToString(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public void clear() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            Node<E> f = this.first;
            while (f != null) {
                f.item = null;
                Node<E> n = f.next;
                f.prev = null;
                f.next = null;
                f = n;
            }
            this.last = null;
            this.first = null;
            this.count = 0;
            this.notFull.signalAll();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.concurrent.BlockingDeque, java.util.Collection, java.util.Deque, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return new DescendingItr();
    }

    private abstract class AbstractItr implements Iterator<E> {
        private Node<E> lastRet;
        Node<E> next;
        E nextItem;

        /* access modifiers changed from: package-private */
        public abstract Node<E> firstNode();

        /* access modifiers changed from: package-private */
        public abstract Node<E> nextNode(Node<E> node);

        AbstractItr() {
            ReentrantLock lock = LinkedBlockingDeque.this.lock;
            lock.lock();
            try {
                this.next = firstNode();
                this.nextItem = this.next == null ? null : this.next.item;
            } finally {
                lock.unlock();
            }
        }

        private Node<E> succ(Node<E> n) {
            while (true) {
                Node<E> s = nextNode(n);
                if (s == null) {
                    return null;
                }
                if (s.item != null) {
                    return s;
                }
                if (s == n) {
                    return firstNode();
                }
                n = s;
            }
        }

        /* access modifiers changed from: package-private */
        public void advance() {
            ReentrantLock lock = LinkedBlockingDeque.this.lock;
            lock.lock();
            try {
                this.next = succ(this.next);
                this.nextItem = this.next == null ? null : this.next.item;
            } finally {
                lock.unlock();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public E next() {
            Node<E> node = this.next;
            if (node != null) {
                this.lastRet = node;
                E x = this.nextItem;
                advance();
                return x;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            Node<E> n = this.lastRet;
            if (n != null) {
                this.lastRet = null;
                ReentrantLock lock = LinkedBlockingDeque.this.lock;
                lock.lock();
                try {
                    if (n.item != null) {
                        LinkedBlockingDeque.this.unlink(n);
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private class Itr extends LinkedBlockingDeque<E>.AbstractItr {
        private Itr() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.LinkedBlockingDeque.AbstractItr
        public Node<E> firstNode() {
            return LinkedBlockingDeque.this.first;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.LinkedBlockingDeque.AbstractItr
        public Node<E> nextNode(Node<E> n) {
            return n.next;
        }
    }

    private class DescendingItr extends LinkedBlockingDeque<E>.AbstractItr {
        private DescendingItr() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.LinkedBlockingDeque.AbstractItr
        public Node<E> firstNode() {
            return LinkedBlockingDeque.this.last;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.LinkedBlockingDeque.AbstractItr
        public Node<E> nextNode(Node<E> n) {
            return n.prev;
        }
    }

    static final class LBDSpliterator<E> implements Spliterator<E> {
        static final int MAX_BATCH = 33554432;
        int batch;
        Node<E> current;
        long est;
        boolean exhausted;
        final LinkedBlockingDeque<E> queue;

        LBDSpliterator(LinkedBlockingDeque<E> queue2) {
            this.queue = queue2;
            this.est = (long) queue2.size();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
            if (r9 != null) goto L_0x0033;
         */
        @Override // java.util.Spliterator
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.Spliterator<E> trySplit() {
            /*
            // Method dump skipped, instructions count: 112
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.LinkedBlockingDeque.LBDSpliterator.trySplit():java.util.Spliterator");
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            if (action != null) {
                LinkedBlockingDeque<E> q = this.queue;
                ReentrantLock lock = q.lock;
                if (!this.exhausted) {
                    this.exhausted = true;
                    Node<E> p = this.current;
                    do {
                        E e = null;
                        lock.lock();
                        if (p == null) {
                            try {
                                p = q.first;
                            } catch (Throwable th) {
                                lock.unlock();
                                throw th;
                            }
                        }
                        while (p != null) {
                            e = p.item;
                            p = p.next;
                            if (e != null) {
                                break;
                            }
                        }
                        lock.unlock();
                        if (e != null) {
                            action.accept(e);
                            continue;
                        }
                    } while (p != null);
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: finally extract failed */
        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action != null) {
                LinkedBlockingDeque<E> q = this.queue;
                ReentrantLock lock = q.lock;
                if (this.exhausted) {
                    return false;
                }
                E e = null;
                lock.lock();
                try {
                    if (this.current == null) {
                        this.current = q.first;
                    }
                    while (this.current != null) {
                        e = this.current.item;
                        this.current = this.current.next;
                        if (e != null) {
                            break;
                        }
                    }
                    lock.unlock();
                    if (this.current == null) {
                        this.exhausted = true;
                    }
                    if (e == null) {
                        return false;
                    }
                    action.accept(e);
                    return true;
                } catch (Throwable th) {
                    lock.unlock();
                    throw th;
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4368;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new LBDSpliterator(this);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            s.defaultWriteObject();
            for (Node<E> p = this.first; p != null; p = p.next) {
                s.writeObject(p.item);
            }
            s.writeObject(null);
        } finally {
            lock2.unlock();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.concurrent.LinkedBlockingDeque<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.count = 0;
        this.first = null;
        this.last = null;
        while (true) {
            Object readObject = s.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}
