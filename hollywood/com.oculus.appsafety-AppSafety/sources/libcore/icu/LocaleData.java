package libcore.icu;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.text.NumberingSystem;
import android.icu.util.UResourceBundle;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import libcore.util.Objects;
import sun.util.locale.LanguageTag;

public final class LocaleData {
    private static final HashMap<String, LocaleData> localeDataCache = new HashMap<>();
    public String NaN;
    public String[] amPm;
    public String currencyPattern;
    public String currencySymbol;
    public char decimalSeparator;
    public String[] eras;
    public String exponentSeparator;
    public Integer firstDayOfWeek;
    public String fullDateFormat;
    public String fullTimeFormat;
    public char groupingSeparator;
    public String infinity;
    public String integerPattern;
    public String internationalCurrencySymbol;
    public String longDateFormat;
    public String[] longMonthNames;
    public String[] longStandAloneMonthNames;
    public String[] longStandAloneWeekdayNames;
    public String longTimeFormat;
    public String[] longWeekdayNames;
    public String mediumDateFormat;
    public String mediumTimeFormat;
    public Integer minimalDaysInFirstWeek;
    public String minusSign;
    public char monetarySeparator;
    public String narrowAm;
    public String narrowPm;
    public String numberPattern;
    public char patternSeparator;
    public String perMill;
    public String percent;
    public String percentPattern;
    public String shortDateFormat;
    public String[] shortMonthNames;
    public String[] shortStandAloneMonthNames;
    public String[] shortStandAloneWeekdayNames;
    public String shortTimeFormat;
    public String[] shortWeekdayNames;
    public String timeFormat_Hm;
    public String timeFormat_Hms;
    public String timeFormat_hm;
    public String timeFormat_hms;
    public String[] tinyMonthNames;
    public String[] tinyStandAloneMonthNames;
    public String[] tinyStandAloneWeekdayNames;
    public String[] tinyWeekdayNames;
    public String today;
    public String tomorrow;
    public String yesterday;
    public char zeroDigit;

    static {
        get(Locale.ROOT);
        get(Locale.US);
        get(Locale.getDefault());
    }

    private LocaleData() {
    }

