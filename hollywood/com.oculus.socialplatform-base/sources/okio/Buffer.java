package okio;

import X.AnonymousClass006;
import androidx.recyclerview.widget.RecyclerView;
import com.adobe.xmp.impl.Base64;
import com.facebook.acra.util.UrlEncodingWriter;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.secure.trustedapp.HashHelper;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Buffer implements BufferedSink, BufferedSource, Cloneable, ByteChannel {
    public static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    public Segment head;
    public long size;

    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public int end = -1;
        public long offset = -1;
        public boolean readWrite;
        public Segment segment;
        public int start = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer != null) {
                this.buffer = null;
                this.segment = null;
                this.offset = -1;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer");
        }

        public long expandBuffer(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException(AnonymousClass006.A03("minByteCount <= 0: ", i));
            } else if (i <= 8192) {
                Buffer buffer2 = this.buffer;
                if (buffer2 == null) {
                    throw new IllegalStateException("not attached to a buffer");
                } else if (this.readWrite) {
                    long j = buffer2.size;
                    Segment writableSegment = buffer2.writableSegment(i);
                    int i2 = 8192 - writableSegment.limit;
                    writableSegment.limit = 8192;
                    long j2 = (long) i2;
                    this.buffer.size = j + j2;
                    this.segment = writableSegment;
                    this.offset = j;
                    this.data = writableSegment.data;
                    this.start = 8192 - i2;
                    this.end = 8192;
                    return j2;
                } else {
                    throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
                }
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A03("minByteCount > Segment.SIZE: ", i));
            }
        }

        public int next() {
            long j;
            long j2 = this.offset;
            if (j2 != this.buffer.size) {
                if (j2 == -1) {
                    j = 0;
                } else {
                    j = j2 + ((long) (this.end - this.start));
                }
                return seek(j);
            }
            throw new IllegalStateException();
        }

        public long resizeBuffer(long j) {
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                throw new IllegalStateException("not attached to a buffer");
            } else if (this.readWrite) {
                long j2 = buffer2.size;
                if (j <= j2) {
                    if (j >= 0) {
                        long j3 = j2 - j;
                        while (true) {
                            if (j3 <= 0) {
                                break;
                            }
                            Buffer buffer3 = this.buffer;
                            Segment segment2 = buffer3.head.prev;
                            int i = segment2.limit;
                            long j4 = (long) (i - segment2.pos);
                            if (j4 > j3) {
                                segment2.limit = (int) (((long) i) - j3);
                                break;
                            }
                            buffer3.head = segment2.pop();
                            SegmentPool.recycle(segment2);
                            j3 -= j4;
                        }
                        this.segment = null;
                        this.offset = j;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                    } else {
                        throw new IllegalArgumentException(AnonymousClass006.A06("newSize < 0: ", j));
                    }
                } else if (j > j2) {
                    long j5 = j - j2;
                    boolean z = true;
                    while (j5 > 0) {
                        Segment writableSegment = this.buffer.writableSegment(1);
                        int i2 = writableSegment.limit;
                        int min = (int) Math.min(j5, (long) (8192 - i2));
                        int i3 = i2 + min;
                        writableSegment.limit = i3;
                        j5 -= (long) min;
                        if (z) {
                            this.segment = writableSegment;
                            this.offset = j2;
                            this.data = writableSegment.data;
                            this.start = i3 - min;
                            this.end = i3;
                            z = false;
                        }
                    }
                }
                this.buffer.size = j;
                return j2;
            } else {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
        }

        public int seek(long j) {
            if (j >= -1) {
                Buffer buffer2 = this.buffer;
                long j2 = buffer2.size;
                if (j <= j2) {
                    if (j == -1 || j == j2) {
                        this.segment = null;
                        this.offset = j;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                        return -1;
                    }
                    long j3 = 0;
                    Segment segment2 = buffer2.head;
                    Segment segment3 = segment2;
                    Segment segment4 = this.segment;
                    if (segment4 != null) {
                        long j4 = this.offset - ((long) (this.start - segment4.pos));
                        if (j4 > j) {
                            segment3 = segment4;
                            j2 = j4;
                        } else {
                            segment2 = segment4;
                            j3 = j4;
                        }
                    }
                    if (j2 - j > j - j3) {
                        while (true) {
                            long j5 = (long) (segment2.limit - segment2.pos);
                            if (j < j5 + j3) {
                                break;
                            }
                            j3 += j5;
                            segment2 = segment2.next;
                        }
                    } else {
                        j3 = j2;
                        segment2 = segment3;
                        while (j3 > j) {
                            segment2 = segment2.prev;
                            j3 -= (long) (segment2.limit - segment2.pos);
                        }
                    }
                    if (this.readWrite && segment2.shared) {
                        Segment unsharedCopy = segment2.unsharedCopy();
                        Buffer buffer3 = this.buffer;
                        if (buffer3.head == segment2) {
                            buffer3.head = unsharedCopy;
                        }
                        segment2.push(unsharedCopy);
                        segment2 = unsharedCopy;
                        unsharedCopy.prev.pop();
                    }
                    this.segment = segment2;
                    this.offset = j;
                    this.data = segment2.data;
                    int i = segment2.pos + ((int) (j - j3));
                    this.start = i;
                    int i2 = segment2.limit;
                    this.end = i2;
                    return i2 - i;
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j), Long.valueOf(this.buffer.size)));
        }
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    @Override // okio.Source, java.lang.AutoCloseable, okio.Sink, java.io.Closeable, java.nio.channels.Channel
    public void close() {
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.Sink, okio.BufferedSink, java.io.Flushable
    public void flush() {
    }

    public byte getByte(long j) {
        Segment segment;
        int i;
        long j2 = j;
        Util.checkOffsetAndCount(this.size, j2, 1);
        long j3 = this.size;
        if (j3 - j > j) {
            segment = this.head;
            while (true) {
                int i2 = segment.limit;
                i = segment.pos;
                long j4 = (long) (i2 - i);
                if (j2 < j4) {
                    break;
                }
                j2 -= j4;
                segment = segment.next;
            }
        } else {
            j2 = j - j3;
            segment = this.head;
            do {
                segment = segment.prev;
                int i3 = segment.limit;
                i = segment.pos;
                j2 += (long) (i3 - i);
            } while (j2 < 0);
        }
        return segment.data[i + ((int) j2)];
    }

    public boolean isOpen() {
        return true;
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        Segment segment = this.head;
        if (segment == null) {
            return options.indexOf(ByteString.EMPTY);
        }
        ByteString[] byteStringArr = options.byteStrings;
        int length = byteStringArr.length;
        for (int i = 0; i < length; i++) {
            ByteString byteString = byteStringArr[i];
            if (this.size >= ((long) byteString.size()) && rangeEquals(segment, segment.pos, byteString, 0, byteString.size())) {
                try {
                    skip((long) byteString.size());
                    return i;
                } catch (EOFException e) {
                    throw new AssertionError(e);
                }
            }
        }
        return -1;
    }

    public int selectPrefix(Options options) {
        Segment segment = this.head;
        ByteString[] byteStringArr = options.byteStrings;
        int length = byteStringArr.length;
        for (int i = 0; i < length; i++) {
            ByteString byteString = byteStringArr[i];
            int min = (int) Math.min(this.size, (long) byteString.size());
            if (min == 0 || rangeEquals(segment, segment.pos, byteString, 0, min)) {
                return i;
            }
        }
        return -1;
    }

    public Segment writableSegment(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.head;
        if (segment == null) {
            Segment take = SegmentPool.take();
            this.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        Segment segment2 = segment.prev;
        if (segment2.limit + i <= 8192 && segment2.owner) {
            return segment2;
        }
        Segment take2 = SegmentPool.take();
        segment2.push(take2);
        return take2;
    }

    public void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public long completeSegmentByteCount() {
        long j = this.size;
        if (j == 0) {
            return 0;
        }
        Segment segment = this.head.prev;
        int i = segment.limit;
        if (i >= 8192 || !segment.owner) {
            return j;
        }
        return j - ((long) (i - segment.pos));
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Buffer) {
                Buffer buffer = (Buffer) obj;
                long j = this.size;
                if (j == buffer.size) {
                    long j2 = 0;
                    if (j != 0) {
                        Segment segment = this.head;
                        Segment segment2 = buffer.head;
                        int i = segment.pos;
                        int i2 = segment2.pos;
                        while (j2 < j) {
                            long min = (long) Math.min(segment.limit - i, segment2.limit - i2);
                            int i3 = 0;
                            while (((long) i3) < min) {
                                int i4 = i + 1;
                                int i5 = i2 + 1;
                                if (segment.data[i] == segment2.data[i2]) {
                                    i3++;
                                    i = i4;
                                    i2 = i5;
                                }
                            }
                            if (i == segment.limit) {
                                segment = segment.next;
                                i = segment.pos;
                            }
                            if (i2 == segment2.limit) {
                                segment2 = segment2.next;
                                i2 = segment2.pos;
                            }
                            j2 += min;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
        } while (segment != segment);
        return i;
    }

    public ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() {
            /* class okio.Buffer.AnonymousClass2 */

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() {
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(Buffer.this);
                sb.append(".inputStream()");
                return sb.toString();
            }

            @Override // java.io.InputStream
            public int read() {
                Buffer buffer = Buffer.this;
                if (buffer.size > 0) {
                    return buffer.readByte() & Base64.INVALID;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                return Buffer.this.read(bArr, i, i2);
            }
        };
    }

    public ByteString md5() {
        return digest("MD5");
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() {
            /* class okio.Buffer.AnonymousClass1 */

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(Buffer.this);
                sb.append(".outputStream()");
                return sb.toString();
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                Buffer.this.writeByte((int) ((byte) i));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                Buffer.this.write(bArr, i, i2);
            }
        };
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        long j = this.size;
        if (j > 0) {
            sink.write(this, j);
        }
        return j;
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        long j = this.size;
        if (j != 0) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            int i3 = i + 1;
            byte b = segment.data[i];
            this.size = j - 1;
            if (i3 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
                return b;
            }
            segment.pos = i3;
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r16 != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        r0.readByte();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        throw new java.lang.NumberFormatException(X.AnonymousClass006.A07("Number too large: ", r0.readUtf8()));
     */
    @Override // okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r10 > 102) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r10 > 57) goto L_0x0022;
     */
    @Override // okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public int readInt() {
        long j = this.size;
        if (j >= 4) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 4) {
                return ((readByte() & Base64.INVALID) << 24) | ((readByte() & Base64.INVALID) << 16) | ((readByte() & Base64.INVALID) << 8) | (readByte() & Base64.INVALID);
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i] & Base64.INVALID) << 24) | ((bArr[i3] & Base64.INVALID) << 16) | ((bArr[i4] & Base64.INVALID) << 8) | (bArr[i5] & Base64.INVALID);
            this.size = j - 4;
            if (i6 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
                return i7;
            }
            segment.pos = i6;
            return i7;
        }
        throw new IllegalStateException(AnonymousClass006.A06("size < 4: ", j));
    }

    @Override // okio.BufferedSource
    public long readLong() {
        long j = this.size;
        if (j >= 8) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 8) {
                return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            long j2 = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i3]) & 255) << 48) | ((((long) bArr[i4]) & 255) << 40) | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 24) | ((((long) bArr[i7]) & 255) << 16);
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            long j3 = (((long) bArr[i9]) & 255) | ((((long) bArr[i8]) & 255) << 8) | j2;
            this.size = j - 8;
            if (i10 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
                return j3;
            }
            segment.pos = i10;
            return j3;
        }
        throw new IllegalStateException(AnonymousClass006.A06("size < 8: ", j));
    }

    @Override // okio.BufferedSource
    public short readShort() {
        long j = this.size;
        if (j >= 2) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 2) {
                return (short) (((readByte() & Base64.INVALID) << 8) | (readByte() & Base64.INVALID));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & Base64.INVALID) << 8) | (bArr[i3] & Base64.INVALID);
            this.size = j - 2;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return (short) i5;
        }
        throw new IllegalStateException(AnonymousClass006.A06("size < 2: ", j));
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        long j;
        int i;
        int i2;
        int i3;
        if (this.size != 0) {
            byte b = getByte(0);
            int i4 = 1;
            if ((b & 128) == 0) {
                i = b & Byte.MAX_VALUE;
                i2 = 1;
                i3 = 0;
            } else if ((b & 224) == 192) {
                i = b & 31;
                i2 = 2;
                i3 = 128;
            } else if ((b & 240) == 224) {
                i = b & 15;
                i2 = 3;
                i3 = 2048;
            } else if ((b & 248) == 240) {
                i = b & 7;
                i2 = 4;
                i3 = 65536;
            } else {
                j = 1;
                skip(j);
                return REPLACEMENT_CHARACTER;
            }
            long j2 = this.size;
            long j3 = (long) i2;
            if (j2 < j3) {
                StringBuilder sb = new StringBuilder("size < ");
                sb.append(i2);
                sb.append(": ");
                sb.append(j2);
                sb.append(" (to read code point prefixed 0x");
                sb.append(Integer.toHexString(b));
                sb.append(")");
                throw new EOFException(sb.toString());
            }
            while (true) {
                if (i4 < i2) {
                    j = (long) i4;
                    byte b2 = getByte(j);
                    if ((b2 & 192) != 128) {
                        break;
                    }
                    i = (i << 6) | (b2 & UrlEncodingWriter.UTF16_REPLACEMENT_BYTE);
                    i4++;
                } else {
                    skip(j3);
                    if (i > 1114111 || ((i >= 55296 && i <= 57343) || i < i3)) {
                        return REPLACEMENT_CHARACTER;
                    }
                    return i;
                }
            }
            skip(j);
            return REPLACEMENT_CHARACTER;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public boolean request(long j) {
        if (this.size >= j) {
            return true;
        }
        return false;
    }

    @Override // okio.BufferedSource
    public void require(long j) throws EOFException {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    public List<Integer> segmentSizes() {
        Segment segment = this.head;
        if (segment == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(segment.limit - segment.pos));
        Segment segment2 = segment;
        while (true) {
            segment2 = segment2.next;
            if (segment2 == segment) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(segment2.limit - segment2.pos));
        }
    }

    public ByteString sha1() {
        return digest(HashHelper.SHA1);
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    public ByteString sha512() {
        return digest("SHA-512");
    }

    @Override // okio.BufferedSource
    public void skip(long j) throws EOFException {
        while (j > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int i = segment.limit;
                int i2 = segment.pos;
                int min = (int) Math.min(j, (long) (i - i2));
                long j2 = (long) min;
                this.size -= j2;
                j -= j2;
                int i3 = i2 + min;
                segment.pos = i3;
                if (i3 == i) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source != null) {
            long j = 0;
            while (true) {
                long read = source.read(this, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    private ByteString digest(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i = segment.pos;
                instance.update(bArr, i, segment.limit - i);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    instance.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                }
            }
            return ByteString.of(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private ByteString hmac(String str, ByteString byteString) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(byteString.toByteArray(), str));
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i = segment.pos;
                instance.update(bArr, i, segment.limit - i);
                Segment segment2 = this.head;
                while (true) {
                    segment2 = segment2.next;
                    if (segment2 == this.head) {
                        break;
                    }
                    instance.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                }
            }
            return ByteString.of(instance.doFinal());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    public long size() {
        return this.size;
    }

    @Override // okio.Source, okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public String toString() {
        return snapshot().toString();
    }

    private boolean rangeEquals(Segment segment, int i, ByteString byteString, int i2, int i3) {
        int i4 = segment.limit;
        byte[] bArr = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                bArr = segment.data;
                i = segment.pos;
                i4 = segment.limit;
            }
            if (bArr[i] != byteString.getByte(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    private void readFrom(InputStream inputStream, long j, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j > 0 || z) {
                Segment writableSegment = writableSegment(1);
                int i = writableSegment.limit;
                int read = inputStream.read(writableSegment.data, i, (int) Math.min(j, (long) (8192 - i)));
                if (read != -1) {
                    writableSegment.limit += read;
                    long j2 = (long) read;
                    this.size += j2;
                    j -= j2;
                } else if (!z) {
                    throw new EOFException();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Override // java.lang.Object
    public Buffer clone() {
        Buffer buffer = new Buffer();
        long j = this.size;
        if (j != 0) {
            Segment sharedCopy = this.head.sharedCopy();
            buffer.head = sharedCopy;
            sharedCopy.prev = sharedCopy;
            sharedCopy.next = sharedCopy;
            Segment segment = this.head;
            while (true) {
                segment = segment.next;
                if (segment == segment) {
                    break;
                }
                sharedCopy.prev.push(segment.sharedCopy());
            }
            buffer.size = j;
        }
        return buffer;
    }

    public Buffer copyTo(OutputStream outputStream) throws IOException {
        copyTo(outputStream, 0, this.size);
        return this;
    }

    public Buffer copyTo(OutputStream outputStream, long j, long j2) throws IOException {
        long j3 = j2;
        long j4 = j;
        if (outputStream != null) {
            Util.checkOffsetAndCount(this.size, j4, j3);
            if (j2 != 0) {
                Segment segment = this.head;
                while (j4 >= ((long) (segment.limit - segment.pos))) {
                    j4 -= (long) (segment.limit - segment.pos);
                    segment = segment.next;
                }
                while (j3 > 0) {
                    int i = (int) (((long) segment.pos) + j4);
                    int min = (int) Math.min((long) (segment.limit - i), j3);
                    outputStream.write(segment.data, i, min);
                    j3 -= (long) min;
                    segment = segment.next;
                    j4 = 0;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    public Buffer copyTo(Buffer buffer, long j, long j2) {
        long j3 = j2;
        long j4 = j;
        if (buffer != null) {
            Util.checkOffsetAndCount(this.size, j4, j3);
            if (j2 != 0) {
                buffer.size += j2;
                Segment segment = this.head;
                while (j4 >= ((long) (segment.limit - segment.pos))) {
                    j4 -= (long) (segment.limit - segment.pos);
                    segment = segment.next;
                }
                while (j3 > 0) {
                    Segment sharedCopy = segment.sharedCopy();
                    int i = (int) (((long) sharedCopy.pos) + j4);
                    sharedCopy.pos = i;
                    sharedCopy.limit = Math.min(i + ((int) j3), sharedCopy.limit);
                    Segment segment2 = buffer.head;
                    if (segment2 == null) {
                        sharedCopy.prev = sharedCopy;
                        sharedCopy.next = sharedCopy;
                        buffer.head = sharedCopy;
                    } else {
                        segment2.prev.push(sharedCopy);
                    }
                    j3 -= (long) (sharedCopy.limit - sharedCopy.pos);
                    segment = segment.next;
                    j4 = 0;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j) {
        return indexOf(b, j, RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long j, long j2) {
        Segment segment;
        long j3 = j;
        long j4 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(j3), Long.valueOf(j2)));
        }
        long j5 = this.size;
        long j6 = j5;
        if (j2 <= j5) {
            j5 = j2;
        }
        if (!(j == j5 || (segment = this.head) == null)) {
            if (j6 - j >= j) {
                while (true) {
                    j6 = j4;
                    j4 = ((long) (segment.limit - segment.pos)) + j6;
                    if (j4 >= j) {
                        break;
                    }
                    segment = segment.next;
                }
            } else {
                while (j6 > j) {
                    segment = segment.prev;
                    j6 -= (long) (segment.limit - segment.pos);
                }
            }
            while (j6 < j5) {
                byte[] bArr = segment.data;
                int i = segment.limit;
                int i2 = segment.pos;
                long j7 = (long) i2;
                int min = (int) Math.min((long) i, (j7 + j5) - j6);
                for (int i3 = (int) ((j7 + j3) - j6); i3 < min; i3++) {
                    if (bArr[i3] == b) {
                        return ((long) (i3 - i2)) + j6;
                    }
                }
                j3 = ((long) (i - i2)) + j6;
                segment = segment.next;
                j6 = j3;
            }
        }
        return -1;
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString byteString, long j) throws IOException {
        long j2 = j;
        if (byteString.size() != 0) {
            long j3 = 0;
            if (j >= 0) {
                Segment segment = this.head;
                long j4 = -1;
                if (segment != null) {
                    long j5 = this.size;
                    if (j5 - j >= j) {
                        while (true) {
                            j5 = j3;
                            j3 = ((long) (segment.limit - segment.pos)) + j5;
                            if (j3 >= j) {
                                break;
                            }
                            segment = segment.next;
                        }
                    } else {
                        while (j5 > j) {
                            segment = segment.prev;
                            j5 -= (long) (segment.limit - segment.pos);
                        }
                    }
                    byte b = byteString.getByte(0);
                    int size2 = byteString.size();
                    long j6 = 1 + (this.size - ((long) size2));
                    while (j5 < j6) {
                        byte[] bArr = segment.data;
                        long j7 = (long) segment.limit;
                        long j8 = (long) segment.pos;
                        int min = (int) Math.min(j7, (j8 + j6) - j5);
                        for (int i = (int) ((j8 + j2) - j5); i < min; i++) {
                            if (bArr[i] == b && rangeEquals(segment, i + 1, byteString, 1, size2)) {
                                return ((long) (i - segment.pos)) + j5;
                            }
                        }
                        j2 = ((long) (segment.limit - segment.pos)) + j5;
                        segment = segment.next;
                        j4 = -1;
                        j5 = j2;
                    }
                }
                return j4;
            }
            throw new IllegalArgumentException("fromIndex < 0");
        }
        throw new IllegalArgumentException("bytes is empty");
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j) {
        int i;
        int i2;
        long j2 = j;
        long j3 = 0;
        if (j >= 0) {
            Segment segment = this.head;
            if (segment != null) {
                long j4 = this.size;
                if (j4 - j >= j) {
                    while (true) {
                        j4 = j3;
                        j3 = ((long) (segment.limit - segment.pos)) + j4;
                        if (j3 >= j) {
                            break;
                        }
                        segment = segment.next;
                    }
                } else {
                    while (j4 > j) {
                        segment = segment.prev;
                        j4 -= (long) (segment.limit - segment.pos);
                    }
                }
                if (byteString.size() == 2) {
                    byte b = byteString.getByte(0);
                    byte b2 = byteString.getByte(1);
                    while (j4 < this.size) {
                        byte[] bArr = segment.data;
                        i2 = segment.pos;
                        i = (int) ((((long) i2) + j2) - j4);
                        int i3 = segment.limit;
                        while (i < i3) {
                            byte b3 = bArr[i];
                            if (!(b3 == b || b3 == b2)) {
                                i++;
                            }
                        }
                        j2 = ((long) (i3 - i2)) + j4;
                        segment = segment.next;
                        j4 = j2;
                    }
                } else {
                    byte[] internalArray = byteString.internalArray();
                    while (j4 < this.size) {
                        byte[] bArr2 = segment.data;
                        i2 = segment.pos;
                        i = (int) ((((long) i2) + j2) - j4);
                        int i4 = segment.limit;
                        while (i < i4) {
                            byte b4 = bArr2[i];
                            for (byte b5 : internalArray) {
                                if (b4 != b5) {
                                }
                            }
                            i++;
                        }
                        j2 = ((long) (i4 - i2)) + j4;
                        segment = segment.next;
                        j4 = j2;
                    }
                }
                return ((long) (i - i2)) + j4;
            }
            return -1;
        }
        throw new IllegalArgumentException("fromIndex < 0");
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString) {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        if (j >= 0 && i >= 0 && i2 >= 0 && this.size - j >= ((long) i2) && byteString.size() - i >= i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (getByte(((long) i3) + j) == byteString.getByte(i + i3)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int remaining = byteBuffer.remaining();
        int i = segment.limit;
        int i2 = segment.pos;
        int min = Math.min(remaining, i - i2);
        byteBuffer.put(segment.data, i2, min);
        int i3 = segment.pos + min;
        segment.pos = i3;
        this.size -= (long) min;
        if (i3 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // okio.BufferedSource
    public int read(byte[] bArr, int i, int i2) {
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int i3 = segment.limit;
        int i4 = segment.pos;
        int min = Math.min(i2, i3 - i4);
        System.arraycopy(segment.data, i4, bArr, i, min);
        int i5 = segment.pos + min;
        segment.pos = i5;
        this.size -= (long) min;
        if (i5 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j >= 0) {
            long j2 = this.size;
            if (j2 == 0) {
                return -1;
            }
            if (j > j2) {
                j = j2;
            }
            buffer.write(this, j);
            return j;
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
        }
    }

    public UnsafeCursor readAndWriteUnsafe() {
        UnsafeCursor unsafeCursor = new UnsafeCursor();
        readAndWriteUnsafe(unsafeCursor);
        return unsafeCursor;
    }

    public UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = true;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer");
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[((int) j)];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("byteCount > Integer.MAX_VALUE: ", j));
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j) throws EOFException {
        return new ByteString(readByteArray(j));
    }

    public Buffer readFrom(InputStream inputStream) throws IOException {
        readFrom(inputStream, RecyclerView.FOREVER_NS, true);
        return this;
    }

    public Buffer readFrom(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            readFrom(inputStream, j, false);
            return this;
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j) throws EOFException {
        long j2 = this.size;
        if (j2 >= j) {
            buffer.write(this, j);
        } else {
            buffer.write(this, j2);
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (true) {
            int length = bArr.length;
            if (i < length) {
                int read = read(bArr, i, length - i);
                if (read != -1) {
                    i += read;
                } else {
                    throw new EOFException();
                }
            } else {
                return;
            }
        }
    }

    @Override // okio.BufferedSource
    public String readString(long j, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount > Integer.MAX_VALUE: ", j));
        } else if (j == 0) {
            return "";
        } else {
            Segment segment = this.head;
            int i = segment.pos;
            if (((long) i) + j > ((long) segment.limit)) {
                return new String(readByteArray(j), charset);
            }
            String str = new String(segment.data, i, (int) j, charset);
            int i2 = (int) (((long) segment.pos) + j);
            segment.pos = i2;
            this.size -= j;
            if (i2 == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return str;
        }
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public UnsafeCursor readUnsafe() {
        UnsafeCursor unsafeCursor = new UnsafeCursor();
        readUnsafe(unsafeCursor);
        return unsafeCursor;
    }

    public UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = false;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer");
    }

    @Override // okio.BufferedSource
    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public String readUtf8(long j) throws EOFException {
        return readString(j, Util.UTF_8);
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(indexOf);
        }
        long j = this.size;
        if (j != 0) {
            return readUtf8(j);
        }
        return null;
    }

    public String readUtf8Line(long j) throws EOFException {
        String readUtf8;
        long j2 = 1;
        if (j > 0) {
            long j3 = j - 1;
            if (getByte(j3) == 13) {
                readUtf8 = readUtf8(j3);
                j2 = 2;
                skip(j2);
                return readUtf8;
            }
        }
        readUtf8 = readUtf8(j);
        skip(j2);
        return readUtf8;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(RecyclerView.FOREVER_NS);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j) throws EOFException {
        if (j >= 0) {
            long j2 = RecyclerView.FOREVER_NS;
            if (j != RecyclerView.FOREVER_NS) {
                j2 = j + 1;
            }
            long indexOf = indexOf((byte) 10, 0, j2);
            if (indexOf != -1) {
                return readUtf8Line(indexOf);
            }
            if (j2 < this.size && getByte(j2 - 1) == 13 && getByte(j2) == 10) {
                return readUtf8Line(j2);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0, Math.min(32L, this.size));
            StringBuilder sb = new StringBuilder("\\n not found: limit=");
            sb.append(Math.min(this.size, j));
            sb.append(" content=");
            sb.append(buffer.readByteString().hex());
            sb.append((char) 8230);
            throw new EOFException(sb.toString());
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("limit < 0: ", j));
    }

    public ByteString snapshot() {
        long j = this.size;
        if (j <= 2147483647L) {
            return snapshot((int) j);
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("size > Integer.MAX_VALUE: ", j));
    }

    public ByteString snapshot(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i);
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i = remaining;
            while (i > 0) {
                Segment writableSegment = writableSegment(1);
                int i2 = writableSegment.limit;
                int min = Math.min(i, 8192 - i2);
                byteBuffer.get(writableSegment.data, i2, min);
                i -= min;
                writableSegment.limit += min;
            }
            this.size += (long) remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString != null) {
            byteString.write(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = (long) i2;
            Util.checkOffsetAndCount((long) bArr.length, (long) i, j);
            int i3 = i2 + i;
            while (i < i3) {
                Segment writableSegment = writableSegment(1);
                int i4 = writableSegment.limit;
                int min = Math.min(i3 - i, 8192 - i4);
                System.arraycopy(bArr, i, writableSegment.data, i4, min);
                i += min;
                writableSegment.limit += min;
            }
            this.size += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long j) throws IOException {
        while (j > 0) {
            long read = source.read(this, j);
            if (read != -1) {
                j -= read;
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) {
        Segment segment;
        int i;
        long j2 = j;
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        } else if (buffer != this) {
            Util.checkOffsetAndCount(buffer.size, 0, j2);
            while (j2 > 0) {
                Segment segment2 = buffer.head;
                Segment segment3 = segment2;
                if (j2 < ((long) (segment2.limit - segment2.pos))) {
                    Segment segment4 = this.head;
                    if (!(segment4 == null || (segment = segment4.prev) == null || !segment.owner)) {
                        long j3 = ((long) segment.limit) + j2;
                        if (segment.shared) {
                            i = 0;
                        } else {
                            i = segment.pos;
                        }
                        if (j3 - ((long) i) <= 8192) {
                            segment2.writeTo(segment, (int) j2);
                            buffer.size -= j2;
                            this.size += j2;
                            return;
                        }
                    }
                    segment3 = segment2.split((int) j2);
                    buffer.head = segment3;
                }
                long j4 = (long) (segment3.limit - segment3.pos);
                buffer.head = segment3.pop();
                Segment segment5 = this.head;
                if (segment5 == null) {
                    this.head = segment3;
                    segment3.prev = segment3;
                    segment3.next = segment3;
                } else {
                    segment5.prev.push(segment3);
                    segment3.compact();
                }
                buffer.size -= j4;
                this.size += j4;
                j2 -= j4;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int i) {
        Segment writableSegment = writableSegment(1);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        writableSegment.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                writeUtf8("-9223372036854775808");
                return this;
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < 10000) {
                if (j >= 100) {
                    i = 4;
                    if (j < 1000) {
                        i = 3;
                    }
                } else if (j >= 10) {
                    i = 2;
                }
            } else if (j < 1000000) {
                i = 6;
                if (j < 100000) {
                    i = 5;
                }
            } else {
                i = 8;
                if (j < 10000000) {
                    i = 7;
                }
            }
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i = 10;
                if (j < 1000000000) {
                    i = 9;
                }
            } else {
                i = 12;
                if (j < 100000000000L) {
                    i = 11;
                }
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i = 13;
            } else {
                i = 15;
                if (j < 100000000000000L) {
                    i = 14;
                }
            }
        } else if (j < 100000000000000000L) {
            i = 17;
            if (j < 10000000000000000L) {
                i = 16;
            }
        } else {
            i = 19;
            if (j < 1000000000000000000L) {
                i = 18;
            }
        }
        if (z) {
            i++;
        }
        Segment writableSegment = writableSegment(i);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit + i;
        while (j != 0) {
            i2--;
            bArr[i2] = DIGITS[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        writableSegment.limit = i2;
        this.size += (long) i;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment writableSegment = writableSegment(numberOfTrailingZeros);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        int i2 = i + numberOfTrailingZeros;
        while (true) {
            i2--;
            if (i2 >= i) {
                bArr[i2] = DIGITS[(int) (15 & j)];
                j >>>= 4;
            } else {
                writableSegment.limit = i2;
                this.size += (long) numberOfTrailingZeros;
                return this;
            }
        }
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i) {
        Segment writableSegment = writableSegment(4);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & MediaProviderUtils.JPEG_HEADER);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & MediaProviderUtils.JPEG_HEADER);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & MediaProviderUtils.JPEG_HEADER);
        bArr[i5] = (byte) (i & MediaProviderUtils.JPEG_HEADER);
        writableSegment.limit = i5 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeIntLe(int i) {
        writeInt(Util.reverseBytesInt(i));
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLong(long j) {
        Segment writableSegment = writableSegment(8);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) ((j >>> 40) & 255));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) ((j >>> 32) & 255));
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) ((j >>> 24) & 255));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) ((j >>> 16) & 255));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i8] = (byte) ((int) (j & 255));
        writableSegment.limit = i8 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLongLe(long j) {
        writeLong(Util.reverseBytesLong(j));
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int i) {
        Segment writableSegment = writableSegment(2);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & MediaProviderUtils.JPEG_HEADER);
        bArr[i3] = (byte) (i & MediaProviderUtils.JPEG_HEADER);
        writableSegment.limit = i3 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShortLe(int i) {
        writeShort((int) Util.reverseBytesShort((short) i));
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError(AnonymousClass006.A03("beginIndex < 0: ", i));
        } else if (i2 >= i) {
            int length = str.length();
            if (i2 > length) {
                throw new IllegalArgumentException(AnonymousClass006.A05("endIndex > string.length: ", i2, " > ", length));
            } else if (charset == null) {
                throw new IllegalArgumentException("charset == null");
            } else if (charset.equals(Util.UTF_8)) {
                writeUtf8(str, i, i2);
                return this;
            } else {
                byte[] bytes = str.substring(i, i2).getBytes(charset);
                write(bytes, 0, bytes.length);
                return this;
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A05("endIndex < beginIndex: ", i2, " < ", i));
        }
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String str, Charset charset) {
        writeString(str, 0, str.length(), charset);
        return this;
    }

    public Buffer writeTo(OutputStream outputStream) throws IOException {
        writeTo(outputStream, this.size);
        return this;
    }

    public Buffer writeTo(OutputStream outputStream, long j) throws IOException {
        long j2 = j;
        if (outputStream != null) {
            Util.checkOffsetAndCount(this.size, 0, j2);
            Segment segment = this.head;
            while (j2 > 0) {
                int i = segment.limit;
                int i2 = segment.pos;
                int min = (int) Math.min(j2, (long) (i - i2));
                outputStream.write(segment.data, i2, min);
                int i3 = segment.pos + min;
                segment.pos = i3;
                long j3 = (long) min;
                this.size -= j3;
                j2 -= j3;
                if (i3 == segment.limit) {
                    Segment pop = segment.pop();
                    this.head = pop;
                    SegmentPool.recycle(segment);
                    segment = pop;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str) {
        writeUtf8(str, 0, str.length());
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str, int i, int i2) {
        char c;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A03("beginIndex < 0: ", i));
        } else if (i2 >= i) {
            int length = str.length();
            if (i2 > length) {
                throw new IllegalArgumentException(AnonymousClass006.A05("endIndex > string.length: ", i2, " > ", length));
            }
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    Segment writableSegment = writableSegment(1);
                    byte[] bArr = writableSegment.data;
                    int i3 = writableSegment.limit - i;
                    int min = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                    while (i4 < min) {
                        char charAt2 = str.charAt(i4);
                        if (charAt2 >= 128) {
                            break;
                        }
                        bArr[i4 + i3] = (byte) charAt2;
                        i4++;
                    }
                    int i5 = writableSegment.limit;
                    int i6 = (i3 + i4) - i5;
                    writableSegment.limit = i5 + i6;
                    this.size += (long) i6;
                    i = i4;
                } else {
                    int i7 = (charAt >> 6) | 192;
                    if (charAt >= 2048) {
                        if (charAt < 55296 || charAt > 57343) {
                            writeByte((charAt >> '\f') | 224);
                            i7 = ((charAt >> 6) & 63) | 128;
                        } else {
                            int i8 = i + 1;
                            if (i8 < i2) {
                                c = str.charAt(i8);
                            } else {
                                c = 0;
                            }
                            if (charAt > 56319 || c < 56320 || c > 57343) {
                                writeByte(63);
                                i = i8;
                            } else {
                                int i9 = (((charAt & 10239) << 10) | (9215 & c)) + 65536;
                                writeByte((i9 >> 18) | 240);
                                writeByte(((i9 >> 12) & 63) | 128);
                                writeByte(((i9 >> 6) & 63) | 128);
                                writeByte((i9 & 63) | 128);
                                i += 2;
                            }
                        }
                    }
                    writeByte(i7);
                    writeByte((charAt & '?') | 128);
                    i++;
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A05("endIndex < beginIndex: ", i2, " < ", i));
        }
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int i) {
        int i2;
        if (i >= 128) {
            int i3 = i >> 6;
            int i4 = i3 | 192;
            if (i >= 2048) {
                if (i < 65536) {
                    if (i < 55296 || i > 57343) {
                        i2 = (i >> 12) | 224;
                    } else {
                        writeByte(63);
                        return this;
                    }
                } else if (i <= 1114111) {
                    writeByte((i >> 18) | 240);
                    i2 = ((i >> 12) & 63) | 128;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A07("Unexpected code point: ", Integer.toHexString(i)));
                }
                writeByte(i2);
                i4 = (i3 & 63) | 128;
            }
            writeByte(i4);
            i = (i & 63) | 128;
        }
        writeByte(i);
        return this;
    }
}
