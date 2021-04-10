package android.icu.text;

import android.icu.util.BytesTrie;
import android.icu.util.CharsTrie;
import java.text.CharacterIterator;

/* access modifiers changed from: package-private */
public class CharsDictionaryMatcher extends DictionaryMatcher {
    private CharSequence characters;

    public CharsDictionaryMatcher(CharSequence charSequence) {
        this.characters = charSequence;
    }

    @Override // android.icu.text.DictionaryMatcher
    public int matches(CharacterIterator characterIterator, int i, int[] iArr, int[] iArr2, int i2, int[] iArr3) {
        int nextCodePoint;
        UCharacterIterator instance = UCharacterIterator.getInstance(characterIterator);
        CharsTrie charsTrie = new CharsTrie(this.characters, 0);
        int nextCodePoint2 = instance.nextCodePoint();
        if (nextCodePoint2 == -1) {
            return 0;
        }
        BytesTrie.Result firstForCodePoint = charsTrie.firstForCodePoint(nextCodePoint2);
        int i3 = 1;
        int i4 = 0;
        while (true) {
            if (!firstForCodePoint.hasValue()) {
                if (firstForCodePoint == BytesTrie.Result.NO_MATCH) {
                    break;
                }
            } else {
                if (i4 < i2) {
                    if (iArr3 != null) {
                        iArr3[i4] = charsTrie.getValue();
                    }
                    iArr[i4] = i3;
                    i4++;
                }
                if (firstForCodePoint == BytesTrie.Result.FINAL_VALUE) {
                    break;
                }
            }
            if (i3 >= i || (nextCodePoint = instance.nextCodePoint()) == -1) {
                break;
            }
            i3++;
            firstForCodePoint = charsTrie.nextForCodePoint(nextCodePoint);
        }
        iArr2[0] = i4;
        return i3;
    }
}
