package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements BiMap<K, V> {
    private static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];

    @Override // com.google.common.collect.BiMap
    public abstract ImmutableBiMap<V, K> inverse();

    public static <K, V> ImmutableBiMap<K, V> of() {
        return EmptyImmutableBiMap.INSTANCE;
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k1, V v1) {
        return new SingletonImmutableBiMap(k1, v1);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k1, V v1, K k2, V v2) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2)});
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3)});
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableBiMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(K key, V value) {
            super.put((Object) key, (Object) value);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableBiMap.of();
            }
            if (i != 1) {
                return new RegularImmutableBiMap(this.size, this.entries);
            }
            return ImmutableBiMap.of(this.entries[0].getKey(), this.entries[0].getValue());
        }
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> bimap = (ImmutableBiMap) map;
            if (!bimap.isPartialView()) {
                return bimap;
            }
        }
        Map.Entry<K, V>[] entries = (Map.Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        int length = entries.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return new RegularImmutableBiMap((Map.Entry<?, ?>[]) entries);
        }
        Map.Entry<K, V> entry = entries[0];
        return of((Object) entry.getKey(), (Object) entry.getValue());
    }

    ImmutableBiMap() {
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ImmutableMap, com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<V> values() {
        return inverse().keySet();
    }

    @Override // com.google.common.collect.BiMap
    @Deprecated
    public V forcePut(K k, V v) {
        throw new UnsupportedOperationException();
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> bimap) {
            super(bimap);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return createMap(new Builder<>());
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
