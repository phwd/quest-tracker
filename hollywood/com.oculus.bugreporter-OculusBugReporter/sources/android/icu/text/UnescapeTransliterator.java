package android.icu.text;

import android.icu.impl.PatternTokenizer;
import android.icu.impl.Utility;
import android.icu.text.Transliterator;

class UnescapeTransliterator extends Transliterator {
    private static final char END = 65535;
    private char[] spec;

    static void register() {
        Transliterator.registerFactory("Hex-Any/Unicode", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Unicode", new char[]{2, 0, 16, 4, 6, 'U', '+', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/Java", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass2 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Java", new char[]{2, 0, 16, 4, 4, PatternTokenizer.BACK_SLASH, 'u', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/C", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass3 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/C", new char[]{2, 0, 16, 4, 4, PatternTokenizer.BACK_SLASH, 'u', 2, 0, 16, '\b', '\b', PatternTokenizer.BACK_SLASH, 'U', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass4 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/XML", new char[]{3, 1, 16, 1, 6, '&', '#', 'x', ';', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/XML10", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass5 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/XML10", new char[]{2, 1, '\n', 1, 7, '&', '#', ';', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any/Perl", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass6 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any/Perl", new char[]{3, 1, 16, 1, 6, PatternTokenizer.BACK_SLASH, 'x', '{', '}', 65535});
            }
        });
        Transliterator.registerFactory("Hex-Any", new Transliterator.Factory() {
            /* class android.icu.text.UnescapeTransliterator.AnonymousClass7 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new UnescapeTransliterator("Hex-Any", new char[]{2, 0, 16, 4, 6, 'U', '+', 2, 0, 16, 4, 4, PatternTokenizer.BACK_SLASH, 'u', 2, 0, 16, '\b', '\b', PatternTokenizer.BACK_SLASH, 'U', 3, 1, 16, 1, 6, '&', '#', 'x', ';', 2, 1, '\n', 1, 7, '&', '#', ';', 3, 1, 16, 1, 6, PatternTokenizer.BACK_SLASH, 'x', '{', '}', 65535});
            }
        });
    }

    UnescapeTransliterator(String ID, char[] spec2) {
        super(ID, null);
        this.spec = spec2;
    }

    /* JADX INFO: Multiple debug info for r5v4 char: [D('prefixLen' int), D('ipat' int)] */
    /* JADX INFO: Multiple debug info for r7v2 char: [D('suffixLen' int), D('ipat' int)] */
    /* JADX INFO: Multiple debug info for r8v2 char: [D('radix' int), D('ipat' int)] */
    /* JADX INFO: Multiple debug info for r9v1 char: [D('minDigits' int), D('ipat' int)] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d5, code lost:
        if (r3 >= r4) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d7, code lost:
        r3 = r3 + android.icu.text.UTF16.getCharCount(r20.char32At(r3));
     */
    @Override // android.icu.text.Transliterator
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleTransliterate(android.icu.text.Replaceable r20, android.icu.text.Transliterator.Position r21, boolean r22) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.UnescapeTransliterator.handleTransliterate(android.icu.text.Replaceable, android.icu.text.Transliterator$Position, boolean):void");
    }

    @Override // android.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        UnicodeSet myFilter = getFilterAsUnicodeSet(inputFilter);
        UnicodeSet items = new UnicodeSet();
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        while (true) {
            char[] cArr = this.spec;
            if (cArr[i] == 65535) {
                break;
            }
            int end = cArr[i] + i + cArr[i + 1] + 5;
            char c = cArr[i + 2];
            for (int j = 0; j < c; j++) {
                Utility.appendNumber(buffer, j, c, 0);
            }
            for (int j2 = i + 5; j2 < end; j2++) {
                items.add(this.spec[j2]);
            }
            i = end;
        }
        items.addAll(buffer.toString());
        items.retainAll(myFilter);
        if (items.size() > 0) {
            sourceSet.addAll(items);
            targetSet.addAll(0, 1114111);
        }
    }
}
