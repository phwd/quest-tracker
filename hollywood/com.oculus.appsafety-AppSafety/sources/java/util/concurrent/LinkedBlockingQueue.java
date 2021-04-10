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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class LinkedBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    private static final long serialVersionUID = -6903933977591709194L;
    private final int capacity;
    private final AtomicInteger count;
    transient Node<E> head;
    private transient Node<E> last;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ReentrantLock putLock;
    private final ReentrantLock takeLock;

    /* access modifiers changed from: package-private */
    public static class Node<E> {
        E item;
        Node<E> next;

        Node(E x) {
            this.item = x;
        }
    }

    private void signalNotEmpty() {
        ReentrantLock takeLock2 = this.takeLock;
        takeLock2.lock();
        try {
            this.notEmpty.signal();
        } finally {
            takeLock2.unlock();
        }
    }

    private void signalNotFull() {
        ReentrantLock putLock2 = this.putLock;
        putLock2.lock();
        try {
            this.notFull.signal();
        } finally {
            putLock2.unlock();
        }
    }

    private void enqueue(Node<E> node) {
        this.last.next = node;
        this.last = node;
    }

    private E dequeue() {
        Node<E> h = this.head;
        Node<E> first = h.next;
        h.next = h;
        this.head = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    /* access modifiers changed from: package-private */
    public void fullyLock() {
        this.putLock.lock();
        this.takeLock.lock();
    }

    /* access modifiers changed from: package-private */
    public void fullyUnlock() {
        this.takeLock.unlock();
        this.putLock.unlock();
    }

    public LinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingQueue(int capacity2) {
        this.count = new AtomicInteger();
        this.takeLock = new ReentrantLock();
        this.notEmpty = this.takeLock.newCondition();
        this.putLock = new ReentrantLock();
        this.notFull = this.putLock.newCondition();
        if (capacity2 > 0) {
            this.capacity = capacity2;
            Node<E> node = new Node<>(null);
            this.head = node;
            this.last = node;
            return;
        }
        throw new IllegalArgumentException();
    }

    public LinkedBlockingQueue(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        ReentrantLock putLock2 = this.putLock;
        putLock2.lock();
        int n = 0;
        try {
            for (Object obj : c) {
                if (obj == null) {
                    throw new NullPointerException();
                } else if (n != this.capacity) {
                    enqueue(new Node<>(obj));
                    n++;
                } else {
                    throw new IllegalStateException("Queue full");
                }
            }
            this.count.set(n);
        } finally {
            putLock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return this.capacity - this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        if (e != null) {
            Node<E> node = new Node<>(e);
            ReentrantLock putLock2 = this.putLock;
            AtomicInteger count2 = this.count;
            putLock2.lockInterruptibly();
            while (count2.get() == this.capacity) {
                try {
                    this.notFull.await();
                } finally {
                    putLock2.unlock();
                }
            }
            enqueue(node);
            int c = count2.getAndIncrement();
            if (c + 1 < this.capacity) {
                this.notFull.signal();
            }
            if (c == 0) {
                signalNotEmpty();
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e != null) {
            long nanos = unit.toNanos(timeout);
            ReentrantLock putLock2 = this.putLock;
            AtomicInteger count2 = this.count;
            putLock2.lockInterruptibly();
            while (count2.get() == this.capacity) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = this.notFull.awaitNanos(nanos);
                } finally {
                    putLock2.unlock();
                }
            }
            enqueue(new Node<>(e));
            int c = count2.getAndIncrement();
            if (c + 1 < this.capacity) {
                this.notFull.signal();
            }
            putLock2.unlock();
            if (c != 0) {
                return true;
            }
            signalNotEmpty();
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(E e) {
        if (e != null) {
            AtomicInteger count2 = this.count;
            if (count2.get() == this.capacity) {
                return false;
            }
            int c = -1;
            Node<E> node = new Node<>(e);
            ReentrantLock putLock2 = this.putLock;
            putLock2.lock();
            try {
                if (count2.get() < this.capacity) {
                    enqueue(node);
                    c = count2.getAndIncrement();
                    if (c + 1 < this.capacity) {
                        this.notFull.signal();
                    }
                }
                if (c == 0) {
                    signalNotEmpty();
                }
                if (c >= 0) {
                    return true;
                }
                return false;
            } finally {
                putLock2.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        AtomicInteger count2 = this.count;
        ReentrantLock takeLock2 = this.takeLock;
        takeLock2.lockInterruptibly();
        while (count2.get() == 0) {
            try {
                this.notEmpty.await();
            } catch (Throwable x) {
                takeLock2.unlock();
                throw x;
            }
        }
        E x2 = dequeue();
        int c = count2.getAndDecrement();
        if (c > 1) {
            this.notEmpty.signal();
        }
        takeLock2.unlock();
        if (c == this.capacity) {
            signalNotFull();
        }
        return x2;
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        AtomicInteger count2 = this.count;
        ReentrantLock takeLock2 = this.takeLock;
        takeLock2.lockInterruptibly();
        while (count2.get() == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            } finally {
                takeLock2.unlock();
            }
        }
        E x = dequeue();
        int c = count2.getAndDecrement();
        if (c > 1) {
            this.notEmpty.signal();
        }
        takeLock2.unlock();
        if (c == this.capacity) {
            signalNotFull();
        }
        return x;
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.Queue
    public E poll() {
        AtomicInteger count2 = this.count;
        if (count2.get() == 0) {
            return null;
        }
        E x = null;
        int c = -1;
        ReentrantLock takeLock2 = this.takeLock;
        takeLock2.lock();
        try {
            if (count2.get() > 0) {
                x = dequeue();
                c = count2.getAndDecrement();
                if (c > 1) {
                    this.notEmpty.signal();
                }
            }
            takeLock2.unlock();
            if (c == this.capacity) {
                signalNotFull();
            }
            return x;
        } catch (Throwable th) {
            takeLock2.unlock();
            throw th;
        }
    }

    @Override // java.util.Queue
    public E peek() {
        E e = null;
        if (this.count.get() == 0) {
            return null;
        }
        ReentrantLock takeLock2 = this.takeLock;
        takeLock2.lock();
        try {
            if (this.count.get() > 0) {
                e = this.head.next.item;
            }
            return e;
        } finally {
            takeLock2.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void unlink(Node<E> p, Node<E> trail) {
        p.item = null;
        trail.next = p.next;
        if (this.last == p) {
            this.last = trail;
        }
        if (this.count.getAndDecrement() == this.capacity) {
            this.notFull.signal();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        fullyLock();
        try {
            Node<E> trail = this.head;
            for (Node<E> p = trail.next; p != null; p = p.next) {
                if (o.equals(p.item)) {
                    unlink(p, trail);
                    return true;
                }
                trail = p;
            }
            fullyUnlock();
            return false;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        fullyLock();
        try {
            for (Node<E> p = this.head.next; p != null; p = p.next) {
                if (o.equals(p.item)) {
                    return true;
                }
            }
            fullyUnlock();
            return false;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        fullyLock();
        try {
            Object[] a = new Object[this.count.get()];
            int k = 0;
            Node<E> p = this.head.next;
            while (p != null) {
                int k2 = k + 1;
                a[k] = p.item;
                p = p.next;
                k = k2;
            }
            return a;
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.AbstractCollection, java.util.Collection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T[] toArray(T[] r6) {
        /*
            r5 = this;
            r5.fullyLock()
            java.util.concurrent.atomic.AtomicInteger r0 = r5.count     // Catch:{ all -> 0x0039 }
            int r0 = r0.get()     // Catch:{ all -> 0x0039 }
            int r1 = r6.length     // Catch:{ all -> 0x0039 }
            if (r1 >= r0) goto L_0x001c
            java.lang.Class r1 = r6.getClass()     // Catch:{ all -> 0x0039 }
            java.lang.Class r1 = r1.getComponentType()     // Catch:{ all -> 0x0039 }
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r0)     // Catch:{ all -> 0x0039 }
            java.lang.Object[] r1 = (java.lang.Object[]) r1     // Catch:{ all -> 0x0039 }
            r6 = r1
        L_0x001c:
            r1 = 0
            java.util.concurrent.LinkedBlockingQueue$Node<E> r2 = r5.head     // Catch:{ all -> 0x0039 }
            java.util.concurrent.LinkedBlockingQueue$Node<E> r2 = r2.next     // Catch:{ all -> 0x0039 }
        L_0x0021:
            if (r2 == 0) goto L_0x002e
            int r3 = r1 + 1
            E r4 = r2.item     // Catch:{ all -> 0x0039 }
            r6[r1] = r4     // Catch:{ all -> 0x0039 }
            java.util.concurrent.LinkedBlockingQueue$Node<E> r1 = r2.next     // Catch:{ all -> 0x0039 }
            r2 = r1
            r1 = r3
            goto L_0x0021
        L_0x002e:
            int r2 = r6.length     // Catch:{ all -> 0x0039 }
            if (r2 <= r1) goto L_0x0034
            r2 = 0
            r6[r1] = r2     // Catch:{ all -> 0x0039 }
        L_0x0034:
            r5.fullyUnlock()
            return r6
        L_0x0039:
            r0 = move-exception
            r5.fullyUnlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.LinkedBlockingQueue.toArray(java.lang.Object[]):java.lang.Object[]");
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return Helpers.collectionToString(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public void clear() {
        fullyLock();
        try {
            Node<E> h = this.head;
            while (true) {
                Node<E> p = h.next;
                if (p == null) {
                    break;
                }
                h.next = h;
                p.item = null;
                h = p;
            }
            this.head = this.last;
            if (this.count.getAndSet(0) == this.capacity) {
                this.notFull.signal();
            }
        } finally {
            fullyUnlock();
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
        } else if (c != this) {
            boolean z = false;
            if (maxElements <= 0) {
                return 0;
            }
            boolean signalNotFull = false;
            ReentrantLock takeLock2 = this.takeLock;
            takeLock2.lock();
            try {
                int n = Math.min(maxElements, this.count.get());
                Node<E> h = this.head;
                int i = 0;
                while (i < n) {
                    try {
                        Node<E> p = h.next;
                        c.add(p.item);
                        p.item = null;
                        h.next = h;
                        h = p;
                        i++;
                    } catch (Throwable th) {
                        if (i > 0) {
                            this.head = h;
                            if (this.count.getAndAdd(-i) == this.capacity) {
                                z = true;
                            }
                        }
                        throw th;
                    }
                }
                if (i > 0) {
                    this.head = h;
                    if (this.count.getAndAdd(-i) == this.capacity) {
                        z = true;
                    }
                    signalNotFull = z;
                }
                return n;
            } finally {
                takeLock2.unlock();
                if (signalNotFull) {
                    signalNotFull();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private Node<E> current;
        private E currentElement;
        private Node<E> lastRet;

        Itr() {
            LinkedBlockingQueue.this.fullyLock();
            try {
                this.current = LinkedBlockingQueue.this.head.next;
                if (this.current != null) {
                    this.currentElement = this.current.item;
                }
            } finally {
                LinkedBlockingQueue.this.fullyUnlock();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current != null;
        }

        @Override // java.util.Iterator
        public E next() {
            Node<E> q;
            LinkedBlockingQueue.this.fullyLock();
            try {
                if (this.current != null) {
                    this.lastRet = this.current;
                    E item = null;
                    Node<E> p = this.current;
                    while (true) {
                        Node<E> node = p.next;
                        q = node;
                        if (node == p) {
                            q = LinkedBlockingQueue.this.head.next;
                        }
                        if (q == null) {
                            break;
                        }
                        E e = q.item;
                        item = e;
                        if (e != null) {
                            break;
                        }
                        p = q;
                    }
                    this.current = q;
                    E x = this.currentElement;
                    this.currentElement = item;
                    return x;
                }
                throw new NoSuchElementException();
            } finally {
                LinkedBlockingQueue.this.fullyUnlock();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastRet != null) {
                LinkedBlockingQueue.this.fullyLock();
                try {
                    Node<E> node = this.lastRet;
                    this.lastRet = null;
                    Node<E> trail = LinkedBlockingQueue.this.head;
                    Node<E> p = trail.next;
                    while (true) {
                        if (p == null) {
                            break;
                        } else if (p == node) {
                            LinkedBlockingQueue.this.unlink(p, trail);
                            break;
                        } else {
                            trail = p;
                            p = p.next;
                        }
                    }
                } finally {
                    LinkedBlockingQueue.this.fullyUnlock();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    static final class LBQSpliterator<E> implements Spliterator<E> {
        static final int MAX_BATCH = 33554432;
        int batch;
        Node<E> current;
        long est;
        boolean exhausted;
        final LinkedBlockingQueue<E> queue;

        LBQSpliterator(LinkedBlockingQueue<E> queue2) {
            this.queue = queue2;
            this.est = (long) queue2.size();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
            if (r8 != null) goto L_0x0035;
         */
        @Override // java.util.Spliterator
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.Spliterator<E> trySplit() {
            /*
            // Method dump skipped, instructions count: 114
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.LinkedBlockingQueue.LBQSpliterator.trySplit():java.util.Spliterator");
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            if (action != null) {
                LinkedBlockingQueue<E> q = this.queue;
                if (!this.exhausted) {
                    this.exhausted = true;
                    Node<E> p = this.current;
                    do {
                        E e = null;
                        q.fullyLock();
                        if (p == null) {
                            try {
                                p = q.head.next;
                            } catch (Throwable th) {
                                q.fullyUnlock();
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
                        q.fullyUnlock();
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
                LinkedBlockingQueue<E> q = this.queue;
                if (this.exhausted) {
                    return false;
                }
                E e = null;
                q.fullyLock();
                try {
                    if (this.current == null) {
                        this.current = q.head.next;
                    }
                    while (this.current != null) {
                        e = this.current.item;
                        this.current = this.current.next;
                        if (e != null) {
                            break;
                        }
                    }
                    q.fullyUnlock();
                    if (this.current == null) {
                        this.exhausted = true;
                    }
                    if (e == null) {
                        return false;
                    }
                    action.accept(e);
                    return true;
                } catch (Throwable th) {
                    q.fullyUnlock();
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
        return new LBQSpliterator(this);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        fullyLock();
        try {
            s.defaultWriteObject();
            for (Node<E> p = this.head.next; p != null; p = p.next) {
                s.writeObject(p.item);
            }
            s.writeObject(null);
        } finally {
            fullyUnlock();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.LinkedBlockingQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.count.set(0);
        Node<E> node = new Node<>(null);
        this.head = node;
        this.last = node;
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
