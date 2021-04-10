package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.Era;

public final class JapaneseEra implements Era, Serializable {
    static final Era[] ERA_CONFIG = JapaneseChronology.JCAL.getEras();
    public static final JapaneseEra HEISEI = new JapaneseEra(2, LocalDate.of(1989, 1, 8));
    private static final JapaneseEra[] KNOWN_ERAS = new JapaneseEra[ERA_CONFIG.length];
    public static final JapaneseEra MEIJI = new JapaneseEra(-1, LocalDate.of(1868, 1, 1));
    private static final int N_ERA_CONSTANTS = (REIWA.getValue() + 2);
    private static final JapaneseEra REIWA = new JapaneseEra(3, LocalDate.of(2019, 5, 1));
    public static final JapaneseEra SHOWA = new JapaneseEra(1, LocalDate.of(1926, 12, 25));
    public static final JapaneseEra TAISHO = new JapaneseEra(N_ERA_CONSTANTS, LocalDate.of(1912, 7, 30));
    private static final long serialVersionUID = 1466499369062886794L;
    private final transient int eraValue;
    private final transient LocalDate since;

    private static int ordinal(int i) {
        return (i + 2) - 1;
    }

    static {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        japaneseEraArr[N_ERA_CONSTANTS] = MEIJI;
        japaneseEraArr[1] = TAISHO;
        japaneseEraArr[2] = SHOWA;
        japaneseEraArr[3] = HEISEI;
        japaneseEraArr[4] = REIWA;
        int i = N_ERA_CONSTANTS;
        while (true) {
            Era[] eraArr = ERA_CONFIG;
            if (i < eraArr.length) {
                CalendarDate sinceDate = eraArr[i].getSinceDate();
                KNOWN_ERAS[i] = new JapaneseEra((i - 2) + 1, LocalDate.of(sinceDate.getYear(), sinceDate.getMonth(), sinceDate.getDayOfMonth()));
                i++;
            } else {
                return;
            }
        }
    }

    private JapaneseEra(int i, LocalDate localDate) {
        this.eraValue = i;
        this.since = localDate;
    }

    /* access modifiers changed from: package-private */
    public Era getPrivateEra() {
        return ERA_CONFIG[ordinal(this.eraValue)];
    }

    public static JapaneseEra of(int i) {
        if (i >= MEIJI.eraValue) {
            int i2 = i + 2;
            JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
            if (i2 <= japaneseEraArr.length) {
                return japaneseEraArr[ordinal(i)];
            }
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    public static JapaneseEra[] values() {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        return (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, japaneseEraArr.length);
    }

    static JapaneseEra toJapaneseEra(Era era) {
        for (int length = ERA_CONFIG.length - 1; length >= 0; length--) {
            if (ERA_CONFIG[length].equals(era)) {
                return KNOWN_ERAS[length];
            }
        }
        return null;
    }

    static Era privateEraFrom(LocalDate localDate) {
        for (int length = KNOWN_ERAS.length - 1; length > 0; length--) {
            if (localDate.compareTo((ChronoLocalDate) KNOWN_ERAS[length].since) >= 0) {
                return ERA_CONFIG[length];
            }
        }
        return null;
    }

    @Override // java.time.chrono.Era
    public int getValue() {
        return this.eraValue;
    }

    @Override // java.time.chrono.Era, java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return JapaneseChronology.INSTANCE.range(ChronoField.ERA);
        }
        return super.range(temporalField);
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return ERA_CONFIG[ordinal(getValue())].getName();
    }

    public String toString() {
        return getName();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }
}
