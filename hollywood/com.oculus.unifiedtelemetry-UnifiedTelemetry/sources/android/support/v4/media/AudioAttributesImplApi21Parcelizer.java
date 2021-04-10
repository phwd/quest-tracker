package android.support.v4.media;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImplApi21;

@RestrictTo({AnonymousClass2C.LIBRARY})
public final class AudioAttributesImplApi21Parcelizer extends androidx.media.AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(CW cw) {
        return androidx.media.AudioAttributesImplApi21Parcelizer.read(cw);
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, CW cw) {
        androidx.media.AudioAttributesImplApi21Parcelizer.write(audioAttributesImplApi21, cw);
    }
}
