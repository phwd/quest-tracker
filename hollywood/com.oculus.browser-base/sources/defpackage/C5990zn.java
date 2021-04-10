package defpackage;

import android.view.ViewGroup;

/* renamed from: zn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5990zn extends AbstractC3094in1 {
    public boolean F = false;
    public final /* synthetic */ ViewGroup G;

    public C5990zn(C0096Bn bn, ViewGroup viewGroup) {
        this.G = viewGroup;
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void b(AbstractC2924hn1 hn1) {
        AbstractC3286ju1.a(this.G, true);
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        if (!this.F) {
            AbstractC3286ju1.a(this.G, false);
        }
        hn1.w(this);
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void d(AbstractC2924hn1 hn1) {
        AbstractC3286ju1.a(this.G, false);
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void e(AbstractC2924hn1 hn1) {
        AbstractC3286ju1.a(this.G, false);
        this.F = true;
    }
}
