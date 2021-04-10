package android.icu.impl;

import android.icu.impl.CurrencyData;
import android.icu.impl.UResource;
import android.icu.impl.locale.AsciiUtil;
import android.icu.lang.UCharacter;
import android.icu.lang.UScript;
import android.icu.text.BreakIterator;
import android.icu.text.CaseMap;
import android.icu.text.DisplayContext;
import android.icu.text.LocaleDisplayNames;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;

public class LocaleDisplayNamesImpl extends LocaleDisplayNames {
    private static final CaseMap.Title TO_TITLE_WHOLE_STRING_NO_LOWERCASE = CaseMap.toTitle().wholeString().noLowercase();
    private static final Cache cache = new Cache(null);
    private static final Map<String, CapitalizationContextUsage> contextUsageTypeMap = new HashMap();
    private final DisplayContext capitalization;
    private transient BreakIterator capitalizationBrkIter;
    private boolean[] capitalizationUsage;
    private final CurrencyData.CurrencyDisplayInfo currencyDisplayInfo;
    private final LocaleDisplayNames.DialectHandling dialectHandling;
    private final String format;
    private final char formatCloseParen;
    private final char formatOpenParen;
    private final char formatReplaceCloseParen;
    private final char formatReplaceOpenParen;
    private final String keyTypeFormat;
    private final DataTable langData;
    private final ULocale locale;
    private final DisplayContext nameLength;
    private final DataTable regionData;
    private final String separatorFormat;
    private final DisplayContext substituteHandling;

    /* access modifiers changed from: private */
    public enum CapitalizationContextUsage {
        LANGUAGE,
        SCRIPT,
        TERRITORY,
        VARIANT,
        KEY,
        KEYVALUE
    }

    public enum DataTableType {
        LANG,
        REGION
    }

    static {
        contextUsageTypeMap.put("languages", CapitalizationContextUsage.LANGUAGE);
        contextUsageTypeMap.put("script", CapitalizationContextUsage.SCRIPT);
        contextUsageTypeMap.put("territory", CapitalizationContextUsage.TERRITORY);
        contextUsageTypeMap.put("variant", CapitalizationContextUsage.VARIANT);
        contextUsageTypeMap.put("key", CapitalizationContextUsage.KEY);
        contextUsageTypeMap.put("keyValue", CapitalizationContextUsage.KEYVALUE);
    }

    private static String toTitleWholeStringNoLowercase(ULocale locale2, String s) {
        return TO_TITLE_WHOLE_STRING_NO_LOWERCASE.apply(locale2.toLocale(), null, s);
    }

    public static LocaleDisplayNames getInstance(ULocale locale2, LocaleDisplayNames.DialectHandling dialectHandling2) {
        LocaleDisplayNames localeDisplayNames;
        synchronized (cache) {
            localeDisplayNames = cache.get(locale2, dialectHandling2);
        }
        return localeDisplayNames;
    }

    public static LocaleDisplayNames getInstance(ULocale locale2, DisplayContext... contexts) {
        LocaleDisplayNames localeDisplayNames;
        synchronized (cache) {
            localeDisplayNames = cache.get(locale2, contexts);
        }
        return localeDisplayNames;
    }

    private final class CapitalizationContextSink extends UResource.Sink {
        boolean hasCapitalizationUsage;

        private CapitalizationContextSink() {
            this.hasCapitalizationUsage = false;
        }

