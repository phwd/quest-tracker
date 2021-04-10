package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public final class WellBehavedMap<K, V> extends ForwardingMap<K, V> {
    private final Map<K, V> delegate;
    @MonotonicNonNullDecl
    private Set<Map.Entry<K, V>> entrySet;

    private WellBehavedMap(Map<K, V> map) {
        this.delegate = map;
    }

    static <K, V> WellBehavedMap<K, V> wrap(Map<K, V> map) {
        return new WellBehavedMap<>(map);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<K, V> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    private final class EntrySet extends Maps.EntrySet<K, V> {
        private EntrySet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.EntrySet
        public Map<K, V> map() {
            return WellBehavedMap.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new TransformedIterator<K, Map.Entry<K, V>>(WellBehavedMap.this.keySet().iterator()) {
                /* class com.google.common.collect.WellBehavedMap.EntrySet.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.TransformedIterator
                public Map.Entry<K, V> transform(final K k) {
                    return new AbstractMapEntry<K, V>() {
                        /* class com.google.common.collect.WellBehavedMap.EntrySet.AnonymousClass1.AnonymousClass1 */

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public K getKey() {
                            return (K) k;
                        }

                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public V getValue() {
                            return (V) WellBehavedMap.this.get(k);
                        }

                        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.collect.WellBehavedMap */
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                        public V setValue(V v) {
                            return (V) WellBehavedMap.this.put(k, v);
                        }
                    };
                }
            };
        }
    }
}
