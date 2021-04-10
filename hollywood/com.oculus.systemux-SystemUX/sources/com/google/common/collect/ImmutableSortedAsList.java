package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.IntFunction;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
final class ImmutableSortedAsList<E> extends RegularImmutableAsList<E> implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> immutableSortedSet, ImmutableList<E> immutableList) {
        super(immutableSortedSet, immutableList);
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
    @GwtIncompatible
    public int indexOf(@NullableDecl Object obj) {
        int indexOf = delegateCollection().indexOf(obj);
        if (indexOf < 0 || !get(indexOf).equals(obj)) {
            return -1;
        }
        return indexOf;
    }

    @Override // com.google.common.collect.ImmutableList
    @GwtIncompatible
    public int lastIndexOf(@NullableDecl Object obj) {
        return indexOf(obj);
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableAsList, com.google.common.collect.ImmutableCollection
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList
    @GwtIncompatible
    public ImmutableList<E> subListUnchecked(int i, int i2) {
        return new RegularImmutableSortedSet(super.subListUnchecked(i, i2), comparator()).asList();
    }

    @Override // java.util.List, com.google.common.collect.ImmutableList, java.util.Collection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        int size = size();
        ImmutableList<? extends E> delegateList = delegateList();
        delegateList.getClass();
        return CollectSpliterators.indexed(size, 1301, new IntFunction() {
            /* class com.google.common.collect.$$Lambda$J9zVGv6YXVkAf1JUJc7fbdQoC3U */

            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ImmutableList.this.get(i);
            }
        }, comparator());
    }
}
