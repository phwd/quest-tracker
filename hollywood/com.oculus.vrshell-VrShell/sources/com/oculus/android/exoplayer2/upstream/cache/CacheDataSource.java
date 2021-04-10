package com.oculus.android.exoplayer2.upstream.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.upstream.DataSink;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSourceException;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.FileDataSource;
import com.oculus.android.exoplayer2.upstream.TeeDataSource;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CacheDataSource implements DataSource {
    public static final long DEFAULT_MAX_CACHE_FILE_SIZE = 2097152;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    @Nullable
    private final EventListener eventListener;
    private int flags;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public interface EventListener {
        void onCachedBytesRead(long j, long j2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public CacheDataSource(Cache cache2, DataSource dataSource) {
        this(cache2, dataSource, 0, 2097152);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, int i) {
        this(cache2, dataSource, i, 2097152);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, int i, long j) {
        this(cache2, dataSource, new FileDataSource(), new CacheDataSink(cache2, j), i, null);
    }

    public CacheDataSource(Cache cache2, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i, @Nullable EventListener eventListener2) {
        this.cache = cache2;
        this.cacheReadDataSource = dataSource2;
        boolean z = false;
        this.blockOnCache = (i & 1) != 0;
        this.ignoreCacheOnError = (i & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i & 4) != 0 ? true : z;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener2;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.uri = dataSpec.uri;
            this.flags = dataSpec.flags;
            this.key = CacheUtil.getKey(dataSpec);
            this.readPosition = dataSpec.position;
            this.currentRequestIgnoresCache = (this.ignoreCacheOnError && this.seenCacheError) || (dataSpec.length == -1 && this.ignoreCacheForUnsetLengthRequests);
            if (dataSpec.length == -1) {
                if (!this.currentRequestIgnoresCache) {
                    this.bytesRemaining = this.cache.getContentLength(this.key);
                    if (this.bytesRemaining != -1) {
                        this.bytesRemaining -= dataSpec.position;
                        if (this.bytesRemaining <= 0) {
                            throw new DataSourceException(0);
                        }
                    }
                    openNextSource(false);
                    return this.bytesRemaining;
                }
            }
            this.bytesRemaining = dataSpec.length;
            openNextSource(false);
            return this.bytesRemaining;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(true);
            }
            int read = this.currentDataSource.read(bArr, i, i2);
            if (read != -1) {
                if (this.currentDataSource == this.cacheReadDataSource) {
                    this.totalCachedBytesRead += (long) read;
                }
                long j = (long) read;
                this.readPosition += j;
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= j;
                }
            } else if (this.currentDataSpecLengthUnset) {
                setBytesRemaining(0);
            } else {
                if (this.bytesRemaining <= 0) {
                    if (this.bytesRemaining == -1) {
                    }
                }
                closeCurrentSource();
                openNextSource(false);
                return read(bArr, i, i2);
            }
            return read;
        } catch (IOException e) {
            if (!this.currentDataSpecLengthUnset || !isCausedByPositionOutOfRange(e)) {
                handleBeforeThrow(e);
                throw e;
            }
            setBytesRemaining(0);
            return -1;
        }
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        DataSource dataSource = this.currentDataSource;
        return dataSource == this.upstreamDataSource ? dataSource.getUri() : this.uri;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    private void openNextSource(boolean z) throws IOException {
        CacheSpan cacheSpan;
        DataSource dataSource;
        DataSpec dataSpec;
        long j;
        if (this.currentRequestIgnoresCache) {
            cacheSpan = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpan = this.cache.startReadWrite(this.key, this.readPosition);
            } catch (InterruptedException unused) {
                throw new InterruptedIOException();
            }
        } else {
            cacheSpan = this.cache.startReadWriteNonBlocking(this.key, this.readPosition);
        }
        if (cacheSpan == null) {
            DataSource dataSource2 = this.upstreamDataSource;
            dataSpec = new DataSpec(this.uri, this.readPosition, this.bytesRemaining, this.key, this.flags);
            dataSource = dataSource2;
        } else if (cacheSpan.isCached) {
            Uri fromFile = Uri.fromFile(cacheSpan.file);
            long j2 = this.readPosition - cacheSpan.position;
            long j3 = cacheSpan.length - j2;
            long j4 = this.bytesRemaining;
            if (j4 != -1) {
                j3 = Math.min(j3, j4);
            }
            DataSpec dataSpec2 = new DataSpec(fromFile, this.readPosition, j2, j3, this.key, this.flags);
            dataSource = this.cacheReadDataSource;
            dataSpec = dataSpec2;
        } else {
            if (cacheSpan.isOpenEnded()) {
                j = this.bytesRemaining;
            } else {
                j = cacheSpan.length;
                long j5 = this.bytesRemaining;
                if (j5 != -1) {
                    j = Math.min(j, j5);
                }
            }
            dataSpec = new DataSpec(this.uri, this.readPosition, j, this.key, this.flags);
            dataSource = this.cacheWriteDataSource;
            if (dataSource == null) {
                dataSource = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpan);
                cacheSpan = null;
            }
        }
        this.checkCachePosition = (this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + MIN_READ_BEFORE_CHECKING_CACHE;
        boolean z2 = true;
        if (z) {
            Assertions.checkState(this.currentDataSource == this.upstreamDataSource);
            if (dataSource != this.upstreamDataSource) {
                try {
                    closeCurrentSource();
                } catch (Throwable th) {
                    if (cacheSpan.isHoleSpan()) {
                        this.cache.releaseHoleSpan(cacheSpan);
                    }
                    throw th;
                }
            } else {
                return;
            }
        }
        if (cacheSpan != null && cacheSpan.isHoleSpan()) {
            this.currentHoleSpan = cacheSpan;
        }
        this.currentDataSource = dataSource;
        if (dataSpec.length != -1) {
            z2 = false;
        }
        this.currentDataSpecLengthUnset = z2;
        long open = dataSource.open(dataSpec);
        if (this.currentDataSpecLengthUnset && open != -1) {
            setBytesRemaining(open);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:? */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Throwable] */
    private static boolean isCausedByPositionOutOfRange(IOException iOException) {
        while (iOException != 0) {
            if ((iOException instanceof DataSourceException) && ((DataSourceException) iOException).reason == 0) {
                return true;
            }
            iOException = iOException.getCause();
        }
        return false;
    }

    private void setBytesRemaining(long j) throws IOException {
        this.bytesRemaining = j;
        if (isWritingToCache()) {
            this.cache.setContentLength(this.key, this.readPosition + j);
        }
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource != null) {
            try {
                dataSource.close();
            } finally {
                this.currentDataSource = null;
                this.currentDataSpecLengthUnset = false;
                CacheSpan cacheSpan = this.currentHoleSpan;
                if (cacheSpan != null) {
                    this.cache.releaseHoleSpan(cacheSpan);
                    this.currentHoleSpan = null;
                }
            }
        }
    }

    private void handleBeforeThrow(IOException iOException) {
        if (this.currentDataSource == this.cacheReadDataSource || (iOException instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null && this.totalCachedBytesRead > 0) {
            eventListener2.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
            this.totalCachedBytesRead = 0;
        }
    }
}
