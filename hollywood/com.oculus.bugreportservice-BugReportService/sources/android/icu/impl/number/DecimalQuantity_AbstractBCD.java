package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.impl.Utility;
import android.icu.text.PluralRules;
import android.icu.text.UFieldPosition;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.FieldPosition;

public abstract class DecimalQuantity_AbstractBCD implements DecimalQuantity {
    private static final double[] DOUBLE_MULTIPLIERS = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d};
    static final byte[] INT64_BCD = {9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 8};
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

    private static int safeSubtract(int i, int i2) {
        int i3 = i - i2;
        if (i2 < 0 && i3 < i) {
            return Integer.MAX_VALUE;
        }
        if (i2 <= 0 || i3 <= i) {
            return i3;
        }
        return Integer.MIN_VALUE;
    }

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

    public void copyFrom(DecimalQuantity decimalQuantity) {
        copyBcdFrom(decimalQuantity);
        DecimalQuantity_AbstractBCD decimalQuantity_AbstractBCD = (DecimalQuantity_AbstractBCD) decimalQuantity;
        this.lOptPos = decimalQuantity_AbstractBCD.lOptPos;
        this.lReqPos = decimalQuantity_AbstractBCD.lReqPos;
        this.rReqPos = decimalQuantity_AbstractBCD.rReqPos;
        this.rOptPos = decimalQuantity_AbstractBCD.rOptPos;
        this.scale = decimalQuantity_AbstractBCD.scale;
        this.precision = decimalQuantity_AbstractBCD.precision;
        this.flags = decimalQuantity_AbstractBCD.flags;
        this.origDouble = decimalQuantity_AbstractBCD.origDouble;
        this.origDelta = decimalQuantity_AbstractBCD.origDelta;
        this.isApproximate = decimalQuantity_AbstractBCD.isApproximate;
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
    public void setIntegerLength(int i, int i2) {
        int i3 = this.lReqPos;
        if (i < i3) {
            i = i3;
        }
        this.lOptPos = i2;
        this.lReqPos = i;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void setFractionLength(int i, int i2) {
        this.rReqPos = -i;
        this.rOptPos = -i2;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void roundToIncrement(BigDecimal bigDecimal, MathContext mathContext) {
        BigDecimal round = toBigDecimal().divide(bigDecimal, 0, mathContext.getRoundingMode()).multiply(bigDecimal).round(mathContext);
        if (round.signum() == 0) {
            setBcdToZero();
        } else {
            setToBigDecimal(round);
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void multiplyBy(BigDecimal bigDecimal) {
        if (!isInfinite() && !isZero() && !isNaN()) {
            setToBigDecimal(toBigDecimal().multiply(bigDecimal));
        }
    }

    public void negate() {
        this.flags = (byte) (this.flags ^ 1);
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int getMagnitude() {
        int i = this.precision;
        if (i != 0) {
            return (this.scale + i) - 1;
        }
        throw new ArithmeticException("Magnitude is not well-defined for zero");
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void adjustMagnitude(int i) {
        if (this.precision != 0) {
            this.scale = Utility.addExact(this.scale, i);
            this.origDelta = Utility.addExact(this.origDelta, i);
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public StandardPlural getStandardPlural(PluralRules pluralRules) {
        if (pluralRules == null) {
            return StandardPlural.OTHER;
        }
        return StandardPlural.orOtherFromString(pluralRules.select(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.number.DecimalQuantity_AbstractBCD$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$PluralRules$Operand = new int[PluralRules.Operand.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.icu.text.PluralRules$Operand[] r0 = android.icu.text.PluralRules.Operand.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand = r0
                int[] r0 = android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.text.PluralRules$Operand r1 = android.icu.text.PluralRules.Operand.i     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.text.PluralRules$Operand r1 = android.icu.text.PluralRules.Operand.f     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.text.PluralRules$Operand r1 = android.icu.text.PluralRules.Operand.t     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.icu.text.PluralRules$Operand r1 = android.icu.text.PluralRules.Operand.v     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.$SwitchMap$android$icu$text$PluralRules$Operand     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.icu.text.PluralRules$Operand r1 = android.icu.text.PluralRules.Operand.w     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.DecimalQuantity_AbstractBCD.AnonymousClass1.<clinit>():void");
        }
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

    @Override // android.icu.impl.number.DecimalQuantity
    public void populateUFieldPosition(FieldPosition fieldPosition) {
        if (fieldPosition instanceof UFieldPosition) {
            ((UFieldPosition) fieldPosition).setFractionDigits((int) getPluralOperand(PluralRules.Operand.v), (long) getPluralOperand(PluralRules.Operand.f));
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int getUpperDisplayMagnitude() {
        int i;
        int i2 = this.scale + this.precision;
        int i3 = this.lReqPos;
        if (i3 > i2) {
            i = i3;
        } else {
            i = this.lOptPos;
            if (i >= i2) {
                i = i2;
            }
        }
        return i - 1;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public int getLowerDisplayMagnitude() {
        int i = this.scale;
        int i2 = this.rReqPos;
        if (i2 < i) {
            return i2;
        }
        int i3 = this.rOptPos;
        return i3 > i ? i3 : i;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public byte getDigit(int i) {
        return getDigitPos(i - this.scale);
    }

    private int fractionCount() {
        return -getLowerDisplayMagnitude();
    }

    private int fractionCountWithoutTrailingZeros() {
        return Math.max(-this.scale, 0);
    }

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

    public void setToInt(int i) {
        setBcdToZero();
        this.flags = 0;
        if (i < 0) {
            this.flags = (byte) (this.flags | 1);
            i = -i;
        }
        if (i != 0) {
            _setToInt(i);
            compact();
        }
    }

    private void _setToInt(int i) {
        if (i == Integer.MIN_VALUE) {
            readLongToBcd(-((long) i));
        } else {
            readIntToBcd(i);
        }
    }

    public void setToLong(long j) {
        setBcdToZero();
        this.flags = 0;
        if (j < 0) {
            this.flags = (byte) (this.flags | 1);
            j = -j;
        }
        if (j != 0) {
            _setToLong(j);
            compact();
        }
    }

    private void _setToLong(long j) {
        if (j == Long.MIN_VALUE) {
            readBigIntegerToBcd(BigInteger.valueOf(j).negate());
        } else if (j <= 2147483647L) {
            readIntToBcd((int) j);
        } else {
            readLongToBcd(j);
        }
    }

    public void setToBigInteger(BigInteger bigInteger) {
        setBcdToZero();
        this.flags = 0;
        if (bigInteger.signum() == -1) {
            this.flags = (byte) (this.flags | 1);
            bigInteger = bigInteger.negate();
        }
        if (bigInteger.signum() != 0) {
            _setToBigInteger(bigInteger);
            compact();
        }
    }

    private void _setToBigInteger(BigInteger bigInteger) {
        if (bigInteger.bitLength() < 32) {
            readIntToBcd(bigInteger.intValue());
        } else if (bigInteger.bitLength() < 64) {
            readLongToBcd(bigInteger.longValue());
        } else {
            readBigIntegerToBcd(bigInteger);
        }
    }

    public void setToDouble(double d) {
        setBcdToZero();
        this.flags = 0;
        if (Double.compare(d, 0.0d) < 0) {
            this.flags = (byte) (this.flags | 1);
            d = -d;
        }
        if (Double.isNaN(d)) {
            this.flags = (byte) (this.flags | 4);
        } else if (Double.isInfinite(d)) {
            this.flags = (byte) (this.flags | 2);
        } else if (d != 0.0d) {
            _setToDoubleFast(d);
            compact();
        }
    }

    private void _setToDoubleFast(double d) {
        double d2;
        this.isApproximate = true;
        this.origDouble = d;
        this.origDelta = 0;
        int doubleToLongBits = ((int) ((Double.doubleToLongBits(d) & 9218868437227405312L) >> 52)) - 1023;
        if (doubleToLongBits <= 52) {
            long j = (long) d;
            if (((double) j) == d) {
                _setToLong(j);
                return;
            }
        }
        int i = (int) (((double) (52 - doubleToLongBits)) / 3.32192809489d);
        if (i >= 0) {
            double d3 = d;
            int i2 = i;
            while (i2 >= 22) {
                d3 *= 1.0E22d;
                i2 -= 22;
            }
            d2 = d3 * DOUBLE_MULTIPLIERS[i2];
        } else {
            double d4 = d;
            int i3 = i;
            while (i3 <= -22) {
                d4 /= 1.0E22d;
                i3 += 22;
            }
            d2 = d4 / DOUBLE_MULTIPLIERS[-i3];
        }
        long round = Math.round(d2);
        if (round != 0) {
            _setToLong(round);
            this.scale -= i;
        }
    }

    private void convertToAccurateDouble() {
        double d = this.origDouble;
        int i = this.origDelta;
        setBcdToZero();
        String d2 = Double.toString(d);
        if (d2.indexOf(69) != -1) {
            int indexOf = d2.indexOf(69);
            _setToLong(Long.parseLong(d2.charAt(0) + d2.substring(2, indexOf)));
            this.scale = this.scale + (Integer.parseInt(d2.substring(indexOf + 1)) - (indexOf - 1)) + 1;
        } else if (d2.charAt(0) == '0') {
            _setToLong(Long.parseLong(d2.substring(2)));
            this.scale += 2 - d2.length();
        } else if (d2.charAt(d2.length() - 1) == '0') {
            _setToLong(Long.parseLong(d2.substring(0, d2.length() - 2)));
        } else {
            int indexOf2 = d2.indexOf(46);
            _setToLong(Long.parseLong(d2.substring(0, indexOf2) + d2.substring(indexOf2 + 1)));
            this.scale = this.scale + (indexOf2 - d2.length()) + 1;
        }
        this.scale += i;
        compact();
        this.explicitExactDouble = true;
    }

    public void setToBigDecimal(BigDecimal bigDecimal) {
        setBcdToZero();
        this.flags = 0;
        if (bigDecimal.signum() == -1) {
            this.flags = (byte) (this.flags | 1);
            bigDecimal = bigDecimal.negate();
        }
        if (bigDecimal.signum() != 0) {
            _setToBigDecimal(bigDecimal);
            compact();
        }
    }

    private void _setToBigDecimal(BigDecimal bigDecimal) {
        int scale2 = bigDecimal.scale();
        _setToBigInteger(bigDecimal.scaleByPowerOfTen(scale2).toBigInteger());
        this.scale -= scale2;
    }

    public long toLong(boolean z) {
        long j = 0;
        int min = Math.min(this.scale + this.precision, this.lOptPos) - 1;
        if (z) {
            min = Math.min(min, 17);
        }
        while (min >= 0) {
            j = (j * 10) + ((long) getDigitPos(min - this.scale));
            min--;
        }
        return isNegative() ? -j : j;
    }

    public long toFractionLong(boolean z) {
        int max = Math.max(this.scale, this.rOptPos);
        if (z) {
            max = Math.min(max, this.rReqPos);
        }
        long j = 0;
        for (int i = -1; i >= max && ((double) j) <= 1.0E17d; i--) {
            j = (j * 10) + ((long) getDigitPos(i - this.scale));
        }
        if (!z) {
            while (j > 0 && j % 10 == 0) {
                j /= 10;
            }
        }
        return j;
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
        for (int i = 0; i < this.precision; i++) {
            byte digit = getDigit(18 - i);
            byte[] bArr = INT64_BCD;
            if (digit < bArr[i]) {
                return true;
            }
            if (digit > bArr[i]) {
                return false;
            }
        }
        return isNegative();
    }

    public double toDouble() {
        double d;
        if (isNaN()) {
            return Double.NaN;
        }
        if (isInfinite()) {
            return isNegative() ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        long j = 0;
        int i = this.precision;
        int min = i - Math.min(i, 17);
        for (int i2 = this.precision - 1; i2 >= min; i2--) {
            j = (j * 10) + ((long) getDigitPos(i2));
        }
        double d2 = (double) j;
        int i3 = this.scale + min;
        int i4 = 0;
        if (i3 >= 0) {
            while (true) {
                if (i3 < 22) {
                    i4 = i3;
                    break;
                }
                d2 *= 1.0E22d;
                if (Double.isInfinite(d2)) {
                    break;
                }
                i3 -= 22;
            }
            d = d2 * DOUBLE_MULTIPLIERS[i4];
        } else {
            while (true) {
                if (i3 > -22) {
                    i4 = i3;
                    break;
                }
                d2 /= 1.0E22d;
                if (d2 == 0.0d) {
                    break;
                }
                i3 += 22;
            }
            d = d2 / DOUBLE_MULTIPLIERS[-i4];
        }
        return isNegative() ? -d : d;
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public BigDecimal toBigDecimal() {
        if (this.isApproximate) {
            convertToAccurateDouble();
        }
        return bcdToBigDecimal();
    }

    public void truncate() {
        int i = this.scale;
        if (i < 0) {
            shiftRight(-i);
            this.scale = 0;
            compact();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ae, code lost:
        if (r4 < 5) goto L_0x0088;
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c7 A[ADDED_TO_REGION] */
    @Override // android.icu.impl.number.DecimalQuantity
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void roundToMagnitude(int r17, java.math.MathContext r18) {
        /*
        // Method dump skipped, instructions count: 304
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.DecimalQuantity_AbstractBCD.roundToMagnitude(int, java.math.MathContext):void");
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public void roundToInfinity() {
        if (this.isApproximate) {
            convertToAccurateDouble();
        }
    }

    public void appendDigit(byte b, int i, boolean z) {
        if (b != 0) {
            int i2 = this.scale;
            if (i2 > 0) {
                i += i2;
                if (z) {
                    this.scale = 0;
                }
            }
            int i3 = i + 1;
            shiftLeft(i3);
            setDigitPos(0, b);
            if (z) {
                this.scale += i3;
            }
        } else if (z && this.precision != 0) {
            this.scale += i + 1;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DecimalQuantity_AbstractBCD)) {
            return false;
        }
        DecimalQuantity_AbstractBCD decimalQuantity_AbstractBCD = (DecimalQuantity_AbstractBCD) obj;
        if (!(this.scale == decimalQuantity_AbstractBCD.scale && this.precision == decimalQuantity_AbstractBCD.precision && this.flags == decimalQuantity_AbstractBCD.flags && this.lOptPos == decimalQuantity_AbstractBCD.lOptPos && this.lReqPos == decimalQuantity_AbstractBCD.lReqPos && this.rReqPos == decimalQuantity_AbstractBCD.rReqPos && this.rOptPos == decimalQuantity_AbstractBCD.rOptPos && this.isApproximate == decimalQuantity_AbstractBCD.isApproximate)) {
            return false;
        }
        if (this.precision == 0) {
            return true;
        }
        if (this.isApproximate) {
            return this.origDouble == decimalQuantity_AbstractBCD.origDouble && this.origDelta == decimalQuantity_AbstractBCD.origDelta;
        }
        for (int upperDisplayMagnitude = getUpperDisplayMagnitude(); upperDisplayMagnitude >= getLowerDisplayMagnitude(); upperDisplayMagnitude--) {
            if (getDigit(upperDisplayMagnitude) != decimalQuantity_AbstractBCD.getDigit(upperDisplayMagnitude)) {
                return false;
            }
        }
        return true;
    }
}
