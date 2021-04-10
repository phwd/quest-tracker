package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible
public interface SortedSetMultimap<K, V> extends SetMultimap<K, V> {
    @Override // com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    Map<K, Collection<V>> asMap();

    @Override // com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    SortedSet<V> get(@Nullable K k);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    SortedSet<V> removeAll(@Nullable Object obj);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
    SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable);

    Comparator<? super V> valueComparator();
}
