package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
final class EmptyContiguousSet<C extends Comparable> extends ContiguousSet<C> {
    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object obj) {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet
    public int hashCode() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> headSetImpl(C c, boolean z) {
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    public int indexOf(Object obj) {
        return -1;
    }

    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet) {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    @GwtIncompatible
    public boolean isHashCodeFast() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> subSetImpl(C c, boolean z, C c2, boolean z2) {
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> tailSetImpl(C c, boolean z) {
        return this;
    }

    @Override // com.google.common.collect.ContiguousSet
    public String toString() {
        return "[]";
    }

    EmptyContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(discreteDomain);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public C first() {
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public C last() {
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ContiguousSet
    public Range<C> range() {
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ContiguousSet
    public Range<C> range(BoundType boundType, BoundType boundType2) {
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.util.NavigableSet, java.lang.Iterable, java.util.AbstractCollection, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<C> iterator() {
        return Iterators.emptyIterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return Iterators.emptyIterator();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public ImmutableList<C> asList() {
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    @GwtIncompatible
    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private static final long serialVersionUID = 0;
        private final DiscreteDomain<C> domain;

        private SerializedForm(DiscreteDomain<C> discreteDomain) {
            this.domain = discreteDomain;
        }

        private Object readResolve() {
            return new EmptyContiguousSet(this.domain);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.domain);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ContiguousSet
    @GwtIncompatible
    public ImmutableSortedSet<C> createDescendingSet() {
        return ImmutableSortedSet.emptySet(Ordering.natural().reverse());
    }
}
