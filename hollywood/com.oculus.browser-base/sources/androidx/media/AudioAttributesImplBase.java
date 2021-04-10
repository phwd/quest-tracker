package androidx.media;

import android.util.SparseIntArray;
import com.oculus.os.Version;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AudioAttributesImplBase implements AudioAttributesImpl {

    /* renamed from: a  reason: collision with root package name */
    public int f9476a = 0;
    public int b = 0;
    public int c = 0;
    public int d = -1;

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.b != audioAttributesImplBase.b) {
            return false;
        }
        int i = this.c;
        int i2 = audioAttributesImplBase.c;
        int i3 = audioAttributesImplBase.d;
        int i4 = 4;
        if (i3 == -1) {
            int i5 = audioAttributesImplBase.f9476a;
            SparseIntArray sparseIntArray = AudioAttributesCompat.f9474a;
            if ((i2 & 1) != 1) {
                if ((i2 & 4) != 4) {
                    switch (i5) {
                        case 2:
                            i4 = 0;
                            break;
                        case 3:
                            i4 = 8;
                            break;
                        case 4:
                            break;
                        case 5:
                        case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                        case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                        case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                        case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                            i4 = 5;
                            break;
                        case 6:
                            i4 = 2;
                            break;
                        case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                            i4 = 10;
                            break;
                        case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                        default:
                            i4 = 3;
                            break;
                        case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                            i4 = 1;
                            break;
                    }
                } else {
                    i4 = 6;
                }
            } else {
                i4 = 7;
            }
        } else {
            i4 = i3;
        }
        if (i4 == 6) {
            i2 |= 4;
        } else if (i4 == 7) {
            i2 |= 1;
        }
        if (i == (i2 & 273) && this.f9476a == audioAttributesImplBase.f9476a && this.d == i3) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.f9476a), Integer.valueOf(this.d)});
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.d != -1) {
            sb.append(" stream=");
            sb.append(this.d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        int i = this.f9476a;
        SparseIntArray sparseIntArray = AudioAttributesCompat.f9474a;
        switch (i) {
            case 0:
                str = "USAGE_UNKNOWN";
                break;
            case 1:
                str = "USAGE_MEDIA";
                break;
            case 2:
                str = "USAGE_VOICE_COMMUNICATION";
                break;
            case 3:
                str = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                break;
            case 4:
                str = "USAGE_ALARM";
                break;
            case 5:
                str = "USAGE_NOTIFICATION";
                break;
            case 6:
                str = "USAGE_NOTIFICATION_RINGTONE";
                break;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                str = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                break;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                str = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                break;
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                str = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                break;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                str = "USAGE_NOTIFICATION_EVENT";
                break;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                str = "USAGE_ASSISTANCE_ACCESSIBILITY";
                break;
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                str = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                break;
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                str = "USAGE_ASSISTANCE_SONIFICATION";
                break;
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                str = "USAGE_GAME";
                break;
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
            default:
                str = AbstractC2531fV.w("unknown usage ", i);
                break;
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                str = "USAGE_ASSISTANT";
                break;
        }
        sb.append(str);
        sb.append(" content=");
        sb.append(this.b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.c).toUpperCase());
        return sb.toString();
    }
}
