package android.support.v4.media;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImplBase;

@RestrictTo({AnonymousClass2C.LIBRARY})
public final class AudioAttributesImplBaseParcelizer extends androidx.media.AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(CW cw) {
        return androidx.media.AudioAttributesImplBaseParcelizer.read(cw);
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, CW cw) {
        androidx.media.AudioAttributesImplBaseParcelizer.write(audioAttributesImplBase, cw);
    }
}
