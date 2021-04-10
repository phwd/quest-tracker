package com.facebook.common.time;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TimeConversions {
    public static long millisecondsToHours(long timeMs) {
        return timeMs / TimeConstants.MS_PER_HOUR;
    }

    public static long millisecondsToHoursRoundingUp(long timeMs) {
        return convertMillisecondsRoundingUp(timeMs, TimeConstants.MS_PER_HOUR);
    }

    public static long millisecondsToHoursRounding(long timeMs) {
        return convertMillisecondsRounding(timeMs, TimeConstants.MS_PER_HOUR);
    }

    public static long millisecondsToMinutes(long timeMs) {
        return timeMs / 60000;
    }

    public static long millisecondsToMinutesRoundingUp(long timeMs) {
        return convertMillisecondsRoundingUp(timeMs, 60000);
    }

    public static long millisecondsToDays(long timeMs) {
        return timeMs / TimeConstants.MS_PER_DAY;
    }

    public static long millisecondsToDaysRoundingUp(long timeMs) {
        return convertMillisecondsRoundingUp(timeMs, TimeConstants.MS_PER_DAY);
    }

    public static long millisecondsToDaysRounding(long timeMs) {
        return convertMillisecondsRounding(timeMs, TimeConstants.MS_PER_DAY);
    }

    public static long millisecondsToWeeks(long timeMs) {
        return timeMs / TimeConstants.MS_PER_WEEK;
    }

    public static long millisecondsToMonths(long timeMs) {
        return timeMs / TimeConstants.MS_PER_MONTH;
    }

    public static long millisecondsToYears(long timeMs) {
        return timeMs / 31536000000L;
    }

    public static long millisecondsToYearsRoundingUp(long timeMs) {
        return convertMillisecondsRoundingUp(timeMs, 31536000000L);
    }

    private static long convertMillisecondsRoundingUp(long timeMs, long units) {
        return (long) Math.ceil(((double) timeMs) / ((double) units));
    }

    private static long convertMillisecondsRounding(long timeMs, long units) {
        return Math.round(((double) timeMs) / ((double) units));
    }

    public static long millisecondsToSeconds(long timeMs) {
        return timeMs / 1000;
    }

    public static double millisecondsToSecondsAsDouble(long timeMs) {
        return ((double) timeMs) / 1000.0d;
    }

    public static long minutesToSeconds(long timeMinutes) {
        return 60 * timeMinutes;
    }

    public static long secondsToMilliseconds(long timeSeconds) {
        return 1000 * timeSeconds;
    }

    public static long minutesToMillieconds(long timeMinutes) {
        return 60000 * timeMinutes;
    }

    public static long nanosecondsToMillisecondsRounded(long nanos) {
        return (500000 + nanos) / 1000000;
    }

    public static long microsecondsToMillisecondsRounding(long microseconds) {
        return (500 + microseconds) / 1000;
    }
}
