package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Y71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y71 extends QK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2475f81 f9253a;

    public Y71(C2475f81 f81) {
        this.f9253a = f81;
    }

    @Override // defpackage.QK
    public void a() {
        C2475f81 f81 = this.f9253a;
        f81.y = false;
        Tab j = ((AbstractC0246Ea1) f81.e).j();
        if (j != null) {
            this.f9253a.g(j.getId());
        }
    }

    @Override // defpackage.QK
    public void c(boolean z) {
        C2475f81.e(this.f9253a, 0);
        C2475f81 f81 = this.f9253a;
        f81.y = true;
        f81.g(-1);
    }
}
