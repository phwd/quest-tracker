package defpackage;

import J.N;
import android.app.Activity;
import android.os.Handler;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Fd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0316Fd1 extends AbstractC6022zx1 {
    public final /* synthetic */ C0377Gd1 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0316Fd1(C0377Gd1 gd1, WebContents webContents) {
        super(webContents);
        this.G = gd1;
    }

    @Override // defpackage.AbstractC6022zx1
    public void destroy() {
        C4941td0.i(ContextUtils.getApplicationContext(), this.G.G.getId(), null, this.G.f8098J);
        super.destroy();
    }

    @Override // defpackage.AbstractC6022zx1
    public void didChangeThemeColor() {
        TabImpl tabImpl = this.G.G;
        tabImpl.m0(tabImpl.L.m());
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFailLoad(boolean z, int i, GURL gurl) {
        C1261Uq0 Y = this.G.G.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).t(this.G.G, z, i, gurl);
        }
        if (z) {
            this.G.G.T(i);
        }
        gurl.h();
        Objects.requireNonNull(AppHooks.get());
        ContextUtils.getApplicationContext();
        if (i == -22) {
            ContextUtils.getApplicationContext();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishLoad(long j, GURL gurl, boolean z, boolean z2) {
        TabImpl tabImpl = this.G.G;
        AbstractC5818ym0 ym0 = tabImpl.K;
        if (ym0 != null) {
            IT it = (IT) ym0;
            N.MhCci$0r(tabImpl.F, it.f8227a, it.b);
        }
        if (z2) {
            this.G.G.U(gurl);
        }
        Objects.requireNonNull(AppHooks.get());
        ContextUtils.getApplicationContext();
        gurl.h();
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        C41 m;
        K41 k41;
        C1261Uq0 Y = this.G.G.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).u(this.G.G, navigationHandle);
        }
        int i = navigationHandle.j;
        if (i != 0) {
            if (navigationHandle.f10940a) {
                this.G.G.T(i);
            }
            String str = navigationHandle.e.f11029a;
            int i2 = navigationHandle.j;
            Objects.requireNonNull(AppHooks.get());
            ContextUtils.getApplicationContext();
            if (i2 == -22) {
                ContextUtils.getApplicationContext();
            }
        }
        C0377Gd1 gd1 = this.G;
        gd1.f8098J = navigationHandle.e;
        if (navigationHandle.f) {
            if (navigationHandle.f10940a) {
                TabImpl tabImpl = gd1.G;
                tabImpl.U = true;
                tabImpl.n0();
                TabImpl tabImpl2 = this.G.G;
                GURL gurl = navigationHandle.e;
                Integer num = navigationHandle.d;
                boolean z = false;
                tabImpl2.d0 = false;
                if (num != null && (num.intValue() & 255) == 8) {
                    z = true;
                }
                if (!tabImpl2.d0(gurl.h(), z)) {
                    tabImpl2.n0();
                    if (tabImpl2.K != null) {
                        tabImpl2.a0(true, null);
                    }
                }
                this.G.G.T = navigationHandle.g;
                Y.b();
                while (Y.hasNext()) {
                    ((AbstractC1404Xa1) Y.next()).S(this.G.G);
                }
            }
            if (navigationHandle.f10940a && (m = C41.m(this.G.G)) != null && (k41 = m.H) != null && k41.I) {
                m.j();
                K41 k412 = m.H;
                if (m.L == null) {
                    m.L = new RunnableC5874z41(m);
                }
                k412.postDelayed(m.L, 500);
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFirstVisuallyNonEmptyPaint() {
        C1261Uq0 Y = this.G.G.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).h(this.G.G);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didRedirectNavigation(NavigationHandle navigationHandle) {
        C1261Uq0 Y = this.G.G.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).v(this.G.G, navigationHandle);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !navigationHandle.c) {
            this.G.G.V(navigationHandle.e);
        }
        C1261Uq0 Y = this.G.G.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).w(this.G.G, navigationHandle);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void loadProgressChanged(float f) {
        TabImpl tabImpl = this.G.G;
        if (tabImpl.Y) {
            tabImpl.f0(f);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntriesChanged() {
        this.G.G.U = true;
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntriesDeleted() {
        this.G.G.g0();
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderProcessGone(boolean z) {
        int i;
        StringBuilder i2 = AbstractC2531fV.i("renderProcessGone() for tab id: ");
        i2.append(this.G.G.getId());
        i2.append(", oom protected: ");
        i2.append(Boolean.toString(z));
        i2.append(", already needs reload: ");
        i2.append(Boolean.toString(this.G.G.f()));
        AbstractC1220Ua0.d("TabWebContentsObs", i2.toString(), new Object[0]);
        if (!this.G.G.f() && !C3372kO0.W(this.G.G)) {
            int stateForApplication = ApplicationStatus.getStateForApplication();
            int i3 = 1;
            boolean z2 = stateForApplication == 1;
            boolean z3 = stateForApplication == 2;
            AbstractC3364kK0.g("Tab.RendererExitStatus", z ? z2 ? 0 : z3 ? 1 : 2 : z2 ? 3 : z3 ? 4 : 5, 6);
            Activity activity = (Activity) this.G.G.f10773J.s0().get();
            if (activity == null) {
                i = 2;
            } else {
                i = ApplicationStatus.e(activity);
            }
            if (this.G.G.isHidden() || i == 4 || i == 5 || i == 6) {
                this.G.G.L.f().g();
                if (!z2) {
                    i3 = 2;
                }
            } else {
                Handler handler = new Handler();
                TabImpl tabImpl = this.G.G;
                C3372kO0 ko0 = (C3372kO0) tabImpl.M().c(C3372kO0.class);
                if (ko0 == null) {
                    ko0 = (C3372kO0) tabImpl.M().e(C3372kO0.class, new C3372kO0(tabImpl));
                }
                ko0.getClass();
                handler.post(new RunnableC0255Ed1(ko0));
                AbstractC3100ip1.f10165a.a("Stability.Android.RendererCrash", true);
                i3 = 0;
            }
            AbstractC3364kK0.g("Tab.RendererCrashStatus", i3, 3);
            TabImpl tabImpl2 = this.G.G;
            tabImpl2.Y = false;
            C1261Uq0 Y = tabImpl2.Y();
            while (Y.hasNext()) {
                ((AbstractC1404Xa1) Y.next()).q(tabImpl2);
            }
            tabImpl2.Z = false;
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void titleWasSet(String str) {
        this.G.G.o0(str);
    }

    @Override // defpackage.AbstractC6022zx1
    public void viewportFitChanged(int i) {
        TabImpl tabImpl = this.G.G;
        Rr1 M = tabImpl.M();
        C2839hG hGVar = (C2839hG) M.c(C2839hG.class);
        if (hGVar == null) {
            hGVar = (C2839hG) M.e(C2839hG.class, new C2839hG(tabImpl));
        }
        C2326eG eGVar = hGVar.G;
        if (i != eGVar.G) {
            eGVar.G = i;
            eGVar.c();
        }
    }
}
