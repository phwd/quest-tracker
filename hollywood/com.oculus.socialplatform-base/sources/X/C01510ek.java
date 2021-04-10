package X;

import sun.misc.Unsafe;

/* renamed from: X.0ek  reason: invalid class name and case insensitive filesystem */
public final class C01510ek extends AnonymousClass110 {
    public static final long A00;
    public static final long A01;
    public static final long A02;
    public static final long A03;
    public static final long A04;
    public static final Unsafe A05;

    @Override // X.AnonymousClass110
    public final void A00(AnonymousClass117 r4, AnonymousClass117 r5) {
        A05.putObject(r4, A03, r5);
    }

    @Override // X.AnonymousClass110
    public final void A01(AnonymousClass117 r4, Thread thread) {
        A05.putObject(r4, A04, thread);
    }

    @Override // X.AnonymousClass110
    public final boolean A02(AnonymousClass0BR<?> r7, AnonymousClass114 r8, AnonymousClass114 r9) {
        return A05.compareAndSwapObject(r7, A00, r8, r9);
    }

    @Override // X.AnonymousClass110
    public final boolean A03(AnonymousClass0BR<?> r7, AnonymousClass117 r8, AnonymousClass117 r9) {
        return A05.compareAndSwapObject(r7, A02, r8, r9);
    }

    @Override // X.AnonymousClass110
    public final boolean A04(AnonymousClass0BR<?> r7, Object obj, Object obj2) {
        return A05.compareAndSwapObject(r7, A01, obj, obj2);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
    static {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01510ek.<clinit>():void");
    }
}
