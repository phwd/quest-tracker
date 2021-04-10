package android.icu.text;

import android.icu.text.Transliterator;
import java.util.List;

class CompoundTransliterator extends Transliterator {
    private int numAnonymousRBTs;
    private Transliterator[] trans;

    CompoundTransliterator(List list) {
        this(list, 0);
    }

    CompoundTransliterator(List list, int i) {
        super("", null);
        this.numAnonymousRBTs = 0;
        this.trans = null;
        init(list, 0, false);
        this.numAnonymousRBTs = i;
    }

    CompoundTransliterator(String str, UnicodeFilter unicodeFilter, Transliterator[] transliteratorArr, int i) {
        super(str, unicodeFilter);
        this.numAnonymousRBTs = 0;
        this.trans = transliteratorArr;
        this.numAnonymousRBTs = i;
    }

    private void init(List list, int i, boolean z) {
        int size = list.size();
        this.trans = new Transliterator[size];
        for (int i2 = 0; i2 < size; i2++) {
            this.trans[i2] = (Transliterator) list.get(i == 0 ? i2 : (size - 1) - i2);
        }
        if (i == 1 && z) {
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < size; i3++) {
                if (i3 > 0) {
                    sb.append(';');
                }
                sb.append(this.trans[i3].getID());
            }
            setID(sb.toString());
        }
        computeMaximumContextLength();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        if (this.trans.length < 1) {
            position.start = position.limit;
            return;
        }
        int i = position.limit;
        int i2 = position.start;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            Transliterator[] transliteratorArr = this.trans;
            if (i3 >= transliteratorArr.length) {
                break;
            }
            position.start = i2;
            int i5 = position.limit;
            if (position.start == i5) {
                break;
            }
            transliteratorArr[i3].filteredTransliterate(replaceable, position, z);
            if (z || position.start == position.limit) {
                i4 += position.limit - i5;
                if (z) {
                    position.limit = position.start;
                }
                i3++;
            } else {
                throw new RuntimeException("ERROR: Incomplete non-incremental transliteration by " + this.trans[i3].getID());
            }
        }
        position.limit = i + i4;
    }

    private void computeMaximumContextLength() {
        int i = 0;
        int i2 = 0;
        while (true) {
            Transliterator[] transliteratorArr = this.trans;
            if (i < transliteratorArr.length) {
                int maximumContextLength = transliteratorArr[i].getMaximumContextLength();
                if (maximumContextLength > i2) {
                    i2 = maximumContextLength;
                }
                i++;
            } else {
                setMaximumContextLength(i2);
                return;
            }
        }
    }

    public Transliterator safeClone() {
        UnicodeFilter filter = getFilter();
        if (filter != null && (filter instanceof UnicodeSet)) {
            filter = new UnicodeSet((UnicodeSet) filter);
        }
        return new CompoundTransliterator(getID(), filter, this.trans, this.numAnonymousRBTs);
    }
}
