package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class EmptyImmutableSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final transient ImmutableSortedSet<K> keySet;

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        return null;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableSortedMap
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSortedMap
    public int size() {
        return 0;
    }

    @Override // com.google.common.collect.ImmutableMap
    public String toString() {
        return "{}";
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator) {
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator, ImmutableSortedMap<K, V> immutableSortedMap) {
        super(immutableSortedMap);
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableCollection<V> values() {
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return ImmutableSet.of();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        throw new AssertionError("should never be called");
    }

    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        Preconditions.checkNotNull(k);
        return this;
    }

    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        Preconditions.checkNotNull(k);
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> createDescendingMap() {
        return new EmptyImmutableSortedMap(Ordering.from(comparator()).reverse(), this);
    }
}
