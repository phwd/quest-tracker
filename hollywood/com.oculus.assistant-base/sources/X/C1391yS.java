package X;

/* renamed from: X.yS  reason: case insensitive filesystem */
public final class C1391yS implements AbstractC0105Aj {
    public final /* synthetic */ YM A00;

    public C1391yS(YM ym) {
        this.A00 = ym;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ak, "event");
        C1260wG wGVar = (C1260wG) ak.A2L();
        if (C0514bB.A05(wGVar.A00.A01, "VoiceSdkAssistantCommandCallback")) {
            YM ym = this.A00;
            if (ym.A08.remove(wGVar.A00)) {
                ym.A07.A01(new C1258wE(wGVar.A00));
            }
        }
    }
}
