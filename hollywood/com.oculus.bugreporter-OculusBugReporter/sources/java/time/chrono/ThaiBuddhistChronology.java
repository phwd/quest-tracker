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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ThaiBuddhistChronology extends AbstractChronology implements Serializable {
    private static final HashMap<String, String[]> ERA_FULL_NAMES = new HashMap<>();
    private static final HashMap<String, String[]> ERA_NARROW_NAMES = new HashMap<>();
    private static final HashMap<String, String[]> ERA_SHORT_NAMES = new HashMap<>();
    private static final String FALLBACK_LANGUAGE = "en";
    public static final ThaiBuddhistChronology INSTANCE = new ThaiBuddhistChronology();
    private static final String TARGET_LANGUAGE = "th";
    static final int YEARS_DIFFERENCE = 543;
    private static final long serialVersionUID = 2775954514031616474L;

    static {
        ERA_NARROW_NAMES.put(FALLBACK_LANGUAGE, new String[]{"BB", "BE"});
        ERA_NARROW_NAMES.put(TARGET_LANGUAGE, new String[]{"BB", "BE"});
        ERA_SHORT_NAMES.put(FALLBACK_LANGUAGE, new String[]{"B.B.", "B.E."});
        ERA_SHORT_NAMES.put(TARGET_LANGUAGE, new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        ERA_FULL_NAMES.put(FALLBACK_LANGUAGE, new String[]{"Before Buddhist", "Budhhist Era"});
        ERA_FULL_NAMES.put(TARGET_LANGUAGE, new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    private ThaiBuddhistChronology() {
    }

    @Override // java.time.chrono.Chronology
    public String getId() {
        return "ThaiBuddhist";
    }

    @Override // java.time.chrono.Chronology
    public String getCalendarType() {
        return "buddhist";
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return date(prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate date(int prolepticYear, int month, int dayOfMonth) {
        return new ThaiBuddhistDate(LocalDate.of(prolepticYear - 543, month, dayOfMonth));
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return dateYearDay(prolepticYear(era, yearOfEra), dayOfYear);
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate dateYearDay(int prolepticYear, int dayOfYear) {
        return new ThaiBuddhistDate(LocalDate.ofYearDay(prolepticYear - 543, dayOfYear));
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate dateEpochDay(long epochDay) {
        return new ThaiBuddhistDate(LocalDate.ofEpochDay(epochDay));
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate dateNow(ZoneId zone) {
        return dateNow(Clock.system(zone));
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate dateNow(Clock clock) {
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistDate date(TemporalAccessor temporal) {
        if (temporal instanceof ThaiBuddhistDate) {
            return (ThaiBuddhistDate) temporal;
        }
        return new ThaiBuddhistDate(LocalDate.from(temporal));
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoLocalDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoLocalDateTime<java.time.chrono.ThaiBuddhistDate> */
    @Override // java.time.chrono.Chronology
    public ChronoLocalDateTime<ThaiBuddhistDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<java.time.chrono.ThaiBuddhistDate> */
    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime<ThaiBuddhistDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<? extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<java.time.chrono.ThaiBuddhistDate> */
    @Override // java.time.chrono.Chronology
    public ChronoZonedDateTime<ThaiBuddhistDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    @Override // java.time.chrono.Chronology
    public boolean isLeapYear(long prolepticYear) {
        return IsoChronology.INSTANCE.isLeapYear(prolepticYear - 543);
    }

    @Override // java.time.chrono.Chronology
    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof ThaiBuddhistEra) {
            return era == ThaiBuddhistEra.BE ? yearOfEra : 1 - yearOfEra;
        }
        throw new ClassCastException("Era must be BuddhistEra");
    }

    @Override // java.time.chrono.Chronology
    public ThaiBuddhistEra eraOf(int eraValue) {
        return ThaiBuddhistEra.of(eraValue);
    }

    @Override // java.time.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(ThaiBuddhistEra.values());
    }

    /* renamed from: java.time.chrono.ThaiBuddhistChronology$1  reason: invalid class name */
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
            return ValueRange.of(range.getMinimum() + 6516, range.getMaximum() + 6516);
        } else if (i == 2) {
            ValueRange range2 = ChronoField.YEAR.range();
            return ValueRange.of(1, (-(range2.getMinimum() + 543)) + 1, range2.getMaximum() + 543);
        } else if (i != 3) {
            return field.range();
        } else {
            ValueRange range3 = ChronoField.YEAR.range();
            return ValueRange.of(range3.getMinimum() + 543, range3.getMaximum() + 543);
        }
    }

    @Override // java.time.chrono.Chronology, java.time.chrono.AbstractChronology
    public ThaiBuddhistDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        return (ThaiBuddhistDate) super.resolveDate(fieldValues, resolverStyle);
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
