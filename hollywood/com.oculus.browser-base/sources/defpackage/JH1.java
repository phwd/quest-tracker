package defpackage;

/* renamed from: JH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class JH1 implements Runnable {
    public final /* synthetic */ OI1 F;
    public final /* synthetic */ C4723sH1 G;

    public JH1(C4723sH1 sh1, OI1 oi1) {
        this.G = sh1;
        this.F = oi1;
    }

    public final void run() {
        synchronized (this.G.b) {
            AbstractC0899Os0 os0 = this.G.c;
            if (os0 != null) {
                os0.b(this.F.b());
            }
        }
    }
}
