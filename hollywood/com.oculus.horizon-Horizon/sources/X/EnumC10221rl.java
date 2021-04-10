package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rl  reason: invalid class name and case insensitive filesystem */
public enum EnumC10221rl {
    LOW,
    MEDIUM,
    HIGH;

    @Nullable
    public static EnumC10221rl getHigherPriority(@Nullable EnumC10221rl r2, @Nullable EnumC10221rl r3) {
        if (r2 == null || (r3 != null && r2.ordinal() <= r3.ordinal())) {
            return r3;
        }
        return r2;
    }
}
