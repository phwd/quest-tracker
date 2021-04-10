package java.time.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Objects;

public final class ThaiBuddhistDate extends ChronoLocalDateImpl<ThaiBuddhistDate> implements ChronoLocalDate, Serializable {
    private static final long serialVersionUID = -8722293800195731463L;
    private final transient LocalDate isoDate;

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    public static ThaiBuddhistDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static ThaiBuddhistDate now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    public static ThaiBuddhistDate now(Clock clock) {
        return new ThaiBuddhistDate(LocalDate.now(clock));
    }

    public static ThaiBuddhistDate of(int prolepticYear, int month, int dayOfMonth) {
        return new ThaiBuddhistDate(LocalDate.of(prolepticYear - 543, month, dayOfMonth));
    }

    public static ThaiBuddhistDate from(TemporalAccessor temporal) {
        return ThaiBuddhistChronology.INSTANCE.date(temporal);
    }

    ThaiBuddhistDate(LocalDate isoDate2) {
        Objects.requireNonNull(isoDate2, "isoDate");
        this.isoDate = isoDate2;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public ThaiBuddhistChronology getChronology() {
        return ThaiBuddhistChronology.INSTANCE;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public ThaiBuddhistEra getEra() {
        return getProlepticYear() >= 1 ? ThaiBuddhistEra.BE : ThaiBuddhistEra.BEFORE_BE;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.rangeRefinedBy(this);
        }
        if (isSupported(field)) {
            ChronoField f = (ChronoField) field;
            int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[f.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                return this.isoDate.range(field);
            }
            if (i != 4) {
                return getChronology().range(f);
            }
            ValueRange range = ChronoField.YEAR.range();
            return ValueRange.of(1, getProlepticYear() <= 0 ? (-(range.getMinimum() + 543)) + 1 : 543 + range.getMaximum());
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.ThaiBuddhistDate$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        int i2 = 1;
        if (i == 4) {
            int prolepticYear = getProlepticYear();
            return (long) (prolepticYear >= 1 ? prolepticYear : 1 - prolepticYear);
        } else if (i == 5) {
            return getProlepticMonth();
        } else {
            if (i == 6) {
                return (long) getProlepticYear();
            }
            if (i != 7) {
                return this.isoDate.getLong(field);
            }
            if (getProlepticYear() < 1) {
                i2 = 0;
            }
            return (long) i2;
        }
    }

    private long getProlepticMonth() {
        return ((((long) getProlepticYear()) * 12) + ((long) this.isoDate.getMonthValue())) - 1;
    }

    private int getProlepticYear() {
        return this.isoDate.getYear() + 543;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r1 != 7) goto L_0x0056;
     */
    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.ThaiBuddhistDate with(java.time.temporal.TemporalField r8, long r9) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.ThaiBuddhistDate.with(java.time.temporal.TemporalField, long):java.time.chrono.ThaiBuddhistDate");
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public ThaiBuddhistDate with(TemporalAdjuster adjuster) {
        return (ThaiBuddhistDate) super.with(adjuster);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public ThaiBuddhistDate plus(TemporalAmount amount) {
        return (ThaiBuddhistDate) super.plus(amount);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public ThaiBuddhistDate minus(TemporalAmount amount) {
        return (ThaiBuddhistDate) super.minus(amount);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate plusYears(long years) {
        return with(this.isoDate.plusYears(years));
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate plusMonths(long months) {
        return with(this.isoDate.plusMonths(months));
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate plusWeeks(long weeksToAdd) {
        return (ThaiBuddhistDate) super.plusWeeks(weeksToAdd);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate plusDays(long days) {
        return with(this.isoDate.plusDays(days));
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public ThaiBuddhistDate plus(long amountToAdd, TemporalUnit unit) {
        return (ThaiBuddhistDate) super.plus(amountToAdd, unit);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public ThaiBuddhistDate minus(long amountToAdd, TemporalUnit unit) {
        return (ThaiBuddhistDate) super.minus(amountToAdd, unit);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate minusYears(long yearsToSubtract) {
        return (ThaiBuddhistDate) super.minusYears(yearsToSubtract);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate minusMonths(long monthsToSubtract) {
        return (ThaiBuddhistDate) super.minusMonths(monthsToSubtract);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate minusWeeks(long weeksToSubtract) {
        return (ThaiBuddhistDate) super.minusWeeks(weeksToSubtract);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public ThaiBuddhistDate minusDays(long daysToSubtract) {
        return (ThaiBuddhistDate) super.minusDays(daysToSubtract);
    }

    private ThaiBuddhistDate with(LocalDate newDate) {
        return newDate.equals(this.isoDate) ? this : new ThaiBuddhistDate(newDate);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoLocalDateTime<?>, java.time.chrono.ChronoLocalDateTime<java.time.chrono.ThaiBuddhistDate> */
    @Override // java.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<ThaiBuddhistDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override // java.time.chrono.ChronoLocalDate
    public ChronoPeriod until(ChronoLocalDate endDate) {
        Period period = this.isoDate.until(endDate);
        return getChronology().period(period.getYears(), period.getMonths(), period.getDays());
    }

    @Override // java.time.chrono.ChronoLocalDate
    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ThaiBuddhistDate) {
            return this.isoDate.equals(((ThaiBuddhistDate) obj).isoDate);
        }
        return false;
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl
    public int hashCode() {
        return getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 8, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        out.writeInt(get(ChronoField.YEAR));
        out.writeByte(get(ChronoField.MONTH_OF_YEAR));
        out.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    static ThaiBuddhistDate readExternal(DataInput in) throws IOException {
        return ThaiBuddhistChronology.INSTANCE.date(in.readInt(), in.readByte(), in.readByte());
    }
}
