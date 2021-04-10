package com.facebook.common.time;

import com.facebook.infer.annotation.Nullsafe;
import java.util.TimeZone;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TimeUtil {
    private static final long[] TIME_UNITS = {TimeConstants.MS_PER_DAY, 3600000, 60000, 1000};
    private static final String[] TIME_UNITS_ABBR = {"d", "h", "m", "s", "ms"};

    public static long minutesToMilliseconds(int i) {
        return ((long) i) * 60000;
    }

    public static long utcToApiTime(long j) {
        return (j + ((long) TimeZone.getTimeZone("PST").getRawOffset())) / 1000;
    }

    public static long apiToUtcTime(long j) {
        return (j * 1000) - ((long) TimeZone.getTimeZone("PST").getRawOffset());
    }

    public static String whenFromNow(long j, long j2) {
        if (j == j2) {
            return "now";
        }
        StringBuilder sb = new StringBuilder();
        String str = j > j2 ? "later" : "ago";
        sb.append(toHumanReadableTime(Math.abs(j - j2)));
        sb.append(' ');
        sb.append(str);
        return sb.toString();
    }

    public static String toHumanReadableTime(long j) {
        StringBuilder sb = new StringBuilder();
        int length = TIME_UNITS.length;
        int i = 0;
        while (i < length) {
            long j2 = j / TIME_UNITS[i];
            if (j2 > 0) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(j2);
                sb.append(TIME_UNITS_ABBR[i]);
                j %= TIME_UNITS[i];
            }
            i++;
        }
        if (sb.length() <= 0) {
            sb.append(j);
            sb.append(TIME_UNITS_ABBR[i]);
        } else if (j > 0) {
            sb.append(' ');
            sb.append(j);
            sb.append(TIME_UNITS_ABBR[i]);
        }
        return sb.toString();
    }

    public static long nanoTime() {
        return System.nanoTime();
    }
}
