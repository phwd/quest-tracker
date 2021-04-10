package defpackage;

/* renamed from: L61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class L61 implements AbstractC3322k60 {
    public final U61 F;

    public L61(U61 u61) {
        this.F = u61;
    }

    @Override // defpackage.AbstractC3322k60
    public void b(boolean z) {
        U61 u61 = this.F;
        u61.b.j(AbstractC5033u71.t, z);
        if (!AbstractC4772sd1.d()) {
            u61.b.j(AbstractC5033u71.u, z);
            u61.b.j(AbstractC5033u71.v, z);
        } else if (AbstractC4772sd1.d() && !z) {
            u61.b.j(AbstractC5033u71.u, false);
        }
        if (!z) {
            u61.i();
        }
    }
}
