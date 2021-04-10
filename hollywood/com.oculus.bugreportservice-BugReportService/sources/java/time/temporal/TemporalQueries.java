package java.time.temporal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.Chronology;

public final class TemporalQueries {
    static final TemporalQuery CHRONO = $$Lambda$TemporalQueries$thd4JmExRUYKd7nNlE7b5oT19ms.INSTANCE;
    static final TemporalQuery LOCAL_DATE = $$Lambda$TemporalQueries$JPrXwgedeqexYxypO8VpPKV4l3c.INSTANCE;
    static final TemporalQuery LOCAL_TIME = $$Lambda$TemporalQueries$WGGw7SkRcanjtxRiTk5p0dKf_jc.INSTANCE;
    static final TemporalQuery OFFSET = $$Lambda$TemporalQueries$bI5NESEXE4DqyC7TnOvbkx1GlvM.INSTANCE;
    static final TemporalQuery PRECISION = $$Lambda$TemporalQueries$okxqZ6ZoOhHd_zSzW7k5qRIaLxM.INSTANCE;
    static final TemporalQuery ZONE = $$Lambda$TemporalQueries$PBpYKRiwkxqQNlcUBOJfaQoONg.INSTANCE;
    static final TemporalQuery ZONE_ID = $$Lambda$TemporalQueries$IZUinmsZUz98YXPe0ftAd27ByiE.INSTANCE;

    public static TemporalQuery zoneId() {
        return ZONE_ID;
    }

    public static TemporalQuery chronology() {
        return CHRONO;
    }

    public static TemporalQuery precision() {
        return PRECISION;
    }

    public static TemporalQuery zone() {
        return ZONE;
    }

    public static TemporalQuery offset() {
        return OFFSET;
    }

    public static TemporalQuery localDate() {
        return LOCAL_DATE;
    }

    public static TemporalQuery localTime() {
        return LOCAL_TIME;
    }

    static /* synthetic */ ZoneId lambda$static$0(TemporalAccessor temporalAccessor) {
        return (ZoneId) temporalAccessor.query(ZONE_ID);
    }

    static /* synthetic */ Chronology lambda$static$1(TemporalAccessor temporalAccessor) {
        return (Chronology) temporalAccessor.query(CHRONO);
    }

    static /* synthetic */ TemporalUnit lambda$static$2(TemporalAccessor temporalAccessor) {
        return (TemporalUnit) temporalAccessor.query(PRECISION);
    }

    static /* synthetic */ ZoneOffset lambda$static$3(TemporalAccessor temporalAccessor) {
        if (temporalAccessor.isSupported(ChronoField.OFFSET_SECONDS)) {
            return ZoneOffset.ofTotalSeconds(temporalAccessor.get(ChronoField.OFFSET_SECONDS));
        }
        return null;
    }

    static /* synthetic */ ZoneId lambda$static$4(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(ZONE_ID);
        return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(OFFSET);
    }

    static /* synthetic */ LocalDate lambda$static$5(TemporalAccessor temporalAccessor) {
        if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
            return LocalDate.ofEpochDay(temporalAccessor.getLong(ChronoField.EPOCH_DAY));
        }
        return null;
    }

    static /* synthetic */ LocalTime lambda$static$6(TemporalAccessor temporalAccessor) {
        if (temporalAccessor.isSupported(ChronoField.NANO_OF_DAY)) {
            return LocalTime.ofNanoOfDay(temporalAccessor.getLong(ChronoField.NANO_OF_DAY));
        }
        return null;
    }
}
