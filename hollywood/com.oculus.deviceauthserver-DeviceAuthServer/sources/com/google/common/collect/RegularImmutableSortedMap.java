package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class RegularImmutableSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final transient RegularImmutableSortedSet<K> keySet;
    private final transient ImmutableList<V> valueList;

    RegularImmutableSortedMap(RegularImmutableSortedSet<K> keySet2, ImmutableList<V> valueList2) {
        this.keySet = keySet2;
        this.valueList = valueList2;
    }

    RegularImmutableSortedMap(RegularImmutableSortedSet<K> keySet2, ImmutableList<V> valueList2, ImmutableSortedMap<K, V> descendingMap) {
        super(descendingMap);
        this.keySet = keySet2;
        this.valueList = valueList2;
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

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
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
                public Map.Entry<K, V> get(int index) {
                    return Maps.immutableEntry(this.keyList.get(index), RegularImmutableSortedMap.this.valueList.get(index));
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

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap, java.util.SortedMap
    public ImmutableCollection<V> values() {
        return this.valueList;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@Nullable Object key) {
        int index = this.keySet.indexOf(key);
        if (index == -1) {
            return null;
        }
        return this.valueList.get(index);
    }

    private ImmutableSortedMap<K, V> getSubMap(int fromIndex, int toIndex) {
        if (fromIndex == 0 && toIndex == size()) {
            return this;
        }
        if (fromIndex == toIndex) {
            return emptyMap(comparator());
        }
        return from(this.keySet.getSubSet(fromIndex, toIndex), this.valueList.subList(fromIndex, toIndex));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.RegularImmutableSortedSet<K> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> headMap(K toKey, boolean inclusive) {
        return getSubMap(0, this.keySet.headIndex(Preconditions.checkNotNull(toKey), inclusive));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.RegularImmutableSortedSet<K> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, com.google.common.collect.ImmutableSortedMap, com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return getSubMap(this.keySet.tailIndex(Preconditions.checkNotNull(fromKey), inclusive), size());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedMap
    public ImmutableSortedMap<K, V> createDescendingMap() {
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) this.keySet.descendingSet(), this.valueList.reverse(), this);
    }
}
