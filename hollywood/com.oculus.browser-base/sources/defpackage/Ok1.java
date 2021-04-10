package defpackage;

import android.view.View;

/* renamed from: Ok1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ok1 extends AbstractC2059ck {
    public View.OnLayoutChangeListener F;
    public final /* synthetic */ Uk1 G;

    public Ok1(Uk1 uk1) {
        this.G = uk1;
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        if (this.G.L.getBackground() == null) {
            Uk1 uk1 = this.G;
            Uk1.h(uk1, Uk1.g(uk1));
        } else if (this.F == null) {
            Nk1 nk1 = new Nk1(this);
            this.F = nk1;
            this.G.L.addOnLayoutChangeListener(nk1);
        }
    }
}
