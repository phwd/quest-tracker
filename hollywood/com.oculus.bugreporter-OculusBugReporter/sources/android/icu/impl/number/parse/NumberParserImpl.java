package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.AffixPatternProvider;
import android.icu.impl.number.CurrencyPluralInfoAffixProvider;
import android.icu.impl.number.CustomSymbolCurrency;
import android.icu.impl.number.DecimalFormatProperties;
import android.icu.impl.number.Grouper;
import android.icu.impl.number.PatternStringParser;
import android.icu.impl.number.PropertiesAffixPatternProvider;
import android.icu.impl.number.RoundingUtils;
import android.icu.number.NumberFormatter;
import android.icu.number.Scale;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NumberParserImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean frozen;
    private final List<NumberParseMatcher> matchers = new ArrayList();
    private final int parseFlags;

    public static NumberParserImpl createSimpleParser(ULocale locale, String pattern, int parseFlags2) {
        NumberParserImpl parser = new NumberParserImpl(parseFlags2);
        Currency currency = Currency.getInstance("USD");
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);
        IgnorablesMatcher ignorables = IgnorablesMatcher.DEFAULT;
        AffixTokenMatcherFactory factory = new AffixTokenMatcherFactory();
        factory.currency = currency;
        factory.symbols = symbols;
        factory.ignorables = ignorables;
        factory.locale = locale;
        factory.parseFlags = parseFlags2;
        PatternStringParser.ParsedPatternInfo patternInfo = PatternStringParser.parseToPatternInfo(pattern);
        AffixMatcher.createMatchers(patternInfo, parser, factory, ignorables, parseFlags2);
        Grouper grouper = Grouper.forStrategy(NumberFormatter.GroupingStrategy.AUTO).withLocaleData(locale, patternInfo);
        parser.addMatcher(ignorables);
        parser.addMatcher(DecimalMatcher.getInstance(symbols, grouper, parseFlags2));
        parser.addMatcher(MinusSignMatcher.getInstance(symbols, false));
        parser.addMatcher(PlusSignMatcher.getInstance(symbols, false));
        parser.addMatcher(PercentMatcher.getInstance(symbols));
        parser.addMatcher(PermilleMatcher.getInstance(symbols));
        parser.addMatcher(NanMatcher.getInstance(symbols, parseFlags2));
        parser.addMatcher(InfinityMatcher.getInstance(symbols));
        parser.addMatcher(PaddingMatcher.getInstance("@"));
        parser.addMatcher(ScientificMatcher.getInstance(symbols, grouper));
        parser.addMatcher(CombinedCurrencyMatcher.getInstance(currency, symbols, parseFlags2));
        parser.addMatcher(new RequireNumberValidator());
        parser.freeze();
        return parser;
    }

    public static Number parseStatic(String input, ParsePosition ppos, DecimalFormatProperties properties, DecimalFormatSymbols symbols) {
        NumberParserImpl parser = createParserFromProperties(properties, symbols, false);
        ParsedNumber result = new ParsedNumber();
        parser.parse(input, true, result);
        if (result.success()) {
            ppos.setIndex(result.charEnd);
            return result.getNumber();
        }
        ppos.setErrorIndex(result.charEnd);
        return null;
    }

    public static CurrencyAmount parseStaticCurrency(String input, ParsePosition ppos, DecimalFormatProperties properties, DecimalFormatSymbols symbols) {
        NumberParserImpl parser = createParserFromProperties(properties, symbols, true);
        ParsedNumber result = new ParsedNumber();
        parser.parse(input, true, result);
        if (result.success()) {
            ppos.setIndex(result.charEnd);
            return new CurrencyAmount(result.getNumber(), Currency.getInstance(result.currencyCode));
        }
        ppos.setErrorIndex(result.charEnd);
        return null;
    }

    public static NumberParserImpl createDefaultParserForLocale(ULocale loc) {
        return createParserFromProperties(PatternStringParser.parseToProperties(AndroidHardcodedSystemProperties.JAVA_VERSION), DecimalFormatSymbols.getInstance(loc), false);
    }

    public static NumberParserImpl createParserFromProperties(DecimalFormatProperties properties, DecimalFormatSymbols symbols, boolean parseCurrency) {
        AffixPatternProvider affixProvider;
        int parseFlags2;
        ULocale locale = symbols.getULocale();
        if (properties.getCurrencyPluralInfo() == null) {
            affixProvider = new PropertiesAffixPatternProvider(properties);
        } else {
            affixProvider = new CurrencyPluralInfoAffixProvider(properties.getCurrencyPluralInfo(), properties);
        }
        Currency currency = CustomSymbolCurrency.resolve(properties.getCurrency(), locale, symbols);
        boolean patternHasDecimalSeparator = true;
        boolean isStrict = properties.getParseMode() == DecimalFormatProperties.ParseMode.STRICT;
        Grouper grouper = Grouper.forProperties(properties);
        int parseFlags3 = 0;
        if (!properties.getParseCaseSensitive()) {
            parseFlags3 = 0 | 1;
        }
        if (properties.getParseIntegerOnly()) {
            parseFlags3 |= 16;
        }
        if (properties.getParseToBigDecimal()) {
            parseFlags3 |= 4096;
        }
        if (properties.getSignAlwaysShown()) {
            parseFlags3 |= 1024;
        }
        if (isStrict) {
            parseFlags2 = parseFlags3 | 8 | 4 | 256 | 512;
        } else {
            parseFlags2 = parseFlags3 | 128;
        }
        if (grouper.getPrimary() <= 0) {
            parseFlags2 |= 32;
        }
        if (parseCurrency || affixProvider.hasCurrencySign()) {
            parseFlags2 |= 2;
        }
        if (!parseCurrency) {
            parseFlags2 |= 8192;
        }
        IgnorablesMatcher ignorables = isStrict ? IgnorablesMatcher.STRICT : IgnorablesMatcher.DEFAULT;
        NumberParserImpl parser = new NumberParserImpl(parseFlags2);
        AffixTokenMatcherFactory factory = new AffixTokenMatcherFactory();
        factory.currency = currency;
        factory.symbols = symbols;
        factory.ignorables = ignorables;
        factory.locale = locale;
        factory.parseFlags = parseFlags2;
        AffixMatcher.createMatchers(affixProvider, parser, factory, ignorables, parseFlags2);
        if (parseCurrency || affixProvider.hasCurrencySign()) {
            parser.addMatcher(CombinedCurrencyMatcher.getInstance(currency, symbols, parseFlags2));
        }
        if (!isStrict && affixProvider.containsSymbolType(-3)) {
            parser.addMatcher(PercentMatcher.getInstance(symbols));
        }
        if (!isStrict && affixProvider.containsSymbolType(-4)) {
            parser.addMatcher(PermilleMatcher.getInstance(symbols));
        }
        if (!isStrict) {
            parser.addMatcher(PlusSignMatcher.getInstance(symbols, false));
            parser.addMatcher(MinusSignMatcher.getInstance(symbols, false));
        }
        parser.addMatcher(NanMatcher.getInstance(symbols, parseFlags2));
        parser.addMatcher(InfinityMatcher.getInstance(symbols));
        String padString = properties.getPadString();
        if (padString != null && !ignorables.getSet().contains(padString)) {
            parser.addMatcher(PaddingMatcher.getInstance(padString));
        }
        parser.addMatcher(ignorables);
        parser.addMatcher(DecimalMatcher.getInstance(symbols, grouper, parseFlags2));
        if (!properties.getParseNoExponent() || properties.getMinimumExponentDigits() > 0) {
            parser.addMatcher(ScientificMatcher.getInstance(symbols, grouper));
        }
        parser.addMatcher(new RequireNumberValidator());
        if (isStrict) {
            parser.addMatcher(new RequireAffixValidator());
        }
        if (parseCurrency) {
            parser.addMatcher(new RequireCurrencyValidator());
        }
        if (properties.getDecimalPatternMatchRequired()) {
            if (!properties.getDecimalSeparatorAlwaysShown() && properties.getMaximumFractionDigits() == 0) {
                patternHasDecimalSeparator = false;
            }
            parser.addMatcher(RequireDecimalSeparatorValidator.getInstance(patternHasDecimalSeparator));
        }
        Scale multiplier = RoundingUtils.scaleFromProperties(properties);
        if (multiplier != null) {
            parser.addMatcher(new MultiplierParseHandler(multiplier));
        }
        parser.freeze();
        return parser;
    }

    public NumberParserImpl(int parseFlags2) {
        this.parseFlags = parseFlags2;
        this.frozen = false;
    }

    public void addMatcher(NumberParseMatcher matcher) {
        this.matchers.add(matcher);
    }

    public void addMatchers(Collection<? extends NumberParseMatcher> matchers2) {
        this.matchers.addAll(matchers2);
    }

    public void freeze() {
        this.frozen = true;
    }

    public int getParseFlags() {
        return this.parseFlags;
    }

    public void parse(String input, boolean greedy, ParsedNumber result) {
        parse(input, 0, greedy, result);
    }

    public void parse(String input, int start, boolean greedy, ParsedNumber result) {
        boolean z = true;
        if ((this.parseFlags & 1) == 0) {
            z = false;
        }
        StringSegment segment = new StringSegment(input, z);
        segment.adjustOffset(start);
        if (greedy) {
            parseGreedyRecursive(segment, result);
        } else {
            parseLongestRecursive(segment, result);
        }
        for (NumberParseMatcher matcher : this.matchers) {
            matcher.postProcess(result);
        }
        result.postProcess();
    }

    private void parseGreedyRecursive(StringSegment segment, ParsedNumber result) {
        if (segment.length() != 0) {
            int initialOffset = segment.getOffset();
            for (int i = 0; i < this.matchers.size(); i++) {
                NumberParseMatcher matcher = this.matchers.get(i);
                if (matcher.smokeTest(segment)) {
                    matcher.match(segment, result);
                    if (segment.getOffset() != initialOffset) {
                        parseGreedyRecursive(segment, result);
                        segment.setOffset(initialOffset);
                        return;
                    }
                }
            }
        }
    }

    private void parseLongestRecursive(StringSegment segment, ParsedNumber result) {
        if (segment.length() != 0) {
            ParsedNumber initial = new ParsedNumber();
            initial.copyFrom(result);
            ParsedNumber candidate = new ParsedNumber();
            int initialOffset = segment.getOffset();
            for (int i = 0; i < this.matchers.size(); i++) {
                NumberParseMatcher matcher = this.matchers.get(i);
                if (matcher.smokeTest(segment)) {
                    int charsToConsume = 0;
                    while (charsToConsume < segment.length()) {
                        charsToConsume += Character.charCount(segment.codePointAt(charsToConsume));
                        candidate.copyFrom(initial);
                        segment.setLength(charsToConsume);
                        boolean maybeMore = matcher.match(segment, candidate);
                        segment.resetLength();
                        if (segment.getOffset() - initialOffset == charsToConsume) {
                            parseLongestRecursive(segment, candidate);
                            if (candidate.isBetterThan(result)) {
                                result.copyFrom(candidate);
                            }
                        }
                        segment.setOffset(initialOffset);
                        if (!maybeMore) {
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
