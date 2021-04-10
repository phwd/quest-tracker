package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* renamed from: X.07p  reason: invalid class name */
public class AnonymousClass07p {
    public final C003307q A00;

    @Nullable
    public AnonymousClass071 A02() {
        return null;
    }

    public boolean A07() {
        return false;
    }

    public boolean A08() {
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnonymousClass07p)) {
            return false;
        }
        AnonymousClass07p r4 = (AnonymousClass07p) obj;
        return A08() == r4.A08() && A07() == r4.A07() && Objects.equals(A01(), r4.A01()) && Objects.equals(A00(), r4.A00()) && Objects.equals(A02(), r4.A02());
    }

    public AnonymousClass07p(@NonNull C003307q r1) {
        this.A00 = r1;
    }

    @NonNull
    public AnonymousClass057 A00() {
        return AnonymousClass057.A04;
    }

    @NonNull
    public AnonymousClass057 A01() {
        return AnonymousClass057.A04;
    }

    @NonNull
    public C003307q A03() {
        return this.A00;
    }

    @NonNull
    public C003307q A04() {
        return this.A00;
    }

    @NonNull
    public C003307q A05() {
        return this.A00;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(A08()), Boolean.valueOf(A07()), A01(), A00(), A02());
    }

    @NonNull
    public C003307q A06(int i, int i2, int i3, int i4) {
        return C003307q.A01;
    }
}
