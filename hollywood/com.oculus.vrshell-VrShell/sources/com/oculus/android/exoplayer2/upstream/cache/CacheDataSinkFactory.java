package com.oculus.android.exoplayer2.upstream.cache;

import com.oculus.android.exoplayer2.upstream.DataSink;

public final class CacheDataSinkFactory implements DataSink.Factory {
    private final int bufferSize;
    private final Cache cache;
    private final long maxCacheFileSize;

    public CacheDataSinkFactory(Cache cache2, long j) {
        this(cache2, j, 20480);
    }

    public CacheDataSinkFactory(Cache cache2, long j, int i) {
        this.cache = cache2;
        this.maxCacheFileSize = j;
        this.bufferSize = i;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSink.Factory
    public DataSink createDataSink() {
        return new CacheDataSink(this.cache, this.maxCacheFileSize, this.bufferSize);
    }
}
