package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Yr  reason: invalid class name and case insensitive filesystem */
public final class C02030Yr extends Enum<C02030Yr> {
    public static String A00(Integer num) {
        switch (num.intValue()) {
            case 1:
                return "FBNS";
            case 2:
                return "PreloadedFBNS";
            case 3:
                return "MqttDirect";
            case 4:
                return "MqttSimpleClient";
            case 5:
                return "MultiuserMqtt";
            case 6:
                return "TestMqttLite";
            case 7:
                return "TestFBNS";
            default:
                return "MqttLite";
        }
    }
}
