package java.util;

import java.lang.reflect.Array;

public abstract class AbstractCollection implements Collection {
    @Override // java.util.Collection, java.lang.Iterable
    public abstract Iterator iterator();

    @Override // java.util.Collection
    public abstract int size();

    protected AbstractCollection() {
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        Iterator it = iterator();
        if (obj == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        Iterator it = iterator();
        for (int i = 0; i < objArr.length; i++) {
            if (!it.hasNext()) {
                return Arrays.copyOf(objArr, i);
            }
            objArr[i] = it.next();
        }
        return it.hasNext() ? finishToArray(objArr, it) : objArr;
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        Object[] objArr2;
        int size = size();
        if (objArr.length >= size) {
            objArr2 = objArr;
        } else {
            objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
        }
        Iterator it = iterator();
        for (int i = 0; i < objArr2.length; i++) {
            if (!it.hasNext()) {
                if (objArr == objArr2) {
                    objArr2[i] = null;
                } else if (objArr.length < i) {
                    return Arrays.copyOf(objArr2, i);
                } else {
                    System.arraycopy(objArr2, 0, objArr, 0, i);
                    if (objArr.length > i) {
                        objArr[i] = null;
                    }
                }
                return objArr;
            }
            objArr2[i] = it.next();
        }
        return it.hasNext() ? finishToArray(objArr2, it) : objArr2;
    }

    private static Object[] finishToArray(Object[] objArr, Iterator it) {
        int length = objArr.length;
        while (it.hasNext()) {
            int length2 = objArr.length;
            if (length == length2) {
                int i = (length2 >> 1) + length2 + 1;
                if (i - 2147483639 > 0) {
                    i = hugeCapacity(length2 + 1);
                }
                objArr = Arrays.copyOf(objArr, i);
            }
            objArr[length] = it.next();
            length++;
        }
        return length == objArr.length ? objArr : Arrays.copyOf(objArr, length);
    }

    private static int hugeCapacity(int i) {
        if (i >= 0) {
            return i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        throw new OutOfMemoryError("Required array size too large");
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        Iterator it = iterator();
        if (obj == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        boolean z = false;
        for (Object obj : collection) {
            if (add(obj)) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        Objects.requireNonNull(collection);
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        Objects.requireNonNull(collection);
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public void clear() {
        Iterator it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public String toString() {
        Iterator it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (true) {
            Object next = it.next();
            if (next == this) {
                next = "(this Collection)";
            }
            sb.append(next);
            if (!it.hasNext()) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
        }
    }
}
