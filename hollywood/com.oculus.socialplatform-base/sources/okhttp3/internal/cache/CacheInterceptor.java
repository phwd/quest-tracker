package okhttp3.internal.cache;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.RealBufferedSink;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor implements Interceptor {
    public final InternalCache cache;

    private CacheRequest maybeCache(Response response, Request request, InternalCache internalCache) throws IOException {
        if (internalCache == null) {
            return null;
        }
        if (CacheStrategy.isCacheable(response, request)) {
            return internalCache.put(response);
        }
        if (HttpMethod.invalidatesCache(request.method)) {
            try {
                internalCache.remove(request);
            } catch (IOException unused) {
            }
        }
        return null;
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        Sink body;
        if (cacheRequest == null || (body = cacheRequest.body()) == null) {
            return response;
        }
        final BufferedSource source = response.body.source();
        final RealBufferedSink realBufferedSink = new RealBufferedSink(body);
        AnonymousClass1 r0 = new Source() {
            /* class okhttp3.internal.cache.CacheInterceptor.AnonymousClass1 */
            public boolean cacheRequestClosed;

            @Override // okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                try {
                    long read = source.read(buffer, j);
                    if (read == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            realBufferedSink.close();
                        }
                        return -1;
                    }
                    buffer.copyTo(realBufferedSink.buffer(), buffer.size - read, read);
                    realBufferedSink.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
                if (okhttp3.internal.Util.skipAll(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x000e;
             */
            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void close() throws java.io.IOException {
                /*
                    r2 = this;
                    boolean r0 = r2.cacheRequestClosed
                    if (r0 != 0) goto L_0x0016
                    java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
                    r0 = 100
                    boolean r0 = okhttp3.internal.Util.skipAll(r2, r0, r1)     // Catch:{ IOException -> 0x000e }
                    if (r0 != 0) goto L_0x0016
                L_0x000e:
                    r0 = 1
                    r2.cacheRequestClosed = r0
                    okhttp3.internal.cache.CacheRequest r0 = r5
                    r0.abort()
                L_0x0016:
                    okio.BufferedSource r0 = r2
                    r0.close()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheInterceptor.AnonymousClass1.close():void");
            }

            @Override // okio.Source
            public Timeout timeout() {
                return source.timeout();
            }
        };
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.body = new RealResponseBody(response.headers, Okio.buffer(r0));
        return newBuilder.build();
    }

    public static Headers combine(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            String str = headers.namesAndValues[i << 1];
            String value = headers.value(i);
            if ((!"Warning".equalsIgnoreCase(str) || !value.startsWith("1")) && (!isEndToEnd(str) || Headers.get(headers2.namesAndValues, str) == null)) {
                Internal.instance.addLenient(builder, str, value);
            }
        }
        int length2 = headers2.namesAndValues.length >> 1;
        for (int i2 = 0; i2 < length2; i2++) {
            String str2 = headers2.namesAndValues[i2 << 1];
            if (!"Content-Length".equalsIgnoreCase(str2) && isEndToEnd(str2)) {
                Internal.instance.addLenient(builder, str2, headers2.value(i2));
            }
        }
        return new Headers(builder);
    }

    public static boolean isEndToEnd(String str) {
        if ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    public static Response stripBody(Response response) {
        if (response == null || response.body == null) {
            return response;
        }
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.body = null;
        return newBuilder.build();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response;
        InternalCache internalCache = this.cache;
        if (internalCache != null) {
            response = internalCache.get(chain.request());
        } else {
            response = null;
        }
        CacheStrategy cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), response).get();
        Request request = cacheStrategy.networkRequest;
        Response response2 = cacheStrategy.cacheResponse;
        InternalCache internalCache2 = this.cache;
        if (internalCache2 != null) {
            internalCache2.trackResponse(cacheStrategy);
        }
        if (response != null && response2 == null) {
            Util.closeQuietly(response.body);
        }
        if (request != null) {
            try {
                Response proceed = chain.proceed(request);
                if (proceed == null && response != null) {
                }
                if (response2 != null) {
                    if (proceed.code == 304) {
                        Response.Builder newBuilder = response2.newBuilder();
                        newBuilder.headers = combine(response2.headers, proceed.headers).newBuilder();
                        newBuilder.sentRequestAtMillis = proceed.sentRequestAtMillis;
                        newBuilder.receivedResponseAtMillis = proceed.receivedResponseAtMillis;
                        newBuilder.cacheResponse(stripBody(response2));
                        newBuilder.networkResponse(stripBody(proceed));
                        Response build = newBuilder.build();
                        proceed.body.close();
                        this.cache.trackConditionalCacheHit();
                        this.cache.update(response2, build);
                        return build;
                    }
                    Util.closeQuietly(response2.body);
                }
                Response.Builder newBuilder2 = proceed.newBuilder();
                newBuilder2.cacheResponse(stripBody(response2));
                newBuilder2.networkResponse(stripBody(proceed));
                Response build2 = newBuilder2.build();
                if (HttpHeaders.hasBody(build2)) {
                    return cacheWritingResponse(maybeCache(build2, proceed.request, this.cache), build2);
                }
                return build2;
            } finally {
                if (response != null) {
                    Util.closeQuietly(response.body);
                }
            }
        } else if (response2 == null) {
            Response.Builder builder = new Response.Builder();
            builder.request = chain.request();
            builder.protocol = Protocol.HTTP_1_1;
            builder.code = 504;
            builder.message = "Unsatisfiable Request (only-if-cached)";
            builder.body = Util.EMPTY_RESPONSE;
            builder.sentRequestAtMillis = -1;
            builder.receivedResponseAtMillis = System.currentTimeMillis();
            return builder.build();
        } else {
            Response.Builder newBuilder3 = response2.newBuilder();
            newBuilder3.cacheResponse(stripBody(response2));
            return newBuilder3.build();
        }
    }

    public CacheInterceptor(InternalCache internalCache) {
        this.cache = internalCache;
    }
}
