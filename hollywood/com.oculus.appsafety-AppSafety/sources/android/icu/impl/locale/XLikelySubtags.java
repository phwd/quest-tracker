package android.icu.impl.locale;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.locale.XCldrStub;
import android.icu.util.ICUException;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import sun.security.x509.CRLReasonCodeExtension;
import sun.util.locale.LanguageTag;

public class XLikelySubtags {
    private static final XLikelySubtags DEFAULT = new XLikelySubtags();
    final Map<String, Map<String, Map<String, LSR>>> langTable;

    public static final XLikelySubtags getDefault() {
        return DEFAULT;
    }

    /* access modifiers changed from: package-private */
    public static abstract class Maker {
        static final Maker HASHMAP = new Maker() {
            /* class android.icu.impl.locale.XLikelySubtags.Maker.AnonymousClass1 */

            @Override // android.icu.impl.locale.XLikelySubtags.Maker
            public Map<Object, Object> make() {
                return new HashMap();
            }
        };
        static final Maker TREEMAP = new Maker() {
            /* class android.icu.impl.locale.XLikelySubtags.Maker.AnonymousClass2 */

            @Override // android.icu.impl.locale.XLikelySubtags.Maker
            public Map<Object, Object> make() {
                return new TreeMap();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract <V> V make();

        Maker() {
        }

        public <K, V> V getSubtable(Map<K, V> langTable, K language) {
            V scriptTable = langTable.get(language);
            if (scriptTable != null) {
                return scriptTable;
            }
            V scriptTable2 = (V) make();
            langTable.put(language, scriptTable2);
            return scriptTable2;
        }
    }

    public static class Aliases {
        final XCldrStub.Multimap<String, String> toAliases;
        final Map<String, String> toCanonical;

        public String getCanonical(String alias) {
            String canonical = this.toCanonical.get(alias);
            return canonical == null ? alias : canonical;
        }

        public Set<String> getAliases(String canonical) {
            Set<String> aliases = this.toAliases.get(canonical);
            return aliases == null ? Collections.singleton(canonical) : aliases;
        }

        public Aliases(String key) {
            UResourceBundle territoryAlias = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "metadata", ICUResourceBundle.ICU_DATA_CLASS_LOADER).get("alias").get(key);
            Map<String, String> toCanonical1 = new HashMap<>();
            for (int i = 0; i < territoryAlias.getSize(); i++) {
                UResourceBundle res = territoryAlias.get(i);
                String aliasFrom = res.getKey();
                if (!aliasFrom.contains("_") && !res.get(CRLReasonCodeExtension.REASON).getString().equals("overlong")) {
                    String aliasTo = res.get("replacement").getString();
                    int spacePos = aliasTo.indexOf(32);
                    String aliasFirst = spacePos < 0 ? aliasTo : aliasTo.substring(0, spacePos);
                    if (!aliasFirst.contains("_")) {
                        toCanonical1.put(aliasFrom, aliasFirst);
                    }
                }
            }
            if (key.equals("language")) {
                toCanonical1.put("mo", "ro");
            }
            this.toCanonical = Collections.unmodifiableMap(toCanonical1);
            this.toAliases = XCldrStub.Multimaps.invertFrom(toCanonical1, XCldrStub.HashMultimap.create());
        }
    }

    public static class LSR {
        public static Aliases LANGUAGE_ALIASES = new Aliases("language");
        public static Aliases REGION_ALIASES = new Aliases("territory");
        private static final HashMap<ULocale, LSR> pseudoReplacements = new HashMap<>(11);
        public final String language;
        public final String region;
        public final String script;

        static {
            String[][] source = {new String[]{"x-bork", "x1", "", ""}, new String[]{"x-elmer", "x2", "", ""}, new String[]{"x-hacker", "x3", "", ""}, new String[]{"x-piglatin", "x4", "", ""}, new String[]{"x-pirate", "x5", "", ""}, new String[]{"en-XA", "x6", "", ""}, new String[]{"en-PSACCENT", "x6", "", ""}, new String[]{"ar-XB", "x7", "", ""}, new String[]{"ar-PSBIDI", "x7", "", ""}, new String[]{"en-XC", "x8", "en", ""}, new String[]{"en-PSCRACK", "x8", "en", ""}};
            for (int i = 0; i < source.length; i++) {
                pseudoReplacements.put(new ULocale(source[i][0]), new LSR(source[i][1], source[i][2], source[i][3]));
            }
        }

        public static LSR from(String language2, String script2, String region2) {
            return new LSR(language2, script2, region2);
        }

        static LSR from(String languageIdentifier) {
            String p3;
            String[] parts = languageIdentifier.split("[-_]");
            if (parts.length < 1 || parts.length > 3) {
                throw new ICUException("too many subtags");
            }
            String lang = parts[0].toLowerCase();
            String p2 = parts.length < 2 ? "" : parts[1];
            if (parts.length < 3) {
                p3 = "";
            } else {
                p3 = parts[2];
            }
            return p2.length() < 4 ? new LSR(lang, "", p2) : new LSR(lang, p2, p3);
        }

