package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Ke  reason: case insensitive filesystem */
public final class C0065Ke {
    public static final C0065Ke A01 = new C0065Ke("");
    public final String A00;

    public final boolean equals(@Nullable Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && this.A00.equals(((C0065Ke) obj).A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public C0065Ke(String str) {
        if (str == null || str.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.A00 = str;
    }

    public final String toString() {
        return this.A00;
    }
}
