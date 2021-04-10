package X;

public final class L5 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.systrace.TraceListenerNotifier$1";
    public final /* synthetic */ L6 A00;

    public L5(L6 l6) {
        this.A00 = l6;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            X.L6 r3 = r4.A00
            java.lang.Object r2 = r3.A01
            monitor-enter(r2)
            r0 = 100
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x000b }
            goto L_0x0012
        L_0x000b:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0017 }
            r0.interrupt()     // Catch:{ all -> 0x0017 }
        L_0x0012:
            r3.A00()     // Catch:{ all -> 0x0017 }
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return
        L_0x0017:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.L5.run():void");
    }
}
