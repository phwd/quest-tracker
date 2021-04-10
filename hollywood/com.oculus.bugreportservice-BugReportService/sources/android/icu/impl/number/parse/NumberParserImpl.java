package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.AffixPatternProvider;
import android.icu.impl.number.CurrencyPluralInfoAffixProvider;
import android.icu.impl.number.CustomSymbolCurrency;
import android.icu.impl.number.DecimalFormatProperties;
import android.icu.impl.number.Grouper;
import android.icu.impl.number.PropertiesAffixPatternProvider;
import android.icu.impl.number.RoundingUtils;
import android.icu.number.Scale;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.Currency;
import android.icu.util.ULocale;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NumberParserImpl {
    private boolean frozen;
    private final List matchers = new ArrayList();
    private final int parseFlags;

    public static NumberParserImpl createParserFromProperties(DecimalFormatProperties decimalFormatProperties, DecimalFormatSymbols decimalFormatSymbols, boolean z) {
        AffixPatternProvider affixPatternProvider;
        ULocale uLocale = decimalFormatSymbols.getULocale();
        if (decimalFormatProperties.getCurrencyPluralInfo() == null) {
            affixPatternProvider = new PropertiesAffixPatternProvider(decimalFormatProperties);
        } else {
            affixPatternProvider = new CurrencyPluralInfoAffixProvider(decimalFormatProperties.getCurrencyPluralInfo(), decimalFormatProperties);
        }
        Currency resolve = CustomSymbolCurrency.resolve(decimalFormatProperties.getCurrency(), uLocale, decimalFormatSymbols);
        boolean z2 = true;
        boolean z3 = decimalFormatProperties.getParseMode() == DecimalFormatProperties.ParseMode.STRICT;
        Grouper forProperties = Grouper.forProperties(decimalFormatProperties);
        int i = !decimalFormatProperties.getParseCaseSensitive() ? 1 : 0;
        if (decimalFormatProperties.getParseIntegerOnly()) {
            i |= 16;
        }
        if (decimalFormatProperties.getParseToBigDecimal()) {
            i |= 4096;
        }
        if (decimalFormatProperties.getSignAlwaysShown()) {
            i |= 1024;
        }
        int i2 = z3 ? i | 8 | 4 | 256 | 512 : i | 128;
        if (forProperties.getPrimary() <= 0) {
            i2 |= 32;
        }
        if (z || affixPatternProvider.hasCurrencySign()) {
            i2 |= 2;
        }
        if (!z) {
            i2 |= 8192;
        }
        IgnorablesMatcher ignorablesMatcher = z3 ? IgnorablesMatcher.STRICT : IgnorablesMatcher.DEFAULT;
        NumberParserImpl numberParserImpl = new NumberParserImpl(i2);
        AffixTokenMatcherFactory affixTokenMatcherFactory = new AffixTokenMatcherFactory();
        affixTokenMatcherFactory.currency = resolve;
        affixTokenMatcherFactory.symbols = decimalFormatSymbols;
        affixTokenMatcherFactory.ignorables = ignorablesMatcher;
        affixTokenMatcherFactory.locale = uLocale;
        affixTokenMatcherFactory.parseFlags = i2;
        AffixMatcher.createMatchers(affixPatternProvider, numberParserImpl, affixTokenMatcherFactory, ignorablesMatcher, i2);
        if (z || affixPatternProvider.hasCurrencySign()) {
            numberParserImpl.addMatcher(CombinedCurrencyMatcher.getInstance(resolve, decimalFormatSymbols, i2));
        }
        if (!z3 && affixPatternProvider.containsSymbolType(-3)) {
            numberParserImpl.addMatcher(PercentMatcher.getInstance(decimalFormatSymbols));
        }
        if (!z3 && affixPatternProvider.containsSymbolType(-4)) {
            numberParserImpl.addMatcher(PermilleMatcher.getInstance(decimalFormatSymbols));
        }
        if (!z3) {
            numberParserImpl.addMatcher(PlusSignMatcher.getInstance(decimalFormatSymbols, false));
            numberParserImpl.addMatcher(MinusSignMatcher.getInstance(decimalFormatSymbols, false));
        }
        numberParserImpl.addMatcher(NanMatcher.getInstance(decimalFormatSymbols, i2));
        numberParserImpl.addMatcher(InfinityMatcher.getInstance(decimalFormatSymbols));
        String padString = decimalFormatProperties.getPadString();
        if (padString != null && !ignorablesMatcher.getSet().contains(padString)) {
            numberParserImpl.addMatcher(PaddingMatcher.getInstance(padString));
        }
        numberParserImpl.addMatcher(ignorablesMatcher);
        numberParserImpl.addMatcher(DecimalMatcher.getInstance(decimalFormatSymbols, forProperties, i2));
        if (!decimalFormatProperties.getParseNoExponent() || decimalFormatProperties.getMinimumExponentDigits() > 0) {
            numberParserImpl.addMatcher(ScientificMatcher.getInstance(decimalFormatSymbols, forProperties));
        }
        numberParserImpl.addMatcher(new RequireNumberValidator());
        if (z3) {
            numberParserImpl.addMatcher(new RequireAffixValidator());
        }
        if (z) {
            numberParserImpl.addMatcher(new RequireCurrencyValidator());
        }
        if (decimalFormatProperties.getDecimalPatternMatchRequired()) {
            if (!decimalFormatProperties.getDecimalSeparatorAlwaysShown() && decimalFormatProperties.getMaximumFractionDigits() == 0) {
                z2 = false;
            }
            numberParserImpl.addMatcher(RequireDecimalSeparatorValidator.getInstance(z2));
        }
        Scale scaleFromProperties = RoundingUtils.scaleFromProperties(decimalFormatProperties);
        if (scaleFromProperties != null) {
            numberParserImpl.addMatcher(new MultiplierParseHandler(scaleFromProperties));
        }
        numberParserImpl.freeze();
        return numberParserImpl;
    }

    public NumberParserImpl(int i) {
        this.parseFlags = i;
        this.frozen = false;
    }

    public void addMatcher(NumberParseMatcher numberParseMatcher) {
        this.matchers.add(numberParseMatcher);
    }

    public void addMatchers(Collection collection) {
        this.matchers.addAll(collection);
    }

    public void freeze() {
        this.frozen = true;
    }

    public int getParseFlags() {
        return this.parseFlags;
    }

    public void parse(String str, int i, boolean z, ParsedNumber parsedNumber) {
        boolean z2 = true;
        if ((this.parseFlags & 1) == 0) {
            z2 = false;
        }
        StringSegment stringSegment = new StringSegment(str, z2);
        stringSegment.adjustOffset(i);
        if (z) {
            parseGreedyRecursive(stringSegment, parsedNumber);
        } else {
            parseLongestRecursive(stringSegment, parsedNumber);
        }
        for (NumberParseMatcher numberParseMatcher : this.matchers) {
            numberParseMatcher.postProcess(parsedNumber);
        }
        parsedNumber.postProcess();
    }

    private void parseGreedyRecursive(StringSegment stringSegment, ParsedNumber parsedNumber) {
        if (stringSegment.length() != 0) {
            int offset = stringSegment.getOffset();
            for (int i = 0; i < this.matchers.size(); i++) {
                NumberParseMatcher numberParseMatcher = (NumberParseMatcher) this.matchers.get(i);
                if (numberParseMatcher.smokeTest(stringSegment)) {
                    numberParseMatcher.match(stringSegment, parsedNumber);
                    if (stringSegment.getOffset() != offset) {
                        parseGreedyRecursive(stringSegment, parsedNumber);
                        stringSegment.setOffset(offset);
                        return;
                    }
                }
            }
        }
    }

    private void parseLongestRecursive(StringSegment stringSegment, ParsedNumber parsedNumber) {
        if (stringSegment.length() != 0) {
            ParsedNumber parsedNumber2 = new ParsedNumber();
            parsedNumber2.copyFrom(parsedNumber);
            ParsedNumber parsedNumber3 = new ParsedNumber();
            int offset = stringSegment.getOffset();
            for (int i = 0; i < this.matchers.size(); i++) {
                NumberParseMatcher numberParseMatcher = (NumberParseMatcher) this.matchers.get(i);
                if (numberParseMatcher.smokeTest(stringSegment)) {
                    int i2 = 0;
                    while (i2 < stringSegment.length()) {
                        i2 += Character.charCount(stringSegment.codePointAt(i2));
                        parsedNumber3.copyFrom(parsedNumber2);
                        stringSegment.setLength(i2);
                        boolean match = numberParseMatcher.match(stringSegment, parsedNumber3);
                        stringSegment.resetLength();
                        if (stringSegment.getOffset() - offset == i2) {
                            parseLongestRecursive(stringSegment, parsedNumber3);
                            if (parsedNumber3.isBetterThan(parsedNumber)) {
                                parsedNumber.copyFrom(parsedNumber3);
                            }
                        }
                        stringSegment.setOffset(offset);
                        if (!match) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        return "<NumberParserImpl matchers=" + this.matchers.toString() + ">";
    }
}
