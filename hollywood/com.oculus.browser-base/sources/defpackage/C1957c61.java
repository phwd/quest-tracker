package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: c61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1957c61 extends WK implements Qr1 {
    public TabImpl F;
    public int G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f9583J;
    public int K;
    public boolean L;

    public C1957c61(Tab tab) {
        this.F = (TabImpl) tab;
        tab.A(this);
    }

    public static C1957c61 V(Tab tab) {
        C1957c61 c61 = (C1957c61) tab.M().c(C1957c61.class);
        if (c61 != null) {
            return c61;
        }
        C1957c61 c612 = new C1957c61(tab);
        tab.M().e(C1957c61.class, c612);
        return c612;
    }

    public final void W() {
        this.L = true;
        C1261Uq0 Y = this.F.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).k(this.F, this.G, this.H, this.I, this.f9583J, this.K);
        }
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.F.P.c(this);
        this.F = null;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.G = 0;
        this.H = 0;
        this.I = 0;
        this.L = false;
    }
}
