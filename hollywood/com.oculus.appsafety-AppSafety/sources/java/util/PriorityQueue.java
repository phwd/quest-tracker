package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Consumer;

public class PriorityQueue<E> extends AbstractQueue<E> implements Serializable {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private static final long serialVersionUID = -7720805057305804111L;
    private final Comparator<? super E> comparator;
    transient int modCount;
    transient Object[] queue;
    int size;

    public PriorityQueue() {
        this(11, null);
    }

    public PriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public PriorityQueue(Comparator<? super E> comparator2) {
        this(11, comparator2);
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator2) {
        if (initialCapacity >= 1) {
            this.queue = new Object[initialCapacity];
            this.comparator = comparator2;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.PriorityQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    public PriorityQueue(Collection<? extends E> c) {
        if (c instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) c;
            this.comparator = sortedSet.comparator();
            initElementsFromCollection(sortedSet);
        } else if (c instanceof PriorityQueue) {
            PriorityQueue priorityQueue = (PriorityQueue) c;
            this.comparator = priorityQueue.comparator();
            initFromPriorityQueue(priorityQueue);
        } else {
            this.comparator = null;
            initFromCollection(c);
        }
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.util.Comparator<? super ? extends E>, java.util.Comparator<? super E> */
    public PriorityQueue(PriorityQueue<? extends E> c) {
        this.comparator = (Comparator<? super Object>) c.comparator();
        initFromPriorityQueue(c);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.util.Comparator<? super ? extends E>, java.util.Comparator<? super E> */
    public PriorityQueue(SortedSet<? extends E> c) {
        this.comparator = (Comparator<? super Object>) c.comparator();
        initElementsFromCollection(c);
    }

    private void initFromPriorityQueue(PriorityQueue<? extends E> c) {
        if (c.getClass() == PriorityQueue.class) {
            this.queue = c.toArray();
            this.size = c.size();
            return;
        }
        initFromCollection(c);
    }

    private void initElementsFromCollection(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if (a.getClass() != Object[].class) {
            a = Arrays.copyOf(a, a.length, Object[].class);
        }
        if (a.length == 1 || this.comparator != null) {
            for (Object e : a) {
                if (e == null) {
                    throw new NullPointerException();
                }
            }
        }
        this.queue = a;
        this.size = a.length;
    }

    private void initFromCollection(Collection<? extends E> c) {
        initElementsFromCollection(c);
        heapify();
    }

    private void grow(int minCapacity) {
        int i;
        int oldCapacity = this.queue.length;
        if (oldCapacity < 64) {
            i = oldCapacity + 2;
        } else {
            i = oldCapacity >> 1;
        }
        int newCapacity = i + oldCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        this.queue = Arrays.copyOf(this.queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        } else if (minCapacity > MAX_ARRAY_SIZE) {
            return Integer.MAX_VALUE;
        } else {
            return MAX_ARRAY_SIZE;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue, java.util.Queue
    public boolean add(E e) {
        return offer(e);
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        if (e != null) {
            this.modCount++;
            int i = this.size;
            if (i >= this.queue.length) {
                grow(i + 1);
            }
            this.size = i + 1;
            if (i == 0) {
                this.queue[0] = e;
            } else {
                siftUp(i, e);
            }
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Queue
    public E peek() {
        if (this.size == 0) {
            return null;
        }
        return (E) this.queue[0];
    }

    private int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        for (int i = 0; i < this.size; i++) {
            if (o.equals(this.queue[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1) {
            return false;
        }
        removeAt(i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean removeEq(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (o == this.queue[i]) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return Arrays.copyOf(this.queue, this.size);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        int size2 = this.size;
        if (a.length < size2) {
            return (T[]) Arrays.copyOf(this.queue, size2, a.getClass());
        }
        System.arraycopy(this.queue, 0, a, 0, size2);
        if (a.length > size2) {
            a[size2] = null;
        }
        return a;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    private final class Itr implements Iterator<E> {
        private int cursor;
        private int expectedModCount;
        private ArrayDeque<E> forgetMeNot;
        private int lastRet;
        private E lastRetElt;

        private Itr() {
            this.lastRet = -1;
            this.expectedModCount = PriorityQueue.this.modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            ArrayDeque<E> arrayDeque;
            return this.cursor < PriorityQueue.this.size || ((arrayDeque = this.forgetMeNot) != null && !arrayDeque.isEmpty());
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.expectedModCount != PriorityQueue.this.modCount) {
                throw new ConcurrentModificationException();
            } else if (this.cursor < PriorityQueue.this.size) {
                Object[] objArr = PriorityQueue.this.queue;
                int i = this.cursor;
                this.cursor = i + 1;
                this.lastRet = i;
                return (E) objArr[i];
            } else {
                ArrayDeque<E> arrayDeque = this.forgetMeNot;
                if (arrayDeque != null) {
                    this.lastRet = -1;
                    this.lastRetElt = arrayDeque.poll();
                    E e = this.lastRetElt;
                    if (e != null) {
                        return e;
                    }
                }
                throw new NoSuchElementException();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: java.util.ArrayDeque<E> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        public void remove() {
            if (this.expectedModCount == PriorityQueue.this.modCount) {
                int i = this.lastRet;
                if (i != -1) {
                    Object removeAt = PriorityQueue.this.removeAt(i);
                    this.lastRet = -1;
                    if (removeAt == null) {
                        this.cursor--;
                    } else {
                        if (this.forgetMeNot == null) {
                            this.forgetMeNot = new ArrayDeque<>();
                        }
                        this.forgetMeNot.add(removeAt);
                    }
                } else {
                    E e = this.lastRetElt;
                    if (e != null) {
                        PriorityQueue.this.removeEq(e);
                        this.lastRetElt = null;
                    } else {
                        throw new IllegalStateException();
                    }
                }
                this.expectedModCount = PriorityQueue.this.modCount;
                return;
            }
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public void clear() {
        this.modCount++;
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.PriorityQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue
    public E poll() {
        int i = this.size;
        if (i == 0) {
            return null;
        }
        int s = i - 1;
        this.size = s;
        this.modCount++;
        Object[] objArr = this.queue;
        E result = (E) objArr[0];
        Object obj = objArr[s];
        objArr[s] = null;
        if (s != 0) {
            siftDown(0, obj);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public E removeAt(int i) {
        this.modCount++;
        int s = this.size - 1;
        this.size = s;
        if (s == i) {
            this.queue[i] = null;
        } else {
            Object[] objArr = this.queue;
            E moved = (E) objArr[s];
            objArr[s] = null;
            siftDown(i, moved);
            if (this.queue[i] == moved) {
                siftUp(i, moved);
                if (this.queue[i] != moved) {
                    return moved;
                }
            }
        }
        return null;
    }

    private void siftUp(int k, E x) {
        if (this.comparator != null) {
            siftUpUsingComparator(k, x);
        } else {
            siftUpComparable(k, x);
        }
    }

    private void siftUpComparable(int k, E x) {
        E key = x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = this.queue[parent];
            if (key.compareTo(e) >= 0) {
                break;
            }
            this.queue[k] = e;
            k = parent;
        }
        this.queue[k] = key;
    }

    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = this.queue[parent];
            if (this.comparator.compare(x, e) >= 0) {
                break;
            }
            this.queue[k] = e;
            k = parent;
        }
        this.queue[k] = x;
    }

    private void siftDown(int k, E x) {
        if (this.comparator != null) {
            siftDownUsingComparator(k, x);
        } else {
            siftDownComparable(k, x);
        }
    }

    private void siftDownComparable(int k, E x) {
        E key = x;
        int half = this.size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object[] objArr = this.queue;
            Object c = objArr[child];
            int right = child + 1;
            if (right < this.size && ((Comparable) c).compareTo(objArr[right]) > 0) {
                child = right;
                c = this.queue[right];
            }
            if (key.compareTo(c) <= 0) {
                break;
            }
            this.queue[k] = c;
            k = child;
        }
        this.queue[k] = key;
    }

    private void siftDownUsingComparator(int k, E x) {
        int half = this.size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object[] objArr = this.queue;
            Object c = objArr[child];
            int right = child + 1;
            if (right < this.size && this.comparator.compare(c, objArr[right]) > 0) {
                child = right;
                c = this.queue[right];
            }
            if (this.comparator.compare(x, c) <= 0) {
                break;
            }
            this.queue[k] = c;
            k = child;
        }
        this.queue[k] = x;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.PriorityQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void heapify() {
        for (int i = (this.size >>> 1) - 1; i >= 0; i--) {
            siftDown(i, this.queue[i]);
        }
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(Math.max(2, this.size + 1));
        for (int i = 0; i < this.size; i++) {
            s.writeObject(this.queue[i]);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        s.readInt();
        this.queue = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = s.readObject();
        }
        heapify();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Spliterator<E> spliterator() {
        return new PriorityQueueSpliterator(this, 0, -1, 0);
    }

    static final class PriorityQueueSpliterator<E> implements Spliterator<E> {
        private int expectedModCount;
        private int fence;
        private int index;
        private final PriorityQueue<E> pq;

        PriorityQueueSpliterator(PriorityQueue<E> pq2, int origin, int fence2, int expectedModCount2) {
            this.pq = pq2;
            this.index = origin;
            this.fence = fence2;
            this.expectedModCount = expectedModCount2;
        }

        private int getFence() {
            int hi = this.fence;
            if (hi >= 0) {
                return hi;
            }
            this.expectedModCount = this.pq.modCount;
            int hi2 = this.pq.size;
            this.fence = hi2;
            return hi2;
        }

        @Override // java.util.Spliterator
        public PriorityQueueSpliterator<E> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            PriorityQueue<E> priorityQueue = this.pq;
            this.index = mid;
            return new PriorityQueueSpliterator<>(priorityQueue, lo, mid, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            Object[] a;
            int mc;
            if (action != null) {
                PriorityQueue<E> q = this.pq;
                if (q != null && (a = q.queue) != null) {
                    int i = this.fence;
                    int hi = i;
                    if (i < 0) {
                        mc = q.modCount;
                        hi = q.size;
                    } else {
                        mc = this.expectedModCount;
                    }
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (hi <= a.length) {
                            while (true) {
                                if (i3 < hi) {
                                    Object obj = a[i3];
                                    if (obj == null) {
                                        break;
                                    }
                                    action.accept(obj);
                                    i3++;
                                } else if (q.modCount == mc) {
                                    return;
                                }
                            }
                        }
                    }
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action != null) {
                int hi = getFence();
                int lo = this.index;
                if (lo < 0 || lo >= hi) {
                    return false;
                }
                this.index = lo + 1;
                Object obj = this.pq.queue[lo];
                if (obj != null) {
                    action.accept(obj);
                    if (this.pq.modCount == this.expectedModCount) {
                        return true;
                    }
                    throw new ConcurrentModificationException();
                }
                throw new ConcurrentModificationException();
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
}
