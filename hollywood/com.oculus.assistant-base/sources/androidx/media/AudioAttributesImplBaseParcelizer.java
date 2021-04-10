package androidx.media;

import X.AbstractC00293k;

public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(AbstractC00293k r3) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.A03 = r3.A01(audioAttributesImplBase.A03, 1);
        audioAttributesImplBase.A00 = r3.A01(audioAttributesImplBase.A00, 2);
        audioAttributesImplBase.A01 = r3.A01(audioAttributesImplBase.A01, 3);
        audioAttributesImplBase.A02 = r3.A01(audioAttributesImplBase.A02, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, AbstractC00293k r3) {
        int i = audioAttributesImplBase.A03;
        r3.A06(1);
        r3.A07(i);
        int i2 = audioAttributesImplBase.A00;
        r3.A06(2);
        r3.A07(i2);
        int i3 = audioAttributesImplBase.A01;
        r3.A06(3);
        r3.A07(i3);
        int i4 = audioAttributesImplBase.A02;
        r3.A06(4);
        r3.A07(i4);
    }
}
