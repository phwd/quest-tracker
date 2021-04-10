package android.icu.util;

import android.icu.impl.CacheBase;
import android.icu.impl.LocaleIDParser;
import android.icu.impl.SoftCache;
import android.icu.impl.locale.AsciiUtil;
import android.icu.impl.locale.BaseLocale;
import android.icu.impl.locale.Extension;
import android.icu.impl.locale.InternalLocaleBuilder;
import android.icu.impl.locale.KeyTypeData;
import android.icu.impl.locale.LanguageTag;
import android.icu.impl.locale.LocaleExtensions;
import android.icu.impl.locale.LocaleSyntaxException;
import android.icu.impl.locale.UnicodeLocaleExtension;
import android.icu.text.LocaleDisplayNames;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class ULocale implements Serializable, Comparable {
    public static Type ACTUAL_LOCALE = new Type();
    private static final SoftCache CACHE = new SoftCache() {
        /* class android.icu.util.ULocale.AnonymousClass2 */

        /* access modifiers changed from: protected */
        public ULocale createInstance(Locale locale, Void r2) {
            return JDKLocaleHelper.toULocale(locale);
        }
    };
    public static final ULocale CANADA = new ULocale("en_CA", Locale.CANADA);
    public static final ULocale CANADA_FRENCH = new ULocale("fr_CA", Locale.CANADA_FRENCH);
    private static String[][] CANONICALIZE_MAP = {new String[]{"C", "en_US_POSIX", null, null}, new String[]{"art_LOJBAN", "jbo", null, null}, new String[]{"az_AZ_CYRL", "az_Cyrl_AZ", null, null}, new String[]{"az_AZ_LATN", "az_Latn_AZ", null, null}, new String[]{"ca_ES_PREEURO", "ca_ES", "currency", "ESP"}, new String[]{"cel_GAULISH", "cel__GAULISH", null, null}, new String[]{"de_1901", "de__1901", null, null}, new String[]{"de_1906", "de__1906", null, null}, new String[]{"de__PHONEBOOK", "de", "collation", "phonebook"}, new String[]{"de_AT_PREEURO", "de_AT", "currency", "ATS"}, new String[]{"de_DE_PREEURO", "de_DE", "currency", "DEM"}, new String[]{"de_LU_PREEURO", "de_LU", "currency", "EUR"}, new String[]{"el_GR_PREEURO", "el_GR", "currency", "GRD"}, new String[]{"en_BOONT", "en__BOONT", null, null}, new String[]{"en_SCOUSE", "en__SCOUSE", null, null}, new String[]{"en_BE_PREEURO", "en_BE", "currency", "BEF"}, new String[]{"en_IE_PREEURO", "en_IE", "currency", "IEP"}, new String[]{"es__TRADITIONAL", "es", "collation", "traditional"}, new String[]{"es_ES_PREEURO", "es_ES", "currency", "ESP"}, new String[]{"eu_ES_PREEURO", "eu_ES", "currency", "ESP"}, new String[]{"fi_FI_PREEURO", "fi_FI", "currency", "FIM"}, new String[]{"fr_BE_PREEURO", "fr_BE", "currency", "BEF"}, new String[]{"fr_FR_PREEURO", "fr_FR", "currency", "FRF"}, new String[]{"fr_LU_PREEURO", "fr_LU", "currency", "LUF"}, new String[]{"ga_IE_PREEURO", "ga_IE", "currency", "IEP"}, new String[]{"gl_ES_PREEURO", "gl_ES", "currency", "ESP"}, new String[]{"hi__DIRECT", "hi", "collation", "direct"}, new String[]{"it_IT_PREEURO", "it_IT", "currency", "ITL"}, new String[]{"ja_JP_TRADITIONAL", "ja_JP", "calendar", "japanese"}, new String[]{"nl_BE_PREEURO", "nl_BE", "currency", "BEF"}, new String[]{"nl_NL_PREEURO", "nl_NL", "currency", "NLG"}, new String[]{"pt_PT_PREEURO", "pt_PT", "currency", "PTE"}, new String[]{"sl_ROZAJ", "sl__ROZAJ", null, null}, new String[]{"sr_SP_CYRL", "sr_Cyrl_RS", null, null}, new String[]{"sr_SP_LATN", "sr_Latn_RS", null, null}, new String[]{"sr_YU_CYRILLIC", "sr_Cyrl_RS", null, null}, new String[]{"th_TH_TRADITIONAL", "th_TH", "calendar", "buddhist"}, new String[]{"uz_UZ_CYRILLIC", "uz_Cyrl_UZ", null, null}, new String[]{"uz_UZ_CYRL", "uz_Cyrl_UZ", null, null}, new String[]{"uz_UZ_LATN", "uz_Latn_UZ", null, null}, new String[]{"zh_CHS", "zh_Hans", null, null}, new String[]{"zh_CHT", "zh_Hant", null, null}, new String[]{"zh_GAN", "zh__GAN", null, null}, new String[]{"zh_GUOYU", "zh", null, null}, new String[]{"zh_HAKKA", "zh__HAKKA", null, null}, new String[]{"zh_MIN", "zh__MIN", null, null}, new String[]{"zh_MIN_NAN", "zh__MINNAN", null, null}, new String[]{"zh_WUU", "zh__WUU", null, null}, new String[]{"zh_XIANG", "zh__XIANG", null, null}, new String[]{"zh_YUE", "zh__YUE", null, null}};
    public static final ULocale CHINA = new ULocale("zh_Hans_CN");
    public static final ULocale CHINESE = new ULocale("zh", Locale.CHINESE);
    private static final Locale EMPTY_LOCALE = new Locale("", "");
    public static final ULocale ENGLISH = new ULocale("en", Locale.ENGLISH);
    public static final ULocale FRANCE = new ULocale("fr_FR", Locale.FRANCE);
    public static final ULocale FRENCH = new ULocale("fr", Locale.FRENCH);
    public static final ULocale GERMAN = new ULocale("de", Locale.GERMAN);
    public static final ULocale GERMANY = new ULocale("de_DE", Locale.GERMANY);
    public static final ULocale ITALIAN = new ULocale("it", Locale.ITALIAN);
    public static final ULocale ITALY = new ULocale("it_IT", Locale.ITALY);
    public static final ULocale JAPAN = new ULocale("ja_JP", Locale.JAPAN);
    public static final ULocale JAPANESE = new ULocale("ja", Locale.JAPANESE);
    public static final ULocale KOREA = new ULocale("ko_KR", Locale.KOREA);
    public static final ULocale KOREAN = new ULocale("ko", Locale.KOREAN);
    public static final ULocale PRC = CHINA;
    public static final ULocale ROOT = new ULocale("", EMPTY_LOCALE);
    public static final ULocale SIMPLIFIED_CHINESE = new ULocale("zh_Hans");
    public static final ULocale TAIWAN = new ULocale("zh_Hant_TW");
    public static final ULocale TRADITIONAL_CHINESE = new ULocale("zh_Hant");
    public static final ULocale UK = new ULocale("en_GB", Locale.UK);
    public static final ULocale US = new ULocale("en_US", Locale.US);
    public static Type VALID_LOCALE = new Type();
    private static Locale[] defaultCategoryLocales = new Locale[Category.values().length];
    private static ULocale[] defaultCategoryULocales = new ULocale[Category.values().length];
    private static Locale defaultLocale = Locale.getDefault();
    private static ULocale defaultULocale = forLocale(defaultLocale);
    private static CacheBase nameCache = new SoftCache() {
        /* class android.icu.util.ULocale.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public String createInstance(String str, Void r2) {
            return new LocaleIDParser(str).getName();
        }
    };
    private static final long serialVersionUID = 3715177670352309217L;
    private static String[][] variantsToKeywords = {new String[]{"EURO", "currency", "EUR"}, new String[]{"PINYIN", "collation", "pinyin"}, new String[]{"STROKE", "collation", "stroke"}};
    private volatile transient BaseLocale baseLocale;
    private volatile transient LocaleExtensions extensions;
    private volatile transient Locale locale;
    private String localeID;

    public enum Category {
        DISPLAY,
        FORMAT
    }

    public Object clone() {
        return this;
    }

    static {
        int i = 0;
        if (JDKLocaleHelper.hasLocaleCategories()) {
            Category[] values = Category.values();
            int length = values.length;
            while (i < length) {
                Category category = values[i];
                int ordinal = category.ordinal();
                defaultCategoryLocales[ordinal] = JDKLocaleHelper.getDefault(category);
                defaultCategoryULocales[ordinal] = forLocale(defaultCategoryLocales[ordinal]);
                i++;
            }
        } else {
            Category[] values2 = Category.values();
            int length2 = values2.length;
            while (i < length2) {
                int ordinal2 = values2[i].ordinal();
                defaultCategoryLocales[ordinal2] = defaultLocale;
                defaultCategoryULocales[ordinal2] = defaultULocale;
                i++;
            }
        }
    }

    private ULocale(String str, Locale locale2) {
        this.localeID = str;
        this.locale = locale2;
    }

    public static ULocale forLocale(Locale locale2) {
        if (locale2 == null) {
            return null;
        }
        return (ULocale) CACHE.getInstance(locale2, null);
    }

    public ULocale(String str) {
        this.localeID = getName(str);
    }

    public static ULocale createCanonical(String str) {
        return new ULocale(canonicalize(str), null);
    }

    private static String lscvToID(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        if (str != null && str.length() > 0) {
            sb.append(str);
        }
        if (str2 != null && str2.length() > 0) {
            sb.append('_');
            sb.append(str2);
        }
        if (str3 != null && str3.length() > 0) {
            sb.append('_');
            sb.append(str3);
        }
        if (str4 != null && str4.length() > 0) {
            if (str3 == null || str3.length() == 0) {
                sb.append('_');
            }
            sb.append('_');
            sb.append(str4);
        }
        return sb.toString();
    }

    public Locale toLocale() {
        if (this.locale == null) {
            this.locale = JDKLocaleHelper.toLocale(this);
        }
        return this.locale;
    }

    public static ULocale getDefault() {
        synchronized (ULocale.class) {
            if (defaultULocale == null) {
                return ROOT;
            }
            Locale locale2 = Locale.getDefault();
            if (!defaultLocale.equals(locale2)) {
                defaultLocale = locale2;
                defaultULocale = forLocale(locale2);
                if (!JDKLocaleHelper.hasLocaleCategories()) {
                    for (Category category : Category.values()) {
                        int ordinal = category.ordinal();
                        defaultCategoryLocales[ordinal] = locale2;
                        defaultCategoryULocales[ordinal] = forLocale(locale2);
                    }
                }
            }
            return defaultULocale;
        }
    }

    public static ULocale getDefault(Category category) {
        synchronized (ULocale.class) {
            int ordinal = category.ordinal();
            if (defaultCategoryULocales[ordinal] == null) {
                return ROOT;
            }
            if (JDKLocaleHelper.hasLocaleCategories()) {
                Locale locale2 = JDKLocaleHelper.getDefault(category);
                if (!defaultCategoryLocales[ordinal].equals(locale2)) {
                    defaultCategoryLocales[ordinal] = locale2;
                    defaultCategoryULocales[ordinal] = forLocale(locale2);
                }
            } else {
                Locale locale3 = Locale.getDefault();
                if (!defaultLocale.equals(locale3)) {
                    defaultLocale = locale3;
                    defaultULocale = forLocale(locale3);
                    for (Category category2 : Category.values()) {
                        int ordinal2 = category2.ordinal();
                        defaultCategoryLocales[ordinal2] = locale3;
                        defaultCategoryULocales[ordinal2] = forLocale(locale3);
                    }
                }
            }
            return defaultCategoryULocales[ordinal];
        }
    }

    public int hashCode() {
        return this.localeID.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ULocale) {
            return this.localeID.equals(((ULocale) obj).localeID);
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0093, code lost:
        if (r5.hasNext() != false) goto L_0x0095;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compareTo(android.icu.util.ULocale r9) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.ULocale.compareTo(android.icu.util.ULocale):int");
    }

    public String getLanguage() {
        return base().getLanguage();
    }

    public String getScript() {
        return base().getScript();
    }

    public String getCountry() {
        return base().getRegion();
    }

    public static String getRegionForSupplementalData(ULocale uLocale, boolean z) {
        String keywordValue = uLocale.getKeywordValue("rg");
        if (keywordValue != null && keywordValue.length() == 6) {
            String upperString = AsciiUtil.toUpperString(keywordValue);
            if (upperString.endsWith("ZZZZ")) {
                return upperString.substring(0, 2);
            }
        }
        String country = uLocale.getCountry();
        return (country.length() != 0 || !z) ? country : addLikelySubtags(uLocale).getCountry();
    }

    public String getVariant() {
        return base().getVariant();
    }

    public String getBaseName() {
        return getBaseName(this.localeID);
    }

    public static String getBaseName(String str) {
        if (str.indexOf(64) == -1) {
            return str;
        }
        return new LocaleIDParser(str).getBaseName();
    }

    public String getName() {
        return this.localeID;
    }

    private static int getShortestSubtagLength(String str) {
        int length = str.length();
        int i = length;
        int i2 = 0;
        boolean z = true;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.charAt(i3) == '_' || str.charAt(i3) == '-') {
                if (i2 != 0 && i2 < i) {
                    i = i2;
                }
                z = true;
            } else {
                if (z) {
                    i2 = 0;
                    z = false;
                }
                i2++;
            }
        }
        return i;
    }

    public static String getName(String str) {
        if (str != null && !str.contains("@") && getShortestSubtagLength(str) == 1) {
            String name = forLanguageTag(str).getName();
            if (name.length() != 0) {
                str = name;
            }
        }
        return (String) nameCache.getInstance(str, null);
    }

    public String toString() {
        return this.localeID;
    }

    public Iterator getKeywords() {
        return getKeywords(this.localeID);
    }

    public static Iterator getKeywords(String str) {
        return new LocaleIDParser(str).getKeywords();
    }

    public String getKeywordValue(String str) {
        return getKeywordValue(this.localeID, str);
    }

    public static String getKeywordValue(String str, String str2) {
        return new LocaleIDParser(str).getKeywordValue(str2);
    }

    public static String canonicalize(String str) {
        boolean z;
        boolean z2 = true;
        LocaleIDParser localeIDParser = new LocaleIDParser(str, true);
        String baseName = localeIDParser.getBaseName();
        if (str.equals("")) {
            return "";
        }
        int i = 0;
        while (true) {
            String[][] strArr = variantsToKeywords;
            if (i >= strArr.length) {
                z = false;
                break;
            }
            String[] strArr2 = strArr[i];
            int lastIndexOf = baseName.lastIndexOf("_" + strArr2[0]);
            if (lastIndexOf > -1) {
                baseName = baseName.substring(0, lastIndexOf);
                if (baseName.endsWith("_")) {
                    baseName = baseName.substring(0, lastIndexOf - 1);
                }
                localeIDParser.setBaseName(baseName);
                localeIDParser.defaultKeywordValue(strArr2[1], strArr2[2]);
                z = true;
            } else {
                i++;
            }
        }
        int i2 = 0;
        while (true) {
            String[][] strArr3 = CANONICALIZE_MAP;
            if (i2 >= strArr3.length) {
                z2 = z;
                break;
            } else if (strArr3[i2][0].equals(baseName)) {
                String[] strArr4 = CANONICALIZE_MAP[i2];
                localeIDParser.setBaseName(strArr4[1]);
                if (strArr4[2] != null) {
                    localeIDParser.defaultKeywordValue(strArr4[2], strArr4[3]);
                }
            } else {
                i2++;
            }
        }
        if (!z2 && localeIDParser.getLanguage().equals("nb") && localeIDParser.getVariant().equals("NY")) {
            localeIDParser.setBaseName(lscvToID("nn", localeIDParser.getScript(), localeIDParser.getCountry(), null));
        }
        return localeIDParser.getName();
    }

    public ULocale setKeywordValue(String str, String str2) {
        return new ULocale(setKeywordValue(this.localeID, str, str2), null);
    }

    public static String setKeywordValue(String str, String str2, String str3) {
        LocaleIDParser localeIDParser = new LocaleIDParser(str);
        localeIDParser.setKeywordValue(str2, str3);
        return localeIDParser.getName();
    }

    public String getDisplayName() {
        return getDisplayNameInternal(this, getDefault(Category.DISPLAY));
    }

    private static String getDisplayNameInternal(ULocale uLocale, ULocale uLocale2) {
        return LocaleDisplayNames.getInstance(uLocale2).localeDisplayName(uLocale);
    }

    public static final class Type {
        private Type() {
        }
    }

    public static ULocale addLikelySubtags(ULocale uLocale) {
        String[] strArr = new String[3];
        int parseTagString = parseTagString(uLocale.localeID, strArr);
        String createLikelySubtagsString = createLikelySubtagsString(strArr[0], strArr[1], strArr[2], parseTagString < uLocale.localeID.length() ? uLocale.localeID.substring(parseTagString) : null);
        return createLikelySubtagsString == null ? uLocale : new ULocale(createLikelySubtagsString);
    }

    private static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    private static void appendTag(String str, StringBuilder sb) {
        if (sb.length() != 0) {
            sb.append('_');
        }
        sb.append(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String createTagString(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 179
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.ULocale.createTagString(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    static String createTagString(String str, String str2, String str3, String str4) {
        return createTagString(str, str2, str3, str4, null);
    }

    private static int parseTagString(String str, String[] strArr) {
        LocaleIDParser localeIDParser = new LocaleIDParser(str);
        String language = localeIDParser.getLanguage();
        String script = localeIDParser.getScript();
        String country = localeIDParser.getCountry();
        if (isEmptyString(language)) {
            strArr[0] = "und";
        } else {
            strArr[0] = language;
        }
        if (script.equals("Zzzz")) {
            strArr[1] = "";
        } else {
            strArr[1] = script;
        }
        if (country.equals("ZZ")) {
            strArr[2] = "";
        } else {
            strArr[2] = country;
        }
        String variant = localeIDParser.getVariant();
        if (!isEmptyString(variant)) {
            int indexOf = str.indexOf(variant);
            return indexOf > 0 ? indexOf - 1 : indexOf;
        }
        int indexOf2 = str.indexOf(64);
        return indexOf2 == -1 ? str.length() : indexOf2;
    }

    private static String lookupLikelySubtags(String str) {
        try {
            return UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "likelySubtags").getString(str);
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    private static String createLikelySubtagsString(String str, String str2, String str3, String str4) {
        String lookupLikelySubtags;
        String lookupLikelySubtags2;
        String lookupLikelySubtags3;
        if (!isEmptyString(str2) && !isEmptyString(str3) && (lookupLikelySubtags3 = lookupLikelySubtags(createTagString(str, str2, str3, null))) != null) {
            return createTagString(null, null, null, str4, lookupLikelySubtags3);
        }
        if (!isEmptyString(str2) && (lookupLikelySubtags2 = lookupLikelySubtags(createTagString(str, str2, null, null))) != null) {
            return createTagString(null, null, str3, str4, lookupLikelySubtags2);
        }
        if (!isEmptyString(str3) && (lookupLikelySubtags = lookupLikelySubtags(createTagString(str, null, str3, null))) != null) {
            return createTagString(null, str2, null, str4, lookupLikelySubtags);
        }
        String lookupLikelySubtags4 = lookupLikelySubtags(createTagString(str, null, null, null));
        if (lookupLikelySubtags4 != null) {
            return createTagString(null, str2, str3, str4, lookupLikelySubtags4);
        }
        return null;
    }

    public String toLanguageTag() {
        BaseLocale base = base();
        LocaleExtensions extensions2 = extensions();
        if (base.getVariant().equalsIgnoreCase("POSIX")) {
            base = BaseLocale.getInstance(base.getLanguage(), base.getScript(), base.getRegion(), "");
            if (extensions2.getUnicodeLocaleType("va") == null) {
                InternalLocaleBuilder internalLocaleBuilder = new InternalLocaleBuilder();
                try {
                    internalLocaleBuilder.setLocale(BaseLocale.ROOT, extensions2);
                    internalLocaleBuilder.setUnicodeLocaleKeyword("va", "posix");
                    extensions2 = internalLocaleBuilder.getLocaleExtensions();
                } catch (LocaleSyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        LanguageTag parseLocale = LanguageTag.parseLocale(base, extensions2);
        StringBuilder sb = new StringBuilder();
        String language = parseLocale.getLanguage();
        if (language.length() > 0) {
            sb.append(LanguageTag.canonicalizeLanguage(language));
        }
        String script = parseLocale.getScript();
        if (script.length() > 0) {
            sb.append("-");
            sb.append(LanguageTag.canonicalizeScript(script));
        }
        String region = parseLocale.getRegion();
        if (region.length() > 0) {
            sb.append("-");
            sb.append(LanguageTag.canonicalizeRegion(region));
        }
        for (String str : parseLocale.getVariants()) {
            sb.append("-");
            sb.append(LanguageTag.canonicalizeVariant(str));
        }
        for (String str2 : parseLocale.getExtensions()) {
            sb.append("-");
            sb.append(LanguageTag.canonicalizeExtension(str2));
        }
        String privateuse = parseLocale.getPrivateuse();
        if (privateuse.length() > 0) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append("x");
            sb.append("-");
            sb.append(LanguageTag.canonicalizePrivateuse(privateuse));
        }
        return sb.toString();
    }

    public static ULocale forLanguageTag(String str) {
        LanguageTag parse = LanguageTag.parse(str, null);
        InternalLocaleBuilder internalLocaleBuilder = new InternalLocaleBuilder();
        internalLocaleBuilder.setLanguageTag(parse);
        return getInstance(internalLocaleBuilder.getBaseLocale(), internalLocaleBuilder.getLocaleExtensions());
    }

    public static String toUnicodeLocaleKey(String str) {
        String bcpKey = KeyTypeData.toBcpKey(str);
        return (bcpKey != null || !UnicodeLocaleExtension.isKey(str)) ? bcpKey : AsciiUtil.toLowerString(str);
    }

    public static String toUnicodeLocaleType(String str, String str2) {
        String bcpType = KeyTypeData.toBcpType(str, str2, null, null);
        return (bcpType != null || !UnicodeLocaleExtension.isType(str2)) ? bcpType : AsciiUtil.toLowerString(str2);
    }

    public static String toLegacyKey(String str) {
        String legacyKey = KeyTypeData.toLegacyKey(str);
        return (legacyKey != null || !str.matches("[0-9a-zA-Z]+")) ? legacyKey : AsciiUtil.toLowerString(str);
    }

    public static String toLegacyType(String str, String str2) {
        String legacyType = KeyTypeData.toLegacyType(str, str2, null, null);
        return (legacyType != null || !str2.matches("[0-9a-zA-Z]+([_/\\-][0-9a-zA-Z]+)*")) ? legacyType : AsciiUtil.toLowerString(str2);
    }

    private static ULocale getInstance(BaseLocale baseLocale2, LocaleExtensions localeExtensions) {
        String lscvToID = lscvToID(baseLocale2.getLanguage(), baseLocale2.getScript(), baseLocale2.getRegion(), baseLocale2.getVariant());
        Set<Character> keys = localeExtensions.getKeys();
        if (!keys.isEmpty()) {
            TreeMap treeMap = new TreeMap();
            for (Character ch : keys) {
                Extension extension = localeExtensions.getExtension(ch);
                if (extension instanceof UnicodeLocaleExtension) {
                    UnicodeLocaleExtension unicodeLocaleExtension = (UnicodeLocaleExtension) extension;
                    for (String str : unicodeLocaleExtension.getUnicodeLocaleKeys()) {
                        String unicodeLocaleType = unicodeLocaleExtension.getUnicodeLocaleType(str);
                        String legacyKey = toLegacyKey(str);
                        if (unicodeLocaleType.length() == 0) {
                            unicodeLocaleType = "yes";
                        }
                        String legacyType = toLegacyType(str, unicodeLocaleType);
                        if (!legacyKey.equals("va") || !legacyType.equals("posix") || baseLocale2.getVariant().length() != 0) {
                            treeMap.put(legacyKey, legacyType);
                        } else {
                            lscvToID = lscvToID + "_POSIX";
                        }
                    }
                    Set<String> unicodeLocaleAttributes = unicodeLocaleExtension.getUnicodeLocaleAttributes();
                    if (unicodeLocaleAttributes.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (String str2 : unicodeLocaleAttributes) {
                            if (sb.length() > 0) {
                                sb.append('-');
                            }
                            sb.append(str2);
                        }
                        treeMap.put("attribute", sb.toString());
                    }
                } else {
                    treeMap.put(String.valueOf(ch), extension.getValue());
                }
            }
            if (!treeMap.isEmpty()) {
                StringBuilder sb2 = new StringBuilder(lscvToID);
                sb2.append("@");
                boolean z = false;
                for (Map.Entry entry : treeMap.entrySet()) {
                    if (z) {
                        sb2.append(";");
                    } else {
                        z = true;
                    }
                    sb2.append((String) entry.getKey());
                    sb2.append("=");
                    sb2.append((String) entry.getValue());
                }
                lscvToID = sb2.toString();
            }
        }
        return new ULocale(lscvToID);
    }

    private BaseLocale base() {
        String str;
        String str2;
        String str3;
        if (this.baseLocale == null) {
            String str4 = "";
            if (!equals(ROOT)) {
                LocaleIDParser localeIDParser = new LocaleIDParser(this.localeID);
                String language = localeIDParser.getLanguage();
                str2 = localeIDParser.getScript();
                str = localeIDParser.getCountry();
                str3 = localeIDParser.getVariant();
                str4 = language;
            } else {
                str3 = str4;
                str2 = str3;
                str = str2;
            }
            this.baseLocale = BaseLocale.getInstance(str4, str2, str, str3);
        }
        return this.baseLocale;
    }

    private LocaleExtensions extensions() {
        if (this.extensions == null) {
            Iterator keywords = getKeywords();
            if (keywords == null) {
                this.extensions = LocaleExtensions.EMPTY_EXTENSIONS;
            } else {
                InternalLocaleBuilder internalLocaleBuilder = new InternalLocaleBuilder();
                while (keywords.hasNext()) {
                    String str = (String) keywords.next();
                    if (str.equals("attribute")) {
                        for (String str2 : getKeywordValue(str).split("[-_]")) {
                            try {
                                internalLocaleBuilder.addUnicodeLocaleAttribute(str2);
                            } catch (LocaleSyntaxException unused) {
                            }
                        }
                    } else if (str.length() >= 2) {
                        String unicodeLocaleKey = toUnicodeLocaleKey(str);
                        String unicodeLocaleType = toUnicodeLocaleType(str, getKeywordValue(str));
                        if (!(unicodeLocaleKey == null || unicodeLocaleType == null)) {
                            try {
                                internalLocaleBuilder.setUnicodeLocaleKeyword(unicodeLocaleKey, unicodeLocaleType);
                            } catch (LocaleSyntaxException unused2) {
                            }
                        }
                    } else if (str.length() == 1 && str.charAt(0) != 'u') {
                        internalLocaleBuilder.setExtension(str.charAt(0), getKeywordValue(str).replace("_", "-"));
                    }
                }
                this.extensions = internalLocaleBuilder.getLocaleExtensions();
            }
        }
        return this.extensions;
    }

    /* access modifiers changed from: private */
    public static final class JDKLocaleHelper {
        private static Object eDISPLAY = null;
        private static Object eFORMAT = null;
        private static boolean hasLocaleCategories = true;
        private static Method mGetDefault;
        private static Method mSetDefault;

        static {
            Class cls;
            try {
                Class[] declaredClasses = Locale.class.getDeclaredClasses();
                int length = declaredClasses.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        cls = null;
                        break;
                    }
                    cls = declaredClasses[i];
                    if (cls.getName().equals("java.util.Locale$Category")) {
                        break;
                    }
                    i++;
                }
                if (cls != null) {
                    mGetDefault = Locale.class.getDeclaredMethod("getDefault", cls);
                    mSetDefault = Locale.class.getDeclaredMethod("setDefault", cls, Locale.class);
                    Method method = cls.getMethod("name", null);
                    Object[] enumConstants = cls.getEnumConstants();
                    for (Object obj : enumConstants) {
                        String str = (String) method.invoke(obj, null);
                        if (str.equals("DISPLAY")) {
                            eDISPLAY = obj;
                        } else if (str.equals("FORMAT")) {
                            eFORMAT = obj;
                        }
                    }
                    if (eDISPLAY == null) {
                        return;
                    }
                    if (eFORMAT != null) {
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            }
        }

        public static boolean hasLocaleCategories() {
            return hasLocaleCategories;
        }

        public static ULocale toULocale(Locale locale) {
            TreeSet<String> treeSet;
            TreeMap treeMap;
            String language = locale.getLanguage();
            String country = locale.getCountry();
            String variant = locale.getVariant();
            String script = locale.getScript();
            Set<Character> extensionKeys = locale.getExtensionKeys();
            if (!extensionKeys.isEmpty()) {
                treeMap = null;
                treeSet = null;
                for (Character ch : extensionKeys) {
                    if (ch.charValue() == 'u') {
                        Set<String> unicodeLocaleAttributes = locale.getUnicodeLocaleAttributes();
                        if (!unicodeLocaleAttributes.isEmpty()) {
                            treeSet = new TreeSet();
                            for (String str : unicodeLocaleAttributes) {
                                treeSet.add(str);
                            }
                        }
                        for (String str2 : locale.getUnicodeLocaleKeys()) {
                            String unicodeLocaleType = locale.getUnicodeLocaleType(str2);
                            if (unicodeLocaleType != null) {
                                if (!str2.equals("va")) {
                                    if (treeMap == null) {
                                        treeMap = new TreeMap();
                                    }
                                    treeMap.put(str2, unicodeLocaleType);
                                } else if (variant.length() == 0) {
                                    variant = unicodeLocaleType;
                                } else {
                                    variant = unicodeLocaleType + "_" + variant;
                                }
                            }
                        }
                    } else {
                        String extension = locale.getExtension(ch.charValue());
                        if (extension != null) {
                            if (treeMap == null) {
                                treeMap = new TreeMap();
                            }
                            treeMap.put(String.valueOf(ch), extension);
                        }
                    }
                }
            } else {
                treeMap = null;
                treeSet = null;
            }
            if (language.equals("no") && country.equals("NO") && variant.equals("NY")) {
                language = "nn";
                variant = "";
            }
            StringBuilder sb = new StringBuilder(language);
            if (script.length() > 0) {
                sb.append('_');
                sb.append(script);
            }
            if (country.length() > 0) {
                sb.append('_');
                sb.append(country);
            }
            if (variant.length() > 0) {
                if (country.length() == 0) {
                    sb.append('_');
                }
                sb.append('_');
                sb.append(variant);
            }
            if (treeSet != null) {
                StringBuilder sb2 = new StringBuilder();
                for (String str3 : treeSet) {
                    if (sb2.length() != 0) {
                        sb2.append('-');
                    }
                    sb2.append(str3);
                }
                if (treeMap == null) {
                    treeMap = new TreeMap();
                }
                treeMap.put("attribute", sb2.toString());
            }
            if (treeMap != null) {
                sb.append('@');
                boolean z = false;
                for (Map.Entry entry : treeMap.entrySet()) {
                    String str4 = (String) entry.getKey();
                    String str5 = (String) entry.getValue();
                    if (str4.length() != 1) {
                        str4 = ULocale.toLegacyKey(str4);
                        if (str5.length() == 0) {
                            str5 = "yes";
                        }
                        str5 = ULocale.toLegacyType(str4, str5);
                    }
                    if (z) {
                        sb.append(';');
                    } else {
                        z = true;
                    }
                    sb.append(str4);
                    sb.append('=');
                    sb.append(str5);
                }
            }
            return new ULocale(ULocale.getName(sb.toString()), locale);
        }

        public static Locale toLocale(ULocale uLocale) {
            Locale locale;
            String name = uLocale.getName();
            if (uLocale.getScript().length() > 0 || name.contains("@")) {
                locale = Locale.forLanguageTag(AsciiUtil.toUpperString(uLocale.toLanguageTag()));
            } else {
                locale = null;
            }
            return locale == null ? new Locale(uLocale.getLanguage(), uLocale.getCountry(), uLocale.getVariant()) : locale;
        }

        public static Locale getDefault(Category category) {
            Object obj;
            if (hasLocaleCategories) {
                int i = AnonymousClass3.$SwitchMap$android$icu$util$ULocale$Category[category.ordinal()];
                if (i == 1) {
                    obj = eDISPLAY;
                } else if (i != 2) {
                    obj = null;
                } else {
                    obj = eFORMAT;
                }
                if (obj != null) {
                    try {
                        return (Locale) mGetDefault.invoke(null, obj);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                    }
                }
            }
            return Locale.getDefault();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.ULocale$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$ULocale$Category = new int[Category.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                android.icu.util.ULocale$Category[] r0 = android.icu.util.ULocale.Category.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.util.ULocale.AnonymousClass3.$SwitchMap$android$icu$util$ULocale$Category = r0
                int[] r0 = android.icu.util.ULocale.AnonymousClass3.$SwitchMap$android$icu$util$ULocale$Category     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.util.ULocale$Category r1 = android.icu.util.ULocale.Category.DISPLAY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.util.ULocale.AnonymousClass3.$SwitchMap$android$icu$util$ULocale$Category     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.util.ULocale$Category r1 = android.icu.util.ULocale.Category.FORMAT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.ULocale.AnonymousClass3.<clinit>():void");
        }
    }
}
