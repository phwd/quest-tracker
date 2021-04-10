package java.time.chrono;

import java.time.DateTimeException;

public enum IsoEra implements Era {
    BCE,
    CE;

    public static IsoEra of(int isoEra) {
        if (isoEra == 0) {
            return BCE;
        }
        if (isoEra == 1) {
            return CE;
        }
        throw new DateTimeException("Invalid era: " + isoEra);
    }

    @Override // java.time.chrono.Era
    public int getValue() {
        return ordinal();
    }
}
