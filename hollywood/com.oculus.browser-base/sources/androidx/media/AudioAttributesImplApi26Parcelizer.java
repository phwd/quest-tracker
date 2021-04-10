package androidx.media;

import android.media.AudioAttributes;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(Ls1 ls1) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.f9475a = (AudioAttributes) ls1.j(audioAttributesImplApi26.f9475a, 1);
        audioAttributesImplApi26.b = ls1.i(audioAttributesImplApi26.b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, Ls1 ls1) {
        Objects.requireNonNull(ls1);
        ls1.n(audioAttributesImplApi26.f9475a, 1);
        ls1.m(audioAttributesImplApi26.b, 2);
    }
}
