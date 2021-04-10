package com.oculus.android.exoplayer2.offline;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.upstream.DataSink;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DummyDataSource;
import com.oculus.android.exoplayer2.upstream.FileDataSource;
import com.oculus.android.exoplayer2.upstream.PriorityDataSource;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.upstream.cache.CacheDataSink;
import com.oculus.android.exoplayer2.upstream.cache.CacheDataSource;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;

public final class DownloaderConstructorHelper {
    private final Cache cache;
    private final DataSource.Factory cacheReadDataSourceFactory;
    private final DataSink.Factory cacheWriteDataSinkFactory;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource.Factory upstreamDataSourceFactory;

    public DownloaderConstructorHelper(Cache cache2, DataSource.Factory factory) {
        this(cache2, factory, null, null, null);
    }

    public DownloaderConstructorHelper(Cache cache2, DataSource.Factory factory, @Nullable DataSource.Factory factory2, @Nullable DataSink.Factory factory3, @Nullable PriorityTaskManager priorityTaskManager2) {
        Assertions.checkNotNull(factory);
        this.cache = cache2;
        this.upstreamDataSourceFactory = factory;
        this.cacheReadDataSourceFactory = factory2;
        this.cacheWriteDataSinkFactory = factory3;
        this.priorityTaskManager = priorityTaskManager2;
    }

    public Cache getCache() {
        return this.cache;
    }

    public PriorityTaskManager getPriorityTaskManager() {
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        return priorityTaskManager2 != null ? priorityTaskManager2 : new PriorityTaskManager();
    }

    public CacheDataSource buildCacheDataSource(boolean z) {
        DataSource.Factory factory = this.cacheReadDataSourceFactory;
        DataSource createDataSource = factory != null ? factory.createDataSource() : new FileDataSource();
        if (z) {
            return new CacheDataSource(this.cache, DummyDataSource.INSTANCE, createDataSource, null, 1, null);
        }
        DataSink.Factory factory2 = this.cacheWriteDataSinkFactory;
        DataSink createDataSink = factory2 != null ? factory2.createDataSink() : new CacheDataSink(this.cache, 2097152);
        DataSource createDataSource2 = this.upstreamDataSourceFactory.createDataSource();
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        return new CacheDataSource(this.cache, priorityTaskManager2 == null ? createDataSource2 : new PriorityDataSource(createDataSource2, priorityTaskManager2, -1000), createDataSource, createDataSink, 1, null);
    }
}
