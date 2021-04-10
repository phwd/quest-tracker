package defpackage;

import J.N;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.banners.AppBannerInProductHelpControllerProvider;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.TabManagementDelegateImpl;
import org.chromium.chrome.browser.toolbar.bottom.ScrollingBottomViewResourceFrameLayout;
import org.chromium.chrome.browser.vr.VrModuleProvider;
import org.chromium.chrome.browser.webapps.PwaBottomSheetController;
import org.chromium.chrome.browser.webapps.PwaBottomSheetControllerProvider;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: ve1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5285ve1 extends GN0 {
    public C5625xe1 A0;
    public NK B0;
    public Z11 C0;
    public Y11 D0;
    public C6003zr0 E0;
    public C0227Dr0 F0;
    public C1647aK0 G0;
    public AbstractC1740ar1 H0;
    public Oj1 I0;
    public C5541x7 J0;
    public PwaBottomSheetController K0;
    public FX L0;
    public AbstractC1497Ym0 M0;
    public C3461kw N0;
    public D70 O0;
    public C1078Rq0 P0;
    public Y2 Q0;
    public final C1078Rq0 z0;

    public C5285ve1(ChromeActivity chromeActivity, Callback callback, AbstractC1509Ys0 ys0, AbstractC0956Pq0 pq0, C1595a3 a3Var, C1078Rq0 rq0, AbstractC0956Pq0 pq02, AbstractC0956Pq0 pq03, Q31 q31, AbstractC0956Pq0 pq04, AbstractC1509Ys0 ys02, AbstractC1509Ys0 ys03, Q31 q312) {
        super(chromeActivity, callback, pq0, a3Var, pq02, pq03, q31, pq04, ys02, ys0, ys03, q312);
        this.z0 = rq0;
        this.W = new C2043ce1(this);
    }

    @Override // defpackage.AbstractC4371qE, defpackage.GN0
    public void destroy() {
        C1128Sl sl;
        C1413Xd1 xd1;
        C5625xe1 xe1 = this.A0;
        if (!(xe1 == null || (xd1 = xe1.f11622a) == null)) {
            AbstractC0124Ca1 ca1 = xd1.f9222J;
            if (ca1 != null) {
                ((AbstractC0246Ea1) ca1).f.c(xd1.K);
            }
            AbstractC2260du0 du0 = xd1.L;
            if (du0 != null) {
                ((AbstractC3838n70) du0).y0.c(xd1.M);
            }
            C1128Sl sl2 = xd1.N;
            if (sl2 != null) {
                sl2.a();
                xd1.N = null;
            }
            VrModuleProvider.b.remove(xd1);
        }
        NK nk = this.B0;
        if (!(nk == null || (sl = nk.g) == null)) {
            sl.a();
            nk.g = null;
        }
        C6003zr0 zr0 = this.E0;
        if (zr0 != null) {
            C4302pr0 pr0 = zr0.d;
            if (pr0 != null) {
                ApplicationStatus.h.c(pr0);
                C1518Yx yx = pr0.b;
                if (yx != null) {
                    NetworkChangeNotifier.j(yx);
                    yx.g();
                    yx.F = null;
                    pr0.b = null;
                }
                pr0.e.removeCallbacks(pr0.f);
                zr0.d = null;
            }
            AbstractC0956Pq0 pq0 = zr0.e;
            if (pq0 != null) {
                ((C1078Rq0) pq0).I.c(zr0.g);
                zr0.e = null;
            }
            zr0.g = null;
            Handler handler = zr0.c;
            if (handler != null) {
                handler.removeCallbacks(zr0.j);
                zr0.c.removeCallbacks(zr0.l);
            }
        }
        Uk1 uk1 = this.V;
        if (uk1 != null) {
            ((View$OnKeyListenerC0001Aa0) uk1.j()).F.M.c(this.H0);
        }
        C0227Dr0 dr0 = this.F0;
        if (dr0 != null) {
            dr0.f.f9314a.H.remove(dr0);
        }
        Z11 z11 = this.C0;
        if (z11 != null) {
            z11.b(this.D0);
            this.C0.b(this.F.V0());
            Z11 z112 = this.C0;
            if (z112.k) {
                z112.d.run();
            }
            if (z112.h) {
                z112.c();
            }
            View$OnLayoutChangeListenerC4337q21 q21 = z112.f9314a;
            ValueAnimator valueAnimator = q21.P;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator valueAnimator2 = q21.Q;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            AnimatorSet animatorSet = q21.R;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet animatorSet2 = q21.S;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
            ((C1551Zj) q21.G).Y.c(q21);
        }
        Oj1 oj1 = this.I0;
        if (oj1 != null) {
            oj1.F.a();
            oj1.I.b(oj1);
            this.Q0.destroy();
        }
        C5541x7 x7Var = this.J0;
        if (x7Var != null) {
            AppBannerInProductHelpControllerProvider.f10617a.b(x7Var);
        }
        PwaBottomSheetController pwaBottomSheetController = this.K0;
        if (pwaBottomSheetController != null) {
            PwaBottomSheetControllerProvider.f10801a.b(pwaBottomSheetController);
        }
        FX fx = this.L0;
        if (fx != null) {
            Y2 y2 = fx.f8020J;
            if (y2 != null) {
                y2.destroy();
                fx.f8020J = null;
            }
            W10 w10 = fx.I;
            if (w10 != null) {
                w10.H.c(fx);
                fx.I = null;
            }
            fx.X.run();
            fx.H = null;
            C1572Zt0 zt0 = fx.V;
            if (zt0 != null) {
                C1748au0 au0 = zt0.G;
                N.MPFnESYL(au0.F, au0);
                au0.H = 0;
                fx.V = null;
            }
            fx.T = HX.f8161a;
            C0887Om0 om0 = fx.S;
            if (om0 != null) {
                om0.d = null;
                om0.b.removeOnAttachStateChangeListener(om0.f);
                om0.e = null;
                fx.S = null;
            }
            M2 m2 = fx.K;
            if (m2 != null) {
                m2.b(fx);
                fx.K = null;
            }
            this.L0 = null;
        }
        super.destroy();
    }

    @Override // defpackage.AbstractC2112d10, defpackage.GN0
    public void g() {
        super.g();
        this.A0 = new C5625xe1(this.F.getWindow(), this.F.P(), this.F.T0());
    }

    @Override // defpackage.GN0
    public GP0 i() {
        ViewGroup viewGroup = (ViewGroup) this.F.findViewById(R.id.coordinator);
        return new GP0(this.F, new C4605re1(this), viewGroup, viewGroup.getResources().getColor(R.color.f14460_resource_name_obfuscated_RES_2131100136));
    }

    @Override // defpackage.GN0
    public C1551Zj j() {
        C1551Zj zj = new C1551Zj(this.F, 0);
        if (this.N0 == null) {
            this.N0 = new C3461kw(new AbstractC2742gk[0]);
        }
        this.N0.o(zj.G);
        return zj;
    }

    @Override // defpackage.GN0
    public void l() {
        super.l();
        DL dl = (DL) this.z0.H;
        if (dl != null && dl.T) {
            ((C5638xj) dl.K).p(dl.P, true, 0);
        }
    }

    @Override // defpackage.GN0
    public void m(D70 d70) {
        boolean z;
        super.m(d70);
        if (!DeviceFormFactor.a(this.F) && N.M09VlOh_("OfflineIndicatorV2")) {
            C1551Zj M02 = this.F.M0();
            ChromeActivity chromeActivity = this.F;
            ResourceManager resourceManager = chromeActivity.I0.M.N;
            P11 V0 = chromeActivity.V0();
            V0.getClass();
            C2555fe1 fe1 = new C2555fe1(V0);
            Q31 q31 = this.W;
            d70.getClass();
            Z11 z11 = new Z11(chromeActivity, resourceManager, M02, fe1, q31, new C2726ge1(d70));
            this.C0 = z11;
            d70.g(z11.b);
            C4945te1 te1 = new C4945te1(this, M02);
            this.D0 = te1;
            this.C0.a(te1);
            this.C0.a(this.F.V0());
            if (N.M09VlOh_("OfflineIndicatorV2")) {
                C1078Rq0 rq0 = new C1078Rq0();
                Uk1 uk1 = this.V;
                if (uk1.d0.Q() == null) {
                    z = false;
                } else {
                    z = ((View$OnKeyListenerC0001Aa0) uk1.d0.Q()).F.T;
                }
                rq0.m(Boolean.valueOf(z));
                this.H0 = new C5115ue1(this, rq0);
                this.E0 = new C6003zr0(this.F, this.C0, rq0, this.W);
                if (this.V.j() != null) {
                    ((View$OnKeyListenerC0001Aa0) this.V.j()).p(this.H0);
                }
            }
        }
        this.O0 = d70;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01d8, code lost:
        if (r3 == false) goto L_0x01da;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x023c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01fe  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean n() {
        /*
        // Method dump skipped, instructions count: 872
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5285ve1.n():boolean");
    }

    @Override // defpackage.AbstractC4968tm0, defpackage.GN0
    public void s() {
        C5887z9 z9Var;
        super.s();
        ChromeActivity chromeActivity = this.F;
        C2971i3 i3Var = chromeActivity.b0;
        M2 m2 = chromeActivity.Y;
        CompositorViewHolder compositorViewHolder = chromeActivity.I0;
        C1595a3 a3Var = chromeActivity.W0;
        W10 w10 = chromeActivity.L0;
        chromeActivity.getClass();
        C3238je1 je1 = new C3238je1(chromeActivity);
        ChromeActivity chromeActivity2 = this.F;
        chromeActivity2.getClass();
        RunnableC3409ke1 ke1 = new RunnableC3409ke1(chromeActivity2);
        D70 d70 = this.O0;
        C3580le1 le1 = new C3580le1(this);
        String string = this.F.getResources().getString(R.string.f61810_resource_name_obfuscated_RES_2131953498);
        C3751me1 me1 = new C3751me1(this);
        FX fx = new FX();
        fx.V = new C1572Zt0(i3Var, compositorViewHolder, new RunnableC5095uX(compositorViewHolder));
        fx.H = new MX(compositorViewHolder.getContext(), new C5265vX(fx), fx.V, new C5435wX(fx), new C5605xX(fx));
        fx.G = compositorViewHolder;
        fx.K = m2;
        fx.L = je1;
        fx.M = ke1;
        fx.N = le1;
        fx.O = string;
        fx.P = me1;
        m2.a(fx);
        compositorViewHolder.addView(fx.H);
        fx.f8020J = new DX(fx, a3Var);
        fx.W = new RunnableC5775yX(fx, compositorViewHolder);
        fx.X = new RunnableC5945zX(fx, compositorViewHolder);
        Tab tab = a3Var.H;
        if (tab != null) {
            fx.Q = tab;
            fx.j();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            fx.I = w10;
            w10.H.b(fx);
        }
        d70.g(fx.V);
        AbstractC3100ip1.f10165a.a("GestureNavigation.Type", fx.i());
        this.L0 = fx;
        if (DeviceFormFactor.a(this.F)) {
            C5037u9 u9Var = this.G;
            if (u9Var == null) {
                z9Var = null;
            } else {
                z9Var = u9Var.e;
            }
            AbstractC0124Ca1 P = this.F.P();
            A61 S = this.F.S(false);
            ChromeActivity chromeActivity3 = this.F;
            NK nk = new NK(P, S, chromeActivity3, z9Var, chromeActivity3.U(), this.F.T0());
            this.B0 = nk;
            for (TabModel tabModel : ((AbstractC0246Ea1) nk.b).f7969a) {
                tabModel.n(nk.d);
            }
            ((AbstractC0246Ea1) nk.b).c(nk.e);
        }
        if (!this.F.h0 && (AbstractC4772sd1.g() || AbstractC4772sd1.a())) {
            Uk1 uk1 = this.V;
            View inflate = ((ViewStub) uk1.s0.findViewById(R.id.bottom_controls_stub)).inflate();
            TabManagementDelegateImpl a2 = AbstractC1680aa1.a();
            M9 m9 = uk1.I;
            GP0 gp0 = uk1.R0;
            AbstractC0956Pq0 pq0 = uk1.R;
            Objects.requireNonNull(a2);
            O71 o71 = new O71((ViewGroup) inflate.findViewById(R.id.bottom_container_slot), m9, gp0, pq0);
            uk1.Z0 = o71;
            uk1.S.m(new C2568fj(uk1.s0, uk1.t0, uk1.f0, uk1.v0.M.N, uk1.w0, uk1.x0, (ScrollingBottomViewResourceFrameLayout) inflate, o71, uk1.Y0));
        }
        if (DL.b()) {
            C1078Rq0 rq0 = this.z0;
            ChromeActivity chromeActivity4 = this.F;
            C2971i3 i3Var2 = chromeActivity4.b0;
            View decorView = chromeActivity4.getWindow().getDecorView();
            ChromeActivity chromeActivity5 = this.F;
            C1595a3 a3Var2 = chromeActivity5.W0;
            chromeActivity5.getClass();
            rq0.m(new DL(chromeActivity4, i3Var2, decorView, a3Var2, new C3922ne1(chromeActivity5), this.a0, true));
        }
        this.u0.g(this.n0.b(new C4093oe1(this)));
        PwaBottomSheetController pwaBottomSheetController = new PwaBottomSheetController(this.F);
        this.K0 = pwaBottomSheetController;
        PwaBottomSheetControllerProvider.f10801a.a(this.F.b0.U, pwaBottomSheetController);
    }
}
