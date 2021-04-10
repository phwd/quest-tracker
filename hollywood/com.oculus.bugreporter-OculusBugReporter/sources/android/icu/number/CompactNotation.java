package android.icu.number;

import android.icu.impl.number.CompactData;
import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.MicroPropsGenerator;
import android.icu.impl.number.MutablePatternModifier;
import android.icu.impl.number.PatternStringParser;
import android.icu.text.CompactDecimalFormat;
import android.icu.text.PluralRules;
import android.icu.util.ULocale;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompactNotation extends Notation {
    final Map<String, Map<String, String>> compactCustomData;
    final CompactDecimalFormat.CompactStyle compactStyle;

    @Deprecated
    public static CompactNotation forCustomData(Map<String, Map<String, String>> compactCustomData2) {
        return new CompactNotation(compactCustomData2);
    }

    CompactNotation(CompactDecimalFormat.CompactStyle compactStyle2) {
        this.compactCustomData = null;
        this.compactStyle = compactStyle2;
    }

    CompactNotation(Map<String, Map<String, String>> compactCustomData2) {
        this.compactStyle = null;
        this.compactCustomData = compactCustomData2;
    }

    /* access modifiers changed from: package-private */
    public MicroPropsGenerator withLocaleData(ULocale locale, String nsName, CompactData.CompactType compactType, PluralRules rules, MutablePatternModifier buildReference, MicroPropsGenerator parent) {
        return new CompactHandler(locale, nsName, compactType, rules, buildReference, parent);
    }

    private static class CompactHandler implements MicroPropsGenerator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final CompactData data;
        final MicroPropsGenerator parent;
        final Map<String, MutablePatternModifier.ImmutablePatternModifier> precomputedMods;
        final PluralRules rules;

        private CompactHandler(CompactNotation notation, ULocale locale, String nsName, CompactData.CompactType compactType, PluralRules rules2, MutablePatternModifier buildReference, MicroPropsGenerator parent2) {
            this.rules = rules2;
            this.parent = parent2;
            this.data = new CompactData();
            if (notation.compactStyle != null) {
                this.data.populate(locale, nsName, notation.compactStyle, compactType);
            } else {
                this.data.populate(notation.compactCustomData);
            }
            if (buildReference != null) {
                this.precomputedMods = new HashMap();
                precomputeAllModifiers(buildReference);
                return;
            }
            this.precomputedMods = null;
        }

        private void precomputeAllModifiers(MutablePatternModifier buildReference) {
            Set<String> allPatterns = new HashSet<>();
            this.data.getUniquePatterns(allPatterns);
            for (String patternString : allPatterns) {
                buildReference.setPatternInfo(PatternStringParser.parseToPatternInfo(patternString));
                this.precomputedMods.put(patternString, buildReference.createImmutable());
            }
        }

        @Override // android.icu.impl.number.MicroPropsGenerator
        public MicroProps processQuantity(DecimalQuantity quantity) {
            int multiplier;
            MicroProps micros = this.parent.processQuantity(quantity);
            if (quantity.isZero()) {
                multiplier = 0;
                micros.rounder.apply(quantity);
            } else {
                multiplier = (quantity.isZero() ? 0 : quantity.getMagnitude()) - micros.rounder.chooseMultiplierAndApply(quantity, this.data);
            }
            String patternString = this.data.getPattern(multiplier, quantity.getStandardPlural(this.rules));
            if (patternString != null) {
                Map<String, MutablePatternModifier.ImmutablePatternModifier> map = this.precomputedMods;
                if (map != null) {
                    map.get(patternString).applyToMicros(micros, quantity);
                } else {
                    ((MutablePatternModifier) micros.modMiddle).setPatternInfo(PatternStringParser.parseToPatternInfo(patternString));
                }
            }
            micros.rounder = Precision.constructPassThrough();
            return micros;
        }
    }
}
