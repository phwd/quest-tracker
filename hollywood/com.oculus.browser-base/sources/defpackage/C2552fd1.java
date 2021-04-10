package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: fd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2552fd1 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC3406kd1 f9936a;
    public final /* synthetic */ AbstractC3577ld1 b;
    public final /* synthetic */ C3919nd1 c;

    public C2552fd1(C3919nd1 nd1, AbstractC3406kd1 kd1, AbstractC3577ld1 ld1) {
        this.c = nd1;
        this.f9936a = kd1;
        this.b = ld1;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        C3919nd1 nd1 = this.c;
        AbstractC0124Ca1 ca1 = nd1.f;
        if (((AbstractC0246Ea1) ca1).h) {
            if (i == 0 || nd1.t) {
                nd1.t = false;
                return;
            }
            if (nd1.x) {
                nd1.x = false;
                AbstractC3568la1 d = ((AbstractC0246Ea1) ca1).c.d();
                if (d instanceof I71) {
                    ((I71) d).a0(tab);
                }
                if (i == 3) {
                    C3919nd1 nd12 = this.c;
                    Objects.requireNonNull(nd12);
                    if (tab != null) {
                        Tab d2 = AbstractC1160Ta1.d(((AbstractC0246Ea1) nd12.f).i(), i2);
                        int i3 = nd12.u;
                        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) nd12.f;
                        if (i3 != ea1.e) {
                            if (AbstractC1160Ta1.e(ea1.i(), tab.getId()) == nd12.w) {
                                AbstractC3535lK0.a("MobileTabSwitched");
                            }
                            if (!AbstractC4772sd1.g() || nd12.i(tab.getId()).size() == 1) {
                                AbstractC3535lK0.a("MobileTabSwitched.GridTabSwitcher");
                            }
                        } else if (tab.getId() == nd12.v) {
                            int i4 = nd12.B;
                            if (i4 == 2) {
                                AbstractC3535lK0.a("MobileTabReturnedToCurrentTab.TabCarousel");
                            } else if (i4 == 0) {
                                AbstractC3535lK0.a("MobileTabReturnedToCurrentTab.TabGrid");
                            }
                            AbstractC3535lK0.a("MobileTabReturnedToCurrentTab");
                            AbstractC3100ip1.f10165a.d("Tabs.TabOffsetOfSwitch.GridTabSwitcher", 0);
                        } else {
                            int i5 = ((AbstractC0246Ea1) nd12.f).c.d().i(d2);
                            int i6 = ((AbstractC0246Ea1) nd12.f).c.d().i(tab);
                            if (i5 != i6) {
                                if (nd12.i(tab.getId()).size() == 1) {
                                    AbstractC3535lK0.a("MobileTabSwitched.GridTabSwitcher");
                                }
                                AbstractC3100ip1.f10165a.d("Tabs.TabOffsetOfSwitch.GridTabSwitcher", i5 - i6);
                            }
                        }
                    }
                }
            }
            if (this.c.e.h(O81.f8603a)) {
                this.c.m(tab.getId(), false);
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void E() {
        if (this.c.e.h(O81.f8603a)) {
            C3919nd1 nd1 = this.c;
            ((C1349Wc1) nd1.d).t(((AbstractC0246Ea1) nd1.f).c.d(), false, this.c.y);
            this.c.o();
            this.c.p();
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        KF0 kf0 = this.c.s;
        if (kf0 != null && kf0.d() == tab.getId()) {
            this.c.s.e();
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        if (((AbstractC0246Ea1) this.c.f).i().getCount() == 1) {
            ((C1349Wc1) this.f9936a).v();
        }
        KF0 kf0 = this.c.s;
        if (kf0 != null && kf0.d() == tab.getId()) {
            ((C1349Wc1) this.b).q(3);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        if (((AbstractC0246Ea1) this.c.f).i().getCount() == 1) {
            ((C1349Wc1) this.f9936a).r();
            return;
        }
        KF0 kf0 = this.c.s;
        if (kf0 != null && kf0.d() == tab.getId()) {
            ((C1349Wc1) this.b).I.l(6, 3);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        C3919nd1 nd1 = this.c;
        if (((AbstractC0246Ea1) nd1.f).h) {
            nd1.t = false;
        }
    }
}
