package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private static final long serialVersionUID = 8683452581122892189L;
    transient Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(Collection<? extends E> c) {
        this.elementData = c.toArray();
        Object[] objArr = this.elementData;
        int length = objArr.length;
        this.size = length;
        if (length == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else if (objArr.getClass() != Object[].class) {
            this.elementData = Arrays.copyOf(this.elementData, this.size, Object[].class);
        }
    }

    public void trimToSize() {
        Object[] objArr;
        this.modCount++;
        int i = this.size;
        Object[] objArr2 = this.elementData;
        if (i < objArr2.length) {
            if (i == 0) {
                objArr = EMPTY_ELEMENTDATA;
            } else {
                objArr = Arrays.copyOf(objArr2, i);
            }
            this.elementData = objArr;
        }
    }

    public void ensureCapacity(int minCapacity) {
        int minExpand;
        if (this.elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minExpand = 0;
        } else {
            minExpand = 10;
        }
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(10, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        this.modCount++;
        if (minCapacity - this.elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = (oldCapacity >> 1) + oldCapacity;
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

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override // java.util.List, java.util.AbstractList
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = 0; i2 < this.size; i2++) {
            if (o.equals(this.elementData[i2])) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.AbstractList
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = this.size - 1; i >= 0; i--) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = this.size - 1; i2 >= 0; i2--) {
            if (o.equals(this.elementData[i2])) {
                return i2;
            }
        }
        return -1;
    }

    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList) super.clone();
            v.elementData = Arrays.copyOf(this.elementData, this.size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public <T> T[] toArray(T[] a) {
        int length = a.length;
        int i = this.size;
        if (length < i) {
            return (T[]) Arrays.copyOf(this.elementData, i, a.getClass());
        }
        System.arraycopy(this.elementData, 0, a, 0, i);
        int length2 = a.length;
        int i2 = this.size;
        if (length2 > i2) {
            a[i2] = null;
        }
        return a;
    }

    @Override // java.util.List, java.util.AbstractList
    public E get(int index) {
        if (index < this.size) {
            return (E) this.elementData[index];
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    @Override // java.util.List, java.util.AbstractList
    public E set(int index, E element) {
        if (index < this.size) {
            Object[] objArr = this.elementData;
            E oldValue = (E) objArr[index];
            objArr[index] = element;
            return oldValue;
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public boolean add(E e) {
        ensureCapacityInternal(this.size + 1);
        Object[] objArr = this.elementData;
        int i = this.size;
        this.size = i + 1;
        objArr[i] = e;
        return true;
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int index, E element) {
        int i = this.size;
        if (index > i || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        ensureCapacityInternal(i + 1);
        Object[] objArr = this.elementData;
        System.arraycopy(objArr, index, objArr, index + 1, this.size - index);
        this.elementData[index] = element;
        this.size++;
    }

    @Override // java.util.List, java.util.AbstractList
    public E remove(int index) {
        if (index < this.size) {
            this.modCount++;
            Object[] objArr = this.elementData;
            E oldValue = (E) objArr[index];
            int numMoved = (this.size - index) - 1;
            if (numMoved > 0) {
                System.arraycopy(objArr, index + 1, objArr, index, numMoved);
            }
            Object[] objArr2 = this.elementData;
            int i = this.size - 1;
            this.size = i;
            objArr2[i] = null;
            return oldValue;
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < this.size; index++) {
                if (this.elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
            return false;
        }
        for (int index2 = 0; index2 < this.size; index2++) {
            if (o.equals(this.elementData[index2])) {
                fastRemove(index2);
                return true;
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        this.modCount++;
        int numMoved = (this.size - index) - 1;
        if (numMoved > 0) {
            Object[] objArr = this.elementData;
            System.arraycopy(objArr, index + 1, objArr, index, numMoved);
        }
        Object[] objArr2 = this.elementData;
        int i = this.size - 1;
        this.size = i;
        objArr2[i] = null;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public void clear() {
        this.modCount++;
        for (int i = 0; i < this.size; i++) {
            this.elementData[i] = null;
        }
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(this.size + numNew);
        System.arraycopy(a, 0, this.elementData, this.size, numNew);
        this.size += numNew;
        if (numNew != 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.AbstractList
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(this.size + numNew);
        int numMoved = this.size - index;
        if (numMoved > 0) {
            Object[] objArr = this.elementData;
            System.arraycopy(objArr, index, objArr, index + numNew, numMoved);
        }
        System.arraycopy(a, 0, this.elementData, index, numNew);
        this.size += numNew;
        if (numNew != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.AbstractList
    public void removeRange(int fromIndex, int toIndex) {
        if (toIndex >= fromIndex) {
            this.modCount++;
            Object[] objArr = this.elementData;
            System.arraycopy(objArr, toIndex, objArr, fromIndex, this.size - toIndex);
            int newSize = this.size - (toIndex - fromIndex);
            for (int i = newSize; i < this.size; i++) {
                this.elementData[i] = null;
            }
            this.size = newSize;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean batchRemove(java.util.Collection<?> r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ArrayList.batchRemove(java.util.Collection, boolean):boolean");
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        int expectedModCount = this.modCount;
        s.defaultWriteObject();
        s.writeInt(this.size);
        for (int i = 0; i < this.size; i++) {
            s.writeObject(this.elementData[i]);
        }
        if (this.modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.elementData = EMPTY_ELEMENTDATA;
        s.defaultReadObject();
        s.readInt();
        int i = this.size;
        if (i > 0) {
            ensureCapacityInternal(i);
            Object[] a = this.elementData;
            for (int i2 = 0; i2 < this.size; i2++) {
                a[i2] = s.readObject();
            }
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator(int index) {
        if (index >= 0 && index <= this.size) {
            return new ListItr(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int expectedModCount;
        int lastRet;
        protected int limit;

        private Itr() {
            this.limit = ArrayList.this.size;
            this.lastRet = -1;
            this.expectedModCount = ArrayList.this.modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor < this.limit;
        }

        @Override // java.util.Iterator
        public E next() {
            if (ArrayList.this.modCount == this.expectedModCount) {
                int i = this.cursor;
                if (i < this.limit) {
                    Object[] elementData = ArrayList.this.elementData;
                    if (i < elementData.length) {
                        this.cursor = i + 1;
                        this.lastRet = i;
                        return (E) elementData[i];
                    }
                    throw new ConcurrentModificationException();
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else if (ArrayList.this.modCount == this.expectedModCount) {
                try {
                    ArrayList.this.remove(this.lastRet);
                    this.cursor = this.lastRet;
                    this.lastRet = -1;
                    this.expectedModCount = ArrayList.this.modCount;
                    this.limit--;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            int size = ArrayList.this.size;
            int i = this.cursor;
            if (i < size) {
                Object[] elementData = ArrayList.this.elementData;
                if (i < elementData.length) {
                    while (i != size && ArrayList.this.modCount == this.expectedModCount) {
                        consumer.accept(elementData[i]);
                        i++;
                    }
                    this.cursor = i;
                    this.lastRet = i - 1;
                    if (ArrayList.this.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return;
                }
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends ArrayList<E>.Itr implements ListIterator<E> {
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
            if (ArrayList.this.modCount == this.expectedModCount) {
                int i = this.cursor - 1;
                if (i >= 0) {
                    Object[] elementData = ArrayList.this.elementData;
                    if (i < elementData.length) {
                        this.cursor = i;
                        this.lastRet = i;
                        return (E) elementData[i];
                    }
                    throw new ConcurrentModificationException();
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else if (ArrayList.this.modCount == this.expectedModCount) {
                try {
                    ArrayList.this.set(this.lastRet, e);
                } catch (IndexOutOfBoundsException e2) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            if (ArrayList.this.modCount == this.expectedModCount) {
                try {
                    int i = this.cursor;
                    ArrayList.this.add(i, e);
                    this.cursor = i + 1;
                    this.lastRet = -1;
                    this.expectedModCount = ArrayList.this.modCount;
                    this.limit++;
                } catch (IndexOutOfBoundsException e2) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, this.size);
        return new SubList(this, 0, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size2) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        } else if (toIndex > size2) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        } else if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    private class SubList extends AbstractList<E> implements RandomAccess {
        private final int offset;
        private final AbstractList<E> parent;
        private final int parentOffset;
        int size;

        SubList(AbstractList<E> parent2, int offset2, int fromIndex, int toIndex) {
            this.parent = parent2;
            this.parentOffset = fromIndex;
            this.offset = offset2 + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = ArrayList.this.modCount;
        }

        @Override // java.util.List, java.util.AbstractList
        public E set(int index, E e) {
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            } else if (ArrayList.this.modCount == this.modCount) {
                E oldValue = (E) ArrayList.this.elementData[this.offset + index];
                ArrayList.this.elementData[this.offset + index] = e;
                return oldValue;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public E get(int index) {
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            } else if (ArrayList.this.modCount == this.modCount) {
                return (E) ArrayList.this.elementData[this.offset + index];
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            if (ArrayList.this.modCount == this.modCount) {
                return this.size;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.List, java.util.AbstractList
        public void add(int index, E e) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            } else if (ArrayList.this.modCount == this.modCount) {
                this.parent.add(this.parentOffset + index, e);
                this.modCount = this.parent.modCount;
                this.size++;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public E remove(int index) {
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            } else if (ArrayList.this.modCount == this.modCount) {
                E result = this.parent.remove(this.parentOffset + index);
                this.modCount = this.parent.modCount;
                this.size--;
                return result;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.AbstractList
        public void removeRange(int fromIndex, int toIndex) {
            if (ArrayList.this.modCount == this.modCount) {
                AbstractList<E> abstractList = this.parent;
                int i = this.parentOffset;
                abstractList.removeRange(i + fromIndex, i + toIndex);
                this.modCount = this.parent.modCount;
                this.size -= toIndex - fromIndex;
                return;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean addAll(Collection<? extends E> c) {
            return addAll(this.size, c);
        }

        @Override // java.util.List, java.util.AbstractList
        public boolean addAll(int index, Collection<? extends E> c) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
            int cSize = c.size();
            if (cSize == 0) {
                return false;
            }
            if (ArrayList.this.modCount == this.modCount) {
                this.parent.addAll(this.parentOffset + index, c);
                this.modCount = this.parent.modCount;
                this.size += cSize;
                return true;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator<E> iterator() {
            return listIterator();
        }

        @Override // java.util.List, java.util.AbstractList
        public ListIterator<E> listIterator(final int index) {
            if (ArrayList.this.modCount != this.modCount) {
                throw new ConcurrentModificationException();
            } else if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            } else {
                final int offset2 = this.offset;
                return new ListIterator<E>() {
                    /* class java.util.ArrayList.SubList.AnonymousClass1 */
                    int cursor = index;
                    int expectedModCount = ArrayList.this.modCount;
                    int lastRet = -1;

                    @Override // java.util.Iterator, java.util.ListIterator
                    public boolean hasNext() {
                        return this.cursor != SubList.this.size;
                    }

                    @Override // java.util.Iterator, java.util.ListIterator
                    public E next() {
                        if (this.expectedModCount == ArrayList.this.modCount) {
                            int i = this.cursor;
                            if (i < SubList.this.size) {
                                Object[] elementData = ArrayList.this.elementData;
                                int i2 = offset2;
                                if (i2 + i < elementData.length) {
                                    this.cursor = i + 1;
                                    this.lastRet = i;
                                    return (E) elementData[i2 + i];
                                }
                                throw new ConcurrentModificationException();
                            }
                            throw new NoSuchElementException();
                        }
                        throw new ConcurrentModificationException();
                    }

                    @Override // java.util.ListIterator
                    public boolean hasPrevious() {
                        return this.cursor != 0;
                    }

                    @Override // java.util.ListIterator
                    public E previous() {
                        if (this.expectedModCount == ArrayList.this.modCount) {
                            int i = this.cursor - 1;
                            if (i >= 0) {
                                Object[] elementData = ArrayList.this.elementData;
                                int i2 = offset2;
                                if (i2 + i < elementData.length) {
                                    this.cursor = i;
                                    this.lastRet = i;
                                    return (E) elementData[i2 + i];
                                }
                                throw new ConcurrentModificationException();
                            }
                            throw new NoSuchElementException();
                        }
                        throw new ConcurrentModificationException();
                    }

                    @Override // java.util.Iterator
                    public void forEachRemaining(Consumer<? super E> consumer) {
                        Objects.requireNonNull(consumer);
                        int size = SubList.this.size;
                        int i = this.cursor;
                        if (i < size) {
                            Object[] elementData = ArrayList.this.elementData;
                            if (offset2 + i < elementData.length) {
                                while (i != size && SubList.this.modCount == this.expectedModCount) {
                                    consumer.accept(elementData[offset2 + i]);
                                    i++;
                                }
                                this.cursor = i;
                                this.lastRet = i;
                                if (this.expectedModCount != ArrayList.this.modCount) {
                                    throw new ConcurrentModificationException();
                                }
                                return;
                            }
                            throw new ConcurrentModificationException();
                        }
                    }

                    @Override // java.util.ListIterator
                    public int nextIndex() {
                        return this.cursor;
                    }

                    @Override // java.util.ListIterator
                    public int previousIndex() {
                        return this.cursor - 1;
                    }

                    @Override // java.util.Iterator, java.util.ListIterator
                    public void remove() {
                        if (this.lastRet < 0) {
                            throw new IllegalStateException();
                        } else if (this.expectedModCount == ArrayList.this.modCount) {
                            try {
                                SubList.this.remove(this.lastRet);
                                this.cursor = this.lastRet;
                                this.lastRet = -1;
                                this.expectedModCount = ArrayList.this.modCount;
                            } catch (IndexOutOfBoundsException e) {
                                throw new ConcurrentModificationException();
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }

                    @Override // java.util.ListIterator
                    public void set(E e) {
                        if (this.lastRet < 0) {
                            throw new IllegalStateException();
                        } else if (this.expectedModCount == ArrayList.this.modCount) {
                            try {
                                ArrayList.this.set(offset2 + this.lastRet, e);
                            } catch (IndexOutOfBoundsException e2) {
                                throw new ConcurrentModificationException();
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }

                    @Override // java.util.ListIterator
                    public void add(E e) {
                        if (this.expectedModCount == ArrayList.this.modCount) {
                            try {
                                int i = this.cursor;
                                SubList.this.add(i, e);
                                this.cursor = i + 1;
                                this.lastRet = -1;
                                this.expectedModCount = ArrayList.this.modCount;
                            } catch (IndexOutOfBoundsException e2) {
                                throw new ConcurrentModificationException();
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }
                };
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public List<E> subList(int fromIndex, int toIndex) {
            ArrayList.subListRangeCheck(fromIndex, toIndex, this.size);
            return new SubList(this, this.offset, fromIndex, toIndex);
        }

        private String outOfBoundsMsg(int index) {
            return "Index: " + index + ", Size: " + this.size;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            if (this.modCount == ArrayList.this.modCount) {
                ArrayList arrayList = ArrayList.this;
                int i = this.offset;
                return new ArrayListSpliterator(arrayList, i, this.size + i, this.modCount);
            }
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        int expectedModCount = this.modCount;
        Object[] objArr = this.elementData;
        int size2 = this.size;
        int i = 0;
        while (this.modCount == expectedModCount && i < size2) {
            action.accept(objArr[i]);
            i++;
        }
        if (this.modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new ArrayListSpliterator(this, 0, -1, 0);
    }

    static final class ArrayListSpliterator<E> implements Spliterator<E> {
        private int expectedModCount;
        private int fence;
        private int index;
        private final ArrayList<E> list;

        ArrayListSpliterator(ArrayList<E> list2, int origin, int fence2, int expectedModCount2) {
            this.list = list2;
            this.index = origin;
            this.fence = fence2;
            this.expectedModCount = expectedModCount2;
        }

        private int getFence() {
            int hi = this.fence;
            if (hi >= 0) {
                return hi;
            }
            ArrayList<E> lst = this.list;
            if (lst == null) {
                this.fence = 0;
                return 0;
            }
            this.expectedModCount = lst.modCount;
            int hi2 = ((ArrayList) lst).size;
            this.fence = hi2;
            return hi2;
        }

        @Override // java.util.Spliterator
        public ArrayListSpliterator<E> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            ArrayList<E> arrayList = this.list;
            this.index = mid;
            return new ArrayListSpliterator<>(arrayList, lo, mid, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action != null) {
                int hi = getFence();
                int i = this.index;
                if (i >= hi) {
                    return false;
                }
                this.index = i + 1;
                action.accept(this.list.elementData[i]);
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
            int mc;
            if (action != null) {
                ArrayList<E> lst = this.list;
                if (!(lst == null || (a = lst.elementData) == null)) {
                    int i = this.fence;
                    int hi = i;
                    if (i < 0) {
                        mc = lst.modCount;
                        hi = ((ArrayList) lst).size;
                    } else {
                        mc = this.expectedModCount;
                    }
                    int i2 = this.index;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (hi <= a.length) {
                            for (int i3 = i2; i3 < hi; i3++) {
                                action.accept(a[i3]);
                            }
                            if (lst.modCount == mc) {
                                return;
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

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        int removeCount = 0;
        BitSet removeSet = new BitSet(this.size);
        int expectedModCount = this.modCount;
        int size2 = this.size;
        int i = 0;
        while (this.modCount == expectedModCount && i < size2) {
            if (filter.test(this.elementData[i])) {
                removeSet.set(i);
                removeCount++;
            }
            i++;
        }
        if (this.modCount == expectedModCount) {
            boolean anyToRemove = removeCount > 0;
            if (anyToRemove) {
                int newSize = size2 - removeCount;
                int i2 = 0;
                int j = 0;
                while (i2 < size2 && j < newSize) {
                    int i3 = removeSet.nextClearBit(i2);
                    Object[] objArr = this.elementData;
                    objArr[j] = objArr[i3];
                    i2 = i3 + 1;
                    j++;
                }
                for (int k = newSize; k < size2; k++) {
                    this.elementData[k] = null;
                }
                this.size = newSize;
                if (this.modCount == expectedModCount) {
                    this.modCount++;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            return anyToRemove;
        }
        throw new ConcurrentModificationException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.function.UnaryOperator<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        int expectedModCount = this.modCount;
        int size2 = this.size;
        int i = 0;
        while (this.modCount == expectedModCount && i < size2) {
            Object[] objArr = this.elementData;
            objArr[i] = operator.apply(objArr[i]);
            i++;
        }
        if (this.modCount == expectedModCount) {
            this.modCount++;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.List
    public void sort(Comparator<? super E> c) {
        int expectedModCount = this.modCount;
        Arrays.sort(this.elementData, 0, this.size, c);
        if (this.modCount == expectedModCount) {
            this.modCount++;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
