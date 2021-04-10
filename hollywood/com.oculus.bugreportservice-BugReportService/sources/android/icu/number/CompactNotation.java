package android.icu.number;

import android.icu.impl.number.CompactData;
import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.MicroPropsGenerator;
import android.icu.impl.number.MutablePatternModifier;
import android.icu.impl.number.PatternStringParser;
import android.icu.text.CompactDecimalFormat$CompactStyle;
import android.icu.text.PluralRules;
import android.icu.util.ULocale;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CompactNotation extends Notation {
    final Map compactCustomData;
    final CompactDecimalFormat$CompactStyle compactStyle;

    CompactNotation(CompactDecimalFormat$CompactStyle compactDecimalFormat$CompactStyle) {
        this.compactCustomData = null;
        this.compactStyle = compactDecimalFormat$CompactStyle;
    }

    CompactNotation(Map map) {
        this.compactStyle = null;
        this.compactCustomData = map;
    }

    /* access modifiers changed from: package-private */
    public MicroPropsGenerator withLocaleData(ULocale uLocale, String str, CompactData.CompactType compactType, PluralRules pluralRules, MutablePatternModifier mutablePatternModifier, MicroPropsGenerator microPropsGenerator) {
        return new CompactHandler(uLocale, str, compactType, pluralRules, mutablePatternModifier, microPropsGenerator);
    }

    private static class CompactHandler implements MicroPropsGenerator {
        final CompactData data;
        final MicroPropsGenerator parent;
        final Map precomputedMods;
        final PluralRules rules;

        private CompactHandler(CompactNotation compactNotation, ULocale uLocale, String str, CompactData.CompactType compactType, PluralRules pluralRules, MutablePatternModifier mutablePatternModifier, MicroPropsGenerator microPropsGenerator) {
            this.rules = pluralRules;
            this.parent = microPropsGenerator;
            this.data = new CompactData();
            CompactDecimalFormat$CompactStyle compactDecimalFormat$CompactStyle = compactNotation.compactStyle;
            if (compactDecimalFormat$CompactStyle != null) {
                this.data.populate(uLocale, str, compactDecimalFormat$CompactStyle, compactType);
            } else {
                this.data.populate(compactNotation.compactCustomData);
            }
            if (mutablePatternModifier != null) {
                this.precomputedMods = new HashMap();
                precomputeAllModifiers(mutablePatternModifier);
                return;
            }
            this.precomputedMods = null;
        }

        private void precomputeAllModifiers(MutablePatternModifier mutablePatternModifier) {
            HashSet<String> hashSet = new HashSet();
            this.data.getUniquePatterns(hashSet);
            for (String str : hashSet) {
                mutablePatternModifier.setPatternInfo(PatternStringParser.parseToPatternInfo(str));
                this.precomputedMods.put(str, mutablePatternModifier.createImmutable());
            }
        }

        @Override // android.icu.impl.number.MicroPropsGenerator
        public MicroProps processQuantity(DecimalQuantity decimalQuantity) {
            MicroProps processQuantity = this.parent.processQuantity(decimalQuantity);
            int i = 0;
            if (decimalQuantity.isZero()) {
                processQuantity.rounder.apply(decimalQuantity);
            } else {
                int chooseMultiplierAndApply = processQuantity.rounder.chooseMultiplierAndApply(decimalQuantity, this.data);
                if (!decimalQuantity.isZero()) {
                    i = decimalQuantity.getMagnitude();
                }
                i -= chooseMultiplierAndApply;
            }
            String pattern = this.data.getPattern(i, decimalQuantity.getStandardPlural(this.rules));
            if (pattern != null) {
                Map map = this.precomputedMods;
                if (map != null) {
                    ((MutablePatternModifier.ImmutablePatternModifier) map.get(pattern)).applyToMicros(processQuantity, decimalQuantity);
                } else {
                    ((MutablePatternModifier) processQuantity.modMiddle).setPatternInfo(PatternStringParser.parseToPatternInfo(pattern));
                }
            }
            processQuantity.rounder = Precision.constructPassThrough();
            return processQuantity;
        }
    }
}
