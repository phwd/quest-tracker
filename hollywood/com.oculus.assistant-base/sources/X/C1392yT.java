package X;

/* renamed from: X.yT  reason: case insensitive filesystem */
public final class C1392yT implements AbstractC0105Aj {
    public final /* synthetic */ YM A00;

    public C1392yT(YM ym) {
        this.A00 = ym;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ak, "event");
        C1255wB wBVar = (C1255wB) ak.A2L();
        if (C0514bB.A05(wBVar.A00.A01, "VoiceSdkAssistantCommandCallback")) {
            YM ym = this.A00;
            ym.A08.add(wBVar.A00);
            ym.A07.A01(new C1257wD(wBVar.A00));
        }
    }
}
