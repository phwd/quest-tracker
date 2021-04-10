package okio;

import X.AnonymousClass006;
import androidx.recyclerview.widget.RecyclerView;
import com.adobe.xmp.impl.Base64;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

public final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    public boolean closed;
    public final Source source;

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.buffer.clear();
        }
    }

    @Override // okio.BufferedSource
    public boolean exhausted() throws IOException {
        if (!this.closed) {
            Buffer buffer2 = this.buffer;
            if (!buffer2.exhausted() || this.source.read(buffer2, 8192) != -1) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() {
            /* class okio.RealBufferedSource.AnonymousClass1 */

            @Override // java.io.InputStream
            public int available() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    return (int) Math.min(realBufferedSource.buffer.size, 2147483647L);
                }
                throw new IOException("closed");
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(RealBufferedSource.this);
                sb.append(".inputStream()");
                return sb.toString();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.readByte() & Base64.INVALID;
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
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
        };
    }

    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        Buffer buffer2;
        if (sink != null) {
            long j = 0;
            while (true) {
                int i = (this.source.read(this.buffer, 8192) > -1 ? 1 : (this.source.read(this.buffer, 8192) == -1 ? 0 : -1));
                buffer2 = this.buffer;
                if (i == 0) {
                    break;
                }
                long completeSegmentByteCount = buffer2.completeSegmentByteCount();
                if (completeSegmentByteCount > 0) {
                    j += completeSegmentByteCount;
                    sink.write(buffer2, completeSegmentByteCount);
                }
            }
            long j2 = buffer2.size;
            if (j2 <= 0) {
                return j;
            }
            long j3 = j + j2;
            sink.write(buffer2, j2);
            return j3;
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.BufferedSource
    public byte readByte() throws IOException {
        require(1);
        return this.buffer.readByte();
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() throws IOException {
        require(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request((long) i2)) {
                break;
            }
            byte b = this.buffer.getByte((long) i);
            if (b < 48 || b > 57) {
                if (i != 0) {
                    break;
                } else if (b != 45) {
                    throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(b)));
                }
            }
            i = i2;
        }
        return this.buffer.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws IOException {
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

    @Override // okio.BufferedSource
    public int readInt() throws IOException {
        require(4);
        return this.buffer.readInt();
    }

    @Override // okio.BufferedSource
    public int readIntLe() throws IOException {
        require(4);
        return this.buffer.readIntLe();
    }

    @Override // okio.BufferedSource
    public long readLong() throws IOException {
        require(8);
        return this.buffer.readLong();
    }

    @Override // okio.BufferedSource
    public long readLongLe() throws IOException {
        require(8);
        return this.buffer.readLongLe();
    }

    @Override // okio.BufferedSource
    public short readShort() throws IOException {
        require(2);
        return this.buffer.readShort();
    }

    @Override // okio.BufferedSource
    public short readShortLe() throws IOException {
        require(2);
        return this.buffer.readShortLe();
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws IOException {
        long j;
        require(1);
        byte b = this.buffer.getByte(0);
        if ((b & 224) == 192) {
            j = 2;
        } else if ((b & 240) == 224) {
            j = 3;
        } else {
            if ((b & 248) == 240) {
                j = 4;
            }
            return this.buffer.readUtf8CodePoint();
        }
        require(j);
        return this.buffer.readUtf8CodePoint();
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws IOException {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return this.buffer.readUtf8Line(indexOf);
        }
        long j = this.buffer.size;
        if (j != 0) {
            return readUtf8(j);
        }
        return null;
    }

    @Override // okio.BufferedSource
    public boolean request(long j) throws IOException {
        Buffer buffer2;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
        } else if (this.closed) {
            throw new IllegalStateException("closed");
        } else {
            do {
                buffer2 = this.buffer;
                if (buffer2.size >= j) {
                    return true;
                }
            } while (this.source.read(buffer2, 8192) != -1);
            return false;
        }
    }

    @Override // okio.BufferedSource
    public int select(Options options) throws IOException {
        Buffer buffer2;
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        do {
            int selectPrefix = this.buffer.selectPrefix(options);
            if (selectPrefix == -1) {
                break;
            }
            long size = (long) options.byteStrings[selectPrefix].size();
            buffer2 = this.buffer;
            if (size <= buffer2.size) {
                buffer2.skip(size);
                return selectPrefix;
            }
        } while (this.source.read(buffer2, 8192) != -1);
        return -1;
    }

    @Override // okio.BufferedSource
    public void skip(long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            Buffer buffer2 = this.buffer;
            if (buffer2.size == 0 && this.source.read(buffer2, 8192) == -1) {
                throw new EOFException();
            }
            Buffer buffer3 = this.buffer;
            long min = Math.min(j, buffer3.size);
            buffer3.skip(min);
            j -= min;
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.source);
        sb.append(")");
        return sb.toString();
    }

    public RealBufferedSource(Source source2) {
        if (source2 != null) {
            this.source = source2;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // okio.BufferedSource
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // okio.BufferedSource
    public void require(long j) throws IOException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) throws IOException {
        return indexOf(b, 0, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j) throws IOException {
        return indexOf(b, j, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j, long j2) throws IOException {
        long j3 = j;
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j3), Long.valueOf(j2)));
        } else {
            while (j3 < j2) {
                Buffer buffer2 = this.buffer;
                long indexOf = buffer2.indexOf(b, j3, j2);
                if (indexOf == -1) {
                    long j4 = buffer2.size;
                    if (j4 >= j2 || this.source.read(buffer2, 8192) == -1) {
                        break;
                    }
                    j3 = Math.max(j3, j4);
                } else {
                    return indexOf;
                }
            }
            return -1;
        }
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long indexOf = this.buffer.indexOf(byteString, j);
            if (indexOf != -1) {
                return indexOf;
            }
            Buffer buffer2 = this.buffer;
            long j2 = buffer2.size;
            if (this.source.read(buffer2, 8192) == -1) {
                return -1;
            }
            j = Math.max(j, (j2 - ((long) byteString.size())) + 1);
        }
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) throws IOException {
        return indexOfElement(byteString, 0);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long indexOfElement = this.buffer.indexOfElement(byteString, j);
            if (indexOfElement != -1) {
                return indexOfElement;
            }
            Buffer buffer2 = this.buffer;
            long j2 = buffer2.size;
            if (this.source.read(buffer2, 8192) == -1) {
                return -1;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString) throws IOException {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) throws IOException {
        if (!this.closed) {
            if (j >= 0 && i >= 0 && i2 >= 0 && byteString.size() - i >= i2) {
                for (int i3 = 0; i3 < i2; i3++) {
                    long j2 = ((long) i3) + j;
                    if (request(1 + j2) && this.buffer.getByte(j2) == byteString.getByte(i + i3)) {
                    }
                }
                return true;
            }
            return false;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Buffer buffer2 = this.buffer;
        if (buffer2.size == 0 && this.source.read(buffer2, 8192) == -1) {
            return -1;
        }
        return this.buffer.read(byteBuffer);
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = (long) i2;
        Util.checkOffsetAndCount((long) bArr.length, (long) i, j);
        Buffer buffer2 = this.buffer;
        if (buffer2.size == 0 && this.source.read(buffer2, 8192) == -1) {
            return -1;
        }
        Buffer buffer3 = this.buffer;
        return buffer3.read(bArr, i, (int) Math.min(j, buffer3.size));
    }

    @Override // okio.Source
    public long read(Buffer buffer2, long j) throws IOException {
        if (buffer2 == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
        } else if (!this.closed) {
            Buffer buffer3 = this.buffer;
            if (buffer3.size == 0 && this.source.read(buffer3, 8192) == -1) {
                return -1;
            }
            Buffer buffer4 = this.buffer;
            return buffer4.read(buffer2, Math.min(j, buffer4.size));
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j) throws IOException {
        require(j);
        return this.buffer.readByteArray(j);
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j) throws IOException {
        require(j);
        return this.buffer.readByteString(j);
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer2, long j) throws IOException {
        try {
            require(j);
            this.buffer.readFully(buffer2, j);
        } catch (EOFException e) {
            buffer2.writeAll(this.buffer);
            throw e;
        }
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws IOException {
        try {
            require((long) bArr.length);
            this.buffer.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                Buffer buffer2 = this.buffer;
                long j = buffer2.size;
                if (j > 0) {
                    int read = buffer2.read(bArr, i, (int) j);
                    if (read != -1) {
                        i += read;
                    } else {
                        throw new AssertionError();
                    }
                } else {
                    throw e;
                }
            }
        }
    }

    @Override // okio.BufferedSource
    public String readString(long j, Charset charset) throws IOException {
        require(j);
        if (charset != null) {
            return this.buffer.readString(j, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) throws IOException {
        if (charset != null) {
            this.buffer.writeAll(this.source);
            return this.buffer.readString(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.BufferedSource
    public String readUtf8() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j) throws IOException {
        require(j);
        return this.buffer.readUtf8(j);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws IOException {
        return readUtf8LineStrict(RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j) throws IOException {
        long j2;
        if (j >= 0) {
            if (j == RecyclerView.FOREVER_NS) {
                j2 = RecyclerView.FOREVER_NS;
            } else {
                j2 = j + 1;
            }
            long indexOf = indexOf((byte) 10, 0, j2);
            if (indexOf != -1) {
                return this.buffer.readUtf8Line(indexOf);
            }
            if (j2 < RecyclerView.FOREVER_NS && request(j2) && this.buffer.getByte(j2 - 1) == 13 && request(1 + j2) && this.buffer.getByte(j2) == 10) {
                return this.buffer.readUtf8Line(j2);
            }
            Buffer buffer2 = new Buffer();
            Buffer buffer3 = this.buffer;
            buffer3.copyTo(buffer2, 0, Math.min(32L, buffer3.size));
            StringBuilder sb = new StringBuilder("\\n not found: limit=");
            sb.append(Math.min(this.buffer.size, j));
            sb.append(" content=");
            sb.append(buffer2.readByteString().hex());
            sb.append((char) 8230);
            throw new EOFException(sb.toString());
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("limit < 0: ", j));
    }
}
