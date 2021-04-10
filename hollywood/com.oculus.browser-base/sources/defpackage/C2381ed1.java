package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: ed1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2381ed1 extends VK {
    public final /* synthetic */ C3919nd1 F;

    public C2381ed1(C3919nd1 nd1) {
        this.F = nd1;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        C3919nd1 nd1 = this.F;
        nd1.t = true;
        nd1.w = tabModel.index();
        AbstractC3568la1 d = ((AbstractC0246Ea1) this.F.f).c.d();
        this.F.e.j(O81.b, d.a());
        I61 i61 = this.F.p;
        if (i61 != null) {
            i61.c.h(false);
        }
        if (this.F.e.h(O81.f8603a)) {
            C3919nd1 nd12 = this.F;
            ((C1349Wc1) nd12.d).t(d, false, nd12.y);
            if (tabModel.a() && tabModel.getCount() == 0 && tabModel2.getCount() > 0) {
                tabModel2.getTabAt(tabModel2.index()).N(0);
            }
            this.F.p();
        }
    }
}
