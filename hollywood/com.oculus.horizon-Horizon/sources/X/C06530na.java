package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0na  reason: invalid class name and case insensitive filesystem */
public final class C06530na extends AnonymousClass0W8<Object> {
    public static final C06530na A00 = new C06530na();
    public static final long serialVersionUID = 0;

    @Override // X.AnonymousClass0W8
    public final boolean A02() {
        return false;
    }

    @Override // X.AnonymousClass0W8
    public final boolean equals(@Nullable Object obj) {
        return obj == this;
    }

    @Override // X.AnonymousClass0W8
    public final int hashCode() {
        return 1502476572;
    }

    @Override // X.AnonymousClass0W8
    public final String toString() {
        return "Optional.absent()";
    }

    @Override // X.AnonymousClass0W8
    public final Object A01() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }
}
