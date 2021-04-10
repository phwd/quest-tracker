package com.facebook.imagepipeline.cache;

public interface MemoryCacheTracker<K> {
    void onCacheHit(K k);

    void onCacheMiss(K k);

    void onCachePut(K k);
}
