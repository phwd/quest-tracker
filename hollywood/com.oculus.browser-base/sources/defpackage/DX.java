package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: DX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DX extends Y2 {
    public final /* synthetic */ FX d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DX(FX fx, C1595a3 a3Var) {
        super(a3Var);
        this.d = fx;
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        Tab tab2 = this.d.Q;
        if (tab2 != null && tab2.isInitialized()) {
            C41.l(this.d.Q).O = null;
        }
        FX fx = this.d;
        fx.Q = tab;
        fx.k();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.d.k();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        this.d.Q = null;
    }
}
