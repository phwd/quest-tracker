package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: b61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1786b61 extends Pr1 {
    public final TabImpl F;
    public final Callback G = new Z51(this);
    public long H;
    public AbstractC2742gk I;

    public C1786b61(Tab tab) {
        TabImpl tabImpl = (TabImpl) tab;
        this.F = tabImpl;
        tabImpl.P.b(new C1606a61(this));
        if (tabImpl.isInitialized() && !TabImpl.c0(tabImpl)) {
            m();
        }
    }

    public static int e(Tab tab) {
        if (tab == null || ((C1786b61) tab.M().c(C1786b61.class)) == null) {
            return 3;
        }
        return ((C1786b61) tab.M().c(C1786b61.class)).c();
    }

    public static void j(Tab tab, int i, boolean z) {
        if (tab != null && ((C1786b61) tab.M().c(C1786b61.class)) != null) {
            ((C1786b61) tab.M().c(C1786b61.class)).h(i, z);
        }
    }

    public static void l(Tab tab) {
        if (tab != null && ((C1786b61) tab.M().c(C1786b61.class)) != null) {
            ((C1786b61) tab.M().c(C1786b61.class)).k();
        }
    }

    public final int c() {
        AbstractC2742gk gkVar = this.I;
        if (gkVar == null) {
            return 3;
        }
        return ((Integer) gkVar.H).intValue();
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        long j = this.H;
        if (j != 0) {
            N.M3JHMcaQ(j, this);
        }
    }

    public void h(int i, boolean z) {
        int c = c();
        if (c != 2 || i != 1) {
            if (c != 1 || i != 2) {
                if (this.H == 0) {
                    this.H = N.MnC9A38r(this);
                }
                N.MQyQC7UK(this.H, this, this.F.L, c, i, z);
            }
        }
    }

    public final void k() {
        if (!this.F.G()) {
            h(3, c() != 2);
        }
    }

    public final void m() {
        AbstractC2742gk gkVar = this.I;
        if (gkVar != null) {
            gkVar.I.c(this.G);
        }
        TabImpl tabImpl = this.F;
        AbstractC2742gk d = tabImpl.e0.d(tabImpl);
        this.I = d;
        if (d != null) {
            d.l(this.G);
        }
    }
}
