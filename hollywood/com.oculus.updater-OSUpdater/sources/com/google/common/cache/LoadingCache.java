package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;

@GwtCompatible
public interface LoadingCache<K, V> extends Function<K, V>, Cache<K, V> {
    @Override // com.google.common.base.Function
    @Deprecated
    V apply(K k);
}
