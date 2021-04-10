package androidx.media;

import android.media.AudioAttributes;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {

    /* renamed from: a  reason: collision with root package name */
    public AudioAttributes f9475a;
    public int b = -1;

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.f9475a.equals(((AudioAttributesImplApi21) obj).f9475a);
    }

    public int hashCode() {
        return this.f9475a.hashCode();
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("AudioAttributesCompat: audioattributes=");
        i.append(this.f9475a);
        return i.toString();
    }
}
