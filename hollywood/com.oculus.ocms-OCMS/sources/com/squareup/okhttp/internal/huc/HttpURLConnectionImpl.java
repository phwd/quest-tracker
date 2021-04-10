package com.squareup.okhttp.internal.huc;

import com.facebook.acra.util.HttpRequest;
import com.facebook.tigon.iface.TigonRequest;
import com.google.common.net.HttpHeaders;
import com.oculus.ocms.library.provider.contract.TrustedBinaryContract;
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
import com.squareup.okhttp.internal.Version;
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
import okio.BufferedSink;
import okio.Sink;

public class HttpURLConnectionImpl extends HttpURLConnection {
    private static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create((MediaType) null, new byte[0]);
    private static final Set<String> METHODS = new LinkedHashSet(Arrays.asList("OPTIONS", TigonRequest.GET, TigonRequest.HEAD, TigonRequest.POST, "PUT", "DELETE", "TRACE", "PATCH"));
    final OkHttpClient client;
    private long fixedContentLength = -1;
    private int followUpCount;
    Handshake handshake;
    protected HttpEngine httpEngine;
    protected IOException httpEngineFailure;
    private Headers.Builder requestHeaders = new Headers.Builder();
    private Headers responseHeaders;
    private Route route;

    public HttpURLConnectionImpl(URL url, OkHttpClient okHttpClient) {
        super(url);
        this.client = okHttpClient;
    }

    @Override // java.net.URLConnection
    public final void connect() throws IOException {
        initHttpEngine();
        do {
        } while (!execute(false));
    }

    public final void disconnect() {
        HttpEngine httpEngine2 = this.httpEngine;
        if (httpEngine2 != null) {
            httpEngine2.cancel();
        }
    }

    public final InputStream getErrorStream() {
        try {
            HttpEngine response = getResponse();
            if (!HttpEngine.hasBody(response.getResponse()) || response.getResponse().code() < 400) {
                return null;
            }
            return response.getResponse().body().byteStream();
        } catch (IOException unused) {
            return null;
        }
    }

    private Headers getHeaders() throws IOException {
        if (this.responseHeaders == null) {
            Response response = getResponse().getResponse();
            this.responseHeaders = response.headers().newBuilder().add(OkHeaders.SELECTED_PROTOCOL, response.protocol().toString()).add(OkHeaders.RESPONSE_SOURCE, responseSourceHeader(response)).build();
        }
        return this.responseHeaders;
    }

