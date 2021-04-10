package com.google.common.collect;

import X.Qc;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.oculus.common.build.BuildConfig;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class RegularImmutableSet<E> extends ImmutableSet<E> {
    public static final RegularImmutableSet<Object> A05 = new RegularImmutableSet<>(new Object[0], 0, null, 0, 0);
    @VisibleForTesting
    public final transient Object[] A00;
    public final transient int A01;
    @VisibleForTesting
    public final transient Object[] A02;
    public final transient int A03;
    public final transient int A04;

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.A00;
        if (obj != null && objArr != null) {
            int A022 = Qc.A02(obj);
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
