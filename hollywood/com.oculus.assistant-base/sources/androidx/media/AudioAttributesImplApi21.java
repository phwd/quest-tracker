package androidx.media;

import X.AbstractC0663eB;
import android.media.AudioAttributes;

public class AudioAttributesImplApi21 implements AbstractC0663eB {
    public int A00 = -1;
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
