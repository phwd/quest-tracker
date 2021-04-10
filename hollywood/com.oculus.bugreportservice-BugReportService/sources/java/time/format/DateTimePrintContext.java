package java.time.format;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;
import java.util.Locale;
import java.util.Objects;

/* access modifiers changed from: package-private */
public final class DateTimePrintContext {
    private DateTimeFormatter formatter;
    private int optional;
    private TemporalAccessor temporal;

    DateTimePrintContext(TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        this.temporal = adjust(temporalAccessor, dateTimeFormatter);
        this.formatter = dateTimeFormatter;
    }

    private static TemporalAccessor adjust(final TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        Chronology chronology = dateTimeFormatter.getChronology();
        final ZoneId zone = dateTimeFormatter.getZone();
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        Chronology chronology2 = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
        final ChronoLocalDate chronoLocalDate = null;
        if (Objects.equals(chronology, chronology2)) {
            chronology = null;
        }
        if (Objects.equals(zone, zoneId)) {
            zone = null;
        }
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        final Chronology chronology3 = chronology != null ? chronology : chronology2;
        if (zone != null) {
            if (temporalAccessor.isSupported(ChronoField.INSTANT_SECONDS)) {
                if (chronology3 == null) {
                    chronology3 = IsoChronology.INSTANCE;
                }
                return chronology3.zonedDateTime(Instant.from(temporalAccessor), zone);
            } else if ((zone.normalized() instanceof ZoneOffset) && temporalAccessor.isSupported(ChronoField.OFFSET_SECONDS) && temporalAccessor.get(ChronoField.OFFSET_SECONDS) != zone.getRules().getOffset(Instant.EPOCH).getTotalSeconds()) {
                throw new DateTimeException("Unable to apply override zone '" + zone + "' because the temporal object being formatted has a different offset but does not represent an instant: " + temporalAccessor);
            }
        }
        if (zone == null) {
            zone = zoneId;
        }
        if (chronology != null) {
            if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                chronoLocalDate = chronology3.date(temporalAccessor);
            } else if (!(chronology == IsoChronology.INSTANCE && chronology2 == null)) {
                ChronoField[] values = ChronoField.values();
                for (ChronoField chronoField : values) {
                    if (chronoField.isDateBased() && temporalAccessor.isSupported(chronoField)) {
                        throw new DateTimeException("Unable to apply override chronology '" + chronology + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + temporalAccessor);
                    }
                }
            }
        }
        return new TemporalAccessor() {
            /* class java.time.format.DateTimePrintContext.AnonymousClass1 */

            @Override // java.time.temporal.TemporalAccessor
            public boolean isSupported(TemporalField temporalField) {
                if (ChronoLocalDate.this == null || !temporalField.isDateBased()) {
                    return temporalAccessor.isSupported(temporalField);
                }
                return ChronoLocalDate.this.isSupported(temporalField);
            }

            @Override // java.time.temporal.TemporalAccessor
            public ValueRange range(TemporalField temporalField) {
                if (ChronoLocalDate.this == null || !temporalField.isDateBased()) {
                    return temporalAccessor.range(temporalField);
                }
                return ChronoLocalDate.this.range(temporalField);
            }

            @Override // java.time.temporal.TemporalAccessor
            public long getLong(TemporalField temporalField) {
                if (ChronoLocalDate.this == null || !temporalField.isDateBased()) {
                    return temporalAccessor.getLong(temporalField);
                }
                return ChronoLocalDate.this.getLong(temporalField);
            }

            @Override // java.time.temporal.TemporalAccessor
            public Object query(TemporalQuery temporalQuery) {
                if (temporalQuery == TemporalQueries.chronology()) {
                    return chronology3;
                }
                if (temporalQuery == TemporalQueries.zoneId()) {
                    return zone;
                }
                if (temporalQuery == TemporalQueries.precision()) {
                    return temporalAccessor.query(temporalQuery);
                }
                return temporalQuery.queryFrom(this);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public TemporalAccessor getTemporal() {
        return this.temporal;
    }

    /* access modifiers changed from: package-private */
    public Locale getLocale() {
        return this.formatter.getLocale();
    }

    /* access modifiers changed from: package-private */
    public DecimalStyle getDecimalStyle() {
        return this.formatter.getDecimalStyle();
    }

    /* access modifiers changed from: package-private */
    public void startOptional() {
        this.optional++;
    }

    /* access modifiers changed from: package-private */
    public void endOptional() {
        this.optional--;
    }

    /* access modifiers changed from: package-private */
    public Object getValue(TemporalQuery temporalQuery) {
        Object query = this.temporal.query(temporalQuery);
        if (query != null || this.optional != 0) {
            return query;
        }
        throw new DateTimeException("Unable to extract value: " + this.temporal.getClass());
    }

    /* access modifiers changed from: package-private */
    public Long getValue(TemporalField temporalField) {
        try {
            return Long.valueOf(this.temporal.getLong(temporalField));
        } catch (DateTimeException e) {
            if (this.optional > 0) {
                return null;
            }
            throw e;
        }
    }

    public String toString() {
        return this.temporal.toString();
    }
}
