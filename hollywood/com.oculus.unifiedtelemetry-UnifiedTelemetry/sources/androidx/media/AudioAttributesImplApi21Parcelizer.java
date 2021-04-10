package androidx.media;

import X.AnonymousClass2C;
import X.CW;
import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2C.LIBRARY})
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(CW cw) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        Parcelable parcelable = audioAttributesImplApi21.A01;
        if (cw.A0I(1)) {
            parcelable = cw.A03();
        }
        audioAttributesImplApi21.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi21.A00 = cw.A02(audioAttributesImplApi21.A00, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, CW cw) {
        AudioAttributes audioAttributes = audioAttributesImplApi21.A01;
        cw.A09(1);
        cw.A0B(audioAttributes);
        int i = audioAttributesImplApi21.A00;
        cw.A09(2);
        cw.A0A(i);
    }
}
