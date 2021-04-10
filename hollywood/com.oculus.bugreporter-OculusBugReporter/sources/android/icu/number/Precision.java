package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MultiplierProducer;
import android.icu.impl.number.RoundingUtils;
import android.icu.util.Currency;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public abstract class Precision implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
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

    @Deprecated
    public abstract void apply(DecimalQuantity decimalQuantity);

    Precision() {
    }

    public static Precision unlimited() {
        return constructInfinite();
    }

    public static FractionPrecision integer() {
        return constructFraction(0, 0);
    }

    public static FractionPrecision fixedFraction(int minMaxFractionPlaces) {
        if (minMaxFractionPlaces >= 0 && minMaxFractionPlaces <= 999) {
            return constructFraction(minMaxFractionPlaces, minMaxFractionPlaces);
        }
        throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
    }

    public static FractionPrecision minFraction(int minFractionPlaces) {
        if (minFractionPlaces >= 0 && minFractionPlaces <= 999) {
            return constructFraction(minFractionPlaces, -1);
        }
        throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
    }

    public static FractionPrecision maxFraction(int maxFractionPlaces) {
        if (maxFractionPlaces >= 0 && maxFractionPlaces <= 999) {
            return constructFraction(0, maxFractionPlaces);
        }
        throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
    }

    public static FractionPrecision minMaxFraction(int minFractionPlaces, int maxFractionPlaces) {
        if (minFractionPlaces >= 0 && maxFractionPlaces <= 999 && minFractionPlaces <= maxFractionPlaces) {
            return constructFraction(minFractionPlaces, maxFractionPlaces);
        }
        throw new IllegalArgumentException("Fraction length must be between 0 and 999 (inclusive)");
    }

    public static Precision fixedSignificantDigits(int minMaxSignificantDigits) {
        if (minMaxSignificantDigits >= 1 && minMaxSignificantDigits <= 999) {
            return constructSignificant(minMaxSignificantDigits, minMaxSignificantDigits);
        }
        throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
    }

    public static Precision minSignificantDigits(int minSignificantDigits) {
        if (minSignificantDigits >= 1 && minSignificantDigits <= 999) {
            return constructSignificant(minSignificantDigits, -1);
        }
        throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
    }

    public static Precision maxSignificantDigits(int maxSignificantDigits) {
        if (maxSignificantDigits >= 1 && maxSignificantDigits <= 999) {
            return constructSignificant(1, maxSignificantDigits);
        }
        throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
    }

    public static Precision minMaxSignificantDigits(int minSignificantDigits, int maxSignificantDigits) {
        if (minSignificantDigits >= 1 && maxSignificantDigits <= 999 && minSignificantDigits <= maxSignificantDigits) {
            return constructSignificant(minSignificantDigits, maxSignificantDigits);
        }
        throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
    }

    @Deprecated
    public static Precision fixedDigits(int a) {
        return fixedSignificantDigits(a);
    }

    @Deprecated
    public static Precision minDigits(int a) {
        return minSignificantDigits(a);
    }

    @Deprecated
    public static Precision maxDigits(int a) {
        return maxSignificantDigits(a);
    }

    @Deprecated
    public static Precision minMaxDigits(int a, int b) {
        return minMaxSignificantDigits(a, b);
    }

    public static Precision increment(BigDecimal roundingIncrement) {
        if (roundingIncrement != null && roundingIncrement.compareTo(BigDecimal.ZERO) > 0) {
            return constructIncrement(roundingIncrement);
        }
        throw new IllegalArgumentException("Rounding increment must be positive and non-null");
    }

    public static CurrencyPrecision currency(Currency.CurrencyUsage currencyUsage) {
        if (currencyUsage != null) {
            return constructCurrency(currencyUsage);
        }
        throw new IllegalArgumentException("CurrencyUsage must be non-null");
    }

    @Deprecated
    public Precision withMode(RoundingMode roundingMode) {
        return withMode(RoundingUtils.mathContextUnlimited(roundingMode));
    }

    @Deprecated
    public Precision withMode(MathContext mathContext2) {
        if (this.mathContext.equals(mathContext2)) {
            return this;
        }
        Precision other = (Precision) clone();
        other.mathContext = mathContext2;
        return other;
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

    static FractionPrecision constructFraction(int minFrac, int maxFrac) {
        if (minFrac == 0 && maxFrac == 0) {
            return FIXED_FRAC_0;
        }
        if (minFrac == 2 && maxFrac == 2) {
            return FIXED_FRAC_2;
        }
        if (minFrac == 0 && maxFrac == 6) {
            return DEFAULT_MAX_FRAC_6;
        }
        return new FractionRounderImpl(minFrac, maxFrac);
    }

    static Precision constructSignificant(int minSig, int maxSig) {
        if (minSig == 2 && maxSig == 2) {
            return FIXED_SIG_2;
        }
        if (minSig == 3 && maxSig == 3) {
            return FIXED_SIG_3;
        }
        if (minSig == 2 && maxSig == 3) {
            return RANGE_SIG_2_3;
        }
        return new SignificantRounderImpl(minSig, maxSig);
    }

    static Precision constructFractionSignificant(FractionPrecision base_, int minSig, int maxSig) {
        FractionRounderImpl base = (FractionRounderImpl) base_;
        if (base.minFrac == 0 && base.maxFrac == 0 && minSig == 2) {
            return COMPACT_STRATEGY;
        }
        return new FracSigRounderImpl(base.minFrac, base.maxFrac, minSig, maxSig);
    }

    static Precision constructIncrement(BigDecimal increment) {
        if (increment.equals(NICKEL.increment)) {
            return NICKEL;
        }
        return new IncrementRounderImpl(increment);
    }

    static CurrencyPrecision constructCurrency(Currency.CurrencyUsage usage) {
        if (usage == Currency.CurrencyUsage.STANDARD) {
            return MONETARY_STANDARD;
        }
        if (usage == Currency.CurrencyUsage.CASH) {
            return MONETARY_CASH;
        }
        throw new AssertionError();
    }

    static Precision constructFromCurrency(CurrencyPrecision base_, Currency currency) {
        CurrencyRounderImpl base = (CurrencyRounderImpl) base_;
        double incrementDouble = currency.getRoundingIncrement(base.usage);
        if (incrementDouble != 0.0d) {
            return constructIncrement(BigDecimal.valueOf(incrementDouble));
        }
        int minMaxFrac = currency.getDefaultFractionDigits(base.usage);
        return constructFraction(minMaxFrac, minMaxFrac);
    }

    static Precision constructPassThrough() {
        return PASS_THROUGH;
    }

    /* access modifiers changed from: package-private */
    public Precision withLocaleData(Currency currency) {
        if (this instanceof CurrencyPrecision) {
            return ((CurrencyPrecision) this).withCurrency(currency);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public int chooseMultiplierAndApply(DecimalQuantity input, MultiplierProducer producer) {
        int _multiplier;
        int magnitude = input.getMagnitude();
        int multiplier = producer.getMultiplier(magnitude);
        input.adjustMagnitude(multiplier);
        apply(input);
        if (input.isZero() || input.getMagnitude() == magnitude + multiplier || multiplier == (_multiplier = producer.getMultiplier(magnitude + 1))) {
            return multiplier;
        }
        input.adjustMagnitude(_multiplier - multiplier);
        apply(input);
        return _multiplier;
    }

    /* access modifiers changed from: package-private */
    public static class InfiniteRounderImpl extends Precision {
        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
            value.roundToInfinity();
            value.setFractionLength(0, Integer.MAX_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public static class FractionRounderImpl extends FractionPrecision {
        final int maxFrac;
        final int minFrac;

        public FractionRounderImpl(int minFrac2, int maxFrac2) {
            this.minFrac = minFrac2;
            this.maxFrac = maxFrac2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
            value.roundToMagnitude(Precision.getRoundingMagnitudeFraction(this.maxFrac), this.mathContext);
            value.setFractionLength(Math.max(0, -Precision.getDisplayMagnitudeFraction(this.minFrac)), Integer.MAX_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public static class SignificantRounderImpl extends Precision {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final int maxSig;
        final int minSig;

        public SignificantRounderImpl(int minSig2, int maxSig2) {
            this.minSig = minSig2;
            this.maxSig = maxSig2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
            value.roundToMagnitude(Precision.getRoundingMagnitudeSignificant(value, this.maxSig), this.mathContext);
            value.setFractionLength(Math.max(0, -Precision.getDisplayMagnitudeSignificant(value, this.minSig)), Integer.MAX_VALUE);
            if (value.isZero() && this.minSig > 0) {
                value.setIntegerLength(1, Integer.MAX_VALUE);
            }
        }

        public void apply(DecimalQuantity quantity, int minInt) {
            quantity.setFractionLength(this.minSig - minInt, Integer.MAX_VALUE);
        }
    }

    static class FracSigRounderImpl extends Precision {
        final int maxFrac;
        final int maxSig;
        final int minFrac;
        final int minSig;

        public FracSigRounderImpl(int minFrac2, int maxFrac2, int minSig2, int maxSig2) {
            this.minFrac = minFrac2;
            this.maxFrac = maxFrac2;
            this.minSig = minSig2;
            this.maxSig = maxSig2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
            int roundingMag;
            int displayMag = Precision.getDisplayMagnitudeFraction(this.minFrac);
            int roundingMag2 = Precision.getRoundingMagnitudeFraction(this.maxFrac);
            int i = this.minSig;
            if (i == -1) {
                roundingMag = Math.max(roundingMag2, Precision.getRoundingMagnitudeSignificant(value, this.maxSig));
            } else {
                roundingMag = Math.min(roundingMag2, Precision.getDisplayMagnitudeSignificant(value, i));
            }
            value.roundToMagnitude(roundingMag, this.mathContext);
            value.setFractionLength(Math.max(0, -displayMag), Integer.MAX_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public static class IncrementRounderImpl extends Precision {
        final BigDecimal increment;

        public IncrementRounderImpl(BigDecimal increment2) {
            this.increment = increment2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
            value.roundToIncrement(this.increment, this.mathContext);
            value.setFractionLength(this.increment.scale(), this.increment.scale());
        }
    }

    /* access modifiers changed from: package-private */
    public static class CurrencyRounderImpl extends CurrencyPrecision {
        final Currency.CurrencyUsage usage;

        public CurrencyRounderImpl(Currency.CurrencyUsage usage2) {
            this.usage = usage2;
        }

        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
            throw new AssertionError();
        }
    }

    static class PassThroughRounderImpl extends Precision {
        @Override // android.icu.number.Precision
        public void apply(DecimalQuantity value) {
        }
    }

    /* access modifiers changed from: private */
    public static int getRoundingMagnitudeFraction(int maxFrac) {
        if (maxFrac == -1) {
            return Integer.MIN_VALUE;
        }
        return -maxFrac;
    }

    /* access modifiers changed from: private */
    public static int getRoundingMagnitudeSignificant(DecimalQuantity value, int maxSig) {
        if (maxSig == -1) {
            return Integer.MIN_VALUE;
        }
        return ((value.isZero() ? 0 : value.getMagnitude()) - maxSig) + 1;
    }

    /* access modifiers changed from: private */
    public static int getDisplayMagnitudeFraction(int minFrac) {
        if (minFrac == 0) {
            return Integer.MAX_VALUE;
        }
        return -minFrac;
    }

    /* access modifiers changed from: private */
    public static int getDisplayMagnitudeSignificant(DecimalQuantity value, int minSig) {
        return ((value.isZero() ? 0 : value.getMagnitude()) - minSig) + 1;
    }
}
