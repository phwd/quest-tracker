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

public final class MinguoDate extends ChronoLocalDateImpl<MinguoDate> implements ChronoLocalDate, Serializable {
    private static final long serialVersionUID = 1300372329181994526L;
    private final transient LocalDate isoDate;

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    public static MinguoDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static MinguoDate now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    public static MinguoDate now(Clock clock) {
        return new MinguoDate(LocalDate.now(clock));
    }

    public static MinguoDate of(int prolepticYear, int month, int dayOfMonth) {
        return new MinguoDate(LocalDate.of(prolepticYear + 1911, month, dayOfMonth));
    }

    public static MinguoDate from(TemporalAccessor temporal) {
        return MinguoChronology.INSTANCE.date(temporal);
    }

    MinguoDate(LocalDate isoDate2) {
        Objects.requireNonNull(isoDate2, "isoDate");
        this.isoDate = isoDate2;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public MinguoChronology getChronology() {
        return MinguoChronology.INSTANCE;
    }

    @Override // java.time.chrono.ChronoLocalDate
    public MinguoEra getEra() {
        return getProlepticYear() >= 1 ? MinguoEra.ROC : MinguoEra.BEFORE_ROC;
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
            return ValueRange.of(1, getProlepticYear() <= 0 ? (-range.getMinimum()) + 1 + 1911 : range.getMaximum() - 1911);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.MinguoDate$1  reason: invalid class name */
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
        return this.isoDate.getYear() - 1911;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r1 != 7) goto L_0x0056;
     */
    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.time.chrono.MinguoDate with(java.time.temporal.TemporalField r8, long r9) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.MinguoDate.with(java.time.temporal.TemporalField, long):java.time.chrono.MinguoDate");
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public MinguoDate with(TemporalAdjuster adjuster) {
        return (MinguoDate) super.with(adjuster);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public MinguoDate plus(TemporalAmount amount) {
        return (MinguoDate) super.plus(amount);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public MinguoDate minus(TemporalAmount amount) {
        return (MinguoDate) super.minus(amount);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate plusYears(long years) {
        return with(this.isoDate.plusYears(years));
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate plusMonths(long months) {
        return with(this.isoDate.plusMonths(months));
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate plusWeeks(long weeksToAdd) {
        return (MinguoDate) super.plusWeeks(weeksToAdd);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate plusDays(long days) {
        return with(this.isoDate.plusDays(days));
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public MinguoDate plus(long amountToAdd, TemporalUnit unit) {
        return (MinguoDate) super.plus(amountToAdd, unit);
    }

    @Override // java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDate, java.time.chrono.ChronoLocalDateImpl, java.time.chrono.ChronoLocalDateImpl, java.time.temporal.Temporal
    public MinguoDate minus(long amountToAdd, TemporalUnit unit) {
        return (MinguoDate) super.minus(amountToAdd, unit);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate minusYears(long yearsToSubtract) {
        return (MinguoDate) super.minusYears(yearsToSubtract);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate minusMonths(long monthsToSubtract) {
        return (MinguoDate) super.minusMonths(monthsToSubtract);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate minusWeeks(long weeksToSubtract) {
        return (MinguoDate) super.minusWeeks(weeksToSubtract);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.ChronoLocalDateImpl
    public MinguoDate minusDays(long daysToSubtract) {
        return (MinguoDate) super.minusDays(daysToSubtract);
    }

    private MinguoDate with(LocalDate newDate) {
        return newDate.equals(this.isoDate) ? this : new MinguoDate(newDate);
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.time.chrono.ChronoLocalDateTime<?>, java.time.chrono.ChronoLocalDateTime<java.time.chrono.MinguoDate> */
    @Override // java.time.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<MinguoDate> atTime(LocalTime localTime) {
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
        if (obj instanceof MinguoDate) {
            return this.isoDate.equals(((MinguoDate) obj).isoDate);
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
        return new Ser((byte) 7, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        out.writeInt(get(ChronoField.YEAR));
        out.writeByte(get(ChronoField.MONTH_OF_YEAR));
        out.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    static MinguoDate readExternal(DataInput in) throws IOException {
        return MinguoChronology.INSTANCE.date(in.readInt(), in.readByte(), in.readByte());
    }
}
