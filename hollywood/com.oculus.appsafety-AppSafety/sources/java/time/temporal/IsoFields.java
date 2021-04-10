package java.time.temporal;

import android.icu.lang.UCharacter;
import android.icu.text.DateTimePatternGenerator;
import android.icu.util.ULocale;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public final class IsoFields {
    public static final TemporalField DAY_OF_QUARTER = Field.DAY_OF_QUARTER;
    public static final TemporalField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    public static final TemporalUnit QUARTER_YEARS = Unit.QUARTER_YEARS;
    public static final TemporalField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;
    public static final TemporalUnit WEEK_BASED_YEARS = Unit.WEEK_BASED_YEARS;
    public static final TemporalField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;

    private IsoFields() {
        throw new AssertionError((Object) "Not instantiable");
    }

    /* access modifiers changed from: private */
    public enum Field implements TemporalField {
        DAY_OF_QUARTER {
            @Override // java.time.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.DAYS;
            }

            @Override // java.time.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            @Override // java.time.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.of(1, 90, 92);
            }

            @Override // java.time.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.DAY_OF_YEAR) && temporal.isSupported(ChronoField.MONTH_OF_YEAR) && temporal.isSupported(ChronoField.YEAR) && Field.isIso(temporal);
            }

            @Override // java.time.temporal.IsoFields.Field, java.time.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (isSupportedBy(temporal)) {
                    long qoy = temporal.getLong(QUARTER_OF_YEAR);
                    if (qoy == 1) {
                        return IsoChronology.INSTANCE.isLeapYear(temporal.getLong(ChronoField.YEAR)) ? ValueRange.of(1, 91) : ValueRange.of(1, 90);
                    } else if (qoy == 2) {
                        return ValueRange.of(1, 91);
                    } else {
                        if (qoy == 3 || qoy == 4) {
                            return ValueRange.of(1, 92);
                        }
                        return range();
                    }
                } else {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
            }

            @Override // java.time.temporal.TemporalField
            public long getFrom(TemporalAccessor temporal) {
                if (isSupportedBy(temporal)) {
                    return (long) (temporal.get(ChronoField.DAY_OF_YEAR) - Field.QUARTER_DAYS[((temporal.get(ChronoField.MONTH_OF_YEAR) - 1) / 3) + (IsoChronology.INSTANCE.isLeapYear(temporal.getLong(ChronoField.YEAR)) ? 4 : 0)]);
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
            }

            @Override // java.time.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long curValue = getFrom(temporal);
                range().checkValidValue(newValue, this);
                return (R) temporal.with(ChronoField.DAY_OF_YEAR, temporal.getLong(ChronoField.DAY_OF_YEAR) + (newValue - curValue));
            }

            @Override // java.time.temporal.TemporalField
            public ChronoLocalDate resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
                LocalDate date;
                long doq;
                Long yearLong = fieldValues.get(ChronoField.YEAR);
                Long qoyLong = fieldValues.get(QUARTER_OF_YEAR);
                if (yearLong == null || qoyLong == null) {
                    return null;
                }
                int y = ChronoField.YEAR.checkValidIntValue(yearLong.longValue());
                long doq2 = fieldValues.get(DAY_OF_QUARTER).longValue();
                Field.ensureIso(partialTemporal);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    date = LocalDate.of(y, 1, 1).plusMonths(Math.multiplyExact(Math.subtractExact(qoyLong.longValue(), 1), 3));
                    doq = Math.subtractExact(doq2, 1);
                } else {
                    LocalDate date2 = LocalDate.of(y, ((QUARTER_OF_YEAR.range().checkValidIntValue(qoyLong.longValue(), QUARTER_OF_YEAR) - 1) * 3) + 1, 1);
                    if (doq2 < 1 || doq2 > 90) {
                        if (resolverStyle == ResolverStyle.STRICT) {
                            rangeRefinedBy(date2).checkValidValue(doq2, this);
                        } else {
                            range().checkValidValue(doq2, this);
                        }
                    }
                    doq = doq2 - 1;
                    date = date2;
                }
                fieldValues.remove(this);
                fieldValues.remove(ChronoField.YEAR);
                fieldValues.remove(QUARTER_OF_YEAR);
                return date.plusDays(doq);
            }

            @Override // java.lang.Enum, java.time.temporal.TemporalField
            public String toString() {
                return "DayOfQuarter";
            }
        },
        QUARTER_OF_YEAR {
            @Override // java.time.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            @Override // java.time.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.YEARS;
            }

            @Override // java.time.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.of(1, 4);
            }

            @Override // java.time.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.MONTH_OF_YEAR) && Field.isIso(temporal);
            }

            @Override // java.time.temporal.TemporalField
            public long getFrom(TemporalAccessor temporal) {
                if (isSupportedBy(temporal)) {
                    return (2 + temporal.getLong(ChronoField.MONTH_OF_YEAR)) / 3;
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
            }

            @Override // java.time.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long curValue = getFrom(temporal);
                range().checkValidValue(newValue, this);
                return (R) temporal.with(ChronoField.MONTH_OF_YEAR, temporal.getLong(ChronoField.MONTH_OF_YEAR) + ((newValue - curValue) * 3));
            }

            @Override // java.lang.Enum, java.time.temporal.TemporalField
            public String toString() {
                return "QuarterOfYear";
            }
        },
        WEEK_OF_WEEK_BASED_YEAR {
            @Override // java.time.temporal.TemporalField
            public String getDisplayName(Locale locale) {
                Objects.requireNonNull(locale, "locale");
                String icuName = DateTimePatternGenerator.getFrozenInstance(ULocale.forLocale(locale)).getAppendItemName(4);
                return (icuName == null || icuName.isEmpty()) ? toString() : icuName;
            }

            @Override // java.time.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return ChronoUnit.WEEKS;
            }

            @Override // java.time.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            @Override // java.time.temporal.TemporalField
            public ValueRange range() {
                return ValueRange.of(1, 52, 53);
            }

            @Override // java.time.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporal);
            }

            @Override // java.time.temporal.IsoFields.Field, java.time.temporal.TemporalField
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (isSupportedBy(temporal)) {
                    return Field.getWeekRange(LocalDate.from(temporal));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // java.time.temporal.TemporalField
            public long getFrom(TemporalAccessor temporal) {
                if (isSupportedBy(temporal)) {
                    return (long) Field.getWeek(LocalDate.from(temporal));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            @Override // java.time.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                range().checkValidValue(newValue, this);
                return (R) temporal.plus(Math.subtractExact(newValue, getFrom(temporal)), ChronoUnit.WEEKS);
            }

            @Override // java.time.temporal.TemporalField
            public ChronoLocalDate resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
                LocalDate date;
                long j;
                Long wbyLong = fieldValues.get(WEEK_BASED_YEAR);
                Long dowLong = fieldValues.get(ChronoField.DAY_OF_WEEK);
                if (wbyLong == null || dowLong == null) {
                    return null;
                }
                int wby = WEEK_BASED_YEAR.range().checkValidIntValue(wbyLong.longValue(), WEEK_BASED_YEAR);
                long wowby = fieldValues.get(WEEK_OF_WEEK_BASED_YEAR).longValue();
                Field.ensureIso(partialTemporal);
                LocalDate date2 = LocalDate.of(wby, 1, 4);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long dow = dowLong.longValue();
                    if (dow > 7) {
                        date2 = date2.plusWeeks((dow - 1) / 7);
                        dow = ((dow - 1) % 7) + 1;
                        j = 1;
                    } else if (dow < 1) {
                        date2 = date2.plusWeeks(Math.subtractExact(dow, 7) / 7);
                        j = 1;
                        dow = ((6 + dow) % 7) + 1;
                    } else {
                        j = 1;
                    }
                    date = date2.plusWeeks(Math.subtractExact(wowby, j)).with((TemporalField) ChronoField.DAY_OF_WEEK, dow);
                } else {
                    int dow2 = ChronoField.DAY_OF_WEEK.checkValidIntValue(dowLong.longValue());
                    if (wowby < 1 || wowby > 52) {
                        if (resolverStyle == ResolverStyle.STRICT) {
                            Field.getWeekRange(date2).checkValidValue(wowby, this);
                        } else {
                            range().checkValidValue(wowby, this);
                        }
                    }
                    date = date2.plusWeeks(wowby - 1).with((TemporalField) ChronoField.DAY_OF_WEEK, (long) dow2);
                }
                fieldValues.remove(this);
                fieldValues.remove(WEEK_BASED_YEAR);
                fieldValues.remove(ChronoField.DAY_OF_WEEK);
                return date;
            }

            @Override // java.lang.Enum, java.time.temporal.TemporalField
            public String toString() {
                return "WeekOfWeekBasedYear";
            }
        },
        WEEK_BASED_YEAR {
            @Override // java.time.temporal.TemporalField
            public TemporalUnit getBaseUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            @Override // java.time.temporal.TemporalField
            public TemporalUnit getRangeUnit() {
                return ChronoUnit.FOREVER;
            }

            @Override // java.time.temporal.TemporalField
            public ValueRange range() {
                return ChronoField.YEAR.range();
            }

            @Override // java.time.temporal.TemporalField
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporal);
            }

            @Override // java.time.temporal.TemporalField
            public long getFrom(TemporalAccessor temporal) {
                if (isSupportedBy(temporal)) {
                    return (long) Field.getWeekBasedYear(LocalDate.from(temporal));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // java.time.temporal.TemporalField
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                if (isSupportedBy(temporal)) {
                    int newWby = range().checkValidIntValue(newValue, WEEK_BASED_YEAR);
                    LocalDate date = LocalDate.from((TemporalAccessor) temporal);
                    int dow = date.get(ChronoField.DAY_OF_WEEK);
                    int week = Field.getWeek(date);
                    if (week == 53 && Field.getWeekRange(newWby) == 52) {
                        week = 52;
                    }
                    LocalDate resolved = LocalDate.of(newWby, 1, 4);
                    return (R) temporal.with(resolved.plusDays((long) ((dow - resolved.get(ChronoField.DAY_OF_WEEK)) + ((week - 1) * 7))));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            @Override // java.lang.Enum, java.time.temporal.TemporalField
            public String toString() {
                return "WeekBasedYear";
            }
        };
        
        private static final int[] QUARTER_DAYS = {0, 90, 181, UCharacter.UnicodeBlock.TANGUT_COMPONENTS_ID, 0, 91, 182, UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID};

        /* synthetic */ Field(AnonymousClass1 x2) {
            this();
        }

        @Override // java.time.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // java.time.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        @Override // java.time.temporal.TemporalField
        public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
            return range();
        }

        /* access modifiers changed from: private */
        public static boolean isIso(TemporalAccessor temporal) {
            return Chronology.from(temporal).equals(IsoChronology.INSTANCE);
        }

        /* access modifiers changed from: private */
        public static void ensureIso(TemporalAccessor temporal) {
            if (!isIso(temporal)) {
                throw new DateTimeException("Resolve requires IsoChronology");
            }
        }

        /* access modifiers changed from: private */
        public static ValueRange getWeekRange(LocalDate date) {
            return ValueRange.of(1, (long) getWeekRange(getWeekBasedYear(date)));
        }

        /* access modifiers changed from: private */
        public static int getWeekRange(int wby) {
            LocalDate date = LocalDate.of(wby, 1, 1);
            if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                return 53;
            }
            if (date.getDayOfWeek() != DayOfWeek.WEDNESDAY || !date.isLeapYear()) {
                return 52;
            }
            return 53;
        }

        /* access modifiers changed from: private */
        public static int getWeek(LocalDate date) {
            int dow0 = date.getDayOfWeek().ordinal();
            boolean z = true;
            int doy0 = date.getDayOfYear() - 1;
            int doyThu0 = (3 - dow0) + doy0;
            int firstMonDoy0 = (doyThu0 - ((doyThu0 / 7) * 7)) - 3;
            if (firstMonDoy0 < -3) {
                firstMonDoy0 += 7;
            }
            if (doy0 < firstMonDoy0) {
                return (int) getWeekRange(date.withDayOfYear(180).minusYears(1)).getMaximum();
            }
            int week = ((doy0 - firstMonDoy0) / 7) + 1;
            if (week != 53) {
                return week;
            }
            if (firstMonDoy0 != -3 && (firstMonDoy0 != -2 || !date.isLeapYear())) {
                z = false;
            }
            if (!z) {
                return 1;
            }
            return week;
        }

        /* access modifiers changed from: private */
        public static int getWeekBasedYear(LocalDate date) {
            int year = date.getYear();
            int doy = date.getDayOfYear();
            if (doy <= 3) {
                if (doy - date.getDayOfWeek().ordinal() < -2) {
                    return year - 1;
                }
                return year;
            } else if (doy < 363) {
                return year;
            } else {
                if (((doy - 363) - (date.isLeapYear() ? 1 : 0)) - date.getDayOfWeek().ordinal() >= 0) {
                    return year + 1;
                }
                return year;
            }
        }
    }

    private enum Unit implements TemporalUnit {
        WEEK_BASED_YEARS("WeekBasedYears", Duration.ofSeconds(31556952)),
        QUARTER_YEARS("QuarterYears", Duration.ofSeconds(7889238));
        
        private final Duration duration;
        private final String name;

        private Unit(String name2, Duration estimatedDuration) {
            this.name = name2;
            this.duration = estimatedDuration;
        }

        @Override // java.time.temporal.TemporalUnit
        public Duration getDuration() {
            return this.duration;
        }

        @Override // java.time.temporal.TemporalUnit
        public boolean isDurationEstimated() {
            return true;
        }

        @Override // java.time.temporal.TemporalUnit
        public boolean isDateBased() {
            return true;
        }

        @Override // java.time.temporal.TemporalUnit
        public boolean isTimeBased() {
            return false;
        }

        @Override // java.time.temporal.TemporalUnit
        public boolean isSupportedBy(Temporal temporal) {
            return temporal.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override // java.time.temporal.TemporalUnit
        public <R extends Temporal> R addTo(R temporal, long amount) {
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return (R) temporal.with(IsoFields.WEEK_BASED_YEAR, Math.addExact((long) temporal.get(IsoFields.WEEK_BASED_YEAR), amount));
            }
            if (i == 2) {
                return (R) temporal.plus(amount / 256, ChronoUnit.YEARS).plus((amount % 256) * 3, ChronoUnit.MONTHS);
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override // java.time.temporal.TemporalUnit
        public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
            if (temporal1Inclusive.getClass() != temporal2Exclusive.getClass()) {
                return temporal1Inclusive.until(temporal2Exclusive, this);
            }
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return Math.subtractExact(temporal2Exclusive.getLong(IsoFields.WEEK_BASED_YEAR), temporal1Inclusive.getLong(IsoFields.WEEK_BASED_YEAR));
            }
            if (i == 2) {
                return temporal1Inclusive.until(temporal2Exclusive, ChronoUnit.MONTHS) / 3;
            }
            throw new IllegalStateException("Unreachable");
        }

        @Override // java.lang.Enum, java.time.temporal.TemporalUnit
        public String toString() {
            return this.name;
        }
    }

    /* renamed from: java.time.temporal.IsoFields$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$IsoFields$Unit = new int[Unit.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$IsoFields$Unit[Unit.WEEK_BASED_YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$IsoFields$Unit[Unit.QUARTER_YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
