package android.icu.text;

import android.icu.text.Transliterator;

class NameUnicodeTransliterator extends Transliterator {
    static void register() {
        Transliterator.registerFactory("Name-Any", new Transliterator.Factory() {
            /* class android.icu.text.NameUnicodeTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NameUnicodeTransliterator(null);
            }
        });
    }

    public NameUnicodeTransliterator(UnicodeFilter unicodeFilter) {
        super("Name-Any", unicodeFilter);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004f, code lost:
        if (r4.length() > r2) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0093, code lost:
        if (r4.length() >= r2) goto L_0x0098;
     */
    @Override // android.icu.text.Transliterator
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleTransliterate(android.icu.text.Replaceable r17, android.icu.text.Transliterator.Position r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.NameUnicodeTransliterator.handleTransliterate(android.icu.text.Replaceable, android.icu.text.Transliterator$Position, boolean):void");
    }
}
