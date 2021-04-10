package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MultiplierProducer;
import android.icu.impl.number.RoundingUtils;
import android.icu.util.Currency;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public abstract class Precision implements Cloneable {
    static final FracSigRounderImpl COMPACT_STRATEGY = new FracSigRounderImpl(0, 0, 2, -1);
    static final FractionRounderImpl DEFAULT_MAX_FRAC_6 = new FractionRounderImpl(0, 6);
    static final FractionRounderImpl FIXED_FRAC_0 = new FractionRounderImpl(0, 0);
    static final FractionRounderImpl FIXED_FRAC_2 = new FractionRounderImpl(2, 2);
    static final SignificantRounderImpl FIXED_SIG_2 = new SignificantRounderImpl(2, 2);
    static final SignificantRounderImpl FIXED_SIG_3 = new SignificantRounderImpl(3, 3);
    static final CurrencyRounderImpl MONETARY_CASH = new CurrencyRounderImpl(Currency.CurrencyUsage.CASH);
    static final CurrencyRounderImpl MONETARY_STANDARD = new CurrencyRounderImpl(Currency.CurrencyUsage.STANDARD);
    static final IncrementRounderImpl NICKEL = new IncrementRounderImpl(BigDecimal.valueOf(0.05d));
    static final InfiniteRounderImpl NONE = new InfiniteRounderImpl();
    static final PassThroughRounderImpl PASS_THROUGH = new PassThroughRounderImpl();
    static final SignificantRounderImpl RANGE_SIG_2_3 = new SignificantRounderImpl(2, 3);
    MathContext mathContext = RoundingUtils.DEFAULT_MATH_CONTEXT_UNLIMITED;

    static class PassThroughRounderImpl extends Precision {
        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
        }
    }

    /* access modifiers changed from: private */
    public static int getDisplayMagnitudeFraction(int i) {
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        return -i;
    }

    /* access modifiers changed from: private */
    public static int getRoundingMagnitudeFraction(int i) {
        if (i == -1) {
            return Integer.MIN_VALUE;
        }
        return -i;
    }

    public abstract void apply(DecimalQuantity decimalQuantity);

    Precision() {
    }

    public Precision withMode(RoundingMode roundingMode) {
        return withMode(RoundingUtils.mathContextUnlimited(roundingMode));
    }

    public Precision withMode(MathContext mathContext2) {
        if (this.mathContext.equals(mathContext2)) {
            return this;
        }
        Precision precision = (Precision) clone();
        precision.mathContext = mathContext2;
        return precision;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    static Precision constructInfinite() {
        return NONE;
    }

    static FractionPrecision constructFraction(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return FIXED_FRAC_0;
        }
        if (i == 2 && i2 == 2) {
            return FIXED_FRAC_2;
        }
        if (i == 0 && i2 == 6) {
            return DEFAULT_MAX_FRAC_6;
        }
        return new FractionRounderImpl(i, i2);
    }

    static Precision constructSignificant(int i, int i2) {
        if (i == 2 && i2 == 2) {
            return FIXED_SIG_2;
        }
        if (i == 3 && i2 == 3) {
            return FIXED_SIG_3;
        }
        if (i == 2 && i2 == 3) {
            return RANGE_SIG_2_3;
        }
        return new SignificantRounderImpl(i, i2);
    }

    static Precision constructIncrement(BigDecimal bigDecimal) {
        if (bigDecimal.equals(NICKEL.increment)) {
            return NICKEL;
        }
        return new IncrementRounderImpl(bigDecimal);
    }

    static CurrencyPrecision constructCurrency(Currency.CurrencyUsage currencyUsage) {
        if (currencyUsage == Currency.CurrencyUsage.STANDARD) {
            return MONETARY_STANDARD;
        }
        if (currencyUsage == Currency.CurrencyUsage.CASH) {
            return MONETARY_CASH;
        }
        throw new AssertionError();
    }

    static Precision constructFromCurrency(CurrencyPrecision currencyPrecision, Currency currency) {
        CurrencyRounderImpl currencyRounderImpl = (CurrencyRounderImpl) currencyPrecision;
        double roundingIncrement = currency.getRoundingIncrement(currencyRounderImpl.usage);
        if (roundingIncrement != 0.0d) {
            return constructIncrement(BigDecimal.valueOf(roundingIncrement));
        }
        int defaultFractionDigits = currency.getDefaultFractionDigits(currencyRounderImpl.usage);
        return constructFraction(defaultFractionDigits, defaultFractionDigits);
    }

    static Precision constructPassThrough() {
        return PASS_THROUGH;
    }

    /* access modifiers changed from: package-private */
    public Precision withLocaleData(Currency currency) {
        return this instanceof CurrencyPrecision ? ((CurrencyPrecision) this).withCurrency(currency) : this;
    }

    /* access modifiers changed from: package-private */
    public int chooseMultiplierAndApply(DecimalQuantity decimalQuantity, MultiplierProducer multiplierProducer) {
        int multiplier;
        int magnitude = decimalQuantity.getMagnitude();
        int multiplier2 = multiplierProducer.getMultiplier(magnitude);
        decimalQuantity.adjustMagnitude(multiplier2);
        apply(decimalQuantity);
        if (decimalQuantity.isZero() || decimalQuantity.getMagnitude() == magnitude + multiplier2 || multiplier2 == (multiplier = multiplierProducer.getMultiplier(magnitude + 1))) {
            return multiplier2;
        }
        decimalQuantity.adjustMagnitude(multiplier - multiplier2);
        apply(decimalQuantity);
        return multiplier;
    }

    static class InfiniteRounderImpl extends Precision {
        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
            decimalQuantity.roundToInfinity();
            decimalQuantity.setFractionLength(0, Integer.MAX_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public static class FractionRounderImpl extends FractionPrecision {
        final int maxFrac;
        final int minFrac;

        public FractionRounderImpl(int i, int i2) {
            this.minFrac = i;
            this.maxFrac = i2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
            decimalQuantity.roundToMagnitude(Precision.getRoundingMagnitudeFraction(this.maxFrac), this.mathContext);
            decimalQuantity.setFractionLength(Math.max(0, -Precision.getDisplayMagnitudeFraction(this.minFrac)), Integer.MAX_VALUE);
        }
    }

    static class SignificantRounderImpl extends Precision {
        final int maxSig;
        final int minSig;

        public SignificantRounderImpl(int i, int i2) {
            this.minSig = i;
            this.maxSig = i2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
            decimalQuantity.roundToMagnitude(Precision.getRoundingMagnitudeSignificant(decimalQuantity, this.maxSig), this.mathContext);
            decimalQuantity.setFractionLength(Math.max(0, -Precision.getDisplayMagnitudeSignificant(decimalQuantity, this.minSig)), Integer.MAX_VALUE);
            if (decimalQuantity.isZero() && this.minSig > 0) {
                decimalQuantity.setIntegerLength(1, Integer.MAX_VALUE);
            }
        }

        public void apply(DecimalQuantity decimalQuantity, int i) {
            decimalQuantity.setFractionLength(this.minSig - i, Integer.MAX_VALUE);
        }
    }

    static class FracSigRounderImpl extends Precision {
        final int maxFrac;
        final int maxSig;
        final int minFrac;
        final int minSig;

        public FracSigRounderImpl(int i, int i2, int i3, int i4) {
            this.minFrac = i;
            this.maxFrac = i2;
            this.minSig = i3;
            this.maxSig = i4;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
            int i;
            int displayMagnitudeFraction = Precision.getDisplayMagnitudeFraction(this.minFrac);
            int roundingMagnitudeFraction = Precision.getRoundingMagnitudeFraction(this.maxFrac);
            int i2 = this.minSig;
            if (i2 == -1) {
                i = Math.max(roundingMagnitudeFraction, Precision.getRoundingMagnitudeSignificant(decimalQuantity, this.maxSig));
            } else {
                i = Math.min(roundingMagnitudeFraction, Precision.getDisplayMagnitudeSignificant(decimalQuantity, i2));
            }
            decimalQuantity.roundToMagnitude(i, this.mathContext);
            decimalQuantity.setFractionLength(Math.max(0, -displayMagnitudeFraction), Integer.MAX_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public static class IncrementRounderImpl extends Precision {
        final BigDecimal increment;

        public IncrementRounderImpl(BigDecimal bigDecimal) {
            this.increment = bigDecimal;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
            decimalQuantity.roundToIncrement(this.increment, this.mathContext);
            decimalQuantity.setFractionLength(this.increment.scale(), this.increment.scale());
        }
    }

    static class CurrencyRounderImpl extends CurrencyPrecision {
        final Currency.CurrencyUsage usage;

        public CurrencyRounderImpl(Currency.CurrencyUsage currencyUsage) {
            this.usage = currencyUsage;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity decimalQuantity) {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public static int getRoundingMagnitudeSignificant(DecimalQuantity decimalQuantity, int i) {
        if (i == -1) {
            return Integer.MIN_VALUE;
        }
        return ((decimalQuantity.isZero() ? 0 : decimalQuantity.getMagnitude()) - i) + 1;
    }

    /* access modifiers changed from: private */
    public static int getDisplayMagnitudeSignificant(DecimalQuantity decimalQuantity, int i) {
        return ((decimalQuantity.isZero() ? 0 : decimalQuantity.getMagnitude()) - i) + 1;
    }
}
