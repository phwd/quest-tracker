package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1zo  reason: invalid class name */
public final class AnonymousClass1zo {
    public static final AnonymousClass1zq A00 = new AnonymousClass1zq();
    @Nullable
    public static volatile AnonymousClass1zp A01;

    public static void A00() {
        if (A01 == null) {
            synchronized (AnonymousClass1zo.class) {
                if (A01 == null) {
                    A01 = new AnonymousClass1zp();
                }
            }
        }
    }
}
