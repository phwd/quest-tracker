package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Nv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0844Nv0 extends AbstractC0855Oa1 {
    public final /* synthetic */ C0905Ov0 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0844Nv0(C0905Ov0 ov0, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = ov0;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        this.c.b(tab);
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        C0905Ov0 ov0 = this.c;
        if (tab == ov0.g) {
            ov0.f(null);
            this.c.e(null);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        C0905Ov0 ov0 = this.c;
        if (tab == ov0.g) {
            ov0.f(null);
            this.c.e(null);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        this.c.b(tab);
    }
}
