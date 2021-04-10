package androidx.media;

import X.AbstractC03450cR;
import X.AnonymousClass02D;
import android.media.AudioAttributes;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

@RequiresApi(21)
@RestrictTo({AnonymousClass02D.LIBRARY})
public class AudioAttributesImplApi21 implements AbstractC03450cR {
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public int A00 = -1;
    @RestrictTo({AnonymousClass02D.LIBRARY})
    public AudioAttributes A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.A01.equals(((AudioAttributesImplApi21) obj).A01);
    }

    public final int hashCode() {
        return this.A01.hashCode();
    }

    public final String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.A01;
    }
}
