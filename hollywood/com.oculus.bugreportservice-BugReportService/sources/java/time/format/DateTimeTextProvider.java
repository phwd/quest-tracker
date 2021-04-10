package java.time.format;

import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.util.locale.provider.CalendarDataUtility;

/* access modifiers changed from: package-private */
public class DateTimeTextProvider {
    private static final ConcurrentMap CACHE = new ConcurrentHashMap(16, 0.75f, 2);
    private static final Comparator COMPARATOR = new Comparator() {
        /* class java.time.format.DateTimeTextProvider.AnonymousClass1 */

        public int compare(Map.Entry entry, Map.Entry entry2) {
            return ((String) entry2.getKey()).length() - ((String) entry.getKey()).length();
        }
    };

    public String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale) {
        throw null;
    }

    DateTimeTextProvider() {
    }

    public String getText(Chronology chronology, TemporalField temporalField, long j, TextStyle textStyle, Locale locale) {
        int i;
        if (chronology == IsoChronology.INSTANCE || !(temporalField instanceof ChronoField)) {
            return getText(temporalField, j, textStyle, locale);
        }
        int i2 = 7;
        int i3 = 0;
        if (temporalField == ChronoField.ERA) {
            if (chronology != JapaneseChronology.INSTANCE) {
                i = (int) j;
            } else if (j == -999) {
                i2 = 0;
            } else {
                i = ((int) j) + 2;
            }
            i2 = 0;
            i3 = i;
        } else if (temporalField == ChronoField.MONTH_OF_YEAR) {
            i3 = ((int) j) - 1;
            i2 = 2;
        } else if (temporalField == ChronoField.DAY_OF_WEEK) {
            i3 = ((int) j) + 1;
            if (i3 > 7) {
                i3 = 1;
            }
        } else if (temporalField != ChronoField.AMPM_OF_DAY) {
            return null;
        } else {
            i2 = 9;
            i3 = (int) j;
        }
        return CalendarDataUtility.retrieveJavaTimeFieldValueName(chronology.getCalendarType(), i2, i3, textStyle.toCalendarStyle(), locale);
    }

    /* access modifiers changed from: private */
    public static Map.Entry createEntry(Object obj, Object obj2) {
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    /* access modifiers changed from: package-private */
    public static final class LocaleStore {
        private final Map parsable;
        private final Map valueTextMap;

        LocaleStore(Map map) {
            this.valueTextMap = map;
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                    hashMap2.put((String) entry2.getValue(), DateTimeTextProvider.createEntry((String) entry2.getValue(), (Long) entry2.getKey()));
                }
                ArrayList arrayList2 = new ArrayList(hashMap2.values());
                Collections.sort(arrayList2, DateTimeTextProvider.COMPARATOR);
                hashMap.put((TextStyle) entry.getKey(), arrayList2);
                arrayList.addAll(arrayList2);
                hashMap.put(null, arrayList);
            }
            Collections.sort(arrayList, DateTimeTextProvider.COMPARATOR);
            this.parsable = hashMap;
        }

        /* access modifiers changed from: package-private */
        public String getText(long j, TextStyle textStyle) {
            Map map = (Map) this.valueTextMap.get(textStyle);
            if (map != null) {
                return (String) map.get(Long.valueOf(j));
            }
            return null;
        }
    }
}
