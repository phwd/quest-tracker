package androidx.media;

import X.AnonymousClass2C;
import X.CW;
import X.CZ;
import X.Zl;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2C.LIBRARY})
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(CW cw) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        CZ cz = audioAttributesCompat.A00;
        if (cw.A0I(1)) {
            cz = cw.A05();
        }
        audioAttributesCompat.A00 = (Zl) cz;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, CW cw) {
        Zl zl = audioAttributesCompat.A00;
        cw.A09(1);
        cw.A0C(zl);
    }
}
