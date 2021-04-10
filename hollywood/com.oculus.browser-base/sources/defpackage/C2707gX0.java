package defpackage;

import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: gX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2707gX0 implements TabModel {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f10004a = new C1322Vq0();
    public Tab b;

    @Override // defpackage.N81
    public boolean a() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public Profile b() {
        Tab tab = this.b;
        if (tab == null) {
            return null;
        }
        return Profile.a(tab.l());
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void c(boolean z) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void d() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void destroy() {
        y();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean e(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3) {
        return q(tab, z, z2, z3);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void f(Tab tab, int i, int i2, int i3) {
        Tab tab2 = this.b;
        z(tab, i2, i3);
        if (tab2 != null) {
            Iterator it = this.f10004a.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).I(tab2);
                } else {
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void g(boolean z, boolean z2) {
        u(this.b);
    }

    @Override // defpackage.N81
    public int getCount() {
        return this.b == null ? 0 : 1;
    }

    @Override // defpackage.N81
    public Tab getTabAt(int i) {
        if (i == 0) {
            return this.b;
        }
        return null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean h(Tab tab) {
        return q(tab, false, false, false);
    }

    @Override // defpackage.N81
    public int i(Tab tab) {
        Tab tab2;
        return (tab == null || (tab2 = this.b) == null || tab2.getId() != tab.getId()) ? -1 : 0;
    }

    @Override // defpackage.N81
    public int index() {
        return this.b != null ? 0 : -1;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean isActiveModel() {
        return true;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public N81 j() {
        return this;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void k() {
        u(this.b);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void l(List list, boolean z) {
        if (this.b != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((Tab) it.next()).getId() == this.b.getId()) {
                    y();
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void m(int i, int i2) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void n(AbstractC5783ya1 ya1) {
        this.f10004a.b(ya1);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean o() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void p() {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean q(Tab tab, boolean z, boolean z2, boolean z3) {
        Tab tab2 = this.b;
        if (tab2 == null || tab2.getId() != tab.getId()) {
            return false;
        }
        y();
        return true;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public Tab r(int i) {
        return null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void s(int i) {
    }

    @Override // defpackage.N81
    public boolean t(int i) {
        return false;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void u(Tab tab) {
        Tab tab2 = this.b;
        if (tab2 != null && tab == tab2) {
            y();
            Iterator it = this.f10004a.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).I(tab);
                } else {
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void v(int i) {
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void w(AbstractC5783ya1 ya1) {
        this.f10004a.c(ya1);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void x(int i, int i2) {
    }

    public void y() {
        z(null, 0, 0);
    }

    public void z(Tab tab, int i, int i2) {
        Tab tab2 = this.b;
        if (tab2 != tab) {
            this.b = tab;
            if (tab2 != null) {
                tab2.R(true);
                Iterator it = this.f10004a.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (!uq0.hasNext()) {
                        break;
                    }
                    ((AbstractC5783ya1) uq0.next()).K(tab2, false);
                }
            }
            if (tab != null) {
                Iterator it2 = this.f10004a.iterator();
                while (true) {
                    C1261Uq0 uq02 = (C1261Uq0) it2;
                    if (!uq02.hasNext()) {
                        break;
                    }
                    AbstractC5783ya1 ya1 = (AbstractC5783ya1) uq02.next();
                    ya1.z(tab, i, i2);
                    ya1.C(tab, 3, -1);
                }
            }
            if (tab2 != null && tab2.isInitialized()) {
                Iterator it3 = this.f10004a.iterator();
                while (true) {
                    C1261Uq0 uq03 = (C1261Uq0) it3;
                    if (uq03.hasNext()) {
                        ((AbstractC5783ya1) uq03.next()).A(tab2.getId(), tab2.a());
                    } else {
                        ((TabImpl) tab2).destroy();
                        return;
                    }
                }
            }
        }
    }
}
