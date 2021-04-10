package defpackage;

import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: r61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4519r61 implements AbstractC3128iz {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3128iz f11183a;
    public final TabImpl b;

    public C4519r61(AbstractC3128iz izVar, Tab tab) {
        this.f11183a = izVar;
        this.b = (TabImpl) tab;
    }

    @Override // defpackage.AbstractC3128iz
    public boolean a() {
        return this.f11183a.a();
    }

    @Override // defpackage.AbstractC3128iz
    public void b() {
        this.f11183a.b();
    }

    @Override // defpackage.AbstractC3128iz
    public List c() {
        List c = this.f11183a.c();
        C1261Uq0 Y = this.b.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).p(this.b);
        }
        return c;
    }

    @Override // defpackage.AbstractC3128iz
    public boolean d(int i) {
        return this.f11183a.d(i);
    }

    @Override // defpackage.AbstractC3128iz
    public W70 e() {
        return this.f11183a.e();
    }

    @Override // defpackage.AbstractC3128iz
    public String f() {
        return this.f11183a.f();
    }
}
