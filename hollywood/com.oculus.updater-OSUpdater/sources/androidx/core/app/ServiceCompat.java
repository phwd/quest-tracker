package androidx.core.app;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ServiceCompat {

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface StopForegroundFlags {
    }

    private ServiceCompat() {
    }
}
