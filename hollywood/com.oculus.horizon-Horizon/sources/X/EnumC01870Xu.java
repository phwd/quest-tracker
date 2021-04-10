package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Xu  reason: invalid class name and case insensitive filesystem */
public enum EnumC01870Xu {
    DATA_INVALID,
    DATA_EXPIRED,
    NOTIF_ACKED,
    NOTIF_DUPED,
    BROADCAST_SENT,
    BROADCAST_FAILED,
    PACKAGE_FAILED,
    PACKAGE_INVALID,
    PACKAGE_UNSUPPORTED,
    PACKAGE_INCOMPATIBLE,
    PACKAGE_NOT_INSTALLED,
    PACKAGE_DISABLED,
    PACKAGE_NOT_TRUSTED,
    PACKAGE_TRUSTED,
    PROCESSOR_FAILED;

    public boolean isPermanentFailure() {
        if (this == DATA_INVALID || this == DATA_EXPIRED || this == PACKAGE_INVALID || this == PACKAGE_UNSUPPORTED || this == PACKAGE_INCOMPATIBLE || this == PACKAGE_NOT_INSTALLED || this == PACKAGE_DISABLED || this == PACKAGE_NOT_TRUSTED) {
            return true;
        }
        return false;
    }

    public boolean isSucceeded() {
        if (this == NOTIF_ACKED || this == NOTIF_DUPED || this == BROADCAST_SENT) {
            return true;
        }
        return false;
    }
}
