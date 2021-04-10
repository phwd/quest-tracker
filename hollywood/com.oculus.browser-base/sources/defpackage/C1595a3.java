package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: a3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1595a3 implements Q31 {
    public final C1322Vq0 F;
    public final C1261Uq0 G;
    public Tab H;
    public D70 I;

    /* renamed from: J  reason: collision with root package name */
    public KO0 f9404J = new T2(this);
    public AbstractC0124Ca1 K;
    public AbstractC0855Oa1 L;
    public AbstractC0612Ka1 M;
    public int N;

    public C1595a3() {
        C1322Vq0 vq0 = new C1322Vq0();
        this.F = vq0;
        this.G = vq0.e();
    }

    public static void a(C1595a3 a3Var, Tab tab) {
        D70 d70 = a3Var.I;
        if (d70 != null) {
            AbstractC2300e70 e70 = d70.S;
            if (!(e70 instanceof D11) && !(e70 instanceof AW0) && tab != null) {
                return;
            }
        }
        if (a3Var.H != tab) {
            a3Var.H = tab;
            a3Var.N = -1;
            a3Var.G.b();
            while (a3Var.G.hasNext()) {
                ((W2) a3Var.G.next()).a(tab, false);
            }
        }
    }

    @Override // defpackage.Q31
    public Object get() {
        return this.H;
    }
}
