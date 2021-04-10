package com.android.okhttp.internal.http;

import com.android.okhttp.internal.Util;
import com.android.okhttp.okio.Buffer;
import com.android.okhttp.okio.Sink;
import com.android.okhttp.okio.Timeout;
import java.io.IOException;
import java.net.ProtocolException;

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

    @Override // com.android.okhttp.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (this.content.size() < ((long) this.limit)) {
                throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
            }
        }
    }

    @Override // com.android.okhttp.okio.Sink
    public void write(Buffer source, long byteCount) throws IOException {
        if (!this.closed) {
            Util.checkOffsetAndCount(source.size(), 0, byteCount);
            if (this.limit == -1 || this.content.size() <= ((long) this.limit) - byteCount) {
                this.content.write(source, byteCount);
                return;
            }
            throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // com.android.okhttp.okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public long contentLength() throws IOException {
        return this.content.size();
    }

    public void writeToSocket(Sink socketOut) throws IOException {
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.content;
        buffer2.copyTo(buffer, 0, buffer2.size());
        socketOut.write(buffer, buffer.size());
    }
}
