package android.icu.util;

import android.icu.impl.Utility;
import android.icu.impl.number.Padder;
import java.nio.ByteBuffer;

public class ByteArrayWrapper implements Comparable<ByteArrayWrapper> {
    public byte[] bytes;
    public int size;

    public ByteArrayWrapper() {
    }

    public ByteArrayWrapper(byte[] bytesToAdopt, int size2) {
        if ((bytesToAdopt != null || size2 == 0) && size2 >= 0 && (bytesToAdopt == null || size2 <= bytesToAdopt.length)) {
            this.bytes = bytesToAdopt;
            this.size = size2;
            return;
        }
        throw new IndexOutOfBoundsException("illegal size: " + size2);
    }

    public ByteArrayWrapper(ByteBuffer source) {
        this.size = source.limit();
        int i = this.size;
        this.bytes = new byte[i];
        source.get(this.bytes, 0, i);
    }

    public ByteArrayWrapper ensureCapacity(int capacity) {
        byte[] bArr = this.bytes;
        if (bArr == null || bArr.length < capacity) {
            byte[] newbytes = new byte[capacity];
            byte[] bArr2 = this.bytes;
            if (bArr2 != null) {
                copyBytes(bArr2, 0, newbytes, 0, this.size);
            }
            this.bytes = newbytes;
        }
        return this;
    }

    public final ByteArrayWrapper set(byte[] src, int start, int limit) {
        this.size = 0;
        append(src, start, limit);
        return this;
    }

    public final ByteArrayWrapper append(byte[] src, int start, int limit) {
        int len = limit - start;
        ensureCapacity(this.size + len);
        copyBytes(src, start, this.bytes, this.size, len);
        this.size += len;
        return this;
    }

    public final byte[] releaseBytes() {
        byte[] result = this.bytes;
        this.bytes = null;
        this.size = 0;
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                result.append(Padder.FALLBACK_PADDING_STRING);
            }
            result.append(Utility.hex((long) (this.bytes[i] & 255), 2));
        }
        return result.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        try {
            ByteArrayWrapper that = (ByteArrayWrapper) other;
            if (this.size != that.size) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (this.bytes[i] != that.bytes[i]) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        int result = this.bytes.length;
        for (int i = 0; i < this.size; i++) {
            result = (result * 37) + this.bytes[i];
        }
        return result;
    }

    public int compareTo(ByteArrayWrapper other) {
        if (this == other) {
            return 0;
        }
        int minSize = this.size;
        int i = other.size;
        if (minSize >= i) {
            minSize = i;
        }
        for (int i2 = 0; i2 < minSize; i2++) {
            byte[] bArr = this.bytes;
            byte b = bArr[i2];
            byte[] bArr2 = other.bytes;
            if (b != bArr2[i2]) {
                return (bArr[i2] & 255) - (bArr2[i2] & 255);
            }
        }
        return this.size - other.size;
    }

    private static final void copyBytes(byte[] src, int srcoff, byte[] tgt, int tgtoff, int length) {
        if (length < 64) {
            int i = srcoff;
            int n = tgtoff;
            while (true) {
                length--;
                if (length >= 0) {
                    tgt[n] = src[i];
                    i++;
                    n++;
                } else {
                    return;
                }
            }
        } else {
            System.arraycopy(src, srcoff, tgt, tgtoff, length);
        }
    }
}
