package com.facebook.hermes.unicode;

import java.text.Collator;
import java.text.DateFormat;
import java.text.Normalizer;
import java.util.Locale;

public class AndroidUnicodeUtils {
    public static int localeCompare(String left, String right) {
        return Collator.getInstance().compare(left, right);
    }

    public static String dateFormat(double unixtimeMs, boolean formatDate, boolean formatTime) {
        DateFormat format;
        if (formatDate && formatTime) {
            format = DateFormat.getDateTimeInstance(2, 2);
        } else if (formatDate) {
            format = DateFormat.getDateInstance(2);
        } else if (formatTime) {
            format = DateFormat.getTimeInstance(2);
        } else {
            throw new RuntimeException("Bad dateFormat configuration");
        }
        return format.format(Long.valueOf((long) unixtimeMs)).toString();
    }

    public static String convertToCase(String input, int targetCase, boolean useCurrentLocale) {
        Locale locale = useCurrentLocale ? Locale.getDefault() : Locale.ENGLISH;
        switch (targetCase) {
            case 0:
                return input.toUpperCase(locale);
            case 1:
                return input.toLowerCase(locale);
            default:
                throw new RuntimeException("Invalid target case");
        }
    }

    public static String normalize(String input, int form) {
        switch (form) {
            case 0:
                return Normalizer.normalize(input, Normalizer.Form.NFC);
            case 1:
                return Normalizer.normalize(input, Normalizer.Form.NFD);
            case 2:
                return Normalizer.normalize(input, Normalizer.Form.NFKC);
            case 3:
                return Normalizer.normalize(input, Normalizer.Form.NFKD);
            default:
                throw new RuntimeException("Invalid form");
        }
    }
}
