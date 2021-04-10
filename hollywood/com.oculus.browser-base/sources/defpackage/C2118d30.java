package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: d30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2118d30 extends Pr1 {
    public final C1947c30 F;
    public final C1596a30 G;

    public C2118d30(Tab tab) {
        C1596a30 a30 = new C1596a30(tab);
        this.G = a30;
        C1947c30 c30 = new C1947c30(a30);
        this.F = c30;
        a30.c = c30;
        TabImpl tabImpl = a30.f9405a;
        tabImpl.P.b(a30.b);
    }

    public static C1947c30 c(Tab tab) {
        C2118d30 d30 = (C2118d30) tab.M().c(C2118d30.class);
        if (d30 == null) {
            return null;
        }
        return d30.F;
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        C1596a30 a30 = this.G;
        TabImpl tabImpl = a30.f9405a;
        tabImpl.P.c(a30.b);
        a30.c = null;
    }
}
