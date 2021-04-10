package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelWrapper;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.TabManagementDelegateImpl;

/* renamed from: n70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3838n70 extends D70 implements AbstractC2431eu0, W {
    public AbstractC0956Pq0 A0;
    public final C1570Zs0 B0;
    public C2090cu0 r0;
    public C5306vl1 s0;
    public AbstractC2300e70 t0;
    public final AbstractC5194v41 u0;
    public AbstractC0757Mi1 v0;
    public boolean w0 = true;
    public boolean x0;
    public final C1322Vq0 y0;
    public boolean z0;

    public AbstractC3838n70(AbstractC4692s70 s70, ViewGroup viewGroup, boolean z, AbstractC2451f01 f01, AbstractC0956Pq0 pq0, Q31 q31, C1570Zs0 zs0, C1570Zs0 zs02, Q31 q312) {
        super(s70, viewGroup, pq0, q31, zs02, q312);
        AbstractC2300e70 e70;
        Context context = s70.getContext();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) s70;
        this.y0 = new C1322Vq0();
        this.u0 = new C3667m70(this, true);
        this.A0 = pq0;
        ((C1078Rq0) pq0).l(new C3496l70(this, pq0));
        if (z) {
            if (f01 != null) {
                TabManagementDelegateImpl a2 = AbstractC1680aa1.a();
                C1078Rq0 rq0 = ((CompositorViewHolder) this.G).V;
                Objects.requireNonNull(a2);
                if (AbstractC2793h01.d()) {
                    e70 = new P01(context, this, compositorViewHolder, f01, rq0);
                } else {
                    e70 = new C01(context, this, compositorViewHolder, f01);
                }
                this.t0 = e70;
            } else {
                this.z0 = true;
            }
        }
        this.B0 = zs0;
        Objects.requireNonNull(zs0.G);
        zs0.F.b(this);
    }

    public void A(boolean z) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null && !e70.Q) {
            if (z) {
                e70.J(SystemClock.uptimeMillis(), -1);
                return;
            }
            e(-1, false);
            b();
        }
    }

    public final boolean B(AbstractC2300e70 e70) {
        return e70 != null && (e70 == this.t0 || e70 == this.r0);
    }

    public boolean C() {
        AbstractC2300e70 e70 = this.S;
        return B(e70) && !e70.Q;
    }

    public void D(AbstractC0124Ca1 ca1) {
        this.M = ca1;
        this.i0.m(ca1);
        this.O = new A70(this, this.M);
        AbstractC2300e70 e70 = this.T;
        if (e70 != null) {
            F(e70, true);
        }
        B70 b70 = new B70(this);
        this.N = b70;
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
        ea1.c(b70);
        ea1.f(this);
        C70 h = h();
        this.P = h;
        ((AbstractC0246Ea1) this.M).c.a(h);
        AbstractC2300e70 e702 = this.t0;
        if (e702 != null) {
            e702.O(ca1, null);
        }
    }

    public void E(boolean z) {
        boolean a2 = C5052uE.a();
        boolean z2 = true;
        boolean z3 = a2 && this.S == this.r0;
        AbstractC2300e70 e70 = this.S;
        AbstractC2300e70 e702 = this.t0;
        if (e70 != e702 || e702 == null) {
            z2 = false;
        }
        if ((z3 || a2) && !z2) {
            F(this.r0, z);
        } else if (e702 != null) {
            F(e702, z);
        }
    }

    public void F(AbstractC2300e70 e70, boolean z) {
        boolean z2 = false;
        this.x0 = false;
        t(null);
        AbstractC2300e70 e702 = this.S;
        if (e702 != e70) {
            if (e702 != null) {
                e702.j();
                e702.g();
            }
            e70.c(this.G.getContext());
            e70.a(this.L);
            this.S = e70;
        }
        C1551Zj zj = ((CompositorViewHolder) this.G).U;
        if (zj != null) {
            this.b0 = !(zj.W == 1.0f);
            zj.G.p(this.W);
            if (this.S.l()) {
                this.W = zj.G.q();
            }
        }
        r();
        this.S.P(SystemClock.uptimeMillis(), z);
        AbstractC4692s70 s70 = this.G;
        AbstractC2300e70 e703 = this.S;
        Objects.requireNonNull(e703);
        boolean z3 = e703 instanceof D11;
        boolean b = this.S.b();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) s70;
        if (!(z3 == compositorViewHolder.N && b == compositorViewHolder.O)) {
            compositorViewHolder.N = z3;
            compositorViewHolder.O = b;
            compositorViewHolder.G(z3);
        }
        ((CompositorViewHolder) this.G).C();
        Iterator it = this.R.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((KO0) uq0.next()).T(this.S);
        }
        int p = e70.p();
        boolean z4 = z && (!this.w0 || ((AbstractC0246Ea1) this.M).i().getCount() <= 0);
        C1570Zs0 zs0 = this.o0;
        C5202v70 v70 = new C5202v70(this, p, z4);
        Objects.requireNonNull(zs0.G);
        zs0.F.g(v70);
        zs0.get();
        if (B(this.S)) {
            if (z && (!this.w0 || ((AbstractC0246Ea1) this.M).i().getCount() <= 0)) {
                z2 = true;
            }
            C1570Zs0 zs02 = this.B0;
            C2813h70 h70 = new C2813h70(this, z2);
            Objects.requireNonNull(zs02.G);
            zs02.F.g(h70);
            zs02.get();
        }
    }

    @Override // defpackage.AbstractC0063Ba1
    public boolean a(boolean z) {
        if (!B(this.S) || !this.S.s()) {
            return false;
        }
        this.S.K(SystemClock.uptimeMillis(), z);
        return true;
    }

    @Override // defpackage.K70
    public void b() {
        AbstractC2300e70 e70 = this.S;
        Object obj = this.T;
        if (obj == null) {
            obj = this.K;
        }
        if (obj == this.K) {
            AbstractC0124Ca1 ca1 = this.M;
            Tab j = ca1 != null ? ((AbstractC0246Ea1) ca1).j() : null;
            j(j != null ? j.getId() : -1);
        }
        if (this.T != null) {
            int p = this.S.p();
            C1570Zs0 zs0 = this.o0;
            C5712y70 y70 = new C5712y70(this, p);
            Objects.requireNonNull(zs0.G);
            zs0.F.g(y70);
            zs0.get();
            F(this.T, true);
        }
        if (B(e70)) {
            C1570Zs0 zs02 = this.B0;
            C3325k70 k70 = new C3325k70(this);
            Objects.requireNonNull(zs02.G);
            zs02.F.g(k70);
            zs02.get();
        }
    }

    @Override // defpackage.K70, defpackage.D70
    public void c(int i) {
        Tab o;
        J70 j70;
        AbstractC0757Mi1 mi1 = this.v0;
        if (mi1 != null) {
            ((LayerTitleCache) mi1).b(i);
        }
        AbstractC0124Ca1 ca1 = this.M;
        if (ca1 != null && this.S != null && (o = ((AbstractC0246Ea1) ca1).o(i)) != null && (j70 = (J70) this.V.get(i)) != null) {
            String s = o.s();
            boolean z = false;
            boolean z2 = o.l() != null && !C3372kO0.W(o) && !(o.isNativePage() || (s != null && s.startsWith("chrome-native://"))) && !o.isHidden();
            C2921hm1 hm1 = (C2921hm1) this.p0.get();
            Objects.requireNonNull(hm1);
            int a2 = AbstractC1300Vg1.a(o);
            if ((o.G() || o.f()) && !AbstractC5818ym0.a(o.s(), o.a())) {
                z = true;
            }
            int f = hm1.f(o);
            int b = Pj1.b(this.a0.getResources(), o, hm1.e(o, o.m()));
            float g = hm1.g(o);
            j70.l(J70.T, a2);
            j70.l(J70.U, f);
            j70.l(J70.V, b);
            j70.k(J70.W, g);
            j70.j(J70.F, z);
            j70.j(J70.G, z2);
            j70.j(J70.S, true);
            ((CompositorViewHolder) this.G).C();
        }
    }

    @Override // defpackage.K70
    public void e(int i, boolean z) {
        J70 o;
        J70 o2;
        s(null);
        if (z) {
            C1570Zs0 zs0 = this.o0;
            C5882z70 z70 = new C5882z70(this, i);
            Objects.requireNonNull(zs0.G);
            zs0.F.g(z70);
            zs0.get();
            Iterator it = this.R.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((KO0) uq0.next()).q(i);
            }
        }
        AbstractC2300e70 e70 = this.S;
        int p = e70.p();
        boolean z2 = false;
        boolean z3 = !this.w0 || e70 != this.t0 || (o2 = e70.o(i)) == null || !o2.H();
        boolean z4 = this.w0 && e70 == this.t0 && this.x0;
        C1570Zs0 zs02 = this.o0;
        C5542x70 x70 = new C5542x70(this, p, z3, z4);
        Objects.requireNonNull(zs02.G);
        zs02.F.g(x70);
        zs02.get();
        AbstractC2300e70 e702 = this.S;
        if (B(e702)) {
            boolean z5 = !this.w0 || e702 != this.t0 || (o = e702.o(i)) == null || !o.H();
            if (e702 == this.t0 && this.x0) {
                z2 = true;
            }
            C1570Zs0 zs03 = this.B0;
            C3154j70 j70 = new C3154j70(this, z5, z2);
            Objects.requireNonNull(zs03.G);
            zs03.F.g(j70);
            zs03.get();
        }
    }

    @Override // defpackage.D70
    public void i() {
        this.h0.f11101a.clear();
        this.R.clear();
        D11 d11 = this.K;
        if (d11 != null) {
            d11.f();
        }
        C1328Vt0 vt0 = this.Z;
        if (vt0 != null) {
            for (AbstractC0292Et0 et0 : vt0.f9113a) {
                et0.z(0, false);
                D70 d70 = et0.q0;
                if (d70 != null) {
                    d70.R.c(et0.r0);
                }
                ApplicationStatus.h(et0);
            }
            vt0.f9113a.clear();
            vt0.c = null;
            vt0.d.clear();
            vt0.e = null;
            vt0.f = null;
        }
        AbstractC1099Sa1 sa1 = this.O;
        if (sa1 != null) {
            sa1.destroy();
        }
        AbstractC0612Ka1 ka1 = this.N;
        if (ka1 != null) {
            ((AbstractC0246Ea1) this.M).f.c(ka1);
        }
        AbstractC5783ya1 ya1 = this.P;
        if (ya1 != null) {
            ((AbstractC0246Ea1) this.M).c.h(ya1);
        }
        this.y0.clear();
        if (this.A0 != null) {
            this.A0 = null;
        }
        AbstractC2300e70 e70 = this.t0;
        if (e70 != null) {
            e70.f();
            this.t0 = null;
        }
        C2090cu0 cu0 = this.r0;
        if (cu0 != null) {
            cu0.f();
        }
        C5306vl1 vl1 = this.s0;
        if (vl1 != null) {
            Objects.requireNonNull(vl1);
        }
    }

    @Override // defpackage.W
    public void l(boolean z) {
        this.w0 = C5052uE.b();
    }

    @Override // defpackage.D70
    public void p(AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1, DA da, IJ ij) {
        Context context = this.G.getContext();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.G;
        Objects.requireNonNull(compositorViewHolder);
        this.r0 = new C2090cu0(context, this, compositorViewHolder, ((CompositorViewHolder) this.G).U);
        this.s0 = new C5306vl1(context, this, compositorViewHolder);
        if (this.z0) {
            this.t0 = new C3565lZ0(context, this, compositorViewHolder, ((CompositorViewHolder) this.G).V);
        }
        CompositorViewHolder compositorViewHolder2 = (CompositorViewHolder) this.G;
        Objects.requireNonNull(compositorViewHolder2);
        D11 d11 = new D11(this.a0, this, compositorViewHolder2, this.G, this.l0, ca1, (TabContentManager) ((C1078Rq0) this.j0).H, this.k0, this.p0);
        this.K = d11;
        d11.V = true;
        t(null);
        this.K.A();
        this.k0.m(((CompositorViewHolder) this.G).U);
        C1328Vt0 vt0 = this.Z;
        vt0.e = ij;
        for (AbstractC0292Et0 et0 : vt0.f9113a) {
            et0.l0 = ij;
        }
        C1328Vt0 vt02 = this.Z;
        ViewGroup viewGroup = this.L;
        vt02.f = viewGroup;
        for (AbstractC0292Et0 et02 : vt02.f9113a) {
            et02.k0 = viewGroup;
        }
        if (this.M == null) {
            D(ca1);
        }
        this.v0 = ((CompositorViewHolder) this.G).L;
        TabContentManager tabContentManager = (TabContentManager) ((C1078Rq0) this.A0).H;
        C5306vl1 vl1 = this.s0;
        vl1.L = ca1;
        vl1.N(tabContentManager);
        C2090cu0 cu0 = this.r0;
        cu0.L = ca1;
        cu0.N(tabContentManager);
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = cu0.V;
        if (accessibilityTabModelWrapper != null) {
            accessibilityTabModelWrapper.c(ca1);
        }
        AbstractC2300e70 e70 = this.t0;
        if (e70 != null) {
            e70.O(ca1, tabContentManager);
            this.t0.A();
        }
    }

    @Override // defpackage.D70
    public void w(int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        Tab d = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.M).l(z), i);
        this.x0 = d != null && d.isNativePage();
        this.S.E(SystemClock.uptimeMillis(), i, AbstractC1160Ta1.e(((AbstractC0246Ea1) this.M).l(z), i), i2, z, !z2, f, f2);
    }

    public Tab z(int i) {
        AbstractC0124Ca1 ca1 = this.M;
        if (ca1 == null) {
            return null;
        }
        return ((AbstractC0246Ea1) ca1).o(i);
    }
}
