package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractListMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 6588350623831699109L;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public abstract List<V> createCollection();

    protected AbstractListMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public List<V> createUnmodifiableEmptyCollection() {
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> get(@Nullable K key) {
        return (List) super.get((Object) key);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> removeAll(@Nullable Object key) {
        return (List) super.removeAll(key);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        return (List) super.replaceValues((Object) key, (Iterable) values);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean put(@Nullable K key, @Nullable V value) {
        return super.put(key, value);
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean equals(@Nullable Object object) {
        return super.equals(object);
    }
}
