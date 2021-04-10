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
    private final TextTrieMap<Currency.CurrencyStringInfo> longNameTrie;
    private final TextTrieMap<Currency.CurrencyStringInfo> symbolTrie;

    public static CombinedCurrencyMatcher getInstance(Currency currency, DecimalFormatSymbols dfs, int parseFlags) {
        return new CombinedCurrencyMatcher(currency, dfs, parseFlags);
    }

    private CombinedCurrencyMatcher(Currency currency, DecimalFormatSymbols dfs, int parseFlags) {
        this.isoCode = currency.getSubtype();
        this.currency1 = currency.getSymbol(dfs.getULocale());
        this.currency2 = currency.getCurrencyCode();
        this.afterPrefixInsert = dfs.getPatternForCurrencySpacing(2, false);
        this.beforeSuffixInsert = dfs.getPatternForCurrencySpacing(2, true);
        if ((parseFlags & 8192) == 0) {
            this.longNameTrie = Currency.getParsingTrie(dfs.getULocale(), 1);
            this.symbolTrie = Currency.getParsingTrie(dfs.getULocale(), 0);
            this.localLongNames = null;
            return;
        }
        this.longNameTrie = null;
        this.symbolTrie = null;
        this.localLongNames = new String[StandardPlural.COUNT];
        for (int i = 0; i < StandardPlural.COUNT; i++) {
            this.localLongNames[i] = currency.getName(dfs.getLocale(), 2, StandardPlural.VALUES.get(i).getKeyword(), (boolean[]) null);
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        boolean maybeMore = false;
        if (result.currencyCode != null) {
            return false;
        }
        int initialOffset = segment.getOffset();
        boolean maybeMore2 = false;
        if (result.seenNumber() && !this.beforeSuffixInsert.isEmpty()) {
            int overlap = segment.getCommonPrefixLength(this.beforeSuffixInsert);
            if (overlap == this.beforeSuffixInsert.length()) {
                segment.adjustOffset(overlap);
            }
            maybeMore2 = 0 != 0 || overlap == segment.length();
        }
        boolean maybeMore3 = maybeMore2 || matchCurrency(segment, result);
        if (result.currencyCode == null) {
            segment.setOffset(initialOffset);
            return maybeMore3;
        } else if (result.seenNumber() || this.afterPrefixInsert.isEmpty()) {
            return maybeMore3;
        } else {
            int overlap2 = segment.getCommonPrefixLength(this.afterPrefixInsert);
            if (overlap2 == this.afterPrefixInsert.length()) {
                segment.adjustOffset(overlap2);
            }
            if (maybeMore3 || overlap2 == segment.length()) {
                maybeMore = true;
            }
            return maybeMore;
        }
    }

    private boolean matchCurrency(StringSegment segment, ParsedNumber result) {
        int overlap1;
        int overlap2;
        if (!this.currency1.isEmpty()) {
            overlap1 = segment.getCaseSensitivePrefixLength(this.currency1);
        } else {
            overlap1 = -1;
        }
        boolean z = true;
        boolean maybeMore = 0 != 0 || overlap1 == segment.length();
        if (overlap1 == this.currency1.length()) {
            result.currencyCode = this.isoCode;
            segment.adjustOffset(overlap1);
            result.setCharsConsumed(segment);
            return maybeMore;
        }
        if (!this.currency2.isEmpty()) {
            overlap2 = segment.getCommonPrefixLength(this.currency2);
        } else {
            overlap2 = -1;
        }
        boolean maybeMore2 = maybeMore || overlap2 == segment.length();
        if (overlap2 == this.currency2.length()) {
            result.currencyCode = this.isoCode;
            segment.adjustOffset(overlap2);
            result.setCharsConsumed(segment);
            return maybeMore2;
        }
        if (this.longNameTrie != null) {
            TextTrieMap.Output trieOutput = new TextTrieMap.Output();
            Iterator<Currency.CurrencyStringInfo> values = this.longNameTrie.get(segment, 0, trieOutput);
            maybeMore2 = maybeMore2 || trieOutput.partialMatch;
            if (values == null) {
                values = this.symbolTrie.get(segment, 0, trieOutput);
                if (!maybeMore2 && !trieOutput.partialMatch) {
                    z = false;
                }
                maybeMore2 = z;
            }
            if (values != null) {
                result.currencyCode = values.next().getISOCode();
                segment.adjustOffset(trieOutput.matchLength);
                result.setCharsConsumed(segment);
                return maybeMore2;
            }
        } else {
            int longestFullMatch = 0;
            for (int i = 0; i < StandardPlural.COUNT; i++) {
                String name = this.localLongNames[i];
                if (!name.isEmpty()) {
                    int overlap = segment.getCommonPrefixLength(name);
                    if (overlap == name.length() && name.length() > longestFullMatch) {
                        longestFullMatch = name.length();
                    }
                    maybeMore2 = maybeMore2 || overlap > 0;
                }
            }
            if (longestFullMatch > 0) {
                result.currencyCode = this.isoCode;
                segment.adjustOffset(longestFullMatch);
                result.setCharsConsumed(segment);
                return maybeMore2;
            }
        }
        return maybeMore2;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        return true;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
    }

    public String toString() {
        return "<CombinedCurrencyMatcher " + this.isoCode + ">";
    }
}
