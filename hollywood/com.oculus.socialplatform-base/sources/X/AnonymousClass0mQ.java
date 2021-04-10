package X;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0mQ  reason: invalid class name */
public final class AnonymousClass0mQ {
    public final boolean equals(@Nullable Object obj) {
        return obj == this || (obj != null && (obj instanceof AnonymousClass0mQ));
    }

    public final int hashCode() {
        return new Byte((byte) 3).hashCode() + new Boolean(true).hashCode();
    }
}
