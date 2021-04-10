package com.facebook.common.cache;

public interface CacheLoader<K, V> {
    V load(K k);
}
