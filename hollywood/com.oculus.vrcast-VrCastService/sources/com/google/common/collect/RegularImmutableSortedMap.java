package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class RegularImmutableSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final transient RegularImmutableSortedSet<K> keySet;
    private final transient ImmutableList<V> valueList;

    RegularImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList) {
        this.keySet = regularImmutableSortedSet;
        this.valueList = immutableList;
    }

    RegularImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList, ImmutableSortedMap<K, V> immutableSortedMap) {
        super(immutableSortedMap);
        this.keySet = regularImmutableSortedSet;
        this.valueList = immutableList;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }

    /* access modifiers changed from: private */
    public class EntrySet extends ImmutableMapEntrySet<K, V> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableAsList<Map.Entry<K, V>>() {
                /* class com.google.common.collect.RegularImmutableSortedMap.EntrySet.AnonymousClass1 */
                private final ImmutableList<K> keyList = RegularImmutableSortedMap.this.keySet().asList();

                @Override // java.util.List
                public Map.Entry<K, V> get(int i) {
                    return Maps.immutableEntry(this.keyList.get(i), RegularImmutableSortedMap.this.valueList.get(i));
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableAsList
                public ImmutableCollection<Map.Entry<K, V>> delegateCollection() {
                    return EntrySet.this;
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMapEntrySet
        public ImmutableMap<K, V> map() {
            return RegularImmutableSortedMap.this;
        }
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableCollection<V> values() {
        return this.valueList;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        int indexOf = this.keySet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.valueList.get(indexOf);
    }

    private ImmutableSortedMap<K, V> getSubMap(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return ImmutableSortedMap.emptyMap(comparator());
        }
        return ImmutableSortedMap.from(this.keySet.getSubSet(i, i2), this.valueList.subList(i, i2));
    }

    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.keySet;
        Preconditions.checkNotNull(k);
        return getSubMap(0, regularImmutableSortedSet.headIndex(k, z));
    }

    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.keySet;
        Preconditions.checkNotNull(k);
        return getSubMap(regularImmutableSortedSet.tailIndex(k, z), size());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> createDescendingMap() {
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) this.keySet.descendingSet(), this.valueList.reverse(), this);
    }
}
