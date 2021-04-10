package java.math;

import java.io.ObjectInputStream;
import java.io.Serializable;

public final class MathContext implements Serializable {
    public static final MathContext DECIMAL128 = new MathContext(34, RoundingMode.HALF_EVEN);
    public static final MathContext DECIMAL32 = new MathContext(7, RoundingMode.HALF_EVEN);
    public static final MathContext DECIMAL64 = new MathContext(16, RoundingMode.HALF_EVEN);
    public static final MathContext UNLIMITED = new MathContext(0, RoundingMode.HALF_UP);
    private static final long serialVersionUID = 5579720004786848255L;
    private final int precision;
    private final RoundingMode roundingMode;

    public MathContext(int i) {
        this(i, RoundingMode.HALF_UP);
    }

    public MathContext(int i, RoundingMode roundingMode2) {
        this.precision = i;
        this.roundingMode = roundingMode2;
        checkValid();
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

    public boolean equals(Object obj) {
        if (obj instanceof MathContext) {
            MathContext mathContext = (MathContext) obj;
            return mathContext.getPrecision() == this.precision && mathContext.getRoundingMode() == this.roundingMode;
        }
    }

    public int hashCode() {
        return this.roundingMode.ordinal() | (this.precision << 3);
    }

    public String toString() {
        return "precision=" + this.precision + " roundingMode=" + this.roundingMode;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
