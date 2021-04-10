package android.icu.util;

import android.icu.impl.CacheBase;
import android.icu.impl.ICUCache;
import android.icu.impl.ICUDebug;
import android.icu.impl.SimpleCache;
import android.icu.impl.SoftCache;
import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.TextTrieMap;
import android.icu.text.CurrencyDisplayNames;
import android.icu.text.CurrencyMetaInfo;
import android.icu.util.MeasureUnit;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Currency extends MeasureUnit {
    private static ICUCache CURRENCY_NAME_CACHE = new SimpleCache();
    private static final boolean DEBUG = ICUDebug.enabled("currency");
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final int[] POW10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    private static final ULocale UND = new ULocale("und");
    private static final CacheBase regionCurrencyCache = new SoftCache() {
        /* class android.icu.util.Currency.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public Currency createInstance(String str, Void r2) {
            return Currency.loadCurrency(str);
        }
    };
    private static final long serialVersionUID = -5839973855554750484L;
    private static ServiceShim shim;
    private final String isoCode;

    public enum CurrencyUsage {
        STANDARD,
        CASH
    }

    /* access modifiers changed from: package-private */
    public static abstract class ServiceShim {
        /* access modifiers changed from: package-private */
        public abstract Currency createInstance(ULocale uLocale);
    }

    public static Currency getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    public static Currency getInstance(ULocale uLocale) {
        String keywordValue = uLocale.getKeywordValue("currency");
        if (keywordValue != null) {
            return getInstance(keywordValue);
        }
        ServiceShim serviceShim = shim;
        if (serviceShim == null) {
            return createCurrency(uLocale);
        }
        return serviceShim.createInstance(uLocale);
    }

    static Currency createCurrency(ULocale uLocale) {
        String variant = uLocale.getVariant();
        if ("EURO".equals(variant)) {
            return getInstance("EUR");
        }
        String regionForSupplementalData = ULocale.getRegionForSupplementalData(uLocale, DEBUG);
        if ("PREEURO".equals(variant)) {
            regionForSupplementalData = regionForSupplementalData + '-';
        }
        return (Currency) regionCurrencyCache.getInstance(regionForSupplementalData, null);
    }

    /* access modifiers changed from: private */
    public static Currency loadCurrency(String str) {
        boolean z;
        if (str.endsWith("-")) {
            str = str.substring(0, str.length() - 1);
            z = true;
        } else {
            z = false;
        }
        List currencies = CurrencyMetaInfo.getInstance().currencies(CurrencyMetaInfo.CurrencyFilter.onRegion(str));
        if (currencies.isEmpty()) {
            return null;
        }
        String str2 = (String) currencies.get(0);
        if (z && "EUR".equals(str2)) {
            if (currencies.size() < 2) {
                return null;
            }
            str2 = (String) currencies.get(1);
        }
        return getInstance(str2);
    }

    public static Currency getInstance(String str) {
        if (str == null) {
            throw new NullPointerException("The input currency code is null.");
        } else if (isAlpha3Code(str)) {
            return (Currency) MeasureUnit.internalGetInstance("currency", str.toUpperCase(Locale.ENGLISH));
        } else {
            throw new IllegalArgumentException("The input currency code is not 3-letter alphabetic code.");
        }
    }

    private static boolean isAlpha3Code(String str) {
        if (str.length() != 3) {
            return DEBUG;
        }
        for (int i = 0; i < 3; i++) {
            char charAt = str.charAt(i);
            if (charAt < 'A' || ((charAt > 'Z' && charAt < 'a') || charAt > 'z')) {
                return DEBUG;
            }
        }
        return true;
    }

    public String getCurrencyCode() {
        return this.subType;
    }

    public String getSymbol(Locale locale) {
        return getSymbol(ULocale.forLocale(locale));
    }

    public String getSymbol(ULocale uLocale) {
        return getName(uLocale, 0, null);
    }

    public String getName(ULocale uLocale, int i, boolean[] zArr) {
        if (zArr != null) {
            zArr[0] = DEBUG;
        }
        CurrencyDisplayNames instance = CurrencyDisplayNames.getInstance(uLocale);
        if (i == 0) {
            return instance.getSymbol(this.subType);
        }
        if (i == 1) {
            return instance.getName(this.subType);
        }
        if (i == 3) {
            return instance.getNarrowSymbol(this.subType);
        }
        throw new IllegalArgumentException("bad name style: " + i);
    }

    public String getName(Locale locale, int i, String str, boolean[] zArr) {
        return getName(ULocale.forLocale(locale), i, str, zArr);
    }

    public String getName(ULocale uLocale, int i, String str, boolean[] zArr) {
        if (i != 2) {
            return getName(uLocale, i, zArr);
        }
        if (zArr != null) {
            zArr[0] = DEBUG;
        }
        return CurrencyDisplayNames.getInstance(uLocale).getPluralName(this.subType, str);
    }

    public static String parse(ULocale uLocale, String str, int i, ParsePosition parsePosition) {
        List currencyTrieVec = getCurrencyTrieVec(uLocale);
        CurrencyNameResultHandler currencyNameResultHandler = new CurrencyNameResultHandler();
        ((TextTrieMap) currencyTrieVec.get(1)).find(str, parsePosition.getIndex(), currencyNameResultHandler);
        String bestCurrencyISOCode = currencyNameResultHandler.getBestCurrencyISOCode();
        int bestMatchLength = currencyNameResultHandler.getBestMatchLength();
        if (i != 1) {
            CurrencyNameResultHandler currencyNameResultHandler2 = new CurrencyNameResultHandler();
            ((TextTrieMap) currencyTrieVec.get(0)).find(str, parsePosition.getIndex(), currencyNameResultHandler2);
            if (currencyNameResultHandler2.getBestMatchLength() > bestMatchLength) {
                bestCurrencyISOCode = currencyNameResultHandler2.getBestCurrencyISOCode();
                bestMatchLength = currencyNameResultHandler2.getBestMatchLength();
            }
        }
        parsePosition.setIndex(parsePosition.getIndex() + bestMatchLength);
        return bestCurrencyISOCode;
    }

    public static TextTrieMap getParsingTrie(ULocale uLocale, int i) {
        List currencyTrieVec = getCurrencyTrieVec(uLocale);
        if (i == 1) {
            return (TextTrieMap) currencyTrieVec.get(1);
        }
        return (TextTrieMap) currencyTrieVec.get(0);
    }

    private static List getCurrencyTrieVec(ULocale uLocale) {
        List list = (List) CURRENCY_NAME_CACHE.get(uLocale);
        if (list != null) {
            return list;
        }
        TextTrieMap textTrieMap = new TextTrieMap(true);
        TextTrieMap textTrieMap2 = new TextTrieMap(DEBUG);
        ArrayList arrayList = new ArrayList();
        arrayList.add(textTrieMap2);
        arrayList.add(textTrieMap);
        setupCurrencyTrieVec(uLocale, arrayList);
        CURRENCY_NAME_CACHE.put(uLocale, arrayList);
        return arrayList;
    }

    private static void setupCurrencyTrieVec(ULocale uLocale, List list) {
        TextTrieMap textTrieMap = (TextTrieMap) list.get(0);
        TextTrieMap textTrieMap2 = (TextTrieMap) list.get(1);
        CurrencyDisplayNames instance = CurrencyDisplayNames.getInstance(uLocale);
        for (Map.Entry entry : instance.symbolMap().entrySet()) {
            String str = (String) entry.getKey();
            StaticUnicodeSets.Key chooseCurrency = StaticUnicodeSets.chooseCurrency(str);
            CurrencyStringInfo currencyStringInfo = new CurrencyStringInfo((String) entry.getValue(), str);
            if (chooseCurrency != null) {
                Iterator it = StaticUnicodeSets.get(chooseCurrency).iterator();
                while (it.hasNext()) {
                    textTrieMap.put((String) it.next(), currencyStringInfo);
                }
            } else {
                textTrieMap.put(str, currencyStringInfo);
            }
        }
        for (Map.Entry entry2 : instance.nameMap().entrySet()) {
            String str2 = (String) entry2.getKey();
            textTrieMap2.put(str2, new CurrencyStringInfo((String) entry2.getValue(), str2));
        }
    }

    public static final class CurrencyStringInfo {
        private String currencyString;
        private String isoCode;

        public CurrencyStringInfo(String str, String str2) {
            this.isoCode = str;
            this.currencyString = str2;
        }

        public String getISOCode() {
            return this.isoCode;
        }
    }

    private static class CurrencyNameResultHandler implements TextTrieMap.ResultHandler {
        private String bestCurrencyISOCode;
        private int bestMatchLength;

        private CurrencyNameResultHandler() {
        }

        @Override // android.icu.impl.TextTrieMap.ResultHandler
        public boolean handlePrefixMatch(int i, Iterator it) {
            if (!it.hasNext()) {
                return true;
            }
            this.bestCurrencyISOCode = ((CurrencyStringInfo) it.next()).getISOCode();
            this.bestMatchLength = i;
            return true;
        }

        public String getBestCurrencyISOCode() {
            return this.bestCurrencyISOCode;
        }

        public int getBestMatchLength() {
            return this.bestMatchLength;
        }
    }

    public int getDefaultFractionDigits() {
        return getDefaultFractionDigits(CurrencyUsage.STANDARD);
    }

    public int getDefaultFractionDigits(CurrencyUsage currencyUsage) {
        return CurrencyMetaInfo.getInstance().currencyDigits(this.subType, currencyUsage).fractionDigits;
    }

    public double getRoundingIncrement(CurrencyUsage currencyUsage) {
        int i;
        CurrencyMetaInfo.CurrencyDigits currencyDigits = CurrencyMetaInfo.getInstance().currencyDigits(this.subType, currencyUsage);
        int i2 = currencyDigits.roundingIncrement;
        if (i2 == 0 || (i = currencyDigits.fractionDigits) < 0) {
            return 0.0d;
        }
        int[] iArr = POW10;
        if (i >= iArr.length) {
            return 0.0d;
        }
        return ((double) i2) / ((double) iArr[i]);
    }

    @Override // android.icu.util.MeasureUnit
    public String toString() {
        return this.subType;
    }

    protected Currency(String str) {
        super("currency", str);
        this.isoCode = str;
    }

    private Object writeReplace() {
        return new MeasureUnit.MeasureUnitProxy(this.type, this.subType);
    }

    private Object readResolve() {
        return getInstance(this.isoCode);
    }
}
