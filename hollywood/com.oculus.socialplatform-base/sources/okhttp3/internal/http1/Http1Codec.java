package okhttp3.internal.http1;

import X.AnonymousClass006;
import com.facebook.acra.util.HttpRequestMultipart;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1Codec implements HttpCodec {
    public static final int STATE_CLOSED = 6;
    public static final int STATE_IDLE = 0;
    public static final int STATE_OPEN_REQUEST_BODY = 1;
    public static final int STATE_OPEN_RESPONSE_BODY = 4;
    public static final int STATE_READING_RESPONSE_BODY = 5;
    public static final int STATE_READ_RESPONSE_HEADERS = 3;
    public static final int STATE_WRITING_REQUEST_BODY = 2;
    public final OkHttpClient client;
    public final BufferedSink sink;
    public final BufferedSource source;
    public int state = 0;
    public final StreamAllocation streamAllocation;

    public abstract class AbstractSource implements Source {
        public boolean closed;
        public final ForwardingTimeout timeout;

        public final void endOfInput(boolean z) throws IOException {
            Http1Codec http1Codec = Http1Codec.this;
            int i = http1Codec.state;
            if (i == 6) {
                return;
            }
            if (i == 5) {
                http1Codec.detachTimeout(this.timeout);
                http1Codec.state = 6;
                StreamAllocation streamAllocation = http1Codec.streamAllocation;
                if (streamAllocation != null) {
                    streamAllocation.streamFinished(!z, http1Codec);
                    return;
                }
                return;
            }
            throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }

        public AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.source.timeout());
        }
    }

    public final class ChunkedSink implements Sink {
        public boolean closed;
        public final ForwardingTimeout timeout;

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                Http1Codec.this.sink.writeUtf8("0\r\n\r\n");
                Http1Codec http1Codec = Http1Codec.this;
                http1Codec.detachTimeout(this.timeout);
                http1Codec.state = 3;
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1Codec.this.sink.writeHexadecimalUnsignedLong(j);
                Http1Codec.this.sink.writeUtf8(HttpRequestMultipart.LINE_FEED);
                Http1Codec.this.sink.write(buffer, j);
                Http1Codec.this.sink.writeUtf8(HttpRequestMultipart.LINE_FEED);
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public class ChunkedSource extends AbstractSource {
        public static final long NO_CHUNK_YET = -1;
        public long bytesRemainingInChunk = -1;
        public boolean hasMoreChunks = true;
        public final HttpUrl url;

        public ChunkedSource(HttpUrl httpUrl) {
            super();
            this.url = httpUrl;
        }

        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1) {
                Http1Codec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.readHexadecimalUnsignedLong();
                String trim = Http1Codec.this.source.readUtf8LineStrict().trim();
                long j = this.bytesRemainingInChunk;
                if (j < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("expected chunk size and optional extensions but was \"");
                    sb.append(j);
                    sb.append(trim);
                    sb.append("\"");
                    throw new ProtocolException(sb.toString());
                } else if (j == 0) {
                    this.hasMoreChunks = false;
                    Http1Codec http1Codec = Http1Codec.this;
                    HttpHeaders.receiveHeaders(http1Codec.client.cookieJar, this.url, http1Codec.readHeaders());
                    endOfInput(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
            if (okhttp3.internal.Util.skipAll(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0012;
         */
        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r2 = this;
                boolean r0 = r2.closed
                if (r0 != 0) goto L_0x0019
                boolean r0 = r2.hasMoreChunks
                if (r0 == 0) goto L_0x0016
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
                r0 = 100
                boolean r0 = okhttp3.internal.Util.skipAll(r2, r0, r1)     // Catch:{ IOException -> 0x0012 }
                if (r0 != 0) goto L_0x0016
            L_0x0012:
                r0 = 0
                r2.endOfInput(r0)
            L_0x0016:
                r0 = 1
                r2.closed = r0
            L_0x0019:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http1.Http1Codec.ChunkedSource.close():void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
            if (r8.hasMoreChunks == false) goto L_0x0021;
         */
        @Override // okio.Source
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r9, long r10) throws java.io.IOException {
            /*
                r8 = this;
                r6 = 0
                int r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r0 < 0) goto L_0x004e
                boolean r0 = r8.closed
                if (r0 != 0) goto L_0x0046
                boolean r0 = r8.hasMoreChunks
                r4 = -1
                if (r0 == 0) goto L_0x0021
                long r1 = r8.bytesRemainingInChunk
                int r0 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r0 == 0) goto L_0x001a
                int r0 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r0 != 0) goto L_0x0022
            L_0x001a:
                r8.readChunkSize()
                boolean r0 = r8.hasMoreChunks
                if (r0 != 0) goto L_0x0022
            L_0x0021:
                return r4
            L_0x0022:
                okhttp3.internal.http1.Http1Codec r0 = okhttp3.internal.http1.Http1Codec.this
                okio.BufferedSource r2 = r0.source
                long r0 = r8.bytesRemainingInChunk
                long r0 = java.lang.Math.min(r10, r0)
                long r2 = r2.read(r9, r0)
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 == 0) goto L_0x003a
                long r0 = r8.bytesRemainingInChunk
                long r0 = r0 - r2
                r8.bytesRemainingInChunk = r0
                return r2
            L_0x003a:
                r0 = 0
                r8.endOfInput(r0)
                java.lang.String r1 = "unexpected end of stream"
                java.net.ProtocolException r0 = new java.net.ProtocolException
                r0.<init>(r1)
                throw r0
            L_0x0046:
                java.lang.String r1 = "closed"
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r0.<init>(r1)
                throw r0
            L_0x004e:
                java.lang.String r0 = "byteCount < 0: "
                java.lang.String r1 = X.AnonymousClass006.A06(r0, r10)
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http1.Http1Codec.ChunkedSource.read(okio.Buffer, long):long");
        }
    }

    public final class FixedLengthSink implements Sink {
        public long bytesRemaining;
        public boolean closed;
        public final ForwardingTimeout timeout;

        public FixedLengthSink(long j) {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
            this.bytesRemaining = j;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining <= 0) {
                    Http1Codec http1Codec = Http1Codec.this;
                    http1Codec.detachTimeout(this.timeout);
                    http1Codec.state = 3;
                    return;
                }
                throw new ProtocolException("unexpected end of stream");
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (!this.closed) {
                Util.checkOffsetAndCount(buffer.size, 0, j);
                long j2 = this.bytesRemaining;
                if (j <= j2) {
                    Http1Codec.this.sink.write(buffer, j);
                    this.bytesRemaining -= j;
                    return;
                }
                StringBuilder sb = new StringBuilder("expected ");
                sb.append(j2);
                sb.append(" bytes but received ");
                sb.append(j);
                throw new ProtocolException(sb.toString());
            }
            throw new IllegalStateException("closed");
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public class FixedLengthSource extends AbstractSource {
        public long bytesRemaining;

        public FixedLengthSource(long j) throws IOException {
            super();
            this.bytesRemaining = j;
            if (j == 0) {
                endOfInput(true);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
            if (okhttp3.internal.Util.skipAll(r5, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0016;
         */
        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r5 = this;
                boolean r0 = r5.closed
                if (r0 != 0) goto L_0x001d
                long r3 = r5.bytesRemaining
                r1 = 0
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 == 0) goto L_0x001a
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
                r0 = 100
                boolean r0 = okhttp3.internal.Util.skipAll(r5, r0, r1)     // Catch:{ IOException -> 0x0016 }
                if (r0 != 0) goto L_0x001a
            L_0x0016:
                r0 = 0
                r5.endOfInput(r0)
            L_0x001a:
                r0 = 1
                r5.closed = r0
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http1.Http1Codec.FixedLengthSource.close():void");
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
            } else if (!this.closed) {
                long j2 = this.bytesRemaining;
                if (j2 == 0) {
                    return -1;
                }
                long read = Http1Codec.this.source.read(buffer, Math.min(j2, j));
                if (read != -1) {
                    long j3 = this.bytesRemaining - read;
                    this.bytesRemaining = j3;
                    if (j3 == 0) {
                        endOfInput(true);
                    }
                    return read;
                }
                endOfInput(false);
                throw new ProtocolException("unexpected end of stream");
            } else {
                throw new IllegalStateException("closed");
            }
        }
    }

    public class UnknownLengthSource extends AbstractSource {
        public boolean inputExhausted;

        public UnknownLengthSource() {
            super();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    endOfInput(false);
                }
                this.closed = true;
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
            } else if (!this.closed) {
                if (!this.inputExhausted) {
                    long read = Http1Codec.this.source.read(buffer, j);
                    if (read != -1) {
                        return read;
                    }
                    this.inputExhausted = true;
                    endOfInput(true);
                }
                return -1;
            } else {
                throw new IllegalStateException("closed");
            }
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Sink createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void detachTimeout(ForwardingTimeout forwardingTimeout) {
        Timeout timeout = forwardingTimeout.delegate;
        forwardingTimeout.setDelegate(Timeout.NONE);
        timeout.clearDeadline();
        timeout.clearTimeout();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() throws IOException {
        this.sink.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() throws IOException {
        this.sink.flush();
    }

    public boolean isClosed() {
        if (this.state == 6) {
            return true;
        }
        return false;
    }

    public Sink newChunkedSink() {
        int i = this.state;
        if (i == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
    }

    public Source newChunkedSource(HttpUrl httpUrl) throws IOException {
        int i = this.state;
        if (i == 4) {
            this.state = 5;
            return new ChunkedSource(httpUrl);
        }
        throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
    }

    public Sink newFixedLengthSink(long j) {
        int i = this.state;
        if (i == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
    }

    public Source newFixedLengthSource(long j) throws IOException {
        int i = this.state;
        if (i == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
    }

    public Source newUnknownLengthSource() throws IOException {
        int i = this.state;
        if (i == 4) {
            StreamAllocation streamAllocation2 = this.streamAllocation;
            if (streamAllocation2 != null) {
                this.state = 5;
                streamAllocation2.noNewStreams();
                return new UnknownLengthSource();
            }
            throw new IllegalStateException("streamAllocation == null");
        }
        throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
    }

    public Headers readHeaders() throws IOException {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String readUtf8LineStrict = this.source.readUtf8LineStrict();
            if (readUtf8LineStrict.length() == 0) {
                return new Headers(builder);
            }
            Internal.instance.addLenient(builder, readUtf8LineStrict);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z) throws IOException {
        int i = this.state;
        if (i == 1 || i == 3) {
            try {
                StatusLine parse = StatusLine.parse(this.source.readUtf8LineStrict());
                Response.Builder builder = new Response.Builder();
                builder.protocol = parse.protocol;
                builder.code = parse.code;
                builder.message = parse.message;
                builder.headers = readHeaders().newBuilder();
                if (z && parse.code == 100) {
                    return null;
                }
                this.state = 4;
                return builder;
            } catch (EOFException e) {
                StringBuilder sb = new StringBuilder("unexpected end of stream on ");
                sb.append(this.streamAllocation);
                IOException iOException = new IOException(sb.toString());
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
        }
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        int i = this.state;
        if (i == 0) {
            this.sink.writeUtf8(str).writeUtf8(HttpRequestMultipart.LINE_FEED);
            int length = headers.namesAndValues.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                this.sink.writeUtf8(headers.namesAndValues[i2 << 1]).writeUtf8(": ").writeUtf8(headers.value(i2)).writeUtf8(HttpRequestMultipart.LINE_FEED);
            }
            this.sink.writeUtf8(HttpRequestMultipart.LINE_FEED);
            this.state = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A03("state: ", i));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) throws IOException {
        writeRequest(request.headers, RequestLine.get(request, this.streamAllocation.connection().route().proxy.type()));
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation2, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation2;
        this.source = bufferedSource;
        this.sink = bufferedSink;
    }

    private Source getTransferStream(Response response) throws IOException {
        if (!HttpHeaders.hasBody(response)) {
            return newFixedLengthSource(0);
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return newChunkedSource(response.request.url);
        }
        long contentLength = HttpHeaders.contentLength(response.headers);
        if (contentLength != -1) {
            return newFixedLengthSource(contentLength);
        }
        return newUnknownLengthSource();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers, Okio.buffer(getTransferStream(response)));
    }
}
