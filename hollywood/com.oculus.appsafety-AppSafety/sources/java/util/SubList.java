package java.util;

/* access modifiers changed from: package-private */
/* compiled from: AbstractList */
public class SubList<E> extends AbstractList<E> {
    private final AbstractList<E> l;
    private final int offset;
    private int size;

    static /* synthetic */ int access$208(SubList x0) {
        int i = x0.size;
        x0.size = i + 1;
        return i;
    }

    static /* synthetic */ int access$210(SubList x0) {
        int i = x0.size;
        x0.size = i - 1;
        return i;
    }

    SubList(AbstractList<E> list, int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        } else if (toIndex > list.size()) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        } else if (fromIndex <= toIndex) {
            this.l = list;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = this.l.modCount;
        } else {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public E set(int index, E element) {
        rangeCheck(index);
        checkForComodification();
        return this.l.set(this.offset + index, element);
    }

    @Override // java.util.List, java.util.AbstractList
    public E get(int index) {
        rangeCheck(index);
        checkForComodification();
        return this.l.get(this.offset + index);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public int size() {
        checkForComodification();
        return this.size;
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        checkForComodification();
        this.l.add(this.offset + index, element);
        this.modCount = this.l.modCount;
        this.size++;
    }

    @Override // java.util.List, java.util.AbstractList
    public E remove(int index) {
        rangeCheck(index);
        checkForComodification();
        E result = this.l.remove(this.offset + index);
        this.modCount = this.l.modCount;
        this.size--;
        return result;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.AbstractList
    public void removeRange(int fromIndex, int toIndex) {
        checkForComodification();
        AbstractList<E> abstractList = this.l;
        int i = this.offset;
        abstractList.removeRange(fromIndex + i, i + toIndex);
        this.modCount = this.l.modCount;
        this.size -= toIndex - fromIndex;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        return addAll(this.size, c);
    }

    @Override // java.util.List, java.util.AbstractList
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize == 0) {
            return false;
        }
        checkForComodification();
        this.l.addAll(this.offset + index, c);
        this.modCount = this.l.modCount;
        this.size += cSize;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator(final int index) {
        checkForComodification();
        rangeCheckForAdd(index);
        return new ListIterator<E>() {
            /* class java.util.SubList.AnonymousClass1 */
            private final ListIterator<E> i = SubList.this.l.listIterator(index + SubList.this.offset);

            @Override // java.util.Iterator, java.util.ListIterator
            public boolean hasNext() {
                return nextIndex() < SubList.this.size;
            }

            @Override // java.util.Iterator, java.util.ListIterator
            public E next() {
                if (hasNext()) {
                    return this.i.next();
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
                    return this.i.previous();
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.i.nextIndex() - SubList.this.offset;
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.i.previousIndex() - SubList.this.offset;
            }

            @Override // java.util.Iterator, java.util.ListIterator
            public void remove() {
                this.i.remove();
                SubList subList = SubList.this;
                subList.modCount = subList.l.modCount;
                SubList.access$210(SubList.this);
            }

            @Override // java.util.ListIterator
            public void set(E e) {
                this.i.set(e);
            }

            @Override // java.util.ListIterator
            public void add(E e) {
                this.i.add(e);
                SubList subList = SubList.this;
                subList.modCount = subList.l.modCount;
                SubList.access$208(SubList.this);
            }
        };
    }

    @Override // java.util.List, java.util.AbstractList
    public List<E> subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }

    private void checkForComodification() {
        if (this.modCount != this.l.modCount) {
            throw new ConcurrentModificationException();
        }
    }
}
