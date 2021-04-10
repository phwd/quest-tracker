package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class MinguoChronology extends AbstractChronology implements Serializable {
    public static final MinguoChronology INSTANCE = new MinguoChronology();
    static final int YEARS_DIFFERENCE = 1911;
    private static final long serialVersionUID = 1039765215346859963L;

    private MinguoChronology() {
    }

    @Override // java.time.chrono.Chronology
    public String getId() {
        return "Minguo";
    }

    @Override // java.time.chrono.Chronology
    public String getCalendarType() {
        return "roc";
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return date(prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate date(int prolepticYear, int month, int dayOfMonth) {
        return new MinguoDate(LocalDate.of(prolepticYear + YEARS_DIFFERENCE, month, dayOfMonth));
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return dateYearDay(prolepticYear(era, yearOfEra), dayOfYear);
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new MinguoDate(LocalDate.ofYearDay(prolepticYear + YEARS_DIFFERENCE, dayOfYear));
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate dateEpochDay(long epochDay) {
        return new MinguoDate(LocalDate.ofEpochDay(epochDay));
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate dateNow(ZoneId zone) {
        return dateNow(Clock.system(zone));
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate dateNow(Clock clock) {
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    @Override // java.time.chrono.Chronology
    public MinguoDate date(TemporalAccessor temporal) {
        if (temporal instanceof MinguoDate) {
            return (MinguoDate) temporal;
        }
        return new MinguoDate(LocalDate.from(temporal));
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoLocalDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoLocalDateTime<java.time.chrono.MinguoDate> */
    @Override // java.time.chrono.Chronology
    public ChronoLocalDateTime<MinguoDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<java.time.chrono.MinguoDate> */
    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime<MinguoDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<java.time.chrono.MinguoDate> */
    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime<MinguoDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    @Override // java.time.chrono.Chronology
    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(1911 + prolepticYear);
    }

    @Override // java.time.chrono.Chronology
    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof MinguoEra) {
            return era == MinguoEra.ROC ? yearOfEra : 1 - yearOfEra;
        }
        throw new ClassCastException("Era must be MinguoEra");
    }

    @Override // java.time.chrono.Chronology
    public MinguoEra eraOf(int eraValue) {
        return MinguoEra.of(eraValue);
    }

    @Override // java.time.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(MinguoEra.values());
    }

    /* renamed from: java.time.chrono.MinguoChronology$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Override // java.time.chrono.Chronology
    public ValueRange range(ChronoField field) {
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[field.ordinal()];
        if (i == 1) {
            ValueRange range = ChronoField.PROLEPTIC_MONTH.range();
            return ValueRange.of(range.getMinimum() - 22932, range.getMaximum() - 22932);
        } else if (i == 2) {
            ValueRange range2 = ChronoField.YEAR.range();
            return ValueRange.of(1, range2.getMaximum() - 1911, (-range2.getMinimum()) + 1 + 1911);
        } else if (i != 3) {
            return field.range();
        } else {
            ValueRange range3 = ChronoField.YEAR.range();
            return ValueRange.of(range3.getMinimum() - 1911, range3.getMaximum() - 1911);
        }
    }

    @Override // java.time.chrono.Chronology, java.time.chrono.AbstractChronology
    public MinguoDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        return (MinguoDate) super.resolveDate(fieldValues, resolverStyle);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public Object writeReplace() {
        return super.writeReplace();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
