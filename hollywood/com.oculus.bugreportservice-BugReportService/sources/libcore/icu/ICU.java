package libcore.icu;

import java.util.Locale;
import libcore.util.BasicLruCache;

public final class ICU {
    private static final BasicLruCache CACHED_PATTERNS = new BasicLruCache(8);

    private static native String getBestDateTimePatternNative(String str, String str2);

    public static native String getCldrVersion();

    public static native String getCurrencyCode(String str);

    public static native String getIcuVersion();

    public static native String getUnicodeVersion();

    static native boolean initLocaleDataNative(String str, LocaleData localeData);

    private static native String toLowerCase(String str, String str2);

    private static native String toUpperCase(String str, String str2);

    public static String getBestDateTimePattern(String str, Locale locale) {
        String str2;
        String languageTag = locale.toLanguageTag();
        String str3 = str + "\t" + languageTag;
        synchronized (CACHED_PATTERNS) {
            str2 = (String) CACHED_PATTERNS.get(str3);
            if (str2 == null) {
                str2 = getBestDateTimePatternNative(str, languageTag);
                CACHED_PATTERNS.put(str3, str2);
            }
        }
        return str2;
    }

    public static String toLowerCase(String str, Locale locale) {
        return toLowerCase(str, locale.toLanguageTag());
    }

    public static String toUpperCase(String str, Locale locale) {
        return toUpperCase(str, locale.toLanguageTag());
    }
}
