package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* renamed from: X.07q  reason: invalid class name and case insensitive filesystem */
public class C001807q {
    public final AnonymousClass07r A00;

    @Nullable
    public C001406t A02() {
        return null;
    }

    public boolean A06() {
        return false;
    }

    public boolean A07() {
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C001807q)) {
            return false;
        }
        C001807q r4 = (C001807q) obj;
        return A06() == r4.A06() && A07() == r4.A07() && Objects.equals(A01(), r4.A01()) && Objects.equals(A00(), r4.A00()) && Objects.equals(A02(), r4.A02());
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(A06()), Boolean.valueOf(A07()), A01(), A00(), A02());
    }

    public C001807q(@NonNull AnonymousClass07r r1) {
        this.A00 = r1;
    }

    @NonNull
    public AnonymousClass053 A00() {
        return AnonymousClass053.A04;
    }

    @NonNull
    public AnonymousClass053 A01() {
        return AnonymousClass053.A04;
    }

    @NonNull
    public AnonymousClass07r A03() {
        return this.A00;
    }

    @NonNull
    public AnonymousClass07r A04() {
        return this.A00;
    }

    @NonNull
    public AnonymousClass07r A05() {
        return this.A00;
    }
}
