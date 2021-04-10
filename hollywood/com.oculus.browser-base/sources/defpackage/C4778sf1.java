package defpackage;

import J.N;
import android.content.Context;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.site_settings.CookieControlsServiceBridge;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;
import org.chromium.chrome.browser.tasks.TasksView;

/* renamed from: sf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4778sf1 implements AbstractC4096of1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC5959zc1 f11290a;
    public final TasksView b;
    public final C5628xf1 c;
    public C0642Kl0 d;
    public Jn1 e;
    public final UH0 f;
    public final boolean g;
    public final int h;

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C4778sf1(org.chromium.chrome.browser.app.ChromeActivity r23, defpackage.GP0 r24, defpackage.UH0 r25, int r26, defpackage.Q31 r27, boolean r28, boolean r29) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4778sf1.<init>(org.chromium.chrome.browser.app.ChromeActivity, GP0, UH0, int, Q31, boolean, boolean):void");
    }

    public Q31 a() {
        int i = this.h;
        if (i == 1 || i == 0) {
            return this.f11290a.p();
        }
        return null;
    }

    public void b() {
        C0642Kl0 kl0 = this.d;
        if (kl0 != null) {
            Profile b2 = Profile.b();
            View$OnClickListenerC5098uY0 U = kl0.F.U();
            if (kl0.f8385J == null) {
                kl0.f8385J = new C4105oi1(kl0.F, 1, 1, new KZ(b2));
                kl0.K = new C0520Il0(b2, U);
            }
            Objects.requireNonNull(F31.a());
            OfflinePageBridge a2 = OfflinePageBridge.a(b2);
            C1425Xh1 xh1 = new C1425Xh1(kl0.f8385J, kl0.K, new C1486Yh1(kl0.F, b2, U), kl0, a2);
            kl0.I = xh1;
            xh1.a(1);
            MostVisitedSitesBridge mostVisitedSitesBridge = (MostVisitedSitesBridge) ((C1486Yh1) xh1.b).f9290a;
            mostVisitedSitesBridge.b = xh1;
            N.MsZWK0fV(mostVisitedSitesBridge.f10768a, mostVisitedSitesBridge, mostVisitedSitesBridge, 12);
        }
        C5628xf1 xf1 = this.c;
        View$OnClickListenerC2109d00 d00 = xf1.G;
        if (!d00.H) {
            d00.F = new CookieControlsServiceBridge(d00);
            d00.I = true;
            d00.H = true;
        }
        xf1.I.j(AbstractC5798yf1.h, xf1.G.I);
    }

    public void c(Context context, AbstractC1834bO bOVar) {
        AbstractC5959zc1 zc1;
        AbstractC5959zc1 zc12 = this.f11290a;
        if (zc12 != null) {
            ChromeActivity chromeActivity = (ChromeActivity) context;
            zc12.o(chromeActivity, chromeActivity.x0, chromeActivity.I0.i(), chromeActivity, chromeActivity.l0());
        }
        C5628xf1 xf1 = this.c;
        xf1.F = bOVar;
        xf1.I.m(AbstractC5798yf1.o, new View$OnClickListenerC4948tf1(xf1));
        xf1.I.m(AbstractC5798yf1.p, new C5118uf1(xf1));
        xf1.I.m(AbstractC5798yf1.r, new View$OnClickListenerC5288vf1(xf1));
        C5458wf1 wf1 = new C5458wf1(xf1);
        xf1.H = wf1;
        xf1.G.G.b(wf1);
        xf1.I.m(AbstractC5798yf1.k, xf1.G);
        xf1.I.m(AbstractC5798yf1.i, xf1.G);
        if (this.g && (zc1 = this.f11290a) != null) {
            zc1.h().d(this.c);
            Dn1.c(Profile.b());
        }
    }
}
