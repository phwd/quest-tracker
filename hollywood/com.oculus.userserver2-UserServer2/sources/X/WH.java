package X;

import javax.annotation.Nullable;

public final class WH {
    public static long A00;
    @Nullable
    public static WI A01;

    public static WI A00() {
        synchronized (WH.class) {
            WI wi = A01;
            if (wi == null) {
                return new WI();
            }
            A01 = wi.A02;
            wi.A02 = null;
            A00 -= 8192;
            return wi;
        }
    }

    public static void A01(WI wi) {
        if (wi.A02 != null || wi.A03 != null) {
            throw new IllegalArgumentException();
        } else if (!wi.A05) {
            synchronized (WH.class) {
                long j = A00 + 8192;
                if (j <= 65536) {
                    A00 = j;
                    wi.A02 = A01;
                    wi.A00 = 0;
                    wi.A01 = 0;
                    A01 = wi;
                }
            }
        }
    }
}
