package java.time;

import android.support.v4.app.NotificationManagerCompat;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
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

public final class Instant implements Temporal, TemporalAdjuster, Comparable<Instant>, Serializable {
    public static final Instant EPOCH = new Instant(0, 0);
    public static final Instant MAX = ofEpochSecond(MAX_SECOND, 999999999);
    private static final long MAX_SECOND = 31556889864403199L;
    public static final Instant MIN = ofEpochSecond(MIN_SECOND, 0);
    private static final long MIN_SECOND = -31557014167219200L;
    private static final long serialVersionUID = -665713676816604388L;
    private final int nanos;
    private final long seconds;

    public static Instant now() {
        return Clock.systemUTC().instant();
    }

    public static Instant now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return clock.instant();
    }

    public static Instant ofEpochSecond(long epochSecond) {
        return create(epochSecond, 0);
    }

    public static Instant ofEpochSecond(long epochSecond, long nanoAdjustment) {
        return create(Math.addExact(epochSecond, Math.floorDiv(nanoAdjustment, 1000000000)), (int) Math.floorMod(nanoAdjustment, 1000000000));
    }

    public static Instant ofEpochMilli(long epochMilli) {
        return create(Math.floorDiv(epochMilli, 1000), 1000000 * ((int) Math.floorMod(epochMilli, 1000)));
    }

    public static Instant from(TemporalAccessor temporal) {
        if (temporal instanceof Instant) {
            return (Instant) temporal;
        }
        Objects.requireNonNull(temporal, "temporal");
        try {
            return ofEpochSecond(temporal.getLong(ChronoField.INSTANT_SECONDS), (long) temporal.get(ChronoField.NANO_OF_SECOND));
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Instant from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public static Instant parse(CharSequence text) {
        return (Instant) DateTimeFormatter.ISO_INSTANT.parse(text, $$Lambda$PTL8WkLA4o1z4zIUBjrvwi808w.INSTANCE);
    }

    private static Instant create(long seconds2, int nanoOfSecond) {
        if ((((long) nanoOfSecond) | seconds2) == 0) {
            return EPOCH;
        }
        if (seconds2 >= MIN_SECOND && seconds2 <= MAX_SECOND) {
            return new Instant(seconds2, nanoOfSecond);
        }
        throw new DateTimeException("Instant exceeds minimum or maximum instant");
    }

    private Instant(long epochSecond, int nanos2) {
        this.seconds = epochSecond;
        this.nanos = nanos2;
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return field instanceof ChronoField ? field == ChronoField.INSTANT_SECONDS || field == ChronoField.NANO_OF_SECOND || field == ChronoField.MICRO_OF_SECOND || field == ChronoField.MILLI_OF_SECOND : field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    public boolean isSupported(TemporalUnit unit) {
        return unit instanceof ChronoUnit ? unit.isTimeBased() || unit == ChronoUnit.DAYS : unit != null && unit.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        return super.range(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return range(field).checkValidIntValue(field.getFrom(this), field);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            return this.nanos;
        }
        if (i == 2) {
            return this.nanos / 1000;
        }
        if (i == 3) {
            return this.nanos / 1000000;
        }
        if (i == 4) {
            ChronoField.INSTANT_SECONDS.checkValidIntValue(this.seconds);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            return (long) this.nanos;
        }
        if (i == 2) {
            return (long) (this.nanos / 1000);
        }
        if (i == 3) {
            return (long) (this.nanos / 1000000);
        }
        if (i == 4) {
            return this.seconds;
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    public long getEpochSecond() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    @Override // java.time.temporal.Temporal
    public Instant with(TemporalAdjuster adjuster) {
        return (Instant) adjuster.adjustInto(this);
    }

    @Override // java.time.temporal.Temporal
    public Instant with(TemporalField field, long newValue) {
        if (!(field instanceof ChronoField)) {
            return (Instant) field.adjustInto(this, newValue);
        }
        ChronoField f = (ChronoField) field;
        f.checkValidValue(newValue);
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[f.ordinal()];
        if (i == 1) {
            return newValue != ((long) this.nanos) ? create(this.seconds, (int) newValue) : this;
        }
        if (i == 2) {
            int nval = ((int) newValue) * 1000;
            return nval != this.nanos ? create(this.seconds, nval) : this;
        } else if (i == 3) {
            int nval2 = ((int) newValue) * 1000000;
            return nval2 != this.nanos ? create(this.seconds, nval2) : this;
        } else if (i == 4) {
            return newValue != this.seconds ? create(newValue, this.nanos) : this;
        } else {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
        }
    }

    public Instant truncatedTo(TemporalUnit unit) {
        if (unit == ChronoUnit.NANOS) {
            return this;
        }
        Duration unitDur = unit.getDuration();
        if (unitDur.getSeconds() <= 86400) {
            long dur = unitDur.toNanos();
            if (86400000000000L % dur == 0) {
                long nod = ((this.seconds % 86400) * 1000000000) + ((long) this.nanos);
                return plusNanos(((nod / dur) * dur) - nod);
            }
            throw new UnsupportedTemporalTypeException("Unit must divide into a standard day without remainder");
        }
        throw new UnsupportedTemporalTypeException("Unit is too large to be used for truncation");
    }

    @Override // java.time.temporal.Temporal
    public Instant plus(TemporalAmount amountToAdd) {
        return (Instant) amountToAdd.addTo(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.Instant$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            $SwitchMap$java$time$temporal$ChronoUnit = new int[ChronoUnit.values().length];
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.HALF_DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DAYS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.NANO_OF_SECOND.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MICRO_OF_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MILLI_OF_SECOND.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    @Override // java.time.temporal.Temporal
    public Instant plus(long amountToAdd, TemporalUnit unit) {
        if (!(unit instanceof ChronoUnit)) {
            return (Instant) unit.addTo(this, amountToAdd);
        }
        switch ((ChronoUnit) unit) {
            case NANOS:
                return plusNanos(amountToAdd);
            case MICROS:
                return plus(amountToAdd / 1000000, (amountToAdd % 1000000) * 1000);
            case MILLIS:
                return plusMillis(amountToAdd);
            case SECONDS:
                return plusSeconds(amountToAdd);
            case MINUTES:
                return plusSeconds(Math.multiplyExact(amountToAdd, 60));
            case HOURS:
                return plusSeconds(Math.multiplyExact(amountToAdd, 3600));
            case HALF_DAYS:
                return plusSeconds(Math.multiplyExact(amountToAdd, 43200));
            case DAYS:
                return plusSeconds(Math.multiplyExact(amountToAdd, 86400));
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + ((Object) unit));
        }
    }

    public Instant plusSeconds(long secondsToAdd) {
        return plus(secondsToAdd, 0);
    }

    public Instant plusMillis(long millisToAdd) {
        return plus(millisToAdd / 1000, (millisToAdd % 1000) * 1000000);
    }

    public Instant plusNanos(long nanosToAdd) {
        return plus(0, nanosToAdd);
    }

    private Instant plus(long secondsToAdd, long nanosToAdd) {
        if ((secondsToAdd | nanosToAdd) == 0) {
            return this;
        }
        return ofEpochSecond(Math.addExact(Math.addExact(this.seconds, secondsToAdd), nanosToAdd / 1000000000), ((long) this.nanos) + (nanosToAdd % 1000000000));
    }

    @Override // java.time.temporal.Temporal
    public Instant minus(TemporalAmount amountToSubtract) {
        return (Instant) amountToSubtract.subtractFrom(this);
    }

    @Override // java.time.temporal.Temporal
    public Instant minus(long amountToSubtract, TemporalUnit unit) {
        return amountToSubtract == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1L, unit) : plus(-amountToSubtract, unit);
    }

    public Instant minusSeconds(long secondsToSubtract) {
        if (secondsToSubtract == Long.MIN_VALUE) {
            return plusSeconds(Long.MAX_VALUE).plusSeconds(1);
        }
        return plusSeconds(-secondsToSubtract);
    }

    public Instant minusMillis(long millisToSubtract) {
        if (millisToSubtract == Long.MIN_VALUE) {
            return plusMillis(Long.MAX_VALUE).plusMillis(1);
        }
        return plusMillis(-millisToSubtract);
    }

    public Instant minusNanos(long nanosToSubtract) {
        if (nanosToSubtract == Long.MIN_VALUE) {
            return plusNanos(Long.MAX_VALUE).plusNanos(1);
        }
        return plusNanos(-nanosToSubtract);
    }

    @Override // java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.precision()) {
            return (R) ChronoUnit.NANOS;
        }
        if (query == TemporalQueries.chronology() || query == TemporalQueries.zoneId() || query == TemporalQueries.zone() || query == TemporalQueries.offset() || query == TemporalQueries.localDate() || query == TemporalQueries.localTime()) {
            return null;
        }
        return query.queryFrom(this);
    }

    @Override // java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.INSTANT_SECONDS, this.seconds).with(ChronoField.NANO_OF_SECOND, (long) this.nanos);
    }

    @Override // java.time.temporal.Temporal
    public long until(Temporal endExclusive, TemporalUnit unit) {
        Instant end = from(endExclusive);
        if (!(unit instanceof ChronoUnit)) {
            return unit.between(this, end);
        }
        switch ((ChronoUnit) unit) {
            case NANOS:
                return nanosUntil(end);
            case MICROS:
                return nanosUntil(end) / 1000;
            case MILLIS:
                return Math.subtractExact(end.toEpochMilli(), toEpochMilli());
            case SECONDS:
                return secondsUntil(end);
            case MINUTES:
                return secondsUntil(end) / 60;
            case HOURS:
                return secondsUntil(end) / 3600;
            case HALF_DAYS:
                return secondsUntil(end) / 43200;
            case DAYS:
                return secondsUntil(end) / 86400;
            default:
                throw new UnsupportedTemporalTypeException("Unsupported unit: " + ((Object) unit));
        }
    }

    private long nanosUntil(Instant end) {
        return Math.addExact(Math.multiplyExact(Math.subtractExact(end.seconds, this.seconds), 1000000000), (long) (end.nanos - this.nanos));
    }

    private long secondsUntil(Instant end) {
        long secsDiff = Math.subtractExact(end.seconds, this.seconds);
        long nanosDiff = (long) (end.nanos - this.nanos);
        if (secsDiff > 0 && nanosDiff < 0) {
            return secsDiff - 1;
        }
        if (secsDiff >= 0 || nanosDiff <= 0) {
            return secsDiff;
        }
        return secsDiff + 1;
    }

    public OffsetDateTime atOffset(ZoneOffset offset) {
        return OffsetDateTime.ofInstant(this, offset);
    }

    public ZonedDateTime atZone(ZoneId zone) {
        return ZonedDateTime.ofInstant(this, zone);
    }

    public long toEpochMilli() {
        long j = this.seconds;
        if (j >= 0 || this.nanos <= 0) {
            return Math.addExact(Math.multiplyExact(this.seconds, 1000), (long) (this.nanos / 1000000));
        }
        return Math.addExact(Math.multiplyExact(j + 1, 1000), (long) ((this.nanos / 1000000) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED));
    }

    public int compareTo(Instant otherInstant) {
        int cmp = Long.compare(this.seconds, otherInstant.seconds);
        if (cmp != 0) {
            return cmp;
        }
        return this.nanos - otherInstant.nanos;
    }

    public boolean isAfter(Instant otherInstant) {
        return compareTo(otherInstant) > 0;
    }

    public boolean isBefore(Instant otherInstant) {
        return compareTo(otherInstant) < 0;
    }

    public boolean equals(Object otherInstant) {
        if (this == otherInstant) {
            return true;
        }
        if (!(otherInstant instanceof Instant)) {
            return false;
        }
        Instant other = (Instant) otherInstant;
        if (this.seconds == other.seconds && this.nanos == other.nanos) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.seconds;
        return ((int) (j ^ (j >>> 32))) + (this.nanos * 51);
    }

    public String toString() {
        return DateTimeFormatter.ISO_INSTANT.format(this);
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        out.writeLong(this.seconds);
        out.writeInt(this.nanos);
    }

    static Instant readExternal(DataInput in) throws IOException {
        return ofEpochSecond(in.readLong(), (long) in.readInt());
    }
}
