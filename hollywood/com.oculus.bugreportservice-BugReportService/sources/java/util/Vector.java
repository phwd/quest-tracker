package java.util;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Vector extends AbstractList implements List, RandomAccess, Cloneable, Serializable {
    private static final long serialVersionUID = -2767605614048989439L;
    protected int capacityIncrement;
    protected int elementCount;
    protected Object[] elementData;

    /* renamed from: java.util.Vector$1  reason: invalid class name */
    class AnonymousClass1 implements Enumeration {
    }

    public Vector(int i, int i2) {
        if (i >= 0) {
            this.elementData = new Object[i];
            this.capacityIncrement = i2;
            return;
        }
        throw new IllegalArgumentException("Illegal Capacity: " + i);
    }

    public Vector(int i) {
        this(i, 0);
    }

    public Vector() {
        this(10);
    }

    public Vector(Collection collection) {
        this.elementData = collection.toArray();
        Object[] objArr = this.elementData;
        this.elementCount = objArr.length;
        if (objArr.getClass() != Object[].class) {
            this.elementData = Arrays.copyOf(this.elementData, this.elementCount, Object[].class);
        }
    }

    public synchronized void copyInto(Object[] objArr) {
        System.arraycopy(this.elementData, 0, objArr, 0, this.elementCount);
    }

    private void ensureCapacityHelper(int i) {
        if (i - this.elementData.length > 0) {
            grow(i);
        }
    }

    private void grow(int i) {
        int length = this.elementData.length;
        int i2 = this.capacityIncrement;
        if (i2 <= 0) {
            i2 = length;
        }
        int i3 = length + i2;
        if (i3 - i < 0) {
            i3 = i;
        }
        if (i3 - 2147483639 > 0) {
            i3 = hugeCapacity(i);
        }
        this.elementData = Arrays.copyOf(this.elementData, i3);
    }

    private static int hugeCapacity(int i) {
        if (i >= 0) {
            return i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        throw new OutOfMemoryError();
    }

    public synchronized void setSize(int i) {
        this.modCount++;
        if (i > this.elementCount) {
            ensureCapacityHelper(i);
        } else {
            for (int i2 = i; i2 < this.elementCount; i2++) {
                this.elementData[i2] = null;
            }
        }
        this.elementCount = i;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized int size() {
        return this.elementCount;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj, 0) >= 0;
    }

    @Override // java.util.List, java.util.AbstractList
    public int indexOf(Object obj) {
        return indexOf(obj, 0);
    }

    public synchronized int indexOf(Object obj, int i) {
        if (obj == null) {
            while (i < this.elementCount) {
                if (this.elementData[i] == null) {
                    return i;
                }
                i++;
            }
        } else {
            while (i < this.elementCount) {
                if (obj.equals(this.elementData[i])) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public synchronized Object elementAt(int i) {
        if (i < this.elementCount) {
        } else {
            throw new ArrayIndexOutOfBoundsException(i + " >= " + this.elementCount);
        }
        return elementData(i);
    }

    public synchronized void removeElementAt(int i) {
        this.modCount++;
        if (i >= this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(i + " >= " + this.elementCount);
        } else if (i >= 0) {
            int i2 = (this.elementCount - i) - 1;
            if (i2 > 0) {
                System.arraycopy(this.elementData, i + 1, this.elementData, i, i2);
            }
            this.elementCount--;
            this.elementData[this.elementCount] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
    }

    public synchronized void insertElementAt(Object obj, int i) {
        this.modCount++;
        if (i <= this.elementCount) {
            ensureCapacityHelper(this.elementCount + 1);
            System.arraycopy(this.elementData, i, this.elementData, i + 1, this.elementCount - i);
            this.elementData[i] = obj;
            this.elementCount++;
        } else {
            throw new ArrayIndexOutOfBoundsException(i + " > " + this.elementCount);
        }
    }

    public synchronized void addElement(Object obj) {
        this.modCount++;
        ensureCapacityHelper(this.elementCount + 1);
        Object[] objArr = this.elementData;
        int i = this.elementCount;
        this.elementCount = i + 1;
        objArr[i] = obj;
    }

    public synchronized boolean removeElement(Object obj) {
        this.modCount++;
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeElementAt(indexOf);
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
        Vector vector;
        try {
            vector = (Vector) super.clone();
            vector.elementData = Arrays.copyOf(this.elementData, this.elementCount);
            vector.modCount = 0;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
        return vector;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.elementCount);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized Object[] toArray(Object[] objArr) {
        if (objArr.length < this.elementCount) {
            return Arrays.copyOf(this.elementData, this.elementCount, objArr.getClass());
        }
        System.arraycopy(this.elementData, 0, objArr, 0, this.elementCount);
        if (objArr.length > this.elementCount) {
            objArr[this.elementCount] = null;
        }
        return objArr;
    }

    /* access modifiers changed from: package-private */
    public Object elementData(int i) {
        return this.elementData[i];
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized Object get(int i) {
        if (i < this.elementCount) {
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return elementData(i);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized Object set(int i, Object obj) {
        Object elementData2;
        if (i < this.elementCount) {
            elementData2 = elementData(i);
            this.elementData[i] = obj;
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return elementData2;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public synchronized boolean add(Object obj) {
        this.modCount++;
        ensureCapacityHelper(this.elementCount + 1);
        Object[] objArr = this.elementData;
        int i = this.elementCount;
        this.elementCount = i + 1;
        objArr[i] = obj;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        return removeElement(obj);
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, Object obj) {
        insertElementAt(obj, i);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized Object remove(int i) {
        Object elementData2;
        this.modCount++;
        if (i < this.elementCount) {
            elementData2 = elementData(i);
            int i2 = (this.elementCount - i) - 1;
            if (i2 > 0) {
                System.arraycopy(this.elementData, i + 1, this.elementData, i, i2);
            }
            Object[] objArr = this.elementData;
            int i3 = this.elementCount - 1;
            this.elementCount = i3;
            objArr[i3] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return elementData2;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public void clear() {
        removeAllElements();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public synchronized boolean containsAll(Collection collection) {
        return super.containsAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean addAll(Collection collection) {
        boolean z;
        z = true;
        this.modCount++;
        Object[] array = collection.toArray();
        int length = array.length;
        ensureCapacityHelper(this.elementCount + length);
        System.arraycopy(array, 0, this.elementData, this.elementCount, length);
        this.elementCount += length;
        if (length == 0) {
            z = false;
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public synchronized boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public synchronized boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractList
    public synchronized boolean addAll(int i, Collection collection) {
        boolean z;
        z = true;
        this.modCount++;
        if (i < 0 || i > this.elementCount) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        Object[] array = collection.toArray();
        int length = array.length;
        ensureCapacityHelper(this.elementCount + length);
        int i2 = this.elementCount - i;
        if (i2 > 0) {
            System.arraycopy(this.elementData, i, this.elementData, i + length, i2);
        }
        System.arraycopy(array, 0, this.elementData, i, length);
        this.elementCount += length;
        if (length == 0) {
            z = false;
        }
        return z;
    }

    @Override // java.util.List, java.util.Collection, java.util.AbstractList
    public synchronized boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.List, java.util.Collection, java.util.AbstractList
    public synchronized int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.AbstractCollection
    public synchronized String toString() {
        return super.toString();
    }

    /* access modifiers changed from: protected */
    @Override // java.util.AbstractList
    public synchronized void removeRange(int i, int i2) {
        this.modCount++;
        System.arraycopy(this.elementData, i2, this.elementData, i, this.elementCount - i2);
        int i3 = this.elementCount - (i2 - i);
        while (this.elementCount != i3) {
            Object[] objArr = this.elementData;
            int i4 = this.elementCount - 1;
            this.elementCount = i4;
            objArr[i4] = null;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized ListIterator listIterator(int i) {
        if (i >= 0) {
            if (i <= this.elementCount) {
            }
        }
        throw new IndexOutOfBoundsException("Index: " + i);
        return new ListItr(i);
    }

    @Override // java.util.List, java.util.AbstractList
    public synchronized ListIterator listIterator() {
        return new ListItr(0);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public synchronized Iterator iterator() {
        return new Itr(this, null);
    }

    private class Itr implements Iterator {
        int cursor;
        int expectedModCount;
        int lastRet;
        protected int limit;

        private Itr() {
            Vector vector = Vector.this;
            this.limit = vector.elementCount;
            this.lastRet = -1;
            this.expectedModCount = vector.modCount;
        }

        /* synthetic */ Itr(Vector vector, AnonymousClass1 r2) {
            this();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor < this.limit;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object elementData;
            synchronized (Vector.this) {
                checkForComodification();
                int i = this.cursor;
                if (i < this.limit) {
                    this.cursor = i + 1;
                    Vector vector = Vector.this;
                    this.lastRet = i;
                    elementData = vector.elementData(i);
                } else {
                    throw new NoSuchElementException();
                }
            }
            return elementData;
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

        /* access modifiers changed from: package-private */
        public final void checkForComodification() {
            if (Vector.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    final class ListItr extends Itr implements ListIterator {
        ListItr(int i) {
            super(Vector.this, null);
            this.cursor = i;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            Object elementData;
            synchronized (Vector.this) {
                checkForComodification();
                int i = this.cursor - 1;
                if (i >= 0) {
                    this.cursor = i;
                    Vector vector = Vector.this;
                    this.lastRet = i;
                    elementData = vector.elementData(i);
                } else {
                    throw new NoSuchElementException();
                }
            }
            return elementData;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            if (this.lastRet != -1) {
                synchronized (Vector.this) {
                    checkForComodification();
                    Vector.this.set(this.lastRet, obj);
                }
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            int i = this.cursor;
            synchronized (Vector.this) {
                checkForComodification();
                Vector.this.add(i, obj);
                this.expectedModCount = Vector.this.modCount;
                this.limit++;
            }
            this.cursor = i + 1;
            this.lastRet = -1;
        }
    }

    @Override // java.util.List
    public synchronized void sort(Comparator comparator) {
        int i = this.modCount;
        Arrays.sort(this.elementData, 0, this.elementCount, comparator);
        if (this.modCount == i) {
            this.modCount++;
        } else {
            throw new ConcurrentModificationException();
        }
    }
}
