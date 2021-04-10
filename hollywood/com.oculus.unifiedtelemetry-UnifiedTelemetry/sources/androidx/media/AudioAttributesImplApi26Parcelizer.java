package androidx.media;

import X.AnonymousClass2C;
import X.CW;
import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2C.LIBRARY})
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(CW cw) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        Parcelable parcelable = audioAttributesImplApi26.A01;
        if (cw.A0I(1)) {
            parcelable = cw.A03();
        }
        audioAttributesImplApi26.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi26.A00 = cw.A02(audioAttributesImplApi26.A00, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, CW cw) {
        AudioAttributes audioAttributes = audioAttributesImplApi26.A01;
        cw.A09(1);
        cw.A0B(audioAttributes);
        int i = audioAttributesImplApi26.A00;
        cw.A09(2);
        cw.A0A(i);
    }
}
