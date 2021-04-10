package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Im  reason: invalid class name and case insensitive filesystem */
public final class C00770Im {
    public static void A01(@Nullable Throwable th) {
        if (Error.class.isInstance(th)) {
            throw ((Throwable) Error.class.cast(th));
        } else if (RuntimeException.class.isInstance(th)) {
            throw ((Throwable) RuntimeException.class.cast(th));
        }
    }

    public static void A00(Throwable th) {
        A01(th);
        throw new RuntimeException(th);
    }
}
