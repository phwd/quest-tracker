package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: la1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3568la1 extends AbstractC5783ya1 implements N81 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f10354a = Collections.unmodifiableList(new ArrayList());
    public TabModel b;
    public C1322Vq0 c = new C1322Vq0();
    public boolean d;
    public boolean e;

    public AbstractC3568la1(TabModel tabModel) {
        this.b = tabModel;
        tabModel.n(this);
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).A(i, z);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void B(Tab tab, int i, int i2) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).B(tab, i, i2);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        R(tab);
        if (S()) {
            Iterator it = this.c.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).C(tab, i, i2);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void D(List list, boolean z) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).D(list, z);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void E() {
        this.d = true;
        if (getCount() != 0) {
            Q();
        }
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).E();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).F(tab);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        L(tab);
        Q();
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).G(tab);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void H(Tab tab) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).H(tab);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        u(tab);
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).I(tab);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void J(Tab tab, int i) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).J(tab, i);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        M(tab);
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).K(tab, z);
            } else {
                return;
            }
        }
    }

    public abstract void L(Tab tab);

    public abstract void M(Tab tab);

    public List N(int i) {
        Tab d2 = AbstractC1160Ta1.d(this.b, i);
        if (d2 == null) {
            return f10354a;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(d2);
        return Collections.unmodifiableList(arrayList);
    }

    public final List O() {
        ArrayList arrayList = new ArrayList();
        TabModel tabModel = this.b;
        for (int i = 0; i < tabModel.getCount(); i++) {
            Tab tabAt = tabModel.getTabAt(i);
            if (!P(tabAt)) {
                arrayList.add(tabAt);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public boolean P(Tab tab) {
        return false;
    }

    public abstract void Q();

    public abstract void R(Tab tab);

    public boolean S() {
        return true;
    }

    public abstract void u(Tab tab);

    @Override // defpackage.AbstractC5783ya1
    public void y() {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).y();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        L(tab);
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).z(tab, i, i2);
            } else {
                return;
            }
        }
    }
}