        public static LSR from(ULocale locale) {
            LSR replacement = pseudoReplacements.get(locale);
            if (replacement != null) {
                return replacement;
            }
            if (!"PSCRACK".equals(locale.getVariant())) {
                return new LSR(locale.getLanguage(), locale.getScript(), locale.getCountry());
            }
            return new LSR("x8", locale.getLanguage() + locale.getScript() + locale.getCountry(), "");
        }

        public static LSR fromMaximalized(ULocale locale) {
            LSR replacement = pseudoReplacements.get(locale);
            if (replacement != null) {
                return replacement;
            }
            if (!"PSCRACK".equals(locale.getVariant())) {
                return fromMaximalized(locale.getLanguage(), locale.getScript(), locale.getCountry());
            }
            return new LSR("x8", locale.getLanguage() + locale.getScript() + locale.getCountry(), "");
        }

        public static LSR fromMaximalized(String language2, String script2, String region2) {
            return XLikelySubtags.DEFAULT.maximize(LANGUAGE_ALIASES.getCanonical(language2), script2, REGION_ALIASES.getCanonical(region2));
        }

        public LSR(String language2, String script2, String region2) {
            this.language = language2;
            this.script = script2;
            this.region = region2;
        }

        public String toString() {
            StringBuilder result = new StringBuilder(this.language);
            if (!this.script.isEmpty()) {
                result.append('-');
                result.append(this.script);
            }
            if (!this.region.isEmpty()) {
                result.append('-');
                result.append(this.region);
            }
            return result.toString();
        }

        public LSR replace(String language2, String script2, String region2) {
            if (language2 == null && script2 == null && region2 == null) {
                return this;
            }
            return new LSR(language2 == null ? this.language : language2, script2 == null ? this.script : script2, region2 == null ? this.region : region2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && obj.getClass() == getClass()) {
                    LSR other = (LSR) obj;
                    if (!this.language.equals(other.language) || !this.script.equals(other.script) || !this.region.equals(other.region)) {
                        return false;
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(this.language, this.script, this.region);
        }
    }

    public XLikelySubtags() {
        this(getDefaultRawData(), true);
    }

    private static Map<String, String> getDefaultRawData() {
        Map<String, String> rawData = new TreeMap<>();
        UResourceBundle bundle = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "likelySubtags");
        Enumeration<String> enumer = bundle.getKeys();
        while (enumer.hasMoreElements()) {
            String key = enumer.nextElement();
            rawData.put(key, bundle.getString(key));
        }
        return rawData;
    }

    public XLikelySubtags(Map<String, String> rawData, boolean skipNoncanonical) {
        this.langTable = init(rawData, skipNoncanonical);
    }

