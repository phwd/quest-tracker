package com.oculus.android.exoplayer2.upstream.cache;

import com.oculus.android.exoplayer2.upstream.cache.Cache;

public interface CacheEvictor extends Cache.Listener {
    void onCacheInitialized();

    void onStartFile(Cache cache, String str, long j, long j2);
}
