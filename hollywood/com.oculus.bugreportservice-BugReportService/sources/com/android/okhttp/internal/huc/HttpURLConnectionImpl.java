package com.android.okhttp.internal.huc;

import com.android.okhttp.Handshake;
import com.android.okhttp.Headers;
import com.android.okhttp.HttpUrl;
import com.android.okhttp.MediaType;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Request;
import com.android.okhttp.RequestBody;
import com.android.okhttp.Response;
import com.android.okhttp.Route;
import com.android.okhttp.internal.Internal;
import com.android.okhttp.internal.URLFilter;
import com.android.okhttp.internal.Util;
import com.android.okhttp.internal.Version;
import com.android.okhttp.internal.http.HttpEngine;
import com.android.okhttp.internal.http.HttpMethod;
import com.android.okhttp.internal.http.OkHeaders;
import com.android.okhttp.internal.http.RetryableSink;
import com.android.okhttp.internal.http.StatusLine;
import com.android.okhttp.internal.http.StreamAllocation;
import com.android.okhttp.okio.Sink;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class HttpURLConnectionImpl extends HttpURLConnection {
    private static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create((MediaType) null, new byte[0]);
    private static final Set METHODS = new LinkedHashSet(Arrays.asList("OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH"));
    final OkHttpClient client;
    private long fixedContentLength;
    private int followUpCount;
    Handshake handshake;
    protected HttpEngine httpEngine;
    protected IOException httpEngineFailure;
    private Headers.Builder requestHeaders;
    private Headers responseHeaders;
    private Route route;
    private URLFilter urlFilter;

    public HttpURLConnectionImpl(URL url, OkHttpClient okHttpClient) {
        super(url);
        this.requestHeaders = new Headers.Builder();
        this.fixedContentLength = -1;
        this.client = okHttpClient;
    }

    public HttpURLConnectionImpl(URL url, OkHttpClient okHttpClient, URLFilter uRLFilter) {
        this(url, okHttpClient);
        this.urlFilter = uRLFilter;
    }

    private Headers getHeaders() {
        if (this.responseHeaders == null) {
            Response response = getResponse().getResponse();
            Headers.Builder newBuilder = response.headers().newBuilder();
            newBuilder.add(OkHeaders.SELECTED_PROTOCOL, response.protocol().toString());
            newBuilder.add(OkHeaders.RESPONSE_SOURCE, responseSourceHeader(response));
            this.responseHeaders = newBuilder.build();
        }
        return this.responseHeaders;
    }

    private static String responseSourceHeader(Response response) {
        if (response.networkResponse() == null) {
            if (response.cacheResponse() == null) {
                return "NONE";
            }
            return "CACHE " + response.code();
        } else if (response.cacheResponse() == null) {
            return "NETWORK " + response.code();
        } else {
            return "CONDITIONAL_CACHE " + response.networkResponse().code();
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

    @Override // java.net.URLConnection
    public final InputStream getInputStream() {
        if (this.doInput) {
            HttpEngine response = getResponse();
            if (getResponseCode() < 400) {
                return response.getResponse().body().byteStream();
            }
            throw new FileNotFoundException(this.url.toString());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final Permission getPermission() {
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

    private void initHttpEngine() {
        IOException iOException = this.httpEngineFailure;
        if (iOException != null) {
            throw iOException;
        } else if (this.httpEngine == null) {
            this.connected = true;
            try {
                if (this.doOutput) {
                    if (this.method.equals("GET")) {
                        this.method = "POST";
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

    private HttpEngine newHttpEngine(String str, StreamAllocation streamAllocation, RetryableSink retryableSink, Response response) {
        OkHttpClient okHttpClient;
        RequestBody requestBody = HttpMethod.requiresRequestBody(str) ? EMPTY_REQUEST_BODY : null;
        HttpUrl httpUrlChecked = Internal.instance.getHttpUrlChecked(getURL().toString());
        Request.Builder builder = new Request.Builder();
        builder.url(httpUrlChecked);
        builder.method(str, requestBody);
        Headers build = this.requestHeaders.build();
        int size = build.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            builder.addHeader(build.name(i), build.value(i));
        }
        if (HttpMethod.permitsRequestBody(str)) {
            long j = this.fixedContentLength;
            if (j != -1) {
                builder.header("Content-Length", Long.toString(j));
            } else if (this.chunkLength > 0) {
                builder.header("Transfer-Encoding", "chunked");
            } else {
                z = true;
            }
            if (build.get("Content-Type") == null) {
                builder.header("Content-Type", "application/x-www-form-urlencoded");
            }
        }
        if (build.get("User-Agent") == null) {
            builder.header("User-Agent", defaultUserAgent());
        }
        Request build2 = builder.build();
        OkHttpClient okHttpClient2 = this.client;
        if (Internal.instance.internalCache(okHttpClient2) == null || getUseCaches()) {
            okHttpClient = okHttpClient2;
        } else {
            OkHttpClient clone = this.client.clone();
            clone.setCache(null);
            okHttpClient = clone;
        }
        return new HttpEngine(okHttpClient, build2, z, true, false, streamAllocation, retryableSink, response);
    }

    private String defaultUserAgent() {
        String property = System.getProperty("http.agent");
        return property != null ? Util.toHumanReadableAscii(property) : Version.userAgent();
    }

    private HttpEngine getResponse() {
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

    /* JADX WARNING: Removed duplicated region for block: B:35:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean execute(boolean r5) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.internal.huc.HttpURLConnectionImpl.execute(boolean):boolean");
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

    public final int getResponseCode() {
        return getResponse().getResponse().code();
    }
}
