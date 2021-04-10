package com.google.gson.internal;

import com.oculus.panellib.Qpl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
    public static DateFormat getUSDateTimeFormat(int dateStyle, int timeStyle) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(dateStyle) + " " + getTimePartOfDateTimePattern(timeStyle), Locale.US);
    }

    private static String getDatePartOfDateTimePattern(int dateStyle) {
        switch (dateStyle) {
            case 0:
                return "EEEE, MMMM d, yyyy";
            case 1:
                return "MMMM d, yyyy";
            case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                return "MMM d, yyyy";
            case 3:
                return "M/d/yy";
            default:
                throw new IllegalArgumentException("Unknown DateFormat style: " + dateStyle);
        }
    }

    private static String getTimePartOfDateTimePattern(int timeStyle) {
        switch (timeStyle) {
            case 0:
            case 1:
                return "h:mm:ss a z";
            case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                return "h:mm:ss a";
            case 3:
                return "h:mm a";
            default:
                throw new IllegalArgumentException("Unknown DateFormat style: " + timeStyle);
        }
    }
}