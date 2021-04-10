package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.common.BytesRange;
import java.util.concurrent.TimeUnit;

public class DefaultEncodedMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    private static final int MAX_CACHE_ENTRIES = Integer.MAX_VALUE;
    private static final int MAX_EVICTION_QUEUE_ENTRIES = Integer.MAX_VALUE;
    private static final long PARAMS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);

    @Override // com.facebook.common.internal.Supplier
    public MemoryCacheParams get() {
        int maxCacheSize = getMaxCacheSize();
        return new MemoryCacheParams(maxCacheSize, BytesRange.TO_END_OF_CONTENT, maxCacheSize, BytesRange.TO_END_OF_CONTENT, maxCacheSize / 8, PARAMS_CHECK_INTERVAL_MS);
    }

    private int getMaxCacheSize() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return 1048576;
        }
        return min < 33554432 ? 2097152 : 4194304;
    }
}
