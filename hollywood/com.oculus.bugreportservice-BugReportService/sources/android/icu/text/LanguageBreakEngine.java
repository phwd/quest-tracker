package android.icu.text;

import android.icu.text.DictionaryBreakEngine;
import java.text.CharacterIterator;

/* access modifiers changed from: package-private */
public interface LanguageBreakEngine {
    int findBreaks(CharacterIterator characterIterator, int i, int i2, DictionaryBreakEngine.DequeI dequeI);

    boolean handles(int i);
}
