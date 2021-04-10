package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.RoundingUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Scale {
    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100L);
    private static final BigDecimal BIG_DECIMAL_1000 = BigDecimal.valueOf(1000L);
    private static final Scale DEFAULT = new Scale(0, null);
    private static final Scale HUNDRED = new Scale(2, null);
    private static final Scale THOUSAND = new Scale(3, null);
    final BigDecimal arbitrary;
    final int magnitude;
    final MathContext mc;
    final BigDecimal reciprocal;

    private Scale(int magnitude2, BigDecimal arbitrary2) {
        this(magnitude2, arbitrary2, RoundingUtils.DEFAULT_MATH_CONTEXT_34_DIGITS);
    }

    private Scale(int magnitude2, BigDecimal arbitrary2, MathContext mc2) {
        BigDecimal bigDecimal;
        if (arbitrary2 != null) {
            if (arbitrary2.compareTo(BigDecimal.ZERO) == 0) {
                bigDecimal = BigDecimal.ZERO;
            } else {
                bigDecimal = arbitrary2.stripTrailingZeros();
            }
            arbitrary2 = bigDecimal;
            if (arbitrary2.precision() == 1 && arbitrary2.unscaledValue().equals(BigInteger.ONE)) {
                magnitude2 -= arbitrary2.scale();
                arbitrary2 = null;
            }
        }
        this.magnitude = magnitude2;
        this.arbitrary = arbitrary2;
        this.mc = mc2;
        if (arbitrary2 == null || BigDecimal.ZERO.compareTo(arbitrary2) == 0) {
            this.reciprocal = null;
        } else {
            this.reciprocal = BigDecimal.ONE.divide(arbitrary2, mc2);
        }
    }

    public static Scale none() {
        return DEFAULT;
    }

    public static Scale powerOfTen(int power) {
        if (power == 0) {
            return DEFAULT;
        }
        if (power == 2) {
            return HUNDRED;
        }
        if (power == 3) {
            return THOUSAND;
        }
        return new Scale(power, null);
    }

    public static Scale byBigDecimal(BigDecimal multiplicand) {
        if (multiplicand.compareTo(BigDecimal.ONE) == 0) {
            return DEFAULT;
        }
        if (multiplicand.compareTo(BIG_DECIMAL_100) == 0) {
            return HUNDRED;
        }
        if (multiplicand.compareTo(BIG_DECIMAL_1000) == 0) {
            return THOUSAND;
        }
        return new Scale(0, multiplicand);
    }

    public static Scale byDouble(double multiplicand) {
        if (multiplicand == 1.0d) {
            return DEFAULT;
        }
        if (multiplicand == 100.0d) {
            return HUNDRED;
        }
        if (multiplicand == 1000.0d) {
            return THOUSAND;
        }
        return new Scale(0, BigDecimal.valueOf(multiplicand));
    }

    public static Scale byDoubleAndPowerOfTen(double multiplicand, int power) {
        return new Scale(power, BigDecimal.valueOf(multiplicand));
    }

    /* access modifiers changed from: package-private */
    public boolean isValid() {
        return (this.magnitude == 0 && this.arbitrary == null) ? false : true;
    }

    @Deprecated
    public Scale withMathContext(MathContext mc2) {
        if (this.mc.equals(mc2)) {
            return this;
        }
        return new Scale(this.magnitude, this.arbitrary, mc2);
    }

    @Deprecated
    public void applyTo(DecimalQuantity quantity) {
        quantity.adjustMagnitude(this.magnitude);
        BigDecimal bigDecimal = this.arbitrary;
        if (bigDecimal != null) {
            quantity.multiplyBy(bigDecimal);
        }
    }

    @Deprecated
    public void applyReciprocalTo(DecimalQuantity quantity) {
        quantity.adjustMagnitude(-this.magnitude);
        BigDecimal bigDecimal = this.reciprocal;
        if (bigDecimal != null) {
            quantity.multiplyBy(bigDecimal);
            quantity.roundToMagnitude(quantity.getMagnitude() - this.mc.getPrecision(), this.mc);
        }
    }
}
