package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.text.CurrencyPluralInfo;

public class CurrencyPluralInfoAffixProvider implements AffixPatternProvider {
    private final PropertiesAffixPatternProvider[] affixesByPlural = new PropertiesAffixPatternProvider[StandardPlural.COUNT];

    public CurrencyPluralInfoAffixProvider(CurrencyPluralInfo cpi, DecimalFormatProperties properties) {
        DecimalFormatProperties pluralProperties = new DecimalFormatProperties();
        pluralProperties.copyFrom(properties);
        for (StandardPlural plural : StandardPlural.VALUES) {
            PatternStringParser.parseToExistingProperties(cpi.getCurrencyPluralPattern(plural.getKeyword()), pluralProperties);
            this.affixesByPlural[plural.ordinal()] = new PropertiesAffixPatternProvider(pluralProperties);
        }
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public char charAt(int flags, int i) {
        return this.affixesByPlural[flags & 255].charAt(flags, i);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public int length(int flags) {
        return this.affixesByPlural[flags & 255].length(flags);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public String getString(int flags) {
        return this.affixesByPlural[flags & 255].getString(flags);
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
    public boolean containsSymbolType(int type) {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].containsSymbolType(type);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasBody() {
        return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasBody();
    }
}
