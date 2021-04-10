package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.impl.number.AffixUtils;
import android.icu.number.NumberFormatter;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.PluralRules;
import android.icu.util.Currency;

public class MutablePatternModifier implements Modifier, AffixUtils.SymbolProvider, MicroPropsGenerator {
    Currency currency;
    StringBuilder currentAffix;
    final boolean isStrong;
    MicroPropsGenerator parent;
    AffixPatternProvider patternInfo;
    boolean perMilleReplacesPercent;
    StandardPlural plural;
    PluralRules rules;
    NumberFormatter.SignDisplay signDisplay;
    int signum;
    DecimalFormatSymbols symbols;
    NumberFormatter.UnitWidth unitWidth;

    public MutablePatternModifier(boolean z) {
        this.isStrong = z;
    }

    public void setPatternInfo(AffixPatternProvider affixPatternProvider) {
        this.patternInfo = affixPatternProvider;
    }

    public void setPatternAttributes(NumberFormatter.SignDisplay signDisplay2, boolean z) {
        this.signDisplay = signDisplay2;
        this.perMilleReplacesPercent = z;
    }

    public void setSymbols(DecimalFormatSymbols decimalFormatSymbols, Currency currency2, NumberFormatter.UnitWidth unitWidth2, PluralRules pluralRules) {
        this.symbols = decimalFormatSymbols;
        this.currency = currency2;
        this.unitWidth = unitWidth2;
        this.rules = pluralRules;
    }

    public void setNumberProperties(int i, StandardPlural standardPlural) {
        this.signum = i;
        this.plural = standardPlural;
    }

    public boolean needsPlurals() {
        return this.patternInfo.containsSymbolType(-7);
    }

    public ImmutablePatternModifier createImmutable() {
        return createImmutableAndChain(null);
    }

    public ImmutablePatternModifier createImmutableAndChain(MicroPropsGenerator microPropsGenerator) {
        NumberStringBuilder numberStringBuilder = new NumberStringBuilder();
        NumberStringBuilder numberStringBuilder2 = new NumberStringBuilder();
        if (needsPlurals()) {
            AdoptingModifierStore adoptingModifierStore = new AdoptingModifierStore();
            for (StandardPlural standardPlural : StandardPlural.VALUES) {
                setNumberProperties(1, standardPlural);
                adoptingModifierStore.setModifier(1, standardPlural, createConstantModifier(numberStringBuilder, numberStringBuilder2));
                setNumberProperties(0, standardPlural);
                adoptingModifierStore.setModifier(0, standardPlural, createConstantModifier(numberStringBuilder, numberStringBuilder2));
                setNumberProperties(-1, standardPlural);
                adoptingModifierStore.setModifier(-1, standardPlural, createConstantModifier(numberStringBuilder, numberStringBuilder2));
            }
            adoptingModifierStore.freeze();
            return new ImmutablePatternModifier(adoptingModifierStore, this.rules, microPropsGenerator);
        }
        setNumberProperties(1, null);
        ConstantMultiFieldModifier createConstantModifier = createConstantModifier(numberStringBuilder, numberStringBuilder2);
        setNumberProperties(0, null);
        ConstantMultiFieldModifier createConstantModifier2 = createConstantModifier(numberStringBuilder, numberStringBuilder2);
        setNumberProperties(-1, null);
        return new ImmutablePatternModifier(new AdoptingModifierStore(createConstantModifier, createConstantModifier2, createConstantModifier(numberStringBuilder, numberStringBuilder2)), null, microPropsGenerator);
    }

    private ConstantMultiFieldModifier createConstantModifier(NumberStringBuilder numberStringBuilder, NumberStringBuilder numberStringBuilder2) {
        numberStringBuilder.clear();
        insertPrefix(numberStringBuilder, 0);
        numberStringBuilder2.clear();
        insertSuffix(numberStringBuilder2, 0);
        if (this.patternInfo.hasCurrencySign()) {
            return new CurrencySpacingEnabledModifier(numberStringBuilder, numberStringBuilder2, !this.patternInfo.hasBody(), this.isStrong, this.symbols);
        }
        return new ConstantMultiFieldModifier(numberStringBuilder, numberStringBuilder2, !this.patternInfo.hasBody(), this.isStrong);
    }

    public static class ImmutablePatternModifier implements MicroPropsGenerator {
        final MicroPropsGenerator parent;
        final AdoptingModifierStore pm;
        final PluralRules rules;

