package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1QL  reason: invalid class name */
public final class AnonymousClass1QL<T> extends AnonymousClass1QO<T> {
    public static final long serialVersionUID = 0;
    public final T reference;

    @Override // X.AnonymousClass1QO
    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof AnonymousClass1QL) {
            return this.reference.equals(((AnonymousClass1QL) obj).reference);
        }
        return false;
    }

    @Override // X.AnonymousClass1QO
    public final int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // X.AnonymousClass1QO
    public final String toString() {
        StringBuilder sb = new StringBuilder("Optional.of(");
        sb.append((Object) this.reference);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass1QL(T t) {
        this.reference = t;
    }
}
