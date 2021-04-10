package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0UV  reason: invalid class name */
public final class AnonymousClass0UV {
    public volatile boolean A00;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof AnonymousClass0UV) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return 31;
    }
}
