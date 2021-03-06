package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class ForwardingSortedSetMultimap<K, V> extends ForwardingSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap
    public abstract SortedSetMultimap<K, V> delegate();

    protected ForwardingSortedSetMultimap() {
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap, com.google.common.collect.SetMultimap
    public SortedSet<V> get(@NullableDecl K k) {
        return delegate().get((Object) k);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap, com.google.common.collect.SetMultimap
    public SortedSet<V> removeAll(@NullableDecl Object obj) {
        return delegate().removeAll(obj);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.ForwardingSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap, com.google.common.collect.SetMultimap
    public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return delegate().replaceValues((Object) k, (Iterable) iterable);
    }

    @Override // com.google.common.collect.SortedSetMultimap
    public Comparator<? super V> valueComparator() {
        return delegate().valueComparator();
    }
}
