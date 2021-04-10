package X;

/* renamed from: X.cj  reason: case insensitive filesystem */
public final class C0600cj extends Thread {
    public static final String __redex_internal_original_name = "okio.AsyncTimeout$Watchdog";

    public C0600cj() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        if ((r6 instanceof X.AnonymousClass31) != false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0052, code lost:
        if ((r6 instanceof X.AnonymousClass34) == false) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        r3 = ((X.AnonymousClass34) r6).A00;
        r2 = X.cI.CANCEL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
        if (X.cS.A00(r3, r2) == false) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        r3.A07.A03(r3.A06, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        r6 = (X.AnonymousClass31) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r6.A00.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0072, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0077, code lost:
        if (r4.getCause() == null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008b, code lost:
        r3 = X.C0604cn.A00;
        r2 = java.util.logging.Level.WARNING;
        r1 = new java.lang.StringBuilder();
        r1.append("Failed to close timed out socket ");
        r1.append(r6.A00);
        r3.log(r2, r1.toString(), (java.lang.Throwable) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a6, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a7, code lost:
        r3 = X.C0604cn.A00;
        r2 = java.util.logging.Level.WARNING;
        r1 = new java.lang.StringBuilder();
        r1.append("Failed to close timed out socket ");
        r1.append(r6.A00);
        r3.log(r2, r1.toString(), (java.lang.Throwable) r4);
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x004a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0600cj.run():void");
    }
}
