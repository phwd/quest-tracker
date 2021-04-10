package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class KN {
    public static final KN A01 = new KN("");
    public final String A00;

    public final boolean equals(@Nullable Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && this.A00.equals(((KN) obj).A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public KN(String str) {
        if (str == null || str.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.A00 = str;
    }

    public final String toString() {
        return this.A00;
    }
}
