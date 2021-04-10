package defpackage;

/* renamed from: lH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC3527lH1 implements Runnable {
    public final /* synthetic */ OI1 F;
    public final /* synthetic */ C1819bH1 G;

    public RunnableC3527lH1(C1819bH1 bh1, OI1 oi1) {
        this.G = bh1;
        this.F = oi1;
    }

    public final void run() {
        synchronized (this.G.b) {
            AbstractC0716Ls0 ls0 = this.G.c;
            if (ls0 != null) {
                ls0.a(this.F);
            }
        }
    }
}
