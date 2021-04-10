package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Calendar;
import java.util.TimeZone;
import sun.util.calendar.Era;
import sun.util.calendar.LocalGregorianCalendar;

public final class JapaneseDate extends ChronoLocalDateImpl implements ChronoLocalDate, Serializable {
    static final LocalDate MEIJI_6_ISODATE = LocalDate.of(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private transient JapaneseEra era;
    private final transient LocalDate isoDate;
    private transient int yearOfEra;

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    JapaneseDate(LocalDate localDate) {
        if (!localDate.isBefore(MEIJI_6_ISODATE)) {
            LocalGregorianCalendar.Date privateJapaneseDate = toPrivateJapaneseDate(localDate);
            this.era = JapaneseEra.toJapaneseEra(privateJapaneseDate.getEra());
            this.yearOfEra = privateJapaneseDate.getYear();
            this.isoDate = localDate;
            return;
        }
        throw new DateTimeException("JapaneseDate before Meiji 6 is not supported");
    }

    @Override // java.time.chrono.ChronoLocalDate
    public JapaneseChronology getChronology() {
        return JapaneseChronology.INSTANCE;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public JapaneseEra getEra() {
        return this.era;
    }

    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    public int lengthOfYear() {
        Calendar createCalendar = JapaneseChronology.createCalendar();
        createCalendar.set(0, this.era.getValue() + 2);
        createCalendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
        return createCalendar.getActualMaximum(6);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        if (temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH || temporalField == ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR || temporalField == ChronoField.ALIGNED_WEEK_OF_MONTH || temporalField == ChronoField.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        return super.isSupported(temporalField);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        if (isSupported(temporalField)) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            if (i == 1) {
                return ValueRange.of(1, (long) lengthOfMonth());
            }
            if (i == 2) {
                return ValueRange.of(1, (long) lengthOfYear());
            }
            if (i != 3) {
                return getChronology().range(chronoField);
            }
            Calendar createCalendar = JapaneseChronology.createCalendar();
            createCalendar.set(0, this.era.getValue() + 2);
            createCalendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
            return ValueRange.of(1, (long) createCalendar.getActualMaximum(1));
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.JapaneseDate$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
            // Method dump skipped, instructions count: 111
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.JapaneseDate.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()]) {
            case 2:
                Calendar createCalendar = JapaneseChronology.createCalendar();
                createCalendar.set(0, this.era.getValue() + 2);
                createCalendar.set(this.yearOfEra, this.isoDate.getMonthValue() - 1, this.isoDate.getDayOfMonth());
                return (long) createCalendar.get(6);
            case 3:
                return (long) this.yearOfEra;
            case 4:
            case 5:
            case 6:
            case 7:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            case 8:
                return (long) this.era.getValue();
            default:
                return this.isoDate.getLong(temporalField);
        }
    }

    private static LocalGregorianCalendar.Date toPrivateJapaneseDate(LocalDate localDate) {
        LocalGregorianCalendar.Date newCalendarDate = JapaneseChronology.JCAL.newCalendarDate((TimeZone) null);
        Era privateEraFrom = JapaneseEra.privateEraFrom(localDate);
        int year = localDate.getYear();
        if (privateEraFrom != null) {
            year -= privateEraFrom.getSinceDate().getYear() - 1;
        }
        newCalendarDate.setEra(privateEraFrom);
        newCalendarDate.setYear(year);
        newCalendarDate.setMonth(localDate.getMonthValue());
        newCalendarDate.setDayOfMonth(localDate.getDayOfMonth());
        JapaneseChronology.JCAL.normalize(newCalendarDate);
        return newCalendarDate;
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public JapaneseDate with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return (JapaneseDate) super.with(temporalField, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        if (getLong(chronoField) == j) {
            return this;
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
        if (i == 3 || i == 8 || i == 9) {
            int checkValidIntValue = getChronology().range(chronoField).checkValidIntValue(j, chronoField);
            int i2 = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            if (i2 == 3) {
                return withYear(checkValidIntValue);
            }
            if (i2 == 8) {
                return withYear(JapaneseEra.of(checkValidIntValue), this.yearOfEra);
            }
            if (i2 == 9) {
                return with(this.isoDate.withYear(checkValidIntValue));
            }
        }
        return with(this.isoDate.with(temporalField, j));
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public JapaneseDate with(TemporalAdjuster temporalAdjuster) {
        return (JapaneseDate) super.with(temporalAdjuster);
    }

    private JapaneseDate withYear(JapaneseEra japaneseEra, int i) {
        return with(this.isoDate.withYear(JapaneseChronology.INSTANCE.prolepticYear(japaneseEra, i)));
    }

    private JapaneseDate withYear(int i) {
        return withYear(getEra(), i);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public JapaneseDate plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public JapaneseDate plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public JapaneseDate plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public JapaneseDate plus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.plus(j, temporalUnit);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public JapaneseDate minus(long j, TemporalUnit temporalUnit) {
        return (JapaneseDate) super.minus(j, temporalUnit);
    }

    private JapaneseDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new JapaneseDate(localDate);
    }

    @Override // java.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override // java.time.chrono.ChronoLocalDate
    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JapaneseDate) {
            return this.isoDate.equals(((JapaneseDate) obj).isoDate);
        }
        return false;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public int hashCode() {
        return this.isoDate.hashCode() ^ getChronology().getId().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 4, this);
    }
}
