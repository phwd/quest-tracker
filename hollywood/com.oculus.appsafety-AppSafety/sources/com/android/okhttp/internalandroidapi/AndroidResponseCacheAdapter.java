package com.android.okhttp.internalandroidapi;

import com.android.okhttp.Cache;
import com.android.okhttp.Response;
import com.android.okhttp.internal.huc.JavaApiConverter;
import com.android.okhttp.internalandroidapi.HasCacheHolder;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public final class AndroidResponseCacheAdapter {
    private final HasCacheHolder.CacheHolder cacheHolder;
    private final Cache okHttpCache;

    public AndroidResponseCacheAdapter(HasCacheHolder.CacheHolder cacheHolder2) {
        this.cacheHolder = cacheHolder2;
        this.okHttpCache = cacheHolder2.getCache();
    }

    public HasCacheHolder.CacheHolder getCacheHolder() {
        return this.cacheHolder;
    }

    public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) throws IOException {
        Response okResponse = this.okHttpCache.internalCache.get(JavaApiConverter.createOkRequest(uri, requestMethod, requestHeaders));
        if (okResponse == null) {
            return null;
        }
        return JavaApiConverter.createJavaCacheResponse(okResponse);
    }

    public CacheRequest put(URI uri, URLConnection urlConnection) throws IOException {
        com.android.okhttp.internal.http.CacheRequest okCacheRequest;
        Response okResponse = JavaApiConverter.createOkResponseForCachePut(uri, urlConnection);
        if (okResponse == null || (okCacheRequest = this.okHttpCache.internalCache.put(okResponse)) == null) {
            return null;
        }
        return JavaApiConverter.createJavaCacheRequest(okCacheRequest);
    }

    public long getSize() throws IOException {
        return this.okHttpCache.getSize();
    }

    public long getMaxSize() {
        return this.okHttpCache.getMaxSize();
    }

    public void flush() throws IOException {
        this.okHttpCache.flush();
    }

    public int getNetworkCount() {
        return this.okHttpCache.getNetworkCount();
    }

    public int getHitCount() {
        return this.okHttpCache.getHitCount();
    }

    public int getRequestCount() {
        return this.okHttpCache.getRequestCount();
    }

    public void close() throws IOException {
        this.okHttpCache.close();
    }

    public void delete() throws IOException {
        this.okHttpCache.delete();
    }
}
