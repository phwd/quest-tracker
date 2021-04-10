package X;

/* renamed from: X.tb  reason: case insensitive filesystem */
public abstract class AbstractC1135tb implements AbstractC0609cs {
    public boolean A00;
    public final t3 A01;
    public final /* synthetic */ tY A02;

    public AbstractC1135tb(tY tYVar) {
        this.A02 = tYVar;
        this.A01 = new t3(tYVar.A04.A5G());
    }

    public final void A00(boolean z) {
        tY tYVar = this.A02;
        int i = tYVar.A00;
        if (i == 6) {
            return;
        }
        if (i == 5) {
            t3 t3Var = this.A01;
            C0610ct ctVar = t3Var.A00;
            t3Var.A00 = C0610ct.A03;
            ctVar.A01();
            ctVar.A02();
            tYVar.A00 = 6;
            c9 c9Var = tYVar.A03;
            if (c9Var != null) {
                c9Var.A05(!z, tYVar);
                return;
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass08.A00("state: ", i));
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A01;
    }
}
