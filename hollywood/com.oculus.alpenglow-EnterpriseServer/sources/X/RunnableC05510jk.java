package X;

/* renamed from: X.0jk  reason: invalid class name and case insensitive filesystem */
public class RunnableC05510jk implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.systrace.TraceListenerNotifier$1";
    public final /* synthetic */ C05520jl A00;

    public RunnableC05510jk(C05520jl r1) {
        this.A00 = r1;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            X.0jl r3 = r4.A00
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
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC05510jk.run():void");
    }
}
