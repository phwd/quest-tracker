package com.facebook.flatbuffers.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class MapEntrySetToValueListAdapter<K, V> implements List<V> {
    private final Set<Map.Entry<K, V>> mEntrySet;

    public MapEntrySetToValueListAdapter(Set<Map.Entry<K, V>> set) {
        this.mEntrySet = set;
    }

    public int size() {
        return this.mEntrySet.size();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<V> iterator() {
        final Iterator<Map.Entry<K, V>> it = this.mEntrySet.iterator();
        return new Iterator<V>() {
            /* class com.facebook.flatbuffers.util.MapEntrySetToValueListAdapter.AnonymousClass1 */

            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return (V) ((Map.Entry) it.next()).getValue();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.List
    public void add(int i, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends V> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends V> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public V get(int i) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    public int lastIndexOf(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public ListIterator<V> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public ListIterator<V> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public V remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public V set(int i, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public List<V> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        throw new UnsupportedOperationException();
    }
}
