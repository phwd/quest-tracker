package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class EmptyImmutableSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final transient ImmutableSortedSet<K> keySet;

    EmptyImmutableSortedMap(Comparator<? super K> comparator) {
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    EmptyImmutableSortedMap(Comparator<? super K> comparator, ImmutableSortedMap<K, V> descendingMap) {
        super(descendingMap);
        this.keySet = ImmutableSortedSet.emptySet(comparator);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@Nullable Object key) {
        return null;
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    @Override // com.google.common.collect.ImmutableSortedMap
    public int size() {
        return 0;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isEmpty() {
        return true;
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableCollection<V> values() {
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.ImmutableMap
    public String toString() {
        return "{}";
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableSortedMap
    public boolean isPartialView() {
        return false;
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

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSetMultimap<K, V> asMultimap() {
        return ImmutableSetMultimap.of();
    }

    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> headMap(K toKey, boolean inclusive) {
        Preconditions.checkNotNull(toKey);
        return this;
    }

    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> tailMap(K fromKey, boolean inclusive) {
        Preconditions.checkNotNull(fromKey);
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> createDescendingMap() {
        return new EmptyImmutableSortedMap(Ordering.from(comparator()).reverse(), this);
    }
}
