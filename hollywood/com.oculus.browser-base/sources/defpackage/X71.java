package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: X71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X71 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public int f9197a = -1;
    public final /* synthetic */ C2475f81 b;

    public X71(C2475f81 f81) {
        this.b = f81;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        if (i == 2) {
            this.f9197a = tab.getId();
        }
        if (i2 != tab.getId()) {
            C5948zY0 zy0 = this.b.n.U().G;
            if (zy0 != null && zy0.b.isShown()) {
                this.b.n.U().j(this.b);
            }
        }
        if (i == 3) {
            if (tab.getId() == this.f9197a || tab.getId() == i2) {
                this.f9197a = -1;
            } else {
                C2475f81.e(this.b, 0);
            }
        }
        if (i != 0) {
            if (AbstractC4772sd1.g() && this.b.f(i2).contains(tab)) {
                return;
            }
            if (!AbstractC4772sd1.a() || !this.b.x) {
                this.b.g(tab.getId());
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void E() {
        Tab j = ((AbstractC0246Ea1) this.b.e).j();
        if (j != null) {
            AbstractC2260du0 du0 = this.b.s;
            if (du0 == null || !((AbstractC3838n70) du0).C()) {
                this.b.g(j.getId());
                StringBuilder sb = new StringBuilder();
                sb.append("TabStrip.SessionVisibility.");
                sb.append(this.b.x ? "Visible" : "Hidden");
                AbstractC3535lK0.a(sb.toString());
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        C2475f81 f81 = this.b;
        if (!f81.x) {
            f81.g(tab.getId());
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        if (this.b.x) {
            if (this.b.f(tab.getId()).size() == (AbstractC4772sd1.g() ? 1 : 2)) {
                this.b.g(-1);
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        if (i == 2 || i == 5) {
            C2475f81.e(this.b, i == 5 ? 2 : 1);
        }
        if (i == 2) {
            C2475f81 f81 = this.b;
            if (f81.x) {
                f81.b.m(AbstractC2646g81.g, Integer.valueOf(f81.f(tab.getId()).size() - 1));
            }
        }
        if (i != 2 && i != 3 && i != 11) {
            this.b.g(tab.getId());
        }
    }
}