        ImmutablePatternModifier(AdoptingModifierStore adoptingModifierStore, PluralRules pluralRules, MicroPropsGenerator microPropsGenerator) {
            this.pm = adoptingModifierStore;
            this.rules = pluralRules;
            this.parent = microPropsGenerator;
        }

        @Override // android.icu.impl.number.MicroPropsGenerator
        public MicroProps processQuantity(DecimalQuantity decimalQuantity) {
            MicroProps processQuantity = this.parent.processQuantity(decimalQuantity);
            applyToMicros(processQuantity, decimalQuantity);
            return processQuantity;
        }

        public void applyToMicros(MicroProps microProps, DecimalQuantity decimalQuantity) {
            if (this.rules == null) {
                microProps.modMiddle = this.pm.getModifierWithoutPlural(decimalQuantity.signum());
                return;
            }
            DecimalQuantity createCopy = decimalQuantity.createCopy();
            createCopy.roundToInfinity();
            microProps.modMiddle = this.pm.getModifier(decimalQuantity.signum(), createCopy.getStandardPlural(this.rules));
        }
    }

    public MicroPropsGenerator addToChain(MicroPropsGenerator microPropsGenerator) {
        this.parent = microPropsGenerator;
        return this;
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity decimalQuantity) {
        MicroProps processQuantity = this.parent.processQuantity(decimalQuantity);
        if (needsPlurals()) {
            DecimalQuantity createCopy = decimalQuantity.createCopy();
            processQuantity.rounder.apply(createCopy);
            setNumberProperties(decimalQuantity.signum(), createCopy.getStandardPlural(this.rules));
        } else {
            setNumberProperties(decimalQuantity.signum(), null);
        }
        processQuantity.modMiddle = this;
        return processQuantity;
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
        int insertPrefix = insertPrefix(numberStringBuilder, i);
        int i3 = i2 + insertPrefix;
        int insertSuffix = insertSuffix(numberStringBuilder, i3);
        int splice = !this.patternInfo.hasBody() ? numberStringBuilder.splice(i + insertPrefix, i3, "", 0, 0, null) : 0;
        CurrencySpacingEnabledModifier.applyCurrencySpacing(numberStringBuilder, i, insertPrefix, i3 + splice, insertSuffix, this.symbols);
        return insertPrefix + splice + insertSuffix;
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        prepareAffix(true);
        int unescapedCount = AffixUtils.unescapedCount(this.currentAffix, false, this);
        prepareAffix(false);
        return unescapedCount + AffixUtils.unescapedCount(this.currentAffix, false, this);
    }

    private int insertPrefix(NumberStringBuilder numberStringBuilder, int i) {
        prepareAffix(true);
        return AffixUtils.unescape(this.currentAffix, numberStringBuilder, i, this);
    }

    private int insertSuffix(NumberStringBuilder numberStringBuilder, int i) {
        prepareAffix(false);
        return AffixUtils.unescape(this.currentAffix, numberStringBuilder, i, this);
    }

    private void prepareAffix(boolean z) {
        if (this.currentAffix == null) {
            this.currentAffix = new StringBuilder();
        }
        PatternStringUtils.patternInfoToStringBuilder(this.patternInfo, z, this.signum, this.signDisplay, this.plural, this.perMilleReplacesPercent, this.currentAffix);
    }

    @Override // android.icu.impl.number.AffixUtils.SymbolProvider
    public CharSequence getSymbol(int i) {
        int i2 = 3;
        switch (i) {
            case -9:
                return this.currency.getName(this.symbols.getULocale(), 3, null);
            case -8:
                return "�";
            case -7:
                return this.currency.getName(this.symbols.getULocale(), 2, this.plural.getKeyword(), (boolean[]) null);
            case -6:
                return this.currency.getCurrencyCode();
            case -5:
                NumberFormatter.UnitWidth unitWidth2 = this.unitWidth;
                if (unitWidth2 == NumberFormatter.UnitWidth.ISO_CODE) {
                    return this.currency.getCurrencyCode();
                }
                if (unitWidth2 == NumberFormatter.UnitWidth.HIDDEN) {
                    return "";
                }
                if (unitWidth2 != NumberFormatter.UnitWidth.NARROW) {
                    i2 = 0;
                }
                return this.currency.getName(this.symbols.getULocale(), i2, null);
            case -4:
                return this.symbols.getPerMillString();
            case -3:
                return this.symbols.getPercentString();
            case -2:
                return this.symbols.getPlusSignString();
            case -1:
                return this.symbols.getMinusSignString();
            default:
                throw new AssertionError();
        }
    }
}
