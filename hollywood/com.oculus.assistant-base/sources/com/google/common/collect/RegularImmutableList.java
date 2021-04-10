package com.google.common.collect;

import com.google.common.base.Preconditions;

public class RegularImmutableList<E> extends ImmutableList<E> {
    public static final ImmutableList A02 = new RegularImmutableList(new Object[0], 0);
    public final transient Object[] A00;
    public final transient int A01;

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A0B() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public final int A0C(Object[] objArr, int i) {
        Object[] objArr2 = this.A00;
        int i2 = this.A01;
        System.arraycopy(objArr2, 0, objArr, i, i2);
        return i + i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Preconditions.checkElementIndex(i, this.A01);
        return this.A00[i];
    }

    public RegularImmutableList(Object[] objArr, int i) {
        this.A00 = objArr;
        this.A01 = i;
    }

    public final int size() {
        return this.A01;
    }
}
