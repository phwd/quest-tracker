package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Wj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1368Wj extends AbstractC1099Sa1 {
    public final /* synthetic */ C1551Zj I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1368Wj(C1551Zj zj, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = zj;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void C(Tab tab, boolean z) {
        if (z && tab == this.I.a0) {
            C1957c61 V = C1957c61.V(tab);
            if (V.L) {
                C1551Zj.a(this.I, V.G, V.H, V.I, V.f9583J, V.K);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void L(Tab tab, boolean z) {
        C1551Zj zj = this.I;
        if (tab == zj.a0 && !z) {
            zj.j(false);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void k(Tab tab, int i, int i2, int i3, int i4, int i5) {
        if (tab == this.I.a0 && tab.isUserInteractable() && !tab.isNativePage()) {
            C1551Zj.a(this.I, i, i2, i3, i4, i5);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        if (tab == this.I.a0 && C3372kO0.W(tab)) {
            this.I.j(false);
        }
    }
}
