package X;

import androidx.annotation.NonNull;

/* renamed from: X.Bp  reason: case insensitive filesystem */
public enum EnumC0040Bp {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;

    public boolean isAtLeast(@NonNull EnumC0040Bp bp) {
        if (compareTo((Enum) bp) >= 0) {
            return true;
        }
        return false;
    }
}
