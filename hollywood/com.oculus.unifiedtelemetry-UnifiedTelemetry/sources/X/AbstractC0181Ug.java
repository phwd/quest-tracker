package X;

import com.google.common.base.Objects;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.Ug  reason: case insensitive filesystem */
public abstract class AbstractC0181Ug<E> {
    public abstract int A00();

    public abstract E A01();

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof AbstractC0181Ug)) {
            return false;
        }
        AbstractC0181Ug ug = (AbstractC0181Ug) obj;
        if (A00() != ug.A00() || !Objects.equal(A01(), ug.A01())) {
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
        if (A00 == 1) {
            return valueOf;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        sb.append(" x ");
        sb.append(A00);
        return sb.toString();
    }
}
