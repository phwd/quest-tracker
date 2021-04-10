package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;

public interface ImageCacheStatsTracker {
    void onBitmapCacheHit(CacheKey cacheKey);

    void onBitmapCacheMiss(CacheKey cacheKey);

    void onBitmapCachePut(CacheKey cacheKey);

    void onDiskCacheGetFail(CacheKey cacheKey);

    void onDiskCacheHit(CacheKey cacheKey);

    void onDiskCacheMiss(CacheKey cacheKey);

    void onDiskCachePut(CacheKey cacheKey);

    void onMemoryCacheHit(CacheKey cacheKey);

    void onMemoryCacheMiss(CacheKey cacheKey);

    void onMemoryCachePut(CacheKey cacheKey);

    void onStagingAreaHit(CacheKey cacheKey);

    void onStagingAreaMiss(CacheKey cacheKey);

    void registerBitmapMemoryCache(MemoryCache<?, ?> memoryCache);

    void registerEncodedMemoryCache(MemoryCache<?, ?> memoryCache);
}
