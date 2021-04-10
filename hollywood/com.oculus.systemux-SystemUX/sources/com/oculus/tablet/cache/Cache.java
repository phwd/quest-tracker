package com.oculus.tablet.cache;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting
public interface Cache {
    CacheItem get(String str);

    void put(String str, CacheItem cacheItem);
}
