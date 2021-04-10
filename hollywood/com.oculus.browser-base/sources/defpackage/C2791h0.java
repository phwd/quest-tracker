package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: h0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2791h0 extends Y2 {
    public final /* synthetic */ C1343Wa1 d;
    public final /* synthetic */ C2962i0 e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2791h0(C2962i0 i0Var, C1595a3 a3Var, C1343Wa1 wa1) {
        super(a3Var);
        this.e = i0Var;
        this.d = wa1;
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        TabImpl tabImpl = this.e.G;
        if (tabImpl != tab) {
            TabImpl tabImpl2 = (TabImpl) tab;
            if (tabImpl != null) {
                this.d.b.c(tabImpl);
            }
            if (tabImpl2 != null) {
                this.d.b.b(tabImpl2);
            }
            this.e.G = tabImpl2;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.e.G.w(this.d.f9156a.b());
    }
}
