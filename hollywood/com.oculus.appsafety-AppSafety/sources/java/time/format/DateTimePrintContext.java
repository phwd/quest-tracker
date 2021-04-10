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

    DateTimePrintContext(TemporalAccessor temporal2, DateTimeFormatter formatter2) {
        this.temporal = adjust(temporal2, formatter2);
        this.formatter = formatter2;
    }

    private static TemporalAccessor adjust(final TemporalAccessor temporal2, DateTimeFormatter formatter2) {
        final ChronoLocalDate effectiveDate;
        Chronology overrideChrono = formatter2.getChronology();
        ZoneId overrideZone = formatter2.getZone();
        if (overrideChrono == null && overrideZone == null) {
            return temporal2;
        }
        Chronology temporalChrono = (Chronology) temporal2.query(TemporalQueries.chronology());
        ZoneId temporalZone = (ZoneId) temporal2.query(TemporalQueries.zoneId());
        if (Objects.equals(overrideChrono, temporalChrono)) {
            overrideChrono = null;
        }
        if (Objects.equals(overrideZone, temporalZone)) {
            overrideZone = null;
        }
        if (overrideChrono == null && overrideZone == null) {
            return temporal2;
        }
        final Chronology effectiveChrono = overrideChrono != null ? overrideChrono : temporalChrono;
        if (overrideZone != null) {
            if (temporal2.isSupported(ChronoField.INSTANT_SECONDS)) {
                return (effectiveChrono != null ? effectiveChrono : IsoChronology.INSTANCE).zonedDateTime(Instant.from(temporal2), overrideZone);
            } else if ((overrideZone.normalized() instanceof ZoneOffset) && temporal2.isSupported(ChronoField.OFFSET_SECONDS) && temporal2.get(ChronoField.OFFSET_SECONDS) != overrideZone.getRules().getOffset(Instant.EPOCH).getTotalSeconds()) {
                throw new DateTimeException("Unable to apply override zone '" + ((Object) overrideZone) + "' because the temporal object being formatted has a different offset but does not represent an instant: " + ((Object) temporal2));
            }
        }
        final ZoneId effectiveZone = overrideZone != null ? overrideZone : temporalZone;
        if (overrideChrono == null) {
            effectiveDate = null;
        } else if (temporal2.isSupported(ChronoField.EPOCH_DAY)) {
            effectiveDate = effectiveChrono.date(temporal2);
        } else {
            if (!(overrideChrono == IsoChronology.INSTANCE && temporalChrono == null)) {
                ChronoField[] values = ChronoField.values();
                for (ChronoField f : values) {
                    if (f.isDateBased() && temporal2.isSupported(f)) {
                        throw new DateTimeException("Unable to apply override chronology '" + ((Object) overrideChrono) + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + ((Object) temporal2));
                    }
                }
            }
            effectiveDate = null;
        }
        return new TemporalAccessor() {
            /* class java.time.format.DateTimePrintContext.AnonymousClass1 */

            @Override // java.time.temporal.TemporalAccessor
            public boolean isSupported(TemporalField field) {
                if (ChronoLocalDate.this == null || !field.isDateBased()) {
                    return temporal2.isSupported(field);
                }
                return ChronoLocalDate.this.isSupported(field);
            }

            @Override // java.time.temporal.TemporalAccessor
            public ValueRange range(TemporalField field) {
                if (ChronoLocalDate.this == null || !field.isDateBased()) {
                    return temporal2.range(field);
                }
                return ChronoLocalDate.this.range(field);
            }

            @Override // java.time.temporal.TemporalAccessor
            public long getLong(TemporalField field) {
                if (ChronoLocalDate.this == null || !field.isDateBased()) {
                    return temporal2.getLong(field);
                }
                return ChronoLocalDate.this.getLong(field);
            }

            @Override // java.time.temporal.TemporalAccessor
            public <R> R query(TemporalQuery<R> query) {
                return query == TemporalQueries.chronology() ? (R) effectiveChrono : query == TemporalQueries.zoneId() ? (R) effectiveZone : query == TemporalQueries.precision() ? (R) temporal2.query(query) : query.queryFrom(this);
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
    public <R> R getValue(TemporalQuery<R> query) {
        R result = (R) this.temporal.query(query);
        if (result != null || this.optional != 0) {
            return result;
        }
        throw new DateTimeException("Unable to extract value: " + ((Object) this.temporal.getClass()));
    }

    /* access modifiers changed from: package-private */
    public Long getValue(TemporalField field) {
        try {
            return Long.valueOf(this.temporal.getLong(field));
        } catch (DateTimeException ex) {
            if (this.optional > 0) {
                return null;
            }
            throw ex;
        }
    }

    public String toString() {
        return this.temporal.toString();
    }
}
