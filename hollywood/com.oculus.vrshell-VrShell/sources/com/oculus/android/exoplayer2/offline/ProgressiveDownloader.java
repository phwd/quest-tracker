package com.oculus.android.exoplayer2.offline;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.offline.Downloader;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.upstream.cache.CacheDataSource;
import com.oculus.android.exoplayer2.upstream.cache.CacheUtil;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;
import java.io.IOException;

public final class ProgressiveDownloader implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private final PriorityTaskManager priorityTaskManager;

    public ProgressiveDownloader(String str, String str2, DownloaderConstructorHelper downloaderConstructorHelper) {
        this.dataSpec = new DataSpec(Uri.parse(str), 0, -1, str2, 0);
        this.cache = downloaderConstructorHelper.getCache();
        this.dataSource = downloaderConstructorHelper.buildCacheDataSource(false);
        this.priorityTaskManager = downloaderConstructorHelper.getPriorityTaskManager();
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public void init() {
        CacheUtil.getCached(this.dataSpec, this.cache, this.cachingCounters);
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public void download(@Nullable Downloader.ProgressListener progressListener) throws InterruptedException, IOException {
        this.priorityTaskManager.add(-1000);
        try {
            CacheUtil.cache(this.dataSpec, this.cache, this.dataSource, new byte[131072], this.priorityTaskManager, -1000, this.cachingCounters, true);
            if (progressListener != null) {
                progressListener.onDownloadProgress(this, 100.0f, this.cachingCounters.contentLength);
            }
        } finally {
            this.priorityTaskManager.remove(-1000);
        }
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public void remove() {
        CacheUtil.remove(this.cache, CacheUtil.getKey(this.dataSpec));
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public long getDownloadedBytes() {
        return this.cachingCounters.totalCachedBytes();
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public float getDownloadPercentage() {
        long j = this.cachingCounters.contentLength;
        if (j == -1) {
            return Float.NaN;
        }
        return (((float) this.cachingCounters.totalCachedBytes()) * 100.0f) / ((float) j);
    }
}
