package com.squareup.okhttp.internal.http;

import X.AnonymousClass06;
import X.C00148h;
import X.Dm;
import X.WF;
import X.WG;
import X.WM;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.ErrorCode;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.framed.FramedStream;
import com.squareup.okhttp.internal.framed.Header;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class Http2xStream implements HttpStream {
    public static final WM CONNECTION = WM.A04("connection");
    public static final WM ENCODING = WM.A04("encoding");
    public static final WM HOST = WM.A04("host");
    public static final List<WM> HTTP_2_SKIPPED_REQUEST_HEADERS;
    public static final List<WM> HTTP_2_SKIPPED_RESPONSE_HEADERS;
    public static final WM KEEP_ALIVE = WM.A04("keep-alive");
    public static final WM PROXY_CONNECTION = WM.A04("proxy-connection");
    public static final List<WM> SPDY_3_SKIPPED_REQUEST_HEADERS;
    public static final List<WM> SPDY_3_SKIPPED_RESPONSE_HEADERS;
    public static final WM TE = WM.A04("te");
    public static final WM TRANSFER_ENCODING = WM.A04("transfer-encoding");
    public static final WM UPGRADE = WM.A04("upgrade");
    public final FramedConnection framedConnection;
    public HttpEngine httpEngine;
    public FramedStream stream;
    public final StreamAllocation streamAllocation;

    public class StreamFinishingSource extends Dm {
        public StreamFinishingSource(WF wf) {
            super(wf);
        }

        @Override // X.Dm, java.io.Closeable, X.WF, java.lang.AutoCloseable
        public void close() throws IOException {
            Http2xStream http2xStream = Http2xStream.this;
            http2xStream.streamAllocation.streamFinished(http2xStream);
            super.close();
        }
    }

    public static String joinOnNull(String str, String str2) {
        return AnonymousClass06.A00(str, 0, str2);
    }

    static {
        WM wm = CONNECTION;
        WM wm2 = HOST;
        WM wm3 = KEEP_ALIVE;
        WM wm4 = PROXY_CONNECTION;
        WM wm5 = TRANSFER_ENCODING;
        WM wm6 = Header.TARGET_METHOD;
        WM wm7 = Header.TARGET_PATH;
        WM wm8 = Header.TARGET_SCHEME;
        WM wm9 = Header.TARGET_AUTHORITY;
        WM wm10 = Header.TARGET_HOST;
        WM wm11 = Header.VERSION;
        SPDY_3_SKIPPED_REQUEST_HEADERS = Util.immutableList(wm, wm2, wm3, wm4, wm5, wm6, wm7, wm8, wm9, wm10, wm11);
        SPDY_3_SKIPPED_RESPONSE_HEADERS = Util.immutableList(wm, wm2, wm3, wm4, wm5);
        WM wm12 = TE;
        WM wm13 = ENCODING;
        WM wm14 = UPGRADE;
        HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(wm, wm2, wm3, wm4, wm12, wm5, wm13, wm14, wm6, wm7, wm8, wm9, wm10, wm11);
        HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(wm, wm2, wm3, wm4, wm12, wm5, wm13, wm14);
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers;
        ArrayList arrayList = new ArrayList((headers.namesAndValues.length >> 1) + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url)));
        arrayList.add(new Header(Header.TARGET_AUTHORITY, Util.hostHeader(request.url)));
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url.scheme));
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            WM A04 = WM.A04(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(A04)) {
                arrayList.add(new Header(A04, headers.value(i)));
            }
        }
        return arrayList;
    }

    public static Response.Builder readHttp2HeadersList(List<Header> list) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int size = list.size();
        String str = null;
        for (int i = 0; i < size; i++) {
            WM wm = list.get(i).name;
            String A0A = list.get(i).value.A0A();
            if (wm.equals(Header.RESPONSE_STATUS)) {
                str = A0A;
            } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(wm)) {
                builder.add(wm.A0A(), A0A);
            }
        }
        if (str != null) {
            StatusLine parse = StatusLine.parse(AnonymousClass06.A03("HTTP/1.1 ", str));
            Response.Builder builder2 = new Response.Builder();
            builder2.protocol = Protocol.HTTP_2;
            builder2.code = parse.code;
            builder2.message = parse.message;
            builder2.headers = new Headers(builder).newBuilder();
            return builder2;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public static Response.Builder readSpdy3HeadersList(List<Header> list) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int size = list.size();
        String str = null;
        String str2 = "HTTP/1.1";
        for (int i = 0; i < size; i++) {
            WM wm = list.get(i).name;
            String A0A = list.get(i).value.A0A();
            int i2 = 0;
            while (true) {
                int length = A0A.length();
                if (i2 >= length) {
                    break;
                }
                int indexOf = A0A.indexOf(0, i2);
                if (indexOf == -1) {
                    indexOf = length;
                }
                String substring = A0A.substring(i2, indexOf);
                if (wm.equals(Header.RESPONSE_STATUS)) {
                    str = substring;
                } else if (wm.equals(Header.VERSION)) {
                    str2 = substring;
                } else if (!SPDY_3_SKIPPED_RESPONSE_HEADERS.contains(wm)) {
                    builder.add(wm.A0A(), substring);
                }
                i2 = indexOf + 1;
            }
        }
        if (str != null) {
            StatusLine parse = StatusLine.parse(AnonymousClass06.A04(str2, " ", str));
            Response.Builder builder2 = new Response.Builder();
            builder2.protocol = Protocol.SPDY_3;
            builder2.code = parse.code;
            builder2.message = parse.message;
            builder2.headers = new Headers(builder).newBuilder();
            return builder2;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public static List<Header> spdy3HeadersList(Request request) {
        Headers headers = request.headers;
        ArrayList arrayList = new ArrayList((headers.namesAndValues.length >> 1) + 5);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url)));
        arrayList.add(new Header(Header.VERSION, "HTTP/1.1"));
        arrayList.add(new Header(Header.TARGET_HOST, Util.hostHeader(request.url)));
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url.scheme));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            WM A04 = WM.A04(headers.name(i).toLowerCase(Locale.US));
            if (!SPDY_3_SKIPPED_REQUEST_HEADERS.contains(A04)) {
                String value = headers.value(i);
                if (linkedHashSet.add(A04)) {
                    arrayList.add(new Header(A04, value));
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        } else if (((Header) arrayList.get(i2)).name.equals(A04)) {
                            arrayList.set(i2, new Header(A04, AnonymousClass06.A00(((Header) arrayList.get(i2)).value.A0A(), 0, value)));
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void cancel() {
        FramedStream framedStream = this.stream;
        if (framedStream != null) {
            framedStream.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public WG createRequestBody(Request request, long j) throws IOException {
        return this.stream.getSink();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers, new C00148h(new StreamFinishingSource(this.stream.source)));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public Response.Builder readResponseHeaders() throws IOException {
        Protocol protocol = this.framedConnection.protocol;
        Protocol protocol2 = Protocol.HTTP_2;
        List<Header> responseHeaders = this.stream.getResponseHeaders();
        if (protocol == protocol2) {
            return readHttp2HeadersList(responseHeaders);
        }
        return readSpdy3HeadersList(responseHeaders);
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        retryableSink.writeToSocket(this.stream.getSink());
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestHeaders(Request request) throws IOException {
        List<Header> spdy3HeadersList;
        if (this.stream == null) {
            this.httpEngine.writingRequestHeaders();
            boolean permitsRequestBody = HttpMethod.permitsRequestBody(request.method);
            if (this.framedConnection.protocol == Protocol.HTTP_2) {
                spdy3HeadersList = http2HeadersList(request);
            } else {
                spdy3HeadersList = spdy3HeadersList(request);
            }
            FramedStream newStream = this.framedConnection.newStream(spdy3HeadersList, permitsRequestBody, true);
            this.stream = newStream;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            newStream.readTimeout.timeout((long) this.httpEngine.client.readTimeout, timeUnit);
            this.stream.writeTimeout.timeout((long) this.httpEngine.client.writeTimeout, timeUnit);
        }
    }

    public Http2xStream(StreamAllocation streamAllocation2, FramedConnection framedConnection2) {
        this.streamAllocation = streamAllocation2;
        this.framedConnection = framedConnection2;
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void setHttpEngine(HttpEngine httpEngine2) {
        this.httpEngine = httpEngine2;
    }
}
