package X;

/* renamed from: X.cq  reason: case insensitive filesystem */
public final class C0607cq {
    public static long A00;
    public static C0606cp A01;

    public static C0606cp A00() {
        synchronized (C0607cq.class) {
            C0606cp cpVar = A01;
            if (cpVar == null) {
                return new C0606cp();
            }
            A01 = cpVar.A02;
            cpVar.A02 = null;
            A00 -= 8192;
            return cpVar;
        }
    }

    public static void A01(C0606cp cpVar) {
        if (cpVar.A02 != null || cpVar.A03 != null) {
            throw new IllegalArgumentException();
        } else if (!cpVar.A05) {
            synchronized (C0607cq.class) {
                long j = A00 + 8192;
                if (j <= 65536) {
                    A00 = j;
                    cpVar.A02 = A01;
                    cpVar.A00 = 0;
                    cpVar.A01 = 0;
                    A01 = cpVar;
                }
            }
        }
    }
}
