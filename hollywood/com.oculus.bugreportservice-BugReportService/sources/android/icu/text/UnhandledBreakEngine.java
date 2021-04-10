package android.icu.text;

import android.icu.impl.CharacterIteration;
import android.icu.lang.UCharacter;
import android.icu.text.DictionaryBreakEngine;
import java.text.CharacterIterator;

/* access modifiers changed from: package-private */
public final class UnhandledBreakEngine implements LanguageBreakEngine {
    volatile UnicodeSet fHandled = new UnicodeSet();

    @Override // android.icu.text.LanguageBreakEngine
    public boolean handles(int i) {
        return this.fHandled.contains(i);
    }

    @Override // android.icu.text.LanguageBreakEngine
    public int findBreaks(CharacterIterator characterIterator, int i, int i2, DictionaryBreakEngine.DequeI dequeI) {
        UnicodeSet unicodeSet = this.fHandled;
        int current32 = CharacterIteration.current32(characterIterator);
        while (characterIterator.getIndex() < i2 && unicodeSet.contains(current32)) {
            CharacterIteration.next32(characterIterator);
            current32 = CharacterIteration.current32(characterIterator);
        }
        return 0;
    }

    public void handleChar(int i) {
        UnicodeSet unicodeSet = this.fHandled;
        if (!unicodeSet.contains(i)) {
            int intPropertyValue = UCharacter.getIntPropertyValue(i, 4106);
            UnicodeSet unicodeSet2 = new UnicodeSet();
            unicodeSet2.applyIntPropertyValue(4106, intPropertyValue);
            unicodeSet2.addAll(unicodeSet);
            this.fHandled = unicodeSet2;
        }
    }
}
