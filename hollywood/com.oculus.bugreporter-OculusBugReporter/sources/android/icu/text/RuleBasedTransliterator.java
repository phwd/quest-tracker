package android.icu.text;

import android.icu.text.Transliterator;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class RuleBasedTransliterator extends Transliterator {
    private final Data data;

    RuleBasedTransliterator(String ID, Data data2, UnicodeFilter filter) {
        super(ID, filter);
        this.data = data2;
        setMaximumContextLength(data2.ruleSet.getMaximumContextLength());
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    @Deprecated
    public void handleTransliterate(Replaceable text, Transliterator.Position index, boolean incremental) {
        synchronized (this.data) {
            int loopCount = 0;
            int loopLimit = (index.limit - index.start) << 4;
            if (loopLimit < 0) {
                loopLimit = Integer.MAX_VALUE;
            }
            while (index.start < index.limit && loopCount <= loopLimit && this.data.ruleSet.transliterate(text, index, incremental)) {
                loopCount++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class Data {
        public TransliterationRuleSet ruleSet = new TransliterationRuleSet();
        Map<String, char[]> variableNames = new HashMap();
        Object[] variables;
        char variablesBase;

        public UnicodeMatcher lookupMatcher(int standIn) {
            int i = standIn - this.variablesBase;
            if (i >= 0) {
                Object[] objArr = this.variables;
                if (i < objArr.length) {
                    return (UnicodeMatcher) objArr[i];
                }
            }
            return null;
        }

        public UnicodeReplacer lookupReplacer(int standIn) {
            int i = standIn - this.variablesBase;
            if (i >= 0) {
                Object[] objArr = this.variables;
                if (i < objArr.length) {
                    return (UnicodeReplacer) objArr[i];
                }
            }
            return null;
        }
    }

    @Override // android.icu.text.Transliterator
    @Deprecated
    public String toRules(boolean escapeUnprintable) {
        return this.data.ruleSet.toRules(escapeUnprintable);
    }

    @Override // android.icu.text.Transliterator
    @Deprecated
    public void addSourceTargetSet(UnicodeSet filter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        this.data.ruleSet.addSourceTargetSet(filter, sourceSet, targetSet);
    }

    @Deprecated
    public Transliterator safeClone() {
        UnicodeFilter filter = getFilter();
        if (filter != null && (filter instanceof UnicodeSet)) {
            filter = new UnicodeSet((UnicodeSet) filter);
        }
        return new RuleBasedTransliterator(getID(), this.data, filter);
    }
}
