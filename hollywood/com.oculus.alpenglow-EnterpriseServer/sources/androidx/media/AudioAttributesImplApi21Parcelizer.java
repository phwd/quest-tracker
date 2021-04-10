package androidx.media;

import X.AnonymousClass02D;
import X.AnonymousClass0HI;
import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY})
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(AnonymousClass0HI r3) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        Parcelable parcelable = audioAttributesImplApi21.A01;
        if (r3.A0I(1)) {
            parcelable = r3.A03();
        }
        audioAttributesImplApi21.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi21.A00 = r3.A02(audioAttributesImplApi21.A00, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, AnonymousClass0HI r3) {
        AudioAttributes audioAttributes = audioAttributesImplApi21.A01;
        r3.A09(1);
        r3.A0B(audioAttributes);
        int i = audioAttributesImplApi21.A00;
        r3.A09(2);
        r3.A0A(i);
    }
}
