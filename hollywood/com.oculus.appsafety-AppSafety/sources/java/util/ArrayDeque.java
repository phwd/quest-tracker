package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Consumer;

public class ArrayDeque<E> extends AbstractCollection<E> implements Deque<E>, Cloneable, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_INITIAL_CAPACITY = 8;
    private static final long serialVersionUID = 2340985798034038923L;
    transient Object[] elements;
    transient int head;
    transient int tail;

    private void allocateElements(int numElements) {
        int initialCapacity = 8;
        if (numElements >= 8) {
            int initialCapacity2 = numElements | (numElements >>> 1);
            int initialCapacity3 = initialCapacity2 | (initialCapacity2 >>> 2);
            int initialCapacity4 = initialCapacity3 | (initialCapacity3 >>> 4);
            int initialCapacity5 = initialCapacity4 | (initialCapacity4 >>> 8);
            initialCapacity = (initialCapacity5 | (initialCapacity5 >>> 16)) + 1;
            if (initialCapacity < 0) {
                initialCapacity >>>= 1;
            }
        }
        this.elements = new Object[initialCapacity];
    }

    private void doubleCapacity() {
        int p = this.head;
        Object[] objArr = this.elements;
        int n = objArr.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity >= 0) {
            Object[] a = new Object[newCapacity];
            System.arraycopy(objArr, p, a, 0, r);
            System.arraycopy(this.elements, 0, a, r, p);
            Arrays.fill(this.elements, (Object) null);
            this.elements = a;
            this.head = 0;
            this.tail = n;
            return;
        }
        throw new IllegalStateException("Sorry, deque too big");
    }

    public ArrayDeque() {
        this.elements = new Object[16];
    }

    public ArrayDeque(int numElements) {
        allocateElements(numElements);
    }

    public ArrayDeque(Collection<? extends E> c) {
        allocateElements(c.size());
        addAll(c);
    }

    @Override // java.util.Deque
    public void addFirst(E e) {
        if (e != null) {
            Object[] objArr = this.elements;
            int length = (this.head - 1) & (objArr.length - 1);
            this.head = length;
            objArr[length] = e;
            if (this.head == this.tail) {
                doubleCapacity();
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Deque
    public void addLast(E e) {
        if (e != null) {
            Object[] objArr = this.elements;
            int i = this.tail;
            objArr[i] = e;
            int length = (objArr.length - 1) & (i + 1);
            this.tail = length;
            if (length == this.head) {
                doubleCapacity();
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Deque
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override // java.util.Deque
    public boolean offerLast(E e) {
        addLast(e);
        return true;
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
        Object[] elements2 = this.elements;
        int h = this.head;
        E result = (E) elements2[h];
        if (result != null) {
            elements2[h] = null;
            this.head = (h + 1) & (elements2.length - 1);
        }
        return result;
    }

    @Override // java.util.Deque
    public E pollLast() {
        Object[] elements2 = this.elements;
        int t = (this.tail - 1) & (elements2.length - 1);
        E result = (E) elements2[t];
        if (result != null) {
            elements2[t] = null;
            this.tail = t;
        }
        return result;
    }

    @Override // java.util.Deque
    public E getFirst() {
        E result = (E) this.elements[this.head];
        if (result != null) {
            return result;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Deque
    public E getLast() {
        Object[] objArr = this.elements;
        E result = (E) objArr[(this.tail - 1) & (objArr.length - 1)];
        if (result != null) {
            return result;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Deque
    public E peekFirst() {
        return (E) this.elements[this.head];
    }

    @Override // java.util.Deque
    public E peekLast() {
        Object[] objArr = this.elements;
        return (E) objArr[(this.tail - 1) & (objArr.length - 1)];
    }

    @Override // java.util.Deque
    public boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        int mask = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object x = this.elements[i];
            if (x == null) {
                return false;
            }
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i + 1) & mask;
        }
    }

    @Override // java.util.Deque
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        int mask = this.elements.length - 1;
        int i = (this.tail - 1) & mask;
        while (true) {
            Object x = this.elements[i];
            if (x == null) {
                return false;
            }
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i - 1) & mask;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.Deque
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.Queue, java.util.Deque
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override // java.util.Queue, java.util.Deque
    public E remove() {
        return removeFirst();
    }

    @Override // java.util.Queue, java.util.Deque
    public E poll() {
        return pollFirst();
    }

    @Override // java.util.Queue, java.util.Deque
    public E element() {
        return getFirst();
    }

    @Override // java.util.Queue, java.util.Deque
    public E peek() {
        return peekFirst();
    }

    @Override // java.util.Deque
    public void push(E e) {
        addFirst(e);
    }

    @Override // java.util.Deque
    public E pop() {
        return removeFirst();
    }

    private void checkInvariants() {
    }

    /* access modifiers changed from: package-private */
    public boolean delete(int i) {
        checkInvariants();
        Object[] elements2 = this.elements;
        int mask = elements2.length - 1;
        int h = this.head;
        int t = this.tail;
        int front = (i - h) & mask;
        int back = (t - i) & mask;
        if (front >= ((t - h) & mask)) {
            throw new ConcurrentModificationException();
        } else if (front < back) {
            if (h <= i) {
                System.arraycopy(elements2, h, elements2, h + 1, front);
            } else {
                System.arraycopy(elements2, 0, elements2, 1, i);
                elements2[0] = elements2[mask];
                System.arraycopy(elements2, h, elements2, h + 1, mask - h);
            }
            elements2[h] = null;
            this.head = (h + 1) & mask;
            return false;
        } else {
            if (i < t) {
                System.arraycopy(elements2, i + 1, elements2, i, back);
                this.tail = t - 1;
            } else {
                System.arraycopy(elements2, i + 1, elements2, i, mask - i);
                elements2[mask] = elements2[0];
                System.arraycopy(elements2, 1, elements2, 0, t);
                this.tail = (t - 1) & mask;
            }
            return true;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public int size() {
        return (this.tail - this.head) & (this.elements.length - 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.lang.Iterable
    public Iterator<E> iterator() {
        return new DeqIterator();
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    private class DeqIterator implements Iterator<E> {
        private int cursor;
        private int fence;
        private int lastRet;

        private DeqIterator() {
            this.cursor = ArrayDeque.this.head;
            this.fence = ArrayDeque.this.tail;
            this.lastRet = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor != this.fence;
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.cursor != this.fence) {
                E result = (E) ArrayDeque.this.elements[this.cursor];
                if (ArrayDeque.this.tail != this.fence || result == null) {
                    throw new ConcurrentModificationException();
                }
                int i = this.cursor;
                this.lastRet = i;
                this.cursor = (i + 1) & (ArrayDeque.this.elements.length - 1);
                return result;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i >= 0) {
                if (ArrayDeque.this.delete(i)) {
                    this.cursor = (this.cursor - 1) & (ArrayDeque.this.elements.length - 1);
                    this.fence = ArrayDeque.this.tail;
                }
                this.lastRet = -1;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            Object[] a = ArrayDeque.this.elements;
            int m = a.length - 1;
            int f = this.fence;
            int i = this.cursor;
            this.cursor = f;
            while (i != f) {
                Object obj = a[i];
                i = (i + 1) & m;
                if (obj != null) {
                    action.accept(obj);
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    private class DescendingIterator implements Iterator<E> {
        private int cursor;
        private int fence;
        private int lastRet;

        private DescendingIterator() {
            this.cursor = ArrayDeque.this.tail;
            this.fence = ArrayDeque.this.head;
            this.lastRet = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor != this.fence;
        }

        @Override // java.util.Iterator
        public E next() {
            int i = this.cursor;
            if (i != this.fence) {
                this.cursor = (i - 1) & (ArrayDeque.this.elements.length - 1);
                E result = (E) ArrayDeque.this.elements[this.cursor];
                if (ArrayDeque.this.head != this.fence || result == null) {
                    throw new ConcurrentModificationException();
                }
                this.lastRet = this.cursor;
                return result;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i >= 0) {
                if (!ArrayDeque.this.delete(i)) {
                    this.cursor = (this.cursor + 1) & (ArrayDeque.this.elements.length - 1);
                    this.fence = ArrayDeque.this.head;
                }
                this.lastRet = -1;
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        int mask = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object x = this.elements[i];
            if (x == null) {
                return false;
            }
            if (o.equals(x)) {
                return true;
            }
            i = (i + 1) & mask;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        int h = this.head;
        int t = this.tail;
        if (h != t) {
            this.tail = 0;
            this.head = 0;
            int i = h;
            int mask = this.elements.length - 1;
            do {
                this.elements[i] = null;
                i = (i + 1) & mask;
            } while (i != t);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int head2 = this.head;
        int tail2 = this.tail;
        boolean wrap = tail2 < head2;
        Object[] a = Arrays.copyOfRange(this.elements, head2, wrap ? this.elements.length + tail2 : tail2);
        if (wrap) {
            Object[] objArr = this.elements;
            System.arraycopy(objArr, 0, a, objArr.length - head2, tail2);
        }
        return a;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        int head2 = this.head;
        int tail2 = this.tail;
        boolean wrap = tail2 < head2;
        int size = (tail2 - head2) + (wrap ? this.elements.length : 0);
        int firstLeg = size - (wrap ? tail2 : 0);
        int len = a.length;
        if (size > len) {
            a = (T[]) Arrays.copyOfRange(this.elements, head2, head2 + size, a.getClass());
        } else {
            System.arraycopy(this.elements, head2, a, 0, firstLeg);
            if (size < len) {
                a[size] = null;
            }
        }
        if (wrap) {
            System.arraycopy(this.elements, 0, a, firstLeg, tail2);
        }
        return a;
    }

    public ArrayDeque<E> clone() {
        try {
            ArrayDeque<E> result = (ArrayDeque) super.clone();
            result.elements = Arrays.copyOf(this.elements, this.elements.length);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size());
        int mask = this.elements.length - 1;
        for (int i = this.head; i != this.tail; i = (i + 1) & mask) {
            s.writeObject(this.elements[i]);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int size = s.readInt();
        allocateElements(size);
        this.head = 0;
        this.tail = size;
        for (int i = 0; i < size; i++) {
            this.elements[i] = s.readObject();
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new DeqSpliterator(this, -1, -1);
    }

    static final class DeqSpliterator<E> implements Spliterator<E> {
        private final ArrayDeque<E> deq;
        private int fence;
        private int index;

        DeqSpliterator(ArrayDeque<E> deq2, int origin, int fence2) {
            this.deq = deq2;
            this.index = origin;
            this.fence = fence2;
        }

        private int getFence() {
            int t = this.fence;
            if (t >= 0) {
                return t;
            }
            int t2 = this.deq.tail;
            this.fence = t2;
            this.index = this.deq.head;
            return t2;
        }

        @Override // java.util.Spliterator
        public DeqSpliterator<E> trySplit() {
            int t = getFence();
            int h = this.index;
            int n = this.deq.elements.length;
            if (h == t || ((h + 1) & (n - 1)) == t) {
                return null;
            }
            if (h > t) {
                t += n;
            }
            int m = ((h + t) >>> 1) & (n - 1);
            ArrayDeque<E> arrayDeque = this.deq;
            this.index = m;
            return new DeqSpliterator<>(arrayDeque, h, m);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> consumer) {
            if (consumer != null) {
                Object[] a = this.deq.elements;
                int m = a.length - 1;
                int f = getFence();
                int i = this.index;
                this.index = f;
                while (i != f) {
                    Object obj = a[i];
                    i = (i + 1) & m;
                    if (obj != null) {
                        consumer.accept(obj);
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> consumer) {
            if (consumer != null) {
                Object[] a = this.deq.elements;
                int m = a.length - 1;
                int f = getFence();
                int i = this.index;
                if (i == f) {
                    return false;
                }
                Object obj = a[i];
                this.index = (i + 1) & m;
                if (obj != null) {
                    consumer.accept(obj);
                    return true;
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            int n = getFence() - this.index;
            if (n < 0) {
                n += this.deq.elements.length;
            }
            return (long) n;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16720;
        }
    }
}
