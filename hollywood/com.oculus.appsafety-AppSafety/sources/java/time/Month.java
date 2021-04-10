package java.time;

import android.icu.lang.UCharacter;
import java.net.HttpURLConnection;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
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

public enum Month implements TemporalAccessor, TemporalAdjuster {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;
    
    private static final Month[] ENUMS = values();

    public static Month of(int month) {
        if (month >= 1 && month <= 12) {
            return ENUMS[month - 1];
        }
        throw new DateTimeException("Invalid value for MonthOfYear: " + month);
    }

    public static Month from(TemporalAccessor temporal) {
        if (temporal instanceof Month) {
            return (Month) temporal;
        }
        try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
                temporal = LocalDate.from(temporal);
            }
            return of(temporal.get(ChronoField.MONTH_OF_YEAR));
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Month from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName(), ex);
        }
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getDisplayName(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(ChronoField.MONTH_OF_YEAR, style).toFormatter(locale).format(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return field instanceof ChronoField ? field == ChronoField.MONTH_OF_YEAR : field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        if (field == ChronoField.MONTH_OF_YEAR) {
            return field.range();
        }
        return super.range(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        if (field == ChronoField.MONTH_OF_YEAR) {
            return getValue();
        }
        return super.get(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (field == ChronoField.MONTH_OF_YEAR) {
            return (long) getValue();
        }
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    public Month plus(long months) {
        return ENUMS[(ordinal() + (((int) (months % 12)) + 12)) % 12];
    }

    public Month minus(long months) {
        return plus(-(months % 12));
    }

    public int length(boolean leapYear) {
        int i = AnonymousClass1.$SwitchMap$java$time$Month[ordinal()];
        if (i == 1) {
            return leapYear ? 29 : 28;
        }
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            return 30;
        }
        return 31;
    }

    public int minLength() {
        int i = AnonymousClass1.$SwitchMap$java$time$Month[ordinal()];
        if (i == 1) {
            return 28;
        }
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            return 30;
        }
        return 31;
    }

    public int maxLength() {
        int i = AnonymousClass1.$SwitchMap$java$time$Month[ordinal()];
        if (i == 1) {
            return 29;
        }
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            return 30;
        }
        return 31;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public int firstDayOfYear(boolean leapYear) {
        switch (this) {
            case FEBRUARY:
                return 32;
            case APRIL:
                return (leapYear ? 1 : 0) + 91;
            case JUNE:
                return leapYear + 152;
            case SEPTEMBER:
                return leapYear + 244;
            case NOVEMBER:
                return leapYear + HttpURLConnection.HTTP_USE_PROXY;
            case JANUARY:
                return 1;
            case MARCH:
                return leapYear + 60;
            case MAY:
                return leapYear + 121;
            case JULY:
                return leapYear + 182;
            case AUGUST:
                return leapYear + 213;
            case OCTOBER:
                return leapYear + UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID;
            default:
                return leapYear + 335;
        }
    }

    public Month firstMonthOfQuarter() {
        return ENUMS[(ordinal() / 3) * 3];
    }

    @Override // java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        return query == TemporalQueries.chronology() ? (R) IsoChronology.INSTANCE : query == TemporalQueries.precision() ? (R) ChronoUnit.MONTHS : (R) super.query(query);
    }

    @Override // java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        if (Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
            return temporal.with(ChronoField.MONTH_OF_YEAR, (long) getValue());
        }
        throw new DateTimeException("Adjustment only supported on ISO date-time");
    }
}
