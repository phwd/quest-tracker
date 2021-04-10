package defpackage;

/* renamed from: o0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3987o0 implements AbstractC1641aI0 {
    public final UH0 F;

    public C3987o0(UH0 uh0) {
        this.F = uh0;
    }

    @Override // defpackage.AbstractC1641aI0
    public void b(AbstractC1821bI0 bi0, Object obj) {
        UH0 uh0 = this.F;
        KH0 kh0 = (KH0) obj;
        QH0 qh0 = AbstractC4158p0.c;
        if (kh0 == qh0) {
            if (uh0.h(qh0)) {
                int f = uh0.f(AbstractC4158p0.b);
                if (f >= 0) {
                    OH0 oh0 = AbstractC4158p0.f11043a;
                    if (f < ((C1794b90) uh0.g(oh0)).size()) {
                        AbstractC4768sc0.b(((C3148j50) ((C1794b90) uh0.g(oh0)).get(f)).f, 2);
                        return;
                    }
                    return;
                }
                return;
            }
            AbstractC4768sc0.b(0, 0);
        } else if (kh0 != AbstractC4158p0.b && kh0 != AbstractC4158p0.d && kh0 != AbstractC4158p0.e) {
            TH0 th0 = AbstractC4158p0.f;
        }
    }
}
