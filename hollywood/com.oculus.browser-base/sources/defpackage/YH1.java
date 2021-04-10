package defpackage;

/* renamed from: YH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class YH1 implements Runnable {
    public final /* synthetic */ OI1 F;
    public final /* synthetic */ RH1 G;

    public YH1(RH1 rh1, OI1 oi1) {
        this.G = rh1;
        this.F = oi1;
    }

    public final void run() {
        synchronized (this.G.b) {
            AbstractC1143Ss0 ss0 = this.G.c;
            if (ss0 != null) {
                ss0.a(this.F.c());
            }
        }
    }
}
