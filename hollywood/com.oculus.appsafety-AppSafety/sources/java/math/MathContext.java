package java.math;

import android.icu.text.PluralRules;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

public final class MathContext implements Serializable {
    public static final MathContext DECIMAL128 = new MathContext(34, RoundingMode.HALF_EVEN);
    public static final MathContext DECIMAL32 = new MathContext(7, RoundingMode.HALF_EVEN);
    public static final MathContext DECIMAL64 = new MathContext(16, RoundingMode.HALF_EVEN);
    public static final MathContext UNLIMITED = new MathContext(0, RoundingMode.HALF_UP);
    private static final long serialVersionUID = 5579720004786848255L;
    private final int precision;
    private final RoundingMode roundingMode;

    public MathContext(int precision2) {
        this(precision2, RoundingMode.HALF_UP);
    }

    public MathContext(int precision2, RoundingMode roundingMode2) {
        this.precision = precision2;
        this.roundingMode = roundingMode2;
        checkValid();
    }

    public MathContext(String s) {
        int spaceIndex;
        int precisionLength = "precision=".length();
        int roundingModeLength = "roundingMode=".length();
        if (!s.startsWith("precision=") || (spaceIndex = s.indexOf(32, precisionLength)) == -1) {
            throw invalidMathContext("Missing precision", s);
        }
        try {
            this.precision = Integer.parseInt(s.substring(precisionLength, spaceIndex));
            int roundingModeStart = spaceIndex + 1;
            if (s.regionMatches(roundingModeStart, "roundingMode=", 0, roundingModeLength)) {
                this.roundingMode = RoundingMode.valueOf(s.substring(roundingModeStart + roundingModeLength));
                checkValid();
                return;
            }
            throw invalidMathContext("Missing rounding mode", s);
        } catch (NumberFormatException e) {
            throw invalidMathContext("Bad precision", s);
        }
    }

    private IllegalArgumentException invalidMathContext(String reason, String s) {
        throw new IllegalArgumentException(reason + PluralRules.KEYWORD_RULE_SEPARATOR + s);
    }

    private void checkValid() {
        if (this.precision < 0) {
            throw new IllegalArgumentException("Negative precision: " + this.precision);
        } else if (this.roundingMode == null) {
            throw new NullPointerException("roundingMode == null");
        }
    }

    public int getPrecision() {
        return this.precision;
    }

    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    public boolean equals(Object x) {
        return (x instanceof MathContext) && ((MathContext) x).getPrecision() == this.precision && ((MathContext) x).getRoundingMode() == this.roundingMode;
    }

    public int hashCode() {
        return (this.precision << 3) | this.roundingMode.ordinal();
    }

    public String toString() {
        return "precision=" + this.precision + " roundingMode=" + ((Object) this.roundingMode);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        try {
            checkValid();
        } catch (Exception ex) {
            throw new StreamCorruptedException(ex.getMessage());
        }
    }
}
