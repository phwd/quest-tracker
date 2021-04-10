package com.facebook.common.time;

import java.util.concurrent.TimeUnit;

public class TimeSpan {
    private final long mDurationNanos;

    public static TimeSpan fromNanos(long nanos) {
        return new TimeSpan(nanos, TimeUnit.NANOSECONDS);
    }

    public static TimeSpan fromMicros(long micros) {
        return new TimeSpan(micros, TimeUnit.MICROSECONDS);
    }

    public static TimeSpan fromMillis(long millis) {
        return new TimeSpan(millis, TimeUnit.MILLISECONDS);
    }

    public static TimeSpan fromSeconds(long seconds) {
        return new TimeSpan(seconds, TimeUnit.SECONDS);
    }

    public static TimeSpan fromMinutes(long minutes) {
        return new TimeSpan(minutes, TimeUnit.MINUTES);
    }

    public static TimeSpan fromHours(long hours) {
        return new TimeSpan(hours, TimeUnit.HOURS);
    }

    public static TimeSpan fromDays(long days) {
        return new TimeSpan(days, TimeUnit.DAYS);
    }

    public TimeSpan(long duration, TimeUnit timeUnit) {
        this.mDurationNanos = timeUnit.toNanos(duration);
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
        long days = toDays();
        long hours = toHours() % 24;
        long minutes = toMinutes() % 60;
        long seconds = toSeconds() % 60;
        long millis = toMillis() % 1000;
        long micros = toMicros() % 1000;
        long nanos = toNanos() % 1000;
        String sep = "";
        StringBuilder sb = new StringBuilder("TimeSpan{");
        if (days > 0) {
            sb.append(days).append(" ");
            pluralize(sb, "Day", days);
            sep = ", ";
        }
        if (hours > 0) {
            sb.append(sep).append(hours).append(" ");
            pluralize(sb, "Hour", hours);
            sep = ", ";
        }
        if (minutes > 0) {
            sb.append(sep).append(minutes).append(" ");
            pluralize(sb, "Minute", minutes);
            sep = ", ";
        }
        if (seconds > 0) {
            sb.append(sep).append(seconds).append(" ");
            pluralize(sb, "Second", seconds);
            sep = ", ";
        }
        if (millis > 0) {
            sb.append(sep).append(millis).append(" ");
            pluralize(sb, "Milli", millis);
            sep = ", ";
        }
        if (micros > 0) {
            sb.append(sep).append(micros).append(" ");
            pluralize(sb, "Micro", micros);
            sep = ", ";
        }
        if (nanos > 0) {
            sb.append(sep).append(nanos).append(" ");
            pluralize(sb, "Nano", nanos);
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mDurationNanos == ((TimeSpan) o).mDurationNanos;
    }

    public int hashCode() {
        return (int) (this.mDurationNanos ^ (this.mDurationNanos >>> 32));
    }

    private static final void pluralize(StringBuilder sb, String singular, long num) {
        sb.append(singular);
        if (num > 1) {
            sb.append("s");
        }
    }
}
