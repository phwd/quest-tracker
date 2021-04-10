package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Wh  reason: invalid class name */
public final class AnonymousClass1Wh extends Enum<AnonymousClass1Wh> {
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
