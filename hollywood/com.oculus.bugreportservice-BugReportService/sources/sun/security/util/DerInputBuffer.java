package sun.security.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

/* access modifiers changed from: package-private */
public class DerInputBuffer extends ByteArrayInputStream implements Cloneable {
    DerInputBuffer(byte[] bArr) {
        super(bArr);
    }

    DerInputBuffer(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public DerInputBuffer dup() {
        try {
            DerInputBuffer derInputBuffer = (DerInputBuffer) clone();
            derInputBuffer.mark(Integer.MAX_VALUE);
            return derInputBuffer;
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] toByteArray() {
        int available = available();
        if (available <= 0) {
            return null;
        }
        byte[] bArr = new byte[available];
        System.arraycopy(this.buf, this.pos, bArr, 0, available);
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public int getPos() {
        return this.pos;
    }

    /* access modifiers changed from: package-private */
    public byte[] getSlice(int i, int i2) {
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buf, i, bArr, 0, i2);
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public int peek() {
        int i = this.pos;
        if (i < this.count) {
            return this.buf[i];
        }
        throw new IOException("out of data");
    }

    public boolean equals(Object obj) {
        if (obj instanceof DerInputBuffer) {
            return equals((DerInputBuffer) obj);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean equals(DerInputBuffer derInputBuffer) {
        if (this == derInputBuffer) {
            return true;
        }
        int available = available();
        if (derInputBuffer.available() != available) {
            return false;
        }
        for (int i = 0; i < available; i++) {
            if (this.buf[this.pos + i] != derInputBuffer.buf[derInputBuffer.pos + i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int available = available();
        int i = this.pos;
        int i2 = 0;
        for (int i3 = 0; i3 < available; i3++) {
            i2 += this.buf[i + i3] * i3;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void truncate(int i) {
        if (i <= available()) {
            this.count = this.pos + i;
            return;
        }
        throw new IOException("insufficient data");
    }

    /* access modifiers changed from: package-private */
    public BigInteger getBigInteger(int i, boolean z) {
        if (i > available()) {
            throw new IOException("short read of integer");
        } else if (i != 0) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.buf, this.pos, bArr, 0, i);
            skip((long) i);
            if (i >= 2 && bArr[0] == 0 && bArr[1] >= 0) {
                throw new IOException("Invalid encoding: redundant leading 0s");
            } else if (z) {
                return new BigInteger(1, bArr);
            } else {
                return new BigInteger(bArr);
            }
        } else {
            throw new IOException("Invalid encoding: zero length Int value");
        }
    }

    public int getInteger(int i) {
        BigInteger bigInteger = getBigInteger(i, false);
        if (bigInteger.compareTo(BigInteger.valueOf(-2147483648L)) < 0) {
            throw new IOException("Integer below minimum valid value");
        } else if (bigInteger.compareTo(BigInteger.valueOf(2147483647L)) <= 0) {
            return bigInteger.intValue();
        } else {
            throw new IOException("Integer exceeds maximum valid value");
        }
    }

    public byte[] getBitString(int i) {
        if (i > available()) {
            throw new IOException("short read of bit string");
        } else if (i != 0) {
            byte[] bArr = this.buf;
            int i2 = this.pos;
            byte b = bArr[i2];
            if (b < 0 || b > 7) {
                throw new IOException("Invalid number of padding bits");
            }
            int i3 = i - 1;
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i2 + 1, bArr2, 0, i3);
            if (b != 0) {
                int i4 = i - 2;
                bArr2[i4] = (byte) (bArr2[i4] & (255 << b));
            }
            skip((long) i);
            return bArr2;
        } else {
            throw new IOException("Invalid encoding: zero length bit string");
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] getBitString() {
        return getBitString(available());
    }

    /* access modifiers changed from: package-private */
    public BitArray getUnalignedBitString() {
        if (this.pos >= this.count) {
            return null;
        }
        int available = available();
        int i = this.buf[this.pos] & 255;
        if (i <= 7) {
            int i2 = available - 1;
            byte[] bArr = new byte[i2];
            int length = bArr.length == 0 ? 0 : (bArr.length * 8) - i;
            System.arraycopy(this.buf, this.pos + 1, bArr, 0, i2);
            BitArray bitArray = new BitArray(length, bArr);
            this.pos = this.count;
            return bitArray;
        }
        throw new IOException("Invalid value for unused bits: " + i);
    }

    public Date getUTCTime(int i) {
        if (i > available()) {
            throw new IOException("short read of DER UTC Time");
        } else if (i >= 11 && i <= 17) {
            return getTime(i, false);
        } else {
            throw new IOException("DER UTC Time length error");
        }
    }

    public Date getGeneralizedTime(int i) {
        if (i > available()) {
            throw new IOException("short read of DER Generalized Time");
        } else if (i >= 13 && i <= 23) {
            return getTime(i, true);
        } else {
            throw new IOException("DER Generalized Time length error");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x01eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Date getTime(int r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 877
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.util.DerInputBuffer.getTime(int, boolean):java.util.Date");
    }
}
