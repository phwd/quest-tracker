package java.time.chrono;

import java.time.DateTimeException;
import java.time.LocalTime;
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
import java.util.Comparator;
import java.util.Objects;

public interface ChronoLocalDate extends Temporal, TemporalAdjuster, Comparable<ChronoLocalDate> {
    boolean equals(Object obj);

    Chronology getChronology();

    int hashCode();

    int lengthOfMonth();

    String toString();

    @Override // java.time.temporal.Temporal
    long until(Temporal temporal, TemporalUnit temporalUnit);

    ChronoPeriod until(ChronoLocalDate chronoLocalDate);

    static default Comparator<ChronoLocalDate> timeLineOrder() {
        return AbstractChronology.DATE_ORDER;
    }

    static default ChronoLocalDate from(TemporalAccessor temporal) {
        if (temporal instanceof ChronoLocalDate) {
            return (ChronoLocalDate) temporal;
        }
        Objects.requireNonNull(temporal, "temporal");
        Chronology chrono = (Chronology) temporal.query(TemporalQueries.chronology());
        if (chrono != null) {
            return chrono.date(temporal);
        }
        throw new DateTimeException("Unable to obtain ChronoLocalDate from TemporalAccessor: " + ((Object) temporal.getClass()));
    }

    default Era getEra() {
        return getChronology().eraOf(get(ChronoField.ERA));
    }

    default boolean isLeapYear() {
        return getChronology().isLeapYear(getLong(ChronoField.YEAR));
    }

    default int lengthOfYear() {
        return isLeapYear() ? 366 : 365;
    }

    @Override // java.time.temporal.TemporalAccessor
    default boolean isSupported(TemporalField field) {
        if (field instanceof ChronoField) {
            return field.isDateBased();
        }
        return field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    default boolean isSupported(TemporalUnit unit) {
        if (unit instanceof ChronoUnit) {
            return unit.isDateBased();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate with(TemporalAdjuster adjuster) {
        return ChronoLocalDateImpl.ensureValid(getChronology(), super.with(adjuster));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate with(TemporalField field, long newValue) {
        if (!(field instanceof ChronoField)) {
            return ChronoLocalDateImpl.ensureValid(getChronology(), field.adjustInto(this, newValue));
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate plus(TemporalAmount amount) {
        return ChronoLocalDateImpl.ensureValid(getChronology(), super.plus(amount));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate plus(long amountToAdd, TemporalUnit unit) {
        if (!(unit instanceof ChronoUnit)) {
            return ChronoLocalDateImpl.ensureValid(getChronology(), unit.addTo(this, amountToAdd));
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + ((Object) unit));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate minus(TemporalAmount amount) {
        return ChronoLocalDateImpl.ensureValid(getChronology(), super.minus(amount));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate minus(long amountToSubtract, TemporalUnit unit) {
        return ChronoLocalDateImpl.ensureValid(getChronology(), super.minus(amountToSubtract, unit));
    }

    @Override // java.time.temporal.TemporalAccessor
    default <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.zoneId() || query == TemporalQueries.zone() || query == TemporalQueries.offset() || query == TemporalQueries.localTime()) {
            return null;
        }
        return query == TemporalQueries.chronology() ? (R) getChronology() : query == TemporalQueries.precision() ? (R) ChronoUnit.DAYS : query.queryFrom(this);
    }

    @Override // java.time.temporal.TemporalAdjuster
    default Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toEpochDay());
    }

    default String format(DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.format(this);
    }

    default ChronoLocalDateTime<?> atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    default long toEpochDay() {
        return getLong(ChronoField.EPOCH_DAY);
    }

    default int compareTo(ChronoLocalDate other) {
        int cmp = Long.compare(toEpochDay(), other.toEpochDay());
        if (cmp == 0) {
            return getChronology().compareTo(other.getChronology());
        }
        return cmp;
    }

    default boolean isAfter(ChronoLocalDate other) {
        return toEpochDay() > other.toEpochDay();
    }

    default boolean isBefore(ChronoLocalDate other) {
        return toEpochDay() < other.toEpochDay();
    }

    default boolean isEqual(ChronoLocalDate other) {
        return toEpochDay() == other.toEpochDay();
    }
}
