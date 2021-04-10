package com.google.common.collect;

import com.google.common.base.Preconditions;

/* access modifiers changed from: package-private */
public class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> EMPTY = new RegularImmutableList(new Object[0], 0);
    final transient Object[] array;
    private final transient int size;

    RegularImmutableList(Object[] array2, int size2) {
        this.array = array2;
        this.size = size2;
    }

    public int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int dstOff) {
        System.arraycopy(this.array, 0, dst, dstOff, this.size);
        return this.size + dstOff;
    }

    @Override // java.util.List
    public E get(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return (E) this.array[index];
    }
}
