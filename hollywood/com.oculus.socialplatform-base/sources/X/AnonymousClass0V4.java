package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0V4  reason: invalid class name */
public final class AnonymousClass0V4 {
    public volatile boolean A00;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof AnonymousClass0V4) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return 31;
    }
}
