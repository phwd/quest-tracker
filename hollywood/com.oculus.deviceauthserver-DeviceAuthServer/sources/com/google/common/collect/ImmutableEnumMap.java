package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Enum;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class ImmutableEnumMap<K extends Enum<K>, V> extends ImmutableMap<K, V> {
    private final transient EnumMap<K, V> delegate;

    static <K extends Enum<K>, V> ImmutableMap<K, V> asImmutable(EnumMap<K, V> map) {
        int size = map.size();
        if (size == 0) {
            return ImmutableMap.of();
        }
        if (size != 1) {
            return new ImmutableEnumMap(map);
        }
        Map.Entry<K, V> entry = (Map.Entry) Iterables.getOnlyElement(map.entrySet());
        return ImmutableMap.of(entry.getKey(), entry.getValue());
    }

    private ImmutableEnumMap(EnumMap<K, V> delegate2) {
        this.delegate = delegate2;
        Preconditions.checkArgument(!delegate2.isEmpty());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<K> createKeySet() {
        return new ImmutableSet<K>() {
            /* class com.google.common.collect.ImmutableEnumMap.AnonymousClass1 */

            @Override // com.google.common.collect.ImmutableCollection
            public boolean contains(Object object) {
                return ImmutableEnumMap.this.delegate.containsKey(object);
            }

            public int size() {
                return ImmutableEnumMap.this.size();
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<K> iterator() {
                return Iterators.unmodifiableIterator(ImmutableEnumMap.this.delegate.keySet().iterator());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }
        };
    }

    public int size() {
        return this.delegate.size();
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean containsKey(@Nullable Object key) {
        return this.delegate.containsKey(key);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object key) {
        return this.delegate.get(key);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet<K, V>() {
            /* class com.google.common.collect.ImmutableEnumMap.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableMapEntrySet
            public ImmutableMap<K, V> map() {
                return ImmutableEnumMap.this;
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return new UnmodifiableIterator<Map.Entry<K, V>>() {
                    /* class com.google.common.collect.ImmutableEnumMap.AnonymousClass2.AnonymousClass1 */
                    private final Iterator<Map.Entry<K, V>> backingIterator = ImmutableEnumMap.this.delegate.entrySet().iterator();

                    public boolean hasNext() {
                        return this.backingIterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Map.Entry<K, V> next() {
                        Map.Entry<K, V> next = this.backingIterator.next();
                        return Maps.immutableEntry(next.getKey(), next.getValue());
                    }
                };
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new EnumSerializedForm(this.delegate);
    }

    private static class EnumSerializedForm<K extends Enum<K>, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final EnumMap<K, V> delegate;

        EnumSerializedForm(EnumMap<K, V> delegate2) {
            this.delegate = delegate2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableEnumMap(this.delegate);
        }
    }
}
