package defpackage;

/* renamed from: SF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class SF1 {
    public static String a(Integer num) {
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
        if (intValue == 0) {
            return "REPEAT_OFF";
        }
        if (intValue == 1) {
            return "REPEAT_ALL";
        }
        if (intValue == 2) {
            return "REPEAT_SINGLE";
        }
        if (intValue != 3) {
            return null;
        }
        return "REPEAT_ALL_AND_SHUFFLE";
    }

    public static Integer b(String str) {
        if (str == null) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1118317585:
                if (str.equals("REPEAT_ALL_AND_SHUFFLE")) {
                    c = 0;
                    break;
                }
                break;
            case -962896020:
                if (str.equals("REPEAT_SINGLE")) {
                    c = 1;
                    break;
                }
                break;
            case 1645938909:
                if (str.equals("REPEAT_ALL")) {
                    c = 2;
                    break;
                }
                break;
            case 1645952171:
                if (str.equals("REPEAT_OFF")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 0;
            default:
                return null;
        }
    }
}
