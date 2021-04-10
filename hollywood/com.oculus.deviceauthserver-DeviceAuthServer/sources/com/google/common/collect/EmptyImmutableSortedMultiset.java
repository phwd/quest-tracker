package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import java.util.Collection;
import java.util.Comparator;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class EmptyImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private final ImmutableSortedSet<E> elementSet;

    EmptyImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return null;
    }

    @Override // com.google.common.collect.Multiset
    public int count(@Nullable Object element) {
        return 0;
    }

    @Override // com.google.common.collect.Multiset, java.util.Collection, java.util.AbstractCollection, com.google.common.collect.ImmutableMultiset
    public boolean containsAll(Collection<?> targets) {
        return targets.isEmpty();
    }

    public int size() {
        return 0;
    }

    @Override // com.google.common.collect.SortedMultisetBridge, com.google.common.collect.Multiset, com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int index) {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        Preconditions.checkNotNull(upperBound);
        Preconditions.checkNotNull(boundType);
        return this;
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        Preconditions.checkNotNull(lowerBound);
        Preconditions.checkNotNull(boundType);
        return this;
    }

    @Override // com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable, java.util.AbstractCollection, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable, com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableMultiset
    public UnmodifiableIterator<E> iterator() {
        return Iterators.emptyIterator();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.ImmutableMultiset
    public boolean equals(@Nullable Object object) {
        if (object instanceof Multiset) {
            return ((Multiset) object).isEmpty();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableMultiset
    public int copyIntoArray(Object[] dst, int offset) {
        return offset;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        return ImmutableList.of();
    }
}
