package com.oculus.android.exoplayer2.upstream.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.PriorityTaskManager;
import java.io.EOFException;
import java.io.IOException;

public final class CacheUtil {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;

    public static class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength = -1;
        public volatile long newlyCachedBytes;

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static String getKey(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : generateKey(dataSpec.uri);
    }

    public static void getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length != -1 ? dataSpec.length : cache.getContentLength(key);
        cachingCounters.contentLength = contentLength;
        cachingCounters.alreadyCachedBytes = 0;
        cachingCounters.newlyCachedBytes = 0;
        long j2 = j;
        long j3 = contentLength;
        while (j3 != 0) {
            int i = (j3 > -1 ? 1 : (j3 == -1 ? 0 : -1));
            long cachedLength = cache.getCachedLength(key, j2, i != 0 ? j3 : Long.MAX_VALUE);
            if (cachedLength > 0) {
                cachingCounters.alreadyCachedBytes += cachedLength;
            } else {
                cachedLength = -cachedLength;
                if (cachedLength == Long.MAX_VALUE) {
                    return;
                }
            }
            j2 += cachedLength;
            if (i == 0) {
                cachedLength = 0;
            }
            j3 -= cachedLength;
        }
    }

    public static void cache(DataSpec dataSpec, Cache cache, DataSource dataSource, @Nullable CachingCounters cachingCounters) throws IOException, InterruptedException {
        cache(dataSpec, cache, new CacheDataSource(cache, dataSource), new byte[131072], null, 0, cachingCounters, false);
    }

    public static void cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, @Nullable CachingCounters cachingCounters, boolean z) throws IOException, InterruptedException {
        CachingCounters cachingCounters2 = cachingCounters;
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        if (cachingCounters2 != null) {
            getCached(dataSpec, cache, cachingCounters2);
        } else {
            cachingCounters2 = new CachingCounters();
        }
        String key = getKey(dataSpec);
        long j = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length != -1 ? dataSpec.length : cache.getContentLength(key);
        while (contentLength != 0) {
            int i2 = (contentLength > -1 ? 1 : (contentLength == -1 ? 0 : -1));
            long cachedLength = cache.getCachedLength(key, j, i2 != 0 ? contentLength : Long.MAX_VALUE);
            if (cachedLength <= 0) {
                long j2 = -cachedLength;
                if (readAndDiscard(dataSpec, j, j2, cacheDataSource, bArr, priorityTaskManager, i, cachingCounters2) >= j2) {
                    cachedLength = j2;
                } else if (z && i2 != 0) {
                    throw new EOFException();
                } else {
                    return;
                }
            }
            j += cachedLength;
            if (i2 == 0) {
                cachedLength = 0;
            }
            contentLength -= cachedLength;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0085, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        com.oculus.android.exoplayer2.util.Util.closeQuietly(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0091, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008d A[ExcHandler: all (r0v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x000d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long readAndDiscard(com.oculus.android.exoplayer2.upstream.DataSpec r16, long r17, long r19, com.oculus.android.exoplayer2.upstream.DataSource r21, byte[] r22, com.oculus.android.exoplayer2.util.PriorityTaskManager r23, int r24, com.oculus.android.exoplayer2.upstream.cache.CacheUtil.CachingCounters r25) throws java.io.IOException, java.lang.InterruptedException {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.android.exoplayer2.upstream.cache.CacheUtil.readAndDiscard(com.oculus.android.exoplayer2.upstream.DataSpec, long, long, com.oculus.android.exoplayer2.upstream.DataSource, byte[], com.oculus.android.exoplayer2.util.PriorityTaskManager, int, com.oculus.android.exoplayer2.upstream.cache.CacheUtil$CachingCounters):long");
    }

    public static void remove(Cache cache, String str) {
        for (CacheSpan cacheSpan : cache.getCachedSpans(str)) {
            try {
                cache.removeSpan(cacheSpan);
            } catch (Cache.CacheException unused) {
            }
        }
    }

    private CacheUtil() {
    }
}
