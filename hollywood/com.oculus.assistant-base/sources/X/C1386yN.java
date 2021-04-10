package X;

/* renamed from: X.yN  reason: case insensitive filesystem */
public final class C1386yN implements AbstractC0105Aj {
    public final /* synthetic */ YJ A00;

    public C1386yN(YJ yj) {
        this.A00 = yj;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        YJ yj = this.A00;
        C0514bB.A01(ak, "event");
        Object A2L = ak.A2L();
        C0514bB.A01(A2L, "event.data");
        String str = ((vR) A2L).A00;
        if (str == null || str.length() == 0) {
            C0139Dd.A0A("AssistantSpeechServiceManager", "Got speak request with no message");
        } else {
            yj.A05.A02(str, new C1384yL(yj));
        }
    }
}
