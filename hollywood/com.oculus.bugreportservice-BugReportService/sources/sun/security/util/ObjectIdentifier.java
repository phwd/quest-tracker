package sun.security.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

public final class ObjectIdentifier implements Serializable {
    private static final long serialVersionUID = 8697030238860181294L;
    private int componentLen = -1;
    private Object components = null;
    private transient boolean componentsCalculated = false;
    private byte[] encoding = null;
    private volatile transient String stringForm;

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        if (!this.componentsCalculated) {
            int[] intArray = toIntArray();
            if (intArray != null) {
                this.components = intArray;
                this.componentLen = intArray.length;
            } else {
                this.components = HugeOidNotSupportedByOldJDK.theOne;
            }
            this.componentsCalculated = true;
        }
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    static class HugeOidNotSupportedByOldJDK implements Serializable {
        private static final long serialVersionUID = 1;
        static HugeOidNotSupportedByOldJDK theOne = new HugeOidNotSupportedByOldJDK();

        HugeOidNotSupportedByOldJDK() {
        }
    }

    public ObjectIdentifier(String str) {
        int indexOf;
        int i;
        String str2;
        int i2;
        byte[] bArr = new byte[str.length()];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        do {
            try {
                indexOf = str.indexOf(46, i3);
                if (indexOf == -1) {
                    str2 = str.substring(i3);
                    i = str.length() - i3;
                } else {
                    str2 = str.substring(i3, indexOf);
                    i = indexOf - i3;
                }
                if (i > 9) {
                    BigInteger bigInteger = new BigInteger(str2);
                    if (i4 == 0) {
                        checkFirstComponent(bigInteger);
                        i5 = bigInteger.intValue();
                        i3 = indexOf + 1;
                        i4++;
                    } else {
                        if (i4 == 1) {
                            checkSecondComponent(i5, bigInteger);
                            bigInteger = bigInteger.add(BigInteger.valueOf((long) (i5 * 40)));
                        } else {
                            checkOtherComponent(i4, bigInteger);
                        }
                        i2 = pack7Oid(bigInteger, bArr, i6);
                    }
                } else {
                    int parseInt = Integer.parseInt(str2);
                    if (i4 == 0) {
                        checkFirstComponent(parseInt);
                        i5 = parseInt;
                        i3 = indexOf + 1;
                        i4++;
                    } else {
                        if (i4 == 1) {
                            checkSecondComponent(i5, parseInt);
                            parseInt += i5 * 40;
                        } else {
                            checkOtherComponent(i4, parseInt);
                        }
                        i2 = pack7Oid(parseInt, bArr, i6);
                    }
                }
                i6 += i2;
                i3 = indexOf + 1;
                i4++;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new IOException("ObjectIdentifier() -- Invalid format: " + e2.toString(), e2);
            }
        } while (indexOf != -1);
        checkCount(i4);
        this.encoding = new byte[i6];
        System.arraycopy(bArr, 0, this.encoding, 0, i6);
        this.stringForm = str;
    }

    public ObjectIdentifier(int[] iArr) {
        checkCount(iArr.length);
        checkFirstComponent(iArr[0]);
        checkSecondComponent(iArr[0], iArr[1]);
        for (int i = 2; i < iArr.length; i++) {
            checkOtherComponent(i, iArr[i]);
        }
        init(iArr, iArr.length);
    }

    public ObjectIdentifier(DerInputStream derInputStream) {
        byte b = (byte) derInputStream.getByte();
        if (b == 6) {
            int length = derInputStream.getLength();
            if (length <= derInputStream.available()) {
                this.encoding = new byte[length];
                derInputStream.getBytes(this.encoding);
                check(this.encoding);
                return;
            }
            throw new IOException("ObjectIdentifier() -- length exceedsdata available.  Length: " + length + ", Available: " + derInputStream.available());
        }
        throw new IOException("ObjectIdentifier() -- data isn't an object ID (tag = " + ((int) b) + ")");
    }

    ObjectIdentifier(DerInputBuffer derInputBuffer) {
        DerInputStream derInputStream = new DerInputStream(derInputBuffer);
        this.encoding = new byte[derInputStream.available()];
        derInputStream.getBytes(this.encoding);
        check(this.encoding);
    }

