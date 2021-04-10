package X;

import sun.misc.Unsafe;

public final class UJ extends AS {
    public static final long A00;
    public static final long A01;
    public static final long A02;
    public static final long A03;
    public static final long A04;
    public static final Unsafe A05;

    @Override // X.AS
    public final void A00(B0 b0, B0 b02) {
        A05.putObject(b0, A03, b02);
    }

    @Override // X.AS
    public final void A01(B0 b0, Thread thread) {
        A05.putObject(b0, A04, thread);
    }

    @Override // X.AS
    public final boolean A02(AbstractC00136k<?> r7, C0048Al al, C0048Al al2) {
        return A05.compareAndSwapObject(r7, A00, al, al2);
    }

    @Override // X.AS
    public final boolean A03(AbstractC00136k<?> r7, B0 b0, B0 b02) {
        return A05.compareAndSwapObject(r7, A02, b0, b02);
    }

    @Override // X.AS
    public final boolean A04(AbstractC00136k<?> r7, Object obj, Object obj2) {
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
        throw new UnsupportedOperationException("Method not decompiled: X.UJ.<clinit>():void");
    }
}
