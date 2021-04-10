package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Ih  reason: invalid class name and case insensitive filesystem */
public final class C00730Ih {
    @CheckReturnValue
    public static boolean A01(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public static C00720Ig A00(Object obj) {
        return new C00720Ig(obj.getClass().getSimpleName());
    }
}
