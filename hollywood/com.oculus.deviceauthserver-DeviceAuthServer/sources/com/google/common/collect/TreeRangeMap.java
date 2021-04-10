package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
@GwtIncompatible("NavigableMap")
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    private static final RangeMap EMPTY_SUB_RANGE_MAP = new RangeMap() {
        /* class com.google.common.collect.TreeRangeMap.AnonymousClass1 */

        @Override // com.google.common.collect.RangeMap
        @Nullable
        public Object get(Comparable key) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        @Nullable
        public Map.Entry<Range, Object> getEntry(Comparable key) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        public Range span() {
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range range, Object value) {
            Preconditions.checkNotNull(range);
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
            }
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
        }

        @Override // com.google.common.collect.RangeMap
        public void remove(Range range) {
            Preconditions.checkNotNull(range);
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asMapOfRanges() {
            return Collections.emptyMap();
        }

        @Override // com.google.common.collect.RangeMap
        public RangeMap subRangeMap(Range range) {
            Preconditions.checkNotNull(range);
            return this;
        }
    };
    private final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound = Maps.newTreeMap();

    public static <K extends Comparable, V> TreeRangeMap<K, V> create() {
        return new TreeRangeMap<>();
    }

    private TreeRangeMap() {
    }

    /* access modifiers changed from: private */
    public static final class RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
        private final Range<K> range;
        private final V value;

        RangeMapEntry(Cut<K> lowerBound, Cut<K> upperBound, V value2) {
            this(Range.create(lowerBound, upperBound), value2);
        }

        RangeMapEntry(Range<K> range2, V value2) {
            this.range = range2;
            this.value = value2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public Range<K> getKey() {
            return this.range;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        public boolean contains(K value2) {
            return this.range.contains(value2);
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
        /* access modifiers changed from: package-private */
        public Cut<K> getLowerBound() {
            return (Cut<C>) this.range.lowerBound;
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
        /* access modifiers changed from: package-private */
        public Cut<K> getUpperBound() {
            return (Cut<C>) this.range.upperBound;
        }
    }

    @Override // com.google.common.collect.RangeMap
    @Nullable
    public V get(K key) {
        Map.Entry<Range<K>, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    @Nullable
    public Map.Entry<Range<K>, V> getEntry(K key) {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> mapEntry = this.entriesByLowerBound.floorEntry(Cut.belowValue(key));
        if (mapEntry == null || !mapEntry.getValue().contains(key)) {
            return null;
        }
        return mapEntry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    public void put(Range<K> range, V value) {
        if (!range.isEmpty()) {
            Preconditions.checkNotNull(value);
            remove(range);
            this.entriesByLowerBound.put(range.lowerBound, new RangeMapEntry(range, value));
        }
    }

    @Override // com.google.common.collect.RangeMap
    public void putAll(RangeMap<K, V> rangeMap) {
        for (Map.Entry<Range<K>, V> entry : rangeMap.asMapOfRanges().entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.common.collect.RangeMap
    public void clear() {
        this.entriesByLowerBound.clear();
    }

    @Override // com.google.common.collect.RangeMap
    public Range<K> span() {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> firstEntry = this.entriesByLowerBound.firstEntry();
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> lastEntry = this.entriesByLowerBound.lastEntry();
        if (firstEntry != null) {
            return Range.create(firstEntry.getValue().getKey().lowerBound, lastEntry.getValue().getKey().upperBound);
        }
        throw new NoSuchElementException();
    }

    private void putRangeMapEntry(Cut<K> lowerBound, Cut<K> upperBound, V value) {
        this.entriesByLowerBound.put(lowerBound, new RangeMapEntry(lowerBound, upperBound, value));
    }

    /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
    /* JADX DEBUG: Type inference failed for r3v5. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
    /* JADX DEBUG: Type inference failed for r3v6. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
    @Override // com.google.common.collect.RangeMap
    public void remove(Range<K> rangeToRemove) {
        if (!rangeToRemove.isEmpty()) {
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> mapEntryBelowToTruncate = this.entriesByLowerBound.lowerEntry(rangeToRemove.lowerBound);
            if (mapEntryBelowToTruncate != null) {
                RangeMapEntry<K, V> rangeMapEntry = mapEntryBelowToTruncate.getValue();
                if (rangeMapEntry.getUpperBound().compareTo((Cut<K>) ((Cut<C>) rangeToRemove.lowerBound)) > 0) {
                    if (rangeMapEntry.getUpperBound().compareTo((Cut<K>) ((Cut<C>) rangeToRemove.upperBound)) > 0) {
                        putRangeMapEntry(rangeToRemove.upperBound, rangeMapEntry.getUpperBound(), mapEntryBelowToTruncate.getValue().getValue());
                    }
                    putRangeMapEntry(rangeMapEntry.getLowerBound(), rangeToRemove.lowerBound, mapEntryBelowToTruncate.getValue().getValue());
                }
            }
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> mapEntryAboveToTruncate = this.entriesByLowerBound.lowerEntry(rangeToRemove.upperBound);
            if (mapEntryAboveToTruncate != null) {
                RangeMapEntry<K, V> rangeMapEntry2 = mapEntryAboveToTruncate.getValue();
                if (rangeMapEntry2.getUpperBound().compareTo((Cut<K>) ((Cut<C>) rangeToRemove.upperBound)) > 0) {
                    putRangeMapEntry(rangeToRemove.upperBound, rangeMapEntry2.getUpperBound(), mapEntryAboveToTruncate.getValue().getValue());
                    this.entriesByLowerBound.remove(rangeToRemove.lowerBound);
                }
            }
            this.entriesByLowerBound.subMap(rangeToRemove.lowerBound, rangeToRemove.upperBound).clear();
        }
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asMapOfRanges() {
        return new AsMapOfRanges();
    }

    /* access modifiers changed from: private */
    public final class AsMapOfRanges extends AbstractMap<Range<K>, V> {
        private AsMapOfRanges() {
        }

        public boolean containsKey(@Nullable Object key) {
            return get(key) != null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@Nullable Object key) {
            if (!(key instanceof Range)) {
                return null;
            }
            Range<?> range = (Range) key;
            RangeMapEntry<K, V> rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
            if (rangeMapEntry == null || !rangeMapEntry.getKey().equals(range)) {
                return null;
            }
            return rangeMapEntry.getValue();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<Range<K>, V>> entrySet() {
            return new AbstractSet<Map.Entry<Range<K>, V>>() {
                /* class com.google.common.collect.TreeRangeMap.AsMapOfRanges.AnonymousClass1 */

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<Range<K>, V>> iterator() {
                    return TreeRangeMap.this.entriesByLowerBound.values().iterator();
                }

                public int size() {
                    return TreeRangeMap.this.entriesByLowerBound.size();
                }
            };
        }
    }

    @Override // com.google.common.collect.RangeMap
    public RangeMap<K, V> subRangeMap(Range<K> subRange) {
        if (subRange.equals(Range.all())) {
            return this;
        }
        return new SubRangeMap(subRange);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private RangeMap<K, V> emptySubRangeMap() {
        return EMPTY_SUB_RANGE_MAP;
    }

    /* access modifiers changed from: private */
    public class SubRangeMap implements RangeMap<K, V> {
        private final Range<K> subRange;

        SubRangeMap(Range<K> subRange2) {
            this.subRange = subRange2;
        }

        @Override // com.google.common.collect.RangeMap
        @Nullable
        public V get(K key) {
            if (this.subRange.contains(key)) {
                return (V) TreeRangeMap.this.get(key);
            }
            return null;
        }

        /* JADX DEBUG: Type inference failed for r2v0. Raw type applied. Possible types: com.google.common.collect.Range<K>, com.google.common.collect.Range<K extends java.lang.Comparable> */
        @Override // com.google.common.collect.RangeMap
        @Nullable
        public Map.Entry<Range<K>, V> getEntry(K key) {
            Map.Entry<Range<K>, V> entry;
            if (!this.subRange.contains(key) || (entry = TreeRangeMap.this.getEntry(key)) == null) {
                return null;
            }
            return Maps.immutableEntry(entry.getKey().intersection((Range<K>) this.subRange), entry.getValue());
        }

        /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
        /* JADX DEBUG: Type inference failed for r2v10. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
        @Override // com.google.common.collect.RangeMap
        public Range<K> span() {
            Cut<C> cut;
            Cut<C> cut2;
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(this.subRange.lowerBound);
            if (lowerEntry == null || lowerEntry.getValue().getUpperBound().compareTo((Cut<K>) ((Cut<C>) this.subRange.lowerBound)) <= 0) {
                cut = (Cut) TreeRangeMap.this.entriesByLowerBound.ceilingKey(this.subRange.lowerBound);
                if (cut == null || cut.compareTo((Cut) this.subRange.upperBound) >= 0) {
                    throw new NoSuchElementException();
                }
            } else {
                cut = this.subRange.lowerBound;
            }
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> upperEntry = TreeRangeMap.this.entriesByLowerBound.lowerEntry(this.subRange.upperBound);
            if (upperEntry != null) {
                if (upperEntry.getValue().getUpperBound().compareTo((Cut<K>) ((Cut<C>) this.subRange.upperBound)) >= 0) {
                    cut2 = this.subRange.upperBound;
                } else {
                    cut2 = upperEntry.getValue().getUpperBound();
                }
                return Range.create(cut, cut2);
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range<K> range, V value) {
            Preconditions.checkArgument(this.subRange.encloses(range), "Cannot put range %s into a subRangeMap(%s)", range, this.subRange);
            TreeRangeMap.this.put(range, value);
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap<K, V> rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                Range<K> span = rangeMap.span();
                Preconditions.checkArgument(this.subRange.encloses(span), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", span, this.subRange);
                TreeRangeMap.this.putAll(rangeMap);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.TreeRangeMap */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.RangeMap
        public void clear() {
            TreeRangeMap.this.remove(this.subRange);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.Range<K extends java.lang.Comparable> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.RangeMap
        public void remove(Range<K> range) {
            if (range.isConnected(this.subRange)) {
                TreeRangeMap.this.remove(range.intersection(this.subRange));
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.Range<K extends java.lang.Comparable> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.RangeMap
        public RangeMap<K, V> subRangeMap(Range<K> range) {
            if (!range.isConnected(this.subRange)) {
                return TreeRangeMap.this.emptySubRangeMap();
            }
            return TreeRangeMap.this.subRangeMap(range.intersection(this.subRange));
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asMapOfRanges() {
            return new SubRangeMapAsMap();
        }

        @Override // com.google.common.collect.RangeMap
        public boolean equals(@Nullable Object o) {
            if (o instanceof RangeMap) {
                return asMapOfRanges().equals(((RangeMap) o).asMapOfRanges());
            }
            return false;
        }

        @Override // com.google.common.collect.RangeMap
        public int hashCode() {
            return asMapOfRanges().hashCode();
        }

        @Override // com.google.common.collect.RangeMap
        public String toString() {
            return asMapOfRanges().toString();
        }

        /* access modifiers changed from: package-private */
        public class SubRangeMapAsMap extends AbstractMap<Range<K>, V> {
            SubRangeMapAsMap() {
            }

            public boolean containsKey(Object key) {
                return get(key) != null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V get(Object key) {
                try {
                    if (key instanceof Range) {
                        Range<K> r = (Range) key;
                        if (SubRangeMap.this.subRange.encloses(r)) {
                            if (!r.isEmpty()) {
                                RangeMapEntry<K, V> candidate = null;
                                if (r.lowerBound.compareTo((Cut) SubRangeMap.this.subRange.lowerBound) == 0) {
                                    Map.Entry<Cut<K>, RangeMapEntry<K, V>> entry = TreeRangeMap.this.entriesByLowerBound.floorEntry(r.lowerBound);
                                    if (entry != null) {
                                        candidate = entry.getValue();
                                    }
                                } else {
                                    candidate = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(r.lowerBound);
                                }
                                if (candidate != null && candidate.getKey().isConnected(SubRangeMap.this.subRange) && candidate.getKey().intersection(SubRangeMap.this.subRange).equals(r)) {
                                    return candidate.getValue();
                                }
                            }
                        }
                        return null;
                    }
                    return null;
                } catch (ClassCastException e) {
                    return null;
                }
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V remove(Object key) {
                V value = (V) get(key);
                if (value == null) {
                    return null;
                }
                TreeRangeMap.this.remove((Range) key);
                return value;
            }

            public void clear() {
                SubRangeMap.this.clear();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private boolean removeEntryIf(Predicate<? super Map.Entry<Range<K>, V>> predicate) {
                List<Range<K>> toRemove = Lists.newArrayList();
                for (Map.Entry<Range<K>, V> entry : entrySet()) {
                    if (predicate.apply(entry)) {
                        toRemove.add(entry.getKey());
                    }
                }
                for (Range<K> range : toRemove) {
                    TreeRangeMap.this.remove(range);
                }
                return !toRemove.isEmpty();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Range<K>> keySet() {
                return new Maps.KeySet<Range<K>, V>(this) {
                    /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass1 */

                    @Override // com.google.common.collect.Maps.KeySet
                    public boolean remove(@Nullable Object o) {
                        return SubRangeMapAsMap.this.remove(o) != null;
                    }

                    @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                    public boolean retainAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(c)), Maps.keyFunction()));
                    }
                };
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Map.Entry<Range<K>, V>> entrySet() {
                return new Maps.EntrySet<Range<K>, V>() {
                    /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass2 */

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.collect.Maps.EntrySet
                    public Map<Range<K>, V> map() {
                        return SubRangeMapAsMap.this;
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                    public Iterator<Map.Entry<Range<K>, V>> iterator() {
                        if (SubRangeMap.this.subRange.isEmpty()) {
                            return Iterators.emptyIterator();
                        }
                        final Iterator<RangeMapEntry<K, V>> backingItr = TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.this.entriesByLowerBound.floorKey(SubRangeMap.this.subRange.lowerBound), SubRangeMap.this.subRange.lowerBound), true).values().iterator();
                        return new AbstractIterator<Map.Entry<Range<K>, V>>() {
                            /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass2.AnonymousClass1 */

                            /* JADX DEBUG: Type inference failed for r2v4. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
                            /* JADX DEBUG: Type inference failed for r2v9. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
                            /* access modifiers changed from: protected */
                            @Override // com.google.common.collect.AbstractIterator
                            public Map.Entry<Range<K>, V> computeNext() {
                                while (backingItr.hasNext()) {
                                    RangeMapEntry<K, V> entry = (RangeMapEntry) backingItr.next();
                                    if (entry.getLowerBound().compareTo((Cut<K>) ((Cut<C>) SubRangeMap.this.subRange.upperBound)) >= 0) {
                                        break;
                                    } else if (entry.getUpperBound().compareTo((Cut<K>) ((Cut<C>) SubRangeMap.this.subRange.lowerBound)) > 0) {
                                        return Maps.immutableEntry(entry.getKey().intersection(SubRangeMap.this.subRange), entry.getValue());
                                    }
                                }
                                return (Map.Entry) endOfData();
                            }
                        };
                    }

                    @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set, com.google.common.collect.Maps.EntrySet
                    public boolean retainAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.not(Predicates.in(c)));
                    }

                    @Override // com.google.common.collect.Maps.EntrySet
                    public int size() {
                        return Iterators.size(iterator());
                    }

                    @Override // com.google.common.collect.Maps.EntrySet
                    public boolean isEmpty() {
                        return !iterator().hasNext();
                    }
                };
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Collection<V> values() {
                return new Maps.Values<Range<K>, V>(this) {
                    /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass3 */

                    @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                    public boolean removeAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.in(c), Maps.valueFunction()));
                    }

                    @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                    public boolean retainAll(Collection<?> c) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(c)), Maps.valueFunction()));
                    }
                };
            }
        }
    }

    @Override // com.google.common.collect.RangeMap
    public boolean equals(@Nullable Object o) {
        if (o instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) o).asMapOfRanges());
        }
        return false;
    }

    @Override // com.google.common.collect.RangeMap
    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    @Override // com.google.common.collect.RangeMap
    public String toString() {
        return this.entriesByLowerBound.values().toString();
    }
}
