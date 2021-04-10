package org.chromium.chrome.browser.app;

import J.N;
import android.app.Activity;
import android.app.assist.AssistContent;
import android.content.ActivityNotFoundException;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BundleUtils;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.compositor.CompositorView;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.dom_distiller.DomDistillerUIUtils;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.omaha.notification.UpdateNotificationServiceBridge;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.printing.TabPrinter;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.ui.BottomContainer;
import org.chromium.chrome.browser.vr.VrModuleProvider;
import org.chromium.chrome.browser.webapps.PwaBottomSheetController;
import org.chromium.chrome.browser.webapps.PwaBottomSheetControllerProvider;
import org.chromium.chrome.browser.webapps.addtohomescreen.AddToHomescreenCoordinator;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.policy.CombinedPolicyProvider;
import org.chromium.content.browser.ScreenOrientationProviderImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.ResourceManager;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class ChromeActivity extends AbstractActivityC0731Ma implements AbstractC3226ja1, AbstractC1400Wz, AbstractC4928tY0, KO0, O11, AbstractC5207v9, AbstractC4356q9, AbstractC1305Vi0 {
    public static final /* synthetic */ int o0 = 0;
    public final S20 A0 = new S20(this, C0());
    public boolean B0;
    public boolean C0;
    public boolean D0;
    public boolean E0;
    public boolean F0;
    public AbstractC3526lH0 G0;
    public C2831hD0 H0;
    public CompositorViewHolder I0;
    public C1078Rq0 J0 = new C1078Rq0();
    public C1078Rq0 K0 = new C1078Rq0();
    public W10 L0;
    public ContextualSearchManager M0;
    public View$OnClickListenerC5098uY0 N0;
    public AbstractC3103iq1 O0;
    public P11 P0;
    public long Q0;
    public long R0;
    public final C3402kc0 S0 = new C3402kc0();
    public boolean T0;
    public boolean U0;
    public C2287e3 V0;
    public C1595a3 W0 = new C1595a3();
    public boolean X0;
    public Bundle Y0;
    public Configuration Z0;
    public C5276vb1 a1;
    public GN0 b1;
    public C4675s11 c1;
    public C0742Md1 d1;
    public List e1 = new ArrayList();
    public C1078Rq0 p0;
    public AbstractC0956Pq0 q0;
    public C1078Rq0 r0 = new C1078Rq0();
    public AbstractC0124Ca1 s0;
    public AbstractC1099Sa1 t0;
    public A61 u0;
    public A61 v0;
    public C1078Rq0 w0 = new C1078Rq0();
    public TabContentManager x0;
    public UmaSessionStats y0;
    public boolean z0;

    public ChromeActivity() {
        C1078Rq0 rq0 = new C1078Rq0();
        this.p0 = rq0;
        this.q0 = new C0733Ma1(rq0);
    }

    public static ChromeActivity J0(WebContents webContents) {
        WindowAndroid I;
        Activity activity;
        if (webContents == null || webContents.g() || (I = webContents.I()) == null || (activity = (Activity) I.s0().get()) == null || !(activity instanceof ChromeActivity)) {
            return null;
        }
        return (ChromeActivity) activity;
    }

    public static int X0() {
        return SysUtils.isLowEndDevice() ? R.style.f72860_resource_name_obfuscated_RES_2132017859 : R.style.f72850_resource_name_obfuscated_RES_2132017858;
    }

    public boolean A0(Tab tab) {
        return false;
    }

    public final void B0() {
        if (this.X0 && K0() != null) {
            ProfileSyncService b = ProfileSyncService.b();
            if (b != null) {
                boolean z = false;
                if (!N.M09VlOh_("MobileIdentityConsistency") ? !(!b.h() || !((HashSet) ProfileSyncService.p(N.Mb40kbdU(b.e, b))).contains(11) || !(b.e() == 1 || b.e() == 4)) : !(!b.h() || !((HashSet) b.c()).contains(11) || !(b.e() == 1 || b.e() == 4))) {
                    z = true;
                }
                if (z) {
                    Objects.requireNonNull(AppHooks.get());
                    AbstractC3526lH0 lh0 = this.G0;
                    if (lh0 != null) {
                        b.q(lh0);
                        this.G0 = null;
                        return;
                    }
                    return;
                }
            }
            if (b == null || !b.h()) {
                AbstractC3641lz.a(2);
            } else if (!((HashSet) b.c()).contains(11)) {
                AbstractC3641lz.a(3);
            } else if (b.e() == 1 || b.e() == 4) {
                AbstractC3641lz.a(5);
            } else {
                AbstractC3641lz.a(4);
            }
            if (this.G0 == null && b != null) {
                C0892Op op = new C0892Op(this);
                this.G0 = op;
                b.a(op);
            }
        }
    }

    @Override // defpackage.AbstractC3441kp0, defpackage.AbstractActivityC5319vq
    public void C() {
        C5276vb1 vb1;
        if (N.M09VlOh_("AndroidNightModeTabReparenting") && (vb1 = this.a1) != null) {
            vb1.b();
        }
        if (!isFinishing()) {
            recreate();
        }
    }

    public R20 C0() {
        return new C2247dq(this);
    }

    public abstract C0742Md1 D0();

    public GN0 E0() {
        return new GN0(this, null, this.K0, this.W0, this.q0, this.r0, new C1563Zp(this), this.p0, new C1570Zs0(), new C1570Zs0(), new C1570Zs0(), new C1735aq());
    }

    public abstract Pair F0();

    public abstract AbstractC0124Ca1 G0();

    public void H0() {
        TraceEvent j0 = TraceEvent.j0("ChromeActivity.doLayoutInflation");
        try {
            P21 g0 = P21.g0();
            try {
                TraceEvent.Y("setContentView(R.layout.main)", null);
                setContentView(R.layout.f39200_resource_name_obfuscated_RES_2131624229);
                TraceEvent.f0("setContentView(R.layout.main)");
                if (O0() != -1) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.control_container_stub);
                    viewStub.setLayoutResource(O0());
                    TraceEvent.Y("toolbarContainerStub.inflate", null);
                    viewStub.inflate();
                    TraceEvent.f0("toolbarContainerStub.inflate");
                }
                DA da = (DA) findViewById(R.id.control_container);
                if (da == null) {
                    AbstractC2417ep1.k(findViewById(R.id.omnibox_results_container_stub));
                }
                int Y02 = Y0();
                if (!(Y02 == -1 || da == null)) {
                    ((ToolbarControlContainer) da).d(Y02);
                }
                g0.close();
                r0();
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
            throw th;
        } catch (Throwable th2) {
            AbstractC0754Mh1.f8495a.a(th, th2);
        }
    }

    public boolean I0() {
        ST st = (ST) S0();
        if (!st.a()) {
            return false;
        }
        st.c();
        return true;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma
    public void J() {
        S20.f = AbstractC1575Zv.e().g("enable-test-intents");
    }

    public Tab K0() {
        if (!this.D0) {
            return null;
        }
        return AbstractC1160Ta1.c(Q0());
    }

    public abstract int L0();

    public C1551Zj M0() {
        return this.b1.k();
    }

    public int N0() {
        return -1;
    }

    @Override // defpackage.AbstractC4356q9
    public boolean O() {
        if (v()) {
            return false;
        }
        int e = ApplicationStatus.e(this);
        boolean isInMultiWindowMode = isInMultiWindowMode();
        if (e == 3) {
            return true;
        }
        if (!isInMultiWindowMode || e != 4) {
            return false;
        }
        return true;
    }

    public int O0() {
        return -1;
    }

    @Override // defpackage.AbstractC3226ja1
    public AbstractC0124Ca1 P() {
        if (this.D0) {
            return this.s0;
        }
        throw new IllegalStateException("Attempting to access TabModelSelector before initialization");
    }

    public A61 P0() {
        return S(((AbstractC0246Ea1) P()).r());
    }

    public TabModel Q0() {
        AbstractC0124Ca1 P = P();
        if (P == null) {
            return SK.f8890a;
        }
        return ((AbstractC0246Ea1) P).i();
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma
    public void R(Intent intent) {
        AbstractC3103iq1 iq1;
        C2831hD0 hd0 = this.H0;
        if (hd0 != null) {
            hd0.d();
        }
        if (!this.A0.x(intent, false)) {
            Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
            this.A0.u(intent);
            if (this.O0 == null) {
                M2 m2 = this.Y;
                if (N.M09VlOh_("UpdateNotificationSchedulingIntegration")) {
                    iq1 = new UpdateNotificationServiceBridge(m2);
                } else {
                    iq1 = new C3616lq1(this, m2);
                }
                this.O0 = iq1;
            }
            this.O0.f(intent);
        }
    }

    public WebContents R0() {
        Tab c;
        if (this.D0 && (c = AbstractC1160Ta1.c(Q0())) != null) {
            return c.l();
        }
        return null;
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 S(boolean z) {
        if (this.D0) {
            return z ? this.v0 : this.u0;
        }
        throw new IllegalStateException("Attempting to access TabCreator before initialization");
    }

    public UT S0() {
        return M0().Z;
    }

    @Override // defpackage.KO0
    public void T(AbstractC2300e70 e70) {
    }

    public AbstractC1509Ys0 T0() {
        return null;
    }

    @Override // defpackage.AbstractC4928tY0
    public View$OnClickListenerC5098uY0 U() {
        C5638xj xjVar;
        GN0 gn0 = this.b1;
        if (gn0 == null) {
            xjVar = null;
        } else {
            xjVar = gn0.a0;
        }
        if (!(gn0 == null || xjVar == null || !xjVar.q())) {
            BottomSheet bottomSheet = xjVar.F;
            boolean z = false;
            if (!(bottomSheet == null || bottomSheet.O == null || bottomSheet.V != 0)) {
                z = true;
            }
            if (!z) {
                return this.b1.b0;
            }
        }
        return this.N0;
    }

    public C4675s11 U0() {
        if (this.c1 == null) {
            this.c1 = new C4675s11(new C0831Np(this), this.Y, this.b0, this, this.A0);
        }
        return this.c1;
    }

    public final P11 V0() {
        if (this.P0 == null) {
            this.P0 = new P11(getWindow(), DeviceFormFactor.a(this), getResources(), this, T0(), this.Y, this.W0, this.b1.T);
        }
        return this.P0;
    }

    public C1343Wa1 W0() {
        GN0 gn0 = this.b1;
        if (gn0 == null) {
            return null;
        }
        return gn0.I;
    }

    public int Y0() {
        return -1;
    }

    public abstract boolean Z0();

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma
    public boolean a(int i, int i2, Intent intent) {
        if (super.a(i, i2, intent)) {
            return true;
        }
        Objects.requireNonNull(VrModuleProvider.b());
        return false;
    }

    public void a1() {
        C2150dE.b().a(new RunnableC0953Pp(this));
        C2150dE.b().a(new RunnableC1075Rp(this, getClass().getSimpleName()));
        C2150dE.b().a(new RunnableC1136Sp(this));
        if (!SysUtils.isLowEndDevice() && !v()) {
            C2150dE.b().a(new RunnableC1197Tp(this));
        }
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma
    public void b() {
        super.b();
        f1();
        AbstractC3535lK0.a("MobileComeToForeground");
        if (this.d1 == null) {
            this.d1 = D0();
        }
        Objects.requireNonNull(this.d1);
        if (!C0742Md1.f8490a) {
            C0742Md1.f8490a = true;
            AbstractC3364kK0.g("MobileStartup.Experimental.LaunchCause", 0, 3);
        }
        Tab K02 = K0();
        if (K02 != null) {
            WebContents l = K02.l();
            for (C1608a70 a70 : AbstractC1788b70.f9513a) {
                Objects.requireNonNull(a70);
                N.M7MirFey(true, a70.f9410a, a70.b, 0, l);
            }
            AbstractC1788b70.f9513a.clear();
            if (l != null) {
                l.q0();
            }
        }
        N.Mo3c6U9N(d1());
        N.Mo4XXQLk(L0());
        N.MJuAVRUC(isInMultiWindowMode());
        C2831hD0 hd0 = this.H0;
        if (hd0 != null) {
            hd0.d();
        }
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        View$OnLayoutChangeListenerC4598rc0 rc0 = this.S0.f10290a;
        if (rc0.c0()) {
            rc0.f0();
            rc0.g0();
        }
    }

    public void b1(D70 d70, View view, ViewGroup viewGroup, DA da) {
        AbstractC5194v41 v41;
        this.J0.m(d70);
        d70.R.b(this);
        CompositorViewHolder compositorViewHolder = this.I0;
        compositorViewHolder.K = d70;
        compositorViewHolder.B();
        this.I0.setFocusable(false);
        CompositorViewHolder compositorViewHolder2 = this.I0;
        ResourceManager resourceManager = compositorViewHolder2.M.N;
        IJ a2 = resourceManager != null ? resourceManager.a() : null;
        if (!(a2 == null || compositorViewHolder2.b0 == null)) {
            a2.d(R.id.control_container);
        }
        compositorViewHolder2.b0 = da;
        if (!(a2 == null || da == null)) {
            a2.c.put(R.id.control_container, ((ToolbarControlContainer) da).f10790J.G);
        }
        CompositorViewHolder compositorViewHolder3 = this.I0;
        C1551Zj M02 = M0();
        compositorViewHolder3.U = M02;
        M02.Y.b(compositorViewHolder3);
        compositorViewHolder3.V.m(compositorViewHolder3.U);
        compositorViewHolder3.B();
        CompositorViewHolder compositorViewHolder4 = this.I0;
        compositorViewHolder4.y0 = view;
        W10 w10 = this.L0;
        W10 w102 = compositorViewHolder4.c0;
        if (w102 != null) {
            w102.H.c(compositorViewHolder4);
        }
        compositorViewHolder4.c0 = w10;
        if (w10 != null) {
            w10.H.b(compositorViewHolder4);
            compositorViewHolder4.t();
        }
        CompositorViewHolder compositorViewHolder5 = this.I0;
        C2921hm1 hm1 = this.b1.T;
        Objects.requireNonNull(compositorViewHolder5);
        CompositorViewHolder compositorViewHolder6 = this.I0;
        AbstractC0124Ca1 P = P();
        compositorViewHolder6.K.p(P, this, compositorViewHolder6.b0, compositorViewHolder6.M.N.a());
        compositorViewHolder6.T = P;
        ((AbstractC0246Ea1) P).c(new C1394Ww(compositorViewHolder6));
        compositorViewHolder6.L.c = compositorViewHolder6.T;
        compositorViewHolder6.A();
        if (!(da == null || !C5052uE.c().f || (v41 = ((AbstractC3838n70) this.I0.K).u0) == null)) {
            ToolbarControlContainer toolbarControlContainer = (ToolbarControlContainer) da;
            toolbarControlContainer.K = new Qj1(toolbarControlContainer, toolbarControlContainer.getContext(), v41);
        }
        C1595a3 a3Var = this.W0;
        a3Var.I = d70;
        d70.R.b(a3Var.f9404J);
        ContextualSearchManager contextualSearchManager = this.M0;
        if (contextualSearchManager != null) {
            contextualSearchManager.U = N.MGz$jbPy(contextualSearchManager);
            contextualSearchManager.V = viewGroup;
            viewGroup.getViewTreeObserver().addOnGlobalFocusChangeListener(contextualSearchManager.I);
            contextualSearchManager.t0 = d70;
            C1796bA bAVar = new C1796bA(contextualSearchManager.G, d70, d70.Z);
            if (bAVar.I0 != contextualSearchManager) {
                bAVar.I0 = contextualSearchManager;
                ChromeActivity chromeActivity = contextualSearchManager.G;
                bAVar.s0 = chromeActivity;
                if (chromeActivity != null) {
                    ApplicationStatus.g(bAVar, chromeActivity);
                }
            }
            contextualSearchManager.T = bAVar;
            Objects.requireNonNull(contextualSearchManager.R);
            contextualSearchManager.N.b = bAVar;
            contextualSearchManager.t0.g(bAVar);
            contextualSearchManager.N.f7711a = viewGroup;
            contextualSearchManager.W = new C2171dL0();
            contextualSearchManager.j0 = false;
            contextualSearchManager.l0 = false;
            contextualSearchManager.Z = false;
            contextualSearchManager.b0 = false;
            contextualSearchManager.f0 = true;
            contextualSearchManager.S.d(0);
            AbstractC0124Ca1 P2 = contextualSearchManager.G.P();
            contextualSearchManager.X = new C0912Oz(contextualSearchManager, P2);
            contextualSearchManager.Y = new C0973Pz(contextualSearchManager, P2);
            C0283Ep h = C0283Ep.h();
            h.c().b(contextualSearchManager);
            boolean d = h.d();
            contextualSearchManager.p0 = d;
            if (d) {
                contextualSearchManager.i(0);
            }
        }
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma
    public void c() {
        AbstractC3535lK0.a("MobileGoToBackground");
        Tab K02 = K0();
        if (K02 != null) {
            this.x0.b(K02);
        }
        this.S0.f10290a.f0();
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        UmaSessionStats umaSessionStats = this.y0;
        if (umaSessionStats != null) {
            umaSessionStats.c();
        }
        super.c();
    }

    public final void c1() {
        if (!this.D0) {
            AbstractC0124Ca1 G02 = G0();
            this.s0 = G02;
            if (G02 == null) {
                this.D0 = true;
                return;
            }
            this.p0.m(G02);
            C1595a3 a3Var = this.W0;
            AbstractC0124Ca1 ca1 = this.s0;
            a3Var.K = ca1;
            a3Var.L = new U2(a3Var, ca1);
            V2 v2 = new V2(a3Var);
            a3Var.M = v2;
            ((AbstractC0246Ea1) a3Var.K).c(v2);
            P11 V02 = V0();
            AbstractC0124Ca1 ca12 = this.s0;
            V02.R = ca12;
            if (ca12 != null) {
                ((AbstractC0246Ea1) ca12).c(V02.L);
            }
            Pair F02 = F0();
            this.u0 = (A61) F02.first;
            this.v0 = (A61) F02.second;
            C1568Zr0 zr0 = (C1568Zr0) AbstractC2254ds0.b.put(this, new C1568Zr0(this.s0));
            if (zr0 != null) {
                zr0.destroy();
            } else {
                ApplicationStatus.g(new C1324Vr0(), this);
            }
            AbstractC1099Sa1 sa1 = this.t0;
            if (sa1 != null) {
                sa1.destroy();
            }
            this.t0 = new C1906bq(this, this.s0);
            this.D0 = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0133  */
    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
        // Method dump skipped, instructions count: 827
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.app.ChromeActivity.d():void");
    }

    public boolean d1() {
        if (L0() == 1 || L0() == 2) {
            return true;
        }
        return false;
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma
    public void e() {
        if (C5259vU.b(this).d() && !SysUtils.isLowEndDevice()) {
            if (C4067oU.f10552a == null) {
                C4067oU.f10552a = new C4067oU(ContextUtils.getApplicationContext());
            }
            C4067oU oUVar = C4067oU.f10552a;
            int i = oUVar.b - 1;
            oUVar.b = i;
            C5089uU uUVar = oUVar.c;
            if (uUVar != null && i == 0) {
                uUVar.b();
            }
        }
        if (this.G0 != null) {
            ProfileSyncService b = ProfileSyncService.b();
            if (b != null) {
                b.q(this.G0);
            }
            this.G0 = null;
        }
        super.e();
    }

    public boolean e1() {
        return false;
    }

    public final void f1() {
        if (this.y0 == null) {
            this.y0 = new UmaSessionStats(this);
        }
        UmaSessionStats.e();
        this.y0.d(P());
    }

    public final void g1() {
        if (!this.F0 && this.E0 && hasWindowFocus()) {
            getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.f14810_resource_name_obfuscated_RES_2131100171)));
            this.F0 = true;
        }
    }

    public final void h1() {
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        Tab K02 = K0();
        AbstractC0124Ca1 ca1 = this.s0;
        if (ca1 != null && !((AbstractC0246Ea1) ca1).j && K02 != null) {
            K02.N(1);
        }
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public C2746gl0 i0() {
        return new C2746gl0(new J9(this), 0);
    }

    public final void i1() {
        g1();
        Tab K02 = K0();
        if (K02 != null) {
            if (K02.isHidden()) {
                K02.y(3);
            } else {
                K02.z();
            }
        }
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean j() {
        return true;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public C2971i3 j0() {
        return new C3968nu(this, this.W0, new C0710Lp(this), this.c0);
    }

    public void j1() {
    }

    public boolean k1(int i, boolean z) {
        PwaBottomSheetController pwaBottomSheetController;
        Profile profile;
        for (AbstractC1244Ui0 ui0 : this.e1) {
            if (ui0.f(i, z)) {
                return true;
            }
        }
        if (i == R.id.preferences_id) {
            Intent intent = new Intent();
            intent.setClass(this, XS0.class);
            U20.q(this, intent);
            AbstractC3535lK0.a("MobileMenuSettings");
        }
        if (i == R.id.update_menu_id) {
            C2249dq1 a2 = C2249dq1.a();
            a2.g = true;
            C5321vq1 vq1 = a2.e;
            if (vq1 != null) {
                int i2 = vq1.f11503a;
                if (i2 != 1) {
                    if (i2 == 3) {
                        AbstractC4981tq1.f11374a.g(0, this);
                    } else if (i2 == 5) {
                        AbstractC4981tq1.f11374a.c(0);
                    } else if (i2 == 6) {
                        AbstractC4981tq1.f11374a.f(0, this);
                    }
                } else if (!TextUtils.isEmpty(vq1.b)) {
                    try {
                        AbstractC4981tq1.f11374a.h(this, 0, false);
                        a2.d(1);
                        N.Mf2ABpoH(C2249dq1.b().f10883a, "omaha.clicked_update_menu_item", true);
                    } catch (ActivityNotFoundException unused) {
                        AbstractC1220Ua0.a("UpdateMenuItemHelper", "Failed to launch Activity for: %s", a2.e.b);
                        a2.d(2);
                    }
                }
                if (a2.e.c != null) {
                    N.MY13p7Sp(C2249dq1.b().f10883a, "omaha.latest_version_when_clicked_upate_menu_item", a2.e.c);
                }
                a2.c();
            }
            return true;
        }
        Tab K02 = K0();
        if (i == R.id.help_id) {
            String s = K02 != null ? K02.s() : "";
            if (((AbstractC0246Ea1) this.s0).r()) {
                profile = Profile.b().c();
            } else {
                profile = Profile.b();
            }
            o1(s, "MobileMenuFeedback", profile);
            return true;
        }
        if (i == R.id.open_history_menu_id) {
            if (K02 != null && AbstractC5154ur1.g(K02.s())) {
                AbstractC1499Yn0.a(5);
            }
            AbstractC3535lK0.a("MobileMenuHistory");
            AbstractC4755sX.a(this, K02);
        }
        if (K02 == null) {
            return false;
        }
        if (i == R.id.backward_menu_id) {
            if (K02.h()) {
                K02.e();
                AbstractC3535lK0.a("MobileMenuBackward");
            }
        } else if (i == R.id.forward_menu_id) {
            if (K02.k()) {
                K02.j();
                AbstractC3535lK0.a("MobileMenuForward");
            }
        } else if (i == R.id.bookmark_this_page_id || i == R.id.bookmark_this_page_chip_id || i == R.id.add_to_bookmarks_menu_id) {
            z0(K02);
            AbstractC3535lK0.a("MobileMenuAddToBookmarks");
        } else if (i == R.id.add_to_reading_list_menu_id) {
            ((BookmarkBridge) this.r0.H).b(new RunnableC1441Xp(this, K02));
            AbstractC3535lK0.a("MobileMenuAddToReadingList");
        } else if (i == R.id.offline_page_id || i == R.id.offline_page_chip_id || i == R.id.add_to_downloads_menu_id) {
            DownloadUtils.a(this, K02);
            AbstractC3535lK0.a("MobileMenuDownloadPage");
        } else if (i == R.id.reload_menu_id) {
            if (K02.d()) {
                K02.t();
                AbstractC3535lK0.a("MobileMenuStop");
            } else {
                K02.q();
                AbstractC3535lK0.a("MobileMenuReload");
            }
        } else if (i == R.id.info_menu_id || i == R.id.info_id) {
            WebContents l = K02.l();
            PageInfoController.l(this, l, null, 1, new C4985ts(this, l, new C1502Yp(this), new C1742as0(K02)), new C0411Gs());
        } else if (i == R.id.translate_id) {
            AbstractC3535lK0.a("MobileMenuTranslate");
            Um1.a(Profile.a(K0().l())).notifyEvent("translate_menu_button_clicked");
            N.M0540rIu(K0().l());
        } else if (i == R.id.print_id) {
            UF0 b = VF0.b();
            if (b != null) {
                VF0 vf0 = (VF0) b;
                if (!vf0.n && N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "printing.enabled")) {
                    TabPrinter tabPrinter = new TabPrinter(K02);
                    SF0 sf0 = new SF0(this);
                    if (!vf0.n) {
                        vf0.d(tabPrinter, sf0, vf0.c, vf0.d);
                        vf0.e();
                    }
                    AbstractC3535lK0.a("MobileMenuPrint");
                }
            }
        } else if (i == R.id.add_to_homescreen_id || i == R.id.add_to_homescreen_menu_id || i == R.id.install_app_id) {
            C2971i3 i3Var = this.b0;
            C2746gl0 l0 = l0();
            WebContents l2 = K02.l();
            Bundle bundle = this.Y0;
            if (!N.M09VlOh_("PwaInstallUseBottomSheet") || (pwaBottomSheetController = (PwaBottomSheetController) PwaBottomSheetControllerProvider.f10801a.e(i3Var.U)) == null) {
                int i3 = bundle.getInt("AppMenuTitleShown");
                AddToHomescreenCoordinator addToHomescreenCoordinator = new AddToHomescreenCoordinator(K02, this, i3Var, l0);
                if (l2 != null && !l2.u().j()) {
                    long j = addToHomescreenCoordinator.a().F;
                    if (j != 0) {
                        N.MFi$dBzL(j, l2, i3);
                    }
                }
            } else if (pwaBottomSheetController.I == null || ((C5638xj) pwaBottomSheetController.H).n() != pwaBottomSheetController.I) {
                N.MHQ0I69E(l2);
            } else {
                ((C5638xj) pwaBottomSheetController.H).m();
            }
            AbstractC3535lK0.a("MobileMenuAddToHomescreen");
        } else if (i == R.id.open_webapk_id || i == R.id.menu_open_webapk_id) {
            Context applicationContext = ContextUtils.getApplicationContext();
            try {
                applicationContext.startActivity(Mw1.a(AbstractC2612fx1.c(applicationContext, K02.s()), K02.s(), false));
                AbstractC3535lK0.a("MobileMenuOpenWebApk");
            } catch (ActivityNotFoundException unused2) {
                C1184Ti1.a(applicationContext, R.string.f56760_resource_name_obfuscated_RES_2131952993, 0).b.show();
            }
        } else if (i == R.id.request_desktop_site_id || i == R.id.request_desktop_site_check_id) {
            K02.l().f().d(!K02.l().f().p(), !K02.isNativePage());
            AbstractC3535lK0.a("MobileMenuRequestDesktopSite");
        } else if (i != R.id.reader_mode_prefs_id) {
            return false;
        } else {
            DomDistillerUIUtils.openSettings(K02.l());
        }
        return true;
    }

    public boolean l1(int i, Bundle bundle) {
        this.Y0 = bundle;
        C3402kc0 kc0 = this.S0;
        if (kc0 != null) {
            kc0.a();
        }
        return k1(i, true);
    }

    @Override // defpackage.AbstractC5207v9
    public B9 m() {
        return new F9(this, this.W0, this.Z, P(), this.b1.V, getWindow().getDecorView(), null, this.r0, l0());
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public long m0() {
        return this.a0;
    }

    public final void m1() {
        if (!this.E0) {
            this.B0 = true;
            return;
        }
        this.B0 = false;
        if (!this.C0) {
            this.C0 = true;
            a1();
            OG0.a().c();
            C2150dE b = C2150dE.b();
            Objects.requireNonNull(b);
            Object obj = ThreadUtils.f10596a;
            Looper.myQueue().addIdleHandler(new C1808bE(b));
        }
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public View n0() {
        View findViewById = findViewById(R.id.control_container);
        return findViewById != null ? findViewById : findViewById(16908290);
    }

    public void n1(long j) {
        AbstractC3364kK0.k("MobileStartup.IntentToCreationTime", j);
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void o0() {
        C2760gq.a();
        this.V0 = new C2287e3(this.p0);
    }

    public void o1(String str, String str2, Profile profile) {
        String str3;
        boolean a2 = Q0().a();
        if (TextUtils.isEmpty(str)) {
            str3 = getString(R.string.f52520_resource_name_obfuscated_RES_2131952569);
        } else if (str.startsWith("chrome-native://bookmarks/")) {
            str3 = getString(R.string.f52470_resource_name_obfuscated_RES_2131952564);
        } else if (str.equals("chrome://history/")) {
            str3 = getString(R.string.f52530_resource_name_obfuscated_RES_2131952570);
        } else if (N.M$l72hrq(str)) {
            str3 = getString(R.string.f52630_resource_name_obfuscated_RES_2131952580);
        } else if (a2) {
            str3 = getString(R.string.f52540_resource_name_obfuscated_RES_2131952571);
        } else if (str.equals("chrome-native://newtab/")) {
            str3 = getString(R.string.f52560_resource_name_obfuscated_RES_2131952573);
        } else {
            str3 = getString(R.string.f52660_resource_name_obfuscated_RES_2131952583);
        }
        C2535fX.a().b(this, str3, profile, str);
        AbstractC3535lK0.a(str2);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.T0) {
            this.T0 = false;
            getWindow().setWindowManager(getWindow().getWindowManager(), getWindow().getAttributes().token, getComponentName().flattenToString(), true);
        }
    }

    @Override // defpackage.AbstractActivityC3119iw
    public final void onBackPressed() {
        AbstractC2883ha haVar;
        D70 d70;
        if (this.E0) {
            AbstractC3535lK0.a("SystemBack");
        }
        C1175Tf1.d();
        Objects.requireNonNull(VrModuleProvider.b());
        boolean z = true;
        if (AbstractC3054ia.b) {
            haVar = AbstractC3054ia.f10145a;
        } else {
            try {
                AbstractC3054ia.f10145a = (AbstractC2883ha) Class.forName("org.chromium.chrome.browser.vr.ArDelegateImpl").newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            } catch (Throwable th) {
                AbstractC3054ia.b = true;
                throw th;
            }
            AbstractC3054ia.b = true;
            haVar = AbstractC3054ia.f10145a;
        }
        if (haVar == null || !haVar.a()) {
            CompositorViewHolder compositorViewHolder = this.I0;
            if (!(compositorViewHolder == null || (d70 = compositorViewHolder.K) == null)) {
                int i = 0;
                while (true) {
                    if (i >= d70.m0.size()) {
                        AbstractC2300e70 e70 = d70.S;
                        if (e70 == null || !e70.z()) {
                            z = false;
                        }
                    } else if (((LO0) d70.m0.get(i)).u() && ((LO0) d70.m0.get(i)).a()) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    return;
                }
            }
            WebContents R02 = R0();
            SelectionPopupControllerImpl r = R02 != null ? SelectionPopupControllerImpl.r(R02) : null;
            if (r != null && r.e()) {
                r.m();
            } else if (!Z0()) {
                this.f10174J.a();
            }
        }
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma, defpackage.AbstractActivityC5319vq
    public final void onDestroy() {
        AbstractC0124Ca1 P;
        Callback callback;
        ContextualSearchManager contextualSearchManager = this.M0;
        if (contextualSearchManager != null) {
            if (contextualSearchManager.f0) {
                contextualSearchManager.i(0);
                ((ST) contextualSearchManager.G.S0()).K.c(contextualSearchManager.f10642J);
                contextualSearchManager.V.getViewTreeObserver().removeOnGlobalFocusChangeListener(contextualSearchManager.I);
                N.MP0VGkxv(contextualSearchManager.U, contextualSearchManager);
                AbstractC0855Oa1 oa1 = contextualSearchManager.X;
                if (oa1 != null) {
                    oa1.destroy();
                }
                AbstractC1099Sa1 sa1 = contextualSearchManager.Y;
                if (sa1 != null) {
                    sa1.destroy();
                }
                contextualSearchManager.X = null;
                contextualSearchManager.Y = null;
                contextualSearchManager.W.a();
                contextualSearchManager.S.a(0);
                C0283Ep.h().c().c(contextualSearchManager);
                C1796bA bAVar = contextualSearchManager.T;
                if (bAVar != null) {
                    bAVar.z(0, false);
                    D70 d70 = bAVar.q0;
                    if (d70 != null) {
                        d70.R.c(bAVar.r0);
                    }
                    ApplicationStatus.h(bAVar);
                }
                contextualSearchManager.T = null;
            }
            this.M0 = null;
        }
        View$OnClickListenerC5098uY0 uy0 = this.N0;
        if (uy0 != null) {
            AbstractC5268vY0.f11483a.b(uy0);
        }
        AbstractC1099Sa1 sa12 = this.t0;
        if (sa12 != null) {
            sa12.destroy();
            this.t0 = null;
        }
        CompositorViewHolder compositorViewHolder = this.I0;
        if (compositorViewHolder != null) {
            D70 d702 = compositorViewHolder.K;
            if (d702 != null) {
                d702.R.c(this);
            }
            CompositorViewHolder compositorViewHolder2 = this.I0;
            compositorViewHolder2.E(null);
            C2712ga gaVar = compositorViewHolder2.p0;
            if (!(gaVar == null || (callback = compositorViewHolder2.q0) == null)) {
                gaVar.I.c(callback);
            }
            LayerTitleCache layerTitleCache = compositorViewHolder2.L;
            if (layerTitleCache != null) {
                long j = layerTitleCache.f;
                if (j != 0) {
                    N.M4YKMV3h(j);
                    layerTitleCache.f = 0;
                }
            }
            CompositorView compositorView = compositorViewHolder2.M;
            ((SurfaceHolder$Callback2C0723Lw) compositorView.G).g();
            C0784Mw mw = compositorView.V;
            if (mw != null) {
                mw.f8511a.getContext().getApplicationContext().unregisterReceiver(mw);
            }
            long j2 = compositorView.K;
            if (j2 != 0) {
                N.MsBICFNS(j2, compositorView);
            }
            compositorView.K = 0;
            D70 d703 = compositorViewHolder2.K;
            if (d703 != null) {
                d703.i();
            }
            W10 w10 = compositorViewHolder2.c0;
            if (w10 != null) {
                w10.H.c(compositorViewHolder2);
                compositorViewHolder2.c0 = null;
            }
            Iterator it = compositorViewHolder2.t0.iterator();
            while (it.hasNext()) {
                ((AbstractC0240Dy) it.next()).e(null);
            }
            compositorViewHolder2.t0.clear();
            AbstractViewGroup$OnHierarchyChangeListenerC1520Yy yy = compositorViewHolder2.h0;
            if (yy != null) {
                yy.H.c(compositorViewHolder2);
            }
            this.I0 = null;
        }
        j1();
        if (this.U0) {
            CombinedPolicyProvider.a().f.remove(this);
            this.U0 = false;
        }
        TabContentManager tabContentManager = this.x0;
        if (tabContentManager != null) {
            Set set = tabContentManager.b;
            if (set != null) {
                set.clear();
            }
            long j3 = tabContentManager.g;
            if (j3 != 0) {
                N.MKivVmLp(j3);
                tabContentManager.g = 0;
            }
            this.x0 = null;
        }
        if (this.w0 != null) {
            this.w0 = null;
        }
        C3402kc0 kc0 = this.S0;
        Iterator it2 = kc0.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it2;
            if (!uq0.hasNext()) {
                break;
            }
            ManualFillingComponentBridge manualFillingComponentBridge = ((C2548fc0) uq0.next()).f9932a;
            long j4 = manualFillingComponentBridge.d;
            if (j4 != 0) {
                N.MVOJP_9v(j4, manualFillingComponentBridge);
            }
        }
        View$OnLayoutChangeListenerC4598rc0 rc0 = kc0.f10290a;
        if (rc0.c0()) {
            rc0.f0();
            C2712ga gaVar2 = rc0.G.O;
            C1078Rq0 rq0 = rc0.I;
            gaVar2.f10005J.remove(rq0);
            rq0.I.c(gaVar2.K);
            gaVar2.n();
            rc0.N.findViewById(16908290).removeOnLayoutChangeListener(rc0);
            rc0.O.destroy();
            C0007Ac0 ac0 = rc0.f11207J;
            for (C5958zc0 zc0 : ac0.f7681a.values()) {
                zc0.a();
            }
            ac0.f7681a.clear();
            Iterator it3 = rc0.K.iterator();
            while (it3.hasNext()) {
                ((Tab) it3.next()).I(rc0.R);
            }
            rc0.K.clear();
            ((ST) rc0.N.S0()).K.c(rc0.S);
            ((C5638xj) rc0.Q).r(rc0.T);
            rc0.G = null;
            rc0.N = null;
        }
        C2287e3 e3Var = this.V0;
        if (e3Var != null) {
            e3Var.a();
            this.V0 = null;
        }
        if (this.D0 && (P = P()) != null) {
            P.destroy();
        }
        C1078Rq0 rq02 = this.r0;
        if (rq02 != null) {
            BookmarkBridge bookmarkBridge = (BookmarkBridge) rq02.H;
            if (bookmarkBridge != null) {
                bookmarkBridge.a();
            }
            this.r0 = null;
        }
        C1595a3 a3Var = this.W0;
        a3Var.F.clear();
        D70 d704 = a3Var.I;
        if (d704 != null) {
            d704.R.c(a3Var.f9404J);
        }
        a3Var.I = null;
        AbstractC0855Oa1 oa12 = a3Var.L;
        if (oa12 != null) {
            oa12.destroy();
        }
        AbstractC0612Ka1 ka1 = a3Var.M;
        if (ka1 != null) {
            ((AbstractC0246Ea1) a3Var.K).f.c(ka1);
            a3Var.M = null;
        }
        a3Var.K = null;
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onGetDirectActions(CancellationSignal cancellationSignal, Consumer consumer) {
        if (this.b1.d0 != null) {
            consumer.accept(Collections.emptyList());
        }
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onMultiWindowModeChanged(boolean z) {
        if (this.E0) {
            if (z) {
                AbstractC3535lK0.a("Android.MultiWindowMode.Enter");
            } else {
                AbstractC3535lK0.a("Android.MultiWindowMode.Exit");
            }
            if (!z && ApplicationStatus.e(this) == 3) {
                UmaSessionStats umaSessionStats = this.y0;
                if (umaSessionStats != null) {
                    umaSessionStats.c();
                }
                f1();
                N.MJuAVRUC(isInMultiWindowMode());
            }
        }
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        super.onMultiWindowModeChanged(z);
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onNewIntent(Intent intent) {
        VrModuleProvider.b().b(this, intent);
        super.onNewIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || !l1(menuItem.getItemId(), null)) {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override // android.app.Activity
    public void onPerformDirectAction(String str, Bundle bundle, CancellationSignal cancellationSignal, Consumer consumer) {
        if (this.b1.d0 != null) {
            consumer.accept(Bundle.EMPTY);
        }
    }

    public void onProvideAssistContent(AssistContent assistContent) {
        Tab K02 = K0();
        boolean e12 = e1();
        String str = null;
        if (!AbstractC4226pO.a() || !N.M09VlOh_("TranslateAssistContent")) {
            AbstractC4972tn1.a(0);
        } else if (e12) {
            AbstractC4972tn1.a(3);
        } else if (K02 == null) {
            AbstractC4972tn1.a(1);
        } else if (K02.a()) {
            AbstractC4972tn1.a(2);
        } else {
            try {
                JSONObject put = new JSONObject().put("@type", "WebPage").put("url", K02.getUrl().h());
                if (!N.Mx5ZGJOG(K02.l(), false)) {
                    AbstractC4972tn1.a(4);
                    str = put.toString();
                } else {
                    String MfoMDliR = N.MfoMDliR(K02.l());
                    if (TextUtils.isEmpty(MfoMDliR)) {
                        AbstractC4972tn1.a(5);
                        str = put.toString();
                    } else {
                        String Mijf24vV = N.Mijf24vV(K02.l());
                        if (TextUtils.isEmpty(Mijf24vV)) {
                            AbstractC4972tn1.a(6);
                            str = put.toString();
                        } else {
                            String MMKf4EpW = N.MMKf4EpW();
                            put.put("inLanguage", Mijf24vV);
                            if (Mijf24vV.equals(MfoMDliR)) {
                                if (!TextUtils.isEmpty(MMKf4EpW)) {
                                    put.put("workTranslation", new JSONObject().put("inLanguage", MMKf4EpW));
                                }
                                AbstractC4972tn1.a(8);
                            } else {
                                put.put("translationOfWork", new JSONObject().put("inLanguage", MfoMDliR));
                                AbstractC4972tn1.a(9);
                            }
                            str = put.toString();
                        }
                    }
                }
            } catch (JSONException unused) {
                AbstractC4972tn1.a(7);
            }
        }
        if (K02 != null && !K02.a() && !e12) {
            assistContent.setWebUri(Uri.parse(K02.s()));
            if (str != null) {
                assistContent.setStructuredData(str);
            }
        }
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC3119iw, defpackage.AbstractActivityC0731Ma
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onStart() {
        CompositorViewHolder compositorViewHolder;
        if (AbstractC1341Wa.f9155a.c() && (compositorViewHolder = this.I0) != null && !compositorViewHolder.l0) {
            compositorViewHolder.M.setBackgroundColor(-1);
        }
        super.onStart();
        if (!q1()) {
            i1();
        }
        if (this.z0) {
            this.z0 = false;
            PartnerBrowserCustomizations.c().d(getApplicationContext());
            PartnerBrowserCustomizations.c().f(new RunnableC1258Up(this));
        }
        CompositorViewHolder compositorViewHolder2 = this.I0;
        if (compositorViewHolder2 != null) {
            C1551Zj zj = compositorViewHolder2.U;
            if (zj != null) {
                zj.Y.b(compositorViewHolder2);
            }
            compositorViewHolder2.C();
        }
        this.Z0 = getResources().getConfiguration();
        this.X0 = true;
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onStop() {
        C1551Zj zj;
        super.onStop();
        if (!q1()) {
            h1();
        } else if (!hasWindowFocus()) {
            h1();
        }
        this.z0 = true;
        CompositorViewHolder compositorViewHolder = this.I0;
        if (!(compositorViewHolder == null || (zj = compositorViewHolder.U) == null)) {
            zj.Y.c(compositorViewHolder);
        }
        this.X0 = false;
    }

    public void onTrimMemory(int i) {
        DA da;
        super.onTrimMemory(i);
        if (AbstractApplicationC3785mq.h(i) && (da = (DA) findViewById(R.id.control_container)) != null) {
            ((ToolbarControlContainer) da).f10790J.G.H = null;
        }
    }

    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (!v()) {
            if (this.H0 == null) {
                this.H0 = new C2831hD0(this, this.W0, S0());
            }
            this.H0.c();
        }
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void onWindowFocusChanged(boolean z) {
        ClipDescription primaryClipDescription;
        super.onWindowFocusChanged(z);
        if (q1()) {
            if (z) {
                i1();
            } else if (ApplicationStatus.e(this) == 5) {
                h1();
            }
        }
        Clipboard instance = Clipboard.getInstance();
        if (instance.d != 0 && z && Build.VERSION.SDK_INT >= 29 && (primaryClipDescription = instance.c.getPrimaryClipDescription()) != null) {
            N.MWrNP8sy(instance.d, instance, C3837n7.d(primaryClipDescription));
        }
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma
    public void p() {
        TraceEvent.Y("ChromeActivity:CompositorInitialization", null);
        TabContentManager tabContentManager = this.x0;
        int integer = tabContentManager.k.getResources().getInteger(R.integer.f35740_resource_name_obfuscated_RES_2131492871);
        int integer2 = tabContentManager.k.getResources().getInteger(R.integer.f35760_resource_name_obfuscated_RES_2131492873);
        boolean b = AbstractC4772sd1.b();
        tabContentManager.g = N.MtRahKHu(tabContentManager, tabContentManager.d, TabContentManager.d(tabContentManager.k, R.integer.f35730_resource_name_obfuscated_RES_2131492870, "approximation-thumbnails"), integer, integer2, !DeviceFormFactor.a(tabContentManager.k), b);
        CompositorViewHolder compositorViewHolder = this.I0;
        C2971i3 i3Var = this.b0;
        TabContentManager tabContentManager2 = this.x0;
        CompositorView compositorView = compositorViewHolder.M;
        boolean isLowEndDevice = SysUtils.isLowEndDevice();
        compositorView.O = i3Var;
        i3Var.Y.b(compositorView);
        compositorView.d(i3Var.X);
        compositorView.P = tabContentManager2;
        compositorView.K = N.M1onz6N$(compositorView, isLowEndDevice, i3Var, tabContentManager2);
        compositorView.I = isLowEndDevice;
        ((SurfaceHolder$Callback2C0723Lw) compositorView.G).f(compositorView.b());
        compositorView.setVisibility(0);
        compositorView.N = (ResourceManager) N.MKvVEaSX(compositorView.K, compositorView);
        N.M_Nkznfe(compositorView.K, compositorView);
        if (C5052uE.c().b) {
            compositorViewHolder.L = new LayerTitleCache(compositorViewHolder.getContext(), compositorViewHolder.M.N);
        }
        if (compositorViewHolder.b0 != null) {
            IJ a2 = compositorViewHolder.M.N.a();
            a2.c.put(R.id.control_container, ((ToolbarControlContainer) compositorViewHolder.b0).f10790J.G);
        }
        C2712ga gaVar = i3Var.O;
        compositorViewHolder.p0 = gaVar;
        C0967Pw pw = new C0967Pw(compositorViewHolder);
        compositorViewHolder.q0 = pw;
        gaVar.l(pw);
        if (AbstractC5686xz.e()) {
            this.M0 = new ContextualSearchManager(this, this, this.b1.c0, this.W0);
        }
        TraceEvent.f0("ChromeActivity:CompositorInitialization");
    }

    public void p1() {
    }

    @Override // defpackage.KO0
    public void q(int i) {
    }

    public final boolean q1() {
        return Build.VERSION.SDK_INT < 29;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void r0() {
        W10 w10;
        this.R0 = SystemClock.elapsedRealtime();
        V0().i();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) findViewById(R.id.compositor_view_holder);
        this.I0 = compositorViewHolder;
        CompositorView compositorView = compositorViewHolder.M;
        if (compositorView.G == null) {
            Object obj = ThreadUtils.f10596a;
            compositorView.c();
        }
        compositorView.Q = viewGroup;
        viewGroup.setFitsSystemWindows(false);
        int i = W10.F;
        if (Build.VERSION.SDK_INT >= 28) {
            w10 = new U10(this);
        } else {
            w10 = new W10(this);
        }
        this.L0 = w10;
        viewGroup.addView(w10, 0);
        super.r0();
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void startActivityForResult(Intent intent, int i) {
        startActivityForResult(intent, i, null);
    }

    public boolean startActivityIfNeeded(Intent intent, int i) {
        return startActivityIfNeeded(intent, i, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0090  */
    @Override // defpackage.AbstractActivityC0731Ma
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void t0(android.content.res.Configuration r9) {
        /*
        // Method dump skipped, instructions count: 147
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.app.ChromeActivity.t0(android.content.res.Configuration):void");
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma
    public void u() {
        this.E0 = true;
        AbstractC3276jr0.a();
        g1();
        DownloadManagerService p = DownloadManagerService.p();
        if (!p.W) {
            p.Q = new KH(false);
            p.R = new KH(true);
            NU0.f8549a.l("ResumptionAttemptLeft");
            DownloadManagerService p2 = DownloadManagerService.p();
            N.MQ35Y$D$(p2.t(), p2, ProfileKey.a());
            p.W = true;
        }
        C2780gw1 gw1 = VrModuleProvider.f10799a;
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        if (this.d0 == null && getIntent() != null) {
            AbstractC2097cw1 b = VrModuleProvider.b();
            getIntent();
            Objects.requireNonNull((C2609fw1) b);
        }
        if (N.M09VlOh_("ChromeShareScreenshot") && BundleUtils.isolatedSplitsEnabled() && Build.VERSION.SDK_INT >= 26) {
            Objects.requireNonNull(AppHooks.get());
        }
        super.u();
        C3402kc0 kc0 = this.S0;
        C2971i3 i3Var = this.b0;
        C5638xj xjVar = this.b1.a0;
        ViewStub viewStub = (ViewStub) findViewById(R.id.keyboard_accessory_stub);
        ViewStub viewStub2 = (ViewStub) findViewById(R.id.keyboard_accessory_sheet_stub);
        Objects.requireNonNull(kc0);
        if (!(viewStub == null || viewStub2 == null)) {
            if (N.M09VlOh_("AutofillKeyboardAccessory")) {
                viewStub.setLayoutResource(R.layout.f38940_resource_name_obfuscated_RES_2131624203);
            } else {
                viewStub.setLayoutResource(R.layout.f38900_resource_name_obfuscated_RES_2131624199);
            }
            viewStub2.setLayoutResource(R.layout.f38950_resource_name_obfuscated_RES_2131624204);
            C2294e50 e50 = new C2294e50(kc0.f10290a, viewStub);
            C3474l0 l0Var = new C3474l0(viewStub2);
            View$OnLayoutChangeListenerC4598rc0 rc0 = kc0.f10290a;
            Objects.requireNonNull(rc0);
            rc0.N = (ChromeActivity) i3Var.s0().get();
            rc0.G = i3Var;
            C2712ga gaVar = i3Var.O;
            C1078Rq0 rq0 = rc0.I;
            gaVar.f10005J.add(rq0);
            rq0.l(gaVar.K);
            rc0.L = e50;
            rc0.Q = xjVar;
            rc0.F.j(AbstractC4938tc0.b, rc0.Y());
            UH0 uh0 = rc0.F;
            uh0.f9530a.b(new C3573lc0(rc0));
            rc0.M = l0Var;
            R50 r50 = rc0.L.b.b;
            Objects.requireNonNull(r50);
            l0Var.f10320a.F.m(AbstractC4158p0.f, new Q50(r50));
            rc0.M.b(rc0.N.getResources().getDimensionPixelSize(R.dimen.f20280_resource_name_obfuscated_RES_2131165647) * 3);
            ChromeActivity chromeActivity = rc0.N;
            chromeActivity.getClass();
            rc0.H = new C3744mc0(chromeActivity);
            rc0.N.findViewById(16908290).addOnLayoutChangeListener(rc0);
            rc0.O = new C4428qc0(rc0, rc0.N.P());
            UT S02 = rc0.N.S0();
            ((ST) S02).K.b(rc0.S);
            ((C5638xj) rc0.Q).j(rc0.T);
            Tab tab = rc0.N.W0.H;
            if (tab != null && rc0.K.add(tab)) {
                tab.A(rc0.R);
            }
            rc0.g0();
        }
        if (N.M09VlOh_("AndroidNightModeTabReparenting") || N.M09VlOh_("AndroidLayoutChangeTabReparenting")) {
            this.a1 = new C5276vb1(new VL0(P()), AbstractC1341Wa.f9155a);
        }
        N.M09VlOh_("InterestFeedSpinnerAlwaysAnimate");
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void u0() {
        TraceEvent j0 = TraceEvent.j0("ChromeActivity.performPostInflationStartup");
        try {
            NQ.a(n0(), new RunnableC0610Ka(this));
            Intent intent = getIntent();
            if (intent != null && this.d0 == null) {
                VrModuleProvider.b().b(this, intent);
            }
            BottomContainer bottomContainer = (BottomContainer) findViewById(R.id.bottom_container);
            View$OnClickListenerC5098uY0 uy0 = new View$OnClickListenerC5098uY0(this, bottomContainer, this.b0);
            this.N0 = uy0;
            AbstractC5268vY0.f11483a.a(this.b0.U, uy0);
            CombinedPolicyProvider.a().f.add(this);
            boolean z = true;
            this.U0 = true;
            C2971i3 i3Var = this.b0;
            i3Var.M = this.I0.M;
            i3Var.P = i3Var.N.isTouchExplorationEnabled();
            i3Var.A0();
            i3Var.Q = new Ny1(i3Var);
            c1();
            CompositorViewHolder compositorViewHolder = this.I0;
            if (SysUtils.isLowEndDevice()) {
                z = false;
            }
            AbstractC0124Ca1 ca1 = this.s0;
            TabContentManager tabContentManager = new TabContentManager(this, compositorViewHolder, z, ca1 != null ? new C0770Mp(ca1) : null);
            this.x0 = tabContentManager;
            new C3323k61(tabContentManager, S0(), P());
            this.w0.m(tabContentManager);
            if (!isFinishing()) {
                M0().d((DA) findViewById(R.id.control_container), this.W0, P(), N0());
            }
            C1551Zj M02 = M0();
            C2712ga gaVar = this.b0.O;
            bottomContainer.G = M02;
            M02.Y.b(bottomContainer);
            bottomContainer.H = gaVar;
            gaVar.l(bottomContainer.F);
            bottomContainer.setTranslationY(bottomContainer.I);
            this.Y.a(bottomContainer);
            this.K0.m(new GT0(this.b1.a0, this.Y, this.W0, new FT0(), d1()));
            if (this.X0) {
                CompositorViewHolder compositorViewHolder2 = this.I0;
                C1551Zj zj = compositorViewHolder2.U;
                if (zj != null) {
                    zj.Y.b(compositorViewHolder2);
                }
                compositorViewHolder2.C();
            }
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void v0() {
        GN0 E02 = E0();
        this.b1 = E02;
        E02.getClass();
        M0();
        M0();
        M0();
        S0();
        V0();
        ScreenOrientationProviderImpl.getInstance();
        Objects.requireNonNull(AbstractApplicationC3785mq.g());
        ((C1078Rq0) this.q0).l(new C1014Qp(this));
        super.v0();
        C2609fw1 fw1 = (C2609fw1) VrModuleProvider.b();
        Objects.requireNonNull(fw1);
        if (VrModuleProvider.d().a(this, getIntent()) && fw1.a() && !fw1.d(this, true)) {
            finish();
        }
        this.z0 = !PartnerBrowserCustomizations.c().e;
        AbstractC1575Zv e = AbstractC1575Zv.e();
        if (!e.g("disable-fullscreen")) {
            TypedValue typedValue = new TypedValue();
            getResources().getValue(R.dimen.f26560_resource_name_obfuscated_RES_2131166275, typedValue, true);
            e.b("top-controls-show-threshold", typedValue.coerceToString().toString());
            getResources().getValue(R.dimen.f26550_resource_name_obfuscated_RES_2131166274, typedValue, true);
            e.b("top-controls-hide-threshold", typedValue.coerceToString().toString());
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.f12900_resource_name_obfuscated_RES_2131099980)));
    }

    @Override // defpackage.AbstractC3083ik
    public boolean w() {
        return true;
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean y() {
        return true;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public final void y0() {
        this.Q0 = SystemClock.elapsedRealtime();
        TraceEvent j0 = TraceEvent.j0("ChromeActivity.triggerLayoutInflation");
        boolean z = true;
        try {
            SelectionPopupControllerImpl.G = true;
            if (!SysUtils.isLowEndDevice()) {
                getWindow().addFlags(16777216);
                this.T0 = true;
            }
            if (X0() == R.style.f72860_resource_name_obfuscated_RES_2132017859) {
                setTheme(R.style.f72860_resource_name_obfuscated_RES_2132017859);
            }
            Bw1 a2 = Bw1.a();
            int O02 = O0();
            Objects.requireNonNull(a2);
            Object obj = ThreadUtils.f10596a;
            if (a2.e == null || a2.d != O02) {
                z = false;
            }
            if (z) {
                View view = new View(this);
                setContentView(view);
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                ViewGroup viewGroup2 = a2.e;
                a2.e = null;
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeView(childAt);
                        viewGroup.addView(childAt);
                    }
                }
                viewGroup.removeView(view);
                r0();
            } else {
                a2.e = null;
                H0();
            }
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 z(boolean z, boolean z2) {
        return S(z);
    }

    public void z0(Tab tab) {
        BookmarkBridge bookmarkBridge;
        if (tab != null && !tab.G() && (bookmarkBridge = (BookmarkBridge) this.r0.H) != null && bookmarkBridge.f()) {
            Um1.a(Profile.b()).notifyEvent("app_menu_bookmark_star_icon_pressed");
            C0695Li li = new C0695Li();
            li.b(new RunnableC1319Vp(this, tab, li, bookmarkBridge));
        }
    }

    public void startActivity(Intent intent, Bundle bundle) {
        Objects.requireNonNull(VrModuleProvider.b());
        Objects.requireNonNull(VrModuleProvider.b());
        super.startActivity(intent, bundle);
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        Objects.requireNonNull(VrModuleProvider.b());
        super.startActivityForResult(intent, i, bundle);
    }

    public boolean startActivityIfNeeded(Intent intent, int i, Bundle bundle) {
        Objects.requireNonNull(VrModuleProvider.b());
        return super.startActivityIfNeeded(intent, i, bundle);
    }
}
