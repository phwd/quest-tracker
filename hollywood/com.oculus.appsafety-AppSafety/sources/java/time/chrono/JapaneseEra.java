package java.time.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.Era;

public final class JapaneseEra implements Era, Serializable {
    static final Era[] ERA_CONFIG = JapaneseChronology.JCAL.getEras();
    static final int ERA_OFFSET = 2;
    public static final JapaneseEra HEISEI = new JapaneseEra(2, LocalDate.of(1989, 1, 8));
    private static final JapaneseEra[] KNOWN_ERAS = new JapaneseEra[ERA_CONFIG.length];
    public static final JapaneseEra MEIJI = new JapaneseEra(-1, LocalDate.of(1868, 1, 1));
    private static final int N_ERA_CONSTANTS = (REIWA.getValue() + 2);
    private static final JapaneseEra REIWA = new JapaneseEra(3, LocalDate.of(2019, 5, 1));
    public static final JapaneseEra SHOWA = new JapaneseEra(1, LocalDate.of(1926, 12, 25));
    public static final JapaneseEra TAISHO = new JapaneseEra(0, LocalDate.of(1912, 7, 30));
    private static final long serialVersionUID = 1466499369062886794L;
    private final transient int eraValue;
    private final transient LocalDate since;

    static {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        japaneseEraArr[0] = MEIJI;
        japaneseEraArr[1] = TAISHO;
        japaneseEraArr[2] = SHOWA;
        japaneseEraArr[3] = HEISEI;
        japaneseEraArr[4] = REIWA;
        int i = N_ERA_CONSTANTS;
        while (true) {
            Era[] eraArr = ERA_CONFIG;
            if (i < eraArr.length) {
                CalendarDate date = eraArr[i].getSinceDate();
                KNOWN_ERAS[i] = new JapaneseEra((i - 2) + 1, LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()));
                i++;
            } else {
                return;
            }
        }
    }

    private JapaneseEra(int eraValue2, LocalDate since2) {
        this.eraValue = eraValue2;
        this.since = since2;
    }

    /* access modifiers changed from: package-private */
    public Era getPrivateEra() {
        return ERA_CONFIG[ordinal(this.eraValue)];
    }

    public static JapaneseEra of(int japaneseEra) {
        if (japaneseEra >= MEIJI.eraValue) {
            int i = japaneseEra + 2;
            JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
            if (i <= japaneseEraArr.length) {
                return japaneseEraArr[ordinal(japaneseEra)];
            }
        }
        throw new DateTimeException("Invalid era: " + japaneseEra);
    }

    public static JapaneseEra valueOf(String japaneseEra) {
        Objects.requireNonNull(japaneseEra, "japaneseEra");
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        for (JapaneseEra era : japaneseEraArr) {
            if (era.getName().equals(japaneseEra)) {
                return era;
            }
        }
        throw new IllegalArgumentException("japaneseEra is invalid");
    }

    public static JapaneseEra[] values() {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        return (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, japaneseEraArr.length);
    }

    @Override // java.time.chrono.Era
    public String getDisplayName(TextStyle style, Locale locale) {
        if (getValue() > N_ERA_CONSTANTS - 2) {
            Objects.requireNonNull(locale, "locale");
            return style.asNormal() == TextStyle.NARROW ? getAbbreviation() : getName();
        }
        return new DateTimeFormatterBuilder().appendText(ChronoField.ERA, style).toFormatter(locale).withChronology(JapaneseChronology.INSTANCE).format(this == MEIJI ? JapaneseDate.MEIJI_6_ISODATE : this.since);
    }

    static JapaneseEra from(LocalDate date) {
        if (!date.isBefore(JapaneseDate.MEIJI_6_ISODATE)) {
            for (int i = KNOWN_ERAS.length - 1; i > 0; i--) {
                JapaneseEra era = KNOWN_ERAS[i];
                if (date.compareTo((ChronoLocalDate) era.since) >= 0) {
                    return era;
                }
            }
            return null;
        }
        throw new DateTimeException("JapaneseDate before Meiji 6 are not supported");
    }

    static JapaneseEra toJapaneseEra(Era privateEra) {
        for (int i = ERA_CONFIG.length - 1; i >= 0; i--) {
            if (ERA_CONFIG[i].equals(privateEra)) {
                return KNOWN_ERAS[i];
            }
        }
        return null;
    }

    static Era privateEraFrom(LocalDate isoDate) {
        for (int i = KNOWN_ERAS.length - 1; i > 0; i--) {
            if (isoDate.compareTo((ChronoLocalDate) KNOWN_ERAS[i].since) >= 0) {
                return ERA_CONFIG[i];
            }
        }
        return null;
    }

    private static int ordinal(int eraValue2) {
        return (eraValue2 + 2) - 1;
    }

    @Override // java.time.chrono.Era
    public int getValue() {
        return this.eraValue;
    }

    @Override // java.time.chrono.Era, java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (field == ChronoField.ERA) {
            return JapaneseChronology.INSTANCE.range(ChronoField.ERA);
        }
        return super.range(field);
    }

    /* access modifiers changed from: package-private */
    public String getAbbreviation() {
        return ERA_CONFIG[ordinal(getValue())].getAbbreviation();
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return ERA_CONFIG[ordinal(getValue())].getName();
    }

    public String toString() {
        return getName();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        out.writeByte(getValue());
    }

    static JapaneseEra readExternal(DataInput in) throws IOException {
        return of(in.readByte());
    }
}
