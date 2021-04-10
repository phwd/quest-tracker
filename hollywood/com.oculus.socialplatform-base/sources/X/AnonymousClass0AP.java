package X;

import androidx.annotation.NonNull;

/* renamed from: X.0AP  reason: invalid class name */
public enum AnonymousClass0AP {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;

    public boolean isAtLeast(@NonNull AnonymousClass0AP r3) {
        if (compareTo((Enum) r3) >= 0) {
            return true;
        }
        return false;
    }
}
