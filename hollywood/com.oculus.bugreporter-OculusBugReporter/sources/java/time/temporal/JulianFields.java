package java.time.temporal;

import android.icu.impl.number.Padder;
import java.time.DateTimeException;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.ResolverStyle;
import java.util.Map;

public final class JulianFields {
    public static final TemporalField JULIAN_DAY = Field.JULIAN_DAY;
    private static final long JULIAN_DAY_OFFSET = 2440588;
    public static final TemporalField MODIFIED_JULIAN_DAY = Field.MODIFIED_JULIAN_DAY;
    public static final TemporalField RATA_DIE = Field.RATA_DIE;

    private JulianFields() {
        throw new AssertionError((Object) "Not instantiable");
    }

    private enum Field implements TemporalField {
        JULIAN_DAY("JulianDay", ChronoUnit.DAYS, ChronoUnit.FOREVER, JulianFields.JULIAN_DAY_OFFSET),
        MODIFIED_JULIAN_DAY("ModifiedJulianDay", ChronoUnit.DAYS, ChronoUnit.FOREVER, 40587),
        RATA_DIE("RataDie", ChronoUnit.DAYS, ChronoUnit.FOREVER, 719163);
        
        private static final long serialVersionUID = -7501623920830201812L;
        private final transient TemporalUnit baseUnit;
        private final transient String name;
        private final transient long offset;
        private final transient ValueRange range;
        private final transient TemporalUnit rangeUnit;

        private Field(String name2, TemporalUnit baseUnit2, TemporalUnit rangeUnit2, long offset2) {
            this.name = name2;
            this.baseUnit = baseUnit2;
            this.rangeUnit = rangeUnit2;
            this.range = ValueRange.of(-365243219162L + offset2, 365241780471L + offset2);
            this.offset = offset2;
        }

        @Override // java.time.temporal.TemporalField
        public TemporalUnit getBaseUnit() {
            return this.baseUnit;
        }

        @Override // java.time.temporal.TemporalField
        public TemporalUnit getRangeUnit() {
            return this.rangeUnit;
        }

        @Override // java.time.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // java.time.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        @Override // java.time.temporal.TemporalField
        public ValueRange range() {
            return this.range;
        }

        @Override // java.time.temporal.TemporalField
        public boolean isSupportedBy(TemporalAccessor temporal) {
            return temporal.isSupported(ChronoField.EPOCH_DAY);
        }

        @Override // java.time.temporal.TemporalField
        public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
            if (isSupportedBy(temporal)) {
                return range();
            }
            throw new DateTimeException("Unsupported field: " + ((Object) this));
        }

        @Override // java.time.temporal.TemporalField
        public long getFrom(TemporalAccessor temporal) {
            return temporal.getLong(ChronoField.EPOCH_DAY) + this.offset;
        }

        @Override // java.time.temporal.TemporalField
        public <R extends Temporal> R adjustInto(R temporal, long newValue) {
            if (range().isValidValue(newValue)) {
                return (R) temporal.with(ChronoField.EPOCH_DAY, Math.subtractExact(newValue, this.offset));
            }
            throw new DateTimeException("Invalid value: " + this.name + Padder.FALLBACK_PADDING_STRING + newValue);
        }

        @Override // java.time.temporal.TemporalField
        public ChronoLocalDate resolve(Map<TemporalField, Long> fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
            long value = fieldValues.remove(this).longValue();
            Chronology chrono = Chronology.from(partialTemporal);
            if (resolverStyle == ResolverStyle.LENIENT) {
                return chrono.dateEpochDay(Math.subtractExact(value, this.offset));
            }
            range().checkValidValue(value, this);
            return chrono.dateEpochDay(value - this.offset);
        }

        @Override // java.lang.Enum, java.time.temporal.TemporalField
        public String toString() {
            return this.name;
        }
    }
}
