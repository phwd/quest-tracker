package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import sun.util.locale.BaseLocale;
import sun.util.locale.InternalLocaleBuilder;
import sun.util.locale.LanguageTag;
import sun.util.locale.LocaleExtensions;
import sun.util.locale.LocaleObjectCache;
import sun.util.locale.LocaleUtils;

public final class Locale implements Cloneable, Serializable {
    public static final Locale CANADA = createConstant("en", "CA");
    public static final Locale CANADA_FRENCH = createConstant("fr", "CA");
    public static final Locale CHINA;
    public static final Locale CHINESE = createConstant("zh", "");
    public static final Locale ENGLISH = createConstant("en", "");
    public static final Locale FRANCE = createConstant("fr", "FR");
    public static final Locale FRENCH = createConstant("fr", "");
    public static final Locale GERMAN = createConstant("de", "");
    public static final Locale GERMANY = createConstant("de", "DE");
    public static final Locale ITALIAN = createConstant("it", "");
    public static final Locale ITALY = createConstant("it", "IT");
    public static final Locale JAPAN = createConstant("ja", "JP");
    public static final Locale JAPANESE = createConstant("ja", "");
    public static final Locale KOREA = createConstant("ko", "KR");
    public static final Locale KOREAN = createConstant("ko", "");
    private static final Cache LOCALECACHE = new Cache(null);
    public static final Locale PRC;
    public static final Locale ROOT = createConstant("", "");
    public static final Locale SIMPLIFIED_CHINESE = createConstant("zh", "CN");
    public static final Locale TAIWAN = TRADITIONAL_CHINESE;
    public static final Locale TRADITIONAL_CHINESE = createConstant("zh", "TW");
    public static final Locale UK = createConstant("en", "GB");
    public static final Locale US = createConstant("en", "US");
    private static volatile Locale defaultDisplayLocale = null;
    private static volatile Locale defaultFormatLocale = null;
    private static volatile String[] isoCountries = null;
    private static volatile String[] isoLanguages = null;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("language", String.class), new ObjectStreamField("country", String.class), new ObjectStreamField("variant", String.class), new ObjectStreamField("hashcode", Integer.TYPE), new ObjectStreamField("script", String.class), new ObjectStreamField("extensions", String.class)};
    static final long serialVersionUID = 9149081749638150636L;
    private transient BaseLocale baseLocale;
    private volatile transient int hashCodeValue;
    private volatile transient String languageTag;
    private transient LocaleExtensions localeExtensions;

    /* access modifiers changed from: private */
    public static class NoImagePreloadHolder {
        public static volatile Locale defaultLocale = Locale.initDefault();
    }

    /* synthetic */ Locale(BaseLocale baseLocale2, LocaleExtensions localeExtensions2, AnonymousClass1 r3) {
        this(baseLocale2, localeExtensions2);
    }

    static {
        Locale locale = SIMPLIFIED_CHINESE;
        CHINA = locale;
        PRC = locale;
    }

    private Locale(BaseLocale baseLocale2, LocaleExtensions localeExtensions2) {
        this.hashCodeValue = 0;
        this.baseLocale = baseLocale2;
        this.localeExtensions = localeExtensions2;
    }

    public Locale(String str, String str2, String str3) {
        this.hashCodeValue = 0;
        if (str == null || str2 == null || str3 == null) {
            throw new NullPointerException();
        }
        this.baseLocale = BaseLocale.getInstance(convertOldISOCodes(str), "", str2, str3);
        this.localeExtensions = getCompatibilityExtensions(str, "", str2, str3);
    }

    public Locale(String str, String str2) {
        this(str, str2, "");
    }

    private static Locale createConstant(String str, String str2) {
        return getInstance(BaseLocale.createInstance(str, str2), null);
    }

    static Locale getInstance(String str, String str2, String str3) {
        return getInstance(str, "", str2, str3, null);
    }

    static Locale getInstance(String str, String str2, String str3, String str4, LocaleExtensions localeExtensions2) {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            throw new NullPointerException();
        }
        if (localeExtensions2 == null) {
            localeExtensions2 = getCompatibilityExtensions(str, str2, str3, str4);
        }
        return getInstance(BaseLocale.getInstance(str, str2, str3, str4), localeExtensions2);
    }

    static Locale getInstance(BaseLocale baseLocale2, LocaleExtensions localeExtensions2) {
        return (Locale) LOCALECACHE.get(new LocaleKey(baseLocale2, localeExtensions2, null));
    }

    /* access modifiers changed from: private */
    public static class Cache extends LocaleObjectCache {
        /* synthetic */ Cache(AnonymousClass1 r1) {
            this();
        }

        private Cache() {
        }

        /* access modifiers changed from: protected */
        public Locale createObject(LocaleKey localeKey) {
            return new Locale(localeKey.base, localeKey.exts, (AnonymousClass1) null);
        }
    }

    /* access modifiers changed from: private */
    public static final class LocaleKey {
        private final BaseLocale base;
        private final LocaleExtensions exts;
        private final int hash;

        /* synthetic */ LocaleKey(BaseLocale baseLocale, LocaleExtensions localeExtensions, AnonymousClass1 r3) {
            this(baseLocale, localeExtensions);
        }

        private LocaleKey(BaseLocale baseLocale, LocaleExtensions localeExtensions) {
            this.base = baseLocale;
            this.exts = localeExtensions;
            int hashCode = this.base.hashCode();
            LocaleExtensions localeExtensions2 = this.exts;
            this.hash = localeExtensions2 != null ? hashCode ^ localeExtensions2.hashCode() : hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LocaleKey)) {
                return false;
            }
            LocaleKey localeKey = (LocaleKey) obj;
            if (this.hash != localeKey.hash || !this.base.equals(localeKey.base)) {
                return false;
            }
            LocaleExtensions localeExtensions = this.exts;
            if (localeExtensions == null) {
                return localeKey.exts == null;
            }
            return localeExtensions.equals(localeKey.exts);
        }

        public int hashCode() {
            return this.hash;
        }
    }

    public static Locale getDefault() {
        return NoImagePreloadHolder.defaultLocale;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.util.Locale$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$Locale$Category = new int[Category.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                java.util.Locale$Category[] r0 = java.util.Locale.Category.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                java.util.Locale.AnonymousClass1.$SwitchMap$java$util$Locale$Category = r0
                int[] r0 = java.util.Locale.AnonymousClass1.$SwitchMap$java$util$Locale$Category     // Catch:{ NoSuchFieldError -> 0x0014 }
                java.util.Locale$Category r1 = java.util.Locale.Category.DISPLAY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = java.util.Locale.AnonymousClass1.$SwitchMap$java$util$Locale$Category     // Catch:{ NoSuchFieldError -> 0x001f }
                java.util.Locale$Category r1 = java.util.Locale.Category.FORMAT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Locale.AnonymousClass1.<clinit>():void");
        }
    }

    public static Locale getDefault(Category category) {
        int i = AnonymousClass1.$SwitchMap$java$util$Locale$Category[category.ordinal()];
        if (i == 1) {
            if (defaultDisplayLocale == null) {
                synchronized (Locale.class) {
                    if (defaultDisplayLocale == null) {
                        defaultDisplayLocale = initDefault(category);
                    }
                }
            }
            return defaultDisplayLocale;
        } else if (i != 2) {
            return getDefault();
        } else {
            if (defaultFormatLocale == null) {
                synchronized (Locale.class) {
                    if (defaultFormatLocale == null) {
                        defaultFormatLocale = initDefault(category);
                    }
                }
            }
            return defaultFormatLocale;
        }
    }

    public static Locale initDefault() {
        String str;
        String str2 = "";
        String property = System.getProperty("user.locale", str2);
        if (!property.isEmpty()) {
            return forLanguageTag(property);
        }
        String property2 = System.getProperty("user.language", "en");
        String property3 = System.getProperty("user.region");
        if (property3 != null) {
            int indexOf = property3.indexOf(95);
            if (indexOf >= 0) {
                String substring = property3.substring(0, indexOf);
                str = property3.substring(indexOf + 1);
                property3 = substring;
            } else {
                str = str2;
            }
        } else {
            String property4 = System.getProperty("user.script", str2);
            String property5 = System.getProperty("user.country", str2);
            str = System.getProperty("user.variant", str2);
            str2 = property4;
            property3 = property5;
        }
        return getInstance(property2, str2, property3, str, null);
    }

    private static Locale initDefault(Category category) {
        Locale locale = NoImagePreloadHolder.defaultLocale;
        return getInstance(System.getProperty(category.languageKey, locale.getLanguage()), System.getProperty(category.scriptKey, locale.getScript()), System.getProperty(category.countryKey, locale.getCountry()), System.getProperty(category.variantKey, locale.getVariant()), null);
    }

    public String getLanguage() {
        return this.baseLocale.getLanguage();
    }

    public String getScript() {
        return this.baseLocale.getScript();
    }

    public String getCountry() {
        return this.baseLocale.getRegion();
    }

    public String getVariant() {
        return this.baseLocale.getVariant();
    }

    public boolean hasExtensions() {
        return this.localeExtensions != null;
    }

    public String getExtension(char c) {
        if (!LocaleExtensions.isValidKey(c)) {
            throw new IllegalArgumentException("Ill-formed extension key: " + c);
        } else if (hasExtensions()) {
            return this.localeExtensions.getExtensionValue(Character.valueOf(c));
        } else {
            return null;
        }
    }

    public Set getExtensionKeys() {
        if (!hasExtensions()) {
            return Collections.emptySet();
        }
        return this.localeExtensions.getKeys();
    }

    public Set getUnicodeLocaleAttributes() {
        if (!hasExtensions()) {
            return Collections.emptySet();
        }
        return this.localeExtensions.getUnicodeLocaleAttributes();
    }

    public String getUnicodeLocaleType(String str) {
        if (!isUnicodeExtensionKey(str)) {
            throw new IllegalArgumentException("Ill-formed Unicode locale key: " + str);
        } else if (hasExtensions()) {
            return this.localeExtensions.getUnicodeLocaleType(str);
        } else {
            return null;
        }
    }

    public Set getUnicodeLocaleKeys() {
        LocaleExtensions localeExtensions2 = this.localeExtensions;
        if (localeExtensions2 == null) {
            return Collections.emptySet();
        }
        return localeExtensions2.getUnicodeLocaleKeys();
    }

    /* access modifiers changed from: package-private */
    public BaseLocale getBaseLocale() {
        return this.baseLocale;
    }

    public final String toString() {
        boolean z = true;
        boolean z2 = this.baseLocale.getLanguage().length() != 0;
        boolean z3 = this.baseLocale.getScript().length() != 0;
        boolean z4 = this.baseLocale.getRegion().length() != 0;
        boolean z5 = this.baseLocale.getVariant().length() != 0;
        LocaleExtensions localeExtensions2 = this.localeExtensions;
        if (localeExtensions2 == null || localeExtensions2.getID().length() == 0) {
            z = false;
        }
        StringBuilder sb = new StringBuilder(this.baseLocale.getLanguage());
        if (z4 || (z2 && (z5 || z3 || z))) {
            sb.append('_');
            sb.append(this.baseLocale.getRegion());
        }
        if (z5 && (z2 || z4)) {
            sb.append('_');
            sb.append(this.baseLocale.getVariant());
        }
        if (z3 && (z2 || z4)) {
            sb.append("_#");
            sb.append(this.baseLocale.getScript());
        }
        if (z && (z2 || z4)) {
            sb.append('_');
            if (!z3) {
                sb.append('#');
            }
            sb.append(this.localeExtensions.getID());
        }
        return sb.toString();
    }

    public String toLanguageTag() {
        if (this.languageTag != null) {
            return this.languageTag;
        }
        LanguageTag parseLocale = LanguageTag.parseLocale(this.baseLocale, this.localeExtensions);
        StringBuilder sb = new StringBuilder();
        String language = parseLocale.getLanguage();
        if (language.length() > 0) {
            LanguageTag.canonicalizeLanguage(language);
            sb.append(language);
        }
        String script = parseLocale.getScript();
        if (script.length() > 0) {
            sb.append("-");
            LanguageTag.canonicalizeScript(script);
            sb.append(script);
        }
        String region = parseLocale.getRegion();
        if (region.length() > 0) {
            sb.append("-");
            LanguageTag.canonicalizeRegion(region);
            sb.append(region);
        }
        for (String str : parseLocale.getVariants()) {
            sb.append("-");
            sb.append(str);
        }
        for (String str2 : parseLocale.getExtensions()) {
            sb.append("-");
            LanguageTag.canonicalizeExtension(str2);
            sb.append(str2);
        }
        String privateuse = parseLocale.getPrivateuse();
        if (privateuse.length() > 0) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append("x");
            sb.append("-");
            sb.append(privateuse);
        }
        String sb2 = sb.toString();
        synchronized (this) {
            if (this.languageTag == null) {
                this.languageTag = sb2;
            }
        }
        return this.languageTag;
    }

    public static Locale forLanguageTag(String str) {
        LanguageTag parse = LanguageTag.parse(str, null);
        InternalLocaleBuilder internalLocaleBuilder = new InternalLocaleBuilder();
        internalLocaleBuilder.setLanguageTag(parse);
        BaseLocale baseLocale2 = internalLocaleBuilder.getBaseLocale();
        LocaleExtensions localeExtensions2 = internalLocaleBuilder.getLocaleExtensions();
        if (localeExtensions2 == null && baseLocale2.getVariant().length() > 0) {
            localeExtensions2 = getCompatibilityExtensions(baseLocale2.getLanguage(), baseLocale2.getScript(), baseLocale2.getRegion(), baseLocale2.getVariant());
        }
        return getInstance(baseLocale2, localeExtensions2);
    }

    public Object clone() {
        try {
            return (Locale) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public int hashCode() {
        int i = this.hashCodeValue;
        if (i == 0) {
            i = this.baseLocale.hashCode();
            LocaleExtensions localeExtensions2 = this.localeExtensions;
            if (localeExtensions2 != null) {
                i ^= localeExtensions2.hashCode();
            }
            this.hashCodeValue = i;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Locale)) {
            return false;
        }
        Locale locale = (Locale) obj;
        if (!this.baseLocale.equals(locale.baseLocale)) {
            return false;
        }
        LocaleExtensions localeExtensions2 = this.localeExtensions;
        if (localeExtensions2 == null) {
            return locale.localeExtensions == null;
        }
        return localeExtensions2.equals(locale.localeExtensions);
    }

    private static boolean isUnicodeExtensionKey(String str) {
        return str.length() == 2 && LocaleUtils.isAlphaNumericString(str);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }

    private Object readResolve() {
        return getInstance(this.baseLocale.getLanguage(), this.baseLocale.getScript(), this.baseLocale.getRegion(), this.baseLocale.getVariant(), this.localeExtensions);
    }

    private static String convertOldISOCodes(String str) {
        LocaleUtils.toLowerString(str);
        String intern = str.intern();
        if (intern == "he") {
            return "iw";
        }
        if (intern == "yi") {
            return "ji";
        }
        return intern == "id" ? "in" : intern;
    }

    private static LocaleExtensions getCompatibilityExtensions(String str, String str2, String str3, String str4) {
        if (LocaleUtils.caseIgnoreMatch(str, "ja") && str2.length() == 0 && LocaleUtils.caseIgnoreMatch(str3, "jp") && "JP".equals(str4)) {
            return LocaleExtensions.CALENDAR_JAPANESE;
        }
        if (!LocaleUtils.caseIgnoreMatch(str, "th") || str2.length() != 0 || !LocaleUtils.caseIgnoreMatch(str3, "th") || !"TH".equals(str4)) {
            return null;
        }
        return LocaleExtensions.NUMBER_THAI;
    }

    public enum Category {
        DISPLAY("user.language.display", "user.script.display", "user.country.display", "user.variant.display"),
        FORMAT("user.language.format", "user.script.format", "user.country.format", "user.variant.format");
        
        final String countryKey;
        final String languageKey;
        final String scriptKey;
        final String variantKey;

        private Category(String str, String str2, String str3, String str4) {
            this.languageKey = str;
            this.scriptKey = str2;
            this.countryKey = str3;
            this.variantKey = str4;
        }
    }
}
