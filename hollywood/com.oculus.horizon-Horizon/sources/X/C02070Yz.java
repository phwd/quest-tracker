package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Yz  reason: invalid class name and case insensitive filesystem */
public final class C02070Yz extends Enum<C02070Yz> {
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
