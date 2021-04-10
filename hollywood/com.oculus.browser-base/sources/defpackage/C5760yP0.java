package defpackage;

import J.N;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: yP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5760yP0 extends WK implements Qr1 {
    public int F;
    public int G = 0;
    public Tab H;

    public C5760yP0(Tab tab) {
        this.H = tab;
        tab.A(this);
    }

    public static C5760yP0 V(Tab tab) {
        C5760yP0 yp0 = null;
        if (tab == null || !tab.isInitialized()) {
            return null;
        }
        if (tab.isInitialized()) {
            yp0 = (C5760yP0) tab.M().c(C5760yP0.class);
        }
        if (yp0 != null) {
            return yp0;
        }
        C5760yP0 yp02 = (C5760yP0) tab.M().e(C5760yP0.class, new C5760yP0(tab));
        tab.A(yp02);
        return yp02;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void E(Tab tab, boolean z) {
        W(tab);
    }

    public final void W(Tab tab) {
        int i = this.F;
        if (i > 0) {
            AbstractC3364kK0.d("Tab.Screenshot.ScreenshotsPerPage", i);
            AbstractC3364kK0.g("Tab.Screenshot.Action", this.G, 3);
            WebContents l = tab.l();
            if (l != null) {
                N.MX4lNgiF(l, "Tab.Screenshot", "HasOccurred");
            }
        }
        this.F = 0;
        this.G = 0;
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.H.I(this);
        this.H = null;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void m(Tab tab, boolean z) {
        W(tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        W(tab);
    }
}
