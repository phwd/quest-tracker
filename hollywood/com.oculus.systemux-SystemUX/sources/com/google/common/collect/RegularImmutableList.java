package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Spliterator;
import java.util.Spliterators;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> EMPTY = new RegularImmutableList(new Object[0]);
    @VisibleForTesting
    final transient Object[] array;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    RegularImmutableList(Object[] objArr) {
        this.array = objArr;
    }

    public int size() {
        return this.array.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        Object[] objArr2 = this.array;
        System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
        return i + this.array.length;
    }

    @Override // java.util.List
    public E get(int i) {
        return (E) this.array[i];
    }

    @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
    public UnmodifiableListIterator<E> listIterator(int i) {
        Object[] objArr = this.array;
        return Iterators.forArray(objArr, 0, objArr.length, i);
    }

    @Override // java.util.List, com.google.common.collect.ImmutableList, java.util.Collection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this.array, 1296);
    }
}
