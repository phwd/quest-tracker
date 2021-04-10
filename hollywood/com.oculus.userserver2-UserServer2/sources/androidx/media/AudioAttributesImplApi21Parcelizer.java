package androidx.media;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import X.TK;
import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2O.LIBRARY})
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(AbstractC0056El el) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        Parcelable parcelable = audioAttributesImplApi21.A01;
        if (el.A09(1)) {
            parcelable = el.A02();
        }
        audioAttributesImplApi21.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi21.A00 = el.A01(audioAttributesImplApi21.A00, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, AbstractC0056El el) {
        AudioAttributes audioAttributes = audioAttributesImplApi21.A01;
        el.A06(1);
        ((TK) el).A05.writeParcelable(audioAttributes, 0);
        int i = audioAttributesImplApi21.A00;
        el.A06(2);
        el.A07(i);
    }
}
