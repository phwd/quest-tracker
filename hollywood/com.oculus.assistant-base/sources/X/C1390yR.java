package X;

/* renamed from: X.yR  reason: case insensitive filesystem */
public final class C1390yR implements AbstractC0105Aj {
    public final /* synthetic */ YM A00;

    public C1390yR(YM ym) {
        this.A00 = ym;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ak, "event");
        YM ym = this.A00;
        AnonymousClass8J A09 = ym.A00.A09("VoiceSDKManager", ((C1222vc) ak.A2L()).A00);
        C0514bB.A01(A09, "assistantClientPlatform.…egistry(TAG, appId, null)");
        synchronized (A09) {
            A09.A05 = false;
            AnonymousClass8J.A00(A09);
        }
    }
}
