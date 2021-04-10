package com.oculus.browser;

import J.N;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PanelApp implements AbstractC3226ja1, AbstractC0849Ny, Oy1, AbstractC5443wa1, AbstractC0530Iq0 {
    public static int F;
    public EnumC4488qw0 G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public long f9704J;
    public WebContents K;
    public OculusUiGestureTarget L;
    public OculusUiGestureTarget M;
    public Tab N;
    public AbstractC1404Xa1 O;
    public AbstractC5783ya1 P;
    public AbstractC0612Ka1 Q;
    public PanelSurface R;
    public HydraApplication S;
    public String T;
    public boolean U;
    public boolean V;
    public String W;
    public String X;
    public ShellEnvironment Y;
    public C4998tw0 Z;
    public PwaPackageHelper a0;
    public boolean b0;
    public PanelSurface c0;
    public WebContents d0;
    public AbstractC6022zx1 e0;
    public C5613xa1 f0;
    public UmaSessionStats g0;
    public C1755aw1 h0 = new C1755aw1();
    public final J00 i0 = new C3120iw0(this);
    public C4146ow0 j0 = new C4146ow0(this);

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0395  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x03c2  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x03c4  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0493  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02a4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0312  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0335  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0390  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PanelApp(com.oculus.browser.HydraApplication r30, android.view.Surface r31, java.util.Map r32, com.oculus.browser.ShellEnvironment r33, com.oculus.browser.PanelService r34) {
        /*
        // Method dump skipped, instructions count: 1228
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.browser.PanelApp.<init>(com.oculus.browser.HydraApplication, android.view.Surface, java.util.Map, com.oculus.browser.ShellEnvironment, com.oculus.browser.PanelService):void");
    }

    public static void B(Tab tab) {
        StringBuilder i = AbstractC2531fV.i("setFocusToTab ");
        i.append(tab != null ? Integer.valueOf(tab.getId()) : "null");
        i.toString();
        if (tab != null) {
            if (tab.b() != null) {
                tab.b().requestFocus();
            }
            if (tab.u() != null) {
                tab.u().onWindowFocusChanged(true);
            }
            if (tab instanceof TabImpl) {
                TabImpl tabImpl = (TabImpl) tab;
                if (tabImpl.isHidden()) {
                    tabImpl.y(3);
                }
            }
        }
    }

    public static boolean q(Uri uri) {
        return uri != null && "ovrweb".equals(uri.getScheme()) && "pwa".equals(uri.getHost());
    }

    public static boolean r(Uri uri) {
        return uri != null && "ovrweb".equals(uri.getScheme()) && "webtask".equals(uri.getHost());
    }

    public static Uri v(String str) {
        try {
            return Uri.parse(new JSONObject(str).getJSONObject("ovr_social_launch").getString("deeplink_message"));
        } catch (JSONException unused) {
            return Uri.parse(str);
        }
    }

    public final void A(int i) {
        if (this.f9704J != 0 && Gatekeeper.g().h(TU.MOUSE_CURSOR)) {
            N.MNMA6pA9(this.f9704J, i);
        }
    }

    public void C() {
        for (TabModel tabModel : ((AbstractC0246Ea1) P()).f7969a) {
            for (int i = 0; i < tabModel.getCount(); i++) {
                Tab tabAt = tabModel.getTabAt(i);
                if (tabAt.l() != null) {
                    N.MkJn$jrk(tabAt);
                }
            }
        }
    }

    public final void D() {
        Tab tab = this.N;
        if (tab != null && (tab instanceof TabImpl) && ((TabImpl) tab).isInitialized()) {
            this.N.getId();
            if (C3649m10.h(this.N) != null) {
                C3649m10.h(this.N).l(true);
            }
            this.N.I(this.O);
        }
    }

    @Override // defpackage.AbstractC3226ja1
    public AbstractC0124Ca1 P() {
        return this.f0.P();
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 S(boolean z) {
        C5613xa1 xa1 = this.f0;
        return xa1.z(z, xa1.Q);
    }

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
        AbstractC1220Ua0.d("PanelApp", "Failed to retrieve Oculus access token", new Object[0]);
    }

    @Override // defpackage.AbstractC0849Ny
    public float b() {
        return 0.0f;
    }

    public void broadcastStoreIntent(String str) {
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse("systemux://store"));
        intent.putExtra("uri", str);
        this.S.sendBroadcast(intent);
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.T = str;
        ThreadUtils.g(new RunnableC3804mw0(this));
    }

    public void closeAllTabs(boolean z) {
        D();
        this.V = true;
        if (z) {
            P().g(z);
        } else if (((AbstractC0246Ea1) P()).r()) {
            ((AbstractC0246Ea1) P()).l(true).g(false, z);
        } else {
            ((AbstractC0246Ea1) P()).l(false).g(false, z);
            newTab(o());
        }
        this.V = false;
        t();
    }

    public void closeTab(int i) {
        this.V = true;
        TabModel i2 = ((AbstractC0246Ea1) P()).i();
        if (i2.r(i) == null) {
            newTab(o());
        }
        AbstractC1160Ta1.a(i2, i, false);
        this.V = false;
        t();
    }

    public final OculusUiGestureTarget d(WebContents webContents) {
        if (webContents == null) {
            return null;
        }
        AbstractC1220Ua0.d("PanelApp", "GetGestureTarget", new Object[0]);
        View containerView = webContents.F().getContainerView();
        if (containerView != null) {
            AbstractC1220Ua0.d("PanelApp", "GetGestureTarget - OculusUiGestureTarget", new Object[0]);
            return new OculusUiGestureTarget(containerView, 1.0f, 0.025f, 5);
        }
        AbstractC1220Ua0.d("PanelApp", "GetGestureTarget - no view", new Object[0]);
        throw new Error("webContents does not have an associated View.");
    }

    public void destroy() {
        boolean l = this.S.l();
        AbstractC1220Ua0.d("PanelApp", "destroy, is in VR? " + l, new Object[0]);
        this.b0 = false;
        this.S.n(this);
        this.S.m(this);
        this.f0.S = null;
        C0591Jq0.e(this);
        this.Z = null;
        onBackground();
        D();
        PanelSurface panelSurface = this.c0;
        panelSurface.nativeDestroy(panelSurface.f9706a);
        this.c0 = null;
        AbstractC6022zx1 zx1 = this.e0;
        if (zx1 != null) {
            zx1.destroy();
            this.e0 = null;
        }
        WebContents webContents = this.d0;
        if (webContents != null) {
            webContents.destroy();
            this.d0 = null;
        }
        PanelSurface panelSurface2 = this.R;
        panelSurface2.nativeDestroy(panelSurface2.f9706a);
        this.R = null;
        if (this.P != null) {
            ((AbstractC0246Ea1) P()).i().w(this.P);
        }
        if (this.Q != null) {
            AbstractC0124Ca1 P2 = P();
            ((AbstractC0246Ea1) P2).f.c(this.Q);
        }
        K00 a2 = K00.a();
        a2.b.remove(this.i0);
        this.S = null;
        this.K = null;
        this.f9704J = 0;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.V = true;
        this.g0 = null;
    }

    public void enterWebVR() {
        AbstractC1220Ua0.d("PanelApp", "enterWebVR", new Object[0]);
        this.S.N = this;
    }

    public void exitWebVR() {
        AbstractC1220Ua0.d("PanelApp", "exitWebVR", new Object[0]);
        HydraApplication hydraApplication = this.S;
        Objects.requireNonNull(hydraApplication);
        Log.i("HydraApplication", "exitWebVR");
        VrShellDelegate vrShellDelegate = hydraApplication.M;
        if (vrShellDelegate != null) {
            vrShellDelegate.forceExitVR();
        }
        hydraApplication.N = null;
    }

    public void f(Tab tab) {
        String str = "cancelWebVRDeepLinkIfNeeded " + tab;
        HydraApplication hydraApplication = this.S;
        String h = tab.getUrl().h();
        VrShellDelegate vrShellDelegate = hydraApplication.M;
        if (vrShellDelegate != null && vrShellDelegate.n(h)) {
            x();
            HydraApplication hydraApplication2 = this.S;
            if (hydraApplication2.M != null) {
                StringBuilder i = AbstractC2531fV.i("cancelWebVRDeepLink, url = ");
                i.append(tab.getUrl());
                i.toString();
                hydraApplication2.M.c(tab);
            }
        }
    }

    public final void g() {
        AbstractC6022zx1 zx1 = this.e0;
        if (zx1 != null) {
            zx1.destroy();
            this.e0 = null;
        }
        WebContents webContents = this.d0;
        if (webContents != null) {
            webContents.destroy();
            this.d0 = null;
        }
        AbstractC1220Ua0.d("PanelApp", "Clearing navui!", new Object[0]);
        s(this.d0, true);
    }

    public int getLastCrashReportCount() {
        return this.S.P.e;
    }

    public String getLastCrashScanTime() {
        Date date = this.S.P.d;
        if (date != null) {
            return date.toString();
        }
        return null;
    }

    public String getWidthPreference() {
        EnumC4488qw0 qw0 = this.G;
        Objects.requireNonNull(qw0);
        return qw0 + "_WIDTH";
    }

    public void h() {
        long j = this.f9704J;
        if (j != 0) {
            N.McQ4N10R(j, false);
        }
    }

    public boolean handleBackPressed() {
        return this.f0.Q;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        if (r5 == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0073, code lost:
        if (r5 != false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0075, code lost:
        m(r11, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0078, code lost:
        t();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007b, code lost:
        if (r6 == false) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007d, code lost:
        J.N.McQ4N10R(r10.f9704J, true);
        r11 = r10.S;
        r3 = r10.N;
        r11.N = r10;
        r3.getUrl().h();
        r11 = r11.M;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0091, code lost:
        if (r11 == null) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0093, code lost:
        r5 = new com.oculus.browser.WebVRNavigationDescriptor(r3, true, r4);
        r11.U = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x009c, code lost:
        if (r11.R != false) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009e, code lost:
        r5.f = 1;
        r11.q();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a3, code lost:
        r3 = r5.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a7, code lost:
        if (r3 == null) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ad, code lost:
        if (r3.d() == false) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00af, code lost:
        r3.A(new defpackage.C5169uw1(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b8, code lost:
        defpackage.AbstractC1220Ua0.d("VrShellDelegate.Oculus", "requestVrNavigation: page %s is already loaded. error = %b", r3.getUrl().h(), java.lang.Boolean.valueOf(r3.p()));
        r1 = r11.U;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d8, code lost:
        if (r1 == null) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00de, code lost:
        if (r1.a() != r3) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e4, code lost:
        if (r3.p() != false) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ea, code lost:
        if (r3.x() != false) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00ec, code lost:
        r11.A();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f0, code lost:
        r11.c(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f3, code lost:
        r10.W = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f5, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleDeepLink(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 281
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.browser.PanelApp.handleDeepLink(java.lang.String):void");
    }

    @Override // defpackage.Oy1
    public WindowAndroid i() {
        return this.f0.O;
    }

    public boolean isCrashScanInProgress() {
        C1444Xq0 xq0 = this.S.P.b;
        return xq0 != null && xq0.isAlive();
    }

    public boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean j() {
        return false;
    }

    public void k(Uri uri) {
        boolean z = r(uri) || q(uri);
        StringBuilder i = AbstractC2531fV.i("determineSingleTabMode - ");
        i.append(z ? "single tab" : "multi tab");
        AbstractC1220Ua0.d("PanelApp", i.toString(), new Object[0]);
        if (this.f0.d(z)) {
            recoveryNavUI(false);
        }
    }

    public final void l() {
        if (this.d0 == null) {
            AbstractC1220Ua0.d("PanelApp", "Creating NavUI.", new Object[0]);
            WebContents a2 = AbstractC5342vx1.a(Profile.b(), false);
            this.d0 = a2;
            HydraApplication hydraApplication = this.S;
            int i = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
            C1459Xy xy = new C1459Xy(hydraApplication, null, a2);
            ViewAndroidDelegate viewAndroidDelegate = new ViewAndroidDelegate(xy);
            viewAndroidDelegate.d = new C2608fw0(this);
            this.d0.J("89.0.4389.105", viewAndroidDelegate, xy, this.f0.O, new C3466kx1());
            xy.setVisibility(0);
            this.e0 = new C3975nw0(this, this.d0);
            s(this.d0, false);
        }
    }

    public void loadUrl(String str, int i, boolean z) {
        Tab tab = this.N;
        if (tab != null) {
            LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
            loadUrlParams.j = z;
            loadUrlParams.f = GeolocationHeader.b(str, tab);
            loadUrlParams.c = i;
            this.N.c(loadUrlParams);
        }
    }

    public final void m(String str, int i) {
        boolean z = str == null || str.isEmpty();
        boolean r = ((AbstractC0246Ea1) P()).r();
        boolean z2 = this.f0.Q;
        if (this.f0.Q || !z || ((AbstractC0246Ea1) P()).l(r).getCount() == 0) {
            if (z) {
                str = o();
            }
            B(S(r).f(str, i));
        }
    }

    public void moveTab(int i, int i2) {
        PanelApp panelApp = (PanelApp) this.S.Q.get(Integer.valueOf(i2));
        if (panelApp == null) {
            AbstractC1220Ua0.a("PanelApp", "Failed to moveTab, source PanelApp did not exist", new Object[0]);
            return;
        }
        TabModel i3 = ((AbstractC0246Ea1) panelApp.P()).i();
        TabModel i4 = ((AbstractC0246Ea1) P()).i();
        if (i3 == null || i4 == null) {
            AbstractC1220Ua0.a("PanelApp", "Failed to moveTab, source or target TabModel did not exist", new Object[0]);
            return;
        }
        Tab o = ((AbstractC0246Ea1) panelApp.P()).o(i);
        if (o == null) {
            AbstractC1220Ua0.a("PanelApp", "Failed to moveTab, no tab found.", new Object[0]);
        }
        i3.u(o);
        i4.f(o, 0, 6, 0);
        panelApp.t();
        t();
    }

    public void moveTabByShift(int i, int i2) {
        TabModel i3 = ((AbstractC0246Ea1) P()).i();
        int i4 = i3.i(AbstractC1160Ta1.d(i3, i));
        if (i2 > 0) {
            i2++;
        }
        i3.m(i, i4 + i2);
    }

    public void n() {
        AbstractC1220Ua0.d("PanelApp", "finalizeHiding", new Object[0]);
        P().d();
        Tab c = AbstractC1160Ta1.c(((AbstractC0246Ea1) P()).i());
        if (c != null && (c instanceof TabImpl)) {
            ((TabImpl) c).N(1);
        }
        C();
        C5613xa1 xa1 = this.f0;
        if (xa1.Q) {
            C2878hX0 hx0 = xa1.N.l;
            Tab j = hx0.c.j();
            if (j != null) {
                hx0.f10075a.setString(hx0.a(), j.s());
                return;
            }
            return;
        }
        xa1.M.y();
    }

    public void newTab(String str) {
        if (!this.f0.Q) {
            boolean r = ((AbstractC0246Ea1) P()).r();
            if (str.equals("")) {
                str = o();
            }
            S(r).f(str, 2);
        }
    }

    public final String o() {
        if (((AbstractC0246Ea1) P()).r()) {
            return "chrome://oculus-ntp-private";
        }
        if (this.Y.isEnterpriseDevice() || Gatekeeper.g().h(TU.TEST_ENTERPRISE_BROWSER)) {
            return "chrome://oculus-ntp-enterprise";
        }
        Gatekeeper g = Gatekeeper.g();
        StringBuilder sb = new StringBuilder("");
        if (!g.O.a()) {
            sb.append("prefsloaded=false&");
        }
        Set keySet = g.f9701J.keySet();
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        g.H = strArr;
        Arrays.sort(strArr);
        String[] strArr2 = g.H;
        for (String str : strArr2) {
            sb.append(str);
            sb.append("=");
            sb.append(((Boolean) g.f9701J.get(str)).booleanValue() ? "1" : "0");
            sb.append("&");
        }
        String sb2 = sb.toString();
        return (sb2 == null || sb2.length() == 0) ? "chrome://oculus-ntp" : AbstractC2531fV.f("chrome://oculus-ntp?", sb2);
    }

    public void onBackground() {
        AbstractC1220Ua0.d("PanelApp", "onBackground, inWebVR = %b", Boolean.valueOf(this.S.l()));
        UmaUtils.c = SystemClock.uptimeMillis();
        if (this.S.N != this) {
            WebContents webContents = this.d0;
            if (webContents != null) {
                webContents.z();
            }
            this.b0 = false;
            this.S.n(this);
        }
        UmaSessionStats umaSessionStats = this.g0;
        if (umaSessionStats != null) {
            umaSessionStats.c();
        }
        PanelSurface panelSurface = this.c0;
        Objects.requireNonNull(panelSurface);
        AbstractC1220Ua0.d("PanelSurface", "onBackground", new Object[0]);
        panelSurface.nativeSetSurface(panelSurface.f9706a, null);
        PanelSurface panelSurface2 = this.R;
        Objects.requireNonNull(panelSurface2);
        AbstractC1220Ua0.d("PanelSurface", "onBackground", new Object[0]);
        panelSurface2.nativeSetSurface(panelSurface2.f9706a, null);
        h();
    }

    public void onForeground() {
        WebVRNavigationDescriptor webVRNavigationDescriptor;
        AbstractC1220Ua0.d("PanelApp", "onForeground", new Object[0]);
        UmaUtils.c();
        l();
        WebContents webContents = this.d0;
        if (webContents != null) {
            webContents.O();
            this.d0.e0(null);
            this.d0.e0(this.f0.O);
        }
        if (Gatekeeper.g().h(TU.UMA)) {
            if (this.g0 == null) {
                this.g0 = new UmaSessionStats(this.S);
            }
            UmaSessionStats.e();
            this.g0.d(P());
        }
        this.b0 = true;
        HydraApplication hydraApplication = this.S;
        Objects.requireNonNull(hydraApplication);
        Log.i("HydraApplication", "onAppShown");
        hydraApplication.S.remove(this);
        VrShellDelegate vrShellDelegate = hydraApplication.M;
        if (vrShellDelegate != null) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "exitVRIfNecessary, inVR? %b", Boolean.valueOf(vrShellDelegate.L));
            if (vrShellDelegate.L || (webVRNavigationDescriptor = vrShellDelegate.U) == null || !webVRNavigationDescriptor.isDeepLinking()) {
                vrShellDelegate.forceExitVR();
            }
        }
        PanelApp panelApp = hydraApplication.N;
        if (panelApp != null) {
            panelApp.h();
        }
        hydraApplication.N = null;
        Tab c = AbstractC1160Ta1.c(((AbstractC0246Ea1) P()).i());
        if (c != null) {
            if (c instanceof TabImpl) {
                TabImpl tabImpl = (TabImpl) c;
                if (tabImpl.isHidden()) {
                    tabImpl.y(3);
                }
            }
            c.z();
        }
        Object[] k = hydraApplication.k();
        if (k != null) {
            for (Object obj : k) {
                Objects.requireNonNull((V9) obj);
                ApplicationStatus.b(1);
            }
        }
        hydraApplication.q();
        D();
        this.N = null;
        this.K = null;
        t();
        this.c0.a();
        this.R.a();
    }

    public void onHandsModeChanged(boolean z) {
        VrShellDelegate vrShellDelegate = this.S.M;
        if (vrShellDelegate != null) {
            long j = vrShellDelegate.G;
            if (j != 0) {
                vrShellDelegate.nativeOnHandsModeChanged(j, z);
            }
        }
    }

    public void onMouseModeChanged(boolean z) {
        VrShellDelegate vrShellDelegate = this.S.M;
        if (vrShellDelegate != null) {
            long j = vrShellDelegate.G;
            if (j != 0) {
                vrShellDelegate.nativeOnMouseModeChanged(j, z);
            }
        }
    }

    public void onUpdatePanelMonitorId(int i) {
        this.G = EnumC4488qw0.a(i);
        this.S.o();
    }

    public void openMostRecentlyClosedTab() {
        if (!this.f0.Q) {
            ((AbstractC0246Ea1) P()).i().p();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public final C4317pw0 p(Uri uri) {
        char c;
        Uri parse;
        C4317pw0 pw0 = new C4317pw0(this, null);
        String host = uri.getHost();
        host.hashCode();
        boolean z = true;
        boolean z2 = false;
        switch (host.hashCode()) {
            case -1118260433:
                if (host.equals("onWebVRExit")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -906336856:
                if (host.equals("search")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3772:
                if (host.equals("vr")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 111418:
                if (host.equals("pwa")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3127582:
                if (host.equals("exit")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 103772132:
                if (host.equals("media")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1224357593:
                if (host.equals("webtask")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - onWebVRExit", new Object[0]);
                pw0.f11102a = true;
                x();
                return pw0;
            case 1:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - search ", new Object[0]);
                String decode = Uri.decode(uri.getQueryParameter("query"));
                if (decode == null && decode.isEmpty()) {
                    this.W = null;
                    break;
                } else {
                    this.W = N.MmoC5pfr(this.f9704J, decode);
                    break;
                }
                break;
            case 2:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - vr", new Object[0]);
                this.W = Uri.decode(uri.getQueryParameter("uri"));
                String decode2 = Uri.decode(uri.getQueryParameter("returntocaller"));
                if (decode2 != null) {
                    pw0.c = decode2.isEmpty() || Integer.parseInt(decode2) != 0;
                }
                String str = this.W;
                if (str != null && !str.isEmpty()) {
                    pw0.b = true;
                }
                if (this.S.l()) {
                    exitWebVR();
                    break;
                }
                break;
            case 3:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - pwa", new Object[0]);
                String decode3 = Uri.decode(uri.getQueryParameter("packageName"));
                SI0 a2 = this.a0.a(decode3, this.Y);
                if (a2 == null) {
                    AbstractC1220Ua0.a("PanelApp", AbstractC2531fV.g("Could not find PWA metadata for '", decode3, "'"), new Object[0]);
                    break;
                } else {
                    String uri2 = a2.b.toString();
                    N.MEJ5AdfM(this.f9704J, decode3, a2.e, uri2, a2.d.toString());
                    C2878hX0 hx0 = ((C3390kX0) this.f0.P()).l;
                    String string = hx0.f10075a.getString(hx0.a(), null);
                    if (string != null) {
                        uri2 = string;
                    }
                    this.W = uri2;
                    break;
                }
            case 4:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - exit", new Object[0]);
                pw0.f11102a = true;
                handleBackPressed();
                if (this.f9704J == 0) {
                    z = false;
                }
                B40.a(z, "mNativePanelApp is ZERO!");
                h();
                N.MDkWG7XF(this.f9704J);
                return pw0;
            case 5:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - media", new Object[0]);
                this.W = Uri.decode(uri.getQueryParameter("uri"));
                String decode4 = Uri.decode(uri.getQueryParameter("projection"));
                if (decode4 != null && !decode4.isEmpty()) {
                    N.M1675DJl(this.f9704J, new String[]{"projectionMode", "uri"}, new String[]{decode4, this.W});
                    break;
                }
            case 6:
                AbstractC1220Ua0.d("PanelApp", "handleDeepLink - webtask", new Object[0]);
                this.W = Uri.decode(uri.getQueryParameter("uri"));
                String decode5 = Uri.decode(uri.getQueryParameter("return"));
                this.X = decode5;
                if (decode5 != null) {
                    this.X = URLEncoder.encode(decode5, "UTF-8");
                    break;
                }
                break;
        }
        String str2 = this.W;
        if (str2 != null && !str2.isEmpty() && (parse = Uri.parse(str2)) != null && ("http".equals(parse.getScheme()) || "https".equals(parse.getScheme()))) {
            z2 = true;
        }
        if (z2) {
            setIncognitoModeEnabled(((AbstractC0246Ea1) P()).r(), this.W);
            pw0.f11102a = true;
        }
        return pw0;
    }

    public void recoveryNavUI(boolean z) {
        AbstractC1220Ua0.d("PanelApp", "recoveryNavUI", new Object[0]);
        g();
        if (this.b0) {
            AbstractC1220Ua0.d("PanelApp", "Recreating navui!", new Object[0]);
            l();
        } else {
            AbstractC1220Ua0.d("PanelApp", "Clearing navui!", new Object[0]);
        }
        s(this.d0, z);
    }

    public void s(WebContents webContents, boolean z) {
        String str;
        StringBuilder i = AbstractC2531fV.i("calling GetGestureTarget - navui ");
        if (webContents == null) {
            str = "null";
        } else {
            str = String.valueOf(webContents.hashCode());
        }
        i.append(str);
        AbstractC1220Ua0.d("PanelApp", i.toString(), new Object[0]);
        this.M = d(webContents);
        B40.a(this.f9704J != 0, "mNativePanelApp is ZERO!");
        N.MQlh35cA(this.f9704J, webContents, this.M, this.X, z);
        String str2 = this.T;
        if (str2 != null) {
            N.MhxPZ65F(this.f9704J, str2);
        }
        if (webContents != null) {
            AbstractC1220Ua0.d("PanelApp", "onNavUIChanged", new Object[0]);
            D();
            this.N = null;
            this.K = null;
            t();
            return;
        }
        AbstractC1220Ua0.d("PanelApp", "onNavUIChanged - no navui", new Object[0]);
    }

    public void scrollToBottom(WebContents webContents) {
        AbstractC1220Ua0.d("PanelApp", "scrollToBottom", new Object[0]);
        View containerView = webContents.F().getContainerView();
        if (containerView != null) {
            containerView.scrollTo(0, Integer.MAX_VALUE);
        }
    }

    public void scrollToTop(WebContents webContents) {
        AbstractC1220Ua0.d("PanelApp", "scrollToTop", new Object[0]);
        View containerView = webContents.F().getContainerView();
        if (containerView != null) {
            containerView.scrollTo(0, Integer.MIN_VALUE);
        }
    }

    public void selectNextTab() {
        TabModel i = ((AbstractC0246Ea1) P()).i();
        int index = i.index() + 1;
        if (index >= i.getCount()) {
            index = 0;
        }
        i.x(index, 3);
    }

    public void selectPreviousTab() {
        TabModel i = ((AbstractC0246Ea1) P()).i();
        int index = i.index() - 1;
        if (index < 0) {
            index = i.getCount() - 1;
        }
        i.x(index, 3);
    }

    public void selectTab(int i) {
        TabModel i2 = ((AbstractC0246Ea1) P()).i();
        i2.x(i2.i(AbstractC1160Ta1.d(i2, i)), 3);
    }

    public void selectTabByIndex(int i) {
        TabModel i2 = ((AbstractC0246Ea1) P()).i();
        if (i < i2.getCount()) {
            i2.x(i, 3);
        }
    }

    public void setIncognitoModeEnabled(boolean z, String str) {
        boolean z2 = this.V;
        int i = 1;
        this.V = true;
        Tab j = ((AbstractC0246Ea1) P()).j();
        if (!(j == null || j.u() == null)) {
            j.u().onWindowFocusChanged(false);
        }
        P().e(z);
        if (this.W == null) {
            i = 2;
        }
        m(str, i);
        this.V = z2;
        if (!z2) {
            t();
        }
    }

    public void setTabAudioMuted(int i, boolean z) {
        WebContents l = AbstractC1160Ta1.d(((AbstractC0246Ea1) P()).i(), i).l();
        if (l != null) {
            l.V(z);
            N.Mu8upwX4(this.f9704J, l);
        }
    }

    public void setUseDesktopUserAgent(boolean z, boolean z2) {
        WebContents webContents;
        AbstractC1220Ua0.d("PanelApp", "setUseDesktopUserAgent - " + z, new Object[0]);
        Tab tab = this.N;
        if (tab != null && this.W == null && (webContents = ((TabImpl) tab).L) != null) {
            webContents.f().d(z, z2);
        }
    }

    public void setWebContentsWindowAndSize(WebContents webContents, boolean z, int i, int i2) {
        C0054Aw0 aw0;
        AbstractC1220Ua0.d("PanelApp", "setWebContentsWindowAndSize useContentWindow:" + z + ",  windowWidth: " + i + ", windowHeight: " + i2, new Object[0]);
        if (z) {
            aw0 = this.f0.P;
        } else {
            aw0 = this.f0.O;
        }
        ((Av1) aw0.I).f(new Point(i, i2), Float.valueOf(1.0f), null, null, null, null, null, null, null, null, null);
        webContents.e0(aw0);
        AbstractC1220Ua0.d("PanelApp", "setWebContentsWindowAndSize onSizeChanged width:" + i + " height:" + i2, new Object[0]);
        webContents.p0(i, i2);
        AbstractC1220Ua0.d("PanelApp", "setWebContentsWindowAndSize nativeOnPhysicalBackingSizeChanged width:" + i + " height:" + i2, new Object[0]);
        N.MtJ0aUvQ(this.f9704J, webContents, i, i2);
        webContents.O();
    }

    public final void t() {
        int i;
        if (!this.V) {
            Tab j = ((AbstractC0246Ea1) P()).j();
            Tab tab = this.N;
            boolean z = false;
            boolean z2 = true;
            if (j != tab) {
                if (!(tab == null || tab.u() == null)) {
                    tab.u().onWindowFocusChanged(false);
                }
                D();
                this.N = j;
                if (j != null) {
                    WebContents l = j.l();
                    Tab tab2 = this.N;
                    if ((tab2 instanceof TabImpl) && ((TabImpl) tab2).isInitialized() && C3649m10.h(this.N) != null) {
                        C3649m10.h(this.N).l(false);
                    }
                    if (l != null) {
                        l.F().d = new C2779gw0(this);
                    }
                    this.N.A(this.O);
                    C4146ow0 ow0 = this.j0;
                    Tab tab3 = this.N;
                    Objects.requireNonNull(ow0);
                    C2118d30.c(tab3).e = new C3198jN(new C4658rw0(ow0.f11036a, tab3));
                    if (l != null) {
                        l.e0(null);
                    }
                    Tab tab4 = this.N;
                    if (tab4 instanceof TabImpl) {
                        ((TabImpl) tab4).p0(this.f0.O);
                    }
                    this.f0.O.y0(true);
                    B(this.N);
                }
            }
            Tab tab5 = this.N;
            if (tab5 != null) {
                WebContents l2 = tab5.l();
                if (l2 != this.K) {
                    this.K = l2;
                    StringBuilder i2 = AbstractC2531fV.i("calling GetGestureTarget - content: ");
                    i2.append(String.valueOf(this.K.hashCode()));
                    AbstractC1220Ua0.d("PanelApp", i2.toString(), new Object[0]);
                    this.L = d(l2);
                    B40.a(this.f9704J != 0, "mNativePanelApp is ZERO!");
                    N.MEqHKbf$(this.f9704J, this.K, this.L, ((AbstractC0246Ea1) P()).r());
                }
                TabModel i3 = ((AbstractC0246Ea1) P()).i();
                boolean r = ((AbstractC0246Ea1) P()).r();
                int count = i3.getCount();
                Tab[] tabArr = new Tab[count];
                for (int i4 = 0; i4 < count; i4++) {
                    tabArr[i4] = i3.getTabAt(i4);
                }
                if (this.f9704J != 0) {
                    z = true;
                }
                B40.a(z, "mNativePanelApp is ZERO!");
                long j2 = this.f9704J;
                Tab c = AbstractC1160Ta1.c(i3);
                if (c == null) {
                    i = -1;
                } else {
                    i = c.getId();
                }
                N.MiwLSbe$(j2, tabArr, i, r);
                return;
            }
            if (this.f9704J == 0) {
                z2 = false;
            }
            B40.a(z2, "mNativePanelApp is ZERO!");
            N.MEqHKbf$(this.f9704J, null, null, ((AbstractC0246Ea1) P()).r());
            AbstractC1220Ua0.d("PanelApp", "resetting mWebContentsGestureTarget - onTabListChanged", new Object[0]);
            this.L = null;
        }
    }

    public void u(AbstractC0124Ca1 ca1, AbstractC0124Ca1 ca12) {
        if (ca12 != null) {
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca12;
            if (!(ea1.i() == null || this.P == null)) {
                ea1.i().w(this.P);
            }
            AbstractC0612Ka1 ka1 = this.Q;
            if (ka1 != null) {
                ea1.f.c(ka1);
            }
        }
        if (ca1 != null) {
            AbstractC0246Ea1 ea12 = (AbstractC0246Ea1) ca1;
            ea12.c(this.Q);
            if (ea12.i() != null) {
                ea12.i().n(this.P);
            }
        }
        t();
    }

    public void updateRefreshRate(float f) {
        AbstractC1220Ua0.d("PanelApp", "updateRefreshRate: rate = " + f, new Object[0]);
        C0054Aw0 aw0 = this.f0.O;
        long j = aw0.H;
        if (j != 0) {
            N.MWNjxKcW(j, aw0, f);
        }
        C0054Aw0 aw02 = this.f0.P;
        long j2 = aw02.H;
        if (j2 != 0) {
            N.MWNjxKcW(j2, aw02, f);
        }
    }

    public void uploadCrashReports() {
        this.S.P.b();
    }

    public final void w() {
        StringBuilder i = AbstractC2531fV.i("postDeferredStartupIfNeeded: posted = ");
        i.append(this.U);
        i.append("; is UI thread = ");
        i.append(ThreadUtils.i());
        AbstractC1220Ua0.d("PanelApp", i.toString(), new Object[0]);
        if (!this.U) {
            AbstractC1220Ua0.d("PanelApp", "postDeferredStartupIfNeeded: posting deferred startup tasks", new Object[0]);
            this.U = true;
            OG0.a().c();
            C2150dE b = C2150dE.b();
            Objects.requireNonNull(b);
            Object obj = ThreadUtils.f10596a;
            Looper.myQueue().addIdleHandler(new C1808bE(b));
            if (this.Y.a()) {
                C2950hw0 hw0 = new C2950hw0(this);
                if (AutomationServer.f9697a != null) {
                    Log.e("BrowserAutomationServer", "attempt to start automation server after shutdown");
                } else {
                    AutomationServer.f9697a = new AutomationServer(hw0);
                }
            }
        }
    }

    public void x() {
        B40.a(this.f9704J != 0, "mNativePanelApp is ZERO!");
        h();
        if (this.K != null) {
            StringBuilder i = AbstractC2531fV.i("calling nativeSetWebContents - restorePanelState ");
            i.append(this.L == null ? "null" : "not null");
            AbstractC1220Ua0.d("PanelApp", i.toString(), new Object[0]);
            N.MEqHKbf$(this.f9704J, this.K, this.L, ((AbstractC0246Ea1) P()).r());
            PanelSurface panelSurface = this.R;
            if (panelSurface != null) {
                panelSurface.a();
            }
            PanelSurface panelSurface2 = this.c0;
            if (panelSurface2 != null) {
                panelSurface2.a();
            }
        }
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean y() {
        return false;
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 z(boolean z, boolean z2) {
        return this.f0.z(z, z2);
    }
}
