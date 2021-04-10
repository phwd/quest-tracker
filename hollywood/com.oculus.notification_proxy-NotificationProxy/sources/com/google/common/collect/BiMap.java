package com.google.common.collect;

import java.util.Map;
import java.util.Set;

public interface BiMap<K, V> extends Map<K, V> {
    @Override // java.util.Map
    Set<V> values();
}
