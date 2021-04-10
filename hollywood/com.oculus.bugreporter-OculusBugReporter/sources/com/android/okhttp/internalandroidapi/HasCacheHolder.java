package com.android.okhttp.internalandroidapi;

import com.android.okhttp.Cache;
import java.io.File;

public interface HasCacheHolder {
    CacheHolder getCacheHolder();

    public static final class CacheHolder {
        private final Cache okHttpCache;

        private CacheHolder(Cache okHttpCache2) {
            this.okHttpCache = okHttpCache2;
        }

        private CacheHolder() {
            throw new UnsupportedOperationException();
        }

        public Cache getCache() {
            return this.okHttpCache;
        }

        public static CacheHolder create(File directory, long maxSizeBytes) {
            return new CacheHolder(new Cache(directory, maxSizeBytes));
        }

        public boolean isEquivalent(File directory, long maxSizeBytes) {
            return this.okHttpCache.getDirectory().equals(directory) && this.okHttpCache.getMaxSize() == maxSizeBytes && !this.okHttpCache.isClosed();
        }
    }
}
