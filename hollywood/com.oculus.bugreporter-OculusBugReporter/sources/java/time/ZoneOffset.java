package java.time;

import android.support.v4.media.MediaPlayer2;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.time.zone.ZoneRules;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ZoneOffset extends ZoneId implements TemporalAccessor, TemporalAdjuster, Comparable<ZoneOffset>, Serializable {
    private static final ConcurrentMap<String, ZoneOffset> ID_CACHE = new ConcurrentHashMap(16, 0.75f, 4);
    public static final ZoneOffset MAX = ofTotalSeconds(MAX_SECONDS);
    private static final int MAX_SECONDS = 64800;
    public static final ZoneOffset MIN = ofTotalSeconds(-64800);
    private static final ConcurrentMap<Integer, ZoneOffset> SECONDS_CACHE = new ConcurrentHashMap(16, 0.75f, 4);
    public static final ZoneOffset UTC = ofTotalSeconds(0);
    private static final long serialVersionUID = 2357656521762053153L;
    private final transient String id;
    private final int totalSeconds;

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.time.ZoneOffset of(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 197
        */
        throw new UnsupportedOperationException("Method not decompiled: java.time.ZoneOffset.of(java.lang.String):java.time.ZoneOffset");
    }

    private static int parseNumber(CharSequence offsetId, int pos, boolean precededByColon) {
        if (!precededByColon || offsetId.charAt(pos - 1) == ':') {
            char ch1 = offsetId.charAt(pos);
            char ch2 = offsetId.charAt(pos + 1);
            if (ch1 >= '0' && ch1 <= '9' && ch2 >= '0' && ch2 <= '9') {
                return ((ch1 - '0') * 10) + (ch2 - '0');
            }
            throw new DateTimeException("Invalid ID for ZoneOffset, non numeric characters found: " + ((Object) offsetId));
        }
        throw new DateTimeException("Invalid ID for ZoneOffset, colon not found when expected: " + ((Object) offsetId));
    }

    public static ZoneOffset ofHours(int hours) {
        return ofHoursMinutesSeconds(hours, 0, 0);
    }

    public static ZoneOffset ofHoursMinutes(int hours, int minutes) {
        return ofHoursMinutesSeconds(hours, minutes, 0);
    }

    public static ZoneOffset ofHoursMinutesSeconds(int hours, int minutes, int seconds) {
        validate(hours, minutes, seconds);
        return ofTotalSeconds(totalSeconds(hours, minutes, seconds));
    }

    public static ZoneOffset from(TemporalAccessor temporal) {
        Objects.requireNonNull(temporal, "temporal");
        ZoneOffset offset = (ZoneOffset) temporal.query(TemporalQueries.offset());
        if (offset != null) {
            return offset;
        }
        throw new DateTimeException("Unable to obtain ZoneOffset from TemporalAccessor: " + ((Object) temporal) + " of type " + temporal.getClass().getName());
    }

    private static void validate(int hours, int minutes, int seconds) {
        if (hours < -18 || hours > 18) {
            throw new DateTimeException("Zone offset hours not in valid range: value " + hours + " is not in the range -18 to 18");
        }
        if (hours > 0) {
            if (minutes < 0 || seconds < 0) {
                throw new DateTimeException("Zone offset minutes and seconds must be positive because hours is positive");
            }
        } else if (hours < 0) {
            if (minutes > 0 || seconds > 0) {
                throw new DateTimeException("Zone offset minutes and seconds must be negative because hours is negative");
            }
        } else if ((minutes > 0 && seconds < 0) || (minutes < 0 && seconds > 0)) {
            throw new DateTimeException("Zone offset minutes and seconds must have the same sign");
        }
        if (Math.abs(minutes) > 59) {
            throw new DateTimeException("Zone offset minutes not in valid range: abs(value) " + Math.abs(minutes) + " is not in the range 0 to 59");
        } else if (Math.abs(seconds) > 59) {
            throw new DateTimeException("Zone offset seconds not in valid range: abs(value) " + Math.abs(seconds) + " is not in the range 0 to 59");
        } else if (Math.abs(hours) != 18) {
        } else {
            if (Math.abs(minutes) > 0 || Math.abs(seconds) > 0) {
                throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
            }
        }
    }

    private static int totalSeconds(int hours, int minutes, int seconds) {
        return (hours * 3600) + (minutes * 60) + seconds;
    }

    public static ZoneOffset ofTotalSeconds(int totalSeconds2) {
        if (Math.abs(totalSeconds2) > MAX_SECONDS) {
            throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
        } else if (totalSeconds2 % MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR != 0) {
            return new ZoneOffset(totalSeconds2);
        } else {
            Integer totalSecs = Integer.valueOf(totalSeconds2);
            ZoneOffset result = SECONDS_CACHE.get(totalSecs);
            if (result != null) {
                return result;
            }
            SECONDS_CACHE.putIfAbsent(totalSecs, new ZoneOffset(totalSeconds2));
            ZoneOffset result2 = SECONDS_CACHE.get(totalSecs);
            ID_CACHE.putIfAbsent(result2.getId(), result2);
            return result2;
        }
    }

    private ZoneOffset(int totalSeconds2) {
        this.totalSeconds = totalSeconds2;
        this.id = buildId(totalSeconds2);
    }

    private static String buildId(int totalSeconds2) {
        if (totalSeconds2 == 0) {
            return "Z";
        }
        int absTotalSeconds = Math.abs(totalSeconds2);
        StringBuilder buf = new StringBuilder();
        int absHours = absTotalSeconds / 3600;
        int absMinutes = (absTotalSeconds / 60) % 60;
        buf.append(totalSeconds2 < 0 ? "-" : "+");
        buf.append(absHours < 10 ? AndroidHardcodedSystemProperties.JAVA_VERSION : "");
        buf.append(absHours);
        String str = ":0";
        buf.append(absMinutes < 10 ? str : ":");
        buf.append(absMinutes);
        int absSeconds = absTotalSeconds % 60;
        if (absSeconds != 0) {
            if (absSeconds >= 10) {
                str = ":";
            }
            buf.append(str);
            buf.append(absSeconds);
        }
        return buf.toString();
    }

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    @Override // java.time.ZoneId
    public String getId() {
        return this.id;
    }

    @Override // java.time.ZoneId
    public ZoneRules getRules() {
        return ZoneRules.of(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField field) {
        return field instanceof ChronoField ? field == ChronoField.OFFSET_SECONDS : field != null && field.isSupportedBy(this);
    }

    @Override // java.time.temporal.TemporalAccessor
    public ValueRange range(TemporalField field) {
        return super.range(field);
    }

    @Override // java.time.temporal.TemporalAccessor
    public int get(TemporalField field) {
        if (field == ChronoField.OFFSET_SECONDS) {
            return this.totalSeconds;
        }
        if (!(field instanceof ChronoField)) {
            return range(field).checkValidIntValue(getLong(field), field);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField field) {
        if (field == ChronoField.OFFSET_SECONDS) {
            return (long) this.totalSeconds;
        }
        if (!(field instanceof ChronoField)) {
            return field.getFrom(this);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + ((Object) field));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.time.ZoneOffset */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.time.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> query) {
        return (query == TemporalQueries.offset() || query == TemporalQueries.zone()) ? this : (R) super.query(query);
    }

    @Override // java.time.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.OFFSET_SECONDS, (long) this.totalSeconds);
    }

    public int compareTo(ZoneOffset other) {
        return other.totalSeconds - this.totalSeconds;
    }

    @Override // java.time.ZoneId
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZoneOffset)) {
            return false;
        }
        if (this.totalSeconds == ((ZoneOffset) obj).totalSeconds) {
            return true;
        }
        return false;
    }

    @Override // java.time.ZoneId
    public int hashCode() {
        return this.totalSeconds;
    }

    @Override // java.time.ZoneId
    public String toString() {
        return this.id;
    }

    private Object writeReplace() {
        return new Ser((byte) 8, this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    @Override // java.time.ZoneId
    public void write(DataOutput out) throws IOException {
        out.writeByte(8);
        writeExternal(out);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        int offsetSecs = this.totalSeconds;
        int offsetByte = offsetSecs % MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR == 0 ? offsetSecs / MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR : 127;
        out.writeByte(offsetByte);
        if (offsetByte == 127) {
            out.writeInt(offsetSecs);
        }
    }

    static ZoneOffset readExternal(DataInput in) throws IOException {
        int offsetByte = in.readByte();
        return ofTotalSeconds(offsetByte == 127 ? in.readInt() : offsetByte * MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR);
    }
}
