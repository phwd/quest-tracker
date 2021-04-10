package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: kX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3390kX0 extends AbstractC0246Ea1 {
    public C2878hX0 l;

    public C3390kX0() {
        super(null, new C3049iX0(), false);
        q(new C2707gX0(), null);
        C3219jX0 jx0 = new C3219jX0(this);
        for (TabModel tabModel : this.f7969a) {
            tabModel.n(jx0);
        }
    }

    @Override // defpackage.AbstractC0124Ca1, defpackage.AbstractC0246Ea1
    public void g(boolean z) {
        if (z) {
            C2878hX0 hx0 = this.l;
            hx0.f10075a.setString(hx0.a(), null);
        }
        super.g(z);
    }
}