    private static String responseSourceHeader(Response response) {
        if (response.networkResponse() == null) {
            if (response.cacheResponse() == null) {
                return TrustedBinaryContract.ACTION_NONE;
            }
            return "CACHE " + response.code();
        } else if (response.cacheResponse() == null) {
            return "NETWORK " + response.code();
        } else {
            return "CONDITIONAL_CACHE " + response.networkResponse().code();
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
            return getHeaders().get(str);
        }
        try {
            return StatusLine.get(getResponse().getResponse()).toString();
        } catch (IOException unused) {
            return null;
        }
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
    public final Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return OkHeaders.toMultimap(this.requestHeaders.build(), null);
        }
        throw new IllegalStateException("Cannot access request header fields after connection is set");
    }

    @Override // java.net.URLConnection
    public final InputStream getInputStream() throws IOException {
        if (this.doInput) {
            HttpEngine response = getResponse();
            if (getResponseCode() < 400) {
                return response.getResponse().body().byteStream();
            }
            throw new FileNotFoundException(this.url.toString());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    @Override // java.net.URLConnection
    public final OutputStream getOutputStream() throws IOException {
        connect();
        BufferedSink bufferedRequestBody = this.httpEngine.getBufferedRequestBody();
        if (bufferedRequestBody == null) {
            throw new ProtocolException("method does not support a request body: " + this.method);
        } else if (!this.httpEngine.hasResponse()) {
            return bufferedRequestBody.outputStream();
        } else {
            throw new ProtocolException("cannot write request body after response has been read");
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final Permission getPermission() throws IOException {
        int i;
        URL url = getURL();
        String host = url.getHost();
        if (url.getPort() != -1) {
            i = url.getPort();
        } else {
            i = HttpUrl.defaultPort(url.getProtocol());
        }
        if (usingProxy()) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) this.client.getProxy().address();
            host = inetSocketAddress.getHostName();
            i = inetSocketAddress.getPort();
        }
        return new SocketPermission(host + ":" + i, "connect, resolve");
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
        this.client.setFollowRedirects(z);
    }

    public boolean getInstanceFollowRedirects() {
        return this.client.getFollowRedirects();
    }

    public int getConnectTimeout() {
        return this.client.getConnectTimeout();
    }

    public void setReadTimeout(int i) {
        this.client.setReadTimeout((long) i, TimeUnit.MILLISECONDS);
    }

    public int getReadTimeout() {
        return this.client.getReadTimeout();
    }

    private void initHttpEngine() throws IOException {
        IOException iOException = this.httpEngineFailure;
        if (iOException != null) {
            throw iOException;
        } else if (this.httpEngine == null) {
            this.connected = true;
            try {
                if (this.doOutput) {
                    if (this.method.equals(TigonRequest.GET)) {
                        this.method = TigonRequest.POST;
                    } else if (!HttpMethod.permitsRequestBody(this.method)) {
                        throw new ProtocolException(this.method + " does not support writing");
                    }
                }
                this.httpEngine = newHttpEngine(this.method, null, null, null);
            } catch (IOException e) {
                this.httpEngineFailure = e;
                throw e;
            }
        }
    }

    private HttpEngine newHttpEngine(String str, StreamAllocation streamAllocation, RetryableSink retryableSink, Response response) throws MalformedURLException, UnknownHostException {
        boolean z;
        Request.Builder method = new Request.Builder().url(Internal.instance.getHttpUrlChecked(getURL().toString())).method(str, HttpMethod.requiresRequestBody(str) ? EMPTY_REQUEST_BODY : null);
        Headers build = this.requestHeaders.build();
        int size = build.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            method.addHeader(build.name(i), build.value(i));
        }
        if (HttpMethod.permitsRequestBody(str)) {
            long j = this.fixedContentLength;
            if (j != -1) {
                method.header("Content-Length", Long.toString(j));
            } else if (this.chunkLength > 0) {
                method.header(HttpHeaders.TRANSFER_ENCODING, "chunked");
            } else {
                z2 = true;
            }
            if (build.get("Content-Type") == null) {
                method.header("Content-Type", HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
            }
            z = z2;
        } else {
            z = false;
        }
        if (build.get("User-Agent") == null) {
            method.header("User-Agent", defaultUserAgent());
        }
        Request build2 = method.build();
        OkHttpClient okHttpClient = this.client;
        if (Internal.instance.internalCache(okHttpClient) != null && !getUseCaches()) {
            okHttpClient = this.client.clone().setCache(null);
        }
        return new HttpEngine(okHttpClient, build2, z, true, false, streamAllocation, retryableSink, response);
    }

    private String defaultUserAgent() {
        String property = System.getProperty("http.agent");
        return property != null ? Util.toHumanReadableAscii(property) : Version.userAgent();
    }

    private HttpEngine getResponse() throws IOException {
        initHttpEngine();
        if (this.httpEngine.hasResponse()) {
            return this.httpEngine;
        }
        while (true) {
            if (execute(true)) {
                Response response = this.httpEngine.getResponse();
                Request followUpRequest = this.httpEngine.followUpRequest();
                if (followUpRequest == null) {
                    this.httpEngine.releaseStreamAllocation();
                    return this.httpEngine;
                }
                int i = this.followUpCount + 1;
                this.followUpCount = i;
                if (i <= 20) {
                    this.url = followUpRequest.url();
                    this.requestHeaders = followUpRequest.headers().newBuilder();
                    Sink requestBody = this.httpEngine.getRequestBody();
                    if (!followUpRequest.method().equals(this.method)) {
                        requestBody = null;
                    }
                    if (requestBody == null || (requestBody instanceof RetryableSink)) {
                        StreamAllocation close = this.httpEngine.close();
                        if (!this.httpEngine.sameConnection(followUpRequest.httpUrl())) {
                            close.release();
                            close = null;
                        }
                        this.httpEngine = newHttpEngine(followUpRequest.method(), close, (RetryableSink) requestBody, response);
                    } else {
                        throw new HttpRetryException("Cannot retry streamed HTTP body", this.responseCode);
                    }
                } else {
                    throw new ProtocolException("Too many follow-up requests: " + this.followUpCount);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean execute(boolean r5) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            com.squareup.okhttp.internal.http.HttpEngine r2 = r4.httpEngine     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            r2.sendRequest()     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            com.squareup.okhttp.internal.http.HttpEngine r2 = r4.httpEngine     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            com.squareup.okhttp.Connection r2 = r2.getConnection()     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            if (r2 == 0) goto L_0x001c
            com.squareup.okhttp.Route r3 = r2.getRoute()     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            r4.route = r3     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            com.squareup.okhttp.Handshake r2 = r2.getHandshake()     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            r4.handshake = r2     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            goto L_0x0021
        L_0x001c:
            r2 = 0
            r4.route = r2     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            r4.handshake = r2     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
        L_0x0021:
            if (r5 == 0) goto L_0x0028
            com.squareup.okhttp.internal.http.HttpEngine r5 = r4.httpEngine     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
            r5.readResponse()     // Catch:{ RequestException -> 0x0050, RouteException -> 0x003a, IOException -> 0x002b }
        L_0x0028:
            return r0
        L_0x0029:
            r5 = move-exception
            goto L_0x0058
        L_0x002b:
            r5 = move-exception
            com.squareup.okhttp.internal.http.HttpEngine r2 = r4.httpEngine     // Catch:{ all -> 0x0029 }
            com.squareup.okhttp.internal.http.HttpEngine r2 = r2.recover(r5)     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x0037
            r4.httpEngine = r2     // Catch:{ all -> 0x0046 }
            return r1
        L_0x0037:
            r4.httpEngineFailure = r5
            throw r5
        L_0x003a:
            r5 = move-exception
            com.squareup.okhttp.internal.http.HttpEngine r2 = r4.httpEngine
            com.squareup.okhttp.internal.http.HttpEngine r2 = r2.recover(r5)
            if (r2 == 0) goto L_0x0049
            r4.httpEngine = r2
            return r1
        L_0x0046:
            r5 = move-exception
            r0 = 0
            goto L_0x0058
        L_0x0049:
            java.io.IOException r5 = r5.getLastConnectException()
            r4.httpEngineFailure = r5
            throw r5
        L_0x0050:
            r5 = move-exception
            java.io.IOException r5 = r5.getCause()
            r4.httpEngineFailure = r5
            throw r5
        L_0x0058:
            if (r0 == 0) goto L_0x0063
            com.squareup.okhttp.internal.http.HttpEngine r0 = r4.httpEngine
            com.squareup.okhttp.internal.http.StreamAllocation r0 = r0.close()
            r0.release()
        L_0x0063:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.huc.HttpURLConnectionImpl.execute(boolean):boolean");
    }

    public final boolean usingProxy() {
        Proxy proxy;
        Route route2 = this.route;
        if (route2 != null) {
            proxy = route2.getProxy();
        } else {
            proxy = this.client.getProxy();
        }
        return (proxy == null || proxy.type() == Proxy.Type.DIRECT) ? false : true;
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        return getResponse().getResponse().message();
    }

    @Override // java.net.HttpURLConnection
    public final int getResponseCode() throws IOException {
        return getResponse().getResponse().code();
    }

    public final void setRequestProperty(String str, String str2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot set request property after connection is made");
        } else if (str == null) {
            throw new NullPointerException("field == null");
        } else if (str2 == null) {
            Platform platform = Platform.get();
            platform.logW("Ignoring header " + str + " because its value was null.");
        } else if ("X-Android-Transports".equals(str) || "X-Android-Protocols".equals(str)) {
            setProtocols(str2, false);
        } else {
            this.requestHeaders.set(str, str2);
        }
    }

    public void setIfModifiedSince(long j) {
        super.setIfModifiedSince(j);
        if (this.ifModifiedSince != 0) {
            this.requestHeaders.set(HttpHeaders.IF_MODIFIED_SINCE, HttpDate.format(new Date(this.ifModifiedSince)));
        } else {
            this.requestHeaders.removeAll(HttpHeaders.IF_MODIFIED_SINCE);
        }
    }

    public final void addRequestProperty(String str, String str2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot add request property after connection is made");
        } else if (str == null) {
            throw new NullPointerException("field == null");
        } else if (str2 == null) {
            Platform platform = Platform.get();
            platform.logW("Ignoring header " + str + " because its value was null.");
        } else if ("X-Android-Transports".equals(str) || "X-Android-Protocols".equals(str)) {
            setProtocols(str2, true);
        } else {
            this.requestHeaders.add(str, str2);
        }
    }

    private void setProtocols(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.addAll(this.client.getProtocols());
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

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        if (METHODS.contains(str)) {
            this.method = str;
            return;
        }
        throw new ProtocolException("Expected one of " + METHODS + " but was " + str);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        setFixedLengthStreamingMode((long) i);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j) {
        if (((HttpURLConnection) this).connected) {
            throw new IllegalStateException("Already connected");
        } else if (this.chunkLength > 0) {
            throw new IllegalStateException("Already in chunked mode");
        } else if (j >= 0) {
            this.fixedContentLength = j;
            ((HttpURLConnection) this).fixedContentLength = (int) Math.min(j, 2147483647L);
        } else {
            throw new IllegalArgumentException("contentLength < 0");
        }
    }
}
