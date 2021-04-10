package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0zK  reason: invalid class name and case insensitive filesystem */
public final class C09380zK extends Enum<C09380zK> {
    public static String A00(Integer num) {
        switch (num.intValue()) {
            case 1:
                return "CONNECT_SENT";
            case 2:
                return "CONNECTED";
            case 3:
                return "DISCONNECTED";
            default:
                return "CONNECTING";
        }
    }
}
