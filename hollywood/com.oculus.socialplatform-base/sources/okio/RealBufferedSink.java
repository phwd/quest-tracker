package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class RealBufferedSink implements BufferedSink {
    public final Buffer buffer = new Buffer();
    public boolean closed;
    public final Sink sink;

    @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink, java.nio.channels.Channel
    public void close() throws IOException {
        if (!this.closed) {
            Throwable th = null;
            try {
                Buffer buffer2 = this.buffer;
                long j = buffer2.size;
                if (j > 0) {
                    this.sink.write(buffer2, j);
                }
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
                throw th;
            }
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() throws IOException {
        if (!this.closed) {
            Buffer buffer2 = this.buffer;
            long j = buffer2.size;
            if (j > 0) {
                this.sink.write(buffer2, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink emitCompleteSegments() throws IOException {
        if (!this.closed) {
            Buffer buffer2 = this.buffer;
            long completeSegmentByteCount = buffer2.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                this.sink.write(buffer2, completeSegmentByteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
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

    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() {
            /* class okio.RealBufferedSink.AnonymousClass1 */

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                RealBufferedSink.this.close();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.flush();
                }
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(RealBufferedSink.this);
                sb.append(".outputStream()");
                return sb.toString();
            }

            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.buffer.writeByte((int) ((byte) i));
                    RealBufferedSink.this.emitCompleteSegments();
                    return;
                }
                throw new IOException("closed");
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.buffer.write(bArr, i, i2);
                    RealBufferedSink.this.emitCompleteSegments();
                    return;
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.sink);
        sb.append(")");
        return sb.toString();
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
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

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeByte(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeDecimalLong(long j) throws IOException {
        if (!this.closed) {
            this.buffer.writeDecimalLong(j);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long j) throws IOException {
        if (!this.closed) {
            this.buffer.writeHexadecimalUnsignedLong(j);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeInt(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeInt(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeIntLe(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeInt(Util.reverseBytesInt(i));
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeLong(long j) throws IOException {
        if (!this.closed) {
            this.buffer.writeLong(j);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeLongLe(long j) throws IOException {
        if (!this.closed) {
            this.buffer.writeLong(Util.reverseBytesLong(j));
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShort(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeShort(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShortLe(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeShortLe(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8CodePoint(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8CodePoint(i);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public RealBufferedSink(Sink sink2) {
        if (sink2 != null) {
            this.sink = sink2;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.closed) {
            int write = this.buffer.write(byteBuffer);
            emitCompleteSegments();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(ByteString byteString) throws IOException {
        if (!this.closed) {
            this.buffer.write(byteString);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j) throws IOException {
        while (j > 0) {
            long read = source.read(this.buffer, j);
            if (read != -1) {
                j -= read;
                emitCompleteSegments();
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] bArr) throws IOException {
        if (!this.closed) {
            this.buffer.write(bArr);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.closed) {
            this.buffer.write(bArr, i, i2);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.Sink
    public void write(Buffer buffer2, long j) throws IOException {
        if (!this.closed) {
            this.buffer.write(buffer2, j);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeString(String str, int i, int i2, Charset charset) throws IOException {
        if (!this.closed) {
            this.buffer.writeString(str, i, i2, charset);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeString(String str, Charset charset) throws IOException {
        if (!this.closed) {
            this.buffer.writeString(str, charset);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String str) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8(str);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String str, int i, int i2) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8(str, i, i2);
            emitCompleteSegments();
            return this;
        }
        throw new IllegalStateException("closed");
    }
}
