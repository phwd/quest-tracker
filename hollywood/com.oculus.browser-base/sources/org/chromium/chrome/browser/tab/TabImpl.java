package org.chromium.chrome.browser.tab;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabImpl implements Tab, AbstractC1282Va1 {
    public long F;
    public final int G;
    public final boolean H;
    public final Context I;

    /* renamed from: J  reason: collision with root package name */
    public WindowAndroid f10773J;
    public AbstractC5818ym0 K;
    public WebContents L;
    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy M;
    public View N;
    public C0011Ad1 O;
    public final C1322Vq0 P = new C1322Vq0();
    public TabWebContentsDelegateAndroidImpl Q;
    public int R = -1;
    public boolean S;
    public boolean T;
    public boolean U = true;
    public final Integer V;
    public Integer W;
    public LoadUrlParams X;
    public boolean Y;
    public boolean Z;
    public boolean a0 = true;
    public int b0 = 0;
    public boolean c0;
    public boolean d0;
    public C61 e0;
    public View.OnAttachStateChangeListener f0;
    public boolean g0;
    public boolean h0;
    public AbstractC3226ja1 i0;
    public final Rr1 j0;
    public boolean k0;
    public C1078Rq0 l0;
    public int m0;
    public boolean n0;

    public TabImpl(int i, boolean z, Integer num, byte[] bArr, AbstractC3226ja1 ja1) {
        Rr1 rr1 = new Rr1();
        this.j0 = rr1;
        C1078Rq0 rq0 = new C1078Rq0();
        this.l0 = rq0;
        rq0.m(Boolean.FALSE);
        C4184p81 a2 = C4184p81.a();
        i = i == -1 ? a2.c.getAndIncrement() : i;
        a2.b(i + 1);
        this.G = i;
        this.H = z;
        if (bArr != null && CachedFeatureFlags.isEnabled("CriticalPersistedTabData")) {
            int i2 = C5383wB.N;
            EnumC3169jC0 a3 = EnumC3169jC0.a(C5383wB.class, z);
            AbstractC2145dC0 dc0 = (AbstractC2145dC0) rr1.e(C5383wB.class, new C5383wB(this, bArr, a3.b(), a3.R));
            this.n0 = true;
        }
        this.I = AbstractC3612lp0.b(ContextUtils.getApplicationContext(), ChromeActivity.X0(), false);
        this.V = num;
        this.i0 = ja1;
        this.f0 = new View$OnAttachStateChangeListenerC4866t81(this);
        this.O = new C0011Ad1(this);
        new C4602rd1(this, new C4355q81(this));
        this.m0 = 0;
    }

    public static boolean c0(Tab tab) {
        if (((TabImpl) tab).L == null || ((TabImpl) tab).L.I() == null) {
            return true;
        }
        return false;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void A(AbstractC1404Xa1 xa1) {
        this.P.b(xa1);
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void B() {
        WebContents webContents = this.L;
        if (webContents != null) {
            webContents.f().q(true);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public AbstractC5792yd1 C() {
        return this.O;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void D(boolean z) {
        this.U = z;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public LoadUrlParams E() {
        return this.X;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public int F() {
        return this.V.intValue();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean G() {
        return !isNativePage() && this.L == null;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public float H() {
        if (!this.Y) {
            return 1.0f;
        }
        return (float) ((int) this.L.v());
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void I(AbstractC1404Xa1 xa1) {
        this.P.c(xa1);
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean J() {
        return this.Z;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void K(boolean z) {
        this.l0.m(Boolean.valueOf(z));
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean L() {
        return this.N != null;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public Rr1 M() {
        return this.j0;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void N(int i) {
        Tab tab;
        try {
            TraceEvent.Y("Tab.hide", null);
            if (!isHidden()) {
                this.a0 = true;
                l0();
                WebContents webContents = this.L;
                if (webContents != null) {
                    webContents.z();
                }
                C5988zm0 zm0 = C5988zm0.f11767a;
                zm0.b.add(new WeakReference(this));
                if (zm0.b.size() > 3 && (tab = (Tab) ((WeakReference) zm0.b.remove(0)).get()) != null) {
                    tab.r();
                }
                Iterator it = this.P.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((AbstractC1404Xa1) uq0.next()).A(this, i);
                    } else {
                        TraceEvent.f0("Tab.hide");
                        return;
                    }
                }
            }
        } finally {
            TraceEvent.f0("Tab.hide");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void O(WindowAndroid windowAndroid, C61 c61) {
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).i(this, null);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean P() {
        int a2 = LR0.a(this.L);
        return (a2 == 5 || a2 == 4) ? false : true;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public AbstractC5818ym0 Q() {
        return this.K;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void R(boolean z) {
        this.S = z;
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).m(this, z);
            } else {
                return;
            }
        }
    }

    public final void S(boolean z) {
        if (this.L != null) {
            this.M.removeOnAttachStateChangeListener(this.f0);
            this.M = null;
            l0();
            WebContents webContents = this.L;
            this.L = null;
            this.Q = null;
            if (z) {
                N.MYIgyGYO(this.F);
                return;
            }
            N.MoDA8Gdb(this.F);
            webContents.m0();
        }
    }

    public void T(int i) {
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).I(this, i);
            } else {
                this.Z = false;
                return;
            }
        }
    }

    public void U(GURL gurl) {
        this.U = true;
        n0();
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).J(this, gurl);
            } else {
                this.Z = false;
                return;
            }
        }
    }

    public void V(GURL gurl) {
        n0();
        if (this.c0) {
            Z(true);
        }
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).K(this, gurl);
            } else {
                return;
            }
        }
    }

    @Deprecated
    public ChromeActivity W() {
        WindowAndroid windowAndroid = this.f10773J;
        if (windowAndroid == null) {
            return null;
        }
        Activity a2 = ContextUtils.a((Context) windowAndroid.f11022J.get());
        if (a2 instanceof ChromeActivity) {
            return (ChromeActivity) a2;
        }
        return null;
    }

    public AbstractC0124Ca1 X() {
        if (W() != null) {
            return W().P();
        }
        AbstractC3226ja1 ja1 = this.i0;
        if (ja1 != null) {
            return ja1.P();
        }
        return null;
    }

    public C1261Uq0 Y() {
        return this.P.e();
    }

    public void Z(boolean z) {
        this.c0 = !z;
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).L(this, z);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean a() {
        return this.H;
    }

    public final void a0(boolean z, Runnable runnable) {
        AbstractC5818ym0 ym0 = this.K;
        if (ym0 != null) {
            Objects.requireNonNull(ym0);
            this.K = null;
        }
        if (runnable != null) {
            runnable.run();
        }
        if (z) {
            e0();
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public View b() {
        View view = this.N;
        if (view != null) {
            return view;
        }
        AbstractC5818ym0 ym0 = this.K;
        if (ym0 == null) {
            return this.M;
        }
        Objects.requireNonNull(ym0);
        return null;
    }

    public final void b0(WebContents webContents) {
        try {
            TraceEvent.Y("ChromeTab.initWebContents", null);
            WebContents webContents2 = this.L;
            this.L = webContents;
            Context context = this.I;
            int i = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
            C1459Xy xy = new C1459Xy(context, null, webContents);
            xy.setContentDescription(this.I.getResources().getString(R.string.f45350_resource_name_obfuscated_RES_2131951852));
            this.M = xy;
            webContents.J("89.0.4389.105", new C5622xd1(this, xy), xy, this.f10773J, new C3466kx1());
            a0(false, null);
            if (webContents2 != null) {
                webContents2.G(0);
                ((WebContentsAccessibilityImpl) AbstractC3637lx1.a(webContents2)).t(false);
            }
            this.L.G(this.b0);
            N.Mt4iWzCb(this.L);
            this.M.addOnAttachStateChangeListener(this.f0);
            l0();
            this.Q = new TabWebContentsDelegateAndroidImpl(this, this.e0.a(this));
            N.MUKSQbrZ(this.F, this.H, c0(this), webContents, this.R, this.Q, new C4690s61(this.e0.e(this), this));
            this.L.q0();
            AbstractC4013o81.b(this);
            e0();
        } finally {
            TraceEvent.f0("ChromeTab.initWebContents");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public int c(LoadUrlParams loadUrlParams) {
        String str;
        Throwable th;
        int M0oMLHHt;
        String str2 = null;
        try {
            TraceEvent.Y("Tab.loadUrl", null);
            if (!this.d0) {
                this.d0 = d0(loadUrlParams.f10938a, false);
            }
            if (!"chrome://java-crash/".equals(loadUrlParams.f10938a)) {
                long j = this.F;
                if (j != 0) {
                    String str3 = loadUrlParams.f10938a;
                    Origin origin = loadUrlParams.b;
                    String str4 = loadUrlParams.f;
                    ResourceRequestBody resourceRequestBody = loadUrlParams.h;
                    int i = loadUrlParams.c;
                    C2512fL0 fl0 = loadUrlParams.d;
                    if (fl0 != null) {
                        str2 = fl0.f9916a;
                    }
                    str = "Tab.loadUrl";
                    try {
                        M0oMLHHt = N.M0oMLHHt(j, str3, origin, str4, resourceRequestBody, i, str2, fl0 != null ? fl0.b : 0, loadUrlParams.i, loadUrlParams.j, loadUrlParams.m, false, loadUrlParams.l, loadUrlParams.k, loadUrlParams.g == 2);
                    } catch (Throwable th2) {
                        th = th2;
                        TraceEvent.f0(str);
                        throw th;
                    }
                    try {
                        Iterator it = this.P.iterator();
                        while (true) {
                            C1261Uq0 uq0 = (C1261Uq0) it;
                            if (uq0.hasNext()) {
                                ((AbstractC1404Xa1) uq0.next()).G(this, loadUrlParams, M0oMLHHt);
                            } else {
                                TraceEvent.f0(str);
                                return M0oMLHHt;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        TraceEvent.f0(str);
                        throw th;
                    }
                } else {
                    throw new RuntimeException("Tab.loadUrl called when no native side exists");
                }
            } else {
                throw new RuntimeException("Intentional Java Crash");
            }
        } catch (Throwable th4) {
            th = th4;
            str = "Tab.loadUrl";
            TraceEvent.f0(str);
            throw th;
        }
    }

    public final void clearNativePtr() {
        this.F = 0;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean d() {
        return this.Y;
    }

    public boolean d0(String str, boolean z) {
        AbstractC5818ym0 ym0;
        boolean z2 = false;
        if (c0(this)) {
            return false;
        }
        if (z) {
            ym0 = null;
        } else {
            ym0 = this.K;
        }
        AbstractC5818ym0 b = this.e0.b(str, ym0, this);
        if (b != null) {
            z2 = true;
            if (this.K != b) {
                a0(true, new RunnableC4696s81(this, b));
            }
            h0();
            C1261Uq0 Y2 = Y();
            while (Y2.hasNext()) {
                ((AbstractC1404Xa1) Y2.next()).x(this, null);
            }
        }
        return z2;
    }

    public final void deleteNavigationEntriesFromFrozenState(long j) {
        Ax1 ax1;
        Ax1 ax12 = C5383wB.q(this).T;
        if (ax12 != null) {
            ByteBuffer byteBuffer = (ByteBuffer) N.MGuJ$X8n(ax12.f7707a, ax12.b, j);
            if (byteBuffer == null) {
                ax1 = null;
            } else {
                Ax1 ax13 = new Ax1(byteBuffer);
                ax13.b = 2;
                ax1 = ax13;
            }
            if (ax1 != null) {
                C5383wB q = C5383wB.q(this);
                q.T = ax1;
                q.n();
                g0();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void destroy() {
        this.k0 = true;
        n0();
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC1404Xa1) uq0.next()).r(this);
        }
        this.P.clear();
        Rr1 rr1 = this.j0;
        rr1.b();
        HashMap hashMap = rr1.b;
        rr1.b = null;
        for (Qr1 qr1 : hashMap.values()) {
            qr1.destroy();
        }
        C0011Ad1 ad1 = this.O;
        TabImpl tabImpl = ad1.I;
        tabImpl.N = null;
        tabImpl.e0();
        AbstractC0072Bd1 bd1 = (AbstractC0072Bd1) ad1.H.peek();
        if (bd1 != null) {
            bd1.e();
        }
        ad1.H.clear();
        AbstractC4541rE rEVar = ad1.K;
        if (rEVar != null) {
            C1708ak akVar = (C1708ak) rEVar;
            ((C1551Zj) akVar.f9447J).Y.c(akVar);
        }
        ad1.I = null;
        a0(false, null);
        S(true);
        List list = AbstractC5036u81.f11390a;
        Object obj = ThreadUtils.f10596a;
        AbstractC5036u81.f11390a.remove(this);
        long j = this.F;
        if (j != 0) {
            N.M1Fyow7a(j);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void e() {
        WebContents webContents = this.L;
        if (webContents != null) {
            webContents.f().e();
        }
    }

    public void e0() {
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).n(this);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean f() {
        WebContents webContents = this.L;
        return webContents != null && webContents.f().f();
    }

    public void f0(float f) {
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).D(this, f);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean g() {
        return this.k0;
    }

    public void g0() {
        this.U = true;
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1404Xa1) uq0.next()).H(this);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public Context getContext() {
        WindowAndroid windowAndroid = this.f10773J;
        if (windowAndroid == null) {
            return this.I;
        }
        Context context = (Context) windowAndroid.f11022J.get();
        return context == context.getApplicationContext() ? this.I : context;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public int getId() {
        return this.G;
    }

    public final long getNativePtr() {
        return this.F;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public String getTitle() {
        if (this.S) {
            return "";
        }
        if (C5383wB.q(this).O == null) {
            n0();
        }
        return C5383wB.q(this).O;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public GURL getUrl() {
        if (!isInitialized() || this.S) {
            return GURL.emptyGURL();
        }
        WebContents webContents = this.L;
        GURL u = webContents != null ? webContents.u() : GURL.emptyGURL();
        if (this.L != null || isNativePage() || !u.h().isEmpty()) {
            C5383wB q = C5383wB.q(this);
            q.P = u;
            q.n();
        }
        if (C5383wB.q(this).P != null) {
            return C5383wB.q(this).P;
        }
        return GURL.emptyGURL();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean h() {
        WebContents webContents = this.L;
        return webContents != null && webContents.f().h();
    }

    public final void h0() {
        C1261Uq0 Y2 = Y();
        while (Y2.hasNext()) {
            ((AbstractC1404Xa1) Y2.next()).Q(this);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public WindowAndroid i() {
        return this.f10773J;
    }

    public void i0() {
        C5383wB q = C5383wB.q(this);
        C1078Rq0 rq0 = this.l0;
        q.K = rq0;
        ZB0 zb0 = new ZB0(q);
        q.L = zb0;
        rq0.l(zb0);
    }

    public final boolean isCustomTab() {
        ChromeActivity W2 = W();
        return W2 != null && W2.d1();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean isHidden() {
        return this.a0;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean isInitialized() {
        return this.F != 0;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean isNativePage() {
        return this.K != null;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean isUserInteractable() {
        return this.g0;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void j() {
        WebContents webContents = this.L;
        if (webContents != null) {
            webContents.f().j();
        }
    }

    public void j0(C0797Nb1 nb1) {
        C5383wB q = C5383wB.q(this);
        q.T = nb1.f8557a;
        q.n();
        C5383wB q2 = C5383wB.q(this);
        q2.S = nb1.d;
        q2.n();
        C5383wB q3 = C5383wB.q(this);
        q3.P = new GURL(nb1.f8557a.a());
        q3.n();
        C5383wB q4 = C5383wB.q(this);
        Ax1 ax1 = nb1.f8557a;
        q4.O = N.MZZlQD12(ax1.f7707a, ax1.b);
        q4.n();
        C5383wB q5 = C5383wB.q(this);
        q5.X = nb1.g;
        q5.n();
        C5383wB q6 = C5383wB.q(this);
        int i = nb1.c;
        if (i == -1) {
            i = this.G;
        }
        q6.u(i);
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean k() {
        WebContents webContents = this.L;
        return webContents != null && webContents.f().k();
    }

    public final boolean k0() {
        boolean z;
        String str;
        try {
            TraceEvent.Y("Tab.unfreezeContents", null);
            Ax1 ax1 = C5383wB.q(this).T;
            WebContents webContents = (WebContents) N.MXGOiJkn(ax1.f7707a, ax1.b, isHidden());
            if (webContents == null) {
                webContents = AbstractC5342vx1.a(Z00.c(this.f10773J, this.H), isHidden());
                Iterator it = this.P.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (!uq0.hasNext()) {
                        break;
                    }
                    ((AbstractC1404Xa1) uq0.next()).M(this);
                }
                z = false;
            } else {
                z = true;
            }
            C5383wB q = C5383wB.q(this);
            q.T = null;
            q.n();
            b0(webContents);
            if (!z) {
                if (C5383wB.q(this).P.h().isEmpty()) {
                    str = "chrome-native://newtab/";
                } else {
                    str = C5383wB.q(this).P.h();
                }
                c(new LoadUrlParams(str, 5));
            }
            return z;
        } finally {
            TraceEvent.f0("Tab.unfreezeContents");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public WebContents l() {
        return this.L;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l0() {
        /*
            r4 = this;
            boolean r0 = r4.a0
            if (r0 != 0) goto L_0x0018
            boolean r0 = r4.G()
            if (r0 != 0) goto L_0x0018
            boolean r0 = r4.h0
            if (r0 != 0) goto L_0x0016
            cw1 r0 = org.chromium.chrome.browser.vr.VrModuleProvider.b()
            java.util.Objects.requireNonNull(r0)
            goto L_0x0018
        L_0x0016:
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            boolean r1 = r4.g0
            if (r0 != r1) goto L_0x001e
            return
        L_0x001e:
            r4.g0 = r0
            Vq0 r1 = r4.P
            java.util.Iterator r1 = r1.iterator()
        L_0x0026:
            r2 = r1
            Uq0 r2 = (defpackage.C1261Uq0) r2
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0039
            java.lang.Object r2 = r2.next()
            Xa1 r2 = (defpackage.AbstractC1404Xa1) r2
            r2.C(r4, r0)
            goto L_0x0026
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabImpl.l0():void");
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public int m() {
        return this.m0;
    }

    public void m0(int i) {
        this.m0 = i;
        C1261Uq0 Y2 = Y();
        while (Y2.hasNext()) {
            ((AbstractC1404Xa1) Y2.next()).s(this, i);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public String n() {
        return HG.b(getUrl());
    }

    public void n0() {
        String str;
        if (!G()) {
            if (isNativePage()) {
                str = ((IT) this.K).b;
            } else {
                WebContents webContents = this.L;
                str = webContents != null ? webContents.getTitle() : "";
            }
            o0(str);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean o() {
        long j = this.F;
        return j != 0 && N.MScJGdM1(j);
    }

    public void o0(String str) {
        if (!TextUtils.equals(C5383wB.q(this).O, str)) {
            this.U = true;
            C5383wB q = C5383wB.q(this);
            q.O = str;
            q.n();
            h0();
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean p() {
        return this.T;
    }

    public void p0(WindowAndroid windowAndroid) {
        this.f10773J = windowAndroid;
        WebContents webContents = this.L;
        if (webContents != null) {
            webContents.e0(windowAndroid);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void q() {
        if (AbstractC2254ds0.f(this)) {
            AbstractC2254ds0.i(this.L, new C1742as0(this));
            return;
        }
        WebContents webContents = this.L;
        if (webContents != null) {
            webContents.f().b(true);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void r() {
        AbstractC5818ym0 ym0 = this.K;
        if (ym0 != null) {
            Objects.requireNonNull(ym0);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public String s() {
        return getUrl().h();
    }

    public final void setNativePtr(long j) {
        this.F = j;
    }

    public void swapWebContents(WebContents webContents, boolean z, boolean z2) {
        Rect rect;
        boolean z3 = (this.M == null || this.L == null) ? false : true;
        if (z3) {
            rect = new Rect(0, 0, this.M.getWidth(), this.M.getHeight());
        } else {
            rect = new Rect();
        }
        Iterator it = this.P.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC1404Xa1) uq0.next()).U(this);
        }
        if (z3) {
            this.L.z();
        }
        Rect a2 = rect.isEmpty() ? AbstractC5112ud1.a(ContextUtils.getApplicationContext()) : null;
        if (a2 != null) {
            rect.set(a2);
        }
        this.L.f0(false);
        S(false);
        a0(false, new RunnableC4525r81(this, webContents, rect, a2));
        if (z) {
            V(getUrl());
            if (z2) {
                U(getUrl());
            }
        }
        Iterator it2 = this.P.iterator();
        while (true) {
            C1261Uq0 uq02 = (C1261Uq0) it2;
            if (uq02.hasNext()) {
                ((AbstractC1404Xa1) uq02.next()).T(this, z, z2);
            } else {
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public void t() {
        if (this.Y) {
            C1261Uq0 Y2 = Y();
            while (Y2.hasNext()) {
                ((AbstractC1404Xa1) Y2.next()).J(this, getUrl());
            }
        }
        WebContents webContents = this.L;
        if (webContents != null) {
            webContents.stop();
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy u() {
        return this.M;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean v() {
        long j = this.F;
        return j != 0 && N.Mx8ntX9U(j);
    }

    @Override // defpackage.AbstractC1282Va1
    public void w(boolean z) {
        View b = b();
        boolean z2 = true;
        if (b != null) {
            int i = z ? 4 : 1;
            if (b.getImportantForAccessibility() != i) {
                b.setImportantForAccessibility(i);
                b.sendAccessibilityEvent(2048);
            }
        }
        WebContents webContents = this.L;
        AbstractC3808mx1 a2 = webContents != null ? AbstractC3637lx1.a(webContents) : null;
        if (a2 != null) {
            if (!z && !L()) {
                z2 = false;
            }
            ((WebContentsAccessibilityImpl) a2).t(z2);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean x() {
        return this.S;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void y(int i) {
        try {
            TraceEvent.Y("Tab.show", null);
            if (isHidden()) {
                this.a0 = false;
                l0();
                z();
                WebContents webContents = this.L;
                if (webContents != null) {
                    webContents.O();
                }
                AbstractC5818ym0 ym0 = this.K;
                if (ym0 != null) {
                    d0(((IT) ym0).f8227a, true);
                }
                C5988zm0 zm0 = C5988zm0.f11767a;
                for (int i2 = 0; i2 < zm0.b.size(); i2++) {
                    if (((Tab) ((WeakReference) zm0.b.get(i2)).get()) == this) {
                        zm0.b.remove(i2);
                    }
                }
                AbstractC5036u81.a(this);
                if (H() < 100.0f) {
                    f0(H());
                }
                Iterator it = this.P.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((AbstractC1404Xa1) uq0.next()).P(this, i);
                    } else {
                        C5383wB q = C5383wB.q(this);
                        q.S = System.currentTimeMillis();
                        q.n();
                        TraceEvent.f0("Tab.show");
                        return;
                    }
                }
            }
        } finally {
            TraceEvent.f0("Tab.show");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public boolean z() {
        C3821n11 n11;
        boolean z = false;
        if (this.i0 == null) {
            AbstractC1220Ua0.a("Tab", "Tab couldn't be loaded because Context was null.", new Object[0]);
            return false;
        } else if (this.X != null) {
            WebContents e = Bw1.a().e(this.H, isHidden(), isCustomTab());
            if (e == null) {
                e = AbstractC5342vx1.a(Z00.c(this.f10773J, this.H), isHidden());
            }
            b0(e);
            c(this.X);
            this.X = null;
            return true;
        } else {
            if (G()) {
                boolean z2 = AbstractC3992o11.f10528a;
                if (CachedFeatureFlags.isEnabled("PaintPreviewShowOnStartup") && AbstractC3992o11.f10528a && !C0283Ep.h().d() && (n11 = (C3821n11) AbstractC3992o11.b.get(i())) != null) {
                    AbstractC3992o11.f10528a = false;
                    C2454f11 f11 = new C2454f11(this, n11.H.G, new RunnableC2625g11(n11), new C2796h11(n11), n11.f10469J);
                    f11.m = n11.F;
                    f11.n = new C2967i11();
                    f11.o = new C3137j11(this);
                    C3650m11 m11 = new C3650m11(f11);
                    Object obj = ThreadUtils.f10596a;
                    if (PageLoadMetrics.f10695a == null) {
                        PageLoadMetrics.f10695a = new C1322Vq0();
                    }
                    PageLoadMetrics.f10695a.b(m11);
                    f11.d = new RunnableC3308k11(m11);
                    if (f11.h == 0) {
                        z = f11.c.j(f11);
                        Objects.requireNonNull(f11.b);
                        AbstractC3100ip1.f10165a.a("Browser.PaintPreview.TabbedPlayer.HadCapture", z);
                        f11.h = z ? 1 : 3;
                    }
                    if (!z) {
                        Runnable runnable = f11.d;
                        if (runnable != null) {
                            runnable.run();
                            f11.d = null;
                        }
                        f11.f9892a.I(f11.f);
                    }
                }
            }
            try {
                TraceEvent.Y("Tab.restoreIfNeeded", null);
                if ((!G() || C5383wB.q(this).T == null || k0()) && f()) {
                    WebContents webContents = this.L;
                    if (webContents != null) {
                        webContents.f().o();
                    }
                    this.Z = true;
                    Iterator it = this.P.iterator();
                    while (true) {
                        C1261Uq0 uq0 = (C1261Uq0) it;
                        if (!uq0.hasNext()) {
                            break;
                        }
                        ((AbstractC1404Xa1) uq0.next()).N(this);
                    }
                }
                return true;
            } finally {
                TraceEvent.f0("Tab.restoreIfNeeded");
            }
        }
    }
}
