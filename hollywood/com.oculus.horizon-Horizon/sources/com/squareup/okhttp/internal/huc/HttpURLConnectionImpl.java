package com.squareup.okhttp.internal.huc;

import X.AbstractC07640v7;
import X.AnonymousClass006;
import X.AnonymousClass0Lx;
import com.facebook.acra.util.HttpRequest;
import com.facebook.tigon.iface.TigonRequest;
import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpDate;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.RetryableSink;
import com.squareup.okhttp.internal.http.StatusLine;
import com.squareup.okhttp.internal.http.StreamAllocation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketPermission;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpURLConnectionImpl extends HttpURLConnection {
    public static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create((MediaType) null, new byte[0]);
    public static final Set<String> METHODS = new LinkedHashSet(Arrays.asList("OPTIONS", TigonRequest.GET, TigonRequest.HEAD, TigonRequest.POST, "PUT", "DELETE", "TRACE", "PATCH"));
    public final OkHttpClient client;
    public long fixedContentLength = -1;
    public int followUpCount;
    public Handshake handshake;
    public HttpEngine httpEngine;
    public IOException httpEngineFailure;
    public Headers.Builder requestHeaders = new Headers.Builder();
    public Headers responseHeaders;
    public Route route;

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.httpEngine = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean execute(boolean r5) throws java.io.IOException {
        /*
            r4 = this;
            r2 = 1
            r3 = 0
            com.squareup.okhttp.internal.http.HttpEngine r0 = r4.httpEngine     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            r0.sendRequest()     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            com.squareup.okhttp.internal.http.HttpEngine r0 = r4.httpEngine     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            com.squareup.okhttp.internal.http.StreamAllocation r0 = r0.streamAllocation     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            com.squareup.okhttp.internal.io.RealConnection r1 = r0.connection()     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            if (r1 == 0) goto L_0x001e
            com.squareup.okhttp.Route r0 = r1.getRoute()     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            r4.route = r0     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            com.squareup.okhttp.Handshake r0 = r1.getHandshake()     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
        L_0x001b:
            r4.handshake = r0     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            goto L_0x0022
        L_0x001e:
            r0 = 0
            r4.route = r0     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            goto L_0x001b
        L_0x0022:
            if (r5 == 0) goto L_0x0029
            com.squareup.okhttp.internal.http.HttpEngine r0 = r4.httpEngine     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
            r0.readResponse()     // Catch:{ RequestException -> 0x004a, RouteException -> 0x0036, IOException -> 0x002a }
        L_0x0029:
            return r2
        L_0x002a:
            r2 = move-exception
            com.squareup.okhttp.internal.http.HttpEngine r1 = r4.httpEngine     // Catch:{ all -> 0x0052 }
            X.0v7 r0 = r1.requestBodyOut     // Catch:{ all -> 0x0052 }
            com.squareup.okhttp.internal.http.HttpEngine r0 = r1.recover(r2, r0)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x004f
            goto L_0x0044
        L_0x0036:
            r1 = move-exception
            com.squareup.okhttp.internal.http.HttpEngine r0 = r4.httpEngine     // Catch:{ all -> 0x0052 }
            com.squareup.okhttp.internal.http.HttpEngine r0 = r0.recover(r1)     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x0044
            java.io.IOException r0 = r1.lastException     // Catch:{ all -> 0x0052 }
            r4.httpEngineFailure = r0     // Catch:{ all -> 0x0052 }
            throw r0     // Catch:{ all -> 0x0052 }
        L_0x0044:
            r4.httpEngine = r0     // Catch:{ all -> 0x0047 }
            goto L_0x0049
        L_0x0047:
            r1 = move-exception
            throw r1
        L_0x0049:
            return r3
        L_0x004a:
            r0 = move-exception
            java.io.IOException r2 = r0.getCause()
        L_0x004f:
            r4.httpEngineFailure = r2
            throw r2
        L_0x0052:
            r1 = move-exception
            com.squareup.okhttp.internal.http.HttpEngine r0 = r4.httpEngine
            com.squareup.okhttp.internal.http.StreamAllocation r0 = r0.close()
            r0.release()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.huc.HttpURLConnectionImpl.execute(boolean):boolean");
    }

    public final InputStream getErrorStream() {
        try {
            HttpEngine response = getResponse();
            if (!HttpEngine.hasBody(response.getResponse()) || response.getResponse().code < 400) {
                return null;
            }
            return response.getResponse().body.byteStream();
        } catch (IOException unused) {
            return null;
        }
    }

    private String defaultUserAgent() {
        String property = System.getProperty("http.agent");
        if (property != null) {
            return Util.toHumanReadableAscii(property);
        }
        return "okhttp/2.7.5";
    }

    private Headers getHeaders() throws IOException {
        Headers headers = this.responseHeaders;
        if (headers != null) {
            return headers;
        }
        Response response = getResponse().getResponse();
        Headers.Builder newBuilder = response.headers.newBuilder();
        newBuilder.add(OkHeaders.SELECTED_PROTOCOL, response.protocol.toString());
        newBuilder.add(OkHeaders.RESPONSE_SOURCE, responseSourceHeader(response));
        Headers headers2 = new Headers(newBuilder);
        this.responseHeaders = headers2;
        return headers2;
    }

    private void initHttpEngine() throws IOException {
        IOException iOException = this.httpEngineFailure;
        if (iOException != null) {
            throw iOException;
        } else if (this.httpEngine == null) {
            this.connected = true;
            try {
                if (this.doOutput) {
                    String str = this.method;
                    if (str.equals(TigonRequest.GET)) {
                        this.method = TigonRequest.POST;
                    } else if (!HttpMethod.permitsRequestBody(str)) {
                        throw new ProtocolException(AnonymousClass006.A05(str, " does not support writing"));
                    }
                }
                this.httpEngine = newHttpEngine(this.method, null, null, null);
            } catch (IOException e) {
                this.httpEngineFailure = e;
                throw e;
            }
        }
    }

    public static String responseSourceHeader(Response response) {
        StringBuilder sb;
        int i;
        String str;
        Response response2 = response.networkResponse;
        if (response2 == null) {
            if (response.cacheResponse == null) {
                return "NONE";
            }
            str = "CACHE ";
        } else if (response.cacheResponse == null) {
            str = "NETWORK ";
        } else {
            sb = new StringBuilder("CONDITIONAL_CACHE ");
            i = response2.code;
            sb.append(i);
            return sb.toString();
        }
        sb = new StringBuilder(str);
        i = response.code;
        sb.append(i);
        return sb.toString();
    }

    private void setProtocols(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.addAll(this.client.protocols);
        }
        for (String str2 : str.split(",", -1)) {
            try {
                arrayList.add(Protocol.get(str2));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        this.client.setProtocols(arrayList);
    }

    public final void addRequestProperty(String str, String str2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot add request property after connection is made");
        } else if (str == null) {
            throw new NullPointerException("field == null");
        } else if (str2 == null) {
            Platform.PLATFORM.logW(AnonymousClass006.A07("Ignoring header ", str, " because its value was null."));
        } else if ("X-Android-Transports".equals(str) || "X-Android-Protocols".equals(str)) {
            setProtocols(str2, true);
        } else {
            this.requestHeaders.add(str, str2);
        }
    }

    public final void disconnect() {
        HttpEngine httpEngine2 = this.httpEngine;
        if (httpEngine2 != null) {
            httpEngine2.cancel();
        }
    }

    public int getConnectTimeout() {
        return this.client.connectTimeout;
    }

    @Override // java.net.URLConnection
    public final InputStream getInputStream() throws IOException {
        if (this.doInput) {
            HttpEngine response = getResponse();
            if (getResponseCode() < 400) {
                return response.getResponse().body.byteStream();
            }
            throw new FileNotFoundException(this.url.toString());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    public boolean getInstanceFollowRedirects() {
        return this.client.followRedirects;
    }

    public int getReadTimeout() {
        return this.client.readTimeout;
    }

    @Override // java.net.URLConnection
    public final Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return OkHeaders.toMultimap(new Headers(this.requestHeaders), null);
        }
        throw new IllegalStateException("Cannot access request header fields after connection is set");
    }

    public final String getRequestProperty(String str) {
        if (str == null) {
            return null;
        }
        return this.requestHeaders.get(str);
    }

    public void setConnectTimeout(int i) {
        this.client.setConnectTimeout((long) i, TimeUnit.MILLISECONDS);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.client.followRedirects = z;
    }

    public void setReadTimeout(int i) {
        this.client.setReadTimeout((long) i, TimeUnit.MILLISECONDS);
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        if (METHODS.contains(str)) {
            this.method = str;
            return;
        }
        StringBuilder sb = new StringBuilder("Expected one of ");
        sb.append(METHODS);
        sb.append(" but was ");
        sb.append(str);
        throw new ProtocolException(sb.toString());
    }

    public final void setRequestProperty(String str, String str2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot set request property after connection is made");
        } else if (str == null) {
            throw new NullPointerException("field == null");
        } else if (str2 == null) {
            Platform.PLATFORM.logW(AnonymousClass006.A07("Ignoring header ", str, " because its value was null."));
        } else if ("X-Android-Transports".equals(str) || "X-Android-Protocols".equals(str)) {
            setProtocols(str2, false);
        } else {
            this.requestHeaders.set(str, str2);
        }
    }

    public final boolean usingProxy() {
        Proxy proxy;
        Route route2 = this.route;
        if (route2 != null) {
            proxy = route2.proxy;
        } else {
            proxy = this.client.proxy;
        }
        if (proxy == null || proxy.type() == Proxy.Type.DIRECT) {
            return false;
        }
        return true;
    }

    public HttpURLConnectionImpl(URL url, OkHttpClient okHttpClient) {
        super(url);
        this.client = okHttpClient;
    }

    private HttpEngine getResponse() throws IOException {
        initHttpEngine();
        HttpEngine httpEngine2 = this.httpEngine;
        if (httpEngine2.hasResponse()) {
            return httpEngine2;
        }
        while (true) {
            if (execute(true)) {
                HttpEngine httpEngine3 = this.httpEngine;
                Response response = httpEngine3.getResponse();
                Request followUpRequest = httpEngine3.followUpRequest();
                if (followUpRequest == null) {
                    this.httpEngine.releaseStreamAllocation();
                    return this.httpEngine;
                }
                int i = this.followUpCount + 1;
                this.followUpCount = i;
                if (i <= 20) {
                    this.url = followUpRequest.url();
                    this.requestHeaders = followUpRequest.headers.newBuilder();
                    HttpEngine httpEngine4 = this.httpEngine;
                    AbstractC07640v7 requestBody = httpEngine4.getRequestBody();
                    if (!followUpRequest.method.equals(this.method)) {
                        requestBody = null;
                    } else if (requestBody != null && !(requestBody instanceof RetryableSink)) {
                        throw new HttpRetryException("Cannot retry streamed HTTP body", this.responseCode);
                    }
                    StreamAllocation close = httpEngine4.close();
                    if (!this.httpEngine.sameConnection(followUpRequest.url)) {
                        close.release();
                        close = null;
                    }
                    this.httpEngine = newHttpEngine(followUpRequest.method, close, (RetryableSink) requestBody, response);
                } else {
                    throw new ProtocolException(AnonymousClass006.A01("Too many follow-up requests: ", i));
                }
            }
        }
    }

    private HttpEngine newHttpEngine(String str, StreamAllocation streamAllocation, RetryableSink retryableSink, Response response) throws MalformedURLException, UnknownHostException {
        RequestBody requestBody = HttpMethod.requiresRequestBody(str) ? EMPTY_REQUEST_BODY : null;
        HttpUrl httpUrlChecked = Internal.instance.getHttpUrlChecked(getURL().toString());
        Request.Builder builder = new Request.Builder();
        builder.url(httpUrlChecked);
        builder.method(str, requestBody);
        Headers headers = new Headers(this.requestHeaders);
        int length = headers.namesAndValues.length >> 1;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            builder.headers.add(headers.name(i), headers.value(i));
        }
        if (HttpMethod.permitsRequestBody(str)) {
            long j = this.fixedContentLength;
            if (j != -1) {
                builder.headers.set("Content-Length", Long.toString(j));
            } else if (this.chunkLength > 0) {
                builder.headers.set("Transfer-Encoding", "chunked");
            } else {
                z = true;
            }
            if (Headers.get(headers.namesAndValues, "Content-Type") == null) {
                builder.headers.set("Content-Type", HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
            }
        }
        if (Headers.get(headers.namesAndValues, "User-Agent") == null) {
            builder.headers.set("User-Agent", defaultUserAgent());
        }
        Request build = builder.build();
        OkHttpClient okHttpClient = this.client;
        if (Internal.instance.internalCache(okHttpClient) != null && !getUseCaches()) {
            okHttpClient = new OkHttpClient(this.client);
            okHttpClient.cache = null;
            okHttpClient.internalCache = null;
        }
        return new HttpEngine(okHttpClient, build, z, true, false, streamAllocation, retryableSink, response);
    }

    @Override // java.net.URLConnection
    public final void connect() throws IOException {
        initHttpEngine();
        do {
        } while (!execute(false));
    }

    public final String getHeaderFieldKey(int i) {
        try {
            return getHeaders().name(i);
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public final Map<String, List<String>> getHeaderFields() {
        try {
            return OkHeaders.toMultimap(getHeaders(), StatusLine.get(getResponse().getResponse()).toString());
        } catch (IOException unused) {
            return Collections.emptyMap();
        }
    }

    @Override // java.net.URLConnection
    public final OutputStream getOutputStream() throws IOException {
        String str;
        connect();
        HttpEngine httpEngine2 = this.httpEngine;
        AnonymousClass0Lx bufferedRequestBody = httpEngine2.getBufferedRequestBody();
        if (bufferedRequestBody == null) {
            str = AnonymousClass006.A05("method does not support a request body: ", this.method);
        } else if (!httpEngine2.hasResponse()) {
            return bufferedRequestBody.A7F();
        } else {
            str = "cannot write request body after response has been read";
        }
        throw new ProtocolException(str);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final Permission getPermission() throws IOException {
        int defaultPort;
        URL url = getURL();
        String host = url.getHost();
        if (url.getPort() != -1) {
            defaultPort = url.getPort();
        } else {
            defaultPort = HttpUrl.defaultPort(url.getProtocol());
        }
        if (usingProxy()) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) this.client.proxy.address();
            host = inetSocketAddress.getHostName();
            defaultPort = inetSocketAddress.getPort();
        }
        return new SocketPermission(AnonymousClass006.A06(host, ":", defaultPort), "connect, resolve");
    }

    @Override // java.net.HttpURLConnection
    public final int getResponseCode() throws IOException {
        return getResponse().getResponse().code;
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        return getResponse().getResponse().message;
    }

    public void setIfModifiedSince(long j) {
        super.setIfModifiedSince(j);
        long j2 = this.ifModifiedSince;
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        Headers.Builder builder = this.requestHeaders;
        if (i != 0) {
            builder.set("If-Modified-Since", HttpDate.format(new Date(j2)));
        } else {
            builder.removeAll("If-Modified-Since");
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final String getHeaderField(int i) {
        try {
            return getHeaders().value(i);
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public final String getHeaderField(String str) {
        if (str != null) {
            return Headers.get(getHeaders().namesAndValues, str);
        }
        try {
            return StatusLine.get(getResponse().getResponse()).toString();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        setFixedLengthStreamingMode((long) i);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j) {
        String str;
        if (((HttpURLConnection) this).connected) {
            str = "Already connected";
        } else if (this.chunkLength > 0) {
            str = "Already in chunked mode";
        } else if (j >= 0) {
            this.fixedContentLength = j;
            ((HttpURLConnection) this).fixedContentLength = (int) Math.min(j, 2147483647L);
            return;
        } else {
            throw new IllegalArgumentException("contentLength < 0");
        }
        throw new IllegalStateException(str);
    }
}
