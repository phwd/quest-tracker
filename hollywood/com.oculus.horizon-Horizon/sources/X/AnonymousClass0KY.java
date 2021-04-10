package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0KY  reason: invalid class name */
public final class AnonymousClass0KY {
    public static void A01(@Nullable Throwable th) {
        Class cls = Error.class;
        if (!cls.isInstance(th)) {
            cls = RuntimeException.class;
            if (!cls.isInstance(th)) {
                return;
            }
        }
        throw ((Throwable) cls.cast(th));
    }

    public static void A00(Throwable th) {
        A01(th);
        throw new RuntimeException(th);
    }
}
