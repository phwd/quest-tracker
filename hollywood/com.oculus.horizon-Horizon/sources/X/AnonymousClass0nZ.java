package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0nZ  reason: invalid class name */
public final class AnonymousClass0nZ<T> extends AnonymousClass0W8<T> {
    public static final long serialVersionUID = 0;
    public final T reference;

    @Override // X.AnonymousClass0W8
    public final boolean A02() {
        return true;
    }

    @Override // X.AnonymousClass0W8
    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof AnonymousClass0nZ) {
            return this.reference.equals(((AnonymousClass0nZ) obj).reference);
        }
        return false;
    }

    @Override // X.AnonymousClass0W8
    public final int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // X.AnonymousClass0W8
    public final String toString() {
        StringBuilder sb = new StringBuilder("Optional.of(");
        sb.append((Object) this.reference);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass0nZ(T t) {
        this.reference = t;
    }

    @Override // X.AnonymousClass0W8
    public final T A01() {
        return this.reference;
    }
}