    private void init(int[] iArr, int i) {
        int i2;
        byte[] bArr = new byte[((i * 5) + 1)];
        if (iArr[1] < Integer.MAX_VALUE - (iArr[0] * 40)) {
            i2 = pack7Oid((iArr[0] * 40) + iArr[1], bArr, 0);
        } else {
            i2 = pack7Oid(BigInteger.valueOf((long) iArr[1]).add(BigInteger.valueOf((long) (iArr[0] * 40))), bArr, 0);
        }
        int i3 = i2 + 0;
        for (int i4 = 2; i4 < i; i4++) {
            i3 += pack7Oid(iArr[i4], bArr, i3);
        }
        this.encoding = new byte[i3];
        System.arraycopy(bArr, 0, this.encoding, 0, i3);
    }

    public static ObjectIdentifier newInternal(int[] iArr) {
        try {
            return new ObjectIdentifier(iArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.write((byte) 6, this.encoding);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectIdentifier)) {
            return false;
        }
        return Arrays.equals(this.encoding, ((ObjectIdentifier) obj).encoding);
    }

    public int hashCode() {
        return Arrays.hashCode(this.encoding);
    }

    public int[] toIntArray() {
        int i;
        int length = this.encoding.length;
        int[] iArr = new int[20];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            byte[] bArr = this.encoding;
            if ((bArr[i4] & 128) == 0) {
                int i5 = (i4 - i3) + 1;
                if (i5 > 4) {
                    BigInteger bigInteger = new BigInteger(pack(bArr, i3, i5, 7, 8));
                    if (i3 == 0) {
                        int i6 = i2 + 1;
                        iArr[i2] = 2;
                        BigInteger subtract = bigInteger.subtract(BigInteger.valueOf(80));
                        if (subtract.compareTo(BigInteger.valueOf(2147483647L)) == 1) {
                            return null;
                        }
                        i = i6 + 1;
                        iArr[i6] = subtract.intValue();
                    } else if (bigInteger.compareTo(BigInteger.valueOf(2147483647L)) == 1) {
                        return null;
                    } else {
                        i = i2 + 1;
                        iArr[i2] = bigInteger.intValue();
                    }
                } else {
                    int i7 = 0;
                    for (int i8 = i3; i8 <= i4; i8++) {
                        i7 = (i7 << 7) | (this.encoding[i8] & Byte.MAX_VALUE);
                    }
                    if (i3 != 0) {
                        i = i2 + 1;
                        iArr[i2] = i7;
                    } else if (i7 < 80) {
                        int i9 = i2 + 1;
                        iArr[i2] = i7 / 40;
                        i = i9 + 1;
                        iArr[i9] = i7 % 40;
                    } else {
                        int i10 = i2 + 1;
                        iArr[i2] = 2;
                        i = i10 + 1;
                        iArr[i10] = i7 - 80;
                    }
                }
                i3 = i4 + 1;
                i2 = i;
            }
            if (i2 >= iArr.length) {
                iArr = Arrays.copyOf(iArr, i2 + 10);
            }
        }
        return Arrays.copyOf(iArr, i2);
    }

    public String toString() {
        String str = this.stringForm;
        if (str != null) {
            return str;
        }
        int length = this.encoding.length;
        StringBuffer stringBuffer = new StringBuffer(length * 4);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if ((this.encoding[i2] & 128) == 0) {
                if (i != 0) {
                    stringBuffer.append('.');
                }
                int i3 = (i2 - i) + 1;
                if (i3 > 4) {
                    BigInteger bigInteger = new BigInteger(pack(this.encoding, i, i3, 7, 8));
                    if (i == 0) {
                        stringBuffer.append("2.");
                        stringBuffer.append(bigInteger.subtract(BigInteger.valueOf(80)));
                    } else {
                        stringBuffer.append(bigInteger);
                    }
                } else {
                    int i4 = 0;
                    for (int i5 = i; i5 <= i2; i5++) {
                        i4 = (i4 << 7) | (this.encoding[i5] & Byte.MAX_VALUE);
                    }
                    if (i != 0) {
                        stringBuffer.append(i4);
                    } else if (i4 < 80) {
                        stringBuffer.append(i4 / 40);
                        stringBuffer.append('.');
                        stringBuffer.append(i4 % 40);
                    } else {
                        stringBuffer.append("2.");
                        stringBuffer.append(i4 - 80);
                    }
                }
                i = i2 + 1;
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        this.stringForm = stringBuffer2;
        return stringBuffer2;
    }

    private static byte[] pack(byte[] bArr, int i, int i2, int i3, int i4) {
        if (i3 == i4) {
            return (byte[]) bArr.clone();
        }
        int i5 = i2 * i3;
        int i6 = ((i5 + i4) - 1) / i4;
        byte[] bArr2 = new byte[i6];
        int i7 = 0;
        int i8 = (i6 * i4) - i5;
        while (i7 < i5) {
            int i9 = i3 - (i7 % i3);
            int i10 = i4 - (i8 % i4);
            int i11 = i9 > i10 ? i10 : i9;
            int i12 = i8 / i4;
            bArr2[i12] = (byte) (((((bArr[(i7 / i3) + i] + 256) >> (i9 - i11)) & ((1 << i11) - 1)) << (i10 - i11)) | bArr2[i12]);
            i7 += i11;
            i8 += i11;
        }
        return bArr2;
    }

    private static int pack7Oid(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] pack = pack(bArr, i, i2, 8, 7);
        int length = pack.length - 1;
        for (int length2 = pack.length - 2; length2 >= 0; length2--) {
            if (pack[length2] != 0) {
                length = length2;
            }
            pack[length2] = (byte) (pack[length2] | 128);
        }
        System.arraycopy(pack, length, bArr2, i3, pack.length - length);
        return pack.length - length;
    }

    private static int pack7Oid(int i, byte[] bArr, int i2) {
        return pack7Oid(new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i}, 0, 4, bArr, i2);
    }

    private static int pack7Oid(BigInteger bigInteger, byte[] bArr, int i) {
        byte[] byteArray = bigInteger.toByteArray();
        return pack7Oid(byteArray, 0, byteArray.length, bArr, i);
    }

    private static void check(byte[] bArr) {
        int length = bArr.length;
        if (length < 1 || (bArr[length - 1] & 128) != 0) {
            throw new IOException("ObjectIdentifier() -- Invalid DER encoding, not ended");
        }
        for (int i = 0; i < length; i++) {
            if (bArr[i] == Byte.MIN_VALUE && (i == 0 || (bArr[i - 1] & 128) == 0)) {
                throw new IOException("ObjectIdentifier() -- Invalid DER encoding, useless extra octet detected");
            }
        }
    }

    private static void checkCount(int i) {
        if (i < 2) {
            throw new IOException("ObjectIdentifier() -- Must be at least two oid components ");
        }
    }

    private static void checkFirstComponent(int i) {
        if (i < 0 || i > 2) {
            throw new IOException("ObjectIdentifier() -- First oid component is invalid ");
        }
    }

    private static void checkFirstComponent(BigInteger bigInteger) {
        if (bigInteger.signum() == -1 || bigInteger.compareTo(BigInteger.valueOf(2)) == 1) {
            throw new IOException("ObjectIdentifier() -- First oid component is invalid ");
        }
    }

    private static void checkSecondComponent(int i, int i2) {
        if (i2 < 0 || (i != 2 && i2 > 39)) {
            throw new IOException("ObjectIdentifier() -- Second oid component is invalid ");
        }
    }

    private static void checkSecondComponent(int i, BigInteger bigInteger) {
        if (bigInteger.signum() == -1 || (i != 2 && bigInteger.compareTo(BigInteger.valueOf(39)) == 1)) {
            throw new IOException("ObjectIdentifier() -- Second oid component is invalid ");
        }
    }

    private static void checkOtherComponent(int i, int i2) {
        if (i2 < 0) {
            throw new IOException("ObjectIdentifier() -- oid component #" + (i + 1) + " must be non-negative ");
        }
    }

    private static void checkOtherComponent(int i, BigInteger bigInteger) {
        if (bigInteger.signum() == -1) {
            throw new IOException("ObjectIdentifier() -- oid component #" + (i + 1) + " must be non-negative ");
        }
    }
}
