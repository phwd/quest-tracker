package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public interface BiMap<K, V> extends Map<K, V> {
    V forcePut(@Nullable K k, @Nullable V v);

    BiMap<V, K> inverse();

    @Override // java.util.Map
    V put(@Nullable K k, @Nullable V v);

    @Override // java.util.Map
    void putAll(Map<? extends K, ? extends V> map);

    @Override // java.util.Map
    Set<V> values();
}
