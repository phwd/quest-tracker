package X;

import javax.annotation.Nullable;

/* renamed from: X.cd  reason: case insensitive filesystem */
public final class C0314cd {
    public static long A00;
    @Nullable
    public static C0315ce A01;

    public static C0315ce A00() {
        synchronized (C0314cd.class) {
            C0315ce ceVar = A01;
            if (ceVar == null) {
                return new C0315ce();
            }
            A01 = ceVar.A02;
            ceVar.A02 = null;
            A00 -= 8192;
            return ceVar;
        }
    }

    public static void A01(C0315ce ceVar) {
        if (ceVar.A02 != null || ceVar.A03 != null) {
            throw new IllegalArgumentException();
        } else if (!ceVar.A05) {
            synchronized (C0314cd.class) {
                long j = A00 + 8192;
                if (j <= 65536) {
                    A00 = j;
                    ceVar.A02 = A01;
                    ceVar.A00 = 0;
                    ceVar.A01 = 0;
                    A01 = ceVar;
                }
            }
        }
    }
}
