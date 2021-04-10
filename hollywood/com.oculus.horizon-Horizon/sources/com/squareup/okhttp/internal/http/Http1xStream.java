package com.squareup.okhttp.internal.http;

import X.AbstractC07630v6;
import X.AbstractC07640v7;
import X.AnonymousClass006;
import X.AnonymousClass0B3;
import X.AnonymousClass0Ls;
import X.AnonymousClass0Lw;
import X.AnonymousClass0Lx;
import X.C00560Au;
import X.C07620v5;
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
    public final AnonymousClass0Lx sink;
    public final AnonymousClass0Lw source;
    public int state = 0;
    public final StreamAllocation streamAllocation;

    public abstract class AbstractSource implements AbstractC07630v6 {
        public boolean closed;
        public final AnonymousClass0Ls timeout;

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
            throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
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

        @Override // X.AbstractC07630v6
        public C07620v5 timeout() {
            return this.timeout;
        }

        public AbstractSource() {
            this.timeout = new AnonymousClass0Ls(Http1xStream.this.source.timeout());
        }
    }

    public final class ChunkedSink implements AbstractC07640v7 {
        public boolean closed;
        public final AnonymousClass0Ls timeout;

        @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                Http1xStream.this.sink.AAI("0\r\n\r\n");
                Http1xStream http1xStream = Http1xStream.this;
                http1xStream.detachTimeout(this.timeout);
                http1xStream.state = 3;
            }
        }

        @Override // X.AbstractC07640v7, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (!this.closed) {
                Http1xStream.this.sink.flush();
            }
        }

        @Override // X.AbstractC07640v7
        public void write(AnonymousClass0B3 r4, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1xStream.this.sink.AAE(j);
                Http1xStream.this.sink.AAI(HttpRequestMultipart.LINE_FEED);
                Http1xStream.this.sink.write(r4, j);
                Http1xStream.this.sink.AAI(HttpRequestMultipart.LINE_FEED);
            }
        }

        @Override // X.AbstractC07640v7
        public C07620v5 timeout() {
            return this.timeout;
        }

        public ChunkedSink() {
            this.timeout = new AnonymousClass0Ls(Http1xStream.this.sink.timeout());
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
                Http1xStream.this.source.A81();
            }
            try {
                this.bytesRemainingInChunk = Http1xStream.this.source.A7v();
                String trim = Http1xStream.this.source.A81().trim();
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
        @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
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
        @Override // X.AbstractC07630v6
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(X.AnonymousClass0B3 r9, long r10) throws java.io.IOException {
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
                X.0Lw r2 = r0.source
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
                java.lang.String r1 = X.AnonymousClass006.A04(r0, r10)
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.Http1xStream.ChunkedSource.read(X.0B3, long):long");
        }
    }

    public final class FixedLengthSink implements AbstractC07640v7 {
        public long bytesRemaining;
        public boolean closed;
        public final AnonymousClass0Ls timeout;

        @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
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

        @Override // X.AbstractC07640v7, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                Http1xStream.this.sink.flush();
            }
        }

        @Override // X.AbstractC07640v7
        public void write(AnonymousClass0B3 r7, long j) throws IOException {
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

        @Override // X.AbstractC07640v7
        public C07620v5 timeout() {
            return this.timeout;
        }

        public FixedLengthSink(long j) {
            this.timeout = new AnonymousClass0Ls(Http1xStream.this.sink.timeout());
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
        @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
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

        @Override // X.AbstractC07630v6
        public long read(AnonymousClass0B3 r10, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
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

        @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    unexpectedEndOfInput();
                }
                this.closed = true;
            }
        }

        @Override // X.AbstractC07630v6
        public long read(AnonymousClass0B3 r6, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
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
    private void detachTimeout(AnonymousClass0Ls r3) {
        C07620v5 r1 = r3.A00;
        C07620v5 r0 = C07620v5.NONE;
        if (r0 != null) {
            r3.A00 = r0;
            r1.clearDeadline();
            r1.clearTimeout();
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
    public AbstractC07640v7 createRequestBody(Request request, long j) throws IOException {
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

    public AbstractC07640v7 newChunkedSink() {
        int i = this.state;
        if (i == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    public AbstractC07630v6 newChunkedSource(HttpEngine httpEngine2) throws IOException {
        int i = this.state;
        if (i == 4) {
            this.state = 5;
            return new ChunkedSource(httpEngine2);
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    public AbstractC07640v7 newFixedLengthSink(long j) {
        int i = this.state;
        if (i == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    public AbstractC07630v6 newFixedLengthSource(long j) throws IOException {
        int i = this.state;
        if (i == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    public AbstractC07630v6 newUnknownLengthSource() throws IOException {
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
            str = AnonymousClass006.A01("state: ", i);
        }
        throw new IllegalStateException(str);
    }

    public Headers readHeaders() throws IOException {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String A81 = this.source.A81();
            if (A81.length() == 0) {
                return new Headers(builder);
            }
            Internal.instance.addLenient(builder, A81);
        }
    }

    public Response.Builder readResponse() throws IOException {
        StatusLine parse;
        Response.Builder builder;
        int i = this.state;
        if (i == 1 || i == 3) {
            do {
                try {
                    parse = StatusLine.parse(this.source.A81());
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
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        int i = this.state;
        if (i == 0) {
            AnonymousClass0Lx r0 = this.sink;
            r0.AAI(str);
            r0.AAI(HttpRequestMultipart.LINE_FEED);
            int length = headers.namesAndValues.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                AnonymousClass0Lx r1 = this.sink;
                r1.AAI(headers.name(i2));
                r1.AAI(": ");
                r1.AAI(headers.value(i2));
                r1.AAI(HttpRequestMultipart.LINE_FEED);
            }
            this.sink.AAI(HttpRequestMultipart.LINE_FEED);
            this.state = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        int i = this.state;
        if (i == 1) {
            this.state = 3;
            retryableSink.writeToSocket(this.sink);
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestHeaders(Request request) throws IOException {
        HttpEngine httpEngine2 = this.httpEngine;
        httpEngine2.writingRequestHeaders();
        writeRequest(request.headers, RequestLine.get(request, httpEngine2.streamAllocation.connection().getRoute().proxy.type()));
    }

    public Http1xStream(StreamAllocation streamAllocation2, AnonymousClass0Lw r3, AnonymousClass0Lx r4) {
        this.streamAllocation = streamAllocation2;
        this.source = r3;
        this.sink = r4;
    }

    private AbstractC07630v6 getTransferStream(Response response) throws IOException {
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
        return new RealResponseBody(response.headers, new C00560Au(getTransferStream(response)));
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
