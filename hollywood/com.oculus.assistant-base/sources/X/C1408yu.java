package X;

/* renamed from: X.yu  reason: case insensitive filesystem */
public final class C1408yu implements AbstractC0105Aj {
    public final /* synthetic */ AnonymousClass3Y A00;

    public C1408yu(AnonymousClass3Y r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        if (((i0) A2L).A00 == AnonymousClass09.A0J) {
            C0112Aq A002 = C0112Aq.A00();
            AbstractC0105Aj aj = this.A00.A00;
            if (aj == null) {
                C0514bB.A03("speechStateSubscriber");
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            A002.A04(i0.class, aj);
            C0112Aq.A00().A01(new C1296wq());
        }
    }
}
