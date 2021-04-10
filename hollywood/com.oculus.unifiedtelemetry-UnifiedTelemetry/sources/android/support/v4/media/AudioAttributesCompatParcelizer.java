package android.support.v4.media;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesCompat;

@RestrictTo({AnonymousClass2C.LIBRARY})
public final class AudioAttributesCompatParcelizer extends androidx.media.AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(CW cw) {
        return androidx.media.AudioAttributesCompatParcelizer.read(cw);
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, CW cw) {
        androidx.media.AudioAttributesCompatParcelizer.write(audioAttributesCompat, cw);
    }
}
