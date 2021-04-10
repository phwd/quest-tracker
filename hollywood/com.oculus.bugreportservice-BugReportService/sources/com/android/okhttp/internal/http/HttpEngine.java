package com.android.okhttp.internal.http;

import com.android.okhttp.Address;
import com.android.okhttp.CertificatePinner;
import com.android.okhttp.Connection;
import com.android.okhttp.Headers;
import com.android.okhttp.HttpUrl;
import com.android.okhttp.Interceptor;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Protocol;
import com.android.okhttp.Request;
import com.android.okhttp.Response;
import com.android.okhttp.ResponseBody;
import com.android.okhttp.Route;
import com.android.okhttp.internal.Internal;
import com.android.okhttp.internal.InternalCache;
import com.android.okhttp.internal.Util;
import com.android.okhttp.internal.Version;
import com.android.okhttp.internal.http.CacheStrategy;
import com.android.okhttp.internal.io.RealConnection;
import com.android.okhttp.okio.Buffer;
import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.BufferedSource;
import com.android.okhttp.okio.GzipSource;
import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Sink;
import com.android.okhttp.okio.Source;
import com.android.okhttp.okio.Timeout;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class HttpEngine {
    private static final ResponseBody EMPTY_BODY = new ResponseBody() {
        /* class com.android.okhttp.internal.http.HttpEngine.AnonymousClass1 */

        @Override // com.android.okhttp.ResponseBody
        public long contentLength() {
            return 0;
        }

        @Override // com.android.okhttp.ResponseBody
        public BufferedSource source() {
            return new Buffer();
        }
    };
    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private Response cacheResponse;
    private CacheStrategy cacheStrategy;
    private final boolean callerWritesRequestBody;
    final OkHttpClient client;
    private final boolean forWebSocket;
    private HttpStream httpStream;
    private Request networkRequest;
    private final Response priorResponse;
    private Sink requestBodyOut;
    long sentRequestMillis = -1;
    private CacheRequest storeRequest;
    public final StreamAllocation streamAllocation;
    private boolean transparentGzip;
    private final Request userRequest;
    private Response userResponse;

    public HttpEngine(OkHttpClient okHttpClient, Request request, boolean z, boolean z2, boolean z3, StreamAllocation streamAllocation2, RetryableSink retryableSink, Response response) {
        this.client = okHttpClient;
        this.userRequest = request;
        this.bufferRequestBody = z;
        this.callerWritesRequestBody = z2;
        this.forWebSocket = z3;
        this.streamAllocation = streamAllocation2 == null ? new StreamAllocation(okHttpClient.getConnectionPool(), createAddress(okHttpClient, request)) : streamAllocation2;
        this.requestBodyOut = retryableSink;
        this.priorResponse = response;
    }

    public void sendRequest() {
        if (this.cacheStrategy == null) {
            if (this.httpStream == null) {
                Request networkRequest2 = networkRequest(this.userRequest);
                InternalCache internalCache = Internal.instance.internalCache(this.client);
                Response response = internalCache != null ? internalCache.get(networkRequest2) : null;
                this.cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), networkRequest2, response).get();
                CacheStrategy cacheStrategy2 = this.cacheStrategy;
                this.networkRequest = cacheStrategy2.networkRequest;
                this.cacheResponse = cacheStrategy2.cacheResponse;
                if (internalCache != null) {
                    internalCache.trackResponse(cacheStrategy2);
                }
                if (response != null && this.cacheResponse == null) {
                    Util.closeQuietly(response.body());
                }
                if (this.networkRequest != null) {
                    this.httpStream = connect();
                    this.httpStream.setHttpEngine(this);
                    if (this.callerWritesRequestBody && permitsRequestBody(this.networkRequest) && this.requestBodyOut == null) {
                        long contentLength = OkHeaders.contentLength(networkRequest2);
                        if (!this.bufferRequestBody) {
                            this.httpStream.writeRequestHeaders(this.networkRequest);
                            this.requestBodyOut = this.httpStream.createRequestBody(this.networkRequest, contentLength);
                        } else if (contentLength > 2147483647L) {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        } else if (contentLength != -1) {
                            this.httpStream.writeRequestHeaders(this.networkRequest);
                            this.requestBodyOut = new RetryableSink((int) contentLength);
                        } else {
                            this.requestBodyOut = new RetryableSink();
                        }
                    }
                } else {
                    Response response2 = this.cacheResponse;
                    if (response2 != null) {
                        Response.Builder newBuilder = response2.newBuilder();
                        newBuilder.request(this.userRequest);
                        newBuilder.priorResponse(stripBody(this.priorResponse));
                        newBuilder.cacheResponse(stripBody(this.cacheResponse));
                        this.userResponse = newBuilder.build();
                    } else {
                        Response.Builder builder = new Response.Builder();
                        builder.request(this.userRequest);
                        builder.priorResponse(stripBody(this.priorResponse));
                        builder.protocol(Protocol.HTTP_1_1);
                        builder.code(504);
                        builder.message("Unsatisfiable Request (only-if-cached)");
                        builder.body(EMPTY_BODY);
                        this.userResponse = builder.build();
                    }
                    this.userResponse = unzip(this.userResponse);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private HttpStream connect() {
        return this.streamAllocation.newStream(this.client.getConnectTimeout(), this.client.getReadTimeout(), this.client.getWriteTimeout(), this.client.getRetryOnConnectionFailure(), !this.networkRequest.method().equals("GET"));
    }

    private static Response stripBody(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.body(null);
        return newBuilder.build();
    }

    public void writingRequestHeaders() {
        if (this.sentRequestMillis == -1) {
            this.sentRequestMillis = System.currentTimeMillis();
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public boolean permitsRequestBody(Request request) {
        return HttpMethod.permitsRequestBody(request.method());
    }

    public Sink getRequestBody() {
        if (this.cacheStrategy != null) {
            return this.requestBodyOut;
        }
        throw new IllegalStateException();
    }

    public boolean hasResponse() {
        return this.userResponse != null;
    }

    public Request getRequest() {
        return this.userRequest;
    }

    public Response getResponse() {
        Response response = this.userResponse;
        if (response != null) {
            return response;
        }
        throw new IllegalStateException();
    }

    public Connection getConnection() {
        return this.streamAllocation.connection();
    }

    public HttpEngine recover(RouteException routeException) {
        if (!this.streamAllocation.recover(routeException) || !this.client.getRetryOnConnectionFailure()) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) this.requestBodyOut, this.priorResponse);
    }

    public HttpEngine recover(IOException iOException, Sink sink) {
        if (!this.streamAllocation.recover(iOException, sink) || !this.client.getRetryOnConnectionFailure()) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) sink, this.priorResponse);
    }

    public HttpEngine recover(IOException iOException) {
        return recover(iOException, this.requestBodyOut);
    }

    private void maybeCache() {
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        if (internalCache != null) {
            if (CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
                this.storeRequest = internalCache.put(stripBody(this.userResponse));
            } else if (HttpMethod.invalidatesCache(this.networkRequest.method())) {
                try {
                    internalCache.remove(this.networkRequest);
                } catch (IOException unused) {
                }
            }
        }
    }

    public void releaseStreamAllocation() {
        this.streamAllocation.release();
    }

    public StreamAllocation close() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            Util.closeQuietly(bufferedSink);
        } else {
            Sink sink = this.requestBodyOut;
            if (sink != null) {
                Util.closeQuietly(sink);
            }
        }
        Response response = this.userResponse;
        if (response != null) {
            Util.closeQuietly(response.body());
        } else {
            this.streamAllocation.connectionFailed();
        }
        return this.streamAllocation;
    }

    private Response unzip(Response response) {
        if (!this.transparentGzip || !"gzip".equalsIgnoreCase(this.userResponse.header("Content-Encoding")) || response.body() == null) {
            return response;
        }
        GzipSource gzipSource = new GzipSource(response.body().source());
        Headers.Builder newBuilder = response.headers().newBuilder();
        newBuilder.removeAll("Content-Encoding");
        newBuilder.removeAll("Content-Length");
        Headers build = newBuilder.build();
        Response.Builder newBuilder2 = response.newBuilder();
        newBuilder2.headers(build);
        newBuilder2.body(new RealResponseBody(build, Okio.buffer(gzipSource)));
        return newBuilder2.build();
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int code = response.code();
        if (((code >= 100 && code < 200) || code == 204 || code == 304) && OkHeaders.contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return false;
        }
        return true;
    }

    private Request networkRequest(Request request) {
        Request.Builder newBuilder = request.newBuilder();
        if (request.header("Host") == null) {
            newBuilder.header("Host", Util.hostHeader(request.httpUrl(), false));
        }
        if (request.header("Connection") == null) {
            newBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header("Accept-Encoding") == null) {
            this.transparentGzip = true;
            newBuilder.header("Accept-Encoding", "gzip");
        }
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            OkHeaders.addCookies(newBuilder, cookieHandler.get(request.uri(), OkHeaders.toMultimap(newBuilder.build().headers(), null)));
        }
        if (request.header("User-Agent") == null) {
            newBuilder.header("User-Agent", Version.userAgent());
        }
        return newBuilder.build();
    }

    public void readResponse() {
        Response response;
        if (this.userResponse == null) {
            if (this.networkRequest == null && this.cacheResponse == null) {
                throw new IllegalStateException("call sendRequest() first!");
            }
            Request request = this.networkRequest;
            if (request != null) {
                if (this.forWebSocket) {
                    this.httpStream.writeRequestHeaders(request);
                    response = readNetworkResponse();
                } else if (!this.callerWritesRequestBody) {
                    response = new NetworkInterceptorChain(0, request).proceed(this.networkRequest);
                } else {
                    BufferedSink bufferedSink = this.bufferedRequestBody;
                    if (bufferedSink != null && bufferedSink.buffer().size() > 0) {
                        this.bufferedRequestBody.emit();
                    }
                    if (this.sentRequestMillis == -1) {
                        if (OkHeaders.contentLength(this.networkRequest) == -1) {
                            Sink sink = this.requestBodyOut;
                            if (sink instanceof RetryableSink) {
                                long contentLength = ((RetryableSink) sink).contentLength();
                                Request.Builder newBuilder = this.networkRequest.newBuilder();
                                newBuilder.header("Content-Length", Long.toString(contentLength));
                                this.networkRequest = newBuilder.build();
                            }
                        }
                        this.httpStream.writeRequestHeaders(this.networkRequest);
                    }
                    Sink sink2 = this.requestBodyOut;
                    if (sink2 != null) {
                        BufferedSink bufferedSink2 = this.bufferedRequestBody;
                        if (bufferedSink2 != null) {
                            bufferedSink2.close();
                        } else {
                            sink2.close();
                        }
                        Sink sink3 = this.requestBodyOut;
                        if (sink3 instanceof RetryableSink) {
                            this.httpStream.writeRequestBody((RetryableSink) sink3);
                        }
                    }
                    response = readNetworkResponse();
                }
                receiveHeaders(response.headers());
                Response response2 = this.cacheResponse;
                if (response2 != null) {
                    if (validate(response2, response)) {
                        Response.Builder newBuilder2 = this.cacheResponse.newBuilder();
                        newBuilder2.request(this.userRequest);
                        newBuilder2.priorResponse(stripBody(this.priorResponse));
                        newBuilder2.headers(combine(this.cacheResponse.headers(), response.headers()));
                        newBuilder2.cacheResponse(stripBody(this.cacheResponse));
                        newBuilder2.networkResponse(stripBody(response));
                        this.userResponse = newBuilder2.build();
                        response.body().close();
                        releaseStreamAllocation();
                        InternalCache internalCache = Internal.instance.internalCache(this.client);
                        internalCache.trackConditionalCacheHit();
                        internalCache.update(this.cacheResponse, stripBody(this.userResponse));
                        this.userResponse = unzip(this.userResponse);
                        return;
                    }
                    Util.closeQuietly(this.cacheResponse.body());
                }
                Response.Builder newBuilder3 = response.newBuilder();
                newBuilder3.request(this.userRequest);
                newBuilder3.priorResponse(stripBody(this.priorResponse));
                newBuilder3.cacheResponse(stripBody(this.cacheResponse));
                newBuilder3.networkResponse(stripBody(response));
                this.userResponse = newBuilder3.build();
                if (hasBody(this.userResponse)) {
                    maybeCache();
                    this.userResponse = unzip(cacheWritingResponse(this.storeRequest, this.userResponse));
                }
            }
        }
    }

    class NetworkInterceptorChain implements Interceptor.Chain {
        private int calls;
        private final int index;
        private final Request request;

        NetworkInterceptorChain(int i, Request request2) {
            this.index = i;
            this.request = request2;
        }

        public Connection connection() {
            return HttpEngine.this.streamAllocation.connection();
        }

        @Override // com.android.okhttp.Interceptor.Chain
        public Response proceed(Request request2) {
            this.calls++;
            if (this.index > 0) {
                Interceptor interceptor = (Interceptor) HttpEngine.this.client.networkInterceptors().get(this.index - 1);
                Address address = connection().getRoute().getAddress();
                if (!request2.httpUrl().host().equals(address.getUriHost()) || request2.httpUrl().port() != address.getUriPort()) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must retain the same host and port");
                } else if (this.calls > 1) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
                }
            }
            if (this.index < HttpEngine.this.client.networkInterceptors().size()) {
                NetworkInterceptorChain networkInterceptorChain = new NetworkInterceptorChain(this.index + 1, request2);
                Interceptor interceptor2 = (Interceptor) HttpEngine.this.client.networkInterceptors().get(this.index);
                Response intercept = interceptor2.intercept(networkInterceptorChain);
                if (networkInterceptorChain.calls != 1) {
                    throw new IllegalStateException("network interceptor " + interceptor2 + " must call proceed() exactly once");
                } else if (intercept != null) {
                    return intercept;
                } else {
                    throw new NullPointerException("network interceptor " + interceptor2 + " returned null");
                }
            } else {
                HttpEngine.this.httpStream.writeRequestHeaders(request2);
                HttpEngine.this.networkRequest = request2;
                if (HttpEngine.this.permitsRequestBody(request2) && request2.body() != null) {
                    BufferedSink buffer = Okio.buffer(HttpEngine.this.httpStream.createRequestBody(request2, request2.body().contentLength()));
                    request2.body().writeTo(buffer);
                    buffer.close();
                }
                Response readNetworkResponse = HttpEngine.this.readNetworkResponse();
                int code = readNetworkResponse.code();
                if ((code != 204 && code != 205) || readNetworkResponse.body().contentLength() <= 0) {
                    return readNetworkResponse;
                }
                throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + readNetworkResponse.body().contentLength());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Response readNetworkResponse() {
        this.httpStream.finishRequest();
        Response.Builder readResponseHeaders = this.httpStream.readResponseHeaders();
        readResponseHeaders.request(this.networkRequest);
        readResponseHeaders.handshake(this.streamAllocation.connection().getHandshake());
        readResponseHeaders.header(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis));
        readResponseHeaders.header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis()));
        Response build = readResponseHeaders.build();
        if (!this.forWebSocket) {
            Response.Builder newBuilder = build.newBuilder();
            newBuilder.body(this.httpStream.openResponseBody(build));
            build = newBuilder.build();
        }
        if ("close".equalsIgnoreCase(build.request().header("Connection")) || "close".equalsIgnoreCase(build.header("Connection"))) {
            this.streamAllocation.noNewStreams();
        }
        return build;
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) {
        Sink body;
        if (cacheRequest == null || (body = cacheRequest.body()) == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink buffer = Okio.buffer(body);
        AnonymousClass2 r2 = new Source() {
            /* class com.android.okhttp.internal.http.HttpEngine.AnonymousClass2 */
            boolean cacheRequestClosed;

            @Override // com.android.okhttp.okio.Source
            public long read(Buffer buffer, long j) {
                try {
                    long read = source.read(buffer, j);
                    if (read == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            buffer.close();
                        }
                        return -1;
                    }
                    buffer.copyTo(buffer.buffer(), buffer.size() - read, read);
                    buffer.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            @Override // com.android.okhttp.okio.Source
            public Timeout timeout() {
                return source.timeout();
            }

            @Override // java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
            public void close() {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        };
        Response.Builder newBuilder = response.newBuilder();
        newBuilder.body(new RealResponseBody(response.headers(), Okio.buffer(r2)));
        return newBuilder.build();
    }

    private static boolean validate(Response response, Response response2) {
        Date date;
        if (response2.code() == 304) {
            return true;
        }
        Date date2 = response.headers().getDate("Last-Modified");
        if (date2 == null || (date = response2.headers().getDate("Last-Modified")) == null || date.getTime() >= date2.getTime()) {
            return false;
        }
        return true;
    }

    private static Headers combine(Headers headers, Headers headers2) {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if ((!"Warning".equalsIgnoreCase(name) || !value.startsWith("1")) && (!OkHeaders.isEndToEnd(name) || headers2.get(name) == null)) {
                builder.add(name, value);
            }
        }
        int size2 = headers2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String name2 = headers2.name(i2);
            if (!"Content-Length".equalsIgnoreCase(name2) && OkHeaders.isEndToEnd(name2)) {
                builder.add(name2, headers2.value(i2));
            }
        }
        return builder.build();
    }

    public void receiveHeaders(Headers headers) {
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            cookieHandler.put(this.userRequest.uri(), OkHeaders.toMultimap(headers, null));
        }
    }

    public Request followUpRequest() {
        Proxy proxy;
        String header;
        HttpUrl resolve;
        if (this.userResponse != null) {
            RealConnection connection = this.streamAllocation.connection();
            Route route = connection != null ? connection.getRoute() : null;
            if (route != null) {
                proxy = route.getProxy();
            } else {
                proxy = this.client.getProxy();
            }
            int code = this.userResponse.code();
            String method = this.userRequest.method();
            if (code != 307 && code != 308) {
                if (code != 401) {
                    if (code != 407) {
                        switch (code) {
                            case 300:
                            case 301:
                            case 302:
                            case 303:
                                break;
                            default:
                                return null;
                        }
                    } else if (proxy.type() != Proxy.Type.HTTP) {
                        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                    }
                }
                return OkHeaders.processAuthHeader(this.client.getAuthenticator(), this.userResponse, proxy);
            } else if (!method.equals("GET") && !method.equals("HEAD")) {
                return null;
            }
            if (!this.client.getFollowRedirects() || (header = this.userResponse.header("Location")) == null || (resolve = this.userRequest.httpUrl().resolve(header)) == null) {
                return null;
            }
            if (!resolve.scheme().equals(this.userRequest.httpUrl().scheme()) && !this.client.getFollowSslRedirects()) {
                return null;
            }
            Request.Builder newBuilder = this.userRequest.newBuilder();
            if (HttpMethod.permitsRequestBody(method)) {
                if (HttpMethod.redirectsToGet(method)) {
                    newBuilder.method("GET", null);
                } else {
                    newBuilder.method(method, null);
                }
                newBuilder.removeHeader("Transfer-Encoding");
                newBuilder.removeHeader("Content-Length");
                newBuilder.removeHeader("Content-Type");
            }
            if (!sameConnection(resolve)) {
                newBuilder.removeHeader("Authorization");
            }
            newBuilder.url(resolve);
            return newBuilder.build();
        }
        throw new IllegalStateException();
    }

    public boolean sameConnection(HttpUrl httpUrl) {
        HttpUrl httpUrl2 = this.userRequest.httpUrl();
        return httpUrl2.host().equals(httpUrl.host()) && httpUrl2.port() == httpUrl.port() && httpUrl2.scheme().equals(httpUrl.scheme());
    }

    private static Address createAddress(OkHttpClient okHttpClient, Request request) {
        CertificatePinner certificatePinner;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        if (request.isHttps()) {
            SSLSocketFactory sslSocketFactory = okHttpClient.getSslSocketFactory();
            hostnameVerifier = okHttpClient.getHostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = okHttpClient.getCertificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(request.httpUrl().host(), request.httpUrl().port(), okHttpClient.getDns(), okHttpClient.getSocketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, okHttpClient.getAuthenticator(), okHttpClient.getProxy(), okHttpClient.getProtocols(), okHttpClient.getConnectionSpecs(), okHttpClient.getProxySelector());
    }
}
