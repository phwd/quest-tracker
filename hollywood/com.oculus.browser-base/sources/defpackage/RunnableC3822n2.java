package defpackage;

import android.view.View;

/* renamed from: n2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3822n2 implements Runnable {
    public C4335q2 F;
    public final /* synthetic */ C4676s2 G;

    public RunnableC3822n2(C4676s2 s2Var, C4335q2 q2Var) {
        this.G = s2Var;
        this.F = q2Var;
    }

    public void run() {
        AbstractC4275pi0 pi0;
        C4616ri0 ri0 = this.G.H;
        if (!(ri0 == null || (pi0 = ri0.f) == null)) {
            pi0.b(ri0);
        }
        View view = (View) this.G.M;
        if (!(view == null || view.getWindowToken() == null || !this.F.f())) {
            this.G.Y = this.F;
        }
        this.G.a0 = null;
    }
}
