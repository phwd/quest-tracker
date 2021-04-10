package X;

/* JADX INFO: Enum class init method not found */
/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
/* renamed from: X.0aS  reason: invalid class name */
public final class AnonymousClass0aS extends Enum<AnonymousClass0aS> {
    public static String A00(Integer num) {
        switch (num.intValue()) {
            case 1:
                return "DUPLICATED_NOTIFICATION";
            case 2:
                return "DELIVERYHELPER_FAILED";
            case 3:
                return "DISCARDED_NOTIFICATION";
            case 4:
                return "ACKNOWLEDGED_NOTIFICATION";
            case 5:
                return "FAIL_NULL_NOTIF_ID";
            case 6:
                return "FAIL_INVALID_RECEIVER";
            case 7:
                return "FAIL_UNTRUSTED_APP";
            case 8:
                return "FAIL_SECURE_BROADCAST";
            case 9:
                return "REDELIVER_NOTIFICATION";
            default:
                return "NOTIFICATION_RECEIVED";
        }
    }
}
