package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapMemoryCacheFactory {
    public static InstrumentedMemoryCache<CacheKey, CloseableImage> get(MemoryCache<CacheKey, CloseableImage> memoryCache, final ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerBitmapMemoryCache(memoryCache);
        return new InstrumentedMemoryCache<>(memoryCache, new MemoryCacheTracker<CacheKey>() {
            /* class com.facebook.imagepipeline.cache.BitmapMemoryCacheFactory.AnonymousClass1 */

            public void onCacheHit(CacheKey cacheKey) {
                imageCacheStatsTracker.onBitmapCacheHit(cacheKey);
            }

            public void onCacheMiss(CacheKey cacheKey) {
                imageCacheStatsTracker.onBitmapCacheMiss(cacheKey);
            }

            public void onCachePut(CacheKey cacheKey) {
                imageCacheStatsTracker.onBitmapCachePut(cacheKey);
            }
        });
    }
}
