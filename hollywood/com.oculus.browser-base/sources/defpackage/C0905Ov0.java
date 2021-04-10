package defpackage;

import J.N;
import android.app.Activity;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Ov0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0905Ov0 {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f8655a;
    public final AbstractC0124Ca1 b;
    public final AbstractC1404Xa1 c = new C0783Mv0(this);
    public final C3538lM d;
    public final C3766mj1 e;
    public final C4172p41 f;
    public Tab g;
    public String h;

    public C0905Ov0(Activity activity, AbstractC0124Ca1 ca1, C3538lM lMVar, C3766mj1 mj1, C4172p41 p41) {
        this.f8655a = activity;
        this.b = ca1;
        this.d = lMVar;
        this.e = mj1;
        this.f = p41;
        new C0844Nv0(this, ca1);
        b(((AbstractC0246Ea1) ca1).j());
    }

    public final void b(Tab tab) {
        if (tab != this.g && ((AbstractC0246Ea1) this.b).j() == tab) {
            e(tab);
            if (this.g != null && !tab.isHidden()) {
                f(tab.getUrl());
            }
        }
    }

    public final boolean c(boolean z, String str) {
        C2976i41 W = C2976i41.W(this.g);
        boolean z2 = false;
        if (z && str.equals(W.H)) {
            return false;
        }
        if (!z) {
            if (!(W.H != null)) {
                return false;
            }
        }
        if (z) {
            W.H = str;
            W.F.A(W);
            W.F.t();
            WebContents l = W.F.l();
            if (l != null) {
                l.z();
                l.a0();
                l.V(true);
                ((WebContentsAccessibilityImpl) AbstractC3637lx1.a(l)).t(true);
                if (N.MybJWOXK(l) || N.MKIWbnaU(l) || N.MDk3$bjp(l)) {
                    N.M3xnlzVW(l);
                }
            }
            C3649m10 h2 = C3649m10.h(W.F);
            if (h2 != null) {
                h2.l(true);
            }
            if (W.G != null && ((C0011Ad1) W.F.C()).b(W)) {
                z2 = true;
            }
            if (z2) {
                W.X();
            } else {
                W.V();
            }
            TabContentManager tabContentManager = ((ChromeActivity) W.F.i().s0().get()).x0;
            if (tabContentManager != null) {
                W.G.post(new RunnableC2634g41(W, tabContentManager));
            }
            return true;
        }
        ((C0011Ad1) W.F.C()).c(W);
        W.G = null;
        WebContents l2 = W.F.l();
        if (l2 != null) {
            l2.O();
            l2.V(false);
            ((WebContentsAccessibilityImpl) AbstractC3637lx1.a(l2)).t(false);
        }
        W.G = null;
        W.H = null;
        if (!this.g.d() && !C3372kO0.W(this.g)) {
            this.g.q();
        }
        return false;
    }

    public final void d() {
        C3538lM lMVar = this.d;
        C3811my1 my1 = new C3811my1(System.currentTimeMillis(), this.h, 2);
        Objects.requireNonNull(lMVar);
        C5232vH0 vh0 = new C5232vH0();
        C5232vH0 vh02 = lMVar.b;
        C2342eM eMVar = new C2342eM(lMVar, my1, vh0);
        C2513fM fMVar = new C2513fM();
        vh02.h(eMVar);
        vh02.a(fMVar);
        this.e.f10443a.f(new C3253jj1(this.h)).g(new C0722Lv0(this, "reportUsageStop"));
        this.h = null;
    }

    public final void e(Tab tab) {
        Tab tab2 = this.g;
        if (!(tab2 == tab || tab2 == null)) {
            tab2.I(this.c);
        }
        if (tab == null || !tab.a()) {
            this.g = tab;
            if (tab != null) {
                tab.A(this.c);
                return;
            }
            return;
        }
        this.g = null;
    }

    public final void f(GURL gurl) {
        String str;
        boolean z;
        if (GURL.k(gurl)) {
            str = "";
        } else {
            str = gurl.d();
        }
        boolean equals = str.equals(this.h);
        if (gurl != null) {
            Pattern pattern = AbstractC5154ur1.f11440a;
            if (AbstractC5154ur1.i(gurl.g())) {
                z = true;
                boolean a2 = this.f.a(str);
                boolean c2 = c(a2, str);
                if (this.h != null && (c2 || !equals)) {
                    d();
                }
                if (z && !a2 && !equals) {
                    this.h = str;
                    C3538lM lMVar = this.d;
                    C3811my1 my1 = new C3811my1(System.currentTimeMillis(), this.h, 1);
                    Objects.requireNonNull(lMVar);
                    C5232vH0 vh0 = new C5232vH0();
                    C5232vH0 vh02 = lMVar.b;
                    C2342eM eMVar = new C2342eM(lMVar, my1, vh0);
                    C2513fM fMVar = new C2513fM();
                    vh02.h(eMVar);
                    vh02.a(fMVar);
                    this.e.f10443a.f(new C3253jj1(this.h)).g(new C0722Lv0(this, "reportUsageStart"));
                    return;
                }
                return;
            }
        }
        z = false;
        boolean a22 = this.f.a(str);
        boolean c22 = c(a22, str);
        d();
        if (z) {
        }
    }
}
