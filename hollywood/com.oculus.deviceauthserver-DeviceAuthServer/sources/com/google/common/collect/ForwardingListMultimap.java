package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingListMultimap<K, V> extends ForwardingMultimap<K, V> implements ListMultimap<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
    public abstract ListMultimap<K, V> delegate();

    protected ForwardingListMultimap() {
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> get(@Nullable K key) {
        return delegate().get((Object) key);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> removeAll(@Nullable Object key) {
        return delegate().removeAll(key);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> replaceValues(K key, Iterable<? extends V> values) {
        return delegate().replaceValues((Object) key, (Iterable) values);
    }
}
