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
    private static final Comparator ZONE_STRINGS_COMPARATOR = new Comparator() {
        /* class libcore.icu.TimeZoneNames.AnonymousClass1 */

        public int compare(String[] strArr, String[] strArr2) {
            return strArr[0].compareTo(strArr2[0]);
        }
    };
    private static final String[] availableTimeZoneIds = TimeZone.getAvailableIDs();
    private static final ZoneStringsCache cachedZoneStrings = new ZoneStringsCache();

    /* access modifiers changed from: private */
    public static native void fillZoneStrings(String str, String[][] strArr);

    public static class ZoneStringsCache extends BasicLruCache {
        public ZoneStringsCache() {
            super(5);
        }

        /* access modifiers changed from: protected */
        public String[][] create(Locale locale) {
            long nanoTime = System.nanoTime();
            String[][] strArr = (String[][]) Array.newInstance(String.class, new int[]{TimeZoneNames.availableTimeZoneIds.length, 5});
            for (int i = 0; i < TimeZoneNames.availableTimeZoneIds.length; i++) {
                strArr[i][0] = TimeZoneNames.availableTimeZoneIds[i];
            }
            long nanoTime2 = System.nanoTime();
            TimeZoneNames.fillZoneStrings(locale.toLanguageTag(), strArr);
            long nanoTime3 = System.nanoTime();
            addOffsetStrings(strArr);
            internStrings(strArr);
            long nanoTime4 = System.nanoTime();
            long millis = TimeUnit.NANOSECONDS.toMillis(nanoTime3 - nanoTime2);
            System.logI("Loaded time zone names for \"" + locale + "\" in " + TimeUnit.NANOSECONDS.toMillis(nanoTime4 - nanoTime) + "ms (" + millis + "ms in ICU)");
            return strArr;
        }

        private void addOffsetStrings(String[][] strArr) {
            for (int i = 0; i < strArr.length; i++) {
                TimeZone timeZone = null;
                for (int i2 = 1; i2 < 5; i2++) {
                    if (strArr[i][i2] == null) {
                        if (timeZone == null) {
                            timeZone = TimeZone.getTimeZone(strArr[i][0]);
                        }
                        int rawOffset = timeZone.getRawOffset();
                        if (i2 == 3 || i2 == 4) {
                            rawOffset += timeZone.getDSTSavings();
                        }
                        strArr[i][i2] = TimeZone.createGmtOffsetString(true, true, rawOffset);
                    }
                }
            }
        }

        private void internStrings(String[][] strArr) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < strArr.length; i++) {
                for (int i2 = 1; i2 < 5; i2++) {
                    String str = strArr[i][i2];
                    String str2 = (String) hashMap.get(str);
                    if (str2 == null) {
                        hashMap.put(str, str);
                    } else {
                        strArr[i][i2] = str2;
                    }
                }
            }
        }
    }

    public static String getDisplayName(String[][] strArr, String str, boolean z, int i) {
        int binarySearch = Arrays.binarySearch(strArr, new String[]{str}, ZONE_STRINGS_COMPARATOR);
        if (binarySearch < 0) {
            return null;
        }
        String[] strArr2 = strArr[binarySearch];
        return z ? i == 1 ? strArr2[3] : strArr2[4] : i == 1 ? strArr2[1] : strArr2[2];
    }

    public static String[][] getZoneStrings(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return (String[][]) cachedZoneStrings.get(locale);
    }
}
