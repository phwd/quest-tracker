package android.icu.impl.number;

import android.icu.impl.StandardPlural;
import android.icu.impl.locale.XLocaleDistance;
import android.icu.impl.number.AffixUtils;
import android.icu.impl.number.Modifier;
import android.icu.number.NumberFormatter;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.NumberFormat;
import android.icu.text.PluralRules;
import android.icu.util.Currency;

public class MutablePatternModifier implements Modifier, AffixUtils.SymbolProvider, MicroPropsGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
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

    public MutablePatternModifier(boolean isStrong2) {
        this.isStrong = isStrong2;
    }

    public void setPatternInfo(AffixPatternProvider patternInfo2) {
        this.patternInfo = patternInfo2;
    }

    public void setPatternAttributes(NumberFormatter.SignDisplay signDisplay2, boolean perMille) {
        this.signDisplay = signDisplay2;
        this.perMilleReplacesPercent = perMille;
    }

    public void setSymbols(DecimalFormatSymbols symbols2, Currency currency2, NumberFormatter.UnitWidth unitWidth2, PluralRules rules2) {
        this.symbols = symbols2;
        this.currency = currency2;
        this.unitWidth = unitWidth2;
        this.rules = rules2;
    }

    public void setNumberProperties(int signum2, StandardPlural plural2) {
        this.signum = signum2;
        this.plural = plural2;
    }

    public boolean needsPlurals() {
        return this.patternInfo.containsSymbolType(-7);
    }

    public ImmutablePatternModifier createImmutable() {
        return createImmutableAndChain(null);
    }

    public ImmutablePatternModifier createImmutableAndChain(MicroPropsGenerator parent2) {
        NumberStringBuilder a = new NumberStringBuilder();
        NumberStringBuilder b = new NumberStringBuilder();
        if (needsPlurals()) {
            AdoptingModifierStore pm = new AdoptingModifierStore();
            for (StandardPlural plural2 : StandardPlural.VALUES) {
                setNumberProperties(1, plural2);
                pm.setModifier(1, plural2, createConstantModifier(a, b));
                setNumberProperties(0, plural2);
                pm.setModifier(0, plural2, createConstantModifier(a, b));
                setNumberProperties(-1, plural2);
                pm.setModifier(-1, plural2, createConstantModifier(a, b));
            }
            pm.freeze();
            return new ImmutablePatternModifier(pm, this.rules, parent2);
        }
        setNumberProperties(1, null);
        Modifier positive = createConstantModifier(a, b);
        setNumberProperties(0, null);
        Modifier zero = createConstantModifier(a, b);
        setNumberProperties(-1, null);
        return new ImmutablePatternModifier(new AdoptingModifierStore(positive, zero, createConstantModifier(a, b)), null, parent2);
    }

    private ConstantMultiFieldModifier createConstantModifier(NumberStringBuilder a, NumberStringBuilder b) {
        insertPrefix(a.clear(), 0);
        insertSuffix(b.clear(), 0);
        if (this.patternInfo.hasCurrencySign()) {
            return new CurrencySpacingEnabledModifier(a, b, !this.patternInfo.hasBody(), this.isStrong, this.symbols);
        }
        return new ConstantMultiFieldModifier(a, b, !this.patternInfo.hasBody(), this.isStrong);
    }

    public static class ImmutablePatternModifier implements MicroPropsGenerator {
        final MicroPropsGenerator parent;
        final AdoptingModifierStore pm;
        final PluralRules rules;

        ImmutablePatternModifier(AdoptingModifierStore pm2, PluralRules rules2, MicroPropsGenerator parent2) {
            this.pm = pm2;
            this.rules = rules2;
            this.parent = parent2;
        }

        @Override // android.icu.impl.number.MicroPropsGenerator
        public MicroProps processQuantity(DecimalQuantity quantity) {
            MicroProps micros = this.parent.processQuantity(quantity);
            applyToMicros(micros, quantity);
            return micros;
        }

        public void applyToMicros(MicroProps micros, DecimalQuantity quantity) {
            if (this.rules == null) {
                micros.modMiddle = this.pm.getModifierWithoutPlural(quantity.signum());
                return;
            }
            DecimalQuantity copy = quantity.createCopy();
            copy.roundToInfinity();
            micros.modMiddle = this.pm.getModifier(quantity.signum(), copy.getStandardPlural(this.rules));
        }
    }

    public MicroPropsGenerator addToChain(MicroPropsGenerator parent2) {
        this.parent = parent2;
        return this;
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity fq) {
        MicroProps micros = this.parent.processQuantity(fq);
        if (needsPlurals()) {
            DecimalQuantity copy = fq.createCopy();
            micros.rounder.apply(copy);
            setNumberProperties(fq.signum(), copy.getStandardPlural(this.rules));
        } else {
            setNumberProperties(fq.signum(), null);
        }
        micros.modMiddle = this;
        return micros;
    }

    @Override // android.icu.impl.number.Modifier
    public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
        int overwriteLen;
        int prefixLen = insertPrefix(output, leftIndex);
        int suffixLen = insertSuffix(output, rightIndex + prefixLen);
        if (!this.patternInfo.hasBody()) {
            overwriteLen = output.splice(leftIndex + prefixLen, rightIndex + prefixLen, "", 0, 0, null);
        } else {
            overwriteLen = 0;
        }
        CurrencySpacingEnabledModifier.applyCurrencySpacing(output, leftIndex, prefixLen, rightIndex + prefixLen + overwriteLen, suffixLen, this.symbols);
        return prefixLen + overwriteLen + suffixLen;
    }

    @Override // android.icu.impl.number.Modifier
    public int getPrefixLength() {
        prepareAffix(true);
        return AffixUtils.unescapedCount(this.currentAffix, true, this);
    }

    @Override // android.icu.impl.number.Modifier
    public int getCodePointCount() {
        prepareAffix(true);
        int result = AffixUtils.unescapedCount(this.currentAffix, false, this);
        prepareAffix(false);
        return result + AffixUtils.unescapedCount(this.currentAffix, false, this);
    }

    @Override // android.icu.impl.number.Modifier
    public boolean isStrong() {
        return this.isStrong;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean containsField(NumberFormat.Field field) {
        return false;
    }

    @Override // android.icu.impl.number.Modifier
    public Modifier.Parameters getParameters() {
        return null;
    }

    @Override // android.icu.impl.number.Modifier
    public boolean semanticallyEquivalent(Modifier other) {
        return false;
    }

    private int insertPrefix(NumberStringBuilder sb, int position) {
        prepareAffix(true);
        return AffixUtils.unescape(this.currentAffix, sb, position, this);
    }

    private int insertSuffix(NumberStringBuilder sb, int position) {
        prepareAffix(false);
        return AffixUtils.unescape(this.currentAffix, sb, position, this);
    }

    private void prepareAffix(boolean isPrefix) {
        if (this.currentAffix == null) {
            this.currentAffix = new StringBuilder();
        }
        PatternStringUtils.patternInfoToStringBuilder(this.patternInfo, isPrefix, this.signum, this.signDisplay, this.plural, this.perMilleReplacesPercent, this.currentAffix);
    }

    @Override // android.icu.impl.number.AffixUtils.SymbolProvider
    public CharSequence getSymbol(int type) {
        int selector = 3;
        switch (type) {
            case -9:
                return this.currency.getName(this.symbols.getULocale(), 3, (boolean[]) null);
            case -8:
                return XLocaleDistance.ANY;
            case -7:
                return this.currency.getName(this.symbols.getULocale(), 2, this.plural.getKeyword(), (boolean[]) null);
            case -6:
                return this.currency.getCurrencyCode();
            case -5:
                if (this.unitWidth == NumberFormatter.UnitWidth.ISO_CODE) {
                    return this.currency.getCurrencyCode();
                }
                if (this.unitWidth == NumberFormatter.UnitWidth.HIDDEN) {
                    return "";
                }
                if (this.unitWidth != NumberFormatter.UnitWidth.NARROW) {
                    selector = 0;
                }
                return this.currency.getName(this.symbols.getULocale(), selector, (boolean[]) null);
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
