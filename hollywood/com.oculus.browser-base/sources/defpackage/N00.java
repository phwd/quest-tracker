package defpackage;

import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: N00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N00 implements M00 {

    /* renamed from: a  reason: collision with root package name */
    public final O00 f8518a;
    public final C1322Vq0 b = new C1322Vq0();
    public final C1322Vq0 c = new C1322Vq0();
    public TabModel d;
    public int e;
    public boolean f;

    public N00(O00 o00) {
        this.f8518a = o00;
        this.d = SK.f8890a;
    }

    @Override // defpackage.N81
    public boolean a() {
        return true;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public Profile b() {
        return this.d.b();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void c(boolean z) {
        this.f = z;
        if (z) {
            z();
        }
        this.d.c(z);
        if (!z) {
            y();
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void d() {
        if (!(j().getCount() == 0)) {
            this.d.d();
            y();
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void destroy() {
        this.d.destroy();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean e(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3) {
        this.e++;
        boolean e2 = this.d.e(tab, tab2, z, z2, z3);
        this.e--;
        y();
        return e2;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void f(Tab tab, int i, int i2, int i3) {
        this.e++;
        z();
        boolean z = getCount() == 0;
        this.d.f(tab, i, i2, i3);
        if (z) {
            Iterator it = this.c.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((P00) uq0.next()).b();
            }
        }
        this.e--;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void g(boolean z, boolean z2) {
        this.e++;
        this.d.g(z, z2);
        this.e--;
        y();
    }

    @Override // defpackage.N81
    public int getCount() {
        return this.d.getCount();
    }

    @Override // defpackage.N81
    public Tab getTabAt(int i) {
        return this.d.getTabAt(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean h(Tab tab) {
        this.e++;
        boolean h = this.d.h(tab);
        this.e--;
        y();
        return h;
    }

    @Override // defpackage.N81
    public int i(Tab tab) {
        return this.d.i(tab);
    }

    @Override // defpackage.N81
    public int index() {
        return this.d.index();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean isActiveModel() {
        return this.f;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public N81 j() {
        return this.d.j();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void k() {
        this.e++;
        this.d.k();
        this.e--;
        y();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void l(List list, boolean z) {
        this.e++;
        this.d.l(list, z);
        this.e--;
        y();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void m(int i, int i2) {
        this.d.m(i, i2);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void n(AbstractC5783ya1 ya1) {
        this.b.b(ya1);
        this.d.n(ya1);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean o() {
        return this.d.o();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void p() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean q(Tab tab, boolean z, boolean z2, boolean z3) {
        this.e++;
        boolean q = this.d.q(tab, z, z2, z3);
        this.e--;
        y();
        return q;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public Tab r(int i) {
        return this.d.r(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void s(int i) {
        this.d.s(i);
    }

    @Override // defpackage.N81
    public boolean t(int i) {
        return this.d.t(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void u(Tab tab) {
        this.e++;
        this.d.u(tab);
        this.e--;
        y();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void v(int i) {
        this.d.v(i);
        y();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void w(AbstractC5783ya1 ya1) {
        this.b.c(ya1);
        this.d.w(ya1);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void x(int i, int i2) {
        this.d.x(i, i2);
    }

    public void y() {
        Object obj = ThreadUtils.f10596a;
        if ((j().getCount() == 0) && !(this.d instanceof TK) && this.e == 0) {
            Iterator it = this.c.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((P00) uq0.next()).a();
                } else {
                    this.d.destroy();
                    this.d = SK.f8890a;
                    return;
                }
            }
        }
    }

    public void z() {
        Profile profile;
        Object obj = ThreadUtils.f10596a;
        if (this.d instanceof TK) {
            O00 o00 = this.f8518a;
            Q31 q31 = o00.i;
            if (q31 != null) {
                Z00.b((WindowAndroid) q31.get());
                profile = Profile.b().c();
            } else {
                profile = Profile.b().c();
            }
            this.d = new C4423qa1(profile, false, o00.f8591a, o00.b, o00.c, o00.d, o00.e, o00.f, o00.g, o00.h, false);
            Iterator it = this.b.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    this.d.n((AbstractC5783ya1) uq0.next());
                } else {
                    return;
                }
            }
        }
    }
}
