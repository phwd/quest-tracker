package android.icu.text;

import android.icu.text.Transliterator;

class UnescapeTransliterator extends Transliterator {
    private char[] spec;

    static void register() {
        Transliterator.registerFactory("Hex-Any/Unicode", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any/Unicode", new char[]{2, 0, 16, 4, 6, 'U', '+', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/Java", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass2 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any/Java", new char[]{2, 0, 16, 4, 4, '\\', 'u', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/C", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass3 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any/C", new char[]{2, 0, 16, 4, 4, '\\', 'u', 2, 0, 16, '\b', '\b', '\\', 'U', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass4 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any/XML", new char[]{3, 1, 16, 1, 6, '&', '#', 'x', ';', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML10", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass5 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any/XML10", new char[]{2, 1, '\n', 1, 7, '&', '#', ';', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/Perl", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass6 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any/Perl", new char[]{3, 1, 16, 1, 6, '\\', 'x', '{', '}', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass7 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnescapeTransliterator("Hex-Any", new char[]{2, 0, 16, 4, 6, 'U', '+', 2, 0, 16, 4, 4, '\\', 'u', 2, 0, 16, '\b', '\b', '\\', 'U', 3, 1, 16, 1, 6, '&', '#', 'x', ';', 2, 1, '\n', 1, 7, '&', '#', ';', 3, 1, 16, 1, 6, '\\', 'x', '{', '}', 65535});
            }
        });
    }

    UnescapeTransliterator(String str, char[] cArr) {
        super(str, null);
        this.spec = cArr;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00aa, code lost:
        if (r3 >= r4) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ac, code lost:
        r3 = r3 + android.icu.text.UTF16.getCharCount(r18.char32At(r3));
     */
    @Override // android.icu.text.Transliterator
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleTransliterate(android.icu.text.Replaceable r18, android.icu.text.Transliterator.Position r19, boolean r20) {
        /*
        // Method dump skipped, instructions count: 197
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.UnescapeTransliterator.handleTransliterate(android.icu.text.Replaceable, android.icu.text.Transliterator$Position, boolean):void");
    }
}
