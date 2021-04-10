package sun.util.locale.provider;

import android.icu.text.DateFormatSymbols;
import android.icu.util.ULocale;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class CalendarDataUtility {
    private static final String BUDDHIST_CALENDAR = "buddhist";
    private static final String GREGORIAN_CALENDAR = "gregorian";
    private static final String ISLAMIC_CALENDAR = "islamic";
    private static final String JAPANESE_CALENDAR = "japanese";
    private static int[] REST_OF_STYLES = {Calendar.SHORT_STANDALONE, 2, Calendar.LONG_STANDALONE, 4, Calendar.NARROW_STANDALONE};

    private CalendarDataUtility() {
    }

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
        L_0x001b:
            goto L_0x003a
        L_0x001c:
            java.lang.String r1 = "islamic"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001b
            r0 = r4
            goto L_0x003b
        L_0x0026:
            java.lang.String r1 = "japanese"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001b
            r0 = r3
            goto L_0x003b
        L_0x0030:
            java.lang.String r1 = "buddhist"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001b
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
            java.lang.String[] r1 = getNames(r5, r6, r8, r9)
            int r2 = r1.length
            if (r7 < r2) goto L_0x0053
            return r0
        L_0x0053:
            r0 = r1[r7]
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.locale.provider.CalendarDataUtility.retrieveFieldValueName(java.lang.String, int, int, int, java.util.Locale):java.lang.String");
    }

    public static String retrieveJavaTimeFieldValueName(String id, int field, int value, int style, Locale locale) {
        return retrieveFieldValueName(id, field, value, style, locale);
    }

    public static Map<String, Integer> retrieveFieldValueNames(String id, int field, int style, Locale locale) {
        Map<String, Integer> names;
        if (style == 0) {
            names = retrieveFieldValueNamesImpl(id, field, 1, locale);
            for (int st : REST_OF_STYLES) {
                names.putAll(retrieveFieldValueNamesImpl(id, field, st, locale));
            }
        } else {
            names = retrieveFieldValueNamesImpl(id, field, style, locale);
        }
        if (names.isEmpty()) {
            return null;
        }
        return names;
    }

    public static Map<String, Integer> retrieveJavaTimeFieldValueNames(String id, int field, int style, Locale locale) {
        return retrieveFieldValueNames(id, field, style, locale);
    }

    private static String normalizeCalendarType(String requestID) {
        if (requestID.equals("gregory") || requestID.equals("iso8601")) {
            return GREGORIAN_CALENDAR;
        }
        if (requestID.startsWith(ISLAMIC_CALENDAR)) {
            return ISLAMIC_CALENDAR;
        }
        return requestID;
    }

    private static Map<String, Integer> retrieveFieldValueNamesImpl(String id, int field, int style, Locale locale) {
        String[] names = getNames(id, field, style, locale);
        int skipped = 0;
        int offset = 0;
        if (field == 0) {
            String normalizeCalendarType = normalizeCalendarType(id);
            char c = 65535;
            int hashCode = normalizeCalendarType.hashCode();
            if (hashCode != -1581060683) {
                if (hashCode != -752730191) {
                    if (hashCode == 2093696456 && normalizeCalendarType.equals(ISLAMIC_CALENDAR)) {
                        c = 1;
                    }
                } else if (normalizeCalendarType.equals(JAPANESE_CALENDAR)) {
                    c = 2;
                }
            } else if (normalizeCalendarType.equals(BUDDHIST_CALENDAR)) {
                c = 0;
            }
            if (c == 0 || c == 1) {
                offset = 1;
            } else if (c == 2) {
                skipped = 232;
                offset = -231;
            }
        }
        Map<String, Integer> result = new LinkedHashMap<>();
        for (int i = skipped; i < names.length; i++) {
            if (!names[i].isEmpty() && result.put(names[i], Integer.valueOf(i + offset)) != null) {
                return new LinkedHashMap();
            }
        }
        return result;
    }

    private static String[] getNames(String id, int field, int style, Locale locale) {
        int context = toContext(style);
        int width = toWidth(style);
        DateFormatSymbols symbols = getDateFormatSymbols(id, locale);
        if (field != 0) {
            if (field == 2) {
                return symbols.getMonths(context, width);
            }
            if (field == 7) {
                return symbols.getWeekdays(context, width);
            }
            if (field == 9) {
                return symbols.getAmPmStrings();
            }
            throw new UnsupportedOperationException("Unknown field: " + field);
        } else if (width == 0) {
            return symbols.getEras();
        } else {
            if (width == 1) {
                return symbols.getEraNames();
            }
            if (width == 2) {
                return symbols.getNarrowEras();
            }
            throw new UnsupportedOperationException("Unknown width: " + width);
        }
    }

    private static DateFormatSymbols getDateFormatSymbols(String id, Locale locale) {
        return new DateFormatSymbols(ULocale.forLocale(locale), normalizeCalendarType(id));
    }

    private static int toWidth(int style) {
        if (style == 1) {
            return 0;
        }
        if (style != 2) {
            if (style == 4 || style == 32772) {
                return 2;
            }
            switch (style) {
                case Calendar.SHORT_STANDALONE:
                    return 0;
                case Calendar.LONG_STANDALONE:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid style: " + style);
            }
        }
        return 1;
    }

    private static int toContext(int style) {
        if (style == 1 || style == 2 || style == 4) {
            return 0;
        }
        if (style != 32772) {
            switch (style) {
                case Calendar.SHORT_STANDALONE:
                case Calendar.LONG_STANDALONE:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid style: " + style);
            }
        }
        return 1;
    }
}
