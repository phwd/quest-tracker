package com.android.okhttp.okio;

/* access modifiers changed from: package-private */
public final class RealBufferedSink implements BufferedSink {
    public final Buffer buffer;
    private boolean closed;
    public final Sink sink;

    public RealBufferedSink(Sink sink2, Buffer buffer2) {
        if (sink2 != null) {
            this.buffer = buffer2;
            this.sink = sink2;
            return;
        }
        throw new IllegalArgumentException("sink == null");
    }

    public RealBufferedSink(Sink sink2) {
        this(sink2, new Buffer());
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // com.android.okhttp.okio.Sink
    public void write(Buffer buffer2, long j) {
        if (!this.closed) {
            this.buffer.write(buffer2, j);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink write(ByteString byteString) {
        if (!this.closed) {
            this.buffer.write(byteString);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink writeUtf8(String str) {
        if (!this.closed) {
            this.buffer.writeUtf8(str);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink write(byte[] bArr) {
        if (!this.closed) {
            this.buffer.write(bArr);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink write(byte[] bArr, int i, int i2) {
        if (!this.closed) {
            this.buffer.write(bArr, i, i2);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public long writeAll(Source source) {
        if (source != null) {
            long j = 0;
            while (true) {
                long read = source.read(this.buffer, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                emitCompleteSegments();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink writeByte(int i) {
        if (!this.closed) {
            this.buffer.writeByte(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink writeShort(int i) {
        if (!this.closed) {
            this.buffer.writeShort(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink writeInt(int i) {
        if (!this.closed) {
            this.buffer.writeInt(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink writeDecimalLong(long j) {
        if (!this.closed) {
            this.buffer.writeDecimalLong(j);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long j) {
        if (!this.closed) {
            this.buffer.writeHexadecimalUnsignedLong(j);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink emitCompleteSegments() {
        if (!this.closed) {
            long completeSegmentByteCount = this.buffer.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                this.sink.write(this.buffer, completeSegmentByteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink emit() {
        if (!this.closed) {
            long size = this.buffer.size();
            if (size > 0) {
                this.sink.write(this.buffer, size);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.Sink
    public void flush() {
        if (!this.closed) {
            Buffer buffer2 = this.buffer;
            long j = buffer2.size;
            if (j > 0) {
                this.sink.write(buffer2, j);
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Throwable th;
        if (!this.closed) {
            try {
                if (this.buffer.size > 0) {
                    this.sink.write(this.buffer, this.buffer.size);
                }
                th = null;
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.sink.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.closed = true;
            if (th != null) {
                Util.sneakyRethrow(th);
                throw null;
            }
        }
    }

    @Override // com.android.okhttp.okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "buffer(" + this.sink + ")";
    }
}
