package android.icu.impl;

import android.icu.text.PluralRanges;
import android.icu.text.PluralRules;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.TreeMap;

public class PluralRulesLoader extends PluralRules.Factory {
    private static final PluralRanges UNKNOWN_RANGE;
    public static final PluralRulesLoader loader = new PluralRulesLoader();
    private static Map localeIdToPluralRanges;
    private Map localeIdToCardinalRulesId;
    private Map localeIdToOrdinalRulesId;
    private Map rulesIdToEquivalentULocale;
    private final Map rulesIdToRules = new HashMap();

    private PluralRulesLoader() {
    }

    private Map getLocaleIdToRulesIdMap(PluralRules.PluralType pluralType) {
        checkBuildRulesIdMaps();
        return pluralType == PluralRules.PluralType.CARDINAL ? this.localeIdToCardinalRulesId : this.localeIdToOrdinalRulesId;
    }

    private void checkBuildRulesIdMaps() {
        int i;
        boolean z;
        Map map;
        Map map2;
        Map map3;
        synchronized (this) {
            z = this.localeIdToCardinalRulesId != null;
        }
        if (!z) {
            try {
                UResourceBundle pluralBundle = getPluralBundle();
                UResourceBundle uResourceBundle = pluralBundle.get("locales");
                map2 = new TreeMap();
                map = new HashMap();
                for (int i2 = 0; i2 < uResourceBundle.getSize(); i2++) {
                    UResourceBundle uResourceBundle2 = uResourceBundle.get(i2);
                    String key = uResourceBundle2.getKey();
                    String intern = uResourceBundle2.getString().intern();
                    map2.put(key, intern);
                    if (!map.containsKey(intern)) {
                        map.put(intern, new ULocale(key));
                    }
                }
                UResourceBundle uResourceBundle3 = pluralBundle.get("locales_ordinals");
                map3 = new TreeMap();
                for (i = 0; i < uResourceBundle3.getSize(); i++) {
                    UResourceBundle uResourceBundle4 = uResourceBundle3.get(i);
                    map3.put(uResourceBundle4.getKey(), uResourceBundle4.getString().intern());
                }
            } catch (MissingResourceException unused) {
                map2 = Collections.emptyMap();
                map3 = Collections.emptyMap();
                map = Collections.emptyMap();
            }
            synchronized (this) {
                if (this.localeIdToCardinalRulesId == null) {
                    this.localeIdToCardinalRulesId = map2;
                    this.localeIdToOrdinalRulesId = map3;
                    this.rulesIdToEquivalentULocale = map;
                }
            }
        }
    }

    public String getRulesIdForLocale(ULocale uLocale, PluralRules.PluralType pluralType) {
        String str;
        int lastIndexOf;
        Map localeIdToRulesIdMap = getLocaleIdToRulesIdMap(pluralType);
        String canonicalize = ULocale.canonicalize(uLocale.getBaseName());
        while (true) {
            str = (String) localeIdToRulesIdMap.get(canonicalize);
            if (str != null || (lastIndexOf = canonicalize.lastIndexOf("_")) == -1) {
                return str;
            }
            canonicalize = canonicalize.substring(0, lastIndexOf);
        }
        return str;
    }

