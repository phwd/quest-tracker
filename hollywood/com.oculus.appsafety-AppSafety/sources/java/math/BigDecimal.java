package java.math;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import libcore.math.MathUtils;
import sun.misc.DoubleConsts;

public class BigDecimal extends Number implements Comparable<BigDecimal>, Serializable {
    private static final BigDecimal[] BI_SCALED_BY_ZERO = new BigDecimal[11];
    private static final int BI_SCALED_BY_ZERO_LENGTH = 11;
    private static final char[] CH_ZEROS = new char[100];
    private static final BigInteger[] FIVE_POW = Multiplication.bigFivePows;
    private static final double LOG10_2 = 0.3010299956639812d;
    private static final long[] LONG_FIVE_POW = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125, 6103515625L, 30517578125L, 152587890625L, 762939453125L, 3814697265625L, 19073486328125L, 95367431640625L, 476837158203125L, 2384185791015625L, 11920928955078125L, 59604644775390625L, 298023223876953125L, 1490116119384765625L, 7450580596923828125L};
    private static final int[] LONG_FIVE_POW_BIT_LENGTH = new int[LONG_FIVE_POW.length];
    private static final int[] LONG_POWERS_OF_TEN_BIT_LENGTH = new int[MathUtils.LONG_POWERS_OF_TEN.length];
    public static final BigDecimal ONE = new BigDecimal(1, 0);
    public static final int ROUND_CEILING = 2;
    public static final int ROUND_DOWN = 1;
    public static final int ROUND_FLOOR = 3;
    public static final int ROUND_HALF_DOWN = 5;
    public static final int ROUND_HALF_EVEN = 6;
    public static final int ROUND_HALF_UP = 4;
    public static final int ROUND_UNNECESSARY = 7;
    public static final int ROUND_UP = 0;
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

    private BigDecimal(long smallValue2, int scale2) {
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        this.smallValue = smallValue2;
        this.scale = scale2;
        this.bitLength = bitLength(smallValue2);
    }

    private BigDecimal(int smallValue2, int scale2) {
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        this.smallValue = (long) smallValue2;
        this.scale = scale2;
        this.bitLength = bitLength(smallValue2);
    }

    public BigDecimal(char[] in, int offset, int len) {
        int begin;
        int offset2;
        int offset3 = offset;
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        int begin2 = offset;
        int last = (len - 1) + offset3;
        if (in == null) {
            throw new NullPointerException("in == null");
        } else if (last >= in.length || offset3 < 0 || len <= 0 || last < 0) {
            throw new NumberFormatException("Bad offset/length: offset=" + offset3 + " len=" + len + " in.length=" + in.length);
        } else {
            StringBuilder unscaledBuffer = new StringBuilder(len);
            if (offset3 <= last && in[offset3] == '+') {
                offset3++;
                begin2++;
            }
            int counter = 0;
            boolean wasNonZero = false;
            while (offset3 <= last && in[offset3] != '.' && in[offset3] != 'e' && in[offset3] != 'E') {
                if (!wasNonZero) {
                    if (in[offset3] == '0') {
                        counter++;
                    } else {
                        wasNonZero = true;
                    }
                }
                offset3++;
            }
            unscaledBuffer.append(in, begin2, offset3 - begin2);
            int bufLength = 0 + (offset3 - begin2);
            if (offset3 > last || in[offset3] != '.') {
                this.scale = 0;
            } else {
                offset3++;
                while (offset3 <= last && in[offset3] != 'e' && in[offset3] != 'E') {
                    if (!wasNonZero) {
                        if (in[offset3] == '0') {
                            counter++;
                        } else {
                            wasNonZero = true;
                        }
                    }
                    offset3++;
                }
                this.scale = offset3 - offset3;
                int i = this.scale;
                bufLength += i;
                unscaledBuffer.append(in, offset3, i);
            }
            if (offset3 <= last && (in[offset3] == 'e' || in[offset3] == 'E')) {
                int offset4 = offset3 + 1;
                if (offset4 > last || in[offset4] != '+' || (offset2 = offset4 + 1) > last || in[offset2] == '-') {
                    begin = offset4;
                } else {
                    begin = offset4 + 1;
                }
                long newScale = ((long) this.scale) - ((long) Integer.parseInt(String.valueOf(in, begin, (last + 1) - begin)));
                this.scale = (int) newScale;
                if (newScale != ((long) this.scale)) {
                    throw new NumberFormatException("Scale out of range");
                }
            }
            if (bufLength < 19) {
                this.smallValue = Long.parseLong(unscaledBuffer.toString());
                this.bitLength = bitLength(this.smallValue);
                return;
            }
            setUnscaledValue(new BigInteger(unscaledBuffer.toString()));
        }
    }

    public BigDecimal(char[] in, int offset, int len, MathContext mc) {
        this(in, offset, len);
        inplaceRound(mc);
    }

    public BigDecimal(char[] in) {
        this(in, 0, in.length);
    }

    public BigDecimal(char[] in, MathContext mc) {
        this(in, 0, in.length);
        inplaceRound(mc);
    }

    public BigDecimal(String val) {
        this(val.toCharArray(), 0, val.length());
    }

    public BigDecimal(String val, MathContext mc) {
        this(val.toCharArray(), 0, val.length());
        inplaceRound(mc);
    }

    public BigDecimal(double val) {
        long mantissa;
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        if (Double.isInfinite(val) || Double.isNaN(val)) {
            throw new NumberFormatException("Infinity or NaN: " + val);
        }
        long bits = Double.doubleToLongBits(val);
        this.scale = 1075 - ((int) ((bits >> 52) & 2047));
        if (this.scale == 1075) {
            mantissa = (bits & DoubleConsts.SIGNIF_BIT_MASK) << 1;
        } else {
            mantissa = (bits & DoubleConsts.SIGNIF_BIT_MASK) | 4503599627370496L;
        }
        if (mantissa == 0) {
            this.scale = 0;
            this.precision = 1;
        }
        int i = this.scale;
        if (i > 0) {
            int trailingZeros = Math.min(i, Long.numberOfTrailingZeros(mantissa));
            mantissa >>>= trailingZeros;
            this.scale -= trailingZeros;
        }
        mantissa = (bits >> 63) != 0 ? -mantissa : mantissa;
        int mantissaBits = bitLength(mantissa);
        int i2 = this.scale;
        if (i2 < 0) {
            this.bitLength = mantissaBits == 0 ? 0 : mantissaBits - i2;
            if (this.bitLength < 64) {
                this.smallValue = mantissa << (-this.scale);
            } else {
                BigInt bi = new BigInt();
                bi.putLongInt(mantissa);
                bi.shift(-this.scale);
                this.intVal = new BigInteger(bi);
            }
            this.scale = 0;
        } else if (i2 > 0) {
            long[] jArr = LONG_FIVE_POW;
            if (i2 >= jArr.length || LONG_FIVE_POW_BIT_LENGTH[i2] + mantissaBits >= 64) {
                setUnscaledValue(Multiplication.multiplyByFivePow(BigInteger.valueOf(mantissa), this.scale));
                return;
            }
            this.smallValue = jArr[i2] * mantissa;
            this.bitLength = bitLength(this.smallValue);
        } else {
            this.smallValue = mantissa;
            this.bitLength = mantissaBits;
        }
    }

    public BigDecimal(double val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }

    public BigDecimal(BigInteger val) {
        this(val, 0);
    }

    public BigDecimal(BigInteger val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }

    public BigDecimal(BigInteger unscaledVal, int scale2) {
        this.toStringImage = null;
        this.hashCode = 0;
        this.precision = 0;
        if (unscaledVal != null) {
            this.scale = scale2;
            setUnscaledValue(unscaledVal);
            return;
        }
        throw new NullPointerException("unscaledVal == null");
    }

    public BigDecimal(BigInteger unscaledVal, int scale2, MathContext mc) {
        this(unscaledVal, scale2);
        inplaceRound(mc);
    }

    public BigDecimal(int val) {
        this(val, 0);
    }

    public BigDecimal(int val, MathContext mc) {
        this(val, 0);
        inplaceRound(mc);
    }

    public BigDecimal(long val) {
        this(val, 0);
    }

    public BigDecimal(long val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }

    public static BigDecimal valueOf(long unscaledVal, int scale2) {
        if (scale2 == 0) {
            return valueOf(unscaledVal);
        }
        if (unscaledVal == 0 && scale2 >= 0) {
            BigDecimal[] bigDecimalArr = ZERO_SCALED_BY;
            if (scale2 < bigDecimalArr.length) {
                return bigDecimalArr[scale2];
            }
        }
        return new BigDecimal(unscaledVal, scale2);
    }

    public static BigDecimal valueOf(long unscaledVal) {
        if (unscaledVal < 0 || unscaledVal >= 11) {
            return new BigDecimal(unscaledVal, 0);
        }
        return BI_SCALED_BY_ZERO[(int) unscaledVal];
    }

    public static BigDecimal valueOf(double val) {
        if (!Double.isInfinite(val) && !Double.isNaN(val)) {
            return new BigDecimal(Double.toString(val));
        }
        throw new NumberFormatException("Infinity or NaN: " + val);
    }

    public BigDecimal add(BigDecimal augend) {
        int diffScale = this.scale - augend.scale;
        if (isZero()) {
            if (diffScale <= 0) {
                return augend;
            }
            if (augend.isZero()) {
                return this;
            }
        } else if (augend.isZero() && diffScale >= 0) {
            return this;
        }
        if (diffScale == 0) {
            if (Math.max(this.bitLength, augend.bitLength) + 1 < 64) {
                return valueOf(this.smallValue + augend.smallValue, this.scale);
            }
            return new BigDecimal(getUnscaledValue().add(augend.getUnscaledValue()), this.scale);
        } else if (diffScale > 0) {
            return addAndMult10(this, augend, diffScale);
        } else {
            return addAndMult10(augend, this, -diffScale);
        }
    }

    private static BigDecimal addAndMult10(BigDecimal thisValue, BigDecimal augend, int diffScale) {
        if (diffScale < MathUtils.LONG_POWERS_OF_TEN.length && Math.max(thisValue.bitLength, augend.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[diffScale]) + 1 < 64) {
            return valueOf(thisValue.smallValue + (augend.smallValue * MathUtils.LONG_POWERS_OF_TEN[diffScale]), thisValue.scale);
        }
        BigInt bi = Multiplication.multiplyByTenPow(augend.getUnscaledValue(), (long) diffScale).getBigInt();
        bi.add(thisValue.getUnscaledValue().getBigInt());
        return new BigDecimal(new BigInteger(bi), thisValue.scale);
    }

    public BigDecimal add(BigDecimal augend, MathContext mc) {
        BigDecimal smaller;
        BigDecimal larger;
        BigInteger tempBI;
        long diffScale = ((long) this.scale) - ((long) augend.scale);
        if (augend.isZero() || isZero() || mc.getPrecision() == 0) {
            return add(augend).round(mc);
        }
        if (((long) approxPrecision()) < diffScale - 1) {
            larger = augend;
            smaller = this;
        } else if (((long) augend.approxPrecision()) >= (-diffScale) - 1) {
            return add(augend).round(mc);
        } else {
            larger = this;
            smaller = augend;
        }
        if (mc.getPrecision() >= larger.approxPrecision()) {
            return add(augend).round(mc);
        }
        int largerSignum = larger.signum();
        if (largerSignum == smaller.signum()) {
            tempBI = Multiplication.multiplyByPositiveInt(larger.getUnscaledValue(), 10).add(BigInteger.valueOf((long) largerSignum));
        } else {
            tempBI = Multiplication.multiplyByPositiveInt(larger.getUnscaledValue().subtract(BigInteger.valueOf((long) largerSignum)), 10).add(BigInteger.valueOf((long) (largerSignum * 9)));
        }
        return new BigDecimal(tempBI, larger.scale + 1).round(mc);
    }

    public BigDecimal subtract(BigDecimal subtrahend) {
        int diffScale = this.scale - subtrahend.scale;
        if (isZero()) {
            if (diffScale <= 0) {
                return subtrahend.negate();
            }
            if (subtrahend.isZero()) {
                return this;
            }
        } else if (subtrahend.isZero() && diffScale >= 0) {
            return this;
        }
        if (diffScale == 0) {
            if (Math.max(this.bitLength, subtrahend.bitLength) + 1 < 64) {
                return valueOf(this.smallValue - subtrahend.smallValue, this.scale);
            }
            return new BigDecimal(getUnscaledValue().subtract(subtrahend.getUnscaledValue()), this.scale);
        } else if (diffScale <= 0) {
            int diffScale2 = -diffScale;
            if (diffScale2 >= MathUtils.LONG_POWERS_OF_TEN.length || Math.max(this.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[diffScale2], subtrahend.bitLength) + 1 >= 64) {
                return new BigDecimal(Multiplication.multiplyByTenPow(getUnscaledValue(), (long) diffScale2).subtract(subtrahend.getUnscaledValue()), subtrahend.scale);
            }
            return valueOf((this.smallValue * MathUtils.LONG_POWERS_OF_TEN[diffScale2]) - subtrahend.smallValue, subtrahend.scale);
        } else if (diffScale >= MathUtils.LONG_POWERS_OF_TEN.length || Math.max(this.bitLength, subtrahend.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[diffScale]) + 1 >= 64) {
            return new BigDecimal(getUnscaledValue().subtract(Multiplication.multiplyByTenPow(subtrahend.getUnscaledValue(), (long) diffScale)), this.scale);
        } else {
            return valueOf(this.smallValue - (subtrahend.smallValue * MathUtils.LONG_POWERS_OF_TEN[diffScale]), this.scale);
        }
    }

    public BigDecimal subtract(BigDecimal subtrahend, MathContext mc) {
        BigInteger tempBI;
        long diffScale = ((long) subtrahend.scale) - ((long) this.scale);
        if (subtrahend.isZero() || isZero() || mc.getPrecision() == 0) {
            return subtract(subtrahend).round(mc);
        }
        if (((long) subtrahend.approxPrecision()) >= diffScale - 1 || mc.getPrecision() >= approxPrecision()) {
            return subtract(subtrahend).round(mc);
        }
        int thisSignum = signum();
        if (thisSignum != subtrahend.signum()) {
            tempBI = Multiplication.multiplyByPositiveInt(getUnscaledValue(), 10).add(BigInteger.valueOf((long) thisSignum));
        } else {
            tempBI = Multiplication.multiplyByPositiveInt(getUnscaledValue().subtract(BigInteger.valueOf((long) thisSignum)), 10).add(BigInteger.valueOf((long) (thisSignum * 9)));
        }
        return new BigDecimal(tempBI, this.scale + 1).round(mc);
    }

    public BigDecimal multiply(BigDecimal multiplicand) {
        long newScale = ((long) this.scale) + ((long) multiplicand.scale);
        if (isZero() || multiplicand.isZero()) {
            return zeroScaledBy(newScale);
        }
        if (this.bitLength + multiplicand.bitLength < 64) {
            long j = this.smallValue;
            long unscaledValue = multiplicand.smallValue * j;
            if (!(unscaledValue == Long.MIN_VALUE && Math.signum((float) j) * Math.signum((float) multiplicand.smallValue) > 0.0f)) {
                return valueOf(unscaledValue, safeLongToInt(newScale));
            }
        }
        return new BigDecimal(getUnscaledValue().multiply(multiplicand.getUnscaledValue()), safeLongToInt(newScale));
    }

    public BigDecimal multiply(BigDecimal multiplicand, MathContext mc) {
        BigDecimal result = multiply(multiplicand);
        result.inplaceRound(mc);
        return result;
    }

    public BigDecimal divide(BigDecimal divisor, int scale2, int roundingMode) {
        return divide(divisor, scale2, RoundingMode.valueOf(roundingMode));
    }

    public BigDecimal divide(BigDecimal divisor, int scale2, RoundingMode roundingMode) {
        if (roundingMode == null) {
            throw new NullPointerException("roundingMode == null");
        } else if (!divisor.isZero()) {
            long diffScale = (((long) this.scale) - ((long) divisor.scale)) - ((long) scale2);
            if (bitLength(diffScale) <= 32) {
                if (this.bitLength < 64 && divisor.bitLength < 64) {
                    if (diffScale == 0) {
                        if (!(this.smallValue == Long.MIN_VALUE && divisor.smallValue == -1)) {
                            return dividePrimitiveLongs(this.smallValue, divisor.smallValue, scale2, roundingMode);
                        }
                    } else if (diffScale > 0) {
                        if (diffScale < ((long) MathUtils.LONG_POWERS_OF_TEN.length) && divisor.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[(int) diffScale] < 64) {
                            return dividePrimitiveLongs(this.smallValue, divisor.smallValue * MathUtils.LONG_POWERS_OF_TEN[(int) diffScale], scale2, roundingMode);
                        }
                    } else if ((-diffScale) < ((long) MathUtils.LONG_POWERS_OF_TEN.length) && this.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[(int) (-diffScale)] < 64) {
                        return dividePrimitiveLongs(this.smallValue * MathUtils.LONG_POWERS_OF_TEN[(int) (-diffScale)], divisor.smallValue, scale2, roundingMode);
                    }
                }
                BigInteger scaledDividend = getUnscaledValue();
                BigInteger scaledDivisor = divisor.getUnscaledValue();
                if (diffScale > 0) {
                    scaledDivisor = Multiplication.multiplyByTenPow(scaledDivisor, (long) ((int) diffScale));
                } else if (diffScale < 0) {
                    scaledDividend = Multiplication.multiplyByTenPow(scaledDividend, (long) ((int) (-diffScale)));
                }
                return divideBigIntegers(scaledDividend, scaledDivisor, scale2, roundingMode);
            }
            throw new ArithmeticException("Unable to perform divisor / dividend scaling: the difference in scale is too big (" + diffScale + ")");
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }

    private static BigDecimal divideBigIntegers(BigInteger scaledDividend, BigInteger scaledDivisor, int scale2, RoundingMode roundingMode) {
        int compRem;
        BigInteger[] quotAndRem = scaledDividend.divideAndRemainder(scaledDivisor);
        BigInteger quotient = quotAndRem[0];
        BigInteger remainder = quotAndRem[1];
        if (remainder.signum() == 0) {
            return new BigDecimal(quotient, scale2);
        }
        int sign = scaledDividend.signum() * scaledDivisor.signum();
        if (scaledDivisor.bitLength() < 63) {
            int compRem2 = compareForRounding(remainder.longValue(), scaledDivisor.longValue());
            compRem = roundingBehavior(quotient.testBit(0) ? 1 : 0, (compRem2 + 5) * sign, roundingMode);
        } else {
            int compRem3 = remainder.abs().shiftLeftOneBit().compareTo(scaledDivisor.abs());
            compRem = roundingBehavior(quotient.testBit(0) ? 1 : 0, (compRem3 + 5) * sign, roundingMode);
        }
        if (compRem == 0) {
            return new BigDecimal(quotient, scale2);
        }
        if (quotient.bitLength() < 63) {
            return valueOf(quotient.longValue() + ((long) compRem), scale2);
        }
        return new BigDecimal(quotient.add(BigInteger.valueOf((long) compRem)), scale2);
    }

    private static BigDecimal dividePrimitiveLongs(long scaledDividend, long scaledDivisor, int scale2, RoundingMode roundingMode) {
        long quotient = scaledDividend / scaledDivisor;
        long remainder = scaledDividend % scaledDivisor;
        int sign = Long.signum(scaledDividend) * Long.signum(scaledDivisor);
        if (remainder != 0) {
            quotient += (long) roundingBehavior(((int) quotient) & 1, (compareForRounding(remainder, scaledDivisor) + 5) * sign, roundingMode);
        }
        return valueOf(quotient, scale2);
    }

    public BigDecimal divide(BigDecimal divisor, int roundingMode) {
        return divide(divisor, this.scale, RoundingMode.valueOf(roundingMode));
    }

    public BigDecimal divide(BigDecimal divisor, RoundingMode roundingMode) {
        return divide(divisor, this.scale, roundingMode);
    }

    public BigDecimal divide(BigDecimal divisor) {
        BigInteger p;
        BigInteger p2 = getUnscaledValue();
        BigInteger q = divisor.getUnscaledValue();
        long diffScale = ((long) this.scale) - ((long) divisor.scale);
        int l = 0;
        int i = 1;
        int lastPow = FIVE_POW.length - 1;
        if (divisor.isZero()) {
            throw new ArithmeticException("Division by zero");
        } else if (p2.signum() == 0) {
            return zeroScaledBy(diffScale);
        } else {
            BigInteger gcd = p2.gcd(q);
            BigInteger p3 = p2.divide(gcd);
            BigInteger q2 = q.divide(gcd);
            int k = q2.getLowestSetBit();
            BigInteger q3 = q2.shiftRight(k);
            while (true) {
                BigInteger[] quotAndRem = q3.divideAndRemainder(FIVE_POW[i]);
                if (quotAndRem[1].signum() == 0) {
                    l += i;
                    if (i < lastPow) {
                        i++;
                    }
                    q3 = quotAndRem[0];
                } else if (i == 1) {
                    break;
                } else {
                    i = 1;
                }
            }
            if (q3.abs().equals(BigInteger.ONE)) {
                if (q3.signum() < 0) {
                    p3 = p3.negate();
                }
                int newScale = safeLongToInt(((long) Math.max(k, l)) + diffScale);
                int i2 = k - l;
                if (i2 > 0) {
                    p = Multiplication.multiplyByFivePow(p3, i2);
                } else {
                    p = p3.shiftLeft(-i2);
                }
                return new BigDecimal(p, newScale);
            }
            throw new ArithmeticException("Non-terminating decimal expansion; no exact representable decimal result");
        }
    }

    public BigDecimal divide(BigDecimal divisor, MathContext mc) {
        long trailingZeros = ((((long) mc.getPrecision()) + 2) + ((long) divisor.approxPrecision())) - ((long) approxPrecision());
        long diffScale = ((long) this.scale) - ((long) divisor.scale);
        long newScale = diffScale;
        int i = 1;
        int lastPow = TEN_POW.length - 1;
        BigInteger[] quotAndRem = {getUnscaledValue()};
        if (mc.getPrecision() != 0 && !isZero()) {
            if (!divisor.isZero()) {
                if (trailingZeros > 0) {
                    quotAndRem[0] = getUnscaledValue().multiply(Multiplication.powerOf10(trailingZeros));
                    newScale += trailingZeros;
                }
                BigInteger[] quotAndRem2 = quotAndRem[0].divideAndRemainder(divisor.getUnscaledValue());
                BigInteger integerQuot = quotAndRem2[0];
                if (quotAndRem2[1].signum() != 0) {
                    newScale++;
                    integerQuot = integerQuot.multiply(BigInteger.TEN).add(BigInteger.valueOf((long) (quotAndRem2[0].signum() * (quotAndRem2[1].shiftLeftOneBit().compareTo(divisor.getUnscaledValue()) + 5))));
                } else {
                    while (!integerQuot.testBit(0)) {
                        BigInteger[] quotAndRem3 = integerQuot.divideAndRemainder(TEN_POW[i]);
                        if (quotAndRem3[1].signum() == 0 && newScale - ((long) i) >= diffScale) {
                            newScale -= (long) i;
                            if (i < lastPow) {
                                i++;
                            }
                            integerQuot = quotAndRem3[0];
                        } else if (i == 1) {
                            break;
                        } else {
                            i = 1;
                        }
                    }
                }
                return new BigDecimal(integerQuot, safeLongToInt(newScale), mc);
            }
        }
        return divide(divisor);
    }

    public BigDecimal divideToIntegralValue(BigDecimal divisor) {
        BigInteger integralValue;
        long newScale = ((long) this.scale) - ((long) divisor.scale);
        long tempScale = 0;
        int i = 1;
        int lastPow = TEN_POW.length - 1;
        if (!divisor.isZero()) {
            if (((long) divisor.approxPrecision()) + newScale > ((long) approxPrecision()) + 1 || isZero()) {
                integralValue = BigInteger.ZERO;
            } else if (newScale == 0) {
                integralValue = getUnscaledValue().divide(divisor.getUnscaledValue());
            } else if (newScale > 0) {
                BigInteger powerOfTen = Multiplication.powerOf10(newScale);
                integralValue = getUnscaledValue().divide(divisor.getUnscaledValue().multiply(powerOfTen)).multiply(powerOfTen);
            } else {
                BigInteger integralValue2 = getUnscaledValue().multiply(Multiplication.powerOf10(-newScale)).divide(divisor.getUnscaledValue());
                while (!integralValue2.testBit(0)) {
                    BigInteger[] quotAndRem = integralValue2.divideAndRemainder(TEN_POW[i]);
                    if (quotAndRem[1].signum() == 0 && tempScale - ((long) i) >= newScale) {
                        tempScale -= (long) i;
                        if (i < lastPow) {
                            i++;
                        }
                        integralValue2 = quotAndRem[0];
                    } else if (i == 1) {
                        break;
                    } else {
                        i = 1;
                    }
                }
                newScale = tempScale;
                integralValue = integralValue2;
            }
            if (integralValue.signum() == 0) {
                return zeroScaledBy(newScale);
            }
            return new BigDecimal(integralValue, safeLongToInt(newScale));
        }
        throw new ArithmeticException("Division by zero");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.math.BigDecimal divideToIntegralValue(java.math.BigDecimal r25, java.math.MathContext r26) {
        /*
        // Method dump skipped, instructions count: 423
        */
        throw new UnsupportedOperationException("Method not decompiled: java.math.BigDecimal.divideToIntegralValue(java.math.BigDecimal, java.math.MathContext):java.math.BigDecimal");
    }

    public BigDecimal remainder(BigDecimal divisor) {
        return divideAndRemainder(divisor)[1];
    }

    public BigDecimal remainder(BigDecimal divisor, MathContext mc) {
        return divideAndRemainder(divisor, mc)[1];
    }

    public BigDecimal[] divideAndRemainder(BigDecimal divisor) {
        BigDecimal[] quotAndRem = new BigDecimal[2];
        quotAndRem[0] = divideToIntegralValue(divisor);
        quotAndRem[1] = subtract(quotAndRem[0].multiply(divisor));
        return quotAndRem;
    }

    public BigDecimal[] divideAndRemainder(BigDecimal divisor, MathContext mc) {
        BigDecimal[] quotAndRem = new BigDecimal[2];
        quotAndRem[0] = divideToIntegralValue(divisor, mc);
        quotAndRem[1] = subtract(quotAndRem[0].multiply(divisor));
        return quotAndRem;
    }

    public BigDecimal pow(int n) {
        if (n == 0) {
            return ONE;
        }
        if (n < 0 || n > 999999999) {
            throw new ArithmeticException("Invalid operation");
        }
        long newScale = ((long) this.scale) * ((long) n);
        if (isZero()) {
            return zeroScaledBy(newScale);
        }
        return new BigDecimal(getUnscaledValue().pow(n), safeLongToInt(newScale));
    }

    public BigDecimal pow(int n, MathContext mc) {
        int m = Math.abs(n);
        int mcPrecision = mc.getPrecision();
        int elength = ((int) Math.log10((double) m)) + 1;
        MathContext newPrecision = mc;
        if (n == 0 || (isZero() && n > 0)) {
            return pow(n);
        }
        if (m > 999999999 || ((mcPrecision == 0 && n < 0) || (mcPrecision > 0 && elength > mcPrecision))) {
            throw new ArithmeticException("Invalid operation");
        }
        if (mcPrecision > 0) {
            newPrecision = new MathContext(mcPrecision + elength + 1, mc.getRoundingMode());
        }
        BigDecimal accum = round(newPrecision);
        for (int oneBitMask = Integer.highestOneBit(m) >> 1; oneBitMask > 0; oneBitMask >>= 1) {
            accum = accum.multiply(accum, newPrecision);
            if ((m & oneBitMask) == oneBitMask) {
                accum = accum.multiply(this, newPrecision);
            }
        }
        if (n < 0) {
            accum = ONE.divide(accum, newPrecision);
        }
        accum.inplaceRound(mc);
        return accum;
    }

    public BigDecimal abs() {
        return signum() < 0 ? negate() : this;
    }

    public BigDecimal abs(MathContext mc) {
        BigDecimal result = signum() < 0 ? negate() : new BigDecimal(getUnscaledValue(), this.scale);
        result.inplaceRound(mc);
        return result;
    }

    public BigDecimal negate() {
        int i = this.bitLength;
        if (i < 63 || (i == 63 && this.smallValue != Long.MIN_VALUE)) {
            return valueOf(-this.smallValue, this.scale);
        }
        return new BigDecimal(getUnscaledValue().negate(), this.scale);
    }

    public BigDecimal negate(MathContext mc) {
        BigDecimal result = negate();
        result.inplaceRound(mc);
        return result;
    }

    public BigDecimal plus() {
        return this;
    }

    public BigDecimal plus(MathContext mc) {
        return round(mc);
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
            int decimalDigits = ((int) (((double) (i2 - 1)) * LOG10_2)) + 1;
            if (getUnscaledValue().divide(Multiplication.powerOf10((long) decimalDigits)).signum() != 0) {
                decimalDigits++;
            }
            this.precision = decimalDigits;
        }
        return this.precision;
    }

    private int decimalDigitsInLong(long value) {
        if (value == Long.MIN_VALUE) {
            return 19;
        }
        int index = Arrays.binarySearch(MathUtils.LONG_POWERS_OF_TEN, Math.abs(value));
        return index < 0 ? (-index) - 1 : index + 1;
    }

    public BigInteger unscaledValue() {
        return getUnscaledValue();
    }

    public BigDecimal round(MathContext mc) {
        BigDecimal thisBD = new BigDecimal(getUnscaledValue(), this.scale);
        thisBD.inplaceRound(mc);
        return thisBD;
    }

    public BigDecimal setScale(int newScale, RoundingMode roundingMode) {
        if (roundingMode != null) {
            long diffScale = ((long) newScale) - ((long) this.scale);
            if (diffScale == 0) {
                return this;
            }
            if (diffScale > 0) {
                if (diffScale >= ((long) MathUtils.LONG_POWERS_OF_TEN.length) || this.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[(int) diffScale] >= 64) {
                    return new BigDecimal(Multiplication.multiplyByTenPow(getUnscaledValue(), (long) ((int) diffScale)), newScale);
                }
                return valueOf(this.smallValue * MathUtils.LONG_POWERS_OF_TEN[(int) diffScale], newScale);
            } else if (this.bitLength >= 64 || (-diffScale) >= ((long) MathUtils.LONG_POWERS_OF_TEN.length)) {
                return divideBigIntegers(getUnscaledValue(), Multiplication.powerOf10(-diffScale), newScale, roundingMode);
            } else {
                return dividePrimitiveLongs(this.smallValue, MathUtils.LONG_POWERS_OF_TEN[(int) (-diffScale)], newScale, roundingMode);
            }
        } else {
            throw new NullPointerException("roundingMode == null");
        }
    }

    public BigDecimal setScale(int newScale, int roundingMode) {
        return setScale(newScale, RoundingMode.valueOf(roundingMode));
    }

    public BigDecimal setScale(int newScale) {
        return setScale(newScale, RoundingMode.UNNECESSARY);
    }

    public BigDecimal movePointLeft(int n) {
        return movePoint(((long) this.scale) + ((long) n));
    }

    private BigDecimal movePoint(long newScale) {
        if (isZero()) {
            return zeroScaledBy(Math.max(newScale, 0L));
        }
        if (newScale >= 0) {
            if (this.bitLength < 64) {
                return valueOf(this.smallValue, safeLongToInt(newScale));
            }
            return new BigDecimal(getUnscaledValue(), safeLongToInt(newScale));
        } else if ((-newScale) >= ((long) MathUtils.LONG_POWERS_OF_TEN.length) || this.bitLength + LONG_POWERS_OF_TEN_BIT_LENGTH[(int) (-newScale)] >= 64) {
            return new BigDecimal(Multiplication.multiplyByTenPow(getUnscaledValue(), (long) safeLongToInt(-newScale)), 0);
        } else {
            return valueOf(this.smallValue * MathUtils.LONG_POWERS_OF_TEN[(int) (-newScale)], 0);
        }
    }

    public BigDecimal movePointRight(int n) {
        return movePoint(((long) this.scale) - ((long) n));
    }

    public BigDecimal scaleByPowerOfTen(int n) {
        long newScale = ((long) this.scale) - ((long) n);
        if (this.bitLength >= 64) {
            return new BigDecimal(getUnscaledValue(), safeLongToInt(newScale));
        }
        long j = this.smallValue;
        if (j == 0) {
            return zeroScaledBy(newScale);
        }
        return valueOf(j, safeLongToInt(newScale));
    }

    public BigDecimal stripTrailingZeros() {
        int i = 1;
        int lastPow = TEN_POW.length - 1;
        long newScale = (long) this.scale;
        if (isZero()) {
            return new BigDecimal(BigInteger.ZERO, 0);
        }
        BigInteger strippedBI = getUnscaledValue();
        while (!strippedBI.testBit(0)) {
            BigInteger[] quotAndRem = strippedBI.divideAndRemainder(TEN_POW[i]);
            if (quotAndRem[1].signum() == 0) {
                newScale -= (long) i;
                if (i < lastPow) {
                    i++;
                }
                strippedBI = quotAndRem[0];
            } else if (i == 1) {
                break;
            } else {
                i = 1;
            }
        }
        return new BigDecimal(strippedBI, safeLongToInt(newScale));
    }

    public int compareTo(BigDecimal val) {
        int thisSign = signum();
        int valueSign = val.signum();
        if (thisSign != valueSign) {
            return thisSign < valueSign ? -1 : 1;
        }
        if (this.scale != val.scale || this.bitLength >= 64 || val.bitLength >= 64) {
            long diffScale = ((long) this.scale) - ((long) val.scale);
            int diffPrecision = approxPrecision() - val.approxPrecision();
            if (((long) diffPrecision) > diffScale + 1) {
                return thisSign;
            }
            if (((long) diffPrecision) < diffScale - 1) {
                return -thisSign;
            }
            BigInteger thisUnscaled = getUnscaledValue();
            BigInteger valUnscaled = val.getUnscaledValue();
            if (diffScale < 0) {
                thisUnscaled = thisUnscaled.multiply(Multiplication.powerOf10(-diffScale));
            } else if (diffScale > 0) {
                valUnscaled = valUnscaled.multiply(Multiplication.powerOf10(diffScale));
            }
            return thisUnscaled.compareTo(valUnscaled);
        }
        long j = this.smallValue;
        long j2 = val.smallValue;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object x) {
        int i;
        if (this == x) {
            return true;
        }
        if (!(x instanceof BigDecimal)) {
            return false;
        }
        BigDecimal x1 = (BigDecimal) x;
        if (x1.scale == this.scale && x1.bitLength == (i = this.bitLength)) {
            if (i < 64) {
                if (x1.smallValue == this.smallValue) {
                    return true;
                }
            } else if (x1.intVal.equals(this.intVal)) {
                return true;
            }
        }
        return false;
    }

    public BigDecimal min(BigDecimal val) {
        return compareTo(val) <= 0 ? this : val;
    }

    public BigDecimal max(BigDecimal val) {
        return compareTo(val) >= 0 ? this : val;
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
        String intString = getUnscaledValue().toString();
        if (this.scale == 0) {
            return intString;
        }
        int begin = getUnscaledValue().signum() < 0 ? 2 : 1;
        int end = intString.length();
        long exponent = ((-((long) this.scale)) + ((long) end)) - ((long) begin);
        StringBuilder result = new StringBuilder();
        result.append(intString);
        int i = this.scale;
        if (i <= 0 || exponent < -6) {
            if (end - begin >= 1) {
                result.insert(begin, '.');
                end++;
            }
            result.insert(end, 'E');
            if (exponent > 0) {
                end++;
                result.insert(end, '+');
            }
            result.insert(end + 1, Long.toString(exponent));
        } else if (exponent >= 0) {
            result.insert(end - i, '.');
        } else {
            result.insert(begin - 1, "0.");
            result.insert(begin + 1, CH_ZEROS, 0, (-((int) exponent)) - 1);
        }
        this.toStringImage = result.toString();
        return this.toStringImage;
    }

    public String toEngineeringString() {
        int rem;
        String intString = getUnscaledValue().toString();
        if (this.scale == 0) {
            return intString;
        }
        int begin = getUnscaledValue().signum() < 0 ? 2 : 1;
        int end = intString.length();
        long exponent = ((-((long) this.scale)) + ((long) end)) - ((long) begin);
        StringBuilder result = new StringBuilder(intString);
        int i = this.scale;
        if (i <= 0 || exponent < -6) {
            int delta = end - begin;
            int rem2 = (int) (exponent % 3);
            if (rem2 != 0) {
                if (getUnscaledValue().signum() == 0) {
                    rem = rem2 < 0 ? -rem2 : 3 - rem2;
                    exponent += (long) rem;
                } else {
                    int rem3 = rem2 < 0 ? rem2 + 3 : rem2;
                    exponent -= (long) rem3;
                    begin += rem3;
                    rem = rem3;
                }
                if (delta < 3) {
                    int i2 = rem - delta;
                    while (i2 > 0) {
                        result.insert(end, '0');
                        i2--;
                        end++;
                    }
                }
            }
            if (end - begin >= 1) {
                result.insert(begin, '.');
                end++;
            }
            if (exponent != 0) {
                result.insert(end, 'E');
                if (exponent > 0) {
                    end++;
                    result.insert(end, '+');
                }
                result.insert(end + 1, Long.toString(exponent));
            }
        } else if (exponent >= 0) {
            result.insert(end - i, '.');
        } else {
            result.insert(begin - 1, "0.");
            result.insert(begin + 1, CH_ZEROS, 0, (-((int) exponent)) - 1);
        }
        return result.toString();
    }

    public String toPlainString() {
        char[] cArr;
        char[] cArr2;
        String intStr = getUnscaledValue().toString();
        if (this.scale == 0 || (isZero() && this.scale < 0)) {
            return intStr;
        }
        int begin = signum() < 0 ? 1 : 0;
        int delta = this.scale;
        StringBuilder result = new StringBuilder(intStr.length() + 1 + Math.abs(this.scale));
        if (begin == 1) {
            result.append('-');
        }
        if (this.scale > 0) {
            int delta2 = delta - (intStr.length() - begin);
            if (delta2 >= 0) {
                result.append("0.");
                while (true) {
                    cArr2 = CH_ZEROS;
                    if (delta2 <= cArr2.length) {
                        break;
                    }
                    result.append(cArr2);
                    delta2 -= CH_ZEROS.length;
                }
                result.append(cArr2, 0, delta2);
                result.append(intStr.substring(begin));
            } else {
                int delta3 = begin - delta2;
                result.append(intStr.substring(begin, delta3));
                result.append('.');
                result.append(intStr.substring(delta3));
            }
        } else {
            result.append(intStr.substring(begin));
            while (true) {
                cArr = CH_ZEROS;
                if (delta >= (-cArr.length)) {
                    break;
                }
                result.append(cArr);
                delta += CH_ZEROS.length;
            }
            result.append(cArr, 0, -delta);
        }
        return result.toString();
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

    public BigInteger toBigIntegerExact() {
        if (this.scale == 0 || isZero()) {
            return getUnscaledValue();
        }
        int i = this.scale;
        if (i < 0) {
            return getUnscaledValue().multiply(Multiplication.powerOf10(-((long) this.scale)));
        }
        if (i > approxPrecision() || this.scale > getUnscaledValue().getLowestSetBit()) {
            throw new ArithmeticException("Rounding necessary");
        }
        BigInteger[] integerAndFraction = getUnscaledValue().divideAndRemainder(Multiplication.powerOf10((long) this.scale));
        if (integerAndFraction[1].signum() == 0) {
            return integerAndFraction[0];
        }
        throw new ArithmeticException("Rounding necessary");
    }

    @Override // java.lang.Number
    public long longValue() {
        int i = this.scale;
        if (i <= -64 || i > approxPrecision()) {
            return 0;
        }
        return toBigInteger().longValue();
    }

    public long longValueExact() {
        return valueExact(64);
    }

    @Override // java.lang.Number
    public int intValue() {
        int i = this.scale;
        if (i <= -32 || i > approxPrecision()) {
            return 0;
        }
        return toBigInteger().intValue();
    }

    public int intValueExact() {
        return (int) valueExact(32);
    }

    public short shortValueExact() {
        return (short) ((int) valueExact(16));
    }

    public byte byteValueExact() {
        return (byte) ((int) valueExact(8));
    }

    @Override // java.lang.Number
    public float floatValue() {
        float floatResult = (float) signum();
        long powerOfTwo = ((long) this.bitLength) - ((long) (((double) this.scale) / LOG10_2));
        if (powerOfTwo < -149 || floatResult == 0.0f) {
            return floatResult * 0.0f;
        }
        if (powerOfTwo > 129) {
            return floatResult * Float.POSITIVE_INFINITY;
        }
        return (float) doubleValue();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        BigInteger mantissa;
        long tempBits;
        long bits;
        long bits2;
        int exponent;
        int sign = signum();
        int exponent2 = 1076;
        long powerOfTwo = ((long) this.bitLength) - ((long) (((double) this.scale) / LOG10_2));
        if (powerOfTwo < -1074 || sign == 0) {
            return ((double) sign) * 0.0d;
        }
        if (powerOfTwo > 1025) {
            return ((double) sign) * Double.POSITIVE_INFINITY;
        }
        BigInteger mantissa2 = getUnscaledValue().abs();
        int i = this.scale;
        if (i <= 0) {
            mantissa = mantissa2.multiply(Multiplication.powerOf10((long) (-i)));
        } else {
            BigInteger powerOfTen = Multiplication.powerOf10((long) i);
            int k = 100 - ((int) powerOfTwo);
            if (k > 0) {
                mantissa2 = mantissa2.shiftLeft(k);
                exponent2 = 1076 - k;
            }
            BigInteger[] quotAndRem = mantissa2.divideAndRemainder(powerOfTen);
            int compRem = quotAndRem[1].shiftLeftOneBit().compareTo(powerOfTen);
            mantissa = quotAndRem[0].shiftLeft(2).add(BigInteger.valueOf((long) ((((compRem + 3) * compRem) / 2) + 1)));
            exponent2 -= 2;
        }
        int lowestSetBit = mantissa.getLowestSetBit();
        int discardedSize = mantissa.bitLength() - 54;
        if (discardedSize > 0) {
            bits = mantissa.shiftRight(discardedSize).longValue();
            tempBits = bits;
            if (((bits & 1) == 1 && lowestSetBit < discardedSize) || (bits & 3) == 3) {
                bits += 2;
            }
        } else {
            bits = mantissa.longValue() << (-discardedSize);
            tempBits = bits;
            if ((bits & 3) == 3) {
                bits += 2;
            }
        }
        if ((bits & 18014398509481984L) == 0) {
            bits2 = bits >> 1;
            exponent = exponent2 + discardedSize;
        } else {
            bits2 = bits >> 2;
            exponent = exponent2 + discardedSize + 1;
        }
        if (exponent > 2046) {
            return ((double) sign) * Double.POSITIVE_INFINITY;
        }
        if (exponent <= 0) {
            if (exponent < -53) {
                return ((double) sign) * 0.0d;
            }
            long bits3 = tempBits >> 1;
            long tempBits2 = bits3 & (-1 >>> (exponent + 63));
            long bits4 = bits3 >> (-exponent);
            if ((bits4 & 3) == 3 || ((bits4 & 1) == 1 && tempBits2 != 0 && lowestSetBit < discardedSize)) {
                bits4++;
            }
            exponent = 0;
            bits2 = bits4 >> 1;
        }
        return Double.longBitsToDouble((((long) sign) & Long.MIN_VALUE) | (((long) exponent) << 52) | (DoubleConsts.SIGNIF_BIT_MASK & bits2));
    }

    public BigDecimal ulp() {
        return valueOf(1, this.scale);
    }

    private void inplaceRound(MathContext mc) {
        int discardedPrecision;
        int mcPrecision = mc.getPrecision();
        if (approxPrecision() >= mcPrecision && mcPrecision != 0 && (discardedPrecision = precision() - mcPrecision) > 0) {
            if (this.bitLength < 64) {
                smallRound(mc, discardedPrecision);
                return;
            }
            BigInteger sizeOfFraction = Multiplication.powerOf10((long) discardedPrecision);
            BigInteger[] integerAndFraction = getUnscaledValue().divideAndRemainder(sizeOfFraction);
            long newScale = ((long) this.scale) - ((long) discardedPrecision);
            if (integerAndFraction[1].signum() != 0) {
                int compRem = integerAndFraction[1].abs().shiftLeftOneBit().compareTo(sizeOfFraction);
                int compRem2 = roundingBehavior(integerAndFraction[0].testBit(0) ? 1 : 0, integerAndFraction[1].signum() * (compRem + 5), mc.getRoundingMode());
                if (compRem2 != 0) {
                    integerAndFraction[0] = integerAndFraction[0].add(BigInteger.valueOf((long) compRem2));
                }
                if (new BigDecimal(integerAndFraction[0]).precision() > mcPrecision) {
                    integerAndFraction[0] = integerAndFraction[0].divide(BigInteger.TEN);
                    newScale--;
                }
            }
            this.scale = safeLongToInt(newScale);
            this.precision = mcPrecision;
            setUnscaledValue(integerAndFraction[0]);
        }
    }

    private static int compareAbsoluteValues(long value1, long value2) {
        long value12 = Math.abs(value1) - 1;
        long value22 = Math.abs(value2) - 1;
        if (value12 > value22) {
            return 1;
        }
        return value12 < value22 ? -1 : 0;
    }

    private static int compareForRounding(long n, long d) {
        long halfD = d / 2;
        if (n == halfD || n == (-halfD)) {
            return -(((int) d) & 1);
        }
        return compareAbsoluteValues(n, halfD);
    }

    private void smallRound(MathContext mc, int discardedPrecision) {
        long sizeOfFraction = MathUtils.LONG_POWERS_OF_TEN[discardedPrecision];
        long newScale = ((long) this.scale) - ((long) discardedPrecision);
        long unscaledVal = this.smallValue;
        long integer = unscaledVal / sizeOfFraction;
        long fraction = unscaledVal % sizeOfFraction;
        if (fraction != 0) {
            integer += (long) roundingBehavior(((int) integer) & 1, Long.signum(fraction) * (compareForRounding(fraction, sizeOfFraction) + 5), mc.getRoundingMode());
            if (Math.log10((double) Math.abs(integer)) >= ((double) mc.getPrecision())) {
                integer /= 10;
                newScale--;
            }
        }
        this.scale = safeLongToInt(newScale);
        this.precision = mc.getPrecision();
        this.smallValue = integer;
        this.bitLength = bitLength(integer);
        this.intVal = null;
    }

    private static int roundingBehavior(int parityBit, int fraction, RoundingMode roundingMode) {
        switch (roundingMode) {
            case UNNECESSARY:
                if (fraction == 0) {
                    return 0;
                }
                throw new ArithmeticException("Rounding necessary");
            case UP:
                return Integer.signum(fraction);
            case DOWN:
            default:
                return 0;
            case CEILING:
                return Math.max(Integer.signum(fraction), 0);
            case FLOOR:
                return Math.min(Integer.signum(fraction), 0);
            case HALF_UP:
                if (Math.abs(fraction) >= 5) {
                    return Integer.signum(fraction);
                }
                return 0;
            case HALF_DOWN:
                if (Math.abs(fraction) > 5) {
                    return Integer.signum(fraction);
                }
                return 0;
            case HALF_EVEN:
                if (Math.abs(fraction) + parityBit > 5) {
                    return Integer.signum(fraction);
                }
                return 0;
        }
    }

    private long valueExact(int bitLengthOfType) {
        BigInteger bigInteger = toBigIntegerExact();
        if (bigInteger.bitLength() < bitLengthOfType) {
            return bigInteger.longValue();
        }
        throw new ArithmeticException("Rounding necessary");
    }

    private int approxPrecision() {
        int i = this.precision;
        if (i > 0) {
            return i;
        }
        return ((int) (((double) (this.bitLength - 1)) * LOG10_2)) + 1;
    }

    private static int safeLongToInt(long longValue) {
        if (longValue >= -2147483648L && longValue <= 2147483647L) {
            return (int) longValue;
        }
        throw new ArithmeticException("Out of int range: " + longValue);
    }

    private static BigDecimal zeroScaledBy(long longScale) {
        if (longScale == ((long) ((int) longScale))) {
            return valueOf(0, (int) longScale);
        }
        if (longScale >= 0) {
            return new BigDecimal(0, Integer.MAX_VALUE);
        }
        return new BigDecimal(0, Integer.MIN_VALUE);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.bitLength = this.intVal.bitLength();
        if (this.bitLength < 64) {
            this.smallValue = this.intVal.longValue();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        getUnscaledValue();
        out.defaultWriteObject();
    }

    private BigInteger getUnscaledValue() {
        if (this.intVal == null) {
            this.intVal = BigInteger.valueOf(this.smallValue);
        }
        return this.intVal;
    }

    private void setUnscaledValue(BigInteger unscaledValue) {
        this.intVal = unscaledValue;
        this.bitLength = unscaledValue.bitLength();
        if (this.bitLength < 64) {
            this.smallValue = unscaledValue.longValue();
        }
    }

    private static int bitLength(long smallValue2) {
        if (smallValue2 < 0) {
            smallValue2 = ~smallValue2;
        }
        return 64 - Long.numberOfLeadingZeros(smallValue2);
    }

    private static int bitLength(int smallValue2) {
        if (smallValue2 < 0) {
            smallValue2 = ~smallValue2;
        }
        return 32 - Integer.numberOfLeadingZeros(smallValue2);
    }
}
