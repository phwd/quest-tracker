package android.support.v4.media;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImplBase;

@RestrictTo({AnonymousClass2O.LIBRARY})
public final class AudioAttributesImplBaseParcelizer extends androidx.media.AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(AbstractC0056El el) {
        return androidx.media.AudioAttributesImplBaseParcelizer.read(el);
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, AbstractC0056El el) {
        androidx.media.AudioAttributesImplBaseParcelizer.write(audioAttributesImplBase, el);
    }
}
