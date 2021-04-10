package libcore.icu;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import libcore.util.BasicLruCache;

public final class TimeZoneNames {
    public static final int LONG_NAME = 1;
    public static final int LONG_NAME_DST = 3;
    public static final int NAME_COUNT = 5;
    public static final int OLSON_NAME = 0;
    public static final int SHORT_NAME = 2;
    public static final int SHORT_NAME_DST = 4;
    private static final Comparator<String[]> ZONE_STRINGS_COMPARATOR = new Comparator<String[]>() {
        /* class libcore.icu.TimeZoneNames.AnonymousClass1 */

        public int compare(String[] lhs, String[] rhs) {
            return lhs[0].compareTo(rhs[0]);
        }
    };
    private static final String[] availableTimeZoneIds = TimeZone.getAvailableIDs();
    private static final ZoneStringsCache cachedZoneStrings = new ZoneStringsCache();

    /* access modifiers changed from: private */
    public static native void fillZoneStrings(String str, String[][] strArr);

    public static class ZoneStringsCache extends BasicLruCache<Locale, String[][]> {
        public ZoneStringsCache() {
            super(5);
        }

        /* access modifiers changed from: protected */
        public String[][] create(Locale locale) {
            long start = System.nanoTime();
            String[][] result = (String[][]) Array.newInstance(String.class, TimeZoneNames.availableTimeZoneIds.length, 5);
            for (int i = 0; i < TimeZoneNames.availableTimeZoneIds.length; i++) {
                result[i][0] = TimeZoneNames.availableTimeZoneIds[i];
            }
            long nativeStart = System.nanoTime();
            TimeZoneNames.fillZoneStrings(locale.toLanguageTag(), result);
            long nativeEnd = System.nanoTime();
            addOffsetStrings(result);
            internStrings(result);
            long end = System.nanoTime();
            long nativeDuration = TimeUnit.NANOSECONDS.toMillis(nativeEnd - nativeStart);
            long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
            System.logI("Loaded time zone names for \"" + ((Object) locale) + "\" in " + duration + "ms (" + nativeDuration + "ms in ICU)");
            return result;
        }

        private void addOffsetStrings(String[][] result) {
            for (int i = 0; i < result.length; i++) {
                TimeZone tz = null;
                for (int j = 1; j < 5; j++) {
                    if (result[i][j] == null) {
                        if (tz == null) {
                            tz = TimeZone.getTimeZone(result[i][0]);
                        }
                        int offsetMillis = tz.getRawOffset();
                        if (j == 3 || j == 4) {
                            offsetMillis += tz.getDSTSavings();
                        }
                        result[i][j] = TimeZone.createGmtOffsetString(true, true, offsetMillis);
                    }
                }
            }
        }

        private void internStrings(String[][] result) {
            HashMap<String, String> internTable = new HashMap<>();
            for (int i = 0; i < result.length; i++) {
                for (int j = 1; j < 5; j++) {
                    String original = result[i][j];
                    String nonDuplicate = internTable.get(original);
                    if (nonDuplicate == null) {
                        internTable.put(original, original);
                    } else {
                        result[i][j] = nonDuplicate;
                    }
                }
            }
        }
    }

    private TimeZoneNames() {
    }

    public static String getDisplayName(String[][] zoneStrings, String id, boolean daylight, int style) {
        int index = Arrays.binarySearch(zoneStrings, new String[]{id}, ZONE_STRINGS_COMPARATOR);
        if (index < 0) {
            return null;
        }
        String[] row = zoneStrings[index];
        return daylight ? style == 1 ? row[3] : row[4] : style == 1 ? row[1] : row[2];
    }

    public static String[][] getZoneStrings(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return (String[][]) cachedZoneStrings.get(locale);
    }
}