        /* synthetic */ CapitalizationContextSink(LocaleDisplayNamesImpl x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table contextsTable = value.getTable();
            for (int i = 0; contextsTable.getKeyAndValue(i, key, value); i++) {
                CapitalizationContextUsage usage = (CapitalizationContextUsage) LocaleDisplayNamesImpl.contextUsageTypeMap.get(key.toString());
                if (usage != null) {
                    int[] intVector = value.getIntVector();
                    if (intVector.length >= 2) {
                        if ((LocaleDisplayNamesImpl.this.capitalization == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU ? intVector[0] : intVector[1]) != 0) {
                            LocaleDisplayNamesImpl.this.capitalizationUsage[usage.ordinal()] = true;
                            this.hasCapitalizationUsage = true;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LocaleDisplayNamesImpl(android.icu.util.ULocale r4, android.icu.text.LocaleDisplayNames.DialectHandling r5) {
        /*
            r3 = this;
            r0 = 2
            android.icu.text.DisplayContext[] r0 = new android.icu.text.DisplayContext[r0]
            android.icu.text.LocaleDisplayNames$DialectHandling r1 = android.icu.text.LocaleDisplayNames.DialectHandling.STANDARD_NAMES
            if (r5 != r1) goto L_0x000a
            android.icu.text.DisplayContext r1 = android.icu.text.DisplayContext.STANDARD_NAMES
            goto L_0x000c
        L_0x000a:
            android.icu.text.DisplayContext r1 = android.icu.text.DisplayContext.DIALECT_NAMES
        L_0x000c:
            r2 = 0
            r0[r2] = r1
            r1 = 1
            android.icu.text.DisplayContext r2 = android.icu.text.DisplayContext.CAPITALIZATION_NONE
            r0[r1] = r2
            r3.<init>(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.LocaleDisplayNamesImpl.<init>(android.icu.util.ULocale, android.icu.text.LocaleDisplayNames$DialectHandling):void");
    }

    public LocaleDisplayNamesImpl(ULocale locale2, DisplayContext... contexts) {
        boolean z;
        ULocale uLocale;
        this.capitalizationUsage = null;
        this.capitalizationBrkIter = null;
        LocaleDisplayNames.DialectHandling dialectHandling2 = LocaleDisplayNames.DialectHandling.STANDARD_NAMES;
        DisplayContext capitalization2 = DisplayContext.CAPITALIZATION_NONE;
        DisplayContext nameLength2 = DisplayContext.LENGTH_FULL;
        DisplayContext substituteHandling2 = DisplayContext.SUBSTITUTE;
        int length = contexts.length;
        DisplayContext substituteHandling3 = substituteHandling2;
        DisplayContext nameLength3 = nameLength2;
        DisplayContext capitalization3 = capitalization2;
        LocaleDisplayNames.DialectHandling dialectHandling3 = dialectHandling2;
        int i = 0;
        while (true) {
            z = true;
            if (i >= length) {
                break;
            }
            DisplayContext contextItem = contexts[i];
            int i2 = AnonymousClass1.$SwitchMap$android$icu$text$DisplayContext$Type[contextItem.type().ordinal()];
            if (i2 == 1) {
                dialectHandling3 = contextItem.value() == DisplayContext.STANDARD_NAMES.value() ? LocaleDisplayNames.DialectHandling.STANDARD_NAMES : LocaleDisplayNames.DialectHandling.DIALECT_NAMES;
            } else if (i2 == 2) {
                capitalization3 = contextItem;
            } else if (i2 == 3) {
                nameLength3 = contextItem;
            } else if (i2 == 4) {
                substituteHandling3 = contextItem;
            }
            i++;
        }
        this.dialectHandling = dialectHandling3;
        this.capitalization = capitalization3;
        this.nameLength = nameLength3;
        this.substituteHandling = substituteHandling3;
        this.langData = LangDataTables.impl.get(locale2, substituteHandling3 == DisplayContext.NO_SUBSTITUTE);
        this.regionData = RegionDataTables.impl.get(locale2, substituteHandling3 != DisplayContext.NO_SUBSTITUTE ? false : z);
        if (ULocale.ROOT.equals(this.langData.getLocale())) {
            uLocale = this.regionData.getLocale();
        } else {
            uLocale = this.langData.getLocale();
        }
        this.locale = uLocale;
        String sep = this.langData.get("localeDisplayPattern", "separator");
        sep = (sep == null || "separator".equals(sep)) ? "{0}, {1}" : sep;
        StringBuilder sb = new StringBuilder();
        this.separatorFormat = SimpleFormatterImpl.compileToStringMinMaxArguments(sep, sb, 2, 2);
        String pattern = this.langData.get("localeDisplayPattern", "pattern");
        pattern = (pattern == null || "pattern".equals(pattern)) ? "{0} ({1})" : pattern;
        this.format = SimpleFormatterImpl.compileToStringMinMaxArguments(pattern, sb, 2, 2);
        if (pattern.contains("（")) {
            this.formatOpenParen = 65288;
            this.formatCloseParen = 65289;
            this.formatReplaceOpenParen = 65339;
            this.formatReplaceCloseParen = 65341;
        } else {
            this.formatOpenParen = '(';
            this.formatCloseParen = ')';
            this.formatReplaceOpenParen = '[';
            this.formatReplaceCloseParen = ']';
        }
        String keyTypePattern = this.langData.get("localeDisplayPattern", "keyTypePattern");
        this.keyTypeFormat = SimpleFormatterImpl.compileToStringMinMaxArguments((keyTypePattern == null || "keyTypePattern".equals(keyTypePattern)) ? "{0}={1}" : keyTypePattern, sb, 2, 2);
        boolean needBrkIter = false;
        if (capitalization3 == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU || capitalization3 == DisplayContext.CAPITALIZATION_FOR_STANDALONE) {
            this.capitalizationUsage = new boolean[CapitalizationContextUsage.values().length];
            ICUResourceBundle rb = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale2);
            CapitalizationContextSink sink = new CapitalizationContextSink(this, null);
            try {
                rb.getAllItemsWithFallback("contextTransforms", sink);
            } catch (MissingResourceException e) {
            }
            needBrkIter = sink.hasCapitalizationUsage;
        }
        if (needBrkIter || capitalization3 == DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE) {
            this.capitalizationBrkIter = BreakIterator.getSentenceInstance(locale2);
        }
        this.currencyDisplayInfo = CurrencyData.provider.getInstance(locale2, false);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public ULocale getLocale() {
        return this.locale;
    }

    @Override // android.icu.text.LocaleDisplayNames
    public LocaleDisplayNames.DialectHandling getDialectHandling() {
        return this.dialectHandling;
    }

    @Override // android.icu.text.LocaleDisplayNames
    public DisplayContext getContext(DisplayContext.Type type) {
        int i = AnonymousClass1.$SwitchMap$android$icu$text$DisplayContext$Type[type.ordinal()];
        if (i == 1) {
            return this.dialectHandling == LocaleDisplayNames.DialectHandling.STANDARD_NAMES ? DisplayContext.STANDARD_NAMES : DisplayContext.DIALECT_NAMES;
        }
        if (i == 2) {
            return this.capitalization;
        }
        if (i == 3) {
            return this.nameLength;
        }
        if (i != 4) {
            return DisplayContext.STANDARD_NAMES;
        }
        return this.substituteHandling;
    }

    private String adjustForUsageAndContext(CapitalizationContextUsage usage, String name) {
        String titleCase;
        boolean[] zArr;
        if (name == null || name.length() <= 0 || !UCharacter.isLowerCase(name.codePointAt(0)) || (this.capitalization != DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE && ((zArr = this.capitalizationUsage) == null || !zArr[usage.ordinal()]))) {
            return name;
        }
        synchronized (this) {
            if (this.capitalizationBrkIter == null) {
                this.capitalizationBrkIter = BreakIterator.getSentenceInstance(this.locale);
            }
            titleCase = UCharacter.toTitleCase(this.locale, name, this.capitalizationBrkIter, 768);
        }
        return titleCase;
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String localeDisplayName(ULocale locale2) {
        return localeDisplayNameInternal(locale2);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String localeDisplayName(Locale locale2) {
        return localeDisplayNameInternal(ULocale.forLocale(locale2));
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String localeDisplayName(String localeId) {
        return localeDisplayNameInternal(new ULocale(localeId));
    }

    private String localeDisplayNameInternal(ULocale locale2) {
        String resultName = null;
        String lang = locale2.getLanguage();
        if (locale2.getBaseName().length() == 0) {
            lang = "root";
        }
        String script = locale2.getScript();
        String country = locale2.getCountry();
        String variant = locale2.getVariant();
        boolean z = true;
        boolean hasScript = script.length() > 0;
        boolean hasCountry = country.length() > 0;
        boolean hasVariant = variant.length() > 0;
        if (this.dialectHandling == LocaleDisplayNames.DialectHandling.DIALECT_NAMES) {
            if (hasScript && hasCountry) {
                String langScriptCountry = lang + '_' + script + '_' + country;
                String result = localeIdName(langScriptCountry);
                if (result != null && !result.equals(langScriptCountry)) {
                    resultName = result;
                    hasScript = false;
                    hasCountry = false;
                }
            }
            if (hasScript) {
                String langScript = lang + '_' + script;
                String result2 = localeIdName(langScript);
                if (result2 != null && !result2.equals(langScript)) {
                    resultName = result2;
                    hasScript = false;
                }
            }
            if (hasCountry) {
                String langCountry = lang + '_' + country;
                String result3 = localeIdName(langCountry);
                if (result3 != null && !result3.equals(langCountry)) {
                    resultName = result3;
                    hasCountry = false;
                }
            }
        }
        if (resultName == null) {
            String result4 = localeIdName(lang);
            if (result4 == null) {
                return null;
            }
            resultName = result4.replace(this.formatOpenParen, this.formatReplaceOpenParen).replace(this.formatCloseParen, this.formatReplaceCloseParen);
        }
        StringBuilder buf = new StringBuilder();
        if (hasScript) {
            String result5 = scriptDisplayNameInContext(script, true);
            if (result5 == null) {
                return null;
            }
            buf.append(result5.replace(this.formatOpenParen, this.formatReplaceOpenParen).replace(this.formatCloseParen, this.formatReplaceCloseParen));
        }
        if (hasCountry) {
            String result6 = regionDisplayName(country, true);
            if (result6 == null) {
                return null;
            }
            appendWithSep(result6.replace(this.formatOpenParen, this.formatReplaceOpenParen).replace(this.formatCloseParen, this.formatReplaceCloseParen), buf);
        }
        if (hasVariant) {
            String result7 = variantDisplayName(variant, true);
            if (result7 == null) {
                return null;
            }
            appendWithSep(result7.replace(this.formatOpenParen, this.formatReplaceOpenParen).replace(this.formatCloseParen, this.formatReplaceCloseParen), buf);
        }
        Iterator<String> keys = locale2.getKeywords();
        if (keys != null) {
            while (keys.hasNext()) {
                String key = keys.next();
                String value = locale2.getKeywordValue(key);
                String keyDisplayName = keyDisplayName(key, z);
                if (keyDisplayName == null) {
                    return null;
                }
                String keyDisplayName2 = keyDisplayName.replace(this.formatOpenParen, this.formatReplaceOpenParen).replace(this.formatCloseParen, this.formatReplaceCloseParen);
                String valueDisplayName = keyValueDisplayName(key, value, true);
                if (valueDisplayName == null) {
                    return null;
                }
                String valueDisplayName2 = valueDisplayName.replace(this.formatOpenParen, this.formatReplaceOpenParen).replace(this.formatCloseParen, this.formatReplaceCloseParen);
                if (!valueDisplayName2.equals(value)) {
                    appendWithSep(valueDisplayName2, buf);
                } else if (!key.equals(keyDisplayName2)) {
                    appendWithSep(SimpleFormatterImpl.formatCompiledPattern(this.keyTypeFormat, keyDisplayName2, valueDisplayName2), buf);
                } else {
                    StringBuilder appendWithSep = appendWithSep(keyDisplayName2, buf);
                    appendWithSep.append("=");
                    appendWithSep.append(valueDisplayName2);
                }
                lang = lang;
                script = script;
                country = country;
                z = true;
            }
        }
        String resultRemainder = null;
        if (buf.length() > 0) {
            resultRemainder = buf.toString();
        }
        if (resultRemainder != null) {
            resultName = SimpleFormatterImpl.formatCompiledPattern(this.format, resultName, resultRemainder);
        }
        return adjustForUsageAndContext(CapitalizationContextUsage.LANGUAGE, resultName);
    }

    private String localeIdName(String localeId) {
        String locIdName;
        if (this.nameLength != DisplayContext.LENGTH_SHORT || (locIdName = this.langData.get("Languages%short", localeId)) == null || locIdName.equals(localeId)) {
            return this.langData.get("Languages", localeId);
        }
        return locIdName;
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String languageDisplayName(String lang) {
        String langName;
        if (lang.equals("root") || lang.indexOf(95) != -1) {
            if (this.substituteHandling == DisplayContext.SUBSTITUTE) {
                return lang;
            }
            return null;
        } else if (this.nameLength != DisplayContext.LENGTH_SHORT || (langName = this.langData.get("Languages%short", lang)) == null || langName.equals(lang)) {
            return adjustForUsageAndContext(CapitalizationContextUsage.LANGUAGE, this.langData.get("Languages", lang));
        } else {
            return adjustForUsageAndContext(CapitalizationContextUsage.LANGUAGE, langName);
        }
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String scriptDisplayName(String script) {
        String str;
        String str2 = this.langData.get("Scripts%stand-alone", script);
        if (str2 == null || str2.equals(script)) {
            if (this.nameLength == DisplayContext.LENGTH_SHORT && (str = this.langData.get("Scripts%short", script)) != null && !str.equals(script)) {
                return adjustForUsageAndContext(CapitalizationContextUsage.SCRIPT, str);
            }
            str2 = this.langData.get("Scripts", script);
        }
        return adjustForUsageAndContext(CapitalizationContextUsage.SCRIPT, str2);
    }

    private String scriptDisplayNameInContext(String script, boolean skipAdjust) {
        String scriptName;
        if (this.nameLength == DisplayContext.LENGTH_SHORT && (scriptName = this.langData.get("Scripts%short", script)) != null && !scriptName.equals(script)) {
            return skipAdjust ? scriptName : adjustForUsageAndContext(CapitalizationContextUsage.SCRIPT, scriptName);
        }
        String scriptName2 = this.langData.get("Scripts", script);
        return skipAdjust ? scriptName2 : adjustForUsageAndContext(CapitalizationContextUsage.SCRIPT, scriptName2);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String scriptDisplayNameInContext(String script) {
        return scriptDisplayNameInContext(script, false);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String scriptDisplayName(int scriptCode) {
        return scriptDisplayName(UScript.getShortName(scriptCode));
    }

    private String regionDisplayName(String region, boolean skipAdjust) {
        String regionName;
        if (this.nameLength == DisplayContext.LENGTH_SHORT && (regionName = this.regionData.get("Countries%short", region)) != null && !regionName.equals(region)) {
            return skipAdjust ? regionName : adjustForUsageAndContext(CapitalizationContextUsage.TERRITORY, regionName);
        }
        String regionName2 = this.regionData.get("Countries", region);
        return skipAdjust ? regionName2 : adjustForUsageAndContext(CapitalizationContextUsage.TERRITORY, regionName2);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String regionDisplayName(String region) {
        return regionDisplayName(region, false);
    }

    private String variantDisplayName(String variant, boolean skipAdjust) {
        String variantName = this.langData.get("Variants", variant);
        return skipAdjust ? variantName : adjustForUsageAndContext(CapitalizationContextUsage.VARIANT, variantName);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String variantDisplayName(String variant) {
        return variantDisplayName(variant, false);
    }

    private String keyDisplayName(String key, boolean skipAdjust) {
        String keyName = this.langData.get("Keys", key);
        return skipAdjust ? keyName : adjustForUsageAndContext(CapitalizationContextUsage.KEY, keyName);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String keyDisplayName(String key) {
        return keyDisplayName(key, false);
    }

    private String keyValueDisplayName(String key, String value, boolean skipAdjust) {
        String tmp;
        String keyValueName = null;
        if (key.equals("currency")) {
            keyValueName = this.currencyDisplayInfo.getName(AsciiUtil.toUpperString(value));
            if (keyValueName == null) {
                keyValueName = value;
            }
        } else {
            if (this.nameLength == DisplayContext.LENGTH_SHORT && (tmp = this.langData.get("Types%short", key, value)) != null && !tmp.equals(value)) {
                keyValueName = tmp;
            }
            if (keyValueName == null) {
                keyValueName = this.langData.get("Types", key, value);
            }
        }
        return skipAdjust ? keyValueName : adjustForUsageAndContext(CapitalizationContextUsage.KEYVALUE, keyValueName);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public String keyValueDisplayName(String key, String value) {
        return keyValueDisplayName(key, value, false);
    }

    @Override // android.icu.text.LocaleDisplayNames
    public List<LocaleDisplayNames.UiListItem> getUiListCompareWholeItems(Set<ULocale> localeSet, Comparator<LocaleDisplayNames.UiListItem> comparator) {
        DisplayContext capContext = getContext(DisplayContext.Type.CAPITALIZATION);
        List<LocaleDisplayNames.UiListItem> result = new ArrayList<>();
        Map<ULocale, Set<ULocale>> baseToLocales = new HashMap<>();
        ULocale.Builder builder = new ULocale.Builder();
        for (ULocale locOriginal : localeSet) {
            builder.setLocale(locOriginal);
            ULocale loc = ULocale.addLikelySubtags(locOriginal);
            ULocale base = new ULocale(loc.getLanguage());
            Set<ULocale> locales = baseToLocales.get(base);
            if (locales == null) {
                Set<ULocale> hashSet = new HashSet<>();
                locales = hashSet;
                baseToLocales.put(base, hashSet);
            }
            locales.add(loc);
        }
        for (Map.Entry<ULocale, Set<ULocale>> entry : baseToLocales.entrySet()) {
            ULocale base2 = entry.getKey();
            Set<ULocale> values = entry.getValue();
            if (values.size() == 1) {
                result.add(newRow(ULocale.minimizeSubtags(values.iterator().next(), ULocale.Minimize.FAVOR_SCRIPT), capContext));
            } else {
                Set<String> scripts = new HashSet<>();
                Set<String> regions = new HashSet<>();
                ULocale maxBase = ULocale.addLikelySubtags(base2);
                scripts.add(maxBase.getScript());
                regions.add(maxBase.getCountry());
                for (ULocale locale2 : values) {
                    scripts.add(locale2.getScript());
                    regions.add(locale2.getCountry());
                }
                boolean hasRegions = false;
                boolean hasScripts = scripts.size() > 1;
                if (regions.size() > 1) {
                    hasRegions = true;
                }
                for (ULocale locale3 : values) {
                    ULocale.Builder modified = builder.setLocale(locale3);
                    if (!hasScripts) {
                        modified.setScript("");
                    }
                    if (!hasRegions) {
                        modified.setRegion("");
                    }
                    result.add(newRow(modified.build(), capContext));
                    baseToLocales = baseToLocales;
                    builder = builder;
                }
            }
            baseToLocales = baseToLocales;
            builder = builder;
        }
        Collections.sort(result, comparator);
        return result;
    }

    private LocaleDisplayNames.UiListItem newRow(ULocale modified, DisplayContext capContext) {
        ULocale minimized = ULocale.minimizeSubtags(modified, ULocale.Minimize.FAVOR_SCRIPT);
        String tempName = modified.getDisplayName(this.locale);
        String nameInDisplayLocale = capContext == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU ? toTitleWholeStringNoLowercase(this.locale, tempName) : tempName;
        String tempName2 = modified.getDisplayName(modified);
        return new LocaleDisplayNames.UiListItem(minimized, modified, nameInDisplayLocale, capContext == DisplayContext.CAPITALIZATION_FOR_UI_LIST_OR_MENU ? toTitleWholeStringNoLowercase(modified, tempName2) : tempName2);
    }

    public static class DataTable {
        final boolean nullIfNotFound;

        DataTable(boolean nullIfNotFound2) {
            this.nullIfNotFound = nullIfNotFound2;
        }

        /* access modifiers changed from: package-private */
        public ULocale getLocale() {
            return ULocale.ROOT;
        }

        /* access modifiers changed from: package-private */
        public String get(String tableName, String code) {
            return get(tableName, null, code);
        }

        /* access modifiers changed from: package-private */
        public String get(String tableName, String subTableName, String code) {
            if (this.nullIfNotFound) {
                return null;
            }
            return code;
        }
    }

    static class ICUDataTable extends DataTable {
        private final ICUResourceBundle bundle;

        public ICUDataTable(String path, ULocale locale, boolean nullIfNotFound) {
            super(nullIfNotFound);
            this.bundle = (ICUResourceBundle) UResourceBundle.getBundleInstance(path, locale.getBaseName());
        }

        @Override // android.icu.impl.LocaleDisplayNamesImpl.DataTable
        public ULocale getLocale() {
            return this.bundle.getULocale();
        }

        @Override // android.icu.impl.LocaleDisplayNamesImpl.DataTable
        public String get(String tableName, String subTableName, String code) {
            return ICUResourceTableAccess.getTableString(this.bundle, tableName, subTableName, code, this.nullIfNotFound ? null : code);
        }
    }

    static abstract class DataTables {
        public abstract DataTable get(ULocale uLocale, boolean z);

        DataTables() {
        }

        public static DataTables load(String className) {
            try {
                return (DataTables) Class.forName(className).newInstance();
            } catch (Throwable th) {
                return new DataTables() {
                    /* class android.icu.impl.LocaleDisplayNamesImpl.DataTables.AnonymousClass1 */

                    @Override // android.icu.impl.LocaleDisplayNamesImpl.DataTables
                    public DataTable get(ULocale locale, boolean nullIfNotFound) {
                        return new DataTable(nullIfNotFound);
                    }
                };
            }
        }
    }

    static abstract class ICUDataTables extends DataTables {
        private final String path;

        protected ICUDataTables(String path2) {
            this.path = path2;
        }

        @Override // android.icu.impl.LocaleDisplayNamesImpl.DataTables
        public DataTable get(ULocale locale, boolean nullIfNotFound) {
            return new ICUDataTable(this.path, locale, nullIfNotFound);
        }
    }

    static class LangDataTables {
        static final DataTables impl = DataTables.load("android.icu.impl.ICULangDataTables");

        LangDataTables() {
        }
    }

    static class RegionDataTables {
        static final DataTables impl = DataTables.load("android.icu.impl.ICURegionDataTables");

        RegionDataTables() {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.LocaleDisplayNamesImpl$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$LocaleDisplayNamesImpl$DataTableType = new int[DataTableType.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$DisplayContext$Type = new int[DisplayContext.Type.values().length];

        static {
            try {
                $SwitchMap$android$icu$impl$LocaleDisplayNamesImpl$DataTableType[DataTableType.LANG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$impl$LocaleDisplayNamesImpl$DataTableType[DataTableType.REGION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$text$DisplayContext$Type[DisplayContext.Type.DIALECT_HANDLING.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$text$DisplayContext$Type[DisplayContext.Type.CAPITALIZATION.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$text$DisplayContext$Type[DisplayContext.Type.DISPLAY_LENGTH.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$icu$text$DisplayContext$Type[DisplayContext.Type.SUBSTITUTE_HANDLING.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static boolean haveData(DataTableType type) {
        int i = AnonymousClass1.$SwitchMap$android$icu$impl$LocaleDisplayNamesImpl$DataTableType[type.ordinal()];
        if (i == 1) {
            return LangDataTables.impl instanceof ICUDataTables;
        }
        if (i == 2) {
            return RegionDataTables.impl instanceof ICUDataTables;
        }
        throw new IllegalArgumentException("unknown type: " + ((Object) type));
    }

    private StringBuilder appendWithSep(String s, StringBuilder b) {
        if (b.length() == 0) {
            b.append(s);
        } else {
            SimpleFormatterImpl.formatAndReplace(this.separatorFormat, b, null, b, s);
        }
        return b;
    }

    private static class Cache {
        private LocaleDisplayNames cache;
        private DisplayContext capitalization;
        private LocaleDisplayNames.DialectHandling dialectHandling;
        private ULocale locale;
        private DisplayContext nameLength;
        private DisplayContext substituteHandling;

        private Cache() {
        }

        /* synthetic */ Cache(AnonymousClass1 x0) {
            this();
        }

        public LocaleDisplayNames get(ULocale locale2, LocaleDisplayNames.DialectHandling dialectHandling2) {
            if (!(dialectHandling2 == this.dialectHandling && DisplayContext.CAPITALIZATION_NONE == this.capitalization && DisplayContext.LENGTH_FULL == this.nameLength && DisplayContext.SUBSTITUTE == this.substituteHandling && locale2.equals(this.locale))) {
                this.locale = locale2;
                this.dialectHandling = dialectHandling2;
                this.capitalization = DisplayContext.CAPITALIZATION_NONE;
                this.nameLength = DisplayContext.LENGTH_FULL;
                this.substituteHandling = DisplayContext.SUBSTITUTE;
                this.cache = new LocaleDisplayNamesImpl(locale2, dialectHandling2);
            }
            return this.cache;
        }

        public LocaleDisplayNames get(ULocale locale2, DisplayContext... contexts) {
            LocaleDisplayNames.DialectHandling dialectHandlingIn = LocaleDisplayNames.DialectHandling.STANDARD_NAMES;
            DisplayContext capitalizationIn = DisplayContext.CAPITALIZATION_NONE;
            DisplayContext nameLengthIn = DisplayContext.LENGTH_FULL;
            DisplayContext substituteHandling2 = DisplayContext.SUBSTITUTE;
            for (DisplayContext contextItem : contexts) {
                int i = AnonymousClass1.$SwitchMap$android$icu$text$DisplayContext$Type[contextItem.type().ordinal()];
                if (i == 1) {
                    dialectHandlingIn = contextItem.value() == DisplayContext.STANDARD_NAMES.value() ? LocaleDisplayNames.DialectHandling.STANDARD_NAMES : LocaleDisplayNames.DialectHandling.DIALECT_NAMES;
                } else if (i == 2) {
                    capitalizationIn = contextItem;
                } else if (i == 3) {
                    nameLengthIn = contextItem;
                } else if (i == 4) {
                    substituteHandling2 = contextItem;
                }
            }
            if (!(dialectHandlingIn == this.dialectHandling && capitalizationIn == this.capitalization && nameLengthIn == this.nameLength && substituteHandling2 == this.substituteHandling && locale2.equals(this.locale))) {
                this.locale = locale2;
                this.dialectHandling = dialectHandlingIn;
                this.capitalization = capitalizationIn;
                this.nameLength = nameLengthIn;
                this.substituteHandling = substituteHandling2;
                this.cache = new LocaleDisplayNamesImpl(locale2, contexts);
            }
            return this.cache;
        }
    }
}
