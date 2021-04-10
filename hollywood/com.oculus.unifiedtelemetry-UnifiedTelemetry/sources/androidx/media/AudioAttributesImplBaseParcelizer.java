package androidx.media;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2C.LIBRARY})
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(CW cw) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.A03 = cw.A02(audioAttributesImplBase.A03, 1);
        audioAttributesImplBase.A00 = cw.A02(audioAttributesImplBase.A00, 2);
        audioAttributesImplBase.A01 = cw.A02(audioAttributesImplBase.A01, 3);
        audioAttributesImplBase.A02 = cw.A02(audioAttributesImplBase.A02, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, CW cw) {
        int i = audioAttributesImplBase.A03;
        cw.A09(1);
        cw.A0A(i);
        int i2 = audioAttributesImplBase.A00;
        cw.A09(2);
        cw.A0A(i2);
        int i3 = audioAttributesImplBase.A01;
        cw.A09(3);
        cw.A0A(i3);
        int i4 = audioAttributesImplBase.A02;
        cw.A09(4);
        cw.A0A(i4);
    }
}
