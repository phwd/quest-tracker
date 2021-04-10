package android.icu.impl.number.parse;

import android.icu.impl.number.AffixUtils;

public class AffixPatternMatcher extends SeriesMatcher implements AffixUtils.TokenConsumer {
    private final String affixPattern;
    private AffixTokenMatcherFactory factory;
    private IgnorablesMatcher ignorables;
    private int lastTypeOrCp;

    private AffixPatternMatcher(String affixPattern2) {
        this.affixPattern = affixPattern2;
    }

    public static AffixPatternMatcher fromAffixPattern(String affixPattern2, AffixTokenMatcherFactory factory2, int parseFlags) {
        IgnorablesMatcher ignorablesMatcher;
        if (affixPattern2.isEmpty()) {
            return null;
        }
        AffixPatternMatcher series = new AffixPatternMatcher(affixPattern2);
        series.factory = factory2;
        if ((parseFlags & 512) != 0) {
            ignorablesMatcher = null;
        } else {
            ignorablesMatcher = factory2.ignorables();
        }
        series.ignorables = ignorablesMatcher;
        series.lastTypeOrCp = 0;
        AffixUtils.iterateWithConsumer(affixPattern2, series);
        series.factory = null;
        series.ignorables = null;
        series.lastTypeOrCp = 0;
        series.freeze();
        return series;
    }

    @Override // android.icu.impl.number.AffixUtils.TokenConsumer
    public void consumeToken(int typeOrCp) {
        if (this.ignorables != null && length() > 0 && (this.lastTypeOrCp < 0 || !this.ignorables.getSet().contains(this.lastTypeOrCp))) {
            addMatcher(this.ignorables);
        }
        if (typeOrCp < 0) {
            switch (typeOrCp) {
                case -9:
                case -8:
                case -7:
                case -6:
                case -5:
                    addMatcher(this.factory.currency());
                    break;
                case -4:
                    addMatcher(this.factory.permille());
                    break;
                case -3:
                    addMatcher(this.factory.percent());
                    break;
                case -2:
                    addMatcher(this.factory.plusSign());
                    break;
                case -1:
                    addMatcher(this.factory.minusSign());
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            IgnorablesMatcher ignorablesMatcher = this.ignorables;
            if (ignorablesMatcher == null || !ignorablesMatcher.getSet().contains(typeOrCp)) {
                addMatcher(CodePointMatcher.getInstance(typeOrCp));
            }
        }
        this.lastTypeOrCp = typeOrCp;
    }

    public String getPattern() {
        return this.affixPattern;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AffixPatternMatcher)) {
            return false;
        }
        return this.affixPattern.equals(((AffixPatternMatcher) other).affixPattern);
    }

    public int hashCode() {
        return this.affixPattern.hashCode();
    }

    @Override // android.icu.impl.number.parse.SeriesMatcher
    public String toString() {
        return this.affixPattern;
    }
}
