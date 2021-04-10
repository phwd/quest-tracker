package X;

/* renamed from: X.bH  reason: case insensitive filesystem */
public final class C0519bH extends AbstractC0890oC {
    public static C0519bH A00;
    public static final Object A01 = new Object();

    public C0519bH() {
        super(null);
    }

    @Override // X.AbstractC0890oC
    public final long A04(long j, long j2, boolean z) {
        return j2;
    }

    @Override // X.AbstractC0890oC
    public final String A05(long j, String str, boolean z) {
        return str;
    }

    @Override // X.AbstractC0890oC
    public final void A06(long j, EnumC0165Fq fq) {
    }

    @Override // X.AbstractC0890oC
    public final boolean A08(long j, boolean z, boolean z2) {
        return z;
    }

    public static synchronized C0519bH A00() {
        C0519bH bHVar;
        synchronized (C0519bH.class) {
            bHVar = A00;
            if (bHVar == null) {
                synchronized (A01) {
                    bHVar = A00;
                    if (bHVar == null) {
                        bHVar = new C0519bH();
                        A00 = bHVar;
                    }
                }
            }
        }
        return bHVar;
    }
}
