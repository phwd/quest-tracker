package android.icu.text;

import android.icu.impl.Norm2AllModes;
import android.icu.impl.Normalizer2Impl;
import android.icu.text.Transliterator;
import java.util.HashMap;
import java.util.Map;

final class NormalizationTransliterator extends Transliterator {
    static final Map SOURCE_CACHE = new HashMap();
    private final Normalizer2 norm2;

    static void register() {
        Transliterator.registerFactory("Any-NFC", new Transliterator.Factory() {
            /* class android.icu.text.NormalizationTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NormalizationTransliterator("NFC", Normalizer2.getNFCInstance());
            }
        });
        Transliterator.registerFactory("Any-NFD", new Transliterator.Factory() {
            /* class android.icu.text.NormalizationTransliterator.AnonymousClass2 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NormalizationTransliterator("NFD", Normalizer2.getNFDInstance());
            }
        });
        Transliterator.registerFactory("Any-NFKC", new Transliterator.Factory() {
            /* class android.icu.text.NormalizationTransliterator.AnonymousClass3 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NormalizationTransliterator("NFKC", Normalizer2.getNFKCInstance());
            }
        });
        Transliterator.registerFactory("Any-NFKD", new Transliterator.Factory() {
            /* class android.icu.text.NormalizationTransliterator.AnonymousClass4 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NormalizationTransliterator("NFKD", Normalizer2.getNFKDInstance());
            }
        });
        Transliterator.registerFactory("Any-FCD", new Transliterator.Factory() {
            /* class android.icu.text.NormalizationTransliterator.AnonymousClass5 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NormalizationTransliterator("FCD", Norm2AllModes.getFCDNormalizer2());
            }
        });
        Transliterator.registerFactory("Any-FCC", new Transliterator.Factory() {
            /* class android.icu.text.NormalizationTransliterator.AnonymousClass6 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new NormalizationTransliterator("FCC", Norm2AllModes.getNFCInstance().fcc);
            }
        });
        Transliterator.registerSpecialInverse("NFC", "NFD", true);
        Transliterator.registerSpecialInverse("NFKC", "NFKD", true);
        Transliterator.registerSpecialInverse("FCC", "NFD", false);
        Transliterator.registerSpecialInverse("FCD", "FCD", false);
    }

    private NormalizationTransliterator(String str, Normalizer2 normalizer2) {
        super(str, null);
        this.norm2 = normalizer2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        int i = position.start;
        int i2 = position.limit;
        if (i < i2) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int char32At = replaceable.char32At(i);
            do {
                sb.setLength(0);
                int i3 = i;
                while (true) {
                    sb.appendCodePoint(char32At);
                    i3 += Character.charCount(char32At);
                    if (i3 >= i2) {
                        break;
                    }
                    Normalizer2 normalizer2 = this.norm2;
                    int char32At2 = replaceable.char32At(i3);
                    if (normalizer2.hasBoundaryBefore(char32At2)) {
                        char32At = char32At2;
                        break;
                    }
                    char32At = char32At2;
                }
                if (i3 == i2 && z && !this.norm2.hasBoundaryAfter(char32At)) {
                    break;
                }
                this.norm2.normalize(sb, sb2);
                if (!Normalizer2Impl.UTF16Plus.equal(sb, sb2)) {
                    replaceable.replace(i, i3, sb2.toString());
                    int length = sb2.length() - (i3 - i);
                    i3 += length;
                    i2 += length;
                }
                i = i3;
            } while (i < i2);
            position.start = i;
            position.contextLimit += i2 - position.limit;
            position.limit = i2;
        }
    }
}
