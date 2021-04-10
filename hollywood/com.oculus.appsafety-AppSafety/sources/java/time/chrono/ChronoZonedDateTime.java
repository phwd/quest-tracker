package java.time.chrono;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
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
import java.util.Comparator;
import java.util.Objects;

public interface ChronoZonedDateTime<D extends ChronoLocalDate> extends Temporal, Comparable<ChronoZonedDateTime<?>> {
    boolean equals(Object obj);

    ZoneOffset getOffset();

    ZoneId getZone();

    int hashCode();

    @Override // java.time.temporal.TemporalAccessor
    boolean isSupported(TemporalField temporalField);

    @Override // java.time.temporal.Temporal
    ChronoZonedDateTime<D> plus(long j, TemporalUnit temporalUnit);

    ChronoLocalDateTime<D> toLocalDateTime();

    String toString();

    @Override // java.time.temporal.Temporal
    ChronoZonedDateTime<D> with(TemporalField temporalField, long j);

    ChronoZonedDateTime<D> withEarlierOffsetAtOverlap();

    ChronoZonedDateTime<D> withLaterOffsetAtOverlap();

    ChronoZonedDateTime<D> withZoneSameInstant(ZoneId zoneId);

    ChronoZonedDateTime<D> withZoneSameLocal(ZoneId zoneId);

    static default Comparator<ChronoZonedDateTime<?>> timeLineOrder() {
        return AbstractChronology.INSTANT_ORDER;
    }

    static default ChronoZonedDateTime<?> from(TemporalAccessor temporal) {
        if (temporal instanceof ChronoZonedDateTime) {
            return (ChronoZonedDateTime) temporal;
        }
        Objects.requireNonNull(temporal, "temporal");
        Chronology chrono = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chrono != null) {
            return chrono.zonedDateTime(temporal);
        }
        throw new DateTimeException("Unable to obtain ChronoZonedDateTime from TemporalAccessor: " + ((Object) temporal.getClass()));
    }

    @Override // java.time.temporal.TemporalAccessor
    default ValueRange range(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.rangeRefinedBy(this);
        }
        if (field == ChronoField.INSTANT_SECONDS || field == ChronoField.OFFSET_SECONDS) {
            return field.range();
        }
        return toLocalDateTime().range(field);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.ChronoZonedDateTime$1  reason: invalid class name */
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

    @Override // java.time.temporal.TemporalAccessor
    default int get(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return super.get(field);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        } else if (i != 2) {
            return toLocalDateTime().get(field);
        } else {
            return getOffset().getTotalSeconds();
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    default long getLong(TemporalField field) {
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            return toEpochSecond();
        }
        if (i != 2) {
            return toLocalDateTime().getLong(field);
        }
        return (long) getOffset().getTotalSeconds();
    }

    default D toLocalDate() {
        return toLocalDateTime().toLocalDate();
    }

    default LocalTime toLocalTime() {
        return toLocalDateTime().toLocalTime();
    }

    default Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    @Override // java.time.temporal.Temporal
    default boolean isSupported(TemporalUnit unit) {
        return unit instanceof ChronoUnit ? unit != ChronoUnit.FOREVER : unit != null && unit.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    default ChronoZonedDateTime<D> with(TemporalAdjuster adjuster) {
        return ChronoZonedDateTimeImpl.ensureValid(getChronology(), super.with(adjuster));
    }

    @Override // java.time.temporal.Temporal
    default ChronoZonedDateTime<D> plus(TemporalAmount amount) {
        return ChronoZonedDateTimeImpl.ensureValid(getChronology(), super.plus(amount));
    }

    @Override // java.time.temporal.Temporal
    default ChronoZonedDateTime<D> minus(TemporalAmount amount) {
        return ChronoZonedDateTimeImpl.ensureValid(getChronology(), super.minus(amount));
    }

    @Override // java.time.temporal.Temporal
    default ChronoZonedDateTime<D> minus(long amountToSubtract, TemporalUnit unit) {
        return ChronoZonedDateTimeImpl.ensureValid(getChronology(), super.minus(amountToSubtract, unit));
    }

    @Override // java.time.temporal.TemporalAccessor
    default <R> R query(TemporalQuery<R> query) {
        return (query == TemporalQueries.zone() || query == TemporalQueries.zoneId()) ? (R) getZone() : query == TemporalQueries.offset() ? (R) getOffset() : query == TemporalQueries.localTime() ? (R) toLocalTime() : query == TemporalQueries.chronology() ? (R) getChronology() : query == TemporalQueries.precision() ? (R) ChronoUnit.NANOS : query.queryFrom(this);
    }

    default String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    default Instant toInstant() {
        return Instant.ofEpochSecond(toEpochSecond(), (long) toLocalTime().getNano());
    }

    default long toEpochSecond() {
        return ((86400 * toLocalDate().toEpochDay()) + ((long) toLocalTime().toSecondOfDay())) - ((long) getOffset().getTotalSeconds());
    }

    default int compareTo(ChronoZonedDateTime<?> other) {
        int cmp = Long.compare(toEpochSecond(), other.toEpochSecond());
        if (cmp != 0) {
            return cmp;
        }
        int cmp2 = toLocalTime().getNano() - other.toLocalTime().getNano();
        if (cmp2 != 0) {
            return cmp2;
        }
        int cmp3 = toLocalDateTime().compareTo(other.toLocalDateTime());
        if (cmp3 != 0) {
            return cmp3;
        }
        int cmp4 = getZone().getId().compareTo(other.getZone().getId());
        if (cmp4 == 0) {
            return getChronology().compareTo(other.getChronology());
        }
        return cmp4;
    }

    default boolean isBefore(ChronoZonedDateTime<?> other) {
        long thisEpochSec = toEpochSecond();
        long otherEpochSec = other.toEpochSecond();
        return thisEpochSec < otherEpochSec || (thisEpochSec == otherEpochSec && toLocalTime().getNano() < other.toLocalTime().getNano());
    }

    default boolean isAfter(ChronoZonedDateTime<?> other) {
        long thisEpochSec = toEpochSecond();
        long otherEpochSec = other.toEpochSecond();
        return thisEpochSec > otherEpochSec || (thisEpochSec == otherEpochSec && toLocalTime().getNano() > other.toLocalTime().getNano());
    }

    default boolean isEqual(ChronoZonedDateTime<?> other) {
        return toEpochSecond() == other.toEpochSecond() && toLocalTime().getNano() == other.toLocalTime().getNano();
    }
}
