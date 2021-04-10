package androidx.media;

import X.AbstractC00293k;
import X.AbstractC00303l;
import X.AbstractC0663eB;

public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AbstractC00293k r3) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        AbstractC00303l r1 = audioAttributesCompat.A00;
        if (r3.A09(1)) {
            r1 = r3.A04();
        }
        audioAttributesCompat.A00 = (AbstractC0663eB) r1;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AbstractC00293k r2) {
        AbstractC0663eB eBVar = audioAttributesCompat.A00;
        r2.A06(1);
        r2.A08(eBVar);
    }
}
