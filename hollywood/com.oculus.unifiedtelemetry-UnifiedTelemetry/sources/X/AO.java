package X;

import androidx.annotation.NonNull;

public enum AO {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;

    public boolean isAtLeast(@NonNull AO ao) {
        if (compareTo((Enum) ao) >= 0) {
            return true;
        }
        return false;
    }
}
