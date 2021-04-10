package java.math;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BigInteger extends Number implements Comparable, Serializable {
    static final BigInteger MINUS_ONE = new BigInteger(-1, 1);
    public static final BigInteger ONE = new BigInteger(1, 1);
    static final BigInteger[] SMALL_VALUES = {ZERO, ONE, new BigInteger(1, 2), new BigInteger(1, 3), new BigInteger(1, 4), new BigInteger(1, 5), new BigInteger(1, 6), new BigInteger(1, 7), new BigInteger(1, 8), new BigInteger(1, 9), TEN};
    public static final BigInteger TEN = new BigInteger(1, 10);
    public static final BigInteger ZERO = new BigInteger(0, 0);
    private static final long serialVersionUID = -8287574255936472291L;
    private transient BigInt bigInt;
    transient int[] digits;
    private transient int firstNonzeroDigit = -2;
    private transient int hashCode = 0;
    private transient boolean javaIsValid = false;
    private byte[] magnitude;
    private transient boolean nativeIsValid = false;
    transient int numberLength;
    transient int sign;
    private int signum;

    BigInteger(BigInt bigInt2) {
        if (bigInt2 == null || !bigInt2.hasNativeBignum()) {
            throw new AssertionError();
        }
        setBigInt(bigInt2);
    }

    BigInteger(int i, long j) {
        boolean z = false;
        BigInt bigInt2 = new BigInt();
        bigInt2.putULongInt(j, i < 0 ? true : z);
        setBigInt(bigInt2);
    }

    BigInteger(int i, int i2, int[] iArr) {
        setJavaRepresentation(i, i2, iArr);
    }

    public BigInteger(String str) {
        BigInt bigInt2 = new BigInt();
        bigInt2.putDecString(str);
        setBigInt(bigInt2);
    }

    public BigInteger(int i, byte[] bArr) {
        boolean z = false;
        if (bArr == null) {
            throw new NullPointerException("magnitude == null");
        } else if (i < -1 || i > 1) {
            throw new NumberFormatException("Invalid signum: " + i);
        } else {
            if (i == 0) {
                for (byte b : bArr) {
                    if (b != 0) {
                        throw new NumberFormatException("signum-magnitude mismatch");
                    }
                }
            }
            BigInt bigInt2 = new BigInt();
            bigInt2.putBigEndian(bArr, i < 0 ? true : z);
            setBigInt(bigInt2);
        }
    }

    public BigInteger(byte[] bArr) {
        if (bArr.length != 0) {
            BigInt bigInt2 = new BigInt();
            bigInt2.putBigEndianTwosComplement(bArr);
            setBigInt(bigInt2);
            return;
        }
        throw new NumberFormatException("value.length == 0");
    }

    /* access modifiers changed from: package-private */
    public BigInt getBigInt() {
        if (this.nativeIsValid) {
            return this.bigInt;
        }
        synchronized (this) {
            if (this.nativeIsValid) {
                return this.bigInt;
            }
            BigInt bigInt2 = new BigInt();
            bigInt2.putLittleEndianInts(this.digits, this.sign < 0);
            setBigInt(bigInt2);
            return bigInt2;
        }
    }

    private void setBigInt(BigInt bigInt2) {
        this.bigInt = bigInt2;
        this.nativeIsValid = true;
    }

    private void setJavaRepresentation(int i, int i2, int[] iArr) {
        while (i2 > 0) {
            i2--;
            if (iArr[i2] != 0) {
                break;
            }
        }
        int i3 = i2 + 1;
        if (iArr[i2] == 0) {
            i = 0;
        }
        this.sign = i;
        this.digits = iArr;
        this.numberLength = i3;
        this.javaIsValid = true;
    }

    /* access modifiers changed from: package-private */
    public void prepareJavaRepresentation() {
        if (!this.javaIsValid) {
            synchronized (this) {
                if (!this.javaIsValid) {
                    int sign2 = this.bigInt.sign();
                    int[] littleEndianIntsMagnitude = sign2 != 0 ? this.bigInt.littleEndianIntsMagnitude() : new int[]{0};
                    setJavaRepresentation(sign2, littleEndianIntsMagnitude.length, littleEndianIntsMagnitude);
                }
            }
        }
    }

    public static BigInteger valueOf(long j) {
        if (j >= 0) {
            BigInteger[] bigIntegerArr = SMALL_VALUES;
            if (j < ((long) bigIntegerArr.length)) {
                return bigIntegerArr[(int) j];
            }
            return new BigInteger(1, j);
        } else if (j != -1) {
            return new BigInteger(-1, -j);
        } else {
            return MINUS_ONE;
        }
    }

    public byte[] toByteArray() {
        return twosComplement();
    }

    public BigInteger abs() {
        BigInt bigInt2 = getBigInt();
        if (bigInt2.sign() >= 0) {
            return this;
        }
        BigInt copy = bigInt2.copy();
        copy.setSign(1);
        return new BigInteger(copy);
    }

    public BigInteger negate() {
        BigInt bigInt2 = getBigInt();
        int sign2 = bigInt2.sign();
        if (sign2 == 0) {
            return this;
        }
        BigInt copy = bigInt2.copy();
        copy.setSign(-sign2);
        return new BigInteger(copy);
    }

    public BigInteger add(BigInteger bigInteger) {
        BigInt bigInt2 = getBigInt();
        BigInt bigInt3 = bigInteger.getBigInt();
        if (bigInt3.sign() == 0) {
            return this;
        }
        if (bigInt2.sign() == 0) {
            return bigInteger;
        }
        return new BigInteger(BigInt.addition(bigInt2, bigInt3));
    }

    public BigInteger subtract(BigInteger bigInteger) {
        BigInt bigInt2 = getBigInt();
        BigInt bigInt3 = bigInteger.getBigInt();
        if (bigInt3.sign() == 0) {
            return this;
        }
        return new BigInteger(BigInt.subtraction(bigInt2, bigInt3));
    }

    public int signum() {
        if (this.javaIsValid) {
            return this.sign;
        }
        return getBigInt().sign();
    }

    public BigInteger shiftRight(int i) {
        return shiftLeft(-i);
    }

    public BigInteger shiftLeft(int i) {
        int signum2;
        if (i == 0 || (signum2 = signum()) == 0) {
            return this;
        }
        if (signum2 > 0 || i >= 0) {
            return new BigInteger(BigInt.shift(getBigInt(), i));
        }
        return BitLevel.shiftRight(this, -i);
    }

    /* access modifiers changed from: package-private */
    public BigInteger shiftLeftOneBit() {
        return signum() == 0 ? this : BitLevel.shiftLeftOneBit(this);
    }

    public int bitLength() {
        if (this.nativeIsValid || !this.javaIsValid) {
            return getBigInt().bitLength();
        }
        return BitLevel.bitLength(this);
    }

    public boolean testBit(int i) {
        if (i >= 0) {
            int signum2 = signum();
            if (signum2 > 0 && this.nativeIsValid && !this.javaIsValid) {
                return getBigInt().isBitSet(i);
            }
            prepareJavaRepresentation();
            if (i == 0) {
                return (this.digits[0] & 1) != 0;
            }
            int i2 = i >> 5;
            if (i2 >= this.numberLength) {
                return signum2 < 0;
            }
            int i3 = this.digits[i2];
            int i4 = 1 << (i & 31);
            if (signum2 < 0) {
                int firstNonzeroDigit2 = getFirstNonzeroDigit();
                if (i2 < firstNonzeroDigit2) {
                    return false;
                }
                i3 = firstNonzeroDigit2 == i2 ? -i3 : ~i3;
            }
            return (i3 & i4) != 0;
        }
        throw new ArithmeticException("n < 0: " + i);
    }

    public int getLowestSetBit() {
        prepareJavaRepresentation();
        if (this.sign == 0) {
            return -1;
        }
        int firstNonzeroDigit2 = getFirstNonzeroDigit();
        return (firstNonzeroDigit2 << 5) + Integer.numberOfTrailingZeros(this.digits[firstNonzeroDigit2]);
    }

    @Override // java.lang.Number
    public int intValue() {
        if (this.nativeIsValid && this.bigInt.twosCompFitsIntoBytes(4)) {
            return (int) this.bigInt.longInt();
        }
        prepareJavaRepresentation();
        return this.sign * this.digits[0];
    }

    @Override // java.lang.Number
    public long longValue() {
        long j;
        if (this.nativeIsValid && this.bigInt.twosCompFitsIntoBytes(8)) {
            return this.bigInt.longInt();
        }
        prepareJavaRepresentation();
        if (this.numberLength > 1) {
            int[] iArr = this.digits;
            j = (((long) iArr[0]) & 4294967295L) | (((long) iArr[1]) << 32);
        } else {
            j = ((long) this.digits[0]) & 4294967295L;
        }
        return ((long) this.sign) * j;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Conversion.bigInteger2Double(this);
    }

    public int compareTo(BigInteger bigInteger) {
        return BigInt.cmp(getBigInt(), bigInteger.getBigInt());
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            prepareJavaRepresentation();
            int i = 0;
            for (int i2 = 0; i2 < this.numberLength; i2++) {
                i = (i * 33) + this.digits[i2];
            }
            this.hashCode = i * this.sign;
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BigInteger) {
            return compareTo((BigInteger) obj) == 0;
        }
        return false;
    }

    public String toString() {
        return getBigInt().decString();
    }

    public String toString(int i) {
        if (i == 10) {
            return getBigInt().decString();
        }
        prepareJavaRepresentation();
        return Conversion.bigInteger2String(this, i);
    }

    public BigInteger gcd(BigInteger bigInteger) {
        return new BigInteger(BigInt.gcd(getBigInt(), bigInteger.getBigInt()));
    }

    public BigInteger multiply(BigInteger bigInteger) {
        return new BigInteger(BigInt.product(getBigInt(), bigInteger.getBigInt()));
    }

    public BigInteger pow(int i) {
        if (i >= 0) {
            return new BigInteger(BigInt.exp(getBigInt(), i));
        }
        throw new ArithmeticException("exp < 0: " + i);
    }

    public BigInteger[] divideAndRemainder(BigInteger bigInteger) {
        BigInt bigInt2 = bigInteger.getBigInt();
        BigInt bigInt3 = new BigInt();
        BigInt bigInt4 = new BigInt();
        BigInt.division(getBigInt(), bigInt2, bigInt3, bigInt4);
        return new BigInteger[]{new BigInteger(bigInt3), new BigInteger(bigInt4)};
    }

    public BigInteger divide(BigInteger bigInteger) {
        BigInt bigInt2 = new BigInt();
        BigInt.division(getBigInt(), bigInteger.getBigInt(), bigInt2, null);
        return new BigInteger(bigInt2);
    }

    private byte[] twosComplement() {
        int i;
        int i2;
        prepareJavaRepresentation();
        if (this.sign == 0) {
            return new byte[]{0};
        }
        int bitLength = bitLength();
        int firstNonzeroDigit2 = getFirstNonzeroDigit();
        int i3 = (bitLength >> 3) + 1;
        byte[] bArr = new byte[i3];
        int i4 = 4;
        if (i3 - (this.numberLength << 2) == 1) {
            bArr[0] = (byte) (this.sign < 0 ? -1 : 0);
            i = 1;
            i2 = 4;
        } else {
            i2 = i3 & 3;
            if (i2 == 0) {
                i2 = 4;
            }
            i = 0;
        }
        int i5 = i3 - (firstNonzeroDigit2 << 2);
        if (this.sign < 0) {
            int i6 = -this.digits[firstNonzeroDigit2];
            int i7 = firstNonzeroDigit2 + 1;
            if (i7 == this.numberLength) {
                i4 = i2;
            }
            int i8 = i6;
            int i9 = i5;
            int i10 = 0;
            while (i10 < i4) {
                i9--;
                bArr[i9] = (byte) i8;
                i10++;
                i8 >>= 8;
            }
            while (i9 > i) {
                int i11 = ~this.digits[i7];
                i7++;
                if (i7 == this.numberLength) {
                    i4 = i2;
                }
                int i12 = i11;
                int i13 = 0;
                while (i13 < i4) {
                    i9--;
                    bArr[i9] = (byte) i12;
                    i13++;
                    i12 >>= 8;
                }
            }
        } else {
            while (i5 > i) {
                int i14 = this.digits[firstNonzeroDigit2];
                firstNonzeroDigit2++;
                if (firstNonzeroDigit2 == this.numberLength) {
                    i4 = i2;
                }
                int i15 = i14;
                int i16 = i5;
                int i17 = 0;
                while (i17 < i4) {
                    i16--;
                    bArr[i16] = (byte) i15;
                    i17++;
                    i15 >>= 8;
                }
                i5 = i16;
            }
        }
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public int getFirstNonzeroDigit() {
        int i;
        if (this.firstNonzeroDigit == -2) {
            if (this.sign == 0) {
                i = -1;
            } else {
                i = 0;
                while (this.digits[i] == 0) {
                    i++;
                }
            }
            this.firstNonzeroDigit = i;
        }
        return this.firstNonzeroDigit;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        BigInt bigInt2 = getBigInt();
        this.signum = bigInt2.sign();
        this.magnitude = bigInt2.bigEndianMagnitude();
        objectOutputStream.defaultWriteObject();
        throw null;
    }
}
