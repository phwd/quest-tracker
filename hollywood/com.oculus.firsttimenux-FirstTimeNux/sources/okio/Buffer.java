package okio;

import com.facebook.acra.ErrorReporter;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.common.procread.ProcReader;
import com.facebook.common.time.Clock;
import com.facebook.common.time.TimeConstants;
import com.facebook.systrace.Systrace;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
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

public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    Segment head;
    long size;

    public final long size() {
        return this.size;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    @Override // okio.BufferedSource
    public Buffer getBuffer() {
        return this;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() {
            /* class okio.Buffer.AnonymousClass1 */

            @Override // java.io.OutputStream
            public void write(int b) {
                Buffer.this.writeByte((int) ((byte) b));
            }

            @Override // java.io.OutputStream
            public void write(byte[] data, int offset, int byteCount) {
                Buffer.this.write(data, offset, byteCount);
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSource
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() {
            /* class okio.Buffer.AnonymousClass2 */

            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.size > 0) {
                    return Buffer.this.readByte() & UnsignedBytes.MAX_VALUE;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] sink, int offset, int byteCount) {
                return Buffer.this.read(sink, offset, byteCount);
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() {
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public final Buffer copyTo(OutputStream out) throws IOException {
        return copyTo(out, 0, this.size);
    }

    public final Buffer copyTo(OutputStream out, long offset, long byteCount) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, offset, byteCount);
        if (byteCount != 0) {
            Segment s = this.head;
            while (offset >= ((long) (s.limit - s.pos))) {
                offset -= (long) (s.limit - s.pos);
                s = s.next;
            }
            while (byteCount > 0) {
                int pos = (int) (((long) s.pos) + offset);
                int toCopy = (int) Math.min((long) (s.limit - pos), byteCount);
                out.write(s.data, pos, toCopy);
                byteCount -= (long) toCopy;
                offset = 0;
                s = s.next;
            }
        }
        return this;
    }

    public final Buffer copyTo(Buffer out, long offset, long byteCount) {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, offset, byteCount);
        if (byteCount != 0) {
            out.size += byteCount;
            Segment s = this.head;
            while (offset >= ((long) (s.limit - s.pos))) {
                offset -= (long) (s.limit - s.pos);
                s = s.next;
            }
            while (byteCount > 0) {
                Segment copy = s.sharedCopy();
                copy.pos = (int) (((long) copy.pos) + offset);
                copy.limit = Math.min(copy.pos + ((int) byteCount), copy.limit);
                if (out.head == null) {
                    copy.prev = copy;
                    copy.next = copy;
                    out.head = copy;
                } else {
                    out.head.prev.push(copy);
                }
                byteCount -= (long) (copy.limit - copy.pos);
                offset = 0;
                s = s.next;
            }
        }
        return this;
    }

    public final Buffer writeTo(OutputStream out) throws IOException {
        return writeTo(out, this.size);
    }

    public final Buffer writeTo(OutputStream out, long byteCount) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0, byteCount);
        Segment s = this.head;
        while (byteCount > 0) {
            int toCopy = (int) Math.min(byteCount, (long) (s.limit - s.pos));
            out.write(s.data, s.pos, toCopy);
            s.pos += toCopy;
            this.size -= (long) toCopy;
            byteCount -= (long) toCopy;
            if (s.pos == s.limit) {
                s = s.pop();
                this.head = s;
                SegmentPool.recycle(s);
            }
        }
        return this;
    }

    public final Buffer readFrom(InputStream in) throws IOException {
        readFrom(in, Clock.MAX_TIME, true);
        return this;
    }

    public final Buffer readFrom(InputStream in, long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        readFrom(in, byteCount, false);
        return this;
    }

    private void readFrom(InputStream in, long byteCount, boolean forever) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (byteCount > 0 || forever) {
                Segment tail = writableSegment(1);
                int bytesRead = in.read(tail.data, tail.limit, (int) Math.min(byteCount, (long) (8192 - tail.limit)));
                if (bytesRead != -1) {
                    tail.limit += bytesRead;
                    this.size += (long) bytesRead;
                    byteCount -= (long) bytesRead;
                } else if (!forever) {
                    throw new EOFException();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final long completeSegmentByteCount() {
        long result = this.size;
        if (result == 0) {
            return 0;
        }
        Segment tail = this.head.prev;
        if (tail.limit < 8192 && tail.owner) {
            result -= (long) (tail.limit - tail.pos);
        }
        return result;
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        if (this.size == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        int pos2 = pos + 1;
        byte b = segment.data[pos];
        this.size--;
        if (pos2 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos2;
        }
        return b;
    }

    public final byte getByte(long pos) {
        Util.checkOffsetAndCount(this.size, pos, 1);
        if (this.size - pos > pos) {
            Segment s = this.head;
            while (true) {
                int segmentByteCount = s.limit - s.pos;
                if (pos < ((long) segmentByteCount)) {
                    return s.data[s.pos + ((int) pos)];
                }
                pos -= (long) segmentByteCount;
                s = s.next;
            }
        } else {
            long pos2 = pos - this.size;
            Segment s2 = this.head.prev;
            while (true) {
                pos2 += (long) (s2.limit - s2.pos);
                if (pos2 >= 0) {
                    return s2.data[s2.pos + ((int) pos2)];
                }
                s2 = s2.prev;
            }
        }
    }

    @Override // okio.BufferedSource
    public short readShort() {
        if (this.size < 2) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 2) {
            return (short) (((readByte() & UnsignedBytes.MAX_VALUE) << 8) | (readByte() & UnsignedBytes.MAX_VALUE));
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        int pos3 = pos2 + 1;
        int s = ((data[pos] & UnsignedBytes.MAX_VALUE) << 8) | (data[pos2] & UnsignedBytes.MAX_VALUE);
        this.size -= 2;
        if (pos3 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos3;
        }
        return (short) s;
    }

    @Override // okio.BufferedSource
    public int readInt() {
        if (this.size < 4) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 4) {
            return ((readByte() & UnsignedBytes.MAX_VALUE) << 24) | ((readByte() & UnsignedBytes.MAX_VALUE) << 16) | ((readByte() & UnsignedBytes.MAX_VALUE) << 8) | (readByte() & UnsignedBytes.MAX_VALUE);
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        int pos3 = pos2 + 1;
        int pos4 = pos3 + 1;
        int pos5 = pos4 + 1;
        int i = ((data[pos] & UnsignedBytes.MAX_VALUE) << 24) | ((data[pos2] & UnsignedBytes.MAX_VALUE) << 16) | ((data[pos3] & UnsignedBytes.MAX_VALUE) << 8) | (data[pos4] & UnsignedBytes.MAX_VALUE);
        this.size -= 4;
        if (pos5 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
            return i;
        }
        segment.pos = pos5;
        return i;
    }

    @Override // okio.BufferedSource
    public long readLong() {
        if (this.size < 8) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        Segment segment = this.head;
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (((long) readInt()) & 4294967295L);
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        int pos3 = pos2 + 1;
        int pos4 = pos3 + 1;
        int pos5 = pos4 + 1;
        int pos6 = pos5 + 1;
        int pos7 = pos6 + 1;
        int pos8 = pos7 + 1;
        int pos9 = pos8 + 1;
        long j = ((((long) data[pos]) & 255) << 56) | ((((long) data[pos2]) & 255) << 48) | ((((long) data[pos3]) & 255) << 40) | ((((long) data[pos4]) & 255) << 32) | ((((long) data[pos5]) & 255) << 24) | ((((long) data[pos6]) & 255) << 16) | ((((long) data[pos7]) & 255) << 8) | (((long) data[pos8]) & 255);
        this.size -= 8;
        if (pos9 == limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
            return j;
        }
        segment.pos = pos9;
        return j;
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        if (r10 != false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        r5.readByte();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0088, code lost:
        throw new java.lang.NumberFormatException("Number too large: " + r5.readUtf8());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c5, code lost:
        if (r11 != r9) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c7, code lost:
        r24.head = r0.pop();
        okio.SegmentPool.recycle(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d4, code lost:
        if (r8 != false) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f4, code lost:
        r0.pos = r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a7 A[SYNTHETIC] */
    @Override // okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009b, code lost:
        if (r8 != r7) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009d, code lost:
        r18.head = r10.pop();
        okio.SegmentPool.recycle(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a8, code lost:
        if (r6 != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c8, code lost:
        r10.pos = r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007d A[SYNTHETIC] */
    @Override // okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() {
        /*
        // Method dump skipped, instructions count: 203
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long byteCount) throws EOFException {
        return new ByteString(readByteArray(byteCount));
    }

    @Override // okio.BufferedSource
    public int select(Options options) {
        int index = selectPrefix(options, false);
        if (index == -1) {
            return -1;
        }
        try {
            skip((long) options.byteStrings[index].size());
            return index;
        } catch (EOFException e) {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public int selectPrefix(Options options, boolean selectTruncated) {
        int nextStep;
        Segment head2 = this.head;
        if (head2 != null) {
            Segment s = head2;
            byte[] data = head2.data;
            int pos = head2.pos;
            int limit = head2.limit;
            int[] trie = options.trie;
            int triePos = 0;
            int prefixIndex = -1;
            loop0:
            while (true) {
                int triePos2 = triePos + 1;
                int scanOrSelect = trie[triePos];
                int triePos3 = triePos2 + 1;
                int possiblePrefixIndex = trie[triePos2];
                if (possiblePrefixIndex != -1) {
                    prefixIndex = possiblePrefixIndex;
                }
                if (s == null) {
                    break;
                }
                if (scanOrSelect < 0) {
                    int trieLimit = triePos3 + (scanOrSelect * -1);
                    while (true) {
                        int pos2 = pos + 1;
                        int triePos4 = triePos3 + 1;
                        if ((data[pos] & UnsignedBytes.MAX_VALUE) != trie[triePos3]) {
                            return prefixIndex;
                        }
                        boolean scanComplete = triePos4 == trieLimit;
                        if (pos2 == limit) {
                            s = s.next;
                            pos = s.pos;
                            data = s.data;
                            limit = s.limit;
                            if (s == head2) {
                                if (!scanComplete) {
                                    break loop0;
                                }
                                s = null;
                            }
                        } else {
                            pos = pos2;
                        }
                        if (scanComplete) {
                            nextStep = trie[triePos4];
                            break;
                        }
                        triePos3 = triePos4;
                    }
                } else {
                    int pos3 = pos + 1;
                    int b = data[pos] & UnsignedBytes.MAX_VALUE;
                    int selectLimit = triePos3 + scanOrSelect;
                    while (triePos3 != selectLimit) {
                        if (b == trie[triePos3]) {
                            nextStep = trie[triePos3 + scanOrSelect];
                            if (pos3 == limit) {
                                s = s.next;
                                pos = s.pos;
                                data = s.data;
                                limit = s.limit;
                                if (s == head2) {
                                    s = null;
                                }
                            } else {
                                pos = pos3;
                            }
                        } else {
                            triePos3++;
                        }
                    }
                    return prefixIndex;
                }
                if (nextStep >= 0) {
                    return nextStep;
                }
                triePos = -nextStep;
            }
            if (selectTruncated) {
                return -2;
            }
            return prefixIndex;
        } else if (selectTruncated) {
            return -2;
        } else {
            return options.indexOf(ByteString.EMPTY);
        }
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer sink, long byteCount) throws EOFException {
        if (this.size < byteCount) {
            sink.write(this, this.size);
            throw new EOFException();
        } else {
            sink.write(this, byteCount);
        }
    }

    @Override // okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        long byteCount = this.size;
        if (byteCount > 0) {
            sink.write(this, byteCount);
        }
        return byteCount;
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
    public String readUtf8(long byteCount) throws EOFException {
        return readString(byteCount, Util.UTF_8);
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public String readString(long byteCount, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, byteCount);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (byteCount > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        } else if (byteCount == 0) {
            return "";
        } else {
            Segment s = this.head;
            if (((long) s.pos) + byteCount > ((long) s.limit)) {
                return new String(readByteArray(byteCount), charset);
            }
            String str = new String(s.data, s.pos, (int) byteCount, charset);
            s.pos = (int) (((long) s.pos) + byteCount);
            this.size -= byteCount;
            if (s.pos != s.limit) {
                return str;
            }
            this.head = s.pop();
            SegmentPool.recycle(s);
            return str;
        }
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws EOFException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return readUtf8Line(newline);
        }
        if (this.size != 0) {
            return readUtf8(this.size);
        }
        return null;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Clock.MAX_TIME);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long limit) throws EOFException {
        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0: " + limit);
        }
        long scanLength = limit == Clock.MAX_TIME ? Clock.MAX_TIME : limit + 1;
        long newline = indexOf((byte) 10, 0, scanLength);
        if (newline != -1) {
            return readUtf8Line(newline);
        }
        if (scanLength < size() && getByte(scanLength - 1) == 13 && getByte(scanLength) == 10) {
            return readUtf8Line(scanLength);
        }
        Buffer data = new Buffer();
        copyTo(data, 0, Math.min(32L, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), limit) + " content=" + data.readByteString().hex() + (char) 8230);
    }

    /* access modifiers changed from: package-private */
    public String readUtf8Line(long newline) throws EOFException {
        if (newline <= 0 || getByte(newline - 1) != 13) {
            String result = readUtf8(newline);
            skip(1);
            return result;
        }
        String result2 = readUtf8(newline - 1);
        skip(2);
        return result2;
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int codePoint;
        int byteCount;
        int min;
        if (this.size == 0) {
            throw new EOFException();
        }
        byte b0 = getByte(0);
        if ((b0 & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
            codePoint = b0 & Ascii.DEL;
            byteCount = 1;
            min = 0;
        } else if ((b0 & 224) == 192) {
            codePoint = b0 & Ascii.US;
            byteCount = 2;
            min = 128;
        } else if ((b0 & 240) == 224) {
            codePoint = b0 & Ascii.SI;
            byteCount = 3;
            min = ProcReader.PROC_CHAR;
        } else if ((b0 & 248) == 240) {
            codePoint = b0 & 7;
            byteCount = 4;
            min = ErrorReporter.DEFAULT_OOM_RESERVATION;
        } else {
            skip(1);
            return REPLACEMENT_CHARACTER;
        }
        if (this.size < ((long) byteCount)) {
            throw new EOFException("size < " + byteCount + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b0) + ")");
        }
        for (int i = 1; i < byteCount; i++) {
            byte b = getByte((long) i);
            if ((b & 192) == 128) {
                codePoint = (codePoint << 6) | (b & 63);
            } else {
                skip((long) i);
                return REPLACEMENT_CHARACTER;
            }
        }
        skip((long) byteCount);
        if (codePoint > 1114111) {
            return REPLACEMENT_CHARACTER;
        }
        if (codePoint >= 55296 && codePoint <= 57343) {
            return REPLACEMENT_CHARACTER;
        }
        if (codePoint < min) {
            return REPLACEMENT_CHARACTER;
        }
        return codePoint;
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
    public byte[] readByteArray(long byteCount) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, byteCount);
        if (byteCount > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        }
        byte[] result = new byte[((int) byteCount)];
        readFully(result);
        return result;
    }

    @Override // okio.BufferedSource
    public int read(byte[] sink) {
        return read(sink, 0, sink.length);
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] sink) throws EOFException {
        int offset = 0;
        while (offset < sink.length) {
            int read = read(sink, offset, sink.length - offset);
            if (read == -1) {
                throw new EOFException();
            }
            offset += read;
        }
    }

    @Override // okio.BufferedSource
    public int read(byte[] sink, int offset, int byteCount) {
        Util.checkOffsetAndCount((long) sink.length, (long) offset, (long) byteCount);
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(byteCount, s.limit - s.pos);
        System.arraycopy(s.data, s.pos, sink, offset, toCopy);
        s.pos += toCopy;
        this.size -= (long) toCopy;
        if (s.pos != s.limit) {
            return toCopy;
        }
        this.head = s.pop();
        SegmentPool.recycle(s);
        return toCopy;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) throws IOException {
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(sink.remaining(), s.limit - s.pos);
        sink.put(s.data, s.pos, toCopy);
        s.pos += toCopy;
        this.size -= (long) toCopy;
        if (s.pos != s.limit) {
            return toCopy;
        }
        this.head = s.pop();
        SegmentPool.recycle(s);
        return toCopy;
    }

    public final void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.BufferedSource
    public void skip(long byteCount) throws EOFException {
        while (byteCount > 0) {
            if (this.head == null) {
                throw new EOFException();
            }
            int toSkip = (int) Math.min(byteCount, (long) (this.head.limit - this.head.pos));
            this.size -= (long) toSkip;
            byteCount -= (long) toSkip;
            this.head.pos += toSkip;
            if (this.head.pos == this.head.limit) {
                Segment toRecycle = this.head;
                this.head = toRecycle.pop();
                SegmentPool.recycle(toRecycle);
            }
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String string) {
        return writeUtf8(string, 0, string.length());
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String string, int beginIndex, int endIndex) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        } else if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + beginIndex);
        } else if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        } else if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        } else {
            int i = beginIndex;
            while (i < endIndex) {
                int c = string.charAt(i);
                if (c < 128) {
                    Segment tail = writableSegment(1);
                    byte[] data = tail.data;
                    int segmentOffset = tail.limit - i;
                    int runLimit = Math.min(endIndex, 8192 - segmentOffset);
                    data[segmentOffset + i] = (byte) c;
                    int i2 = i + 1;
                    while (i2 < runLimit) {
                        int c2 = string.charAt(i2);
                        if (c2 >= 128) {
                            break;
                        }
                        data[segmentOffset + i2] = (byte) c2;
                        i2++;
                    }
                    int runSize = (i2 + segmentOffset) - tail.limit;
                    tail.limit += runSize;
                    this.size += (long) runSize;
                    i = i2;
                } else if (c < 2048) {
                    writeByte((c >> 6) | 192);
                    writeByte((c & 63) | 128);
                    i++;
                } else if (c < 55296 || c > 57343) {
                    writeByte((c >> 12) | 224);
                    writeByte(((c >> 6) & 63) | 128);
                    writeByte((c & 63) | 128);
                    i++;
                } else {
                    int low = i + 1 < endIndex ? string.charAt(i + 1) : 0;
                    if (c > 56319 || low < 56320 || low > 57343) {
                        writeByte(63);
                        i++;
                    } else {
                        int codePoint = ErrorReporter.DEFAULT_OOM_RESERVATION + (((-55297 & c) << 10) | (-56321 & low));
                        writeByte((codePoint >> 18) | 240);
                        writeByte(((codePoint >> 12) & 63) | 128);
                        writeByte(((codePoint >> 6) & 63) | 128);
                        writeByte((codePoint & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        }
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            writeByte(codePoint);
        } else if (codePoint < 2048) {
            writeByte((codePoint >> 6) | 192);
            writeByte((codePoint & 63) | 128);
        } else if (codePoint < 65536) {
            if (codePoint < 55296 || codePoint > 57343) {
                writeByte((codePoint >> 12) | 224);
                writeByte(((codePoint >> 6) & 63) | 128);
                writeByte((codePoint & 63) | 128);
            } else {
                writeByte(63);
            }
        } else if (codePoint <= 1114111) {
            writeByte((codePoint >> 18) | 240);
            writeByte(((codePoint >> 12) & 63) | 128);
            writeByte(((codePoint >> 6) & 63) | 128);
            writeByte((codePoint & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(codePoint));
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String string, Charset charset) {
        return writeString(string, 0, string.length(), charset);
    }

    @Override // okio.BufferedSink
    public Buffer writeString(String string, int beginIndex, int endIndex, Charset charset) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        } else if (beginIndex < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + beginIndex);
        } else if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        } else if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(Util.UTF_8)) {
            return writeUtf8(string, beginIndex, endIndex);
        } else {
            byte[] data = string.substring(beginIndex, endIndex).getBytes(charset);
            return write(data, 0, data.length);
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] source) {
        if (source != null) {
            return write(source, 0, source.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] source, int offset, int byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        Util.checkOffsetAndCount((long) source.length, (long) offset, (long) byteCount);
        int limit = offset + byteCount;
        while (offset < limit) {
            Segment tail = writableSegment(1);
            int toCopy = Math.min(limit - offset, 8192 - tail.limit);
            System.arraycopy(source, offset, tail.data, tail.limit, toCopy);
            offset += toCopy;
            tail.limit += toCopy;
        }
        this.size += (long) byteCount;
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        int byteCount = source.remaining();
        int remaining = byteCount;
        while (remaining > 0) {
            Segment tail = writableSegment(1);
            int toCopy = Math.min(remaining, 8192 - tail.limit);
            source.get(tail.data, tail.limit, toCopy);
            remaining -= toCopy;
            tail.limit += toCopy;
        }
        this.size += (long) byteCount;
        return byteCount;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this, Systrace.TRACE_TAG_REACT_JAVA_BRIDGE);
            if (readCount == -1) {
                return totalBytesRead;
            }
            totalBytesRead += readCount;
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink write(Source source, long byteCount) throws IOException {
        while (byteCount > 0) {
            long read = source.read(this, byteCount);
            if (read == -1) {
                throw new EOFException();
            }
            byteCount -= read;
        }
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int b) {
        Segment tail = writableSegment(1);
        byte[] bArr = tail.data;
        int i = tail.limit;
        tail.limit = i + 1;
        bArr[i] = (byte) b;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int s) {
        Segment tail = writableSegment(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((s >>> 8) & ProcReader.PROC_TERM_MASK);
        data[limit2] = (byte) (s & ProcReader.PROC_TERM_MASK);
        tail.limit = limit2 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShortLe(int s) {
        return writeShort((int) Util.reverseBytesShort((short) s));
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i) {
        Segment tail = writableSegment(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((i >>> 24) & ProcReader.PROC_TERM_MASK);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((i >>> 16) & ProcReader.PROC_TERM_MASK);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((i >>> 8) & ProcReader.PROC_TERM_MASK);
        data[limit4] = (byte) (i & ProcReader.PROC_TERM_MASK);
        tail.limit = limit4 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytesInt(i));
    }

    @Override // okio.BufferedSink
    public Buffer writeLong(long v) {
        Segment tail = writableSegment(8);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((int) ((v >>> 56) & 255));
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((int) ((v >>> 48) & 255));
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((int) ((v >>> 40) & 255));
        int limit5 = limit4 + 1;
        data[limit4] = (byte) ((int) ((v >>> 32) & 255));
        int limit6 = limit5 + 1;
        data[limit5] = (byte) ((int) ((v >>> 24) & 255));
        int limit7 = limit6 + 1;
        data[limit6] = (byte) ((int) ((v >>> 16) & 255));
        int limit8 = limit7 + 1;
        data[limit7] = (byte) ((int) ((v >>> 8) & 255));
        data[limit8] = (byte) ((int) (v & 255));
        tail.limit = limit8 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeLongLe(long v) {
        return writeLong(Util.reverseBytesLong(v));
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long v) {
        int width;
        if (v == 0) {
            return writeByte(48);
        }
        boolean negative = false;
        if (v < 0) {
            v = -v;
            if (v < 0) {
                return writeUtf8("-9223372036854775808");
            }
            negative = true;
        }
        if (v < 100000000) {
            width = v < StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS ? v < 100 ? v < 10 ? 1 : 2 : v < 1000 ? 3 : 4 : v < 1000000 ? v < 100000 ? 5 : 6 : v < 10000000 ? 7 : 8;
        } else if (v < 1000000000000L) {
            width = v < 10000000000L ? v < TimeConstants.NS_PER_SECOND ? 9 : 10 : v < 100000000000L ? 11 : 12;
        } else if (v < 1000000000000000L) {
            width = v < 10000000000000L ? 13 : v < 100000000000000L ? 14 : 15;
        } else if (v < 100000000000000000L) {
            width = v < 10000000000000000L ? 16 : 17;
        } else {
            width = v < 1000000000000000000L ? 18 : 19;
        }
        if (negative) {
            width++;
        }
        Segment tail = writableSegment(width);
        byte[] data = tail.data;
        int pos = tail.limit + width;
        while (v != 0) {
            pos--;
            data[pos] = DIGITS[(int) (v % 10)];
            v /= 10;
        }
        if (negative) {
            data[pos - 1] = 45;
        }
        tail.limit += width;
        this.size += (long) width;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long v) {
        if (v == 0) {
            return writeByte(48);
        }
        int width = (Long.numberOfTrailingZeros(Long.highestOneBit(v)) / 4) + 1;
        Segment tail = writableSegment(width);
        byte[] data = tail.data;
        int start = tail.limit;
        for (int pos = (tail.limit + width) - 1; pos >= start; pos--) {
            data[pos] = DIGITS[(int) (15 & v)];
            v >>>= 4;
        }
        tail.limit += width;
        this.size += (long) width;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Segment writableSegment(int minimumCapacity) {
        if (minimumCapacity < 1 || minimumCapacity > 8192) {
            throw new IllegalArgumentException();
        } else if (this.head == null) {
            this.head = SegmentPool.take();
            Segment segment = this.head;
            Segment segment2 = this.head;
            Segment segment3 = this.head;
            segment2.prev = segment3;
            segment.next = segment3;
            return segment3;
        } else {
            Segment tail = this.head.prev;
            if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
                return tail.push(SegmentPool.take());
            }
            return tail;
        }
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        } else if (source == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            Util.checkOffsetAndCount(source.size, 0, byteCount);
            while (byteCount > 0) {
                if (byteCount < ((long) (source.head.limit - source.head.pos))) {
                    Segment tail = this.head != null ? this.head.prev : null;
                    if (tail != null && tail.owner) {
                        if ((byteCount + ((long) tail.limit)) - ((long) (tail.shared ? 0 : tail.pos)) <= Systrace.TRACE_TAG_REACT_JAVA_BRIDGE) {
                            source.head.writeTo(tail, (int) byteCount);
                            source.size -= byteCount;
                            this.size += byteCount;
                            return;
                        }
                    }
                    source.head = source.head.split((int) byteCount);
                }
                Segment segmentToMove = source.head;
                long movedByteCount = (long) (segmentToMove.limit - segmentToMove.pos);
                source.head = segmentToMove.pop();
                if (this.head == null) {
                    this.head = segmentToMove;
                    Segment segment = this.head;
                    Segment segment2 = this.head;
                    Segment segment3 = this.head;
                    segment2.prev = segment3;
                    segment.next = segment3;
                } else {
                    this.head.prev.push(segmentToMove).compact();
                }
                source.size -= movedByteCount;
                this.size += movedByteCount;
                byteCount -= movedByteCount;
            }
        }
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (this.size == 0) {
            return -1;
        } else {
            if (byteCount > this.size) {
                byteCount = this.size;
            }
            sink.write(this, byteCount);
            return byteCount;
        }
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0, Clock.MAX_TIME);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex) {
        return indexOf(b, fromIndex, Clock.MAX_TIME);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex, long toIndex) {
        Segment s;
        long offset;
        if (fromIndex < 0 || toIndex < fromIndex) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(fromIndex), Long.valueOf(toIndex)));
        }
        if (toIndex > this.size) {
            toIndex = this.size;
        }
        if (fromIndex == toIndex || (s = this.head) == null) {
            return -1;
        }
        if (this.size - fromIndex >= fromIndex) {
            offset = 0;
            while (true) {
                long nextOffset = offset + ((long) (s.limit - s.pos));
                if (nextOffset >= fromIndex) {
                    break;
                }
                s = s.next;
                offset = nextOffset;
            }
        } else {
            offset = this.size;
            while (offset > fromIndex) {
                s = s.prev;
                offset -= (long) (s.limit - s.pos);
            }
        }
        while (offset < toIndex) {
            byte[] data = s.data;
            int limit = (int) Math.min((long) s.limit, (((long) s.pos) + toIndex) - offset);
            for (int pos = (int) ((((long) s.pos) + fromIndex) - offset); pos < limit; pos++) {
                if (data[pos] == b) {
                    return ((long) (pos - s.pos)) + offset;
                }
            }
            offset += (long) (s.limit - s.pos);
            fromIndex = offset;
            s = s.next;
        }
        return -1;
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString bytes) throws IOException {
        return indexOf(bytes, 0);
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString bytes, long fromIndex) throws IOException {
        long offset;
        if (bytes.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        } else if (fromIndex < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        } else {
            Segment s = this.head;
            if (s == null) {
                return -1;
            }
            if (this.size - fromIndex >= fromIndex) {
                offset = 0;
                while (true) {
                    long nextOffset = offset + ((long) (s.limit - s.pos));
                    if (nextOffset >= fromIndex) {
                        break;
                    }
                    s = s.next;
                    offset = nextOffset;
                }
            } else {
                offset = this.size;
                while (offset > fromIndex) {
                    s = s.prev;
                    offset -= (long) (s.limit - s.pos);
                }
            }
            byte b0 = bytes.getByte(0);
            int bytesSize = bytes.size();
            long resultLimit = (this.size - ((long) bytesSize)) + 1;
            while (offset < resultLimit) {
                byte[] data = s.data;
                int segmentLimit = (int) Math.min((long) s.limit, (((long) s.pos) + resultLimit) - offset);
                for (int pos = (int) ((((long) s.pos) + fromIndex) - offset); pos < segmentLimit; pos++) {
                    if (data[pos] == b0 && rangeEquals(s, pos + 1, bytes, 1, bytesSize)) {
                        return ((long) (pos - s.pos)) + offset;
                    }
                }
                offset += (long) (s.limit - s.pos);
                fromIndex = offset;
                s = s.next;
            }
            return -1;
        }
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes) {
        return indexOfElement(targetBytes, 0);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        long offset;
        if (fromIndex < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        if (this.size - fromIndex >= fromIndex) {
            offset = 0;
            while (true) {
                long nextOffset = offset + ((long) (s.limit - s.pos));
                if (nextOffset >= fromIndex) {
                    break;
                }
                s = s.next;
                offset = nextOffset;
            }
        } else {
            offset = this.size;
            while (offset > fromIndex) {
                s = s.prev;
                offset -= (long) (s.limit - s.pos);
            }
        }
        if (targetBytes.size() == 2) {
            byte b0 = targetBytes.getByte(0);
            byte b1 = targetBytes.getByte(1);
            while (offset < this.size) {
                byte[] data = s.data;
                int limit = s.limit;
                for (int pos = (int) ((((long) s.pos) + fromIndex) - offset); pos < limit; pos++) {
                    byte b = data[pos];
                    if (b == b0 || b == b1) {
                        return ((long) (pos - s.pos)) + offset;
                    }
                }
                offset += (long) (s.limit - s.pos);
                fromIndex = offset;
                s = s.next;
            }
        } else {
            byte[] targetByteArray = targetBytes.internalArray();
            while (offset < this.size) {
                byte[] data2 = s.data;
                int limit2 = s.limit;
                for (int pos2 = (int) ((((long) s.pos) + fromIndex) - offset); pos2 < limit2; pos2++) {
                    byte b2 = data2[pos2];
                    for (byte t : targetByteArray) {
                        if (b2 == t) {
                            return ((long) (pos2 - s.pos)) + offset;
                        }
                    }
                }
                offset += (long) (s.limit - s.pos);
                fromIndex = offset;
                s = s.next;
            }
        }
        return -1;
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes) {
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) {
        if (offset < 0 || bytesOffset < 0 || byteCount < 0 || this.size - offset < ((long) byteCount) || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i = 0; i < byteCount; i++) {
            if (getByte(((long) i) + offset) != bytes.getByte(bytesOffset + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean rangeEquals(Segment segment, int segmentPos, ByteString bytes, int bytesOffset, int bytesLimit) {
        int segmentLimit = segment.limit;
        byte[] data = segment.data;
        for (int i = bytesOffset; i < bytesLimit; i++) {
            if (segmentPos == segmentLimit) {
                segment = segment.next;
                data = segment.data;
                segmentPos = segment.pos;
                segmentLimit = segment.limit;
            }
            if (data[segmentPos] != bytes.getByte(i)) {
                return false;
            }
            segmentPos++;
        }
        return true;
    }

    @Override // okio.Sink, okio.BufferedSink, java.io.Flushable
    public void flush() {
    }

    public boolean isOpen() {
        return true;
    }

    @Override // okio.Source, java.lang.AutoCloseable, okio.Sink, java.io.Closeable, java.nio.channels.Channel
    public void close() {
    }

    @Override // okio.Source, okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    /* access modifiers changed from: package-private */
    public List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        result.add(Integer.valueOf(this.head.limit - this.head.pos));
        for (Segment s = this.head.next; s != this.head; s = s.next) {
            result.add(Integer.valueOf(s.limit - s.pos));
        }
        return result;
    }

    public final ByteString md5() {
        return digest("MD5");
    }

    public final ByteString sha1() {
        return digest(HashHelper.SHA1);
    }

    public final ByteString sha256() {
        return digest("SHA-256");
    }

    public final ByteString sha512() {
        return digest("SHA-512");
    }

    private ByteString digest(String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            if (this.head != null) {
                messageDigest.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                for (Segment s = this.head.next; s != this.head; s = s.next) {
                    messageDigest.update(s.data, s.pos, s.limit - s.pos);
                }
            }
            return ByteString.of(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public final ByteString hmacSha1(ByteString key) {
        return hmac("HmacSHA1", key);
    }

    public final ByteString hmacSha256(ByteString key) {
        return hmac("HmacSHA256", key);
    }

    public final ByteString hmacSha512(ByteString key) {
        return hmac("HmacSHA512", key);
    }

    private ByteString hmac(String algorithm, ByteString key) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            if (this.head != null) {
                mac.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                for (Segment s = this.head.next; s != this.head; s = s.next) {
                    mac.update(s.data, s.pos, s.limit - s.pos);
                }
            }
            return ByteString.of(mac.doFinal());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buffer)) {
            return false;
        }
        Buffer that = (Buffer) o;
        if (this.size != that.size) {
            return false;
        }
        if (this.size == 0) {
            return true;
        }
        Segment sa = this.head;
        Segment sb = that.head;
        int posA = sa.pos;
        int posB = sb.pos;
        long pos = 0;
        while (pos < this.size) {
            long count = (long) Math.min(sa.limit - posA, sb.limit - posB);
            for (int i = 0; ((long) i) < count; i++) {
                posA++;
                posB++;
                if (sa.data[posA] != sb.data[posB]) {
                    return false;
                }
            }
            if (posA == sa.limit) {
                sa = sa.next;
                posA = sa.pos;
            } else {
                posA = posA;
            }
            if (posB == sb.limit) {
                sb = sb.next;
                posB = sb.pos;
            } else {
                posB = posB;
            }
            pos += count;
        }
        return true;
    }

    public int hashCode() {
        Segment s = this.head;
        if (s == null) {
            return 0;
        }
        int result = 1;
        do {
            int limit = s.limit;
            for (int pos = s.pos; pos < limit; pos++) {
                result = (result * 31) + s.data[pos];
            }
            s = s.next;
        } while (s != this.head);
        return result;
    }

    public String toString() {
        return snapshot().toString();
    }

    @Override // java.lang.Object
    public Buffer clone() {
        Buffer result = new Buffer();
        if (this.size != 0) {
            result.head = this.head.sharedCopy();
            Segment segment = result.head;
            Segment segment2 = result.head;
            Segment segment3 = result.head;
            segment2.prev = segment3;
            segment.next = segment3;
            for (Segment s = this.head.next; s != this.head; s = s.next) {
                result.head.prev.push(s.sharedCopy());
            }
            result.size = this.size;
        }
        return result;
    }

    public final ByteString snapshot() {
        if (this.size <= 2147483647L) {
            return snapshot((int) this.size);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
    }

    public final ByteString snapshot(int byteCount) {
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, byteCount);
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe(new UnsafeCursor());
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = false;
        return unsafeCursor;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe(new UnsafeCursor());
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = true;
        return unsafeCursor;
    }

    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public int end = -1;
        public long offset = -1;
        public boolean readWrite;
        private Segment segment;
        public int start = -1;

        public final int next() {
            if (this.offset == this.buffer.size) {
                throw new IllegalStateException();
            } else if (this.offset == -1) {
                return seek(0);
            } else {
                return seek(this.offset + ((long) (this.end - this.start)));
            }
        }

        public final int seek(long offset2) {
            long nextOffset;
            Segment next;
            if (offset2 < -1 || offset2 > this.buffer.size) {
                throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(offset2), Long.valueOf(this.buffer.size)));
            } else if (offset2 == -1 || offset2 == this.buffer.size) {
                this.segment = null;
                this.offset = offset2;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            } else {
                long min = 0;
                long max = this.buffer.size;
                Segment head = this.buffer.head;
                Segment tail = this.buffer.head;
                if (this.segment != null) {
                    long segmentOffset = this.offset - ((long) (this.start - this.segment.pos));
                    if (segmentOffset > offset2) {
                        max = segmentOffset;
                        tail = this.segment;
                    } else {
                        min = segmentOffset;
                        head = this.segment;
                    }
                }
                if (max - offset2 > offset2 - min) {
                    next = head;
                    nextOffset = min;
                    while (offset2 >= ((long) (next.limit - next.pos)) + nextOffset) {
                        nextOffset += (long) (next.limit - next.pos);
                        next = next.next;
                    }
                } else {
                    next = tail;
                    nextOffset = max;
                    while (nextOffset > offset2) {
                        next = next.prev;
                        nextOffset -= (long) (next.limit - next.pos);
                    }
                }
                if (this.readWrite && next.shared) {
                    Segment unsharedNext = next.unsharedCopy();
                    if (this.buffer.head == next) {
                        this.buffer.head = unsharedNext;
                    }
                    next = next.push(unsharedNext);
                    next.prev.pop();
                }
                this.segment = next;
                this.offset = offset2;
                this.data = next.data;
                this.start = next.pos + ((int) (offset2 - nextOffset));
                this.end = next.limit;
                return this.end - this.start;
            }
        }

        public final long resizeBuffer(long newSize) {
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            } else if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            } else {
                long oldSize = this.buffer.size;
                if (newSize <= oldSize) {
                    if (newSize < 0) {
                        throw new IllegalArgumentException("newSize < 0: " + newSize);
                    }
                    long bytesToSubtract = oldSize - newSize;
                    while (true) {
                        if (bytesToSubtract <= 0) {
                            break;
                        }
                        Segment tail = this.buffer.head.prev;
                        int tailSize = tail.limit - tail.pos;
                        if (((long) tailSize) > bytesToSubtract) {
                            tail.limit = (int) (((long) tail.limit) - bytesToSubtract);
                            break;
                        }
                        this.buffer.head = tail.pop();
                        SegmentPool.recycle(tail);
                        bytesToSubtract -= (long) tailSize;
                    }
                    this.segment = null;
                    this.offset = newSize;
                    this.data = null;
                    this.start = -1;
                    this.end = -1;
                } else if (newSize > oldSize) {
                    boolean needsToSeek = true;
                    long bytesToAdd = newSize - oldSize;
                    while (bytesToAdd > 0) {
                        Segment tail2 = this.buffer.writableSegment(1);
                        int segmentBytesToAdd = (int) Math.min(bytesToAdd, (long) (8192 - tail2.limit));
                        tail2.limit += segmentBytesToAdd;
                        bytesToAdd -= (long) segmentBytesToAdd;
                        if (needsToSeek) {
                            this.segment = tail2;
                            this.offset = oldSize;
                            this.data = tail2.data;
                            this.start = tail2.limit - segmentBytesToAdd;
                            this.end = tail2.limit;
                            needsToSeek = false;
                        }
                    }
                }
                this.buffer.size = newSize;
                return oldSize;
            }
        }

        public final long expandBuffer(int minByteCount) {
            if (minByteCount <= 0) {
                throw new IllegalArgumentException("minByteCount <= 0: " + minByteCount);
            } else if (minByteCount > 8192) {
                throw new IllegalArgumentException("minByteCount > Segment.SIZE: " + minByteCount);
            } else if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            } else if (!this.readWrite) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
            } else {
                long oldSize = this.buffer.size;
                Segment tail = this.buffer.writableSegment(minByteCount);
                int result = 8192 - tail.limit;
                tail.limit = ProcReader.PROC_OUT_LONG;
                this.buffer.size = ((long) result) + oldSize;
                this.segment = tail;
                this.offset = oldSize;
                this.data = tail.data;
                this.start = 8192 - result;
                this.end = ProcReader.PROC_OUT_LONG;
                return (long) result;
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.buffer = null;
            this.segment = null;
            this.offset = -1;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }
    }
}
