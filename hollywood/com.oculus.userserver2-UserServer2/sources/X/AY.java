package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AY extends RuntimeException {
    public AY(@NonNull String str, @Nullable Exception exc) {
        super(str, exc);
    }
}
