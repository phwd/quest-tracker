package org.chromium.chrome.browser.compositor.bottombar;

import J.N;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OverlayPanelContent {

    /* renamed from: a  reason: collision with root package name */
    public WebContents f10635a;
    public ViewGroup b;
    public long c = N.MIJaVtKT(this);
    public final WebContentsDelegateAndroid d;
    public ChromeActivity e;
    public AbstractC6022zx1 f;
    public String g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public AbstractC6009zt0 l;
    public C0231Dt0 m;
    public String n;
    public InterceptNavigationDelegate o;
    public boolean p;
    public int q;
    public int r;
    public boolean s;
    public final int t;
    public int u;

    public OverlayPanelContent(AbstractC6009zt0 zt0, C0231Dt0 dt0, ChromeActivity chromeActivity, boolean z, float f2) {
        this.l = zt0;
        this.m = dt0;
        this.e = chromeActivity;
        this.p = z;
        this.t = (int) (f2 * chromeActivity.getResources().getDisplayMetrics().density);
        this.d = new C0597Jt0(this);
    }

    public final void a() {
        int i2;
        int i3;
        if (this.f10635a != null) {
            if (this.h && !this.i) {
                b();
            } else {
                return;
            }
        }
        WebContents a2 = AbstractC5342vx1.a(Z00.c(this.e.b0, this.p), true);
        this.f10635a = a2;
        ChromeActivity chromeActivity = this.e;
        int i4 = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
        C1459Xy xy = new C1459Xy(chromeActivity, null, a2);
        int i5 = this.q;
        if (!(i5 == 0 && this.r == 0)) {
            if (i5 == 0) {
                i2 = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
            } else {
                i2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
            }
            int i6 = this.r;
            if (i6 == 0) {
                i3 = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
            } else {
                i3 = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
            }
            xy.K = i2;
            xy.L = i3;
        }
        this.f10635a.J("89.0.4389.105", new C0779Mt0(this, xy), xy, this.e.b0, new C3466kx1());
        N.Mt4iWzCb(this.f10635a);
        N.MzHfGFwX(this.c, this, this.f10635a, this.d);
        this.f = new C0658Kt0(this, this.f10635a);
        this.b = xy;
        C0718Lt0 lt0 = new C0718Lt0(this);
        this.o = lt0;
        N.MhbyyKle(this.c, this, lt0, this.f10635a);
        ContextualSearchManager contextualSearchManager = ((C1461Xz) this.l).f9246a;
        N.MUjQ3OuO(contextualSearchManager.U, contextualSearchManager, contextualSearchManager.g());
        d();
        this.e.I0.addView(this.b, 1);
    }

    public final void b() {
        if (this.f10635a != null) {
            this.e.I0.removeView(this.b);
            N.Mgx0E3X8(this.c, this);
            this.f10635a = null;
            AbstractC6022zx1 zx1 = this.f;
            if (zx1 != null) {
                zx1.destroy();
                this.f = null;
            }
            this.h = false;
            this.j = false;
            this.i = false;
            e(false);
        }
    }

    public void c(String str, boolean z) {
        this.n = null;
        if (!z) {
            this.n = str;
            return;
        }
        a();
        this.g = str;
        this.h = true;
        this.j = true;
        this.f10635a.f().c(new LoadUrlParams(str, 0));
    }

    public final void clearNativePanelContentPtr() {
        this.c = 0;
    }

    public void d() {
        WebContents webContents = this.f10635a;
        if (webContents != null) {
            int i2 = this.r - (this.s ? this.t : 0);
            N.M7MukokD(this.c, this, webContents, this.q, i2);
            this.f10635a.p0(this.q, i2);
        }
    }

    public final void e(boolean z) {
        if (this.k != z) {
            this.k = z;
            if (z) {
                if (!TextUtils.isEmpty(this.n)) {
                    c(this.n, true);
                }
                if (this.f10635a == null) {
                    a();
                }
                WebContents webContents = this.f10635a;
                if (webContents != null) {
                    webContents.O();
                }
                C1967cA cAVar = ((C1461Xz) this.l).f9246a.T.F0;
                cAVar.b = true;
                cAVar.k = true;
                cAVar.v = System.nanoTime();
                cAVar.w = 0;
            } else {
                WebContents webContents2 = this.f10635a;
                if (webContents2 != null) {
                    webContents2.z();
                }
            }
            C1461Xz xz = (C1461Xz) this.l;
            Objects.requireNonNull(xz);
            if (z) {
                ContextualSearchManager contextualSearchManager = xz.f9246a;
                contextualSearchManager.b0 = true;
                if (contextualSearchManager.n0 == null) {
                    C2138dA dAVar = contextualSearchManager.R;
                    C4700sA sAVar = dAVar.c;
                    if ((sAVar.f != null && (sAVar.g == 2 || !dAVar.n())) && !TextUtils.isEmpty(xz.f9246a.P.f)) {
                        ContextualSearchManager contextualSearchManager2 = xz.f9246a;
                        contextualSearchManager2.n0 = new C4188pA(contextualSearchManager2.P.f, null, null, false, null, null);
                        xz.f9246a.Z = false;
                    }
                }
                ContextualSearchManager contextualSearchManager3 = xz.f9246a;
                C4188pA pAVar = contextualSearchManager3.n0;
                if (pAVar != null && (!contextualSearchManager3.Z || contextualSearchManager3.i0)) {
                    pAVar.d = false;
                    contextualSearchManager3.o();
                }
                ContextualSearchManager contextualSearchManager4 = xz.f9246a;
                contextualSearchManager4.i0 = true;
                C2138dA dAVar2 = contextualSearchManager4.R;
                dAVar2.b.n("contextual_search_tap_count", 0);
                dAVar2.b.n("contextual_search_tap_quick_answer_count", 0);
                if (ContextualSearchManager.k()) {
                    MF d2 = dAVar2.d();
                    if (d2.b()) {
                        d2.c(-1 - d2.c);
                    }
                    int c2 = dAVar2.b.c("contextual_search_promo_open_count");
                    Pattern pattern = AA.f7657a;
                    AbstractC3364kK0.d("Search.ContextualSearchPromoOpenCount", c2);
                }
                dAVar2.b.c("contextual_search_all_time_open_count");
            }
        }
    }
}
