package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: L11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L11 extends Y2 {
    public final /* synthetic */ P11 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public L11(P11 p11, C1595a3 a3Var) {
        super(a3Var);
        this.d = p11;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        this.d.i();
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        P11 p11 = this.d;
        p11.U = tab;
        if (tab != null) {
            tab.Q();
        }
        p11.a0 = false;
        if (tab != null) {
            this.d.i();
        }
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            this.d.U = null;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        Tab tab2 = this.d.U;
        if (tab2 != null) {
            tab2.Q();
        }
        P11 p11 = this.d;
        if (p11.a0) {
            p11.i();
        }
        this.d.a0 = false;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        P11 p11 = this.d;
        p11.U = null;
        p11.a0 = false;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void s(Tab tab, int i) {
        this.d.i();
    }
}
