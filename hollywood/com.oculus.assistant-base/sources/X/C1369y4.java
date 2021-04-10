package X;

/* renamed from: X.y4  reason: case insensitive filesystem */
public final class C1369y4 implements AbstractC0105Aj {
    public final /* synthetic */ Y2 A00;

    public C1369y4(Y2 y2) {
        this.A00 = y2;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A01(ak, "event");
        C1255wB wBVar = (C1255wB) ak.A2L();
        C0387Va va = wBVar.A00;
        String str = va.A01;
        if (str != null && str.hashCode() == -1510785722 && str.equals("SpeakingStateMessage")) {
            Y2 y2 = this.A00;
            y2.A07.add(va);
            y2.A05.A01(new C1257wD(wBVar.A00));
        }
    }
}
