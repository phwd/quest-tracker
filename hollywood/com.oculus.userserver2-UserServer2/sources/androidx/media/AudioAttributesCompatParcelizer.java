package androidx.media;

import X.AbstractC0056El;
import X.AbstractC0059Eo;
import X.AnonymousClass2O;
import X.TR;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2O.LIBRARY})
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AbstractC0056El el) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        AbstractC0059Eo eo = audioAttributesCompat.A00;
        if (el.A09(1)) {
            eo = el.A04();
        }
        audioAttributesCompat.A00 = (TR) eo;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AbstractC0056El el) {
        TR tr = audioAttributesCompat.A00;
        el.A06(1);
        el.A08(tr);
    }
}
