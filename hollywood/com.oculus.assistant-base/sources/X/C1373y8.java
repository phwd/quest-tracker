package X;

/* renamed from: X.y8  reason: case insensitive filesystem */
public final class C1373y8 implements AbstractC0105Aj {
    public final /* synthetic */ Y2 A00;

    public C1373y8(Y2 y2) {
        this.A00 = y2;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A01(ak, "event");
        C1260wG wGVar = (C1260wG) ak.A2L();
        C0387Va va = wGVar.A00;
        String str = va.A01;
        if (str != null && str.hashCode() == -1510785722 && str.equals("SpeakingStateMessage")) {
            Y2 y2 = this.A00;
            if (y2.A07.remove(va)) {
                y2.A05.A01(new C1258wE(wGVar.A00));
            }
        }
    }
}
