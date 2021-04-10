package defpackage;

import com.oculus.browser.PanelApp;

/* renamed from: iw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3120iw0 implements J00 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PanelApp f10175a;

    public C3120iw0(PanelApp panelApp) {
        this.f10175a = panelApp;
    }

    @Override // defpackage.J00
    public void a() {
        ((AbstractC0246Ea1) this.f10175a.P()).l(true).g(false, false);
    }

    @Override // defpackage.J00
    public boolean b() {
        return ((AbstractC0246Ea1) this.f10175a.P()).l(true).getCount() > 0;
    }

    @Override // defpackage.J00
    public boolean isActiveModel() {
        return b();
    }
}
