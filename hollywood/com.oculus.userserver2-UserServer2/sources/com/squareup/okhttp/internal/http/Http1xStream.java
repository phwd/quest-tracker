package com.squareup.okhttp.internal.http;

import X.AnonymousClass06;
import X.AnonymousClass8k;
import X.C00148h;
import X.Dl;
import X.Dp;
import X.Du;
import X.WE;
import X.WF;
import X.WG;
import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.io.RealConnection;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;

public final class Http1xStream implements HttpStream {
    public static final int STATE_CLOSED = 6;
    public static final int STATE_IDLE = 0;
    public static final int STATE_OPEN_REQUEST_BODY = 1;
    public static final int STATE_OPEN_RESPONSE_BODY = 4;
    public static final int STATE_READING_RESPONSE_BODY = 5;
    public static final int STATE_READ_RESPONSE_HEADERS = 3;
    public static final int STATE_WRITING_REQUEST_BODY = 2;
    public HttpEngine httpEngine;
    public final Du sink;
    public final Dp source;
    public int state = 0;
    public final StreamAllocation streamAllocation;

    public abstract class AbstractSource implements WF {
        public boolean closed;
        public final Dl timeout;

        public final void endOfInput() throws IOException {
            Http1xStream http1xStream = Http1xStream.this;
            int i = http1xStream.state;
            if (i == 5) {
                http1xStream.detachTimeout(this.timeout);
                http1xStream.state = 6;
                StreamAllocation streamAllocation = http1xStream.streamAllocation;
                if (streamAllocation != null) {
                    streamAllocation.streamFinished(http1xStream);
                    return;
                }
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
        }

        public final void unexpectedEndOfInput() {
            Http1xStream http1xStream = Http1xStream.this;
            if (http1xStream.state != 6) {
                http1xStream.state = 6;
                StreamAllocation streamAllocation = http1xStream.streamAllocation;
                if (streamAllocation != null) {
                    streamAllocation.noNewStreams();
                    Http1xStream http1xStream2 = Http1xStream.this;
                    http1xStream2.streamAllocation.streamFinished(http1xStream2);
                }
            }
        }

        @Override // X.WF
        public WE timeout() {
            return this.timeout;
        }

        public AbstractSource() {
            this.timeout = new Dl(Http1xStream.this.source.timeout());
        }
    }

    public final class ChunkedSink implements WG {
        public boolean closed;
        public final Dl timeout;

        @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                Http1xStream.this.sink.A44("0\r\n\r\n");
                Http1xStream http1xStream = Http1xStream.this;
                http1xStream.detachTimeout(this.timeout);
                http1xStream.state = 3;
            }
        }

