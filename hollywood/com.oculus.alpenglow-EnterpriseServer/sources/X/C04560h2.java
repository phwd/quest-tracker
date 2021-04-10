package X;

import javax.annotation.Nullable;

/* renamed from: X.0h2  reason: invalid class name and case insensitive filesystem */
public final class C04560h2 {
    public static long A00;
    @Nullable
    public static C04570h3 A01;

    public static C04570h3 A00() {
        synchronized (C04560h2.class) {
            C04570h3 r4 = A01;
            if (r4 == null) {
                return new C04570h3();
            }
            A01 = r4.A00;
            r4.A00 = null;
            A00 -= 8192;
            return r4;
        }
    }

    public static void A01(C04570h3 r6) {
        if (r6.A00 != null || r6.A03 != null) {
            throw new IllegalArgumentException();
        } else if (!r6.A05) {
            synchronized (C04560h2.class) {
                long j = A00 + 8192;
                if (j <= 65536) {
                    A00 = j;
                    r6.A00 = A01;
                    r6.A01 = 0;
                    r6.A02 = 0;
                    A01 = r6;
                }
            }
        }
    }
}
