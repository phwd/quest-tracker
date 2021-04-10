package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import java.util.Map;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    private final transient ImmutableMap<E, Integer> map;
    private final transient int size;

    RegularImmutableMultiset(ImmutableMap<E, Integer> map2, int size2) {
        this.map = map2;
        this.size = size2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.map.isPartialView();
    }

    @Override // com.google.common.collect.Multiset
    public int count(@Nullable Object element) {
        Integer value = this.map.get(element);
        if (value == null) {
            return 0;
        }
        return value.intValue();
    }

    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset, com.google.common.collect.ImmutableCollection
    public boolean contains(@Nullable Object element) {
        return this.map.containsKey(element);
    }

    @Override // com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        return this.map.keySet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int index) {
        Map.Entry<E, Integer> mapEntry = this.map.entrySet().asList().get(index);
        return Multisets.immutableEntry(mapEntry.getKey(), mapEntry.getValue().intValue());
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public int hashCode() {
        return this.map.hashCode();
    }
}
