package java.util.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import sun.misc.Unsafe;

public class CopyOnWriteArrayList implements List, RandomAccess, Cloneable, Serializable {
    private static final long LOCK;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = 8673264195747942595L;
    private volatile transient Object[] array;
    final transient Object lock = new Object();

    /* access modifiers changed from: package-private */
    public final Object[] getArray() {
        return this.array;
    }

    /* access modifiers changed from: package-private */
    public final void setArray(Object[] objArr) {
        this.array = objArr;
    }

    public CopyOnWriteArrayList() {
        setArray(new Object[0]);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return getArray().length;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    private static int indexOf(Object obj, Object[] objArr, int i, int i2) {
        if (obj == null) {
            while (i < i2) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < i2) {
            if (obj.equals(objArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        Object[] array2 = getArray();
        return indexOf(obj, array2, 0, array2.length) >= 0;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Object[] array2 = getArray();
        return indexOf(obj, array2, 0, array2.length);
    }

    public Object clone() {
        try {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) super.clone();
            copyOnWriteArrayList.resetLock();
            return copyOnWriteArrayList;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] array2 = getArray();
        return Arrays.copyOf(array2, array2.length);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        Object[] array2 = getArray();
        int length = array2.length;
        if (objArr.length < length) {
            return Arrays.copyOf(array2, length, objArr.getClass());
        }
        System.arraycopy(array2, 0, objArr, 0, length);
        if (objArr.length > length) {
            objArr[length] = null;
        }
        return objArr;
    }

    private Object get(Object[] objArr, int i) {
        return objArr[i];
    }

    static String outOfBounds(int i, int i2) {
        return "Index: " + i + ", Size: " + i2;
    }

    @Override // java.util.List
    public Object get(int i) {
        return get(getArray(), i);
    }

    @Override // java.util.List
    public Object set(int i, Object obj) {
        Object obj2;
        synchronized (this.lock) {
            Object[] array2 = getArray();
            obj2 = get(array2, i);
            if (obj2 != obj) {
                Object[] copyOf = Arrays.copyOf(array2, array2.length);
                copyOf[i] = obj;
                setArray(copyOf);
            } else {
                setArray(array2);
            }
        }
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        synchronized (this.lock) {
            Object[] array2 = getArray();
            int length = array2.length;
            Object[] copyOf = Arrays.copyOf(array2, length + 1);
            copyOf[length] = obj;
            setArray(copyOf);
        }
        return true;
    }

    @Override // java.util.List
    public void add(int i, Object obj) {
        Object[] objArr;
        synchronized (this.lock) {
            Object[] array2 = getArray();
            int length = array2.length;
            if (i > length || i < 0) {
                throw new IndexOutOfBoundsException(outOfBounds(i, length));
            }
            int i2 = length - i;
            if (i2 == 0) {
                objArr = Arrays.copyOf(array2, length + 1);
            } else {
                Object[] objArr2 = new Object[(length + 1)];
                System.arraycopy(array2, 0, objArr2, 0, i);
                System.arraycopy(array2, i, objArr2, i + 1, i2);
                objArr = objArr2;
            }
            objArr[i] = obj;
            setArray(objArr);
        }
    }

    @Override // java.util.List
    public Object remove(int i) {
        Object obj;
        synchronized (this.lock) {
            Object[] array2 = getArray();
            int length = array2.length;
            obj = get(array2, i);
            int i2 = (length - i) - 1;
            if (i2 == 0) {
                setArray(Arrays.copyOf(array2, length - 1));
            } else {
                Object[] objArr = new Object[(length - 1)];
                System.arraycopy(array2, 0, objArr, 0, i);
                System.arraycopy(array2, i + 1, objArr, i, i2);
                setArray(objArr);
            }
        }
        return obj;
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        Object[] array2 = getArray();
        int indexOf = indexOf(obj, array2, 0, array2.length);
        if (indexOf < 0) {
            return false;
        }
        return remove(obj, array2, indexOf);
    }

    private boolean remove(Object obj, Object[] objArr, int i) {
        synchronized (this.lock) {
            Object[] array2 = getArray();
            int length = array2.length;
            if (objArr != array2) {
                int min = Math.min(i, length);
                int i2 = 0;
                while (true) {
                    if (i2 < min) {
                        if (array2[i2] != objArr[i2] && Objects.equals(obj, array2[i2])) {
                            i = i2;
                            break;
                        }
                        i2++;
                    } else if (i >= length) {
                        return false;
                    } else {
                        if (array2[i] != obj) {
                            i = indexOf(obj, array2, i, length);
                            if (i < 0) {
                                return false;
                            }
                        }
                    }
                }
            }
            Object[] objArr2 = new Object[(length - 1)];
            System.arraycopy(array2, 0, objArr2, 0, i);
            System.arraycopy(array2, i + 1, objArr2, i, (length - i) - 1);
            setArray(objArr2);
            return true;
        }
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        Object[] array2 = getArray();
        int length = array2.length;
        for (Object obj : collection) {
            if (indexOf(obj, array2, 0, length) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        if (collection != null) {
            synchronized (this.lock) {
                Object[] array2 = getArray();
                int length = array2.length;
                if (length != 0) {
                    Object[] objArr = new Object[length];
                    int i = 0;
                    for (Object obj : array2) {
                        if (!collection.contains(obj)) {
                            objArr[i] = obj;
                            i++;
                        }
                    }
                    if (i != length) {
                        setArray(Arrays.copyOf(objArr, i));
                        return true;
                    }
                }
                return false;
            }
        }
        throw new NullPointerException();
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        if (collection != null) {
            synchronized (this.lock) {
                Object[] array2 = getArray();
                int length = array2.length;
                if (length != 0) {
                    Object[] objArr = new Object[length];
                    int i = 0;
                    for (Object obj : array2) {
                        if (collection.contains(obj)) {
                            objArr[i] = obj;
                            i++;
                        }
                    }
                    if (i != length) {
                        setArray(Arrays.copyOf(objArr, i));
                        return true;
                    }
                }
                return false;
            }
        }
        throw new NullPointerException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        synchronized (this.lock) {
            setArray(new Object[0]);
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        Object[] array2 = collection.getClass() == CopyOnWriteArrayList.class ? ((CopyOnWriteArrayList) collection).getArray() : collection.toArray();
        if (array2.length == 0) {
            return false;
        }
        synchronized (this.lock) {
            Object[] array3 = getArray();
            int length = array3.length;
            if (length == 0 && array2.getClass() == Object[].class) {
                setArray(array2);
            } else {
                Object[] copyOf = Arrays.copyOf(array3, array2.length + length);
                System.arraycopy(array2, 0, copyOf, length, array2.length);
                setArray(copyOf);
            }
        }
        return true;
    }

    @Override // java.util.List
    public void sort(Comparator comparator) {
        synchronized (this.lock) {
            Object[] array2 = getArray();
            Object[] copyOf = Arrays.copyOf(array2, array2.length);
            Arrays.sort(copyOf, comparator);
            setArray(copyOf);
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

    public String toString() {
        return Arrays.toString(getArray());
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Iterator it = ((List) obj).iterator();
        for (Object obj2 : getArray()) {
            if (!it.hasNext() || !Objects.equals(obj2, it.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        Object[] array2 = getArray();
        int i = 1;
        for (Object obj : array2) {
            i = (i * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return i;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new COWIterator(getArray(), 0);
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return new COWIterator(getArray(), 0);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i) {
        Object[] array2 = getArray();
        int length = array2.length;
        if (i >= 0 && i <= length) {
            return new COWIterator(array2, i);
        }
        throw new IndexOutOfBoundsException(outOfBounds(i, length));
    }

    static final class COWIterator implements ListIterator {
        private int cursor;
        private final Object[] snapshot;

        COWIterator(Object[] objArr, int i) {
            this.cursor = i;
            this.snapshot = objArr;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return this.cursor < this.snapshot.length;
        }

        public boolean hasPrevious() {
            return this.cursor > 0;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public Object next() {
            if (hasNext()) {
                Object[] objArr = this.snapshot;
                int i = this.cursor;
                this.cursor = i + 1;
                return objArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public Object previous() {
            if (hasPrevious()) {
                Object[] objArr = this.snapshot;
                int i = this.cursor - 1;
                this.cursor = i;
                return objArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    private void resetLock() {
        U.putObjectVolatile(this, LOCK, new Object());
    }

    static {
        try {
            LOCK = U.objectFieldOffset(CopyOnWriteArrayList.class.getDeclaredField("lock"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
