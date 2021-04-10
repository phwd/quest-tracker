package sun.security.util;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class BitArray {
    private static final byte[][] NYBBLE = {new byte[]{48, 48, 48, 48}, new byte[]{48, 48, 48, 49}, new byte[]{48, 48, 49, 48}, new byte[]{48, 48, 49, 49}, new byte[]{48, 49, 48, 48}, new byte[]{48, 49, 48, 49}, new byte[]{48, 49, 49, 48}, new byte[]{48, 49, 49, 49}, new byte[]{49, 48, 48, 48}, new byte[]{49, 48, 48, 49}, new byte[]{49, 48, 49, 48}, new byte[]{49, 48, 49, 49}, new byte[]{49, 49, 48, 48}, new byte[]{49, 49, 48, 49}, new byte[]{49, 49, 49, 48}, new byte[]{49, 49, 49, 49}};
    private int length;
    private byte[] repn;

    private static int subscript(int i) {
        return i / 8;
    }

    private static int position(int i) {
        return 1 << (7 - (i % 8));
    }

    public BitArray(int i) {
        if (i >= 0) {
            this.length = i;
            this.repn = new byte[(((i + 8) - 1) / 8)];
            return;
        }
        throw new IllegalArgumentException("Negative length for BitArray");
    }

    public BitArray(int i, byte[] bArr) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative length for BitArray");
        } else if (bArr.length * 8 >= i) {
            this.length = i;
            int i2 = ((i + 8) - 1) / 8;
            byte b = (byte) (255 << ((i2 * 8) - i));
            this.repn = new byte[i2];
            System.arraycopy(bArr, 0, this.repn, 0, i2);
            if (i2 > 0) {
                byte[] bArr2 = this.repn;
                int i3 = i2 - 1;
                bArr2[i3] = (byte) (b & bArr2[i3]);
            }
        } else {
            throw new IllegalArgumentException("Byte array too short to represent bit array of given length");
        }
    }

    public BitArray(boolean[] zArr) {
        this.length = zArr.length;
        this.repn = new byte[((this.length + 7) / 8)];
        for (int i = 0; i < this.length; i++) {
            set(i, zArr[i]);
        }
    }

    private BitArray(BitArray bitArray) {
        this.length = bitArray.length;
        this.repn = (byte[]) bitArray.repn.clone();
    }

    public boolean get(int i) {
        if (i >= 0 && i < this.length) {
            return (this.repn[subscript(i)] & position(i)) != 0;
        }
        throw new ArrayIndexOutOfBoundsException(Integer.toString(i));
    }

    public void set(int i, boolean z) {
        if (i < 0 || i >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Integer.toString(i));
        }
        int subscript = subscript(i);
        int position = position(i);
        if (z) {
            byte[] bArr = this.repn;
            bArr[subscript] = (byte) (position | bArr[subscript]);
            return;
        }
        byte[] bArr2 = this.repn;
        bArr2[subscript] = (byte) ((~position) & bArr2[subscript]);
    }

    public int length() {
        return this.length;
    }

    public byte[] toByteArray() {
        return (byte[]) this.repn.clone();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        if (bitArray.length != this.length) {
            return false;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.repn;
            if (i >= bArr.length) {
                return true;
            }
            if (bArr[i] != bitArray.repn[i]) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.repn;
            if (i >= bArr.length) {
                return this.length ^ i2;
            }
            i2 = (i2 * 31) + bArr[i];
            i++;
        }
    }

    public Object clone() {
        return new BitArray(this);
    }

    public String toString() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            bArr = this.repn;
            if (i >= bArr.length - 1) {
                break;
            }
            byteArrayOutputStream.write(NYBBLE[(bArr[i] >> 4) & 15], 0, 4);
            byteArrayOutputStream.write(NYBBLE[this.repn[i] & 15], 0, 4);
            if (i % 8 == 7) {
                byteArrayOutputStream.write(10);
            } else {
                byteArrayOutputStream.write(32);
            }
            i++;
        }
        for (int length2 = (bArr.length - 1) * 8; length2 < this.length; length2++) {
            byteArrayOutputStream.write(get(length2) ? 49 : 48);
        }
        new String(byteArrayOutputStream.toByteArray());
        throw null;
    }

    public BitArray truncate() {
        for (int i = this.length - 1; i >= 0; i--) {
            if (get(i)) {
                return new BitArray(i + 1, Arrays.copyOf(this.repn, (i + 8) / 8));
            }
        }
        return new BitArray(1);
    }
}
