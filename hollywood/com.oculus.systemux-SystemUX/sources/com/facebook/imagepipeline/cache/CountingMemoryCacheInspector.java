package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingMemoryCacheInspector<K, V> {
    private final CountingMemoryCache<K, V> mCountingBitmapCache;

    public static class DumpInfoEntry<K, V> {
        public final K key;
        public final CloseableReference<V> value;

        public DumpInfoEntry(K k, CloseableReference<V> closeableReference) {
            this.key = (K) Preconditions.checkNotNull(k);
            this.value = CloseableReference.cloneOrNull(closeableReference);
        }

        public void release() {
            CloseableReference.closeSafely((CloseableReference<?>) this.value);
        }
    }

    public static class DumpInfo<K, V> {
        public final List<DumpInfoEntry<K, V>> lruEntries = new ArrayList();
        public final int lruSize;
        public final int maxEntriesCount;
        public final int maxEntrySize;
        public final int maxSize;
        public final Map<Bitmap, Object> otherEntries = new HashMap();
        public final List<DumpInfoEntry<K, V>> sharedEntries = new ArrayList();
        public final int size;

        public DumpInfo(int i, int i2, MemoryCacheParams memoryCacheParams) {
            this.maxSize = memoryCacheParams.maxCacheSize;
            this.maxEntriesCount = memoryCacheParams.maxCacheEntries;
            this.maxEntrySize = memoryCacheParams.maxCacheEntrySize;
            this.size = i;
            this.lruSize = i2;
        }

        public void release() {
            for (DumpInfoEntry<K, V> dumpInfoEntry : this.lruEntries) {
                dumpInfoEntry.release();
            }
            for (DumpInfoEntry<K, V> dumpInfoEntry2 : this.sharedEntries) {
                dumpInfoEntry2.release();
            }
        }
    }

    public CountingMemoryCacheInspector(CountingMemoryCache<K, V> countingMemoryCache) {
        this.mCountingBitmapCache = countingMemoryCache;
    }

    public DumpInfo dumpCacheContent() {
        DumpInfo dumpInfo;
        synchronized (this.mCountingBitmapCache) {
            dumpInfo = new DumpInfo(this.mCountingBitmapCache.getSizeInBytes(), this.mCountingBitmapCache.getEvictionQueueSizeInBytes(), this.mCountingBitmapCache.mMemoryCacheParams);
            for (Map.Entry<K, CountingMemoryCache.Entry<K, V>> entry : this.mCountingBitmapCache.mCachedEntries.getMatchingEntries(null)) {
                CountingMemoryCache.Entry<K, V> value = entry.getValue();
                DumpInfoEntry<K, V> dumpInfoEntry = new DumpInfoEntry<>(value.key, value.valueRef);
                if (value.clientCount > 0) {
                    dumpInfo.sharedEntries.add(dumpInfoEntry);
                } else {
                    dumpInfo.lruEntries.add(dumpInfoEntry);
                }
            }
            for (Map.Entry<Bitmap, Object> entry2 : this.mCountingBitmapCache.mOtherEntries.entrySet()) {
                if (entry2 != null && !entry2.getKey().isRecycled()) {
                    dumpInfo.otherEntries.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        return dumpInfo;
    }
}
