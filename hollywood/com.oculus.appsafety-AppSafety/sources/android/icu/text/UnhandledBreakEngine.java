package android.icu.text;

import android.icu.impl.CharacterIteration;
import android.icu.lang.UCharacter;
import android.icu.lang.UProperty;
import android.icu.text.DictionaryBreakEngine;
import java.text.CharacterIterator;

/* access modifiers changed from: package-private */
public final class UnhandledBreakEngine implements LanguageBreakEngine {
    volatile UnicodeSet fHandled = new UnicodeSet();

    @Override // android.icu.text.LanguageBreakEngine
    public boolean handles(int c) {
        return this.fHandled.contains(c);
    }

    @Override // android.icu.text.LanguageBreakEngine
    public int findBreaks(CharacterIterator text, int startPos, int endPos, DictionaryBreakEngine.DequeI foundBreaks) {
        UnicodeSet uniset = this.fHandled;
        int c = CharacterIteration.current32(text);
        while (text.getIndex() < endPos && uniset.contains(c)) {
            CharacterIteration.next32(text);
            c = CharacterIteration.current32(text);
        }
        return 0;
    }

    public void handleChar(int c) {
        UnicodeSet originalSet = this.fHandled;
        if (!originalSet.contains(c)) {
            int script = UCharacter.getIntPropertyValue(c, UProperty.SCRIPT);
            UnicodeSet newSet = new UnicodeSet();
            newSet.applyIntPropertyValue(UProperty.SCRIPT, script);
            newSet.addAll(originalSet);
            this.fHandled = newSet;
        }
    }
}
