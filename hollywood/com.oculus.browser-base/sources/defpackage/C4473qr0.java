package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: qr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4473qr0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4643rr0 f11167a;

    public C4473qr0(C4643rr0 rr0) {
        this.f11167a = rr0;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.f11167a.f = null;
        tab.I(this);
        V();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        this.f11167a.f = null;
        ((TabImpl) tab).P.c(this);
        V();
    }

    public final void V() {
        C4643rr0 rr0 = this.f11167a;
        rr0.f(rr0.e.f9307J == 4);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        this.f11167a.f = null;
        tab.I(this);
        V();
    }
}
