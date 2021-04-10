package com.android.okhttp.internal.huc;

import com.android.okhttp.Request;
import com.android.okhttp.Response;
import com.android.okhttp.internal.InternalCache;
import com.android.okhttp.internal.http.CacheRequest;
import com.android.okhttp.internal.http.CacheStrategy;
import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Sink;
import java.io.OutputStream;
import java.net.CacheResponse;
import java.net.ResponseCache;

public final class CacheAdapter implements InternalCache {
    private final ResponseCache delegate;

    @Override // com.android.okhttp.internal.InternalCache
    public void remove(Request request) {
    }

    @Override // com.android.okhttp.internal.InternalCache
    public void trackConditionalCacheHit() {
    }

    @Override // com.android.okhttp.internal.InternalCache
    public void trackResponse(CacheStrategy cacheStrategy) {
    }

    @Override // com.android.okhttp.internal.InternalCache
    public void update(Response response, Response response2) {
    }

    public CacheAdapter(ResponseCache responseCache) {
        this.delegate = responseCache;
    }

    @Override // com.android.okhttp.internal.InternalCache
    public Response get(Request request) {
        CacheResponse javaCachedResponse = getJavaCachedResponse(request);
        if (javaCachedResponse == null) {
            return null;
        }
        return JavaApiConverter.createOkResponseForCacheGet(request, javaCachedResponse);
    }

    @Override // com.android.okhttp.internal.InternalCache
    public CacheRequest put(Response response) {
        final java.net.CacheRequest put = this.delegate.put(response.request().uri(), JavaApiConverter.createJavaUrlConnectionForCachePut(response));
        if (put == null) {
            return null;
        }
        return new CacheRequest() {
            /* class com.android.okhttp.internal.huc.CacheAdapter.AnonymousClass1 */

            @Override // com.android.okhttp.internal.http.CacheRequest
            public Sink body() {
                OutputStream body = put.getBody();
                if (body != null) {
                    return Okio.sink(body);
                }
                return null;
            }

            @Override // com.android.okhttp.internal.http.CacheRequest
            public void abort() {
                put.abort();
            }
        };
    }

    private CacheResponse getJavaCachedResponse(Request request) {
        return this.delegate.get(request.uri(), request.method(), JavaApiConverter.extractJavaHeaders(request));
    }
}
