package android.icu.impl;

import android.icu.impl.CurrencyData;
import android.icu.impl.ICUResourceBundle;
import android.icu.util.ICUException;
import android.icu.util.ULocale;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class ICUCurrencyDisplayInfoProvider implements CurrencyData.CurrencyDisplayInfoProvider {
    private volatile ICUCurrencyDisplayInfo currencyDisplayInfoCache = null;

    @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfoProvider
    public CurrencyData.CurrencyDisplayInfo getInstance(ULocale uLocale, boolean z) {
        ICUResourceBundle iCUResourceBundle;
        if (uLocale == null) {
            uLocale = ULocale.ROOT;
        }
        ICUCurrencyDisplayInfo iCUCurrencyDisplayInfo = this.currencyDisplayInfoCache;
        if (iCUCurrencyDisplayInfo != null && iCUCurrencyDisplayInfo.locale.equals(uLocale) && iCUCurrencyDisplayInfo.fallback == z) {
            return iCUCurrencyDisplayInfo;
        }
        if (z) {
            iCUResourceBundle = ICUResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/curr", uLocale, ICUResourceBundle.OpenType.LOCALE_DEFAULT_ROOT);
        } else {
            try {
                iCUResourceBundle = ICUResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/curr", uLocale, ICUResourceBundle.OpenType.LOCALE_ONLY);
            } catch (MissingResourceException unused) {
                return null;
            }
        }
        ICUCurrencyDisplayInfo iCUCurrencyDisplayInfo2 = new ICUCurrencyDisplayInfo(uLocale, iCUResourceBundle, z);
        this.currencyDisplayInfoCache = iCUCurrencyDisplayInfo2;
        return iCUCurrencyDisplayInfo2;
    }

    static class ICUCurrencyDisplayInfo extends CurrencyData.CurrencyDisplayInfo {
        final boolean fallback;
        private volatile FormattingData formattingDataCache = null;
        final ULocale locale;
        private volatile NarrowSymbol narrowSymbolCache = null;
        private volatile SoftReference parsingDataCache = new SoftReference(null);
        private volatile String[] pluralsDataCache = null;
        private final ICUResourceBundle rb;
        private volatile CurrencyData.CurrencySpacingInfo spacingInfoCache = null;
        private volatile Map unitPatternsCache = null;

        /* access modifiers changed from: package-private */
        public static class FormattingData {
            String displayName = null;
            CurrencyData.CurrencyFormatInfo formatInfo = null;
            final String isoCode;
            String symbol = null;

            FormattingData(String str) {
                this.isoCode = str;
            }
        }

        /* access modifiers changed from: package-private */
        public static class NarrowSymbol {
            final String isoCode;
            String narrowSymbol = null;

            NarrowSymbol(String str) {
                this.isoCode = str;
            }
        }

        /* access modifiers changed from: package-private */
        public static class ParsingData {
            Map nameToIsoCode = new HashMap();
            Map symbolToIsoCode = new HashMap();

            ParsingData() {
            }
        }

        public ICUCurrencyDisplayInfo(ULocale uLocale, ICUResourceBundle iCUResourceBundle, boolean z) {
            this.locale = uLocale;
            this.fallback = z;
            this.rb = iCUResourceBundle;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getName(String str) {
            FormattingData fetchFormattingData = fetchFormattingData(str);
            if (fetchFormattingData.displayName != null || !this.fallback) {
                return fetchFormattingData.displayName;
            }
            return str;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getSymbol(String str) {
            FormattingData fetchFormattingData = fetchFormattingData(str);
            if (fetchFormattingData.symbol != null || !this.fallback) {
                return fetchFormattingData.symbol;
            }
            return str;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getNarrowSymbol(String str) {
            NarrowSymbol fetchNarrowSymbol = fetchNarrowSymbol(str);
            if (fetchNarrowSymbol.narrowSymbol != null || !this.fallback) {
                return fetchNarrowSymbol.narrowSymbol;
            }
            return str;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getPluralName(String str, String str2) {
            StandardPlural orNullFromString = StandardPlural.orNullFromString(str2);
            String[] fetchPluralsData = fetchPluralsData(str);
            String str3 = orNullFromString != null ? fetchPluralsData[orNullFromString.ordinal() + 1] : null;
            if (str3 == null && this.fallback) {
                str3 = fetchPluralsData[StandardPlural.OTHER.ordinal() + 1];
            }
            if (str3 == null && this.fallback) {
                str3 = fetchFormattingData(str).displayName;
            }
            return (str3 != null || !this.fallback) ? str3 : str;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public Map symbolMap() {
            return fetchParsingData().symbolToIsoCode;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public Map nameMap() {
            return fetchParsingData().nameToIsoCode;
        }

        @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfo
        public Map getUnitPatterns() {
            return fetchUnitPatterns();
        }

        @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfo
        public CurrencyData.CurrencyFormatInfo getFormatInfo(String str) {
            return fetchFormattingData(str).formatInfo;
        }

        @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfo
        public CurrencyData.CurrencySpacingInfo getSpacingInfo() {
            CurrencyData.CurrencySpacingInfo fetchSpacingInfo = fetchSpacingInfo();
            return ((!fetchSpacingInfo.hasBeforeCurrency || !fetchSpacingInfo.hasAfterCurrency) && this.fallback) ? CurrencyData.CurrencySpacingInfo.DEFAULT : fetchSpacingInfo;
        }

        /* access modifiers changed from: package-private */
        public FormattingData fetchFormattingData(String str) {
            FormattingData formattingData = this.formattingDataCache;
            if (formattingData != null && formattingData.isoCode.equals(str)) {
                return formattingData;
            }
            FormattingData formattingData2 = new FormattingData(str);
            CurrencySink currencySink = new CurrencySink(!this.fallback, CurrencySink.EntrypointTable.CURRENCIES);
            currencySink.formattingData = formattingData2;
            ICUResourceBundle iCUResourceBundle = this.rb;
            iCUResourceBundle.getAllItemsWithFallbackNoFail("Currencies/" + str, currencySink);
            this.formattingDataCache = formattingData2;
            return formattingData2;
        }

        /* access modifiers changed from: package-private */
        public NarrowSymbol fetchNarrowSymbol(String str) {
            NarrowSymbol narrowSymbol = this.narrowSymbolCache;
            if (narrowSymbol != null && narrowSymbol.isoCode.equals(str)) {
                return narrowSymbol;
            }
            NarrowSymbol narrowSymbol2 = new NarrowSymbol(str);
            CurrencySink currencySink = new CurrencySink(!this.fallback, CurrencySink.EntrypointTable.CURRENCY_NARROW);
            currencySink.narrowSymbol = narrowSymbol2;
            ICUResourceBundle iCUResourceBundle = this.rb;
            iCUResourceBundle.getAllItemsWithFallbackNoFail("Currencies%narrow/" + str, currencySink);
            this.narrowSymbolCache = narrowSymbol2;
            return narrowSymbol2;
        }

        /* access modifiers changed from: package-private */
        public String[] fetchPluralsData(String str) {
            String[] strArr = this.pluralsDataCache;
            if (strArr != null && strArr[0].equals(str)) {
                return strArr;
            }
            String[] strArr2 = new String[(StandardPlural.COUNT + 1)];
            strArr2[0] = str;
            CurrencySink currencySink = new CurrencySink(!this.fallback, CurrencySink.EntrypointTable.CURRENCY_PLURALS);
            currencySink.pluralsData = strArr2;
            ICUResourceBundle iCUResourceBundle = this.rb;
            iCUResourceBundle.getAllItemsWithFallbackNoFail("CurrencyPlurals/" + str, currencySink);
            this.pluralsDataCache = strArr2;
            return strArr2;
        }

        /* access modifiers changed from: package-private */
        public ParsingData fetchParsingData() {
            ParsingData parsingData = (ParsingData) this.parsingDataCache.get();
            if (parsingData != null) {
                return parsingData;
            }
            ParsingData parsingData2 = new ParsingData();
            CurrencySink currencySink = new CurrencySink(!this.fallback, CurrencySink.EntrypointTable.TOP);
            currencySink.parsingData = parsingData2;
            this.rb.getAllItemsWithFallback("", currencySink);
            this.parsingDataCache = new SoftReference(parsingData2);
            return parsingData2;
        }

        /* access modifiers changed from: package-private */
        public Map fetchUnitPatterns() {
            Map map = this.unitPatternsCache;
            if (map != null) {
                return map;
            }
            HashMap hashMap = new HashMap();
            CurrencySink currencySink = new CurrencySink(!this.fallback, CurrencySink.EntrypointTable.CURRENCY_UNIT_PATTERNS);
            currencySink.unitPatterns = hashMap;
            this.rb.getAllItemsWithFallback("CurrencyUnitPatterns", currencySink);
            this.unitPatternsCache = hashMap;
            return hashMap;
        }

        /* access modifiers changed from: package-private */
        public CurrencyData.CurrencySpacingInfo fetchSpacingInfo() {
            CurrencyData.CurrencySpacingInfo currencySpacingInfo = this.spacingInfoCache;
            if (currencySpacingInfo != null) {
                return currencySpacingInfo;
            }
            CurrencyData.CurrencySpacingInfo currencySpacingInfo2 = new CurrencyData.CurrencySpacingInfo();
            CurrencySink currencySink = new CurrencySink(!this.fallback, CurrencySink.EntrypointTable.CURRENCY_SPACING);
            currencySink.spacingInfo = currencySpacingInfo2;
            this.rb.getAllItemsWithFallback("currencySpacing", currencySink);
            this.spacingInfoCache = currencySpacingInfo2;
            return currencySpacingInfo2;
        }

        /* access modifiers changed from: private */
        public static final class CurrencySink extends UResource$Sink {
            final EntrypointTable entrypointTable;
            FormattingData formattingData = null;
            NarrowSymbol narrowSymbol = null;
            final boolean noRoot;
            ParsingData parsingData = null;
            String[] pluralsData = null;
            CurrencyData.CurrencySpacingInfo spacingInfo = null;
            Map unitPatterns = null;

            /* access modifiers changed from: package-private */
            public enum EntrypointTable {
                TOP,
                CURRENCIES,
                CURRENCY_PLURALS,
                CURRENCY_NARROW,
                CURRENCY_SPACING,
                CURRENCY_UNIT_PATTERNS
            }

            CurrencySink(boolean z, EntrypointTable entrypointTable2) {
                this.noRoot = z;
                this.entrypointTable = entrypointTable2;
            }

            @Override // android.icu.impl.UResource$Sink
            public void put(UResource$Key uResource$Key, UResource$Value uResource$Value, boolean z) {
                if (!this.noRoot || !z) {
                    switch (AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable[this.entrypointTable.ordinal()]) {
                        case 1:
                            consumeTopTable(uResource$Key, uResource$Value);
                            return;
                        case 2:
                            consumeCurrenciesEntry(uResource$Key, uResource$Value);
                            return;
                        case 3:
                            consumeCurrencyPluralsEntry(uResource$Key, uResource$Value);
                            return;
                        case 4:
                            consumeCurrenciesNarrowEntry(uResource$Key, uResource$Value);
                            return;
                        case 5:
                            consumeCurrencySpacingTable(uResource$Key, uResource$Value);
                            return;
                        case 6:
                            consumeCurrencyUnitPatternsTable(uResource$Key, uResource$Value);
                            return;
                        default:
                            return;
                    }
                }
            }

            private void consumeTopTable(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    if (uResource$Key.contentEquals("Currencies")) {
                        consumeCurrenciesTable(uResource$Key, uResource$Value);
                    } else if (uResource$Key.contentEquals("Currencies%variant")) {
                        consumeCurrenciesVariantTable(uResource$Key, uResource$Value);
                    } else if (uResource$Key.contentEquals("CurrencyPlurals")) {
                        consumeCurrencyPluralsTable(uResource$Key, uResource$Value);
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrenciesTable(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    String uResource$Key2 = uResource$Key.toString();
                    if (uResource$Value.getType() == 8) {
                        UResource$Array array = uResource$Value.getArray();
                        this.parsingData.symbolToIsoCode.put(uResource$Key2, uResource$Key2);
                        array.getValue(0, uResource$Value);
                        this.parsingData.symbolToIsoCode.put(uResource$Value.getString(), uResource$Key2);
                        array.getValue(1, uResource$Value);
                        this.parsingData.nameToIsoCode.put(uResource$Value.getString(), uResource$Key2);
                    } else {
                        throw new ICUException("Unexpected data type in Currencies table for " + uResource$Key2);
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrenciesEntry(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                String uResource$Key2 = uResource$Key.toString();
                if (uResource$Value.getType() == 8) {
                    UResource$Array array = uResource$Value.getArray();
                    if (this.formattingData.symbol == null) {
                        array.getValue(0, uResource$Value);
                        this.formattingData.symbol = uResource$Value.getString();
                    }
                    if (this.formattingData.displayName == null) {
                        array.getValue(1, uResource$Value);
                        this.formattingData.displayName = uResource$Value.getString();
                    }
                    if (array.getSize() > 2 && this.formattingData.formatInfo == null) {
                        array.getValue(2, uResource$Value);
                        UResource$Array array2 = uResource$Value.getArray();
                        array2.getValue(0, uResource$Value);
                        String string = uResource$Value.getString();
                        array2.getValue(1, uResource$Value);
                        String string2 = uResource$Value.getString();
                        array2.getValue(2, uResource$Value);
                        String string3 = uResource$Value.getString();
                        this.formattingData.formatInfo = new CurrencyData.CurrencyFormatInfo(uResource$Key2, string, string2, string3);
                        return;
                    }
                    return;
                }
                throw new ICUException("Unexpected data type in Currencies table for " + uResource$Key2);
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrenciesNarrowEntry(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                NarrowSymbol narrowSymbol2 = this.narrowSymbol;
                if (narrowSymbol2.narrowSymbol == null) {
                    narrowSymbol2.narrowSymbol = uResource$Value.getString();
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrenciesVariantTable(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    this.parsingData.symbolToIsoCode.put(uResource$Value.getString(), uResource$Key.toString());
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrencyPluralsTable(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    String uResource$Key2 = uResource$Key.toString();
                    UResource$Table table2 = uResource$Value.getTable();
                    for (int i2 = 0; table2.getKeyAndValue(i2, uResource$Key, uResource$Value); i2++) {
                        if (StandardPlural.orNullFromString(uResource$Key.toString()) != null) {
                            this.parsingData.nameToIsoCode.put(uResource$Value.getString(), uResource$Key2);
                        } else {
                            throw new ICUException("Could not make StandardPlural from keyword " + ((Object) uResource$Key));
                        }
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrencyPluralsEntry(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    StandardPlural orNullFromString = StandardPlural.orNullFromString(uResource$Key.toString());
                    if (orNullFromString != null) {
                        if (this.pluralsData[orNullFromString.ordinal() + 1] == null) {
                            this.pluralsData[orNullFromString.ordinal() + 1] = uResource$Value.getString();
                        }
                    } else {
                        throw new ICUException("Could not make StandardPlural from keyword " + ((Object) uResource$Key));
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrencySpacingTable(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                CurrencyData.CurrencySpacingInfo.SpacingType spacingType;
                CurrencyData.CurrencySpacingInfo.SpacingPattern spacingPattern;
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    if (uResource$Key.contentEquals("beforeCurrency")) {
                        spacingType = CurrencyData.CurrencySpacingInfo.SpacingType.BEFORE;
                        this.spacingInfo.hasBeforeCurrency = true;
                    } else if (uResource$Key.contentEquals("afterCurrency")) {
                        spacingType = CurrencyData.CurrencySpacingInfo.SpacingType.AFTER;
                        this.spacingInfo.hasAfterCurrency = true;
                    }
                    UResource$Table table2 = uResource$Value.getTable();
                    for (int i2 = 0; table2.getKeyAndValue(i2, uResource$Key, uResource$Value); i2++) {
                        if (uResource$Key.contentEquals("currencyMatch")) {
                            spacingPattern = CurrencyData.CurrencySpacingInfo.SpacingPattern.CURRENCY_MATCH;
                        } else if (uResource$Key.contentEquals("surroundingMatch")) {
                            spacingPattern = CurrencyData.CurrencySpacingInfo.SpacingPattern.SURROUNDING_MATCH;
                        } else if (uResource$Key.contentEquals("insertBetween")) {
                            spacingPattern = CurrencyData.CurrencySpacingInfo.SpacingPattern.INSERT_BETWEEN;
                        }
                        this.spacingInfo.setSymbolIfNull(spacingType, spacingPattern, uResource$Value.getString());
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void consumeCurrencyUnitPatternsTable(UResource$Key uResource$Key, UResource$Value uResource$Value) {
                UResource$Table table = uResource$Value.getTable();
                for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                    String uResource$Key2 = uResource$Key.toString();
                    if (this.unitPatterns.get(uResource$Key2) == null) {
                        this.unitPatterns.put(uResource$Key2, uResource$Value.getString());
                    }
                }
            }
        }
    }

    /* renamed from: android.icu.impl.ICUCurrencyDisplayInfoProvider$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable = new int[ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable = r0
                int[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable r1 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.TOP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable r1 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.CURRENCIES     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable r1 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.CURRENCY_PLURALS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable r1 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.CURRENCY_NARROW     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable r1 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.CURRENCY_SPACING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.$SwitchMap$android$icu$impl$ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable     // Catch:{ NoSuchFieldError -> 0x004b }
                android.icu.impl.ICUCurrencyDisplayInfoProvider$ICUCurrencyDisplayInfo$CurrencySink$EntrypointTable r1 = android.icu.impl.ICUCurrencyDisplayInfoProvider.ICUCurrencyDisplayInfo.CurrencySink.EntrypointTable.CURRENCY_UNIT_PATTERNS     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ICUCurrencyDisplayInfoProvider.AnonymousClass1.<clinit>():void");
        }
    }
}
