package java.util.concurrent;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    private static final long serialVersionUID = -817911632652898426L;
    int count;
    final Object[] items;
    transient ArrayBlockingQueue<E>.Itrs itrs;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    int putIndex;
    int takeIndex;

    /* access modifiers changed from: package-private */
    public final int dec(int i) {
        return (i == 0 ? this.items.length : i) - 1;
    }

    /* access modifiers changed from: package-private */
    public final E itemAt(int i) {
        return (E) this.items[i];
    }

    private void enqueue(E x) {
        Object[] items2 = this.items;
        int i = this.putIndex;
        items2[i] = x;
        int i2 = i + 1;
        this.putIndex = i2;
        if (i2 == items2.length) {
            this.putIndex = 0;
        }
        this.count++;
        this.notEmpty.signal();
    }

    private E dequeue() {
        Object[] items2 = this.items;
        int i = this.takeIndex;
        E x = (E) items2[i];
        items2[i] = null;
        int i2 = i + 1;
        this.takeIndex = i2;
        if (i2 == items2.length) {
            this.takeIndex = 0;
        }
        this.count--;
        ArrayBlockingQueue<E>.Itrs itrs2 = this.itrs;
        if (itrs2 != null) {
            itrs2.elementDequeued();
        }
        this.notFull.signal();
        return x;
    }

    /* access modifiers changed from: package-private */
    public void removeAt(int removeIndex) {
        Object[] items2 = this.items;
        int i = this.takeIndex;
        if (removeIndex == i) {
            items2[i] = null;
            int i2 = i + 1;
            this.takeIndex = i2;
            if (i2 == items2.length) {
                this.takeIndex = 0;
            }
            this.count--;
            ArrayBlockingQueue<E>.Itrs itrs2 = this.itrs;
            if (itrs2 != null) {
                itrs2.elementDequeued();
            }
        } else {
            int i3 = removeIndex;
            int putIndex2 = this.putIndex;
            while (true) {
                i3++;
                if (i3 == items2.length) {
                    i3 = 0;
                }
                if (i3 == putIndex2) {
                    break;
                }
                items2[i3] = items2[i3];
            }
            items2[i3] = null;
            this.putIndex = i3;
            this.count--;
            ArrayBlockingQueue<E>.Itrs itrs3 = this.itrs;
            if (itrs3 != null) {
                itrs3.removedAt(removeIndex);
            }
        }
        this.notFull.signal();
    }

    public ArrayBlockingQueue(int capacity) {
        this(capacity, false);
    }

    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity > 0) {
            this.items = new Object[capacity];
            this.lock = new ReentrantLock(fair);
            this.notEmpty = this.lock.newCondition();
            this.notFull = this.lock.newCondition();
            return;
        }
        throw new IllegalArgumentException();
    }

    public ArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c) {
        this(capacity, fair);
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        int i = 0;
        try {
            Iterator<? extends E> it = c.iterator();
            while (it.hasNext()) {
                int i2 = i + 1;
                try {
                    this.items[i] = Objects.requireNonNull(it.next());
                    i = i2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException();
                }
            }
            try {
                this.count = i;
                this.putIndex = i == capacity ? 0 : i;
            } finally {
                lock2.unlock();
            }
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue, java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean add(E e) {
        return super.add(e);
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(E e) {
        Objects.requireNonNull(e);
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            if (this.count == this.items.length) {
                return false;
            }
            enqueue(e);
            lock2.unlock();
            return true;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        Objects.requireNonNull(e);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (this.count == this.items.length) {
            try {
                this.notFull.await();
            } finally {
                lock2.unlock();
            }
        }
        enqueue(e);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        Objects.requireNonNull(e);
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (this.count == this.items.length) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.notFull.awaitNanos(nanos);
            } finally {
                lock2.unlock();
            }
        }
        enqueue(e);
        lock2.unlock();
        return true;
    }

    @Override // java.util.Queue
    public E poll() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.count == 0 ? null : dequeue();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (this.count == 0) {
            try {
                this.notEmpty.await();
            } finally {
                lock2.unlock();
            }
        }
        return dequeue();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (this.count == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            } finally {
                lock2.unlock();
            }
        }
        E dequeue = dequeue();
        lock2.unlock();
        return dequeue;
    }

    @Override // java.util.Queue
    public E peek() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return itemAt(this.takeIndex);
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.count;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.items.length - this.count;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            if (this.count > 0) {
                Object[] items2 = this.items;
                int putIndex2 = this.putIndex;
                int i = this.takeIndex;
                while (!o.equals(items2[i])) {
                    i++;
                    if (i == items2.length) {
                        i = 0;
                        continue;
                    }
                    if (i == putIndex2) {
                    }
                }
                removeAt(i);
                lock2.unlock();
                return true;
            }
            return false;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            if (this.count > 0) {
                Object[] items2 = this.items;
                int putIndex2 = this.putIndex;
                int i = this.takeIndex;
                while (!o.equals(items2[i])) {
                    i++;
                    if (i == items2.length) {
                        i = 0;
                        continue;
                    }
                    if (i == putIndex2) {
                    }
                }
                lock2.unlock();
                return true;
            }
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
            Object[] items2 = this.items;
            int end = this.takeIndex + this.count;
            Object[] a = Arrays.copyOfRange(items2, this.takeIndex, end);
            if (end != this.putIndex) {
                System.arraycopy(items2, 0, a, items2.length - this.takeIndex, this.putIndex);
            }
            return a;
        } finally {
            lock2.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Object[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.AbstractCollection, java.util.Collection
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T[] toArray(T[] r9) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.lock
            r0.lock()
            java.lang.Object[] r1 = r8.items     // Catch:{ all -> 0x003b }
            int r2 = r8.count     // Catch:{ all -> 0x003b }
            int r3 = r1.length     // Catch:{ all -> 0x003b }
            int r4 = r8.takeIndex     // Catch:{ all -> 0x003b }
            int r3 = r3 - r4
            int r3 = java.lang.Math.min(r3, r2)     // Catch:{ all -> 0x003b }
            int r4 = r9.length     // Catch:{ all -> 0x003b }
            r5 = 0
            if (r4 >= r2) goto L_0x0024
            int r4 = r8.takeIndex     // Catch:{ all -> 0x003b }
            int r6 = r8.takeIndex     // Catch:{ all -> 0x003b }
            int r6 = r6 + r2
            java.lang.Class r7 = r9.getClass()     // Catch:{ all -> 0x003b }
            java.lang.Object[] r4 = java.util.Arrays.copyOfRange(r1, r4, r6, r7)     // Catch:{ all -> 0x003b }
            r9 = r4
            goto L_0x002f
        L_0x0024:
            int r4 = r8.takeIndex     // Catch:{ all -> 0x003b }
            java.lang.System.arraycopy(r1, r4, r9, r5, r3)     // Catch:{ all -> 0x003b }
            int r4 = r9.length     // Catch:{ all -> 0x003b }
            if (r4 <= r2) goto L_0x002f
            r4 = 0
            r9[r2] = r4     // Catch:{ all -> 0x003b }
        L_0x002f:
            if (r3 >= r2) goto L_0x0036
            int r4 = r8.putIndex     // Catch:{ all -> 0x003b }
            java.lang.System.arraycopy(r1, r5, r9, r3, r4)     // Catch:{ all -> 0x003b }
        L_0x0036:
            r0.unlock()
            return r9
        L_0x003b:
            r1 = move-exception
            r0.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ArrayBlockingQueue.toArray(java.lang.Object[]):java.lang.Object[]");
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
            int k = this.count;
            if (k > 0) {
                Object[] items2 = this.items;
                int putIndex2 = this.putIndex;
                int i = this.takeIndex;
                do {
                    items2[i] = null;
                    i++;
                    if (i == items2.length) {
                        i = 0;
                        continue;
                    }
                } while (i != putIndex2);
                this.takeIndex = putIndex2;
                this.count = 0;
                if (this.itrs != null) {
                    this.itrs.queueIsEmpty();
                }
                while (k > 0 && lock2.hasWaiters(this.notFull)) {
                    this.notFull.signal();
                    k--;
                }
            }
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
        Objects.requireNonNull(c);
        if (c == this) {
            throw new IllegalArgumentException();
        } else if (maxElements <= 0) {
            return 0;
        } else {
            Object[] items2 = this.items;
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                int n = Math.min(maxElements, this.count);
                int take = this.takeIndex;
                int i = 0;
                while (i < n) {
                    try {
                        c.add(items2[take]);
                        items2[take] = null;
                        take++;
                        if (take == items2.length) {
                            take = 0;
                        }
                        i++;
                    } catch (Throwable th) {
                        if (i > 0) {
                            this.count -= i;
                            this.takeIndex = take;
                            if (this.itrs != null) {
                                if (this.count == 0) {
                                    this.itrs.queueIsEmpty();
                                } else if (i > take) {
                                    this.itrs.takeIndexWrapped();
                                }
                            }
                            while (i > 0 && lock2.hasWaiters(this.notFull)) {
                                this.notFull.signal();
                                i--;
                            }
                        }
                        throw th;
                    }
                }
                if (i > 0) {
                    this.count -= i;
                    this.takeIndex = take;
                    if (this.itrs != null) {
                        if (this.count == 0) {
                            this.itrs.queueIsEmpty();
                        } else if (i > take) {
                            this.itrs.takeIndexWrapped();
                        }
                    }
                    while (i > 0 && lock2.hasWaiters(this.notFull)) {
                        this.notFull.signal();
                        i--;
                    }
                }
                return n;
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    /* access modifiers changed from: package-private */
    public class Itrs {
        private static final int LONG_SWEEP_PROBES = 16;
        private static final int SHORT_SWEEP_PROBES = 4;
        int cycles;
        private ArrayBlockingQueue<E>.Itrs.Node head;
        private ArrayBlockingQueue<E>.Itrs.Node sweeper;

        /* access modifiers changed from: private */
        public class Node extends WeakReference<ArrayBlockingQueue<E>.Itr> {
            ArrayBlockingQueue<E>.Itrs.Node next;

            Node(ArrayBlockingQueue<E>.Itr iterator, ArrayBlockingQueue<E>.Itrs.Node next2) {
                super(iterator);
                this.next = next2;
            }
        }

        Itrs(ArrayBlockingQueue<E>.Itr initial) {
            register(initial);
        }

        /* access modifiers changed from: package-private */
        public void doSomeSweeping(boolean tryHarder) {
            boolean passedGo;
            ArrayBlockingQueue<E>.Itrs.Node p;
            ArrayBlockingQueue<E>.Itrs.Node o;
            ArrayBlockingQueue<E>.Itrs.Node node;
            int probes = tryHarder ? 16 : 4;
            ArrayBlockingQueue<E>.Itrs.Node sweeper2 = this.sweeper;
            if (sweeper2 == null) {
                o = null;
                p = this.head;
                passedGo = true;
            } else {
                o = sweeper2;
                p = o.next;
                passedGo = false;
            }
            while (true) {
                node = null;
                if (probes <= 0) {
                    break;
                }
                if (p == null) {
                    if (passedGo) {
                        break;
                    }
                    o = null;
                    p = this.head;
                    passedGo = true;
                }
                ArrayBlockingQueue<E>.Itr it = (Itr) p.get();
                ArrayBlockingQueue<E>.Itrs.Node next = p.next;
                if (it == null || it.isDetached()) {
                    probes = 16;
                    p.clear();
                    p.next = null;
                    if (o == null) {
                        this.head = next;
                        if (next == null) {
                            ArrayBlockingQueue.this.itrs = null;
                            return;
                        }
                    } else {
                        o.next = next;
                    }
                } else {
                    o = p;
                }
                p = next;
                probes--;
            }
            if (p != null) {
                node = o;
            }
            this.sweeper = node;
        }

        /* access modifiers changed from: package-private */
        public void register(ArrayBlockingQueue<E>.Itr itr) {
            this.head = new Node(itr, this.head);
        }

        /* access modifiers changed from: package-private */
        public void takeIndexWrapped() {
            this.cycles++;
            ArrayBlockingQueue<E>.Itrs.Node o = null;
            ArrayBlockingQueue<E>.Itrs.Node p = this.head;
            while (p != null) {
                ArrayBlockingQueue<E>.Itr it = (Itr) p.get();
                ArrayBlockingQueue<E>.Itrs.Node next = p.next;
                if (it == null || it.takeIndexWrapped()) {
                    p.clear();
                    p.next = null;
                    if (o == null) {
                        this.head = next;
                    } else {
                        o.next = next;
                    }
                } else {
                    o = p;
                }
                p = next;
            }
            if (this.head == null) {
                ArrayBlockingQueue.this.itrs = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void removedAt(int removedIndex) {
            ArrayBlockingQueue<E>.Itrs.Node o = null;
            ArrayBlockingQueue<E>.Itrs.Node p = this.head;
            while (p != null) {
                ArrayBlockingQueue<E>.Itr it = (Itr) p.get();
                ArrayBlockingQueue<E>.Itrs.Node next = p.next;
                if (it == null || it.removedAt(removedIndex)) {
                    p.clear();
                    p.next = null;
                    if (o == null) {
                        this.head = next;
                    } else {
                        o.next = next;
                    }
                } else {
                    o = p;
                }
                p = next;
            }
            if (this.head == null) {
                ArrayBlockingQueue.this.itrs = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void queueIsEmpty() {
            for (ArrayBlockingQueue<E>.Itrs.Node p = this.head; p != null; p = p.next) {
                ArrayBlockingQueue<E>.Itr it = (Itr) p.get();
                if (it != null) {
                    p.clear();
                    it.shutdown();
                }
            }
            this.head = null;
            ArrayBlockingQueue.this.itrs = null;
        }

        /* access modifiers changed from: package-private */
        public void elementDequeued() {
            if (ArrayBlockingQueue.this.count == 0) {
                queueIsEmpty();
            } else if (ArrayBlockingQueue.this.takeIndex == 0) {
                takeIndexWrapped();
            }
        }
    }

    /* access modifiers changed from: private */
    public class Itr implements Iterator<E> {
        private static final int DETACHED = -3;
        private static final int NONE = -1;
        private static final int REMOVED = -2;
        private int cursor;
        private E lastItem;
        private int lastRet = -1;
        private int nextIndex;
        private E nextItem;
        private int prevCycles;
        private int prevTakeIndex;

        Itr() {
            ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                if (ArrayBlockingQueue.this.count == 0) {
                    this.cursor = -1;
                    this.nextIndex = -1;
                    this.prevTakeIndex = -3;
                } else {
                    int takeIndex = ArrayBlockingQueue.this.takeIndex;
                    this.prevTakeIndex = takeIndex;
                    this.nextIndex = takeIndex;
                    this.nextItem = (E) ArrayBlockingQueue.this.itemAt(takeIndex);
                    this.cursor = incCursor(takeIndex);
                    if (ArrayBlockingQueue.this.itrs == null) {
                        ArrayBlockingQueue.this.itrs = new Itrs(this);
                    } else {
                        ArrayBlockingQueue.this.itrs.register(this);
                        ArrayBlockingQueue.this.itrs.doSomeSweeping(false);
                    }
                    this.prevCycles = ArrayBlockingQueue.this.itrs.cycles;
                }
            } finally {
                lock.unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isDetached() {
            return this.prevTakeIndex < 0;
        }

        private int incCursor(int index) {
            int index2 = index + 1;
            if (index2 == ArrayBlockingQueue.this.items.length) {
                index2 = 0;
            }
            if (index2 == ArrayBlockingQueue.this.putIndex) {
                return -1;
            }
            return index2;
        }

        private boolean invalidated(int index, int prevTakeIndex2, long dequeues, int length) {
            if (index < 0) {
                return false;
            }
            int distance = index - prevTakeIndex2;
            if (distance < 0) {
                distance += length;
            }
            if (dequeues > ((long) distance)) {
                return true;
            }
            return false;
        }

        private void incorporateDequeues() {
            int cycles = ArrayBlockingQueue.this.itrs.cycles;
            int takeIndex = ArrayBlockingQueue.this.takeIndex;
            int prevCycles2 = this.prevCycles;
            int prevTakeIndex2 = this.prevTakeIndex;
            if (cycles != prevCycles2 || takeIndex != prevTakeIndex2) {
                int len = ArrayBlockingQueue.this.items.length;
                long dequeues = (long) (((cycles - prevCycles2) * len) + (takeIndex - prevTakeIndex2));
                if (invalidated(this.lastRet, prevTakeIndex2, dequeues, len)) {
                    this.lastRet = -2;
                }
                if (invalidated(this.nextIndex, prevTakeIndex2, dequeues, len)) {
                    this.nextIndex = -2;
                }
                if (invalidated(this.cursor, prevTakeIndex2, dequeues, len)) {
                    this.cursor = takeIndex;
                }
                if (this.cursor >= 0 || this.nextIndex >= 0 || this.lastRet >= 0) {
                    this.prevCycles = cycles;
                    this.prevTakeIndex = takeIndex;
                    return;
                }
                detach();
            }
        }

        private void detach() {
            if (this.prevTakeIndex >= 0) {
                this.prevTakeIndex = -3;
                ArrayBlockingQueue.this.itrs.doSomeSweeping(true);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextItem != null) {
                return true;
            }
            noNext();
            return false;
        }

        private void noNext() {
            ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                if (!isDetached()) {
                    incorporateDequeues();
                    if (this.lastRet >= 0) {
                        this.lastItem = (E) ArrayBlockingQueue.this.itemAt(this.lastRet);
                        detach();
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        @Override // java.util.Iterator
        public E next() {
            E x = this.nextItem;
            if (x != null) {
                ReentrantLock lock = ArrayBlockingQueue.this.lock;
                lock.lock();
                try {
                    if (!isDetached()) {
                        incorporateDequeues();
                    }
                    this.lastRet = this.nextIndex;
                    int cursor2 = this.cursor;
                    if (cursor2 >= 0) {
                        ArrayBlockingQueue arrayBlockingQueue = ArrayBlockingQueue.this;
                        this.nextIndex = cursor2;
                        this.nextItem = (E) arrayBlockingQueue.itemAt(cursor2);
                        this.cursor = incCursor(cursor2);
                    } else {
                        this.nextIndex = -1;
                        this.nextItem = null;
                    }
                    return x;
                } finally {
                    lock.unlock();
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            ReentrantLock lock = ArrayBlockingQueue.this.lock;
            lock.lock();
            try {
                if (!isDetached()) {
                    incorporateDequeues();
                }
                int lastRet2 = this.lastRet;
                this.lastRet = -1;
                if (lastRet2 >= 0) {
                    if (!isDetached()) {
                        ArrayBlockingQueue.this.removeAt(lastRet2);
                    } else {
                        E lastItem2 = this.lastItem;
                        this.lastItem = null;
                        if (ArrayBlockingQueue.this.itemAt(lastRet2) == lastItem2) {
                            ArrayBlockingQueue.this.removeAt(lastRet2);
                        }
                    }
                } else if (lastRet2 == -1) {
                    throw new IllegalStateException();
                }
                if (this.cursor < 0 && this.nextIndex < 0) {
                    detach();
                }
            } finally {
                lock.unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            this.cursor = -1;
            if (this.nextIndex >= 0) {
                this.nextIndex = -2;
            }
            if (this.lastRet >= 0) {
                this.lastRet = -2;
                this.lastItem = null;
            }
            this.prevTakeIndex = -3;
        }

        private int distance(int index, int prevTakeIndex2, int length) {
            int distance = index - prevTakeIndex2;
            if (distance < 0) {
                return distance + length;
            }
            return distance;
        }

        /* JADX INFO: Multiple debug info for r7v0 int: [D('lastRet' int), D('x' int)] */
        /* JADX INFO: Multiple debug info for r9v0 int: [D('nextIndex' int), D('x' int)] */
        /* access modifiers changed from: package-private */
        public boolean removedAt(int removedIndex) {
            if (isDetached()) {
                return true;
            }
            int takeIndex = ArrayBlockingQueue.this.takeIndex;
            int prevTakeIndex2 = this.prevTakeIndex;
            int len = ArrayBlockingQueue.this.items.length;
            int removedDistance = (((ArrayBlockingQueue.this.itrs.cycles - this.prevCycles) + (removedIndex < takeIndex ? 1 : 0)) * len) + (removedIndex - prevTakeIndex2);
            int cursor2 = this.cursor;
            if (cursor2 >= 0) {
                int x = distance(cursor2, prevTakeIndex2, len);
                if (x == removedDistance) {
                    if (cursor2 == ArrayBlockingQueue.this.putIndex) {
                        cursor2 = -1;
                        this.cursor = -1;
                    }
                } else if (x > removedDistance) {
                    int dec = ArrayBlockingQueue.this.dec(cursor2);
                    cursor2 = dec;
                    this.cursor = dec;
                }
            }
            int lastRet2 = this.lastRet;
            if (lastRet2 >= 0) {
                int x2 = distance(lastRet2, prevTakeIndex2, len);
                if (x2 == removedDistance) {
                    lastRet2 = -2;
                    this.lastRet = -2;
                } else if (x2 > removedDistance) {
                    int dec2 = ArrayBlockingQueue.this.dec(lastRet2);
                    lastRet2 = dec2;
                    this.lastRet = dec2;
                }
            }
            int nextIndex2 = this.nextIndex;
            if (nextIndex2 >= 0) {
                int x3 = distance(nextIndex2, prevTakeIndex2, len);
                if (x3 == removedDistance) {
                    nextIndex2 = -2;
                    this.nextIndex = -2;
                } else if (x3 > removedDistance) {
                    int dec3 = ArrayBlockingQueue.this.dec(nextIndex2);
                    nextIndex2 = dec3;
                    this.nextIndex = dec3;
                }
            }
            if (cursor2 >= 0 || nextIndex2 >= 0 || lastRet2 >= 0) {
                return false;
            }
            this.prevTakeIndex = -3;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean takeIndexWrapped() {
            if (isDetached()) {
                return true;
            }
            if (ArrayBlockingQueue.this.itrs.cycles - this.prevCycles <= 1) {
                return false;
            }
            shutdown();
            return true;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 4368);
    }
}
