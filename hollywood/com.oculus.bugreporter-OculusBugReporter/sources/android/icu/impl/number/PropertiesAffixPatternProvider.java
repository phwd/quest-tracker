package android.icu.impl.number;

public class PropertiesAffixPatternProvider implements AffixPatternProvider {
    private final String negPrefix;
    private final String negSuffix;
    private final String posPrefix;
    private final String posSuffix;

    public PropertiesAffixPatternProvider(DecimalFormatProperties properties) {
        String ppo = AffixUtils.escape(properties.getPositivePrefix());
        String pso = AffixUtils.escape(properties.getPositiveSuffix());
        String npo = AffixUtils.escape(properties.getNegativePrefix());
        String nso = AffixUtils.escape(properties.getNegativeSuffix());
        String ppp = properties.getPositivePrefixPattern();
        String psp = properties.getPositiveSuffixPattern();
        String npp = properties.getNegativePrefixPattern();
        String nsp = properties.getNegativeSuffixPattern();
        String str = "";
        if (ppo != null) {
            this.posPrefix = ppo;
        } else if (ppp != null) {
            this.posPrefix = ppp;
        } else {
            this.posPrefix = str;
        }
        if (pso != null) {
            this.posSuffix = pso;
        } else if (psp != null) {
            this.posSuffix = psp;
        } else {
            this.posSuffix = str;
        }
        if (npo != null) {
            this.negPrefix = npo;
        } else if (npp != null) {
            this.negPrefix = npp;
        } else {
            String str2 = "-";
            if (ppp != null) {
                str2 = str2 + ppp;
            }
            this.negPrefix = str2;
        }
        if (nso != null) {
            this.negSuffix = nso;
        } else if (nsp != null) {
            this.negSuffix = nsp;
        } else {
            this.negSuffix = psp != null ? psp : str;
        }
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public char charAt(int flags, int i) {
        return getString(flags).charAt(i);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public int length(int flags) {
        return getString(flags).length();
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public String getString(int flags) {
        boolean negative = true;
        boolean prefix = (flags & 256) != 0;
        if ((flags & 512) == 0) {
            negative = false;
        }
        if (prefix && negative) {
            return this.negPrefix;
        }
        if (prefix) {
            return this.posPrefix;
        }
        if (negative) {
            return this.negSuffix;
        }
        return this.posSuffix;
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean positiveHasPlusSign() {
        return AffixUtils.containsType(this.posPrefix, -2) || AffixUtils.containsType(this.posSuffix, -2);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasNegativeSubpattern() {
        return true;
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean negativeHasMinusSign() {
        return AffixUtils.containsType(this.negPrefix, -1) || AffixUtils.containsType(this.negSuffix, -1);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasCurrencySign() {
        return AffixUtils.hasCurrencySymbols(this.posPrefix) || AffixUtils.hasCurrencySymbols(this.posSuffix) || AffixUtils.hasCurrencySymbols(this.negPrefix) || AffixUtils.hasCurrencySymbols(this.negSuffix);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean containsSymbolType(int type) {
        return AffixUtils.containsType(this.posPrefix, type) || AffixUtils.containsType(this.posSuffix, type) || AffixUtils.containsType(this.negPrefix, type) || AffixUtils.containsType(this.negSuffix, type);
    }

    @Override // android.icu.impl.number.AffixPatternProvider
    public boolean hasBody() {
        return true;
    }

    public String toString() {
        return super.toString() + " {" + this.posPrefix + "#" + this.posSuffix + ";" + this.negPrefix + "#" + this.negSuffix + "}";
    }
}
