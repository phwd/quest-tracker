package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.SortedLists;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@Beta
public final class ImmutableRangeSet<C extends Comparable> extends AbstractRangeSet<C> implements Serializable {
    private static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet<>(ImmutableList.of(Range.all()));
    private static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet<>(ImmutableList.of());
    private transient ImmutableRangeSet<C> complement;
    private final transient ImmutableList<Range<C>> ranges;

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Comparable */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public /* bridge */ /* synthetic */ boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    static <C extends Comparable> ImmutableRangeSet<C> all() {
        return ALL;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of(Range<C> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return of();
        }
        if (range.equals(Range.all())) {
            return all();
        }
        return new ImmutableRangeSet<>(ImmutableList.of(range));
    }

    public static <C extends Comparable> ImmutableRangeSet<C> copyOf(RangeSet<C> rangeSet) {
        Preconditions.checkNotNull(rangeSet);
        if (rangeSet.isEmpty()) {
            return of();
        }
        if (rangeSet.encloses(Range.all())) {
            return all();
        }
        if (rangeSet instanceof ImmutableRangeSet) {
            ImmutableRangeSet<C> immutableRangeSet = (ImmutableRangeSet) rangeSet;
            if (!immutableRangeSet.isPartialView()) {
                return immutableRangeSet;
            }
        }
        return new ImmutableRangeSet<>(ImmutableList.copyOf((Collection) rangeSet.asRanges()));
    }

    ImmutableRangeSet(ImmutableList<Range<C>> ranges2) {
        this.ranges = ranges2;
    }

