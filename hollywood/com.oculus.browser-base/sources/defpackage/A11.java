package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: A11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A11 extends AbstractC1099Sa1 {
    public final /* synthetic */ D11 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public A11(D11 d11, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = d11;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        D11 d11 = this.I;
        if (d11.n0 && d11.Z.f(J70.e) == tab.getId() && d11.Z.h(J70.F) && d11.e0) {
            d11.R();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        if (this.I.Z.f(J70.e) != tab.getId()) {
            this.I.Y(tab);
        } else {
            this.I.Z(tab);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void j(Tab tab, int i) {
        this.I.Z(tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.I.Z(tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void s(Tab tab, int i) {
        this.I.Z(tab);
    }
}
