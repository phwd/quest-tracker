package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ArrayDeque extends AbstractCollection implements Deque, Cloneable, Serializable {
    private static final long serialVersionUID = 2340985798034038923L;
    transient Object[] elements;
    transient int head;
    transient int tail;

    private void checkInvariants() {
    }

    private void allocateElements(int i) {
        int i2 = 8;
        if (i >= 8) {
            int i3 = i | (i >>> 1);
            int i4 = i3 | (i3 >>> 2);
            int i5 = i4 | (i4 >>> 4);
            int i6 = i5 | (i5 >>> 8);
            i2 = (i6 | (i6 >>> 16)) + 1;
            if (i2 < 0) {
                i2 >>>= 1;
            }
        }
        this.elements = new Object[i2];
    }

    private void doubleCapacity() {
        int i = this.head;
        Object[] objArr = this.elements;
        int length = objArr.length;
        int i2 = length - i;
        int i3 = length << 1;
        if (i3 >= 0) {
            Object[] objArr2 = new Object[i3];
            System.arraycopy(objArr, i, objArr2, 0, i2);
            System.arraycopy(this.elements, 0, objArr2, i2, i);
            Arrays.fill(this.elements, (Object) null);
            this.elements = objArr2;
            this.head = 0;
            this.tail = length;
            return;
        }
        throw new IllegalStateException("Sorry, deque too big");
    }

    public ArrayDeque() {
        this.elements = new Object[16];
    }

    public ArrayDeque(Collection collection) {
        allocateElements(collection.size());
        addAll(collection);
    }

    public void addLast(Object obj) {
        if (obj != null) {
            Object[] objArr = this.elements;
            int i = this.tail;
            objArr[i] = obj;
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
    public Object removeFirst() {
        Object pollFirst = pollFirst();
        if (pollFirst != null) {
            return pollFirst;
        }
        throw new NoSuchElementException();
    }

    public Object pollFirst() {
        Object[] objArr = this.elements;
        int i = this.head;
        Object obj = objArr[i];
        if (obj != null) {
            objArr[i] = null;
            this.head = (objArr.length - 1) & (i + 1);
        }
        return obj;
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object obj2 = this.elements[i];
            if (obj2 == null) {
                return false;
            }
            if (obj.equals(obj2)) {
                delete(i);
                return true;
            }
            i = (i + 1) & length;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.Deque
    public Object poll() {
        return pollFirst();
    }

    /* access modifiers changed from: package-private */
    public boolean delete(int i) {
        checkInvariants();
        Object[] objArr = this.elements;
        int length = objArr.length - 1;
        int i2 = this.head;
        int i3 = this.tail;
        int i4 = (i - i2) & length;
        int i5 = (i3 - i) & length;
        if (i4 >= ((i3 - i2) & length)) {
            throw new ConcurrentModificationException();
        } else if (i4 < i5) {
            if (i2 <= i) {
                System.arraycopy(objArr, i2, objArr, i2 + 1, i4);
            } else {
                System.arraycopy(objArr, 0, objArr, 1, i);
                objArr[0] = objArr[length];
                System.arraycopy(objArr, i2, objArr, i2 + 1, length - i2);
            }
            objArr[i2] = null;
            this.head = (i2 + 1) & length;
            return false;
        } else {
            if (i < i3) {
                System.arraycopy(objArr, i + 1, objArr, i, i5);
                this.tail = i3 - 1;
            } else {
                System.arraycopy(objArr, i + 1, objArr, i, length - i);
                objArr[length] = objArr[0];
                System.arraycopy(objArr, 1, objArr, 0, i3);
                this.tail = (i3 - 1) & length;
            }
            return true;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return (this.elements.length - 1) & (this.tail - this.head);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.lang.Iterable
    public Iterator iterator() {
        return new DeqIterator();
    }

    private class DeqIterator implements Iterator {
        private int cursor;
        private int fence;
        private int lastRet;

        private DeqIterator() {
            ArrayDeque arrayDeque = ArrayDeque.this;
            this.cursor = arrayDeque.head;
            this.fence = arrayDeque.tail;
            this.lastRet = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor != this.fence;
        }

        @Override // java.util.Iterator
        public Object next() {
            int i = this.cursor;
            int i2 = this.fence;
            if (i != i2) {
                ArrayDeque arrayDeque = ArrayDeque.this;
                Object[] objArr = arrayDeque.elements;
                Object obj = objArr[i];
                if (arrayDeque.tail != i2 || obj == null) {
                    throw new ConcurrentModificationException();
                }
                this.lastRet = i;
                this.cursor = (i + 1) & (objArr.length - 1);
                return obj;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i >= 0) {
                if (ArrayDeque.this.delete(i)) {
                    ArrayDeque arrayDeque = ArrayDeque.this;
                    this.cursor = (this.cursor - 1) & (arrayDeque.elements.length - 1);
                    this.fence = arrayDeque.tail;
                }
                this.lastRet = -1;
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object obj2 = this.elements[i];
            if (obj2 == null) {
                return false;
            }
            if (obj.equals(obj2)) {
                return true;
            }
            i = (i + 1) & length;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        int i = this.head;
        int i2 = this.tail;
        if (i != i2) {
            this.tail = 0;
            this.head = 0;
            int length = this.elements.length - 1;
            do {
                this.elements[i] = null;
                i = (i + 1) & length;
            } while (i != i2);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int i = this.head;
        int i2 = this.tail;
        boolean z = i2 < i;
        Object[] copyOfRange = Arrays.copyOfRange(this.elements, i, z ? this.elements.length + i2 : i2);
        if (z) {
            Object[] objArr = this.elements;
            System.arraycopy(objArr, 0, copyOfRange, objArr.length - i, i2);
        }
        return copyOfRange;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        int i = this.head;
        int i2 = this.tail;
        boolean z = i2 < i;
        int length = (i2 - i) + (z ? this.elements.length : 0);
        int i3 = length - (z ? i2 : 0);
        int length2 = objArr.length;
        if (length > length2) {
            objArr = Arrays.copyOfRange(this.elements, i, length + i, objArr.getClass());
        } else {
            System.arraycopy(this.elements, i, objArr, 0, i3);
            if (length < length2) {
                objArr[length] = null;
            }
        }
        if (z) {
            System.arraycopy(this.elements, 0, objArr, i3, i2);
        }
        return objArr;
    }

    public ArrayDeque clone() {
        try {
            ArrayDeque arrayDeque = (ArrayDeque) super.clone();
            arrayDeque.elements = Arrays.copyOf(this.elements, this.elements.length);
            return arrayDeque;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
