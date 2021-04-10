package androidx.media;

import X.AbstractC07180rZ;
import X.AnonymousClass02C;
import X.AnonymousClass0CX;
import X.AnonymousClass0Ca;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY})
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AnonymousClass0CX r3) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        AnonymousClass0Ca r1 = audioAttributesCompat.A00;
        if (r3.A0I(1)) {
            r1 = r3.A05();
        }
        audioAttributesCompat.A00 = (AbstractC07180rZ) r1;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AnonymousClass0CX r2) {
        AbstractC07180rZ r1 = audioAttributesCompat.A00;
        r2.A09(1);
        r2.A0C(r1);
    }
}
