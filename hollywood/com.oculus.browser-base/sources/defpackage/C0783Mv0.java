package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: Mv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0783Mv0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0905Ov0 f8509a;

    public C0783Mv0(C0905Ov0 ov0) {
        this.f8509a = ov0;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.f8509a.f(null);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        if (!tab.d() && !tab.J()) {
            this.f8509a.f(tab.getUrl());
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void R(Tab tab, GURL gurl) {
        String str;
        if (GURL.k(gurl)) {
            str = "";
        } else {
            str = gurl.d();
        }
        C0905Ov0 ov0 = this.f8509a;
        ov0.c(ov0.f.a(str), str);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void h(Tab tab) {
        this.f8509a.f(tab.getUrl());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.f8509a.f(null);
    }
}
