package com.android.okhttp.internal.http;

import com.android.okhttp.internal.Util;
import com.android.okhttp.okio.Buffer;
import com.android.okhttp.okio.Sink;
import com.android.okhttp.okio.Timeout;
import java.net.ProtocolException;

public final class RetryableSink implements Sink {
    private boolean closed;
    private final Buffer content;
    private final int limit;

    @Override // com.android.okhttp.okio.Sink
    public void flush() {
    }

    public RetryableSink(int i) {
        this.content = new Buffer();
        this.limit = i;
    }

    public RetryableSink() {
        this(-1);
    }

    @Override // com.android.okhttp.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.closed = true;
            if (this.content.size() < ((long) this.limit)) {
                throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
            }
        }
    }

    @Override // com.android.okhttp.okio.Sink
    public void write(Buffer buffer, long j) {
        if (!this.closed) {
            Util.checkOffsetAndCount(buffer.size(), 0, j);
            if (this.limit == -1 || this.content.size() <= ((long) this.limit) - j) {
                this.content.write(buffer, j);
                return;
            }
            throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public long contentLength() {
        return this.content.size();
    }

    public void writeToSocket(Sink sink) {
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.content;
        buffer2.copyTo(buffer, 0, buffer2.size());
        sink.write(buffer, buffer.size());
    }
}
