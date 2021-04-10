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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
@GwtIncompatible
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    private static final RangeMap EMPTY_SUB_RANGE_MAP = new RangeMap() {
        /* class com.google.common.collect.TreeRangeMap.AnonymousClass1 */

        @Override // com.google.common.collect.RangeMap
        public void clear() {
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Object get(Comparable comparable) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Map.Entry<Range, Object> getEntry(Comparable comparable) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        public Range span() {
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range range, Object obj) {
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
        public void remove(Range range) {
            Preconditions.checkNotNull(range);
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asMapOfRanges() {
            return Collections.emptyMap();
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asDescendingMapOfRanges() {
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

        RangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
            this(Range.create(cut, cut2), v);
        }

        RangeMapEntry(Range<K> range2, V v) {
            this.range = range2;
            this.value = v;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public Range<K> getKey() {
            return this.range;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        public boolean contains(K k) {
            return this.range.contains(k);
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
    @NullableDecl
    public V get(K k) {
        Map.Entry<Range<K>, V> entry = getEntry(k);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    @NullableDecl
    public Map.Entry<Range<K>, V> getEntry(K k) {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> floorEntry = this.entriesByLowerBound.floorEntry(Cut.belowValue(k));
        if (floorEntry == null || !floorEntry.getValue().contains(k)) {
            return null;
        }
        return floorEntry.getValue();
    }

    @Override // com.google.common.collect.RangeMap
    public void put(Range<K> range, V v) {
        if (!range.isEmpty()) {
            Preconditions.checkNotNull(v);
            remove(range);
            this.entriesByLowerBound.put(range.lowerBound, new RangeMapEntry(range, v));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.TreeRangeMap<K extends java.lang.Comparable, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.RangeMap
    public void putCoalescing(Range<K> range, V v) {
        if (this.entriesByLowerBound.isEmpty()) {
            put(range, v);
        } else {
            put(coalescedRange(range, Preconditions.checkNotNull(v)), v);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Range<K> coalescedRange(Range<K> range, V v) {
        return coalesce(coalesce(range, v, this.entriesByLowerBound.lowerEntry(range.lowerBound)), v, this.entriesByLowerBound.floorEntry(range.upperBound));
    }

    private static <K extends Comparable, V> Range<K> coalesce(Range<K> range, V v, @NullableDecl Map.Entry<Cut<K>, RangeMapEntry<K, V>> entry) {
        return (entry == null || !entry.getValue().getKey().isConnected(range) || !entry.getValue().getValue().equals(v)) ? range : range.span(entry.getValue().getKey());
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

    private void putRangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
        this.entriesByLowerBound.put(cut, new RangeMapEntry(cut, cut2, v));
    }

    /* JADX DEBUG: Type inference failed for r3v0. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
    /* JADX DEBUG: Type inference failed for r3v1. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
    /* JADX DEBUG: Type inference failed for r3v2. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
    @Override // com.google.common.collect.RangeMap
    public void remove(Range<K> range) {
        if (!range.isEmpty()) {
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry = this.entriesByLowerBound.lowerEntry(range.lowerBound);
            if (lowerEntry != null) {
                RangeMapEntry<K, V> value = lowerEntry.getValue();
                if (value.getUpperBound().compareTo((Cut<K>) ((Cut<C>) range.lowerBound)) > 0) {
                    if (value.getUpperBound().compareTo((Cut<K>) ((Cut<C>) range.upperBound)) > 0) {
                        putRangeMapEntry(range.upperBound, value.getUpperBound(), lowerEntry.getValue().getValue());
                    }
                    putRangeMapEntry(value.getLowerBound(), range.lowerBound, lowerEntry.getValue().getValue());
                }
            }
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry2 = this.entriesByLowerBound.lowerEntry(range.upperBound);
            if (lowerEntry2 != null) {
                RangeMapEntry<K, V> value2 = lowerEntry2.getValue();
                if (value2.getUpperBound().compareTo((Cut<K>) ((Cut<C>) range.upperBound)) > 0) {
                    putRangeMapEntry(range.upperBound, value2.getUpperBound(), lowerEntry2.getValue().getValue());
                }
            }
            this.entriesByLowerBound.subMap(range.lowerBound, range.upperBound).clear();
        }
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asMapOfRanges() {
        return new AsMapOfRanges(this.entriesByLowerBound.values());
    }

    @Override // com.google.common.collect.RangeMap
    public Map<Range<K>, V> asDescendingMapOfRanges() {
        return new AsMapOfRanges(this.entriesByLowerBound.descendingMap().values());
    }

    /* access modifiers changed from: private */
    public final class AsMapOfRanges extends Maps.IteratorBasedAbstractMap<Range<K>, V> {
        final Iterable<Map.Entry<Range<K>, V>> entryIterable;

        AsMapOfRanges(Iterable<RangeMapEntry<K, V>> iterable) {
            this.entryIterable = iterable;
        }

        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            if (!(obj instanceof Range)) {
                return null;
            }
            Range range = (Range) obj;
            RangeMapEntry rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
            if (rangeMapEntry == null || !rangeMapEntry.getKey().equals(range)) {
                return null;
            }
            return (V) rangeMapEntry.getValue();
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public int size() {
            return TreeRangeMap.this.entriesByLowerBound.size();
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.Iterator<java.util.Map$Entry<com.google.common.collect.Range<K>, V>>, java.util.Iterator<java.util.Map$Entry<com.google.common.collect.Range<K extends java.lang.Comparable>, V>> */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
            return (Iterator<Map.Entry<Range<K>, V>>) this.entryIterable.iterator();
        }
    }

    @Override // com.google.common.collect.RangeMap
    public RangeMap<K, V> subRangeMap(Range<K> range) {
        if (range.equals(Range.all())) {
            return this;
        }
        return new SubRangeMap(range);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private RangeMap<K, V> emptySubRangeMap() {
        return EMPTY_SUB_RANGE_MAP;
    }

    /* access modifiers changed from: private */
    public class SubRangeMap implements RangeMap<K, V> {
        private final Range<K> subRange;

        SubRangeMap(Range<K> range) {
            this.subRange = range;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public V get(K k) {
            if (this.subRange.contains(k)) {
                return (V) TreeRangeMap.this.get(k);
            }
            return null;
        }

        /* JADX DEBUG: Type inference failed for r1v0. Raw type applied. Possible types: com.google.common.collect.Range<K>, com.google.common.collect.Range<K extends java.lang.Comparable> */
        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Map.Entry<Range<K>, V> getEntry(K k) {
            Map.Entry<Range<K>, V> entry;
            if (!this.subRange.contains(k) || (entry = TreeRangeMap.this.getEntry(k)) == null) {
                return null;
            }
            return Maps.immutableEntry(entry.getKey().intersection((Range<K>) this.subRange), entry.getValue());
        }

        /* JADX DEBUG: Type inference failed for r3v1. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
        /* JADX DEBUG: Type inference failed for r1v17. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
        @Override // com.google.common.collect.RangeMap
        public Range<K> span() {
            Cut<C> cut;
            Cut<C> cut2;
            Map.Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(this.subRange.lowerBound);
            if (floorEntry == null || ((RangeMapEntry) floorEntry.getValue()).getUpperBound().compareTo((Cut<K>) ((Cut<C>) this.subRange.lowerBound)) <= 0) {
                cut = (Cut) TreeRangeMap.this.entriesByLowerBound.ceilingKey(this.subRange.lowerBound);
                if (cut == null || cut.compareTo((Cut) this.subRange.upperBound) >= 0) {
                    throw new NoSuchElementException();
                }
            } else {
                cut = this.subRange.lowerBound;
            }
            Map.Entry lowerEntry = TreeRangeMap.this.entriesByLowerBound.lowerEntry(this.subRange.upperBound);
            if (lowerEntry != null) {
                if (((RangeMapEntry) lowerEntry.getValue()).getUpperBound().compareTo((Cut<K>) ((Cut<C>) this.subRange.upperBound)) >= 0) {
                    cut2 = this.subRange.upperBound;
                } else {
                    cut2 = ((RangeMapEntry) lowerEntry.getValue()).getUpperBound();
                }
                return Range.create(cut, cut2);
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range<K> range, V v) {
            Preconditions.checkArgument(this.subRange.encloses(range), "Cannot put range %s into a subRangeMap(%s)", range, this.subRange);
            TreeRangeMap.this.put(range, v);
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range<K> range, V v) {
            if (TreeRangeMap.this.entriesByLowerBound.isEmpty() || range.isEmpty() || !this.subRange.encloses(range)) {
                put(range, v);
            } else {
                put(TreeRangeMap.this.coalescedRange(range, Preconditions.checkNotNull(v)).intersection(this.subRange), v);
            }
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
        public Map<Range<K>, V> asDescendingMapOfRanges() {
            return new TreeRangeMap<K, V>.SubRangeMap.SubRangeMapAsMap() {
                /* class com.google.common.collect.TreeRangeMap.SubRangeMap.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap
                public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
                    if (SubRangeMap.this.subRange.isEmpty()) {
                        return Iterators.emptyIterator();
                    }
                    final Iterator<V> it = TreeRangeMap.this.entriesByLowerBound.headMap(SubRangeMap.this.subRange.upperBound, false).descendingMap().values().iterator();
                    return new AbstractIterator<Map.Entry<Range<K>, V>>() {
                        /* class com.google.common.collect.TreeRangeMap.SubRangeMap.AnonymousClass1.AnonymousClass1 */

                        /* JADX DEBUG: Type inference failed for r2v3. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIterator
                        public Map.Entry<Range<K>, V> computeNext() {
                            if (!it.hasNext()) {
                                return (Map.Entry) endOfData();
                            }
                            RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                            if (rangeMapEntry.getUpperBound().compareTo((Cut<K>) ((Cut<C>) SubRangeMap.this.subRange.lowerBound)) <= 0) {
                                return (Map.Entry) endOfData();
                            }
                            return Maps.immutableEntry(rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange), rangeMapEntry.getValue());
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.RangeMap
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof RangeMap) {
                return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
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

            public boolean containsKey(Object obj) {
                return get(obj) != null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V get(Object obj) {
                RangeMapEntry rangeMapEntry;
                try {
                    if (obj instanceof Range) {
                        Range range = (Range) obj;
                        if (SubRangeMap.this.subRange.encloses(range)) {
                            if (!range.isEmpty()) {
                                if (range.lowerBound.compareTo((Cut) SubRangeMap.this.subRange.lowerBound) == 0) {
                                    Map.Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(range.lowerBound);
                                    rangeMapEntry = floorEntry != null ? (RangeMapEntry) floorEntry.getValue() : null;
                                } else {
                                    rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                                }
                                if (rangeMapEntry != null && rangeMapEntry.getKey().isConnected(SubRangeMap.this.subRange) && rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange).equals(range)) {
                                    return (V) rangeMapEntry.getValue();
                                }
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public V remove(Object obj) {
                V v = (V) get(obj);
                if (v == null) {
                    return null;
                }
                TreeRangeMap.this.remove((Range) obj);
                return v;
            }

            public void clear() {
                SubRangeMap.this.clear();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private boolean removeEntryIf(Predicate<? super Map.Entry<Range<K>, V>> predicate) {
                ArrayList<Range<K>> newArrayList = Lists.newArrayList();
                for (Map.Entry<Range<K>, V> entry : entrySet()) {
                    if (predicate.apply(entry)) {
                        newArrayList.add(entry.getKey());
                    }
                }
                for (Range<K> range : newArrayList) {
                    TreeRangeMap.this.remove(range);
                }
                return !newArrayList.isEmpty();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Set<Range<K>> keySet() {
                return new Maps.KeySet<Range<K>, V>(this) {
                    /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass1 */

                    @Override // com.google.common.collect.Maps.KeySet
                    public boolean remove(@NullableDecl Object obj) {
                        return SubRangeMapAsMap.this.remove(obj) != null;
                    }

                    @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.keyFunction()));
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
                        return SubRangeMapAsMap.this.entryIterator();
                    }

                    @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set, com.google.common.collect.Maps.EntrySet
                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.not(Predicates.in(collection)));
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

            /* access modifiers changed from: package-private */
            public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
                if (SubRangeMap.this.subRange.isEmpty()) {
                    return Iterators.emptyIterator();
                }
                final Iterator<V> it = TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.this.entriesByLowerBound.floorKey(SubRangeMap.this.subRange.lowerBound), SubRangeMap.this.subRange.lowerBound), true).values().iterator();
                return new AbstractIterator<Map.Entry<Range<K>, V>>() {
                    /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass3 */

                    /* JADX DEBUG: Type inference failed for r2v3. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
                    /* JADX DEBUG: Type inference failed for r2v7. Raw type applied. Possible types: com.google.common.collect.Cut<C extends java.lang.Comparable>, com.google.common.collect.Cut<K extends java.lang.Comparable> */
                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public Map.Entry<Range<K>, V> computeNext() {
                        while (it.hasNext()) {
                            RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                            if (rangeMapEntry.getLowerBound().compareTo((Cut<K>) ((Cut<C>) SubRangeMap.this.subRange.upperBound)) >= 0) {
                                return (Map.Entry) endOfData();
                            }
                            if (rangeMapEntry.getUpperBound().compareTo((Cut<K>) ((Cut<C>) SubRangeMap.this.subRange.lowerBound)) > 0) {
                                return Maps.immutableEntry(rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange), rangeMapEntry.getValue());
                            }
                        }
                        return (Map.Entry) endOfData();
                    }
                };
            }

            @Override // java.util.AbstractMap, java.util.Map
            public Collection<V> values() {
                return new Maps.Values<Range<K>, V>(this) {
                    /* class com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.AnonymousClass4 */

                    @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                    public boolean removeAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.in(collection), Maps.valueFunction()));
                    }

                    @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.valueFunction()));
                    }
                };
            }
        }
    }

    @Override // com.google.common.collect.RangeMap
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
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
