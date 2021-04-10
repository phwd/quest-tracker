package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: u91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5039u91 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0124Ca1 f11393a;
    public final AbstractC5783ya1 b;
    public final RK c;
    public final /* synthetic */ I91 d;

    public C5039u91(I91 i91, AbstractC0124Ca1 ca1) {
        this.d = i91;
        this.f11393a = ca1;
        J71 j71 = new J71(this);
        this.b = j71;
        K71 k71 = new K71(this);
        this.c = k71;
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
        ea1.c.a(j71);
        ((I71) ea1.c.g(false)).g.b(k71);
        ((I71) ea1.c.g(true)).g.b(k71);
    }

    public void a(int i) {
        AbstractC3842n81.b().edit().remove(String.valueOf(i)).apply();
    }

    public void b(int i, String str) {
        AbstractC3842n81.b().edit().putString(String.valueOf(i), str).apply();
    }

    public void c(Tab tab, String str) {
        I91 i91 = this.d;
        if (i91.s) {
            int x = this.d.g.x(AbstractC3842n81.a(i91.i, tab).getId());
            if (x != -1) {
                ((C4765sb0) this.d.g.get(x)).b.m(AbstractC5106ub1.g, str);
                this.d.k(C4384qI0.a(tab), ((C4765sb0) this.d.g.get(x)).b);
                if (AbstractC4772sd1.d()) {
                    this.d.j(C4384qI0.a(tab), ((C4765sb0) this.d.g.get(x)).b);
                }
            }
        }
    }
}
