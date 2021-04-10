package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0KT  reason: invalid class name */
public final class AnonymousClass0KT {
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

    public static AnonymousClass0KS A00(Object obj) {
        return new AnonymousClass0KS(obj.getClass().getSimpleName());
    }
}
