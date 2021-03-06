package java.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class IsoChronology extends AbstractChronology implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    private IsoChronology() {
    }

    @Override // java.time.chrono.Chronology
    public String getId() {
        return "ISO";
    }

    @Override // java.time.chrono.Chronology
    public String getCalendarType() {
        return "iso8601";
    }

    @Override // java.time.chrono.Chronology
    public LocalDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return date(prolepticYear(era, yearOfEra), month, dayOfMonth);
    }

    @Override // java.time.chrono.Chronology
    public LocalDate date(int prolepticYear, int month, int dayOfMonth) {
        return LocalDate.of(prolepticYear, month, dayOfMonth);
    }

    @Override // java.time.chrono.Chronology
    public LocalDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return dateYearDay(prolepticYear(era, yearOfEra), dayOfYear);
    }

    @Override // java.time.chrono.Chronology
    public LocalDate dateYearDay(int prolepticYear, int dayOfYear) {
        return LocalDate.ofYearDay(prolepticYear, dayOfYear);
    }

    @Override // java.time.chrono.Chronology
    public LocalDate dateEpochDay(long epochDay) {
        return LocalDate.ofEpochDay(epochDay);
    }

    @Override // java.time.chrono.Chronology
    public LocalDate date(TemporalAccessor temporal) {
        return LocalDate.from(temporal);
    }

    @Override // java.time.chrono.Chronology
    public LocalDateTime localDateTime(TemporalAccessor temporal) {
        return LocalDateTime.from(temporal);
    }

    @Override // java.time.chrono.Chronology
    public ZonedDateTime zonedDateTime(TemporalAccessor temporal) {
        return ZonedDateTime.from(temporal);
    }

    @Override // java.time.chrono.Chronology
    public ZonedDateTime zonedDateTime(Instant instant, ZoneId zone) {
        return ZonedDateTime.ofInstant(instant, zone);
    }

    @Override // java.time.chrono.Chronology
    public LocalDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    @Override // java.time.chrono.Chronology
    public LocalDate dateNow(ZoneId zone) {
        return dateNow(Clock.system(zone));
    }

    @Override // java.time.chrono.Chronology
    public LocalDate dateNow(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    @Override // java.time.chrono.Chronology
    public boolean isLeapYear(long prolepticYear) {
        return (3 & prolepticYear) == 0 && (prolepticYear % 100 != 0 || prolepticYear % 400 == 0);
    }

    @Override // java.time.chrono.Chronology
    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof IsoEra) {
            return era == IsoEra.CE ? yearOfEra : 1 - yearOfEra;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override // java.time.chrono.Chronology
    public IsoEra eraOf(int eraValue) {
        return IsoEra.of(eraValue);
    }

    @Override // java.time.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(IsoEra.values());
    }

    @Override // java.time.chrono.Chronology, java.time.chrono.AbstractChronology
    public LocalDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        return (LocalDate) super.resolveDate(fieldValues, resolverStyle);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public void resolveProlepticMonth(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        Long pMonth = fieldValues.remove(ChronoField.PROLEPTIC_MONTH);
        if (pMonth != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.PROLEPTIC_MONTH.checkValidValue(pMonth.longValue());
            }
            addFieldValue(fieldValues, ChronoField.MONTH_OF_YEAR, Math.floorMod(pMonth.longValue(), 12) + 1);
            addFieldValue(fieldValues, ChronoField.YEAR, Math.floorDiv(pMonth.longValue(), 12));
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public LocalDate resolveYearOfEra(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        Long yoeLong = fieldValues.remove(ChronoField.YEAR_OF_ERA);
        if (yoeLong != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.YEAR_OF_ERA.checkValidValue(yoeLong.longValue());
            }
            Long era = fieldValues.remove(ChronoField.ERA);
            if (era == null) {
                Long year = fieldValues.get(ChronoField.YEAR);
                if (resolverStyle != ResolverStyle.STRICT) {
                    addFieldValue(fieldValues, ChronoField.YEAR, (year == null || year.longValue() > 0) ? yoeLong.longValue() : Math.subtractExact(1, yoeLong.longValue()));
                    return null;
                } else if (year != null) {
                    addFieldValue(fieldValues, ChronoField.YEAR, year.longValue() > 0 ? yoeLong.longValue() : Math.subtractExact(1, yoeLong.longValue()));
                    return null;
                } else {
                    fieldValues.put(ChronoField.YEAR_OF_ERA, yoeLong);
                    return null;
                }
            } else if (era.longValue() == 1) {
                addFieldValue(fieldValues, ChronoField.YEAR, yoeLong.longValue());
                return null;
            } else if (era.longValue() == 0) {
                addFieldValue(fieldValues, ChronoField.YEAR, Math.subtractExact(1, yoeLong.longValue()));
                return null;
            } else {
                throw new DateTimeException("Invalid value for era: " + ((Object) era));
            }
        } else if (!fieldValues.containsKey(ChronoField.ERA)) {
            return null;
        } else {
            ChronoField.ERA.checkValidValue(fieldValues.get(ChronoField.ERA).longValue());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public LocalDate resolveYMD(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
        if (resolverStyle == ResolverStyle.LENIENT) {
            long months = Math.subtractExact(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1);
            return LocalDate.of(y, 1, 1).plusMonths(months).plusDays(Math.subtractExact(fieldValues.remove(ChronoField.DAY_OF_MONTH).longValue(), 1));
        }
        int moy = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue());
        int dom = ChronoField.DAY_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_MONTH).longValue());
        if (resolverStyle == ResolverStyle.SMART) {
            if (moy == 4 || moy == 6 || moy == 9 || moy == 11) {
                dom = Math.min(dom, 30);
            } else if (moy == 2) {
                dom = Math.min(dom, Month.FEBRUARY.length(Year.isLeap((long) y)));
            }
        }
        return LocalDate.of(y, moy, dom);
    }

    @Override // java.time.chrono.Chronology
    public ValueRange range(ChronoField field) {
        return field.range();
    }

    @Override // java.time.chrono.Chronology
    public Period period(int years, int months, int days) {
        return Period.of(years, months, days);
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.chrono.AbstractChronology
    public Object writeReplace() {
        return super.writeReplace();
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
