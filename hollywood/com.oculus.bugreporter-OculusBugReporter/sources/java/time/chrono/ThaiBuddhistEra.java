package java.time.chrono;

import java.time.DateTimeException;

public enum ThaiBuddhistEra implements Era {
    BEFORE_BE,
    BE;

    public static ThaiBuddhistEra of(int thaiBuddhistEra) {
        if (thaiBuddhistEra == 0) {
            return BEFORE_BE;
        }
        if (thaiBuddhistEra == 1) {
            return BE;
        }
        throw new DateTimeException("Invalid era: " + thaiBuddhistEra);
    }

    @Override // java.time.chrono.Era
    public int getValue() {
        return ordinal();
    }
}
