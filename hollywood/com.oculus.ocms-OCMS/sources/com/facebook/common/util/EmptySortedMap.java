package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class EmptySortedMap<K, V> implements SortedMap<K, V>, Comparator<K> {
    public static SortedMap EMPTY_SORTED_MAP = new EmptySortedMap();

    public void clear() {
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return this;
    }

    @Override // java.util.Comparator
    public int compare(@Nullable K k, @Nullable K k2) {
        return 0;
    }

    public boolean containsKey(@Nullable Object obj) {
        return false;
    }

    public boolean containsValue(@Nullable Object obj) {
        return false;
    }

    @Override // java.util.Map
    @Nullable
    public V get(@Nullable Object obj) {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public static <K, V> SortedMap<K, V> emptySortedMap() {
        return EMPTY_SORTED_MAP;
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> subMap(@Nullable K k, @Nullable K k2) {
        return EMPTY_SORTED_MAP;
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> headMap(@Nullable K k) {
        return EMPTY_SORTED_MAP;
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> tailMap(@Nullable K k) {
        return EMPTY_SORTED_MAP;
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        throw new NoSuchElementException();
    }

    @Override // java.util.Map
    public V put(@Nullable K k, @Nullable V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V remove(@Nullable Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, java.util.SortedMap
    public Set<K> keySet() {
        return Collections.emptySet();
    }

    @Override // java.util.Map, java.util.SortedMap
    public Collection<V> values() {
        return Collections.emptySet();
    }

    @Override // java.util.Map, java.util.SortedMap
    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.emptySet();
    }
}
