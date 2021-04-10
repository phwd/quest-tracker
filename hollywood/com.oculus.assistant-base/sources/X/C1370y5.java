package X;

/* renamed from: X.y5  reason: case insensitive filesystem */
public final class C1370y5 implements AbstractC0105Aj {
    public final /* synthetic */ Y2 A00;

    public C1370y5(Y2 y2) {
        this.A00 = y2;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        Y2 y2 = this.A00;
        C0514bB.A01(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        Integer num = ((i0) A2L).A00;
        C0514bB.A01(num, "event.data.state");
        Y2.A00(y2, num);
    }
}
