package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Vj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1307Vj extends Y2 {
    public final /* synthetic */ C1551Zj d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1307Vj(C1551Zj zj, C1595a3 a3Var) {
        super(a3Var);
        this.d = zj;
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        C1551Zj zj = this.d;
        Tab tab2 = zj.a0;
        zj.a0 = tab;
        if (!(tab2 == tab || tab == null)) {
            zj.G.s();
            if (tab.isUserInteractable()) {
                zj.e();
            }
        }
        if (tab == null && ((Integer) zj.G.H).intValue() != 2) {
            zj.h();
        }
    }
}
