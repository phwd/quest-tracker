package androidx.media;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import X.TK;
import android.media.AudioAttributes;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2O.LIBRARY})
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(AbstractC0056El el) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        Parcelable parcelable = audioAttributesImplApi26.A01;
        if (el.A09(1)) {
            parcelable = el.A02();
        }
        audioAttributesImplApi26.A01 = (AudioAttributes) parcelable;
        audioAttributesImplApi26.A00 = el.A01(audioAttributesImplApi26.A00, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, AbstractC0056El el) {
        AudioAttributes audioAttributes = audioAttributesImplApi26.A01;
        el.A06(1);
        ((TK) el).A05.writeParcelable(audioAttributes, 0);
        int i = audioAttributesImplApi26.A00;
        el.A06(2);
        el.A07(i);
    }
}
