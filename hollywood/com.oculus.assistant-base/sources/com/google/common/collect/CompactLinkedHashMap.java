package com.google.common.collect;

import java.util.Arrays;

public class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    public transient int A00;
    public transient int A01;
    public transient long[] A02;
    public final boolean accessOrder = false;

    public CompactLinkedHashMap() {
        super(3);
    }

    public static void A02(CompactLinkedHashMap compactLinkedHashMap, int i, int i2) {
        if (i == -2) {
            compactLinkedHashMap.A00 = i2;
        } else {
            long[] jArr = compactLinkedHashMap.A02;
            jArr[i] = (jArr[i] & -4294967296L) | (((long) i2) & 4294967295L);
        }
        if (i2 == -2) {
            compactLinkedHashMap.A01 = i;
            return;
        }
        long[] jArr2 = compactLinkedHashMap.A02;
        jArr2[i2] = (jArr2[i2] & 4294967295L) | (((long) i) << 32);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A05(int i) {
        int size = size() - 1;
        long[] jArr = this.A02;
        A02(this, (int) (jArr[i] >>> 32), A03(i));
        if (i < size) {
            A02(this, (int) (jArr[size] >>> 32), i);
            A02(this, i, A03(size));
        }
        super.A05(i);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A06(int i) {
        super.A06(i);
        this.A02 = Arrays.copyOf(this.A02, i);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A07(int i, float f) {
        super.A07(i, f);
        this.A00 = -2;
        this.A01 = -2;
        long[] jArr = new long[i];
        this.A02 = jArr;
        Arrays.fill(jArr, -1L);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void A08(int i, Object obj, Object obj2, int i2) {
        super.A08(i, obj, obj2, i2);
        A02(this, this.A01, i);
        A02(this, i, -2);
    }

    @Override // com.google.common.collect.CompactHashMap
    public final void clear() {
        super.clear();
        this.A00 = -2;
        this.A01 = -2;
    }
}
