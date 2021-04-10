package com.android.okhttp.okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* access modifiers changed from: package-private */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer;
    private boolean closed;
    public final Source source;

    public RealBufferedSource(Source source2, Buffer buffer2) {
        if (source2 != null) {
            this.buffer = buffer2;
            this.source = source2;
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    public RealBufferedSource(Source source2) {
        this(source2, new Buffer());
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // com.android.okhttp.okio.Source
    public long read(Buffer buffer2, long j) {
        if (buffer2 == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.closed) {
            Buffer buffer3 = this.buffer;
            if (buffer3.size == 0 && this.source.read(buffer3, 8192) == -1) {
                return -1;
            }
            return this.buffer.read(buffer2, Math.min(j, this.buffer.size));
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public boolean exhausted() {
        if (!this.closed) {
            return this.buffer.exhausted() && this.source.read(this.buffer, 8192) == -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void require(long j) {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    public boolean request(long j) {
        Buffer buffer2;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.closed) {
            do {
                buffer2 = this.buffer;
                if (buffer2.size >= j) {
                    return true;
                }
            } while (this.source.read(buffer2, 8192) != -1);
            return false;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte readByte() {
        require(1);
        return this.buffer.readByte();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public ByteString readByteString(long j) {
        require(j);
        return this.buffer.readByteString(j);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte[] readByteArray(long j) {
        require(j);
        return this.buffer.readByteArray(j);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8LineStrict() {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return this.buffer.readUtf8Line(indexOf);
        }
        Buffer buffer2 = new Buffer();
        Buffer buffer3 = this.buffer;
        buffer3.copyTo(buffer2, 0, Math.min(32L, buffer3.size()));
        throw new EOFException("\\n not found: size=" + this.buffer.size() + " content=" + buffer2.readByteString().hex() + "...");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public short readShort() {
        require(2);
        return this.buffer.readShort();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public short readShortLe() {
        require(2);
        return this.buffer.readShortLe();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readInt() {
        require(4);
        return this.buffer.readInt();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readIntLe() {
        require(4);
        return this.buffer.readIntLe();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readHexadecimalUnsignedLong() {
        require(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request((long) i2)) {
                break;
            }
            byte b = this.buffer.getByte((long) i);
            if ((b >= 48 && b <= 57) || ((b >= 97 && b <= 102) || (b >= 65 && b <= 70))) {
                i = i2;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(b)));
            }
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void skip(long j) {
        if (!this.closed) {
            while (j > 0) {
                Buffer buffer2 = this.buffer;
                if (buffer2.size == 0 && this.source.read(buffer2, 8192) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.buffer.size());
                this.buffer.skip(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0);
    }

    public long indexOf(byte b, long j) {
        Buffer buffer2;
        if (!this.closed) {
            do {
                buffer2 = this.buffer;
                if (j < buffer2.size) {
                    while (true) {
                        long indexOf = this.buffer.indexOf(b, j);
                        if (indexOf != -1) {
                            return indexOf;
                        }
                        Buffer buffer3 = this.buffer;
                        long j2 = buffer3.size;
                        if (this.source.read(buffer3, 8192) == -1) {
                            return -1;
                        }
                        j = j2;
                    }
                }
            } while (this.source.read(buffer2, 8192) != -1);
            return -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() {
            /* class com.android.okhttp.okio.RealBufferedSource.AnonymousClass1 */

            @Override // java.io.InputStream
            public int read() {
                if (!RealBufferedSource.this.closed) {
                    RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.readByte() & 255;
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                if (!RealBufferedSource.this.closed) {
                    Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
                    RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.read(bArr, i, i2);
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream
            public int available() {
                if (!RealBufferedSource.this.closed) {
                    return (int) Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
                }
                throw new IOException("closed");
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() {
                RealBufferedSource.this.close();
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    @Override // java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.buffer.clear();
        }
    }

    @Override // com.android.okhttp.okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ")";
    }
}