    public PluralRules getRulesForRulesId(String str) {
        boolean containsKey;
        PluralRules pluralRules;
        synchronized (this.rulesIdToRules) {
            containsKey = this.rulesIdToRules.containsKey(str);
            pluralRules = containsKey ? (PluralRules) this.rulesIdToRules.get(str) : null;
        }
        if (!containsKey) {
            try {
                UResourceBundle uResourceBundle = getPluralBundle().get("rules").get(str);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < uResourceBundle.getSize(); i++) {
                    UResourceBundle uResourceBundle2 = uResourceBundle.get(i);
                    if (i > 0) {
                        sb.append("; ");
                    }
                    sb.append(uResourceBundle2.getKey());
                    sb.append(": ");
                    sb.append(uResourceBundle2.getString());
                }
                pluralRules = PluralRules.parseDescription(sb.toString());
            } catch (ParseException | MissingResourceException unused) {
            }
            synchronized (this.rulesIdToRules) {
                if (this.rulesIdToRules.containsKey(str)) {
                    pluralRules = (PluralRules) this.rulesIdToRules.get(str);
                } else {
                    this.rulesIdToRules.put(str, pluralRules);
                }
            }
        }
        return pluralRules;
    }

    public UResourceBundle getPluralBundle() {
        return ICUResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "plurals", ICUResourceBundle.ICU_DATA_CLASS_LOADER, true);
    }

    public PluralRules forLocale(ULocale uLocale, PluralRules.PluralType pluralType) {
        String rulesIdForLocale = getRulesIdForLocale(uLocale, pluralType);
        if (rulesIdForLocale == null || rulesIdForLocale.trim().length() == 0) {
            return PluralRules.DEFAULT;
        }
        PluralRules rulesForRulesId = getRulesForRulesId(rulesIdForLocale);
        return rulesForRulesId == null ? PluralRules.DEFAULT : rulesForRulesId;
    }

    static {
        PluralRanges pluralRanges = new PluralRanges();
        pluralRanges.freeze();
        UNKNOWN_RANGE = pluralRanges;
        String[][] strArr = {new String[]{"locales", "id ja km ko lo ms my th vi zh"}, new String[]{"other", "other", "other"}, new String[]{"locales", "am bn fr gu hi hy kn mr pa zu"}, new String[]{"one", "one", "one"}, new String[]{"one", "other", "other"}, new String[]{"other", "other", "other"}, new String[]{"locales", "fa"}, new String[]{"one", "one", "other"}, new String[]{"one", "other", "other"}, new String[]{"other", "other", "other"}, new String[]{"locales", "ka"}, new String[]{"one", "other", "one"}, new String[]{"other", "one", "other"}, new String[]{"other", "other", "other"}, new String[]{"locales", "az de el gl hu it kk ky ml mn ne nl pt sq sw ta te tr ug uz"}, new String[]{"one", "other", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "other", "other"}, new String[]{"locales", "af bg ca en es et eu fi nb sv ur"}, new String[]{"one", "other", "other"}, new String[]{"other", "one", "other"}, new String[]{"other", "other", "other"}, new String[]{"locales", "da fil is"}, new String[]{"one", "one", "one"}, new String[]{"one", "other", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "other", "other"}, new String[]{"locales", "si"}, new String[]{"one", "one", "one"}, new String[]{"one", "other", "other"}, new String[]{"other", "one", "other"}, new String[]{"other", "other", "other"}, new String[]{"locales", "mk"}, new String[]{"one", "one", "other"}, new String[]{"one", "other", "other"}, new String[]{"other", "one", "other"}, new String[]{"other", "other", "other"}, new String[]{"locales", "lv"}, new String[]{"zero", "zero", "other"}, new String[]{"zero", "one", "one"}, new String[]{"zero", "other", "other"}, new String[]{"one", "zero", "other"}, new String[]{"one", "one", "one"}, new String[]{"one", "other", "other"}, new String[]{"other", "zero", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "other", "other"}, new String[]{"locales", "ro"}, new String[]{"one", "few", "few"}, new String[]{"one", "other", "other"}, new String[]{"few", "one", "few"}, new String[]{"few", "few", "few"}, new String[]{"few", "other", "other"}, new String[]{"other", "few", "few"}, new String[]{"other", "other", "other"}, new String[]{"locales", "hr sr bs"}, new String[]{"one", "one", "one"}, new String[]{"one", "few", "few"}, new String[]{"one", "other", "other"}, new String[]{"few", "one", "one"}, new String[]{"few", "few", "few"}, new String[]{"few", "other", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "few", "few"}, new String[]{"other", "other", "other"}, new String[]{"locales", "sl"}, new String[]{"one", "one", "few"}, new String[]{"one", "two", "two"}, new String[]{"one", "few", "few"}, new String[]{"one", "other", "other"}, new String[]{"two", "one", "few"}, new String[]{"two", "two", "two"}, new String[]{"two", "few", "few"}, new String[]{"two", "other", "other"}, new String[]{"few", "one", "few"}, new String[]{"few", "two", "two"}, new String[]{"few", "few", "few"}, new String[]{"few", "other", "other"}, new String[]{"other", "one", "few"}, new String[]{"other", "two", "two"}, new String[]{"other", "few", "few"}, new String[]{"other", "other", "other"}, new String[]{"locales", "he"}, new String[]{"one", "two", "other"}, new String[]{"one", "many", "many"}, new String[]{"one", "other", "other"}, new String[]{"two", "many", "other"}, new String[]{"two", "other", "other"}, new String[]{"many", "many", "many"}, new String[]{"many", "other", "many"}, new String[]{"other", "one", "other"}, new String[]{"other", "two", "other"}, new String[]{"other", "many", "many"}, new String[]{"other", "other", "other"}, new String[]{"locales", "cs pl sk"}, new String[]{"one", "few", "few"}, new String[]{"one", "many", "many"}, new String[]{"one", "other", "other"}, new String[]{"few", "few", "few"}, new String[]{"few", "many", "many"}, new String[]{"few", "other", "other"}, new String[]{"many", "one", "one"}, new String[]{"many", "few", "few"}, new String[]{"many", "many", "many"}, new String[]{"many", "other", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "few", "few"}, new String[]{"other", "many", "many"}, new String[]{"other", "other", "other"}, new String[]{"locales", "lt ru uk"}, new String[]{"one", "one", "one"}, new String[]{"one", "few", "few"}, new String[]{"one", "many", "many"}, new String[]{"one", "other", "other"}, new String[]{"few", "one", "one"}, new String[]{"few", "few", "few"}, new String[]{"few", "many", "many"}, new String[]{"few", "other", "other"}, new String[]{"many", "one", "one"}, new String[]{"many", "few", "few"}, new String[]{"many", "many", "many"}, new String[]{"many", "other", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "few", "few"}, new String[]{"other", "many", "many"}, new String[]{"other", "other", "other"}, new String[]{"locales", "cy"}, new String[]{"zero", "one", "one"}, new String[]{"zero", "two", "two"}, new String[]{"zero", "few", "few"}, new String[]{"zero", "many", "many"}, new String[]{"zero", "other", "other"}, new String[]{"one", "two", "two"}, new String[]{"one", "few", "few"}, new String[]{"one", "many", "many"}, new String[]{"one", "other", "other"}, new String[]{"two", "few", "few"}, new String[]{"two", "many", "many"}, new String[]{"two", "other", "other"}, new String[]{"few", "many", "many"}, new String[]{"few", "other", "other"}, new String[]{"many", "other", "other"}, new String[]{"other", "one", "one"}, new String[]{"other", "two", "two"}, new String[]{"other", "few", "few"}, new String[]{"other", "many", "many"}, new String[]{"other", "other", "other"}, new String[]{"locales", "ar"}, new String[]{"zero", "one", "zero"}, new String[]{"zero", "two", "zero"}, new String[]{"zero", "few", "few"}, new String[]{"zero", "many", "many"}, new String[]{"zero", "other", "other"}, new String[]{"one", "two", "other"}, new String[]{"one", "few", "few"}, new String[]{"one", "many", "many"}, new String[]{"one", "other", "other"}, new String[]{"two", "few", "few"}, new String[]{"two", "many", "many"}, new String[]{"two", "other", "other"}, new String[]{"few", "few", "few"}, new String[]{"few", "many", "many"}, new String[]{"few", "other", "other"}, new String[]{"many", "few", "few"}, new String[]{"many", "many", "many"}, new String[]{"many", "other", "other"}, new String[]{"other", "one", "other"}, new String[]{"other", "two", "other"}, new String[]{"other", "few", "few"}, new String[]{"other", "many", "many"}, new String[]{"other", "other", "other"}};
        HashMap hashMap = new HashMap();
        String[] strArr2 = null;
        PluralRanges pluralRanges2 = null;
        for (String[] strArr3 : strArr) {
            if (strArr3[0].equals("locales")) {
                if (pluralRanges2 != null) {
                    pluralRanges2.freeze();
                    for (String str : strArr2) {
                        hashMap.put(str, pluralRanges2);
                    }
                }
                strArr2 = strArr3[1].split(" ");
                pluralRanges2 = new PluralRanges();
            } else {
                pluralRanges2.add(StandardPlural.fromString(strArr3[0]), StandardPlural.fromString(strArr3[1]), StandardPlural.fromString(strArr3[2]));
            }
        }
        for (String str2 : strArr2) {
            hashMap.put(str2, pluralRanges2);
        }
        localeIdToPluralRanges = Collections.unmodifiableMap(hashMap);
    }
}
