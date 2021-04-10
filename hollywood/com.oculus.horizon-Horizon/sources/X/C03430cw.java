package X;

import sun.misc.Unsafe;

/* renamed from: X.0cw  reason: invalid class name and case insensitive filesystem */
public final class C03430cw extends AbstractC08240wE {
    public static final long A00;
    public static final long A01;
    public static final long A02;
    public static final long A03;
    public static final long A04;
    public static final Unsafe A05;

    @Override // X.AbstractC08240wE
    public final void A00(C08310wL r4, C08310wL r5) {
        A05.putObject(r4, A03, r5);
    }

    @Override // X.AbstractC08240wE
    public final void A01(C08310wL r4, Thread thread) {
        A05.putObject(r4, A04, thread);
    }

    @Override // X.AbstractC08240wE
    public final boolean A02(AnonymousClass06Z<?> r7, C08280wI r8, C08280wI r9) {
        return A05.compareAndSwapObject(r7, A00, r8, r9);
    }

    @Override // X.AbstractC08240wE
    public final boolean A03(AnonymousClass06Z<?> r7, C08310wL r8, C08310wL r9) {
        return A05.compareAndSwapObject(r7, A02, r8, r9);
    }

    @Override // X.AbstractC08240wE
    public final boolean A04(AnonymousClass06Z<?> r7, Object obj, Object obj2) {
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
        throw new UnsupportedOperationException("Method not decompiled: X.C03430cw.<clinit>():void");
    }
}
