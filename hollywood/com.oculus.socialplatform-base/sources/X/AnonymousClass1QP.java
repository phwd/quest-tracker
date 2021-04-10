package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1QP  reason: invalid class name */
public final class AnonymousClass1QP extends AnonymousClass1QO<Object> {
    public static final AnonymousClass1QP A00 = new AnonymousClass1QP();
    public static final long serialVersionUID = 0;

    @Override // X.AnonymousClass1QO
    public final boolean equals(@Nullable Object obj) {
        return obj == this;
    }

    @Override // X.AnonymousClass1QO
    public final int hashCode() {
        return 1502476572;
    }

    @Override // X.AnonymousClass1QO
    public final String toString() {
        return "Optional.absent()";
    }
}
