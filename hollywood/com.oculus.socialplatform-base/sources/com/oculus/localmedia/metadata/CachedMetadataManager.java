package com.oculus.localmedia.metadata;

import android.content.Context;
import com.oculus.localmedia.database.LocalMediaCacheDatabase;
import com.oculus.localmedia.database.LocalMediaSQLHelper;
import java.util.Map;

public class CachedMetadataManager {
    public static CachedMetadataManager sCachedMetadataManager;
    public LocalMediaCacheDatabase mCachedMetadataDatabase = null;
    public Map<String, CachedMetadata> mCachedMetadataMemoryCache = null;

    private synchronized void loadAllFromDatabase() {
        if (this.mCachedMetadataMemoryCache == null) {
            this.mCachedMetadataMemoryCache = this.mCachedMetadataDatabase.loadAllFromDatabase();
        }
    }

    public static void init(Context context) {
        if (sCachedMetadataManager == null) {
            sCachedMetadataManager = new CachedMetadataManager(context);
        }
    }

    public CachedMetadataManager(Context context) {
        this.mCachedMetadataDatabase = new LocalMediaCacheDatabase(new LocalMediaSQLHelper(context, null));
    }

    public static CachedMetadataManager getSingleton() {
        return sCachedMetadataManager;
    }

    public CachedMetadata get(String str) {
        loadAllFromDatabase();
        return this.mCachedMetadataMemoryCache.get(str);
    }

    public void set(String str, CachedMetadata cachedMetadata) {
        loadAllFromDatabase();
        if (cachedMetadata != null) {
            this.mCachedMetadataMemoryCache.put(str, cachedMetadata);
            this.mCachedMetadataDatabase.put(str, cachedMetadata);
        }
    }
}
