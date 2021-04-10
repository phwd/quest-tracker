package sun.misc;

import java.util.Arrays;
import java.util.regex.Pattern;

public class FloatingDecimal {
    static final ASCIIToBinaryConverter A2BC_NEGATIVE_INFINITY = new PreparedASCIIToBinaryBuffer(Double.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    static final ASCIIToBinaryConverter A2BC_NEGATIVE_ZERO = new PreparedASCIIToBinaryBuffer(-0.0d, -0.0f);
    static final ASCIIToBinaryConverter A2BC_NOT_A_NUMBER = new PreparedASCIIToBinaryBuffer(Double.NaN, Float.NaN);
    static final ASCIIToBinaryConverter A2BC_POSITIVE_INFINITY = new PreparedASCIIToBinaryBuffer(Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    static final ASCIIToBinaryConverter A2BC_POSITIVE_ZERO = new PreparedASCIIToBinaryBuffer(0.0d, 0.0f);
    private static final BinaryToASCIIConverter B2AC_NEGATIVE_INFINITY = new ExceptionalBinaryToASCIIBuffer("-Infinity", true);
    private static final BinaryToASCIIConverter B2AC_NEGATIVE_ZERO = new BinaryToASCIIBuffer(true, new char[]{'0'});
    private static final BinaryToASCIIConverter B2AC_NOT_A_NUMBER = new ExceptionalBinaryToASCIIBuffer("NaN", false);
    private static final BinaryToASCIIConverter B2AC_POSITIVE_INFINITY = new ExceptionalBinaryToASCIIBuffer("Infinity", false);
    private static final BinaryToASCIIConverter B2AC_POSITIVE_ZERO = new BinaryToASCIIBuffer(false, new char[]{'0'});
    private static final int INFINITY_LENGTH = 8;
    private static final int NAN_LENGTH = 3;
    private static final ThreadLocal threadLocalBinaryToASCIIBuffer = new ThreadLocal() {
        /* class sun.misc.FloatingDecimal.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public BinaryToASCIIBuffer initialValue() {
            return new BinaryToASCIIBuffer();
        }
    };

    /* access modifiers changed from: package-private */
    public interface ASCIIToBinaryConverter {
        double doubleValue();
    }

    public interface BinaryToASCIIConverter {
        void appendTo(Appendable appendable);

        int getDecimalExponent();

        int getDigits(char[] cArr);

        boolean isExceptional();

        boolean isNegative();

        String toJavaFormatString();
    }

    /* access modifiers changed from: private */
    public static class HexFloatPattern {
        private static final Pattern VALUE = Pattern.compile("([-+])?0[xX](((\\p{XDigit}+)\\.?)|((\\p{XDigit}*)\\.(\\p{XDigit}+)))[pP]([-+])?(\\p{Digit}+)[fFdD]?");
    }

    public static String toJavaFormatString(double d) {
        return getBinaryToASCIIConverter(d).toJavaFormatString();
    }

    public static String toJavaFormatString(float f) {
        return getBinaryToASCIIConverter(f).toJavaFormatString();
    }

    public static void appendTo(double d, Appendable appendable) {
        getBinaryToASCIIConverter(d).appendTo(appendable);
    }

    public static void appendTo(float f, Appendable appendable) {
        getBinaryToASCIIConverter(f).appendTo(appendable);
    }

    public static double parseDouble(String str) {
        return readJavaFormatString(str).doubleValue();
    }

    private static class ExceptionalBinaryToASCIIBuffer implements BinaryToASCIIConverter {
        private final String image;
        private boolean isNegative;

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isExceptional() {
            return true;
        }

        public ExceptionalBinaryToASCIIBuffer(String str, boolean z) {
            this.image = str;
            this.isNegative = z;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public String toJavaFormatString() {
            return this.image;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public void appendTo(Appendable appendable) {
            if (appendable instanceof StringBuilder) {
                ((StringBuilder) appendable).append(this.image);
            } else if (appendable instanceof StringBuffer) {
                ((StringBuffer) appendable).append(this.image);
            }
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDecimalExponent() {
            throw new IllegalArgumentException("Exceptional value does not have an exponent");
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDigits(char[] cArr) {
            throw new IllegalArgumentException("Exceptional value does not have digits");
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isNegative() {
            return this.isNegative;
        }
    }

    /* access modifiers changed from: package-private */
    public static class BinaryToASCIIBuffer implements BinaryToASCIIConverter {
        private static final int[] N_5_BITS = {0, FloatingDecimal.NAN_LENGTH, 5, 7, 10, 12, 14, 17, 19, 21, 24, 26, 28, 31, 33, 35, 38, 40, 42, 45, 47, 49, 52, 54, 56, 59, 61};
        private static int[] insignificantDigitsNumber = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2, FloatingDecimal.NAN_LENGTH, FloatingDecimal.NAN_LENGTH, FloatingDecimal.NAN_LENGTH, FloatingDecimal.NAN_LENGTH, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, FloatingDecimal.INFINITY_LENGTH, FloatingDecimal.INFINITY_LENGTH, FloatingDecimal.INFINITY_LENGTH, 9, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 18, 19};
        private final char[] buffer;
        private int decExponent;
        private boolean decimalDigitsRoundedUp;
        private final char[] digits;
        private boolean exactDecimalConversion;
        private int firstDigitIndex;
        private boolean isNegative;
        private int nDigits;

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isExceptional() {
            return false;
        }

        BinaryToASCIIBuffer() {
            this.buffer = new char[26];
            this.exactDecimalConversion = false;
            this.decimalDigitsRoundedUp = false;
            this.digits = new char[20];
        }

        BinaryToASCIIBuffer(boolean z, char[] cArr) {
            this.buffer = new char[26];
            this.exactDecimalConversion = false;
            this.decimalDigitsRoundedUp = false;
            this.isNegative = z;
            this.decExponent = 0;
            this.digits = cArr;
            this.firstDigitIndex = 0;
            this.nDigits = cArr.length;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public String toJavaFormatString() {
            new String(this.buffer, 0, getChars(this.buffer));
            throw null;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public void appendTo(Appendable appendable) {
            int chars = getChars(this.buffer);
            if (appendable instanceof StringBuilder) {
                ((StringBuilder) appendable).append(this.buffer, 0, chars);
            } else if (appendable instanceof StringBuffer) {
                ((StringBuffer) appendable).append(this.buffer, 0, chars);
            }
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDecimalExponent() {
            return this.decExponent;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDigits(char[] cArr) {
            System.arraycopy((Object) this.digits, this.firstDigitIndex, (Object) cArr, 0, this.nDigits);
            return this.nDigits;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isNegative() {
            return this.isNegative;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSign(boolean z) {
            this.isNegative = z;
        }

        private void developLongDigits(int i, long j, int i2) {
            if (i2 != 0) {
                long j2 = FDBigInteger.LONG_5_POW[i2] << i2;
                long j3 = j % j2;
                j /= j2;
                i += i2;
                if (j3 >= (j2 >> 1)) {
                    j++;
                }
            }
            int length = this.digits.length - 1;
            if (j <= 2147483647L) {
                int i3 = (int) j;
                int i4 = i3 % 10;
                int i5 = i3 / 10;
                while (i4 == 0) {
                    i++;
                    i4 = i5 % 10;
                    i5 /= 10;
                }
                while (i5 != 0) {
                    this.digits[length] = (char) (i4 + 48);
                    i++;
                    i4 = i5 % 10;
                    i5 /= 10;
                    length--;
                }
                this.digits[length] = (char) (i4 + 48);
            } else {
                int i6 = (int) (j % 10);
                long j4 = j / 10;
                while (i6 == 0) {
                    i++;
                    i6 = (int) (j4 % 10);
                    j4 /= 10;
                }
                while (j4 != 0) {
                    this.digits[length] = (char) (i6 + 48);
                    i++;
                    i6 = (int) (j4 % 10);
                    j4 /= 10;
                    length--;
                }
                this.digits[length] = (char) (i6 + 48);
            }
            this.decExponent = i + 1;
            this.firstDigitIndex = length;
            this.nDigits = this.digits.length - length;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void dtoa(int i, long j, int i2, boolean z) {
            boolean z2;
            boolean z3;
            int i3;
            long j2;
            int i4;
            boolean z4;
            boolean z5;
            boolean z6;
            boolean z7;
            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
            int i5 = 53 - numberOfTrailingZeros;
            this.decimalDigitsRoundedUp = false;
            this.exactDecimalConversion = false;
            int max = Math.max(0, (i5 - i) - 1);
            if (i > 62 || i < -21 || max >= FDBigInteger.LONG_5_POW.length || N_5_BITS[max] + i5 >= 64 || max != 0) {
                int estimateDecExp = estimateDecExp(j, i);
                int max2 = Math.max(0, -estimateDecExp);
                int i6 = max2 + max + i;
                int max3 = Math.max(0, estimateDecExp);
                int i7 = max + max3;
                int i8 = i6 - i2;
                long j3 = j >>> numberOfTrailingZeros;
                int i9 = i6 - (i5 - 1);
                int min = Math.min(i9, i7);
                int i10 = i9 - min;
                int i11 = i7 - min;
                int i12 = i8 - min;
                if (i5 == 1) {
                    i12--;
                }
                if (i12 < 0) {
                    i10 -= i12;
                    i11 -= i12;
                    i12 = 0;
                }
                int i13 = i5 + i10;
                int[] iArr = N_5_BITS;
                int i14 = i13 + (max2 < iArr.length ? iArr[max2] : max2 * FloatingDecimal.NAN_LENGTH);
                int i15 = i11 + 1;
                int i16 = max3 + 1;
                int[] iArr2 = N_5_BITS;
                int i17 = i15 + (i16 < iArr2.length ? iArr2[i16] : i16 * FloatingDecimal.NAN_LENGTH);
                if (i14 >= 64 || i17 >= 64) {
                    FDBigInteger valueOfPow52 = FDBigInteger.valueOfPow52(max3, i11);
                    int normalizationBias = valueOfPow52.getNormalizationBias();
                    FDBigInteger leftShift = valueOfPow52.leftShift(normalizationBias);
                    FDBigInteger valueOfMulPow52 = FDBigInteger.valueOfMulPow52(j3, max2, i10 + normalizationBias);
                    FDBigInteger valueOfPow522 = FDBigInteger.valueOfPow52(max2 + 1, i12 + normalizationBias + 1);
                    FDBigInteger valueOfPow523 = FDBigInteger.valueOfPow52(i16, i11 + normalizationBias + 1);
                    int quoRemIteration = valueOfMulPow52.quoRemIteration(leftShift);
                    z3 = valueOfMulPow52.cmp(valueOfPow522) < 0;
                    z2 = valueOfPow523.addAndCmp(valueOfMulPow52, valueOfPow522) <= 0;
                    if (quoRemIteration != 0 || z2) {
                        this.digits[0] = (char) (quoRemIteration + 48);
                        i3 = 1;
                    } else {
                        estimateDecExp--;
                        i3 = 0;
                    }
                    if (!z || estimateDecExp < -3 || estimateDecExp >= FloatingDecimal.INFINITY_LENGTH) {
                        z3 = false;
                        z2 = false;
                    }
                    while (!z3 && !z2) {
                        int quoRemIteration2 = valueOfMulPow52.quoRemIteration(leftShift);
                        valueOfPow522 = valueOfPow522.multBy10();
                        boolean z8 = valueOfMulPow52.cmp(valueOfPow522) < 0;
                        boolean z9 = valueOfPow523.addAndCmp(valueOfMulPow52, valueOfPow522) <= 0;
                        this.digits[i3] = (char) (quoRemIteration2 + 48);
                        z3 = z8;
                        z2 = z9;
                        i3++;
                    }
                    if (!z2 || !z3) {
                        j2 = 0;
                    } else {
                        valueOfMulPow52 = valueOfMulPow52.leftShift(1);
                        j2 = (long) valueOfMulPow52.cmp(valueOfPow523);
                    }
                    this.exactDecimalConversion = valueOfMulPow52.cmp(FDBigInteger.ZERO) == 0;
                } else if (i14 >= 32 || i17 >= 32) {
                    long[] jArr = FDBigInteger.LONG_5_POW;
                    long j4 = (j3 * jArr[max2]) << i10;
                    long j5 = jArr[max3] << i11;
                    long j6 = j5 * 10;
                    int i18 = (int) (j4 / j5);
                    long j7 = (j4 % j5) * 10;
                    long j8 = (jArr[max2] << i12) * 10;
                    z3 = j7 < j8;
                    z2 = j7 + j8 > j6;
                    if (i18 != 0 || z2) {
                        this.digits[0] = (char) (i18 + 48);
                        i4 = 1;
                    } else {
                        estimateDecExp--;
                        i4 = 0;
                    }
                    if (!z || estimateDecExp < -3 || estimateDecExp >= FloatingDecimal.INFINITY_LENGTH) {
                        z3 = false;
                        z2 = false;
                    }
                    while (!z3 && !z2) {
                        int i19 = (int) (j7 / j5);
                        j7 = (j7 % j5) * 10;
                        j8 *= 10;
                        if (j8 > 0) {
                            z5 = j7 < j8;
                            if (j7 + j8 <= j6) {
                                z4 = false;
                                this.digits[i4] = (char) (i19 + 48);
                                z3 = z5;
                                z2 = z4;
                                i4++;
                            }
                        } else {
                            z5 = true;
                        }
                        z4 = true;
                        this.digits[i4] = (char) (i19 + 48);
                        z3 = z5;
                        z2 = z4;
                        i4++;
                    }
                    long j9 = (j7 << 1) - j6;
                    this.exactDecimalConversion = j7 == 0;
                    j2 = j9;
                    i3 = i4;
                } else {
                    int[] iArr3 = FDBigInteger.SMALL_5_POW;
                    int i20 = (((int) j3) * iArr3[max2]) << i10;
                    int i21 = iArr3[max3] << i11;
                    int i22 = iArr3[max2] << i12;
                    int i23 = i21 * 10;
                    int i24 = i20 / i21;
                    int i25 = (i20 % i21) * 10;
                    int i26 = i22 * 10;
                    boolean z10 = i25 < i26;
                    z2 = i25 + i26 > i23;
                    if (i24 != 0 || z2) {
                        this.digits[0] = (char) (i24 + 48);
                        i3 = 1;
                    } else {
                        estimateDecExp--;
                        i3 = 0;
                    }
                    if (!z || estimateDecExp < -3 || estimateDecExp >= FloatingDecimal.INFINITY_LENGTH) {
                        z10 = false;
                        z2 = false;
                    }
                    while (!z10 && !z2) {
                        int i27 = i25 / i21;
                        i25 = (i25 % i21) * 10;
                        i26 *= 10;
                        if (((long) i26) > 0) {
                            z7 = i25 < i26;
                            if (i25 + i26 <= i23) {
                                z6 = false;
                                this.digits[i3] = (char) (i27 + 48);
                                z10 = z7;
                                z2 = z6;
                                i3++;
                            }
                        } else {
                            z7 = true;
                        }
                        z6 = true;
                        this.digits[i3] = (char) (i27 + 48);
                        z10 = z7;
                        z2 = z6;
                        i3++;
                    }
                    j2 = (long) ((i25 << 1) - i23);
                    this.exactDecimalConversion = i25 == 0;
                    z3 = z10;
                }
                this.decExponent = estimateDecExp + 1;
                this.firstDigitIndex = 0;
                this.nDigits = i3;
                if (!z2) {
                    return;
                }
                if (z3) {
                    int i28 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                    if (i28 == 0) {
                        if ((this.digits[(this.firstDigitIndex + this.nDigits) - 1] & 1) != 0) {
                            roundup();
                        }
                    } else if (i28 > 0) {
                        roundup();
                    }
                } else {
                    roundup();
                }
            } else {
                developLongDigits(0, i >= 52 ? j << (i - 52) : j >>> (52 - i), i > i2 ? insignificantDigitsForPow2((i - i2) - 1) : 0);
            }
        }

        private void roundup() {
            int i = (this.firstDigitIndex + this.nDigits) - 1;
            char c = this.digits[i];
            if (c == '9') {
                while (c == '9' && i > this.firstDigitIndex) {
                    char[] cArr = this.digits;
                    cArr[i] = '0';
                    i--;
                    c = cArr[i];
                }
                if (c == '9') {
                    this.decExponent++;
                    this.digits[this.firstDigitIndex] = '1';
                    return;
                }
            }
            this.digits[i] = (char) (c + 1);
            this.decimalDigitsRoundedUp = true;
        }

        static int estimateDecExp(long j, int i) {
            double longBitsToDouble = ((Double.longBitsToDouble((j & 4503599627370495L) | 4607182418800017408L) - 1.5d) * 0.289529654d) + 0.176091259d + (((double) i) * 0.301029995663981d);
            long doubleToRawLongBits = Double.doubleToRawLongBits(longBitsToDouble);
            int i2 = ((int) ((9218868437227405312L & doubleToRawLongBits) >> 52)) - 1023;
            boolean z = (Long.MIN_VALUE & doubleToRawLongBits) != 0;
            if (i2 >= 0 && i2 < 52) {
                long j2 = 4503599627370495 >> i2;
                int i3 = (int) (((4503599627370495L & doubleToRawLongBits) | 4503599627370496L) >> (52 - i2));
                if (z) {
                    return (j2 & doubleToRawLongBits) == 0 ? -i3 : (-i3) - 1;
                }
                return i3;
            } else if (i2 < 0) {
                return ((Long.MAX_VALUE & doubleToRawLongBits) != 0 && z) ? -1 : 0;
            } else {
                return (int) longBitsToDouble;
            }
        }

        private static int insignificantDigitsForPow2(int i) {
            if (i <= 1) {
                return 0;
            }
            int[] iArr = insignificantDigitsNumber;
            if (i < iArr.length) {
                return iArr[i];
            }
            return 0;
        }

        private int getChars(char[] cArr) {
            int i;
            int i2;
            int i3;
            int i4;
            int i5 = 0;
            if (this.isNegative) {
                cArr[0] = '-';
                i5 = 1;
            }
            int i6 = this.decExponent;
            if (i6 <= 0 || i6 >= FloatingDecimal.INFINITY_LENGTH) {
                int i7 = this.decExponent;
                if (i7 > 0 || i7 <= -3) {
                    int i8 = i5 + 1;
                    char[] cArr2 = this.digits;
                    int i9 = this.firstDigitIndex;
                    cArr[i5] = cArr2[i9];
                    int i10 = i8 + 1;
                    cArr[i8] = '.';
                    int i11 = this.nDigits;
                    if (i11 > 1) {
                        System.arraycopy((Object) cArr2, i9 + 1, (Object) cArr, i10, i11 - 1);
                        i = i10 + (this.nDigits - 1);
                    } else {
                        i = i10 + 1;
                        cArr[i10] = '0';
                    }
                    int i12 = i + 1;
                    cArr[i] = 'E';
                    int i13 = this.decExponent;
                    if (i13 <= 0) {
                        i3 = i12 + 1;
                        cArr[i12] = '-';
                        i2 = (-i13) + 1;
                    } else {
                        i2 = i13 - 1;
                        i3 = i12;
                    }
                    if (i2 <= 9) {
                        i4 = i3 + 1;
                        cArr[i3] = (char) (i2 + 48);
                    } else if (i2 <= 99) {
                        int i14 = i3 + 1;
                        cArr[i3] = (char) ((i2 / 10) + 48);
                        int i15 = i14 + 1;
                        cArr[i14] = (char) ((i2 % 10) + 48);
                        return i15;
                    } else {
                        int i16 = i3 + 1;
                        cArr[i3] = (char) ((i2 / 100) + 48);
                        int i17 = i2 % 100;
                        int i18 = i16 + 1;
                        cArr[i16] = (char) ((i17 / 10) + 48);
                        i4 = i18 + 1;
                        cArr[i18] = (char) ((i17 % 10) + 48);
                    }
                    return i4;
                }
                int i19 = i5 + 1;
                cArr[i5] = '0';
                int i20 = i19 + 1;
                cArr[i19] = '.';
                if (i7 != 0) {
                    Arrays.fill(cArr, i20, i20 - i7, '0');
                    i20 -= this.decExponent;
                }
                System.arraycopy((Object) this.digits, this.firstDigitIndex, (Object) cArr, i20, this.nDigits);
                return i20 + this.nDigits;
            }
            int min = Math.min(this.nDigits, i6);
            System.arraycopy((Object) this.digits, this.firstDigitIndex, (Object) cArr, i5, min);
            int i21 = i5 + min;
            int i22 = this.decExponent;
            if (min < i22) {
                int i23 = (i22 - min) + i21;
                Arrays.fill(cArr, i21, i23, '0');
                int i24 = i23 + 1;
                cArr[i23] = '.';
                int i25 = i24 + 1;
                cArr[i24] = '0';
                return i25;
            }
            int i26 = i21 + 1;
            cArr[i21] = '.';
            int i27 = this.nDigits;
            if (min < i27) {
                int i28 = i27 - min;
                System.arraycopy((Object) this.digits, this.firstDigitIndex + min, (Object) cArr, i26, i28);
                return i26 + i28;
            }
            int i29 = i26 + 1;
            cArr[i26] = '0';
            return i29;
        }
    }

    private static BinaryToASCIIBuffer getBinaryToASCIIBuffer() {
        return (BinaryToASCIIBuffer) threadLocalBinaryToASCIIBuffer.get();
    }

    /* access modifiers changed from: package-private */
    public static class PreparedASCIIToBinaryBuffer implements ASCIIToBinaryConverter {
        private final double doubleVal;
        private final float floatVal;

        public PreparedASCIIToBinaryBuffer(double d, float f) {
            this.doubleVal = d;
            this.floatVal = f;
        }

        @Override // sun.misc.FloatingDecimal.ASCIIToBinaryConverter
        public double doubleValue() {
            return this.doubleVal;
        }
    }

    /* access modifiers changed from: package-private */
    public static class ASCIIToBinaryBuffer implements ASCIIToBinaryConverter {
        private static final double[] BIG_10_POW = {1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};
        private static final int MAX_SMALL_TEN = (SMALL_10_POW.length - 1);
        private static final int SINGLE_MAX_SMALL_TEN = (SINGLE_SMALL_10_POW.length - 1);
        private static final float[] SINGLE_SMALL_10_POW = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f};
        private static final double[] SMALL_10_POW = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d};
        private static final double[] TINY_10_POW = {1.0E-16d, 1.0E-32d, 1.0E-64d, 1.0E-128d, 1.0E-256d};
        int decExponent;
        char[] digits;
        boolean isNegative;
        int nDigits;

        ASCIIToBinaryBuffer(boolean z, int i, char[] cArr, int i2) {
            this.isNegative = z;
            this.decExponent = i;
            this.digits = cArr;
            this.nDigits = i2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:103:0x01c5  */
        /* JADX WARNING: Removed duplicated region for block: B:112:0x01de  */
        /* JADX WARNING: Removed duplicated region for block: B:117:0x01ed  */
        /* JADX WARNING: Removed duplicated region for block: B:133:0x0217  */
        /* JADX WARNING: Removed duplicated region for block: B:147:0x0213 A[EDGE_INSN: B:147:0x0213->B:131:0x0213 ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x0139  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x0175  */
        /* JADX WARNING: Removed duplicated region for block: B:89:0x0179  */
        /* JADX WARNING: Removed duplicated region for block: B:92:0x0191  */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x0198  */
        /* JADX WARNING: Removed duplicated region for block: B:95:0x019d  */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x01a1  */
        @Override // sun.misc.FloatingDecimal.ASCIIToBinaryConverter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public double doubleValue() {
            /*
            // Method dump skipped, instructions count: 543
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.misc.FloatingDecimal.ASCIIToBinaryBuffer.doubleValue():double");
        }
    }

    public static BinaryToASCIIConverter getBinaryToASCIIConverter(double d) {
        return getBinaryToASCIIConverter(d, true);
    }

    static BinaryToASCIIConverter getBinaryToASCIIConverter(double d, boolean z) {
        int i;
        long j;
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        boolean z2 = (Long.MIN_VALUE & doubleToRawLongBits) != 0;
        long j2 = 4503599627370495L & doubleToRawLongBits;
        int i2 = (int) ((doubleToRawLongBits & 9218868437227405312L) >> 52);
        if (i2 != 2047) {
            if (i2 != 0) {
                j = j2 | 4503599627370496L;
                i = 53;
            } else if (j2 == 0) {
                return z2 ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            } else {
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
                int i3 = numberOfLeadingZeros - 11;
                j = j2 << i3;
                i = 64 - numberOfLeadingZeros;
                i2 = 1 - i3;
            }
            int i4 = i2 - 1023;
            BinaryToASCIIBuffer binaryToASCIIBuffer = getBinaryToASCIIBuffer();
            binaryToASCIIBuffer.setSign(z2);
            binaryToASCIIBuffer.dtoa(i4, j, i, z);
            return binaryToASCIIBuffer;
        } else if (j2 == 0) {
            return z2 ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
        } else {
            return B2AC_NOT_A_NUMBER;
        }
    }

    private static BinaryToASCIIConverter getBinaryToASCIIConverter(float f) {
        int i;
        int i2;
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        boolean z = (Integer.MIN_VALUE & floatToRawIntBits) != 0;
        int i3 = 8388607 & floatToRawIntBits;
        int i4 = (floatToRawIntBits & 2139095040) >> 23;
        if (i4 != 255) {
            if (i4 != 0) {
                i2 = i3 | 8388608;
                i = 24;
            } else if (i3 == 0) {
                return z ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            } else {
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i3);
                int i5 = numberOfLeadingZeros - 8;
                i2 = i3 << i5;
                i = 32 - numberOfLeadingZeros;
                i4 = 1 - i5;
            }
            int i6 = i4 - 127;
            BinaryToASCIIBuffer binaryToASCIIBuffer = getBinaryToASCIIBuffer();
            binaryToASCIIBuffer.setSign(z);
            binaryToASCIIBuffer.dtoa(i6, ((long) i2) << 29, i, true);
            return binaryToASCIIBuffer;
        } else if (((long) i3) == 0) {
            return z ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
        } else {
            return B2AC_NOT_A_NUMBER;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0129 A[EDGE_INSN: B:149:0x0129->B:104:0x0129 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[Catch:{ StringIndexOutOfBoundsException -> 0x017d }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039 A[Catch:{ StringIndexOutOfBoundsException -> 0x017d }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0110  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static sun.misc.FloatingDecimal.ASCIIToBinaryConverter readJavaFormatString(java.lang.String r18) {
        /*
        // Method dump skipped, instructions count: 409
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.misc.FloatingDecimal.readJavaFormatString(java.lang.String):sun.misc.FloatingDecimal$ASCIIToBinaryConverter");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0140, code lost:
        if ((r3 & 1) != 0) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0156, code lost:
        if ((r3 & 3) != 0) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015a, code lost:
        r3 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0202  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static sun.misc.FloatingDecimal.ASCIIToBinaryConverter parseHexString(java.lang.String r31) {
        /*
        // Method dump skipped, instructions count: 704
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.misc.FloatingDecimal.parseHexString(java.lang.String):sun.misc.FloatingDecimal$ASCIIToBinaryConverter");
    }

    static String stripLeadingZeros(String str) {
        if (str.isEmpty() || str.charAt(0) != '0') {
            return str;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return str.substring(i);
            }
        }
        return "";
    }

    static int getHexDigit(String str, int i) {
        int digit = Character.digit(str.charAt(i), 16);
        if (digit > -1 && digit < 16) {
            return digit;
        }
        throw new AssertionError((Object) ("Unexpected failure of digit conversion of " + str.charAt(i)));
    }
}
