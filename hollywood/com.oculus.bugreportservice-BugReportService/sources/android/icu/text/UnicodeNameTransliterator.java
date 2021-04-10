package android.icu.text;

import android.icu.lang.UCharacter;
import android.icu.text.Transliterator;

class UnicodeNameTransliterator extends Transliterator {
    static void register() {
        Transliterator.registerFactory("Any-Name", new Transliterator.Factory() {
            /* class android.icu.text.UnicodeNameTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UnicodeNameTransliterator(null);
            }
        });
    }

    public UnicodeNameTransliterator(UnicodeFilter unicodeFilter) {
        super("Any-Name", unicodeFilter);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        int i = position.start;
        int i2 = position.limit;
        StringBuilder sb = new StringBuilder();
        sb.append("\\N{");
        while (i < i2) {
            int char32At = replaceable.char32At(i);
            String extendedName = UCharacter.getExtendedName(char32At);
            if (extendedName != null) {
                sb.setLength(3);
                sb.append(extendedName);
                sb.append('}');
                int charCount = UTF16.getCharCount(char32At);
                replaceable.replace(i, i + charCount, sb.toString());
                int length = sb.length();
                i += length;
                i2 += length - charCount;
            } else {
                i++;
            }
        }
        position.contextLimit += i2 - position.limit;
        position.limit = i2;
        position.start = i;
    }
}
