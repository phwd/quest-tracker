package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Cj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0149Cj extends Z2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5638xj f7833a;
    public final /* synthetic */ C0515Ij b;

    public C0149Cj(C0515Ij ij, C5638xj xjVar) {
        this.b = ij;
        this.f7833a = xjVar;
    }

    @Override // defpackage.Z2
    public void b(Tab tab) {
        Integer num;
        C0515Ij ij;
        Tab tab2;
        C0515Ij ij2 = this.b;
        if (ij2.N.get() == null) {
            num = null;
        } else {
            num = Integer.valueOf(((C3818n01) ((AbstractC2451f01) this.b.N.get())).c.T);
        }
        C0515Ij.l(ij2, tab, num);
        if (tab != null && (tab2 = (ij = this.b).a0) != tab) {
            if (tab2 != null) {
                tab2.I(ij.L);
            }
            C0515Ij ij3 = this.b;
            ij3.a0 = tab;
            tab.A(ij3.L);
            this.f7833a.k();
        }
    }
}
