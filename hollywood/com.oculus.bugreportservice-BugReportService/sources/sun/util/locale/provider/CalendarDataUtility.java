package sun.util.locale.provider;

import android.icu.text.DateFormatSymbols;
import android.icu.util.ULocale;
import java.util.Locale;
import java.util.Map;

public class CalendarDataUtility {
    private static int[] REST_OF_STYLES = {32769, 2, 32770, 4, 32772};

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String retrieveFieldValueName(java.lang.String r5, int r6, int r7, int r8, java.util.Locale r9) {
        /*
            if (r6 != 0) goto L_0x0047
            java.lang.String r0 = normalizeCalendarType(r5)
            int r1 = r0.hashCode()
            r2 = -1581060683(0xffffffffa1c2edb5, float:-1.3208872E-18)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L_0x0030
            r2 = -752730191(0xffffffffd3223fb1, float:-6.9685327E11)
            if (r1 == r2) goto L_0x0026
            r2 = 2093696456(0x7ccb45c8, float:8.443613E36)
            if (r1 == r2) goto L_0x001c
            goto L_0x003a
        L_0x001c:
            java.lang.String r1 = "islamic"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003a
            r0 = r4
            goto L_0x003b
        L_0x0026:
            java.lang.String r1 = "japanese"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003a
            r0 = r3
            goto L_0x003b
        L_0x0030:
            java.lang.String r1 = "buddhist"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003a
            r0 = 0
            goto L_0x003b
        L_0x003a:
            r0 = -1
        L_0x003b:
            if (r0 == 0) goto L_0x0045
            if (r0 == r4) goto L_0x0045
            if (r0 == r3) goto L_0x0042
            goto L_0x0047
        L_0x0042:
            int r7 = r7 + 231
            goto L_0x0047
        L_0x0045:
            int r7 = r7 + -1
        L_0x0047:
            r0 = 0
            if (r7 >= 0) goto L_0x004b
            return r0
        L_0x004b:
            java.lang.String[] r5 = getNames(r5, r6, r8, r9)
            int r6 = r5.length
            if (r7 < r6) goto L_0x0053
            return r0
        L_0x0053:
            r5 = r5[r7]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.locale.provider.CalendarDataUtility.retrieveFieldValueName(java.lang.String, int, int, int, java.util.Locale):java.lang.String");
    }

    public static String retrieveJavaTimeFieldValueName(String str, int i, int i2, int i3, Locale locale) {
        return retrieveFieldValueName(str, i, i2, i3, locale);
    }

    public static Map retrieveFieldValueNames(String str, int i, int i2, Locale locale) {
        Map map;
        if (i2 == 0) {
            map = retrieveFieldValueNamesImpl(str, i, 1, locale);
            for (int i3 : REST_OF_STYLES) {
                map.putAll(retrieveFieldValueNamesImpl(str, i, i3, locale));
            }
        } else {
            map = retrieveFieldValueNamesImpl(str, i, i2, locale);
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    private static String normalizeCalendarType(String str) {
        if (str.equals("gregory") || str.equals("iso8601")) {
            return "gregorian";
        }
        if (str.startsWith("islamic")) {
            return "islamic";
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Map retrieveFieldValueNamesImpl(java.lang.String r4, int r5, int r6, java.util.Locale r7) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.locale.provider.CalendarDataUtility.retrieveFieldValueNamesImpl(java.lang.String, int, int, java.util.Locale):java.util.Map");
    }

    private static String[] getNames(String str, int i, int i2, Locale locale) {
        int context = toContext(i2);
        int width = toWidth(i2);
        DateFormatSymbols dateFormatSymbols = getDateFormatSymbols(str, locale);
        if (i != 0) {
            if (i == 2) {
                return dateFormatSymbols.getMonths(context, width);
            }
            if (i == 7) {
                return dateFormatSymbols.getWeekdays(context, width);
            }
            if (i == 9) {
                return dateFormatSymbols.getAmPmStrings();
            }
            throw new UnsupportedOperationException("Unknown field: " + i);
        } else if (width == 0) {
            return dateFormatSymbols.getEras();
        } else {
            if (width == 1) {
                return dateFormatSymbols.getEraNames();
            }
            if (width == 2) {
                return dateFormatSymbols.getNarrowEras();
            }
            throw new UnsupportedOperationException("Unknown width: " + width);
        }
    }

    private static DateFormatSymbols getDateFormatSymbols(String str, Locale locale) {
        return new DateFormatSymbols(ULocale.forLocale(locale), normalizeCalendarType(str));
    }

    private static int toWidth(int i) {
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            if (i == 4 || i == 32772) {
                return 2;
            }
            switch (i) {
                case 32769:
                    return 0;
                case 32770:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid style: " + i);
            }
        }
        return 1;
    }

    private static int toContext(int i) {
        if (i == 1 || i == 2 || i == 4) {
            return 0;
        }
        if (i != 32772) {
            switch (i) {
                case 32769:
                case 32770:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid style: " + i);
            }
        }
        return 1;
    }
}
