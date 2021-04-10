package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtIncompatible
public final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    static final ImmutableSortedMultiset<Comparable> NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(Ordering.natural());
    private static final long[] ZERO_CUMULATIVE_COUNTS = {0};
    private final transient long[] cumulativeCounts;
    @VisibleForTesting
    final transient RegularImmutableSortedSet<E> elementSet;
    private final transient int length;
    private final transient int offset;

    RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> elementSet2, long[] cumulativeCounts2, int offset2, int length2) {
        this.elementSet = elementSet2;
        this.cumulativeCounts = cumulativeCounts2;
        this.offset = offset2;
        this.length = length2;
    }

    private int getCount(int index) {
        return (int) (this.cumulativeCounts[(this.offset + index) + 1] - this.cumulativeCounts[this.offset + index]);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int index) {
        return Multisets.immutableEntry(this.elementSet.asList().get(index), getCount(index));
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(0);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(this.length - 1);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object element) {
        int index = this.elementSet.indexOf(element);
        if (index >= 0) {
            return getCount(index);
        }
        return 0;
    }

    @Override // com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.cumulativeCounts[this.offset + this.length] - this.cumulativeCounts[this.offset]);
    }

    @Override // com.google.common.collect.SortedMultisetBridge, com.google.common.collect.Multiset, com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        return getSubMultiset(0, this.elementSet.headIndex(upperBound, Preconditions.checkNotNull(boundType) == BoundType.CLOSED));
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        return getSubMultiset(this.elementSet.tailIndex(lowerBound, Preconditions.checkNotNull(boundType) == BoundType.CLOSED), this.length);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMultiset<E> getSubMultiset(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, this.length);
        if (from == to) {
            return emptyMultiset(comparator());
        }
        return (from == 0 && to == this.length) ? this : new RegularImmutableSortedMultiset<>(this.elementSet.getSubSet(from, to), this.cumulativeCounts, this.offset + from, to - from);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length + -1;
    }
}
