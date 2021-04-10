package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import sun.misc.Unsafe;

public class PriorityBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    private static final long ALLOCATIONSPINLOCK;
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = 5595510919245408276L;
    private volatile transient int allocationSpinLock;
    private transient Comparator<? super E> comparator;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private PriorityQueue<E> q;
    private transient Object[] queue;
    private transient int size;

    public PriorityBlockingQueue() {
        this(11, null);
    }

    public PriorityBlockingQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public PriorityBlockingQueue(int initialCapacity, Comparator<? super E> comparator2) {
        if (initialCapacity >= 1) {
            this.lock = new ReentrantLock();
            this.notEmpty = this.lock.newCondition();
            this.comparator = comparator2;
            this.queue = new Object[initialCapacity];
            return;
        }
        throw new IllegalArgumentException();
    }

    public PriorityBlockingQueue(Collection<? extends E> c) {
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        boolean heapify = true;
        boolean screen = true;
        if (c instanceof SortedSet) {
            this.comparator = ((SortedSet) c).comparator();
            heapify = false;
        } else if (c instanceof PriorityBlockingQueue) {
            PriorityBlockingQueue priorityBlockingQueue = (PriorityBlockingQueue) c;
            this.comparator = priorityBlockingQueue.comparator();
            screen = false;
            if (priorityBlockingQueue.getClass() == PriorityBlockingQueue.class) {
                heapify = false;
            }
        }
        Object[] a = c.toArray();
        int n = a.length;
        a = a.getClass() != Object[].class ? Arrays.copyOf(a, n, Object[].class) : a;
        if (screen && (n == 1 || this.comparator != null)) {
            for (int i = 0; i < n; i++) {
                if (a[i] == null) {
                    throw new NullPointerException();
                }
            }
        }
        this.queue = a;
        this.size = n;
        if (heapify) {
            heapify();
        }
    }

    private void tryGrow(Object[] array, int oldCap) {
        int i;
        this.lock.unlock();
        Object[] newArray = null;
        if (this.allocationSpinLock == 0 && U.compareAndSwapInt(this, ALLOCATIONSPINLOCK, 0, 1)) {
            if (oldCap < 64) {
                i = oldCap + 2;
            } else {
                i = oldCap >> 1;
            }
            int newCap = i + oldCap;
            if (newCap - MAX_ARRAY_SIZE > 0) {
                int minCap = oldCap + 1;
                if (minCap < 0 || minCap > MAX_ARRAY_SIZE) {
                    try {
                        throw new OutOfMemoryError();
                    } catch (Throwable th) {
                        this.allocationSpinLock = 0;
                        throw th;
                    }
                } else {
                    newCap = MAX_ARRAY_SIZE;
                }
            }
            if (newCap > oldCap && this.queue == array) {
                newArray = new Object[newCap];
            }
            this.allocationSpinLock = 0;
        }
        if (newArray == null) {
            Thread.yield();
        }
        this.lock.lock();
        if (newArray != null && this.queue == array) {
            this.queue = newArray;
            System.arraycopy(array, 0, newArray, 0, oldCap);
        }
    }

    private E dequeue() {
        int n = this.size - 1;
        if (n < 0) {
            return null;
        }
        Object[] array = this.queue;
        E result = (E) array[0];
        Object obj = array[n];
        array[n] = null;
        Comparator<? super E> cmp = this.comparator;
        if (cmp == null) {
            siftDownComparable(0, obj, array, n);
        } else {
            siftDownUsingComparator(0, obj, array, n, cmp);
        }
        this.size = n;
        return result;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void siftUpComparable(int k, T x, Object[] array) {
        T key = x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = array[parent];
            if (key.compareTo(e) >= 0) {
                break;
            }
            array[k] = e;
            k = parent;
        }
        array[k] = key;
    }

    private static <T> void siftUpUsingComparator(int k, T x, Object[] array, Comparator<? super T> cmp) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = array[parent];
            if (cmp.compare(x, e) >= 0) {
                break;
            }
            array[k] = e;
            k = parent;
        }
        array[k] = x;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Comparable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> void siftDownComparable(int r7, T r8, java.lang.Object[] r9, int r10) {
        /*
            if (r10 <= 0) goto L_0x002e
            r0 = r8
            java.lang.Comparable r0 = (java.lang.Comparable) r0
            int r1 = r10 >>> 1
        L_0x0007:
            if (r7 >= r1) goto L_0x002c
            int r2 = r7 << 1
            int r2 = r2 + 1
            r3 = r9[r2]
            int r4 = r2 + 1
            if (r4 >= r10) goto L_0x0021
            r5 = r3
            java.lang.Comparable r5 = (java.lang.Comparable) r5
            r6 = r9[r4]
            int r5 = r5.compareTo(r6)
            if (r5 <= 0) goto L_0x0021
            r2 = r4
            r3 = r9[r4]
        L_0x0021:
            int r5 = r0.compareTo(r3)
            if (r5 > 0) goto L_0x0028
            goto L_0x002c
        L_0x0028:
            r9[r7] = r3
            r7 = r2
            goto L_0x0007
        L_0x002c:
            r9[r7] = r0
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.PriorityBlockingQueue.siftDownComparable(int, java.lang.Object, java.lang.Object[], int):void");
    }

    private static <T> void siftDownUsingComparator(int k, T x, Object[] array, int n, Comparator<? super T> cmp) {
        if (n > 0) {
            int half = n >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                Object c = array[child];
                int right = child + 1;
                if (right < n && cmp.compare(c, array[right]) > 0) {
                    child = right;
                    c = array[right];
                }
                if (cmp.compare(x, c) <= 0) {
                    break;
                }
                array[k] = c;
                k = child;
            }
            array[k] = x;
        }
    }

    private void heapify() {
        Object[] array = this.queue;
        int n = this.size;
        int half = (n >>> 1) - 1;
        Comparator<? super E> cmp = this.comparator;
        if (cmp == null) {
            for (int i = half; i >= 0; i--) {
                siftDownComparable(i, array[i], array, n);
            }
            return;
        }
        for (int i2 = half; i2 >= 0; i2--) {
            siftDownUsingComparator(i2, array[i2], array, n, cmp);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue, java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean add(E e) {
        return offer(e);
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(E e) {
        int n;
        Object[] array;
        if (e != null) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            while (true) {
                n = this.size;
                array = this.queue;
                int cap = array.length;
                if (n >= cap) {
                    tryGrow(array, cap);
                } else {
                    try {
                        break;
                    } catch (Throwable th) {
                        lock2.unlock();
                        throw th;
                    }
                }
            }
            Comparator<? super E> cmp = this.comparator;
            if (cmp == null) {
                siftUpComparable(n, e, array);
            } else {
                siftUpUsingComparator(n, e, array, cmp);
            }
            this.size = n + 1;
            this.notEmpty.signal();
            lock2.unlock();
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) {
        offer(e);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long timeout, TimeUnit unit) {
        return offer(e);
    }

    @Override // java.util.Queue
    public E poll() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return dequeue();
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (true) {
            try {
                E result = dequeue();
                if (result != null) {
                    return result;
                }
                this.notEmpty.await();
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E result;
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock2 = this.lock;
        lock2.lockInterruptibly();
        while (true) {
            try {
                result = dequeue();
                if (result == null && nanos > ALLOCATIONSPINLOCK) {
                    nanos = this.notEmpty.awaitNanos(nanos);
                }
            } finally {
                lock2.unlock();
            }
        }
        return result;
    }

    @Override // java.util.Queue
    public E peek() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.size == 0 ? null : (E) this.queue[0];
        } finally {
            lock2.unlock();
        }
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return this.size;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    private int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        Object[] array = this.queue;
        int n = this.size;
        for (int i = 0; i < n; i++) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    private void removeAt(int i) {
        Object[] array = this.queue;
        int n = this.size - 1;
        if (n == i) {
            array[i] = null;
        } else {
            Object obj = array[n];
            array[n] = null;
            Comparator<? super E> cmp = this.comparator;
            if (cmp == null) {
                siftDownComparable(i, obj, array, n);
            } else {
                siftDownUsingComparator(i, obj, array, n, cmp);
            }
            if (array[i] == obj) {
                if (cmp == null) {
                    siftUpComparable(i, obj, array);
                } else {
                    siftUpUsingComparator(i, obj, array, cmp);
                }
            }
        }
        this.size = n;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object o) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            int i = indexOf(o);
            if (i == -1) {
                return false;
            }
            removeAt(i);
            lock2.unlock();
            return true;
        } finally {
            lock2.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeEQ(Object o) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            Object[] array = this.queue;
            int i = 0;
            int n = this.size;
            while (true) {
                if (i >= n) {
                    break;
                } else if (o == array[i]) {
                    removeAt(i);
                    break;
                } else {
                    i++;
                }
            }
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object o) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return indexOf(o) != -1;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return Helpers.collectionToString(this);
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
                int n = Math.min(this.size, maxElements);
                for (int i = 0; i < n; i++) {
                    c.add(this.queue[0]);
                    dequeue();
                }
                return n;
            } finally {
                lock2.unlock();
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public void clear() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            Object[] array = this.queue;
            int n = this.size;
            this.size = 0;
            for (int i = 0; i < n; i++) {
                array[i] = null;
            }
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            return Arrays.copyOf(this.queue, this.size);
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        ReentrantLock lock2 = this.lock;
        lock2.lock();
        try {
            int n = this.size;
            if (a.length < n) {
                return (T[]) Arrays.copyOf(this.queue, this.size, a.getClass());
            }
            System.arraycopy(this.queue, 0, a, 0, n);
            if (a.length > n) {
                a[n] = null;
            }
            lock2.unlock();
            return a;
        } finally {
            lock2.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr(toArray());
    }

    final class Itr implements Iterator<E> {
        final Object[] array;
        int cursor;
        int lastRet = -1;

        Itr(Object[] array2) {
            this.array = array2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor < this.array.length;
        }

        @Override // java.util.Iterator
        public E next() {
            int i = this.cursor;
            Object[] objArr = this.array;
            if (i < objArr.length) {
                this.lastRet = i;
                this.cursor = i + 1;
                return (E) objArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i >= 0) {
                PriorityBlockingQueue.this.removeEQ(this.array[i]);
                this.lastRet = -1;
                return;
            }
            throw new IllegalStateException();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        this.lock.lock();
        try {
            this.q = new PriorityQueue<>(Math.max(this.size, 1), this.comparator);
            this.q.addAll(this);
            s.defaultWriteObject();
        } finally {
            this.q = null;
            this.lock.unlock();
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        try {
            s.defaultReadObject();
            this.queue = new Object[this.q.size()];
            this.comparator = this.q.comparator();
            addAll(this.q);
        } finally {
            this.q = null;
        }
    }

    static final class PBQSpliterator<E> implements Spliterator<E> {
        Object[] array;
        int fence;
        int index;
        final PriorityBlockingQueue<E> queue;

        PBQSpliterator(PriorityBlockingQueue<E> queue2, Object[] array2, int index2, int fence2) {
            this.queue = queue2;
            this.array = array2;
            this.index = index2;
            this.fence = fence2;
        }

        /* access modifiers changed from: package-private */
        public final int getFence() {
            int hi = this.fence;
            if (hi >= 0) {
                return hi;
            }
            Object[] array2 = this.queue.toArray();
            this.array = array2;
            int hi2 = array2.length;
            this.fence = hi2;
            return hi2;
        }

        @Override // java.util.Spliterator
        public PBQSpliterator<E> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            PriorityBlockingQueue<E> priorityBlockingQueue = this.queue;
            Object[] objArr = this.array;
            this.index = mid;
            return new PBQSpliterator<>(priorityBlockingQueue, objArr, lo, mid);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            if (action != null) {
                Object[] objArr = this.array;
                Object[] a = objArr;
                if (objArr == null) {
                    Object[] array2 = this.queue.toArray();
                    a = array2;
                    this.fence = array2.length;
                }
                int hi = this.fence;
                if (hi <= a.length) {
                    int i = this.index;
                    int i2 = i;
                    if (i >= 0) {
                        this.index = hi;
                        if (i2 < hi) {
                            do {
                                action.accept(a[i2]);
                                i2++;
                            } while (i2 < hi);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action != null) {
                int fence2 = getFence();
                int i = this.index;
                if (fence2 <= i || i < 0) {
                    return false;
                }
                Object[] objArr = this.array;
                this.index = i + 1;
                action.accept(objArr[i]);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return (long) (getFence() - this.index);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16704;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new PBQSpliterator(this, null, 0, -1);
    }

    static {
        try {
            ALLOCATIONSPINLOCK = U.objectFieldOffset(PriorityBlockingQueue.class.getDeclaredField("allocationSpinLock"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
