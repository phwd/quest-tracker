package java.util;

public abstract class AbstractSequentialList<E> extends AbstractList<E> {
    @Override // java.util.List, java.util.AbstractList
    public abstract ListIterator<E> listIterator(int i);

    protected AbstractSequentialList() {
    }

    @Override // java.util.List, java.util.AbstractList
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException e) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public E set(int index, E element) {
        try {
            ListIterator<E> e = listIterator(index);
            E oldVal = e.next();
            e.set(element);
            return oldVal;
        } catch (NoSuchElementException e2) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int index, E element) {
        try {
            listIterator(index).add(element);
        } catch (NoSuchElementException e) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public E remove(int index) {
        try {
            ListIterator<E> e = listIterator(index);
            E outCast = e.next();
            e.remove();
            return outCast;
        } catch (NoSuchElementException e2) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean modified = false;
        try {
            ListIterator<E> e1 = listIterator(index);
            for (E e : c) {
                e1.add(e);
                modified = true;
            }
            return modified;
        } catch (NoSuchElementException e2) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<E> iterator() {
        return listIterator();
    }
}
