package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.text.CurrencyPluralInfo;

public class CurrencyPluralInfoAffixProvider implements AffixPatternProvider {
    private final PropertiesAffixPatternProvider[] affixesByPlural = new PropertiesAffixPatternProvider[StandardPlural.COUNT];

    public CurrencyPluralInfoAffixProvider(CurrencyPluralInfo currencyPluralInfo, DecimalFormatProperties decimalFormatProperties) {
        DecimalFormatProperties decimalFormatProperties2 = new DecimalFormatProperties();
        decimalFormatProperties2.copyFrom(decimalFormatProperties);
        for (StandardPlural standardPlural : StandardPlural.VALUES) {
            PatternStringParser.parseToExistingProperties(currencyPluralInfo.getCurrencyPluralPattern(standardPlural.getKeyword()), decimalFormatProperties2);
            this.affixesByPlural[standardPlural.ordinal()] = new PropertiesAffixPatternProvider(decimalFormatProperties2);
        }
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public char charAt(int i, int i2) {
        return this.affixesByPlural[i & 255].charAt(i, i2);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public int length(int i) {
        return this.affixesByPlural[i & 255].length(i);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public String getString(int i) {
        return this.affixesByPlural[i & 255].getString(i);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean positiveHasPlusSign() {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].positiveHasPlusSign();
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasNegativeSubpattern() {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasNegativeSubpattern();
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean negativeHasMinusSign() {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].negativeHasMinusSign();
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasCurrencySign() {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasCurrencySign();
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean containsSymbolType(int i) {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].containsSymbolType(i);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasBody() {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasBody();
    }
}
