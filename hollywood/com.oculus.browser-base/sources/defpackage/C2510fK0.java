package defpackage;

import J.N;
import android.app.Activity;
import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: fK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2510fK0 extends WK implements Qr1 {
    public String F;
    public boolean G;
    public AbstractC6022zx1 H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9915J;
    public String K;
    public boolean L;
    public boolean M;
    public long N;
    public C2169dK0 O;
    public boolean P;
    public Tab Q;
    public InterceptNavigationDelegate R;

    public C2510fK0(Tab tab) {
        this.Q = tab;
        tab.A(this);
    }

    public static boolean V() {
        if (AbstractC1575Zv.e().g("enable-dom-distiller") && !AbstractC1575Zv.e().g("disable-reader-mode-bottom-bar")) {
            if (GG.a() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        if (this.M) {
            AbstractC3364kK0.i("DomDistiller.Time.ViewingReaderModePage", W());
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void G(Tab tab, LoadUrlParams loadUrlParams, int i) {
        WebContents webContents;
        Activity b = AbstractC5112ud1.b(tab);
        if (((b == null || b.getIntent().getExtras() == null) ? 0 : b.getIntent().getExtras().getInt("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE")) == 3 && HG.c(loadUrlParams.f10938a) && (webContents = ((TabImpl) tab).L) != null) {
            C1998cK0 ck0 = new C1998cK0(b);
            this.R = ck0;
            N.MEwGhN3r(ck0, webContents);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        if (!this.f9915J) {
            this.I = 1;
            this.K = tab.s();
            if (this.O == null) {
                this.O = new C2169dK0(this, tab);
                ((D61) tab.M().c(D61.class)).F.b(this.O);
            }
            if (HG.c(tab.s()) && !this.M) {
                X();
            }
            if (this.H == null && this.Q.l() != null) {
                this.H = new C2339eK0(this, this.Q.l());
            }
            Y();
        }
    }

    public final long W() {
        this.M = false;
        return SystemClock.elapsedRealtime() - this.N;
    }

    public final void X() {
        this.M = true;
        this.N = SystemClock.elapsedRealtime();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if ((defpackage.GG.a() == 4) == false) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void Y() {
        /*
            r4 = this;
            org.chromium.chrome.browser.tab.Tab r0 = r4.Q
            if (r0 == 0) goto L_0x003b
            org.chromium.content_public.browser.WebContents r0 = r0.l()
            if (r0 != 0) goto L_0x000b
            goto L_0x003b
        L_0x000b:
            org.chromium.chrome.browser.tab.Tab r0 = r4.Q
            org.chromium.content_public.browser.WebContents r0 = r0.l()
            org.chromium.content_public.browser.NavigationController r0 = r0.f()
            boolean r0 = r0.p()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002a
            int r0 = defpackage.GG.a()
            r3 = 4
            if (r0 != r3) goto L_0x0026
            r0 = r1
            goto L_0x0027
        L_0x0026:
            r0 = r2
        L_0x0027:
            if (r0 != 0) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r1 = r2
        L_0x002b:
            if (r1 != 0) goto L_0x003b
            int r0 = r4.I
            if (r0 != 0) goto L_0x003b
            boolean r0 = r4.f9915J
            if (r0 == 0) goto L_0x0036
            goto L_0x003b
        L_0x0036:
            org.chromium.chrome.browser.tab.Tab r0 = r4.Q
            J.N.MqhmiFry(r0)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2510fK0.Y():void");
    }

    @Override // defpackage.Qr1
    public void destroy() {
        AbstractC6022zx1 zx1 = this.H;
        if (zx1 != null) {
            zx1.destroy();
        }
        this.P = true;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        if (!this.f9915J || HG.c(tab.s())) {
            this.I = 1;
            this.K = tab.s();
            if (tab.l() != null) {
                this.H = new C2339eK0(this, this.Q.l());
                if (HG.c(tab.s())) {
                    this.I = 2;
                    this.F = tab.s();
                }
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        if (tab != null) {
            if (!this.L) {
                AbstractC3100ip1.f10165a.a("DomDistiller.ReaderShownForPageLoad", false);
            }
            if (this.M) {
                AbstractC3364kK0.i("DomDistiller.Time.ViewingReaderModePage", W());
            }
            ((D61) tab.M().c(D61.class)).F.c(this.O);
            AbstractC6022zx1 zx1 = this.H;
            if (zx1 != null) {
                zx1.destroy();
            }
            this.I = 0;
            this.f9915J = false;
            this.K = null;
            this.L = false;
            this.M = false;
            this.O = null;
        }
    }
}
