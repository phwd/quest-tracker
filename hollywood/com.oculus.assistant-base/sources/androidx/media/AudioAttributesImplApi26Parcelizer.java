package androidx.media;

import X.AbstractC00293k;
import X.C0665eH;
import android.media.AudioAttributes;
import android.os.Parcelable;

public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(AbstractC00293k r3) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        Parcelable parcelable = audioAttributesImplApi26.A01;
        if (r3.A09(1)) {
            parcelable = r3.A02();
        }
        audioAttributesImplApi26.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi26.A00 = r3.A01(audioAttributesImplApi26.A00, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, AbstractC00293k r4) {
        AudioAttributes audioAttributes = audioAttributesImplApi26.A01;
        r4.A06(1);
        ((C0665eH) r4).A05.writeParcelable(audioAttributes, 0);
        int i = audioAttributesImplApi26.A00;
        r4.A06(2);
        r4.A07(i);
    }
}
