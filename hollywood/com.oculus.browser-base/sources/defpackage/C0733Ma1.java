package defpackage;

import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Ma1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0733Ma1 extends C1078Rq0 implements AbstractC0612Ka1 {

    /* renamed from: J  reason: collision with root package name */
    public AbstractC0124Ca1 f8485J;
    public boolean K;

    public C0733Ma1(AbstractC0956Pq0 pq0) {
        ((C1078Rq0) pq0).l(new C0673La1(this));
    }

    @Override // defpackage.AbstractC0612Ka1
    public void b() {
        this.K = true;
        m(((AbstractC0246Ea1) this.f8485J).i().b());
    }

    @Override // defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        Profile b = tabModel.b();
        if (b != null) {
            m(b);
        }
    }

    @Override // defpackage.AbstractC0612Ka1
    public void e() {
    }

    @Override // defpackage.AbstractC0612Ka1
    public void f(Tab tab, int i) {
    }
}
