package java.text;

import android.icu.text.Normalizer;

public final class Normalizer {
    private Normalizer() {
    }

    public enum Form {
        NFD(android.icu.text.Normalizer.NFD),
        NFC(android.icu.text.Normalizer.NFC),
        NFKD(android.icu.text.Normalizer.NFKD),
        NFKC(android.icu.text.Normalizer.NFKC);
        
        private final Normalizer.Mode icuMode;

        private Form(Normalizer.Mode icuMode2) {
            this.icuMode = icuMode2;
        }
    }

    public static String normalize(CharSequence src, Form form) {
        return android.icu.text.Normalizer.normalize(src.toString(), form.icuMode);
    }

    public static boolean isNormalized(CharSequence src, Form form) {
        return android.icu.text.Normalizer.isNormalized(src.toString(), form.icuMode, 0);
    }
}
