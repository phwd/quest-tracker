package X;

import androidx.annotation.NonNull;

/* renamed from: X.0DX  reason: invalid class name */
public enum AnonymousClass0DX {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;

    public boolean isAtLeast(@NonNull AnonymousClass0DX r3) {
        if (compareTo((Enum) r3) >= 0) {
            return true;
        }
        return false;
    }
}
