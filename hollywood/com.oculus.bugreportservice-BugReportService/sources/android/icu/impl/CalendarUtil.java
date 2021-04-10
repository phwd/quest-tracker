package android.icu.impl;

import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.TreeMap;

public final class CalendarUtil {
    public static String getCalendarType(ULocale uLocale) {
        String keywordValue = uLocale.getKeywordValue("calendar");
        if (keywordValue != null) {
            return keywordValue.toLowerCase(Locale.ROOT);
        }
        ULocale createCanonical = ULocale.createCanonical(uLocale.toString());
        String keywordValue2 = createCanonical.getKeywordValue("calendar");
        if (keywordValue2 != null) {
            return keywordValue2;
        }
        return CalendarPreferences.INSTANCE.getCalendarTypeForRegion(ULocale.getRegionForSupplementalData(createCanonical, true));
    }

    private static final class CalendarPreferences extends UResource$Sink {
        private static final CalendarPreferences INSTANCE = new CalendarPreferences();
        Map prefs = new TreeMap();

        CalendarPreferences() {
            try {
                ((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "supplementalData")).getAllItemsWithFallback("calendarPreferenceData", this);
            } catch (MissingResourceException unused) {
            }
        }

        /* access modifiers changed from: package-private */
        public String getCalendarTypeForRegion(String str) {
            String str2 = (String) this.prefs.get(str);
            return str2 == null ? "gregorian" : str2;
        }

        @Override // android.icu.impl.UResource$Sink
        public void put(UResource$Key uResource$Key, UResource$Value uResource$Value, boolean z) {
            UResource$Table table = uResource$Value.getTable();
            for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                if (uResource$Value.getArray().getValue(0, uResource$Value)) {
                    String string = uResource$Value.getString();
                    if (!string.equals("gregorian")) {
                        this.prefs.put(uResource$Key.toString(), string);
                    }
                }
            }
        }
    }
}
