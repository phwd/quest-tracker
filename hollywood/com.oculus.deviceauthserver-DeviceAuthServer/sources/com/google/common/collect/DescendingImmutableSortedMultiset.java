package com.google.common.collect;

import com.google.common.collect.Multiset;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class DescendingImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private final transient ImmutableSortedMultiset<E> forward;

    DescendingImmutableSortedMultiset(ImmutableSortedMultiset<E> forward2) {
        this.forward = forward2;
    }

    @Override // com.google.common.collect.Multiset
    public int count(@Nullable Object element) {
        return this.forward.count(element);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return this.forward.lastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return this.forward.firstEntry();
    }

    public int size() {
        return this.forward.size();
    }

    @Override // com.google.common.collect.SortedMultisetBridge, com.google.common.collect.Multiset, com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedSet<E> elementSet() {
        return this.forward.elementSet().descendingSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int index) {
        return this.forward.entrySet().asList().reverse().get(index);
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> descendingMultiset() {
        return this.forward;
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        return this.forward.tailMultiset((Object) upperBound, boundType).descendingMultiset();
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableSortedMultiset
    public ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        return this.forward.headMultiset((Object) lowerBound, boundType).descendingMultiset();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.forward.isPartialView();
    }
}
