package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Ea1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0246Ea1 extends P00 implements AbstractC0124Ca1 {

    /* renamed from: a  reason: collision with root package name */
    public List f7969a = new ArrayList();
    public M00 b;
    public C3910na1 c = new C3910na1();
    public final AbstractC3739ma1 d;
    public int e;
    public final C1322Vq0 f = new C1322Vq0();
    public final C1322Vq0 g = new C1322Vq0();
    public boolean h;
    public boolean i;
    public boolean j;
    public final AbstractC3226ja1 k;

    public AbstractC0246Ea1(AbstractC3226ja1 ja1, AbstractC3739ma1 ma1, boolean z) {
        this.k = ja1;
        this.d = ma1;
        this.i = z;
    }

    @Override // defpackage.P00
    public void a() {
        Iterator it = this.g.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((P00) uq0.next()).a();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.P00
    public void b() {
        Iterator it = this.g.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((P00) uq0.next()).b();
            } else {
                return;
            }
        }
    }

    public void c(AbstractC0612Ka1 ka1) {
        if (!this.f.F.contains(ka1)) {
            this.f.b(ka1);
        }
    }

    @Override // defpackage.AbstractC0124Ca1
    public void d() {
        for (int i2 = 0; i2 < this.f7969a.size(); i2++) {
            ((TabModel) this.f7969a.get(i2)).d();
        }
    }

    @Override // defpackage.AbstractC0124Ca1
    public void destroy() {
        this.f.c(this.c);
        C3910na1 na1 = this.c;
        for (int i2 = 0; i2 < na1.F.size(); i2++) {
            ((AbstractC3568la1) na1.F.get(i2)).c.clear();
        }
        na1.G.clear();
        M00 m00 = this.b;
        if (m00 != null) {
            ((N00) m00).c.c(this);
        }
        for (int i3 = 0; i3 < this.f7969a.size(); i3++) {
            ((TabModel) this.f7969a.get(i3)).destroy();
        }
        this.f7969a.clear();
    }

    @Override // defpackage.AbstractC0124Ca1
    public void e(boolean z) {
        if (this.f7969a.size() == 0) {
            this.i = z;
            return;
        }
        int n = n(z);
        if (n != this.e) {
            TabModel tabModel = (TabModel) this.f7969a.get(n);
            TabModel tabModel2 = (TabModel) this.f7969a.get(this.e);
            tabModel2.c(false);
            tabModel.c(true);
            this.e = n;
            Iterator it = this.f.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0612Ka1) uq0.next()).c(tabModel, tabModel2);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC0124Ca1
    public void f(AbstractC0063Ba1 ba1) {
    }

    @Override // defpackage.AbstractC0124Ca1
    public void g(boolean z) {
        for (int i2 = 0; i2 < this.f7969a.size(); i2++) {
            ((TabModel) this.f7969a.get(i2)).g(!z, z);
        }
    }

    public boolean h(Tab tab) {
        for (int i2 = 0; i2 < this.f7969a.size(); i2++) {
            TabModel tabModel = (TabModel) this.f7969a.get(i2);
            if (tabModel.i(tab) >= 0) {
                return tabModel.h(tab);
            }
        }
        return false;
    }

    public TabModel i() {
        if (this.f7969a.size() == 0) {
            return SK.f8890a;
        }
        return (TabModel) this.f7969a.get(this.e);
    }

    public Tab j() {
        return AbstractC1160Ta1.c(i());
    }

    public int k() {
        Tab j2 = j();
        if (j2 != null) {
            return j2.getId();
        }
        return -1;
    }

    public TabModel l(boolean z) {
        int n = n(z);
        if (n == -1) {
            return SK.f8890a;
        }
        return (TabModel) this.f7969a.get(n);
    }

    public TabModel m(int i2) {
        for (int i3 = 0; i3 < this.f7969a.size(); i3++) {
            TabModel tabModel = (TabModel) this.f7969a.get(i3);
            if (AbstractC1160Ta1.d(tabModel, i2) != null || tabModel.t(i2)) {
                return tabModel;
            }
        }
        return null;
    }

    public final int n(boolean z) {
        for (int i2 = 0; i2 < this.f7969a.size(); i2++) {
            if (z == ((TabModel) this.f7969a.get(i2)).a()) {
                return i2;
            }
        }
        return -1;
    }

    public Tab o(int i2) {
        for (int i3 = 0; i3 < this.f7969a.size(); i3++) {
            Tab d2 = AbstractC1160Ta1.d((N81) this.f7969a.get(i3), i2);
            if (d2 != null) {
                return d2;
            }
        }
        return null;
    }

    public int p() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f7969a.size(); i3++) {
            i2 += ((TabModel) this.f7969a.get(i3)).getCount();
        }
        return i2;
    }

    public final void q(TabModel tabModel, M00 m00) {
        if (tabModel != null) {
            this.f7969a.add(tabModel);
        }
        if (m00 != null) {
            this.f7969a.add(m00);
        }
        this.b = m00;
        this.e = n(this.i);
        C3910na1 na1 = this.c;
        AbstractC3739ma1 ma1 = this.d;
        List list = this.f7969a;
        Objects.requireNonNull(na1);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(ma1.a((TabModel) list.get(i2)));
        }
        na1.F = Collections.unmodifiableList(arrayList);
        for (AbstractC5783ya1 ya1 : na1.G) {
            for (AbstractC3568la1 la1 : na1.F) {
                la1.c.b(ya1);
            }
        }
        na1.G.clear();
        c(this.c);
        this.c.a(new C0185Da1(this));
        M00 m002 = this.b;
        if (m002 != null) {
            ((N00) m002).c.b(this);
            ((N00) m00).c(this.i);
        }
        tabModel.c(!this.i);
        s();
    }

    public boolean r() {
        if (this.f7969a.size() == 0) {
            return this.i;
        }
        return i().a();
    }

    public void s() {
        Iterator it = this.f.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0612Ka1) uq0.next()).e();
            } else {
                return;
            }
        }
    }
}
