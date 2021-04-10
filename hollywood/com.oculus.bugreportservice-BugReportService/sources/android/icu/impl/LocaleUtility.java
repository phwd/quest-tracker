package android.icu.impl;

import java.util.Locale;

public class LocaleUtility {
    public static Locale getLocaleFromName(String str) {
        String str2;
        String str3;
        int indexOf = str.indexOf(95);
        String str4 = "";
        if (indexOf < 0) {
            str3 = str;
            str2 = str4;
        } else {
            str3 = str.substring(0, indexOf);
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(95, i);
            if (indexOf2 < 0) {
                str4 = str.substring(i);
                str2 = str4;
            } else {
                str4 = str.substring(i, indexOf2);
                str2 = str.substring(indexOf2 + 1);
            }
        }
        return new Locale(str3, str4, str2);
    }

    public static boolean isFallbackOf(String str, String str2) {
        if (!str2.startsWith(str)) {
            return false;
        }
        int length = str.length();
        if (length == str2.length() || str2.charAt(length) == '_') {
            return true;
        }
        return false;
    }
}
