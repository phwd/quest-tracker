package com.android.okhttp.okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

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
    public long read(Buffer sink, long byteCount) throws IOException {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (this.buffer.size == 0 && this.source.read(this.buffer, 8192) == -1) {
            return -1;
        } else {
            return this.buffer.read(sink, Math.min(byteCount, this.buffer.size));
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public boolean exhausted() throws IOException {
        if (!this.closed) {
            return this.buffer.exhausted() && this.source.read(this.buffer, 8192) == -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void require(long byteCount) throws IOException {
        if (!request(byteCount)) {
            throw new EOFException();
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public boolean request(long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (!this.closed) {
            while (this.buffer.size < byteCount) {
                if (this.source.read(this.buffer, 8192) == -1) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte readByte() throws IOException {
        require(1);
        return this.buffer.readByte();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public ByteString readByteString() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public ByteString readByteString(long byteCount) throws IOException {
        require(byteCount);
        return this.buffer.readByteString(byteCount);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte[] readByteArray(long byteCount) throws IOException {
        require(byteCount);
        return this.buffer.readByteArray(byteCount);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int read(byte[] sink) throws IOException {
        return read(sink, 0, sink.length);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void readFully(byte[] sink) throws IOException {
        try {
            require((long) sink.length);
            this.buffer.readFully(sink);
        } catch (EOFException e) {
            int offset = 0;
            while (this.buffer.size > 0) {
                Buffer buffer2 = this.buffer;
                int read = buffer2.read(sink, offset, (int) buffer2.size);
                if (read != -1) {
                    offset += read;
                } else {
                    throw new AssertionError();
                }
            }
            throw e;
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int read(byte[] sink, int offset, int byteCount) throws IOException {
        Util.checkOffsetAndCount((long) sink.length, (long) offset, (long) byteCount);
        if (this.buffer.size == 0 && this.source.read(this.buffer, 8192) == -1) {
            return -1;
        }
        return this.buffer.read(sink, offset, (int) Math.min((long) byteCount, this.buffer.size));
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void readFully(Buffer sink, long byteCount) throws IOException {
        try {
            require(byteCount);
            this.buffer.readFully(sink, byteCount);
        } catch (EOFException e) {
            sink.writeAll(this.buffer);
            throw e;
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        if (sink != null) {
            long totalBytesWritten = 0;
            while (this.source.read(this.buffer, 8192) != -1) {
                long emitByteCount = this.buffer.completeSegmentByteCount();
                if (emitByteCount > 0) {
                    totalBytesWritten += emitByteCount;
                    sink.write(this.buffer, emitByteCount);
                }
            }
            if (this.buffer.size() <= 0) {
                return totalBytesWritten;
            }
            long totalBytesWritten2 = totalBytesWritten + this.buffer.size();
            Buffer buffer2 = this.buffer;
            sink.write(buffer2, buffer2.size());
            return totalBytesWritten2;
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8(long byteCount) throws IOException {
        require(byteCount);
        return this.buffer.readUtf8(byteCount);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readString(Charset charset) throws IOException {
        if (charset != null) {
            this.buffer.writeAll(this.source);
            return this.buffer.readString(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readString(long byteCount, Charset charset) throws IOException {
        require(byteCount);
        if (charset != null) {
            return this.buffer.readString(byteCount, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8Line() throws IOException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return this.buffer.readUtf8Line(newline);
        }
        if (this.buffer.size != 0) {
            return readUtf8(this.buffer.size);
        }
        return null;
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8LineStrict() throws IOException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return this.buffer.readUtf8Line(newline);
        }
        Buffer data = new Buffer();
        Buffer buffer2 = this.buffer;
        buffer2.copyTo(data, 0, Math.min(32L, buffer2.size()));
        throw new EOFException("\\n not found: size=" + this.buffer.size() + " content=" + data.readByteString().hex() + "...");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readUtf8CodePoint() throws IOException {
        require(1);
        byte b0 = this.buffer.getByte(0);
        if ((b0 & 224) == 192) {
            require(2);
        } else if ((b0 & 240) == 224) {
            require(3);
        } else if ((b0 & 248) == 240) {
            require(4);
        }
        return this.buffer.readUtf8CodePoint();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public short readShort() throws IOException {
        require(2);
        return this.buffer.readShort();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public short readShortLe() throws IOException {
        require(2);
        return this.buffer.readShortLe();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readInt() throws IOException {
        require(4);
        return this.buffer.readInt();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readIntLe() throws IOException {
        require(4);
        return this.buffer.readIntLe();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readLong() throws IOException {
        require(8);
        return this.buffer.readLong();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readLongLe() throws IOException {
        require(8);
        return this.buffer.readLongLe();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    @Override // com.android.okhttp.okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 1
            r6.require(r0)
            r0 = 0
        L_0x0006:
            int r1 = r0 + 1
            long r1 = (long) r1
            boolean r1 = r6.request(r1)
            if (r1 == 0) goto L_0x0041
            com.android.okhttp.okio.Buffer r1 = r6.buffer
            long r2 = (long) r0
            byte r1 = r1.getByte(r2)
            r2 = 48
            if (r1 < r2) goto L_0x001e
            r2 = 57
            if (r1 <= r2) goto L_0x0025
        L_0x001e:
            if (r0 != 0) goto L_0x0028
            r2 = 45
            if (r1 == r2) goto L_0x0025
            goto L_0x0028
        L_0x0025:
            int r0 = r0 + 1
            goto L_0x0006
        L_0x0028:
            if (r0 == 0) goto L_0x002b
            goto L_0x0041
        L_0x002b:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            java.lang.Byte r5 = java.lang.Byte.valueOf(r1)
            r3[r4] = r5
            java.lang.String r4 = "Expected leading [0-9] or '-' character but was %#x"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r2.<init>(r3)
            throw r2
        L_0x0041:
            com.android.okhttp.okio.Buffer r0 = r6.buffer
            long r0 = r0.readDecimalLong()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.okio.RealBufferedSource.readDecimalLong():long");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws IOException {
        require(1);
        int pos = 0;
        while (true) {
            if (!request((long) (pos + 1))) {
                break;
            }
            byte b = this.buffer.getByte((long) pos);
            if ((b >= 48 && b <= 57) || ((b >= 97 && b <= 102) || (b >= 65 && b <= 70))) {
                pos++;
            } else if (pos == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(b)));
            }
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void skip(long byteCount) throws IOException {
        if (!this.closed) {
            while (byteCount > 0) {
                if (this.buffer.size == 0 && this.source.read(this.buffer, 8192) == -1) {
                    throw new EOFException();
                }
                long toSkip = Math.min(byteCount, this.buffer.size());
                this.buffer.skip(toSkip);
                byteCount -= toSkip;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(byte b) throws IOException {
        return indexOf(b, 0);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(byte b, long fromIndex) throws IOException {
        if (!this.closed) {
            while (fromIndex >= this.buffer.size) {
                if (this.source.read(this.buffer, 8192) == -1) {
                    return -1;
                }
            }
            do {
                long index = this.buffer.indexOf(b, fromIndex);
                if (index != -1) {
                    return index;
                }
                fromIndex = this.buffer.size;
            } while (this.source.read(this.buffer, 8192) != -1);
            return -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(ByteString bytes) throws IOException {
        return indexOf(bytes, 0);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(ByteString bytes, long fromIndex) throws IOException {
        if (bytes.size() != 0) {
            while (true) {
                long fromIndex2 = indexOf(bytes.getByte(0), fromIndex);
                if (fromIndex2 == -1) {
                    return -1;
                }
                if (rangeEquals(fromIndex2, bytes)) {
                    return fromIndex2;
                }
                fromIndex = fromIndex2 + 1;
            }
        } else {
            throw new IllegalArgumentException("bytes is empty");
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOfElement(ByteString targetBytes) throws IOException {
        return indexOfElement(targetBytes, 0);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOfElement(ByteString targetBytes, long fromIndex) throws IOException {
        if (!this.closed) {
            while (fromIndex >= this.buffer.size) {
                if (this.source.read(this.buffer, 8192) == -1) {
                    return -1;
                }
            }
            do {
                long index = this.buffer.indexOfElement(targetBytes, fromIndex);
                if (index != -1) {
                    return index;
                }
                fromIndex = this.buffer.size;
            } while (this.source.read(this.buffer, 8192) != -1);
            return -1;
        }
        throw new IllegalStateException("closed");
    }

    private boolean rangeEquals(long offset, ByteString bytes) throws IOException {
        return request(((long) bytes.size()) + offset) && this.buffer.rangeEquals(offset, bytes);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() {
            /* class com.android.okhttp.okio.RealBufferedSource.AnonymousClass1 */

            @Override // java.io.InputStream
            public int read() throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                } else if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192) == -1) {
                    return -1;
                } else {
                    return RealBufferedSource.this.buffer.readByte() & 255;
                }
            }

            @Override // java.io.InputStream
            public int read(byte[] data, int offset, int byteCount) throws IOException {
                if (!RealBufferedSource.this.closed) {
                    Util.checkOffsetAndCount((long) data.length, (long) offset, (long) byteCount);
                    if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.read(data, offset, byteCount);
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream
            public int available() throws IOException {
                if (!RealBufferedSource.this.closed) {
                    return (int) Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
                }
                throw new IOException("closed");
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }

            public String toString() {
                return ((Object) RealBufferedSource.this) + ".inputStream()";
            }
        };
    }

    @Override // java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
    public void close() throws IOException {
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
        return "buffer(" + ((Object) this.source) + ")";
    }
}
