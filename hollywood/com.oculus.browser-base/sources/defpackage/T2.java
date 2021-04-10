package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: T2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T2 implements KO0 {
    public final /* synthetic */ C1595a3 F;

    public T2(C1595a3 a3Var) {
        this.F = a3Var;
    }

    @Override // defpackage.KO0
    public void T(AbstractC2300e70 e70) {
        if (!(e70 instanceof AW0)) {
            Tab j = ((AbstractC0246Ea1) this.F.K).j();
            if (!(e70 instanceof D11)) {
                j = null;
            }
            C1595a3.a(this.F, j);
        }
    }

    @Override // defpackage.KO0
    public void q(int i) {
        C1595a3 a3Var = this.F;
        AbstractC0124Ca1 ca1 = a3Var.K;
        if (ca1 != null && a3Var.N != i) {
            Tab o = ((AbstractC0246Ea1) ca1).o(i);
            C1595a3 a3Var2 = this.F;
            a3Var2.N = i;
            a3Var2.G.b();
            while (this.F.G.hasNext()) {
                ((W2) this.F.G.next()).a(o, true);
            }
        }
    }
}
