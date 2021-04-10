package com.facebook.common.time;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TimeConversions {
    public static long minutesToMillieconds(long j) {
        return j * 60000;
    }

    public static long minutesToSeconds(long j) {
        return j * 60;
    }

    public static long secondsToMilliseconds(long j) {
        return j * 1000;
    }

    public static long millisecondsToHours(long j) {
        return j / 3600000;
    }

    public static long millisecondsToHoursRoundingUp(long j) {
        return convertMillisecondsRoundingUp(j, 3600000);
    }

    public static long millisecondsToHoursRounding(long j) {
        return convertMillisecondsRounding(j, 3600000);
    }

    public static long millisecondsToMinutes(long j) {
        return j / 60000;
    }

    public static long millisecondsToMinutesRoundingUp(long j) {
        return convertMillisecondsRoundingUp(j, 60000);
    }

    public static long millisecondsToDays(long j) {
        return j / TimeConstants.MS_PER_DAY;
    }

    public static long millisecondsToDaysRoundingUp(long j) {
        return convertMillisecondsRoundingUp(j, TimeConstants.MS_PER_DAY);
    }

    public static long millisecondsToDaysRounding(long j) {
        return convertMillisecondsRounding(j, TimeConstants.MS_PER_DAY);
    }

    public static long millisecondsToWeeks(long j) {
        return j / TimeConstants.MS_PER_WEEK;
    }

    public static long millisecondsToMonths(long j) {
        return j / TimeConstants.MS_PER_MONTH;
    }

    public static long millisecondsToYears(long j) {
        return j / 31536000000L;
    }

    public static long millisecondsToYearsRoundingUp(long j) {
        return convertMillisecondsRoundingUp(j, 31536000000L);
    }

    private static long convertMillisecondsRoundingUp(long j, long j2) {
        double d = (double) j;
        double d2 = (double) j2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (long) Math.ceil(d / d2);
    }

    private static long convertMillisecondsRounding(long j, long j2) {
        double d = (double) j;
        double d2 = (double) j2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return Math.round(d / d2);
    }

    public static long millisecondsToSeconds(long j) {
        return j / 1000;
    }

    public static double millisecondsToSecondsAsDouble(long j) {
        double d = (double) j;
        Double.isNaN(d);
        return d / 1000.0d;
    }

    public static long nanosecondsToMillisecondsRounded(long j) {
        return (j + 500000) / 1000000;
    }

    public static long microsecondsToMillisecondsRounding(long j) {
        return (j + 500) / 1000;
    }
}
