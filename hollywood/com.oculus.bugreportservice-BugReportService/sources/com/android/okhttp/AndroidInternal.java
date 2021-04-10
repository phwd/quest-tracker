package com.android.okhttp;

import com.android.okhttp.internal.huc.CacheAdapter;
import java.net.ResponseCache;

public class AndroidInternal {
    public static void setResponseCache(OkUrlFactory okUrlFactory, ResponseCache responseCache) {
        okUrlFactory.client().setInternalCache(responseCache != null ? new CacheAdapter(responseCache) : null);
    }
}
