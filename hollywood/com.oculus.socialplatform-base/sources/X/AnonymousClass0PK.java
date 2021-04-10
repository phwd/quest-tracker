package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0PK  reason: invalid class name */
public enum AnonymousClass0PK {
    LOW,
    MEDIUM,
    HIGH;

    @Nullable
    public static AnonymousClass0PK getHigherPriority(@Nullable AnonymousClass0PK r2, @Nullable AnonymousClass0PK r3) {
        if (r2 == null || (r3 != null && r2.ordinal() <= r3.ordinal())) {
            return r3;
        }
        return r2;
    }
}
