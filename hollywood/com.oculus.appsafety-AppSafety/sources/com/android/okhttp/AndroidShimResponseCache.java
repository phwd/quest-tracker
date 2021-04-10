package com.android.okhttp;

import com.android.okhttp.internal.huc.JavaApiConverter;
import java.io.File;
import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class AndroidShimResponseCache extends ResponseCache {
    private final Cache delegate;

    private AndroidShimResponseCache(Cache delegate2) {
        this.delegate = delegate2;
    }

    public static AndroidShimResponseCache create(File directory, long maxSize) throws IOException {
        return new AndroidShimResponseCache(new Cache(directory, maxSize));
    }

    public boolean isEquivalent(File directory, long maxSize) {
        Cache installedCache = getCache();
        return installedCache.getDirectory().equals(directory) && installedCache.getMaxSize() == maxSize && !installedCache.isClosed();
    }

    public Cache getCache() {
        return this.delegate;
    }

    @Override // java.net.ResponseCache
    public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) throws IOException {
        Response okResponse = this.delegate.internalCache.get(JavaApiConverter.createOkRequest(uri, requestMethod, requestHeaders));
        if (okResponse == null) {
            return null;
        }
        return JavaApiConverter.createJavaCacheResponse(okResponse);
    }

    @Override // java.net.ResponseCache
    public CacheRequest put(URI uri, URLConnection urlConnection) throws IOException {
        com.android.okhttp.internal.http.CacheRequest okCacheRequest;
        Response okResponse = JavaApiConverter.createOkResponseForCachePut(uri, urlConnection);
        if (okResponse == null || (okCacheRequest = this.delegate.internalCache.put(okResponse)) == null) {
            return null;
        }
        return JavaApiConverter.createJavaCacheRequest(okCacheRequest);
    }

    public long size() throws IOException {
        return this.delegate.getSize();
    }

    public long maxSize() {
        return this.delegate.getMaxSize();
    }

    public void flush() throws IOException {
        this.delegate.flush();
    }

    public int getNetworkCount() {
        return this.delegate.getNetworkCount();
    }

    public int getHitCount() {
        return this.delegate.getHitCount();
    }

    public int getRequestCount() {
        return this.delegate.getRequestCount();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public void delete() throws IOException {
        this.delegate.delete();
    }
}
