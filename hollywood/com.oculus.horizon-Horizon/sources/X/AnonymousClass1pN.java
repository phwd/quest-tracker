package X;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1pN  reason: invalid class name */
public final class AnonymousClass1pN {
    public static final AnonymousClass1pN A02 = new AnonymousClass1pN(-1, false);
    public static final AnonymousClass1pN A03 = new AnonymousClass1pN(-1, true);
    public static final AnonymousClass1pN A04 = new AnonymousClass1pN(-2, false);
    public final int A00;
    public final boolean A01;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass1pN)) {
            return false;
        }
        AnonymousClass1pN r4 = (AnonymousClass1pN) obj;
        return this.A00 == r4.A00 && this.A01 == r4.A01;
    }

    public final String toString() {
        return String.format(null, "%d defer:%b", Integer.valueOf(this.A00), Boolean.valueOf(this.A01));
    }

    public final int hashCode() {
        int hashCode;
        Integer valueOf = Integer.valueOf(this.A00);
        Boolean valueOf2 = Boolean.valueOf(this.A01);
        int i = 0;
        if (valueOf == null) {
            hashCode = 0;
        } else {
            hashCode = valueOf.hashCode();
        }
        if (valueOf2 != null) {
            i = valueOf2.hashCode();
        }
        return ((hashCode + 31) * 31) + i;
    }

    public AnonymousClass1pN(int i, boolean z) {
        this.A00 = i;
        this.A01 = z;
    }
}
