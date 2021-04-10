package com.squareup.okhttp.internal.http;

import X.AnonymousClass006;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

public final class RetryableSink implements Sink {
    public boolean closed;
    public final Buffer content;
    public final int limit;

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            long j = this.content.size;
            int i = this.limit;
            if (j < ((long) i)) {
                StringBuilder sb = new StringBuilder("content-length promised ");
                sb.append(i);
                sb.append(" bytes, but received ");
                sb.append(j);
                throw new ProtocolException(sb.toString());
            }
        }
    }

    public long contentLength() throws IOException {
        return this.content.size;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (!this.closed) {
            Util.checkOffsetAndCount(buffer.size, 0, j);
            int i = this.limit;
            if (i == -1 || this.content.size <= ((long) i) - j) {
                this.content.write(buffer, j);
                return;
            }
            throw new ProtocolException(AnonymousClass006.A04("exceeded content-length limit of ", i, " bytes"));
        }
        throw new IllegalStateException("closed");
    }

    public void writeToSocket(Sink sink) throws IOException {
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.content;
        buffer2.copyTo(buffer, 0, buffer2.size);
        sink.write(buffer, buffer.size);
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public RetryableSink() {
        this(-1);
    }

    public RetryableSink(int i) {
        this.content = new Buffer();
        this.limit = i;
    }
}
