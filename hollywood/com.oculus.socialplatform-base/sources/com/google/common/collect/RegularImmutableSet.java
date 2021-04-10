package com.google.common.collect;

import X.AbstractC05710wh;
import X.C05150uI;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableSet<E> extends ImmutableSet<E> {
    public static final RegularImmutableSet<Object> A05 = new RegularImmutableSet<>(new Object[0], 0, null, 0, 0);
    @VisibleForTesting
    public final transient Object[] A00;
    @VisibleForTesting
    public final transient Object[] A01;
    public final transient int A02;
    public final transient int A03;
    public final transient int A04;

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A0F() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final boolean A0K() {
        return true;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int A0G(Object[] objArr, int i) {
        Object[] objArr2 = this.A00;
        int i2 = this.A04;
        System.arraycopy(objArr2, 0, objArr, i, i2);
        return i + i2;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A0J() {
        return ImmutableList.A0E(this.A00, this.A04);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.A01;
        if (obj != null && objArr != null) {
            int A022 = C05150uI.A02(obj);
            while (true) {
                int i = A022 & this.A03;
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
        this.A00 = objArr;
        this.A01 = objArr2;
        this.A03 = i2;
        this.A02 = i;
        this.A04 = i3;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final AbstractC05710wh<E> A0I() {
        return A0H().iterator();
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        return this.A02;
    }

    public final int size() {
        return this.A04;
    }
}
