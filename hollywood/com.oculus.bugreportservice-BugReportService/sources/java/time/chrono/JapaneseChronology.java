package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.LocalGregorianCalendar;

public final class JapaneseChronology extends AbstractChronology implements Serializable {
    public static final JapaneseChronology INSTANCE = new JapaneseChronology();
    static final LocalGregorianCalendar JCAL = ((LocalGregorianCalendar) CalendarSystem.forName("japanese"));
    private static final Locale LOCALE = Locale.forLanguageTag("ja-JP-u-ca-japanese");
    private static final long serialVersionUID = 459996390165777884L;

    @Override // java.time.chrono.Chronology
    public String getCalendarType() {
        return "japanese";
    }

    @Override // java.time.chrono.Chronology
    public String getId() {
        return "Japanese";
    }

    static Calendar createCalendar() {
        return Calendar.getJapaneseImperialInstance(TimeZone.getDefault(), LOCALE);
    }

    private JapaneseChronology() {
    }

    @Override // java.time.chrono.Chronology
    public JapaneseDate date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof JapaneseDate) {
            return (JapaneseDate) temporalAccessor;
        }
        return new JapaneseDate(LocalDate.from(temporalAccessor));
    }

    @Override // java.time.chrono.Chronology
    public ChronoLocalDateTime localDateTime(TemporalAccessor temporalAccessor) {
        return super.localDateTime(temporalAccessor);
    }

    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime zonedDateTime(Instant instant, ZoneId zoneId) {
        return super.zonedDateTime(instant, zoneId);
    }

    public int prolepticYear(Era era, int i) {
        if (era instanceof JapaneseEra) {
            JapaneseEra japaneseEra = (JapaneseEra) era;
            int year = (japaneseEra.getPrivateEra().getSinceDate().getYear() + i) - 1;
            if (i == 1) {
                return year;
            }
            if (year >= -999999999 && year <= 999999999) {
                LocalGregorianCalendar.Date newCalendarDate = JCAL.newCalendarDate((TimeZone) null);
                newCalendarDate.setEra(japaneseEra.getPrivateEra());
                newCalendarDate.setDate(i, 1, 1);
                if (JCAL.validate(newCalendarDate)) {
                    return year;
                }
            }
            throw new DateTimeException("Invalid yearOfEra value");
        }
        throw new ClassCastException("Era must be JapaneseEra");
    }

    @Override // java.time.chrono.Chronology
    public JapaneseEra eraOf(int i) {
        return JapaneseEra.of(i);
    }

    /* access modifiers changed from: package-private */
    public JapaneseEra getCurrentEra() {
        JapaneseEra[] values = JapaneseEra.values();
        return values[values.length - 1];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.JapaneseChronology$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                java.time.temporal.ChronoField[] r0 = java.time.temporal.ChronoField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField = r0
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x001f }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x002a }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.ALIGNED_WEEK_OF_MONTH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0035 }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0040 }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.YEAR_OF_ERA     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x004b }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.DAY_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0056 }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.YEAR     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = java.time.chrono.JapaneseChronology.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0062 }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.ERA     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.JapaneseChronology.AnonymousClass1.<clinit>():void");
        }
    }

    public ValueRange range(ChronoField chronoField) {
        switch (AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + chronoField);
            case 5:
                Calendar createCalendar = createCalendar();
                return ValueRange.of(1, (long) createCalendar.getGreatestMinimum(1), (long) (createCalendar.getLeastMaximum(1) + 1), (long) (999999999 - getCurrentEra().getPrivateEra().getSinceDate().getYear()));
            case 6:
                Calendar createCalendar2 = createCalendar();
                return ValueRange.of((long) createCalendar2.getMinimum(6), (long) createCalendar2.getGreatestMinimum(6), (long) createCalendar2.getLeastMaximum(6), (long) createCalendar2.getMaximum(6));
            case 7:
                return ValueRange.of((long) JapaneseDate.MEIJI_6_ISODATE.getYear(), 999999999);
            case 8:
                return ValueRange.of((long) JapaneseEra.MEIJI.getValue(), (long) getCurrentEra().getValue());
            default:
                return chronoField.range();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public Object writeReplace() {
        return super.writeReplace();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
