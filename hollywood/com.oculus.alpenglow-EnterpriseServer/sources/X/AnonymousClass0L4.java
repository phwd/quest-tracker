package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0L4  reason: invalid class name */
public final class AnonymousClass0L4 {
    public static final AnonymousClass0L4 A01 = new AnonymousClass0L4("");
    public final String A00;

    public final boolean equals(@Nullable Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && this.A00.equals(((AnonymousClass0L4) obj).A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00;
    }

    public AnonymousClass0L4(String str) {
        if (str == null || str.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.A00 = str;
    }
}
