package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: K71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K71 extends RK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5039u91 f8345a;

    public K71(C5039u91 u91) {
        this.f8345a = u91;
    }

    @Override // defpackage.RK
    public void f(Tab tab, int i) {
        C5039u91 u91 = this.f8345a;
        int h = h(tab);
        Objects.requireNonNull(u91);
        String c = AbstractC3842n81.c(h);
        Objects.requireNonNull(this.f8345a);
        String c2 = AbstractC3842n81.c(i);
        if (c != null) {
            this.f8345a.a(h(tab));
            if (c2 == null) {
                this.f8345a.b(i, c);
            }
        }
    }

    @Override // defpackage.RK
    public void g(Tab tab, int i) {
        I71 i71 = (I71) ((AbstractC0246Ea1) this.f8345a.f11393a).c.d();
        C5039u91 u91 = this.f8345a;
        int h = h(tab);
        Objects.requireNonNull(u91);
        String c = AbstractC3842n81.c(h);
        if (c != null) {
            if (i71.N(tab.getId()).size() == 2) {
                this.f8345a.a(h(tab));
            } else if (h(tab) != i) {
                this.f8345a.a(h(tab));
                this.f8345a.b(i, c);
            }
        }
    }

    public final int h(Tab tab) {
        return C5383wB.q(tab).R;
    }
}
