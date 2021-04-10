package X;

/* JADX INFO: Enum class init method not found */
/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
/* renamed from: X.1z1  reason: invalid class name */
public final class AnonymousClass1z1 extends Enum<AnonymousClass1z1> {
    public static String A00(Integer num) {
        if (num == null) {
            return "null";
        }
        switch (num.intValue()) {
            case 1:
                return "PREPARED";
            case 2:
                return "STARTED";
            default:
                return "STOPPED";
        }
    }
}
