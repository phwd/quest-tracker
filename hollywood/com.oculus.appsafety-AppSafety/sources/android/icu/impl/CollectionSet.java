package android.icu.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class CollectionSet<E> implements Set<E> {
    private final Collection<E> data;

    public CollectionSet(Collection<E> data2) {
        this.data = data2;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.data.size();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object o) {
        return this.data.contains(o);
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.data.iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        return this.data.toArray();
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] a) {
        return (T[]) this.data.toArray(a);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e) {
        return this.data.add(e);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object o) {
        return this.data.remove(o);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> c) {
        return this.data.containsAll(c);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> c) {
        return this.data.addAll(c);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> c) {
        return this.data.retainAll(c);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> c) {
        return this.data.removeAll(c);
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        this.data.clear();
    }
}
