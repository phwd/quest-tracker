package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Kv  reason: invalid class name */
public final class AnonymousClass0Kv {
    public static final AnonymousClass0Kv A01 = new AnonymousClass0Kv("");
    public final String A00;

    public final boolean equals(@Nullable Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && this.A00.equals(((AnonymousClass0Kv) obj).A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass0Kv(String str) {
        if (str == null || str.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.A00 = str;
    }

    public final String toString() {
        return this.A00;
    }
}
