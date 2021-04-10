package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class ImmutableMapKeySet<K, V> extends ImmutableSet<K> {
    private final ImmutableMap<K, V> map;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    ImmutableMapKeySet(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    public int size() {
        return this.map.size();
    }

    /* JADX DEBUG: Type inference failed for r0v2. Raw type applied. Possible types: com.google.common.collect.UnmodifiableIterator<E>, com.google.common.collect.UnmodifiableIterator<K> */
    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<K> iterator() {
        return (UnmodifiableIterator<E>) asList().iterator();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<K> createAsList() {
        final ImmutableList<Map.Entry<K, V>> asList = this.map.entrySet().asList();
        return new ImmutableAsList<K>() {
            /* class com.google.common.collect.ImmutableMapKeySet.AnonymousClass1 */

            @Override // java.util.List
            public K get(int i) {
                return (K) ((Map.Entry) asList.get(i)).getKey();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableAsList
            public ImmutableCollection<K> delegateCollection() {
                return ImmutableMapKeySet.this;
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }

    private static class KeySetSerializedForm<K> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, ?> map;

        KeySetSerializedForm(ImmutableMap<K, ?> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.keySet();
        }
    }
}
