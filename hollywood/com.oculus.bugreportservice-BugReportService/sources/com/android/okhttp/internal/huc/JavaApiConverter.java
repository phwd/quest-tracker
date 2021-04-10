package com.android.okhttp.internal.huc;

import com.android.okhttp.Handshake;
import com.android.okhttp.Headers;
import com.android.okhttp.MediaType;
import com.android.okhttp.Request;
import com.android.okhttp.RequestBody;
import com.android.okhttp.Response;
import com.android.okhttp.ResponseBody;
import com.android.okhttp.internal.Internal;
import com.android.okhttp.internal.http.OkHeaders;
import com.android.okhttp.internal.http.StatusLine;
import com.android.okhttp.okio.BufferedSource;
import com.android.okhttp.okio.Okio;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SecureCacheResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class JavaApiConverter {
    private static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create((MediaType) null, new byte[0]);

    static /* synthetic */ RuntimeException access$100() {
        throwRequestModificationException();
        throw null;
    }

    static /* synthetic */ RuntimeException access$200() {
        throwResponseBodyAccessException();
        throw null;
    }

    private static Headers createHeaders(Map map) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry entry : map.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null)) {
                String trim = ((String) entry.getKey()).trim();
                for (String str : (List) entry.getValue()) {
                    Internal.instance.addLenient(builder, trim, str.trim());
                }
            }
        }
        return builder.build();
    }

    static Response createOkResponseForCacheGet(Request request, CacheResponse cacheResponse) {
        Headers headers;
        List list;
        Headers createHeaders = createHeaders(cacheResponse.getHeaders());
        if (OkHeaders.hasVaryAll(createHeaders)) {
            headers = new Headers.Builder().build();
        } else {
            headers = OkHeaders.varyHeaders(request.headers(), createHeaders);
        }
        Request.Builder builder = new Request.Builder();
        builder.url(request.httpUrl());
        builder.method(request.method(), null);
        builder.headers(headers);
        Request build = builder.build();
        Response.Builder builder2 = new Response.Builder();
        builder2.request(build);
        StatusLine parse = StatusLine.parse(extractStatusLine(cacheResponse));
        builder2.protocol(parse.protocol);
        builder2.code(parse.code);
        builder2.message(parse.message);
        Headers extractOkHeaders = extractOkHeaders(cacheResponse);
        builder2.headers(extractOkHeaders);
        builder2.body(createOkBody(extractOkHeaders, cacheResponse));
        if (cacheResponse instanceof SecureCacheResponse) {
            SecureCacheResponse secureCacheResponse = (SecureCacheResponse) cacheResponse;
            try {
                list = secureCacheResponse.getServerCertificateChain();
            } catch (SSLPeerUnverifiedException unused) {
                list = Collections.emptyList();
            }
            List localCertificateChain = secureCacheResponse.getLocalCertificateChain();
            if (localCertificateChain == null) {
                localCertificateChain = Collections.emptyList();
            }
            builder2.handshake(Handshake.get(secureCacheResponse.getCipherSuite(), list, localCertificateChain));
        }
        return builder2.build();
    }

    static HttpURLConnection createJavaUrlConnectionForCachePut(Response response) {
        if (response.request().isHttps()) {
            return new CacheHttpsURLConnection(new CacheHttpURLConnection(response));
        }
        return new CacheHttpURLConnection(response);
    }

    static Map extractJavaHeaders(Request request) {
        return OkHeaders.toMultimap(request.headers(), null);
    }

    private static Headers extractOkHeaders(CacheResponse cacheResponse) {
        return extractOkHeaders(cacheResponse.getHeaders());
    }

    static Headers extractOkHeaders(Map map) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                for (String str2 : (List) entry.getValue()) {
                    Internal.instance.addLenient(builder, str, str2);
                }
            }
        }
        return builder.build();
    }

    private static String extractStatusLine(CacheResponse cacheResponse) {
        return extractStatusLine(cacheResponse.getHeaders());
    }

    static String extractStatusLine(Map map) {
        List list = (List) map.get(null);
        if (list != null && list.size() != 0) {
            return (String) list.get(0);
        }
        throw new ProtocolException("CacheResponse is missing a 'null' header containing the status line. Headers=" + map);
    }

    private static ResponseBody createOkBody(final Headers headers, final CacheResponse cacheResponse) {
        return new ResponseBody() {
            /* class com.android.okhttp.internal.huc.JavaApiConverter.AnonymousClass4 */
            private BufferedSource body;

            @Override // com.android.okhttp.ResponseBody
            public long contentLength() {
                return OkHeaders.contentLength(Headers.this);
            }

            @Override // com.android.okhttp.ResponseBody
            public BufferedSource source() {
                if (this.body == null) {
                    this.body = Okio.buffer(Okio.source(cacheResponse.getBody()));
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
        public String getHeaderField(String str) {
            if (str == null) {
                return StatusLine.get(this.response).toString();
            }
            return this.response.headers().get(str);
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() {
            JavaApiConverter.access$200();
            throw null;
        }

        @Override // java.net.URLConnection
        public void setUseCaches(boolean z) {
            JavaApiConverter.access$100();
            throw null;
        }

        @Override // java.net.URLConnection
        public boolean getUseCaches() {
            return super.getUseCaches();
        }
    }

    /* access modifiers changed from: private */
    public static final class CacheHttpsURLConnection extends DelegatingHttpsURLConnection {
        private final CacheHttpURLConnection delegate;

        public CacheHttpsURLConnection(CacheHttpURLConnection cacheHttpURLConnection) {
            super(cacheHttpURLConnection);
            this.delegate = cacheHttpURLConnection;
        }
    }

    private static RuntimeException throwRequestModificationException() {
        throw new UnsupportedOperationException("ResponseCache cannot modify the request.");
    }

    private static RuntimeException throwResponseBodyAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access the response body.");
    }
}
