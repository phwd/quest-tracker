package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

public final class RetryableSink implements Sink {
    private boolean closed;
    private final Buffer content;
    private final int limit;

    public RetryableSink(int limit2) {
        this.content = new Buffer();
        this.limit = limit2;
    }

    public RetryableSink() {
        this(-1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (this.content.size() < ((long) this.limit)) {
                throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
            }
        }
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Util.checkOffsetAndCount(source.size(), 0, byteCount);
        if (this.limit == -1 || this.content.size() <= ((long) this.limit) - byteCount) {
            this.content.write(source, byteCount);
            return;
        }
        throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public long contentLength() throws IOException {
        return this.content.size();
    }

    public void writeToSocket(Sink socketOut) throws IOException {
        Buffer buffer = new Buffer();
        this.content.copyTo(buffer, 0, this.content.size());
        socketOut.write(buffer, buffer.size());
    }
}
