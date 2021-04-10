package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public final class SegmentedByteString extends ByteString {
    public final transient int[] directory;
    public final transient byte[][] segments;

    public SegmentedByteString(Buffer buffer, int i) {
        super(null);
        Util.checkOffsetAndCount(buffer.size, 0, (long) i);
        Segment segment = buffer.head;
        int i2 = 0;
        Segment segment2 = segment;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (segment2.limit != segment2.pos) {
                i3 += segment2.limit - segment2.pos;
                i4++;
                segment2 = segment2.next;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        byte[][] bArr = new byte[i4][];
        this.segments = bArr;
        int[] iArr = new int[(i4 << 1)];
        this.directory = iArr;
        int i5 = 0;
        while (i2 < i) {
            bArr[i5] = segment.data;
            i2 += segment.limit - segment.pos;
            if (i2 > i) {
                i2 = i;
            }
            iArr[i5] = i2;
            iArr[i4 + i5] = segment.pos;
            segment.shared = true;
            i5++;
            segment = segment.next;
        }
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        ByteString byteString;
        int size;
        return obj == this || ((obj instanceof ByteString) && (byteString = (ByteString) obj).size() == (size = size()) && rangeEquals(0, byteString, 0, size));
    }

    private int segment(int i) {
        int binarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i + 1);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        return binarySearch;
    }

    @Override // okio.ByteString
    public byte getByte(int i) {
        int i2;
        Util.checkOffsetAndCount((long) this.directory[this.segments.length - 1], (long) i, 1);
        int segment = segment(i);
        if (segment == 0) {
            i2 = 0;
        } else {
            i2 = this.directory[segment - 1];
        }
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[segment][(i - i2) + iArr[bArr.length + segment]];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        byte[][] bArr = this.segments;
        int length = bArr.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr2 = bArr[i2];
            int[] iArr = this.directory;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr2[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.hashCode = i3;
        return i3;
    }

    @Override // okio.ByteString
    public int size() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        int[] iArr = this.directory;
        int length = this.segments.length;
        byte[] bArr = new byte[iArr[length - 1]];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.directory;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.segments[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    private ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    private Object writeReplace() {
        return toByteString();
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public ByteString hmacSha1(ByteString byteString) {
        return toByteString().hmacSha1(byteString);
    }

    @Override // okio.ByteString
    public ByteString hmacSha256(ByteString byteString) {
        return toByteString().hmacSha256(byteString);
    }

    @Override // okio.ByteString
    public int indexOf(byte[] bArr, int i) {
        return toByteString().indexOf(bArr, i);
    }

    @Override // okio.ByteString
    public byte[] internalArray() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] bArr, int i) {
        return toByteString().lastIndexOf(bArr, i);
    }

    @Override // okio.ByteString
    public ByteString md5() {
        return toByteString().md5();
    }

    @Override // okio.ByteString
    public ByteString sha1() {
        return toByteString().sha1();
    }

    @Override // okio.ByteString
    public ByteString sha256() {
        return toByteString().sha256();
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        return toByteString().string(charset);
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    @Override // okio.ByteString
    public String utf8() {
        return toByteString().utf8();
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        int i4;
        if (i >= 0 && i <= size() - i3) {
            int segment = segment(i);
            while (i3 > 0) {
                if (segment == 0) {
                    i4 = 0;
                } else {
                    i4 = this.directory[segment - 1];
                }
                int[] iArr = this.directory;
                int min = Math.min(i3, ((iArr[segment] - i4) + i4) - i);
                byte[][] bArr = this.segments;
                if (byteString.rangeEquals(i2, bArr[segment], (i - i4) + iArr[bArr.length + segment], min)) {
                    i += min;
                    i2 += min;
                    i3 -= min;
                    segment++;
                }
            }
            return true;
        }
        return false;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (i >= 0 && i <= size() - i3 && i2 >= 0 && i2 <= bArr.length - i3) {
            int segment = segment(i);
            while (i3 > 0) {
                if (segment == 0) {
                    i4 = 0;
                } else {
                    i4 = this.directory[segment - 1];
                }
                int[] iArr = this.directory;
                int min = Math.min(i3, ((iArr[segment] - i4) + i4) - i);
                byte[][] bArr2 = this.segments;
                if (Util.arrayRangeEquals(bArr2[segment], (i - i4) + iArr[bArr2.length + segment], bArr, i2, min)) {
                    i += min;
                    i2 += min;
                    i3 -= min;
                    segment++;
                }
            }
            return true;
        }
        return false;
    }

    @Override // okio.ByteString
    public ByteString substring(int i) {
        return toByteString().substring(i);
    }

    @Override // okio.ByteString
    public ByteString substring(int i, int i2) {
        return toByteString().substring(i, i2);
    }

    @Override // okio.ByteString
    public void write(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            int length = this.segments.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int[] iArr = this.directory;
                int i3 = iArr[length + i];
                int i4 = iArr[i];
                outputStream.write(this.segments[i], i3, i4 - i2);
                i++;
                i2 = i4;
            }
            return;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.ByteString
    public void write(Buffer buffer) {
        byte[][] bArr = this.segments;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            Segment segment = new Segment(bArr[i], i3, (i3 + i4) - i2, true, false);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                segment2.prev.push(segment);
            }
            i++;
            i2 = i4;
        }
        buffer.size += (long) i2;
    }
}
