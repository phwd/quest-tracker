package com.oculus.android.exoplayer2.offline;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.offline.Downloader;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.upstream.cache.CacheDataSource;
import com.oculus.android.exoplayer2.upstream.cache.CacheUtil;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public abstract class SegmentDownloader<M, K> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CacheDataSource dataSource;
    private volatile long downloadedBytes;
    private volatile int downloadedSegments;
    private K[] keys;
    private M manifest;
    private final Uri manifestUri;
    private final CacheDataSource offlineDataSource;
    private final PriorityTaskManager priorityTaskManager;
    private volatile int totalSegments;

    public abstract K[] getAllRepresentationKeys() throws IOException;

    /* access modifiers changed from: protected */
    public abstract M getManifest(DataSource dataSource2, Uri uri) throws IOException;

    /* access modifiers changed from: protected */
    public abstract List<Segment> getSegments(DataSource dataSource2, M m, K[] kArr, boolean z) throws InterruptedException, IOException;

    /* access modifiers changed from: protected */
    public static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long j, DataSpec dataSpec2) {
            this.startTimeUs = j;
            this.dataSpec = dataSpec2;
        }

        public int compareTo(@NonNull Segment segment) {
            int i = ((this.startTimeUs - segment.startTimeUs) > 0 ? 1 : ((this.startTimeUs - segment.startTimeUs) == 0 ? 0 : -1));
            if (i == 0) {
                return 0;
            }
            return i < 0 ? -1 : 1;
        }
    }

    public SegmentDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        this.manifestUri = uri;
        this.cache = downloaderConstructorHelper.getCache();
        this.dataSource = downloaderConstructorHelper.buildCacheDataSource(false);
        this.offlineDataSource = downloaderConstructorHelper.buildCacheDataSource(true);
        this.priorityTaskManager = downloaderConstructorHelper.getPriorityTaskManager();
        resetCounters();
    }

    public final M getManifest() throws IOException {
        return getManifestIfNeeded(false);
    }

    public final void selectRepresentations(K[] kArr) {
        this.keys = (kArr == null || kArr.length <= 0) ? null : (K[]) ((Object[]) kArr.clone());
        resetCounters();
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public final void init() throws InterruptedException, IOException {
        try {
            getManifestIfNeeded(true);
            try {
                initStatus(true);
            } catch (IOException | InterruptedException e) {
                resetCounters();
                throw e;
            }
        } catch (IOException unused) {
        }
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public final synchronized void download(@Nullable Downloader.ProgressListener progressListener) throws IOException, InterruptedException {
        this.priorityTaskManager.add(-1000);
        try {
            getManifestIfNeeded(false);
            List<Segment> initStatus = initStatus(false);
            notifyListener(progressListener);
            Collections.sort(initStatus);
            byte[] bArr = new byte[131072];
            CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
            for (int i = 0; i < initStatus.size(); i++) {
                CacheUtil.cache(initStatus.get(i).dataSpec, this.cache, this.dataSource, bArr, this.priorityTaskManager, -1000, cachingCounters, true);
                this.downloadedBytes += cachingCounters.newlyCachedBytes;
                this.downloadedSegments++;
                notifyListener(progressListener);
            }
        } finally {
            this.priorityTaskManager.remove(-1000);
        }
    }

    public final int getTotalSegments() {
        return this.totalSegments;
    }

    public final int getDownloadedSegments() {
        return this.downloadedSegments;
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public float getDownloadPercentage() {
        int i = this.totalSegments;
        int i2 = this.downloadedSegments;
        if (i == -1 || i2 == -1) {
            return Float.NaN;
        }
        if (i == 0) {
            return 100.0f;
        }
        return (((float) i2) * 100.0f) / ((float) i);
    }

    @Override // com.oculus.android.exoplayer2.offline.Downloader
    public final void remove() throws InterruptedException {
        List<Segment> list;
        try {
            getManifestIfNeeded(true);
        } catch (IOException unused) {
        }
        resetCounters();
        M m = this.manifest;
        if (m != null) {
            try {
                list = getSegments(this.offlineDataSource, m, getAllRepresentationKeys(), true);
            } catch (IOException unused2) {
                list = null;
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    remove(list.get(i).dataSpec.uri);
                }
            }
            this.manifest = null;
        }
        remove(this.manifestUri);
    }

    private void resetCounters() {
        this.totalSegments = -1;
        this.downloadedSegments = -1;
        this.downloadedBytes = -1;
    }

    private void remove(Uri uri) {
        CacheUtil.remove(this.cache, CacheUtil.generateKey(uri));
    }

    private void notifyListener(Downloader.ProgressListener progressListener) {
        if (progressListener != null) {
            progressListener.onDownloadProgress(this, getDownloadPercentage(), this.downloadedBytes);
        }
    }

    private synchronized List<Segment> initStatus(boolean z) throws IOException, InterruptedException {
        List<Segment> segments;
        DataSource dataSource2 = getDataSource(z);
        if (this.keys == null) {
            this.keys = getAllRepresentationKeys();
        }
        segments = getSegments(dataSource2, this.manifest, this.keys, z);
        CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
        this.totalSegments = segments.size();
        this.downloadedSegments = 0;
        this.downloadedBytes = 0;
        for (int size = segments.size() - 1; size >= 0; size--) {
            CacheUtil.getCached(segments.get(size).dataSpec, this.cache, cachingCounters);
            this.downloadedBytes += cachingCounters.alreadyCachedBytes;
            if (cachingCounters.alreadyCachedBytes == cachingCounters.contentLength) {
                this.downloadedSegments++;
                segments.remove(size);
            }
        }
        return segments;
    }

    private M getManifestIfNeeded(boolean z) throws IOException {
        if (this.manifest == null) {
            this.manifest = getManifest(getDataSource(z), this.manifestUri);
        }
        return this.manifest;
    }

    private DataSource getDataSource(boolean z) {
        return z ? this.offlineDataSource : this.dataSource;
    }
}
