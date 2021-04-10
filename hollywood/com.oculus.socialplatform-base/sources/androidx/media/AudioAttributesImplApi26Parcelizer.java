package androidx.media;

import X.AnonymousClass02C;
import X.AnonymousClass0Cb;
import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY})
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(AnonymousClass0Cb r3) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        Parcelable parcelable = audioAttributesImplApi26.A01;
        if (r3.A0I(1)) {
            parcelable = r3.A03();
        }
        audioAttributesImplApi26.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi26.A00 = r3.A02(audioAttributesImplApi26.A00, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, AnonymousClass0Cb r3) {
        AudioAttributes audioAttributes = audioAttributesImplApi26.A01;
        r3.A09(1);
        r3.A0B(audioAttributes);
        int i = audioAttributesImplApi26.A00;
        r3.A09(2);
        r3.A0A(i);
    }
}
