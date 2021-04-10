package java.math;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import libcore.math.MathUtils;

public class BigDecimal extends Number implements Comparable, Serializable {
    private static final BigDecimal[] BI_SCALED_BY_ZERO = new BigDecimal[11];
    private static final char[] CH_ZEROS = new char[100];
    private static final BigInteger[] FIVE_POW = Multiplication.bigFivePows;
    private static final long[] LONG_FIVE_POW = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125, 6103515625L, 30517578125L, 152587890625L, 762939453125L, 3814697265625L, 19073486328125L, 95367431640625L, 476837158203125L, 2384185791015625L, 11920928955078125L, 59604644775390625L, 298023223876953125L, 1490116119384765625L, 7450580596923828125L};
    private static final int[] LONG_FIVE_POW_BIT_LENGTH = new int[LONG_FIVE_POW.length];
    private static final int[] LONG_POWERS_OF_TEN_BIT_LENGTH = new int[MathUtils.LONG_POWERS_OF_TEN.length];
    public static final BigDecimal ONE = new BigDecimal(1, 0);
    public static final BigDecimal TEN = new BigDecimal(10, 0);
    private static final BigInteger[] TEN_POW = Multiplication.bigTenPows;
    public static final BigDecimal ZERO = new BigDecimal(0, 0);
    private static final BigDecimal[] ZERO_SCALED_BY = new BigDecimal[11];
    private static final long serialVersionUID = 6108874887143696463L;
    private transient int bitLength;
    private transient int hashCode;
    private BigInteger intVal;
    private transient int precision;
    private int scale;
    private transient long smallValue;
    private transient String toStringImage;

    static {
        Arrays.fill(CH_ZEROS, '0');
        for (int i = 0; i < ZERO_SCALED_BY.length; i++) {
            BI_SCALED_BY_ZERO[i] = new BigDecimal(i, 0);
            ZERO_SCALED_BY[i] = new BigDecimal(0, i);
        }
        int i2 = 0;
        while (true) {
            int[] iArr = LONG_FIVE_POW_BIT_LENGTH;
            if (i2 >= iArr.length) {
                break;
            }
            iArr[i2] = bitLength(LONG_FIVE_POW[i2]);
            i2++;
        }
        int i3 = 0;
        while (true) {
            int[] iArr2 = LONG_POWERS_OF_TEN_BIT_LENGTH;
            if (i3 < iArr2.length) {
                iArr2[i3] = bitLength(MathUtils.LONG_POWERS_OF_TEN[i3]);
                i3++;
            } else {
                return;
            }
        }
    }

    private BigDecimal(long j, int i) {
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        this.smallValue = j;
        this.scale = i;
        this.bitLength = bitLength(j);
    }

    private BigDecimal(int i, int i2) {
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        this.smallValue = (long) i;
        this.scale = i2;
        this.bitLength = bitLength(i);
    }

    public BigDecimal(char[] cArr, int i, int i2) {
        int i3;
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        int i4 = (i2 - 1) + i;
        if (cArr == null) {
            throw new NullPointerException("in == null");
        } else if (i4 >= cArr.length || i < 0 || i2 <= 0 || i4 < 0) {
            throw new NumberFormatException("Bad offset/length: offset=" + i + " len=" + i2 + " in.length=" + cArr.length);
        } else {
            StringBuilder sb = new StringBuilder(i2);
            if (i <= i4 && cArr[i] == '+') {
                i++;
            }
            int i5 = i;
            boolean z = false;
            while (i5 <= i4 && cArr[i5] != '.' && cArr[i5] != 'e' && cArr[i5] != 'E') {
                if (!z && cArr[i5] != '0') {
                    z = true;
                }
                i5++;
            }
            int i6 = i5 - i;
            sb.append(cArr, i, i6);
            int i7 = i6 + 0;
            if (i5 > i4 || cArr[i5] != '.') {
                this.scale = 0;
                i3 = i5;
            } else {
                int i8 = i5 + 1;
                i3 = i8;
                while (i3 <= i4 && cArr[i3] != 'e' && cArr[i3] != 'E') {
                    if (!z && cArr[i3] != '0') {
                        z = true;
                    }
                    i3++;
                }
                this.scale = i3 - i8;
                int i9 = this.scale;
                i7 += i9;
                sb.append(cArr, i8, i9);
            }
            if (i3 <= i4 && (cArr[i3] == 'e' || cArr[i3] == 'E')) {
                int i10 = i3 + 1;
                int i11 = (i10 > i4 || cArr[i10] != '+' || (i11 = i10 + 1) > i4 || cArr[i11] == '-') ? i10 : i11;
                String.valueOf(cArr, i11, (i4 + 1) - i11);
                throw null;
            } else if (i7 < 19) {
                this.smallValue = Long.parseLong(sb.toString());
                this.bitLength = bitLength(this.smallValue);
            } else {
                setUnscaledValue(new BigInteger(sb.toString()));
            }
        }
    }

    public BigDecimal(String str) {
        this(str.toCharArray(), 0, str.length());
    }

    public BigDecimal(BigInteger bigInteger) {
        this(bigInteger, 0);
    }

    public BigDecimal(BigInteger bigInteger, int i) {
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        if (bigInteger != null) {
            this.scale = i;
            setUnscaledValue(bigInteger);
            return;
        }
        throw new NullPointerException("unscaledVal == null");
    }

    public BigDecimal(BigInteger bigInteger, int i, MathContext mathContext) {
        this(bigInteger, i);
        inplaceRound(mathContext);
    }

    public BigDecimal(long j) {
        this(j, 0);
    }

    public static BigDecimal valueOf(long j, int i) {
        if (i == 0) {
            return valueOf(j);
        }
        if (j == 0 && i >= 0) {
            BigDecimal[] bigDecimalArr = ZERO_SCALED_BY;
            if (i < bigDecimalArr.length) {
                return bigDecimalArr[i];
            }
        }
        return new BigDecimal(j, i);
    }

    public static BigDecimal valueOf(long j) {
        if (j < 0 || j >= 11) {
            return new BigDecimal(j, 0);
        }
        return BI_SCALED_BY_ZERO[(int) j];
    }

    public static BigDecimal valueOf(double d) {
        if (!Double.isInfinite(d) && !Double.isNaN(d)) {
            return new BigDecimal(Double.toString(d));
        }
        throw new NumberFormatException("Infinity or NaN: " + d);
    }

    public BigDecimal add(BigDecimal bigDecimal) {
        int i = this.scale - bigDecimal.scale;
        if (isZero()) {
            if (i <= 0) {
                return bigDecimal;
            }
            if (bigDecimal.isZero()) {
                return this;
            }
        } else if (bigDecimal.isZero() && i >= 0) {
            return this;
        }
        if (i == 0) {
            if (Math.max(this.bitLength, bigDecimal.bitLength) + 1 < 64) {
                return valueOf(this.smallValue + bigDecimal.smallValue, this.scale);
            }
            return new BigDecimal(getUnscaledValue().add(bigDecimal.getUnscaledValue()), this.scale);
        } else if (i > 0) {
            return addAndMult10(this, bigDecimal, i);
        } else {
            return addAndMult10(bigDecimal, this, -i);
        }
    }

    private static BigDecimal addAndMult10(BigDecimal bigDecimal, BigDecimal bigDecimal2, int i) {
        if (i < MathUtils.LONG_POWERS_OF_TEN.length && Math.max(bigDecimal.bitLength, bigDecimal2.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[i]) + 1 < 64) {
            return valueOf(bigDecimal.smallValue + (bigDecimal2.smallValue * MathUtils.LONG_POWERS_OF_TEN[i]), bigDecimal.scale);
        }
        BigInt bigInt = Multiplication.multiplyByTenPow(bigDecimal2.getUnscaledValue(), (long) i).getBigInt();
        bigInt.add(bigDecimal.getUnscaledValue().getBigInt());
        return new BigDecimal(new BigInteger(bigInt), bigDecimal.scale);
    }

    public BigDecimal subtract(BigDecimal bigDecimal) {
        int i = this.scale - bigDecimal.scale;
        if (isZero()) {
            if (i <= 0) {
                return bigDecimal.negate();
            }
            if (bigDecimal.isZero()) {
                return this;
            }
        } else if (bigDecimal.isZero() && i >= 0) {
            return this;
        }
        if (i == 0) {
            if (Math.max(this.bitLength, bigDecimal.bitLength) + 1 < 64) {
                return valueOf(this.smallValue - bigDecimal.smallValue, this.scale);
            }
            return new BigDecimal(getUnscaledValue().subtract(bigDecimal.getUnscaledValue()), this.scale);
        } else if (i <= 0) {
            int i2 = -i;
            if (i2 >= MathUtils.LONG_POWERS_OF_TEN.length || Math.max(this.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[i2], bigDecimal.bitLength) + 1 >= 64) {
                return new BigDecimal(Multiplication.multiplyByTenPow(getUnscaledValue(), (long) i2).subtract(bigDecimal.getUnscaledValue()), bigDecimal.scale);
            }
            return valueOf((this.smallValue * MathUtils.LONG_POWERS_OF_TEN[i2]) - bigDecimal.smallValue, bigDecimal.scale);
        } else if (i >= MathUtils.LONG_POWERS_OF_TEN.length || Math.max(this.bitLength, bigDecimal.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[i]) + 1 >= 64) {
            return new BigDecimal(getUnscaledValue().subtract(Multiplication.multiplyByTenPow(bigDecimal.getUnscaledValue(), (long) i)), this.scale);
        } else {
            return valueOf(this.smallValue - (bigDecimal.smallValue * MathUtils.LONG_POWERS_OF_TEN[i]), this.scale);
        }
    }

    public BigDecimal multiply(BigDecimal bigDecimal) {
        long j = ((long) this.scale) + ((long) bigDecimal.scale);
        if (isZero() || bigDecimal.isZero()) {
            return zeroScaledBy(j);
        }
        if (this.bitLength + bigDecimal.bitLength < 64) {
            long j2 = this.smallValue;
            long j3 = bigDecimal.smallValue * j2;
            if (!(j3 == Long.MIN_VALUE && Math.signum((float) j2) * Math.signum((float) bigDecimal.smallValue) > 0.0f)) {
                return valueOf(j3, safeLongToInt(j));
            }
        }
        return new BigDecimal(getUnscaledValue().multiply(bigDecimal.getUnscaledValue()), safeLongToInt(j));
    }

    public BigDecimal divide(BigDecimal bigDecimal, int i, int i2) {
        return divide(bigDecimal, i, RoundingMode.valueOf(i2));
    }

    public BigDecimal divide(BigDecimal bigDecimal, int i, RoundingMode roundingMode) {
        int i2;
        if (roundingMode == null) {
            throw new NullPointerException("roundingMode == null");
        } else if (!bigDecimal.isZero()) {
            long j = (((long) this.scale) - ((long) bigDecimal.scale)) - ((long) i);
            if (bitLength(j) <= 32) {
                int i3 = this.bitLength;
                if (i3 < 64 && (i2 = bigDecimal.bitLength) < 64) {
                    int i4 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                    if (i4 == 0) {
                        if (!(this.smallValue == Long.MIN_VALUE && bigDecimal.smallValue == -1)) {
                            return dividePrimitiveLongs(this.smallValue, bigDecimal.smallValue, i, roundingMode);
                        }
                    } else if (i4 > 0) {
                        long[] jArr = MathUtils.LONG_POWERS_OF_TEN;
                        if (j < ((long) jArr.length)) {
                            int i5 = (int) j;
                            if (i2 + LONG_POWERS_OF_TEN_BIT_LENGTH[i5] < 64) {
                                return dividePrimitiveLongs(this.smallValue, jArr[i5] * bigDecimal.smallValue, i, roundingMode);
                            }
                        }
                    } else {
                        long j2 = -j;
                        long[] jArr2 = MathUtils.LONG_POWERS_OF_TEN;
                        if (j2 < ((long) jArr2.length)) {
                            int i6 = (int) j2;
                            if (i3 + LONG_POWERS_OF_TEN_BIT_LENGTH[i6] < 64) {
                                return dividePrimitiveLongs(this.smallValue * jArr2[i6], bigDecimal.smallValue, i, roundingMode);
                            }
                        }
                    }
                }
                BigInteger unscaledValue = getUnscaledValue();
                BigInteger unscaledValue2 = bigDecimal.getUnscaledValue();
                int i7 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (i7 > 0) {
                    unscaledValue2 = Multiplication.multiplyByTenPow(unscaledValue2, (long) ((int) j));
                } else if (i7 < 0) {
                    unscaledValue = Multiplication.multiplyByTenPow(unscaledValue, (long) ((int) (-j)));
                }
                return divideBigIntegers(unscaledValue, unscaledValue2, i, roundingMode);
            }
            throw new ArithmeticException("Unable to perform divisor / dividend scaling: the difference in scale is too big (" + j + ")");
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }

    private static BigDecimal divideBigIntegers(BigInteger bigInteger, BigInteger bigInteger2, int i, RoundingMode roundingMode) {
        int i2;
        BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(bigInteger2);
        BigInteger bigInteger3 = divideAndRemainder[0];
        BigInteger bigInteger4 = divideAndRemainder[1];
        if (bigInteger4.signum() == 0) {
            return new BigDecimal(bigInteger3, i);
        }
        int signum = bigInteger.signum() * bigInteger2.signum();
        if (bigInteger2.bitLength() < 63) {
            int compareForRounding = compareForRounding(bigInteger4.longValue(), bigInteger2.longValue());
            i2 = roundingBehavior(bigInteger3.testBit(0) ? 1 : 0, signum * (compareForRounding + 5), roundingMode);
        } else {
            int compareTo = bigInteger4.abs().shiftLeftOneBit().compareTo(bigInteger2.abs());
            i2 = roundingBehavior(bigInteger3.testBit(0) ? 1 : 0, signum * (compareTo + 5), roundingMode);
        }
        if (i2 == 0) {
            return new BigDecimal(bigInteger3, i);
        }
        if (bigInteger3.bitLength() < 63) {
            return valueOf(bigInteger3.longValue() + ((long) i2), i);
        }
        return new BigDecimal(bigInteger3.add(BigInteger.valueOf((long) i2)), i);
    }

    private static BigDecimal dividePrimitiveLongs(long j, long j2, int i, RoundingMode roundingMode) {
        long j3 = j / j2;
        long j4 = j % j2;
        int signum = Long.signum(j) * Long.signum(j2);
        if (j4 != 0) {
            j3 += (long) roundingBehavior(((int) j3) & 1, signum * (compareForRounding(j4, j2) + 5), roundingMode);
        }
        return valueOf(j3, i);
    }

    public BigDecimal divide(BigDecimal bigDecimal) {
        BigInteger bigInteger;
        BigInteger unscaledValue = getUnscaledValue();
        BigInteger unscaledValue2 = bigDecimal.getUnscaledValue();
        long j = ((long) this.scale) - ((long) bigDecimal.scale);
        int length = FIVE_POW.length - 1;
        if (bigDecimal.isZero()) {
            throw new ArithmeticException("Division by zero");
        } else if (unscaledValue.signum() == 0) {
            return zeroScaledBy(j);
        } else {
            BigInteger gcd = unscaledValue.gcd(unscaledValue2);
            BigInteger divide = unscaledValue.divide(gcd);
            BigInteger divide2 = unscaledValue2.divide(gcd);
            int lowestSetBit = divide2.getLowestSetBit();
            BigInteger shiftRight = divide2.shiftRight(lowestSetBit);
            int i = 1;
            int i2 = 0;
            while (true) {
                BigInteger[] divideAndRemainder = shiftRight.divideAndRemainder(FIVE_POW[i]);
                if (divideAndRemainder[1].signum() == 0) {
                    i2 += i;
                    if (i < length) {
                        i++;
                    }
                    shiftRight = divideAndRemainder[0];
                } else if (i == 1) {
                    break;
                } else {
                    i = 1;
                }
            }
            if (shiftRight.abs().equals(BigInteger.ONE)) {
                if (shiftRight.signum() < 0) {
                    divide = divide.negate();
                }
                int safeLongToInt = safeLongToInt(j + ((long) Math.max(lowestSetBit, i2)));
                int i3 = lowestSetBit - i2;
                if (i3 > 0) {
                    bigInteger = Multiplication.multiplyByFivePow(divide, i3);
                } else {
                    bigInteger = divide.shiftLeft(-i3);
                }
                return new BigDecimal(bigInteger, safeLongToInt);
            }
            throw new ArithmeticException("Non-terminating decimal expansion; no exact representable decimal result");
        }
    }

    public BigDecimal divide(BigDecimal bigDecimal, MathContext mathContext) {
        long j;
        BigInteger bigInteger;
        int i;
        long precision2 = ((((long) mathContext.getPrecision()) + 2) + ((long) bigDecimal.approxPrecision())) - ((long) approxPrecision());
        long j2 = ((long) this.scale) - ((long) bigDecimal.scale);
        int length = TEN_POW.length - 1;
        BigInteger[] bigIntegerArr = {getUnscaledValue()};
        if (mathContext.getPrecision() == 0 || isZero() || bigDecimal.isZero()) {
            return divide(bigDecimal);
        }
        if (precision2 > 0) {
            bigIntegerArr[0] = getUnscaledValue().multiply(Multiplication.powerOf10(precision2));
            j = precision2 + j2;
        } else {
            j = j2;
        }
        BigInteger[] divideAndRemainder = bigIntegerArr[0].divideAndRemainder(bigDecimal.getUnscaledValue());
        BigInteger bigInteger2 = divideAndRemainder[0];
        if (divideAndRemainder[1].signum() != 0) {
            bigInteger = bigInteger2.multiply(BigInteger.TEN).add(BigInteger.valueOf((long) (divideAndRemainder[0].signum() * (divideAndRemainder[1].shiftLeftOneBit().compareTo(bigDecimal.getUnscaledValue()) + 5))));
            j++;
        } else {
            loop0:
            do {
                i = 1;
                while (true) {
                    if (bigInteger2.testBit(0)) {
                        break loop0;
                    }
                    BigInteger[] divideAndRemainder2 = bigInteger2.divideAndRemainder(TEN_POW[i]);
                    if (divideAndRemainder2[1].signum() != 0) {
                        break;
                    }
                    long j3 = j - ((long) i);
                    if (j3 < j2) {
                        continue;
                        break;
                    }
                    if (i < length) {
                        i++;
                    }
                    bigInteger2 = divideAndRemainder2[0];
                    j = j3;
                }
            } while (i != 1);
            bigInteger = bigInteger2;
        }
        return new BigDecimal(bigInteger, safeLongToInt(j), mathContext);
    }

    public BigDecimal abs() {
        return signum() < 0 ? negate() : this;
    }

    public BigDecimal negate() {
        int i = this.bitLength;
        if (i < 63 || (i == 63 && this.smallValue != Long.MIN_VALUE)) {
            return valueOf(-this.smallValue, this.scale);
        }
        return new BigDecimal(getUnscaledValue().negate(), this.scale);
    }

    public int signum() {
        if (this.bitLength < 64) {
            return Long.signum(this.smallValue);
        }
        return getUnscaledValue().signum();
    }

    private boolean isZero() {
        return this.bitLength == 0 && this.smallValue != -1;
    }

    public int scale() {
        return this.scale;
    }

    public int precision() {
        int i = this.precision;
        if (i != 0) {
            return i;
        }
        int i2 = this.bitLength;
        if (i2 == 0) {
            this.precision = 1;
        } else if (i2 < 64) {
            this.precision = decimalDigitsInLong(this.smallValue);
        } else {
            int i3 = ((int) (((double) (i2 - 1)) * 0.3010299956639812d)) + 1;
            if (getUnscaledValue().divide(Multiplication.powerOf10((long) i3)).signum() != 0) {
                i3++;
            }
            this.precision = i3;
        }
        return this.precision;
    }

    private int decimalDigitsInLong(long j) {
        if (j == Long.MIN_VALUE) {
            return 19;
        }
        int binarySearch = Arrays.binarySearch(MathUtils.LONG_POWERS_OF_TEN, Math.abs(j));
        return binarySearch < 0 ? (-binarySearch) - 1 : binarySearch + 1;
    }

    public BigInteger unscaledValue() {
        return getUnscaledValue();
    }

    public BigDecimal round(MathContext mathContext) {
        BigDecimal bigDecimal = new BigDecimal(getUnscaledValue(), this.scale);
        bigDecimal.inplaceRound(mathContext);
        return bigDecimal;
    }

    public BigDecimal setScale(int i, RoundingMode roundingMode) {
        if (roundingMode != null) {
            long j = ((long) i) - ((long) this.scale);
            int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i2 == 0) {
                return this;
            }
            if (i2 > 0) {
                long[] jArr = MathUtils.LONG_POWERS_OF_TEN;
                if (j < ((long) jArr.length)) {
                    int i3 = (int) j;
                    if (this.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[i3] < 64) {
                        return valueOf(this.smallValue * jArr[i3], i);
                    }
                }
                return new BigDecimal(Multiplication.multiplyByTenPow(getUnscaledValue(), (long) ((int) j)), i);
            }
            if (this.bitLength < 64) {
                long j2 = -j;
                long[] jArr2 = MathUtils.LONG_POWERS_OF_TEN;
                if (j2 < ((long) jArr2.length)) {
                    return dividePrimitiveLongs(this.smallValue, jArr2[(int) j2], i, roundingMode);
                }
            }
            return divideBigIntegers(getUnscaledValue(), Multiplication.powerOf10(-j), i, roundingMode);
        }
        throw new NullPointerException("roundingMode == null");
    }

    public BigDecimal setScale(int i) {
        return setScale(i, RoundingMode.UNNECESSARY);
    }

    public BigDecimal scaleByPowerOfTen(int i) {
        long j = ((long) this.scale) - ((long) i);
        if (this.bitLength >= 64) {
            return new BigDecimal(getUnscaledValue(), safeLongToInt(j));
        }
        long j2 = this.smallValue;
        if (j2 == 0) {
            return zeroScaledBy(j);
        }
        return valueOf(j2, safeLongToInt(j));
    }

    public BigDecimal stripTrailingZeros() {
        int i;
        int length = TEN_POW.length - 1;
        long j = (long) this.scale;
        if (isZero()) {
            return new BigDecimal(BigInteger.ZERO, 0);
        }
        BigInteger unscaledValue = getUnscaledValue();
        long j2 = j;
        loop0:
        do {
            i = 1;
            while (true) {
                if (unscaledValue.testBit(0)) {
                    break loop0;
                }
                BigInteger[] divideAndRemainder = unscaledValue.divideAndRemainder(TEN_POW[i]);
                if (divideAndRemainder[1].signum() != 0) {
                    break;
                }
                j2 -= (long) i;
                if (i < length) {
                    i++;
                }
                unscaledValue = divideAndRemainder[0];
            }
        } while (i != 1);
        return new BigDecimal(unscaledValue, safeLongToInt(j2));
    }

    public int compareTo(BigDecimal bigDecimal) {
        int signum = signum();
        int signum2 = bigDecimal.signum();
        if (signum != signum2) {
            return signum < signum2 ? -1 : 1;
        }
        if (this.scale != bigDecimal.scale || this.bitLength >= 64 || bigDecimal.bitLength >= 64) {
            long j = ((long) this.scale) - ((long) bigDecimal.scale);
            long approxPrecision = (long) (approxPrecision() - bigDecimal.approxPrecision());
            if (approxPrecision > j + 1) {
                return signum;
            }
            if (approxPrecision < j - 1) {
                return -signum;
            }
            BigInteger unscaledValue = getUnscaledValue();
            BigInteger unscaledValue2 = bigDecimal.getUnscaledValue();
            int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i < 0) {
                unscaledValue = unscaledValue.multiply(Multiplication.powerOf10(-j));
            } else if (i > 0) {
                unscaledValue2 = unscaledValue2.multiply(Multiplication.powerOf10(j));
            }
            return unscaledValue.compareTo(unscaledValue2);
        }
        long j2 = this.smallValue;
        long j3 = bigDecimal.smallValue;
        if (j2 < j3) {
            return -1;
        }
        if (j2 > j3) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        int i;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BigDecimal)) {
            return false;
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        if (bigDecimal.scale == this.scale && bigDecimal.bitLength == (i = this.bitLength)) {
            if (i < 64) {
                if (bigDecimal.smallValue == this.smallValue) {
                    return true;
                }
            } else if (bigDecimal.intVal.equals(this.intVal)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        if (this.bitLength < 64) {
            long j = this.smallValue;
            this.hashCode = (int) (j & -1);
            this.hashCode = (this.hashCode * 33) + ((int) ((j >> 32) & -1));
            this.hashCode = (this.hashCode * 17) + this.scale;
            return this.hashCode;
        }
        this.hashCode = (this.intVal.hashCode() * 17) + this.scale;
        return this.hashCode;
    }

    public String toString() {
        String str = this.toStringImage;
        if (str != null) {
            return str;
        }
        if (this.bitLength < 32) {
            this.toStringImage = Conversion.toDecimalScaledString(this.smallValue, this.scale);
            return this.toStringImage;
        }
        String bigInteger = getUnscaledValue().toString();
        if (this.scale == 0) {
            return bigInteger;
        }
        int i = getUnscaledValue().signum() < 0 ? 2 : 1;
        int length = bigInteger.length();
        long j = ((-((long) this.scale)) + ((long) length)) - ((long) i);
        StringBuilder sb = new StringBuilder();
        sb.append(bigInteger);
        int i2 = this.scale;
        if (i2 <= 0 || j < -6) {
            if (length - i >= 1) {
                sb.insert(i, '.');
                length++;
            }
            sb.insert(length, 'E');
            if (j > 0) {
                length++;
                sb.insert(length, '+');
            }
            sb.insert(length + 1, Long.toString(j));
        } else if (j >= 0) {
            sb.insert(length - i2, '.');
        } else {
            sb.insert(i - 1, "0.");
            sb.insert(i + 1, CH_ZEROS, 0, (-((int) j)) - 1);
        }
        this.toStringImage = sb.toString();
        return this.toStringImage;
    }

    public String toPlainString() {
        char[] cArr;
        char[] cArr2;
        String bigInteger = getUnscaledValue().toString();
        if (this.scale == 0 || (isZero() && this.scale < 0)) {
            return bigInteger;
        }
        int i = signum() < 0 ? 1 : 0;
        int i2 = this.scale;
        StringBuilder sb = new StringBuilder(bigInteger.length() + 1 + Math.abs(this.scale));
        if (i == 1) {
            sb.append('-');
        }
        if (this.scale > 0) {
            int length = i2 - (bigInteger.length() - i);
            if (length >= 0) {
                sb.append("0.");
                while (true) {
                    cArr2 = CH_ZEROS;
                    if (length <= cArr2.length) {
                        break;
                    }
                    sb.append(cArr2);
                    length -= CH_ZEROS.length;
                }
                sb.append(cArr2, 0, length);
                sb.append(bigInteger.substring(i));
            } else {
                int i3 = i - length;
                sb.append(bigInteger.substring(i, i3));
                sb.append('.');
                sb.append(bigInteger.substring(i3));
            }
        } else {
            sb.append(bigInteger.substring(i));
            while (true) {
                cArr = CH_ZEROS;
                if (i2 >= (-cArr.length)) {
                    break;
                }
                sb.append(cArr);
                i2 += CH_ZEROS.length;
            }
            sb.append(cArr, 0, -i2);
        }
        return sb.toString();
    }

    public BigInteger toBigInteger() {
        if (this.scale == 0 || isZero()) {
            return getUnscaledValue();
        }
        if (this.scale < 0) {
            return getUnscaledValue().multiply(Multiplication.powerOf10(-((long) this.scale)));
        }
        return getUnscaledValue().divide(Multiplication.powerOf10((long) this.scale));
    }

    @Override // java.lang.Number
    public long longValue() {
        int i = this.scale;
        if (i <= -64 || i > approxPrecision()) {
            return 0;
        }
        return toBigInteger().longValue();
    }

    @Override // java.lang.Number
    public int intValue() {
        int i = this.scale;
        if (i <= -32 || i > approxPrecision()) {
            return 0;
        }
        return toBigInteger().intValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009a, code lost:
        if (r2 < r3) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a0, code lost:
        if ((r19 & 3) == 3) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ae, code lost:
        if ((r19 & 3) == 3) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b9, code lost:
        r13 = r19;
     */
    @Override // java.lang.Number
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double doubleValue() {
        /*
        // Method dump skipped, instructions count: 280
        */
        throw new UnsupportedOperationException("Method not decompiled: java.math.BigDecimal.doubleValue():double");
    }

    private void inplaceRound(MathContext mathContext) {
        int precision2;
        int precision3 = mathContext.getPrecision();
        if (approxPrecision() >= precision3 && precision3 != 0 && (precision2 = precision() - precision3) > 0) {
            if (this.bitLength < 64) {
                smallRound(mathContext, precision2);
                return;
            }
            long j = (long) precision2;
            BigInteger powerOf10 = Multiplication.powerOf10(j);
            BigInteger[] divideAndRemainder = getUnscaledValue().divideAndRemainder(powerOf10);
            long j2 = ((long) this.scale) - j;
            if (divideAndRemainder[1].signum() != 0) {
                int compareTo = divideAndRemainder[1].abs().shiftLeftOneBit().compareTo(powerOf10);
                boolean testBit = divideAndRemainder[0].testBit(0);
                int roundingBehavior = roundingBehavior(testBit ? 1 : 0, divideAndRemainder[1].signum() * (compareTo + 5), mathContext.getRoundingMode());
                if (roundingBehavior != 0) {
                    divideAndRemainder[0] = divideAndRemainder[0].add(BigInteger.valueOf((long) roundingBehavior));
                }
                if (new BigDecimal(divideAndRemainder[0]).precision() > precision3) {
                    divideAndRemainder[0] = divideAndRemainder[0].divide(BigInteger.TEN);
                    j2--;
                }
            }
            this.scale = safeLongToInt(j2);
            this.precision = precision3;
            setUnscaledValue(divideAndRemainder[0]);
        }
    }

    private static int compareAbsoluteValues(long j, long j2) {
        int i = ((Math.abs(j) - 1) > (Math.abs(j2) - 1) ? 1 : ((Math.abs(j) - 1) == (Math.abs(j2) - 1) ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        return i < 0 ? -1 : 0;
    }

    private static int compareForRounding(long j, long j2) {
        long j3 = j2 / 2;
        return (j == j3 || j == (-j3)) ? -(((int) j2) & 1) : compareAbsoluteValues(j, j3);
    }

    private void smallRound(MathContext mathContext, int i) {
        long j = MathUtils.LONG_POWERS_OF_TEN[i];
        long j2 = ((long) this.scale) - ((long) i);
        long j3 = this.smallValue;
        long j4 = j3 / j;
        long j5 = j3 % j;
        if (j5 != 0) {
            j4 += (long) roundingBehavior(((int) j4) & 1, Long.signum(j5) * (compareForRounding(j5, j) + 5), mathContext.getRoundingMode());
            if (Math.log10((double) Math.abs(j4)) >= ((double) mathContext.getPrecision())) {
                j4 /= 10;
                j2--;
            }
        }
        this.scale = safeLongToInt(j2);
        this.precision = mathContext.getPrecision();
        this.smallValue = j4;
        this.bitLength = bitLength(j4);
        this.intVal = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.math.BigDecimal$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.math.RoundingMode[] r0 = java.math.RoundingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode = r0
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.math.RoundingMode r1 = java.math.RoundingMode.UNNECESSARY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x001f }
                java.math.RoundingMode r1 = java.math.RoundingMode.UP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x002a }
                java.math.RoundingMode r1 = java.math.RoundingMode.DOWN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.math.RoundingMode r1 = java.math.RoundingMode.CEILING     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.math.RoundingMode r1 = java.math.RoundingMode.FLOOR     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x004b }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_UP     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_DOWN     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = java.math.BigDecimal.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0062 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_EVEN     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.math.BigDecimal.AnonymousClass1.<clinit>():void");
        }
    }

    private static int roundingBehavior(int i, int i2, RoundingMode roundingMode) {
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                if (i2 == 0) {
                    return 0;
                }
                throw new ArithmeticException("Rounding necessary");
            case 2:
                return Integer.signum(i2);
            case 3:
            default:
                return 0;
            case 4:
                return Math.max(Integer.signum(i2), 0);
            case 5:
                return Math.min(Integer.signum(i2), 0);
            case 6:
                if (Math.abs(i2) >= 5) {
                    return Integer.signum(i2);
                }
                return 0;
            case 7:
                if (Math.abs(i2) > 5) {
                    return Integer.signum(i2);
                }
                return 0;
            case 8:
                if (Math.abs(i2) + i > 5) {
                    return Integer.signum(i2);
                }
                return 0;
        }
    }

    private int approxPrecision() {
        int i = this.precision;
        return i > 0 ? i : ((int) (((double) (this.bitLength - 1)) * 0.3010299956639812d)) + 1;
    }

    private static int safeLongToInt(long j) {
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("Out of int range: " + j);
    }

    private static BigDecimal zeroScaledBy(long j) {
        int i = (int) j;
        if (j == ((long) i)) {
            return valueOf(0, i);
        }
        if (j >= 0) {
            return new BigDecimal(0, Integer.MAX_VALUE);
        }
        return new BigDecimal(0, Integer.MIN_VALUE);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        getUnscaledValue();
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private BigInteger getUnscaledValue() {
        if (this.intVal == null) {
            this.intVal = BigInteger.valueOf(this.smallValue);
        }
        return this.intVal;
    }

    private void setUnscaledValue(BigInteger bigInteger) {
        this.intVal = bigInteger;
        this.bitLength = bigInteger.bitLength();
        if (this.bitLength < 64) {
            this.smallValue = bigInteger.longValue();
        }
    }

    private static int bitLength(long j) {
        if (j < 0) {
            j = ~j;
        }
        return 64 - Long.numberOfLeadingZeros(j);
    }

    private static int bitLength(int i) {
        if (i < 0) {
            i = ~i;
        }
        return 32 - Integer.numberOfLeadingZeros(i);
    }
}
