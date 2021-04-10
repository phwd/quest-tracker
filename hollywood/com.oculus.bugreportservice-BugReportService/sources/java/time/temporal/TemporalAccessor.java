package java.time.temporal;

import java.time.DateTimeException;
import java.util.Objects;

public interface TemporalAccessor {
    long getLong(TemporalField temporalField);

    boolean isSupported(TemporalField temporalField);

    default ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            Objects.requireNonNull(temporalField, "field");
            return temporalField.rangeRefinedBy(this);
        } else if (isSupported(temporalField)) {
            return temporalField.range();
        } else {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
    }

    default int get(TemporalField temporalField) {
        ValueRange range = range(temporalField);
        if (range.isIntValue()) {
            long j = getLong(temporalField);
            if (range.isValidValue(j)) {
                return (int) j;
            }
            throw new DateTimeException("Invalid value for " + temporalField + " (valid values " + range + "): " + j);
        }
        throw new UnsupportedTemporalTypeException("Invalid field " + temporalField + " for get() method, use getLong() instead");
    }

    default Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.chronology() || temporalQuery == TemporalQueries.precision()) {
            return null;
        }
        return temporalQuery.queryFrom(this);
    }
}
