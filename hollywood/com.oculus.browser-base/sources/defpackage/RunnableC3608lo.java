package defpackage;

/* renamed from: lo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3608lo implements Runnable {
    public final C5653xo F;

    public RunnableC3608lo(C5653xo xoVar) {
        this.F = xoVar;
    }

    public void run() {
        C5653xo xoVar = this.F;
        if (xoVar.o || xoVar.q) {
            AbstractC3364kK0.g("Android.ChildProcessLauncher.OnServiceConnectedTimedOutResult", 0, 3);
        } else if (xoVar.D) {
            AbstractC3364kK0.g("Android.ChildProcessLauncher.OnServiceConnectedTimedOutResult", 1, 3);
        } else {
            AbstractC3364kK0.g("Android.ChildProcessLauncher.OnServiceConnectedTimedOutResult", 2, 3);
            AbstractC1220Ua0.f("ChildProcessConn", "Fallback to " + xoVar.h, new Object[0]);
            C5653xo.d = true;
            ServiceConnectionC2244dp dpVar = (ServiceConnectionC2244dp) xoVar.w;
            boolean z = dpVar.h;
            boolean z2 = ((ServiceConnectionC2244dp) xoVar.x).h;
            boolean z3 = ((ServiceConnectionC2244dp) xoVar.y).h;
            dpVar.b();
            ((ServiceConnectionC2244dp) xoVar.x).b();
            ((ServiceConnectionC2244dp) xoVar.y).b();
            xoVar.c(xoVar.h);
            if (z) {
                ((ServiceConnectionC2244dp) xoVar.w).a();
            }
            if (z2) {
                ((ServiceConnectionC2244dp) xoVar.x).a();
            }
            if (z3) {
                ((ServiceConnectionC2244dp) xoVar.y).a();
            }
        }
    }
}
