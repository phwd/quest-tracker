package java.time.chrono;

import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Locale;

public interface Era extends TemporalAccessor, TemporalAdjuster {
    int getValue();

    @Override // java.time.temporal.TemporalAccessor
    default boolean isSupported(TemporalField field) {
        return field instanceof ChronoField ? field == ChronoField.ERA : field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    default ValueRange range(TemporalField field) {
        return super.range(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    default int get(TemporalField field) {
        if (field == ChronoField.ERA) {
            return getValue();
        }
        return super.get(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    default long getLong(TemporalField field) {
        if (field == ChronoField.ERA) {
            return (long) getValue();
        }
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    @Override // java.time.temporal.TemporalAccessor
    default <R> R query(TemporalQuery<R> query) {
        return query == TemporalQueries.precision() ? (R) ChronoUnit.ERAS : (R) super.query(query);
    }

    @Override // java.time.temporal.TemporalAdjuster
    default Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.ERA, (long) getValue());
    }

    default String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(ChronoField.ERA, style).toFormatter(locale).format(this);
    }
}
