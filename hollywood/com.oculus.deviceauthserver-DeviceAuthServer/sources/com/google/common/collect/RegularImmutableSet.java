package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableSet<E> extends ImmutableSet<E> {
    private final Object[] elements;
    private final transient int hashCode;
    private final transient int mask;
    @VisibleForTesting
    final transient Object[] table;

    RegularImmutableSet(Object[] elements2, int hashCode2, Object[] table2, int mask2) {
        this.elements = elements2;
        this.table = table2;
        this.mask = mask2;
        this.hashCode = hashCode2;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object target) {
        if (target == null) {
            return false;
        }
        int i = Hashing.smear(target.hashCode());
        while (true) {
            Object candidate = this.table[this.mask & i];
            if (candidate == null) {
                return false;
            }
            if (candidate.equals(target)) {
                return true;
            }
            i++;
        }
    }

    public int size() {
        return this.elements.length;
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.forArray(this.elements);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        Object[] objArr = this.elements;
        System.arraycopy(objArr, 0, dst, offset, objArr.length);
        return this.elements.length + offset;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> createAsList() {
        return new RegularImmutableAsList(this, this.elements);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet
    public int hashCode() {
        return this.hashCode;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return true;
    }
}
