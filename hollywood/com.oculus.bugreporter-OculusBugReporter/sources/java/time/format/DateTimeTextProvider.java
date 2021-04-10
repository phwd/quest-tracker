package java.time.format;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.util.UResourceBundle;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.util.locale.provider.CalendarDataUtility;

/* access modifiers changed from: package-private */
public class DateTimeTextProvider {
    private static final ConcurrentMap<Map.Entry<TemporalField, Locale>, Object> CACHE = new ConcurrentHashMap(16, 0.75f, 2);
    private static final Comparator<Map.Entry<String, Long>> COMPARATOR = new Comparator<Map.Entry<String, Long>>() {
        /* class java.time.format.DateTimeTextProvider.AnonymousClass1 */

        public int compare(Map.Entry<String, Long> obj1, Map.Entry<String, Long> obj2) {
            return obj2.getKey().length() - obj1.getKey().length();
        }
    };

    DateTimeTextProvider() {
    }

    static DateTimeTextProvider getInstance() {
        return new DateTimeTextProvider();
    }

    public String getText(TemporalField field, long value, TextStyle style, Locale locale) {
        Object store = findStore(field, locale);
        if (store instanceof LocaleStore) {
            return ((LocaleStore) store).getText(value, style);
        }
        return null;
    }

    public String getText(Chronology chrono, TemporalField field, long value, TextStyle style, Locale locale) {
        int fieldValue;
        int fieldIndex;
        if (chrono == IsoChronology.INSTANCE || !(field instanceof ChronoField)) {
            return getText(field, value, style, locale);
        }
        if (field == ChronoField.ERA) {
            fieldIndex = 0;
            if (chrono != JapaneseChronology.INSTANCE) {
                fieldValue = (int) value;
            } else if (value == -999) {
                fieldValue = 0;
            } else {
                fieldValue = ((int) value) + 2;
            }
        } else if (field == ChronoField.MONTH_OF_YEAR) {
            fieldIndex = 2;
            fieldValue = ((int) value) - 1;
        } else if (field == ChronoField.DAY_OF_WEEK) {
            fieldIndex = 7;
            fieldValue = ((int) value) + 1;
            if (fieldValue > 7) {
                fieldValue = 1;
            }
        } else if (field != ChronoField.AMPM_OF_DAY) {
            return null;
        } else {
            fieldIndex = 9;
            fieldValue = (int) value;
        }
        return CalendarDataUtility.retrieveJavaTimeFieldValueName(chrono.getCalendarType(), fieldIndex, fieldValue, style.toCalendarStyle(), locale);
    }

