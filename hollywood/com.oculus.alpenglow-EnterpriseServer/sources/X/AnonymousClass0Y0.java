package X;

import com.google.common.base.Objects;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0Y0  reason: invalid class name */
public abstract class AnonymousClass0Y0<E> {
    public abstract int A00();

    public abstract E A01();

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof AnonymousClass0Y0)) {
            return false;
        }
        AnonymousClass0Y0 r4 = (AnonymousClass0Y0) obj;
        if (A00() != r4.A00() || !Objects.equal(A01(), r4.A01())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        E A01 = A01();
        if (A01 == null) {
            hashCode = 0;
        } else {
            hashCode = A01.hashCode();
        }
        return hashCode ^ A00();
    }

    public final String toString() {
        String valueOf = String.valueOf(A01());
        int A00 = A00();
        if (A00 != 1) {
            return AnonymousClass006.A06(valueOf, " x ", A00);
        }
        return valueOf;
    }
}
