package androidx.media;

import android.media.AudioAttributes;
import android.os.Build;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImpl;
import androidx.media.AudioAttributesImplApi21;
import androidx.media.AudioAttributesImplApi26;
import androidx.media.AudioAttributesImplBase;
import androidx.versionedparcelable.VersionedParcelable;
import com.facebook.ultralight.UL;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AudioAttributesCompat implements VersionedParcelable {
    private static final int[] SDK_USAGES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    private static final SparseIntArray SUPPRESSIBLE_USAGES = new SparseIntArray();
    static boolean sForceLegacyBehavior;
    @RestrictTo
    public AudioAttributesImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface AttributeUsage {
    }

    static {
        SUPPRESSIBLE_USAGES.put(5, 1);
        SUPPRESSIBLE_USAGES.put(6, 2);
        SUPPRESSIBLE_USAGES.put(7, 2);
        SUPPRESSIBLE_USAGES.put(8, 1);
        SUPPRESSIBLE_USAGES.put(9, 1);
        SUPPRESSIBLE_USAGES.put(10, 1);
    }

    @RestrictTo
    public AudioAttributesCompat() {
    }

    AudioAttributesCompat(AudioAttributesImpl audioAttributesImpl) {
        this.mImpl = audioAttributesImpl;
    }

    @Nullable
    public static AudioAttributesCompat wrap(@NonNull Object obj) {
        if (sForceLegacyBehavior) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return new AudioAttributesCompat(new AudioAttributesImplApi26((AudioAttributes) obj));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return new AudioAttributesCompat(new AudioAttributesImplApi21((AudioAttributes) obj));
        }
        return null;
    }

    public static class Builder {
        final AudioAttributesImpl.Builder mBuilderImpl;

        public Builder() {
            if (AudioAttributesCompat.sForceLegacyBehavior) {
                this.mBuilderImpl = new AudioAttributesImplBase.Builder();
            } else if (Build.VERSION.SDK_INT >= 26) {
                this.mBuilderImpl = new AudioAttributesImplApi26.Builder();
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.mBuilderImpl = new AudioAttributesImplApi21.Builder();
            } else {
                this.mBuilderImpl = new AudioAttributesImplBase.Builder();
            }
        }

        public AudioAttributesCompat build() {
            return new AudioAttributesCompat(this.mBuilderImpl.build());
        }

        public Builder setLegacyStreamType(int i) {
            this.mBuilderImpl.setLegacyStreamType(i);
            return this;
        }
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }

    static String usageToString(int i) {
        switch (i) {
            case 0:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case 4:
                return "USAGE_ALARM";
            case 5:
                return "USAGE_NOTIFICATION";
            case 6:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 12}*/:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case UL.id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID /*{ENCODED_INT: 13}*/:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID /*{ENCODED_INT: 14}*/:
                return "USAGE_GAME";
            case UL.id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID /*{ENCODED_INT: 15}*/:
            default:
                return "unknown usage " + i;
            case UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID /*{ENCODED_INT: 16}*/:
                return "USAGE_ASSISTANT";
        }
    }

    static int toVolumeStreamType(boolean z, int i, int i2) {
        if ((i & 1) == 1) {
            return z ? 1 : 7;
        }
        if ((i & 4) == 4) {
            return z ? 0 : 6;
        }
        switch (i2) {
            case 0:
            case 1:
            case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 12}*/:
            case UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID /*{ENCODED_INT: 14}*/:
            case UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID /*{ENCODED_INT: 16}*/:
                return 3;
            case 2:
                return 0;
            case 3:
                return z ? 0 : 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 11:
                return 10;
            case UL.id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID /*{ENCODED_INT: 13}*/:
                return 1;
            case UL.id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID /*{ENCODED_INT: 15}*/:
            default:
                if (!z) {
                    return 3;
                }
                throw new IllegalArgumentException("Unknown usage value " + i2 + " in audio attributes");
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        AudioAttributesImpl audioAttributesImpl = this.mImpl;
        if (audioAttributesImpl != null) {
            return audioAttributesImpl.equals(audioAttributesCompat.mImpl);
        }
        if (audioAttributesCompat.mImpl == null) {
            return true;
        }
        return false;
    }
}
