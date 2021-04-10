package android.icu.text;

import android.icu.text.Transliterator;
import java.util.ArrayList;
import java.util.List;

class TransliterationRuleSet {
    private int[] index;
    private int maxContextLength = 0;
    private List ruleVector = new ArrayList();
    private TransliterationRule[] rules;

    public int getMaximumContextLength() {
        return this.maxContextLength;
    }

    public void addRule(TransliterationRule transliterationRule) {
        this.ruleVector.add(transliterationRule);
        int anteContextLength = transliterationRule.getAnteContextLength();
        if (anteContextLength > this.maxContextLength) {
            this.maxContextLength = anteContextLength;
        }
        this.rules = null;
    }

    public void freeze() {
        int i;
        int size = this.ruleVector.size();
        this.index = new int[257];
        ArrayList arrayList = new ArrayList(size * 2);
        int[] iArr = new int[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = ((TransliterationRule) this.ruleVector.get(i3)).getIndexValue();
        }
        for (int i4 = 0; i4 < 256; i4++) {
            this.index[i4] = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                if (iArr[i5] < 0) {
                    TransliterationRule transliterationRule = (TransliterationRule) this.ruleVector.get(i5);
                    if (transliterationRule.matchesIndexValue(i4)) {
                        arrayList.add(transliterationRule);
                    }
                } else if (iArr[i5] == i4) {
                    arrayList.add((TransliterationRule) this.ruleVector.get(i5));
                }
            }
        }
        this.index[256] = arrayList.size();
        this.rules = new TransliterationRule[arrayList.size()];
        arrayList.toArray(this.rules);
        StringBuilder sb = null;
        while (i2 < 256) {
            int i6 = this.index[i2];
            while (true) {
                i = i2 + 1;
                if (i6 >= this.index[i] - 1) {
                    break;
                }
                TransliterationRule transliterationRule2 = this.rules[i6];
                i6++;
                StringBuilder sb2 = sb;
                for (int i7 = i6; i7 < this.index[i]; i7++) {
                    TransliterationRule transliterationRule3 = this.rules[i7];
                    if (transliterationRule2.masks(transliterationRule3)) {
                        if (sb2 == null) {
                            sb2 = new StringBuilder();
                        } else {
                            sb2.append("\n");
                        }
                        sb2.append("Rule " + transliterationRule2 + " masks " + transliterationRule3);
                    }
                }
                sb = sb2;
            }
            i2 = i;
        }
        if (sb != null) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public boolean transliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        int char32At = replaceable.char32At(position.start) & 255;
        for (int i = this.index[char32At]; i < this.index[char32At + 1]; i++) {
            int matchAndReplace = this.rules[i].matchAndReplace(replaceable, position, z);
            if (matchAndReplace == 1) {
                return false;
            }
            if (matchAndReplace == 2) {
                return true;
            }
        }
        int i2 = position.start;
        position.start = i2 + UTF16.getCharCount(replaceable.char32At(i2));
        return true;
    }
}
