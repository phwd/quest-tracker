package java.util;

public abstract class AbstractList extends AbstractCollection implements List {
    protected transient int modCount = 0;

    @Override // java.util.List
    public abstract Object get(int i);

    protected AbstractList() {
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean add(Object obj) {
        add(size(), obj);
        return true;
    }

    @Override // java.util.List
    public Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        ListIterator listIterator = listIterator();
        if (obj == null) {
            while (listIterator.hasNext()) {
                if (listIterator.next() == null) {
                    return listIterator.previousIndex();
                }
            }
            return -1;
        }
        while (listIterator.hasNext()) {
            if (obj.equals(listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public void clear() {
        removeRange(0, size());
    }

    public boolean addAll(int i, Collection collection) {
        rangeCheckForAdd(i);
        boolean z = false;
        for (Object obj : collection) {
            add(i, obj);
            z = true;
            i++;
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new Itr();
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i) {
        rangeCheckForAdd(i);
        return new ListItr(i);
    }

    /* access modifiers changed from: private */
    public class Itr implements Iterator {
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
        public Object next() {
            checkForComodification();
            try {
                int i = this.cursor;
                Object obj = AbstractList.this.get(i);
                this.lastRet = i;
                this.cursor = i + 1;
                return obj;
            } catch (IndexOutOfBoundsException unused) {
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
                } catch (IndexOutOfBoundsException unused) {
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
    public class ListItr extends Itr implements ListIterator {
        ListItr(int i) {
            super();
            this.cursor = i;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            checkForComodification();
            try {
                int i = this.cursor - 1;
                Object obj = AbstractList.this.get(i);
                this.cursor = i;
                this.lastRet = i;
                return obj;
            } catch (IndexOutOfBoundsException unused) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            if (this.lastRet >= 0) {
                checkForComodification();
                try {
                    AbstractList.this.set(this.lastRet, obj);
                    this.expectedModCount = AbstractList.this.modCount;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            checkForComodification();
            try {
                int i = this.cursor;
                AbstractList.this.add(i, obj);
                this.lastRet = -1;
                this.cursor = i + 1;
                this.expectedModCount = AbstractList.this.modCount;
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        ListIterator listIterator = listIterator();
        ListIterator listIterator2 = ((List) obj).listIterator();
        while (listIterator.hasNext() && listIterator2.hasNext()) {
            Object next = listIterator.next();
            Object next2 = listIterator2.next();
            if (next == null) {
                if (next2 == null) {
                }
            } else if (!next.equals(next2)) {
            }
            return false;
        }
        return !listIterator.hasNext() && !listIterator2.hasNext();
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int i;
        Iterator it = iterator();
        int i2 = 1;
        while (it.hasNext()) {
            Object next = it.next();
            int i3 = i2 * 31;
            if (next == null) {
                i = 0;
            } else {
                i = next.hashCode();
            }
            i2 = i3 + i;
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public void removeRange(int i, int i2) {
        ListIterator listIterator = listIterator(i);
        int i3 = i2 - i;
        for (int i4 = 0; i4 < i3; i4++) {
            listIterator.next();
            listIterator.remove();
        }
    }

    private void rangeCheckForAdd(int i) {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        }
    }

    private String outOfBoundsMsg(int i) {
        return "Index: " + i + ", Size: " + size();
    }
}
