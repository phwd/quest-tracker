package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class ImmutableMapKeySet<K, V> extends ImmutableSet<K> {
    private final ImmutableMap<K, V> map;

    ImmutableMapKeySet(ImmutableMap<K, V> map2) {
        this.map = map2;
    }

    public int size() {
        return this.map.size();
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: com.google.common.collect.UnmodifiableIterator<E>, com.google.common.collect.UnmodifiableIterator<K> */
    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<K> iterator() {
        return (UnmodifiableIterator<E>) asList().iterator();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@Nullable Object object) {
        return this.map.containsKey(object);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<K> createAsList() {
        final ImmutableList<Map.Entry<K, V>> entryList = this.map.entrySet().asList();
        return new ImmutableAsList<K>() {
            /* class com.google.common.collect.ImmutableMapKeySet.AnonymousClass1 */

            @Override // java.util.List
            public K get(int index) {
                return (K) ((Map.Entry) entryList.get(index)).getKey();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableAsList
            public ImmutableCollection<K> delegateCollection() {
                return ImmutableMapKeySet.this;
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @GwtIncompatible("serialization")
    public Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }

    @GwtIncompatible("serialization")
    private static class KeySetSerializedForm<K> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, ?> map;

        KeySetSerializedForm(ImmutableMap<K, ?> map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.keySet();
        }
    }
}
