package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import sun.misc.Unsafe;

public class CopyOnWriteArrayList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
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
    public final void setArray(Object[] a) {
        this.array = a;
    }

    public CopyOnWriteArrayList() {
        setArray(new Object[0]);
    }

    public CopyOnWriteArrayList(Collection<? extends E> c) {
        Object[] elements;
        if (c.getClass() == CopyOnWriteArrayList.class) {
            elements = ((CopyOnWriteArrayList) c).getArray();
        } else {
            elements = c.toArray();
            if (elements.getClass() != Object[].class) {
                elements = Arrays.copyOf(elements, elements.length, Object[].class);
            }
        }
        setArray(elements);
    }

    public CopyOnWriteArrayList(E[] toCopyIn) {
        setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return getArray().length;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    private static int indexOf(Object o, Object[] elements, int index, int fence) {
        if (o == null) {
            for (int i = index; i < fence; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = index; i2 < fence; i2++) {
            if (o.equals(elements[i2])) {
                return i2;
            }
        }
        return -1;
    }

    private static int lastIndexOf(Object o, Object[] elements, int index) {
        if (o == null) {
            for (int i = index; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = index; i2 >= 0; i2--) {
            if (o.equals(elements[i2])) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object o) {
        Object[] elements = getArray();
        return indexOf(o, elements, 0, elements.length) >= 0;
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        Object[] elements = getArray();
        return indexOf(o, elements, 0, elements.length);
    }

    public int indexOf(E e, int index) {
        Object[] elements = getArray();
        return indexOf(e, elements, index, elements.length);
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        Object[] elements = getArray();
        return lastIndexOf(o, elements, elements.length - 1);
    }

    public int lastIndexOf(E e, int index) {
        return lastIndexOf(e, getArray(), index);
    }

    public Object clone() {
        try {
            CopyOnWriteArrayList<E> clone = (CopyOnWriteArrayList) super.clone();
            clone.resetLock();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] elements = getArray();
        return Arrays.copyOf(elements, elements.length);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] a) {
        Object[] elements = getArray();
        int len = elements.length;
        if (a.length < len) {
            return (T[]) Arrays.copyOf(elements, len, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, len);
        if (a.length > len) {
            a[len] = null;
        }
        return a;
    }

    private E get(Object[] a, int index) {
        return (E) a[index];
    }

    static String outOfBounds(int index, int size) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override // java.util.List
    public E get(int index) {
        return get(getArray(), index);
    }

    @Override // java.util.List
    public E set(int index, E element) {
        E oldValue;
        synchronized (this.lock) {
            Object[] elements = getArray();
            oldValue = get(elements, index);
            if (oldValue != element) {
                Object[] newElements = Arrays.copyOf(elements, elements.length);
                newElements[index] = element;
                setArray(newElements);
            } else {
                setArray(elements);
            }
        }
        return oldValue;
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
        }
        return true;
    }

    @Override // java.util.List
    public void add(int index, E element) {
        Object[] newElements;
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            if (index > len || index < 0) {
                throw new IndexOutOfBoundsException(outOfBounds(index, len));
            }
            int numMoved = len - index;
            if (numMoved == 0) {
                newElements = Arrays.copyOf(elements, len + 1);
            } else {
                newElements = new Object[(len + 1)];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index, newElements, index + 1, numMoved);
            }
            newElements[index] = element;
            setArray(newElements);
        }
    }

    @Override // java.util.List
    public E remove(int index) {
        E oldValue;
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            oldValue = get(elements, index);
            int numMoved = (len - index) - 1;
            if (numMoved == 0) {
                setArray(Arrays.copyOf(elements, len - 1));
            } else {
                Object[] newElements = new Object[(len - 1)];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index + 1, newElements, index, numMoved);
                setArray(newElements);
            }
        }
        return oldValue;
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object o) {
        Object[] snapshot = getArray();
        int index = indexOf(o, snapshot, 0, snapshot.length);
        if (index < 0) {
            return false;
        }
        return remove(o, snapshot, index);
    }

    private boolean remove(Object o, Object[] snapshot, int index) {
        synchronized (this.lock) {
            Object[] current = getArray();
            int len = current.length;
            if (snapshot != current) {
                int prefix = Math.min(index, len);
                int i = 0;
                while (true) {
                    if (i < prefix) {
                        if (current[i] != snapshot[i] && Objects.equals(o, current[i])) {
                            index = i;
                            break;
                        }
                        i++;
                    } else if (index >= len) {
                        return false;
                    } else {
                        if (current[index] != o) {
                            index = indexOf(o, current, index, len);
                            if (index < 0) {
                                return false;
                            }
                        }
                    }
                }
            }
            Object[] newElements = new Object[(len - 1)];
            System.arraycopy(current, 0, newElements, 0, index);
            System.arraycopy(current, index + 1, newElements, index, (len - index) - 1);
            setArray(newElements);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeRange(int fromIndex, int toIndex) {
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            if (fromIndex < 0 || toIndex > len || toIndex < fromIndex) {
                throw new IndexOutOfBoundsException();
            }
            int newlen = len - (toIndex - fromIndex);
            int numMoved = len - toIndex;
            if (numMoved == 0) {
                setArray(Arrays.copyOf(elements, newlen));
            } else {
                Object[] newElements = new Object[newlen];
                System.arraycopy(elements, 0, newElements, 0, fromIndex);
                System.arraycopy(elements, toIndex, newElements, fromIndex, numMoved);
                setArray(newElements);
            }
        }
    }

    public boolean addIfAbsent(E e) {
        Object[] snapshot = getArray();
        if (indexOf(e, snapshot, 0, snapshot.length) >= 0) {
            return false;
        }
        return addIfAbsent(e, snapshot);
    }

    private boolean addIfAbsent(E e, Object[] snapshot) {
        synchronized (this.lock) {
            Object[] current = getArray();
            int len = current.length;
            if (snapshot != current) {
                int common = Math.min(snapshot.length, len);
                for (int i = 0; i < common; i++) {
                    if (current[i] != snapshot[i] && Objects.equals(e, current[i])) {
                        return false;
                    }
                }
                if (indexOf(e, current, common, len) >= 0) {
                    return false;
                }
            }
            Object[] newElements = Arrays.copyOf(current, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        Object[] elements = getArray();
        int len = elements.length;
        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            if (indexOf(it.next(), elements, 0, len) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        if (c != null) {
            synchronized (this.lock) {
                Object[] elements = getArray();
                int len = elements.length;
                if (len != 0) {
                    int newlen = 0;
                    Object[] temp = new Object[len];
                    for (Object element : elements) {
                        if (!c.contains(element)) {
                            temp[newlen] = element;
                            newlen++;
                        }
                    }
                    if (newlen != len) {
                        setArray(Arrays.copyOf(temp, newlen));
                        return true;
                    }
                }
                return false;
            }
        }
        throw new NullPointerException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        if (c != null) {
            synchronized (this.lock) {
                Object[] elements = getArray();
                int len = elements.length;
                if (len != 0) {
                    int newlen = 0;
                    Object[] temp = new Object[len];
                    for (Object element : elements) {
                        if (c.contains(element)) {
                            temp[newlen] = element;
                            newlen++;
                        }
                    }
                    if (newlen != len) {
                        setArray(Arrays.copyOf(temp, newlen));
                        return true;
                    }
                }
                return false;
            }
        }
        throw new NullPointerException();
    }

    public int addAllAbsent(Collection<? extends E> c) {
        int added;
        Object[] cs = c.toArray();
        if (cs.length == 0) {
            return 0;
        }
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            added = 0;
            for (Object e : cs) {
                if (indexOf(e, elements, 0, len) < 0 && indexOf(e, cs, 0, added) < 0) {
                    cs[added] = e;
                    added++;
                }
            }
            if (added > 0) {
                Object[] newElements = Arrays.copyOf(elements, len + added);
                System.arraycopy(cs, 0, newElements, len, added);
                setArray(newElements);
            }
        }
        return added;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        synchronized (this.lock) {
            setArray(new Object[0]);
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        Object[] cs = c.getClass() == CopyOnWriteArrayList.class ? ((CopyOnWriteArrayList) c).getArray() : c.toArray();
        if (cs.length == 0) {
            return false;
        }
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            if (len == 0 && cs.getClass() == Object[].class) {
                setArray(cs);
            } else {
                Object[] newElements = Arrays.copyOf(elements, cs.length + len);
                System.arraycopy(cs, 0, newElements, len, cs.length);
                setArray(newElements);
            }
        }
        return true;
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] newElements;
        Object[] cs = c.toArray();
        synchronized (this.lock) {
            Object[] elements = getArray();
            int len = elements.length;
            if (index > len || index < 0) {
                throw new IndexOutOfBoundsException(outOfBounds(index, len));
            } else if (cs.length == 0) {
                return false;
            } else {
                int numMoved = len - index;
                if (numMoved == 0) {
                    newElements = Arrays.copyOf(elements, cs.length + len);
                } else {
                    newElements = new Object[(cs.length + len)];
                    System.arraycopy(elements, 0, newElements, 0, index);
                    System.arraycopy(elements, index, newElements, cs.length + index, numMoved);
                }
                System.arraycopy(cs, 0, newElements, index, cs.length);
                setArray(newElements);
                return true;
            }
        }
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> action) {
        if (action != null) {
            for (Object x : getArray()) {
                action.accept(x);
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        Object[] objArr;
        if (filter != null) {
            synchronized (this.lock) {
                Object[] elements = getArray();
                int len = elements.length;
                for (int i = 0; i < len; i++) {
                    if (filter.test(elements[i])) {
                        int newlen = i;
                        Object[] newElements = new Object[(len - 1)];
                        System.arraycopy(elements, 0, newElements, 0, newlen);
                        for (int i2 = i + 1; i2 < len; i2++) {
                            Object obj = elements[i2];
                            if (!filter.test(obj)) {
                                newElements[newlen] = obj;
                                newlen++;
                            }
                        }
                        if (newlen == len - 1) {
                            objArr = newElements;
                        } else {
                            objArr = Arrays.copyOf(newElements, newlen);
                        }
                        setArray(objArr);
                        return true;
                    }
                }
                return false;
            }
        }
        throw new NullPointerException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: java.util.function.UnaryOperator<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public void replaceAll(UnaryOperator<E> operator) {
        if (operator != 0) {
            synchronized (this.lock) {
                Object[] elements = getArray();
                int len = elements.length;
                Object[] newElements = Arrays.copyOf(elements, len);
                for (int i = 0; i < len; i++) {
                    newElements[i] = operator.apply(elements[i]);
                }
                setArray(newElements);
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.List
    public void sort(Comparator<? super E> c) {
        synchronized (this.lock) {
            Object[] elements = getArray();
            Object[] newElements = Arrays.copyOf(elements, elements.length);
            Arrays.sort(newElements, c);
            setArray(newElements);
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        Object[] elements = getArray();
        s.writeInt(elements.length);
        for (Object element : elements) {
            s.writeObject(element);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        resetLock();
        int len = s.readInt();
        Object[] elements = new Object[len];
        for (int i = 0; i < len; i++) {
            elements[i] = s.readObject();
        }
        setArray(elements);
    }

    public String toString() {
        return Arrays.toString(getArray());
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }
        Iterator<?> it = ((List) o).iterator();
        for (Object obj : getArray()) {
            if (!it.hasNext() || !Objects.equals(obj, it.next())) {
                return false;
            }
        }
        if (it.hasNext()) {
            return false;
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        Object[] array2 = getArray();
        int length = array2.length;
        int hashCode = 1;
        for (int hashCode2 = 0; hashCode2 < length; hashCode2++) {
            Object x = array2[hashCode2];
            hashCode = (hashCode * 31) + (x == null ? 0 : x.hashCode());
        }
        return hashCode;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new COWIterator(getArray(), 0);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new COWIterator(getArray(), 0);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        Object[] elements = getArray();
        int len = elements.length;
        if (index >= 0 && index <= len) {
            return new COWIterator(elements, index);
        }
        throw new IndexOutOfBoundsException(outOfBounds(index, len));
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(getArray(), 1040);
    }

    /* access modifiers changed from: package-private */
    public static final class COWIterator<E> implements ListIterator<E> {
        private int cursor;
        private final Object[] snapshot;

        COWIterator(Object[] elements, int initialCursor) {
            this.cursor = initialCursor;
            this.snapshot = elements;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return this.cursor < this.snapshot.length;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.cursor > 0;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public E next() {
            if (hasNext()) {
                Object[] objArr = this.snapshot;
                int i = this.cursor;
                this.cursor = i + 1;
                return (E) objArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (hasPrevious()) {
                Object[] objArr = this.snapshot;
                int i = this.cursor - 1;
                this.cursor = i;
                return (E) objArr[i];
            }
            throw new NoSuchElementException();
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
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            int size = this.snapshot.length;
            for (int i = this.cursor; i < size; i++) {
                action.accept(this.snapshot[i]);
            }
            this.cursor = size;
        }
    }

    @Override // java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        COWSubList cOWSubList;
        synchronized (this.lock) {
            int len = getArray().length;
            if (fromIndex < 0 || toIndex > len || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException();
            }
            cOWSubList = new COWSubList(this, fromIndex, toIndex);
        }
        return cOWSubList;
    }

    private static class COWSubList<E> extends AbstractList<E> implements RandomAccess {
        private Object[] expectedArray = this.l.getArray();
        private final CopyOnWriteArrayList<E> l;
        private final int offset;
        private int size;

        COWSubList(CopyOnWriteArrayList<E> list, int fromIndex, int toIndex) {
            this.l = list;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        private void checkForComodification() {
            if (this.l.getArray() != this.expectedArray) {
                throw new ConcurrentModificationException();
            }
        }

        private void rangeCheck(int index) {
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException(CopyOnWriteArrayList.outOfBounds(index, this.size));
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public E set(int index, E element) {
            E x;
            synchronized (this.l.lock) {
                rangeCheck(index);
                checkForComodification();
                x = this.l.set(this.offset + index, element);
                this.expectedArray = this.l.getArray();
            }
            return x;
        }

        @Override // java.util.List, java.util.AbstractList
        public E get(int index) {
            E e;
            synchronized (this.l.lock) {
                rangeCheck(index);
                checkForComodification();
                e = this.l.get(this.offset + index);
            }
            return e;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            int i;
            synchronized (this.l.lock) {
                checkForComodification();
                i = this.size;
            }
            return i;
        }

        @Override // java.util.List, java.util.AbstractList
        public void add(int index, E element) {
            synchronized (this.l.lock) {
                checkForComodification();
                if (index < 0 || index > this.size) {
                    throw new IndexOutOfBoundsException(CopyOnWriteArrayList.outOfBounds(index, this.size));
                }
                this.l.add(this.offset + index, element);
                this.expectedArray = this.l.getArray();
                this.size++;
            }
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
        public void clear() {
            synchronized (this.l.lock) {
                checkForComodification();
                this.l.removeRange(this.offset, this.offset + this.size);
                this.expectedArray = this.l.getArray();
                this.size = 0;
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public E remove(int index) {
            E result;
            synchronized (this.l.lock) {
                rangeCheck(index);
                checkForComodification();
                result = this.l.remove(this.offset + index);
                this.expectedArray = this.l.getArray();
                this.size--;
            }
            return result;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean remove(Object o) {
            int index = indexOf(o);
            if (index == -1) {
                return false;
            }
            remove(index);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator<E> iterator() {
            COWSubListIterator cOWSubListIterator;
            synchronized (this.l.lock) {
                checkForComodification();
                cOWSubListIterator = new COWSubListIterator(this.l, 0, this.offset, this.size);
            }
            return cOWSubListIterator;
        }

        @Override // java.util.List, java.util.AbstractList
        public ListIterator<E> listIterator(int index) {
            COWSubListIterator cOWSubListIterator;
            synchronized (this.l.lock) {
                checkForComodification();
                if (index < 0 || index > this.size) {
                    throw new IndexOutOfBoundsException(CopyOnWriteArrayList.outOfBounds(index, this.size));
                }
                cOWSubListIterator = new COWSubListIterator(this.l, index, this.offset, this.size);
            }
            return cOWSubListIterator;
        }

        @Override // java.util.List, java.util.AbstractList
        public List<E> subList(int fromIndex, int toIndex) {
            COWSubList cOWSubList;
            synchronized (this.l.lock) {
                checkForComodification();
                if (fromIndex < 0 || toIndex > this.size || fromIndex > toIndex) {
                    throw new IndexOutOfBoundsException();
                }
                cOWSubList = new COWSubList(this.l, this.offset + fromIndex, this.offset + toIndex);
            }
            return cOWSubList;
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> action) {
            if (action != null) {
                int lo = this.offset;
                int hi = this.offset + this.size;
                Object[] a = this.expectedArray;
                if (this.l.getArray() != a) {
                    throw new ConcurrentModificationException();
                } else if (lo < 0 || hi > a.length) {
                    throw new IndexOutOfBoundsException();
                } else {
                    for (int i = lo; i < hi; i++) {
                        action.accept(a[i]);
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: java.util.function.UnaryOperator<E> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.List
        public void replaceAll(UnaryOperator<E> operator) {
            if (operator != 0) {
                synchronized (this.l.lock) {
                    int lo = this.offset;
                    int hi = this.offset + this.size;
                    Object[] elements = this.expectedArray;
                    if (this.l.getArray() == elements) {
                        int len = elements.length;
                        if (lo < 0 || hi > len) {
                            throw new IndexOutOfBoundsException();
                        }
                        Object[] newElements = Arrays.copyOf(elements, len);
                        for (int i = lo; i < hi; i++) {
                            newElements[i] = operator.apply(elements[i]);
                        }
                        CopyOnWriteArrayList<E> copyOnWriteArrayList = this.l;
                        this.expectedArray = newElements;
                        copyOnWriteArrayList.setArray(newElements);
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.List
        public void sort(Comparator<? super E> c) {
            synchronized (this.l.lock) {
                int lo = this.offset;
                int hi = this.offset + this.size;
                Object[] elements = this.expectedArray;
                if (this.l.getArray() == elements) {
                    int len = elements.length;
                    if (lo < 0 || hi > len) {
                        throw new IndexOutOfBoundsException();
                    }
                    Object[] newElements = Arrays.copyOf(elements, len);
                    Arrays.sort(newElements, lo, hi, c);
                    CopyOnWriteArrayList<E> copyOnWriteArrayList = this.l;
                    this.expectedArray = newElements;
                    copyOnWriteArrayList.setArray(newElements);
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean removeAll(Collection<?> c) {
            if (c != null) {
                boolean removed = false;
                synchronized (this.l.lock) {
                    int n = this.size;
                    if (n > 0) {
                        int lo = this.offset;
                        int hi = this.offset + n;
                        Object[] elements = this.expectedArray;
                        if (this.l.getArray() == elements) {
                            int len = elements.length;
                            if (lo < 0 || hi > len) {
                                throw new IndexOutOfBoundsException();
                            }
                            int newSize = 0;
                            Object[] temp = new Object[n];
                            for (int i = lo; i < hi; i++) {
                                Object element = elements[i];
                                if (!c.contains(element)) {
                                    temp[newSize] = element;
                                    newSize++;
                                }
                            }
                            if (newSize != n) {
                                Object[] newElements = new Object[((len - n) + newSize)];
                                System.arraycopy(elements, 0, newElements, 0, lo);
                                System.arraycopy(temp, 0, newElements, lo, newSize);
                                System.arraycopy(elements, hi, newElements, lo + newSize, len - hi);
                                this.size = newSize;
                                removed = true;
                                CopyOnWriteArrayList<E> copyOnWriteArrayList = this.l;
                                this.expectedArray = newElements;
                                copyOnWriteArrayList.setArray(newElements);
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }
                }
                return removed;
            }
            throw new NullPointerException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean retainAll(Collection<?> c) {
            if (c != null) {
                boolean removed = false;
                synchronized (this.l.lock) {
                    int n = this.size;
                    if (n > 0) {
                        int lo = this.offset;
                        int hi = this.offset + n;
                        Object[] elements = this.expectedArray;
                        if (this.l.getArray() == elements) {
                            int len = elements.length;
                            if (lo < 0 || hi > len) {
                                throw new IndexOutOfBoundsException();
                            }
                            int newSize = 0;
                            Object[] temp = new Object[n];
                            for (int i = lo; i < hi; i++) {
                                Object element = elements[i];
                                if (c.contains(element)) {
                                    temp[newSize] = element;
                                    newSize++;
                                }
                            }
                            if (newSize != n) {
                                Object[] newElements = new Object[((len - n) + newSize)];
                                System.arraycopy(elements, 0, newElements, 0, lo);
                                System.arraycopy(temp, 0, newElements, lo, newSize);
                                System.arraycopy(elements, hi, newElements, lo + newSize, len - hi);
                                this.size = newSize;
                                removed = true;
                                CopyOnWriteArrayList<E> copyOnWriteArrayList = this.l;
                                this.expectedArray = newElements;
                                copyOnWriteArrayList.setArray(newElements);
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }
                }
                return removed;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            if (filter != null) {
                boolean removed = false;
                synchronized (this.l.lock) {
                    int n = this.size;
                    if (n > 0) {
                        int lo = this.offset;
                        int hi = this.offset + n;
                        Object[] elements = this.expectedArray;
                        if (this.l.getArray() == elements) {
                            int len = elements.length;
                            if (lo < 0 || hi > len) {
                                throw new IndexOutOfBoundsException();
                            }
                            int newSize = 0;
                            Object[] temp = new Object[n];
                            for (int i = lo; i < hi; i++) {
                                Object obj = elements[i];
                                if (!filter.test(obj)) {
                                    temp[newSize] = obj;
                                    newSize++;
                                }
                            }
                            if (newSize != n) {
                                Object[] newElements = new Object[((len - n) + newSize)];
                                System.arraycopy(elements, 0, newElements, 0, lo);
                                System.arraycopy(temp, 0, newElements, lo, newSize);
                                System.arraycopy(elements, hi, newElements, lo + newSize, len - hi);
                                this.size = newSize;
                                removed = true;
                                CopyOnWriteArrayList<E> copyOnWriteArrayList = this.l;
                                this.expectedArray = newElements;
                                copyOnWriteArrayList.setArray(newElements);
                            }
                        } else {
                            throw new ConcurrentModificationException();
                        }
                    }
                }
                return removed;
            }
            throw new NullPointerException();
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Spliterator<E> spliterator() {
            int lo = this.offset;
            int hi = this.offset + this.size;
            Object[] a = this.expectedArray;
            if (this.l.getArray() != a) {
                throw new ConcurrentModificationException();
            } else if (lo >= 0 && hi <= a.length) {
                return Spliterators.spliterator(a, lo, hi, 1040);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private static class COWSubListIterator<E> implements ListIterator<E> {
        private final ListIterator<E> it;
        private final int offset;
        private final int size;

        COWSubListIterator(List<E> l, int index, int offset2, int size2) {
            this.offset = offset2;
            this.size = size2;
            this.it = l.listIterator(index + offset2);
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return nextIndex() < this.size;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public E next() {
            if (hasNext()) {
                return this.it.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (hasPrevious()) {
                return this.it.previous();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.it.nextIndex() - this.offset;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.it.previousIndex() - this.offset;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (nextIndex() < this.size) {
                action.accept(this.it.next());
            }
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
