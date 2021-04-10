package android.support.v4.media;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesCompat;

@RestrictTo({AnonymousClass2O.LIBRARY})
public final class AudioAttributesCompatParcelizer extends androidx.media.AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AbstractC0056El el) {
        return androidx.media.AudioAttributesCompatParcelizer.read(el);
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AbstractC0056El el) {
        androidx.media.AudioAttributesCompatParcelizer.write(audioAttributesCompat, el);
    }
}
