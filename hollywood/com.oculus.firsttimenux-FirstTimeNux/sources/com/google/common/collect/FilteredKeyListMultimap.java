package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public final class FilteredKeyListMultimap<K, V> extends FilteredKeyMultimap<K, V> implements ListMultimap<K, V> {
    FilteredKeyListMultimap(ListMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        super(unfiltered, keyPredicate);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.FilteredMultimap
    public ListMultimap<K, V> unfiltered() {
        return (ListMultimap) super.unfiltered();
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap
    public List<V> get(K key) {
        return (List) super.get((Object) key);
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap
    public List<V> removeAll(@NullableDecl Object key) {
        return (List) super.removeAll(key);
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<V> replaceValues(K key, Iterable<? extends V> values) {
        return (List) super.replaceValues((Object) key, (Iterable) values);
    }
}
