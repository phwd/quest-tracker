package X;

/* renamed from: X.yW  reason: case insensitive filesystem */
public final class C1395yW implements AbstractC0105Aj {
    public final /* synthetic */ YM A00;

    public C1395yW(YM ym) {
        this.A00 = ym;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ak, "event");
        YM ym = this.A00;
        C1224ve veVar = (C1224ve) ak.A2L();
        AnonymousClass8J A09 = ym.A00.A09("VoiceSDKManager", veVar.A01);
        C0514bB.A01(A09, "assistantClientPlatform.…egistry(TAG, appId, null)");
        String str = veVar.A00;
        synchronized (A09) {
            A09.A08.remove(str);
            A09.A07.remove(str);
            AnonymousClass8J.A00(A09);
        }
    }
}
