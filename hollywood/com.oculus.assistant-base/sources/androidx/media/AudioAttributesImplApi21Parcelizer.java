package androidx.media;

import X.AbstractC00293k;
import X.C0665eH;
import android.media.AudioAttributes;
import android.os.Parcelable;

public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(AbstractC00293k r3) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        Parcelable parcelable = audioAttributesImplApi21.A01;
        if (r3.A09(1)) {
            parcelable = r3.A02();
        }
        audioAttributesImplApi21.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi21.A00 = r3.A01(audioAttributesImplApi21.A00, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, AbstractC00293k r4) {
        AudioAttributes audioAttributes = audioAttributesImplApi21.A01;
        r4.A06(1);
        ((C0665eH) r4).A05.writeParcelable(audioAttributes, 0);
        int i = audioAttributesImplApi21.A00;
        r4.A06(2);
        r4.A07(i);
    }
}
