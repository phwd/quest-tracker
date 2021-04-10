package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Comparator;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class ImmutableSortedAsList<E> extends RegularImmutableAsList<E> implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> backingSet, ImmutableList<E> backingList) {
        super(backingSet, backingList);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableAsList, com.google.common.collect.RegularImmutableAsList
    public ImmutableSortedSet<E> delegateCollection() {
        return (ImmutableSortedSet) super.delegateCollection();
    }

    @Override // com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return delegateCollection().comparator();
    }

    @Override // com.google.common.collect.ImmutableList
    @GwtIncompatible("ImmutableSortedSet.indexOf")
    public int indexOf(@Nullable Object target) {
        int index = delegateCollection().indexOf(target);
        if (index < 0 || !get(index).equals(target)) {
            return -1;
        }
        return index;
    }

    @Override // com.google.common.collect.ImmutableList
    @GwtIncompatible("ImmutableSortedSet.indexOf")
    public int lastIndexOf(@Nullable Object target) {
        return indexOf(target);
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableAsList, com.google.common.collect.ImmutableCollection
    public boolean contains(Object target) {
        return indexOf(target) >= 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList
    @GwtIncompatible("super.subListUnchecked does not exist; inherited subList is valid if slow")
    public ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        return new RegularImmutableSortedSet(super.subListUnchecked(fromIndex, toIndex), comparator()).asList();
    }
}
