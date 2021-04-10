package android.support.v4.media;

import X.AnonymousClass02C;
import X.AnonymousClass0Cb;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesCompat;

@RestrictTo({AnonymousClass02C.LIBRARY})
public final class AudioAttributesCompatParcelizer extends androidx.media.AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AnonymousClass0Cb r0) {
        return androidx.media.AudioAttributesCompatParcelizer.read(r0);
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AnonymousClass0Cb r1) {
        androidx.media.AudioAttributesCompatParcelizer.write(audioAttributesCompat, r1);
    }
}
