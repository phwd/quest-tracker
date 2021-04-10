package defpackage;

import com.oculus.os.Version;

/* renamed from: wl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5475wl0 extends RuntimeException {
    public final int F;

    public C5475wl0(int i) {
        this.F = i;
    }

    public String toString() {
        String str;
        StringBuilder i = AbstractC2531fV.i("MojoResult(");
        i.append(this.F);
        i.append("): ");
        switch (this.F) {
            case 0:
                str = "OK";
                break;
            case 1:
                str = "CANCELLED";
                break;
            case 2:
            default:
                str = "UNKNOWN";
                break;
            case 3:
                str = "INVALID_ARGUMENT";
                break;
            case 4:
                str = "DEADLINE_EXCEEDED";
                break;
            case 5:
                str = "NOT_FOUND";
                break;
            case 6:
                str = "ALREADY_EXISTS";
                break;
            case Version.VERSION_7:
                str = "PERMISSION_DENIED";
                break;
            case Version.VERSION_8:
                str = "RESOURCE_EXHAUSTED";
                break;
            case Version.VERSION_9:
                str = "FAILED_PRECONDITION";
                break;
            case Version.VERSION_10:
                str = "ABORTED";
                break;
            case Version.VERSION_11:
                str = "OUT_OF_RANGE";
                break;
            case Version.VERSION_12:
                str = "UNIMPLEMENTED";
                break;
            case Version.VERSION_13:
                str = "INTERNAL";
                break;
            case Version.VERSION_14:
                str = "UNAVAILABLE";
                break;
            case Version.VERSION_15:
                str = "DATA_LOSS";
                break;
            case Version.VERSION_16:
                str = "BUSY";
                break;
            case Version.VERSION_17:
                str = "SHOULD_WAIT";
                break;
        }
        i.append(str);
        return i.toString();
    }
}
