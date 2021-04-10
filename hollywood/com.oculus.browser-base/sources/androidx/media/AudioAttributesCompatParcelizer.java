package androidx.media;

import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(Ls1 ls1) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        Ns1 ns1 = audioAttributesCompat.b;
        if (ls1.h(1)) {
            ns1 = ls1.k();
        }
        audioAttributesCompat.b = (AudioAttributesImpl) ns1;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, Ls1 ls1) {
        Objects.requireNonNull(ls1);
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.b;
        ls1.l(1);
        ls1.o(audioAttributesImpl);
    }
}