    private Map<String, Map<String, Map<String, LSR>>> init(Map<String, String> rawData, boolean skipNoncanonical) {
        Map<String, Map<String, Map<String, LSR>>> result = (Map) Maker.TREEMAP.make();
        Map<LSR, LSR> internCache = new HashMap<>();
        for (Map.Entry<String, String> sourceTarget : rawData.entrySet()) {
            LSR ltp = LSR.from(sourceTarget.getKey());
            String language = ltp.language;
            String script = ltp.script;
            String region = ltp.region;
            LSR ltp2 = LSR.from(sourceTarget.getValue());
            String languageTarget = ltp2.language;
            String scriptTarget = ltp2.script;
            String regionTarget = ltp2.region;
            set(result, language, script, region, languageTarget, scriptTarget, regionTarget, internCache);
            Collection<String> languageAliases = LSR.LANGUAGE_ALIASES.getAliases(language);
            Collection<String> regionAliases = LSR.REGION_ALIASES.getAliases(region);
            for (String languageAlias : languageAliases) {
                for (String regionAlias : regionAliases) {
                    if (!languageAlias.equals(language) || !regionAlias.equals(region)) {
                        set(result, languageAlias, script, regionAlias, languageTarget, scriptTarget, regionTarget, internCache);
                        languageTarget = languageTarget;
                        ltp2 = ltp2;
                        region = region;
                        language = language;
                    }
                }
            }
        }
        set(result, LanguageTag.UNDETERMINED, "Latn", "", "en", "Latn", "US", internCache);
        for (Map.Entry<String, LSR> regionEntry : result.get(LanguageTag.UNDETERMINED).get("").entrySet()) {
            LSR value = regionEntry.getValue();
            set(result, LanguageTag.UNDETERMINED, value.script, value.region, value);
        }
        if (result.containsKey(LanguageTag.UNDETERMINED)) {
            for (Map.Entry<String, Map<String, Map<String, LSR>>> langEntry : result.entrySet()) {
                String lang = langEntry.getKey();
                Map<String, Map<String, LSR>> scriptMap = langEntry.getValue();
                if (scriptMap.containsKey("")) {
                    Iterator<Map.Entry<String, Map<String, LSR>>> it = scriptMap.entrySet().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Map.Entry<String, Map<String, LSR>> scriptEntry = it.next();
                            String script2 = scriptEntry.getKey();
                            if (!scriptEntry.getValue().containsKey("")) {
                                throw new IllegalArgumentException("failure: " + lang + "-" + script2);
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("failure: " + lang);
                }
            }
            return result;
        }
        throw new IllegalArgumentException("failure: base");
    }

    private void set(Map<String, Map<String, Map<String, LSR>>> langTable2, String language, String script, String region, String languageTarget, String scriptTarget, String regionTarget, Map<LSR, LSR> internCache) {
        LSR newValue = new LSR(languageTarget, scriptTarget, regionTarget);
        LSR oldValue = internCache.get(newValue);
        if (oldValue == null) {
            internCache.put(newValue, newValue);
            oldValue = newValue;
        }
        set(langTable2, language, script, region, oldValue);
    }

    private void set(Map<String, Map<String, Map<String, LSR>>> langTable2, String language, String script, String region, LSR newValue) {
        ((Map) Maker.TREEMAP.getSubtable((Map) Maker.TREEMAP.getSubtable(langTable2, language), script)).put(region, newValue);
    }

    public LSR maximize(String source) {
        return maximize(ULocale.forLanguageTag(source));
    }

    public LSR maximize(ULocale source) {
        return maximize(source.getLanguage(), source.getScript(), source.getCountry());
    }

    public LSR maximize(LSR source) {
        return maximize(source.language, source.script, source.region);
    }

    public LSR maximize(String language, String script, String region) {
        int retainOldMask = 0;
        Map<String, Map<String, LSR>> scriptTable = this.langTable.get(language);
        if (scriptTable == null) {
            retainOldMask = 0 | 4;
            scriptTable = this.langTable.get(LanguageTag.UNDETERMINED);
        } else if (!language.equals(LanguageTag.UNDETERMINED)) {
            retainOldMask = 0 | 4;
        }
        if (script.equals("Zzzz")) {
            script = "";
        }
        Map<String, LSR> regionTable = scriptTable.get(script);
        if (regionTable == null) {
            retainOldMask |= 2;
            regionTable = scriptTable.get("");
        } else if (!script.isEmpty()) {
            retainOldMask |= 2;
        }
        if (region.equals("ZZ")) {
            region = "";
        }
        LSR result = regionTable.get(region);
        if (result == null) {
            retainOldMask |= 1;
            result = regionTable.get("");
            if (result == null) {
                return null;
            }
        } else if (!region.isEmpty()) {
            retainOldMask |= 1;
        }
        switch (retainOldMask) {
            case 1:
                return result.replace(null, null, region);
            case 2:
                return result.replace(null, script, null);
            case 3:
                return result.replace(null, script, region);
            case 4:
                return result.replace(language, null, null);
            case 5:
                return result.replace(language, null, region);
            case 6:
                return result.replace(language, script, null);
            case 7:
                return result.replace(language, script, region);
            default:
                return result;
        }
    }

    private LSR minimizeSubtags(String languageIn, String scriptIn, String regionIn, ULocale.Minimize fieldToFavor) {
        LSR result = maximize(languageIn, scriptIn, regionIn);
        LSR value00 = this.langTable.get(result.language).get("").get("");
        boolean favorRegionOk = false;
        if (result.script.equals(value00.script)) {
            if (result.region.equals(value00.region)) {
                return result.replace(null, "", "");
            }
            if (fieldToFavor == ULocale.Minimize.FAVOR_REGION) {
                return result.replace(null, "", null);
            }
            favorRegionOk = true;
        }
        if (maximize(languageIn, scriptIn, "").equals(result)) {
            return result.replace(null, null, "");
        }
        if (favorRegionOk) {
            return result.replace(null, "", null);
        }
        return result;
    }

    private static StringBuilder show(Map<?, ?> map, String indent, StringBuilder output) {
        String first = indent.isEmpty() ? "" : "\t";
        for (Map.Entry<?, ?> e : map.entrySet()) {
            String key = e.getKey().toString();
            Object value = e.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            sb.append(key.isEmpty() ? "∅" : key);
            output.append(sb.toString());
            if (value instanceof Map) {
                show((Map) value, indent + "\t", output);
            } else {
                output.append("\t" + Objects.toString(value));
                output.append("\n");
            }
            first = indent;
        }
        return output;
    }

    public String toString() {
        return show(this.langTable, "", new StringBuilder()).toString();
    }
}
