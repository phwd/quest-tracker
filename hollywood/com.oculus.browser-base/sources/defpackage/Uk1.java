package defpackage;

import J.N;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Uk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Uk1 extends AbstractC1740ar1 implements AbstractC0995Qg1, AbstractC1056Rg1, AbstractC0025Ai0, W {
    public final B90 A0;
    public final C5476wl1 B0;
    public C5976zi0 C0;
    public C2744gk1 D0;
    public P11 E0;
    public final D00 F;
    public PX F0;
    public final C5880z61 G;
    public C1550Zi1 G0;
    public final C2921hm1 H;
    public C0090Bk H0;
    public M9 I;
    public int I0;

    /* renamed from: J  reason: collision with root package name */
    public WS0 f9046J;
    public int J0;
    public final Vl1 K;
    public boolean K0;
    public final ToolbarControlContainer L;
    public boolean L0;
    public final AbstractC2230dk M;
    public Runnable M0;
    public final TT N;
    public boolean N0;
    public final C1078Rq0 O;
    public int O0;
    public final C1078Rq0 P = new C1078Rq0();
    public int P0;
    public final C1078Rq0 Q;
    public Q31 Q0;
    public final AbstractC0956Pq0 R;
    public final GP0 R0;
    public C1078Rq0 S;
    public AbstractC2451f01 S0;
    public final C1078Rq0 T;
    public AbstractC2280e01 T0;
    public AbstractC0124Ca1 U;
    public H7 U0;
    public AbstractC0612Ka1 V;
    public AbstractC1509Ys0 V0;
    public AbstractC0956Pq0 W;
    public AbstractC1509Ys0 W0;
    public Y2 X;
    public AbstractC1267Ut0 X0;
    public final C1595a3 Y;
    public C1078Rq0 Y0;
    public final LocationBarModel Z;
    public L71 Z0;
    public AbstractC0956Pq0 a0;
    public final Callback b0;
    public AbstractC0383Gf1 c0;
    public AbstractC3225ja0 d0;
    public DQ e0;
    public D70 f0;
    public AbstractC0512Ii g0;
    public EQ h0;
    public int i0;
    public AbstractC2642g70 j0;
    public I70 k0;
    public AbstractC1509Ys0 l0;
    public C1128Sl m0;
    public KO0 n0;
    public final C3802mv1 o0;
    public G2 p0;
    public final Callback q0;
    public final Handler r0;
    public final I7 s0;
    public final WindowAndroid t0;
    public final AbstractC5207v9 u0;
    public final CompositorViewHolder v0;
    public final AbstractC1888bk w0;
    public final UT x0;
    public C4762sa0 y0;
    public ComponentCallbacks z0;

    public Uk1(I7 i7, AbstractC1888bk bkVar, UT ut, ToolbarControlContainer toolbarControlContainer, CompositorViewHolder compositorViewHolder, Callback callback, C2921hm1 hm1, C1343Wa1 wa1, AbstractC0956Pq0 pq0, C5609xZ xZVar, List list, C1595a3 a3Var, GP0 gp0, ActionMode$CallbackC5300vj1 vj1, DQ dq, AbstractC0956Pq0 pq02, AbstractC0956Pq0 pq03, Q31 q31, AbstractC1509Ys0 ys0, AbstractC1509Ys0 ys02, boolean z, AbstractC0956Pq0 pq04, AbstractC1509Ys0 ys03, AbstractC0956Pq0 pq05, AbstractC1509Ys0 ys04, AbstractC1509Ys0 ys05, WindowAndroid windowAndroid, Q31 q312, boolean z2, Q31 q313, P11 p11, AbstractC5207v9 v9Var, M2 m2, Q31 q314) {
        AbstractC1377Wn0 wn0;
        C1078Rq0 rq0;
        C1078Rq0 rq02 = new C1078Rq0();
        this.O = rq02;
        C1078Rq0 rq03 = new C1078Rq0();
        this.Q = rq03;
        this.S = new C1078Rq0();
        this.T = new C1078Rq0();
        this.i0 = 0;
        this.m0 = new C1128Sl();
        Handler handler = new Handler();
        this.r0 = handler;
        this.I0 = -1;
        this.J0 = -1;
        this.N0 = true;
        this.Y0 = new C1078Rq0();
        TraceEvent.Y("ToolbarManager.ToolbarManager", null);
        this.s0 = i7;
        this.t0 = windowAndroid;
        this.v0 = compositorViewHolder;
        this.w0 = bkVar;
        this.x0 = ut;
        C3802mv1 mv1 = new C3802mv1(i7.c0(), toolbarControlContainer, i7.findViewById(R.id.action_bar_black_background));
        this.o0 = mv1;
        this.R0 = gp0;
        this.W = pq04;
        this.R = pq05;
        this.V0 = ys04;
        this.W0 = ys05;
        this.u0 = v9Var;
        this.E0 = p11;
        this.q0 = callback;
        Wj1 wj1 = (Wj1) i7.findViewById(R.id.toolbar);
        if (wj1 instanceof ToolbarPhone) {
            wn0 = new Ek1(this);
        } else if (wj1 instanceof ToolbarTablet) {
            wn0 = new Fk1(this);
        } else {
            wn0 = AbstractC1377Wn0.f9173a;
        }
        LocationBarModel locationBarModel = new LocationBarModel(i7, wn0, new Xj1(), new C3085ik1(), new Ik1(this));
        this.Z = locationBarModel;
        this.L = toolbarControlContainer;
        this.a0 = pq03;
        C4793sk1 sk1 = new C4793sk1(this);
        this.b0 = sk1;
        ((C1078Rq0) pq03).l(sk1);
        this.l0 = ys0;
        ys0.g(this.m0.b(new C5303vk1(this)));
        Jk1 jk1 = new Jk1(this);
        this.z0 = jk1;
        i7.registerComponentCallbacks(jk1);
        D00 d00 = new D00();
        this.F = d00;
        C5880z61 z61 = new C5880z61();
        this.G = z61;
        this.H = hm1;
        hm1.f8908J.b(this);
        M9 m9 = new M9(i7);
        this.I = m9;
        m9.K.b(this);
        this.f9046J = new WS0(i7);
        this.Y = a3Var;
        C5476wl1 wl1 = new C5476wl1(new C5473wk1(locationBarModel), new C5643xk1(this), pq02, this.S, new C5813yk1(), new RunnableC5983zk1(this));
        this.B0 = wl1;
        this.H0 = ((C1551Zj) bkVar).G;
        AbstractC1117Sg1 sg1 = DeviceFormFactor.a(i7) ? this.I : hm1;
        M9 m92 = this.I;
        compositorViewHolder.getClass();
        Ak1 ak1 = new Ak1(compositorViewHolder);
        this.C0 = new C5976zi0(ys02, this.H0, windowAndroid, new Bk1(this), ak1, z, q312, z2 ? this.f9046J : sg1, R.id.menu_button_wrapper);
        C5976zi0 zi0 = new C5976zi0(ys02, this.H0, windowAndroid, new Yj1(this), ak1, z, q312, m92, R.id.none);
        N30 n30 = compositorViewHolder.f10633J;
        Vr1 vr1 = new Vr1(i7, handler, new C2061ck1());
        AbstractC1509Ys0 ys06 = this.l0;
        M9 m93 = this.I;
        C5976zi0 zi02 = this.C0;
        C0330Fi0 fi0 = zi02.c;
        if (fi0 == null) {
            rq0 = null;
        } else {
            rq0 = fi0.d;
        }
        Vl1 vl1 = new Vl1(toolbarControlContainer, wj1, locationBarModel, wl1, vr1, list, ys06, sg1, m93, zi02, zi0, rq0, this.W, rq02, rq03, new C2232dk1(n30), new C2402ek1(this, xZVar), new C2573fk1(compositorViewHolder));
        this.D0 = new C2744gk1(this);
        QX.c().c.b(this.D0);
        this.D0.a();
        if ((wj1 instanceof ToolbarPhone) && AbstractC2793h01.b()) {
            xZVar.N.b(new C2915hk1(this));
        }
        HomeButton g = wj1.g();
        if (g != null) {
            C1078Rq0 rq04 = this.O;
            QX c = QX.c();
            c.getClass();
            C3256jk1 jk12 = new C3256jk1(c);
            C1078Rq0 rq05 = this.P;
            NX nx = new NX(g);
            rq04.l(nx);
            rq05.l(nx);
            g.H = jk12;
            g.I = rq05;
            g.d();
        }
        this.K = vl1;
        G2 g2 = new G2(i7, mv1, vj1);
        this.p0 = g2;
        g2.e = (float) wj1.k();
        if (wj1 instanceof CustomTabToolbar) {
            CustomTabToolbar customTabToolbar = (CustomTabToolbar) wj1;
            ActionMode$CallbackC5300vj1 vj12 = this.p0.b;
            customTabToolbar.r0 = locationBarModel;
            WB wb = new WB(customTabToolbar, locationBarModel, vj12, (UrlBarApi26) customTabToolbar.a0);
            customTabToolbar.q0 = wb;
            this.d0 = wb;
        } else {
            C3909na0 na0 = new C3909na0(i7.findViewById(R.id.location_bar), wj1, pq02, locationBarModel, this.p0.b, new Uy1(i7.getWindow()), windowAndroid, a3Var, q313, pq0, d00, m2, new Zj1(q314));
            wj1.K(na0);
            this.d0 = na0;
        }
        if (this.d0.Q() != null) {
            ((View$OnKeyListenerC0001Aa0) this.d0.Q()).F.M.b(this);
        }
        this.y0 = new C4762sa0(gp0, new Sk1(wa1), i7, locationBarModel, new RunnableC1710ak1(this), compositorViewHolder);
        if (this.d0.Q() != null) {
            ((View$OnKeyListenerC0001Aa0) this.d0.Q()).p(this.y0);
        }
        this.A0 = new B90(this.T, wj1.L);
        wj1.G.b(p11);
        this.X = new Kk1(this, a3Var);
        this.V = new Lk1(this);
        this.g0 = new Mk1(this);
        Ok1 ok1 = new Ok1(this);
        this.M = ok1;
        ((C1551Zj) this.w0).Y.b(ok1);
        Pk1 pk1 = new Pk1(this);
        this.N = pk1;
        this.x0.b(pk1);
        this.h0 = new Qk1(this);
        this.k0 = new Rk1(this);
        this.n0 = new Ck1(this);
        this.X0 = new Dk1(this);
        wj1.N(z61);
        C4261pd1 pd1 = vl1.b;
        if (pd1 != null) {
            pd1.d = z61;
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone = pd1.i;
            if (tabSwitcherModeTTPhone != null) {
                tabSwitcherModeTTPhone.H = z61;
                ToggleTabStackButton toggleTabStackButton = tabSwitcherModeTTPhone.N;
                if (toggleTabStackButton != null) {
                    toggleTabStackButton.R = z61;
                    z61.a(toggleTabStackButton);
                }
                IncognitoToggleTabLayout incognitoToggleTabLayout = tabSwitcherModeTTPhone.K;
                if (incognitoToggleTabLayout != null) {
                    incognitoToggleTabLayout.H0 = z61;
                    z61.a(incognitoToggleTabLayout);
                }
            }
        }
        T01 t01 = vl1.c;
        if (t01 != null) {
            C0435Hc1 hc1 = t01.g;
            if (hc1 != null) {
                hc1.d = z61;
                hc1.a();
                C0374Gc1 gc1 = new C0374Gc1(hc1);
                hc1.e = gc1;
                hc1.d.a(gc1);
            } else {
                t01.i = z61;
            }
        }
        D00 d002 = this.F;
        C4261pd1 pd12 = vl1.b;
        if (pd12 != null) {
            pd12.f = d002;
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone2 = pd12.i;
            if (tabSwitcherModeTTPhone2 != null) {
                tabSwitcherModeTTPhone2.f(d002);
            }
        } else {
            T01 t012 = vl1.c;
            if (t012 != null) {
                t012.f8931a.f9248a.m(Z01.f9312a, d002);
            }
        }
        C0283Ep h = C0283Ep.h();
        h.c().b(this);
        l(h.d());
        LocationBarModel locationBarModel2 = this.Z;
        boolean b = AbstractC2793h01.b();
        if (locationBarModel2.k != b) {
            locationBarModel2.k = b;
            locationBarModel2.y();
        }
        this.e0 = dq;
        dq.f.b(this.h0);
        ys03.g(this.m0.b(new C1890bk1(this)));
        TraceEvent.f0("ToolbarManager.ToolbarManager");
    }

    public static void f(Uk1 uk1) {
        if (uk1.Z.r()) {
            uk1.Z.d().Q();
        }
    }

    public static int g(Uk1 uk1) {
        int i = ((C1551Zj) uk1.w0).M;
        View findViewById = uk1.L.findViewById(R.id.toolbar_shadow);
        return i - (uk1.L.getHeight() - (findViewById != null ? findViewById.getHeight() : 0));
    }

    public static void h(Uk1 uk1, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) uk1.L.getLayoutParams();
        if (marginLayoutParams.topMargin != i) {
            marginLayoutParams.topMargin = i;
            uk1.L.setLayoutParams(marginLayoutParams);
        }
    }

    public static void i(Uk1 uk1, boolean z) {
        uk1.Z.z();
        if (z) {
            uk1.Z.B();
            uk1.r();
        }
    }

    @Override // defpackage.AbstractC0995Qg1
    public void a(int i, boolean z) {
        if (this.N0) {
            if (this.O0 != i) {
                this.O0 = i;
                LocationBarModel locationBarModel = this.Z;
                locationBarModel.g = i;
                locationBarModel.C();
                locationBarModel.y();
                this.K.f9104a.y(z);
                this.f9046J.b(i, z);
            }
        }
    }

    @Override // defpackage.AbstractC1056Rg1
    public void c(ColorStateList colorStateList, boolean z) {
        q();
    }

    @Override // defpackage.AbstractC1740ar1
    public void e(boolean z) {
        this.K.f9104a.D(z);
        DQ dq = this.e0;
        if (dq != null && z) {
            dq.a();
        }
        C0090Bk bk = this.H0;
        if (bk != null) {
            if (z) {
                this.I0 = bk.r(this.I0);
            } else {
                bk.p(this.I0);
            }
            this.q0.onResult(Boolean.valueOf(z));
        }
    }

    public AbstractC1834bO j() {
        return this.d0.Q();
    }

    public View k() {
        return this.C0.e.F;
    }

    @Override // defpackage.W
    public void l(boolean z) {
        Vl1 vl1 = this.K;
        vl1.f9104a.s(z);
        C4261pd1 pd1 = vl1.b;
        if (pd1 != null) {
            pd1.h = z;
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone = pd1.i;
            if (tabSwitcherModeTTPhone != null) {
                tabSwitcherModeTTPhone.d(z);
                return;
            }
            return;
        }
        T01 t01 = vl1.c;
        if (t01 != null) {
            Y01 y01 = t01.f8931a;
            y01.f9248a.j(Z01.k, z);
            y01.d();
        }
    }

    public void m(D70 d70, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3, View.OnClickListener onClickListener4, Q31 q31) {
        ToggleTabStackButton toggleTabStackButton;
        TraceEvent.Y("ToolbarManager.initializeWithNative", null);
        this.U = (AbstractC0124Ca1) ((C1078Rq0) this.W).H;
        this.Q0 = q31;
        View$OnLongClickListenerC0008Ac1 ac1 = new View$OnLongClickListenerC0008Ac1(new C0252Ec1(), new C3427kk1(this));
        Vl1 vl1 = this.K;
        d70.getClass();
        RunnableC3598lk1 lk1 = new RunnableC3598lk1(d70);
        C1078Rq0 rq0 = this.T;
        AbstractC1888bk bkVar = this.w0;
        C2921hm1 hm1 = this.H;
        C4261pd1 pd1 = vl1.b;
        if (pd1 != null) {
            pd1.b = onClickListener;
            TabSwitcherModeTTPhone tabSwitcherModeTTPhone = pd1.i;
            if (!(tabSwitcherModeTTPhone == null || (toggleTabStackButton = tabSwitcherModeTTPhone.N) == null)) {
                toggleTabStackButton.S = onClickListener;
            }
            pd1.c = onClickListener2;
            if (tabSwitcherModeTTPhone != null) {
                tabSwitcherModeTTPhone.G = onClickListener2;
            }
            AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) ((C1078Rq0) vl1.g).H;
            pd1.e = ca1;
            if (tabSwitcherModeTTPhone != null) {
                tabSwitcherModeTTPhone.I = ca1;
                IncognitoToggleTabLayout incognitoToggleTabLayout = tabSwitcherModeTTPhone.K;
                if (incognitoToggleTabLayout != null) {
                    incognitoToggleTabLayout.x(ca1);
                }
            }
            pd1.b();
            pd1.c();
        } else {
            T01 t01 = vl1.c;
            if (t01 != null) {
                t01.f8931a.f9248a.m(Z01.b, onClickListener2);
                T01 t012 = vl1.c;
                AbstractC0124Ca1 ca12 = (AbstractC0124Ca1) ((C1078Rq0) vl1.g).H;
                t012.e = ca12;
                Y01 y01 = t012.f8931a;
                y01.g = ca12;
                if (y01.i == null) {
                    y01.i = new W01(y01);
                }
                y01.f9248a.j(Z01.j, ((AbstractC0246Ea1) ca12).r());
                y01.a((C0517Ik) y01.f.get());
                ((AbstractC0246Ea1) y01.g).c(y01.i);
                T01 t013 = vl1.c;
                C0435Hc1 hc1 = t013.g;
                if (hc1 != null) {
                    hc1.f8165a.m(AbstractC0496Ic1.b, onClickListener);
                } else {
                    t013.k = onClickListener;
                }
                T01 t014 = vl1.c;
                TabSwitcherButtonView tabSwitcherButtonView = t014.h;
                if (tabSwitcherButtonView != null) {
                    tabSwitcherButtonView.setOnLongClickListener(ac1);
                } else {
                    t014.l = ac1;
                }
                T01 t015 = vl1.c;
                if (t015.d != null) {
                    t015.a();
                }
                Y01 y012 = t015.f8931a;
                y012.h = new V01(y012);
                AbstractC0444Hf1.a().b.b(y012.h);
                y012.n = AbstractC0444Hf1.a().e();
                y012.c(AbstractC0444Hf1.a().e());
            }
        }
        Wj1 wj1 = vl1.f9104a;
        AbstractC0124Ca1 ca13 = (AbstractC0124Ca1) ((C1078Rq0) vl1.g).H;
        wj1.O();
        vl1.f9104a.h().H();
        vl1.f9104a.L(onClickListener);
        vl1.f9104a.M(ac1);
        vl1.f9104a.F(onClickListener3);
        vl1.f9104a.H(null);
        vl1.f9104a.J(lk1);
        vl1.f9104a.w();
        if (C5052uE.c().g) {
            Context context = vl1.f9104a.getContext();
            ToolbarControlContainer toolbarControlContainer = vl1.h;
            toolbarControlContainer.getClass();
            Xl1 xl1 = new Xl1(context, d70, new Tl1(toolbarControlContainer), rq0, bkVar, vl1.i, hm1);
            vl1.j = xl1;
            d70.g(xl1);
            Wj1 wj12 = vl1.f9104a;
            Xl1 xl12 = vl1.j;
            wj12.T = xl12;
            boolean z = wj12.getVisibility() == 0;
            C2067cm1 cm1 = xl12.I;
            cm1.n = z;
            cm1.b();
        }
        this.K.f9104a.addOnAttachStateChangeListener(new Gk1(this));
        LocationBarModel locationBarModel = this.Z;
        Objects.requireNonNull(locationBarModel);
        locationBarModel.l = N.M8_Iwqb0(locationBarModel);
        this.f0 = d70;
        d70.R.b(this.n0);
        this.f0.Z.b.b(this.X0);
        C0330Fi0 fi0 = this.C0.c;
        if (fi0 != null && fi0.m) {
            fi0.n = new RunnableC0208Di0(fi0);
            C2249dq1.a().f(fi0.n);
        }
        AbstractC0444Hf1.a().j(new RunnableC3769mk1(this));
        this.L0 = true;
        ((AbstractC0246Ea1) this.U).c(this.V);
        o(this.Y.H);
        if (((AbstractC0246Ea1) this.U).h) {
            this.K0 = true;
        }
        if (this.K0 && this.L0) {
            this.K.f9104a.z();
        }
        C5880z61 z61 = this.G;
        AbstractC0124Ca1 ca14 = this.U;
        z61.b = ca14;
        C5200v61 v61 = new C5200v61(z61);
        z61.c = v61;
        ((AbstractC0246Ea1) ca14).c(v61);
        C5370w61 w61 = new C5370w61(z61);
        z61.d = w61;
        ((AbstractC0246Ea1) z61.b).c.a(w61);
        if (((AbstractC0246Ea1) z61.b).c.d() instanceof I71) {
            z61.e = new C5540x61(z61);
            ((I71) ((AbstractC0246Ea1) z61.b).c.d()).g.b(z61.e);
        }
        z61.b();
        D00 d00 = this.F;
        AbstractC0124Ca1 ca15 = this.U;
        d00.c = ca15;
        ((AbstractC0246Ea1) ca15).c(d00.b);
        d00.a(((AbstractC0246Ea1) d00.c).r());
        M9 m9 = this.I;
        D00 d002 = this.F;
        m9.N = d002;
        d002.f7854a.b(m9);
        m9.Q = d002.b();
        m9.e();
        Runnable runnable = this.M0;
        if (runnable != null) {
            runnable.run();
            this.M0 = null;
        }
        Tab j = ((AbstractC0246Ea1) this.U).j();
        if (!(j == null || j.l() == null || TextUtils.isEmpty(j.s()))) {
            this.L.f10790J.I = true;
        }
        if (N.M09VlOh_("ToolbarIphAndroid")) {
            Vr1 vr1 = new Vr1(this.s0, this.r0, new C3940nk1());
            Tm1 a2 = Um1.a(Profile.b());
            View findViewById = this.L.findViewById(R.id.home_button);
            I7 i7 = this.s0;
            D00 d003 = this.F;
            d003.getClass();
            this.F0 = new PX(i7, findViewById, vr1, new C4111ok1(d003), this.V0, this.W0, new C4282pk1(), this.T, a2);
            I7 i72 = this.s0;
            D00 d004 = this.F;
            d004.getClass();
            C4453qk1 qk1 = new C4453qk1(d004);
            AbstractC1509Ys0 ys0 = this.V0;
            AbstractC1509Ys0 ys02 = this.W0;
            AbstractC1509Ys0 ys03 = this.l0;
            Vl1 vl12 = this.K;
            vl12.getClass();
            this.G0 = new C1550Zi1(i72, (ToggleTabStackButton) this.L.findViewById(R.id.tab_switcher_button), vr1, qk1, ys0, ys02, ys03, new C4623rk1(vl12), this.T, a2);
        }
        this.T.m(this.Y.H);
        TraceEvent.f0("ToolbarManager.initializeWithNative");
    }

    public final void n() {
        this.K.f9104a.B();
        if (this.Z.r()) {
            this.Z.d().Q();
        }
    }

    public final void o(Tab tab) {
        LocationBarModel locationBarModel = this.Z;
        boolean z = locationBarModel.i;
        Tab d = locationBarModel.d();
        boolean a2 = tab != null ? tab.a() : ((AbstractC0246Ea1) this.U).r();
        LocationBarModel locationBarModel2 = this.Z;
        locationBarModel2.f = tab;
        if (locationBarModel2.i != a2) {
            locationBarModel2.i = a2;
            Iterator it = locationBarModel2.m.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC4592ra0) uq0.next()).k();
            }
        }
        locationBarModel2.C();
        locationBarModel2.A();
        locationBarModel2.B();
        locationBarModel2.y();
        locationBarModel2.z();
        this.Z.B();
        this.Z.z();
        this.Z.B();
        r();
        if (!(d == null || z == a2 || !DeviceFormFactor.a(this.s0))) {
            this.p0.b();
        }
        if (!(d == tab && z == a2)) {
            int a3 = AbstractC2934hr.a(this.s0.getResources(), a2);
            if (tab != null) {
                a3 = this.H.e(tab, tab.m());
            }
            a(a3, false);
            n();
            if (tab != null) {
                this.K.f9104a.x();
            }
            p(false, 12);
            Tab d2 = this.Z.d();
            if (d2 != null) {
                d2.Q();
            }
        }
        r();
    }

    public void p(boolean z, int i) {
        if (this.L0 && this.d0.Q() != null) {
            boolean z2 = ((View$OnKeyListenerC0001Aa0) this.d0.Q()).F.T;
            ((View$OnKeyListenerC0001Aa0) this.d0.Q()).F.n(z, null, i);
            if (z2 && z) {
                this.d0.F();
            }
        }
    }

    public final void q() {
        Tab d = this.Z.d();
        BookmarkBridge bookmarkBridge = (BookmarkBridge) ((C1078Rq0) this.a0).H;
        boolean z = true;
        boolean z2 = (d == null || bookmarkBridge == null || !bookmarkBridge.e(d)) ? false : true;
        if (!(d == null || bookmarkBridge == null || bookmarkBridge.f())) {
            z = false;
        }
        this.K.f9104a.T(z2, z);
    }

    public final void r() {
        B9 b9;
        Menu menu;
        View childAt;
        Tab d = this.Z.d();
        boolean z = true;
        boolean z2 = d != null && C3372kO0.W(d);
        Vl1 vl1 = this.K;
        vl1.f9104a.U();
        vl1.d.b();
        this.K.f9104a.S(d != null && d.h());
        this.K.f9104a.V(d != null && d.k());
        Tab d2 = this.Z.d();
        if (z2 || ((d2 == null || !d2.d()) && this.L0)) {
            z = false;
        }
        this.K.f9104a.X(z);
        C0330Fi0 fi0 = this.C0.c;
        if (!(fi0 == null || (b9 = fi0.b) == null || fi0.e == null)) {
            ((F9) b9).j(z);
            View$OnKeyListenerC2476f9 f9Var = ((C5887z9) fi0.e).F;
            if (!(f9Var == null || f9Var.O == null || (menu = f9Var.F) == null || f9Var.M == null || f9Var.N == null)) {
                int size = menu.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        i = -1;
                        break;
                    } else if (f9Var.F.getItem(i).getItemId() == R.id.icon_row_menu_id) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i != -1) {
                    int firstVisiblePosition = f9Var.N.getFirstVisiblePosition();
                    int lastVisiblePosition = f9Var.N.getLastVisiblePosition();
                    if (i >= firstVisiblePosition && i <= lastVisiblePosition && (childAt = f9Var.N.getChildAt(i - firstVisiblePosition)) != null) {
                        f9Var.N.getAdapter().getView(i, childAt, f9Var.N);
                    }
                }
            }
        }
        q();
        MenuButton menuButton = this.K.e.e;
        if (menuButton != null) {
            menuButton.setVisibility(0);
        }
    }
}
