package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import java.io.File;

public final class ExternalPreferredCacheDiskCacheFactory extends DiskLruCacheFactory {
    public ExternalPreferredCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, 262144000);
    }

    public ExternalPreferredCacheDiskCacheFactory(Context context, long j) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, j);
    }

    public ExternalPreferredCacheDiskCacheFactory(final Context context, final String str, long j) {
        super(new DiskLruCacheFactory.CacheDirectoryGetter() {
            /* class com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory.AnonymousClass1 */

            @Nullable
            private File getInternalCacheDirectory() {
                File cacheDir = context.getCacheDir();
                if (cacheDir == null) {
                    return null;
                }
                String str = str;
                return str != null ? new File(cacheDir, str) : cacheDir;
            }

            @Override // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
            public File getCacheDirectory() {
                File externalCacheDir;
                File internalCacheDirectory = getInternalCacheDirectory();
                if ((internalCacheDirectory != null && internalCacheDirectory.exists()) || (externalCacheDir = context.getExternalCacheDir()) == null || !externalCacheDir.canWrite()) {
                    return internalCacheDirectory;
                }
                String str = str;
                return str != null ? new File(externalCacheDir, str) : externalCacheDir;
            }
        }, j);
    }
}
