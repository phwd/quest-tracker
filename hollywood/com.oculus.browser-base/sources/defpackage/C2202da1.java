package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: da1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2202da1 implements AbstractC4968tm0, AbstractC4371qE {
    public final AbstractC1404Xa1 F = new C1860ba1(this);
    public ChromeActivity G;
    public final C2746gl0 H;
    public final Q31 I;

    /* renamed from: J  reason: collision with root package name */
    public final Q31 f9790J;
    public C3965nt K;
    public AbstractC0855Oa1 L;
    public Tab M;
    public int N;

    public C2202da1(ChromeActivity chromeActivity, C2746gl0 gl0, Q31 q31, Q31 q312) {
        this.G = chromeActivity;
        this.H = gl0;
        this.I = q31;
        this.f9790J = q312;
        chromeActivity.Y.a(this);
        this.N = -1;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        AbstractC0855Oa1 oa1 = this.L;
        if (oa1 != null) {
            oa1.destroy();
        }
        C3965nt ntVar = this.K;
        if (ntVar != null) {
            ((C1551Zj) ntVar.P).Y.c(ntVar);
        }
        Tab tab = this.M;
        if (tab != null) {
            tab.I(this.F);
            this.M = null;
        }
        this.G = null;
    }

    public final void f(Tab tab) {
        if (tab != this.M) {
            this.H.c(1, 6);
            Tab tab2 = this.M;
            if (tab2 != null) {
                tab2.I(this.F);
            }
            this.M = tab;
            if (tab != null) {
                tab.A(this.F);
                g();
            }
        }
    }

    public final void g() {
        if (this.M.isUserInteractable()) {
            this.H.g(1, this.N);
            this.N = -1;
        } else if (this.N == -1) {
            this.N = this.H.l(1);
        }
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        this.K = new C3965nt(this.G, this.f9790J);
        ((C3461kw) this.I.get()).o(this.K.Q);
        C2746gl0 gl0 = this.H;
        gl0.f10017a.put(1, this.K);
        f(this.G.K0());
        this.L = new C2031ca1(this, this.G.P());
    }
}