    public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField field, TextStyle style, Locale locale) {
        Object store = findStore(field, locale);
        if (store instanceof LocaleStore) {
            return ((LocaleStore) store).getTextIterator(style);
        }
        return null;
    }

    public Iterator<Map.Entry<String, Long>> getTextIterator(Chronology chrono, TemporalField field, TextStyle style, Locale locale) {
        int fieldIndex;
        if (chrono == IsoChronology.INSTANCE || !(field instanceof ChronoField)) {
            return getTextIterator(field, style, locale);
        }
        int i = AnonymousClass2.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) field).ordinal()];
        if (i == 1) {
            fieldIndex = 0;
        } else if (i == 2) {
            fieldIndex = 2;
        } else if (i == 3) {
            fieldIndex = 7;
        } else if (i != 4) {
            return null;
        } else {
            fieldIndex = 9;
        }
        Map<String, Integer> map = CalendarDataUtility.retrieveJavaTimeFieldValueNames(chrono.getCalendarType(), fieldIndex, style == null ? 0 : style.toCalendarStyle(), locale);
        if (map == null) {
            return null;
        }
        List<Map.Entry<String, Long>> list = new ArrayList<>(map.size());
        if (fieldIndex == 0) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int era = entry.getValue().intValue();
                if (chrono == JapaneseChronology.INSTANCE) {
                    era = era == 0 ? -999 : era - 2;
                }
                list.add(createEntry(entry.getKey(), Long.valueOf((long) era)));
            }
        } else if (fieldIndex == 2) {
            for (Map.Entry<String, Integer> entry2 : map.entrySet()) {
                list.add(createEntry(entry2.getKey(), Long.valueOf((long) (entry2.getValue().intValue() + 1))));
            }
        } else if (fieldIndex != 7) {
            for (Map.Entry<String, Integer> entry3 : map.entrySet()) {
                list.add(createEntry(entry3.getKey(), Long.valueOf((long) entry3.getValue().intValue())));
            }
        } else {
            for (Map.Entry<String, Integer> entry4 : map.entrySet()) {
                list.add(createEntry(entry4.getKey(), Long.valueOf((long) toWeekDay(entry4.getValue().intValue()))));
            }
        }
        return list.iterator();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.time.format.DateTimeTextProvider$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$java$time$temporal$ChronoField = new int[ChronoField.values().length];

        static {
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.ERA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.DAY_OF_WEEK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.AMPM_OF_DAY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private Object findStore(TemporalField field, Locale locale) {
        Map.Entry<TemporalField, Locale> key = createEntry(field, locale);
        Object store = CACHE.get(key);
        if (store != null) {
            return store;
        }
        CACHE.putIfAbsent(key, createStore(field, locale));
        return CACHE.get(key);
    }

    private static int toWeekDay(int calWeekDay) {
        if (calWeekDay == 1) {
            return 7;
        }
        return calWeekDay - 1;
    }

    private Object createStore(TemporalField field, Locale locale) {
        Map<String, Integer> displayNames;
        String name;
        Map<String, Integer> displayNames2;
        Map<TextStyle, Map<Long, String>> styleMap = new HashMap<>();
        int i = 0;
        if (field == ChronoField.ERA) {
            TextStyle[] values = TextStyle.values();
            for (TextStyle textStyle : values) {
                if (!textStyle.isStandalone() && (displayNames2 = CalendarDataUtility.retrieveJavaTimeFieldValueNames("gregory", 0, textStyle.toCalendarStyle(), locale)) != null) {
                    Map<Long, String> map = new HashMap<>();
                    for (Map.Entry<String, Integer> entry : displayNames2.entrySet()) {
                        map.put(Long.valueOf((long) entry.getValue().intValue()), entry.getKey());
                    }
                    if (!map.isEmpty()) {
                        styleMap.put(textStyle, map);
                    }
                }
            }
            return new LocaleStore(styleMap);
        } else if (field == ChronoField.MONTH_OF_YEAR) {
            TextStyle[] values2 = TextStyle.values();
            int length = values2.length;
            while (i < length) {
                TextStyle textStyle2 = values2[i];
                Map<String, Integer> displayNames3 = CalendarDataUtility.retrieveJavaTimeFieldValueNames("gregory", 2, textStyle2.toCalendarStyle(), locale);
                Map<Long, String> map2 = new HashMap<>();
                if (displayNames3 != null) {
                    for (Map.Entry<String, Integer> entry2 : displayNames3.entrySet()) {
                        map2.put(Long.valueOf((long) (entry2.getValue().intValue() + 1)), entry2.getKey());
                    }
                } else {
                    int month = 0;
                    while (month <= 11 && (name = CalendarDataUtility.retrieveJavaTimeFieldValueName("gregory", 2, month, textStyle2.toCalendarStyle(), locale)) != null) {
                        map2.put(Long.valueOf((long) (month + 1)), name);
                        month++;
                    }
                }
                if (!map2.isEmpty()) {
                    styleMap.put(textStyle2, map2);
                }
                i++;
            }
            return new LocaleStore(styleMap);
        } else if (field == ChronoField.DAY_OF_WEEK) {
            TextStyle[] values3 = TextStyle.values();
            int length2 = values3.length;
            while (i < length2) {
                TextStyle textStyle3 = values3[i];
                Map<String, Integer> displayNames4 = CalendarDataUtility.retrieveJavaTimeFieldValueNames("gregory", 7, textStyle3.toCalendarStyle(), locale);
                Map<Long, String> map3 = new HashMap<>();
                if (displayNames4 != null) {
                    for (Map.Entry<String, Integer> entry3 : displayNames4.entrySet()) {
                        map3.put(Long.valueOf((long) toWeekDay(entry3.getValue().intValue())), entry3.getKey());
                    }
                } else {
                    for (int wday = 1; wday <= 7; wday++) {
                        String name2 = CalendarDataUtility.retrieveJavaTimeFieldValueName("gregory", 7, wday, textStyle3.toCalendarStyle(), locale);
                        if (name2 == null) {
                            break;
                        }
                        map3.put(Long.valueOf((long) toWeekDay(wday)), name2);
                    }
                }
                if (!map3.isEmpty()) {
                    styleMap.put(textStyle3, map3);
                }
                i++;
            }
            return new LocaleStore(styleMap);
        } else if (field == ChronoField.AMPM_OF_DAY) {
            TextStyle[] values4 = TextStyle.values();
            int length3 = values4.length;
            while (i < length3) {
                TextStyle textStyle4 = values4[i];
                if (!textStyle4.isStandalone() && (displayNames = CalendarDataUtility.retrieveJavaTimeFieldValueNames("gregory", 9, textStyle4.toCalendarStyle(), locale)) != null) {
                    Map<Long, String> map4 = new HashMap<>();
                    for (Map.Entry<String, Integer> entry4 : displayNames.entrySet()) {
                        map4.put(Long.valueOf((long) entry4.getValue().intValue()), entry4.getKey());
                    }
                    if (!map4.isEmpty()) {
                        styleMap.put(textStyle4, map4);
                    }
                }
                i++;
            }
            return new LocaleStore(styleMap);
        } else if (field != IsoFields.QUARTER_OF_YEAR) {
            return "";
        } else {
            ICUResourceBundle quartersRb = ((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale)).getWithFallback("calendar/gregorian/quarters");
            ICUResourceBundle formatRb = quartersRb.getWithFallback("format");
            ICUResourceBundle standaloneRb = quartersRb.getWithFallback("stand-alone");
            styleMap.put(TextStyle.FULL, extractQuarters(formatRb, "wide"));
            styleMap.put(TextStyle.FULL_STANDALONE, extractQuarters(standaloneRb, "wide"));
            styleMap.put(TextStyle.SHORT, extractQuarters(formatRb, "abbreviated"));
            styleMap.put(TextStyle.SHORT_STANDALONE, extractQuarters(standaloneRb, "abbreviated"));
            styleMap.put(TextStyle.NARROW, extractQuarters(formatRb, "narrow"));
            styleMap.put(TextStyle.NARROW_STANDALONE, extractQuarters(standaloneRb, "narrow"));
            return new LocaleStore(styleMap);
        }
    }

    private static Map<Long, String> extractQuarters(ICUResourceBundle rb, String key) {
        String[] names = rb.getWithFallback(key).getStringArray();
        Map<Long, String> map = new HashMap<>();
        for (int q = 0; q < names.length; q++) {
            map.put(Long.valueOf((long) (q + 1)), names[q]);
        }
        return map;
    }

    /* access modifiers changed from: private */
    public static <A, B> Map.Entry<A, B> createEntry(A text, B field) {
        return new AbstractMap.SimpleImmutableEntry(text, field);
    }

    /* access modifiers changed from: package-private */
    public static final class LocaleStore {
        private final Map<TextStyle, List<Map.Entry<String, Long>>> parsable;
        private final Map<TextStyle, Map<Long, String>> valueTextMap;

        /* JADX WARNING: Removed duplicated region for block: B:6:0x003c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        LocaleStore(java.util.Map<java.time.format.TextStyle, java.util.Map<java.lang.Long, java.lang.String>> r11) {
            /*
            // Method dump skipped, instructions count: 139
            */
            throw new UnsupportedOperationException("Method not decompiled: java.time.format.DateTimeTextProvider.LocaleStore.<init>(java.util.Map):void");
        }

        /* access modifiers changed from: package-private */
        public String getText(long value, TextStyle style) {
            Map<Long, String> map = this.valueTextMap.get(style);
            if (map != null) {
                return map.get(Long.valueOf(value));
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<String, Long>> getTextIterator(TextStyle style) {
            List<Map.Entry<String, Long>> list = this.parsable.get(style);
            if (list != null) {
                return list.iterator();
            }
            return null;
        }
    }
}
