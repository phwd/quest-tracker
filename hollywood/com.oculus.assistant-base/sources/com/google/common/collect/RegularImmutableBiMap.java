package com.google.common.collect;

public final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    public static final RegularImmutableBiMap A05 = new RegularImmutableBiMap();
    public final transient int A00;
    public final transient int A01;
    public final transient RegularImmutableBiMap A02;
    public final transient Object[] A03;
    public final transient int[] A04;

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public final Object get(Object obj) {
        return RegularImmutableMap.A00(this.A04, this.A03, this.A01, this.A00, obj);
    }

    public final int size() {
        return this.A01;
    }

    public RegularImmutableBiMap() {
        this.A04 = null;
        this.A03 = new Object[0];
        this.A00 = 0;
        this.A01 = 0;
        this.A02 = this;
    }

    public RegularImmutableBiMap(int[] iArr, Object[] objArr, int i, RegularImmutableBiMap regularImmutableBiMap) {
        this.A04 = iArr;
        this.A03 = objArr;
        this.A00 = 1;
        this.A01 = i;
        this.A02 = regularImmutableBiMap;
    }

    public RegularImmutableBiMap(Object[] objArr, int i) {
        int i2;
        this.A03 = objArr;
        this.A01 = i;
        this.A00 = 0;
        if (i >= 2) {
            i2 = ImmutableSet.A02(i);
        } else {
            i2 = 0;
        }
        this.A04 = RegularImmutableMap.A01(objArr, i, i2, 0);
        this.A02 = new RegularImmutableBiMap(RegularImmutableMap.A01(objArr, i, i2, 1), objArr, i, this);
    }
}
