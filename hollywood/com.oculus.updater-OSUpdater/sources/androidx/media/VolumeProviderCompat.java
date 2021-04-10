package androidx.media;

import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class VolumeProviderCompat {

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ControlType {
    }
}
