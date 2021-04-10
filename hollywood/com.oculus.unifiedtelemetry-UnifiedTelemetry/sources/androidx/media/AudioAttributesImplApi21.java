package androidx.media;

import X.AnonymousClass2C;
import X.Zl;
import android.media.AudioAttributes;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

@RequiresApi(21)
@RestrictTo({AnonymousClass2C.LIBRARY})
public class AudioAttributesImplApi21 implements Zl {
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public int A00 = -1;
    @RestrictTo({AnonymousClass2C.LIBRARY})
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
        StringBuilder sb = new StringBuilder("AudioAttributesCompat: audioattributes=");
        sb.append(this.A01);
        return sb.toString();
    }
}
