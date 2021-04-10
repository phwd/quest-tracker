package defpackage;

import java.util.Objects;

/* renamed from: Ck1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ck1 implements KO0 {
    public final /* synthetic */ Uk1 F;

    public Ck1(Uk1 uk1) {
        this.F = uk1;
    }

    @Override // defpackage.KO0
    public void T(AbstractC2300e70 e70) {
        Vl1 vl1 = this.F.K;
        Objects.requireNonNull(e70);
        vl1.f9104a.G(e70 instanceof D11);
    }

    @Override // defpackage.KO0
    public void q(int i) {
        AbstractC0124Ca1 ca1 = this.F.U;
        this.F.o(ca1 != null ? ((AbstractC0246Ea1) ca1).o(i) : null);
        if (this.F.K.f9104a.I(true)) {
            ((Rj1) this.F.L.f10790J.G).f(null);
        }
    }
}
