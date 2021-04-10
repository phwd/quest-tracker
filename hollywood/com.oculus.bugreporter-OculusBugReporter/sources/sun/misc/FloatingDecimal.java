package sun.misc;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloatingDecimal {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final ASCIIToBinaryConverter A2BC_NEGATIVE_INFINITY = new PreparedASCIIToBinaryBuffer(Double.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    static final ASCIIToBinaryConverter A2BC_NEGATIVE_ZERO = new PreparedASCIIToBinaryBuffer(-0.0d, -0.0f);
    static final ASCIIToBinaryConverter A2BC_NOT_A_NUMBER = new PreparedASCIIToBinaryBuffer(Double.NaN, Float.NaN);
    static final ASCIIToBinaryConverter A2BC_POSITIVE_INFINITY = new PreparedASCIIToBinaryBuffer(Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    static final ASCIIToBinaryConverter A2BC_POSITIVE_ZERO = new PreparedASCIIToBinaryBuffer(0.0d, 0.0f);
    private static final BinaryToASCIIConverter B2AC_NEGATIVE_INFINITY = new ExceptionalBinaryToASCIIBuffer("-Infinity", true);
    private static final BinaryToASCIIConverter B2AC_NEGATIVE_ZERO = new BinaryToASCIIBuffer(true, new char[]{'0'});
    private static final BinaryToASCIIConverter B2AC_NOT_A_NUMBER = new ExceptionalBinaryToASCIIBuffer(NAN_REP, false);
    private static final BinaryToASCIIConverter B2AC_POSITIVE_INFINITY = new ExceptionalBinaryToASCIIBuffer(INFINITY_REP, false);
    private static final BinaryToASCIIConverter B2AC_POSITIVE_ZERO = new BinaryToASCIIBuffer(false, new char[]{'0'});
    static final int BIG_DECIMAL_EXPONENT = 324;
    static final long EXP_ONE = 4607182418800017408L;
    static final int EXP_SHIFT = 52;
    static final long FRACT_HOB = 4503599627370496L;
    private static final int INFINITY_LENGTH = INFINITY_REP.length();
    private static final String INFINITY_REP = "Infinity";
    static final int INT_DECIMAL_DIGITS = 9;
    static final int MAX_DECIMAL_DIGITS = 15;
    static final int MAX_DECIMAL_EXPONENT = 308;
    static final int MAX_NDIGITS = 1100;
    static final int MAX_SMALL_BIN_EXP = 62;
    static final int MIN_DECIMAL_EXPONENT = -324;
    static final int MIN_SMALL_BIN_EXP = -21;
    private static final int NAN_LENGTH = NAN_REP.length();
    private static final String NAN_REP = "NaN";
    static final int SINGLE_EXP_SHIFT = 23;
    static final int SINGLE_FRACT_HOB = 8388608;
    static final int SINGLE_MAX_DECIMAL_DIGITS = 7;
    static final int SINGLE_MAX_DECIMAL_EXPONENT = 38;
    static final int SINGLE_MAX_NDIGITS = 200;
    static final int SINGLE_MIN_DECIMAL_EXPONENT = -45;
    private static final ThreadLocal<BinaryToASCIIBuffer> threadLocalBinaryToASCIIBuffer = new ThreadLocal<BinaryToASCIIBuffer>() {
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

        float floatValue();
    }

    public interface BinaryToASCIIConverter {
        void appendTo(Appendable appendable);

        boolean decimalDigitsExact();

        boolean digitsRoundedUp();

        int getDecimalExponent();

        int getDigits(char[] cArr);

        boolean isExceptional();

        boolean isNegative();

        String toJavaFormatString();
    }

    public static String toJavaFormatString(double d) {
        return getBinaryToASCIIConverter(d).toJavaFormatString();
    }

    public static String toJavaFormatString(float f) {
        return getBinaryToASCIIConverter(f).toJavaFormatString();
    }

    public static void appendTo(double d, Appendable buf) {
        getBinaryToASCIIConverter(d).appendTo(buf);
    }

    public static void appendTo(float f, Appendable buf) {
        getBinaryToASCIIConverter(f).appendTo(buf);
    }

    public static double parseDouble(String s) throws NumberFormatException {
        return readJavaFormatString(s).doubleValue();
    }

    public static float parseFloat(String s) throws NumberFormatException {
        return readJavaFormatString(s).floatValue();
    }

    private static class ExceptionalBinaryToASCIIBuffer implements BinaryToASCIIConverter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String image;
        private boolean isNegative;

        public ExceptionalBinaryToASCIIBuffer(String image2, boolean isNegative2) {
            this.image = image2;
            this.isNegative = isNegative2;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public String toJavaFormatString() {
            return this.image;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public void appendTo(Appendable buf) {
            if (buf instanceof StringBuilder) {
                ((StringBuilder) buf).append(this.image);
            } else if (buf instanceof StringBuffer) {
                ((StringBuffer) buf).append(this.image);
            }
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDecimalExponent() {
            throw new IllegalArgumentException("Exceptional value does not have an exponent");
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDigits(char[] digits) {
            throw new IllegalArgumentException("Exceptional value does not have digits");
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isNegative() {
            return this.isNegative;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isExceptional() {
            return true;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean digitsRoundedUp() {
            throw new IllegalArgumentException("Exceptional value is not rounded");
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean decimalDigitsExact() {
            throw new IllegalArgumentException("Exceptional value is not exact");
        }
    }

    /* access modifiers changed from: package-private */
    public static class BinaryToASCIIBuffer implements BinaryToASCIIConverter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int[] N_5_BITS = {0, 3, 5, 7, 10, 12, 14, 17, 19, 21, 24, 26, 28, 31, 33, 35, 38, 40, 42, 45, 47, 49, 52, 54, 56, 59, 61};
        private static int[] insignificantDigitsNumber = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 18, 19};
        private final char[] buffer;
        private int decExponent;
        private boolean decimalDigitsRoundedUp;
        private final char[] digits;
        private boolean exactDecimalConversion;
        private int firstDigitIndex;
        private boolean isNegative;
        private int nDigits;

        BinaryToASCIIBuffer() {
            this.buffer = new char[26];
            this.exactDecimalConversion = false;
            this.decimalDigitsRoundedUp = false;
            this.digits = new char[20];
        }

        BinaryToASCIIBuffer(boolean isNegative2, char[] digits2) {
            this.buffer = new char[26];
            this.exactDecimalConversion = false;
            this.decimalDigitsRoundedUp = false;
            this.isNegative = isNegative2;
            this.decExponent = 0;
            this.digits = digits2;
            this.firstDigitIndex = 0;
            this.nDigits = digits2.length;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public String toJavaFormatString() {
            return new String(this.buffer, 0, getChars(this.buffer));
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public void appendTo(Appendable buf) {
            int len = getChars(this.buffer);
            if (buf instanceof StringBuilder) {
                ((StringBuilder) buf).append(this.buffer, 0, len);
            } else if (buf instanceof StringBuffer) {
                ((StringBuffer) buf).append(this.buffer, 0, len);
            }
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDecimalExponent() {
            return this.decExponent;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public int getDigits(char[] digits2) {
            System.arraycopy((Object) this.digits, this.firstDigitIndex, (Object) digits2, 0, this.nDigits);
            return this.nDigits;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isNegative() {
            return this.isNegative;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean isExceptional() {
            return false;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean digitsRoundedUp() {
            return this.decimalDigitsRoundedUp;
        }

        @Override // sun.misc.FloatingDecimal.BinaryToASCIIConverter
        public boolean decimalDigitsExact() {
            return this.exactDecimalConversion;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSign(boolean isNegative2) {
            this.isNegative = isNegative2;
        }

        private void developLongDigits(int decExponent2, long lvalue, int insignificantDigits) {
            if (insignificantDigits != 0) {
                long pow10 = FDBigInteger.LONG_5_POW[insignificantDigits] << insignificantDigits;
                long residue = lvalue % pow10;
                lvalue /= pow10;
                decExponent2 += insignificantDigits;
                if (residue >= (pow10 >> 1)) {
                    lvalue++;
                }
            }
            int digitno = this.digits.length - 1;
            if (lvalue <= 2147483647L) {
                int ivalue = (int) lvalue;
                int c = ivalue % 10;
                int ivalue2 = ivalue / 10;
                while (c == 0) {
                    decExponent2++;
                    c = ivalue2 % 10;
                    ivalue2 /= 10;
                }
                while (ivalue2 != 0) {
                    this.digits[digitno] = (char) (c + 48);
                    decExponent2++;
                    c = ivalue2 % 10;
                    ivalue2 /= 10;
                    digitno--;
                }
                this.digits[digitno] = (char) (c + 48);
            } else {
                int c2 = (int) (lvalue % 10);
                long lvalue2 = lvalue / 10;
                while (c2 == 0) {
                    decExponent2++;
                    c2 = (int) (lvalue2 % 10);
                    lvalue2 /= 10;
                }
                while (lvalue2 != 0) {
                    this.digits[digitno] = (char) (c2 + 48);
                    decExponent2++;
                    c2 = (int) (lvalue2 % 10);
                    lvalue2 /= 10;
                    digitno--;
                }
                this.digits[digitno] = (char) (c2 + 48);
            }
            this.decExponent = decExponent2 + 1;
            this.firstDigitIndex = digitno;
            this.nDigits = this.digits.length - digitno;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void dtoa(int binExp, long fractBits, int nSignificantBits, boolean isCompatibleFormat) {
            boolean high;
            boolean low;
            long lowDigitDifference;
            int ndigit;
            FDBigInteger Mval;
            int ndigit2;
            FDBigInteger Mval2;
            long lowDigitDifference2;
            boolean low2;
            int ndigit3;
            boolean low3;
            boolean z;
            boolean low4;
            int ndigit4;
            int m;
            boolean z2;
            boolean low5;
            int insignificant;
            long fractBits2;
            int tailZeros = Long.numberOfTrailingZeros(fractBits);
            int ndigit5 = 53 - tailZeros;
            this.decimalDigitsRoundedUp = false;
            this.exactDecimalConversion = false;
            int nTinyBits = Math.max(0, (ndigit5 - binExp) - 1);
            if (binExp > 62 || binExp < FloatingDecimal.MIN_SMALL_BIN_EXP || nTinyBits >= FDBigInteger.LONG_5_POW.length || N_5_BITS[nTinyBits] + ndigit5 >= 64 || nTinyBits != 0) {
                int decExp = estimateDecExp(fractBits, binExp);
                int B5 = Math.max(0, -decExp);
                int B2 = B5 + nTinyBits + binExp;
                int S5 = Math.max(0, decExp);
                int S2 = S5 + nTinyBits;
                int M2 = B2 - nSignificantBits;
                long fractBits3 = fractBits >>> tailZeros;
                int B22 = B2 - (ndigit5 - 1);
                int common2factor = Math.min(B22, S2);
                int B23 = B22 - common2factor;
                int S22 = S2 - common2factor;
                int M22 = M2 - common2factor;
                if (ndigit5 == 1) {
                    M22--;
                }
                if (M22 < 0) {
                    B23 -= M22;
                    S22 -= M22;
                    M22 = 0;
                }
                int i = ndigit5 + B23;
                int[] iArr = N_5_BITS;
                int Bbits = i + (B5 < iArr.length ? iArr[B5] : B5 * 3);
                int i2 = S22 + 1;
                int i3 = S5 + 1;
                int[] iArr2 = N_5_BITS;
                int tenSbits = i2 + (i3 < iArr2.length ? iArr2[S5 + 1] : (S5 + 1) * 3);
                if (Bbits >= 64 || tenSbits >= 64) {
                    FDBigInteger Sval = FDBigInteger.valueOfPow52(S5, S22);
                    int shiftBias = Sval.getNormalizationBias();
                    FDBigInteger Sval2 = Sval.leftShift(shiftBias);
                    FDBigInteger Bval = FDBigInteger.valueOfMulPow52(fractBits3, B5, B23 + shiftBias);
                    FDBigInteger Mval3 = FDBigInteger.valueOfPow52(B5 + 1, M22 + shiftBias + 1);
                    FDBigInteger tenSval = FDBigInteger.valueOfPow52(S5 + 1, S22 + shiftBias + 1);
                    int q = Bval.quoRemIteration(Sval2);
                    low = Bval.cmp(Mval3) < 0;
                    boolean high2 = tenSval.addAndCmp(Bval, Mval3) <= 0;
                    if (q != 0 || high2) {
                        ndigit2 = 0 + 1;
                        Mval = Mval3;
                        this.digits[0] = (char) (q + 48);
                    } else {
                        decExp--;
                        ndigit2 = 0;
                        Mval = Mval3;
                    }
                    if (!isCompatibleFormat || decExp < -3 || decExp >= 8) {
                        low = false;
                        Mval2 = Mval;
                        high = false;
                    } else {
                        Mval2 = Mval;
                        high = high2;
                    }
                    while (!low && !high) {
                        q = Bval.quoRemIteration(Sval2);
                        Mval2 = Mval2.multBy10();
                        low = Bval.cmp(Mval2) < 0;
                        high = tenSval.addAndCmp(Bval, Mval2) <= 0;
                        this.digits[ndigit2] = (char) (q + 48);
                        Sval2 = Sval2;
                        ndigit2++;
                    }
                    if (!high || !low) {
                        lowDigitDifference2 = 0;
                    } else {
                        Bval = Bval.leftShift(1);
                        lowDigitDifference2 = (long) Bval.cmp(tenSval);
                    }
                    this.exactDecimalConversion = Bval.cmp(FDBigInteger.ZERO) == 0;
                    lowDigitDifference = lowDigitDifference2;
                    ndigit = ndigit2;
                } else if (Bbits >= 32 || tenSbits >= 32) {
                    long b = (FDBigInteger.LONG_5_POW[B5] * fractBits3) << B23;
                    long s = FDBigInteger.LONG_5_POW[S5] << S22;
                    long tens = s * 10;
                    int q2 = (int) (b / s);
                    long b2 = (b % s) * 10;
                    long m2 = (FDBigInteger.LONG_5_POW[B5] << M22) * 10;
                    boolean low6 = b2 < m2;
                    boolean high3 = b2 + m2 > tens;
                    if (q2 != 0 || high3) {
                        ndigit3 = 0 + 1;
                        low2 = low6;
                        this.digits[0] = (char) (q2 + 48);
                    } else {
                        decExp--;
                        ndigit3 = 0;
                        low2 = low6;
                    }
                    if (!isCompatibleFormat || decExp < -3 || decExp >= 8) {
                        low3 = false;
                        ndigit = ndigit3;
                        high = false;
                    } else {
                        ndigit = ndigit3;
                        low3 = low2;
                        high = high3;
                    }
                    while (!low3 && !high) {
                        int q3 = (int) (b2 / s);
                        b2 = (b2 % s) * 10;
                        m2 *= 10;
                        if (m2 > 0) {
                            low4 = b2 < m2;
                            z = b2 + m2 > tens;
                        } else {
                            low4 = true;
                            z = true;
                        }
                        high = z;
                        this.digits[ndigit] = (char) (q3 + 48);
                        low3 = low4;
                        ndigit++;
                    }
                    lowDigitDifference = (b2 << 1) - tens;
                    this.exactDecimalConversion = b2 == 0;
                    low = low3;
                } else {
                    int b3 = (((int) fractBits3) * FDBigInteger.SMALL_5_POW[B5]) << B23;
                    int s2 = FDBigInteger.SMALL_5_POW[S5] << S22;
                    int tens2 = s2 * 10;
                    int q4 = b3 / s2;
                    int b4 = (b3 % s2) * 10;
                    int m3 = (FDBigInteger.SMALL_5_POW[B5] << M22) * 10;
                    boolean low7 = b4 < m3;
                    boolean high4 = b4 + m3 > tens2;
                    if (q4 != 0 || high4) {
                        m = m3;
                        ndigit4 = 0 + 1;
                        high = high4;
                        this.digits[0] = (char) (q4 + 48);
                    } else {
                        decExp--;
                        m = m3;
                        high = high4;
                        ndigit4 = 0;
                    }
                    if (!isCompatibleFormat || decExp < -3 || decExp >= 8) {
                        low7 = false;
                        high = false;
                    }
                    while (!low7 && !high) {
                        q4 = b4 / s2;
                        b4 = (b4 % s2) * 10;
                        int m4 = m * 10;
                        if (((long) m4) > 0) {
                            low5 = b4 < m4;
                            z2 = b4 + m4 > tens2;
                        } else {
                            low5 = true;
                            z2 = true;
                        }
                        low7 = low5;
                        high = z2;
                        this.digits[ndigit4] = (char) (q4 + 48);
                        m = m4;
                        ndigit4++;
                        ndigit5 = ndigit5;
                    }
                    lowDigitDifference = (long) ((b4 << 1) - tens2);
                    this.exactDecimalConversion = b4 == 0;
                    low = low7;
                    ndigit = ndigit4;
                }
                this.decExponent = decExp + 1;
                this.firstDigitIndex = 0;
                this.nDigits = ndigit;
                if (!high) {
                    return;
                }
                if (!low) {
                    roundup();
                } else if (lowDigitDifference == 0) {
                    if ((this.digits[(this.firstDigitIndex + this.nDigits) - 1] & 1) != 0) {
                        roundup();
                    }
                } else if (lowDigitDifference > 0) {
                    roundup();
                }
            } else {
                if (binExp > nSignificantBits) {
                    insignificant = insignificantDigitsForPow2((binExp - nSignificantBits) - 1);
                } else {
                    insignificant = 0;
                }
                if (binExp >= 52) {
                    fractBits2 = fractBits << (binExp - 52);
                } else {
                    fractBits2 = fractBits >>> (52 - binExp);
                }
                developLongDigits(0, fractBits2, insignificant);
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

        static int estimateDecExp(long fractBits, int binExp) {
            double d = ((Double.longBitsToDouble((fractBits & DoubleConsts.SIGNIF_BIT_MASK) | FloatingDecimal.EXP_ONE) - 1.5d) * 0.289529654d) + 0.176091259d + (((double) binExp) * 0.301029995663981d);
            long dBits = Double.doubleToRawLongBits(d);
            int exponent = ((int) ((DoubleConsts.EXP_BIT_MASK & dBits) >> 52)) - 1023;
            boolean isNegative2 = (Long.MIN_VALUE & dBits) != 0;
            if (exponent >= 0 && exponent < 52) {
                long mask = DoubleConsts.SIGNIF_BIT_MASK >> exponent;
                int r = (int) (((DoubleConsts.SIGNIF_BIT_MASK & dBits) | FloatingDecimal.FRACT_HOB) >> (52 - exponent));
                if (isNegative2) {
                    return (mask & dBits) == 0 ? -r : (-r) - 1;
                }
                return r;
            } else if (exponent >= 0) {
                return (int) d;
            } else {
                if ((Long.MAX_VALUE & dBits) != 0 && isNegative2) {
                    return -1;
                }
                return 0;
            }
        }

        private static int insignificantDigits(int insignificant) {
            int i = 0;
            while (((long) insignificant) >= 10) {
                insignificant = (int) (((long) insignificant) / 10);
                i++;
            }
            return i;
        }

        private static int insignificantDigitsForPow2(int p2) {
            if (p2 <= 1) {
                return 0;
            }
            int[] iArr = insignificantDigitsNumber;
            if (p2 < iArr.length) {
                return iArr[p2];
            }
            return 0;
        }

        /* JADX INFO: Multiple debug info for r2v12 int: [D('charLength' int), D('i' int)] */
        /* JADX INFO: Multiple debug info for r0v23 int: [D('t' int), D('i' int)] */
        private int getChars(char[] result) {
            int i;
            int e;
            int i2 = 0;
            if (this.isNegative) {
                result[0] = '-';
                i2 = 1;
            }
            int i3 = this.decExponent;
            if (i3 <= 0 || i3 >= 8) {
                int i4 = this.decExponent;
                if (i4 > 0 || i4 <= -3) {
                    int i5 = i2 + 1;
                    char[] cArr = this.digits;
                    int i6 = this.firstDigitIndex;
                    result[i2] = cArr[i6];
                    int i7 = i5 + 1;
                    result[i5] = '.';
                    int i8 = this.nDigits;
                    if (i8 > 1) {
                        System.arraycopy((Object) cArr, i6 + 1, (Object) result, i7, i8 - 1);
                        i = i7 + (this.nDigits - 1);
                    } else {
                        result[i7] = '0';
                        i = i7 + 1;
                    }
                    int i9 = i + 1;
                    result[i] = 'E';
                    int e2 = this.decExponent;
                    if (e2 <= 0) {
                        result[i9] = '-';
                        e = (-e2) + 1;
                        i9++;
                    } else {
                        e = e2 - 1;
                    }
                    if (e <= 9) {
                        int i10 = i9 + 1;
                        result[i9] = (char) (e + 48);
                        return i10;
                    } else if (e <= 99) {
                        int i11 = i9 + 1;
                        result[i9] = (char) ((e / 10) + 48);
                        int i12 = i11 + 1;
                        result[i11] = (char) ((e % 10) + 48);
                        return i12;
                    } else {
                        int i13 = i9 + 1;
                        result[i9] = (char) ((e / 100) + 48);
                        int e3 = e % 100;
                        int i14 = i13 + 1;
                        result[i13] = (char) ((e3 / 10) + 48);
                        int i15 = i14 + 1;
                        result[i14] = (char) ((e3 % 10) + 48);
                        return i15;
                    }
                } else {
                    int i16 = i2 + 1;
                    result[i2] = '0';
                    int i17 = i16 + 1;
                    result[i16] = '.';
                    if (i4 != 0) {
                        Arrays.fill(result, i17, i17 - i4, '0');
                        i17 -= this.decExponent;
                    }
                    System.arraycopy((Object) this.digits, this.firstDigitIndex, (Object) result, i17, this.nDigits);
                    return i17 + this.nDigits;
                }
            } else {
                int charLength = Math.min(this.nDigits, i3);
                System.arraycopy((Object) this.digits, this.firstDigitIndex, (Object) result, i2, charLength);
                int i18 = i2 + charLength;
                int i19 = this.decExponent;
                if (charLength < i19) {
                    int charLength2 = i19 - charLength;
                    Arrays.fill(result, i18, i18 + charLength2, '0');
                    int i20 = i18 + charLength2;
                    int i21 = i20 + 1;
                    result[i20] = '.';
                    int i22 = i21 + 1;
                    result[i21] = '0';
                    return i22;
                }
                int i23 = i18 + 1;
                result[i18] = '.';
                int i24 = this.nDigits;
                if (charLength < i24) {
                    int t = i24 - charLength;
                    System.arraycopy((Object) this.digits, this.firstDigitIndex + charLength, (Object) result, i23, t);
                    return t + i23;
                }
                int i25 = i23 + 1;
                result[i23] = '0';
                return i25;
            }
        }
    }

    private static BinaryToASCIIBuffer getBinaryToASCIIBuffer() {
        return threadLocalBinaryToASCIIBuffer.get();
    }

    /* access modifiers changed from: package-private */
    public static class PreparedASCIIToBinaryBuffer implements ASCIIToBinaryConverter {
        private final double doubleVal;
        private final float floatVal;

        public PreparedASCIIToBinaryBuffer(double doubleVal2, float floatVal2) {
            this.doubleVal = doubleVal2;
            this.floatVal = floatVal2;
        }

        @Override // sun.misc.FloatingDecimal.ASCIIToBinaryConverter
        public double doubleValue() {
            return this.doubleVal;
        }

        @Override // sun.misc.FloatingDecimal.ASCIIToBinaryConverter
        public float floatValue() {
            return this.floatVal;
        }
    }

    /* access modifiers changed from: package-private */
    public static class ASCIIToBinaryBuffer implements ASCIIToBinaryConverter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
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

        ASCIIToBinaryBuffer(boolean negSign, int decExponent2, char[] digits2, int n) {
            this.isNegative = negSign;
            this.decExponent = decExponent2;
            this.digits = digits2;
            this.nDigits = n;
        }

        /* JADX INFO: Multiple debug info for r3v3 long: [D('i' int), D('lValue' long)] */
        /* JADX INFO: Multiple debug info for r4v5 int: [D('bigIntNBits' int), D('exp' int)] */
        /* JADX INFO: Multiple debug info for r1v7 int: [D('binexp' int), D('B2' int)] */
        /* JADX INFO: Multiple debug info for r10v2 int: [D('iDigits' int), D('D2' int)] */
        /* JADX INFO: Multiple debug info for r1v18 'bigD0'  sun.misc.FDBigInteger: [D('B2' int), D('bigD0' sun.misc.FDBigInteger)] */
        /* JADX WARNING: Removed duplicated region for block: B:115:0x021e  */
        /* JADX WARNING: Removed duplicated region for block: B:125:0x0245  */
        /* JADX WARNING: Removed duplicated region for block: B:130:0x025b  */
        /* JADX WARNING: Removed duplicated region for block: B:158:0x0292 A[EDGE_INSN: B:158:0x0292->B:144:0x0292 ?: BREAK  , SYNTHETIC] */
        @Override // sun.misc.FloatingDecimal.ASCIIToBinaryConverter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public double doubleValue() {
            /*
            // Method dump skipped, instructions count: 670
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.misc.FloatingDecimal.ASCIIToBinaryBuffer.doubleValue():double");
        }

        /* JADX INFO: Multiple debug info for r3v2 float: [D('i' int), D('fValue' float)] */
        /* JADX INFO: Multiple debug info for r1v3 int: [D('kDigits' int), D('bigIntNBits' int)] */
        /* JADX INFO: Multiple debug info for r4v7 int: [D('B2' int), D('exp' int)] */
        /* JADX INFO: Multiple debug info for r7v4 int: [D('D5' int), D('D2' int)] */
        @Override // sun.misc.FloatingDecimal.ASCIIToBinaryConverter
        public float floatValue() {
            int bigBbits;
            int hulpbias;
            FDBigInteger bigD0;
            boolean overvalue;
            int Ulp2;
            int i;
            FDBigInteger diff;
            int i2;
            int bigIntNBits = Math.min(this.nDigits, 8);
            int iValue = this.digits[0] - '0';
            for (int i3 = 1; i3 < bigIntNBits; i3++) {
                iValue = ((iValue * 10) + this.digits[i3]) - 48;
            }
            float fValue = (float) iValue;
            int i4 = this.decExponent;
            int exp = i4 - bigIntNBits;
            int i5 = this.nDigits;
            if (i5 <= 7) {
                if (exp == 0 || fValue == 0.0f) {
                    return this.isNegative ? -fValue : fValue;
                }
                if (exp >= 0) {
                    int i6 = SINGLE_MAX_SMALL_TEN;
                    if (exp <= i6) {
                        float fValue2 = fValue * SINGLE_SMALL_10_POW[exp];
                        return this.isNegative ? -fValue2 : fValue2;
                    }
                    int slop = 7 - bigIntNBits;
                    if (exp <= i6 + slop) {
                        float[] fArr = SINGLE_SMALL_10_POW;
                        float fValue3 = fValue * fArr[slop] * fArr[exp - slop];
                        return this.isNegative ? -fValue3 : fValue3;
                    }
                } else if (exp >= (-SINGLE_MAX_SMALL_TEN)) {
                    float fValue4 = fValue / SINGLE_SMALL_10_POW[-exp];
                    return this.isNegative ? -fValue4 : fValue4;
                }
            } else if (i4 >= i5 && i5 + i4 <= 15) {
                long lValue = (long) iValue;
                int i7 = bigIntNBits;
                while (true) {
                    i2 = this.nDigits;
                    if (i7 >= i2) {
                        break;
                    }
                    lValue = (10 * lValue) + ((long) (this.digits[i7] - '0'));
                    i7++;
                }
                float fValue5 = (float) (((double) lValue) * SMALL_10_POW[this.decExponent - i2]);
                return this.isNegative ? -fValue5 : fValue5;
            }
            double dValue = (double) fValue;
            if (exp > 0) {
                if (this.decExponent > 39) {
                    return this.isNegative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
                }
                if ((exp & 15) != 0) {
                    dValue *= SMALL_10_POW[exp & 15];
                }
                int i8 = exp >> 4;
                if (i8 != 0) {
                    int j = 0;
                    for (int exp2 = i8; exp2 > 0; exp2 >>= 1) {
                        if ((exp2 & 1) != 0) {
                            dValue *= BIG_10_POW[j];
                        }
                        j++;
                    }
                }
            } else if (exp < 0) {
                int exp3 = -exp;
                if (this.decExponent >= -46) {
                    if ((exp3 & 15) != 0) {
                        dValue /= SMALL_10_POW[exp3 & 15];
                    }
                    int exp4 = exp3 >> 4;
                    if (exp4 != 0) {
                        int j2 = 0;
                        for (int exp5 = exp4; exp5 > 0; exp5 >>= 1) {
                            if ((exp5 & 1) != 0) {
                                dValue *= TINY_10_POW[j2];
                            }
                            j2++;
                        }
                    }
                } else if (this.isNegative) {
                    return -0.0f;
                } else {
                    return 0.0f;
                }
            }
            float fValue6 = Math.max(Float.MIN_VALUE, Math.min(Float.MAX_VALUE, (float) dValue));
            if (this.nDigits > 200) {
                this.nDigits = 201;
                this.digits[200] = '1';
            }
            FDBigInteger bigD02 = new FDBigInteger((long) iValue, this.digits, bigIntNBits, this.nDigits);
            int B2 = this.decExponent - this.nDigits;
            int ieeeBits = Float.floatToRawIntBits(fValue6);
            int B5 = Math.max(0, -B2);
            int D2 = Math.max(0, B2);
            FDBigInteger bigD03 = bigD02.multByPow52(D2, 0);
            bigD03.makeImmutable();
            FDBigInteger bigD = null;
            int prevD2 = 0;
            while (true) {
                int binexp = ieeeBits >>> 23;
                int bigBbits2 = 8388607 & ieeeBits;
                if (binexp > 0) {
                    bigBbits = bigBbits2 | 8388608;
                } else {
                    int shift = Integer.numberOfLeadingZeros(bigBbits2) - 8;
                    bigBbits = bigBbits2 << shift;
                    binexp = 1 - shift;
                }
                int binexp2 = binexp - 127;
                int lowOrderZeros = Integer.numberOfTrailingZeros(bigBbits);
                int bigBbits3 = bigBbits >>> lowOrderZeros;
                int bigIntExp = (binexp2 - 23) + lowOrderZeros;
                int bigIntNBits2 = 24 - lowOrderZeros;
                int B22 = B5;
                int D22 = D2;
                if (bigIntExp >= 0) {
                    B22 += bigIntExp;
                } else {
                    D22 -= bigIntExp;
                }
                if (binexp2 <= -127) {
                    hulpbias = binexp2 + lowOrderZeros + 127;
                } else {
                    hulpbias = lowOrderZeros + 1;
                }
                int exp6 = B22 + hulpbias;
                int D23 = D22 + hulpbias;
                int common2 = Math.min(exp6, Math.min(D23, B22));
                int D24 = D23 - common2;
                int Ulp22 = B22 - common2;
                FDBigInteger bigB = FDBigInteger.valueOfMulPow52((long) bigBbits3, B5, exp6 - common2);
                if (bigD == null || prevD2 != D24) {
                    bigD = bigD03.leftShift(D24);
                    prevD2 = D24;
                }
                int cmpResult = bigB.cmp(bigD);
                if (cmpResult <= 0) {
                    bigD0 = bigD03;
                    i = 1;
                    if (cmpResult >= 0) {
                        break;
                    }
                    diff = bigD.rightInplaceSub(bigB);
                    overvalue = false;
                    Ulp2 = Ulp22;
                } else {
                    diff = bigB.leftInplaceSub(bigD);
                    bigD0 = bigD03;
                    i = 1;
                    if (bigIntNBits2 == 1) {
                        if (bigIntExp > -126) {
                            int Ulp23 = Ulp22 - 1;
                            if (Ulp23 < 0) {
                                i = 1;
                                diff = diff.leftShift(1);
                                overvalue = true;
                                Ulp2 = 0;
                            } else {
                                i = 1;
                                overvalue = true;
                                Ulp2 = Ulp23;
                            }
                        } else {
                            i = 1;
                        }
                    }
                    overvalue = true;
                    Ulp2 = Ulp22;
                }
                int cmpResult2 = diff.cmpPow52(B5, Ulp2);
                if (cmpResult2 < 0) {
                    break;
                } else if (cmpResult2 != 0) {
                    if (overvalue) {
                        i = -1;
                    }
                    ieeeBits += i;
                    if (ieeeBits == 0 || ieeeBits == 2139095040) {
                        break;
                    }
                    bigIntNBits = bigIntNBits;
                    B2 = B2;
                    D2 = D2;
                    iValue = iValue;
                    dValue = dValue;
                    bigD03 = bigD0;
                } else if ((ieeeBits & 1) != 0) {
                    if (overvalue) {
                        i = -1;
                    }
                    ieeeBits += i;
                }
            }
            if (this.isNegative) {
                ieeeBits |= Integer.MIN_VALUE;
            }
            return Float.intBitsToFloat(ieeeBits);
        }
    }

    public static BinaryToASCIIConverter getBinaryToASCIIConverter(double d) {
        return getBinaryToASCIIConverter(d, true);
    }

    static BinaryToASCIIConverter getBinaryToASCIIConverter(double d, boolean isCompatibleFormat) {
        long fractBits;
        int nSignificantBits;
        long dBits = Double.doubleToRawLongBits(d);
        boolean isNegative = (Long.MIN_VALUE & dBits) != 0;
        long fractBits2 = DoubleConsts.SIGNIF_BIT_MASK & dBits;
        int binExp = (int) ((DoubleConsts.EXP_BIT_MASK & dBits) >> 52);
        if (binExp != 2047) {
            if (binExp != 0) {
                fractBits = fractBits2 | FRACT_HOB;
                nSignificantBits = 53;
            } else if (fractBits2 == 0) {
                return isNegative ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            } else {
                int leadingZeros = Long.numberOfLeadingZeros(fractBits2);
                int shift = leadingZeros - 11;
                fractBits = fractBits2 << shift;
                binExp = 1 - shift;
                nSignificantBits = 64 - leadingZeros;
            }
            BinaryToASCIIBuffer buf = getBinaryToASCIIBuffer();
            buf.setSign(isNegative);
            buf.dtoa(binExp - 1023, fractBits, nSignificantBits, isCompatibleFormat);
            return buf;
        } else if (fractBits2 == 0) {
            return isNegative ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
        } else {
            return B2AC_NOT_A_NUMBER;
        }
    }

    private static BinaryToASCIIConverter getBinaryToASCIIConverter(float f) {
        int fractBits;
        int nSignificantBits;
        int fBits = Float.floatToRawIntBits(f);
        boolean isNegative = (Integer.MIN_VALUE & fBits) != 0;
        int fractBits2 = 8388607 & fBits;
        int binExp = (2139095040 & fBits) >> 23;
        if (binExp != 255) {
            if (binExp != 0) {
                fractBits = fractBits2 | 8388608;
                nSignificantBits = 24;
            } else if (fractBits2 == 0) {
                return isNegative ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            } else {
                int leadingZeros = Integer.numberOfLeadingZeros(fractBits2);
                int shift = leadingZeros - 8;
                fractBits = fractBits2 << shift;
                binExp = 1 - shift;
                nSignificantBits = 32 - leadingZeros;
            }
            BinaryToASCIIBuffer buf = getBinaryToASCIIBuffer();
            buf.setSign(isNegative);
            buf.dtoa(binExp - 127, ((long) fractBits) << 29, nSignificantBits, true);
            return buf;
        } else if (((long) fractBits2) == 0) {
            return isNegative ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
        } else {
            return B2AC_NOT_A_NUMBER;
        }
    }

    /* JADX INFO: Multiple debug info for r9v0 char[]: [D('ch' char), D('digits' char[])] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0132 A[Catch:{ StringIndexOutOfBoundsException -> 0x01ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0160 A[Catch:{ StringIndexOutOfBoundsException -> 0x01ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x016c A[Catch:{ StringIndexOutOfBoundsException -> 0x01ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x016f A[Catch:{ StringIndexOutOfBoundsException -> 0x01ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0159 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static sun.misc.FloatingDecimal.ASCIIToBinaryConverter readJavaFormatString(java.lang.String r22) throws java.lang.NumberFormatException {
        /*
        // Method dump skipped, instructions count: 478
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.misc.FloatingDecimal.readJavaFormatString(java.lang.String):sun.misc.FloatingDecimal$ASCIIToBinaryConverter");
    }

    /* access modifiers changed from: private */
    public static class HexFloatPattern {
        private static final Pattern VALUE = Pattern.compile("([-+])?0[xX](((\\p{XDigit}+)\\.?)|((\\p{XDigit}*)\\.(\\p{XDigit}+)))[pP]([-+])?(\\p{Digit}+)[fFdD]?");

        private HexFloatPattern() {
        }
    }

    /* JADX INFO: Multiple debug info for r2v12 long: [D('sticky' boolean), D('significand' long)] */
    static ASCIIToBinaryConverter parseHexString(String s) {
        int leftDigits;
        String significandString;
        int exponentAdjust;
        long significand;
        int nextShift;
        long significand2;
        long significand3;
        double value;
        int bitsDiscarded;
        Matcher m = HexFloatPattern.VALUE.matcher(s);
        if (m.matches()) {
            String group1 = m.group(1);
            boolean isNegative = group1 != null && group1.equals("-");
            int rightDigits = 0;
            String group4 = m.group(4);
            if (group4 != null) {
                significandString = stripLeadingZeros(group4);
                leftDigits = significandString.length();
            } else {
                String group6 = stripLeadingZeros(m.group(6));
                leftDigits = group6.length();
                String group7 = m.group(7);
                rightDigits = group7.length();
                significandString = group6 + group7;
            }
            String significandString2 = stripLeadingZeros(significandString);
            int signifLength = significandString2.length();
            if (leftDigits >= 1) {
                exponentAdjust = (leftDigits - 1) * 4;
            } else {
                exponentAdjust = ((rightDigits - signifLength) + 1) * -4;
            }
            if (signifLength == 0) {
                return isNegative ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
            }
            String group8 = m.group(8);
            boolean positiveExponent = group8 == null || group8.equals("+");
            try {
                long exponent = ((positiveExponent ? 1 : -1) * ((long) Integer.parseInt(m.group(9)))) + ((long) exponentAdjust);
                boolean round = false;
                boolean sticky = false;
                long leadingDigit = (long) getHexDigit(significandString2, 0);
                if (leadingDigit == 1) {
                    significand = 0 | (leadingDigit << 52);
                    nextShift = 48;
                } else if (leadingDigit <= 3) {
                    significand = 0 | (leadingDigit << 51);
                    nextShift = 47;
                    exponent++;
                } else if (leadingDigit <= 7) {
                    significand = 0 | (leadingDigit << 50);
                    nextShift = 46;
                    exponent += 2;
                } else if (leadingDigit <= 15) {
                    significand = 0 | (leadingDigit << 49);
                    nextShift = 45;
                    exponent += 3;
                } else {
                    throw new AssertionError((Object) "Result from digit conversion too large!");
                }
                int nextShift2 = nextShift;
                int i = 1;
                while (i < signifLength && nextShift2 >= 0) {
                    significand |= ((long) getHexDigit(significandString2, i)) << nextShift2;
                    nextShift2 -= 4;
                    i++;
                    m = m;
                }
                if (i < signifLength) {
                    long currentDigit = (long) getHexDigit(significandString2, i);
                    if (nextShift2 == -4) {
                        boolean round2 = (currentDigit & 8) != 0;
                        sticky = (currentDigit & 7) != 0;
                        round = round2;
                    } else if (nextShift2 == -3) {
                        long significand4 = significand | ((currentDigit & 8) >> 3);
                        boolean round3 = (currentDigit & 4) != 0;
                        sticky = (currentDigit & 3) != 0;
                        round = round3;
                        significand = significand4;
                    } else if (nextShift2 == -2) {
                        long significand5 = significand | ((currentDigit & 12) >> 2);
                        boolean round4 = (currentDigit & 2) != 0;
                        sticky = (currentDigit & 1) != 0;
                        round = round4;
                        significand = significand5;
                    } else if (nextShift2 == -1) {
                        long significand6 = significand | ((currentDigit & 14) >> 1);
                        round = (currentDigit & 1) != 0;
                        significand = significand6;
                    } else {
                        throw new AssertionError((Object) "Unexpected shift distance remainder.");
                    }
                    for (int i2 = i + 1; i2 < signifLength && !sticky; i2++) {
                        sticky = sticky || ((long) getHexDigit(significandString2, i2)) != 0;
                    }
                }
                int floatBits = isNegative ? Integer.MIN_VALUE : 0;
                if (exponent >= -126) {
                    if (exponent > 127) {
                        floatBits |= FloatConsts.EXP_BIT_MASK;
                    } else {
                        boolean floatSticky = (significand & ((1 << 28) - 1)) != 0 || round || sticky;
                        int iValue = (int) (significand >>> 28);
                        if ((iValue & 3) != 1 || floatSticky) {
                            iValue++;
                        }
                        floatBits |= ((((int) exponent) + 126) << 23) + (iValue >> 1);
                    }
                } else if (exponent >= -150) {
                    int threshShift = (int) (-98 - exponent);
                    boolean floatSticky2 = (significand & ((1 << threshShift) - 1)) != 0 || round || sticky;
                    int iValue2 = (int) (significand >>> threshShift);
                    if ((iValue2 & 3) != 1 || floatSticky2) {
                        iValue2++;
                    }
                    floatBits |= iValue2 >> 1;
                }
                float fValue = Float.intBitsToFloat(floatBits);
                if (exponent > 1023) {
                    return isNegative ? A2BC_NEGATIVE_INFINITY : A2BC_POSITIVE_INFINITY;
                }
                if (exponent <= 1023 && exponent >= -1022) {
                    significand3 = (((1023 + exponent) << 52) & DoubleConsts.EXP_BIT_MASK) | (significand & DoubleConsts.SIGNIF_BIT_MASK);
                    significand2 = 0;
                } else if (exponent < -1075) {
                    return isNegative ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
                } else {
                    boolean sticky2 = sticky || round;
                    int bitsDiscarded2 = 53 - ((((int) exponent) + 1074) + 1);
                    round = (significand & (1 << (bitsDiscarded2 + -1))) != 0;
                    if (bitsDiscarded2 > 1) {
                        bitsDiscarded = bitsDiscarded2;
                        sticky = sticky2 || (significand & (~(-1 << (bitsDiscarded2 + -1)))) != 0;
                    } else {
                        bitsDiscarded = bitsDiscarded2;
                        sticky = sticky2;
                    }
                    significand2 = 0;
                    significand3 = ((significand >> bitsDiscarded) & DoubleConsts.SIGNIF_BIT_MASK) | 0;
                }
                boolean leastZero = (significand3 & 1) == significand2;
                if ((leastZero && round && sticky) || (!leastZero && round)) {
                    significand3++;
                }
                if (isNegative) {
                    value = Double.longBitsToDouble(significand3 | Long.MIN_VALUE);
                } else {
                    value = Double.longBitsToDouble(significand3);
                }
                return new PreparedASCIIToBinaryBuffer(value, fValue);
            } catch (NumberFormatException e) {
                return isNegative ? positiveExponent ? A2BC_NEGATIVE_INFINITY : A2BC_NEGATIVE_ZERO : positiveExponent ? A2BC_POSITIVE_INFINITY : A2BC_POSITIVE_ZERO;
            }
        } else {
            throw new NumberFormatException("For input string: \"" + s + "\"");
        }
    }

    static String stripLeadingZeros(String s) {
        if (s.isEmpty() || s.charAt(0) != '0') {
            return s;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                return s.substring(i);
            }
        }
        return "";
    }

    static int getHexDigit(String s, int position) {
        int value = Character.digit(s.charAt(position), 16);
        if (value > -1 && value < 16) {
            return value;
        }
        throw new AssertionError((Object) ("Unexpected failure of digit conversion of " + s.charAt(position)));
    }
}
