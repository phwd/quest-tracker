package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: Bj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0088Bj extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5638xj f7750a;
    public final /* synthetic */ C0515Ij b;

    public C0088Bj(C0515Ij ij, C5638xj xjVar) {
        this.b = ij;
        this.f7750a = xjVar;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        this.f7750a.k();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.f7750a.k();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        C0515Ij ij = this.b;
        if (ij.a0 == tab) {
            ij.a0 = null;
            this.f7750a.k();
        }
    }
}
