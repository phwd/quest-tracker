package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import com.oculus.browser.R;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.NewTabButton;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.chrome.browser.vr.VrModuleProvider;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.components.messages.MessageContainer;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: GN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GN0 extends AbstractC2112d10 implements AbstractC4371qE, AbstractC4968tm0, AbstractC1244Ui0, AbstractC4356q9 {
    public ChromeActivity F;
    public C5037u9 G;
    public final AbstractC1305Vi0 H;
    public final C1343Wa1 I;

    /* renamed from: J  reason: collision with root package name */
    public C1595a3 f8088J;
    public AbstractC0956Pq0 K;
    public DQ L;
    public EQ M;
    public Callback N;
    public C1328Vt0 O;
    public AbstractC1267Ut0 P;
    public AbstractC1509Ys0 Q;
    public AbstractC2642g70 R;
    public I70 S;
    public C2921hm1 T;
    public Callback U;
    public Uk1 V;
    public Q31 W;
    public AbstractC2404el0 X;
    public AbstractC3976nw1 Y;
    public C0515Ij Z;
    public C5638xj a0;
    public View$OnClickListenerC5098uY0 b0;
    public GP0 c0;
    public JF d0;
    public List e0;
    public C5609xZ f0;
    public final ActionMode$CallbackC5300vj1 g0;
    public C1078Rq0 h0 = new C1078Rq0();
    public final AbstractC0956Pq0 i0;
    public final AbstractC0956Pq0 j0;
    public final C1570Zs0 k0;
    public AbstractC0576Jj l0;
    public final Q31 m0;
    public final C1128Sl n0;
    public C1551Zj o0;
    public AbstractC0956Pq0 p0;
    public final AbstractC1509Ys0 q0;
    public AbstractC1406Xb0 r0;
    public C0638Kj0 s0;
    public C2253ds t0;
    public AbstractC1509Ys0 u0;
    public C1570Zs0 v0 = new C1570Zs0();
    public Q31 w0;
    public final C1078Rq0 x0;
    public final Y2 y0;

    public GN0(ChromeActivity chromeActivity, Callback callback, AbstractC0956Pq0 pq0, C1595a3 a3Var, AbstractC0956Pq0 pq02, AbstractC0956Pq0 pq03, Q31 q31, AbstractC0956Pq0 pq04, AbstractC1509Ys0 ys0, AbstractC1509Ys0 ys02, AbstractC1509Ys0 ys03, Q31 q312) {
        C1078Rq0 rq0 = new C1078Rq0();
        this.x0 = rq0;
        C1128Sl sl = new C1128Sl();
        this.n0 = sl;
        this.F = chromeActivity;
        this.U = callback;
        chromeActivity.Y.a(this);
        ChromeActivity chromeActivity2 = this.F;
        Objects.requireNonNull(chromeActivity2);
        this.H = chromeActivity2;
        chromeActivity2.e1.add(this);
        this.f8088J = a3Var;
        C2175dN0 dn0 = new C2175dN0(this);
        this.N = dn0;
        this.F.J0.l(dn0);
        this.K = pq0;
        C1343Wa1 wa1 = new C1343Wa1();
        this.I = wa1;
        new C2962i0(chromeActivity.Y, this.f8088J, wa1);
        if (Build.VERSION.SDK_INT >= 28) {
            new C5623xe(chromeActivity, chromeActivity.Y, this.f8088J);
        }
        this.i0 = pq02;
        this.j0 = pq03;
        this.k0 = new C1570Zs0();
        this.m0 = q31;
        this.g0 = new ActionMode$CallbackC5300vj1();
        this.p0 = pq04;
        this.h0.m(Boolean.FALSE);
        this.Q = ys03;
        ys03.g(sl.b(new C4054oN0(this)));
        this.q0 = ys0;
        this.u0 = ys02;
        this.w0 = q312;
        this.y0 = new C5586xN0(this, this.f8088J);
        ChromeActivity chromeActivity3 = this.F;
        chromeActivity3.getClass();
        C4225pN0 pn0 = new C4225pN0(chromeActivity3);
        ChromeActivity chromeActivity4 = this.F;
        chromeActivity4.getClass();
        this.T = new C2921hm1(chromeActivity3, rq0, pn0, new C4396qN0(chromeActivity4), new C4566rN0());
    }

    public static void h(GN0 gn0) {
        C5037u9 u9Var = gn0.G;
        if (u9Var != null) {
            u9Var.e.f();
        }
    }

    @Override // defpackage.AbstractC4356q9
    public boolean O() {
        if (this.m0.get() != null) {
            C1796bA bAVar = ((ContextualSearchManager) this.m0.get()).T;
            if (bAVar != null && bAVar.N()) {
                return false;
            }
        }
        DQ dq = this.L;
        if (dq != null) {
            BQ bq = dq.f7890a;
            return !(bq != null && bq.getVisibility() == 0) || DeviceFormFactor.a(this.F);
        }
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        Animator animator;
        AppBarLayout appBarLayout;
        List list;
        P00 p00;
        this.n0.a();
        ((ChromeActivity) this.H).e1.remove(this);
        this.F.J0.I.c(this.N);
        AbstractC1406Xb0 xb0 = this.r0;
        if (xb0 != null) {
            ((C0820Nj0) xb0).j(7);
            AbstractC0881Oj0.f8643a.b(this.r0);
            this.r0 = null;
        }
        C2253ds dsVar = this.t0;
        if (dsVar != null) {
            dsVar.d.f(dsVar.i);
            dsVar.c.Y.c(dsVar.f);
            AbstractC2642g70 g70 = dsVar.g;
            if (g70 != null) {
                ((D70) g70).Q.c(dsVar.j);
            }
            AbstractC0124Ca1 ca1 = dsVar.h;
            if (ca1 != null) {
                ((AbstractC0246Ea1) ca1).c.h(dsVar.k);
            }
            dsVar.h = null;
            dsVar.g = null;
            dsVar.f9813a = null;
            dsVar.b = null;
            dsVar.c = null;
            dsVar.d = null;
            this.t0 = null;
        }
        C0638Kj0 kj0 = this.s0;
        if (kj0 != null) {
            kj0.G.Y.c(kj0);
            kj0.F = null;
            kj0.G = null;
            this.s0 = null;
        }
        C1328Vt0 vt0 = this.O;
        if (vt0 != null) {
            vt0.b.c(this.P);
        }
        AbstractC2642g70 g702 = this.R;
        if (g702 != null) {
            ((D70) g702).Q.c(this.S);
            this.R = null;
        }
        Uk1 uk1 = this.V;
        if (uk1 != null) {
            if (uk1.L0) {
                uk1.e0.f.c(uk1.h0);
            }
            if (uk1.W != null) {
                uk1.W = null;
            }
            AbstractC0124Ca1 ca12 = uk1.U;
            if (ca12 != null) {
                ((AbstractC0246Ea1) ca12).f.c(uk1.V);
            }
            AbstractC0956Pq0 pq0 = uk1.a0;
            if (pq0 != null) {
                BookmarkBridge bookmarkBridge = (BookmarkBridge) ((C1078Rq0) pq0).H;
                if (bookmarkBridge != null) {
                    bookmarkBridge.e.c(uk1.g0);
                }
                ((C1078Rq0) uk1.a0).I.c(uk1.b0);
                uk1.a0 = null;
            }
            if (uk1.c0 != null) {
                AbstractC0444Hf1.a().b.c(uk1.c0);
                uk1.c0 = null;
            }
            AbstractC2642g70 g703 = uk1.j0;
            if (g703 != null) {
                ((D70) g703).Q.c(uk1.k0);
                uk1.j0 = null;
            }
            if (uk1.l0 != null) {
                uk1.l0 = null;
            }
            D70 d70 = uk1.f0;
            if (d70 != null) {
                d70.R.c(uk1.n0);
                uk1.f0.Z.b.c(uk1.X0);
                uk1.f0 = null;
            }
            QX.c().c.c(uk1.D0);
            Object obj = uk1.S.H;
            if (obj != null) {
                C2568fj fjVar = (C2568fj) obj;
                L71 l71 = fjVar.b;
                if (l71 != null) {
                    O71 o71 = (O71) l71;
                    if (o71.R != null) {
                        o71.O.destroy();
                        I61 i61 = o71.N;
                        if (i61 != null) {
                            i61.a();
                        }
                        o71.M.b();
                        C2475f81 f81 = o71.P;
                        AbstractC0124Ca1 ca13 = f81.e;
                        if (ca13 != null) {
                            ((AbstractC0246Ea1) ca13).c.h(f81.c);
                            ((AbstractC0246Ea1) f81.e).f.c(f81.l);
                            if (f81.t != null) {
                                ((I71) ((AbstractC0246Ea1) f81.e).c.g(false)).g.c(f81.t);
                                ((I71) ((AbstractC0246Ea1) f81.e).c.g(true)).g.c(f81.t);
                            }
                        }
                        AbstractC0850Ny0 ny0 = f81.u;
                        if (ny0 != null) {
                            f81.m.b(ny0);
                        }
                        AbstractC1099Sa1 sa1 = f81.v;
                        if (sa1 != null) {
                            sa1.destroy();
                        }
                        AbstractC2260du0 du0 = f81.s;
                        if (du0 != null) {
                            ((AbstractC3838n70) du0).y0.c(f81.r);
                        }
                        C1128Sl sl = f81.q;
                        if (sl != null) {
                            sl.a();
                            f81.q = null;
                        }
                        Callback callback = f81.w;
                        if (callback != null) {
                            ((C1078Rq0) f81.p).I.c(callback);
                        }
                        f81.h.f8908J.c(f81.j);
                        f81.h.K.c(f81.k);
                        M2 m2 = o71.Q;
                        if (m2 != null) {
                            m2.b(o71);
                        }
                    }
                }
                C2910hj hjVar = fjVar.f9944a;
                hjVar.I.a();
                ((C1551Zj) hjVar.H).Y.c(hjVar);
                hjVar.K.u0().h(hjVar);
                AbstractC2642g70 g704 = hjVar.P;
                if (g704 != null) {
                    ((D70) g704).Q.c(hjVar);
                    hjVar.P = null;
                }
                uk1.S = null;
            }
            uk1.K.f9104a.G.c(uk1.E0);
            Vl1 vl1 = uk1.K;
            Xl1 xl1 = vl1.j;
            if (xl1 != null) {
                C0297Ew ew = xl1.H;
                ew.b.f9530a.c(ew.e);
                ew.d.I.c(ew.f);
                C2067cm1 cm1 = xl1.I;
                cm1.l.a();
                cm1.f.a();
                cm1.g.onResult(null);
                ((C1078Rq0) cm1.e).I.c(cm1.g);
                ((D70) cm1.b).Q.c(cm1.c);
                ((C1551Zj) cm1.h).Y.c(cm1.i);
                C2408em1 em1 = xl1.G;
                N.MPFnESYL(em1.F, em1);
                em1.G = 0;
                vl1.j = null;
            }
            vl1.f9104a.d();
            C4261pd1 pd1 = vl1.b;
            if (pd1 != null) {
                TabSwitcherModeTTPhone tabSwitcherModeTTPhone = pd1.i;
                if (tabSwitcherModeTTPhone != null) {
                    D00 d00 = tabSwitcherModeTTPhone.f10789J;
                    if (d00 != null) {
                        d00.f7854a.c(tabSwitcherModeTTPhone);
                        tabSwitcherModeTTPhone.f10789J = null;
                    }
                    NewTabButton newTabButton = tabSwitcherModeTTPhone.M;
                    if (newTabButton != null) {
                        D00 d002 = newTabButton.K;
                        if (d002 != null) {
                            d002.f7854a.c(newTabButton);
                            newTabButton.K = null;
                        }
                        tabSwitcherModeTTPhone.M = null;
                    }
                    ToggleTabStackButton toggleTabStackButton = tabSwitcherModeTTPhone.N;
                    if (toggleTabStackButton != null) {
                        C5880z61 z61 = toggleTabStackButton.R;
                        if (z61 != null) {
                            z61.f11721a.c(toggleTabStackButton);
                        }
                        tabSwitcherModeTTPhone.N = null;
                    }
                    IncognitoToggleTabLayout incognitoToggleTabLayout = tabSwitcherModeTTPhone.K;
                    if (incognitoToggleTabLayout != null) {
                        AbstractC0124Ca1 ca14 = incognitoToggleTabLayout.G0;
                        if (ca14 != null) {
                            ((AbstractC0246Ea1) ca14).f.c(incognitoToggleTabLayout.I0);
                        }
                        C5880z61 z612 = incognitoToggleTabLayout.H0;
                        if (z612 != null) {
                            z612.f11721a.c(incognitoToggleTabLayout);
                        }
                        tabSwitcherModeTTPhone.K = null;
                    }
                    pd1.i = null;
                }
                AbstractC0124Ca1 ca15 = pd1.e;
                if (!(ca15 == null || (p00 = pd1.j) == null)) {
                    ((AbstractC0246Ea1) ca15).g.c(p00);
                }
                C5976zi0 zi0 = pd1.g;
                if (zi0 != null) {
                    zi0.a();
                    pd1.g = null;
                }
            } else {
                T01 t01 = vl1.c;
                if (t01 != null) {
                    Y01 y01 = t01.f8931a;
                    if (y01.h != null) {
                        AbstractC0444Hf1.a().b.c(y01.h);
                    }
                    AbstractC0612Ka1 ka1 = y01.i;
                    if (ka1 != null) {
                        ((AbstractC0246Ea1) y01.g).f.c(ka1);
                    }
                    I70 i70 = y01.k;
                    if (i70 != null) {
                        ((D70) y01.j).Q.c(i70);
                    }
                    C1128Sl sl2 = y01.o;
                    if (sl2 != null) {
                        sl2.a();
                        y01.o = null;
                    }
                    H00 h00 = t01.f;
                    if (h00 != null) {
                        ((AbstractC0246Ea1) h00.b).f.c(h00.c);
                    }
                    C0435Hc1 hc1 = t01.g;
                    if (hc1 != null) {
                        AbstractC1117Sg1 sg1 = hc1.b;
                        if (sg1 != null) {
                            sg1.K.c(hc1.c);
                            hc1.b = null;
                        }
                        C5880z61 z613 = hc1.d;
                        if (z613 != null) {
                            z613.f11721a.c(hc1.e);
                            hc1.d = null;
                        }
                    }
                    C5976zi0 zi02 = t01.m;
                    if (zi02 != null) {
                        zi02.a();
                        t01.m = null;
                    }
                    C1128Sl sl3 = t01.n;
                    if (sl3 != null) {
                        sl3.a();
                        t01.n = null;
                    }
                    t01.g = null;
                    t01.h = null;
                    t01.i = null;
                    t01.j = null;
                    t01.k = null;
                    t01.l = null;
                }
            }
            C2770gt0 gt0 = vl1.d;
            if (gt0 != null) {
                for (Map.Entry entry : gt0.b.entrySet()) {
                    ((AbstractC0639Kk) entry.getKey()).n((AbstractC0578Jk) entry.getValue());
                }
                gt0.b.clear();
                vl1.d = null;
            }
            if (vl1.f != null) {
                vl1.f = null;
            }
            if (vl1.g != null) {
                vl1.g = null;
            }
            if (vl1.h != null) {
                vl1.h = null;
            }
            D00 d003 = uk1.F;
            AbstractC0124Ca1 ca16 = d003.c;
            if (ca16 != null) {
                ((AbstractC0246Ea1) ca16).f.c(d003.b);
                d003.c = null;
            }
            d003.f7854a.clear();
            C5880z61 z614 = uk1.G;
            AbstractC5783ya1 ya1 = z614.d;
            if (ya1 != null) {
                ((AbstractC0246Ea1) z614.b).c.h(ya1);
            }
            if (z614.e != null) {
                ((I71) ((AbstractC0246Ea1) z614.b).c.d()).g.c(z614.e);
            }
            AbstractC0124Ca1 ca17 = z614.b;
            if (ca17 != null) {
                ((AbstractC0246Ea1) ca17).f.c(z614.c);
                z614.b = null;
            }
            z614.f11721a.clear();
            LocationBarModel locationBarModel = uk1.Z;
            long j = locationBarModel.l;
            if (j != 0) {
                N.MltVHpYK(j, locationBarModel);
                locationBarModel.l = 0;
            }
            uk1.r0.removeCallbacksAndMessages(null);
            ((C1551Zj) uk1.w0).Y.c(uk1.M);
            uk1.x0.f(uk1.N);
            C2921hm1 hm1 = uk1.H;
            if (hm1 != null) {
                hm1.f8908J.c(uk1);
            }
            M9 m9 = uk1.I;
            if (m9 != null) {
                m9.K.c(uk1);
                M9 m92 = uk1.I;
                m92.f8908J.clear();
                m92.K.clear();
                D00 d004 = m92.N;
                if (d004 != null) {
                    d004.f7854a.c(m92);
                    m92.N = null;
                }
                AbstractC2642g70 g705 = m92.O;
                if (g705 != null) {
                    ((D70) g705).Q.c(m92.P);
                    m92.O = null;
                }
                uk1.I = null;
            }
            Y2 y2 = uk1.X;
            if (y2 != null) {
                y2.destroy();
                uk1.X = null;
            }
            B90 b90 = uk1.A0;
            if (b90 != null) {
                b90.b.b.a();
            }
            DQ dq = uk1.e0;
            if (dq != null) {
                dq.f.c(uk1.h0);
                uk1.e0 = null;
            }
            C5976zi0 zi03 = uk1.C0;
            if (zi03 != null) {
                zi03.a();
                uk1.C0 = null;
            }
            PX px = uk1.F0;
            if (px != null) {
                px.e.a();
                uk1.F0 = null;
            }
            C1550Zi1 zi1 = uk1.G0;
            if (zi1 != null) {
                zi1.f9361a.a();
                zi1.i.a();
                AbstractC2642g70 g706 = zi1.j;
                if (g706 != null) {
                    ((D70) g706).Q.c(zi1.k);
                    zi1.j = null;
                    zi1.k = null;
                }
                uk1.G0 = null;
            }
            C1128Sl sl4 = uk1.m0;
            if (sl4 != null) {
                sl4.a();
                uk1.m0 = null;
            }
            AbstractC2451f01 f01 = uk1.S0;
            if (f01 != null) {
                ((C3818n01) f01).c.L.c(uk1.T0);
                AbstractC2451f01 f012 = uk1.S0;
                H7 h7 = uk1.U0;
                AbstractC4096of1 of1 = ((C3818n01) f012).g;
                if (!(of1 == null || (appBarLayout = ((C4778sf1) of1).b.i0) == null || (list = appBarLayout.M) == null || h7 == null)) {
                    list.remove(h7);
                }
                uk1.S0 = null;
                uk1.T0 = null;
                uk1.U0 = null;
            }
            uk1.s0.unregisterComponentCallbacks(uk1.z0);
            uk1.z0 = null;
            C0283Ep.h().c().c(uk1);
            this.V = null;
        }
        C5037u9 u9Var = this.G;
        if (u9Var != null) {
            u9Var.e.I.remove(this);
            this.G.e.I.remove(this.F);
            C5037u9 u9Var2 = this.G;
            C5887z9 z9Var = u9Var2.e;
            if (z9Var != null) {
                z9Var.f();
                z9Var.P.b(z9Var);
            }
            F9 f9 = (F9) u9Var2.d;
            ((C1078Rq0) f9.m).I.c(f9.n);
            C1128Sl sl5 = f9.l;
            if (sl5 != null) {
                sl5.a();
                f9.l = null;
            }
        }
        C2921hm1 hm12 = this.T;
        if (hm12 != null) {
            hm12.f8908J.clear();
            hm12.K.clear();
            hm12.L.a();
            this.T = null;
        }
        this.y0.destroy();
        DQ dq2 = this.L;
        if (dq2 != null) {
            dq2.f.c(this.M);
        }
        AbstractC3976nw1 nw1 = this.Y;
        if (nw1 != null) {
            VrModuleProvider.b.remove(nw1);
        }
        if (!(this.X == null || this.F.l0() == null)) {
            this.F.l0().h.c(this.X);
        }
        C0515Ij ij = this.Z;
        if (ij != null) {
            ij.destroy();
        }
        C5638xj xjVar = this.a0;
        if (xjVar != null) {
            AbstractC0576Jj jj = this.l0;
            if (jj != null) {
                xjVar.r(jj);
            }
            AbstractC5978zj.f11762a.b(this.a0);
            BottomSheet bottomSheet = this.a0.F;
            if (bottomSheet != null) {
                StringBuilder i = AbstractC2531fV.i("Sheet destroyed: state: ");
                i.append(bottomSheet.U);
                i.append(", content null: ");
                i.append(bottomSheet.a0 == null);
                AbstractC1220Ua0.d("BottomSheet", i.toString(), new Object[0]);
                bottomSheet.h0 = true;
                bottomSheet.f0 = false;
                bottomSheet.H.clear();
                ValueAnimator valueAnimator = bottomSheet.O;
                if (valueAnimator != null) {
                    valueAnimator.end();
                }
                bottomSheet.O = null;
            }
        }
        C1551Zj zj = this.o0;
        if (zj != null) {
            zj.a0 = null;
            ST st = zj.Z;
            st.U = null;
            st.j(null);
            Y2 y22 = st.S;
            if (y22 != null) {
                y22.destroy();
            }
            AbstractC1099Sa1 sa12 = st.T;
            if (sa12 != null) {
                sa12.destroy();
            }
            Y2 y23 = zj.X;
            if (y23 != null) {
                y23.destroy();
            }
            zj.G.K.removeCallbacksAndMessages(null);
            AbstractC1099Sa1 sa13 = zj.K;
            if (sa13 != null) {
                sa13.destroy();
            }
            VrModuleProvider.b.remove(zj);
            this.o0 = null;
        }
        List<AbstractC0639Kk> list2 = this.e0;
        if (list2 != null) {
            for (AbstractC0639Kk kk : list2) {
                kk.destroy();
            }
            this.e0 = null;
        }
        GP0 gp0 = this.c0;
        if (!(gp0 == null || (animator = gp0.b.e) == null)) {
            animator.end();
        }
        this.c0 = null;
        if (this.p0 != null) {
            this.p0 = null;
        }
        this.F = null;
    }

    @Override // defpackage.AbstractC1244Ui0
    public boolean f(int i, boolean z) {
        C5037u9 u9Var;
        View view;
        boolean z2 = true;
        if (i == R.id.show_menu && (u9Var = this.G) != null) {
            C5887z9 z9Var = u9Var.e;
            if (z9Var != null && z9Var.j()) {
                boolean hasPermanentMenuKey = ViewConfiguration.get(u9Var.f11391a).hasPermanentMenuKey();
                C5887z9 z9Var2 = u9Var.e;
                if (hasPermanentMenuKey) {
                    view = null;
                } else {
                    view = ((Uk1) u9Var.b).k();
                }
                z9Var2.k(view, false);
            }
            return true;
        } else if (i == R.id.find_in_page_id) {
            DQ dq = this.L;
            if (dq == null) {
                return false;
            }
            if (dq.f7890a == null) {
                BQ bq = (BQ) dq.b.inflate();
                dq.f7890a = bq;
                bq.M = dq.c;
                bq.p(bq.i());
                BQ bq2 = dq.f7890a;
                bq2.R = dq.d;
                bq2.G.setCustomSelectionActionModeCallback(dq.e);
                dq.f7890a.T = new CQ(dq);
            }
            dq.f7890a.b();
            Tab tab = this.f8088J.H;
            if (z) {
                AbstractC3535lK0.a("MobileMenuFindInPage");
                N.MX4lNgiF(tab.l(), "MobileMenu.FindInPage", "HasOccurred");
            } else {
                AbstractC3535lK0.a("MobileShortcutFindInPage");
            }
            return true;
        } else {
            if (i == R.id.share_menu_button_id || i == R.id.share_menu_id || i == R.id.direct_share_menu_id) {
                if (i != R.id.direct_share_menu_id) {
                    z2 = false;
                }
                ((AbstractC0246Ea1) ((AbstractC0124Ca1) ((C1078Rq0) this.p0).H)).r();
                GT0 gt0 = (GT0) ((C1078Rq0) this.K).H;
                Tab tab2 = this.f8088J.H;
                if (!(gt0 == null || tab2 == null)) {
                    if (z2) {
                        AbstractC3535lK0.a("MobileMenuDirectShare");
                    } else {
                        AbstractC3535lK0.a("MobileMenuShare");
                    }
                    gt0.c(tab2, z2, 0);
                }
            } else if (i == R.id.paint_preview_show_id) {
                Tab tab3 = this.f8088J.H;
                if (tab3 != null) {
                    C3858nE nEVar = new C3858nE(tab3);
                    N.MJ3oAy5s();
                    C1872be1 be1 = nEVar.b;
                    be1.I.b(be1.F, new C3174jE(nEVar));
                }
            } else if (i == R.id.get_image_descriptions_id) {
                CZ b = CZ.b();
                ChromeActivity chromeActivity = this.F;
                C2746gl0 l02 = chromeActivity.l0();
                WebContents l = this.f8088J.H.l();
                Objects.requireNonNull(b);
                Profile b2 = Profile.b();
                if (!N.MzIXnlkD(Wr1.a(b2).f10883a, "settings.a11y.enable_accessibility_image_labels_android")) {
                    PU0 pu0 = NU0.f8549a;
                    if (pu0.d("Chrome.ImageDescriptions.DontAskAgain", false)) {
                        b.b.a(true, l);
                        C1184Ti1.b(chromeActivity, chromeActivity.getResources().getText(R.string.f52970_resource_name_obfuscated_RES_2131952614), 1).b.show();
                    } else {
                        l02.i(new FZ(chromeActivity, l02, b.b, pu0.f("Chrome.ImageDescriptions.JustOnceCount", 0) >= 3, l).H, 0, false);
                    }
                } else if (!N.MzIXnlkD(Wr1.a(b2).f10883a, "settings.a11y.enable_accessibility_image_labels_only_on_wifi") || C5222vE.d(chromeActivity) == 2) {
                    N.Mf2ABpoH(CZ.a(b.b.f7745a, b2).f10883a, "settings.a11y.enable_accessibility_image_labels_android", false);
                    C1184Ti1.b(chromeActivity, chromeActivity.getResources().getText(R.string.f52980_resource_name_obfuscated_RES_2131952615), 1).b.show();
                } else {
                    b.b.a(false, l);
                    C1184Ti1.b(chromeActivity, chromeActivity.getResources().getText(R.string.f52970_resource_name_obfuscated_RES_2131952614), 1).b.show();
                }
            }
            return false;
        }
    }

    @Override // defpackage.AbstractC2112d10
    public void g() {
        if (this.F.Y0() != -1) {
            ChromeActivity chromeActivity = this.F;
            C5037u9 u9Var = new C5037u9(chromeActivity, chromeActivity.Y, this.V, chromeActivity, chromeActivity.getWindow().getDecorView(), this.F.getWindow().getDecorView().findViewById(R.id.menu_anchor_stub));
            this.G = u9Var;
            C5887z9 z9Var = u9Var.e;
            if (!z9Var.I.contains(this)) {
                z9Var.I.add(this);
            }
            C5037u9 u9Var2 = this.G;
            ChromeActivity chromeActivity2 = this.F;
            C5887z9 z9Var2 = u9Var2.e;
            if (!z9Var2.I.contains(chromeActivity2)) {
                z9Var2.I.add(chromeActivity2);
            }
            this.k0.a(this.G);
        }
        int L0 = this.F.L0();
        ChromeActivity chromeActivity3 = this.F;
        chromeActivity3.getClass();
        RunnableC3883nN0 nn0 = new RunnableC3883nN0(chromeActivity3);
        AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) ((C1078Rq0) this.p0).H;
        DQ dq = this.L;
        C5638xj xjVar = this.a0;
        C1551Zj M0 = this.F.M0();
        ChromeActivity chromeActivity4 = this.F;
        JF jf = new JF(chromeActivity3, L0, chromeActivity3, nn0, ca1, dq, xjVar, M0, chromeActivity4.I0, chromeActivity4.W0);
        this.d0 = jf;
        chromeActivity4.Y.a(jf);
        C5638xj xjVar2 = this.a0;
        if (xjVar2 != null) {
            FN0 fn0 = new FN0(this);
            this.l0 = fn0;
            xjVar2.j(fn0);
        }
        if (this.G != null) {
            this.X = new C5756yN0(this);
            this.F.l0().h.b(this.X);
        }
        C1595a3 a3Var = this.F.W0;
        Uk1 uk1 = this.V;
        uk1.getClass();
        new C0649Kp(a3Var, new C4736sN0(uk1), new C4906tN0(this), this.K);
        C5926zN0 zn0 = new C5926zN0(this);
        this.Y = zn0;
        VrModuleProvider.b.add(zn0);
    }

    public GP0 i() {
        ViewGroup viewGroup = (ViewGroup) this.F.findViewById(R.id.coordinator);
        return new GP0(this.F, new CN0(this), viewGroup, viewGroup.getResources().getColor(R.color.f14460_resource_name_obfuscated_RES_2131100136));
    }

    public C1551Zj j() {
        return new C1551Zj(this.F, 0);
    }

    public C1551Zj k() {
        if (this.o0 == null) {
            if (!this.F.v()) {
                this.o0 = j();
            } else {
                throw new IllegalStateException();
            }
        }
        return this.o0;
    }

    public void l() {
        if (this.m0.get() != null) {
            ((ContextualSearchManager) this.m0.get()).i(0);
        }
    }

    public void m(D70 d70) {
        C1328Vt0 vt0 = this.O;
        if (vt0 != null) {
            vt0.b.c(this.P);
        }
        C1328Vt0 vt02 = d70.Z;
        this.O = vt02;
        if (this.P == null) {
            this.P = new AN0(this);
        }
        vt02.b.b(this.P);
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        if (N.M09VlOh_("MessagesForAndroidInfrastructure")) {
            MessageContainer messageContainer = (MessageContainer) this.F.findViewById(R.id.message_container);
            C0638Kj0 kj0 = new C0638Kj0(messageContainer, k());
            this.s0 = kj0;
            C5076uN0 un0 = new C5076uN0(kj0);
            C0283Ep h = C0283Ep.h();
            C2971i3 i3Var = this.F.b0;
            i3Var.getClass();
            this.r0 = new C0820Nj0(messageContainer, un0, h, new C5246vN0(i3Var));
            C2253ds dsVar = new C2253ds(this.F.M0(), this.s0, this.F.S0(), this.Q, this.p0, this.r0);
            this.t0 = dsVar;
            AbstractC1406Xb0 xb0 = this.r0;
            ((C0820Nj0) xb0).F.d = dsVar;
            AbstractC0881Oj0.f8643a.a(this.F.b0.U, xb0);
        }
    }
}
