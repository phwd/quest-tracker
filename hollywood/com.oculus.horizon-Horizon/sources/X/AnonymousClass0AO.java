package X;

import androidx.annotation.NonNull;

/* renamed from: X.0AO  reason: invalid class name */
public enum AnonymousClass0AO {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;

    public boolean isAtLeast(@NonNull AnonymousClass0AO r3) {
        if (compareTo((Enum) r3) >= 0) {
            return true;
        }
        return false;
    }
}
