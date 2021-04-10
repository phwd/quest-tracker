package androidx.media;

import android.media.AudioAttributes;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(Ls1 ls1) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.f9475a = (AudioAttributes) ls1.j(audioAttributesImplApi21.f9475a, 1);
        audioAttributesImplApi21.b = ls1.i(audioAttributesImplApi21.b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, Ls1 ls1) {
        Objects.requireNonNull(ls1);
        ls1.n(audioAttributesImplApi21.f9475a, 1);
        ls1.m(audioAttributesImplApi21.b, 2);
    }
}
