package java.time;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Objects;

public final class Year implements Temporal, TemporalAdjuster, Comparable<Year>, Serializable {
    public static final int MAX_VALUE = 999999999;
    public static final int MIN_VALUE = -999999999;
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).toFormatter();
    private static final long serialVersionUID = -23038383694477807L;
    private final int year;

    public static Year now() {
        return now(Clock.systemDefaultZone());
    }

    public static Year now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    public static Year now(Clock clock) {
        return of(LocalDate.now(clock).getYear());
    }

    public static Year of(int isoYear) {
        ChronoField.YEAR.checkValidValue((long) isoYear);
        return new Year(isoYear);
    }

    public static Year from(TemporalAccessor temporal) {
        if (temporal instanceof Year) {
            return (Year) temporal;
        }
        Objects.requireNonNull(temporal, "temporal");
        try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
                temporal = LocalDate.from(temporal);
            }
            return of(temporal.get(ChronoField.YEAR));
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Year from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public static Year parse(CharSequence text) {
        return parse(text, PARSER);
    }

    public static Year parse(CharSequence text, DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return (Year) formatter.parse(text, $$Lambda$1t2bycXU085eFZcwODXkbd0X4Bk.INSTANCE);
    }

    public static boolean isLeap(long year2) {
        return (3 & year2) == 0 && (year2 % 100 != 0 || year2 % 400 == 0);
    }

    private Year(int year2) {
        this.year = year2;
    }

    public int getValue() {
        return this.year;
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return field instanceof ChronoField ? field == ChronoField.YEAR || field == ChronoField.YEAR_OF_ERA || field == ChronoField.ERA : field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    public boolean isSupported(TemporalUnit unit) {
        return unit instanceof ChronoUnit ? unit == ChronoUnit.YEARS || unit == ChronoUnit.DECADES || unit == ChronoUnit.CENTURIES || unit == ChronoUnit.MILLENNIA || unit == ChronoUnit.ERAS : unit != null && unit.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (field != ChronoField.YEAR_OF_ERA) {
            return super.range(field);
        }
        return ValueRange.of(1, this.year <= 0 ? 1000000000 : 999999999);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        return range(field).checkValidIntValue(getLong(field), field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        int i2 = 1;
        if (i == 1) {
            int i3 = this.year;
            if (i3 < 1) {
                i3 = 1 - i3;
            }
            return (long) i3;
        } else if (i == 2) {
            return (long) this.year;
        } else {
            if (i == 3) {
                if (this.year < 1) {
                    i2 = 0;
                }
                return (long) i2;
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
        }
    }

    public boolean isLeap() {
        return isLeap((long) this.year);
    }

    public boolean isValidMonthDay(MonthDay monthDay) {
        return monthDay != null && monthDay.isValidYear(this.year);
    }

    public int length() {
        return isLeap() ? 366 : 365;
    }

    @Override // java.time.temporal.Temporal
    public Year with(TemporalAdjuster adjuster) {
        return (Year) adjuster.adjustInto(this);
    }

    @Override // java.time.temporal.Temporal
    public Year with(TemporalField field, long newValue) {
        if (!(field instanceof ChronoField)) {
            return (Year) field.adjustInto(this, newValue);
        }
        ChronoField f = (ChronoField) field;
        f.checkValidValue(newValue);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[f.ordinal()];
        if (i == 1) {
            return of((int) (this.year < 1 ? 1 - newValue : newValue));
        } else if (i == 2) {
            return of((int) newValue);
        } else {
            if (i == 3) {
                return getLong(ChronoField.ERA) == newValue ? this : of(1 - this.year);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
        }
    }

    @Override // java.time.temporal.Temporal
    public Year plus(TemporalAmount amountToAdd) {
        return (Year) amountToAdd.addTo(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.Year$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DECADES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.CENTURIES.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLENNIA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.ERAS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    @Override // java.time.temporal.Temporal
    public Year plus(long amountToAdd, TemporalUnit unit) {
        if (!(unit instanceof ChronoUnit)) {
            return (Year) unit.addTo(this, amountToAdd);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) unit).ordinal()];
        if (i == 1) {
            return plusYears(amountToAdd);
        }
        if (i == 2) {
            return plusYears(Math.multiplyExact(amountToAdd, 10));
        }
        if (i == 3) {
            return plusYears(Math.multiplyExact(amountToAdd, 100));
        }
        if (i == 4) {
            return plusYears(Math.multiplyExact(amountToAdd, 1000));
        }
        if (i == 5) {
            return with((TemporalField) ChronoField.ERA, Math.addExact(getLong(ChronoField.ERA), amountToAdd));
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + ((Object) unit));
    }

    public Year plusYears(long yearsToAdd) {
        if (yearsToAdd == 0) {
            return this;
        }
        return of(ChronoField.YEAR.checkValidIntValue(((long) this.year) + yearsToAdd));
    }

    @Override // java.time.temporal.Temporal
    public Year minus(TemporalAmount amountToSubtract) {
        return (Year) amountToSubtract.subtractFrom(this);
    }

    @Override // java.time.temporal.Temporal
    public Year minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1L, unit) : plus(-amountToSubtract, unit);
    }

    public Year minusYears(long yearsToSubtract) {
        return yearsToSubtract == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-yearsToSubtract);
    }

    @Override // java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        return query == TemporalQueries.chronology() ? (R) IsoChronology.INSTANCE : query == TemporalQueries.precision() ? (R) ChronoUnit.YEARS : (R) super.query(query);
    }

    @Override // java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        if (Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
            return temporal.with(ChronoField.YEAR, (long) this.year);
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }

    @Override // java.time.temporal.Temporal
    public long until(Temporal endExclusive, TemporalUnit unit) {
        Year end = from(endExclusive);
        if (!(unit instanceof ChronoUnit)) {
            return unit.between(this, end);
        }
        long yearsUntil = ((long) end.year) - ((long) this.year);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) unit).ordinal()];
        if (i == 1) {
            return yearsUntil;
        }
        if (i == 2) {
            return yearsUntil / 10;
        }
        if (i == 3) {
            return yearsUntil / 100;
        }
        if (i == 4) {
            return yearsUntil / 1000;
        }
        if (i == 5) {
            return end.getLong(ChronoField.ERA) - getLong(ChronoField.ERA);
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + ((Object) unit));
    }

    public String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    public LocalDate atDay(int dayOfYear) {
        return LocalDate.ofYearDay(this.year, dayOfYear);
    }

    public YearMonth atMonth(Month month) {
        return YearMonth.of(this.year, month);
    }

    public YearMonth atMonth(int month) {
        return YearMonth.of(this.year, month);
    }

    public LocalDate atMonthDay(MonthDay monthDay) {
        return monthDay.atYear(this.year);
    }

    public int compareTo(Year other) {
        return this.year - other.year;
    }

    public boolean isAfter(Year other) {
        return this.year > other.year;
    }

    public boolean isBefore(Year other) {
        return this.year < other.year;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Year)) {
            return false;
        }
        if (this.year == ((Year) obj).year) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.year;
    }

    public String toString() {
        return Integer.toString(this.year);
    }

    private Object writeReplace() {
        return new Ser((byte) 11, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        out.writeInt(this.year);
    }

    static Year readExternal(DataInput in) throws IOException {
        return of(in.readInt());
    }
}
