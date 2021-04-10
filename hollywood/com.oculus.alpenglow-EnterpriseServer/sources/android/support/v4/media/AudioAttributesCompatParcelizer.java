package android.support.v4.media;

import X.AnonymousClass02D;
import X.AnonymousClass0HI;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesCompat;

@RestrictTo({AnonymousClass02D.LIBRARY})
public final class AudioAttributesCompatParcelizer extends androidx.media.AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AnonymousClass0HI r0) {
        return androidx.media.AudioAttributesCompatParcelizer.read(r0);
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AnonymousClass0HI r1) {
        androidx.media.AudioAttributesCompatParcelizer.write(audioAttributesCompat, r1);
    }
}
