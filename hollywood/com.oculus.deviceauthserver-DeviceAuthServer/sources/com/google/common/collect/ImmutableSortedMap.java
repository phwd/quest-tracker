package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {
    private static final ImmutableSortedMap<Comparable, Object> NATURAL_EMPTY_MAP = new EmptyImmutableSortedMap(NATURAL_ORDER);
    private static final Comparator<Comparable> NATURAL_ORDER = Ordering.natural();
    private static final long serialVersionUID = 0;
    private transient ImmutableSortedMap<K, V> descendingMap;

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedMap<K, V> createDescendingMap();

    @Override // java.util.NavigableMap
    public abstract ImmutableSortedMap<K, V> headMap(K k, boolean z);

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, java.util.SortedMap
    public abstract ImmutableSortedSet<K> keySet();

    @Override // java.util.NavigableMap
    public abstract ImmutableSortedMap<K, V> tailMap(K k, boolean z);

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, java.util.SortedMap
    public abstract ImmutableCollection<V> values();

    static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<? super K> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return of();
        }
        return new EmptyImmutableSortedMap(comparator);
    }

    static <K, V> ImmutableSortedMap<K, V> fromSortedEntries(Comparator<? super K> comparator, int size, Map.Entry<K, V>[] entries) {
        if (size == 0) {
            return emptyMap(comparator);
        }
        ImmutableList.Builder<K> keyBuilder = ImmutableList.builder();
        ImmutableList.Builder<V> valueBuilder = ImmutableList.builder();
        for (int i = 0; i < size; i++) {
            Map.Entry<K, V> entry = entries[i];
            keyBuilder.add(entry.getKey());
            valueBuilder.add(entry.getValue());
        }
        return new RegularImmutableSortedMap(new RegularImmutableSortedSet(keyBuilder.build(), comparator), valueBuilder.build());
    }

    static <K, V> ImmutableSortedMap<K, V> from(ImmutableSortedSet<K> keySet, ImmutableList<V> valueList) {
        if (keySet.isEmpty()) {
            return emptyMap(keySet.comparator());
        }
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) keySet, valueList);
    }

    public static <K, V> ImmutableSortedMap<K, V> of() {
        return (ImmutableSortedMap<K, V>) NATURAL_EMPTY_MAP;
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k1, V v1) {
        return from(ImmutableSortedSet.of((Comparable) k1), ImmutableList.of(v1));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2) {
        return fromEntries(Ordering.natural(), false, 2, entryOf(k1, v1), entryOf(k2, v2));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return fromEntries(Ordering.natural(), false, 3, entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return fromEntries(Ordering.natural(), false, 4, entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
    }

    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return fromEntries(Ordering.natural(), false, 5, entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return copyOfInternal(map, Ordering.natural());
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        return copyOfInternal(map, (Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOfSorted(SortedMap<K, ? extends V> map) {
        Comparator<? super K> comparator = map.comparator();
        if (comparator == null) {
            comparator = NATURAL_ORDER;
        }
        return copyOfInternal(map, comparator);
    }

    private static <K, V> ImmutableSortedMap<K, V> copyOfInternal(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        boolean z;
        boolean sameComparator = false;
        if (map instanceof SortedMap) {
            Comparator<?> comparator2 = ((SortedMap) map).comparator();
            if (comparator2 == null) {
                z = comparator == NATURAL_ORDER;
            } else {
                z = comparator.equals(comparator2);
            }
            sameComparator = z;
        }
        if (sameComparator && (map instanceof ImmutableSortedMap)) {
            ImmutableSortedMap<K, V> kvMap = (ImmutableSortedMap) map;
            if (!kvMap.isPartialView()) {
                return kvMap;
            }
        }
        Map.Entry<K, V>[] entries = (Map.Entry[]) map.entrySet().toArray(new Map.Entry[0]);
        return fromEntries(comparator, sameComparator, entries.length, entries);
    }

    static <K, V> ImmutableSortedMap<K, V> fromEntries(Comparator<? super K> comparator, boolean sameComparator, int size, Map.Entry<K, V>... entries) {
        for (int i = 0; i < size; i++) {
            Map.Entry<K, V> entry = entries[i];
            entries[i] = entryOf(entry.getKey(), entry.getValue());
        }
        if (!sameComparator) {
            sortEntries(comparator, size, entries);
            validateEntries(size, entries, comparator);
        }
        return fromSortedEntries(comparator, size, entries);
    }

    private static <K, V> void sortEntries(Comparator<? super K> comparator, int size, Map.Entry<K, V>[] entries) {
        Arrays.sort(entries, 0, size, Ordering.from(comparator).onKeys());
    }

    private static <K, V> void validateEntries(int size, Map.Entry<K, V>[] entries, Comparator<? super K> comparator) {
        for (int i = 1; i < size; i++) {
            checkNoConflict(comparator.compare(entries[i + -1].getKey(), entries[i].getKey()) != 0, "key", entries[i - 1], entries[i]);
        }
    }

    public static <K extends Comparable<?>, V> Builder<K, V> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <K, V> Builder<K, V> orderedBy(Comparator<K> comparator) {
        return new Builder<>(comparator);
    }

    public static <K extends Comparable<?>, V> Builder<K, V> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    public static class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        private final Comparator<? super K> comparator;

        public Builder(Comparator<? super K> comparator2) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(K key, V value) {
            super.put((Object) key, (Object) value);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableSortedMap<K, V> build() {
            return ImmutableSortedMap.fromEntries(this.comparator, false, this.size, this.entries);
        }
    }

    ImmutableSortedMap() {
    }

    ImmutableSortedMap(ImmutableSortedMap<K, V> descendingMap2) {
        this.descendingMap = descendingMap2;
    }

    public int size() {
        return values().size();
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean containsValue(@Nullable Object value) {
        return values().contains(value);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return keySet().isPartialView() || values().isPartialView();
    }

    @Override // com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map, java.util.SortedMap
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return keySet().comparator();
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        return keySet().first();
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        return keySet().last();
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> headMap(K toKey) {
        return headMap((Object) toKey, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> subMap(K fromKey, K toKey) {
        return subMap((Object) fromKey, true, (Object) toKey, false);
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        Preconditions.checkNotNull(fromKey);
        Preconditions.checkNotNull(toKey);
        Preconditions.checkArgument(comparator().compare(fromKey, toKey) <= 0, "expected fromKey <= toKey but %s > %s", fromKey, toKey);
        return headMap((Object) toKey, toInclusive).tailMap((Object) fromKey, fromInclusive);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> tailMap(K fromKey) {
        return tailMap((Object) fromKey, true);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K key) {
        return headMap((Object) key, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K key) {
        return (K) Maps.keyOrNull(lowerEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K key) {
        return headMap((Object) key, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K floorKey(K key) {
        return (K) Maps.keyOrNull(floorEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K key) {
        return tailMap((Object) key, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K key) {
        return (K) Maps.keyOrNull(ceilingEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K key) {
        return tailMap((Object) key, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K higherKey(K key) {
        return (K) Maps.keyOrNull(higherEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return entrySet().asList().get(0);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return entrySet().asList().get(size() - 1);
    }

    @Override // java.util.NavigableMap
    @Deprecated
    public final Map.Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    @Deprecated
    public final Map.Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> descendingMap() {
        ImmutableSortedMap<K, V> result = this.descendingMap;
        if (result != null) {
            return result;
        }
        ImmutableSortedMap<K, V> result2 = createDescendingMap();
        this.descendingMap = result2;
        return result2;
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedSet<K> navigableKeySet() {
        return keySet();
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedSet<K> descendingKeySet() {
        return keySet().descendingSet();
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;
        private final Comparator<Object> comparator;

        SerializedForm(ImmutableSortedMap<?, ?> sortedMap) {
            super(sortedMap);
            this.comparator = sortedMap.comparator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return createMap(new Builder<>(this.comparator));
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
