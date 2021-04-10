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
import java.util.Comparator;
import java.util.Objects;

public interface ChronoLocalDateTime<D extends ChronoLocalDate> extends Temporal, TemporalAdjuster, Comparable<ChronoLocalDateTime<?>> {
    ChronoZonedDateTime<D> atZone(ZoneId zoneId);

    boolean equals(Object obj);

    int hashCode();

    @Override // java.time.temporal.TemporalAccessor
    boolean isSupported(TemporalField temporalField);

    @Override // java.time.temporal.Temporal
    ChronoLocalDateTime<D> plus(long j, TemporalUnit temporalUnit);

    D toLocalDate();

    LocalTime toLocalTime();

    String toString();

    @Override // java.time.temporal.Temporal
    ChronoLocalDateTime<D> with(TemporalField temporalField, long j);

    static default Comparator<ChronoLocalDateTime<?>> timeLineOrder() {
        return AbstractChronology.DATE_TIME_ORDER;
    }

    static default ChronoLocalDateTime<?> from(TemporalAccessor temporal) {
        if (temporal instanceof ChronoLocalDateTime) {
            return (ChronoLocalDateTime) temporal;
        }
        Objects.requireNonNull(temporal, "temporal");
        Chronology chrono = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chrono != null) {
            return chrono.localDateTime(temporal);
        }
        throw new DateTimeException("Unable to obtain ChronoLocalDateTime from TemporalAccessor: " + ((Object) temporal.getClass()));
    }

    default Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    @Override // java.time.temporal.Temporal
    default boolean isSupported(TemporalUnit unit) {
        return unit instanceof ChronoUnit ? unit != ChronoUnit.FOREVER : unit != null && unit.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDateTime<D> with(TemporalAdjuster adjuster) {
        return ChronoLocalDateTimeImpl.ensureValid(getChronology(), super.with(adjuster));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDateTime<D> plus(TemporalAmount amount) {
        return ChronoLocalDateTimeImpl.ensureValid(getChronology(), super.plus(amount));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDateTime<D> minus(TemporalAmount amount) {
        return ChronoLocalDateTimeImpl.ensureValid(getChronology(), super.minus(amount));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDateTime<D> minus(long amountToSubtract, TemporalUnit unit) {
        return ChronoLocalDateTimeImpl.ensureValid(getChronology(), super.minus(amountToSubtract, unit));
    }

    @Override // java.time.temporal.TemporalAccessor
    default <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.zoneId() || query == TemporalQueries.zone() || query == TemporalQueries.offset()) {
            return null;
        }
        return query == TemporalQueries.localTime() ? (R) toLocalTime() : query == TemporalQueries.chronology() ? (R) getChronology() : query == TemporalQueries.precision() ? (R) ChronoUnit.NANOS : query.queryFrom(this);
    }

    @Override // java.time.temporal.TemporalAdjuster
    default Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toLocalDate().toEpochDay()).with(ChronoField.NANO_OF_DAY, toLocalTime().toNanoOfDay());
    }

    default String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    default Instant toInstant(ZoneOffset offset) {
        return Instant.ofEpochSecond(toEpochSecond(offset), (long) toLocalTime().getNano());
    }

    default long toEpochSecond(ZoneOffset offset) {
        Objects.requireNonNull(offset, "offset");
        return ((86400 * toLocalDate().toEpochDay()) + ((long) toLocalTime().toSecondOfDay())) - ((long) offset.getTotalSeconds());
    }

    default int compareTo(ChronoLocalDateTime<?> other) {
        int cmp = toLocalDate().compareTo(other.toLocalDate());
        if (cmp != 0) {
            return cmp;
        }
        int cmp2 = toLocalTime().compareTo(other.toLocalTime());
        if (cmp2 == 0) {
            return getChronology().compareTo(other.getChronology());
        }
        return cmp2;
    }

    default boolean isAfter(ChronoLocalDateTime<?> other) {
        long thisEpDay = toLocalDate().toEpochDay();
        long otherEpDay = other.toLocalDate().toEpochDay();
        return thisEpDay > otherEpDay || (thisEpDay == otherEpDay && toLocalTime().toNanoOfDay() > other.toLocalTime().toNanoOfDay());
    }

    default boolean isBefore(ChronoLocalDateTime<?> other) {
        long thisEpDay = toLocalDate().toEpochDay();
        long otherEpDay = other.toLocalDate().toEpochDay();
        return thisEpDay < otherEpDay || (thisEpDay == otherEpDay && toLocalTime().toNanoOfDay() < other.toLocalTime().toNanoOfDay());
    }

    default boolean isEqual(ChronoLocalDateTime<?> other) {
        return toLocalTime().toNanoOfDay() == other.toLocalTime().toNanoOfDay() && toLocalDate().toEpochDay() == other.toLocalDate().toEpochDay();
    }
}
