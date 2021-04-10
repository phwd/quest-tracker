package defpackage;

import J.N;
import com.oculus.browser.PanelApp;
import com.oculus.browser.PwaAppListFetcher;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: jw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3291jw0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PanelApp f10250a;

    public C3291jw0(PanelApp panelApp) {
        this.f10250a = panelApp;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void D(Tab tab, float f) {
        StringBuilder i = AbstractC2531fV.i("onLoadProgressChanged ");
        i.append(tab.getId());
        i.append(" ");
        i.append(this.f10250a.f9704J);
        i.append(" ");
        Tab tab2 = this.f10250a.N;
        i.append(tab2 != null ? Integer.valueOf(tab2.getId()) : "");
        i.toString();
        N.MkhJ$3DK(this.f10250a.f9704J, (double) f);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        tab.getId();
        this.f10250a.w();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        tab.getId();
        String str = "Page " + tab.getUrl() + " failed, error = " + i;
        this.f10250a.w();
        this.f10250a.f(tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        tab.getId();
        this.f10250a.w();
        PwaAppListFetcher.F.f();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        tab.getId();
        this.f10250a.t();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        tab.getId();
        this.f10250a.t();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        tab.getId();
        this.f10250a.w();
        this.f10250a.f(tab);
    }
}
