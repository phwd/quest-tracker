package com.facebook.common.time;

import java.util.concurrent.TimeUnit;

public class TimeSpan {
    private final long mDurationNanos;

    public static TimeSpan fromNanos(long j) {
        return new TimeSpan(j, TimeUnit.NANOSECONDS);
    }

    public static TimeSpan fromMicros(long j) {
        return new TimeSpan(j, TimeUnit.MICROSECONDS);
    }

    public static TimeSpan fromMillis(long j) {
        return new TimeSpan(j, TimeUnit.MILLISECONDS);
    }

    public static TimeSpan fromSeconds(long j) {
        return new TimeSpan(j, TimeUnit.SECONDS);
    }

    public static TimeSpan fromMinutes(long j) {
        return new TimeSpan(j, TimeUnit.MINUTES);
    }

    public static TimeSpan fromHours(long j) {
        return new TimeSpan(j, TimeUnit.HOURS);
    }

    public static TimeSpan fromDays(long j) {
        return new TimeSpan(j, TimeUnit.DAYS);
    }

    public TimeSpan(long j, TimeUnit timeUnit) {
        this.mDurationNanos = timeUnit.toNanos(j);
    }

    public long toNanos() {
        return this.mDurationNanos;
    }

    public long toMicros() {
        return TimeUnit.NANOSECONDS.toMicros(this.mDurationNanos);
    }

    public long toMillis() {
        return TimeUnit.NANOSECONDS.toMillis(this.mDurationNanos);
    }

    public long toSeconds() {
        return TimeUnit.NANOSECONDS.toSeconds(this.mDurationNanos);
    }

    public long toMinutes() {
        return TimeUnit.NANOSECONDS.toMinutes(this.mDurationNanos);
    }

    public long toHours() {
        return TimeUnit.NANOSECONDS.toHours(this.mDurationNanos);
    }

    public long toDays() {
        return TimeUnit.NANOSECONDS.toDays(this.mDurationNanos);
    }

    public long as(TimeUnit timeUnit) {
        return timeUnit.convert(this.mDurationNanos, TimeUnit.NANOSECONDS);
    }

    public String toString() {
        long j;
        String str;
        long days = toDays();
        long hours = toHours() % 24;
        long minutes = toMinutes() % 60;
        long seconds = toSeconds() % 60;
        long millis = toMillis() % 1000;
        long micros = toMicros() % 1000;
        long nanos = toNanos() % 1000;
        StringBuilder sb = new StringBuilder("TimeSpan{");
        if (days > 0) {
            sb.append(days);
            sb.append(" ");
            j = nanos;
            pluralize(sb, "Day", days);
            str = ", ";
        } else {
            j = nanos;
            str = "";
        }
        if (hours > 0) {
            sb.append(str);
            sb.append(hours);
            sb.append(" ");
            pluralize(sb, "Hour", hours);
            str = ", ";
        }
        if (minutes > 0) {
            sb.append(str);
            sb.append(minutes);
            sb.append(" ");
            pluralize(sb, "Minute", minutes);
            str = ", ";
        }
        if (seconds > 0) {
            sb.append(str);
            sb.append(seconds);
            sb.append(" ");
            pluralize(sb, "Second", seconds);
            str = ", ";
        }
        if (millis > 0) {
            sb.append(str);
            sb.append(millis);
            sb.append(" ");
            pluralize(sb, "Milli", millis);
            str = ", ";
        }
        if (micros > 0) {
            sb.append(str);
            sb.append(micros);
            sb.append(" ");
            pluralize(sb, "Micro", micros);
            str = ", ";
        }
        if (j > 0) {
            sb.append(str);
            sb.append(j);
            sb.append(" ");
            pluralize(sb, "Nano", j);
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mDurationNanos == ((TimeSpan) obj).mDurationNanos;
    }

    public int hashCode() {
        long j = this.mDurationNanos;
        return (int) (j ^ (j >>> 32));
    }

    private static final void pluralize(StringBuilder sb, String str, long j) {
        sb.append(str);
        if (j > 1) {
            sb.append("s");
        }
    }
}
