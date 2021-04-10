package defpackage;

import android.view.Window;

/* renamed from: i8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2986i8 implements AbstractC1886bj0 {
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 F;

    public C2986i8(LayoutInflater$Factory2C3156j8 j8Var) {
        this.F = j8Var;
    }

    @Override // defpackage.AbstractC1886bj0
    public void a(C4616ri0 ri0, boolean z) {
        C4616ri0 k = ri0.k();
        boolean z2 = k != ri0;
        LayoutInflater$Factory2C3156j8 j8Var = this.F;
        if (z2) {
            ri0 = k;
        }
        C2815h8 B = j8Var.B(ri0);
        if (B == null) {
            return;
        }
        if (z2) {
            this.F.s(B.f10049a, B, k);
            this.F.u(B, true);
            return;
        }
        this.F.u(B, z);
    }

    @Override // defpackage.AbstractC1886bj0
    public boolean b(C4616ri0 ri0) {
        Window.Callback E;
        if (ri0 != null) {
            return true;
        }
        LayoutInflater$Factory2C3156j8 j8Var = this.F;
        if (!j8Var.j0 || (E = j8Var.E()) == null || this.F.v0) {
            return true;
        }
        E.onMenuOpened(108, ri0);
        return true;
    }
}
