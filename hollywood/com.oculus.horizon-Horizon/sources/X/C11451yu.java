package X;

import java.util.Arrays;

/* renamed from: X.1yu  reason: invalid class name and case insensitive filesystem */
public final class C11451yu {
    public final int A00;
    public final int A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C11451yu r5 = (C11451yu) obj;
            if (!(this.A02 == r5.A02 && this.A01 == r5.A01 && this.A00 == r5.A00)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.A02), Integer.valueOf(this.A01), 16, 2, Integer.valueOf(this.A00)});
    }

    public final String toString() {
        Object[] objArr = new Object[5];
        int i = this.A02;
        String str = "CAMCORDER";
        switch (i) {
            case 0:
                str = "MIC";
                break;
            case 1:
            case 5:
                break;
            case 2:
                str = "VOICE_UPLINK";
                break;
            case 3:
                str = "VOICE_DOWNLINK";
                break;
            case 4:
                str = "VOICE_CALL";
                break;
            case 6:
                str = "VOICE_RECOGNITION";
                break;
            case 7:
                str = "VOICE_COMMUNICATION";
                break;
            case 8:
                str = "REMOTE_SUBMIX";
                break;
            case 9:
                str = "UNPROCESSED";
                break;
            case 10:
                str = "VOICE_PERFORMANCE";
                break;
            default:
                str = AnonymousClass006.A01("Wrong enum ", i);
                break;
        }
        objArr[0] = str;
        objArr[1] = Integer.valueOf(this.A01);
        objArr[2] = "CHANNEL_IN_MONO";
        objArr[3] = "ENCODING_PCM_16BIT";
        objArr[4] = Integer.valueOf(this.A00);
        return String.format(null, "AudioRecorderConfig{source=%s, sampleRateHz=%d, channelType=%s, encoding=%s, audioBufferMultiplier=%d}", objArr);
    }

    public C11451yu(C11511zB r2) {
        this.A02 = r2.A02;
        this.A01 = r2.A01;
        this.A00 = r2.A00;
    }
}
