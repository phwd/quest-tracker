package com.fasterxml.jackson.databind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final String GMT_ID = "GMT";
    private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone(GMT_ID);

    public static TimeZone timeZoneGMT() {
        return TIMEZONE_GMT;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    public static Date parse(String str) {
        int i;
        try {
            int parseInt = parseInt(str, 0, 4);
            checkOffset(str, 4, '-');
            int parseInt2 = parseInt(str, 5, 7);
            checkOffset(str, 7, '-');
            int parseInt3 = parseInt(str, 8, 10);
            checkOffset(str, 10, 'T');
            int parseInt4 = parseInt(str, 11, 13);
            checkOffset(str, 13, ':');
            int parseInt5 = parseInt(str, 14, 16);
            checkOffset(str, 16, ':');
            int i2 = 19;
            int parseInt6 = parseInt(str, 17, 19);
            if (str.charAt(19) == '.') {
                checkOffset(str, 19, '.');
                i = parseInt(str, 20, 23);
                i2 = 23;
            } else {
                i = 0;
            }
            char charAt = str.charAt(i2);
            String str2 = GMT_ID;
            if (charAt == '+' || charAt == '-') {
                str2 = str2 + str.substring(i2);
            } else if (charAt != 'Z') {
                throw new IndexOutOfBoundsException("Invalid time zone indicator " + charAt);
            }
            TimeZone timeZone = TimeZone.getTimeZone(str2);
            if (timeZone.getID().equals(str2)) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                gregorianCalendar.setLenient(false);
                gregorianCalendar.set(1, parseInt);
                gregorianCalendar.set(2, parseInt2 - 1);
                gregorianCalendar.set(5, parseInt3);
                gregorianCalendar.set(11, parseInt4);
                gregorianCalendar.set(12, parseInt5);
                gregorianCalendar.set(13, parseInt6);
                gregorianCalendar.set(14, i);
                return gregorianCalendar.getTime();
            }
            throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Failed to parse date " + str, e);
        } catch (NumberFormatException e2) {
            throw new IllegalArgumentException("Failed to parse date " + str, e2);
        } catch (IllegalArgumentException e3) {
            throw new IllegalArgumentException("Failed to parse date " + str, e3);
        }
    }

    private static void checkOffset(String str, int i, char c) throws IndexOutOfBoundsException {
        char charAt = str.charAt(i);
        if (charAt != c) {
            throw new IndexOutOfBoundsException("Expected '" + c + "' character but found '" + charAt + "'");
        }
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i3 = 0;
        if (i < i2) {
            int i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
                i = i4;
            } else {
                throw new NumberFormatException("Invalid number: " + str);
            }
        }
        while (i < i2) {
            int i5 = i + 1;
            int digit2 = Character.digit(str.charAt(i), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str);
            }
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }
}
