package java.nio.file.attribute;

import dalvik.system.VMRuntime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class FileTime implements Comparable<FileTime> {
    private static final long DAYS_PER_10000_YEARS = 3652425;
    private static final long HOURS_PER_DAY = 24;
    private static final long MAX_SECOND = 31556889864403199L;
    private static final long MICROS_PER_SECOND = 1000000;
    private static final long MILLIS_PER_SECOND = 1000;
    private static final long MINUTES_PER_HOUR = 60;
    private static final long MIN_SECOND = -31557014167219200L;
    private static final int NANOS_PER_MICRO = 1000;
    private static final int NANOS_PER_MILLI = 1000000;
    private static final long NANOS_PER_SECOND = 1000000000;
    private static final long SECONDS_0000_TO_1970 = 62167219200L;
    private static final long SECONDS_PER_10000_YEARS = 315569520000L;
    private static final long SECONDS_PER_DAY = 86400;
    private static final long SECONDS_PER_HOUR = 3600;
    private static final long SECONDS_PER_MINUTE = 60;
    private Instant instant;
    private final TimeUnit unit;
    private final long value;
    private String valueAsString;

    private FileTime(long value2, TimeUnit unit2, Instant instant2) {
        this.value = value2;
        this.unit = unit2;
        this.instant = instant2;
    }

    public static FileTime from(long value2, TimeUnit unit2) {
        Objects.requireNonNull(unit2, "unit");
        return new FileTime(value2, unit2, null);
    }

    public static FileTime fromMillis(long value2) {
        return new FileTime(value2, TimeUnit.MILLISECONDS, null);
    }

    public static FileTime from(Instant instant2) {
        Objects.requireNonNull(instant2, "instant");
        return new FileTime(0, null, instant2);
    }

    public long to(TimeUnit unit2) {
        Objects.requireNonNull(unit2, "unit");
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return unit2.convert(this.value, timeUnit);
        }
        long secs = unit2.convert(this.instant.getEpochSecond(), TimeUnit.SECONDS);
        if (secs == Long.MIN_VALUE || secs == Long.MAX_VALUE) {
            return secs;
        }
        long nanos = unit2.convert((long) this.instant.getNano(), TimeUnit.NANOSECONDS);
        long r = secs + nanos;
        if (((secs ^ r) & (nanos ^ r)) >= 0) {
            return r;
        }
        if (secs < 0) {
            return Long.MIN_VALUE;
        }
        return Long.MAX_VALUE;
    }

    public long toMillis() {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toMillis(this.value);
        }
        long secs = this.instant.getEpochSecond();
        int nanos = this.instant.getNano();
        long r = secs * 1000;
        if (((Math.abs(secs) | 1000) >>> 31) == 0 || r / 1000 == secs) {
            return ((long) (nanos / NANOS_PER_MILLI)) + r;
        }
        if (secs < 0) {
            return Long.MIN_VALUE;
        }
        return Long.MAX_VALUE;
    }

    private static long scale(long d, long m, long over) {
        if (d > over) {
            return Long.MAX_VALUE;
        }
        if (d < (-over)) {
            return Long.MIN_VALUE;
        }
        return d * m;
    }

    public Instant toInstant() {
        long secs;
        if (this.instant == null) {
            int nanos = 0;
            switch (this.unit) {
                case DAYS:
                    secs = scale(this.value, SECONDS_PER_DAY, 106751991167300L);
                    break;
                case HOURS:
                    secs = scale(this.value, SECONDS_PER_HOUR, 2562047788015215L);
                    break;
                case MINUTES:
                    secs = scale(this.value, 60, 153722867280912930L);
                    break;
                case SECONDS:
                    secs = this.value;
                    break;
                case MILLISECONDS:
                    secs = Math.floorDiv(this.value, 1000);
                    nanos = ((int) Math.floorMod(this.value, 1000)) * NANOS_PER_MILLI;
                    break;
                case MICROSECONDS:
                    secs = Math.floorDiv(this.value, (long) MICROS_PER_SECOND);
                    nanos = ((int) Math.floorMod(this.value, (long) MICROS_PER_SECOND)) * 1000;
                    break;
                case NANOSECONDS:
                    secs = Math.floorDiv(this.value, (long) NANOS_PER_SECOND);
                    nanos = (int) Math.floorMod(this.value, (long) NANOS_PER_SECOND);
                    break;
                default:
                    throw new AssertionError((Object) "Unit not handled");
            }
            if (secs <= MIN_SECOND) {
                this.instant = Instant.MIN;
            } else if (secs >= MAX_SECOND) {
                this.instant = Instant.MAX;
            } else {
                this.instant = Instant.ofEpochSecond(secs, (long) nanos);
            }
        }
        return this.instant;
    }

    public boolean equals(Object obj) {
        return (obj instanceof FileTime) && compareTo((FileTime) obj) == 0;
    }

    public int hashCode() {
        return toInstant().hashCode();
    }

    private long toDays() {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toDays(this.value);
        }
        return TimeUnit.SECONDS.toDays(toInstant().getEpochSecond());
    }

    private long toExcessNanos(long days) {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null) {
            return timeUnit.toNanos(this.value - timeUnit.convert(days, TimeUnit.DAYS));
        }
        return TimeUnit.SECONDS.toNanos(toInstant().getEpochSecond() - TimeUnit.DAYS.toSeconds(days));
    }

    public int compareTo(FileTime other) {
        TimeUnit timeUnit = this.unit;
        if (timeUnit != null && timeUnit == other.unit) {
            return Long.compare(this.value, other.value);
        }
        long secs = toInstant().getEpochSecond();
        int cmp = Long.compare(secs, other.toInstant().getEpochSecond());
        if (cmp != 0) {
            return cmp;
        }
        int cmp2 = Long.compare((long) toInstant().getNano(), (long) other.toInstant().getNano());
        if (cmp2 != 0) {
            return cmp2;
        }
        if (secs != MAX_SECOND && secs != MIN_SECOND) {
            return 0;
        }
        long days = toDays();
        long daysOther = other.toDays();
        if (days == daysOther) {
            return Long.compare(toExcessNanos(days), other.toExcessNanos(daysOther));
        }
        return Long.compare(days, daysOther);
    }

    private StringBuilder append(StringBuilder sb, int w, int d) {
        while (w > 0) {
            sb.append((char) ((d / w) + 48));
            d %= w;
            w /= 10;
        }
        return sb;
    }

    public String toString() {
        long secs;
        int year;
        LocalDateTime ldt;
        if (this.valueAsString == null) {
            int nanos = 0;
            if (this.instant != null || this.unit.compareTo((Enum) TimeUnit.SECONDS) < 0) {
                secs = toInstant().getEpochSecond();
                nanos = toInstant().getNano();
            } else {
                secs = this.unit.toSeconds(this.value);
            }
            if (secs >= -62167219200L) {
                long zeroSecs = (secs - SECONDS_PER_10000_YEARS) + SECONDS_0000_TO_1970;
                ldt = LocalDateTime.ofEpochSecond(Math.floorMod(zeroSecs, (long) SECONDS_PER_10000_YEARS) - SECONDS_0000_TO_1970, nanos, ZoneOffset.UTC);
                year = ldt.getYear() + (((int) (Math.floorDiv(zeroSecs, (long) SECONDS_PER_10000_YEARS) + 1)) * VMRuntime.SDK_VERSION_CUR_DEVELOPMENT);
            } else {
                long zeroSecs2 = secs + SECONDS_0000_TO_1970;
                long hi = zeroSecs2 / SECONDS_PER_10000_YEARS;
                ldt = LocalDateTime.ofEpochSecond((zeroSecs2 % SECONDS_PER_10000_YEARS) - SECONDS_0000_TO_1970, nanos, ZoneOffset.UTC);
                year = ldt.getYear() + (((int) hi) * VMRuntime.SDK_VERSION_CUR_DEVELOPMENT);
            }
            if (year <= 0) {
                year--;
            }
            int fraction = ldt.getNano();
            StringBuilder sb = new StringBuilder(64);
            sb.append(year < 0 ? "-" : "");
            int year2 = Math.abs(year);
            if (year2 < 10000) {
                append(sb, 1000, Math.abs(year2));
            } else {
                sb.append(String.valueOf(year2));
            }
            sb.append('-');
            append(sb, 10, ldt.getMonthValue());
            sb.append('-');
            append(sb, 10, ldt.getDayOfMonth());
            sb.append('T');
            append(sb, 10, ldt.getHour());
            sb.append(':');
            append(sb, 10, ldt.getMinute());
            sb.append(':');
            append(sb, 10, ldt.getSecond());
            if (fraction != 0) {
                sb.append('.');
                int w = 100000000;
                while (fraction % 10 == 0) {
                    fraction /= 10;
                    w /= 10;
                }
                append(sb, w, fraction);
            }
            sb.append('Z');
            this.valueAsString = sb.toString();
        }
        return this.valueAsString;
    }
}
