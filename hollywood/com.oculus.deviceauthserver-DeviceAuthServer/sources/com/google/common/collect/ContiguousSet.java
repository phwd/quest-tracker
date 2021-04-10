package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedSet;
import java.lang.Comparable;
import java.util.NoSuchElementException;

@Beta
@GwtCompatible(emulated = true)
public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    final DiscreteDomain<C> domain;

    /* access modifiers changed from: package-private */
    public abstract ContiguousSet<C> headSetImpl(C c, boolean z);

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType boundType, BoundType boundType2);

    /* access modifiers changed from: package-private */
    public abstract ContiguousSet<C> subSetImpl(C c, boolean z, C c2, boolean z2);

    /* access modifiers changed from: package-private */
    public abstract ContiguousSet<C> tailSetImpl(C c, boolean z);

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> domain2) {
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(domain2);
        Range<C> effectiveRange = range;
        try {
            if (!range.hasLowerBound()) {
                effectiveRange = effectiveRange.intersection(Range.atLeast(domain2.minValue()));
            }
            if (!range.hasUpperBound()) {
                effectiveRange = effectiveRange.intersection(Range.atMost(domain2.maxValue()));
            }
            if (effectiveRange.isEmpty() || Range.compareOrThrow(range.lowerBound.leastValueAbove(domain2), range.upperBound.greatestValueBelow(domain2)) > 0) {
                return new EmptyContiguousSet(domain2);
            }
            return new RegularContiguousSet(effectiveRange, domain2);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }

    ContiguousSet(DiscreteDomain<C> domain2) {
        super(Ordering.natural());
        this.domain = domain2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.ContiguousSet<C extends java.lang.Comparable> */
    /* JADX WARN: Multi-variable type inference failed */
    public ContiguousSet<C> headSet(C toElement) {
        return headSetImpl((Comparable) Preconditions.checkNotNull(toElement), false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.ContiguousSet<C extends java.lang.Comparable> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible("NavigableSet")
    public ContiguousSet<C> headSet(C toElement, boolean inclusive) {
        return headSetImpl((Comparable) Preconditions.checkNotNull(toElement), inclusive);
    }

    public ContiguousSet<C> subSet(C fromElement, C toElement) {
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        Preconditions.checkArgument(comparator().compare(fromElement, toElement) <= 0);
        return subSetImpl((Comparable) fromElement, true, (Comparable) toElement, false);
    }

    @GwtIncompatible("NavigableSet")
    public ContiguousSet<C> subSet(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        Preconditions.checkArgument(comparator().compare(fromElement, toElement) <= 0);
        return subSetImpl((Comparable) fromElement, fromInclusive, (Comparable) toElement, toInclusive);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.ContiguousSet<C extends java.lang.Comparable> */
    /* JADX WARN: Multi-variable type inference failed */
    public ContiguousSet<C> tailSet(C fromElement) {
        return tailSetImpl((Comparable) Preconditions.checkNotNull(fromElement), true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.ContiguousSet<C extends java.lang.Comparable> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible("NavigableSet")
    public ContiguousSet<C> tailSet(C fromElement, boolean inclusive) {
        return tailSetImpl((Comparable) Preconditions.checkNotNull(fromElement), inclusive);
    }

    public String toString() {
        return range().toString();
    }

    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }
}
