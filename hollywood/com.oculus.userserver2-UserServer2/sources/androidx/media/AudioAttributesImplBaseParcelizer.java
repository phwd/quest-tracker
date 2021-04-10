package androidx.media;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2O.LIBRARY})
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(AbstractC0056El el) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.A03 = el.A01(audioAttributesImplBase.A03, 1);
        audioAttributesImplBase.A00 = el.A01(audioAttributesImplBase.A00, 2);
        audioAttributesImplBase.A01 = el.A01(audioAttributesImplBase.A01, 3);
        audioAttributesImplBase.A02 = el.A01(audioAttributesImplBase.A02, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, AbstractC0056El el) {
        int i = audioAttributesImplBase.A03;
        el.A06(1);
        el.A07(i);
        int i2 = audioAttributesImplBase.A00;
        el.A06(2);
        el.A07(i2);
        int i3 = audioAttributesImplBase.A01;
        el.A06(3);
        el.A07(i3);
        int i4 = audioAttributesImplBase.A02;
        el.A06(4);
        el.A07(i4);
    }
}
