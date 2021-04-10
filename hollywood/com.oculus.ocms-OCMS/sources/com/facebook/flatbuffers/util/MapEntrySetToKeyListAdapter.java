package com.facebook.flatbuffers.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class MapEntrySetToKeyListAdapter<K, V> implements List<K> {
    private final Set<Map.Entry<K, V>> mEntrySet;

    public MapEntrySetToKeyListAdapter(Set<Map.Entry<K, V>> set) {
        this.mEntrySet = set;
    }

    public int size() {
        return this.mEntrySet.size();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<K> iterator() {
        final Iterator<Map.Entry<K, V>> it = this.mEntrySet.iterator();
        return new Iterator<K>() {
            /* class com.facebook.flatbuffers.util.MapEntrySetToKeyListAdapter.AnonymousClass1 */

            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                return (K) ((Map.Entry) it.next()).getKey();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.List
    public void add(int i, K k) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(K k) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends K> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends K> collection) {
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
    public K get(int i) {
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
    public ListIterator<K> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public ListIterator<K> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public K remove(int i) {
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
    public K set(int i, K k) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public List<K> subList(int i, int i2) {
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
