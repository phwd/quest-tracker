package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Vh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1302Vh0 {

    /* renamed from: a  reason: collision with root package name */
    public Tab f9100a;
    public C0936Ph0 b;
    public final AbstractC1404Xa1 c;

    public C1302Vh0(Tab tab) {
        C1241Uh0 uh0 = new C1241Uh0(this);
        this.c = uh0;
        this.f9100a = tab;
        tab.A(uh0);
        a();
    }

    public final void a() {
        C0936Ph0 ph0 = this.b;
        if (ph0 != null) {
            ph0.j(this.f9100a.l());
        } else if (this.f9100a.l() != null) {
            this.b = new C0936Ph0(this.f9100a.l(), this);
        }
    }
}