    private ImmutableRangeSet(ImmutableList<Range<C>> ranges2, ImmutableRangeSet<C> complement2) {
        this.ranges = ranges2;
        this.complement = complement2;
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public boolean encloses(Range<C> otherRange) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), otherRange.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        return index != -1 && this.ranges.get(index).encloses(otherRange);
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public Range<C> rangeContaining(C value) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(value), Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (index == -1) {
            return null;
        }
        Range<C> range = this.ranges.get(index);
        if (range.contains(value)) {
            return range;
        }
        return null;
    }

    @Override // com.google.common.collect.RangeSet
    public Range<C> span() {
        if (!this.ranges.isEmpty()) {
            Cut<C> cut = this.ranges.get(0).lowerBound;
            ImmutableList<Range<C>> immutableList = this.ranges;
            return Range.create(cut, immutableList.get(immutableList.size() - 1).upperBound);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public void addAll(RangeSet<C> rangeSet) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet, com.google.common.collect.AbstractRangeSet
    public void removeAll(RangeSet<C> rangeSet) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableSet<Range<C>> asRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet(this.ranges, Range.RANGE_LEX_ORDERING);
    }

    /* access modifiers changed from: private */
    public final class ComplementRanges extends ImmutableList<Range<C>> {
        private final boolean positiveBoundedAbove;
        private final boolean positiveBoundedBelow;
        private final int size;

        ComplementRanges() {
            this.positiveBoundedBelow = ((Range) ImmutableRangeSet.this.ranges.get(0)).hasLowerBound();
            this.positiveBoundedAbove = ((Range) Iterables.getLast(ImmutableRangeSet.this.ranges)).hasUpperBound();
            int size2 = ImmutableRangeSet.this.ranges.size() - 1;
            size2 = this.positiveBoundedBelow ? size2 + 1 : size2;
            this.size = this.positiveBoundedAbove ? size2 + 1 : size2;
        }

        public int size() {
            return this.size;
        }

        @Override // java.util.List
        public Range<C> get(int index) {
            Cut<C> lowerBound;
            Cut<C> upperBound;
            Preconditions.checkElementIndex(index, this.size);
            if (this.positiveBoundedBelow) {
                lowerBound = index == 0 ? Cut.belowAll() : ((Range) ImmutableRangeSet.this.ranges.get(index - 1)).upperBound;
            } else {
                lowerBound = ((Range) ImmutableRangeSet.this.ranges.get(index)).upperBound;
            }
            if (!this.positiveBoundedAbove || index != this.size - 1) {
                upperBound = ((Range) ImmutableRangeSet.this.ranges.get((!this.positiveBoundedBelow ? 1 : 0) + index)).lowerBound;
            } else {
                upperBound = Cut.aboveAll();
            }
            return Range.create(lowerBound, upperBound);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableRangeSet<C> complement() {
        ImmutableRangeSet<C> result = this.complement;
        if (result != null) {
            return result;
        }
        if (this.ranges.isEmpty()) {
            ImmutableRangeSet<C> all = all();
            this.complement = all;
            return all;
        } else if (this.ranges.size() != 1 || !this.ranges.get(0).equals(Range.all())) {
            ImmutableRangeSet<C> result2 = new ImmutableRangeSet<>(new ComplementRanges(), this);
            this.complement = result2;
            return result2;
        } else {
            ImmutableRangeSet<C> of = of();
            this.complement = of;
            return of;
        }
    }

    private ImmutableList<Range<C>> intersectRanges(final Range<C> range) {
        final int fromIndex;
        int toIndex;
        if (this.ranges.isEmpty() || range.isEmpty()) {
            return ImmutableList.of();
        }
        if (range.encloses(span())) {
            return this.ranges;
        }
        if (range.hasLowerBound()) {
            fromIndex = SortedLists.binarySearch(this.ranges, Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        } else {
            fromIndex = 0;
        }
        if (range.hasUpperBound()) {
            toIndex = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        } else {
            toIndex = this.ranges.size();
        }
        final int length = toIndex - fromIndex;
        if (length == 0) {
            return ImmutableList.of();
        }
        return new ImmutableList<Range<C>>() {
            /* class com.google.common.collect.ImmutableRangeSet.AnonymousClass1 */

            public int size() {
                return length;
            }

            @Override // java.util.List
            public Range<C> get(int index) {
                Preconditions.checkElementIndex(index, length);
                if (index == 0 || index == length - 1) {
                    return ((Range) ImmutableRangeSet.this.ranges.get(fromIndex + index)).intersection(range);
                }
                return (Range) ImmutableRangeSet.this.ranges.get(fromIndex + index);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }
        };
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableRangeSet<C> subRangeSet(Range<C> range) {
        if (!isEmpty()) {
            Range<C> span = span();
            if (range.encloses(span)) {
                return this;
            }
            if (range.isConnected(span)) {
                return new ImmutableRangeSet<>(intersectRanges(range));
            }
        }
        return of();
    }

    public ImmutableSortedSet<C> asSet(DiscreteDomain<C> domain) {
        Preconditions.checkNotNull(domain);
        if (isEmpty()) {
            return ImmutableSortedSet.of();
        }
        Range<C> span = span().canonical(domain);
        if (span.hasLowerBound()) {
            if (!span.hasUpperBound()) {
                try {
                    domain.maxValue();
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
                }
            }
            return new AsSet(domain);
        }
        throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
    }

    /* access modifiers changed from: private */
    public final class AsSet extends ImmutableSortedSet<C> {
        private final DiscreteDomain<C> domain;
        private transient Integer size;

        AsSet(DiscreteDomain<C> domain2) {
            super(Ordering.natural());
            this.domain = domain2;
        }

        public int size() {
            Integer result = this.size;
            if (result == null) {
                long total = 0;
                UnmodifiableIterator it = ImmutableRangeSet.this.ranges.iterator();
                while (it.hasNext()) {
                    total += (long) ContiguousSet.create((Range) it.next(), this.domain).size();
                    if (total >= 2147483647L) {
                        break;
                    }
                }
                Integer valueOf = Integer.valueOf(Ints.saturatedCast(total));
                this.size = valueOf;
                result = valueOf;
            }
            return result.intValue();
        }

        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.util.NavigableSet, java.lang.Iterable, java.util.AbstractCollection, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<C> iterator() {
            return new AbstractIterator<C>() {
                /* class com.google.common.collect.ImmutableRangeSet.AsSet.AnonymousClass1 */
                Iterator<C> elemItr = Iterators.emptyIterator();
                final Iterator<Range<C>> rangeItr = ImmutableRangeSet.this.ranges.iterator();

                /* JADX WARN: Type inference failed for: r0v4, types: [C extends java.lang.Comparable, java.lang.Comparable] */
                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                public C computeNext() {
                    while (!this.elemItr.hasNext()) {
                        if (!this.rangeItr.hasNext()) {
                            return (C) ((Comparable) endOfData());
                        }
                        this.elemItr = ContiguousSet.create(this.rangeItr.next(), AsSet.this.domain).iterator();
                    }
                    return this.elemItr.next();
                }
            };
        }

        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
        @GwtIncompatible("NavigableSet")
        public UnmodifiableIterator<C> descendingIterator() {
            return new AbstractIterator<C>() {
                /* class com.google.common.collect.ImmutableRangeSet.AsSet.AnonymousClass2 */
                Iterator<C> elemItr = Iterators.emptyIterator();
                final Iterator<Range<C>> rangeItr = ImmutableRangeSet.this.ranges.reverse().iterator();

                /* JADX WARN: Type inference failed for: r0v4, types: [C extends java.lang.Comparable, java.lang.Comparable] */
                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                public C computeNext() {
                    while (!this.elemItr.hasNext()) {
                        if (!this.rangeItr.hasNext()) {
                            return (C) ((Comparable) endOfData());
                        }
                        this.elemItr = ContiguousSet.create(this.rangeItr.next(), AsSet.this.domain).descendingIterator();
                    }
                    return this.elemItr.next();
                }
            };
        }

        /* JADX DEBUG: Type inference failed for r1v0. Raw type applied. Possible types: com.google.common.collect.DiscreteDomain<C>, com.google.common.collect.DiscreteDomain<C extends java.lang.Comparable> */
        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> subSet(Range<C> range) {
            return ImmutableRangeSet.this.subRangeSet((Range) range).asSet((DiscreteDomain<C>) this.domain);
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> headSetImpl(C toElement, boolean inclusive) {
            return subSet(Range.upTo(toElement, BoundType.forBoolean(inclusive)));
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
            if (fromInclusive || toInclusive || Range.compareOrThrow(fromElement, toElement) != 0) {
                return subSet(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
            }
            return ImmutableSortedSet.of();
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> tailSetImpl(C fromElement, boolean inclusive) {
            return subSet(Range.downTo(fromElement, BoundType.forBoolean(inclusive)));
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean contains(@Nullable Object o) {
            if (o == null) {
                return false;
            }
            try {
                return ImmutableRangeSet.this.contains((Comparable) o);
            } catch (ClassCastException e) {
                return false;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v3, resolved type: com.google.common.collect.Range */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet
        public int indexOf(Object target) {
            if (!contains(target)) {
                return -1;
            }
            Comparable comparable = (Comparable) target;
            long total = 0;
            UnmodifiableIterator it = ImmutableRangeSet.this.ranges.iterator();
            while (it.hasNext()) {
                Range range = (Range) it.next();
                if (range.contains(comparable)) {
                    return Ints.saturatedCast(((long) ContiguousSet.create(range, this.domain).indexOf(comparable)) + total);
                }
                total += (long) ContiguousSet.create(range, this.domain).size();
            }
            throw new AssertionError("impossible");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return ImmutableRangeSet.this.ranges.isPartialView();
        }

        public String toString() {
            return ImmutableRangeSet.this.ranges.toString();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
        }
    }

    private static class AsSetSerializedForm<C extends Comparable> implements Serializable {
        private final DiscreteDomain<C> domain;
        private final ImmutableList<Range<C>> ranges;

        AsSetSerializedForm(ImmutableList<Range<C>> ranges2, DiscreteDomain<C> domain2) {
            this.ranges = ranges2;
            this.domain = domain2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableRangeSet(this.ranges).asSet(this.domain);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return this.ranges.isPartialView();
    }

    public static <C extends Comparable<?>> Builder<C> builder() {
        return new Builder<>();
    }

    public static class Builder<C extends Comparable<?>> {
        private final RangeSet<C> rangeSet = TreeRangeSet.create();

        public Builder<C> add(Range<C> range) {
            if (range.isEmpty()) {
                throw new IllegalArgumentException("range must not be empty, but was " + range);
            } else if (!this.rangeSet.complement().encloses(range)) {
                for (Range<C> currentRange : this.rangeSet.asRanges()) {
                    Preconditions.checkArgument(!currentRange.isConnected(range) || currentRange.intersection(range).isEmpty(), "Ranges may not overlap, but received %s and %s", currentRange, range);
                }
                throw new AssertionError("should have thrown an IAE above");
            } else {
                this.rangeSet.add(range);
                return this;
            }
        }

        public Builder<C> addAll(RangeSet<C> ranges) {
            for (Range<C> range : ranges.asRanges()) {
                add(range);
            }
            return this;
        }

        public ImmutableRangeSet<C> build() {
            return ImmutableRangeSet.copyOf(this.rangeSet);
        }
    }

    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private final ImmutableList<Range<C>> ranges;

        SerializedForm(ImmutableList<Range<C>> ranges2) {
            this.ranges = ranges2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            if (this.ranges.isEmpty()) {
                return ImmutableRangeSet.of();
            }
            if (this.ranges.equals(ImmutableList.of(Range.all()))) {
                return ImmutableRangeSet.all();
            }
            return new ImmutableRangeSet(this.ranges);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.ranges);
    }
}
