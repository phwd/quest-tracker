package androidx.media;

import android.media.AudioAttributes;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImpl;
import com.facebook.ultralight.UL;

@RequiresApi(UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID)
@RestrictTo
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    @RestrictTo
    public AudioAttributes mAudioAttributes;
    @RestrictTo
    public int mLegacyStreamType;

    AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.mLegacyStreamType = -1;
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = i;
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.mAudioAttributes;
    }

    /* access modifiers changed from: package-private */
    public static class Builder implements AudioAttributesImpl.Builder {
        final AudioAttributes.Builder mFwkBuilder = new AudioAttributes.Builder();

        Builder() {
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        public AudioAttributesImpl build() {
            return new AudioAttributesImplApi21(this.mFwkBuilder.build());
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        public Builder setLegacyStreamType(int i) {
            this.mFwkBuilder.setLegacyStreamType(i);
            return this;
        }
    }
}
