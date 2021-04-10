package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtIncompatible
public class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient long[] A00;
    public transient int A01;
    public transient int A02;
    public final boolean accessOrder = false;

    public CompactLinkedHashMap() {
        super(3);
    }

    private void A00(int i, int i2) {
        if (i == -2) {
            this.A01 = i2;
        } else {
            long[] jArr = this.A00;
            jArr[i] = (jArr[i] & -4294967296L) | (((long) i2) & 4294967295L);
        }
        if (i2 == -2) {
            this.A02 = i;
            return;
        }
        long[] jArr2 = this.A00;
        jArr2[i2] = (jArr2[i2] & 4294967295L) | (((long) i) << 32);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final int A04(int i) {
        return (int) this.A00[i];
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A06(int i) {
        if (this.accessOrder) {
            A00((int) (this.A00[i] >>> 32), A04(i));
            A00(this.A02, i);
            A00(i, -2);
            super.A02++;
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    public final int A03() {
        return this.A01;
    }

    @Override // com.google.common.collect.CompactHashMap
    public final int A05(int i, int i2) {
        if (i >= size()) {
            return i2;
        }
        return i;
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A07(int i) {
        int size = size() - 1;
        long[] jArr = this.A00;
        A00((int) (jArr[i] >>> 32), A04(i));
        if (i < size) {
            A00((int) (jArr[size] >>> 32), i);
            A00(i, A04(size));
        }
        super.A07(i);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A08(int i) {
        super.A08(i);
        this.A00 = Arrays.copyOf(this.A00, i);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A09(int i, float f) {
        super.A09(i, f);
        this.A01 = -2;
        this.A02 = -2;
        long[] jArr = new long[i];
        this.A00 = jArr;
        Arrays.fill(jArr, -1L);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A0A(int i, K k, V v, int i2) {
        super.A0A(i, k, v, i2);
        A00(this.A02, i);
        A00(i, -2);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void clear() {
        super.clear();
        this.A01 = -2;
        this.A02 = -2;
    }
}
