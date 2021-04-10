package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* renamed from: X.0B6  reason: invalid class name */
public class AnonymousClass0B6 {
    public final AnonymousClass0B7 A00;

    @Nullable
    public AnonymousClass0AH A02() {
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
        if (!(obj instanceof AnonymousClass0B6)) {
            return false;
        }
        AnonymousClass0B6 r4 = (AnonymousClass0B6) obj;
        return A08() == r4.A08() && A07() == r4.A07() && Objects.equals(A01(), r4.A01()) && Objects.equals(A00(), r4.A00()) && Objects.equals(A02(), r4.A02());
    }

    @NonNull
    public AnonymousClass08P A00() {
        return AnonymousClass08P.A04;
    }

    @NonNull
    public AnonymousClass08P A01() {
        return AnonymousClass08P.A04;
    }

    @NonNull
    public AnonymousClass0B7 A03() {
        return this.A00;
    }

    @NonNull
    public AnonymousClass0B7 A04() {
        return this.A00;
    }

    @NonNull
    public AnonymousClass0B7 A05() {
        return this.A00;
    }

    public AnonymousClass0B6(@NonNull AnonymousClass0B7 r1) {
        this.A00 = r1;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(A08()), Boolean.valueOf(A07()), A01(), A00(), A02());
    }

    @NonNull
    public AnonymousClass0B7 A06(int i, int i2, int i3, int i4) {
        return AnonymousClass0B7.A01;
    }
}
