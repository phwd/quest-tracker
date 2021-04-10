package X;

/* renamed from: X.0hB  reason: invalid class name */
public final class AnonymousClass0hB extends Thread {
    public static final String __redex_internal_original_name = "okio.AsyncTimeout$Watchdog";

    public AnonymousClass0hB() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0015, code lost:
        r1.timedOut();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r3 = this;
        L_0x0000:
            java.lang.Class<X.0Of> r2 = X.AnonymousClass0Of.class
            monitor-enter(r2)     // Catch:{ InterruptedException -> 0x0000 }
            X.0Of r1 = X.AnonymousClass0Of.awaitTimeout()     // Catch:{ all -> 0x001a }
            if (r1 != 0) goto L_0x000b
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            goto L_0x0000
        L_0x000b:
            X.0Of r0 = X.AnonymousClass0Of.head     // Catch:{ all -> 0x001a }
            if (r1 != r0) goto L_0x0014
            r0 = 0
            X.AnonymousClass0Of.head = r0     // Catch:{ all -> 0x001a }
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            goto L_0x0019
        L_0x0014:
            monitor-exit(r2)     // Catch:{ all -> 0x001a }
            r1.timedOut()
            goto L_0x0000
        L_0x0019:
            return
        L_0x001a:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0hB.run():void");
    }
}
