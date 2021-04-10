package com.squareup.okhttp.internal.http;

import X.AnonymousClass006;
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
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.apache.commons.cli.HelpFormatter;

public final class Http2xStream implements HttpStream {
    public static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
    public static final ByteString ENCODING = ByteString.encodeUtf8("encoding");
    public static final ByteString HOST = ByteString.encodeUtf8("host");
    public static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS;
    public static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS;
    public static final ByteString KEEP_ALIVE = ByteString.encodeUtf8("keep-alive");
    public static final ByteString PROXY_CONNECTION = ByteString.encodeUtf8("proxy-connection");
    public static final List<ByteString> SPDY_3_SKIPPED_REQUEST_HEADERS;
    public static final List<ByteString> SPDY_3_SKIPPED_RESPONSE_HEADERS;
    public static final ByteString TE = ByteString.encodeUtf8("te");
    public static final ByteString TRANSFER_ENCODING = ByteString.encodeUtf8("transfer-encoding");
    public static final ByteString UPGRADE = ByteString.encodeUtf8("upgrade");
    public final FramedConnection framedConnection;
    public HttpEngine httpEngine;
    public FramedStream stream;
    public final StreamAllocation streamAllocation;

    public class StreamFinishingSource extends ForwardingSource {
        public StreamFinishingSource(Source source) {
            super(source);
        }

        @Override // okio.Source, okio.ForwardingSource, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Http2xStream http2xStream = Http2xStream.this;
            http2xStream.streamAllocation.streamFinished(http2xStream);
            super.close();
        }
    }

    public static String joinOnNull(String str, String str2) {
        return AnonymousClass006.A02(str, 0, str2);
    }

    static {
        ByteString byteString = CONNECTION;
        ByteString byteString2 = HOST;
        ByteString byteString3 = KEEP_ALIVE;
        ByteString byteString4 = PROXY_CONNECTION;
        ByteString byteString5 = TRANSFER_ENCODING;
        ByteString byteString6 = Header.TARGET_METHOD;
        ByteString byteString7 = Header.TARGET_PATH;
        ByteString byteString8 = Header.TARGET_SCHEME;
        ByteString byteString9 = Header.TARGET_AUTHORITY;
        ByteString byteString10 = Header.TARGET_HOST;
        ByteString byteString11 = Header.VERSION;
        SPDY_3_SKIPPED_REQUEST_HEADERS = Util.immutableList(byteString, byteString2, byteString3, byteString4, byteString5, byteString6, byteString7, byteString8, byteString9, byteString10, byteString11);
        SPDY_3_SKIPPED_RESPONSE_HEADERS = Util.immutableList(byteString, byteString2, byteString3, byteString4, byteString5);
        ByteString byteString12 = TE;
        ByteString byteString13 = ENCODING;
        ByteString byteString14 = UPGRADE;
        HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(byteString, byteString2, byteString3, byteString4, byteString12, byteString5, byteString13, byteString14, byteString6, byteString7, byteString8, byteString9, byteString10, byteString11);
        HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(byteString, byteString2, byteString3, byteString4, byteString12, byteString5, byteString13, byteString14);
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
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                arrayList.add(new Header(encodeUtf8, headers.value(i)));
            }
        }
        return arrayList;
    }

    public static Response.Builder readHttp2HeadersList(List<Header> list) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int size = list.size();
        String str = null;
        for (int i = 0; i < size; i++) {
            ByteString byteString = list.get(i).name;
            String utf8 = list.get(i).value.utf8();
            if (byteString.equals(Header.RESPONSE_STATUS)) {
                str = utf8;
            } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(byteString)) {
                builder.add(byteString.utf8(), utf8);
            }
        }
        if (str != null) {
            StatusLine parse = StatusLine.parse(AnonymousClass006.A07("HTTP/1.1 ", str));
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
            ByteString byteString = list.get(i).name;
            String utf8 = list.get(i).value.utf8();
            int i2 = 0;
            while (true) {
                int length = utf8.length();
                if (i2 >= length) {
                    break;
                }
                int indexOf = utf8.indexOf(0, i2);
                if (indexOf == -1) {
                    indexOf = length;
                }
                String substring = utf8.substring(i2, indexOf);
                if (byteString.equals(Header.RESPONSE_STATUS)) {
                    str = substring;
                } else if (byteString.equals(Header.VERSION)) {
                    str2 = substring;
                } else if (!SPDY_3_SKIPPED_RESPONSE_HEADERS.contains(byteString)) {
                    builder.add(byteString.utf8(), substring);
                }
                i2 = indexOf + 1;
            }
        }
        if (str != null) {
            StatusLine parse = StatusLine.parse(AnonymousClass006.A09(str2, HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, str));
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
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!SPDY_3_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                String value = headers.value(i);
                if (linkedHashSet.add(encodeUtf8)) {
                    arrayList.add(new Header(encodeUtf8, value));
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        } else if (((Header) arrayList.get(i2)).name.equals(encodeUtf8)) {
                            arrayList.set(i2, new Header(encodeUtf8, AnonymousClass006.A02(((Header) arrayList.get(i2)).value.utf8(), 0, value)));
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
    public Sink createRequestBody(Request request, long j) throws IOException {
        return this.stream.getSink();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers, Okio.buffer(new StreamFinishingSource(this.stream.source)));
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
