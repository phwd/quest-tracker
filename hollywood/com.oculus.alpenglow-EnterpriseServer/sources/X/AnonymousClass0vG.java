package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vG  reason: invalid class name */
public final class AnonymousClass0vG extends Enum<AnonymousClass0vG> {
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
