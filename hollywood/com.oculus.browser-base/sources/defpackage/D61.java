package defpackage;

import J.N;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.dom_distiller.content.DistillablePageUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: D61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D61 extends WK implements DistillablePageUtils.PageDistillableDelegate, Qr1 {
    public final C1322Vq0 F = new C1322Vq0();
    public Tab G;
    public WebContents H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f7865J;
    public boolean K;

    public D61(Tab tab) {
        this.G = tab;
        V();
        this.G.A(this);
    }

    public final void V() {
        this.I = false;
        this.f7865J = false;
        this.K = false;
        Tab tab = this.G;
        if (tab != null && tab.l() != null && this.G.l() != this.H) {
            WebContents l = this.G.l();
            this.H = l;
            N.MFtP575Y(l, this);
        }
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.F.clear();
        this.G.I(this);
        this.G = null;
        this.H = null;
        V();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005d, code lost:
        if ((!J.N.MzIXnlkD(defpackage.Wr1.a(org.chromium.chrome.browser.profiles.Profile.a(r9.l())).f10883a, "dom_distiller.reader_for_accessibility") && defpackage.GG.a() == 2) == false) goto L_0x005f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    @Override // org.chromium.components.dom_distiller.content.DistillablePageUtils.PageDistillableDelegate
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g(boolean r8, boolean r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.D61.g(boolean, boolean, boolean):void");
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            V();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        V();
    }
}
