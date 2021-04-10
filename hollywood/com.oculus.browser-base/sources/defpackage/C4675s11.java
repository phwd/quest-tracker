package defpackage;

import J.N;
import android.content.Intent;
import android.text.TextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: s11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4675s11 implements AbstractC1818bH0, AbstractC4371qE {
    public final Q31 F;
    public final M2 G;
    public final WindowAndroid H;
    public final AbstractC3226ja1 I;

    /* renamed from: J  reason: collision with root package name */
    public final S20 f11245J;
    public LoadUrlParams K;
    public Tab L;
    public C4504r11 M;

    public C4675s11(Q31 q31, M2 m2, WindowAndroid windowAndroid, AbstractC3226ja1 ja1, S20 s20) {
        this.F = q31;
        this.G = m2;
        this.H = windowAndroid;
        this.I = ja1;
        this.f11245J = s20;
        m2.a(this);
        ProfileManager.f10754a.b(this);
    }

    public static String g(Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.VIEW".equals(action) || "android.intent.action.MAIN".equals(action) || (action == null && "com.google.android.apps.chrome.Main".equals(intent.getComponent().getClassName()))) {
            return S20.m(intent);
        }
        return null;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        Tab tab = this.L;
        if (tab != null) {
            tab.destroy();
        }
        this.L = null;
        ProfileManager.f10754a.c(this);
        this.G.b(this);
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
        TraceEvent j0 = TraceEvent.j0("StartupTabPreloader.onProfileAdded");
        try {
            if (!profile.g()) {
                ProfileManager.f10754a.c(this);
                boolean j = j();
                if (j) {
                    h();
                }
                AbstractC3100ip1.f10165a.a("Startup.Android.StartupTabPreloader.TabLoaded", j);
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } else if (j0 != null) {
                j0.close();
                return;
            } else {
                return;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final void h() {
        Intent intent = (Intent) this.F.get();
        GURL a2 = AbstractC1911br1.a(g(intent));
        C3623lt ltVar = (C3623lt) this.I.S(false);
        WebContents a3 = AbstractC5342vx1.a(Profile.b(), false);
        this.K = new LoadUrlParams(a2.i(), 0);
        String h = S20.h(intent);
        if (h != null && !h.isEmpty()) {
            this.K.d = new C2512fL0(h, 1);
        }
        this.K.c = S20.k(intent, 134217728);
        C2128d61 b = C2128d61.b(false);
        b.d = false;
        b.d(1);
        b.e = this.H;
        b.k = a3;
        b.l = ltVar.g();
        Tab a4 = b.a();
        this.L = a4;
        C4504r11 r11 = new C4504r11(this, null);
        this.M = r11;
        a4.A(r11);
        this.L.c(this.K);
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
    }

    public boolean j() {
        boolean d;
        if (!N.M09VlOh_("PrioritizeBootstrapTasks") || this.L != null) {
            return false;
        }
        Intent intent = (Intent) this.F.get();
        if (U20.d(intent, "org.chromium.chrome.browser.init.DISABLE_STARTUP_TAB_PRELOADER", false) || this.f11245J.x(intent, true) || g(intent) == null || (d = U20.d(intent, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false))) {
            return false;
        }
        try {
            if (!(this.I.S(d) instanceof C3623lt)) {
                return false;
            }
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    public Tab k(LoadUrlParams loadUrlParams, int i) {
        boolean z;
        Tab tab = this.L;
        if (tab == null) {
            return null;
        }
        boolean z2 = false;
        if (i == tab.F()) {
            LoadUrlParams loadUrlParams2 = this.K;
            if (!TextUtils.equals(loadUrlParams2.f10938a, loadUrlParams.f10938a)) {
                z = false;
            } else {
                C2512fL0 fl0 = loadUrlParams2.d;
                String str = fl0 != null ? fl0.f9916a : null;
                C2512fL0 fl02 = loadUrlParams.d;
                z = TextUtils.equals(str, fl02 != null ? fl02.f9916a : null);
            }
            if (z) {
                z2 = true;
            }
        }
        AbstractC3100ip1.f10165a.a("Startup.Android.StartupTabPreloader.TabTaken", z2);
        if (!z2) {
            this.L.destroy();
            this.L = null;
            this.K = null;
            return null;
        }
        Tab tab2 = this.L;
        this.L = null;
        this.K = null;
        tab2.I(this.M);
        return tab2;
    }
}
