package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ArrayList extends AbstractList implements List, RandomAccess, Cloneable, Serializable {
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
    private static final long serialVersionUID = 8683452581122892189L;
    transient Object[] elementData;
    private int size;

    public ArrayList(int i) {
        if (i > 0) {
            this.elementData = new Object[i];
        } else if (i == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + i);
        }
    }

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(Collection collection) {
        this.elementData = collection.toArray();
        Object[] objArr = this.elementData;
        int length = objArr.length;
        this.size = length;
        if (length == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else if (objArr.getClass() != Object[].class) {
            this.elementData = Arrays.copyOf(this.elementData, this.size, Object[].class);
        }
    }

    private void ensureCapacityInternal(int i) {
        if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            i = Math.max(10, i);
        }
        ensureExplicitCapacity(i);
    }

    private void ensureExplicitCapacity(int i) {
        this.modCount++;
        if (i - this.elementData.length > 0) {
            grow(i);
        }
    }

    private void grow(int i) {
        int length = this.elementData.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = hugeCapacity(i);
        }
        this.elementData = Arrays.copyOf(this.elementData, i2);
    }

    private static int hugeCapacity(int i) {
        if (i >= 0) {
            return i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        throw new OutOfMemoryError();
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
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List, java.util.AbstractList
    public int indexOf(Object obj) {
        int i = 0;
        if (obj == null) {
            while (i < this.size) {
                if (this.elementData[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < this.size) {
            if (obj.equals(this.elementData[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Object clone() {
        try {
            ArrayList arrayList = (ArrayList) super.clone();
            arrayList.elementData = Arrays.copyOf(this.elementData, this.size);
            arrayList.modCount = 0;
            return arrayList;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        int length = objArr.length;
        int i = this.size;
        if (length < i) {
            return Arrays.copyOf(this.elementData, i, objArr.getClass());
        }
        System.arraycopy(this.elementData, 0, objArr, 0, i);
        int length2 = objArr.length;
        int i2 = this.size;
        if (length2 > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object get(int i) {
        if (i < this.size) {
            return this.elementData[i];
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
    }

    @Override // java.util.List, java.util.AbstractList
    public Object set(int i, Object obj) {
        if (i < this.size) {
            Object[] objArr = this.elementData;
            Object obj2 = objArr[i];
            objArr[i] = obj;
            return obj2;
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public boolean add(Object obj) {
        ensureCapacityInternal(this.size + 1);
        Object[] objArr = this.elementData;
        int i = this.size;
        this.size = i + 1;
        objArr[i] = obj;
        return true;
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, Object obj) {
        int i2 = this.size;
        if (i > i2 || i < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        }
        ensureCapacityInternal(i2 + 1);
        Object[] objArr = this.elementData;
        System.arraycopy(objArr, i, objArr, i + 1, this.size - i);
        this.elementData[i] = obj;
        this.size++;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object remove(int i) {
        int i2 = this.size;
        if (i < i2) {
            this.modCount++;
            Object[] objArr = this.elementData;
            Object obj = objArr[i];
            int i3 = (i2 - i) - 1;
            if (i3 > 0) {
                System.arraycopy(objArr, i + 1, objArr, i, i3);
            }
            Object[] objArr2 = this.elementData;
            int i4 = this.size - 1;
            this.size = i4;
            objArr2[i4] = null;
            return obj;
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        if (obj == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i2 = 0; i2 < this.size; i2++) {
                if (obj.equals(this.elementData[i2])) {
                    fastRemove(i2);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int i) {
        this.modCount++;
        int i2 = (this.size - i) - 1;
        if (i2 > 0) {
            Object[] objArr = this.elementData;
            System.arraycopy(objArr, i + 1, objArr, i, i2);
        }
        Object[] objArr2 = this.elementData;
        int i3 = this.size - 1;
        this.size = i3;
        objArr2[i3] = null;
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
    public boolean addAll(Collection collection) {
        Object[] array = collection.toArray();
        int length = array.length;
        ensureCapacityInternal(this.size + length);
        System.arraycopy(array, 0, this.elementData, this.size, length);
        this.size += length;
        if (length != 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractList
    public boolean addAll(int i, Collection collection) {
        if (i > this.size || i < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        }
        Object[] array = collection.toArray();
        int length = array.length;
        ensureCapacityInternal(this.size + length);
        int i2 = this.size - i;
        if (i2 > 0) {
            Object[] objArr = this.elementData;
            System.arraycopy(objArr, i, objArr, i + length, i2);
        }
        System.arraycopy(array, 0, this.elementData, i, length);
        this.size += length;
        if (length != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        if (i2 >= i) {
            this.modCount++;
            Object[] objArr = this.elementData;
            System.arraycopy(objArr, i2, objArr, i, this.size - i2);
            int i3 = this.size - (i2 - i);
            for (int i4 = i3; i4 < this.size; i4++) {
                this.elementData[i4] = null;
            }
            this.size = i3;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    private String outOfBoundsMsg(int i) {
        return "Index: " + i + ", Size: " + this.size;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        Objects.requireNonNull(collection);
        return batchRemove(collection, false);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection collection) {
        Objects.requireNonNull(collection);
        return batchRemove(collection, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean batchRemove(java.util.Collection r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ArrayList.batchRemove(java.util.Collection, boolean):boolean");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.elementData = EMPTY_ELEMENTDATA;
        objectInputStream.defaultReadObject();
        throw null;
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator listIterator(int i) {
        if (i >= 0 && i <= this.size) {
            return new ListItr(i);
        }
        throw new IndexOutOfBoundsException("Index: " + i);
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator listIterator() {
        return new ListItr(0);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator {
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
        public Object next() {
            ArrayList arrayList = ArrayList.this;
            if (arrayList.modCount == this.expectedModCount) {
                int i = this.cursor;
                if (i < this.limit) {
                    Object[] objArr = arrayList.elementData;
                    if (i < objArr.length) {
                        this.cursor = i + 1;
                        this.lastRet = i;
                        return objArr[i];
                    }
                    throw new ConcurrentModificationException();
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i >= 0) {
                ArrayList arrayList = ArrayList.this;
                if (arrayList.modCount == this.expectedModCount) {
                    try {
                        arrayList.remove(i);
                        this.cursor = this.lastRet;
                        this.lastRet = -1;
                        this.expectedModCount = ArrayList.this.modCount;
                        this.limit--;
                    } catch (IndexOutOfBoundsException unused) {
                        throw new ConcurrentModificationException();
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator {
        ListItr(int i) {
            super();
            this.cursor = i;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            ArrayList arrayList = ArrayList.this;
            if (arrayList.modCount == this.expectedModCount) {
                int i = this.cursor - 1;
                if (i >= 0) {
                    Object[] objArr = arrayList.elementData;
                    if (i < objArr.length) {
                        this.cursor = i;
                        this.lastRet = i;
                        return objArr[i];
                    }
                    throw new ConcurrentModificationException();
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            int i = this.lastRet;
            if (i >= 0) {
                ArrayList arrayList = ArrayList.this;
                if (arrayList.modCount == this.expectedModCount) {
                    try {
                        arrayList.set(i, obj);
                    } catch (IndexOutOfBoundsException unused) {
                        throw new ConcurrentModificationException();
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            ArrayList arrayList = ArrayList.this;
            if (arrayList.modCount == this.expectedModCount) {
                try {
                    int i = this.cursor;
                    arrayList.add(i, obj);
                    this.cursor = i + 1;
                    this.lastRet = -1;
                    this.expectedModCount = ArrayList.this.modCount;
                    this.limit++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    public List subList(int i, int i2) {
        subListRangeCheck(i, i2, this.size);
        return new SubList(this, 0, i, i2);
    }

    static void subListRangeCheck(int i, int i2, int i3) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + i);
        } else if (i2 > i3) {
            throw new IndexOutOfBoundsException("toIndex = " + i2);
        } else if (i > i2) {
            throw new IllegalArgumentException("fromIndex(" + i + ") > toIndex(" + i2 + ")");
        }
    }

    private class SubList extends AbstractList implements RandomAccess {
        private final int offset;
        private final AbstractList parent;
        private final int parentOffset;
        int size;

        SubList(AbstractList abstractList, int i, int i2, int i3) {
            this.parent = abstractList;
            this.parentOffset = i2;
            this.offset = i + i2;
            this.size = i3 - i2;
            this.modCount = ArrayList.this.modCount;
        }

        @Override // java.util.List, java.util.AbstractList
        public Object set(int i, Object obj) {
            if (i < 0 || i >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
            }
            ArrayList arrayList = ArrayList.this;
            if (arrayList.modCount == this.modCount) {
                Object[] objArr = arrayList.elementData;
                int i2 = this.offset;
                Object obj2 = objArr[i2 + i];
                objArr[i2 + i] = obj;
                return obj2;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.List, java.util.AbstractList
        public Object get(int i) {
            if (i < 0 || i >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
            }
            ArrayList arrayList = ArrayList.this;
            if (arrayList.modCount == this.modCount) {
                return arrayList.elementData[this.offset + i];
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            if (ArrayList.this.modCount == this.modCount) {
                return this.size;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.List, java.util.AbstractList
        public void add(int i, Object obj) {
            if (i < 0 || i > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
            } else if (ArrayList.this.modCount == this.modCount) {
                this.parent.add(this.parentOffset + i, obj);
                this.modCount = this.parent.modCount;
                this.size++;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public Object remove(int i) {
            if (i < 0 || i >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
            } else if (ArrayList.this.modCount == this.modCount) {
                Object remove = this.parent.remove(this.parentOffset + i);
                this.modCount = this.parent.modCount;
                this.size--;
                return remove;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.AbstractList
        public void removeRange(int i, int i2) {
            if (ArrayList.this.modCount == this.modCount) {
                AbstractList abstractList = this.parent;
                int i3 = this.parentOffset;
                abstractList.removeRange(i3 + i, i3 + i2);
                this.modCount = this.parent.modCount;
                this.size -= i2 - i;
                return;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean addAll(Collection collection) {
            return addAll(this.size, collection);
        }

        @Override // java.util.AbstractList
        public boolean addAll(int i, Collection collection) {
            if (i < 0 || i > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
            }
            int size2 = collection.size();
            if (size2 == 0) {
                return false;
            }
            if (ArrayList.this.modCount == this.modCount) {
                this.parent.addAll(this.parentOffset + i, collection);
                this.modCount = this.parent.modCount;
                this.size += size2;
                return true;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator iterator() {
            return listIterator();
        }

        @Override // java.util.List, java.util.AbstractList
        public ListIterator listIterator(final int i) {
            if (ArrayList.this.modCount != this.modCount) {
                throw new ConcurrentModificationException();
            } else if (i < 0 || i > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
            } else {
                final int i2 = this.offset;
                return new ListIterator() {
                    /* class java.util.ArrayList.SubList.AnonymousClass1 */
                    int cursor = i;
                    int expectedModCount = ArrayList.this.modCount;
                    int lastRet = -1;

                    @Override // java.util.Iterator, java.util.ListIterator
                    public boolean hasNext() {
                        return this.cursor != SubList.this.size;
                    }

                    @Override // java.util.Iterator, java.util.ListIterator
                    public Object next() {
                        int i = this.expectedModCount;
                        SubList subList = SubList.this;
                        ArrayList arrayList = ArrayList.this;
                        if (i == arrayList.modCount) {
                            int i2 = this.cursor;
                            if (i2 < subList.size) {
                                Object[] objArr = arrayList.elementData;
                                int i3 = i2;
                                if (i3 + i2 < objArr.length) {
                                    this.cursor = i2 + 1;
                                    this.lastRet = i2;
                                    return objArr[i3 + i2];
                                }
                                throw new ConcurrentModificationException();
                            }
                            throw new NoSuchElementException();
                        }
                        throw new ConcurrentModificationException();
                    }

                    @Override // java.util.ListIterator
                    public Object previous() {
                        int i = this.expectedModCount;
                        ArrayList arrayList = ArrayList.this;
                        if (i == arrayList.modCount) {
                            int i2 = this.cursor - 1;
                            if (i2 >= 0) {
                                Object[] objArr = arrayList.elementData;
                                int i3 = i2;
                                if (i3 + i2 < objArr.length) {
                                    this.cursor = i2;
                                    this.lastRet = i2;
                                    return objArr[i3 + i2];
                                }
                                throw new ConcurrentModificationException();
                            }
                            throw new NoSuchElementException();
                        }
                        throw new ConcurrentModificationException();
                    }

                    @Override // java.util.ListIterator
                    public int previousIndex() {
                        return this.cursor - 1;
                    }

                    @Override // java.util.Iterator, java.util.ListIterator
                    public void remove() {
                        int i = this.lastRet;
                        if (i >= 0) {
                            int i2 = this.expectedModCount;
                            SubList subList = SubList.this;
                            if (i2 == ArrayList.this.modCount) {
                                try {
                                    subList.remove(i);
                                    this.cursor = this.lastRet;
                                    this.lastRet = -1;
                                    this.expectedModCount = ArrayList.this.modCount;
                                } catch (IndexOutOfBoundsException unused) {
                                    throw new ConcurrentModificationException();
                                }
                            } else {
                                throw new ConcurrentModificationException();
                            }
                        } else {
                            throw new IllegalStateException();
                        }
                    }

                    @Override // java.util.ListIterator
                    public void set(Object obj) {
                        int i = this.lastRet;
                        if (i >= 0) {
                            int i2 = this.expectedModCount;
                            ArrayList arrayList = ArrayList.this;
                            if (i2 == arrayList.modCount) {
                                try {
                                    arrayList.set(i2 + i, obj);
                                } catch (IndexOutOfBoundsException unused) {
                                    throw new ConcurrentModificationException();
                                }
                            } else {
                                throw new ConcurrentModificationException();
                            }
                        } else {
                            throw new IllegalStateException();
                        }
                    }

                    @Override // java.util.ListIterator
                    public void add(Object obj) {
                        int i = this.expectedModCount;
                        SubList subList = SubList.this;
                        if (i == ArrayList.this.modCount) {
                            try {
                                int i2 = this.cursor;
                                subList.add(i2, obj);
                                this.cursor = i2 + 1;
                                this.lastRet = -1;
                                this.expectedModCount = ArrayList.this.modCount;
                            } catch (IndexOutOfBoundsException unused) {
                                throw new ConcurrentModificationException();
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }
                };
            }
        }

        private String outOfBoundsMsg(int i) {
            return "Index: " + i + ", Size: " + this.size;
        }
    }

    @Override // java.util.List
    public void sort(Comparator comparator) {
        int i = this.modCount;
        Arrays.sort(this.elementData, 0, this.size, comparator);
        int i2 = this.modCount;
        if (i2 == i) {
            this.modCount = i2 + 1;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
