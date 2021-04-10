package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: hG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2839hG extends Pr1 {
    public Tab F;
    public C2326eG G = new C2326eG(new C2668gG(this.F));
    public final AbstractC1404Xa1 H;

    public C2839hG(Tab tab) {
        C2497fG fGVar = new C2497fG(this);
        this.H = fGVar;
        this.F = tab;
        tab.A(fGVar);
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        this.F.I(this.H);
        C2326eG eGVar = this.G;
        W10 w10 = eGVar.H;
        if (w10 != null) {
            w10.H.c(eGVar);
            eGVar.H = null;
            eGVar.F = null;
        }
    }
}
