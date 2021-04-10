package androidx.media;

import X.AnonymousClass02C;
import X.AnonymousClass0Cb;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY})
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(AnonymousClass0Cb r3) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.A03 = r3.A02(audioAttributesImplBase.A03, 1);
        audioAttributesImplBase.A00 = r3.A02(audioAttributesImplBase.A00, 2);
        audioAttributesImplBase.A01 = r3.A02(audioAttributesImplBase.A01, 3);
        audioAttributesImplBase.A02 = r3.A02(audioAttributesImplBase.A02, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, AnonymousClass0Cb r3) {
        int i = audioAttributesImplBase.A03;
        r3.A09(1);
        r3.A0A(i);
        int i2 = audioAttributesImplBase.A00;
        r3.A09(2);
        r3.A0A(i2);
        int i3 = audioAttributesImplBase.A01;
        r3.A09(3);
        r3.A0A(i3);
        int i4 = audioAttributesImplBase.A02;
        r3.A09(4);
        r3.A0A(i4);
    }
}
