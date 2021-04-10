package X;

/* renamed from: X.gU  reason: case insensitive filesystem */
public abstract class AbstractC0744gU implements AbstractC0105Aj {
    public final /* synthetic */ C0651dw A00;

    public AbstractC0744gU(C0651dw dwVar) {
        this.A00 = dwVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ai, "busContext");
        C0514bB.A02(ak, "event");
        this.A00.A00.post(new gT(this, ai, ak));
    }
}
