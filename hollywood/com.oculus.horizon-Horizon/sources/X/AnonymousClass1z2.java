package X;

/* JADX INFO: Enum class init method not found */
/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
/* renamed from: X.1z2  reason: invalid class name */
public final class AnonymousClass1z2 extends Enum<AnonymousClass1z2> {
    public static String A00(Integer num) {
        switch (num.intValue()) {
            case 1:
                return "STARTED";
            case 2:
                return "STOP_IN_PROGRESS";
            case 3:
                return "STOPPED";
            default:
                return "PREPARED";
        }
    }
}
