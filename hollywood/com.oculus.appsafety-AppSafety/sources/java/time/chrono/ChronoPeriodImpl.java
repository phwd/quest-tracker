package java.time.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* access modifiers changed from: package-private */
public final class ChronoPeriodImpl implements ChronoPeriod, Serializable {
    private static final List<TemporalUnit> SUPPORTED_UNITS = Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS));
    private static final long serialVersionUID = 57387258289L;
    private final Chronology chrono;
    final int days;
    final int months;
    final int years;

    ChronoPeriodImpl(Chronology chrono2, int years2, int months2, int days2) {
        Objects.requireNonNull(chrono2, "chrono");
        this.chrono = chrono2;
        this.years = years2;
        this.months = months2;
        this.days = days2;
    }

    @Override // java.time.temporal.TemporalAmount, java.time.chrono.ChronoPeriod
    public long get(TemporalUnit unit) {
        if (unit == ChronoUnit.YEARS) {
            return (long) this.years;
        }
        if (unit == ChronoUnit.MONTHS) {
            return (long) this.months;
        }
        if (unit == ChronoUnit.DAYS) {
            return (long) this.days;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + ((Object) unit));
    }

    @Override // java.time.temporal.TemporalAmount, java.time.chrono.ChronoPeriod
    public List<TemporalUnit> getUnits() {
        return SUPPORTED_UNITS;
    }

    @Override // java.time.chrono.ChronoPeriod
    public Chronology getChronology() {
        return this.chrono;
    }

    @Override // java.time.chrono.ChronoPeriod
    public boolean isZero() {
        return this.years == 0 && this.months == 0 && this.days == 0;
    }

    @Override // java.time.chrono.ChronoPeriod
    public boolean isNegative() {
        return this.years < 0 || this.months < 0 || this.days < 0;
    }

    @Override // java.time.chrono.ChronoPeriod
    public ChronoPeriod plus(TemporalAmount amountToAdd) {
        ChronoPeriodImpl amount = validateAmount(amountToAdd);
        return new ChronoPeriodImpl(this.chrono, Math.addExact(this.years, amount.years), Math.addExact(this.months, amount.months), Math.addExact(this.days, amount.days));
    }

    @Override // java.time.chrono.ChronoPeriod
    public ChronoPeriod minus(TemporalAmount amountToSubtract) {
        ChronoPeriodImpl amount = validateAmount(amountToSubtract);
        return new ChronoPeriodImpl(this.chrono, Math.subtractExact(this.years, amount.years), Math.subtractExact(this.months, amount.months), Math.subtractExact(this.days, amount.days));
    }

    private ChronoPeriodImpl validateAmount(TemporalAmount amount) {
        Objects.requireNonNull(amount, "amount");
        if (amount instanceof ChronoPeriodImpl) {
            ChronoPeriodImpl period = (ChronoPeriodImpl) amount;
            if (this.chrono.equals(period.getChronology())) {
                return period;
            }
            throw new ClassCastException("Chronology mismatch, expected: " + this.chrono.getId() + ", actual: " + period.getChronology().getId());
        }
        throw new DateTimeException("Unable to obtain ChronoPeriod from TemporalAmount: " + ((Object) amount.getClass()));
    }

    @Override // java.time.chrono.ChronoPeriod
    public ChronoPeriod multipliedBy(int scalar) {
        if (isZero() || scalar == 1) {
            return this;
        }
        return new ChronoPeriodImpl(this.chrono, Math.multiplyExact(this.years, scalar), Math.multiplyExact(this.months, scalar), Math.multiplyExact(this.days, scalar));
    }

    @Override // java.time.chrono.ChronoPeriod
    public ChronoPeriod normalized() {
        long monthRange = monthRange();
        if (monthRange <= 0) {
            return this;
        }
        int i = this.years;
        int i2 = this.months;
        long totalMonths = (((long) i) * monthRange) + ((long) i2);
        long splitYears = totalMonths / monthRange;
        int splitMonths = (int) (totalMonths % monthRange);
        if (splitYears == ((long) i) && splitMonths == i2) {
            return this;
        }
        return new ChronoPeriodImpl(this.chrono, Math.toIntExact(splitYears), splitMonths, this.days);
    }

    private long monthRange() {
        ValueRange startRange = this.chrono.range(ChronoField.MONTH_OF_YEAR);
        if (!startRange.isFixed() || !startRange.isIntValue()) {
            return -1;
        }
        return (startRange.getMaximum() - startRange.getMinimum()) + 1;
    }

    @Override // java.time.temporal.TemporalAmount, java.time.chrono.ChronoPeriod
    public Temporal addTo(Temporal temporal) {
        validateChrono(temporal);
        if (this.months == 0) {
            int i = this.years;
            if (i != 0) {
                temporal = temporal.plus((long) i, ChronoUnit.YEARS);
            }
        } else {
            long monthRange = monthRange();
            if (monthRange > 0) {
                temporal = temporal.plus((((long) this.years) * monthRange) + ((long) this.months), ChronoUnit.MONTHS);
            } else {
                int i2 = this.years;
                if (i2 != 0) {
                    temporal = temporal.plus((long) i2, ChronoUnit.YEARS);
                }
                temporal = temporal.plus((long) this.months, ChronoUnit.MONTHS);
            }
        }
        int i3 = this.days;
        if (i3 != 0) {
            return temporal.plus((long) i3, ChronoUnit.DAYS);
        }
        return temporal;
    }

    @Override // java.time.temporal.TemporalAmount, java.time.chrono.ChronoPeriod
    public Temporal subtractFrom(Temporal temporal) {
        validateChrono(temporal);
        if (this.months == 0) {
            int i = this.years;
            if (i != 0) {
                temporal = temporal.minus((long) i, ChronoUnit.YEARS);
            }
        } else {
            long monthRange = monthRange();
            if (monthRange > 0) {
                temporal = temporal.minus((((long) this.years) * monthRange) + ((long) this.months), ChronoUnit.MONTHS);
            } else {
                int i2 = this.years;
                if (i2 != 0) {
                    temporal = temporal.minus((long) i2, ChronoUnit.YEARS);
                }
                temporal = temporal.minus((long) this.months, ChronoUnit.MONTHS);
            }
        }
        int i3 = this.days;
        if (i3 != 0) {
            return temporal.minus((long) i3, ChronoUnit.DAYS);
        }
        return temporal;
    }

    private void validateChrono(TemporalAccessor temporal) {
        Objects.requireNonNull(temporal, "temporal");
        Chronology temporalChrono = (Chronology) temporal.query(TemporalQueries.chronology());
        if (temporalChrono != null && !this.chrono.equals(temporalChrono)) {
            throw new DateTimeException("Chronology mismatch, expected: " + this.chrono.getId() + ", actual: " + temporalChrono.getId());
        }
    }

    @Override // java.time.chrono.ChronoPeriod
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoPeriodImpl)) {
            return false;
        }
        ChronoPeriodImpl other = (ChronoPeriodImpl) obj;
        if (this.years == other.years && this.months == other.months && this.days == other.days && this.chrono.equals(other.chrono)) {
            return true;
        }
        return false;
    }

    @Override // java.time.chrono.ChronoPeriod
    public int hashCode() {
        return ((this.years + Integer.rotateLeft(this.months, 8)) + Integer.rotateLeft(this.days, 16)) ^ this.chrono.hashCode();
    }

    @Override // java.time.chrono.ChronoPeriod
    public String toString() {
        if (isZero()) {
            return getChronology().toString() + " P0D";
        }
        StringBuilder buf = new StringBuilder();
        buf.append(getChronology().toString());
        buf.append(' ');
        buf.append('P');
        int i = this.years;
        if (i != 0) {
            buf.append(i);
            buf.append('Y');
        }
        int i2 = this.months;
        if (i2 != 0) {
            buf.append(i2);
            buf.append('M');
        }
        int i3 = this.days;
        if (i3 != 0) {
            buf.append(i3);
            buf.append('D');
        }
        return buf.toString();
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        return new Ser((byte) 9, this);
    }

    private void readObject(ObjectInputStream s) throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        out.writeUTF(this.chrono.getId());
        out.writeInt(this.years);
        out.writeInt(this.months);
        out.writeInt(this.days);
    }

    static ChronoPeriodImpl readExternal(DataInput in) throws IOException {
        return new ChronoPeriodImpl(Chronology.of(in.readUTF()), in.readInt(), in.readInt(), in.readInt());
    }
}
