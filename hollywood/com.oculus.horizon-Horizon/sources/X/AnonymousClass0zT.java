package X;

import com.oculus.horizon.service.ODHInterfaceService;

/* renamed from: X.0zT  reason: invalid class name */
public final class AnonymousClass0zT extends Enum<AnonymousClass0zT> {
    public static String A00(Integer num) {
        if (num == null) {
            return "null";
        }
        switch (num.intValue()) {
            case 1:
                return "END_ARRAY";
            case 2:
                return "BEGIN_OBJECT";
            case 3:
                return "END_OBJECT";
            case 4:
                return "NAME";
            case 5:
                return "STRING";
            case 6:
                return "NUMBER";
            case 7:
                return "BOOLEAN";
            case 8:
                return ODHInterfaceService.NULL_VALUE;
            case 9:
                return "END_DOCUMENT";
            default:
                return "BEGIN_ARRAY";
        }
    }
}
