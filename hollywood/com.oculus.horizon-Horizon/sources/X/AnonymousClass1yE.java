package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1yE  reason: invalid class name */
public final class AnonymousClass1yE {
    public final int A00;
    public final int A01;

    public final boolean equals(@Nullable Object obj) {
        AnonymousClass1yE r4;
        if (obj == null || !(obj instanceof AnonymousClass1yE) || (r4 = (AnonymousClass1yE) obj) == null || this.A01 != r4.A01 || this.A00 != r4.A00) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (this.A01 * 31) + this.A00;
    }

    public final String toString() {
        int i = this.A01;
        int i2 = this.A00;
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        return sb.toString();
    }

    public AnonymousClass1yE(int i, int i2) {
        this.A01 = i;
        this.A00 = i2;
    }
}
