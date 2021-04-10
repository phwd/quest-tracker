package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yR  reason: invalid class name */
public final class AnonymousClass0yR<T> extends AbstractC09150yk<T> {
    public static final long serialVersionUID = 0;
    public final T reference;

    @Override // X.AbstractC09150yk
    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof AnonymousClass0yR) {
            return this.reference.equals(((AnonymousClass0yR) obj).reference);
        }
        return false;
    }

    @Override // X.AbstractC09150yk
    public final int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // X.AbstractC09150yk
    public final String toString() {
        return "Optional.of(" + ((Object) this.reference) + ")";
    }

    public AnonymousClass0yR(T t) {
        this.reference = t;
    }
}
