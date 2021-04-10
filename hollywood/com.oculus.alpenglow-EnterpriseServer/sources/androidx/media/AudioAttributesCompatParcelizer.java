package androidx.media;

import X.AbstractC03450cR;
import X.AnonymousClass02D;
import X.AnonymousClass0HI;
import X.AnonymousClass0HL;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY})
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AnonymousClass0HI r3) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        AnonymousClass0HL r1 = audioAttributesCompat.A00;
        if (r3.A0I(1)) {
            r1 = r3.A05();
        }
        audioAttributesCompat.A00 = (AbstractC03450cR) r1;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AnonymousClass0HI r2) {
        AbstractC03450cR r1 = audioAttributesCompat.A00;
        r2.A09(1);
        r2.A0C(r1);
    }
}
