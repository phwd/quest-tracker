package org.chromium.chrome.browser.contextualsearch;

import J.N;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.oculus.browser.R;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.prefs.PrefService;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextualSearchManager implements AbstractC0669Kz, AbstractC1522Yz, AbstractC4870tA, W {
    public final C1322Vq0 F = new C1322Vq0();
    public final ChromeActivity G;
    public final AbstractC1400Wz H;
    public final ViewTreeObserver.OnGlobalFocusChangeListener I;

    /* renamed from: J  reason: collision with root package name */
    public final TT f10642J;
    public final AbstractC0486Hz K;
    public final C5720yA L;
    public final C1339Vz M;
    public final C0059Az N;
    public final GP0 O;
    public C4700sA P;
    public AbstractC1522Yz Q;
    public C2138dA R;
    public C0547Iz S;
    public C1796bA T;
    public long U;
    public ViewGroup V;
    public C2171dL0 W;
    public AbstractC0855Oa1 X;
    public AbstractC1099Sa1 Y;
    public boolean Z;
    public long a0;
    public boolean b0;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;
    public boolean g0;
    public ContextualSearchContext h0;
    public boolean i0;
    public boolean j0;
    public boolean k0;
    public boolean l0;
    public boolean m0;
    public C4188pA n0;
    public C4188pA o0;
    public boolean p0;
    public boolean q0;
    public AJ0 r0;
    public int s0;
    public D70 t0;

    public ContextualSearchManager(ChromeActivity chromeActivity, AbstractC1400Wz wz, GP0 gp0, Q31 q31) {
        this.G = chromeActivity;
        this.H = wz;
        this.O = gp0;
        this.I = new ViewTreeObserver$OnGlobalFocusChangeListenerC0790Mz(this, chromeActivity.findViewById(R.id.control_container));
        C0851Nz nz = new C0851Nz(this);
        this.f10642J = nz;
        ((ST) chromeActivity.S0()).K.b(nz);
        this.P = new C4700sA(chromeActivity, this);
        this.Q = this;
        this.R = new C2138dA(this.P, this.Q);
        this.L = new C5720yA();
        this.S = new C0547Iz(this.R, new C1278Uz(this));
        this.K = new C4017oA();
        this.M = new C1339Vz(this, null);
        this.N = new C0059Az();
    }

    public static void a(ContextualSearchManager contextualSearchManager, int i) {
        C1328Vt0 vt0;
        AbstractC0292Et0 et0;
        C3649m10 e;
        contextualSearchManager.G.P().d();
        boolean z = true;
        if (!contextualSearchManager.T.O() && (e = contextualSearchManager.e()) != null) {
            B10 b10 = e.R;
            if ((b10 != null ? b10.getVisibility() : 8) == 0) {
                contextualSearchManager.c0 = true;
                e.l(true);
            }
        }
        int i2 = contextualSearchManager.T.L;
        if (!(contextualSearchManager.b0 || contextualSearchManager.a0 == 0 || i2 == 0 || i2 == 1)) {
            contextualSearchManager.r();
        }
        contextualSearchManager.T.W();
        contextualSearchManager.g0 = false;
        String str = contextualSearchManager.P.f;
        boolean k = contextualSearchManager.R.k();
        if (k) {
            contextualSearchManager.i0 = false;
        }
        if (!k || !contextualSearchManager.R.n()) {
            if (!TextUtils.isEmpty(str)) {
                boolean m = contextualSearchManager.R.m();
                C4188pA pAVar = new C4188pA(str, null, null, m, null, null);
                contextualSearchManager.n0 = pAVar;
                pAVar.a("", contextualSearchManager.L.a());
                contextualSearchManager.Z = false;
                contextualSearchManager.T.v0(str);
                if (m) {
                    contextualSearchManager.o();
                }
            } else {
                contextualSearchManager.i(0);
                return;
            }
        }
        contextualSearchManager.b0 = false;
        Objects.requireNonNull(contextualSearchManager.R);
        if (k()) {
            contextualSearchManager.j0 = true;
            boolean i3 = contextualSearchManager.R.i();
            contextualSearchManager.k0 = i3;
            contextualSearchManager.l0 = false;
            contextualSearchManager.T.u0(true, i3);
            contextualSearchManager.T.F0.f9588a = true;
        }
        C1796bA bAVar = contextualSearchManager.T;
        if (bAVar.O() && bAVar.L == 2) {
            bAVar.j0(i);
        }
        if (!bAVar.C0 && (vt0 = bAVar.z0) != null && bAVar != (et0 = vt0.c) && et0 == null) {
            vt0.c = bAVar;
            vt0.a(bAVar, i);
        }
        boolean z2 = contextualSearchManager.P.g == 1;
        contextualSearchManager.e0 = z2;
        C0059Az az = contextualSearchManager.N;
        Profile a2 = Profile.a(contextualSearchManager.G.K0().l());
        Objects.requireNonNull(az);
        Tm1 a3 = Um1.a(a2);
        a3.notifyEvent(z2 ? "contextual_search_triggered_by_tap" : "contextual_search_triggered_by_longpress");
        if (z2) {
            if (a3.getTriggerState("IPH_ContextualSearchPromoteTap") != 0) {
                z = false;
            }
            Pattern pattern = AA.f7657a;
            AbstractC3100ip1.f10165a.a("Search.ContextualSearchTapIPHShown", z);
        }
    }

    public static PrefService f() {
        return Wr1.a(Profile.b());
    }

    public static boolean j() {
        return N.Ma80fvz5(f().f10883a, "search.contextual_search_enabled").equals("false");
    }

    public static boolean k() {
        return N.Ma80fvz5(f().f10883a, "search.contextual_search_enabled").isEmpty();
    }

    public final void b() {
        if (this.S.b(10)) {
            this.S.c(10);
            return;
        }
        C4017oA oAVar = (C4017oA) this.K;
        oAVar.b = false;
        oAVar.d = false;
        oAVar.f = null;
        oAVar.c = null;
        oAVar.e = 0;
    }

    public URL c() {
        WebContents d = d();
        if (d == null) {
            return null;
        }
        try {
            return new URL(d.e());
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public void clearNativeManager() {
        this.U = 0;
    }

    public final WebContents d() {
        return this.P.b();
    }

    public final C3649m10 e() {
        Tab K0 = this.G.K0();
        if (K0 == null) {
            return null;
        }
        W w = C3649m10.F;
        return (C3649m10) K0.M().c(C3649m10.class);
    }

    public final WebContents g() {
        C1796bA bAVar = this.T;
        if (bAVar == null) {
            return null;
        }
        return bAVar.Z();
    }

    public void h() {
        if (!n() && m() && !this.m0 && this.T.i0()) {
            i(6);
        }
    }

    public void i(int i) {
        this.S.d(Integer.valueOf(i));
    }

    @Override // defpackage.W
    public void l(boolean z) {
        this.p0 = z;
        if (z) {
            i(0);
        }
    }

    public boolean m() {
        C1796bA bAVar = this.T;
        return bAVar != null && bAVar.O();
    }

    public boolean n() {
        return this.q0 || this.p0;
    }

    public final void o() {
        this.a0 = System.currentTimeMillis();
        C4188pA pAVar = this.n0;
        this.o0 = pAVar;
        String b = pAVar.b();
        N.MA4yNvGA(this.U, this, b);
        this.T.Y().c(b, true);
        this.Z = true;
        if (this.T.e0() && g() != null) {
            g().O();
        }
    }

    public final void onChangeOverlayPosition(int i) {
        if (!this.T.O() || i < 0 || i > 3) {
            AbstractC1220Ua0.f("ContextualSearch", AbstractC2531fV.w("Unexpected request to set Overlay position to ", i), new Object[0]);
        } else if (i == 0) {
            this.T.z(0, true);
        } else if (i == 1) {
            this.T.j0(0);
        } else if (i == 2) {
            this.T.n0(0);
        } else if (i == 3) {
            C1796bA bAVar = this.T;
            bAVar.H0 = false;
            bAVar.w(4, 0, 218);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:144:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0342  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x034b  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0362  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x039e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x03ae  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x03b8  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x03c4  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0476  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0478  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x047e  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x04c6  */
    /* JADX WARNING: Removed duplicated region for block: B:231:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onSearchTermResolutionResponse(boolean r29, int r30, java.lang.String r31, java.lang.String r32, java.lang.String r33, java.lang.String r34, boolean r35, int r36, int r37, java.lang.String r38, java.lang.String r39, java.lang.String r40, java.lang.String r41, int r42, long r43, java.lang.String r45, java.lang.String r46, int r47) {
        /*
        // Method dump skipped, instructions count: 1232
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.contextualsearch.ContextualSearchManager.onSearchTermResolutionResponse(boolean, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, long, java.lang.String, java.lang.String, int):void");
    }

    public final void onSetCaption(String str, boolean z) {
        C1796bA bAVar;
        if (!TextUtils.isEmpty(str) && (bAVar = this.T) != null) {
            bAVar.s0().d.p(str);
            AJ0 aj0 = this.r0;
            if (aj0 != null) {
                aj0.f7666a = true;
                aj0.b = z;
            }
            C2138dA dAVar = this.R;
            boolean z2 = this.e0;
            Objects.requireNonNull(dAVar);
            if (z2 && z) {
                dAVar.b.c("contextual_search_tap_quick_answer_count");
                dAVar.b.c("contextual_search_all_time_tap_quick_answer_count");
            }
        }
    }

    public void onTextSurroundingSelectionAvailable(String str, String str2, int i, int i2) {
        String str3;
        String str4;
        int a2;
        if (this.S.b(9)) {
            boolean z = false;
            if (str2.length() == 0) {
                this.S.d(0);
                return;
            }
            ContextualSearchContext contextualSearchContext = this.h0;
            contextualSearchContext.i = str;
            contextualSearchContext.c = str2;
            contextualSearchContext.d = i;
            contextualSearchContext.e = i2;
            if (i == i2 && i <= str2.length()) {
                if (contextualSearchContext.g >= 0) {
                    z = true;
                }
                if (!z) {
                    contextualSearchContext.g = i;
                    contextualSearchContext.j = null;
                    contextualSearchContext.l = -1;
                    int b = contextualSearchContext.b(i);
                    int a3 = contextualSearchContext.a(contextualSearchContext.g);
                    if (!(b == -1 || a3 == -1)) {
                        contextualSearchContext.k = b;
                        contextualSearchContext.j = contextualSearchContext.c.substring(b, a3);
                        contextualSearchContext.l = contextualSearchContext.g - b;
                        int i3 = contextualSearchContext.k;
                        while (i3 >= 1) {
                            int i4 = i3 - 1;
                            if (!contextualSearchContext.d(i4)) {
                                break;
                            }
                            i3 = i4;
                        }
                        if (i3 != 0) {
                            int b2 = contextualSearchContext.b(i3);
                            contextualSearchContext.n = b2;
                            if (b2 != -1) {
                                contextualSearchContext.m = contextualSearchContext.c.substring(b2, i3);
                            }
                        }
                        int length = contextualSearchContext.j.length() + contextualSearchContext.k;
                        do {
                            length++;
                            if (length >= contextualSearchContext.c.length()) {
                                break;
                            }
                        } while (contextualSearchContext.d(length));
                        if (!(length == contextualSearchContext.c.length() || (a2 = contextualSearchContext.a(length)) == -1)) {
                            contextualSearchContext.p = length;
                            contextualSearchContext.o = contextualSearchContext.c.substring(length, a2);
                        }
                    }
                }
            }
            if (i2 > i) {
                contextualSearchContext.g();
                contextualSearchContext.f();
            }
            String c = contextualSearchContext.c();
            String str5 = contextualSearchContext.q;
            String str6 = contextualSearchContext.r;
            String str7 = str5.equals(str6) ? "" : str6;
            if (str5.equals(c)) {
                str4 = "";
                str3 = str4;
            } else {
                str4 = c;
                str3 = str5;
            }
            N.Mv7i3uKU(contextualSearchContext.f10641a, contextualSearchContext, str4, str3, str7);
            if (this.R.j(this.h0.c())) {
                Pattern pattern = AA.f7657a;
                AbstractC3100ip1.f10165a.a("Search.RelatedSearches.QualifiedUsers", true);
            }
            this.S.c(9);
        }
    }

    public void p(String str) {
        if (!this.d0 && this.T != null && !"about:blank".equals(str) && !str.startsWith("intent:")) {
            C1796bA bAVar = this.T;
            boolean z = false;
            if (bAVar.J0) {
                OverlayPanelContent overlayPanelContent = bAVar.y0;
                if (!(overlayPanelContent != null && overlayPanelContent.j)) {
                    z = true;
                }
            }
            if (z) {
                this.d0 = true;
                bAVar.H0 = true;
                bAVar.w(4, 10, 218);
            }
        }
    }

    public void q() {
        A61 S2;
        this.m0 = true;
        if (!(this.n0 == null || g() == null)) {
            WebContents g = g();
            NavigationEntry s = g.f().s();
            String h = s != null ? s.b.h() : g.e();
            if (h.equals(this.n0.b())) {
                h = this.n0.c();
            }
            if (h != null) {
                ChromeActivity chromeActivity = (ChromeActivity) this.H;
                Tab K0 = chromeActivity.K0();
                if (!(K0 == null || (S2 = chromeActivity.S(K0.a())) == null)) {
                    S2.b(new LoadUrlParams(h, 0), 0, chromeActivity.K0());
                }
                this.T.z(11, false);
            }
        }
        this.m0 = false;
    }

    public final void r() {
        C4188pA pAVar = this.o0;
        if (pAVar != null) {
            C1796bA bAVar = this.T;
            String b = pAVar.b();
            long j = this.a0;
            OverlayPanelContent overlayPanelContent = bAVar.y0;
            if (overlayPanelContent != null) {
                N.Me5Orzs5(overlayPanelContent.c, overlayPanelContent, b, j);
            }
        }
    }

    public void setNativeManager(long j) {
        this.U = j;
    }
}
