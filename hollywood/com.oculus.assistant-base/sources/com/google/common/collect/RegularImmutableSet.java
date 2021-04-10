package com.google.common.collect;

import X.Tw;

public final class RegularImmutableSet<E> extends ImmutableSet<E> {
    public static final RegularImmutableSet A05 = new RegularImmutableSet(new Object[0], 0, null, 0, 0);
    public final transient Object[] A00;
    public final transient int A01;
    public final transient Object[] A02;
    public final transient int A03;
    public final transient int A04;

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(Object obj) {
        Object[] objArr = this.A00;
        if (obj != null && objArr != null) {
            int A022 = Tw.A02(obj);
            while (true) {
                int i = A022 & this.A04;
                Object obj2 = objArr[i];
                if (obj2 == null) {
                    break;
                } else if (obj2.equals(obj)) {
                    return true;
                } else {
                    A022 = i + 1;
                }
            }
        }
        return false;
    }

    public RegularImmutableSet(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.A02 = objArr;
        this.A00 = objArr2;
        this.A04 = i2;
        this.A03 = i;
        this.A01 = i3;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        return this.A03;
    }

    public final int size() {
        return this.A01;
    }
}
