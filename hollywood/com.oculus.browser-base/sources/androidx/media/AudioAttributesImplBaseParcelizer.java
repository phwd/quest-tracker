package androidx.media;

import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(Ls1 ls1) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f9476a = ls1.i(audioAttributesImplBase.f9476a, 1);
        audioAttributesImplBase.b = ls1.i(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.c = ls1.i(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = ls1.i(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, Ls1 ls1) {
        Objects.requireNonNull(ls1);
        ls1.m(audioAttributesImplBase.f9476a, 1);
        ls1.m(audioAttributesImplBase.b, 2);
        ls1.m(audioAttributesImplBase.c, 3);
        ls1.m(audioAttributesImplBase.d, 4);
    }
}
