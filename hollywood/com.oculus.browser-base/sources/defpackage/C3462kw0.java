package defpackage;

import com.oculus.browser.PanelApp;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: kw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3462kw0 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PanelApp f10316a;

    public C3462kw0(PanelApp panelApp) {
        this.f10316a = panelApp;
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        this.f10316a.t();
    }

    @Override // defpackage.AbstractC5783ya1
    public void B(Tab tab, int i, int i2) {
        tab.getId();
        this.f10316a.t();
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        tab.getId();
        this.f10316a.t();
    }

    @Override // defpackage.AbstractC5783ya1
    public void E() {
        this.f10316a.t();
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        this.f10316a.selectTab(tab.getId());
    }
}
