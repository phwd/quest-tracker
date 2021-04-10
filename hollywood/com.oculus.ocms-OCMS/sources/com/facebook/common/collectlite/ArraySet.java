package com.facebook.common.collectlite;

import androidx.collection.SimpleArrayMap;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.Nullable;

public class ArraySet<E> implements Set<E> {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final Integer SENTINEL_MAP_VALUE = 0;
    private final SimpleArrayMap<E, Integer> mMap;

    public ArraySet() {
        this.mMap = new SimpleArrayMap<>();
    }

    public ArraySet(int i) {
        this.mMap = new SimpleArrayMap<>(i);
    }

    public ArraySet(@Nullable Collection<? extends E> collection) {
        this.mMap = new SimpleArrayMap<>();
        if (collection != null) {
            addAll(collection);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e) {
        return this.mMap.put(e, SENTINEL_MAP_VALUE) == null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.facebook.common.collectlite.ArraySet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        ensureCapacity(size() + collection.size());
        boolean z = false;
        if (collection instanceof ArraySet) {
            int size = size();
            this.mMap.putAll(((ArraySet) collection).mMap);
            if (size() != size) {
                return true;
            }
            return false;
        } else if (!(collection instanceof List) || !(collection instanceof RandomAccess)) {
            Iterator<? extends E> it = collection.iterator();
            while (it.hasNext()) {
                z |= add(it.next());
            }
            return z;
        } else {
            List list = (List) collection;
            int size2 = list.size();
            boolean z2 = false;
            for (int i = 0; i < size2; i++) {
                z2 |= add(list.get(i));
            }
            return z2;
        }
    }

    public void clear() {
        this.mMap.clear();
    }

    public void clearAndAddAll(ArraySet<? extends E> arraySet) {
        clear();
        addAll(arraySet);
    }

    public boolean contains(Object obj) {
        return this.mMap.containsKey(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        if (!(collection instanceof List) || !(collection instanceof RandomAccess)) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }
        List list = (List) collection;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!contains(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void ensureCapacity(int i) {
        this.mMap.ensureCapacity(i);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            try {
                int size = size();
                for (int i = 0; i < size; i++) {
                    if (!set.contains(valueAt(i))) {
                        return false;
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            E valueAt = valueAt(i2);
            if (valueAt != null) {
                i += valueAt.hashCode();
            }
        }
        return i;
    }

    public int indexOf(E e) {
        return this.mMap.indexOfKey(e);
    }

    public boolean isEmpty() {
        return this.mMap.isEmpty();
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return new ArraySetIterator();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        boolean z;
        if (!(collection instanceof List) || !(collection instanceof RandomAccess)) {
            Iterator<?> it = collection.iterator();
            z = false;
            while (it.hasNext()) {
                z |= remove(it.next());
            }
        } else {
            List list = (List) collection;
            int size = list.size();
            z = false;
            for (int i = 0; i < size; i++) {
                z |= remove(list.get(i));
            }
        }
        return z;
    }

    public E removeAt(int i) {
        E keyAt = this.mMap.keyAt(i);
        this.mMap.removeAt(i);
        return keyAt;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int size = size() - 1; size >= 0; size--) {
            if (!collection.contains(valueAt(size))) {
                removeAt(size);
                z = true;
            }
        }
        return z;
    }

    public int size() {
        return this.mMap.size();
    }

    public Object[] toArray() {
        int size = this.mMap.size();
        if (size == 0) {
            return EMPTY_OBJECT_ARRAY;
        }
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = this.mMap.keyAt(i);
        }
        return objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v9, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        for (int i = 0; i < size; i++) {
            tArr[i] = valueAt(i);
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        int size = size();
        StringBuilder sb = new StringBuilder(size * 14);
        sb.append('{');
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            E valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append((Object) valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public E valueAt(int i) {
        return this.mMap.keyAt(i);
    }

    private final class ArraySetIterator implements Iterator<E> {
        private int mIndex = -1;
        private boolean mRemoved;

        public ArraySetIterator() {
        }

        public boolean hasNext() {
            return this.mIndex + 1 < ArraySet.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                this.mRemoved = false;
                this.mIndex++;
                return (E) ArraySet.this.valueAt(this.mIndex);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (!this.mRemoved) {
                ArraySet.this.removeAt(this.mIndex);
                this.mRemoved = true;
                this.mIndex--;
                return;
            }
            throw new IllegalStateException();
        }
    }
}
