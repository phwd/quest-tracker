package com.squareup.okhttp.internal.http;

import X.AbstractC07630v6;
import X.AbstractC07640v7;
import X.AnonymousClass0B3;
import X.AnonymousClass0Lr;
import X.AnonymousClass0Lw;
import X.AnonymousClass0Lx;
import X.C00560Au;
import X.C00570Av;
import X.C07620v5;
import com.facebook.tigon.iface.TigonRequest;
import com.squareup.okhttp.Address;
import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.CacheStrategy;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.util.Date;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class HttpEngine {
    public static final ResponseBody EMPTY_BODY = new ResponseBody() {
        /* class com.squareup.okhttp.internal.http.HttpEngine.AnonymousClass1 */

        @Override // com.squareup.okhttp.ResponseBody
        public long contentLength() {
            return 0;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public MediaType contentType() {
            return null;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public AnonymousClass0Lw source() {
            return new AnonymousClass0B3();
        }
    };
    public static final int MAX_FOLLOW_UPS = 20;
    public final boolean bufferRequestBody;
    public AnonymousClass0Lx bufferedRequestBody;
    public Response cacheResponse;
    public CacheStrategy cacheStrategy;
    public final boolean callerWritesRequestBody;
    public final OkHttpClient client;
    public final boolean forWebSocket;
    public HttpStream httpStream;
    public Request networkRequest;
    public final Response priorResponse;
    public AbstractC07640v7 requestBodyOut;
    public long sentRequestMillis = -1;
    public CacheRequest storeRequest;
    public final StreamAllocation streamAllocation;
    public boolean transparentGzip;
    public final Request userRequest;
    public Response userResponse;

    public class NetworkInterceptorChain implements Interceptor.Chain {
        public int calls;
        public final int index;
        public final Request request;

        public NetworkInterceptorChain(int i, Request request2) {
            this.index = i;
            this.request = request2;
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Connection connection() {
            return HttpEngine.this.streamAllocation.connection();
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Response proceed(Request request2) throws IOException {
            RequestBody requestBody;
            StringBuilder sb;
            String str;
            this.calls++;
            int i = this.index;
            if (i > 0) {
                Interceptor interceptor = HttpEngine.this.client.networkInterceptors.get(i - 1);
                Address address = connection().getRoute().address;
                HttpUrl httpUrl = request2.url;
                String str2 = httpUrl.host;
                HttpUrl httpUrl2 = address.url;
                if (!str2.equals(httpUrl2.host) || httpUrl.port != httpUrl2.port) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("network interceptor ");
                    sb2.append(interceptor);
                    sb2.append(" must retain the same host and port");
                    str = sb2.toString();
                    throw new IllegalStateException(str);
                } else if (this.calls > 1) {
                    sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(interceptor);
                    sb.append(" must call proceed() exactly once");
                    str = sb.toString();
                    throw new IllegalStateException(str);
                }
            }
            if (this.index < HttpEngine.this.client.networkInterceptors.size()) {
                NetworkInterceptorChain networkInterceptorChain = new NetworkInterceptorChain(this.index + 1, request2);
                Interceptor interceptor2 = HttpEngine.this.client.networkInterceptors.get(this.index);
                Response intercept = interceptor2.intercept(networkInterceptorChain);
                if (networkInterceptorChain.calls != 1) {
                    sb = new StringBuilder();
                    sb.append("network interceptor ");
                    sb.append(interceptor2);
                    sb.append(" must call proceed() exactly once");
                    str = sb.toString();
                    throw new IllegalStateException(str);
                } else if (intercept != null) {
                    return intercept;
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("network interceptor ");
                    sb3.append(interceptor2);
                    sb3.append(" returned null");
                    throw new NullPointerException(sb3.toString());
                }
            } else {
                HttpEngine.this.httpStream.writeRequestHeaders(request2);
                HttpEngine httpEngine = HttpEngine.this;
                httpEngine.networkRequest = request2;
                if (HttpMethod.permitsRequestBody(request2.method) && (requestBody = request2.body) != null) {
                    C00570Av r1 = new C00570Av(httpEngine.httpStream.createRequestBody(request2, requestBody.contentLength()));
                    request2.body.writeTo(r1);
                    r1.close();
                }
                Response readNetworkResponse = HttpEngine.this.readNetworkResponse();
                int i2 = readNetworkResponse.code;
                if (i2 == 204 || i2 == 205) {
                    ResponseBody responseBody = readNetworkResponse.body;
                    if (responseBody.contentLength() > 0) {
                        StringBuilder sb4 = new StringBuilder("HTTP ");
                        sb4.append(i2);
                        sb4.append(" had non-zero Content-Length: ");
                        sb4.append(responseBody.contentLength());
                        throw new ProtocolException(sb4.toString());
                    }
                }
                return readNetworkResponse;
            }
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Request request() {
            return this.request;
        }
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        AbstractC07640v7 body;
        if (cacheRequest == null || (body = cacheRequest.body()) == null) {
            return response;
        }
        final AnonymousClass0Lw source = response.body.source();
        final C00570Av r1 = new C00570Av(body);
        AnonymousClass2 r0 = new AbstractC07630v6() {
            /* class com.squareup.okhttp.internal.http.HttpEngine.AnonymousClass2 */
            public boolean cacheRequestClosed;

            @Override // X.AbstractC07630v6
            public long read(AnonymousClass0B3 r11, long j) throws IOException {
                try {
                    long read = source.read(r11, j);
                    if (read == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            r1.close();
                        }
                        return -1;
                    }
                    r11.A0H(r1.A1V(), r11.A00 - read, read);
                    r1.A2P();
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
                if (com.squareup.okhttp.internal.Util.skipAll(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x000e;
             */
            @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void close() throws java.io.IOException {
                /*
                    r2 = this;
                    boolean r0 = r2.cacheRequestClosed
                    if (r0 != 0) goto L_0x0016
                    java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
                    r0 = 100
                    boolean r0 = com.squareup.okhttp.internal.Util.skipAll(r2, r0, r1)     // Catch:{ IOException -> 0x000e }
                    if (r0 != 0) goto L_0x0016
                L_0x000e:
                    r0 = 1
                    r2.cacheRequestClosed = r0
                    com.squareup.okhttp.internal.http.CacheRequest r0 = r5
                    r0.abort()
                L_0x0016:
                    X.0Lw r0 = r2
                    r0.close()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.HttpEngine.AnonymousClass2.close():void");
            }

            @Override // X.AbstractC07630v6
            public C07620v5 timeout() {
                return source.timeout();
            }
        };
        Response.Builder builder = new Response.Builder(response);
        builder.body = new RealResponseBody(response.headers, new C00560Au(r0));
        return builder.build();
    }

    public static Headers combine(Headers headers, Headers headers2) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if ((!"Warning".equalsIgnoreCase(name) || !value.startsWith(DiskLruCache.VERSION_1)) && (!OkHeaders.isEndToEnd(name) || Headers.get(headers2.namesAndValues, name) == null)) {
                builder.add(name, value);
            }
        }
        int length2 = headers2.namesAndValues.length >> 1;
        for (int i2 = 0; i2 < length2; i2++) {
            String name2 = headers2.name(i2);
            if (!"Content-Length".equalsIgnoreCase(name2) && OkHeaders.isEndToEnd(name2)) {
                builder.add(name2, headers2.value(i2));
            }
        }
        return new Headers(builder);
    }

    private HttpStream connect() throws RouteException, RequestException, IOException {
        boolean z = !this.networkRequest.method.equals(TigonRequest.GET);
        StreamAllocation streamAllocation2 = this.streamAllocation;
        OkHttpClient okHttpClient = this.client;
        return streamAllocation2.newStream(okHttpClient.connectTimeout, okHttpClient.readTimeout, okHttpClient.writeTimeout, okHttpClient.retryOnConnectionFailure, z);
    }

    public static Address createAddress(OkHttpClient okHttpClient, Request request) {
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        HttpUrl httpUrl = request.url;
        SSLSocketFactory sSLSocketFactory = null;
        if (httpUrl.isHttps()) {
            sSLSocketFactory = okHttpClient.sslSocketFactory;
            hostnameVerifier = okHttpClient.hostnameVerifier;
            certificatePinner = okHttpClient.certificatePinner;
        } else {
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host, httpUrl.port, okHttpClient.dns, okHttpClient.socketFactory, sSLSocketFactory, hostnameVerifier, certificatePinner, okHttpClient.authenticator, okHttpClient.proxy, okHttpClient.protocols, okHttpClient.connectionSpecs, okHttpClient.proxySelector);
    }

    public static boolean hasBody(Response response) {
        int i;
        if (response.request.method.equals(TigonRequest.HEAD) || ((((i = response.code) >= 100 && i < 200) || i == 204 || i == 304) && OkHeaders.contentLength(response.headers) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding", null)))) {
            return false;
        }
        return true;
    }

    private void maybeCache() throws IOException {
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        if (internalCache == null) {
            return;
        }
        if (!CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
            Request request = this.networkRequest;
            if (HttpMethod.invalidatesCache(request.method)) {
                try {
                    internalCache.remove(request);
                } catch (IOException unused) {
                }
            }
        } else {
            this.storeRequest = internalCache.put(stripBody(this.userResponse));
        }
    }

    private Request networkRequest(Request request) throws IOException {
        Request.Builder builder = new Request.Builder(request);
        if (request.header("Host") == null) {
            builder.headers.set("Host", Util.hostHeader(request.url));
        }
        if (request.header("Connection") == null) {
            builder.headers.set("Connection", "Keep-Alive");
        }
        if (request.header("Accept-Encoding") == null) {
            this.transparentGzip = true;
            builder.headers.set("Accept-Encoding", "gzip");
        }
        CookieHandler cookieHandler = this.client.cookieHandler;
        if (cookieHandler != null) {
            OkHeaders.addCookies(builder, cookieHandler.get(request.uri(), OkHeaders.toMultimap(builder.build().headers, null)));
        }
        if (request.header("User-Agent") == null) {
            builder.headers.set("User-Agent", "okhttp/2.7.5");
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Response readNetworkResponse() throws IOException {
        this.httpStream.finishRequest();
        Response.Builder readResponseHeaders = this.httpStream.readResponseHeaders();
        readResponseHeaders.request = this.networkRequest;
        readResponseHeaders.handshake = this.streamAllocation.connection().getHandshake();
        readResponseHeaders.headers.set(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis));
        readResponseHeaders.headers.set(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis()));
        Response build = readResponseHeaders.build();
        if (!this.forWebSocket) {
            Response.Builder builder = new Response.Builder(build);
            builder.body = this.httpStream.openResponseBody(build);
            build = builder.build();
        }
        if ("close".equalsIgnoreCase(build.request.header("Connection")) || "close".equalsIgnoreCase(build.header("Connection", null))) {
            this.streamAllocation.noNewStreams();
        }
        return build;
    }

    public static Response stripBody(Response response) {
        if (response == null || response.body == null) {
            return response;
        }
        Response.Builder builder = new Response.Builder(response);
        builder.body = null;
        return builder.build();
    }

    private Response unzip(Response response) throws IOException {
        ResponseBody responseBody;
        if (!this.transparentGzip || !"gzip".equalsIgnoreCase(this.userResponse.header("Content-Encoding", null)) || (responseBody = response.body) == null) {
            return response;
        }
        AnonymousClass0Lr r4 = new AnonymousClass0Lr(responseBody.source());
        Headers.Builder newBuilder = response.headers.newBuilder();
        newBuilder.removeAll("Content-Encoding");
        newBuilder.removeAll("Content-Length");
        Headers headers = new Headers(newBuilder);
        Response.Builder builder = new Response.Builder(response);
        builder.headers = headers.newBuilder();
        builder.body = new RealResponseBody(headers, new C00560Au(r4));
        return builder.build();
    }

    public static boolean validate(Response response, Response response2) {
        Date date;
        Date date2;
        if (response2.code == 304 || ((date = response.headers.getDate("Last-Modified")) != null && (date2 = response2.headers.getDate("Last-Modified")) != null && date2.getTime() < date.getTime())) {
            return true;
        }
        return false;
    }

    public void cancel() {
        this.streamAllocation.cancel();
    }

    public StreamAllocation close() {
        AnonymousClass0Lx r0 = this.bufferedRequestBody;
        if (r0 != null) {
            Util.closeQuietly(r0);
        } else {
            AbstractC07640v7 r02 = this.requestBodyOut;
            if (r02 != null) {
                Util.closeQuietly(r02);
            }
        }
        Response response = this.userResponse;
        if (response != null) {
            Util.closeQuietly(response.body);
        } else {
            this.streamAllocation.connectionFailed();
        }
        return this.streamAllocation;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.okhttp.Request followUpRequest() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 224
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.HttpEngine.followUpRequest():com.squareup.okhttp.Request");
    }

    public AnonymousClass0Lx getBufferedRequestBody() {
        AnonymousClass0Lx r0 = this.bufferedRequestBody;
        if (r0 != null) {
            return r0;
        }
        AbstractC07640v7 requestBody = getRequestBody();
        if (requestBody == null) {
            return null;
        }
        C00570Av r02 = new C00570Av(requestBody);
        this.bufferedRequestBody = r02;
        return r02;
    }

    public Connection getConnection() {
        return this.streamAllocation.connection();
    }

    public AbstractC07640v7 getRequestBody() {
        if (this.cacheStrategy != null) {
            return this.requestBodyOut;
        }
        throw new IllegalStateException();
    }

    public Response getResponse() {
        Response response = this.userResponse;
        if (response != null) {
            return response;
        }
        throw new IllegalStateException();
    }

    public boolean hasResponse() {
        if (this.userResponse != null) {
            return true;
        }
        return false;
    }

    public boolean permitsRequestBody(Request request) {
        return HttpMethod.permitsRequestBody(request.method);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readResponse() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 338
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.HttpEngine.readResponse():void");
    }

    public void receiveHeaders(Headers headers) throws IOException {
        CookieHandler cookieHandler = this.client.cookieHandler;
        if (cookieHandler != null) {
            cookieHandler.put(this.userRequest.uri(), OkHeaders.toMultimap(headers, null));
        }
    }

    public void releaseStreamAllocation() throws IOException {
        this.streamAllocation.release();
    }

    public boolean sameConnection(HttpUrl httpUrl) {
        HttpUrl httpUrl2 = this.userRequest.url;
        if (!httpUrl2.host.equals(httpUrl.host) || httpUrl2.port != httpUrl.port || !httpUrl2.scheme.equals(httpUrl.scheme)) {
            return false;
        }
        return true;
    }

    public void sendRequest() throws RequestException, RouteException, IOException {
        Response response;
        Response.Builder builder;
        AbstractC07640v7 createRequestBody;
        int i;
        if (this.cacheStrategy != null) {
            return;
        }
        if (this.httpStream == null) {
            Request networkRequest2 = networkRequest(this.userRequest);
            InternalCache internalCache = Internal.instance.internalCache(this.client);
            if (internalCache != null) {
                response = internalCache.get(networkRequest2);
            } else {
                response = null;
            }
            CacheStrategy cacheStrategy2 = new CacheStrategy.Factory(System.currentTimeMillis(), networkRequest2, response).get();
            this.cacheStrategy = cacheStrategy2;
            this.networkRequest = cacheStrategy2.networkRequest;
            this.cacheResponse = cacheStrategy2.cacheResponse;
            if (internalCache != null) {
                internalCache.trackResponse(cacheStrategy2);
            }
            if (response != null && this.cacheResponse == null) {
                Util.closeQuietly(response.body);
            }
            if (this.networkRequest != null) {
                HttpStream connect = connect();
                this.httpStream = connect;
                connect.setHttpEngine(this);
                if (this.callerWritesRequestBody) {
                    Request request = this.networkRequest;
                    if (HttpMethod.permitsRequestBody(request.method) && this.requestBodyOut == null) {
                        long contentLength = OkHeaders.contentLength(networkRequest2.headers);
                        if (!this.bufferRequestBody) {
                            this.httpStream.writeRequestHeaders(request);
                            createRequestBody = this.httpStream.createRequestBody(this.networkRequest, contentLength);
                        } else if (contentLength <= 2147483647L) {
                            if (contentLength != -1) {
                                this.httpStream.writeRequestHeaders(request);
                                i = (int) contentLength;
                            } else {
                                i = -1;
                            }
                            createRequestBody = new RetryableSink(i);
                        } else {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        }
                        this.requestBodyOut = createRequestBody;
                        return;
                    }
                    return;
                }
                return;
            }
            Response response2 = this.cacheResponse;
            if (response2 != null) {
                builder = new Response.Builder(response2);
                builder.request = this.userRequest;
                builder.priorResponse(stripBody(this.priorResponse));
                builder.cacheResponse(stripBody(this.cacheResponse));
            } else {
                builder = new Response.Builder();
                builder.request = this.userRequest;
                builder.priorResponse(stripBody(this.priorResponse));
                builder.protocol = Protocol.HTTP_1_1;
                builder.code = 504;
                builder.message = "Unsatisfiable Request (only-if-cached)";
                builder.body = EMPTY_BODY;
            }
            Response build = builder.build();
            this.userResponse = build;
            this.userResponse = unzip(build);
            return;
        }
        throw new IllegalStateException();
    }

    public void writingRequestHeaders() {
        if (this.sentRequestMillis == -1) {
            this.sentRequestMillis = System.currentTimeMillis();
            return;
        }
        throw new IllegalStateException();
    }

    public HttpEngine(OkHttpClient okHttpClient, Request request, boolean z, boolean z2, boolean z3, StreamAllocation streamAllocation2, RetryableSink retryableSink, Response response) {
        this.client = okHttpClient;
        this.userRequest = request;
        this.bufferRequestBody = z;
        this.callerWritesRequestBody = z2;
        this.forWebSocket = z3;
        this.streamAllocation = streamAllocation2 == null ? new StreamAllocation(okHttpClient.connectionPool, createAddress(okHttpClient, request)) : streamAllocation2;
        this.requestBodyOut = retryableSink;
        this.priorResponse = response;
    }

    public Request getRequest() {
        return this.userRequest;
    }

    public HttpEngine recover(RouteException routeException) {
        if (!this.streamAllocation.recover(routeException) || !this.client.retryOnConnectionFailure) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) this.requestBodyOut, this.priorResponse);
    }

    public HttpEngine recover(IOException iOException) {
        return recover(iOException, this.requestBodyOut);
    }

    public HttpEngine recover(IOException iOException, AbstractC07640v7 r11) {
        if (!this.streamAllocation.recover(iOException, r11) || !this.client.retryOnConnectionFailure) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) r11, this.priorResponse);
    }
}
