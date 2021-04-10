package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class SingletonImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    transient ImmutableBiMap<V, K> inverse;
    final transient K singleKey;
    final transient V singleValue;

    SingletonImmutableBiMap(K singleKey2, V singleValue2) {
        CollectPreconditions.checkEntryNotNull(singleKey2, singleValue2);
        this.singleKey = singleKey2;
        this.singleValue = singleValue2;
    }

    private SingletonImmutableBiMap(K singleKey2, V singleValue2, ImmutableBiMap<V, K> inverse2) {
        this.singleKey = singleKey2;
        this.singleValue = singleValue2;
        this.inverse = inverse2;
    }

    SingletonImmutableBiMap(Map.Entry<? extends K, ? extends V> entry) {
        this(entry.getKey(), entry.getValue());
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@Nullable Object key) {
        if (this.singleKey.equals(key)) {
            return this.singleValue;
        }
        return null;
    }

    public int size() {
        return 1;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean containsKey(@Nullable Object key) {
        return this.singleKey.equals(key);
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean containsValue(@Nullable Object value) {
        return this.singleValue.equals(value);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return ImmutableSet.of(Maps.immutableEntry(this.singleKey, this.singleValue));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<K> createKeySet() {
        return ImmutableSet.of(this.singleKey);
    }

    @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> result = this.inverse;
        if (result != null) {
            return result;
        }
        SingletonImmutableBiMap singletonImmutableBiMap = new SingletonImmutableBiMap(this.singleValue, this.singleKey, this);
        this.inverse = singletonImmutableBiMap;
        return singletonImmutableBiMap;
    }
}
