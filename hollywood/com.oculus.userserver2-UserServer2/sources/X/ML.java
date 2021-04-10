package X;

import sun.misc.Unsafe;

public final class ML extends U8 {
    public static final long A00;
    public static final long A01;
    public static final long A02;
    public static final long A03;
    public static final long A04;
    public static final Unsafe A05;

    @Override // X.U8
    public final void A00(X9 x9, X9 x92) {
        A05.putObject(x9, A03, x92);
    }

    @Override // X.U8
    public final void A01(X9 x9, Thread thread) {
        A05.putObject(x9, A04, thread);
    }

    @Override // X.U8
    public final boolean A02(AnonymousClass6f<?> r7, X6 x6, X6 x62) {
        return A05.compareAndSwapObject(r7, A00, x6, x62);
    }

    @Override // X.U8
    public final boolean A03(AnonymousClass6f<?> r7, X9 x9, X9 x92) {
        return A05.compareAndSwapObject(r7, A02, x9, x92);
    }

    @Override // X.U8
    public final boolean A04(AnonymousClass6f<?> r7, Object obj, Object obj2) {
        return A05.compareAndSwapObject(r7, A01, obj, obj2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|7)) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006f, code lost:
        throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
    static {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: X.ML.<clinit>():void");
    }
}
