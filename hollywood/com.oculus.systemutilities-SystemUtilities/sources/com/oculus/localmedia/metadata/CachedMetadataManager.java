package com.oculus.localmedia.metadata;

import android.content.Context;
import com.oculus.localmedia.database.LocalMediaCacheDatabase;
import com.oculus.localmedia.database.LocalMediaSQLHelper;
import java.util.Map;

public class CachedMetadataManager {
    private static CachedMetadataManager sCachedMetadataManager = null;
    private LocalMediaCacheDatabase mCachedMetadataDatabase = null;
    private Map<String, CachedMetadata> mCachedMetadataMemoryCache = null;

    public static void init(Context context) {
        if (sCachedMetadataManager == null) {
            sCachedMetadataManager = new CachedMetadataManager(context);
        }
    }

    public static CachedMetadataManager getSingleton() {
        return sCachedMetadataManager;
    }

    private CachedMetadataManager(Context context) {
        this.mCachedMetadataDatabase = new LocalMediaCacheDatabase(new LocalMediaSQLHelper(context, null));
    }

    private synchronized void loadAllFromDatabase() {
        if (this.mCachedMetadataMemoryCache == null) {
            this.mCachedMetadataMemoryCache = this.mCachedMetadataDatabase.loadAllFromDatabase();
        }
    }

    public CachedMetadata get(String path) {
        loadAllFromDatabase();
        return this.mCachedMetadataMemoryCache.get(path);
    }

    public void set(String path, CachedMetadata metadata) {
        loadAllFromDatabase();
        if (metadata != null) {
            this.mCachedMetadataMemoryCache.put(path, metadata);
            this.mCachedMetadataDatabase.put(path, metadata);
        }
    }
}
