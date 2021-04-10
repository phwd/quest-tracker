package java.time;

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

public enum DayOfWeek implements TemporalAccessor, TemporalAdjuster {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
    
    private static final DayOfWeek[] ENUMS = values();

    public static DayOfWeek of(int dayOfWeek) {
        if (dayOfWeek >= 1 && dayOfWeek <= 7) {
            return ENUMS[dayOfWeek - 1];
        }
        throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
    }

    public static DayOfWeek from(TemporalAccessor temporal) {
        if (temporal instanceof DayOfWeek) {
            return (DayOfWeek) temporal;
        }
        try {
            return of(temporal.get(ChronoField.DAY_OF_WEEK));
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain DayOfWeek from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(ChronoField.DAY_OF_WEEK, style).toFormatter(locale).format(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return field instanceof ChronoField ? field == ChronoField.DAY_OF_WEEK : field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (field == ChronoField.DAY_OF_WEEK) {
            return field.range();
        }
        return super.range(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        if (field == ChronoField.DAY_OF_WEEK) {
            return getValue();
        }
        return super.get(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (field == ChronoField.DAY_OF_WEEK) {
            return (long) getValue();
        }
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    public DayOfWeek plus(long days) {
        return ENUMS[(ordinal() + (((int) (days % 7)) + 7)) % 7];
    }

    public DayOfWeek minus(long days) {
        return plus(-(days % 7));
    }

    @Override // java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        return query == TemporalQueries.precision() ? (R) ChronoUnit.DAYS : (R) super.query(query);
    }

    @Override // java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.DAY_OF_WEEK, (long) getValue());
    }
}
