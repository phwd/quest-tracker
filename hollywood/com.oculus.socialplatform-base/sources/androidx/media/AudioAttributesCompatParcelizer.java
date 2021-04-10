package androidx.media;

import X.AbstractC05200uh;
import X.AnonymousClass02C;
import X.AnonymousClass0Cb;
import X.AnonymousClass0Ce;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY})
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AnonymousClass0Cb r3) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        AnonymousClass0Ce r1 = audioAttributesCompat.A00;
        if (r3.A0I(1)) {
            r1 = r3.A05();
        }
        audioAttributesCompat.A00 = (AbstractC05200uh) r1;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AnonymousClass0Cb r2) {
        AbstractC05200uh r1 = audioAttributesCompat.A00;
        r2.A09(1);
        r2.A0C(r1);
    }
}
