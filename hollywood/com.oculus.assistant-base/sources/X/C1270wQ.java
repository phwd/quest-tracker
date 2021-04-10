package X;

/* renamed from: X.wQ  reason: case insensitive filesystem */
public final class C1270wQ implements AbstractC0105Aj {
    public final /* synthetic */ A1 A00;

    public C1270wQ(A1 a1) {
        this.A00 = a1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ai, "<anonymous parameter 0>");
        C0112Aq A002 = C0112Aq.A00();
        AbstractC0105Aj aj = this.A00.A00;
        if (aj == null) {
            C0514bB.A03("assistantTimeSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        A002.A04(C1296wq.class, aj);
        C0112Aq.A00().A01(new C1290wk());
    }
}
