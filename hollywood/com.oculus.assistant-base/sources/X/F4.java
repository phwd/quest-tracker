package X;

public final class F4 extends Thread {
    public static final String __redex_internal_original_name = "com.facebook.jni.DestructorThread$1";

    public F4() {
        super("HybridData DestructorThread");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|(3:3|(1:5)|8)(1:7)|6) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:6:0x0030, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r5 = this;
        L_0x0000:
            java.lang.ref.ReferenceQueue r0 = X.F8.A02     // Catch:{ InterruptedException -> 0x0000 }
            java.lang.ref.Reference r4 = r0.remove()     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r4 = (X.F5) r4     // Catch:{ InterruptedException -> 0x0000 }
            r4.destruct()     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r0 = r4.A01     // Catch:{ InterruptedException -> 0x0000 }
            if (r0 != 0) goto L_0x0030
            X.F7 r0 = X.F8.A01     // Catch:{ InterruptedException -> 0x0000 }
            java.util.concurrent.atomic.AtomicReference r1 = r0.A00     // Catch:{ InterruptedException -> 0x0000 }
            r0 = 0
            java.lang.Object r3 = r1.getAndSet(r0)     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r3 = (X.F5) r3     // Catch:{ InterruptedException -> 0x0000 }
        L_0x001a:
            if (r3 == 0) goto L_0x0030
            X.F5 r2 = r3.A00     // Catch:{ InterruptedException -> 0x0000 }
            X.F6 r0 = X.F8.A00     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r1 = r0.A00     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r0 = r1.A00     // Catch:{ InterruptedException -> 0x0000 }
            r3.A00 = r0     // Catch:{ InterruptedException -> 0x0000 }
            r1.A00 = r3     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r0 = r3.A00     // Catch:{ InterruptedException -> 0x0000 }
            r0.A01 = r3     // Catch:{ InterruptedException -> 0x0000 }
            r3.A01 = r1     // Catch:{ InterruptedException -> 0x0000 }
            r3 = r2
            goto L_0x001a
        L_0x0030:
            X.F5 r1 = r4.A00     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r0 = r4.A01     // Catch:{ InterruptedException -> 0x0000 }
            r1.A01 = r0     // Catch:{ InterruptedException -> 0x0000 }
            X.F5 r0 = r4.A01     // Catch:{ InterruptedException -> 0x0000 }
            r0.A00 = r1     // Catch:{ InterruptedException -> 0x0000 }
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: X.F4.run():void");
    }
}
