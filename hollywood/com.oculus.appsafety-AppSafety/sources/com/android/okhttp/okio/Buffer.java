package com.android.okhttp.okio;

import android.icu.text.PluralRules;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sun.security.util.DerValue;

public final class Buffer implements BufferedSource, BufferedSink, Cloneable {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    Segment head;
    long size;

    public long size() {
        return this.size;
    }

    @Override // com.android.okhttp.okio.BufferedSource, com.android.okhttp.okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() {
            /* class com.android.okhttp.okio.Buffer.AnonymousClass1 */

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
                return ((Object) this) + ".outputStream()";
            }
        };
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() {
            /* class com.android.okhttp.okio.Buffer.AnonymousClass2 */

            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.size > 0) {
                    return Buffer.this.readByte() & 255;
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
                return ((Object) Buffer.this) + ".inputStream()";
            }
        };
    }

    public Buffer copyTo(OutputStream out) throws IOException {
        return copyTo(out, 0, this.size);
    }

    public Buffer copyTo(OutputStream out, long offset, long byteCount) throws IOException {
        if (out != null) {
            Util.checkOffsetAndCount(this.size, offset, byteCount);
            if (byteCount == 0) {
                return this;
            }
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
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    public Buffer copyTo(Buffer out, long offset, long byteCount) {
        if (out != null) {
            Util.checkOffsetAndCount(this.size, offset, byteCount);
            if (byteCount == 0) {
                return this;
            }
            out.size += byteCount;
            Segment s = this.head;
            while (offset >= ((long) (s.limit - s.pos))) {
                offset -= (long) (s.limit - s.pos);
                s = s.next;
            }
            while (byteCount > 0) {
                Segment copy = new Segment(s);
                copy.pos = (int) (((long) copy.pos) + offset);
                copy.limit = Math.min(copy.pos + ((int) byteCount), copy.limit);
                Segment segment = out.head;
                if (segment == null) {
                    copy.prev = copy;
                    copy.next = copy;
                    out.head = copy;
                } else {
                    segment.prev.push(copy);
                }
                byteCount -= (long) (copy.limit - copy.pos);
                offset = 0;
                s = s.next;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    public Buffer writeTo(OutputStream out) throws IOException {
        return writeTo(out, this.size);
    }

    public Buffer writeTo(OutputStream out, long byteCount) throws IOException {
        if (out != null) {
            Util.checkOffsetAndCount(this.size, 0, byteCount);
            Segment s = this.head;
            while (byteCount > 0) {
                int toCopy = (int) Math.min(byteCount, (long) (s.limit - s.pos));
                out.write(s.data, s.pos, toCopy);
                s.pos += toCopy;
                this.size -= (long) toCopy;
                byteCount -= (long) toCopy;
                if (s.pos == s.limit) {
                    Segment pop = s.pop();
                    s = pop;
                    this.head = pop;
                    SegmentPool.recycle(s);
                }
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    public Buffer readFrom(InputStream in) throws IOException {
        readFrom(in, Long.MAX_VALUE, true);
        return this;
    }

    public Buffer readFrom(InputStream in, long byteCount) throws IOException {
        if (byteCount >= 0) {
            readFrom(in, byteCount, false);
            return this;
        }
        throw new IllegalArgumentException("byteCount < 0: " + byteCount);
    }

    private void readFrom(InputStream in, long byteCount, boolean forever) throws IOException {
        if (in != null) {
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
        } else {
            throw new IllegalArgumentException("in == null");
        }
    }

    public long completeSegmentByteCount() {
        long result = this.size;
        if (result == 0) {
            return 0;
        }
        Segment tail = this.head.prev;
        if (tail.limit >= 8192 || !tail.owner) {
            return result;
        }
        return result - ((long) (tail.limit - tail.pos));
    }

    /* JADX INFO: Multiple debug info for r1v2 byte: [D('b' byte), D('pos' int)] */
    @Override // com.android.okhttp.okio.BufferedSource
    public byte readByte() {
        if (this.size != 0) {
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
        throw new IllegalStateException("size == 0");
    }

    public byte getByte(long pos) {
        Util.checkOffsetAndCount(this.size, pos, 1);
        Segment s = this.head;
        while (true) {
            int segmentByteCount = s.limit - s.pos;
            if (pos < ((long) segmentByteCount)) {
                return s.data[s.pos + ((int) pos)];
            }
            pos -= (long) segmentByteCount;
            s = s.next;
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public short readShort() {
        if (this.size >= 2) {
            Segment segment = this.head;
            int pos = segment.pos;
            int limit = segment.limit;
            if (limit - pos < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] data = segment.data;
            int pos2 = pos + 1;
            int pos3 = pos2 + 1;
            int s = ((data[pos] & 255) << 8) | (data[pos2] & 255);
            this.size -= 2;
            if (pos3 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos3;
            }
            return (short) s;
        }
        throw new IllegalStateException("size < 2: " + this.size);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readInt() {
        if (this.size >= 4) {
            Segment segment = this.head;
            int pos = segment.pos;
            int limit = segment.limit;
            if (limit - pos < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] data = segment.data;
            int pos2 = pos + 1;
            int pos3 = pos2 + 1;
            int i = ((data[pos] & 255) << 24) | ((data[pos2] & 255) << 16);
            int pos4 = pos3 + 1;
            int i2 = i | ((data[pos3] & 255) << 8);
            int pos5 = pos4 + 1;
            int i3 = i2 | (data[pos4] & 255);
            this.size -= 4;
            if (pos5 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos5;
            }
            return i3;
        }
        throw new IllegalStateException("size < 4: " + this.size);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readLong() {
        if (this.size >= 8) {
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
            long v = ((((long) data[pos]) & 255) << 56) | ((((long) data[pos2]) & 255) << 48) | ((((long) data[pos3]) & 255) << 40) | ((((long) data[pos4]) & 255) << 32) | ((((long) data[pos5]) & 255) << 24) | ((((long) data[pos6]) & 255) << 16) | ((((long) data[pos7]) & 255) << 8) | (((long) data[pos8]) & 255);
            this.size -= 8;
            if (pos9 == limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = pos9;
            }
            return v;
        }
        throw new IllegalStateException("size < 8: " + this.size);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00de, code lost:
        if (r4 == false) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return -r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return r1;
     */
    @Override // com.android.okhttp.okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() {
        /*
        // Method dump skipped, instructions count: 236
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0093, code lost:
        if (r8 != r9) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0095, code lost:
        r14.head = r6.pop();
        com.android.okhttp.okio.SegmentPool.recycle(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
        r6.pos = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        if (r5 != false) goto L_0x00a7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078 A[SYNTHETIC] */
    @Override // com.android.okhttp.okio.BufferedSource
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public ByteString readByteString(long byteCount) throws EOFException {
        return new ByteString(readByteArray(byteCount));
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void readFully(Buffer sink, long byteCount) throws EOFException {
        long j = this.size;
        if (j >= byteCount) {
            sink.write(this, byteCount);
        } else {
            sink.write(this, j);
            throw new EOFException();
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        long byteCount = this.size;
        if (byteCount > 0) {
            sink.write(this, byteCount);
        }
        return byteCount;
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8(long byteCount) throws EOFException {
        return readString(byteCount, Util.UTF_8);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
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
            String result = new String(s.data, s.pos, (int) byteCount, charset);
            s.pos = (int) (((long) s.pos) + byteCount);
            this.size -= byteCount;
            if (s.pos == s.limit) {
                this.head = s.pop();
                SegmentPool.recycle(s);
            }
            return result;
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8Line() throws EOFException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return readUtf8Line(newline);
        }
        long j = this.size;
        if (j != 0) {
            return readUtf8(j);
        }
        return null;
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        long newline = indexOf((byte) 10);
        if (newline != -1) {
            return readUtf8Line(newline);
        }
        Buffer data = new Buffer();
        copyTo(data, 0, Math.min(32L, this.size));
        throw new EOFException("\\n not found: size=" + size() + " content=" + data.readByteString().hex() + "...");
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

    @Override // com.android.okhttp.okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int min;
        int byteCount;
        int codePoint;
        if (this.size != 0) {
            byte b0 = getByte(0);
            if ((b0 & 128) == 0) {
                codePoint = b0 & Byte.MAX_VALUE;
                byteCount = 1;
                min = 0;
            } else if ((b0 & 224) == 192) {
                codePoint = b0 & 31;
                byteCount = 2;
                min = 128;
            } else if ((b0 & 240) == 224) {
                codePoint = b0 & 15;
                byteCount = 3;
                min = 2048;
            } else if ((b0 & 248) == 240) {
                codePoint = b0 & 7;
                byteCount = 4;
                min = 65536;
            } else {
                skip(1);
                return 65533;
            }
            if (this.size >= ((long) byteCount)) {
                for (int i = 1; i < byteCount; i++) {
                    byte b = getByte((long) i);
                    if ((b & DerValue.TAG_PRIVATE) == 128) {
                        codePoint = (codePoint << 6) | (b & 63);
                    } else {
                        skip((long) i);
                        return 65533;
                    }
                }
                skip((long) byteCount);
                if (codePoint > 1114111) {
                    return 65533;
                }
                if ((codePoint < 55296 || codePoint > 57343) && codePoint >= min) {
                    return codePoint;
                }
                return 65533;
            }
            throw new EOFException("size < " + byteCount + PluralRules.KEYWORD_RULE_SEPARATOR + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b0) + ")");
        }
        throw new EOFException();
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public byte[] readByteArray(long byteCount) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0, byteCount);
        if (byteCount <= 2147483647L) {
            byte[] result = new byte[((int) byteCount)];
            readFully(result);
            return result;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public int read(byte[] sink) {
        return read(sink, 0, sink.length);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void readFully(byte[] sink) throws EOFException {
        int offset = 0;
        while (offset < sink.length) {
            int read = read(sink, offset, sink.length - offset);
            if (read != -1) {
                offset += read;
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
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
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return toCopy;
    }

    public void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public void skip(long byteCount) throws EOFException {
        while (byteCount > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int toSkip = (int) Math.min(byteCount, (long) (segment.limit - this.head.pos));
                this.size -= (long) toSkip;
                byteCount -= (long) toSkip;
                this.head.pos += toSkip;
                if (this.head.pos == this.head.limit) {
                    Segment toRecycle = this.head;
                    this.head = toRecycle.pop();
                    SegmentPool.recycle(toRecycle);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString != null) {
            byteString.write(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeUtf8(String string) {
        return writeUtf8(string, 0, string.length());
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeUtf8(String string, int beginIndex, int endIndex) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        } else if (beginIndex < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + beginIndex);
        } else if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        } else if (endIndex <= string.length()) {
            int runSize = beginIndex;
            while (runSize < endIndex) {
                int c = string.charAt(runSize);
                if (c < 128) {
                    Segment tail = writableSegment(1);
                    byte[] data = tail.data;
                    int segmentOffset = tail.limit - runSize;
                    int runLimit = Math.min(endIndex, 8192 - segmentOffset);
                    int i = runSize + 1;
                    data[runSize + segmentOffset] = (byte) c;
                    while (i < runLimit) {
                        int c2 = string.charAt(i);
                        if (c2 >= 128) {
                            break;
                        }
                        data[i + segmentOffset] = (byte) c2;
                        i++;
                    }
                    int runSize2 = (i + segmentOffset) - tail.limit;
                    tail.limit += runSize2;
                    this.size += (long) runSize2;
                    runSize = i;
                } else if (c < 2048) {
                    writeByte((c >> 6) | 192);
                    writeByte(128 | (c & 63));
                    runSize++;
                } else if (c < 55296 || c > 57343) {
                    writeByte((c >> 12) | 224);
                    writeByte(((c >> 6) & 63) | 128);
                    writeByte(128 | (c & 63));
                    runSize++;
                } else {
                    int low = runSize + 1 < endIndex ? string.charAt(runSize + 1) : 0;
                    if (c > 56319 || low < 56320 || low > 57343) {
                        writeByte(63);
                        runSize++;
                    } else {
                        int codePoint = (((-55297 & c) << 10) | (-56321 & low)) + 65536;
                        writeByte((codePoint >> 18) | 240);
                        writeByte(((codePoint >> 12) & 63) | 128);
                        writeByte(((codePoint >> 6) & 63) | 128);
                        writeByte(128 | (codePoint & 63));
                        runSize += 2;
                    }
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        }
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            writeByte(codePoint);
        } else if (codePoint < 2048) {
            writeByte((codePoint >> 6) | 192);
            writeByte(128 | (codePoint & 63));
        } else if (codePoint < 65536) {
            if (codePoint < 55296 || codePoint > 57343) {
                writeByte((codePoint >> 12) | 224);
                writeByte(((codePoint >> 6) & 63) | 128);
                writeByte(128 | (codePoint & 63));
            } else {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(codePoint));
            }
        } else if (codePoint <= 1114111) {
            writeByte((codePoint >> 18) | 240);
            writeByte(((codePoint >> 12) & 63) | 128);
            writeByte(((codePoint >> 6) & 63) | 128);
            writeByte(128 | (codePoint & 63));
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(codePoint));
        }
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeString(String string, Charset charset) {
        return writeString(string, 0, string.length(), charset);
    }

    @Override // com.android.okhttp.okio.BufferedSink
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
            return writeUtf8(string);
        } else {
            byte[] data = string.substring(beginIndex, endIndex).getBytes(charset);
            return write(data, 0, data.length);
        }
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer write(byte[] source) {
        if (source != null) {
            return write(source, 0, source.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer write(byte[] source, int offset, int byteCount) {
        if (source != null) {
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
        throw new IllegalArgumentException("source == null");
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source != null) {
            long totalBytesRead = 0;
            while (true) {
                long readCount = source.read(this, 8192);
                if (readCount == -1) {
                    return totalBytesRead;
                }
                totalBytesRead += readCount;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public BufferedSink write(Source source, long byteCount) throws IOException {
        while (byteCount > 0) {
            long read = source.read(this, byteCount);
            if (read != -1) {
                byteCount -= read;
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeByte(int b) {
        Segment tail = writableSegment(1);
        byte[] bArr = tail.data;
        int i = tail.limit;
        tail.limit = i + 1;
        bArr[i] = (byte) b;
        this.size++;
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeShort(int s) {
        Segment tail = writableSegment(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((s >>> 8) & 255);
        data[limit2] = (byte) (s & 255);
        tail.limit = limit2 + 1;
        this.size += 2;
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeShortLe(int s) {
        return writeShort((int) Util.reverseBytesShort((short) s));
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeInt(int i) {
        Segment tail = writableSegment(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((i >>> 24) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((i >>> 16) & 255);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((i >>> 8) & 255);
        data[limit4] = (byte) (i & 255);
        tail.limit = limit4 + 1;
        this.size += 4;
        return this;
    }

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytesInt(i));
    }

    @Override // com.android.okhttp.okio.BufferedSink
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

    @Override // com.android.okhttp.okio.BufferedSink
    public Buffer writeLongLe(long v) {
        return writeLong(Util.reverseBytesLong(v));
    }

    @Override // com.android.okhttp.okio.BufferedSink
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
            width = v < 10000 ? v < 100 ? v < 10 ? 1 : 2 : v < 1000 ? 3 : 4 : v < 1000000 ? v < 100000 ? 5 : 6 : v < 10000000 ? 7 : 8;
        } else if (v < 1000000000000L) {
            width = v < 10000000000L ? v < 1000000000 ? 9 : 10 : v < 100000000000L ? 11 : 12;
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

    @Override // com.android.okhttp.okio.BufferedSink
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
        }
        Segment segment = this.head;
        if (segment == null) {
            this.head = SegmentPool.take();
            Segment segment2 = this.head;
            segment2.prev = segment2;
            segment2.next = segment2;
            return segment2;
        }
        Segment tail = segment.prev;
        if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
            return tail.push(SegmentPool.take());
        }
        return tail;
    }

    /* JADX INFO: Multiple debug info for r0v10 com.android.okhttp.okio.Segment: [D('tail' com.android.okhttp.okio.Segment), D('segmentToMove' com.android.okhttp.okio.Segment)] */
    @Override // com.android.okhttp.okio.Sink
    public void write(Buffer source, long byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        } else if (source != this) {
            Util.checkOffsetAndCount(source.size, 0, byteCount);
            while (byteCount > 0) {
                if (byteCount < ((long) (source.head.limit - source.head.pos))) {
                    Segment segment = this.head;
                    Segment tail = segment != null ? segment.prev : null;
                    if (tail != null && tail.owner) {
                        if ((((long) tail.limit) + byteCount) - ((long) (tail.shared ? 0 : tail.pos)) <= 8192) {
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
                Segment segment2 = this.head;
                if (segment2 == null) {
                    this.head = segmentToMove;
                    Segment segment3 = this.head;
                    segment3.prev = segment3;
                    segment3.next = segment3;
                } else {
                    segment2.prev.push(segmentToMove).compact();
                }
                source.size -= movedByteCount;
                this.size += movedByteCount;
                byteCount -= movedByteCount;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }

    @Override // com.android.okhttp.okio.Source
    public long read(Buffer sink, long byteCount) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (byteCount >= 0) {
            long j = this.size;
            if (j == 0) {
                return -1;
            }
            if (byteCount > j) {
                byteCount = this.size;
            }
            sink.write(this, byteCount);
            return byteCount;
        } else {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOf(byte b, long fromIndex) {
        if (fromIndex >= 0) {
            Segment s = this.head;
            if (s == null) {
                return -1;
            }
            long offset = 0;
            do {
                int segmentByteCount = s.limit - s.pos;
                if (fromIndex >= ((long) segmentByteCount)) {
                    fromIndex -= (long) segmentByteCount;
                } else {
                    byte[] data = s.data;
                    int limit = s.limit;
                    for (int pos = (int) (((long) s.pos) + fromIndex); pos < limit; pos++) {
                        if (data[pos] == b) {
                            return (((long) pos) + offset) - ((long) s.pos);
                        }
                    }
                    fromIndex = 0;
                }
                offset += (long) segmentByteCount;
                s = s.next;
            } while (s != this.head);
            return -1;
        }
        throw new IllegalArgumentException("fromIndex < 0");
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
    public long indexOfElement(ByteString targetBytes) {
        return indexOfElement(targetBytes, 0);
    }

    @Override // com.android.okhttp.okio.BufferedSource
    public long indexOfElement(ByteString targetBytes, long fromIndex) {
        if (fromIndex >= 0) {
            Segment s = this.head;
            if (s == null) {
                return -1;
            }
            byte[] toFind = targetBytes.toByteArray();
            long offset = 0;
            long fromIndex2 = fromIndex;
            while (true) {
                int segmentByteCount = s.limit - s.pos;
                if (fromIndex2 >= ((long) segmentByteCount)) {
                    fromIndex2 -= (long) segmentByteCount;
                } else {
                    byte[] data = s.data;
                    long limit = (long) s.limit;
                    for (long pos = ((long) s.pos) + fromIndex2; pos < limit; pos++) {
                        byte b = data[(int) pos];
                        int i = 0;
                        for (int length = toFind.length; i < length; length = length) {
                            if (b == toFind[i]) {
                                return (offset + pos) - ((long) s.pos);
                            }
                            i++;
                        }
                    }
                    fromIndex2 = 0;
                }
                offset += (long) segmentByteCount;
                s = s.next;
                if (s == this.head) {
                    return -1;
                }
            }
        } else {
            throw new IllegalArgumentException("fromIndex < 0");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean rangeEquals(long offset, ByteString bytes) {
        int byteCount = bytes.size();
        if (this.size - offset < ((long) byteCount)) {
            return false;
        }
        for (int i = 0; i < byteCount; i++) {
            if (getByte(((long) i) + offset) != bytes.getByte(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.okhttp.okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // com.android.okhttp.okio.Sink, java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
    public void close() {
    }

    @Override // com.android.okhttp.okio.Sink, com.android.okhttp.okio.Source
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buffer)) {
            return false;
        }
        Buffer that = (Buffer) o;
        long j = this.size;
        if (j != that.size) {
            return false;
        }
        if (j == 0) {
            return true;
        }
        Segment sa = this.head;
        Segment sb = that.head;
        int posA = sa.pos;
        int posB = sb.pos;
        long pos = 0;
        while (pos < this.size) {
            long count = (long) Math.min(sa.limit - posA, sb.limit - posB);
            int i = 0;
            while (((long) i) < count) {
                int posA2 = posA + 1;
                int posB2 = posB + 1;
                if (sa.data[posA] != sb.data[posB]) {
                    return false;
                }
                i++;
                posA = posA2;
                posB = posB2;
            }
            if (posA == sa.limit) {
                sa = sa.next;
                posA = sa.pos;
            }
            if (posB == sb.limit) {
                sb = sb.next;
                posB = sb.pos;
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
        long j = this.size;
        if (j == 0) {
            return "Buffer[size=0]";
        }
        if (j <= 16) {
            return String.format("Buffer[size=%s data=%s]", Long.valueOf(this.size), clone().readByteString().hex());
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
            for (Segment s = this.head.next; s != this.head; s = s.next) {
                md5.update(s.data, s.pos, s.limit - s.pos);
            }
            return String.format("Buffer[size=%s md5=%s]", Long.valueOf(this.size), ByteString.of(md5.digest()).hex());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public Buffer clone() {
        Buffer result = new Buffer();
        if (this.size == 0) {
            return result;
        }
        result.head = new Segment(this.head);
        Segment segment = result.head;
        segment.prev = segment;
        segment.next = segment;
        for (Segment s = this.head.next; s != this.head; s = s.next) {
            result.head.prev.push(new Segment(s));
        }
        result.size = this.size;
        return result;
    }

    public ByteString snapshot() {
        long j = this.size;
        if (j <= 2147483647L) {
            return snapshot((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
    }

    public ByteString snapshot(int byteCount) {
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, byteCount);
    }
}
