package android.icu.impl.number.parse;

import android.icu.impl.StandardPlural;
import android.icu.impl.StringSegment;
import android.icu.impl.TextTrieMap;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.Currency;
import java.util.Iterator;

public class CombinedCurrencyMatcher implements NumberParseMatcher {
    private final String afterPrefixInsert;
    private final String beforeSuffixInsert;
    private final String currency1;
    private final String currency2;
    private final String isoCode;
    private final String[] localLongNames;
    private final TextTrieMap longNameTrie;
    private final TextTrieMap symbolTrie;

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment stringSegment) {
        return true;
    }

    public static CombinedCurrencyMatcher getInstance(Currency currency, DecimalFormatSymbols decimalFormatSymbols, int i) {
        return new CombinedCurrencyMatcher(currency, decimalFormatSymbols, i);
    }

    private CombinedCurrencyMatcher(Currency currency, DecimalFormatSymbols decimalFormatSymbols, int i) {
        this.isoCode = currency.getSubtype();
        this.currency1 = currency.getSymbol(decimalFormatSymbols.getULocale());
        this.currency2 = currency.getCurrencyCode();
        this.afterPrefixInsert = decimalFormatSymbols.getPatternForCurrencySpacing(2, false);
        this.beforeSuffixInsert = decimalFormatSymbols.getPatternForCurrencySpacing(2, true);
        if ((i & 8192) == 0) {
            this.longNameTrie = Currency.getParsingTrie(decimalFormatSymbols.getULocale(), 1);
            this.symbolTrie = Currency.getParsingTrie(decimalFormatSymbols.getULocale(), 0);
            this.localLongNames = null;
            return;
        }
        this.longNameTrie = null;
        this.symbolTrie = null;
        this.localLongNames = new String[StandardPlural.COUNT];
        for (int i2 = 0; i2 < StandardPlural.COUNT; i2++) {
            this.localLongNames[i2] = currency.getName(decimalFormatSymbols.getLocale(), 2, ((StandardPlural) StandardPlural.VALUES.get(i2)).getKeyword(), (boolean[]) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    @Override // android.icu.impl.number.parse.NumberParseMatcher
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(android.icu.impl.StringSegment r6, android.icu.impl.number.parse.ParsedNumber r7) {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.parse.CombinedCurrencyMatcher.match(android.icu.impl.StringSegment, android.icu.impl.number.parse.ParsedNumber):boolean");
    }

    private boolean matchCurrency(StringSegment stringSegment, ParsedNumber parsedNumber) {
        int i = -1;
        int caseSensitivePrefixLength = !this.currency1.isEmpty() ? stringSegment.getCaseSensitivePrefixLength(this.currency1) : -1;
        boolean z = caseSensitivePrefixLength == stringSegment.length();
        if (caseSensitivePrefixLength == this.currency1.length()) {
            parsedNumber.currencyCode = this.isoCode;
            stringSegment.adjustOffset(caseSensitivePrefixLength);
            parsedNumber.setCharsConsumed(stringSegment);
            return z;
        }
        if (!this.currency2.isEmpty()) {
            i = stringSegment.getCommonPrefixLength(this.currency2);
        }
        boolean z2 = z || i == stringSegment.length();
        if (i == this.currency2.length()) {
            parsedNumber.currencyCode = this.isoCode;
            stringSegment.adjustOffset(i);
            parsedNumber.setCharsConsumed(stringSegment);
            return z2;
        } else if (this.longNameTrie != null) {
            TextTrieMap.Output output = new TextTrieMap.Output();
            Iterator it = this.longNameTrie.get(stringSegment, 0, output);
            boolean z3 = z2 || output.partialMatch;
            if (it == null) {
                it = this.symbolTrie.get(stringSegment, 0, output);
                z3 = z3 || output.partialMatch;
            }
            if (it == null) {
                return z3;
            }
            parsedNumber.currencyCode = ((Currency.CurrencyStringInfo) it.next()).getISOCode();
            stringSegment.adjustOffset(output.matchLength);
            parsedNumber.setCharsConsumed(stringSegment);
            return z3;
        } else {
            boolean z4 = z2;
            int i2 = 0;
            for (int i3 = 0; i3 < StandardPlural.COUNT; i3++) {
                String str = this.localLongNames[i3];
                if (!str.isEmpty()) {
                    int commonPrefixLength = stringSegment.getCommonPrefixLength(str);
                    if (commonPrefixLength == str.length() && str.length() > i2) {
                        i2 = str.length();
                    }
                    z4 = z4 || commonPrefixLength > 0;
                }
            }
            if (i2 <= 0) {
                return z4;
            }
            parsedNumber.currencyCode = this.isoCode;
            stringSegment.adjustOffset(i2);
            parsedNumber.setCharsConsumed(stringSegment);
            return z4;
        }
    }

    public String toString() {
        return "<CombinedCurrencyMatcher " + this.isoCode + ">";
    }
}
