package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    private static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private transient ImmutableSetMultimap<K, V> multimapView;
    private transient ImmutableCollection<V> values;

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    @Override // java.util.Map
    public abstract V get(@Nullable Object obj);

    /* access modifiers changed from: package-private */
    public abstract boolean isPartialView();

    public static <K, V> ImmutableMap<K, V> of() {
        return ImmutableBiMap.of();
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1) {
        return ImmutableBiMap.of((Object) k1, (Object) v1);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    static <K, V> ImmutableMapEntry.TerminalEntry<K, V> entryOf(K key, V value) {
        CollectPreconditions.checkEntryNotNull(key, value);
        return new ImmutableMapEntry.TerminalEntry<>(key, value);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    static void checkNoConflict(boolean safe, String conflictDescription, Map.Entry<?, ?> entry1, Map.Entry<?, ?> entry2) {
        if (!safe) {
            throw new IllegalArgumentException("Multiple entries with same " + conflictDescription + ": " + entry1 + " and " + entry2);
        }
    }

    public static class Builder<K, V> {
        ImmutableMapEntry.TerminalEntry<K, V>[] entries;
        int size;

        public Builder() {
            this(4);
        }

        Builder(int initialCapacity) {
            this.entries = new ImmutableMapEntry.TerminalEntry[initialCapacity];
            this.size = 0;
        }

        private void ensureCapacity(int minCapacity) {
            ImmutableMapEntry.TerminalEntry<K, V>[] terminalEntryArr = this.entries;
            if (minCapacity > terminalEntryArr.length) {
                this.entries = (ImmutableMapEntry.TerminalEntry[]) ObjectArrays.arraysCopyOf(terminalEntryArr, ImmutableCollection.Builder.expandedCapacity(terminalEntryArr.length, minCapacity));
            }
        }

        public Builder<K, V> put(K key, V value) {
            ensureCapacity(this.size + 1);
            ImmutableMapEntry.TerminalEntry<K, V> entry = ImmutableMap.entryOf(key, value);
            ImmutableMapEntry.TerminalEntry<K, V>[] terminalEntryArr = this.entries;
            int i = this.size;
            this.size = i + 1;
            terminalEntryArr[i] = entry;
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.ImmutableMap$Builder<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            ensureCapacity(this.size + map.size());
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry);
            }
            return this;
        }

        public ImmutableMap<K, V> build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableMap.of();
            }
            if (i != 1) {
                return new RegularImmutableMap(i, this.entries);
            }
            return ImmutableMap.of(this.entries[0].getKey(), this.entries[0].getValue());
        }
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap)) {
            ImmutableMap<K, V> kvMap = (ImmutableMap) map;
            if (!kvMap.isPartialView()) {
                return kvMap;
            }
        } else if (map instanceof EnumMap) {
            return copyOfEnumMapUnsafe(map);
        }
        Map.Entry<K, V>[] entries = (Map.Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        int length = entries.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return new RegularImmutableMap((Map.Entry<?, ?>[]) entries);
        }
        Map.Entry<K, V> onlyEntry = entries[0];
        return of(onlyEntry.getKey(), onlyEntry.getValue());
    }

    private static <K, V> ImmutableMap<K, V> copyOfEnumMapUnsafe(Map<? extends K, ? extends V> map) {
        return copyOfEnumMap((EnumMap) map);
    }

    private static <K extends Enum<K>, V> ImmutableMap<K, V> copyOfEnumMap(Map<K, ? extends V> original) {
        EnumMap<K, V> copy = new EnumMap<>(original);
        for (Map.Entry<K, V> entry : copy.entrySet()) {
            CollectPreconditions.checkEntryNotNull(entry.getKey(), entry.getValue());
        }
        return ImmutableEnumMap.asImmutable(copy);
    }

    ImmutableMap() {
    }

    @Override // java.util.Map
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@Nullable Object key) {
        return get(key) != null;
    }

    public boolean containsValue(@Nullable Object value) {
        return values().contains(value);
    }

    @Override // java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> result = this.entrySet;
        if (result != null) {
            return result;
        }
        ImmutableSet<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    @Override // java.util.Map
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        ImmutableSet<K> createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    @Override // java.util.Map
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> result = this.values;
        if (result != null) {
            return result;
        }
        ImmutableMapValues immutableMapValues = new ImmutableMapValues(this);
        this.values = immutableMapValues;
        return immutableMapValues;
    }

    @Beta
    public ImmutableSetMultimap<K, V> asMultimap() {
        ImmutableSetMultimap<K, V> result = this.multimapView;
        if (result != null) {
            return result;
        }
        ImmutableSetMultimap<K, V> createMultimapView = createMultimapView();
        this.multimapView = createMultimapView;
        return createMultimapView;
    }

    private ImmutableSetMultimap<K, V> createMultimapView() {
        ImmutableMap<K, ImmutableSet<V>> map = viewMapValuesAsSingletonSets();
        return new ImmutableSetMultimap<>(map, map.size(), null);
    }

    private ImmutableMap<K, ImmutableSet<V>> viewMapValuesAsSingletonSets() {
        return new MapViewOfValuesAsSingletonSets(this);
    }

    /* access modifiers changed from: private */
    public static final class MapViewOfValuesAsSingletonSets<K, V> extends ImmutableMap<K, ImmutableSet<V>> {
        private final ImmutableMap<K, V> delegate;

        MapViewOfValuesAsSingletonSets(ImmutableMap<K, V> delegate2) {
            this.delegate = (ImmutableMap) Preconditions.checkNotNull(delegate2);
        }

        public int size() {
            return this.delegate.size();
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean containsKey(@Nullable Object key) {
            return this.delegate.containsKey(key);
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public ImmutableSet<V> get(@Nullable Object key) {
            V outerValue = this.delegate.get(key);
            if (outerValue == null) {
                return null;
            }
            return ImmutableSet.of(outerValue);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<Map.Entry<K, ImmutableSet<V>>> createEntrySet() {
            return new ImmutableMapEntrySet<K, ImmutableSet<V>>() {
                /* class com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableMapEntrySet
                public ImmutableMap<K, ImmutableSet<V>> map() {
                    return MapViewOfValuesAsSingletonSets.this;
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
                public UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>> iterator() {
                    final Iterator<Map.Entry<K, V>> backingIterator = MapViewOfValuesAsSingletonSets.this.delegate.entrySet().iterator();
                    return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>() {
                        /* class com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.AnonymousClass1.AnonymousClass1 */

                        public boolean hasNext() {
                            return backingIterator.hasNext();
                        }

                        @Override // java.util.Iterator
                        public Map.Entry<K, ImmutableSet<V>> next() {
                            final Map.Entry<K, V> backingEntry = (Map.Entry) backingIterator.next();
                            return new AbstractMapEntry<K, ImmutableSet<V>>() {
                                /* class com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.AnonymousClass1.AnonymousClass1.AnonymousClass1 */

                                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                                public K getKey() {
                                    return (K) backingEntry.getKey();
                                }

                                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                                public ImmutableSet<V> getValue() {
                                    return ImmutableSet.of(backingEntry.getValue());
                                }
                            };
                        }
                    };
                }
            };
        }
    }

    public boolean equals(@Nullable Object object) {
        return Maps.equalsImpl(this, object);
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap<?, ?> map) {
            this.keys = new Object[map.size()];
            this.values = new Object[map.size()];
            int i = 0;
            UnmodifiableIterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<?, ?> entry = it.next();
                this.keys[i] = entry.getKey();
                this.values[i] = entry.getValue();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return createMap(new Builder<>());
        }

        /* access modifiers changed from: package-private */
        public Object createMap(Builder<Object, Object> builder) {
            int i = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i >= objArr.length) {
                    return builder.build();
                }
                builder.put(objArr[i], this.values[i]);
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
