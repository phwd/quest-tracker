package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingSortedSetMultimap<K, V> extends ForwardingSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap
    public abstract SortedSetMultimap<K, V> delegate();

    protected ForwardingSortedSetMultimap() {
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    public SortedSet<V> get(@Nullable K key) {
        return delegate().get((Object) key);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    public SortedSet<V> removeAll(@Nullable Object key) {
        return delegate().removeAll(key);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
        return delegate().replaceValues((Object) key, (Iterable) values);
    }

    @Override // com.google.common.collect.SortedSetMultimap
    public Comparator<? super V> valueComparator() {
        return delegate().valueComparator();
    }
}
