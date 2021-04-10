package X;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Ot {
    public final boolean equals(@Nullable Object obj) {
        return obj == this || (obj != null && (obj instanceof Ot));
    }

    public final int hashCode() {
        return new Byte((byte) 3).hashCode() + new Boolean(true).hashCode();
    }
}
