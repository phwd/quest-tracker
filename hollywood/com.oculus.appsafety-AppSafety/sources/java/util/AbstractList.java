package java.util;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    protected transient int modCount = 0;

    @Override // java.util.List
    public abstract E get(int i);

    protected AbstractList() {
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override // java.util.List
    public E set(int index, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int index, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        ListIterator<E> it = listIterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return it.previousIndex();
                }
            }
            return -1;
        }
        while (it.hasNext()) {
            if (o.equals(it.next())) {
                return it.previousIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        ListIterator<E> it = listIterator(size());
        if (o == null) {
            while (it.hasPrevious()) {
                if (it.previous() == null) {
                    return it.nextIndex();
                }
            }
            return -1;
        }
        while (it.hasPrevious()) {
            if (o.equals(it.previous())) {
                return it.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public void clear() {
        removeRange(0, size());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.AbstractList<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        boolean modified = false;
        Iterator<? extends E> it = c.iterator();
        while (it.hasNext()) {
            add(index, it.next());
            modified = true;
            index++;
        }
        return modified;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);
        return new ListItr(index);
    }

    /* access modifiers changed from: private */
    public class Itr implements Iterator<E> {
        int cursor;
        int expectedModCount;
        int lastRet;

        private Itr() {
            this.cursor = 0;
            this.lastRet = -1;
            this.expectedModCount = AbstractList.this.modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor != AbstractList.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            checkForComodification();
            try {
                int i = this.cursor;
                E next = (E) AbstractList.this.get(i);
                this.lastRet = i;
                this.cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastRet >= 0) {
                checkForComodification();
                try {
                    AbstractList.this.remove(this.lastRet);
                    if (this.lastRet < this.cursor) {
                        this.cursor--;
                    }
                    this.lastRet = -1;
                    this.expectedModCount = AbstractList.this.modCount;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        /* access modifiers changed from: package-private */
        public final void checkForComodification() {
            if (AbstractList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* access modifiers changed from: private */
    public class ListItr extends AbstractList<E>.Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            this.cursor = index;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override // java.util.ListIterator
        public E previous() {
            checkForComodification();
            try {
                int i = this.cursor - 1;
                E previous = (E) AbstractList.this.get(i);
                this.cursor = i;
                this.lastRet = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
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

        @Override // java.util.ListIterator
        public void set(E e) {
            if (this.lastRet >= 0) {
                checkForComodification();
                try {
                    AbstractList.this.set(this.lastRet, e);
                    this.expectedModCount = AbstractList.this.modCount;
                } catch (IndexOutOfBoundsException e2) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            checkForComodification();
            try {
                int i = this.cursor;
                AbstractList.this.add(i, e);
                this.lastRet = -1;
                this.cursor = i + 1;
                this.expectedModCount = AbstractList.this.modCount;
            } catch (IndexOutOfBoundsException e2) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        if (this instanceof RandomAccess) {
            return new RandomAccessSubList(this, fromIndex, toIndex);
        }
        return new SubList(this, fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }
        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((List) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (o1 == null) {
                if (o2 == null) {
                }
            } else if (!o1.equals(o2)) {
            }
            return false;
        }
        if (e1.hasNext() || e2.hasNext()) {
            return false;
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int hashCode = 1;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E e = it.next();
            hashCode = (hashCode * 31) + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    /* access modifiers changed from: protected */
    public void removeRange(int fromIndex, int toIndex) {
        ListIterator<E> it = listIterator(fromIndex);
        int n = toIndex - fromIndex;
        for (int i = 0; i < n; i++) {
            it.next();
            it.remove();
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }
}
