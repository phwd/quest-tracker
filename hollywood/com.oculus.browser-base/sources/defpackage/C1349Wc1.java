package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: Wc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1349Wc1 implements AbstractC4371qE, AbstractC5959zc1, AbstractC5789yc1, AbstractC3748md1, AbstractC3406kd1, AbstractC3577ld1 {
    public final ZH0 F;
    public final M2 G;
    public final AbstractC1305Vi0 H;
    public final C1795b91 I;

    /* renamed from: J  reason: collision with root package name */
    public final C3919nd1 f9158J;
    public final C2920hm0 K;
    public final I61 L;
    public final AbstractC0124Ca1 M;
    public final int N;
    public final C5639xj0 O;
    public final C3261jm0 P;
    public C0371Gb1 Q;
    public C1288Vc1 R;
    public C4978tp1 S;
    public C4599rc1 T;
    public C2926ho0 U;
    public X51 V;
    public ViewGroup W;
    public AbstractC3226ja1 X;
    public boolean Y;
    public KF0 Z;
    public final ViewGroup a0;
    public final AbstractC1244Ui0 b0;
    public C4352q71 c0;
    public BF0 d0;

    public C1349Wc1(Context context, M2 m2, AbstractC0124Ca1 ca1, TabContentManager tabContentManager, AbstractC2400ek ekVar, AbstractC3226ja1 ja1, AbstractC1305Vi0 vi0, ViewGroup viewGroup, AbstractC0956Pq0 pq0, C3261jm0 jm0, GP0 gp0, int i) {
        C3919nd1 nd1;
        C1166Tc1 tc1 = new C1166Tc1(this);
        this.b0 = tc1;
        this.N = i;
        this.M = ca1;
        this.W = viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) ((AbstractActivityC2601fu) context).findViewById(R.id.coordinator);
        this.a0 = viewGroup2;
        this.X = ja1;
        this.P = jm0;
        UH0 uh0 = new UH0(O81.j);
        C3919nd1 nd12 = new C3919nd1(context, this, uh0, ca1, ekVar, viewGroup, tabContentManager, this, this, jm0, i);
        this.f9158J = nd12;
        C2920hm0 hm0 = new C2920hm0(context, tabContentManager, ca1);
        this.K = hm0;
        C1795b91 b91 = new C1795b91(i, context, ca1, hm0, new C0618Kc1(ca1, context), true, nd12, null, 1, null, this, viewGroup, true, "GridTabSwitcher");
        this.I = b91;
        this.F = ZH0.a(uh0, b91.G, new C0679Lc1());
        if (!AbstractC4772sd1.d() || !AbstractC4772sd1.f()) {
            nd1 = nd12;
        } else {
            nd1 = nd12;
            nd1.i.b(new C1227Uc1(this));
        }
        ca1.getClass();
        this.O = new C5639xj0(context, new C0739Mc1(ca1), new C0800Nc1(this));
        if (AbstractC4772sd1.g()) {
            I61 i61 = new I61(context, ca1, tabContentManager, ja1, viewGroup2, this, nd1, new C0861Oc1(this), pq0, gp0);
            this.L = i61;
            nd1.p = i61;
        } else {
            this.L = null;
        }
        if (i == 0) {
            if (CachedFeatureFlags.isEnabled("CloseTabSuggestions") || (AbstractC4772sd1.g() && !C3919nd1.k())) {
                b91.k(3, new L70(R.layout.f41730_resource_name_obfuscated_RES_2131624482), new C0922Pc1());
            }
            if (TextUtils.equals(AbstractC4772sd1.b.c(), "NewTabTile")) {
                b91.k(5, new L70(R.layout.f39900_resource_name_obfuscated_RES_2131624299), new C0983Qc1());
            }
            if (AbstractC4772sd1.e()) {
                b91.k(6, new L70(R.layout.f40910_resource_name_obfuscated_RES_2131624400), new C1044Rc1());
            }
        }
        if (CachedFeatureFlags.isEnabled("InstantStart") || (AbstractC4772sd1.d.c() && i != 2)) {
            this.V = new X51(ca1);
        }
        this.H = vi0;
        ((ChromeActivity) vi0).e1.add(tc1);
        this.G = m2;
        m2.a(this);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        ((ChromeActivity) this.H).e1.remove(this.b0);
        this.I.destroy();
        C5639xj0 xj0 = this.O;
        for (int i = 0; i < xj0.b.size(); i++) {
            ((AbstractC4110ok0) xj0.b.get(i)).f10572a.c(xj0.f11629a);
        }
        this.F.b();
        I61 i61 = this.L;
        if (i61 != null) {
            i61.a();
        }
        C4978tp1 tp1 = this.S;
        if (tp1 != null) {
            AbstractC0124Ca1 ca1 = tp1.b;
            if (ca1 != null) {
                ((AbstractC0246Ea1) ca1).f.c(tp1.e);
                ((I71) ((AbstractC0246Ea1) tp1.b).c.g(false)).g.c(tp1.d);
                ((I71) ((AbstractC0246Ea1) tp1.b).c.g(true)).g.c(tp1.d);
            }
            tp1.f.destroy();
        }
        C4352q71 q71 = this.c0;
        if (q71 != null) {
            q71.f11118a.getViewTreeObserver().removeOnGlobalLayoutListener(q71.e);
        }
        C2926ho0 ho0 = this.U;
        if (ho0 != null) {
            C3438ko0 ko0 = ho0.b;
            ((AbstractC0246Ea1) ko0.f10305a).f.c(ko0.b);
        }
        C2920hm0 hm0 = this.K;
        ((AbstractC0246Ea1) hm0.b).f.c(hm0.c);
        C0371Gb1 gb1 = this.Q;
        if (gb1 != null) {
            gb1.a();
        }
        C3919nd1 nd1 = this.f9158J;
        ((AbstractC0246Ea1) nd1.f).f.c(nd1.h);
        ((C1551Zj) nd1.j).Y.c(nd1.k);
        ((AbstractC0246Ea1) nd1.f).c.h(nd1.g);
        nd1.n.b.c(nd1.o);
        this.G.b(this);
        X51 x51 = this.V;
        if (x51 != null) {
            x51.d.destroy();
            AbstractC3568la1 g = ((AbstractC0246Ea1) x51.b).c.g(false);
            if (g != null) {
                g.c.c(x51.c);
            }
            ((AbstractC0246Ea1) x51.b).f.c(x51.e);
        }
    }

    @Override // defpackage.AbstractC5789yc1
    public long f() {
        return this.I.G.s1;
    }

    @Override // defpackage.AbstractC5789yc1
    public int g() {
        return this.I.G.l1;
    }

    @Override // defpackage.AbstractC5959zc1
    public AbstractC5279vc1 h() {
        return this.f9158J;
    }

    @Override // defpackage.AbstractC5959zc1
    public void i(AbstractC5449wc1 wc1) {
        this.f9158J.r = wc1;
    }

    @Override // defpackage.AbstractC5789yc1
    public void j() {
        C1795b91 b91 = this.I;
        TabListRecyclerView tabListRecyclerView = b91.G;
        View$OnLayoutChangeListenerC2948hv1 hv1 = tabListRecyclerView.q1;
        if (hv1 != null) {
            hv1.H = null;
            tabListRecyclerView.F0();
        }
        b91.F.c = false;
        C3919nd1 nd1 = this.f9158J;
        nd1.j();
        nd1.f10505a.postDelayed(nd1.b, (long) nd1.j());
        nd1.h();
        nd1.f10505a.postDelayed(nd1.c, (long) nd1.h());
    }

    @Override // defpackage.AbstractC5789yc1
    public int k() {
        return this.I.i();
    }

    @Override // defpackage.AbstractC5789yc1
    public boolean l() {
        boolean z;
        C3919nd1 nd1 = this.f9158J;
        nd1.f10505a.removeCallbacks(nd1.b);
        nd1.f10505a.removeCallbacks(nd1.c);
        if (!((AbstractC0246Ea1) nd1.f).h) {
            z = false;
        } else {
            z = AbstractC4772sd1.j() ? ((C1349Wc1) nd1.d).t(((AbstractC0246Ea1) nd1.f).c.d(), false, nd1.y) : false;
            nd1.p();
        }
        C1795b91 b91 = this.I;
        TabListRecyclerView tabListRecyclerView = b91.G;
        ValueAnimator valueAnimator = tabListRecyclerView.m1;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator valueAnimator2 = tabListRecyclerView.n1;
        if (valueAnimator2 != null) {
            valueAnimator2.end();
        }
        tabListRecyclerView.D0();
        tabListRecyclerView.w1 = tabListRecyclerView.y0;
        tabListRecyclerView.s0(null);
        I91 i91 = b91.F;
        Objects.requireNonNull(i91);
        if (AbstractC4772sd1.j() && ((AbstractC0246Ea1) i91.i).h) {
            for (int i = 0; i < i91.g.size(); i++) {
                if (((C4765sb0) i91.g.get(i)).b.f(J91.f8274a) == 0) {
                    UH0 uh0 = ((C4765sb0) i91.g.get(i)).b;
                    QH0 qh0 = AbstractC5106ub1.h;
                    uh0.h(qh0);
                    ((C4765sb0) i91.g.get(i)).b.j(qh0, false);
                }
            }
        }
        return z;
    }

    @Override // defpackage.AbstractC5789yc1
    public Rect m(boolean z) {
        I61 i61 = this.L;
        if (i61 == null || !i61.d()) {
            if (z) {
                this.I.p();
            }
            return this.I.f9518J;
        }
        I61 i612 = this.L;
        i612.b.p();
        C1795b91 b91 = i612.b;
        Rect rect = b91.f9518J;
        Rect h = b91.h();
        rect.offset(h.left, h.top);
        Rect h2 = this.I.h();
        rect.offset(-h2.left, -h2.top);
        return rect;
    }

    @Override // defpackage.AbstractC5959zc1
    public AbstractC5789yc1 n() {
        return this;
    }

    @Override // defpackage.AbstractC5959zc1
    public void o(Context context, TabContentManager tabContentManager, IJ ij, AbstractC4928tY0 ty0, C2746gl0 gl0) {
        if (!this.Y) {
            int i = this.N;
            if (i == 2) {
                i = 0;
            }
            C0371Gb1 gb1 = new C0371Gb1(context, this.a0, this.M, tabContentManager, i);
            this.Q = gb1;
            this.f9158J.q = gb1.i;
            String string = context.getString(R.string.f63170_resource_name_obfuscated_RES_2131953634);
            C0676Lb1 lb1 = this.Q.i;
            this.R = new C1288Vc1(this, string, R.plurals.f42620_resource_name_obfuscated_RES_2131820554, 2, new C5616xb1(lb1, 1), new C0310Fb1(lb1));
            this.I.j(ij);
            I61 i61 = this.L;
            if (i61 != null) {
                i61.c(context, this.M, tabContentManager, this.I.F.F);
            }
            C2920hm0 hm0 = this.K;
            hm0.p.e(((AbstractC0246Ea1) hm0.b).i().b());
            if (AbstractC4772sd1.g()) {
                this.S = new C4978tp1(context, this.M, ty0);
            } else {
                this.S = null;
            }
            if (this.N == 0) {
                if (CachedFeatureFlags.isEnabled("CloseTabSuggestions")) {
                    this.T = new C4599rc1(this.M, this.G);
                    C2891hc1 hc1 = new C2891hc1(context, this.M, this.Q.i);
                    this.T.N.b(hc1);
                    C5639xj0 xj0 = this.O;
                    xj0.b.add(hc1);
                    hc1.f10572a.b(xj0.f11629a);
                }
                if (TextUtils.equals(AbstractC4772sd1.b.c(), "NewTabTile")) {
                    this.U = new C2926ho0(this.M, this.X);
                }
                if (AbstractC4772sd1.g() && !C3919nd1.k()) {
                    C4352q71 q71 = new C4352q71(context, this.W, gl0);
                    this.c0 = q71;
                    U30 u30 = new U30(q71);
                    C5639xj0 xj02 = this.O;
                    xj02.b.add(u30);
                    u30.a(xj02.f11629a);
                }
                if (AbstractC4772sd1.e()) {
                    this.d0 = new BF0(context, gl0, this, this.M);
                    if (!CF0.d()) {
                        KF0 kf0 = new KF0(this.I, this.f9158J);
                        this.Z = kf0;
                        C5639xj0 xj03 = this.O;
                        xj03.b.add(kf0);
                        kf0.a(xj03.f11629a);
                        this.f9158J.s = this.Z;
                    }
                }
            }
            this.Y = true;
        }
    }

    @Override // defpackage.AbstractC5959zc1
    public Q31 p() {
        I61 i61 = this.L;
        i61.getClass();
        return new C1105Sc1(i61);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void q(int r6) {
        /*
        // Method dump skipped, instructions count: 174
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1349Wc1.q(int):void");
    }

    public void r() {
        this.I.l(3, 4);
    }

    public boolean t(N81 n81, boolean z, boolean z2) {
        ArrayList arrayList;
        Map map = C4384qI0.f11132a;
        if (n81 != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < n81.getCount(); i++) {
                arrayList.add(C4384qI0.a(n81.getTabAt(i)));
            }
        } else {
            arrayList = null;
        }
        return u(arrayList, z, z2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        if (r4.isEmpty() == false) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0027, code lost:
        if (r3.c.d().getCount() > 0) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean u(java.util.List r6, boolean r7, boolean r8) {
        /*
        // Method dump skipped, instructions count: 247
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1349Wc1.u(java.util.List, boolean, boolean):boolean");
    }

    public void v() {
        List a2 = this.O.a();
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) a2;
            if (i < arrayList.size()) {
                if (((C0089Bj0) arrayList.get(i)).f7751a != 3) {
                    this.I.g(3, ((C0089Bj0) arrayList.get(i)).b);
                }
                i++;
            } else {
                arrayList.size();
                return;
            }
        }
    }
}
