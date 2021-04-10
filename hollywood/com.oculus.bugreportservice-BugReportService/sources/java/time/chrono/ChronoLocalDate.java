package java.time.chrono;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public interface ChronoLocalDate extends Temporal, TemporalAdjuster, Comparable {
    Chronology getChronology();

    int hashCode();

    String toString();

    default Era getEra() {
        return getChronology().eraOf(get(ChronoField.ERA));
    }

    @Override // java.time.temporal.TemporalAccessor
    default boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isDateBased();
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return ChronoLocalDateImpl.ensureValid(getChronology(), super.with(temporalAdjuster));
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return ChronoLocalDateImpl.ensureValid(getChronology(), temporalField.adjustInto(this, j));
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return ChronoLocalDateImpl.ensureValid(getChronology(), temporalUnit.addTo(this, j));
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return ChronoLocalDateImpl.ensureValid(getChronology(), super.minus(j, temporalUnit));
    }

    @Override // java.time.temporal.TemporalAccessor
    default Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset() || temporalQuery == TemporalQueries.localTime()) {
            return null;
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return ChronoUnit.DAYS;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // java.time.temporal.TemporalAdjuster
    default Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toEpochDay());
    }

    default ChronoLocalDateTime atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    default long toEpochDay() {
        return getLong(ChronoField.EPOCH_DAY);
    }

    default int compareTo(ChronoLocalDate chronoLocalDate) {
        int compare = Long.compare(toEpochDay(), chronoLocalDate.toEpochDay());
        return compare == 0 ? getChronology().compareTo(chronoLocalDate.getChronology()) : compare;
    }

    default boolean isBefore(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() < chronoLocalDate.toEpochDay();
    }
}
