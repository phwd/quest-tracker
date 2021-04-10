package com.android.okhttp.internal.huc;

import com.android.okhttp.Handshake;
import com.android.okhttp.Headers;
import com.android.okhttp.MediaType;
import com.android.okhttp.Request;
import com.android.okhttp.RequestBody;
import com.android.okhttp.Response;
import com.android.okhttp.ResponseBody;
import com.android.okhttp.internal.Internal;
import com.android.okhttp.internal.Util;
import com.android.okhttp.internal.http.HttpMethod;
import com.android.okhttp.internal.http.OkHeaders;
import com.android.okhttp.internal.http.StatusLine;
import com.android.okhttp.okio.BufferedSource;
import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Sink;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.net.URLConnection;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public final class JavaApiConverter {
    private static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create((MediaType) null, new byte[0]);

    private JavaApiConverter() {
    }

    public static Response createOkResponseForCachePut(URI uri, URLConnection urlConnection) throws IOException {
        Certificate[] peerCertificates;
        HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
        Response.Builder okResponseBuilder = new Response.Builder();
        Headers varyHeaders = varyHeaders(urlConnection, createHeaders(urlConnection.getHeaderFields()));
        RequestBody placeholderBody = null;
        if (varyHeaders == null) {
            return null;
        }
        String requestMethod = httpUrlConnection.getRequestMethod();
        if (HttpMethod.requiresRequestBody(requestMethod)) {
            placeholderBody = EMPTY_REQUEST_BODY;
        }
        okResponseBuilder.request(new Request.Builder().url(uri.toString()).method(requestMethod, placeholderBody).headers(varyHeaders).build());
        StatusLine statusLine = StatusLine.parse(extractStatusLine(httpUrlConnection));
        okResponseBuilder.protocol(statusLine.protocol);
        okResponseBuilder.code(statusLine.code);
        okResponseBuilder.message(statusLine.message);
        okResponseBuilder.networkResponse(okResponseBuilder.build());
        okResponseBuilder.headers(extractOkResponseHeaders(httpUrlConnection));
        okResponseBuilder.body(createOkBody(urlConnection));
        if (httpUrlConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) httpUrlConnection;
            try {
                peerCertificates = httpsUrlConnection.getServerCertificates();
            } catch (SSLPeerUnverifiedException e) {
                peerCertificates = null;
            }
            okResponseBuilder.handshake(Handshake.get(httpsUrlConnection.getCipherSuite(), nullSafeImmutableList(peerCertificates), nullSafeImmutableList(httpsUrlConnection.getLocalCertificates())));
        }
        return okResponseBuilder.build();
    }

    private static Headers createHeaders(Map<String, List<String>> headers) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            if (!(header.getKey() == null || header.getValue() == null)) {
                String name = header.getKey().trim();
                for (String value : header.getValue()) {
                    Internal.instance.addLenient(builder, name, value.trim());
                }
            }
        }
        return builder.build();
    }

    private static Headers varyHeaders(URLConnection urlConnection, Headers responseHeaders) {
        if (OkHeaders.hasVaryAll(responseHeaders)) {
            return null;
        }
        Set<String> varyFields = OkHeaders.varyFields(responseHeaders);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        if (!((urlConnection instanceof CacheHttpURLConnection) || (urlConnection instanceof CacheHttpsURLConnection))) {
            return null;
        }
        Map<String, List<String>> requestProperties = urlConnection.getRequestProperties();
        Headers.Builder result = new Headers.Builder();
        for (String fieldName : varyFields) {
            List<String> fieldValues = requestProperties.get(fieldName);
            if (fieldValues != null) {
                for (String fieldValue : fieldValues) {
                    Internal.instance.addLenient(result, fieldName, fieldValue);
                }
            } else if (fieldName.equals("Accept-Encoding")) {
                result.add("Accept-Encoding", "gzip");
            }
        }
        return result.build();
    }

    static Response createOkResponseForCacheGet(Request request, CacheResponse javaResponse) throws IOException {
        Headers varyHeaders;
        List<Certificate> peerCertificates;
        Headers responseHeaders = createHeaders(javaResponse.getHeaders());
        if (OkHeaders.hasVaryAll(responseHeaders)) {
            varyHeaders = new Headers.Builder().build();
        } else {
            varyHeaders = OkHeaders.varyHeaders(request.headers(), responseHeaders);
        }
        Request cacheRequest = new Request.Builder().url(request.httpUrl()).method(request.method(), null).headers(varyHeaders).build();
        Response.Builder okResponseBuilder = new Response.Builder();
        okResponseBuilder.request(cacheRequest);
        StatusLine statusLine = StatusLine.parse(extractStatusLine(javaResponse));
        okResponseBuilder.protocol(statusLine.protocol);
        okResponseBuilder.code(statusLine.code);
        okResponseBuilder.message(statusLine.message);
        Headers okHeaders = extractOkHeaders(javaResponse);
        okResponseBuilder.headers(okHeaders);
        okResponseBuilder.body(createOkBody(okHeaders, javaResponse));
        if (javaResponse instanceof SecureCacheResponse) {
            SecureCacheResponse javaSecureCacheResponse = (SecureCacheResponse) javaResponse;
            try {
                peerCertificates = javaSecureCacheResponse.getServerCertificateChain();
            } catch (SSLPeerUnverifiedException e) {
                peerCertificates = Collections.emptyList();
            }
            List<Certificate> localCertificates = javaSecureCacheResponse.getLocalCertificateChain();
            if (localCertificates == null) {
                localCertificates = Collections.emptyList();
            }
            okResponseBuilder.handshake(Handshake.get(javaSecureCacheResponse.getCipherSuite(), peerCertificates, localCertificates));
        }
        return okResponseBuilder.build();
    }

    public static Request createOkRequest(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) {
        RequestBody placeholderBody;
        if (HttpMethod.requiresRequestBody(requestMethod)) {
            placeholderBody = EMPTY_REQUEST_BODY;
        } else {
            placeholderBody = null;
        }
        Request.Builder builder = new Request.Builder().url(uri.toString()).method(requestMethod, placeholderBody);
        if (requestHeaders != null) {
            builder.headers(extractOkHeaders(requestHeaders));
        }
        return builder.build();
    }

    public static CacheResponse createJavaCacheResponse(final Response response) {
        final Headers headers = response.headers();
        final ResponseBody body = response.body();
        if (!response.request().isHttps()) {
            return new CacheResponse() {
                /* class com.android.okhttp.internal.huc.JavaApiConverter.AnonymousClass2 */

                @Override // java.net.CacheResponse
                public Map<String, List<String>> getHeaders() throws IOException {
                    return OkHeaders.toMultimap(Headers.this, StatusLine.get(response).toString());
                }

                @Override // java.net.CacheResponse
                public InputStream getBody() throws IOException {
                    ResponseBody responseBody = body;
                    if (responseBody == null) {
                        return null;
                    }
                    return responseBody.byteStream();
                }
            };
        }
        final Handshake handshake = response.handshake();
        return new SecureCacheResponse() {
            /* class com.android.okhttp.internal.huc.JavaApiConverter.AnonymousClass1 */

            @Override // java.net.SecureCacheResponse
            public String getCipherSuite() {
                Handshake handshake = Handshake.this;
                if (handshake != null) {
                    return handshake.cipherSuite();
                }
                return null;
            }

            @Override // java.net.SecureCacheResponse
            public List<Certificate> getLocalCertificateChain() {
                Handshake handshake = Handshake.this;
                if (handshake == null) {
                    return null;
                }
                List<Certificate> certificates = handshake.localCertificates();
                if (certificates.size() > 0) {
                    return certificates;
                }
                return null;
            }

            @Override // java.net.SecureCacheResponse
            public List<Certificate> getServerCertificateChain() throws SSLPeerUnverifiedException {
                Handshake handshake = Handshake.this;
                if (handshake == null) {
                    return null;
                }
                List<Certificate> certificates = handshake.peerCertificates();
                if (certificates.size() > 0) {
                    return certificates;
                }
                return null;
            }

            @Override // java.net.SecureCacheResponse
            public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
                Handshake handshake = Handshake.this;
                if (handshake == null) {
                    return null;
                }
                return handshake.peerPrincipal();
            }

            @Override // java.net.SecureCacheResponse
            public Principal getLocalPrincipal() {
                Handshake handshake = Handshake.this;
                if (handshake == null) {
                    return null;
                }
                return handshake.localPrincipal();
            }

            @Override // java.net.CacheResponse
            public Map<String, List<String>> getHeaders() throws IOException {
                return OkHeaders.toMultimap(headers, StatusLine.get(response).toString());
            }

            @Override // java.net.CacheResponse
            public InputStream getBody() throws IOException {
                ResponseBody responseBody = body;
                if (responseBody == null) {
                    return null;
                }
                return responseBody.byteStream();
            }
        };
    }

    public static CacheRequest createJavaCacheRequest(final com.android.okhttp.internal.http.CacheRequest okCacheRequest) {
        return new CacheRequest() {
            /* class com.android.okhttp.internal.huc.JavaApiConverter.AnonymousClass3 */

            @Override // java.net.CacheRequest
            public void abort() {
                com.android.okhttp.internal.http.CacheRequest.this.abort();
            }

            @Override // java.net.CacheRequest
            public OutputStream getBody() throws IOException {
                Sink body = com.android.okhttp.internal.http.CacheRequest.this.body();
                if (body == null) {
                    return null;
                }
                return Okio.buffer(body).outputStream();
            }
        };
    }

    static HttpURLConnection createJavaUrlConnectionForCachePut(Response okResponse) {
        if (okResponse.request().isHttps()) {
            return new CacheHttpsURLConnection(new CacheHttpURLConnection(okResponse));
        }
        return new CacheHttpURLConnection(okResponse);
    }

    static Map<String, List<String>> extractJavaHeaders(Request request) {
        return OkHeaders.toMultimap(request.headers(), null);
    }

    private static Headers extractOkHeaders(CacheResponse javaResponse) throws IOException {
        return extractOkHeaders(javaResponse.getHeaders());
    }

    private static Headers extractOkResponseHeaders(HttpURLConnection httpUrlConnection) {
        return extractOkHeaders(httpUrlConnection.getHeaderFields());
    }

    static Headers extractOkHeaders(Map<String, List<String>> javaHeaders) {
        Headers.Builder okHeadersBuilder = new Headers.Builder();
        for (Map.Entry<String, List<String>> javaHeader : javaHeaders.entrySet()) {
            String name = javaHeader.getKey();
            if (name != null) {
                for (String value : javaHeader.getValue()) {
                    Internal.instance.addLenient(okHeadersBuilder, name, value);
                }
            }
        }
        return okHeadersBuilder.build();
    }

    private static String extractStatusLine(HttpURLConnection httpUrlConnection) {
        return httpUrlConnection.getHeaderField((String) null);
    }

    private static String extractStatusLine(CacheResponse javaResponse) throws IOException {
        return extractStatusLine(javaResponse.getHeaders());
    }

    static String extractStatusLine(Map<String, List<String>> javaResponseHeaders) throws ProtocolException {
        List<String> values = javaResponseHeaders.get(null);
        if (values != null && values.size() != 0) {
            return values.get(0);
        }
        throw new ProtocolException("CacheResponse is missing a 'null' header containing the status line. Headers=" + ((Object) javaResponseHeaders));
    }

    private static ResponseBody createOkBody(final Headers okHeaders, final CacheResponse cacheResponse) {
        return new ResponseBody() {
            /* class com.android.okhttp.internal.huc.JavaApiConverter.AnonymousClass4 */
            private BufferedSource body;

            @Override // com.android.okhttp.ResponseBody
            public MediaType contentType() {
                String contentTypeHeader = Headers.this.get("Content-Type");
                if (contentTypeHeader == null) {
                    return null;
                }
                return MediaType.parse(contentTypeHeader);
            }

            @Override // com.android.okhttp.ResponseBody
            public long contentLength() {
                return OkHeaders.contentLength(Headers.this);
            }

            @Override // com.android.okhttp.ResponseBody
            public BufferedSource source() throws IOException {
                if (this.body == null) {
                    this.body = Okio.buffer(Okio.source(cacheResponse.getBody()));
                }
                return this.body;
            }
        };
    }

    private static ResponseBody createOkBody(final URLConnection urlConnection) {
        if (!urlConnection.getDoInput()) {
            return null;
        }
        return new ResponseBody() {
            /* class com.android.okhttp.internal.huc.JavaApiConverter.AnonymousClass5 */
            private BufferedSource body;

            @Override // com.android.okhttp.ResponseBody
            public MediaType contentType() {
                String contentTypeHeader = URLConnection.this.getContentType();
                if (contentTypeHeader == null) {
                    return null;
                }
                return MediaType.parse(contentTypeHeader);
            }

            @Override // com.android.okhttp.ResponseBody
            public long contentLength() {
                return JavaApiConverter.stringToLong(URLConnection.this.getHeaderField("Content-Length"));
            }

            @Override // com.android.okhttp.ResponseBody
            public BufferedSource source() throws IOException {
                if (this.body == null) {
                    this.body = Okio.buffer(Okio.source(URLConnection.this.getInputStream()));
                }
                return this.body;
            }
        };
    }

    /* access modifiers changed from: private */
    public static final class CacheHttpURLConnection extends HttpURLConnection {
        private final Request request;
        private final Response response;

        public CacheHttpURLConnection(Response response2) {
            super(response2.request().url());
            this.request = response2.request();
            this.response = response2;
            this.connected = true;
            this.doOutput = this.request.body() != null;
            this.doInput = true;
            this.useCaches = true;
            this.method = this.request.method();
        }

        @Override // java.net.URLConnection
        public void connect() throws IOException {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void disconnect() {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setRequestProperty(String key, String value) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void addRequestProperty(String key, String value) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public String getRequestProperty(String key) {
            return this.request.header(key);
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getRequestProperties() {
            return OkHeaders.toMultimap(this.request.headers(), null);
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(int contentLength) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(long contentLength) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void setChunkedStreamingMode(int chunklen) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void setInstanceFollowRedirects(boolean followRedirects) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public boolean getInstanceFollowRedirects() {
            return super.getInstanceFollowRedirects();
        }

        @Override // java.net.HttpURLConnection
        public void setRequestMethod(String method) throws ProtocolException {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public String getRequestMethod() {
            return this.request.method();
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderFieldKey(int position) {
            if (position < 0) {
                throw new IllegalArgumentException("Invalid header index: " + position);
            } else if (position == 0) {
                return null;
            } else {
                return this.response.headers().name(position - 1);
            }
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderField(int position) {
            if (position < 0) {
                throw new IllegalArgumentException("Invalid header index: " + position);
            } else if (position == 0) {
                return StatusLine.get(this.response).toString();
            } else {
                return this.response.headers().value(position - 1);
            }
        }

        @Override // java.net.URLConnection
        public String getHeaderField(String fieldName) {
            if (fieldName == null) {
                return StatusLine.get(this.response).toString();
            }
            return this.response.headers().get(fieldName);
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getHeaderFields() {
            return OkHeaders.toMultimap(this.response.headers(), StatusLine.get(this.response).toString());
        }

        @Override // java.net.HttpURLConnection
        public int getResponseCode() throws IOException {
            return this.response.code();
        }

        @Override // java.net.HttpURLConnection
        public String getResponseMessage() throws IOException {
            return this.response.message();
        }

        @Override // java.net.HttpURLConnection
        public InputStream getErrorStream() {
            return null;
        }

        @Override // java.net.HttpURLConnection
        public boolean usingProxy() {
            return false;
        }

        @Override // java.net.URLConnection
        public void setConnectTimeout(int timeout) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public int getConnectTimeout() {
            return 0;
        }

        @Override // java.net.URLConnection
        public void setReadTimeout(int timeout) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public int getReadTimeout() {
            return 0;
        }

        @Override // java.net.URLConnection
        public Object getContent() throws IOException {
            throw JavaApiConverter.throwResponseBodyAccessException();
        }

        @Override // java.net.URLConnection
        public Object getContent(Class[] classes) throws IOException {
            throw JavaApiConverter.throwResponseBodyAccessException();
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() throws IOException {
            throw JavaApiConverter.throwResponseBodyAccessException();
        }

        @Override // java.net.URLConnection
        public OutputStream getOutputStream() throws IOException {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setDoInput(boolean doInput) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getDoInput() {
            return this.doInput;
        }

        @Override // java.net.URLConnection
        public void setDoOutput(boolean doOutput) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getDoOutput() {
            return this.doOutput;
        }

        @Override // java.net.URLConnection
        public void setAllowUserInteraction(boolean allowUserInteraction) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getAllowUserInteraction() {
            return false;
        }

        @Override // java.net.URLConnection
        public void setUseCaches(boolean useCaches) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getUseCaches() {
            return super.getUseCaches();
        }

        @Override // java.net.URLConnection
        public void setIfModifiedSince(long ifModifiedSince) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public long getIfModifiedSince() {
            return JavaApiConverter.stringToLong(this.request.headers().get("If-Modified-Since"));
        }

        @Override // java.net.URLConnection
        public boolean getDefaultUseCaches() {
            return super.getDefaultUseCaches();
        }

        @Override // java.net.URLConnection
        public void setDefaultUseCaches(boolean defaultUseCaches) {
            super.setDefaultUseCaches(defaultUseCaches);
        }
    }

    /* access modifiers changed from: private */
    public static final class CacheHttpsURLConnection extends DelegatingHttpsURLConnection {
        private final CacheHttpURLConnection delegate;

        public CacheHttpsURLConnection(CacheHttpURLConnection delegate2) {
            super(delegate2);
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
        public Handshake handshake() {
            return this.delegate.response.handshake();
        }

        @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
        public HostnameVerifier getHostnameVerifier() {
            throw JavaApiConverter.throwRequestSslAccessException();
        }

        @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
        public void setSSLSocketFactory(SSLSocketFactory socketFactory) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
        public SSLSocketFactory getSSLSocketFactory() {
            throw JavaApiConverter.throwRequestSslAccessException();
        }

        @Override // java.net.URLConnection
        public long getContentLengthLong() {
            return this.delegate.getContentLengthLong();
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(long contentLength) {
            this.delegate.setFixedLengthStreamingMode(contentLength);
        }

        @Override // java.net.URLConnection
        public long getHeaderFieldLong(String field, long defaultValue) {
            return this.delegate.getHeaderFieldLong(field, defaultValue);
        }
    }

    /* access modifiers changed from: private */
    public static RuntimeException throwRequestModificationException() {
        throw new UnsupportedOperationException("ResponseCache cannot modify the request.");
    }

    private static RuntimeException throwRequestHeaderAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access request headers");
    }

    /* access modifiers changed from: private */
    public static RuntimeException throwRequestSslAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access SSL internals");
    }

    /* access modifiers changed from: private */
    public static RuntimeException throwResponseBodyAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access the response body.");
    }

    private static <T> List<T> nullSafeImmutableList(T[] elements) {
        return elements == null ? Collections.emptyList() : Util.immutableList(elements);
    }

    /* access modifiers changed from: private */
    public static long stringToLong(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
