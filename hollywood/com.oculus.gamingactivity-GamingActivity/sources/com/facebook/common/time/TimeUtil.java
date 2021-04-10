package com.facebook.common.time;

import com.facebook.infer.annotation.Nullsafe;
import java.util.TimeZone;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TimeUtil {
    private static final long[] TIME_UNITS = {TimeConstants.MS_PER_DAY, TimeConstants.MS_PER_HOUR, 60000, 1000};
    private static final String[] TIME_UNITS_ABBR = {"d", "h", "m", "s", "ms"};

    public static long utcToApiTime(long utcTime) {
        return (utcTime + ((long) TimeZone.getTimeZone("PST").getRawOffset())) / 1000;
    }

    public static long apiToUtcTime(long apiTime) {
        return (apiTime * 1000) - ((long) TimeZone.getTimeZone("PST").getRawOffset());
    }

    public static long minutesToMilliseconds(int minutes) {
        return ((long) minutes) * 60000;
    }

    public static String whenFromNow(long whenMillis, long nowMillis) {
        if (whenMillis == nowMillis) {
            return "now";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(toHumanReadableTime(Math.abs(whenMillis - nowMillis))).append(' ').append(whenMillis > nowMillis ? "later" : "ago");
        return sb.toString();
    }

    public static String toHumanReadableTime(long milliseconds) {
        StringBuilder sb = new StringBuilder();
        int len = TIME_UNITS.length;
        int i = 0;
        while (i < len) {
            long quotient = milliseconds / TIME_UNITS[i];
            if (quotient > 0) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(quotient).append(TIME_UNITS_ABBR[i]);
                milliseconds %= TIME_UNITS[i];
            }
            i++;
        }
        if (sb.length() <= 0) {
            sb.append(milliseconds).append(TIME_UNITS_ABBR[i]);
        } else if (milliseconds > 0) {
            sb.append(' ').append(milliseconds).append(TIME_UNITS_ABBR[i]);
        }
        return sb.toString();
    }

    public static long nanoTime() {
        return System.nanoTime();
    }
}
