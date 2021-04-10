package java.time.chrono;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

public interface ChronoZonedDateTime extends Temporal, Comparable {
    ZoneOffset getOffset();

    ZoneId getZone();

    ChronoLocalDateTime toLocalDateTime();

    @Override // java.time.temporal.TemporalAccessor
    default ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        if (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.OFFSET_SECONDS) {
            return temporalField.range();
        }
        return toLocalDateTime().range(temporalField);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.chrono.ChronoZonedDateTime$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                java.time.temporal.ChronoField[] r0 = java.time.temporal.ChronoField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.time.chrono.ChronoZonedDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField = r0
                int[] r0 = java.time.chrono.ChronoZonedDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.INSTANT_SECONDS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.time.chrono.ChronoZonedDateTime.AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x001f }
                java.time.temporal.ChronoField r1 = java.time.temporal.ChronoField.OFFSET_SECONDS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.chrono.ChronoZonedDateTime.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    default int get(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return super.get(temporalField);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        if (i == 1) {
            throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        } else if (i != 2) {
            return toLocalDateTime().get(temporalField);
        } else {
            return getOffset().getTotalSeconds();
        }
    }

    @Override // java.time.temporal.TemporalAccessor
    default long getLong(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        int i = AnonymousClass1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
        if (i == 1) {
            return toEpochSecond();
        }
        if (i != 2) {
            return toLocalDateTime().getLong(temporalField);
        }
        return (long) getOffset().getTotalSeconds();
    }

    default ChronoLocalDate toLocalDate() {
        return toLocalDateTime().toLocalDate();
    }

    default LocalTime toLocalTime() {
        return toLocalDateTime().toLocalTime();
    }

    default Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    @Override // java.time.temporal.Temporal
    default ChronoZonedDateTime with(TemporalAdjuster temporalAdjuster) {
        return ChronoZonedDateTimeImpl.ensureValid(getChronology(), super.with(temporalAdjuster));
    }

    @Override // java.time.temporal.Temporal
    default ChronoZonedDateTime minus(long j, TemporalUnit temporalUnit) {
        return ChronoZonedDateTimeImpl.ensureValid(getChronology(), super.minus(j, temporalUnit));
    }

    @Override // java.time.temporal.TemporalAccessor
    default Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.zoneId()) {
            return getZone();
        }
        if (temporalQuery == TemporalQueries.offset()) {
            return getOffset();
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

    default long toEpochSecond() {
        return ((toLocalDate().toEpochDay() * 86400) + ((long) toLocalTime().toSecondOfDay())) - ((long) getOffset().getTotalSeconds());
    }

    default int compareTo(ChronoZonedDateTime chronoZonedDateTime) {
        int compare = Long.compare(toEpochSecond(), chronoZonedDateTime.toEpochSecond());
        if (compare != 0) {
            return compare;
        }
        int nano = toLocalTime().getNano() - chronoZonedDateTime.toLocalTime().getNano();
        if (nano != 0) {
            return nano;
        }
        int compareTo = toLocalDateTime().compareTo(chronoZonedDateTime.toLocalDateTime());
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = getZone().getId().compareTo(chronoZonedDateTime.getZone().getId());
        return compareTo2 == 0 ? getChronology().compareTo(chronoZonedDateTime.getChronology()) : compareTo2;
    }
}
