package com.oculus.android.exoplayer2.upstream.cache;

import com.oculus.android.exoplayer2.upstream.DataSink;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.FileDataSourceFactory;
import com.oculus.android.exoplayer2.upstream.cache.CacheDataSource;

public final class CacheDataSourceFactory implements DataSource.Factory {
    private final Cache cache;
    private final DataSource.Factory cacheReadDataSourceFactory;
    private final DataSink.Factory cacheWriteDataSinkFactory;
    private final CacheDataSource.EventListener eventListener;
    private final int flags;
    private final DataSource.Factory upstreamFactory;

    public CacheDataSourceFactory(Cache cache2, DataSource.Factory factory) {
        this(cache2, factory, 0);
    }

    public CacheDataSourceFactory(Cache cache2, DataSource.Factory factory, int i) {
        this(cache2, factory, i, 2097152);
    }

    public CacheDataSourceFactory(Cache cache2, DataSource.Factory factory, int i, long j) {
        this(cache2, factory, new FileDataSourceFactory(), new CacheDataSinkFactory(cache2, j), i, null);
    }

    public CacheDataSourceFactory(Cache cache2, DataSource.Factory factory, DataSource.Factory factory2, DataSink.Factory factory3, int i, CacheDataSource.EventListener eventListener2) {
        this.cache = cache2;
        this.upstreamFactory = factory;
        this.cacheReadDataSourceFactory = factory2;
        this.cacheWriteDataSinkFactory = factory3;
        this.flags = i;
        this.eventListener = eventListener2;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource.Factory
    public CacheDataSource createDataSource() {
        Cache cache2 = this.cache;
        DataSource createDataSource = this.upstreamFactory.createDataSource();
        DataSource createDataSource2 = this.cacheReadDataSourceFactory.createDataSource();
        DataSink.Factory factory = this.cacheWriteDataSinkFactory;
        return new CacheDataSource(cache2, createDataSource, createDataSource2, factory != null ? factory.createDataSink() : null, this.flags, this.eventListener);
    }
}
