package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: U2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U2 extends AbstractC0855Oa1 {
    public final /* synthetic */ C1595a3 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public U2(C1595a3 a3Var, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = a3Var;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        C1595a3.a(this.c, tab);
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        if (((AbstractC0246Ea1) this.c.K).i().getCount() <= 1) {
            C1595a3.a(this.c, null);
        }
    }
}
