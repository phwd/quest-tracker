package android.icu.util;

import android.icu.text.BreakIterator;
import android.icu.text.Collator;
import android.icu.text.DateFormat;
import android.icu.text.NumberFormat;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import sun.security.x509.PolicyInformation;

public class GlobalizationPreferences implements Freezable<GlobalizationPreferences> {
    public static final int BI_CHARACTER = 0;
    private static final int BI_LIMIT = 5;
    public static final int BI_LINE = 2;
    public static final int BI_SENTENCE = 3;
    public static final int BI_TITLE = 4;
    public static final int BI_WORD = 1;
    public static final int DF_FULL = 0;
    private static final int DF_LIMIT = 5;
    public static final int DF_LONG = 1;
    public static final int DF_MEDIUM = 2;
    public static final int DF_NONE = 4;
    public static final int DF_SHORT = 3;
    public static final int ID_CURRENCY = 7;
    public static final int ID_CURRENCY_SYMBOL = 8;
    public static final int ID_KEYWORD = 5;
    public static final int ID_KEYWORD_VALUE = 6;
    public static final int ID_LANGUAGE = 1;
    public static final int ID_LOCALE = 0;
    public static final int ID_SCRIPT = 2;
    public static final int ID_TERRITORY = 3;
    public static final int ID_TIMEZONE = 9;
    public static final int ID_VARIANT = 4;
    public static final int NF_CURRENCY = 1;
    public static final int NF_INTEGER = 4;
    private static final int NF_LIMIT = 5;
    public static final int NF_NUMBER = 0;
    public static final int NF_PERCENT = 2;
    public static final int NF_SCIENTIFIC = 3;
    private static final int TYPE_BREAKITERATOR = 5;
    private static final int TYPE_CALENDAR = 1;
    private static final int TYPE_COLLATOR = 4;
    private static final int TYPE_DATEFORMAT = 2;
    private static final int TYPE_GENERIC = 0;
    private static final int TYPE_LIMIT = 6;
    private static final int TYPE_NUMBERFORMAT = 3;
    private static final HashMap<ULocale, BitSet> available_locales = new HashMap<>();
    private static final String[][] language_territory_hack = {new String[]{"af", "ZA"}, new String[]{"am", "ET"}, new String[]{"ar", "SA"}, new String[]{"as", "IN"}, new String[]{"ay", "PE"}, new String[]{"az", "AZ"}, new String[]{"bal", "PK"}, new String[]{"be", "BY"}, new String[]{"bg", "BG"}, new String[]{"bn", "IN"}, new String[]{"bs", "BA"}, new String[]{"ca", "ES"}, new String[]{"ch", "MP"}, new String[]{"cpe", "SL"}, new String[]{"cs", "CZ"}, new String[]{"cy", "GB"}, new String[]{"da", "DK"}, new String[]{"de", "DE"}, new String[]{"dv", "MV"}, new String[]{"dz", "BT"}, new String[]{"el", "GR"}, new String[]{"en", "US"}, new String[]{"es", "ES"}, new String[]{"et", "EE"}, new String[]{"eu", "ES"}, new String[]{"fa", "IR"}, new String[]{"fi", "FI"}, new String[]{"fil", "PH"}, new String[]{"fj", "FJ"}, new String[]{"fo", "FO"}, new String[]{"fr", "FR"}, new String[]{"ga", "IE"}, new String[]{"gd", "GB"}, new String[]{"gl", "ES"}, new String[]{"gn", "PY"}, new String[]{"gu", "IN"}, new String[]{"gv", "GB"}, new String[]{"ha", "NG"}, new String[]{"he", "IL"}, new String[]{"hi", "IN"}, new String[]{"ho", "PG"}, new String[]{"hr", "HR"}, new String[]{"ht", "HT"}, new String[]{"hu", "HU"}, new String[]{"hy", "AM"}, new String[]{PolicyInformation.ID, "ID"}, new String[]{"is", "IS"}, new String[]{"it", "IT"}, new String[]{"ja", "JP"}, new String[]{"ka", "GE"}, new String[]{"kk", "KZ"}, new String[]{"kl", "GL"}, new String[]{"km", "KH"}, new String[]{"kn", "IN"}, new String[]{"ko", "KR"}, new String[]{"kok", "IN"}, new String[]{"ks", "IN"}, new String[]{"ku", "TR"}, new String[]{"ky", "KG"}, new String[]{"la", "VA"}, new String[]{"lb", "LU"}, new String[]{"ln", "CG"}, new String[]{"lo", "LA"}, new String[]{"lt", "LT"}, new String[]{"lv", "LV"}, new String[]{"mai", "IN"}, new String[]{"men", "GN"}, new String[]{"mg", "MG"}, new String[]{"mh", "MH"}, new String[]{"mk", "MK"}, new String[]{"ml", "IN"}, new String[]{"mn", "MN"}, new String[]{"mni", "IN"}, new String[]{"mo", "MD"}, new String[]{"mr", "IN"}, new String[]{DateFormat.MINUTE_SECOND, "MY"}, new String[]{"mt", "MT"}, new String[]{"my", "MM"}, new String[]{"na", "NR"}, new String[]{"nb", "NO"}, new String[]{"nd", "ZA"}, new String[]{"ne", "NP"}, new String[]{"niu", "NU"}, new String[]{"nl", "NL"}, new String[]{"nn", "NO"}, new String[]{"no", "NO"}, new String[]{"nr", "ZA"}, new String[]{"nso", "ZA"}, new String[]{"ny", "MW"}, new String[]{"om", "KE"}, new String[]{"or", "IN"}, new String[]{"pa", "IN"}, new String[]{"pau", "PW"}, new String[]{"pl", "PL"}, new String[]{"ps", "PK"}, new String[]{"pt", "BR"}, new String[]{"qu", "PE"}, new String[]{"rn", "BI"}, new String[]{"ro", "RO"}, new String[]{"ru", "RU"}, new String[]{"rw", "RW"}, new String[]{"sd", "IN"}, new String[]{"sg", "CF"}, new String[]{"si", "LK"}, new String[]{"sk", "SK"}, new String[]{"sl", "SI"}, new String[]{"sm", "WS"}, new String[]{"so", "DJ"}, new String[]{"sq", "CS"}, new String[]{"sr", "CS"}, new String[]{"ss", "ZA"}, new String[]{"st", "ZA"}, new String[]{"sv", "SE"}, new String[]{"sw", "KE"}, new String[]{"ta", "IN"}, new String[]{"te", "IN"}, new String[]{"tem", "SL"}, new String[]{"tet", "TL"}, new String[]{"th", "TH"}, new String[]{"ti", "ET"}, new String[]{"tg", "TJ"}, new String[]{"tk", "TM"}, new String[]{"tkl", "TK"}, new String[]{"tvl", "TV"}, new String[]{"tl", "PH"}, new String[]{"tn", "ZA"}, new String[]{"to", "TO"}, new String[]{"tpi", "PG"}, new String[]{"tr", "TR"}, new String[]{"ts", "ZA"}, new String[]{"uk", "UA"}, new String[]{"ur", "IN"}, new String[]{"uz", "UZ"}, new String[]{"ve", "ZA"}, new String[]{"vi", "VN"}, new String[]{"wo", "SN"}, new String[]{"xh", "ZA"}, new String[]{"zh", "CN"}, new String[]{"zh_Hant", "TW"}, new String[]{"zu", "ZA"}, new String[]{"aa", "ET"}, new String[]{"byn", "ER"}, new String[]{"eo", "DE"}, new String[]{"gez", "ET"}, new String[]{"haw", "US"}, new String[]{"iu", "CA"}, new String[]{"kw", "GB"}, new String[]{"sa", "IN"}, new String[]{"sh", "HR"}, new String[]{"sid", "ET"}, new String[]{"syr", "SY"}, new String[]{"tig", "ER"}, new String[]{"tt", "RU"}, new String[]{"wal", "ET"}};
    private static final Map<String, String> language_territory_hack_map = new HashMap();
    static final String[][] territory_tzid_hack = {new String[]{"AQ", "Antarctica/McMurdo"}, new String[]{"AR", "America/Buenos_Aires"}, new String[]{"AU", "Australia/Sydney"}, new String[]{"BR", "America/Sao_Paulo"}, new String[]{"CA", "America/Toronto"}, new String[]{"CD", "Africa/Kinshasa"}, new String[]{"CL", "America/Santiago"}, new String[]{"CN", "Asia/Shanghai"}, new String[]{"EC", "America/Guayaquil"}, new String[]{"ES", "Europe/Madrid"}, new String[]{"GB", "Europe/London"}, new String[]{"GL", "America/Godthab"}, new String[]{"ID", "Asia/Jakarta"}, new String[]{"ML", "Africa/Bamako"}, new String[]{"MX", "America/Mexico_City"}, new String[]{"MY", "Asia/Kuala_Lumpur"}, new String[]{"NZ", "Pacific/Auckland"}, new String[]{"PT", "Europe/Lisbon"}, new String[]{"RU", "Europe/Moscow"}, new String[]{"UA", "Europe/Kiev"}, new String[]{"US", "America/New_York"}, new String[]{"UZ", "Asia/Tashkent"}, new String[]{"PF", "Pacific/Tahiti"}, new String[]{"FM", "Pacific/Kosrae"}, new String[]{"KI", "Pacific/Tarawa"}, new String[]{"KZ", "Asia/Almaty"}, new String[]{"MH", "Pacific/Majuro"}, new String[]{"MN", "Asia/Ulaanbaatar"}, new String[]{"SJ", "Arctic/Longyearbyen"}, new String[]{"UM", "Pacific/Midway"}};
    static final Map<String, String> territory_tzid_hack_map = new HashMap();
    private BreakIterator[] breakIterators;
    private Calendar calendar;
    private Collator collator;
    private Currency currency;
    private DateFormat[][] dateFormats;
    private volatile boolean frozen;
    private List<ULocale> implicitLocales;
    private List<ULocale> locales;
    private NumberFormat[] numberFormats;
    private String territory;
    private TimeZone timezone;

