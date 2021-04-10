package android.icu.text;

import android.icu.text.Transliterator;

class RemoveTransliterator extends Transliterator {
    static void register() {
        Transliterator.registerFactory("Any-Remove", new Transliterator.Factory() {
            /* class android.icu.text.RemoveTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new RemoveTransliterator();
            }
        });
        Transliterator.registerSpecialInverse("Remove", "Null", false);
    }

    public RemoveTransliterator() {
        super("Any-Remove", null);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        replaceable.replace(position.start, position.limit, "");
        int i = position.limit;
        int i2 = i - position.start;
        position.contextLimit -= i2;
        position.limit = i - i2;
    }
}
