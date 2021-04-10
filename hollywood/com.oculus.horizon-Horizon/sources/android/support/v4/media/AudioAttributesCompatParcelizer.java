package android.support.v4.media;

import X.AnonymousClass02C;
import X.AnonymousClass0CX;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesCompat;

@RestrictTo({AnonymousClass02C.LIBRARY})
public final class AudioAttributesCompatParcelizer extends androidx.media.AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AnonymousClass0CX r0) {
        return androidx.media.AudioAttributesCompatParcelizer.read(r0);
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AnonymousClass0CX r1) {
        androidx.media.AudioAttributesCompatParcelizer.write(audioAttributesCompat, r1);
    }
}
