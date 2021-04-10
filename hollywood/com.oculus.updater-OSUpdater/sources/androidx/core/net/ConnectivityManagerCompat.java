package androidx.core.net;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface RestrictBackgroundStatus {
    }

    private ConnectivityManagerCompat() {
    }
}
