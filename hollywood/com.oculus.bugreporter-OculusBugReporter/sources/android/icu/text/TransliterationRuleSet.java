package android.icu.text;

import android.icu.text.Transliterator;
import java.util.ArrayList;
import java.util.List;

class TransliterationRuleSet {
    private int[] index;
    private int maxContextLength = 0;
    private List<TransliterationRule> ruleVector = new ArrayList();
    private TransliterationRule[] rules;

    public int getMaximumContextLength() {
        return this.maxContextLength;
    }

    public void addRule(TransliterationRule rule) {
        this.ruleVector.add(rule);
        int len = rule.getAnteContextLength();
        if (len > this.maxContextLength) {
            this.maxContextLength = len;
        }
        this.rules = null;
    }

    public void freeze() {
        int n = this.ruleVector.size();
        this.index = new int[257];
        List<TransliterationRule> v = new ArrayList<>(n * 2);
        int[] indexValue = new int[n];
        for (int j = 0; j < n; j++) {
            indexValue[j] = this.ruleVector.get(j).getIndexValue();
        }
        for (int x = 0; x < 256; x++) {
            this.index[x] = v.size();
            for (int j2 = 0; j2 < n; j2++) {
                if (indexValue[j2] < 0) {
                    TransliterationRule r = this.ruleVector.get(j2);
                    if (r.matchesIndexValue(x)) {
                        v.add(r);
                    }
                } else if (indexValue[j2] == x) {
                    v.add(this.ruleVector.get(j2));
                }
            }
        }
        this.index[256] = v.size();
        this.rules = new TransliterationRule[v.size()];
        v.toArray(this.rules);
        StringBuilder errors = null;
        for (int x2 = 0; x2 < 256; x2++) {
            for (int j3 = this.index[x2]; j3 < this.index[x2 + 1] - 1; j3++) {
                TransliterationRule r1 = this.rules[j3];
                for (int k = j3 + 1; k < this.index[x2 + 1]; k++) {
                    TransliterationRule r2 = this.rules[k];
                    if (r1.masks(r2)) {
                        if (errors == null) {
                            errors = new StringBuilder();
                        } else {
                            errors.append("\n");
                        }
                        errors.append("Rule " + ((Object) r1) + " masks " + ((Object) r2));
                    }
                }
            }
        }
        if (errors != null) {
            throw new IllegalArgumentException(errors.toString());
        }
    }

    public boolean transliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
        int indexByte = text.char32At(pos.start) & 255;
        for (int i = this.index[indexByte]; i < this.index[indexByte + 1]; i++) {
            int m = this.rules[i].matchAndReplace(text, pos, incremental);
            if (m == 1) {
                return false;
            }
            if (m == 2) {
                return true;
            }
        }
        pos.start += UTF16.getCharCount(text.char32At(pos.start));
        return true;
    }

    /* access modifiers changed from: package-private */
    public String toRules(boolean escapeUnprintable) {
        int count = this.ruleVector.size();
        StringBuilder ruleSource = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0) {
                ruleSource.append('\n');
            }
            ruleSource.append(this.ruleVector.get(i).toRule(escapeUnprintable));
        }
        return ruleSource.toString();
    }

    /* access modifiers changed from: package-private */
    public void addSourceTargetSet(UnicodeSet filter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet currentFilter = new UnicodeSet(filter);
        UnicodeSet revisiting = new UnicodeSet();
        int count = this.ruleVector.size();
        for (int i = 0; i < count; i++) {
            this.ruleVector.get(i).addSourceTargetSet(currentFilter, sourceSet, targetSet, revisiting.clear());
            currentFilter.addAll(revisiting);
        }
    }
}
