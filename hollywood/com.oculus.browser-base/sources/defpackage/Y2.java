package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Y2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Y2 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final C1595a3 f9249a;
    public final W2 b;
    public Tab c;

    public Y2(C1595a3 a3Var) {
        this.f9249a = a3Var;
        X2 x2 = new X2(this);
        this.b = x2;
        a3Var.F.b(x2);
        Tab tab = a3Var.H;
        Tab tab2 = this.c;
        if (tab2 != null) {
            tab2.I(this);
        }
        this.c = tab;
        if (tab != null) {
            tab.A(this);
        }
    }

    public void V(Tab tab, boolean z) {
    }

    public void destroy() {
        Tab tab = this.c;
        if (tab != null) {
            tab.I(this);
            this.c = null;
        }
        C1595a3 a3Var = this.f9249a;
        a3Var.F.c(this.b);
    }
}
