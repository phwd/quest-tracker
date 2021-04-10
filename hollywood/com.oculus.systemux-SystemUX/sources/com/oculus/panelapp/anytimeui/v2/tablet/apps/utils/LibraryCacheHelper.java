package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.tablet.cache.CacheItem;
import com.oculus.tablet.cache.DiskCache;
import java.util.List;

public class LibraryCacheHelper {
    private static final String APPS_KEY = "apps";
    private static final long APPS_VALID_DURATION_SECONDS = 1209600;
    private static final String CACHE_SUBDIRECTORY = "library";
    private static final String TAG = LoggingUtil.tag(LibraryCacheHelper.class);
    private DiskCache mDiskCache;

    public LibraryCacheHelper(Context context) {
        this.mDiskCache = new DiskCache(context, "library");
    }

    public void saveApps(List<App> list) {
        CacheItem cacheItem = new CacheItem(new Gson().toJson(list).getBytes());
        cacheItem.setExpirationTime((System.currentTimeMillis() / 1000) + APPS_VALID_DURATION_SECONDS);
        this.mDiskCache.put("apps", cacheItem);
    }

    public List<App> loadApps() {
        List<App> list;
        long currentTimeMillis = System.currentTimeMillis();
        CacheItem cacheItem = this.mDiskCache.get("apps");
        if (cacheItem == null || cacheItem.isExpired()) {
            list = null;
        } else {
            list = (List) new Gson().fromJson(new String(cacheItem.getBytes()), new TypeToken<List<App>>() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryCacheHelper.AnonymousClass1 */
            }.getType());
        }
        Log.d(TAG, String.format("Finished loading apps from cache in %d ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
        return list;
    }
}
