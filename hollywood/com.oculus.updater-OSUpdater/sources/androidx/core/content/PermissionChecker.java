package androidx.core.content;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface PermissionResult {
    }

    private PermissionChecker() {
    }
}
