package java.time.chrono;

import java.time.DateTimeException;

public enum MinguoEra implements Era {
    BEFORE_ROC,
    ROC;

    public static MinguoEra of(int minguoEra) {
        if (minguoEra == 0) {
            return BEFORE_ROC;
        }
        if (minguoEra == 1) {
            return ROC;
        }
        throw new DateTimeException("Invalid era: " + minguoEra);
    }

    @Override // java.time.chrono.Era
    public int getValue() {
        return ordinal();
    }
}
