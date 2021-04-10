package defpackage;

import android.view.Window;

/* renamed from: Y7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Y7 implements AbstractC1886bj0 {
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 F;

    public Y7(LayoutInflater$Factory2C3156j8 j8Var) {
        this.F = j8Var;
    }

    @Override // defpackage.AbstractC1886bj0
    public void a(C4616ri0 ri0, boolean z) {
        this.F.t(ri0);
    }

    @Override // defpackage.AbstractC1886bj0
    public boolean b(C4616ri0 ri0) {
        Window.Callback E = this.F.E();
        if (E == null) {
            return true;
        }
        E.onMenuOpened(108, ri0);
        return true;
    }
}
