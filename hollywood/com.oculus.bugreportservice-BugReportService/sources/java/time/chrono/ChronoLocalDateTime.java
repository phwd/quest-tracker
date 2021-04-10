package java.time.chrono;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

public interface ChronoLocalDateTime extends Temporal, TemporalAdjuster, Comparable {
    int hashCode();

    ChronoLocalDate toLocalDate();

    LocalTime toLocalTime();

    String toString();

    default Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    @Override // java.time.temporal.Temporal
    default ChronoLocalDateTime minus(long j, TemporalUnit temporalUnit) {
        return ChronoLocalDateTimeImpl.ensureValid(getChronology(), super.minus(j, temporalUnit));
    }

    @Override // java.time.temporal.TemporalAccessor
    default Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset()) {
            return null;
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return toLocalTime();
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return ChronoUnit.NANOS;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // java.time.temporal.TemporalAdjuster
    default Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toLocalDate().toEpochDay()).with(ChronoField.NANO_OF_DAY, toLocalTime().toNanoOfDay());
    }

    default Instant toInstant(ZoneOffset zoneOffset) {
        return Instant.ofEpochSecond(toEpochSecond(zoneOffset), (long) toLocalTime().getNano());
    }

    default long toEpochSecond(ZoneOffset zoneOffset) {
        Objects.requireNonNull(zoneOffset, "offset");
        return ((toLocalDate().toEpochDay() * 86400) + ((long) toLocalTime().toSecondOfDay())) - ((long) zoneOffset.getTotalSeconds());
    }

    default int compareTo(ChronoLocalDateTime chronoLocalDateTime) {
        int compareTo = toLocalDate().compareTo(chronoLocalDateTime.toLocalDate());
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = toLocalTime().compareTo(chronoLocalDateTime.toLocalTime());
        return compareTo2 == 0 ? getChronology().compareTo(chronoLocalDateTime.getChronology()) : compareTo2;
    }

    default boolean isAfter(ChronoLocalDateTime chronoLocalDateTime) {
        int i = (toLocalDate().toEpochDay() > chronoLocalDateTime.toLocalDate().toEpochDay() ? 1 : (toLocalDate().toEpochDay() == chronoLocalDateTime.toLocalDate().toEpochDay() ? 0 : -1));
        return i > 0 || (i == 0 && toLocalTime().toNanoOfDay() > chronoLocalDateTime.toLocalTime().toNanoOfDay());
    }

    default boolean isBefore(ChronoLocalDateTime chronoLocalDateTime) {
        int i = (toLocalDate().toEpochDay() > chronoLocalDateTime.toLocalDate().toEpochDay() ? 1 : (toLocalDate().toEpochDay() == chronoLocalDateTime.toLocalDate().toEpochDay() ? 0 : -1));
        return i < 0 || (i == 0 && toLocalTime().toNanoOfDay() < chronoLocalDateTime.toLocalTime().toNanoOfDay());
    }
}
