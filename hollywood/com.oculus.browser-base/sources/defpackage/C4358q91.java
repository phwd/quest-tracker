package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: q91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4358q91 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I91 f11120a;

    public C4358q91(I91 i91) {
        this.f11120a = i91;
    }

    @Override // defpackage.AbstractC5783ya1
    public void B(Tab tab, int i, int i2) {
        if (!(((AbstractC0246Ea1) this.f11120a.i).c.d() instanceof I71)) {
            I91 i91 = this.f11120a;
            int y = i91.g.y(i);
            int y2 = this.f11120a.g.y(i2);
            if ((((AbstractC0246Ea1) i91.i).c.d() instanceof UK) && i91.i(y2) && i91.i(y)) {
                i91.g.r(y2, y);
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        this.f11120a.v = -1;
        if (tab.getId() != i2) {
            int x = this.f11120a.g.x(i2);
            if (x != -1) {
                ((C4765sb0) this.f11120a.g.get(x)).b.j(AbstractC5106ub1.h, false);
            }
            int x2 = this.f11120a.g.x(tab.getId());
            if (x2 != -1) {
                ((C4765sb0) this.f11120a.g.get(x2)).b.j(AbstractC5106ub1.h, true);
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        AbstractC3568la1 d;
        int i;
        I91 i91 = this.f11120a;
        I91.a(i91, tab, !i91.s);
        if (AbstractC4772sd1.a()) {
            ((AbstractC0246Ea1) this.f11120a.i).i().x(AbstractC1160Ta1.e(((AbstractC0246Ea1) this.f11120a.i).i(), tab.getId()), 3);
        }
        if (I91.b.containsKey(Integer.valueOf(tab.getId()))) {
            int intValue = ((Integer) I91.b.get(Integer.valueOf(tab.getId()))).intValue();
            if (intValue == 0) {
                AbstractC3535lK0.a("TabStrip.UndoCloseTab");
            } else if (intValue == 2) {
                AbstractC3535lK0.a("GridTabSwitch.UndoCloseTab");
            } else if (intValue == 3) {
                AbstractC3535lK0.a("GridTabSwitcher.UndoCloseTabGroup");
            }
            I91.b.remove(Integer.valueOf(tab.getId()));
        }
        I91 i912 = this.f11120a;
        if (i912.s && (i = (d = ((AbstractC0246Ea1) i912.i).c.d()).i(tab)) != -1 && this.f11120a.g(tab.getId()).size() != 1 && i < this.f11120a.g.size()) {
            Tab tabAt = d.getTabAt(i);
            int y = this.f11120a.g.y(i);
            this.f11120a.o(y, C4384qI0.a(tabAt), ((C4765sb0) this.f11120a.g.get(y)).b.h(AbstractC5106ub1.h), false, false);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        if (this.f11120a.g.x(tab.getId()) != -1) {
            K91 k91 = this.f11120a.g;
            k91.s(k91.x(tab.getId()));
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        if (this.f11120a.g.x(tab.getId()) != -1) {
            tab.I(this.f11120a.B);
            K91 k91 = this.f11120a.g;
            k91.s(k91.x(tab.getId()));
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        AbstractC3568la1 d;
        int i3;
        I91 i91 = this.f11120a;
        if (((AbstractC0246Ea1) i91.i).h) {
            I91.a(i91, tab, !i91.s);
            if (i == 3) {
                I91 i912 = this.f11120a;
                if (i912.s && (i3 = (d = ((AbstractC0246Ea1) i912.i).c.d()).i(tab)) != -1) {
                    Tab tabAt = d.getTabAt(i3);
                    int y = this.f11120a.g.y(i3);
                    if (this.f11120a.g.x(tabAt.getId()) == y) {
                        this.f11120a.o(y, C4384qI0.a(tabAt), ((C4765sb0) this.f11120a.g.get(y)).b.h(AbstractC5106ub1.h), false, false);
                    }
                }
            }
        }
    }
}
