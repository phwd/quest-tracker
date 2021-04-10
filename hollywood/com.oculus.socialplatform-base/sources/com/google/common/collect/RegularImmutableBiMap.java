package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.RegularImmutableMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    public static final RegularImmutableBiMap<Object, Object> A05 = new RegularImmutableBiMap<>();
    @VisibleForTesting
    public final transient Object[] A00;
    public final transient int A01;
    public final transient int A02;
    public final transient RegularImmutableBiMap<V, K> A03;
    public final transient int[] A04;

    @Override // com.google.common.collect.ImmutableMap
    public final boolean A0C() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<K> A0A() {
        return new RegularImmutableMap.KeySet(this, new RegularImmutableMap.KeysOrValuesAsList(this.A00, this.A01, this.A02));
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<Map.Entry<K, V>> A0B() {
        return new RegularImmutableMap.EntrySet(this, this.A00, this.A01, this.A02);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public final V get(@NullableDecl Object obj) {
        return (V) RegularImmutableMap.A01(this.A04, this.A00, this.A02, this.A01, obj);
    }

    @Override // com.google.common.collect.ImmutableBiMap
    public final ImmutableBiMap<V, K> A0D() {
        return this.A03;
    }

    public final int size() {
        return this.A02;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.RegularImmutableBiMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public RegularImmutableBiMap() {
        this.A04 = null;
        this.A00 = new Object[0];
        this.A01 = 0;
        this.A02 = 0;
        this.A03 = this;
    }

    public RegularImmutableBiMap(int[] iArr, Object[] objArr, int i, RegularImmutableBiMap<V, K> regularImmutableBiMap) {
        this.A04 = iArr;
        this.A00 = objArr;
        this.A01 = 1;
        this.A02 = i;
        this.A03 = regularImmutableBiMap;
    }

    public RegularImmutableBiMap(Object[] objArr, int i) {
        int i2;
        this.A00 = objArr;
        this.A02 = i;
        this.A01 = 0;
        if (i >= 2) {
            i2 = ImmutableSet.A02(i);
        } else {
            i2 = 0;
        }
        this.A04 = RegularImmutableMap.A02(objArr, i, i2, 0);
        this.A03 = new RegularImmutableBiMap<>(RegularImmutableMap.A02(objArr, i, i2, 1), objArr, i, this);
    }
}
