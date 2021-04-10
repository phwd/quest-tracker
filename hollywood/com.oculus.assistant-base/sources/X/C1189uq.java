package X;

import sun.misc.Unsafe;

/* renamed from: X.uq  reason: case insensitive filesystem */
public final class C1189uq extends AbstractC0375Ul {
    public static final long A00;
    public static final long A01;
    public static final long A02;
    public static final long A03;
    public static final long A04;
    public static final Unsafe A05;

    @Override // X.AbstractC0375Ul
    public final void A00(Us us, Us us2) {
        A05.putObject(us, A03, us2);
    }

    @Override // X.AbstractC0375Ul
    public final void A01(Us us, Thread thread) {
        A05.putObject(us, A04, thread);
    }

    @Override // X.AbstractC0375Ul
    public final boolean A02(SH sh, C0379Up up, C0379Up up2) {
        return A05.compareAndSwapObject(sh, A00, up, up2);
    }

    @Override // X.AbstractC0375Ul
    public final boolean A03(SH sh, Us us, Us us2) {
        return A05.compareAndSwapObject(sh, A02, us, us2);
    }

    @Override // X.AbstractC0375Ul
    public final boolean A04(SH sh, Object obj, Object obj2) {
        return A05.compareAndSwapObject(sh, A01, obj, obj2);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
    static {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1189uq.<clinit>():void");
    }
}
