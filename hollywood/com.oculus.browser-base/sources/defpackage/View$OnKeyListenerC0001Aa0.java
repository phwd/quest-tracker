package defpackage;

import J.N;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.common.ResourceRequestBody;

/* renamed from: Aa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnKeyListenerC0001Aa0 extends AbstractC4592ra0 implements AbstractC1834bO, Nv1, AbstractC0061Ba, Hq1, View.OnKeyListener, ComponentCallbacks, AbstractC0383Gf1 {
    public final AbstractView$OnClickListenerC5272va0 F;
    public Sv1 G;
    public final AbstractC4422qa0 H;
    public C0122Ca I;

    /* renamed from: J  reason: collision with root package name */
    public final C1570Zs0 f7678J;
    public R11 K;
    public C0859Oc L;
    public C3450ks0 M;
    public Oq1 N;
    public AbstractC0956Pq0 O;
    public WF0 P;
    public C1128Sl Q = new C1128Sl();
    public final AbstractC1511Yt0 R;
    public final LocaleManager S;
    public final List T = new ArrayList();
    public final AbstractC1509Ys0 U;
    public TemplateUrl V;
    public boolean W;

    public View$OnKeyListenerC0001Aa0(AbstractView$OnClickListenerC5272va0 va0, AbstractC4422qa0 qa0, C1570Zs0 zs0, AbstractC0956Pq0 pq0, WF0 wf0, AbstractC1511Yt0 yt0, LocaleManager localeManager, AbstractC1509Ys0 ys0) {
        this.F = va0;
        this.H = qa0;
        qa0.k(this);
        this.f7678J = zs0;
        this.R = yt0;
        this.S = localeManager;
        this.G = new Sv1(this, zs0);
        this.O = pq0;
        ((C1078Rq0) pq0).l(this.Q.b(new C5442wa0(this)));
        this.P = wf0;
        this.U = ys0;
    }

    public Sv1 C() {
        if (this.F == null) {
            return null;
        }
        return this.G;
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        TemplateUrlService templateUrlService = (TemplateUrlService) this.U.get();
        TemplateUrl a2 = templateUrlService.a();
        TemplateUrl templateUrl = this.V;
        if (templateUrl != null || a2 != null) {
            if (templateUrl == null || !templateUrl.equals(a2)) {
                this.V = a2;
                this.F.w(AbstractC5762yQ0.g(this.H.a()), templateUrlService.e(), AbstractC5762yQ0.c(templateUrlService));
            }
        }
    }

    @Override // defpackage.Hq1
    public void f(boolean z) {
        Objects.requireNonNull(this.F);
        AbstractC3364kK0.g("Android.OmniboxFocusReason", z ? 1 : 0, 14);
    }

    @Override // defpackage.AbstractC0061Ba
    public void g() {
        this.F.s();
    }

    @Override // defpackage.Hq1
    public View h() {
        Tab d = this.H.d();
        if (d == null) {
            return null;
        }
        return d.b();
    }

    @Override // defpackage.Hq1
    public boolean i() {
        return !this.H.a();
    }

    @Override // defpackage.Hq1
    public void j() {
        this.F.a();
        q();
    }

    @Override // defpackage.AbstractC4592ra0
    public void k() {
        this.F.u();
    }

    @Override // defpackage.AbstractC4592ra0
    public void l() {
        this.F.i();
    }

    @Override // defpackage.AbstractC4592ra0
    public void o() {
        Profile profile;
        this.F.m(this.H.i());
        C3450ks0 ks0 = this.M;
        if (!(ks0 == null || (profile = (Profile) ((C1078Rq0) this.O).H) == null)) {
            N.MZa0jqjv(ks0.f10309a, ks0, profile);
        }
        this.F.t();
    }

    public void onConfigurationChanged(Configuration configuration) {
        AbstractView$OnClickListenerC5272va0 va0 = this.F;
        if (va0.T && va0.W && configuration.keyboard != 2) {
            va0.n(false, null, 12);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005f, code lost:
        if (r4.onKeyDown(r11, r12) != false) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x011d, code lost:
        if (r10.getSelectionEnd() == r10.getText().length()) goto L_0x011f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0122  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKey(android.view.View r10, int r11, android.view.KeyEvent r12) {
        /*
        // Method dump skipped, instructions count: 324
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnKeyListenerC0001Aa0.onKey(android.view.View, int, android.view.KeyEvent):boolean");
    }

    public void onLowMemory() {
    }

    public void p(AbstractC1740ar1 ar1) {
        this.F.M.b(ar1);
    }

    public final void q() {
        View b;
        if (this.H.r() && (b = this.H.d().b()) != null) {
            b.requestFocus();
        }
    }

    public void r(String str, int i, long j, String str2, byte[] bArr) {
        Tab d = this.H.d();
        if (!this.R.a(str, i, str2, bArr, this.H.a())) {
            if (d != null && (d.isNativePage() || AbstractC5154ur1.g(d.s()))) {
                if ((i & 255) == 5) {
                    AbstractC1499Yn0.a(0);
                } else if (N.Mj1SQ9S8(str)) {
                    AbstractC1499Yn0.a(1);
                } else {
                    AbstractC1499Yn0.a(2);
                }
                if (str.isEmpty()) {
                    str = d.s();
                }
            }
            if (d != null && !str.isEmpty()) {
                LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
                loadUrlParams.f = GeolocationHeader.b(str, d);
                loadUrlParams.c = 33554432 | i;
                if (j != 0) {
                    loadUrlParams.l = j;
                }
                if (!TextUtils.isEmpty(str2)) {
                    StringBuilder sb = new StringBuilder();
                    String str3 = loadUrlParams.f;
                    if (str3 != null && !str3.isEmpty()) {
                        sb.append(str3);
                        sb.append("\r\n");
                    }
                    loadUrlParams.e = new C5952za0(this, str2);
                    sb.append(loadUrlParams.a("\r\n", true));
                    loadUrlParams.f = sb.toString();
                }
                if (!(bArr == null || bArr.length == 0)) {
                    loadUrlParams.h = ResourceRequestBody.a(bArr);
                }
                d.c(loadUrlParams);
                AbstractC3535lK0.a("MobileOmniboxUse");
            }
            this.S.c();
            q();
        }
    }

    public final void t(Profile profile) {
        if (profile != null && this.W) {
            C0859Oc oc = this.L;
            C2379ed edVar = oc.H;
            AutocompleteController autocompleteController = edVar.P;
            autocompleteController.b(true);
            autocompleteController.f = N.M09VlOh_("OmniboxNativeVoiceSuggestProvider");
            autocompleteController.f10726a = N.MHKRbGMP(autocompleteController, profile);
            C4044oJ oJVar = edVar.g0;
            X60 x60 = oJVar.g;
            if (x60 != null) {
                x60.a();
                oJVar.g = null;
            }
            MZ mz = oJVar.f;
            if (mz != null) {
                mz.b();
                oJVar.f = null;
            }
            BookmarkBridge bookmarkBridge = oJVar.h;
            if (bookmarkBridge != null) {
                bookmarkBridge.a();
                oJVar.h = null;
            }
            oJVar.g = new X60(profile);
            PF pf = CV.f7814a;
            oJVar.f = QZ.a(2, PZ.a(profile), pf, 512000);
            oJVar.h = new BookmarkBridge(profile);
            C4305ps0 ps0 = oc.G;
            MZ mz2 = ps0.e;
            if (mz2 != null) {
                mz2.b();
                ps0.e = null;
            }
            ps0.e = QZ.a(3, PZ.a(profile), pf, 512000);
            C3450ks0 ks0 = this.M;
            N.MXz11HdP(ks0.f10309a, ks0, profile);
            this.F.k(AbstractC5762yQ0.g(profile.g()));
        }
    }

    public void u(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (!this.W) {
                this.T.add(new RunnableC5782ya0(this, str));
                return;
            }
            this.F.n(true, null, 9);
            AbstractView$OnClickListenerC5272va0 va0 = this.F;
            va0.f11487J.k(Pq1.c(str), 0, 0);
            C2379ed edVar = this.L.H;
            edVar.r(false);
            if (edVar.N.r()) {
                edVar.P.a(edVar.N.b(), edVar.N.i(), edVar.N.l(false), str, -1, false, null, false);
            }
            this.N.i(true, false);
        }
    }

    public void x() {
        AbstractView$OnClickListenerC5272va0 va0 = this.F;
        if (va0.T) {
            if (AbstractC5818ym0.a(this.H.i(), this.H.a())) {
                AbstractView$OnClickListenerC5272va0 va02 = this.F;
                va02.f11487J.k(Pq1.c, 2, 0);
                va02.c();
            } else {
                this.F.o(this.H.h(), 0, 0);
            }
            this.N.i(false, false);
            return;
        }
        va0.m(this.H.i());
    }
}
