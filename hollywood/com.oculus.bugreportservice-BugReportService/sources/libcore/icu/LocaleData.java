package libcore.icu;

import android.icu.impl.ICUResourceBundle;
import android.icu.text.NumberingSystem;
import android.icu.util.UResourceBundle;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import libcore.util.Objects;

public final class LocaleData {
    private static final HashMap localeDataCache = new HashMap();
    public String NaN;
    public String[] amPm;
    public String currencyPattern;
    public char decimalSeparator;
    public String[] eras;
    public String exponentSeparator;
    public Integer firstDayOfWeek;
    public String fullDateFormat;
    public String fullTimeFormat;
    public char groupingSeparator;
    public String infinity;
    public String integerPattern;
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
        return "und".equals(locale.toLanguageTag()) ? Locale.ROOT : locale;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1 = (libcore.icu.LocaleData) libcore.icu.LocaleData.localeDataCache.get(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r1 == null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        libcore.icu.LocaleData.localeDataCache.put(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r3 = initLocaleData(r3);
        r2 = libcore.icu.LocaleData.localeDataCache;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static libcore.icu.LocaleData get(java.util.Locale r3) {
        /*
            if (r3 == 0) goto L_0x0036
            java.lang.String r0 = r3.toLanguageTag()
            java.util.HashMap r1 = libcore.icu.LocaleData.localeDataCache
            monitor-enter(r1)
            java.util.HashMap r2 = libcore.icu.LocaleData.localeDataCache     // Catch:{ all -> 0x0033 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0033 }
            libcore.icu.LocaleData r2 = (libcore.icu.LocaleData) r2     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0015
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            return r2
        L_0x0015:
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            libcore.icu.LocaleData r3 = initLocaleData(r3)
            java.util.HashMap r2 = libcore.icu.LocaleData.localeDataCache
            monitor-enter(r2)
            java.util.HashMap r1 = libcore.icu.LocaleData.localeDataCache     // Catch:{ all -> 0x0030 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0030 }
            libcore.icu.LocaleData r1 = (libcore.icu.LocaleData) r1     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0029
            monitor-exit(r2)     // Catch:{ all -> 0x0030 }
            return r1
        L_0x0029:
            java.util.HashMap r1 = libcore.icu.LocaleData.localeDataCache     // Catch:{ all -> 0x0030 }
            r1.put(r0, r3)     // Catch:{ all -> 0x0030 }
            monitor-exit(r2)     // Catch:{ all -> 0x0030 }
            return r3
        L_0x0030:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0030 }
            throw r3
        L_0x0033:
            r3 = move-exception
            monitor-exit(r1)
            throw r3
        L_0x0036:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "locale == null"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.icu.LocaleData.get(java.util.Locale):libcore.icu.LocaleData");
    }

    public String toString() {
        return Objects.toString(this);
    }

    public String getDateFormat(int i) {
        if (i == 0) {
            return this.fullDateFormat;
        }
        if (i == 1) {
            return this.longDateFormat;
        }
        if (i == 2) {
            return this.mediumDateFormat;
        }
        if (i == 3) {
            return this.shortDateFormat;
        }
        throw new AssertionError();
    }

    public String getTimeFormat(int i) {
        if (i == 0) {
            return this.fullTimeFormat;
        }
        if (i == 1) {
            return this.longTimeFormat;
        }
        if (i == 2) {
            Boolean bool = DateFormat.is24Hour;
            if (bool == null) {
                return this.mediumTimeFormat;
            }
            return bool.booleanValue() ? this.timeFormat_Hms : this.timeFormat_hms;
        } else if (i == 3) {
            Boolean bool2 = DateFormat.is24Hour;
            if (bool2 == null) {
                return this.shortTimeFormat;
            }
            return bool2.booleanValue() ? this.timeFormat_Hm : this.timeFormat_hm;
        } else {
            throw new AssertionError();
        }
    }

    private static LocaleData initLocaleData(Locale locale) {
        LocaleData localeData = new LocaleData();
        if (ICU.initLocaleDataNative(locale.toLanguageTag(), localeData)) {
            initializePatternSeparator(localeData, locale);
            localeData.timeFormat_hm = ICU.getBestDateTimePattern("hm", locale);
            localeData.timeFormat_Hm = ICU.getBestDateTimePattern("Hm", locale);
            localeData.timeFormat_hms = ICU.getBestDateTimePattern("hms", locale);
            localeData.timeFormat_Hms = ICU.getBestDateTimePattern("Hms", locale);
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
        throw new AssertionError((Object) ("couldn't initialize LocaleData for locale " + locale));
    }

    private static void initializePatternSeparator(LocaleData localeData, Locale locale) {
        String str;
        NumberingSystem instance = NumberingSystem.getInstance(locale);
        if (instance == null || instance.getRadix() != 10 || instance.isAlgorithmic()) {
            str = "latn";
        } else {
            str = instance.getName();
        }
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", locale);
        String str2 = null;
        if (!"latn".equals(str)) {
            try {
                str2 = iCUResourceBundle.getStringWithFallback("NumberElements/" + str + "/symbols/list");
            } catch (MissingResourceException unused) {
            }
        }
        if (str2 == null) {
            try {
                str2 = iCUResourceBundle.getStringWithFallback("NumberElements/latn/symbols/list");
            } catch (MissingResourceException unused2) {
            }
        }
        if (str2 == null || str2.isEmpty()) {
            str2 = ";";
        }
        localeData.patternSeparator = str2.charAt(0);
    }
}