    public GlobalizationPreferences() {
        reset();
    }

    public GlobalizationPreferences setLocales(List<ULocale> inputLocales) {
        if (!isFrozen()) {
            this.locales = processLocales(inputLocales);
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public List<ULocale> getLocales() {
        if (this.locales == null) {
            return guessLocales();
        }
        List<ULocale> result = new ArrayList<>();
        result.addAll(this.locales);
        return result;
    }

    public ULocale getLocale(int index) {
        List<ULocale> lcls = this.locales;
        if (lcls == null) {
            lcls = guessLocales();
        }
        if (index < 0 || index >= lcls.size()) {
            return null;
        }
        return lcls.get(index);
    }

    public GlobalizationPreferences setLocales(ULocale[] uLocales) {
        if (!isFrozen()) {
            return setLocales(Arrays.asList(uLocales));
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public GlobalizationPreferences setLocale(ULocale uLocale) {
        if (!isFrozen()) {
            return setLocales(new ULocale[]{uLocale});
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public GlobalizationPreferences setLocales(String acceptLanguageString) {
        if (!isFrozen()) {
            try {
                return setLocales(ULocale.parseAcceptLanguage(acceptLanguageString, true));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid Accept-Language string");
            }
        } else {
            throw new UnsupportedOperationException("Attempt to modify immutable object");
        }
    }

    public ResourceBundle getResourceBundle(String baseName) {
        return getResourceBundle(baseName, null);
    }

    public ResourceBundle getResourceBundle(String baseName, ClassLoader loader) {
        UResourceBundle urb = null;
        UResourceBundle candidate = null;
        String actualLocaleName = null;
        List<ULocale> fallbacks = getLocales();
        int i = 0;
        while (true) {
            if (i >= fallbacks.size()) {
                break;
            }
            String localeName = fallbacks.get(i).toString();
            if (actualLocaleName != null && localeName.equals(actualLocaleName)) {
                urb = candidate;
                break;
            }
            if (loader == null) {
                try {
                    candidate = UResourceBundle.getBundleInstance(baseName, localeName);
                } catch (MissingResourceException e) {
                    actualLocaleName = null;
                }
            } else {
                candidate = UResourceBundle.getBundleInstance(baseName, localeName, loader);
            }
            if (candidate != null) {
                actualLocaleName = candidate.getULocale().getName();
                if (actualLocaleName.equals(localeName)) {
                    urb = candidate;
                    break;
                } else if (urb == null) {
                    urb = candidate;
                }
            } else {
                continue;
            }
            i++;
        }
        if (urb != null) {
            return urb;
        }
        throw new MissingResourceException("Can't find bundle for base name " + baseName, baseName, "");
    }

    public GlobalizationPreferences setTerritory(String territory2) {
        if (!isFrozen()) {
            this.territory = territory2;
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public String getTerritory() {
        String str = this.territory;
        if (str == null) {
            return guessTerritory();
        }
        return str;
    }

    public GlobalizationPreferences setCurrency(Currency currency2) {
        if (!isFrozen()) {
            this.currency = currency2;
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public Currency getCurrency() {
        Currency currency2 = this.currency;
        if (currency2 == null) {
            return guessCurrency();
        }
        return currency2;
    }

    public GlobalizationPreferences setCalendar(Calendar calendar2) {
        if (!isFrozen()) {
            this.calendar = (Calendar) calendar2.clone();
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public Calendar getCalendar() {
        Calendar calendar2 = this.calendar;
        if (calendar2 == null) {
            return guessCalendar();
        }
        Calendar temp = (Calendar) calendar2.clone();
        temp.setTimeZone(getTimeZone());
        temp.setTimeInMillis(System.currentTimeMillis());
        return temp;
    }

    public GlobalizationPreferences setTimeZone(TimeZone timezone2) {
        if (!isFrozen()) {
            this.timezone = (TimeZone) timezone2.clone();
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public TimeZone getTimeZone() {
        TimeZone timeZone = this.timezone;
        if (timeZone == null) {
            return guessTimeZone();
        }
        return timeZone.cloneAsThawed();
    }

    public Collator getCollator() {
        Collator collator2 = this.collator;
        if (collator2 == null) {
            return guessCollator();
        }
        try {
            return (Collator) collator2.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException("Error in cloning collator", e);
        }
    }

    public GlobalizationPreferences setCollator(Collator collator2) {
        if (!isFrozen()) {
            try {
                this.collator = (Collator) collator2.clone();
                return this;
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException("Error in cloning collator", e);
            }
        } else {
            throw new UnsupportedOperationException("Attempt to modify immutable object");
        }
    }

    public BreakIterator getBreakIterator(int type) {
        if (type < 0 || type >= 5) {
            throw new IllegalArgumentException("Illegal break iterator type");
        }
        BreakIterator[] breakIteratorArr = this.breakIterators;
        if (breakIteratorArr == null || breakIteratorArr[type] == null) {
            return guessBreakIterator(type);
        }
        return (BreakIterator) breakIteratorArr[type].clone();
    }

    public GlobalizationPreferences setBreakIterator(int type, BreakIterator iterator) {
        if (type < 0 || type >= 5) {
            throw new IllegalArgumentException("Illegal break iterator type");
        } else if (!isFrozen()) {
            if (this.breakIterators == null) {
                this.breakIterators = new BreakIterator[5];
            }
            this.breakIterators[type] = (BreakIterator) iterator.clone();
            return this;
        } else {
            throw new UnsupportedOperationException("Attempt to modify immutable object");
        }
    }

    /* JADX INFO: Multiple debug info for r4v4 java.lang.String[]: [D('parts' java.lang.String[]), D('temp' android.icu.util.Currency)] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0009 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getDisplayName(java.lang.String r13, int r14) {
        /*
        // Method dump skipped, instructions count: 314
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.GlobalizationPreferences.getDisplayName(java.lang.String, int):java.lang.String");
    }

    public GlobalizationPreferences setDateFormat(int dateStyle, int timeStyle, DateFormat format) {
        if (!isFrozen()) {
            if (this.dateFormats == null) {
                this.dateFormats = (DateFormat[][]) Array.newInstance(DateFormat.class, 5, 5);
            }
            this.dateFormats[dateStyle][timeStyle] = (DateFormat) format.clone();
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public DateFormat getDateFormat(int dateStyle, int timeStyle) {
        if (!(dateStyle == 4 && timeStyle == 4) && dateStyle >= 0 && dateStyle < 5 && timeStyle >= 0 && timeStyle < 5) {
            DateFormat result = null;
            DateFormat[][] dateFormatArr = this.dateFormats;
            if (dateFormatArr != null) {
                result = dateFormatArr[dateStyle][timeStyle];
            }
            if (result == null) {
                return guessDateFormat(dateStyle, timeStyle);
            }
            DateFormat result2 = (DateFormat) result.clone();
            result2.setTimeZone(getTimeZone());
            return result2;
        }
        throw new IllegalArgumentException("Illegal date format style arguments");
    }

    public NumberFormat getNumberFormat(int style) {
        if (style < 0 || style >= 5) {
            throw new IllegalArgumentException("Illegal number format type");
        }
        NumberFormat result = null;
        NumberFormat[] numberFormatArr = this.numberFormats;
        if (numberFormatArr != null) {
            result = numberFormatArr[style];
        }
        if (result != null) {
            return (NumberFormat) result.clone();
        }
        return guessNumberFormat(style);
    }

    public GlobalizationPreferences setNumberFormat(int style, NumberFormat format) {
        if (!isFrozen()) {
            if (this.numberFormats == null) {
                this.numberFormats = new NumberFormat[5];
            }
            this.numberFormats[style] = (NumberFormat) format.clone();
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    public GlobalizationPreferences reset() {
        if (!isFrozen()) {
            this.locales = null;
            this.territory = null;
            this.calendar = null;
            this.collator = null;
            this.breakIterators = null;
            this.timezone = null;
            this.currency = null;
            this.dateFormats = null;
            this.numberFormats = null;
            this.implicitLocales = null;
            return this;
        }
        throw new UnsupportedOperationException("Attempt to modify immutable object");
    }

    /* access modifiers changed from: protected */
    public List<ULocale> processLocales(List<ULocale> inputLocales) {
        List<ULocale> result = new ArrayList<>();
        for (int i = 0; i < inputLocales.size(); i++) {
            ULocale uloc = inputLocales.get(i);
            String language = uloc.getLanguage();
            String script = uloc.getScript();
            String country = uloc.getCountry();
            String variant = uloc.getVariant();
            boolean bInserted = false;
            int j = 0;
            while (true) {
                if (j >= result.size()) {
                    break;
                }
                ULocale u = result.get(j);
                if (u.getLanguage().equals(language)) {
                    String s = u.getScript();
                    String c = u.getCountry();
                    String v = u.getVariant();
                    if (!s.equals(script)) {
                        if (s.length() != 0 || c.length() != 0 || v.length() != 0) {
                            if (s.length() != 0 || !c.equals(country)) {
                                if (script.length() == 0 && country.length() > 0 && c.length() == 0) {
                                    result.add(j, uloc);
                                    bInserted = true;
                                    break;
                                }
                            } else {
                                result.add(j, uloc);
                                bInserted = true;
                                break;
                            }
                        } else {
                            result.add(j, uloc);
                            bInserted = true;
                            break;
                        }
                    } else if (c.equals(country) || c.length() != 0 || v.length() != 0) {
                        if (!v.equals(variant) && v.length() == 0) {
                            result.add(j, uloc);
                            bInserted = true;
                            break;
                        }
                    } else {
                        result.add(j, uloc);
                        bInserted = true;
                        break;
                    }
                }
                j++;
            }
            if (!bInserted) {
                result.add(uloc);
            }
        }
        int index = 0;
        while (index < result.size()) {
            ULocale uloc2 = result.get(index);
            while (true) {
                ULocale fallback = uloc2.getFallback();
                uloc2 = fallback;
                if (fallback == null || uloc2.getLanguage().length() == 0) {
                    index++;
                } else {
                    index++;
                    result.add(index, uloc2);
                }
            }
            index++;
        }
        int index2 = 0;
        while (index2 < result.size() - 1) {
            ULocale uloc3 = result.get(index2);
            boolean bRemoved = false;
            int i2 = index2 + 1;
            while (true) {
                if (i2 >= result.size()) {
                    break;
                } else if (uloc3.equals(result.get(i2))) {
                    result.remove(index2);
                    bRemoved = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!bRemoved) {
                index2++;
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public DateFormat guessDateFormat(int dateStyle, int timeStyle) {
        ULocale dfLocale = getAvailableLocale(2);
        if (dfLocale == null) {
            dfLocale = ULocale.ROOT;
        }
        if (timeStyle == 4) {
            return DateFormat.getDateInstance(getCalendar(), dateStyle, dfLocale);
        }
        if (dateStyle == 4) {
            return DateFormat.getTimeInstance(getCalendar(), timeStyle, dfLocale);
        }
        return DateFormat.getDateTimeInstance(getCalendar(), dateStyle, timeStyle, dfLocale);
    }

    /* access modifiers changed from: protected */
    public NumberFormat guessNumberFormat(int style) {
        ULocale nfLocale = getAvailableLocale(3);
        if (nfLocale == null) {
            nfLocale = ULocale.ROOT;
        }
        if (style == 0) {
            return NumberFormat.getInstance(nfLocale);
        }
        if (style == 1) {
            NumberFormat result = NumberFormat.getCurrencyInstance(nfLocale);
            result.setCurrency(getCurrency());
            return result;
        } else if (style == 2) {
            return NumberFormat.getPercentInstance(nfLocale);
        } else {
            if (style == 3) {
                return NumberFormat.getScientificInstance(nfLocale);
            }
            if (style == 4) {
                return NumberFormat.getIntegerInstance(nfLocale);
            }
            throw new IllegalArgumentException("Unknown number format style");
        }
    }

    /* access modifiers changed from: protected */
    public String guessTerritory() {
        for (ULocale locale : getLocales()) {
            String result = locale.getCountry();
            if (result.length() != 0) {
                return result;
            }
        }
        ULocale firstLocale = getLocale(0);
        String language = firstLocale.getLanguage();
        String script = firstLocale.getScript();
        String result2 = null;
        if (script.length() != 0) {
            Map<String, String> map = language_territory_hack_map;
            result2 = map.get(language + "_" + script);
        }
        if (result2 == null) {
            result2 = language_territory_hack_map.get(language);
        }
        if (result2 == null) {
            return "US";
        }
        return result2;
    }

    /* access modifiers changed from: protected */
    public Currency guessCurrency() {
        return Currency.getInstance(new ULocale("und-" + getTerritory()));
    }

    /* access modifiers changed from: protected */
    public List<ULocale> guessLocales() {
        if (this.implicitLocales == null) {
            List<ULocale> result = new ArrayList<>(1);
            result.add(ULocale.getDefault());
            this.implicitLocales = processLocales(result);
        }
        return this.implicitLocales;
    }

    /* access modifiers changed from: protected */
    public Collator guessCollator() {
        ULocale collLocale = getAvailableLocale(4);
        if (collLocale == null) {
            collLocale = ULocale.ROOT;
        }
        return Collator.getInstance(collLocale);
    }

    /* access modifiers changed from: protected */
    public BreakIterator guessBreakIterator(int type) {
        ULocale brkLocale = getAvailableLocale(5);
        if (brkLocale == null) {
            brkLocale = ULocale.ROOT;
        }
        if (type == 0) {
            return BreakIterator.getCharacterInstance(brkLocale);
        }
        if (type == 1) {
            return BreakIterator.getWordInstance(brkLocale);
        }
        if (type == 2) {
            return BreakIterator.getLineInstance(brkLocale);
        }
        if (type == 3) {
            return BreakIterator.getSentenceInstance(brkLocale);
        }
        if (type == 4) {
            return BreakIterator.getTitleInstance(brkLocale);
        }
        throw new IllegalArgumentException("Unknown break iterator type");
    }

    /* access modifiers changed from: protected */
    public TimeZone guessTimeZone() {
        String timezoneString = territory_tzid_hack_map.get(getTerritory());
        if (timezoneString == null) {
            String[] attempt = TimeZone.getAvailableIDs(getTerritory());
            if (attempt.length == 0) {
                timezoneString = "Etc/GMT";
            } else {
                int i = 0;
                while (i < attempt.length && attempt[i].indexOf("/") < 0) {
                    i++;
                }
                if (i > attempt.length) {
                    i = 0;
                }
                timezoneString = attempt[i];
            }
        }
        return TimeZone.getTimeZone(timezoneString);
    }

    /* access modifiers changed from: protected */
    public Calendar guessCalendar() {
        ULocale calLocale = getAvailableLocale(1);
        if (calLocale == null) {
            calLocale = ULocale.US;
        }
        return Calendar.getInstance(getTimeZone(), calLocale);
    }

    private ULocale getAvailableLocale(int type) {
        List<ULocale> locs = getLocales();
        for (int i = 0; i < locs.size(); i++) {
            ULocale l = locs.get(i);
            if (isAvailableLocale(l, type)) {
                return l;
            }
        }
        return null;
    }

    private boolean isAvailableLocale(ULocale loc, int type) {
        BitSet bits = available_locales.get(loc);
        if (bits == null || !bits.get(type)) {
            return false;
        }
        return true;
    }

    static {
        ULocale[] brkLocales;
        ULocale[] allLocales = ULocale.getAvailableLocales();
        for (ULocale uLocale : allLocales) {
            BitSet bits = new BitSet(6);
            available_locales.put(uLocale, bits);
            bits.set(0);
        }
        ULocale[] calLocales = Calendar.getAvailableULocales();
        for (int i = 0; i < calLocales.length; i++) {
            BitSet bits2 = available_locales.get(calLocales[i]);
            if (bits2 == null) {
                bits2 = new BitSet(6);
                available_locales.put(allLocales[i], bits2);
            }
            bits2.set(1);
        }
        ULocale[] dateLocales = DateFormat.getAvailableULocales();
        for (int i2 = 0; i2 < dateLocales.length; i2++) {
            BitSet bits3 = available_locales.get(dateLocales[i2]);
            if (bits3 == null) {
                bits3 = new BitSet(6);
                available_locales.put(allLocales[i2], bits3);
            }
            bits3.set(2);
        }
        ULocale[] numLocales = NumberFormat.getAvailableULocales();
        for (int i3 = 0; i3 < numLocales.length; i3++) {
            BitSet bits4 = available_locales.get(numLocales[i3]);
            if (bits4 == null) {
                bits4 = new BitSet(6);
                available_locales.put(allLocales[i3], bits4);
            }
            bits4.set(3);
        }
        ULocale[] collLocales = Collator.getAvailableULocales();
        for (int i4 = 0; i4 < collLocales.length; i4++) {
            BitSet bits5 = available_locales.get(collLocales[i4]);
            if (bits5 == null) {
                bits5 = new BitSet(6);
                available_locales.put(allLocales[i4], bits5);
            }
            bits5.set(4);
        }
        for (ULocale uLocale2 : BreakIterator.getAvailableULocales()) {
            available_locales.get(uLocale2).set(5);
        }
        int i5 = 0;
        while (true) {
            String[][] strArr = language_territory_hack;
            if (i5 >= strArr.length) {
                break;
            }
            language_territory_hack_map.put(strArr[i5][0], strArr[i5][1]);
            i5++;
        }
        int i6 = 0;
        while (true) {
            String[][] strArr2 = territory_tzid_hack;
            if (i6 < strArr2.length) {
                territory_tzid_hack_map.put(strArr2[i6][0], strArr2[i6][1]);
                i6++;
            } else {
                return;
            }
        }
    }

    @Override // android.icu.util.Freezable
    public boolean isFrozen() {
        return this.frozen;
    }

    @Override // android.icu.util.Freezable
    public GlobalizationPreferences freeze() {
        this.frozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable
    public GlobalizationPreferences cloneAsThawed() {
        try {
            GlobalizationPreferences result = (GlobalizationPreferences) clone();
            result.frozen = false;
            return result;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
