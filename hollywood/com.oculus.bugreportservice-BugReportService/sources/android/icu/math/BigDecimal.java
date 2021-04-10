package android.icu.math;

import java.io.Serializable;
import java.math.BigInteger;

public class BigDecimal extends Number implements Serializable, Comparable {
    public static final BigDecimal ONE = new BigDecimal(1L);
    public static final BigDecimal TEN = new BigDecimal(10);
    public static final BigDecimal ZERO = new BigDecimal(0L);
    private static byte[] bytecar = new byte[190];
    private static byte[] bytedig = diginit();
    private static final MathContext plainMC = new MathContext(0, 0);
    private static final long serialVersionUID = 8245355804974198832L;
    private int exp;
    private byte form;
    private byte ind;
    private byte[] mant;

    public BigDecimal(java.math.BigDecimal bigDecimal) {
        this(bigDecimal.toString());
    }

    public BigDecimal(BigInteger bigInteger) {
        this(bigInteger.toString(10));
    }

    public BigDecimal(BigInteger bigInteger, int i) {
        this(bigInteger.toString(10));
        if (i >= 0) {
            this.exp = -i;
            return;
        }
        throw new NumberFormatException("Negative scale: " + i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:141:0x00e2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BigDecimal(char[] r17, int r18, int r19) {
        /*
        // Method dump skipped, instructions count: 425
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.<init>(char[], int, int):void");
    }

    public BigDecimal(int i) {
        this.form = 0;
        int i2 = 9;
        if (i > 9 || i < -9) {
            if (i > 0) {
                this.ind = 1;
                i = -i;
            } else {
                this.ind = -1;
            }
            int i3 = i;
            while (true) {
                i3 /= 10;
                if (i3 == 0) {
                    break;
                }
                i2--;
            }
            int i4 = 10 - i2;
            this.mant = new byte[i4];
            int i5 = i4 - 1;
            while (true) {
                this.mant[i5] = (byte) (-((byte) (i % 10)));
                i /= 10;
                if (i != 0) {
                    i5--;
                } else {
                    return;
                }
            }
        } else if (i == 0) {
            this.mant = ZERO.mant;
            this.ind = 0;
        } else if (i == 1) {
            this.mant = ONE.mant;
            this.ind = 1;
        } else if (i == -1) {
            this.mant = ONE.mant;
            this.ind = -1;
        } else {
            this.mant = new byte[1];
            if (i > 0) {
                this.mant[0] = (byte) i;
                this.ind = 1;
                return;
            }
            this.mant[0] = (byte) (-i);
            this.ind = -1;
        }
    }

    public BigDecimal(long j) {
        this.form = 0;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i > 0) {
            this.ind = 1;
            j = -j;
        } else if (i == 0) {
            this.ind = 0;
        } else {
            this.ind = -1;
        }
        int i2 = 18;
        long j2 = j;
        while (true) {
            j2 /= 10;
            if (j2 == 0) {
                break;
            }
            i2--;
        }
        int i3 = 19 - i2;
        this.mant = new byte[i3];
        int i4 = i3 - 1;
        while (true) {
            this.mant[i4] = (byte) (-((byte) ((int) (j % 10))));
            j /= 10;
            if (j != 0) {
                i4--;
            } else {
                return;
            }
        }
    }

    public BigDecimal(String str) {
        this(str.toCharArray(), 0, str.length());
    }

    private BigDecimal() {
        this.form = 0;
    }

    public BigDecimal add(BigDecimal bigDecimal, MathContext mathContext) {
        int i;
        int i2;
        byte[] bArr;
        int i3;
        byte[] bArr2;
        boolean z;
        byte b;
        byte b2;
        BigDecimal bigDecimal2 = this;
        BigDecimal bigDecimal3 = bigDecimal;
        if (mathContext.lostDigits) {
            bigDecimal2.checkdigits(bigDecimal3, mathContext.digits);
        }
        if (bigDecimal2.ind == 0 && mathContext.form != 0) {
            return bigDecimal.plus(mathContext);
        }
        if (bigDecimal3.ind == 0 && mathContext.form != 0) {
            return bigDecimal2.plus(mathContext);
        }
        int i4 = mathContext.digits;
        if (i4 > 0) {
            if (bigDecimal2.mant.length > i4) {
                bigDecimal2 = clone(this);
                bigDecimal2.round(mathContext);
            }
            if (bigDecimal3.mant.length > i4) {
                bigDecimal3 = clone(bigDecimal);
                bigDecimal3.round(mathContext);
            }
        }
        BigDecimal bigDecimal4 = new BigDecimal();
        byte[] bArr3 = bigDecimal2.mant;
        int length = bArr3.length;
        byte[] bArr4 = bigDecimal3.mant;
        int length2 = bArr4.length;
        int i5 = bigDecimal2.exp;
        int i6 = bigDecimal3.exp;
        if (i5 == i6) {
            bigDecimal4.exp = i5;
        } else if (i5 > i6) {
            int i7 = (length + i5) - i6;
            if (i7 < length2 + i4 + 1 || i4 <= 0) {
                bigDecimal4.exp = bigDecimal3.exp;
                int i8 = i4 + 1;
                if (i7 > i8 && i4 > 0) {
                    int i9 = (i7 - i4) - 1;
                    length2 -= i9;
                    bigDecimal4.exp += i9;
                    i7 = i8;
                }
                if (i7 > length) {
                    length = i7;
                }
            } else {
                bigDecimal4.mant = bArr3;
                bigDecimal4.exp = i5;
                bigDecimal4.ind = bigDecimal2.ind;
                if (length < i4) {
                    bigDecimal4.mant = extend(bigDecimal2.mant, i4);
                    bigDecimal4.exp -= i4 - length;
                }
                bigDecimal4.finish(mathContext, false);
                return bigDecimal4;
            }
        } else {
            int i10 = (length2 + i6) - i5;
            if (i10 < length + i4 + 1 || i4 <= 0) {
                bigDecimal4.exp = bigDecimal2.exp;
                int i11 = i4 + 1;
                if (i10 > i11 && i4 > 0) {
                    int i12 = (i10 - i4) - 1;
                    length -= i12;
                    bigDecimal4.exp += i12;
                    i10 = i11;
                }
                if (i10 > length2) {
                    length2 = i10;
                }
            } else {
                bigDecimal4.mant = bArr4;
                bigDecimal4.exp = i6;
                bigDecimal4.ind = bigDecimal3.ind;
                if (length2 < i4) {
                    bigDecimal4.mant = extend(bigDecimal3.mant, i4);
                    bigDecimal4.exp -= i4 - length2;
                }
                bigDecimal4.finish(mathContext, false);
                return bigDecimal4;
            }
        }
        byte b3 = bigDecimal2.ind;
        if (b3 == 0) {
            bigDecimal4.ind = 1;
        } else {
            bigDecimal4.ind = b3;
        }
        if ((bigDecimal2.ind == -1) == (bigDecimal3.ind == -1)) {
            i3 = length;
            bArr = bArr4;
            i2 = length2;
            i = 1;
            bArr2 = bArr3;
        } else {
            if (bigDecimal3.ind != 0) {
                if ((bigDecimal2.ind == 0) || (length < length2)) {
                    bigDecimal4.ind = (byte) (-bigDecimal4.ind);
                } else if (length <= length2) {
                    int length3 = bArr3.length - 1;
                    int length4 = bArr4.length - 1;
                    int i13 = 0;
                    int i14 = 0;
                    while (true) {
                        if (i13 <= length3) {
                            b = bArr3[i13];
                        } else if (i14 <= length4) {
                            b = 0;
                        } else if (mathContext.form != 0) {
                            return ZERO;
                        }
                        if (i14 <= length4) {
                            b2 = bArr4[i14];
                        } else {
                            b2 = 0;
                        }
                        if (b == b2) {
                            i13++;
                            i14++;
                        } else if (b < b2) {
                            bigDecimal4.ind = (byte) (-bigDecimal4.ind);
                        }
                    }
                }
                bArr = bArr3;
                i2 = length;
                bArr2 = bArr4;
                i3 = length2;
                i = -1;
            }
            bArr2 = bArr3;
            i3 = length;
            bArr = bArr4;
            i2 = length2;
            i = -1;
        }
        bigDecimal4.mant = byteaddsub(bArr2, i3, bArr, i2, i, false);
        bigDecimal4.finish(mathContext, false);
        return bigDecimal4;
    }

    public int compareTo(BigDecimal bigDecimal) {
        return compareTo(bigDecimal, plainMC);
    }

    public int compareTo(BigDecimal bigDecimal, MathContext mathContext) {
        if (mathContext.lostDigits) {
            checkdigits(bigDecimal, mathContext.digits);
        }
        boolean z = true;
        if ((this.ind == bigDecimal.ind) && (this.exp == bigDecimal.exp)) {
            int length = this.mant.length;
            byte[] bArr = bigDecimal.mant;
            if (length < bArr.length) {
                return (byte) (-this.ind);
            }
            if (length > bArr.length) {
                return this.ind;
            }
            boolean z2 = length <= mathContext.digits;
            if (mathContext.digits != 0) {
                z = false;
            }
            if (z2 || z) {
                int i = 0;
                while (length > 0) {
                    byte[] bArr2 = this.mant;
                    byte b = bArr2[i];
                    byte[] bArr3 = bigDecimal.mant;
                    if (b < bArr3[i]) {
                        return (byte) (-this.ind);
                    }
                    if (bArr2[i] > bArr3[i]) {
                        return this.ind;
                    }
                    length--;
                    i++;
                }
                return 0;
            }
        } else {
            byte b2 = this.ind;
            byte b3 = bigDecimal.ind;
            if (b2 < b3) {
                return -1;
            }
            if (b2 > b3) {
                return 1;
            }
        }
        BigDecimal clone = clone(bigDecimal);
        clone.ind = (byte) (-clone.ind);
        return add(clone, mathContext).ind;
    }

    public BigDecimal divide(BigDecimal bigDecimal, int i, int i2) {
        if (i >= 0) {
            return dodivide('D', bigDecimal, new MathContext(0, 0, false, i2), i);
        }
        throw new ArithmeticException("Negative scale: " + i);
    }

    public BigDecimal divide(BigDecimal bigDecimal, MathContext mathContext) {
        return dodivide('D', bigDecimal, mathContext, -1);
    }

    public BigDecimal multiply(BigDecimal bigDecimal, MathContext mathContext) {
        int i;
        byte[] bArr;
        BigDecimal bigDecimal2 = this;
        BigDecimal bigDecimal3 = bigDecimal;
        if (mathContext.lostDigits) {
            bigDecimal2.checkdigits(bigDecimal3, mathContext.digits);
        }
        int i2 = mathContext.digits;
        if (i2 > 0) {
            if (bigDecimal2.mant.length > i2) {
                bigDecimal2 = clone(this);
                bigDecimal2.round(mathContext);
            }
            if (bigDecimal3.mant.length > i2) {
                bigDecimal3 = clone(bigDecimal);
                bigDecimal3.round(mathContext);
            }
            i = 0;
        } else {
            int i3 = bigDecimal2.exp;
            i = i3 > 0 ? i3 + 0 : 0;
            int i4 = bigDecimal3.exp;
            if (i4 > 0) {
                i += i4;
            }
        }
        byte[] bArr2 = bigDecimal2.mant;
        int length = bArr2.length;
        byte[] bArr3 = bigDecimal3.mant;
        if (length < bArr3.length) {
            bArr = bArr3;
        } else {
            bArr = bArr2;
            bArr2 = bArr3;
        }
        int length2 = (bArr2.length + bArr.length) - 1;
        int i5 = bArr2[0] * bArr[0] > 9 ? length2 + 1 : length2;
        BigDecimal bigDecimal4 = new BigDecimal();
        int i6 = 0;
        int i7 = length2;
        byte[] bArr4 = new byte[i5];
        int length3 = bArr2.length;
        while (length3 > 0) {
            byte b = bArr2[i6];
            if (b != 0) {
                bArr4 = byteaddsub(bArr4, bArr4.length, bArr, i7, b, true);
            }
            i7--;
            length3--;
            i6++;
        }
        bigDecimal4.ind = (byte) (bigDecimal2.ind * bigDecimal3.ind);
        bigDecimal4.exp = (bigDecimal2.exp + bigDecimal3.exp) - i;
        if (i == 0) {
            bigDecimal4.mant = bArr4;
        } else {
            bigDecimal4.mant = extend(bArr4, bArr4.length + i);
        }
        bigDecimal4.finish(mathContext, false);
        return bigDecimal4;
    }

    public BigDecimal plus(MathContext mathContext) {
        int i;
        if (mathContext.lostDigits) {
            checkdigits(null, mathContext.digits);
        }
        if (mathContext.form == 0 && this.form == 0 && (this.mant.length <= (i = mathContext.digits) || i == 0)) {
            return this;
        }
        BigDecimal clone = clone(this);
        clone.finish(mathContext, false);
        return clone;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.valueOf(toString()).doubleValue();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BigDecimal)) {
            return false;
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        if (this.ind != bigDecimal.ind) {
            return false;
        }
        if (((this.mant.length == bigDecimal.mant.length) & (this.exp == bigDecimal.exp)) && (this.form == bigDecimal.form)) {
            int length = this.mant.length;
            int i = 0;
            while (length > 0) {
                if (this.mant[i] != bigDecimal.mant[i]) {
                    return false;
                }
                length--;
                i++;
            }
        } else {
            char[] layout = layout();
            char[] layout2 = bigDecimal.layout();
            if (layout.length != layout2.length) {
                return false;
            }
            int length2 = layout.length;
            int i2 = 0;
            while (length2 > 0) {
                if (layout[i2] != layout2[i2]) {
                    return false;
                }
                length2--;
                i2++;
            }
        }
        return true;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return toBigInteger().intValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return toBigInteger().longValue();
    }

    public BigDecimal movePointLeft(int i) {
        BigDecimal clone = clone(this);
        clone.exp -= i;
        clone.finish(plainMC, false);
        return clone;
    }

    public BigDecimal movePointRight(int i) {
        BigDecimal clone = clone(this);
        clone.exp += i;
        clone.finish(plainMC, false);
        return clone;
    }

    public int scale() {
        int i = this.exp;
        if (i >= 0) {
            return 0;
        }
        return -i;
    }

    public BigDecimal setScale(int i) {
        return setScale(i, 7);
    }

    public BigDecimal setScale(int i, int i2) {
        int scale = scale();
        if (scale == i && this.form == 0) {
            return this;
        }
        BigDecimal clone = clone(this);
        if (scale <= i) {
            int i3 = scale == 0 ? clone.exp + i : i - scale;
            byte[] bArr = clone.mant;
            clone.mant = extend(bArr, bArr.length + i3);
            clone.exp = -i;
        } else if (i >= 0) {
            clone.round(clone.mant.length - (scale - i), i2);
            if (clone.exp != (-i)) {
                byte[] bArr2 = clone.mant;
                clone.mant = extend(bArr2, bArr2.length + 1);
                clone.exp--;
            }
        } else {
            throw new ArithmeticException("Negative scale: " + i);
        }
        clone.form = 0;
        return clone;
    }

    public int signum() {
        return this.ind;
    }

    public java.math.BigDecimal toBigDecimal() {
        return new java.math.BigDecimal(unscaledValue(), scale());
    }

    public BigInteger toBigInteger() {
        boolean z = true;
        boolean z2 = this.exp >= 0;
        if (this.form != 0) {
            z = false;
        }
        if (!z2 || !z) {
            int i = this.exp;
            if (i >= 0) {
                this = clone(this);
                this.form = 0;
            } else if ((-i) >= this.mant.length) {
                this = ZERO;
            } else {
                this = clone(this);
                byte[] bArr = this.mant;
                int length = bArr.length + this.exp;
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, 0, bArr2, 0, length);
                this.mant = bArr2;
                this.form = 0;
                this.exp = 0;
            }
        }
        new String(this.layout());
        throw null;
    }

    public String toString() {
        new String(layout());
        throw null;
    }

    public BigInteger unscaledValue() {
        if (this.exp < 0) {
            this = clone(this);
            this.exp = 0;
        }
        return this.toBigInteger();
    }

    public static BigDecimal valueOf(double d) {
        return new BigDecimal(new Double(d).toString());
    }

    public static BigDecimal valueOf(long j) {
        return valueOf(j, 0);
    }

    public static BigDecimal valueOf(long j, int i) {
        BigDecimal bigDecimal;
        if (j == 0) {
            bigDecimal = ZERO;
        } else if (j == 1) {
            bigDecimal = ONE;
        } else if (j == 10) {
            bigDecimal = TEN;
        } else {
            bigDecimal = new BigDecimal(j);
        }
        if (i == 0) {
            return bigDecimal;
        }
        if (i >= 0) {
            BigDecimal clone = clone(bigDecimal);
            clone.exp = -i;
            return clone;
        }
        throw new NumberFormatException("Negative scale: " + i);
    }

    private char[] layout() {
        byte[] bArr = this.mant;
        char[] cArr = new char[bArr.length];
        int length = bArr.length;
        int i = 0;
        while (length > 0) {
            cArr[i] = (char) (this.mant[i] + 48);
            length--;
            i++;
        }
        char c = '-';
        if (this.form != 0) {
            StringBuilder sb = new StringBuilder(cArr.length + 15);
            if (this.ind == -1) {
                sb.append('-');
            }
            int length2 = (this.exp + cArr.length) - 1;
            if (this.form == 1) {
                sb.append(cArr[0]);
                if (cArr.length > 1) {
                    sb.append('.');
                    sb.append(cArr, 1, cArr.length - 1);
                }
            } else {
                int i2 = length2 % 3;
                if (i2 < 0) {
                    i2 += 3;
                }
                length2 -= i2;
                int i3 = i2 + 1;
                if (i3 >= cArr.length) {
                    sb.append(cArr, 0, cArr.length);
                    for (int length3 = i3 - cArr.length; length3 > 0; length3--) {
                        sb.append('0');
                    }
                } else {
                    sb.append(cArr, 0, i3);
                    sb.append('.');
                    sb.append(cArr, i3, cArr.length - i3);
                }
            }
            if (length2 != 0) {
                if (length2 < 0) {
                    length2 = -length2;
                } else {
                    c = '+';
                }
                sb.append('E');
                sb.append(c);
                sb.append(length2);
            }
            char[] cArr2 = new char[sb.length()];
            int length4 = sb.length();
            if (length4 != 0) {
                sb.getChars(0, length4, cArr2, 0);
            }
            return cArr2;
        } else if (this.exp != 0) {
            int i4 = this.ind == -1 ? 1 : 0;
            int i5 = this.exp;
            int length5 = cArr.length + i5;
            if (length5 < 1) {
                int i6 = i4 + 2;
                char[] cArr3 = new char[(i6 - i5)];
                if (i4 != 0) {
                    cArr3[0] = '-';
                }
                cArr3[i4] = '0';
                cArr3[i4 + 1] = '.';
                int i7 = -length5;
                int i8 = i6;
                while (i7 > 0) {
                    cArr3[i8] = '0';
                    i7--;
                    i8++;
                }
                System.arraycopy((Object) cArr, 0, (Object) cArr3, i6 - length5, cArr.length);
                return cArr3;
            } else if (length5 > cArr.length) {
                char[] cArr4 = new char[(i4 + length5)];
                if (i4 != 0) {
                    cArr4[0] = '-';
                }
                System.arraycopy((Object) cArr, 0, (Object) cArr4, i4, cArr.length);
                int length6 = length5 - cArr.length;
                int length7 = i4 + cArr.length;
                while (length6 > 0) {
                    cArr4[length7] = '0';
                    length6--;
                    length7++;
                }
                return cArr4;
            } else {
                char[] cArr5 = new char[(i4 + 1 + cArr.length)];
                if (i4 != 0) {
                    cArr5[0] = '-';
                }
                System.arraycopy((Object) cArr, 0, (Object) cArr5, i4, length5);
                int i9 = i4 + length5;
                cArr5[i9] = '.';
                System.arraycopy((Object) cArr, length5, (Object) cArr5, i9 + 1, cArr.length - length5);
                return cArr5;
            }
        } else if (this.ind >= 0) {
            return cArr;
        } else {
            char[] cArr6 = new char[(cArr.length + 1)];
            cArr6[0] = '-';
            System.arraycopy((Object) cArr, 0, (Object) cArr6, 1, cArr.length);
            return cArr6;
        }
    }

    private BigDecimal dodivide(char c, BigDecimal bigDecimal, MathContext mathContext, int i) {
        byte[] bArr;
        int i2;
        byte[] bArr2;
        boolean z;
        boolean z2;
        byte[] bArr3;
        int i3;
        int i4;
        int i5;
        int i6;
        byte b;
        BigDecimal bigDecimal2 = this;
        BigDecimal bigDecimal3 = bigDecimal;
        int i7 = i;
        if (mathContext.lostDigits) {
            bigDecimal2.checkdigits(bigDecimal3, mathContext.digits);
        }
        if (bigDecimal3.ind == 0) {
            throw new ArithmeticException("Divide by 0");
        } else if (bigDecimal2.ind != 0) {
            int i8 = mathContext.digits;
            if (i8 > 0) {
                if (bigDecimal2.mant.length > i8) {
                    bigDecimal2 = clone(this);
                    bigDecimal2.round(mathContext);
                }
                if (bigDecimal3.mant.length > i8) {
                    bigDecimal3 = clone(bigDecimal);
                    bigDecimal3.round(mathContext);
                }
            } else {
                if (i7 == -1) {
                    i7 = scale();
                }
                int length = bigDecimal2.mant.length;
                int i9 = bigDecimal2.exp;
                if (i7 != (-i9)) {
                    length = length + i7 + i9;
                }
                i8 = (length - (bigDecimal3.mant.length - 1)) - bigDecimal3.exp;
                byte[] bArr4 = bigDecimal2.mant;
                if (i8 < bArr4.length) {
                    i8 = bArr4.length;
                }
                byte[] bArr5 = bigDecimal3.mant;
                if (i8 < bArr5.length) {
                    i8 = bArr5.length;
                }
            }
            int length2 = ((bigDecimal2.exp - bigDecimal3.exp) + bigDecimal2.mant.length) - bigDecimal3.mant.length;
            int i10 = 0;
            if (length2 >= 0 || c == 'D') {
                BigDecimal bigDecimal4 = new BigDecimal();
                bigDecimal4.ind = (byte) (bigDecimal2.ind * bigDecimal3.ind);
                bigDecimal4.exp = length2;
                int i11 = i8 + 1;
                bigDecimal4.mant = new byte[i11];
                int i12 = i8 + i8 + 1;
                byte[] extend = extend(bigDecimal2.mant, i12);
                byte[] bArr6 = bigDecimal3.mant;
                int i13 = (bArr6[0] * 10) + 1;
                if (bArr6.length > 1) {
                    i13 += bArr6[1];
                }
                int i14 = i13;
                int i15 = 0;
                int i16 = i12;
                byte[] bArr7 = extend;
                int i17 = i16;
                loop0:
                while (true) {
                    int i18 = i10;
                    bArr = bArr7;
                    while (true) {
                        if (i17 < i16) {
                            i2 = i14;
                            bArr2 = bArr6;
                            break;
                        }
                        if (i17 == i16) {
                            int i19 = i17;
                            while (true) {
                                if (i19 <= 0) {
                                    bigDecimal4.mant[i15] = (byte) (i18 + 1);
                                    i15++;
                                    bArr[0] = 0;
                                    break loop0;
                                }
                                i2 = i14;
                                if (i10 < bArr6.length) {
                                    b = bArr6[i10];
                                    bArr2 = bArr6;
                                } else {
                                    bArr2 = bArr6;
                                    b = 0;
                                }
                                if (bArr[i10] < b) {
                                    break;
                                } else if (bArr[i10] > b) {
                                    i4 = 1;
                                    i5 = bArr[0];
                                    break;
                                } else {
                                    i19--;
                                    i10++;
                                    bArr6 = bArr2;
                                    i14 = i2;
                                }
                            }
                        } else {
                            i2 = i14;
                            bArr2 = bArr6;
                            i4 = 1;
                            int i20 = bArr[i10] * 10;
                            i5 = i20;
                            if (i17 > 1) {
                                i5 = i20 + bArr[1];
                            }
                        }
                        int i21 = (i5 * 10) / i2;
                        if (i21 == 0) {
                            i21 = i4;
                        }
                        i18 += i21;
                        byte[] byteaddsub = byteaddsub(bArr, i17, bArr2, i16, -i21, true);
                        if (byteaddsub[0] != 0) {
                            bArr6 = bArr2;
                            bArr = byteaddsub;
                            i16 = i16;
                            i14 = i2;
                            i10 = 0;
                        } else {
                            int i22 = i17 - 2;
                            int i23 = i17;
                            int i24 = 0;
                            while (i24 <= i22 && byteaddsub[i24] == 0) {
                                i23--;
                                i24++;
                            }
                            if (i24 == 0) {
                                i6 = 0;
                            } else {
                                i6 = 0;
                                System.arraycopy(byteaddsub, i24, byteaddsub, 0, i23);
                            }
                            i17 = i23;
                            i14 = i2;
                            bArr6 = bArr2;
                            bArr = byteaddsub;
                            i10 = i6;
                            i16 = i16;
                        }
                    }
                    if ((i15 != 0) || (i18 != 0)) {
                        bigDecimal4.mant[i15] = (byte) i18;
                        i3 = i15 + 1;
                        if (!(i3 == i11 || bArr[0] == 0)) {
                            i15 = i3;
                        }
                    }
                    if ((i7 >= 0 && (-bigDecimal4.exp) > i7) || (c != 'D' && bigDecimal4.exp <= 0)) {
                        break;
                    }
                    bigDecimal4.exp--;
                    i16--;
                    bArr7 = bArr;
                    bArr6 = bArr2;
                    i14 = i2;
                    i10 = 0;
                }
                i15 = i3;
                int i25 = i15 == 0 ? 1 : i15;
                if ((c == 'I') || (c == 'R')) {
                    int i26 = bigDecimal4.exp;
                    if (i25 + i26 > i8) {
                        throw new ArithmeticException("Integer overflow");
                    } else if (c == 'R') {
                        if (bigDecimal4.mant[0] == 0) {
                            BigDecimal clone = clone(bigDecimal2);
                            clone.finish(mathContext, false);
                            return clone;
                        } else if (bArr[0] == 0) {
                            return ZERO;
                        } else {
                            bigDecimal4.ind = bigDecimal2.ind;
                            bigDecimal4.exp = (i26 - (i12 - bigDecimal2.mant.length)) + bigDecimal2.exp;
                            for (int i27 = i17 - 1; i27 >= 1; i27--) {
                                if ((!(bigDecimal4.exp < bigDecimal2.exp) || !(bigDecimal4.exp < bigDecimal3.exp)) || bArr[i27] != 0) {
                                    break;
                                }
                                i17--;
                                bigDecimal4.exp++;
                            }
                            if (i17 < bArr.length) {
                                bArr3 = new byte[i17];
                                z2 = false;
                                System.arraycopy(bArr, 0, bArr3, 0, i17);
                            } else {
                                z2 = false;
                                bArr3 = bArr;
                            }
                            bigDecimal4.mant = bArr3;
                            bigDecimal4.finish(mathContext, z2);
                            return bigDecimal4;
                        }
                    }
                } else if (bArr[0] != 0) {
                    byte[] bArr8 = bigDecimal4.mant;
                    int i28 = i25 - 1;
                    byte b2 = bArr8[i28];
                    if (b2 % 5 == 0) {
                        bArr8[i28] = (byte) (b2 + 1);
                    }
                }
                if (i7 >= 0) {
                    byte[] bArr9 = bigDecimal4.mant;
                    if (i25 != bArr9.length) {
                        bigDecimal4.exp -= bArr9.length - i25;
                    }
                    bigDecimal4.round(bigDecimal4.mant.length - ((-bigDecimal4.exp) - i7), mathContext.roundingMode);
                    if (bigDecimal4.exp != (-i7)) {
                        byte[] bArr10 = bigDecimal4.mant;
                        z = true;
                        bigDecimal4.mant = extend(bArr10, bArr10.length + 1);
                        bigDecimal4.exp--;
                    } else {
                        z = true;
                    }
                    bigDecimal4.finish(mathContext, z);
                    return bigDecimal4;
                }
                byte[] bArr11 = bigDecimal4.mant;
                if (i25 == bArr11.length) {
                    bigDecimal4.round(mathContext);
                } else if (bArr11[0] == 0) {
                    return ZERO;
                } else {
                    byte[] bArr12 = new byte[i25];
                    System.arraycopy(bArr11, 0, bArr12, 0, i25);
                    bigDecimal4.mant = bArr12;
                }
                bigDecimal4.finish(mathContext, true);
                return bigDecimal4;
            } else if (c == 'I') {
                return ZERO;
            } else {
                BigDecimal clone2 = clone(bigDecimal2);
                clone2.finish(mathContext, false);
                return clone2;
            }
        } else if (mathContext.form != 0) {
            return ZERO;
        } else {
            if (i7 == -1) {
                return bigDecimal2;
            }
            return bigDecimal2.setScale(i7);
        }
    }

    private void bad(char[] cArr) {
        new StringBuilder().append("Not a number: ");
        String.valueOf(cArr);
        throw null;
    }

    private static final byte[] extend(byte[] bArr, int i) {
        if (bArr.length == i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:55:0x006b */
    private static final byte[] byteaddsub(byte[] bArr, int i, byte[] bArr2, int i2, int i3, boolean z) {
        byte b;
        byte b2;
        byte[] bArr3 = bArr;
        int length = bArr3.length;
        int length2 = bArr2.length;
        int i4 = i - 1;
        int i5 = i2 - 1;
        int i6 = i5 < i4 ? i4 : i5;
        byte[] bArr4 = (!z || i6 + 1 != length) ? null : bArr3;
        if (bArr4 == null) {
            bArr4 = new byte[(i6 + 1)];
        }
        int i7 = 0;
        boolean z2 = i3 == 1 || i3 == -1;
        int i8 = i4;
        int i9 = i5;
        int i10 = i6;
        int i11 = 0;
        while (i10 >= 0) {
            if (i8 >= 0) {
                if (i8 < length) {
                    i11 += bArr3[i8];
                }
                i8--;
            }
            if (i9 >= 0) {
                if (i9 < length2) {
                    if (!z2) {
                        b2 = bArr2[i9] * i3;
                    } else if (i3 > 0) {
                        b2 = bArr2[i9];
                    } else {
                        i11 -= bArr2[i9];
                    }
                    i11 += b2 == 1 ? 1 : 0;
                }
                i9--;
            }
            if (i11 >= 10 || i11 < 0) {
                int i12 = i11 + 90;
                bArr4[i10] = bytedig[i12];
                b = bytecar[i12];
            } else {
                bArr4[i10] = (byte) i11;
                b = 0;
            }
            i10--;
            i11 = b;
        }
        if (i11 == 0) {
            return bArr4;
        }
        if (!z || i6 + 2 != bArr3.length) {
            bArr3 = null;
        }
        if (bArr3 == null) {
            bArr3 = new byte[(i6 + 2)];
        }
        bArr3[0] = (byte) i11;
        if (i6 < 10) {
            int i13 = i6 + 1;
            while (i13 > 0) {
                int i14 = i7 + 1;
                bArr3[i14] = bArr4[i7];
                i13--;
                i7 = i14;
            }
        } else {
            System.arraycopy(bArr4, 0, bArr3, 1, i6 + 1);
        }
        return bArr3;
    }

    private static final byte[] diginit() {
        byte[] bArr = new byte[190];
        for (int i = 0; i <= 189; i++) {
            int i2 = i - 90;
            if (i2 >= 0) {
                bArr[i] = (byte) (i2 % 10);
                bytecar[i] = (byte) (i2 / 10);
            } else {
                int i3 = i2 + 100;
                bArr[i] = (byte) (i3 % 10);
                bytecar[i] = (byte) ((i3 / 10) - 10);
            }
        }
        return bArr;
    }

    private static final BigDecimal clone(BigDecimal bigDecimal) {
        BigDecimal bigDecimal2 = new BigDecimal();
        bigDecimal2.ind = bigDecimal.ind;
        bigDecimal2.exp = bigDecimal.exp;
        bigDecimal2.form = bigDecimal.form;
        bigDecimal2.mant = bigDecimal.mant;
        return bigDecimal2;
    }

    private void checkdigits(BigDecimal bigDecimal, int i) {
        if (i != 0) {
            byte[] bArr = this.mant;
            if (bArr.length > i && !allzero(bArr, i)) {
                throw new ArithmeticException("Too many digits: " + toString());
            } else if (bigDecimal != null) {
                byte[] bArr2 = bigDecimal.mant;
                if (bArr2.length > i && !allzero(bArr2, i)) {
                    throw new ArithmeticException("Too many digits: " + bigDecimal.toString());
                }
            }
        }
    }

    private BigDecimal round(MathContext mathContext) {
        round(mathContext.digits, mathContext.roundingMode);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v6, types: [int] */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        if (r4 >= 5) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        if (allzero(r0, r12 + 1) == false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0070, code lost:
        if ((r12[r12.length - 1] % 2) != 0) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007c, code lost:
        if (allzero(r0, r12) == false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0087, code lost:
        if (allzero(r0, r12) == false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0093, code lost:
        if (allzero(r0, r12) == false) goto L_0x0097;
     */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00d3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d4  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.math.BigDecimal round(int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 260
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.round(int, int):android.icu.math.BigDecimal");
    }

    private static final boolean allzero(byte[] bArr, int i) {
        if (i < 0) {
            i = 0;
        }
        int length = bArr.length - 1;
        while (i <= length) {
            if (bArr[i] != 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0097, code lost:
        if (r9 <= 999999999) goto L_0x00af;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.math.BigDecimal finish(android.icu.math.MathContext r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 229
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.finish(android.icu.math.MathContext, boolean):android.icu.math.BigDecimal");
    }
}
