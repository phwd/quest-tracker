package defpackage;

/* renamed from: Yp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Yp1 implements Runnable {
    public final C2249dq1 F;
    public final Runnable G;

    public Yp1(C2249dq1 dq1, Runnable runnable) {
        this.F = dq1;
        this.G = runnable;
    }

    public void run() {
        C2249dq1 dq1 = this.F;
        Runnable runnable = this.G;
        if (dq1.c.F.contains(runnable)) {
            runnable.run();
        }
    }
}
