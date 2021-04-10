package android.icu.text;

import android.icu.text.Transliterator;

class NullTransliterator extends Transliterator {
    public NullTransliterator() {
        super("Any-Null", null);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        position.start = position.limit;
    }
}
