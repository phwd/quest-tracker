package android.support.v4.media;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImplApi26;

@RestrictTo({AnonymousClass2C.LIBRARY})
public final class AudioAttributesImplApi26Parcelizer extends androidx.media.AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(CW cw) {
        return androidx.media.AudioAttributesImplApi26Parcelizer.read(cw);
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, CW cw) {
        androidx.media.AudioAttributesImplApi26Parcelizer.write(audioAttributesImplApi26, cw);
    }
}
