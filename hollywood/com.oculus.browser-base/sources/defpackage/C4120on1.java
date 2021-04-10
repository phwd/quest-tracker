package defpackage;

/* renamed from: on1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4120on1 extends AbstractC3094in1 {
    public C4291pn1 F;

    public C4120on1(C4291pn1 pn1) {
        this.F = pn1;
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void a(AbstractC2924hn1 hn1) {
        C4291pn1 pn1 = this.F;
        if (!pn1.f0) {
            pn1.G();
            this.F.f0 = true;
        }
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        C4291pn1 pn1 = this.F;
        int i = pn1.e0 - 1;
        pn1.e0 = i;
        if (i == 0) {
            pn1.f0 = false;
            pn1.n();
        }
        hn1.w(this);
    }
}
