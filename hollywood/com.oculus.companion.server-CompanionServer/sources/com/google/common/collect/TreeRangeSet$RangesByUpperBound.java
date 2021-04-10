package com.google.common.collect;

import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

/* access modifiers changed from: package-private */
public final class TreeRangeSet$RangesByUpperBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
    private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
    private final Range<Cut<C>> upperBoundWindow;

    @Override // java.util.NavigableMap
    public /* bridge */ /* synthetic */ NavigableMap headMap(Object obj, boolean z) {
        return headMap((Cut) ((Cut) obj), z);
    }

    @Override // java.util.NavigableMap
    public /* bridge */ /* synthetic */ NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        return subMap((Cut) ((Cut) obj), z, (Cut) ((Cut) obj2), z2);
    }

    @Override // java.util.NavigableMap
    public /* bridge */ /* synthetic */ NavigableMap tailMap(Object obj, boolean z) {
        return tailMap((Cut) ((Cut) obj), z);
    }

    private TreeRangeSet$RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> navigableMap, Range<Cut<C>> range) {
        this.rangesByLowerBound = navigableMap;
        this.upperBoundWindow = range;
    }

    private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> range) {
        if (range.isConnected(this.upperBoundWindow)) {
            return new TreeRangeSet$RangesByUpperBound(this.rangesByLowerBound, range.intersection(this.upperBoundWindow));
        }
        return ImmutableSortedMap.of();
    }

    public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> cut, boolean z, Cut<C> cut2, boolean z2) {
        return subMap(Range.range(cut, BoundType.forBoolean(z), cut2, BoundType.forBoolean(z2)));
    }

    public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> cut, boolean z) {
        return subMap(Range.upTo(cut, BoundType.forBoolean(z)));
    }

    public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> cut, boolean z) {
        return subMap(Range.downTo(cut, BoundType.forBoolean(z)));
    }

    @Override // java.util.SortedMap
    public Comparator<? super Cut<C>> comparator() {
        return Ordering.natural();
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Range<C> get(Object obj) {
        Map.Entry<Cut<C>, Range<C>> lowerEntry;
        if (obj instanceof Cut) {
            try {
                Cut<C> cut = (Cut) obj;
                if (this.upperBoundWindow.contains(cut) && (lowerEntry = this.rangesByLowerBound.lowerEntry(cut)) != null && lowerEntry.getValue().upperBound.equals(cut)) {
                    return lowerEntry.getValue();
                }
            } catch (ClassCastException unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractNavigableMap
    public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
        final Iterator<Range<C>> it;
        if (!this.upperBoundWindow.hasLowerBound()) {
            it = this.rangesByLowerBound.values().iterator();
        } else {
            Map.Entry<Cut<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(this.upperBoundWindow.lowerEndpoint());
            if (lowerEntry == null) {
                it = this.rangesByLowerBound.values().iterator();
            } else if (this.upperBoundWindow.lowerBound.isLessThan(lowerEntry.getValue().upperBound)) {
                it = this.rangesByLowerBound.tailMap(lowerEntry.getKey(), true).values().iterator();
            } else {
                it = this.rangesByLowerBound.tailMap(this.upperBoundWindow.lowerEndpoint(), true).values().iterator();
            }
        }
        return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
            /* class com.google.common.collect.TreeRangeSet$RangesByUpperBound.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public Map.Entry<Cut<C>, Range<C>> computeNext() {
                if (!it.hasNext()) {
                    return (Map.Entry) endOfData();
                }
                Range range = (Range) it.next();
                if (TreeRangeSet$RangesByUpperBound.this.upperBoundWindow.upperBound.isLessThan(range.upperBound)) {
                    return (Map.Entry) endOfData();
                }
                return Maps.immutableEntry(range.upperBound, range);
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractNavigableMap
    public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
        Collection<Range<C>> collection;
        if (this.upperBoundWindow.hasUpperBound()) {
            collection = this.rangesByLowerBound.headMap(this.upperBoundWindow.upperEndpoint(), false).descendingMap().values();
        } else {
            collection = this.rangesByLowerBound.descendingMap().values();
        }
        final PeekingIterator peekingIterator = Iterators.peekingIterator(collection.iterator());
        if (peekingIterator.hasNext() && this.upperBoundWindow.upperBound.isLessThan(((Range) peekingIterator.peek()).upperBound)) {
            peekingIterator.next();
        }
        return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
            /* class com.google.common.collect.TreeRangeSet$RangesByUpperBound.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public Map.Entry<Cut<C>, Range<C>> computeNext() {
                if (!peekingIterator.hasNext()) {
                    return (Map.Entry) endOfData();
                }
                Range range = (Range) peekingIterator.next();
                if (TreeRangeSet$RangesByUpperBound.this.upperBoundWindow.lowerBound.isLessThan(range.upperBound)) {
                    return Maps.immutableEntry(range.upperBound, range);
                }
                return (Map.Entry) endOfData();
            }
        };
    }

    public int size() {
        if (this.upperBoundWindow.equals(Range.all())) {
            return this.rangesByLowerBound.size();
        }
        return Iterators.size(entryIterator());
    }

    public boolean isEmpty() {
        if (this.upperBoundWindow.equals(Range.all())) {
            return this.rangesByLowerBound.isEmpty();
        }
        return !entryIterator().hasNext();
    }
}
