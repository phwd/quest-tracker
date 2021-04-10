package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.impl.Utility;
import android.icu.text.PluralRules;
import android.icu.text.UFieldPosition;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.FieldPosition;
import sun.misc.DoubleConsts;

public abstract class DecimalQuantity_AbstractBCD implements DecimalQuantity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final double[] DOUBLE_MULTIPLIERS = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d};
    protected static final int INFINITY_FLAG = 2;
    static final byte[] INT64_BCD = {9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 8};
    protected static final int NAN_FLAG = 4;
    protected static final int NEGATIVE_FLAG = 1;
    private static final int SECTION_LOWER_EDGE = -1;
    private static final int SECTION_UPPER_EDGE = -2;
    @Deprecated
    public boolean explicitExactDouble = false;
    protected byte flags;
    protected boolean isApproximate;
    protected int lOptPos = Integer.MAX_VALUE;
    protected int lReqPos = 0;
    protected int origDelta;
    protected double origDouble;
    protected int precision;
    protected int rOptPos = Integer.MIN_VALUE;
    protected int rReqPos = 0;
    protected int scale;

    /* access modifiers changed from: protected */
    public abstract BigDecimal bcdToBigDecimal();

    /* access modifiers changed from: protected */
    public abstract void compact();

    /* access modifiers changed from: protected */
    public abstract void copyBcdFrom(DecimalQuantity decimalQuantity);

    /* access modifiers changed from: protected */
    public abstract byte getDigitPos(int i);

    /* access modifiers changed from: protected */
    public abstract void readBigIntegerToBcd(BigInteger bigInteger);

    /* access modifiers changed from: protected */
    public abstract void readIntToBcd(int i);

    /* access modifiers changed from: protected */
    public abstract void readLongToBcd(long j);

    /* access modifiers changed from: protected */
    public abstract void setBcdToZero();

    /* access modifiers changed from: protected */
    public abstract void setDigitPos(int i, byte b);

    /* access modifiers changed from: protected */
    public abstract void shiftLeft(int i);

    /* access modifiers changed from: protected */
    public abstract void shiftRight(int i);

    @Override // android.icu.impl.number.DecimalQuantity
    public void copyFrom(DecimalQuantity _other) {
        copyBcdFrom(_other);
        DecimalQuantity_AbstractBCD other = (DecimalQuantity_AbstractBCD) _other;
        this.lOptPos = other.lOptPos;
        this.lReqPos = other.lReqPos;
        this.rReqPos = other.rReqPos;
        this.rOptPos = other.rOptPos;
        this.scale = other.scale;
        this.precision = other.precision;
        this.flags = other.flags;
        this.origDouble = other.origDouble;
        this.origDelta = other.origDelta;
        this.isApproximate = other.isApproximate;
    }

    public DecimalQuantity_AbstractBCD clear() {
        this.lOptPos = Integer.MAX_VALUE;
        this.lReqPos = 0;
        this.rReqPos = 0;
        this.rOptPos = Integer.MIN_VALUE;
        this.flags = 0;
        setBcdToZero();
        return this;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void setIntegerLength(int minInt, int maxInt) {
        if (minInt < this.lReqPos) {
            minInt = this.lReqPos;
        }
        this.lOptPos = maxInt;
        this.lReqPos = minInt;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void setFractionLength(int minFrac, int maxFrac) {
        this.rReqPos = -minFrac;
        this.rOptPos = -maxFrac;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public long getPositionFingerprint() {
        return (((0 ^ ((long) this.lOptPos)) ^ ((long) (this.lReqPos << 16))) ^ (((long) this.rReqPos) << 32)) ^ (((long) this.rOptPos) << 48);
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void roundToIncrement(BigDecimal roundingIncrement, MathContext mathContext) {
        BigDecimal temp = toBigDecimal().divide(roundingIncrement, 0, mathContext.getRoundingMode()).multiply(roundingIncrement).round(mathContext);
        if (temp.signum() == 0) {
            setBcdToZero();
        } else {
            setToBigDecimal(temp);
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void multiplyBy(BigDecimal multiplicand) {
        if (!isInfinite() && !isZero() && !isNaN()) {
            setToBigDecimal(toBigDecimal().multiply(multiplicand));
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void negate() {
        this.flags = (byte) (this.flags ^ 1);
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int getMagnitude() throws ArithmeticException {
        int i = this.precision;
        if (i != 0) {
            return (this.scale + i) - 1;
        }
        throw new ArithmeticException("Magnitude is not well-defined for zero");
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void adjustMagnitude(int delta) {
        if (this.precision != 0) {
            this.scale = Utility.addExact(this.scale, delta);
            this.origDelta = Utility.addExact(this.origDelta, delta);
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public StandardPlural getStandardPlural(PluralRules rules) {
        if (rules == null) {
            return StandardPlural.OTHER;
        }
        return StandardPlural.orOtherFromString(rules.select(this));
    }

    @Override // android.icu.text.PluralRules.IFixedDecimal
    public double getPluralOperand(PluralRules.Operand operand) {
        int i = AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand[operand.ordinal()];
        if (i == 1) {
            return (double) (isNegative() ? -toLong(true) : toLong(true));
        } else if (i == 2) {
            return (double) toFractionLong(true);
        } else {
            if (i == 3) {
                return (double) toFractionLong(false);
            }
            if (i == 4) {
                return (double) fractionCount();
            }
            if (i != 5) {
                return Math.abs(toDouble());
            }
            return (double) fractionCountWithoutTrailingZeros();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.number.DecimalQuantity_AbstractBCD$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$PluralRules$Operand = new int[PluralRules.Operand.values().length];

        static {
            try {
                $SwitchMap$android$icu$text$PluralRules$Operand[PluralRules.Operand.i.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$text$PluralRules$Operand[PluralRules.Operand.f.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$text$PluralRules$Operand[PluralRules.Operand.t.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$text$PluralRules$Operand[PluralRules.Operand.v.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$text$PluralRules$Operand[PluralRules.Operand.w.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void populateUFieldPosition(FieldPosition fp) {
        if (fp instanceof UFieldPosition) {
            ((UFieldPosition) fp).setFractionDigits((int) getPluralOperand(PluralRules.Operand.v), (long) getPluralOperand(PluralRules.Operand.f));
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int getUpperDisplayMagnitude() {
        int magnitude = this.scale + this.precision;
        int result = this.lReqPos;
        if (result <= magnitude && (result = this.lOptPos) >= magnitude) {
            result = magnitude;
        }
        return result - 1;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int getLowerDisplayMagnitude() {
        int magnitude = this.scale;
        int result = this.rReqPos;
        if (result < magnitude) {
            return result;
        }
        int result2 = this.rOptPos;
        return result2 > magnitude ? result2 : magnitude;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public byte getDigit(int magnitude) {
        return getDigitPos(magnitude - this.scale);
    }

    private int fractionCount() {
        return -getLowerDisplayMagnitude();
    }

    private int fractionCountWithoutTrailingZeros() {
        return Math.max(-this.scale, 0);
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public boolean isNegative() {
        return (this.flags & 1) != 0;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int signum() {
        if (isNegative()) {
            return -1;
        }
        return isZero() ? 0 : 1;
    }

    @Override // android.icu.text.PluralRules.IFixedDecimal, android.icu.impl.number.DecimalQuantity
    public boolean isInfinite() {
        return (this.flags & 2) != 0;
    }

    @Override // android.icu.text.PluralRules.IFixedDecimal, android.icu.impl.number.DecimalQuantity
    public boolean isNaN() {
        return (this.flags & 4) != 0;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public boolean isZero() {
        return this.precision == 0;
    }

    public void setToInt(int n) {
        setBcdToZero();
        this.flags = 0;
        if (n < 0) {
            this.flags = (byte) (this.flags | 1);
            n = -n;
        }
        if (n != 0) {
            _setToInt(n);
            compact();
        }
    }

    private void _setToInt(int n) {
        if (n == Integer.MIN_VALUE) {
            readLongToBcd(-((long) n));
        } else {
            readIntToBcd(n);
        }
    }

    public void setToLong(long n) {
        setBcdToZero();
        this.flags = 0;
        if (n < 0) {
            this.flags = (byte) (this.flags | 1);
            n = -n;
        }
        if (n != 0) {
            _setToLong(n);
            compact();
        }
    }

    private void _setToLong(long n) {
        if (n == Long.MIN_VALUE) {
            readBigIntegerToBcd(BigInteger.valueOf(n).negate());
        } else if (n <= 2147483647L) {
            readIntToBcd((int) n);
        } else {
            readLongToBcd(n);
        }
    }

    public void setToBigInteger(BigInteger n) {
        setBcdToZero();
        this.flags = 0;
        if (n.signum() == -1) {
            this.flags = (byte) (this.flags | 1);
            n = n.negate();
        }
        if (n.signum() != 0) {
            _setToBigInteger(n);
            compact();
        }
    }

    private void _setToBigInteger(BigInteger n) {
        if (n.bitLength() < 32) {
            readIntToBcd(n.intValue());
        } else if (n.bitLength() < 64) {
            readLongToBcd(n.longValue());
        } else {
            readBigIntegerToBcd(n);
        }
    }

    public void setToDouble(double n) {
        setBcdToZero();
        this.flags = 0;
        if (Double.compare(n, 0.0d) < 0) {
            this.flags = (byte) (this.flags | 1);
            n = -n;
        }
        if (Double.isNaN(n)) {
            this.flags = (byte) (this.flags | 4);
        } else if (Double.isInfinite(n)) {
            this.flags = (byte) (this.flags | 2);
        } else if (n != 0.0d) {
            _setToDoubleFast(n);
            compact();
        }
    }

    private void _setToDoubleFast(double n) {
        double n2;
        this.isApproximate = true;
        this.origDouble = n;
        this.origDelta = 0;
        int exponent = ((int) ((DoubleConsts.EXP_BIT_MASK & Double.doubleToLongBits(n)) >> 52)) - 1023;
        if (exponent > 52 || ((double) ((long) n)) != n) {
            int fracLength = (int) (((double) (52 - exponent)) / 3.32192809489d);
            if (fracLength >= 0) {
                int i = fracLength;
                while (i >= 22) {
                    n *= 1.0E22d;
                    i -= 22;
                }
                n2 = n * DOUBLE_MULTIPLIERS[i];
            } else {
                int i2 = fracLength;
                while (i2 <= -22) {
                    n /= 1.0E22d;
                    i2 += 22;
                }
                n2 = n / DOUBLE_MULTIPLIERS[-i2];
            }
            long result = Math.round(n2);
            if (result != 0) {
                _setToLong(result);
                this.scale -= fracLength;
                return;
            }
            return;
        }
        _setToLong((long) n);
    }

    private void convertToAccurateDouble() {
        double n = this.origDouble;
        int delta = this.origDelta;
        setBcdToZero();
        String dstr = Double.toString(n);
        if (dstr.indexOf(69) != -1) {
            int expPos = dstr.indexOf(69);
            _setToLong(Long.parseLong(dstr.charAt(0) + dstr.substring(2, expPos)));
            this.scale = this.scale + (Integer.parseInt(dstr.substring(expPos + 1)) - (expPos + -1)) + 1;
        } else if (dstr.charAt(0) == '0') {
            _setToLong(Long.parseLong(dstr.substring(2)));
            this.scale += 2 - dstr.length();
        } else if (dstr.charAt(dstr.length() - 1) == '0') {
            _setToLong(Long.parseLong(dstr.substring(0, dstr.length() - 2)));
        } else {
            int decimalPos = dstr.indexOf(46);
            _setToLong(Long.parseLong(dstr.substring(0, decimalPos) + dstr.substring(decimalPos + 1)));
            this.scale = this.scale + (decimalPos - dstr.length()) + 1;
        }
        this.scale += delta;
        compact();
        this.explicitExactDouble = true;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void setToBigDecimal(BigDecimal n) {
        setBcdToZero();
        this.flags = 0;
        if (n.signum() == -1) {
            this.flags = (byte) (this.flags | 1);
            n = n.negate();
        }
        if (n.signum() != 0) {
            _setToBigDecimal(n);
            compact();
        }
    }

    private void _setToBigDecimal(BigDecimal n) {
        int fracLength = n.scale();
        _setToBigInteger(n.scaleByPowerOfTen(fracLength).toBigInteger());
        this.scale -= fracLength;
    }

    public long toLong(boolean truncateIfOverflow) {
        long result = 0;
        int upperMagnitude = Math.min(this.scale + this.precision, this.lOptPos) - 1;
        if (truncateIfOverflow) {
            upperMagnitude = Math.min(upperMagnitude, 17);
        }
        for (int magnitude = upperMagnitude; magnitude >= 0; magnitude--) {
            result = (10 * result) + ((long) getDigitPos(magnitude - this.scale));
        }
        if (isNegative()) {
            return -result;
        }
        return result;
    }

    public long toFractionLong(boolean includeTrailingZeros) {
        long result = 0;
        int lowerMagnitude = Math.max(this.scale, this.rOptPos);
        if (includeTrailingZeros) {
            lowerMagnitude = Math.min(lowerMagnitude, this.rReqPos);
        }
        for (int magnitude = -1; magnitude >= lowerMagnitude && ((double) result) <= 1.0E17d; magnitude--) {
            result = (10 * result) + ((long) getDigitPos(magnitude - this.scale));
        }
        if (!includeTrailingZeros) {
            while (result > 0 && result % 10 == 0) {
                result /= 10;
            }
        }
        return result;
    }

    public boolean fitsInLong() {
        if (isZero()) {
            return true;
        }
        if (this.scale < 0) {
            return false;
        }
        int magnitude = getMagnitude();
        if (magnitude < 18) {
            return true;
        }
        if (magnitude > 18) {
            return false;
        }
        for (int p = 0; p < this.precision; p++) {
            byte digit = getDigit(18 - p);
            byte[] bArr = INT64_BCD;
            if (digit < bArr[p]) {
                return true;
            }
            if (digit > bArr[p]) {
                return false;
            }
        }
        return isNegative();
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public double toDouble() {
        double result;
        if (isNaN()) {
            return Double.NaN;
        }
        if (isInfinite()) {
            return isNegative() ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        long tempLong = 0;
        int i = this.precision;
        int lostDigits = i - Math.min(i, 17);
        for (int shift = this.precision - 1; shift >= lostDigits; shift--) {
            tempLong = (10 * tempLong) + ((long) getDigitPos(shift));
        }
        double result2 = (double) tempLong;
        int _scale = this.scale + lostDigits;
        if (_scale >= 0) {
            int i2 = _scale;
            while (true) {
                if (i2 < 22) {
                    break;
                }
                result2 *= 1.0E22d;
                if (Double.isInfinite(result2)) {
                    i2 = 0;
                    break;
                }
                i2 -= 22;
            }
            result = result2 * DOUBLE_MULTIPLIERS[i2];
        } else {
            int i3 = _scale;
            while (true) {
                if (i3 > -22) {
                    break;
                }
                result2 /= 1.0E22d;
                if (result2 == 0.0d) {
                    i3 = 0;
                    break;
                }
                i3 += 22;
            }
            result = result2 / DOUBLE_MULTIPLIERS[-i3];
        }
        if (isNegative()) {
            return -result;
        }
        return result;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public BigDecimal toBigDecimal() {
        if (this.isApproximate) {
            convertToAccurateDouble();
        }
        return bcdToBigDecimal();
    }

    private static int safeSubtract(int a, int b) {
        int diff = a - b;
        if (b < 0 && diff < a) {
            return Integer.MAX_VALUE;
        }
        if (b <= 0 || diff <= a) {
            return diff;
        }
        return Integer.MIN_VALUE;
    }

    public void truncate() {
        int i = this.scale;
        if (i < 0) {
            shiftRight(-i);
            this.scale = 0;
            compact();
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void roundToMagnitude(int magnitude, MathContext mathContext) {
        int position = safeSubtract(magnitude, this.scale);
        int _mcPrecision = mathContext.getPrecision();
        if (magnitude == Integer.MAX_VALUE || (_mcPrecision > 0 && this.precision - position > _mcPrecision)) {
            position = this.precision - _mcPrecision;
        }
        if ((position > 0 || this.isApproximate) && this.precision != 0) {
            byte leadingDigit = getDigitPos(safeSubtract(position, 1));
            byte trailingDigit = getDigitPos(position);
            int section = 2;
            if (this.isApproximate) {
                int p = safeSubtract(position, 2);
                int minP = Math.max(0, this.precision - 14);
                if (leadingDigit == 0) {
                    section = -1;
                    while (true) {
                        if (p < minP) {
                            break;
                        } else if (getDigitPos(p) != 0) {
                            section = 1;
                            break;
                        } else {
                            p--;
                        }
                    }
                } else if (leadingDigit == 4) {
                    while (true) {
                        if (p < minP) {
                            break;
                        } else if (getDigitPos(p) != 9) {
                            section = 1;
                            break;
                        } else {
                            p--;
                        }
                    }
                } else if (leadingDigit == 5) {
                    while (true) {
                        if (p < minP) {
                            break;
                        } else if (getDigitPos(p) != 0) {
                            section = 3;
                            break;
                        } else {
                            p--;
                        }
                    }
                } else if (leadingDigit == 9) {
                    section = -2;
                    while (true) {
                        if (p < minP) {
                            break;
                        } else if (getDigitPos(p) != 9) {
                            section = 3;
                            break;
                        } else {
                            p--;
                        }
                    }
                } else {
                    section = leadingDigit < 5 ? 1 : 3;
                }
                boolean roundsAtMidpoint = RoundingUtils.roundsAtMidpoint(mathContext.getRoundingMode().ordinal());
                if (safeSubtract(position, 1) < this.precision - 14 || ((roundsAtMidpoint && section == 2) || (!roundsAtMidpoint && section < 0))) {
                    convertToAccurateDouble();
                    roundToMagnitude(magnitude, mathContext);
                    return;
                }
                this.isApproximate = false;
                this.origDouble = 0.0d;
                this.origDelta = 0;
                if (position > 0) {
                    if (section == -1) {
                        section = 1;
                    }
                    if (section == -2) {
                        section = 3;
                    }
                } else {
                    return;
                }
            } else if (leadingDigit < 5) {
                section = 1;
            } else if (leadingDigit > 5) {
                section = 3;
            } else {
                int p2 = safeSubtract(position, 2);
                while (true) {
                    if (p2 < 0) {
                        break;
                    } else if (getDigitPos(p2) != 0) {
                        section = 3;
                        break;
                    } else {
                        p2--;
                    }
                }
            }
            boolean roundDown = RoundingUtils.getRoundingDirection(trailingDigit % 2 == 0, isNegative(), section, mathContext.getRoundingMode().ordinal(), this);
            if (position >= this.precision) {
                setBcdToZero();
                this.scale = magnitude;
            } else {
                shiftRight(position);
            }
            if (!roundDown) {
                if (trailingDigit == 9) {
                    int bubblePos = 0;
                    while (getDigitPos(bubblePos) == 9) {
                        bubblePos++;
                    }
                    shiftRight(bubblePos);
                }
                setDigitPos(0, (byte) (getDigitPos(0) + 1));
                this.precision++;
            }
            compact();
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void roundToInfinity() {
        if (this.isApproximate) {
            convertToAccurateDouble();
        }
    }

    @Deprecated
    public void appendDigit(byte value, int leadingZeros, boolean appendAsInteger) {
        if (value != 0) {
            int i = this.scale;
            if (i > 0) {
                leadingZeros += i;
                if (appendAsInteger) {
                    this.scale = 0;
                }
            }
            shiftLeft(leadingZeros + 1);
            setDigitPos(0, value);
            if (appendAsInteger) {
                this.scale += leadingZeros + 1;
            }
        } else if (appendAsInteger && this.precision != 0) {
            this.scale += leadingZeros + 1;
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public String toPlainString() {
        StringBuilder sb = new StringBuilder();
        if (isNegative()) {
            sb.append('-');
        }
        if (this.precision == 0 || getMagnitude() < 0) {
            sb.append('0');
        }
        for (int m = getUpperDisplayMagnitude(); m >= getLowerDisplayMagnitude(); m--) {
            sb.append((char) (getDigit(m) + 48));
            if (m == 0) {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    public String toScientificString() {
        StringBuilder sb = new StringBuilder();
        toScientificString(sb);
        return sb.toString();
    }

    public void toScientificString(StringBuilder result) {
        if (isNegative()) {
            result.append('-');
        }
        int i = this.precision;
        if (i == 0) {
            result.append("0E+0");
            return;
        }
        int min = Math.min(i + this.scale, this.lOptPos);
        int i2 = this.scale;
        int upperPos = (min - i2) - 1;
        int lowerPos = Math.max(i2, this.rOptPos) - this.scale;
        result.append((char) (getDigitPos(upperPos) + 48));
        int p = upperPos - 1;
        if (p >= lowerPos) {
            result.append('.');
            while (p >= lowerPos) {
                result.append((char) (getDigitPos(p) + 48));
                p--;
            }
        }
        result.append('E');
        int _scale = this.scale + upperPos;
        if (_scale == Integer.MIN_VALUE) {
            result.append("-2147483648");
            return;
        }
        if (_scale < 0) {
            _scale *= -1;
            result.append('-');
        } else {
            result.append('+');
        }
        if (_scale == 0) {
            result.append('0');
        }
        int insertIndex = result.length();
        while (_scale > 0) {
            result.insert(insertIndex, (char) ((_scale % 10) + 48));
            _scale /= 10;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof DecimalQuantity_AbstractBCD)) {
            return false;
        }
        DecimalQuantity_AbstractBCD _other = (DecimalQuantity_AbstractBCD) other;
        if (!(this.scale == _other.scale && this.precision == _other.precision && this.flags == _other.flags && this.lOptPos == _other.lOptPos && this.lReqPos == _other.lReqPos && this.rReqPos == _other.rReqPos && this.rOptPos == _other.rOptPos && this.isApproximate == _other.isApproximate)) {
            return false;
        }
        if (this.precision == 0) {
            return true;
        }
        if (!this.isApproximate) {
            for (int m = getUpperDisplayMagnitude(); m >= getLowerDisplayMagnitude(); m--) {
                if (getDigit(m) != _other.getDigit(m)) {
                    return false;
                }
            }
            return true;
        } else if (this.origDouble == _other.origDouble && this.origDelta == _other.origDelta) {
            return true;
        } else {
            return false;
        }
    }
}
