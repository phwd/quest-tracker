package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.ntp.RecentlyClosedBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModelJniBridge;
import org.chromium.content_public.browser.WebContents;

/* renamed from: qa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4423qa1 extends TabModelJniBridge {
    public final List d = new ArrayList();
    public final A61 e;
    public final A61 f;
    public final AbstractC5953za1 g;
    public final TabContentManager h;
    public final C4766sb1 i;
    public final AbstractC3397ka1 j;
    public final C1322Vq0 k;
    public final AbstractC3780mo0 l;
    public final C1280Va m;
    public RecentlyClosedBridge n;
    public final C4252pa1 o = new C4252pa1(this, null);
    public int p = -1;
    public boolean q = true;
    public boolean r;

    public C4423qa1(Profile profile, boolean z, A61 a61, A61 a612, AbstractC5953za1 za1, TabContentManager tabContentManager, C4766sb1 sb1, AbstractC3780mo0 mo0, C1280Va va, AbstractC3397ka1 ka1, boolean z2) {
        super(profile, z);
        this.e = a61;
        this.f = a612;
        this.g = za1;
        this.h = tabContentManager;
        this.i = sb1;
        this.l = mo0;
        this.m = va;
        this.j = ka1;
        this.q = z2;
        this.k = new C1322Vq0();
        this.b = N.M15HofTq(this, profile, this.c);
        this.n = new RecentlyClosedBridge(profile);
    }

    public final boolean A(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (tab == null || !this.d.contains(tab)) {
            return false;
        }
        boolean o2 = z3 & o();
        tab.R(true);
        Iterator it = this.k.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC5783ya1) uq0.next()).K(tab, z);
        }
        D(tab, tab2, z2 ? 1 : 0, o2, !o2);
        if (z4 && o2) {
            Iterator it2 = this.k.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (!uq02.hasNext()) {
                    break;
                }
                ((AbstractC5783ya1) uq02.next()).H(tab);
            }
        }
        if (!o2) {
            B(tab, false);
        }
        return true;
    }

    public final void B(Tab tab, boolean z) {
        WebContents webContents;
        TabContentManager tabContentManager = this.h;
        if (tabContentManager != null) {
            tabContentManager.j(tab.getId());
        }
        C4766sb1 sb1 = this.i;
        sb1.f.remove(tab);
        sb1.g.remove(sb1.h(tab.getId()));
        C4084ob1 ob1 = sb1.i;
        if (ob1 != null && ob1.f10561a.f11206a == tab.getId()) {
            sb1.i.a(false);
            sb1.i = null;
            sb1.k();
        }
        C3742mb1 mb1 = sb1.j;
        if (mb1 != null && mb1.j == tab.getId()) {
            sb1.j.b(false);
            sb1.j = null;
            sb1.t();
        }
        sb1.f(AbstractC1224Ub1.b(tab.getId(), tab.a()), false);
        Map map = AbstractC2145dC0.F;
        tab.K(false);
        P20 p20 = C2361eV0.Q;
        if (((C2361eV0) ((AbstractC2145dC0) tab.M().c(C2361eV0.class))) != null) {
            ((C2361eV0) ((AbstractC2145dC0) tab.M().c(C2361eV0.class))).q();
        }
        if (!this.f10777a) {
            if (tab.G()) {
                Ax1 ax1 = C5383wB.q(tab).T;
                if (!(ax1 == null || (webContents = (WebContents) N.MXGOiJkn(ax1.f7707a, ax1.b, true)) == null)) {
                    N.MVKUCy7F(webContents);
                    webContents.destroy();
                }
            } else {
                N.MVKUCy7F(tab.l());
            }
        }
        Iterator it = this.k.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC5783ya1) uq0.next()).A(tab.getId(), tab.a());
        }
        if (z) {
            Iterator it2 = this.k.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (!uq02.hasNext()) {
                    break;
                }
                ((AbstractC5783ya1) uq02.next()).F(tab);
            }
        }
        tab.destroy();
    }

    public final boolean C() {
        if (this.d.size() <= 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (!((Tab) this.d.get(i2)).x()) {
                return true;
            }
        }
        return false;
    }

    public final void D(Tab tab, Tab tab2, int i2, boolean z, boolean z2) {
        boolean z3;
        int i3;
        WebContents l2;
        int id = tab.getId();
        int i4 = i(tab);
        Tab c = AbstractC1160Ta1.c(this);
        Tab tabAt = getTabAt(i4 == 0 ? 1 : i4 - 1);
        if (tab2 == null) {
            tab2 = r(id);
        }
        if (z2) {
            d();
        }
        if (z && (l2 = tab.l()) != null) {
            l2.a0();
            l2.V(true);
        }
        this.d.remove(tab);
        if (tab2 == null) {
            z3 = false;
        } else {
            z3 = tab2.a();
        }
        int i5 = -1;
        if (tab2 == null) {
            i3 = -1;
        } else {
            i3 = tab2.getId();
        }
        if (tab2 != null) {
            i5 = AbstractC1160Ta1.e(((AbstractC0246Ea1) this.j).l(z3), i3);
        }
        if (tab2 != c) {
            if (z3 != this.f10777a) {
                this.p = i(tabAt);
            }
            ((AbstractC0246Ea1) this.j).l(z3).x(i5, i2);
        } else {
            this.p = i5;
        }
        if (z2) {
            this.o.B();
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void c(boolean z) {
        this.r = z;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public boolean closeTabAt(int i2) {
        return h(getTabAt(i2));
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public boolean createTabWithWebContents(Tab tab, Profile profile, WebContents webContents) {
        A61 y = y(profile.g());
        Objects.requireNonNull(y);
        return y.c(tab, webContents, 5, webContents.u());
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void d() {
        while (this.o.getCount() > this.d.size()) {
            v(this.o.y().getId());
        }
        if (o()) {
            Iterator it = this.k.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).y();
                } else {
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, org.chromium.chrome.browser.tabmodel.TabModel
    public void destroy() {
        for (Tab tab : this.d) {
            if ((!((AbstractC0246Ea1) this.j).j || !this.m.b(tab.getId())) && tab.isInitialized()) {
                tab.destroy();
            }
        }
        C4252pa1 pa1 = this.o;
        if (!((AbstractC0246Ea1) pa1.b.j).j) {
            for (Tab tab2 : pa1.f11074a) {
                if (tab2.isInitialized()) {
                    tab2.destroy();
                }
            }
        }
        this.d.clear();
        this.k.clear();
        RecentlyClosedBridge recentlyClosedBridge = this.n;
        N.MN6LZLAP(recentlyClosedBridge.f10713a, recentlyClosedBridge);
        recentlyClosedBridge.f10713a = 0;
        super.destroy();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean e(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3) {
        return A(tab, tab2, z, z2, z3, z3);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void f(Tab tab, int i2, int i3, int i4) {
        try {
            TraceEvent.Y("TabModelImpl.addTab", null);
            Iterator it = this.k.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC5783ya1) uq0.next()).J(tab, i3);
            }
            boolean b = ((C0002Aa1) this.g).b(i3, this.f10777a);
            int a2 = ((C0002Aa1) this.g).a(i3, i2, tab);
            if (tab.a() == this.f10777a) {
                d();
                if (a2 < 0 || a2 > this.d.size()) {
                    this.d.add(tab);
                } else {
                    this.d.add(a2, tab);
                    int i5 = this.p;
                    if (a2 <= i5) {
                        this.p = i5 + 1;
                    }
                }
                if (!this.r) {
                    this.p = Math.max(this.p, 0);
                }
                this.o.B();
                int i6 = i(tab);
                if (z()) {
                    N.M2Pb3$9r(this.b, this, tab);
                }
                Iterator it2 = this.k.iterator();
                while (true) {
                    C1261Uq0 uq02 = (C1261Uq0) it2;
                    if (!uq02.hasNext()) {
                        break;
                    }
                    ((AbstractC5783ya1) uq02.next()).z(tab, i3, i4);
                }
                if (b) {
                    x(i6, 2);
                }
                return;
            }
            throw new IllegalStateException("Attempting to open tab in wrong model");
        } finally {
            TraceEvent.f0("TabModelImpl.addTab");
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void g(boolean z, boolean z2) {
        C4766sb1 sb1 = this.i;
        boolean z3 = this.f10777a;
        if (z3) {
            sb1.n = true;
        } else {
            sb1.m = true;
        }
        if (z2) {
            d();
            for (int i2 = 0; i2 < getCount(); i2++) {
                getTabAt(i2).R(true);
            }
            while (getCount() > 0) {
                AbstractC1160Ta1.b(this, 0);
            }
        } else if (!z || !((C0551Ja1) this.j).u.a(z3)) {
            if (QX.i()) {
                d();
                for (int i3 = 0; i3 < getCount(); i3++) {
                    getTabAt(i3).R(true);
                }
                while (getCount() > 0) {
                    AbstractC1160Ta1.b(this, 0);
                }
                return;
            }
            boolean z4 = !AbstractC4772sd1.b();
            for (int i4 = 0; i4 < getCount(); i4++) {
                getTabAt(i4).R(true);
            }
            ArrayList arrayList = new ArrayList();
            while (getCount() > 0) {
                Tab tabAt = getTabAt(0);
                arrayList.add(tabAt);
                A(tabAt, null, z4, false, true, false);
            }
            if (o()) {
                Iterator it = this.k.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((AbstractC5783ya1) uq0.next()).D(arrayList, true);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, defpackage.N81
    public int getCount() {
        return this.d.size();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, defpackage.N81
    public Tab getTabAt(int i2) {
        if (i2 < 0 || i2 >= this.d.size()) {
            return null;
        }
        return (Tab) this.d.get(i2);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean h(Tab tab) {
        return q(tab, true, false, false);
    }

    @Override // defpackage.N81
    public int i(Tab tab) {
        int indexOf;
        if (tab == null || (indexOf = this.d.indexOf(tab)) == -1) {
            return -1;
        }
        return indexOf;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, defpackage.N81
    public int index() {
        return this.p;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, org.chromium.chrome.browser.tabmodel.TabModel
    public boolean isActiveModel() {
        return this.r;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public boolean isSessionRestoreInProgress() {
        return ((C0551Ja1) this.j).t();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public N81 j() {
        if (!o()) {
            return this;
        }
        return this.o;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void k() {
        g(true, false);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void l(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Tab tab = (Tab) it.next();
            if (this.d.contains(tab)) {
                tab.R(true);
                A(tab, null, false, false, z, false);
            }
        }
        if (z && o()) {
            Iterator it2 = this.k.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it2;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).D(list, false);
                } else {
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void m(int i2, int i3) {
        int c = AbstractC4089od0.c(i3, 0, this.d.size());
        int e2 = AbstractC1160Ta1.e(this, i2);
        if (e2 != -1 && e2 != c && e2 + 1 != c) {
            d();
            Tab tab = (Tab) this.d.remove(e2);
            if (e2 < c) {
                c--;
            }
            this.d.add(c, tab);
            int i4 = this.p;
            if (e2 == i4) {
                this.p = c;
            } else if (e2 < i4 && c >= i4) {
                this.p = i4 - 1;
            } else if (e2 > i4 && c <= i4) {
                this.p = i4 + 1;
            }
            this.o.B();
            Iterator it = this.k.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).B(tab, c, e2);
                } else {
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void n(AbstractC5783ya1 ya1) {
        this.k.b(ya1);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean o() {
        return !this.f10777a && this.q;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a8  */
    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openNewTab(org.chromium.chrome.browser.tab.Tab r23, java.lang.String r24, org.chromium.url.Origin r25, java.lang.String r26, org.chromium.content_public.common.ResourceRequestBody r27, int r28, boolean r29, boolean r30) {
        /*
        // Method dump skipped, instructions count: 175
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4423qa1.openNewTab(org.chromium.chrome.browser.tab.Tab, java.lang.String, org.chromium.url.Origin, java.lang.String, org.chromium.content_public.common.ResourceRequestBody, int, boolean, boolean):void");
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void p() {
        if (this.o.A()) {
            Tab y = this.o.y();
            if (y != null) {
                s(y.getId());
                return;
            }
            return;
        }
        RecentlyClosedBridge recentlyClosedBridge = this.n;
        N.M2TIwc10(recentlyClosedBridge.f10713a, recentlyClosedBridge);
        if (getCount() == 1) {
            x(0, 2);
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public boolean q(Tab tab, boolean z, boolean z2, boolean z3) {
        return A(tab, null, z, z2, z3, z3);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public Tab r(int i2) {
        Tab d2 = AbstractC1160Ta1.d(this, i2);
        Tab c = AbstractC1160Ta1.c(this);
        if (d2 == null) {
            return c;
        }
        int i3 = i(d2);
        Tab tabAt = getTabAt(i3 == 0 ? 1 : i3 - 1);
        int i4 = C5383wB.q(d2).Q;
        Tab d3 = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.j).l(this.f10777a), i4);
        if (d3 == null) {
            d3 = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.j).l(true ^ this.f10777a), i4);
        }
        if (!this.r) {
            c = AbstractC1160Ta1.c(((AbstractC0246Ea1) this.j).i());
        } else if (d2 == c || c == null || c.x()) {
            if (d3 != null && !d3.x() && ((Integer) this.l.get()).intValue() == 0) {
                c = d3;
            } else if (tabAt == null || tabAt.x()) {
                c = this.f10777a ? AbstractC1160Ta1.c(((AbstractC0246Ea1) this.j).l(false)) : null;
            } else {
                c = tabAt;
            }
        }
        if (c == null || !c.x()) {
            return c;
        }
        return null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void s(int i2) {
        Tab z = this.o.z(i2);
        if (z != null) {
            z.R(false);
            int indexOf = this.o.f11074a.indexOf(z);
            int i3 = -1;
            for (int i4 = 0; i4 < indexOf; i4++) {
                Tab tabAt = this.o.getTabAt(i4);
                if (i3 == this.d.size() - 1) {
                    break;
                }
                int i5 = i3 + 1;
                if (tabAt == this.d.get(i5)) {
                    i3 = i5;
                }
            }
            int i6 = i3 + 1;
            int i7 = this.p;
            if (i7 >= i6) {
                this.p = i7 + 1;
            }
            this.d.add(i6, z);
            WebContents l2 = z.l();
            if (l2 != null) {
                l2.V(false);
            }
            boolean z2 = this.r;
            if (this.p == -1) {
                if (z2) {
                    AbstractC1160Ta1.f(this, i6);
                } else {
                    this.p = i6;
                }
            }
            this.i.u();
            Iterator it = this.k.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5783ya1) uq0.next()).G(z);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.N81
    public boolean t(int i2) {
        return this.o.z(i2) != null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void u(Tab tab) {
        D(tab, null, 0, false, true);
        Iterator it = this.k.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5783ya1) uq0.next()).I(tab);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void v(int i2) {
        Tab z = this.o.z(i2);
        if (z != null) {
            this.o.f11074a.remove(z);
            B(z, true);
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void w(AbstractC5783ya1 ya1) {
        this.k.c(ya1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[Catch:{ all -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void x(int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4423qa1.x(int, int):void");
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public A61 y(boolean z) {
        return z ? this.f : this.e;
    }
}
