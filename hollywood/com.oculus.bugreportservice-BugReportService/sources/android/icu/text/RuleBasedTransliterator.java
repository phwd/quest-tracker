package android.icu.text;

import android.icu.text.Transliterator;
import java.util.HashMap;
import java.util.Map;

public class RuleBasedTransliterator extends Transliterator {
    private final Data data;

    RuleBasedTransliterator(String str, Data data2, UnicodeFilter unicodeFilter) {
        super(str, unicodeFilter);
        this.data = data2;
        setMaximumContextLength(data2.ruleSet.getMaximumContextLength());
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        synchronized (this.data) {
            int i = 0;
            int i2 = (position.limit - position.start) << 4;
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
            }
            while (position.start < position.limit && i <= i2 && this.data.ruleSet.transliterate(replaceable, position, z)) {
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class Data {
        public TransliterationRuleSet ruleSet = new TransliterationRuleSet();
        Map variableNames = new HashMap();
        Object[] variables;
        char variablesBase;

        public UnicodeMatcher lookupMatcher(int i) {
            int i2 = i - this.variablesBase;
            if (i2 >= 0) {
                Object[] objArr = this.variables;
                if (i2 < objArr.length) {
                    return (UnicodeMatcher) objArr[i2];
                }
            }
            return null;
        }

        public UnicodeReplacer lookupReplacer(int i) {
            int i2 = i - this.variablesBase;
            if (i2 >= 0) {
                Object[] objArr = this.variables;
                if (i2 < objArr.length) {
                    return (UnicodeReplacer) objArr[i2];
                }
            }
            return null;
        }
    }

    public Transliterator safeClone() {
        UnicodeFilter filter = getFilter();
        if (filter != null && (filter instanceof UnicodeSet)) {
            filter = new UnicodeSet((UnicodeSet) filter);
        }
        return new RuleBasedTransliterator(getID(), this.data, filter);
    }
}
