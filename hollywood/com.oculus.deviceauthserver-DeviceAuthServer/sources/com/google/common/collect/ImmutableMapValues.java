package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    private final ImmutableMap<K, V> map;

    ImmutableMapValues(ImmutableMap<K, V> map2) {
        this.map = map2;
    }

    public int size() {
        return this.map.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public UnmodifiableIterator<V> iterator() {
        return Maps.valueIterator((UnmodifiableIterator) this.map.entrySet().iterator());
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@Nullable Object object) {
        return object != null && Iterators.contains(iterator(), object);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<V> createAsList() {
        final ImmutableList<Map.Entry<K, V>> entryList = this.map.entrySet().asList();
        return new ImmutableAsList<V>() {
            /* class com.google.common.collect.ImmutableMapValues.AnonymousClass1 */

            @Override // java.util.List
            public V get(int index) {
                return (V) ((Map.Entry) entryList.get(index)).getValue();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableAsList
            public ImmutableCollection<V> delegateCollection() {
                return ImmutableMapValues.this;
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible("serialization")
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }

    @GwtIncompatible("serialization")
    private static class SerializedForm<V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<?, V> map;

        SerializedForm(ImmutableMap<?, V> map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.values();
        }
    }
}
