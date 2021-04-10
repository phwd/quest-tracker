package java.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private static final long serialVersionUID = -2767605614048989439L;
    protected int capacityIncrement;
    protected int elementCount;
    protected Object[] elementData;

    public Vector(int initialCapacity, int capacityIncrement2) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity];
            this.capacityIncrement = capacityIncrement2;
            return;
        }
        throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    public Vector(int initialCapacity) {
        this(initialCapacity, 0);
    }

    public Vector() {
        this(10);
    }

    public Vector(Collection<? extends E> c) {
        this.elementData = c.toArray();
        Object[] objArr = this.elementData;
        this.elementCount = objArr.length;
        if (objArr.getClass() != Object[].class) {
            this.elementData = Arrays.copyOf(this.elementData, this.elementCount, Object[].class);
        }
    }

    public synchronized void copyInto(Object[] anArray) {
        System.arraycopy(this.elementData, 0, anArray, 0, this.elementCount);
    }

    public synchronized void trimToSize() {
        this.modCount++;
        if (this.elementCount < this.elementData.length) {
            this.elementData = Arrays.copyOf(this.elementData, this.elementCount);
        }
    }

    public synchronized void ensureCapacity(int minCapacity) {
        if (minCapacity > 0) {
            this.modCount++;
            ensureCapacityHelper(minCapacity);
        }
    }

    private void ensureCapacityHelper(int minCapacity) {
        if (minCapacity - this.elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int i = this.capacityIncrement;
        if (i <= 0) {
            i = oldCapacity;
        }
        int newCapacity = i + oldCapacity;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        this.elementData = Arrays.copyOf(this.elementData, newCapacity);
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

    public synchronized void setSize(int newSize) {
        this.modCount++;
        if (newSize > this.elementCount) {
            ensureCapacityHelper(newSize);
        } else {
            for (int i = newSize; i < this.elementCount; i++) {
                this.elementData[i] = null;
            }
        }
        this.elementCount = newSize;
    }

    public synchronized int capacity() {
        return this.elementData.length;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized int size() {
        return this.elementCount;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    public Enumeration<E> elements() {
        return new Enumeration<E>() {
            /* class java.util.Vector.AnonymousClass1 */
            int count = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.count < Vector.this.elementCount;
            }

            @Override // java.util.Enumeration
            public E nextElement() {
                synchronized (Vector.this) {
                    if (this.count < Vector.this.elementCount) {
                        Vector vector = Vector.this;
                        int i = this.count;
                        this.count = i + 1;
                        return (E) vector.elementData(i);
                    }
                    throw new NoSuchElementException("Vector Enumeration");
                }
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return indexOf(o, 0) >= 0;
    }

    @Override // java.util.List, java.util.AbstractList
    public int indexOf(Object o) {
        return indexOf(o, 0);
    }

    public synchronized int indexOf(Object o, int index) {
        if (o == null) {
            for (int i = index; i < this.elementCount; i++) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i2 = index; i2 < this.elementCount; i2++) {
                if (o.equals(this.elementData[i2])) {
                    return i2;
                }
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized int lastIndexOf(Object o) {
        return lastIndexOf(o, this.elementCount - 1);
    }

    public synchronized int lastIndexOf(Object o, int index) {
        if (index < this.elementCount) {
            if (o == null) {
                for (int i = index; i >= 0; i--) {
                    if (this.elementData[i] == null) {
                        return i;
                    }
                }
            } else {
                for (int i2 = index; i2 >= 0; i2--) {
                    if (o.equals(this.elementData[i2])) {
                        return i2;
                    }
                }
            }
            return -1;
        }
        throw new IndexOutOfBoundsException(index + " >= " + this.elementCount);
    }

    public synchronized E elementAt(int index) {
        if (index < this.elementCount) {
        } else {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + this.elementCount);
        }
        return elementData(index);
    }

    public synchronized E firstElement() {
        if (this.elementCount != 0) {
        } else {
            throw new NoSuchElementException();
        }
        return elementData(0);
    }

    public synchronized E lastElement() {
        if (this.elementCount != 0) {
        } else {
            throw new NoSuchElementException();
        }
        return elementData(this.elementCount - 1);
    }

    public synchronized void setElementAt(E obj, int index) {
        if (index < this.elementCount) {
            this.elementData[index] = obj;
        } else {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + this.elementCount);
        }
    }

    public synchronized void removeElementAt(int index) {
        this.modCount++;
        if (index >= this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + this.elementCount);
        } else if (index >= 0) {
            int j = (this.elementCount - index) - 1;
            if (j > 0) {
                System.arraycopy(this.elementData, index + 1, this.elementData, index, j);
            }
            this.elementCount--;
            this.elementData[this.elementCount] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    public synchronized void insertElementAt(E obj, int index) {
        this.modCount++;
        if (index <= this.elementCount) {
            ensureCapacityHelper(this.elementCount + 1);
            System.arraycopy(this.elementData, index, this.elementData, index + 1, this.elementCount - index);
            this.elementData[index] = obj;
            this.elementCount++;
        } else {
            throw new ArrayIndexOutOfBoundsException(index + " > " + this.elementCount);
        }
    }

    public synchronized void addElement(E obj) {
        this.modCount++;
        ensureCapacityHelper(this.elementCount + 1);
        Object[] objArr = this.elementData;
        int i = this.elementCount;
        this.elementCount = i + 1;
        objArr[i] = obj;
    }

    public synchronized boolean removeElement(Object obj) {
        this.modCount++;
        int i = indexOf(obj);
        if (i < 0) {
            return false;
        }
        removeElementAt(i);
        return true;
    }

    public synchronized void removeAllElements() {
        this.modCount++;
        for (int i = 0; i < this.elementCount; i++) {
            this.elementData[i] = null;
        }
        this.elementCount = 0;
    }

    public synchronized Object clone() {
        Vector<E> v;
        try {
            v = (Vector) super.clone();
            v.elementData = Arrays.copyOf(this.elementData, this.elementCount);
            v.modCount = 0;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
        return v;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.elementCount);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized <T> T[] toArray(T[] a) {
        if (a.length < this.elementCount) {
            return (T[]) Arrays.copyOf(this.elementData, this.elementCount, a.getClass());
        }
        System.arraycopy(this.elementData, 0, a, 0, this.elementCount);
        if (a.length > this.elementCount) {
            a[this.elementCount] = null;
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    public E elementData(int index) {
        return (E) this.elementData[index];
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized E get(int index) {
        if (index < this.elementCount) {
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return elementData(index);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized E set(int index, E element) {
        E oldValue;
        if (index < this.elementCount) {
            oldValue = elementData(index);
            this.elementData[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return oldValue;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public synchronized boolean add(E e) {
        this.modCount++;
        ensureCapacityHelper(this.elementCount + 1);
        Object[] objArr = this.elementData;
        int i = this.elementCount;
        this.elementCount = i + 1;
        objArr[i] = e;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean remove(Object o) {
        return removeElement(o);
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int index, E element) {
        insertElementAt(element, index);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized E remove(int index) {
        E oldValue;
        this.modCount++;
        if (index < this.elementCount) {
            oldValue = elementData(index);
            int numMoved = (this.elementCount - index) - 1;
            if (numMoved > 0) {
                System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
            }
            Object[] objArr = this.elementData;
            int i = this.elementCount - 1;
            this.elementCount = i;
            objArr[i] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return oldValue;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public void clear() {
        removeAllElements();
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean addAll(Collection<? extends E> c) {
        boolean z;
        z = true;
        this.modCount++;
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityHelper(this.elementCount + numNew);
        System.arraycopy(a, 0, this.elementData, this.elementCount, numNew);
        this.elementCount += numNew;
        if (numNew == 0) {
            z = false;
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized boolean addAll(int index, Collection<? extends E> c) {
        boolean z;
        z = true;
        this.modCount++;
        if (index < 0 || index > this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityHelper(this.elementCount + numNew);
        int numMoved = this.elementCount - index;
        if (numMoved > 0) {
            System.arraycopy(this.elementData, index, this.elementData, index + numNew, numMoved);
        }
        System.arraycopy(a, 0, this.elementData, index, numNew);
        this.elementCount += numNew;
        if (numNew == 0) {
            z = false;
        }
        return z;
    }

    @Override // java.util.List, java.util.Collection, java.util.AbstractList
    public synchronized boolean equals(Object o) {
        return super.equals(o);
    }

    @Override // java.util.List, java.util.Collection, java.util.AbstractList
    public synchronized int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.AbstractCollection
    public synchronized String toString() {
        return super.toString();
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized List<E> subList(int fromIndex, int toIndex) {
        return Collections.synchronizedList(super.subList(fromIndex, toIndex), this);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.AbstractList
    public synchronized void removeRange(int fromIndex, int toIndex) {
        this.modCount++;
        System.arraycopy(this.elementData, toIndex, this.elementData, fromIndex, this.elementCount - toIndex);
        int newElementCount = this.elementCount - (toIndex - fromIndex);
        while (this.elementCount != newElementCount) {
            Object[] objArr = this.elementData;
            int i = this.elementCount - 1;
            this.elementCount = i;
            objArr[i] = null;
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        Object[] data;
        ObjectOutputStream.PutField fields = s.putFields();
        synchronized (this) {
            fields.put("capacityIncrement", this.capacityIncrement);
            fields.put("elementCount", this.elementCount);
            data = (Object[]) this.elementData.clone();
        }
        fields.put("elementData", data);
        s.writeFields();
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized ListIterator<E> listIterator(int index) {
        if (index >= 0) {
            if (index <= this.elementCount) {
            }
        }
        throw new IndexOutOfBoundsException("Index: " + index);
        return new ListItr(index);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public synchronized Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int expectedModCount;
        int lastRet;
        protected int limit;

        private Itr() {
            this.limit = Vector.this.elementCount;
            this.lastRet = -1;
            this.expectedModCount = Vector.this.modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor < this.limit;
        }

        @Override // java.util.Iterator
        public E next() {
            E e;
            synchronized (Vector.this) {
                checkForComodification();
                int i = this.cursor;
                if (i < this.limit) {
                    this.cursor = i + 1;
                    Vector vector = Vector.this;
                    this.lastRet = i;
                    e = (E) vector.elementData(i);
                } else {
                    throw new NoSuchElementException();
                }
            }
            return e;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastRet != -1) {
                synchronized (Vector.this) {
                    checkForComodification();
                    Vector.this.remove(this.lastRet);
                    this.expectedModCount = Vector.this.modCount;
                    this.limit--;
                }
                this.cursor = this.lastRet;
                this.lastRet = -1;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            synchronized (Vector.this) {
                int size = this.limit;
                int i = this.cursor;
                if (i < size) {
                    Object[] objArr = Vector.this.elementData;
                    if (i < objArr.length) {
                        while (i != size && Vector.this.modCount == this.expectedModCount) {
                            action.accept(objArr[i]);
                            i++;
                        }
                        this.cursor = i;
                        this.lastRet = i - 1;
                        checkForComodification();
                        return;
                    }
                    throw new ConcurrentModificationException();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void checkForComodification() {
            if (Vector.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    final class ListItr extends Vector<E>.Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            this.cursor = index;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.cursor;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override // java.util.ListIterator
        public E previous() {
            E e;
            synchronized (Vector.this) {
                checkForComodification();
                int i = this.cursor - 1;
                if (i >= 0) {
                    this.cursor = i;
                    Vector vector = Vector.this;
                    this.lastRet = i;
                    e = (E) vector.elementData(i);
                } else {
                    throw new NoSuchElementException();
                }
            }
            return e;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            if (this.lastRet != -1) {
                synchronized (Vector.this) {
                    checkForComodification();
                    Vector.this.set(this.lastRet, e);
                }
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            int i = this.cursor;
            synchronized (Vector.this) {
                checkForComodification();
                Vector.this.add(i, e);
                this.expectedModCount = Vector.this.modCount;
                this.limit++;
            }
            this.cursor = i + 1;
            this.lastRet = -1;
        }
    }

    @Override // java.lang.Iterable
    public synchronized void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        int expectedModCount = this.modCount;
        Object[] objArr = this.elementData;
        int elementCount2 = this.elementCount;
        int i = 0;
        while (this.modCount == expectedModCount && i < elementCount2) {
            action.accept(objArr[i]);
            i++;
        }
        if (this.modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection
    public synchronized boolean removeIf(Predicate<? super E> filter) {
        boolean anyToRemove;
        Objects.requireNonNull(filter);
        int removeCount = 0;
        int size = this.elementCount;
        BitSet removeSet = new BitSet(size);
        int expectedModCount = this.modCount;
        anyToRemove = false;
        int i = 0;
        while (this.modCount == expectedModCount && i < size) {
            if (filter.test(this.elementData[i])) {
                removeSet.set(i);
                removeCount++;
            }
            i++;
        }
        if (this.modCount == expectedModCount) {
            if (removeCount > 0) {
                anyToRemove = true;
            }
            if (anyToRemove) {
                int newSize = size - removeCount;
                int i2 = 0;
                int j = 0;
                while (i2 < size && j < newSize) {
                    int i3 = removeSet.nextClearBit(i2);
                    this.elementData[j] = this.elementData[i3];
                    i2 = i3 + 1;
                    j++;
                }
                for (int k = newSize; k < size; k++) {
                    this.elementData[k] = null;
                }
                this.elementCount = newSize;
                if (this.modCount == expectedModCount) {
                    this.modCount++;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        } else {
            throw new ConcurrentModificationException();
        }
        return anyToRemove;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.function.UnaryOperator<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public synchronized void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        int expectedModCount = this.modCount;
        int size = this.elementCount;
        int i = 0;
        while (this.modCount == expectedModCount && i < size) {
            this.elementData[i] = operator.apply(this.elementData[i]);
            i++;
        }
        if (this.modCount == expectedModCount) {
            this.modCount++;
        } else {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.List
    public synchronized void sort(Comparator<? super E> c) {
        int expectedModCount = this.modCount;
        Arrays.sort(this.elementData, 0, this.elementCount, c);
        if (this.modCount == expectedModCount) {
            this.modCount++;
        } else {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new VectorSpliterator(this, null, 0, -1, 0);
    }

    static final class VectorSpliterator<E> implements Spliterator<E> {
        private Object[] array;
        private int expectedModCount;
        private int fence;
        private int index;
        private final Vector<E> list;

        VectorSpliterator(Vector<E> list2, Object[] array2, int origin, int fence2, int expectedModCount2) {
            this.list = list2;
            this.array = array2;
            this.index = origin;
            this.fence = fence2;
            this.expectedModCount = expectedModCount2;
        }

        private int getFence() {
            int i = this.fence;
            int hi = i;
            if (i < 0) {
                synchronized (this.list) {
                    this.array = this.list.elementData;
                    this.expectedModCount = this.list.modCount;
                    int i2 = this.list.elementCount;
                    this.fence = i2;
                    hi = i2;
                }
            }
            return hi;
        }

        @Override // java.util.Spliterator
        public Spliterator<E> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            Vector<E> vector = this.list;
            Object[] objArr = this.array;
            this.index = mid;
            return new VectorSpliterator(vector, objArr, lo, mid, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action != null) {
                int fence2 = getFence();
                int i = this.index;
                if (fence2 <= i) {
                    return false;
                }
                this.index = i + 1;
                action.accept(this.array[i]);
                if (this.list.modCount == this.expectedModCount) {
                    return true;
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            Object[] a;
            if (action != null) {
                Vector<E> lst = this.list;
                if (lst != null) {
                    int i = this.fence;
                    int hi = i;
                    if (i < 0) {
                        synchronized (lst) {
                            this.expectedModCount = lst.modCount;
                            a = lst.elementData;
                            this.array = a;
                            int i2 = lst.elementCount;
                            this.fence = i2;
                            hi = i2;
                        }
                    } else {
                        a = this.array;
                    }
                    if (a != null) {
                        int i3 = this.index;
                        if (i3 >= 0) {
                            this.index = hi;
                            if (hi <= a.length) {
                                for (int i4 = i3; i4 < hi; i4++) {
                                    action.accept(a[i4]);
                                }
                                if (lst.modCount == this.expectedModCount) {
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
        public long estimateSize() {
            return (long) (getFence() - this.index);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16464;
        }
    }
}
