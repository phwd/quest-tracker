package java.time.chrono;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Objects;

/* access modifiers changed from: package-private */
public final class ChronoZonedDateTimeImpl<D extends ChronoLocalDate> implements ChronoZonedDateTime<D>, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final transient ChronoLocalDateTimeImpl<D> dateTime;
    private final transient ZoneOffset offset;
    private final transient ZoneId zone;

    static <R extends ChronoLocalDate> ChronoZonedDateTime<R> ofBest(ChronoLocalDateTimeImpl<R> localDateTime, ZoneId zone2, ZoneOffset preferredOffset) {
        ZoneOffset offset2;
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zone2, "zone");
        if (zone2 instanceof ZoneOffset) {
            return new ChronoZonedDateTimeImpl(localDateTime, (ZoneOffset) zone2, zone2);
        }
        ZoneRules rules = zone2.getRules();
        LocalDateTime isoLDT = LocalDateTime.from((TemporalAccessor) localDateTime);
        List<ZoneOffset> validOffsets = rules.getValidOffsets(isoLDT);
        if (validOffsets.size() == 1) {
            offset2 = validOffsets.get(0);
        } else if (validOffsets.size() == 0) {
            ZoneOffsetTransition trans = rules.getTransition(isoLDT);
            localDateTime = localDateTime.plusSeconds(trans.getDuration().getSeconds());
            offset2 = trans.getOffsetAfter();
        } else if (preferredOffset == null || !validOffsets.contains(preferredOffset)) {
            offset2 = validOffsets.get(0);
        } else {
            offset2 = preferredOffset;
        }
        Objects.requireNonNull(offset2, "offset");
        return new ChronoZonedDateTimeImpl(localDateTime, offset2, zone2);
    }

    static ChronoZonedDateTimeImpl<?> ofInstant(Chronology chrono, Instant instant, ZoneId zone2) {
        ZoneOffset offset2 = zone2.getRules().getOffset(instant);
        Objects.requireNonNull(offset2, "offset");
        return new ChronoZonedDateTimeImpl<>((ChronoLocalDateTimeImpl) chrono.localDateTime(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset2)), offset2, zone2);
    }

    private ChronoZonedDateTimeImpl<D> create(Instant instant, ZoneId zone2) {
        return (ChronoZonedDateTimeImpl<D>) ofInstant(getChronology(), instant, zone2);
    }

    static <R extends ChronoLocalDate> ChronoZonedDateTimeImpl<R> ensureValid(Chronology chrono, Temporal temporal) {
        ChronoZonedDateTimeImpl<R> other = (ChronoZonedDateTimeImpl) temporal;
        if (chrono.equals(other.getChronology())) {
            return other;
        }
        throw new ClassCastException("Chronology mismatch, required: " + chrono.getId() + ", actual: " + other.getChronology().getId());
    }

    private ChronoZonedDateTimeImpl(ChronoLocalDateTimeImpl<D> dateTime2, ZoneOffset offset2, ZoneId zone2) {
        this.dateTime = (ChronoLocalDateTimeImpl) Objects.requireNonNull(dateTime2, "dateTime");
        this.offset = (ZoneOffset) Objects.requireNonNull(offset2, "offset");
        this.zone = (ZoneId) Objects.requireNonNull(zone2, "zone");
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ZoneOffset getOffset() {
        return this.offset;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<D> withEarlierOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(LocalDateTime.from((TemporalAccessor) this));
        if (trans != null && trans.isOverlap()) {
            ZoneOffset earlierOffset = trans.getOffsetBefore();
            if (!earlierOffset.equals(this.offset)) {
                return new ChronoZonedDateTimeImpl(this.dateTime, earlierOffset, this.zone);
            }
        }
        return this;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<D> withLaterOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(LocalDateTime.from((TemporalAccessor) this));
        if (trans != null) {
            ZoneOffset offset2 = trans.getOffsetAfter();
            if (!offset2.equals(getOffset())) {
                return new ChronoZonedDateTimeImpl(this.dateTime, offset2, this.zone);
            }
        }
        return this;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoLocalDateTime<D> toLocalDateTime() {
        return this.dateTime;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ZoneId getZone() {
        return this.zone;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<D> withZoneSameLocal(ZoneId zone2) {
        return ofBest(this.dateTime, zone2, this.offset);
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public ChronoZonedDateTime<D> withZoneSameInstant(ZoneId zone2) {
        Objects.requireNonNull(zone2, "zone");
        return this.zone.equals(zone2) ? this : create(this.dateTime.toInstant(this.offset), zone2);
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return (field instanceof ChronoField) || (field != null && field.isSupportedBy(this));
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    public ChronoZonedDateTime<D> with(TemporalField field, long newValue) {
        if (!(field instanceof ChronoField)) {
            return ensureValid(getChronology(), field.adjustInto(this, newValue));
        }
        ChronoField f = (ChronoField) field;
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[f.ordinal()];
        if (i == 1) {
            return plus(newValue - toEpochSecond(), (TemporalUnit) ChronoUnit.SECONDS);
        }
        if (i != 2) {
            return ofBest(this.dateTime.with(field, newValue), this.zone, this.offset);
        }
        return create(this.dateTime.toInstant(ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue))), this.zone);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.ChronoZonedDateTimeImpl$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Override // java.time.chrono.ChronoZonedDateTime, java.time.chrono.ChronoZonedDateTime, java.time.temporal.Temporal
    public ChronoZonedDateTime<D> plus(long amountToAdd, TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return with((TemporalAdjuster) this.dateTime.plus(amountToAdd, unit));
        }
        return ensureValid(getChronology(), unit.addTo(this, amountToAdd));
    }

    @Override // java.time.temporal.Temporal
    public long until(Temporal endExclusive, TemporalUnit unit) {
        Objects.requireNonNull(endExclusive, "endExclusive");
        ChronoZonedDateTime<? extends ChronoLocalDate> zonedDateTime = getChronology().zonedDateTime(endExclusive);
        if (unit instanceof ChronoUnit) {
            return this.dateTime.until(zonedDateTime.withZoneSameInstant(this.offset).toLocalDateTime(), unit);
        }
        Objects.requireNonNull(unit, "unit");
        return unit.between(this, zonedDateTime);
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.dateTime);
        out.writeObject(this.offset);
        out.writeObject(this.zone);
    }

    /* JADX DEBUG: Type inference failed for r3v1. Raw type applied. Possible types: java.time.chrono.ChronoZonedDateTime<D extends java.time.chrono.ChronoLocalDate>, java.time.chrono.ChronoZonedDateTime<?> */
    static ChronoZonedDateTime<?> readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        return (ChronoZonedDateTime<D>) ((ChronoLocalDateTime) in.readObject()).atZone((ZoneOffset) in.readObject()).withZoneSameLocal((ZoneId) in.readObject());
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoZonedDateTime)) {
            return false;
        }
        if (compareTo((ChronoZonedDateTime) obj) == 0) {
            return true;
        }
        return false;
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public int hashCode() {
        return (toLocalDateTime().hashCode() ^ getOffset().hashCode()) ^ Integer.rotateLeft(getZone().hashCode(), 3);
    }

    @Override // java.time.chrono.ChronoZonedDateTime
    public String toString() {
        String str = toLocalDateTime().toString() + getOffset().toString();
        if (getOffset() == getZone()) {
            return str;
        }
        return str + '[' + getZone().toString() + ']';
    }
}
