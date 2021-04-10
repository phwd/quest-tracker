package X;

/* renamed from: X.wP  reason: case insensitive filesystem */
public final class C1269wP implements AbstractC0105Aj {
    public final /* synthetic */ A7 A00;

    public C1269wP(A7 a7) {
        this.A00 = a7;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ai, "<anonymous parameter 0>");
        C0112Aq A002 = C0112Aq.A00();
        AbstractC0105Aj aj = this.A00.A00;
        if (aj == null) {
            C0514bB.A03("assistantPhotoTakenSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        A002.A04(C1291wl.class, aj);
        C0112Aq.A00().A01(new C1290wk());
    }
}
