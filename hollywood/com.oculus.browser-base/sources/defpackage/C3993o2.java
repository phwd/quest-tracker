package defpackage;

import android.view.View;

/* renamed from: o2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3993o2 extends AbstractView$OnTouchListenerC2013cS {
    public final /* synthetic */ C4164p2 O;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3993o2(C4164p2 p2Var, View view, C4676s2 s2Var) {
        super(view);
        this.O = p2Var;
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public AbstractC3386kV0 b() {
        C4335q2 q2Var = this.O.H.Y;
        if (q2Var == null) {
            return null;
        }
        return q2Var.a();
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public boolean c() {
        this.O.H.n();
        return true;
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public boolean d() {
        C4676s2 s2Var = this.O.H;
        if (s2Var.a0 != null) {
            return false;
        }
        s2Var.f();
        return true;
    }
}
