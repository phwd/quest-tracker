package X;

/* renamed from: X.10i  reason: invalid class name */
public class AnonymousClass10i extends AbstractC097010l {
    public static String A00(Integer num) {
        if (num == null) {
            return "null";
        }
        switch (num.intValue()) {
            case 1:
                return "DEBUG";
            case 2:
                return "INFO";
            case 3:
                return "WARN";
            case 4:
                return "ERROR";
            case 5:
                return "ASSERT";
            default:
                return "VERBOSE";
        }
    }
}
