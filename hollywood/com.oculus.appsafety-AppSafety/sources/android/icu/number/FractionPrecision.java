package android.icu.number;

public abstract class FractionPrecision extends Precision {
    FractionPrecision() {
    }

    public Precision withMinDigits(int minSignificantDigits) {
        if (minSignificantDigits >= 1 && minSignificantDigits <= 999) {
            return constructFractionSignificant(this, minSignificantDigits, -1);
        }
        throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
    }

    public Precision withMaxDigits(int maxSignificantDigits) {
        if (maxSignificantDigits >= 1 && maxSignificantDigits <= 999) {
            return constructFractionSignificant(this, -1, maxSignificantDigits);
        }
        throw new IllegalArgumentException("Significant digits must be between 1 and 999 (inclusive)");
    }
}