        @Override // X.WG, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (!this.closed) {
                Http1xStream.this.sink.flush();
            }
        }

        @Override // X.WG
        public void write(AnonymousClass8k r4, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1xStream.this.sink.A40(j);
                Http1xStream.this.sink.A44(HttpRequestMultipart.LINE_FEED);
                Http1xStream.this.sink.write(r4, j);
                Http1xStream.this.sink.A44(HttpRequestMultipart.LINE_FEED);
            }
        }

        @Override // X.WG
        public WE timeout() {
            return this.timeout;
        }

        public ChunkedSink() {
            this.timeout = new Dl(Http1xStream.this.sink.timeout());
        }
    }

    public class ChunkedSource extends AbstractSource {
        public static final long NO_CHUNK_YET = -1;
        public long bytesRemainingInChunk = -1;
        public boolean hasMoreChunks = true;
        public final HttpEngine httpEngine;

        public ChunkedSource(HttpEngine httpEngine2) throws IOException {
            super();
            this.httpEngine = httpEngine2;
        }

        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1) {
                Http1xStream.this.source.A3B();
            }
            try {
                this.bytesRemainingInChunk = Http1xStream.this.source.A35();
                String trim = Http1xStream.this.source.A3B().trim();
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
                    this.httpEngine.receiveHeaders(Http1xStream.this.readHeaders());
                    endOfInput();
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
            if (com.squareup.okhttp.internal.Util.skipAll(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0012;
         */
        @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r2 = this;
                boolean r0 = r2.closed
                if (r0 != 0) goto L_0x0018
                boolean r0 = r2.hasMoreChunks
                if (r0 == 0) goto L_0x0015
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
                r0 = 100
                boolean r0 = com.squareup.okhttp.internal.Util.skipAll(r2, r0, r1)     // Catch:{ IOException -> 0x0012 }
                if (r0 != 0) goto L_0x0015
            L_0x0012:
                r2.unexpectedEndOfInput()
            L_0x0015:
                r0 = 1
                r2.closed = r0
            L_0x0018:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.Http1xStream.ChunkedSource.close():void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
            if (r8.hasMoreChunks == false) goto L_0x0021;
         */
        @Override // X.WF
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(X.AnonymousClass8k r9, long r10) throws java.io.IOException {
            /*
                r8 = this;
                r6 = 0
                int r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r0 < 0) goto L_0x004d
                boolean r0 = r8.closed
                if (r0 != 0) goto L_0x0045
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
                com.squareup.okhttp.internal.http.Http1xStream r0 = com.squareup.okhttp.internal.http.Http1xStream.this
                X.Dp r2 = r0.source
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
                r8.unexpectedEndOfInput()
                java.lang.String r1 = "unexpected end of stream"
                java.net.ProtocolException r0 = new java.net.ProtocolException
                r0.<init>(r1)
                throw r0
            L_0x0045:
                java.lang.String r1 = "closed"
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r0.<init>(r1)
                throw r0
            L_0x004d:
                java.lang.String r0 = "byteCount < 0: "
                java.lang.String r1 = X.AnonymousClass06.A02(r0, r10)
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.Http1xStream.ChunkedSource.read(X.8k, long):long");
        }
    }

    public final class FixedLengthSink implements WG {
        public long bytesRemaining;
        public boolean closed;
        public final Dl timeout;

        @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining <= 0) {
                    Http1xStream http1xStream = Http1xStream.this;
                    http1xStream.detachTimeout(this.timeout);
                    http1xStream.state = 3;
                    return;
                }
                throw new ProtocolException("unexpected end of stream");
            }
        }

        @Override // X.WG, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                Http1xStream.this.sink.flush();
            }
        }

        @Override // X.WG
        public void write(AnonymousClass8k r7, long j) throws IOException {
            if (!this.closed) {
                Util.checkOffsetAndCount(r7.A00, 0, j);
                long j2 = this.bytesRemaining;
                if (j <= j2) {
                    Http1xStream.this.sink.write(r7, j);
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

        @Override // X.WG
        public WE timeout() {
            return this.timeout;
        }

        public FixedLengthSink(long j) {
            this.timeout = new Dl(Http1xStream.this.sink.timeout());
            this.bytesRemaining = j;
        }
    }

    public class FixedLengthSource extends AbstractSource {
        public long bytesRemaining;

        public FixedLengthSource(long j) throws IOException {
            super();
            this.bytesRemaining = j;
            if (j == 0) {
                endOfInput();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
            if (com.squareup.okhttp.internal.Util.skipAll(r5, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0016;
         */
        @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r5 = this;
                boolean r0 = r5.closed
                if (r0 != 0) goto L_0x001c
                long r3 = r5.bytesRemaining
                r1 = 0
                int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r0 == 0) goto L_0x0019
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
                r0 = 100
                boolean r0 = com.squareup.okhttp.internal.Util.skipAll(r5, r0, r1)     // Catch:{ IOException -> 0x0016 }
                if (r0 != 0) goto L_0x0019
            L_0x0016:
                r5.unexpectedEndOfInput()
            L_0x0019:
                r0 = 1
                r5.closed = r0
            L_0x001c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.Http1xStream.FixedLengthSource.close():void");
        }

        @Override // X.WF
        public long read(AnonymousClass8k r10, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass06.A02("byteCount < 0: ", j));
            } else if (!this.closed) {
                long j2 = this.bytesRemaining;
                if (j2 == 0) {
                    return -1;
                }
                long read = Http1xStream.this.source.read(r10, Math.min(j2, j));
                if (read != -1) {
                    long j3 = this.bytesRemaining - read;
                    this.bytesRemaining = j3;
                    if (j3 == 0) {
                        endOfInput();
                    }
                    return read;
                }
                unexpectedEndOfInput();
                throw new ProtocolException("unexpected end of stream");
            } else {
                throw new IllegalStateException("closed");
            }
        }
    }

    public class UnknownLengthSource extends AbstractSource {
        public boolean inputExhausted;

        @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    unexpectedEndOfInput();
                }
                this.closed = true;
            }
        }

        @Override // X.WF
        public long read(AnonymousClass8k r6, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass06.A02("byteCount < 0: ", j));
            } else if (!this.closed) {
                if (!this.inputExhausted) {
                    long read = Http1xStream.this.source.read(r6, j);
                    if (read != -1) {
                        return read;
                    }
                    this.inputExhausted = true;
                    endOfInput();
                }
                return -1;
            } else {
                throw new IllegalStateException("closed");
            }
        }

        public UnknownLengthSource() {
            super();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void detachTimeout(Dl dl) {
        WE we = dl.A00;
        WE we2 = WE.NONE;
        if (we2 != null) {
            dl.A00 = we2;
            we.clearDeadline();
            we.clearTimeout();
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public WG createRequestBody(Request request, long j) throws IOException {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void finishRequest() throws IOException {
        this.sink.flush();
    }

    public boolean isClosed() {
        if (this.state == 6) {
            return true;
        }
        return false;
    }

    public WG newChunkedSink() {
        int i = this.state;
        if (i == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    public WF newChunkedSource(HttpEngine httpEngine2) throws IOException {
        int i = this.state;
        if (i == 4) {
            this.state = 5;
            return new ChunkedSource(httpEngine2);
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    public WG newFixedLengthSink(long j) {
        int i = this.state;
        if (i == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    public WF newFixedLengthSource(long j) throws IOException {
        int i = this.state;
        if (i == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    public WF newUnknownLengthSource() throws IOException {
        String str;
        int i = this.state;
        if (i == 4) {
            StreamAllocation streamAllocation2 = this.streamAllocation;
            if (streamAllocation2 != null) {
                this.state = 5;
                streamAllocation2.noNewStreams();
                return new UnknownLengthSource();
            }
            str = "streamAllocation == null";
        } else {
            str = AnonymousClass06.A01("state: ", i);
        }
        throw new IllegalStateException(str);
    }

    public Headers readHeaders() throws IOException {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String A3B = this.source.A3B();
            if (A3B.length() == 0) {
                return new Headers(builder);
            }
            Internal.instance.addLenient(builder, A3B);
        }
    }

    public Response.Builder readResponse() throws IOException {
        StatusLine parse;
        Response.Builder builder;
        int i = this.state;
        if (i == 1 || i == 3) {
            do {
                try {
                    parse = StatusLine.parse(this.source.A3B());
                    builder = new Response.Builder();
                    builder.protocol = parse.protocol;
                    builder.code = parse.code;
                    builder.message = parse.message;
                    builder.headers = readHeaders().newBuilder();
                } catch (EOFException e) {
                    StringBuilder sb = new StringBuilder("unexpected end of stream on ");
                    sb.append(this.streamAllocation);
                    IOException iOException = new IOException(sb.toString());
                    iOException.initCause(e);
                    throw iOException;
                }
            } while (parse.code == 100);
            this.state = 4;
            return builder;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        int i = this.state;
        if (i == 0) {
            Du du = this.sink;
            du.A44(str);
            du.A44(HttpRequestMultipart.LINE_FEED);
            int length = headers.namesAndValues.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                Du du2 = this.sink;
                du2.A44(headers.name(i2));
                du2.A44(": ");
                du2.A44(headers.value(i2));
                du2.A44(HttpRequestMultipart.LINE_FEED);
            }
            this.sink.A44(HttpRequestMultipart.LINE_FEED);
            this.state = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        int i = this.state;
        if (i == 1) {
            this.state = 3;
            retryableSink.writeToSocket(this.sink);
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestHeaders(Request request) throws IOException {
        HttpEngine httpEngine2 = this.httpEngine;
        httpEngine2.writingRequestHeaders();
        writeRequest(request.headers, RequestLine.get(request, httpEngine2.streamAllocation.connection().getRoute().proxy.type()));
    }

    public Http1xStream(StreamAllocation streamAllocation2, Dp dp, Du du) {
        this.streamAllocation = streamAllocation2;
        this.source = dp;
        this.sink = du;
    }

    private WF getTransferStream(Response response) throws IOException {
        if (!HttpEngine.hasBody(response)) {
            return newFixedLengthSource(0);
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding", null))) {
            return newChunkedSource(this.httpEngine);
        }
        long contentLength = OkHeaders.contentLength(response.headers);
        if (contentLength != -1) {
            return newFixedLengthSource(contentLength);
        }
        return newUnknownLengthSource();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers, new C00148h(getTransferStream(response)));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public Response.Builder readResponseHeaders() throws IOException {
        return readResponse();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void setHttpEngine(HttpEngine httpEngine2) {
        this.httpEngine = httpEngine2;
    }
}