    public static Locale mapInvalidAndNullLocales(Locale locale) {
        if (locale == null) {
            return Locale.getDefault();
        }
        if (LanguageTag.UNDETERMINED.equals(locale.toLanguageTag())) {
            return Locale.ROOT;
        }
        return locale;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1 = libcore.icu.LocaleData.localeDataCache.get(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r1 == null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        libcore.icu.LocaleData.localeDataCache.put(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r2 = initLocaleData(r5);
        r3 = libcore.icu.LocaleData.localeDataCache;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static libcore.icu.LocaleData get(java.util.Locale r5) {
        /*
            if (r5 == 0) goto L_0x0036
            java.lang.String r0 = r5.toLanguageTag()
            java.util.HashMap<java.lang.String, libcore.icu.LocaleData> r1 = libcore.icu.LocaleData.localeDataCache
            monitor-enter(r1)
            java.util.HashMap<java.lang.String, libcore.icu.LocaleData> r2 = libcore.icu.LocaleData.localeDataCache     // Catch:{ all -> 0x0033 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0033 }
            libcore.icu.LocaleData r2 = (libcore.icu.LocaleData) r2     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0015
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            return r2
        L_0x0015:
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            libcore.icu.LocaleData r2 = initLocaleData(r5)
            java.util.HashMap<java.lang.String, libcore.icu.LocaleData> r3 = libcore.icu.LocaleData.localeDataCache
            monitor-enter(r3)
            java.util.HashMap<java.lang.String, libcore.icu.LocaleData> r1 = libcore.icu.LocaleData.localeDataCache     // Catch:{ all -> 0x0030 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0030 }
            libcore.icu.LocaleData r1 = (libcore.icu.LocaleData) r1     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0029
            monitor-exit(r3)     // Catch:{ all -> 0x0030 }
            return r1
        L_0x0029:
            java.util.HashMap<java.lang.String, libcore.icu.LocaleData> r4 = libcore.icu.LocaleData.localeDataCache     // Catch:{ all -> 0x0030 }
            r4.put(r0, r2)     // Catch:{ all -> 0x0030 }
            monitor-exit(r3)     // Catch:{ all -> 0x0030 }
            return r2
        L_0x0030:
            r1 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0030 }
            throw r1
        L_0x0033:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x0036:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "locale == null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.icu.LocaleData.get(java.util.Locale):libcore.icu.LocaleData");
    }

    public String toString() {
        return Objects.toString(this);
    }

    public String getDateFormat(int style) {
        if (style == 0) {
            return this.fullDateFormat;
        }
        if (style == 1) {
            return this.longDateFormat;
        }
        if (style == 2) {
            return this.mediumDateFormat;
        }
        if (style == 3) {
            return this.shortDateFormat;
        }
        throw new AssertionError();
    }

    public String getTimeFormat(int style) {
        if (style == 0) {
            return this.fullTimeFormat;
        }
        if (style == 1) {
            return this.longTimeFormat;
        }
        if (style != 2) {
            if (style != 3) {
                throw new AssertionError();
            } else if (DateFormat.is24Hour == null) {
                return this.shortTimeFormat;
            } else {
                return DateFormat.is24Hour.booleanValue() ? this.timeFormat_Hm : this.timeFormat_hm;
            }
        } else if (DateFormat.is24Hour == null) {
            return this.mediumTimeFormat;
        } else {
            return DateFormat.is24Hour.booleanValue() ? this.timeFormat_Hms : this.timeFormat_hms;
        }
    }

    private static LocaleData initLocaleData(Locale locale) {
        LocaleData localeData = new LocaleData();
        if (ICU.initLocaleDataNative(locale.toLanguageTag(), localeData)) {
            initializePatternSeparator(localeData, locale);
            localeData.timeFormat_hm = ICU.getBestDateTimePattern("hm", locale);
            localeData.timeFormat_Hm = ICU.getBestDateTimePattern(android.icu.text.DateFormat.HOUR24_MINUTE, locale);
            localeData.timeFormat_hms = ICU.getBestDateTimePattern("hms", locale);
            localeData.timeFormat_Hms = ICU.getBestDateTimePattern(android.icu.text.DateFormat.HOUR24_MINUTE_SECOND, locale);
            String str = localeData.fullTimeFormat;
            if (str != null) {
                localeData.fullTimeFormat = str.replace('v', 'z');
            }
            String str2 = localeData.numberPattern;
            if (str2 != null) {
                localeData.integerPattern = str2.replaceAll("\\.[#,]*", "");
            }
            return localeData;
        }
        throw new AssertionError((Object) ("couldn't initialize LocaleData for locale " + ((Object) locale)));
    }

    private static void initializePatternSeparator(LocaleData localeData, Locale locale) {
        String nsName;
        NumberingSystem ns = NumberingSystem.getInstance(locale);
        if (ns == null || ns.getRadix() != 10 || ns.isAlgorithmic()) {
            nsName = "latn";
        } else {
            nsName = ns.getName();
        }
        ICUResourceBundle rb = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale);
        String patternSeparator2 = null;
        if (!"latn".equals(nsName)) {
            try {
                patternSeparator2 = rb.getStringWithFallback("NumberElements/" + nsName + "/symbols/list");
            } catch (MissingResourceException e) {
            }
        }
        if (patternSeparator2 == null) {
            try {
                patternSeparator2 = rb.getStringWithFallback("NumberElements/latn/symbols/list");
            } catch (MissingResourceException e2) {
            }
        }
        if (patternSeparator2 == null || patternSeparator2.isEmpty()) {
            patternSeparator2 = ";";
        }
        localeData.patternSeparator = patternSeparator2.charAt(0);
    }
}
